/*
 * Copyright (c) 2013 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.yangtools.yang.data.impl;

import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.data.api.CompositeNode;
import org.opendaylight.yangtools.yang.data.api.ModifyAction;
import org.opendaylight.yangtools.yang.data.api.MutableSimpleNode;
import org.opendaylight.yangtools.yang.data.api.SimpleNode;
import org.opendaylight.yangtools.yang.data.api.schema.NormalizedNode;

/**
 * @author michal.rehak
 * @param <T>
 *            type of simple node value
 *
 * @deprecated Use one of the {@link NormalizedNode} implementation packages.
 */
@Deprecated
public class MutableSimpleNodeTOImpl<T> extends SimpleNodeTOImpl<T> implements MutableSimpleNode<T> {
    private static final long serialVersionUID = 1L;
    private SimpleNode<T> original;

    /**
     * @param qname
     * @param parent
     * @param value
     * @param modifyAction
     */
    public MutableSimpleNodeTOImpl(final QName qname, final CompositeNode parent, final T value, final ModifyAction modifyAction) {
        super(qname, parent, value, modifyAction);
    }

    @Override
    public void setModifyAction(final ModifyAction action) {
        super.setModificationAction(action);
    }

    @Override
    public MutableSimpleNode<T> asMutable() {
        return this;
    }

    @Override
    public SimpleNode<T> getOriginal() {
        return original;
    }

    /**
     * @param original
     *            the original to set
     */
    public void setOriginal(final SimpleNode<T> original) {
        this.original = original;
    }
}
