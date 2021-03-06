/*
 * Copyright (c) 2013 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.yangtools.yang.data.impl.schema.builder.impl;

import java.util.Map;

import org.opendaylight.yangtools.yang.data.api.YangInstanceIdentifier;
import org.opendaylight.yangtools.yang.data.api.schema.ChoiceNode;
import org.opendaylight.yangtools.yang.data.api.schema.DataContainerChild;
import org.opendaylight.yangtools.yang.data.impl.schema.builder.api.DataContainerNodeBuilder;
import org.opendaylight.yangtools.yang.data.impl.schema.nodes.AbstractImmutableDataContainerNode;

public class ImmutableChoiceNodeBuilder extends AbstractImmutableDataContainerNodeBuilder<YangInstanceIdentifier.NodeIdentifier, ChoiceNode> {

    protected ImmutableChoiceNodeBuilder() {
        super();
    }

    protected ImmutableChoiceNodeBuilder(final int sizeHint) {
        super(sizeHint);
    }

    protected ImmutableChoiceNodeBuilder(final ImmutableChoiceNode node) {
        super(node);
    }

    public static DataContainerNodeBuilder<YangInstanceIdentifier.NodeIdentifier, ChoiceNode> create() {
        return new ImmutableChoiceNodeBuilder();
    }

    public static DataContainerNodeBuilder<YangInstanceIdentifier.NodeIdentifier, ChoiceNode> create(final int sizeHint) {
        return new ImmutableChoiceNodeBuilder(sizeHint);
    }

    public static DataContainerNodeBuilder<YangInstanceIdentifier.NodeIdentifier, ChoiceNode> create(final ChoiceNode node) {
        if (!(node instanceof ImmutableChoiceNode)) {
            throw new UnsupportedOperationException(String.format("Cannot initialize from class %s", node.getClass()));
        }

        return new ImmutableChoiceNodeBuilder((ImmutableChoiceNode)node);
    }

    @Override
    public ChoiceNode build() {
        return new ImmutableChoiceNode(getNodeIdentifier(), buildValue());
    }

    private static final class ImmutableChoiceNode extends AbstractImmutableDataContainerNode<YangInstanceIdentifier.NodeIdentifier> implements ChoiceNode {

        ImmutableChoiceNode(final YangInstanceIdentifier.NodeIdentifier nodeIdentifier,
                final Map<YangInstanceIdentifier.PathArgument, DataContainerChild<? extends YangInstanceIdentifier.PathArgument, ?>> children) {
            super(children, nodeIdentifier);
        }
    }
}
