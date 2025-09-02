package androidx.datastore.preferences.protobuf;

import java.util.Map;

abstract class ExtensionSchema {
    /* access modifiers changed from: package-private */
    public abstract int extensionNumber(Map.Entry entry);

    /* access modifiers changed from: package-private */
    public abstract Object findExtensionByNumber(ExtensionRegistryLite extensionRegistryLite, MessageLite messageLite, int i);

    /* access modifiers changed from: package-private */
    public abstract FieldSet getExtensions(Object obj);

    /* access modifiers changed from: package-private */
    public abstract FieldSet getMutableExtensions(Object obj);

    /* access modifiers changed from: package-private */
    public abstract boolean hasExtensions(MessageLite messageLite);

    /* access modifiers changed from: package-private */
    public abstract void makeImmutable(Object obj);

    /* access modifiers changed from: package-private */
    public abstract Object parseExtension(Object obj, Reader reader, Object obj2, ExtensionRegistryLite extensionRegistryLite, FieldSet fieldSet, Object obj3, UnknownFieldSchema unknownFieldSchema);

    /* access modifiers changed from: package-private */
    public abstract void parseLengthPrefixedMessageSetItem(Reader reader, Object obj, ExtensionRegistryLite extensionRegistryLite, FieldSet fieldSet);

    /* access modifiers changed from: package-private */
    public abstract void parseMessageSetItem(ByteString byteString, Object obj, ExtensionRegistryLite extensionRegistryLite, FieldSet fieldSet);

    /* access modifiers changed from: package-private */
    public abstract void serializeExtension(Writer writer, Map.Entry entry);

    ExtensionSchema() {
    }
}
