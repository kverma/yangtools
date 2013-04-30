/*
  * Copyright (c) 2013 Cisco Systems, Inc. and others.  All rights reserved.
  *
  * This program and the accompanying materials are made available under the
  * terms of the Eclipse Public License v1.0 which accompanies this distribution,
  * and is available at http://www.eclipse.org/legal/epl-v10.html
  */
package org.opendaylight.controller.yang.model.util;

import java.net.URI;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.opendaylight.controller.yang.common.QName;
import org.opendaylight.controller.yang.model.api.SchemaPath;
import org.opendaylight.controller.yang.model.api.Status;
import org.opendaylight.controller.yang.model.api.UnknownSchemaNode;
import org.opendaylight.controller.yang.model.api.type.IdentityrefTypeDefinition;

/**
 * The <code>default</code> implementation of Identityref Type Definition interface.
 *
 * @see IdentityrefTypeDefinition
 */
public class IdentityrefType implements IdentityrefTypeDefinition {

    private final QName name = BaseTypes.constructQName("identityref");
    private final SchemaPath path;
    private final String description = "The identityref type is used to reference an existing identity.";
    private final String reference = "https://tools.ietf.org/html/rfc6020#section-9.10";
    private final IdentityrefTypeDefinition baseType;

    private final QName identity;

    private String units = "";

    private IdentityrefType(QName identity) {
        this.identity = identity;
        this.path = BaseTypes.schemaPath(name);
        this.baseType = this;
    }

    public IdentityrefType(QName identity, SchemaPath schemaPath) {
        this.identity = identity;
        this.path = schemaPath;
        this.baseType = new IdentityrefType(identity);
    }

    public IdentityrefType(final List<String> actualPath,
            final URI namespace, final Date revision, final QName identity) {
        this.identity = identity;
        this.path = BaseTypes.schemaPath(actualPath, namespace, revision);
        this.baseType = new IdentityrefType(identity);
    }

    @Override
    public String getUnits() {
        return units;
    }

    @Override
    public Object getDefaultValue() {
        return identity;
    }

    @Override
    public QName getQName() {
        return name;
    }

    @Override
    public SchemaPath getPath() {
        return path;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getReference() {
        return reference;
    }

    @Override
    public Status getStatus() {
        return Status.CURRENT;
    }

    @Override
    public List<UnknownSchemaNode> getUnknownSchemaNodes() {
        return Collections.emptyList();
    }

    @Override
    public QName getIdentity() {
        return identity;
    }

    @Override
    public IdentityrefTypeDefinition getBaseType() {
        return baseType;
    }

}