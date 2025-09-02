package androidx.datastore.preferences.protobuf;

abstract class ExtensionSchemas {
    private static final ExtensionSchema FULL_SCHEMA = loadSchemaForFullRuntime();
    private static final ExtensionSchema LITE_SCHEMA = new ExtensionSchemaLite();

    private static ExtensionSchema loadSchemaForFullRuntime() {
        if (Protobuf.assumeLiteRuntime) {
            return null;
        }
        try {
            return (ExtensionSchema) Class.forName("androidx.datastore.preferences.protobuf.ExtensionSchemaFull").getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
        } catch (Exception unused) {
            return null;
        }
    }

    static ExtensionSchema lite() {
        return LITE_SCHEMA;
    }

    static ExtensionSchema full() {
        ExtensionSchema extensionSchema = FULL_SCHEMA;
        if (extensionSchema != null) {
            return extensionSchema;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
}
