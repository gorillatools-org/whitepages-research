package androidx.datastore.preferences.protobuf;

interface MessageInfoFactory {
    boolean isSupported(Class cls);

    MessageInfo messageInfoFor(Class cls);
}
