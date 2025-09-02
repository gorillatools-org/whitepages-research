package androidx.datastore.preferences.protobuf;

abstract class ListFieldSchemas {
    private static final ListFieldSchema FULL_SCHEMA = loadSchemaForFullRuntime();
    private static final ListFieldSchema LITE_SCHEMA = new ListFieldSchemaLite();

    static ListFieldSchema full() {
        return FULL_SCHEMA;
    }

    static ListFieldSchema lite() {
        return LITE_SCHEMA;
    }

    private static ListFieldSchema loadSchemaForFullRuntime() {
        if (Protobuf.assumeLiteRuntime) {
            return null;
        }
        try {
            return (ListFieldSchema) Class.forName("androidx.datastore.preferences.protobuf.ListFieldSchemaFull").getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
        } catch (Exception unused) {
            return null;
        }
    }
}
