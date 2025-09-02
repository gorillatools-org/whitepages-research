package androidx.datastore.preferences.protobuf;

public interface MessageLite extends MessageLiteOrBuilder {

    public interface Builder extends MessageLiteOrBuilder, Cloneable {
        MessageLite buildPartial();
    }

    int getSerializedSize();

    Builder newBuilderForType();

    void writeTo(CodedOutputStream codedOutputStream);
}
