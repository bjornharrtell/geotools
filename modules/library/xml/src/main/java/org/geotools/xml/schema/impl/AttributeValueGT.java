/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2004-2008, Open Source Geospatial Foundation (OSGeo)
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
package org.geotools.xml.schema.impl;

import org.geotools.xml.schema.Attribute;
import org.geotools.xml.schema.AttributeValue;

/** @author dzwiers */
public class AttributeValueGT implements AttributeValue {
    private String value;
    private Attribute attribute;

    private AttributeValueGT() {
        // do nothing
    }

    /** Creates a new AttributeValueGT object. */
    public AttributeValueGT(Attribute attribute, String value) {
        this.attribute = attribute;
        this.value = value;
    }

    /** @see org.geotools.xml.schema.AttributeValue#getValue() */
    @Override
    public String getValue() {
        return value;
    }

    /** @see org.geotools.xml.schema.AttributeValue#getAttribute() */
    @Override
    public Attribute getAttribute() {
        return attribute;
    }
}
