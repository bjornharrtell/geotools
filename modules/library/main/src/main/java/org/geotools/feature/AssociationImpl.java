/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2002-2008, Open Source Geospatial Foundation (OSGeo)
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
package org.geotools.feature;

import org.geotools.api.feature.Association;
import org.geotools.api.feature.Attribute;
import org.geotools.api.feature.type.AssociationDescriptor;
import org.geotools.api.feature.type.AssociationType;
import org.geotools.api.feature.type.AttributeType;

public class AssociationImpl extends PropertyImpl implements Association {

    protected AssociationImpl(Attribute value, AssociationDescriptor descriptor) {
        super(value, descriptor);
    }

    @Override
    public AttributeType getRelatedType() {
        return getType().getRelatedType();
    }

    @Override
    public AssociationDescriptor getDescriptor() {
        return (AssociationDescriptor) super.getDescriptor();
    }

    @Override
    public AssociationType getType() {
        return (AssociationType) super.getType();
    }

    @Override
    public Attribute getValue() {
        return (Attribute) super.getValue();
    }
}
