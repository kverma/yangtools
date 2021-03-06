/*
 * Copyright (c) 2015 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.yangtools.yang.model.export;

import java.util.List;
import org.opendaylight.yangtools.yang.model.api.TypeDefinition;
import org.opendaylight.yangtools.yang.model.api.type.UnionTypeDefinition;
import org.opendaylight.yangtools.yang.model.util.ExtendedType;

class DerivedUnion extends NormalizatedDerivedType<UnionTypeDefinition> implements UnionTypeDefinition {

    public DerivedUnion(final ExtendedType definition) {
        super(UnionTypeDefinition.class, definition);
    }

    @Override
    UnionTypeDefinition createDerived(final ExtendedType base) {
        return new DerivedUnion(base);
    }

    @Override
    public List<TypeDefinition<?>> getTypes() {
        return getBaseType().getTypes();
    }

}