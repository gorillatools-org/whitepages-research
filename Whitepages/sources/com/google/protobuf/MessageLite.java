package com.google.protobuf;

public interface MessageLite extends MessageLiteOrBuilder {

    public interface Builder extends MessageLiteOrBuilder, Cloneable {
        MessageLite build();

        MessageLite buildPartial();

        Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite);

        Builder mergeFrom(MessageLite messageLite);
    }

    int getSerializedSize();

    Builder newBuilderForType();

    Builder toBuilder();

    void writeTo(CodedOutputStream codedOutputStream);
}
