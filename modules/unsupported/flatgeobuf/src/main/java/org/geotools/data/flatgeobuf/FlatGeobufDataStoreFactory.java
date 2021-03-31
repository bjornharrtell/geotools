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

import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import org.geotools.data.DataStore;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFactorySpi;
import org.geotools.data.store.ContentDataStore;
import org.geotools.util.KVP;
import org.geotools.util.logging.Logging;

public class FlatGeobufDataStoreFactory implements FileDataStoreFactorySpi {

    private static final Logger LOGGER = Logging.getLogger(FlatGeobufDataStoreFactory.class);

    public static final Param URL_PARAM =
            new Param(
                    "url",
                    URL.class,
                    "The FlatGeobuf file or directory",
                    true,
                    null,
                    new KVP(Param.EXT, "fgb"));

    public static final Param NAMESPACE_PARAM =
            new Param(
                    "namespace",
                    URI.class,
                    "uri to a the namespace",
                    false,
                    null, // not required
                    new KVP(Param.LEVEL, "advanced"));

    public FlatGeobufDataStoreFactory() {}

    @Override
    public DataStore createDataStore(Map<String, ?> map) throws IOException {
        URL url = (URL) URL_PARAM.lookUp(map);
        URI namespace = (URI) NAMESPACE_PARAM.lookUp(map);
        File file;
        try {
            file = Paths.get(url.toURI()).toFile();
        } catch (URISyntaxException e) {
            throw new IOException(e);
        }

        ContentDataStore store;
        if (file.isDirectory()) {
            store = new FlatGeobufDirectoryDataStore(file);
        } else {
            store = new FlatGeobufDataStore(url);
        }
        if (namespace != null) {
            store.setNamespaceURI(namespace.toString());
        }
        return store;
    }

    @Override
    public FileDataStore createDataStore(URL url) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put(URL_PARAM.key, url);
        return (FileDataStore) createDataStore(params);
    }

    @Override
    public DataStore createNewDataStore(Map<String, ?> map) throws IOException {
        URL url = (URL) URL_PARAM.lookUp(map);
        File file;
        try {
            file = Paths.get(url.toURI()).toFile();
        } catch (URISyntaxException e) {
            throw new IOException(e);
        }
        if (file.isDirectory()) {
            return new FlatGeobufDirectoryDataStore(file);
        } else {
            if (file.exists()) {
                LOGGER.warning("File already exists: " + file);
            }
            return new FlatGeobufDataStore(url);
        }
    }

    @Override
    public String getTypeName(URL url) throws IOException {
        DataStore ds = createDataStore(url);
        String[] names = ds.getTypeNames(); // should be exactly one
        ds.dispose();
        return ((names == null || names.length == 0) ? null : names[0]);
    }

    @Override
    public String getDisplayName() {
        return "FlatGeobuf";
    }

    @Override
    public String getDescription() {
        return "A DataStore for reading and writing FlatGeobuf files";
    }

    @Override
    public Param[] getParametersInfo() {
        return new Param[] {URL_PARAM, NAMESPACE_PARAM};
    }

    @Override
    public boolean canProcess(Map<String, ?> map) {
        try {
            URL url = (URL) URL_PARAM.lookUp(map);
            File file;
            try {
                file = Paths.get(url.toURI()).toFile();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
            if (file != null) {
                return file.isDirectory() || file.getPath().toLowerCase().endsWith(".fgb");
            }
        } catch (IOException e) {
            // ignore as we are expected to return true or false
        }
        return false;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public Map<RenderingHints.Key, ?> getImplementationHints() {
        return null;
    }

    @Override
    public String[] getFileExtensions() {
        return new String[] {".fgb"};
    }

    @Override
    public boolean canProcess(URL url) {
        return url != null && url.getFile().toLowerCase().endsWith("fgb");
    }
}
