package androidx.datastore.preferences.protobuf;

import androidx.datastore.preferences.protobuf.WireFormat;

public class MapEntryLite {
    private final Object key;
    private final Metadata metadata;
    private final Object value;

    static class Metadata {
        public final Object defaultKey;
        public final Object defaultValue;
        public final WireFormat.FieldType keyType;
        public final WireFormat.FieldType valueType;

        public Metadata(WireFormat.FieldType fieldType, Object obj, WireFormat.FieldType fieldType2, Object obj2) {
            this.keyType = fieldType;
            this.defaultKey = obj;
            this.valueType = fieldType2;
            this.defaultValue = obj2;
        }
    }

    private MapEntryLite(WireFormat.FieldType fieldType, Object obj, WireFormat.FieldType fieldType2, Object obj2) {
        this.metadata = new Metadata(fieldType, obj, fieldType2, obj2);
        this.key = obj;
        this.value = obj2;
    }

    public static MapEntryLite newDefaultInstance(WireFormat.FieldType fieldType, Object obj, WireFormat.FieldType fieldType2, Object obj2) {
        return new MapEntryLite(fieldType, obj, fieldType2, obj2);
    }

    static void writeTo(CodedOutputStream codedOutputStream, Metadata metadata2, Object obj, Object obj2) {
        FieldSet.writeElement(codedOutputStream, metadata2.keyType, 1, obj);
        FieldSet.writeElement(codedOutputStream, metadata2.valueType, 2, obj2);
    }

    static int computeSerializedSize(Metadata metadata2, Object obj, Object obj2) {
        return FieldSet.computeElementSize(metadata2.keyType, 1, obj) + FieldSet.computeElementSize(metadata2.valueType, 2, obj2);
    }

    public int computeMessageSize(int i, Object obj, Object obj2) {
        return CodedOutputStream.computeTagSize(i) + CodedOutputStream.computeLengthDelimitedFieldSize(computeSerializedSize(this.metadata, obj, obj2));
    }

    /* access modifiers changed from: package-private */
    public Metadata getMetadata() {
        return this.metadata;
    }
}
