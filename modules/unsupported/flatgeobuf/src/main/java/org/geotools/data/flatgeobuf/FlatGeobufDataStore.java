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
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import org.geotools.data.Query;
import org.geotools.data.store.ContentDataStore;
import org.geotools.data.store.ContentEntry;
import org.geotools.data.store.ContentFeatureSource;
import org.geotools.feature.NameImpl;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.Name;
import org.wololo.flatgeobuf.ColumnMeta;
import org.wololo.flatgeobuf.GeometryConversions;
import org.wololo.flatgeobuf.HeaderMeta;

public class FlatGeobufDataStore extends ContentDataStore {

    private File file;
    private HeaderMeta headerMeta;
    private String typeName;
    private SimpleFeatureType createFeatureType;

    public FlatGeobufDataStore(File file) {
        this.file = file;
    }

    protected static String removeFileExtension(String filename, boolean removeAllExtensions) {
        if (filename == null || filename.isEmpty()) return filename;
        String extPattern = "(?<!^)[.]" + (removeAllExtensions ? ".*" : "[^.]*$");
        return filename.replaceAll(extPattern, "");
    }

    protected HeaderMeta getHeaderMeta() throws IOException {
        String fileName = removeFileExtension(file.getName(), true);
        if (!file.exists() || file.length() == 0) {
            typeName = fileName;
            return null;
        }
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
            String name = this.headerMeta.name;
            if (name == null || name.isEmpty()) {
                LOGGER.info("No name in header will use file name " + fileName);
                this.typeName = fileName;
            } else {
                LOGGER.info("Using name found in header as typeName " + name);
                this.typeName = name;
            }
        }
        return headerMeta;
    }

    protected File getFile() {
        return file;
    }

    protected SimpleFeatureType getFeatureType() throws IOException {
        getHeaderMeta();
        if (headerMeta != null) {
            SimpleFeatureTypeBuilder ftb = new SimpleFeatureTypeBuilder();
            ftb.setName(typeName);
            ftb.setAbstract(false);
            ftb.add("geom", GeometryConversions.getGeometryClass(headerMeta.geometryType));
            for (ColumnMeta columnMeta : headerMeta.columns)
                ftb.add(columnMeta.name, columnMeta.getBinding());
            SimpleFeatureType featureType = ftb.buildFeatureType();
            return featureType;
        } else if (createFeatureType != null) {
            return createFeatureType;
        }
        throw new RuntimeException("Could not get FeatureType");
    }

    @Override
    protected List<Name> createTypeNames() throws IOException {
        getHeaderMeta();
        Name name = new NameImpl(typeName);
        return Collections.singletonList(name);
    }

    @Override
    public void createSchema(SimpleFeatureType featureType) {
        this.createFeatureType = featureType;
    }

    @Override
    protected ContentFeatureSource createFeatureSource(ContentEntry contentEntry)
            throws IOException {
        if (!file.exists() || file.canWrite()) {
            return new FlatGeobufFeatureStore(contentEntry, Query.ALL);
        } else {
            return new FlatGeobufFeatureSource(contentEntry, Query.ALL);
        }
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
}
