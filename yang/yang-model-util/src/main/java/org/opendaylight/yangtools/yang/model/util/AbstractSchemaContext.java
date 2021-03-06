package org.opendaylight.yangtools.yang.model.util;

import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import com.google.common.collect.SetMultimap;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.model.api.AugmentationSchema;
import org.opendaylight.yangtools.yang.model.api.ConstraintDefinition;
import org.opendaylight.yangtools.yang.model.api.DataSchemaNode;
import org.opendaylight.yangtools.yang.model.api.ExtensionDefinition;
import org.opendaylight.yangtools.yang.model.api.GroupingDefinition;
import org.opendaylight.yangtools.yang.model.api.Module;
import org.opendaylight.yangtools.yang.model.api.ModuleIdentifier;
import org.opendaylight.yangtools.yang.model.api.NotificationDefinition;
import org.opendaylight.yangtools.yang.model.api.RpcDefinition;
import org.opendaylight.yangtools.yang.model.api.SchemaContext;
import org.opendaylight.yangtools.yang.model.api.SchemaPath;
import org.opendaylight.yangtools.yang.model.api.Status;
import org.opendaylight.yangtools.yang.model.api.TypeDefinition;
import org.opendaylight.yangtools.yang.model.api.UnknownSchemaNode;
import org.opendaylight.yangtools.yang.model.api.UsesNode;


public abstract class AbstractSchemaContext implements SchemaContext {

    protected static final Supplier<TreeSet<Module>> MODULE_SET_SUPPLIER = new Supplier<TreeSet<Module>>() {
        @Override
        public TreeSet<Module> get() {
            return new TreeSet<>(REVISION_COMPARATOR);
        }
    };

    protected static final Comparator<Module> REVISION_COMPARATOR = new Comparator<Module>() {
        @Override
        public int compare(final Module o1, final Module o2) {
            if (o2.getRevision() == null) {
                return -1;
            }

            return o2.getRevision().compareTo(o1.getRevision());
        }
    };

    /**
     * @return yang sources where key is ModuleIdentifier
     */
    protected abstract Map<ModuleIdentifier, String> getIdentifiersToSources();

    /**
     * @return Map of modules where key is namespace
     */
    protected abstract SetMultimap<URI, Module> getNamespaceToModules();

    /**
     * @return Map of modules where key is name of module
     */
    protected abstract SetMultimap<String, Module> getNameToModules();

    @Override
    public Set<DataSchemaNode> getDataDefinitions() {
        final Set<DataSchemaNode> dataDefs = new HashSet<>();
        for (Module m : getModules()) {
            dataDefs.addAll(m.getChildNodes());
        }
        return dataDefs;
    }

    @Override
    public Set<NotificationDefinition> getNotifications() {
        final Set<NotificationDefinition> notifications = new HashSet<>();
        for (Module m : getModules()) {
            notifications.addAll(m.getNotifications());
        }
        return notifications;
    }

    @Override
    public Set<RpcDefinition> getOperations() {
        final Set<RpcDefinition> rpcs = new HashSet<>();
        for (Module m : getModules()) {
            rpcs.addAll(m.getRpcs());
        }
        return rpcs;
    }

    @Override
    public Set<ExtensionDefinition> getExtensions() {
        final Set<ExtensionDefinition> extensions = new HashSet<>();
        for (Module m : getModules()) {
            extensions.addAll(m.getExtensionSchemaNodes());
        }
        return extensions;
    }

    @Override
    public Module findModuleByName(final String name, final Date revision) {
        for (final Module module : getNameToModules().get(name)) {
            if (revision == null || revision.equals(module.getRevision())) {
                return module;
            }
        }

        return null;
    }

    @Override
    public Set<Module> findModuleByNamespace(final URI namespace) {
        final Set<Module> ret = getNamespaceToModules().get(namespace);
        return ret == null ? Collections.<Module>emptySet() : ret;
    }

    @Override
    public Module findModuleByNamespaceAndRevision(final URI namespace, final Date revision) {
        if (namespace == null) {
            return null;
        }
        for (Module module : findModuleByNamespace(namespace)) {
            if (revision == null || revision.equals(module.getRevision())) {
                return module;
            }
        }
        return null;
    }

    @Override
    public boolean isAugmenting() {
        return false;
    }

    @Override
    public boolean isAddedByUses() {
        return false;
    }

    @Override
    public boolean isConfiguration() {
        return false;
    }

    @Override
    public ConstraintDefinition getConstraints() {
        return null;
    }

    @Override
    public QName getQName() {
        return SchemaContext.NAME;
    }

    @Override
    public SchemaPath getPath() {
        return SchemaPath.ROOT;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getReference() {
        return null;
    }

    @Override
    public Status getStatus() {
        return Status.CURRENT;
    }

    @Override
    public List<UnknownSchemaNode> getUnknownSchemaNodes() {
        final List<UnknownSchemaNode> result = new ArrayList<>();
        for (Module module : getModules()) {
            result.addAll(module.getUnknownSchemaNodes());
        }
        return Collections.unmodifiableList(result);
    }

    @Override
    public Set<TypeDefinition<?>> getTypeDefinitions() {
        final Set<TypeDefinition<?>> result = new LinkedHashSet<>();
        for (Module module : getModules()) {
            result.addAll(module.getTypeDefinitions());
        }
        return Collections.unmodifiableSet(result);
    }

    @Override
    public Set<DataSchemaNode> getChildNodes() {
        final Set<DataSchemaNode> result = new LinkedHashSet<>();
        for (Module module : getModules()) {
            result.addAll(module.getChildNodes());
        }
        return Collections.unmodifiableSet(result);
    }

    @Override
    public Set<GroupingDefinition> getGroupings() {
        final Set<GroupingDefinition> result = new LinkedHashSet<>();
        for (Module module : getModules()) {
            result.addAll(module.getGroupings());
        }
        return Collections.unmodifiableSet(result);
    }

    @Override
    public DataSchemaNode getDataChildByName(final QName name) {
        for (Module module : getModules()) {
            final DataSchemaNode result = module.getDataChildByName(name);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    @Override
    public DataSchemaNode getDataChildByName(final String name) {
        for (Module module : getModules()) {
            final DataSchemaNode result = module.getDataChildByName(name);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    @Override
    public Set<UsesNode> getUses() {
        return Collections.emptySet();
    }

    @Override
    public boolean isPresenceContainer() {
        return false;
    }

    @Override
    public Set<AugmentationSchema> getAvailableAugmentations() {
        return Collections.emptySet();
    }

    //FIXME: should work for submodules too
    @Override
    public Set<ModuleIdentifier> getAllModuleIdentifiers() {
        return getIdentifiersToSources().keySet();
    }

    @Override
    public Optional<String> getModuleSource(final ModuleIdentifier moduleIdentifier) {
        String maybeSource = getIdentifiersToSources().get(moduleIdentifier);
        return Optional.fromNullable(maybeSource);
    }

}
