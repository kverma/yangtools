/*
 * Copyright (c) 2014 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.yangtools.yang.data.codec.gson;

import org.opendaylight.yangtools.yang.model.api.DataSchemaNode;

abstract class SimpleNodeDataWithSchema extends AbstractNodeDataWithSchema {

    private Object value;

    public SimpleNodeDataWithSchema(final DataSchemaNode dataSchemaNode) {
        super(dataSchemaNode);
    }

    void setValue(final Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

}
