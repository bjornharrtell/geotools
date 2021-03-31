/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2019, Open Source Geospatial Foundation (OSGeo)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package org.geotools.data.flatgeobuf;

import static com.google.flatbuffers.Constants.SIZE_PREFIX_LENGTH;

import com.google.flatbuffers.ByteBufferUtil;
import com.google.flatbuffers.FlatBufferBuilder;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.geotools.data.memory.MemoryFeatureCollection;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.locationtech.jts.geom.Envelope;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.wololo.flatgeobuf.Constants;
import org.wololo.flatgeobuf.HeaderMeta;
import org.wololo.flatgeobuf.PackedRTree;
import org.wololo.flatgeobuf.PackedRTree.SearchHit;
import org.wololo.flatgeobuf.generated.Feature;

public class FeatureCollectionConversions {

    private static final class ReadHitsIterable implements Iterable<SimpleFeature> {
        private final SimpleFeatureBuilder fb;
        private final ArrayList<SearchHit> hits;
        private final HeaderMeta headerMeta;
        private final int featuresOffset;
        private final ByteBuffer bb;

        private ReadHitsIterable(
                SimpleFeatureBuilder fb,
                ArrayList<SearchHit> hits,
                HeaderMeta headerMeta,
                int featuresOffset,
                ByteBuffer bb) {
            this.fb = fb;
            this.hits = hits;
            this.headerMeta = headerMeta;
            this.featuresOffset = featuresOffset;
            this.bb = bb;
        }

        @Override
        public Iterator<SimpleFeature> iterator() {
            Iterator<SimpleFeature> it =
                    new Iterator<SimpleFeature>() {
                        int i = 0;

                        @Override
                        public boolean hasNext() {
                            return i < hits.size();
                        }

                        @Override
                        public SimpleFeature next() {
                            if (!hasNext()) throw new NoSuchElementException();
                            SearchHit hit = hits.get(i);
                            int offset = featuresOffset + (int) hit.offset;
                            bb.position(offset);
                            int featureSize = ByteBufferUtil.getSizePrefix(bb);
                            bb.position(offset += SIZE_PREFIX_LENGTH);
                            Feature feature = Feature.getRootAsFeature(bb);
                            bb.position(offset += featureSize);
                            SimpleFeature f =
                                    FeatureConversions.deserialize(
                                            feature, fb, headerMeta, hit.index);
                            i++;
                            return f;
                        }
                    };
            return it;
        }
    }

    private static final class ReadAllInterable implements Iterable<SimpleFeature> {
        private final HeaderMeta headerMeta;
        private final int featuresOffset;
        private final ByteBuffer bb;
        private final SimpleFeatureBuilder fb;

        private ReadAllInterable(
                HeaderMeta headerMeta, int featuresOffset, ByteBuffer bb, SimpleFeatureBuilder fb) {
            this.headerMeta = headerMeta;
            this.featuresOffset = featuresOffset;
            this.bb = bb;
            this.fb = fb;
            this.bb.position(featuresOffset);
        }

        @Override
        public Iterator<SimpleFeature> iterator() {
            Iterator<SimpleFeature> it =
                    new Iterator<SimpleFeature>() {
                        long count = 0;
                        int offset = featuresOffset;

                        @Override
                        public boolean hasNext() {
                            return bb.hasRemaining();
                        }

                        @Override
                        public SimpleFeature next() {
                            if (!hasNext()) throw new NoSuchElementException();
                            int featureSize = ByteBufferUtil.getSizePrefix(bb);
                            bb.position(offset += SIZE_PREFIX_LENGTH);
                            Feature feature = Feature.getRootAsFeature(bb);
                            bb.position(offset += featureSize);
                            SimpleFeature f =
                                    FeatureConversions.deserialize(
                                            feature, fb, headerMeta, count++);
                            return f;
                        }
                    };
            return it;
        }
    }

    public static void serialize(
            SimpleFeatureCollection featureCollection,
            long featuresCount,
            OutputStream outputStream)
            throws IOException {
        SimpleFeatureType featureType = featureCollection.getSchema();
        FlatBufferBuilder builder = FlatBuffers.newBuilder(16 * 1024);
        try {
            HeaderMeta headerMeta = HeaderMetaUtil.fromFeatureType(featureType, featuresCount);
            outputStream.write(Constants.MAGIC_BYTES);
            HeaderMeta.write(headerMeta, outputStream, builder);
            builder.clear();
            try (FeatureIterator<SimpleFeature> iterator = featureCollection.features()) {
                while (iterator.hasNext()) {
                    SimpleFeature feature = iterator.next();
                    FeatureConversions.serialize(feature, headerMeta, outputStream, builder);
                    builder.clear();
                }
            }
        } finally {
            FlatBuffers.release(builder);
        }
    }

    public static SimpleFeatureCollection deserialize(ByteBuffer bb) throws IOException {
        HeaderMeta headerMeta = HeaderMeta.read(bb);
        SimpleFeatureType featureType = HeaderMetaUtil.toFeatureType(headerMeta, "unknown");
        return deserialize(bb, headerMeta, featureType);
    }

    public static SimpleFeatureCollection deserialize(
            ByteBuffer bb, HeaderMeta headerMeta, SimpleFeatureType ft) throws IOException {
        Iterator<SimpleFeature> it = deserialize(bb, headerMeta, ft, null).iterator();
        MemoryFeatureCollection fc = new MemoryFeatureCollection(ft);
        while (it.hasNext()) fc.add(it.next());
        return fc;
    }

    public static Iterable<SimpleFeature> deserialize(ByteBuffer bb, Envelope rect)
            throws IOException {
        HeaderMeta headerMeta = HeaderMeta.read(bb);
        SimpleFeatureType featureType = HeaderMetaUtil.toFeatureType(headerMeta, "unknown");
        return deserialize(bb, headerMeta, featureType, rect);
    }

    public static Iterable<SimpleFeature> deserialize(
            ByteBuffer bb, HeaderMeta headerMeta, SimpleFeatureType ft, Envelope rect)
            throws IOException {
        int treeSize =
                headerMeta.featuresCount > 0 && headerMeta.indexNodeSize > 0
                        ? (int)
                                PackedRTree.calcSize(
                                        (int) headerMeta.featuresCount, headerMeta.indexNodeSize)
                        : 0;
        int featuresOffset = headerMeta.offset + treeSize;
        SimpleFeatureBuilder fb = new SimpleFeatureBuilder(ft);
        if (treeSize > 0) bb.position(featuresOffset);

        Iterable<SimpleFeature> iterable;
        if (rect == null) {
            iterable = new ReadAllInterable(headerMeta, featuresOffset, bb, fb);
        } else {
            ArrayList<SearchHit> hits =
                    new PackedRTree()
                            .search(
                                    bb,
                                    headerMeta.offset,
                                    (int) headerMeta.featuresCount,
                                    headerMeta.indexNodeSize,
                                    rect);
            iterable = new ReadHitsIterable(fb, hits, headerMeta, featuresOffset, bb);
        }

        return iterable;
    }
}
