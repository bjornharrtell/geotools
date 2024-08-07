/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2002-2009, Open Source Geospatial Foundation (OSGeo)
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

package org.geotools.gml3.bindings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.geotools.api.feature.ComplexAttribute;
import org.geotools.gml3.ComplexAttributeTestSupport;
import org.geotools.gml3.GML;
import org.geotools.measure.Measure;
import org.junit.Test;
import org.w3c.dom.Document;

public class MeasureTypeBindingTest extends ComplexAttributeTestSupport {
    @Test
    public void testParser() throws Exception {
        GML3MockData.element(GML.measure, document, document);
        document.getDocumentElement().setAttribute("uom", "http://someuri");
        document.getDocumentElement().appendChild(document.createTextNode("1234"));
        Measure measure = (Measure) parse();
        assertNotNull(measure);
        assertEquals(1234, measure.doubleValue(), 0.1);

        assertEquals("http://someuri", measure.getUnit().getSymbol());
    }

    @Test
    public void testEncode() throws Exception {
        ComplexAttribute myCode = gmlMeasureType(GML.measure, "12", "m");
        Document dom = encode(myCode, GML.measure);
        // print(dom);
        assertEquals("gml:measure", dom.getDocumentElement().getNodeName());
        assertEquals("12", dom.getDocumentElement().getFirstChild().getNodeValue());
        assertNotNull(dom.getDocumentElement().getAttribute("uom"));
        assertEquals("m", dom.getDocumentElement().getAttribute("uom"));
    }
}
