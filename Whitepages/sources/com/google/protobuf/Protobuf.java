package com.google.protobuf;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class Protobuf {
    private static final Protobuf INSTANCE = new Protobuf();
    private final ConcurrentMap schemaCache = new ConcurrentHashMap();
    private final SchemaFactory schemaFactory = new ManifestSchemaFactory();

    public static Protobuf getInstance() {
        return INSTANCE;
    }

    public Schema schemaFor(Class cls) {
        Internal.checkNotNull(cls, "messageType");
        Schema schema = (Schema) this.schemaCache.get(cls);
        if (schema != null) {
            return schema;
        }
        Schema createSchema = this.schemaFactory.createSchema(cls);
        Schema registerSchema = registerSchema(cls, createSchema);
        return registerSchema != null ? registerSchema : createSchema;
    }

    public Schema schemaFor(Object obj) {
        return schemaFor((Class) obj.getClass());
    }

    public Schema registerSchema(Class cls, Schema schema) {
        Internal.checkNotNull(cls, "messageType");
        Internal.checkNotNull(schema, "schema");
        return (Schema) this.schemaCache.putIfAbsent(cls, schema);
    }

    private Protobuf() {
    }
}
