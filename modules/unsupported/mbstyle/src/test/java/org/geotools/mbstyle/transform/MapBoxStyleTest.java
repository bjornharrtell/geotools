/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2017, Open Source Geospatial Foundation (OSGeo)
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
package org.geotools.mbstyle.transform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.mbstyle.MBFillLayer;
import org.geotools.mbstyle.MBLayer;
import org.geotools.mbstyle.MBStyle;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.opengis.filter.FilterFactory2;
import org.opengis.style.FeatureTypeStyle;
import org.opengis.style.PolygonSymbolizer;
import org.opengis.style.Rule;
import org.opengis.style.Symbolizer;

/**
 * Test parsing and transforming a Mapbox fill layer from json.
 */
public class MapBoxStyleTest {

    static FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();

    JSONParser jsonParser = new JSONParser();

    @Test
    public void testFill() throws IOException, ParseException {

        // Read file to JSONObject
        InputStream is = this.getClass().getResourceAsStream("fillStyleTest.json");
        String fileContents = IOUtils.toString(is, "utf-8");
        JSONObject jsonObject = (JSONObject) jsonParser.parse(fileContents);

        // Parse to MBStyle
        MBStyle mbStyle = new MBStyle(jsonObject);
        List<MBLayer> layers = mbStyle.layers("geoserver-states");

        assertEquals(1, layers.size());

        // Find the MBFillLayer and assert it contains the correct FeatureTypeStyle.
        assertTrue(layers.get(0) instanceof MBFillLayer);
        MBFillLayer mbFill = (MBFillLayer) layers.get(0);
        FeatureTypeStyle fts = new MBStyleTransformer().transform(mbFill);

        assertEquals(1, fts.rules().size());
        for (Rule r : fts.rules()) {
            assertEquals(1, r.symbolizers().size());
            for (Symbolizer symbolizer : r.symbolizers()) {
                assertTrue(PolygonSymbolizer.class.isAssignableFrom(symbolizer.getClass()));
                PolygonSymbolizer psym = (PolygonSymbolizer) symbolizer;
                assertTrue("#e100ff"
                        .equalsIgnoreCase((String) psym.getFill().getColor().evaluate(null)));
                assertEquals(Double.valueOf(.84),
                        psym.getFill().getOpacity().evaluate(null, Double.class));
            }
        }

    }

}