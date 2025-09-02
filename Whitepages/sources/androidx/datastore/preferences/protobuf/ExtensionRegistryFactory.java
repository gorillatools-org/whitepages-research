package androidx.datastore.preferences.protobuf;

abstract class ExtensionRegistryFactory {
    static final Class EXTENSION_REGISTRY_CLASS = reflectExtensionRegistry();

    static Class reflectExtensionRegistry() {
        try {
            return Class.forName("androidx.datastore.preferences.protobuf.ExtensionRegistry");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static ExtensionRegistryLite createEmpty() {
        ExtensionRegistryLite invokeSubclassFactory = invokeSubclassFactory("getEmptyRegistry");
        return invokeSubclassFactory != null ? invokeSubclassFactory : ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
    }

    private static final ExtensionRegistryLite invokeSubclassFactory(String str) {
        Class cls = EXTENSION_REGISTRY_CLASS;
        if (cls == null) {
            return null;
        }
        try {
            return (ExtensionRegistryLite) cls.getDeclaredMethod(str, (Class[]) null).invoke((Object) null, (Object[]) null);
        } catch (Exception unused) {
            return null;
        }
    }
}
