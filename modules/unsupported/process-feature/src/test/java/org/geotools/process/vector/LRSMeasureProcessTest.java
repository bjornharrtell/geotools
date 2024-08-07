/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2012, Open Source Geospatial Foundation (OSGeo)
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
package org.geotools.process.vector;

import java.io.File;
import java.io.IOException;
import org.geotools.api.data.DataStore;
import org.geotools.api.data.SimpleFeatureSource;
import org.geotools.api.feature.Feature;
import org.geotools.api.feature.type.FeatureType;
import org.geotools.data.property.PropertyDataStore;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.feature.DefaultFeatureCollection;
import org.geotools.feature.FeatureCollection;
import org.geotools.process.ProcessException;
import org.geotools.test.TestData;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

public class LRSMeasureProcessTest {
    private DataStore featureSource;

    private final GeometryFactory geometryFactory = new GeometryFactory();

    @Before
    public void setup() throws IOException {
        File file = TestData.file(this, null);
        featureSource = new PropertyDataStore(file);
    }

    @After
    public void tearDown() {
        featureSource.dispose();
    }

    @Test
    public void testBadParamFromLrs() throws Exception {
        SimpleFeatureSource source = featureSource.getFeatureSource("lrssimple");
        LRSMeasureProcess process = new LRSMeasureProcess();
        SimpleFeatureCollection origional = source.getFeatures();
        Point point = geometryFactory.createPoint(new Coordinate(1.0, 0.0));

        try {
            process.execute(origional, "from_lrs_bad", "to_lrs", point, null);
            Assert.fail("Expected error from bad from_lrs name");
        } catch (ProcessException e) {
            // Successful
        }
    }

    @Test
    public void testBadParamToLrs() throws Exception {
        SimpleFeatureSource source = featureSource.getFeatureSource("lrssimple");
        LRSMeasureProcess process = new LRSMeasureProcess();
        SimpleFeatureCollection origional = source.getFeatures();
        Point point = geometryFactory.createPoint(new Coordinate(1.0, 0.0));

        try {
            process.execute(origional, "from_lrs", "to_lrs_bad", point, null);
            Assert.fail("Expected error from bad to_lrs name");
        } catch (ProcessException e) {
            // Successful
        }
    }

    @Test
    public void testnullParamFromLrs() throws Exception {
        SimpleFeatureSource source = featureSource.getFeatureSource("lrssimple");
        LRSMeasureProcess process = new LRSMeasureProcess();
        SimpleFeatureCollection origional = source.getFeatures();
        Point point = geometryFactory.createPoint(new Coordinate(1.0, 0.0));

        try {
            process.execute(origional, null, "to_lrs", point, null);
            Assert.fail("Expected error from bad from_lrs name");
        } catch (ProcessException e) {
            // Successful
        }
    }

    @Test
    public void testNullParamToLrs() throws Exception {
        SimpleFeatureSource source = featureSource.getFeatureSource("lrssimple");
        LRSMeasureProcess process = new LRSMeasureProcess();
        SimpleFeatureCollection origional = source.getFeatures();
        Point point = geometryFactory.createPoint(new Coordinate(1.0, 0.0));

        try {
            process.execute(origional, "from_lrs", null, point, null);
            Assert.fail("Expected error from bad to_lrs name");
        } catch (ProcessException e) {
            // Successful
        }
    }

    @Test
    public void testBadPoint() throws Exception {
        SimpleFeatureSource source = featureSource.getFeatureSource("lrssimple");
        LRSMeasureProcess process = new LRSMeasureProcess();
        SimpleFeatureCollection origional = source.getFeatures();

        try {
            process.execute(origional, "from_lrs", "to_lrs_bad", null, null);
            Assert.fail("Expected error from bad measure value");
        } catch (ProcessException e) {
            // Successful
        }
    }

    @Test
    public void testNoFeaturesGiven() throws Exception {
        LRSMeasureProcess process = new LRSMeasureProcess();
        DefaultFeatureCollection origional = new DefaultFeatureCollection();
        Point point = geometryFactory.createPoint(new Coordinate(1.0, 0.0));

        FeatureCollection<? extends FeatureType, ? extends Feature> result =
                process.execute(origional, "from_lrs", "to_lrs", point, null);
        Assert.assertEquals(0, result.size());
    }

    @Test
    public void testGoodMeasure() throws Exception {
        SimpleFeatureSource source = featureSource.getFeatureSource("lrssimple");
        LRSMeasureProcess process = new LRSMeasureProcess();
        SimpleFeatureCollection origional = source.getFeatures();
        Point point = geometryFactory.createPoint(new Coordinate(1.0, 0.0));

        FeatureCollection result = process.execute(origional, "from_lrs", "to_lrs", point, null);
        Assert.assertEquals(1, result.size());
        Feature feature = result.features().next();
        Assert.assertNotNull(feature.getProperty("lrs_measure"));
        Assert.assertNotNull(feature.getProperty("lrs_measure").getValue());
        Double measure = (Double) feature.getProperty("lrs_measure").getValue();
        Assert.assertEquals(1.0, measure, 0.0);
    }
}
