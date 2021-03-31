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

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import org.geotools.data.FeatureReader;
import org.geotools.data.FeatureWriter;
import org.geotools.data.FileDataStore;
import org.geotools.data.Query;
import org.geotools.data.Transaction;
import org.geotools.data.store.ContentDataStore;
import org.geotools.data.store.ContentEntry;
import org.geotools.data.store.ContentFeatureSource;
import org.geotools.feature.NameImpl;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.Name;
import org.opengis.filter.Filter;
import org.wololo.flatgeobuf.ColumnMeta;
import org.wololo.flatgeobuf.GeometryConversions;
import org.wololo.flatgeobuf.HeaderMeta;

public class FlatGeobufDataStore extends ContentDataStore implements FileDataStore {

    private File file;
    private HeaderMeta headerMeta;
    private String typeName;

    public FlatGeobufDataStore(URL url) throws IOException {
        try {
            this.file = Paths.get(url.toURI()).toFile();
            this.typeName = removeFileExtension(file.getName(), true);
        } catch (URISyntaxException e) {
            throw new IOException(e);
        }
    }

    protected static String removeFileExtension(String filename, boolean removeAllExtensions) {
        if (filename == null || filename.isEmpty()) return filename;
        String extPattern = "(?<!^)[.]" + (removeAllExtensions ? ".*" : "[^.]*$");
        return filename.replaceAll(extPattern, "");
    }

    protected HeaderMeta getHeaderMeta() throws IOException {
        if (headerMeta == null) {
            try (FileChannel fileChannel =
                    (FileChannel)
                            Files.newByteChannel(
                                    file.toPath(), EnumSet.of(StandardOpenOption.READ))) {
                MappedByteBuffer bb =
                        fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
                bb.order(ByteOrder.LITTLE_ENDIAN);
                this.headerMeta = HeaderMeta.read(bb);
            }
        }
        return headerMeta;
    }

    protected File getFile() {
        return file;
    }

    protected SimpleFeatureType getFeatureType(Name name) throws IOException {
        getHeaderMeta();
        SimpleFeatureTypeBuilder ftb = new SimpleFeatureTypeBuilder();
        ftb.setName(name);
        ftb.setAbstract(false);
        ftb.add("geom", GeometryConversions.getGeometryClass(headerMeta.geometryType));
        for (ColumnMeta columnMeta : headerMeta.columns)
            ftb.add(columnMeta.name, columnMeta.getBinding());
        SimpleFeatureType featureType = ftb.buildFeatureType();
        return featureType;
    }

    @Override
    protected List<Name> createTypeNames() throws IOException {
        return Collections.singletonList(getTypeName());
    }

    Name getTypeName() throws IOException {
        getHeaderMeta();
        return new NameImpl(namespaceURI, typeName);
    }

    @Override
    public void createSchema(SimpleFeatureType featureType) {
        throw new RuntimeException("Cannot create schema");
    }

    @Override
    protected ContentFeatureSource createFeatureSource(ContentEntry entry) throws IOException {
        return getFeatureSource();
    }

    @Override
    public ContentFeatureSource getFeatureSource() throws IOException {
        ContentEntry entry = ensureEntry(getTypeName());
        return new FlatGeobufFeatureSource(entry, Query.ALL);
    }

    @Override
    public void removeSchema(Name typeName) throws IOException {
        this.removeSchema(typeName.getLocalPart());
    }

    @Override
    public void removeSchema(String typeName) throws IOException {
        if (!file.exists()) {
            throw new IOException(
                    "Can't delete " + file.getAbsolutePath() + " because it doesn't exist!");
        }
        file.delete();
    }

    @Override
    public SimpleFeatureType getSchema() throws IOException {
        return getSchema(getTypeName());
    }

    @Override
    public void updateSchema(SimpleFeatureType featureType) throws IOException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public FeatureReader<SimpleFeatureType, SimpleFeature> getFeatureReader() throws IOException {
        return super.getFeatureReader(
                new Query(getTypeName().getLocalPart()), Transaction.AUTO_COMMIT);
    }

    @Override
    public FeatureWriter<SimpleFeatureType, SimpleFeature> getFeatureWriter(
            Filter filter, Transaction transaction) throws IOException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public FeatureWriter<SimpleFeatureType, SimpleFeature> getFeatureWriter(Transaction transaction)
            throws IOException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public FeatureWriter<SimpleFeatureType, SimpleFeature> getFeatureWriterAppend(
            Transaction transaction) throws IOException {
        throw new RuntimeException("Not implemented");
    }
}
