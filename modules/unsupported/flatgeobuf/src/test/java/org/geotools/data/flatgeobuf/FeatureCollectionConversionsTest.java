package org.geotools.data.flatgeobuf;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;
import java.util.Iterator;
import org.geotools.util.URLs;
import org.junit.Test;
import org.locationtech.jts.geom.Envelope;
import org.opengis.feature.simple.SimpleFeature;

public class FeatureCollectionConversionsTest {
    @Test
    public void countriesTest() throws IOException {
        File file =
                URLs.urlToFile(
                        getClass()
                                .getClassLoader()
                                .getResource("org/geotools/data/flatgeobuf/countries.fgb"));
        byte[] bytes = Files.readAllBytes(file.toPath());
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        bb.order(ByteOrder.LITTLE_ENDIAN);

        Iterator<SimpleFeature> it = FeatureCollectionConversions.deserialize(bb, null).iterator();
        int count = 0;
        while (it.hasNext()) {
            it.next();
            count++;
        }
        assertEquals(179, count);
    }

    @Test
    public void countriesTestFilter() throws IOException {
        File file =
                URLs.urlToFile(
                        getClass()
                                .getClassLoader()
                                .getResource("org/geotools/data/flatgeobuf/countries.fgb"));
        try (FileChannel fileChannel =
                (FileChannel)
                        Files.newByteChannel(file.toPath(), EnumSet.of(StandardOpenOption.READ))) {
            MappedByteBuffer mappedByteBuffer =
                    fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
            mappedByteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            Envelope rect = new Envelope(12, 12, 56, 56);
            Iterator<SimpleFeature> it =
                    FeatureCollectionConversions.deserialize(mappedByteBuffer, rect).iterator();
            int count = 0;
            while (it.hasNext()) {
                it.next();
                count++;
            }
            assertEquals(3, count);
        }
    }
}
