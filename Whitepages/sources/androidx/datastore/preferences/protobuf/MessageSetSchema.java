package androidx.datastore.preferences.protobuf;

import android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.Iterator;
import java.util.Map;

final class MessageSetSchema implements Schema {
    private final MessageLite defaultInstance;
    private final ExtensionSchema extensionSchema;
    private final boolean hasExtensions;
    private final UnknownFieldSchema unknownFieldSchema;

    private MessageSetSchema(UnknownFieldSchema unknownFieldSchema2, ExtensionSchema extensionSchema2, MessageLite messageLite) {
        this.unknownFieldSchema = unknownFieldSchema2;
        this.hasExtensions = extensionSchema2.hasExtensions(messageLite);
        this.extensionSchema = extensionSchema2;
        this.defaultInstance = messageLite;
    }

    static MessageSetSchema newSchema(UnknownFieldSchema unknownFieldSchema2, ExtensionSchema extensionSchema2, MessageLite messageLite) {
        return new MessageSetSchema(unknownFieldSchema2, extensionSchema2, messageLite);
    }

    public Object newInstance() {
        MessageLite messageLite = this.defaultInstance;
        if (messageLite instanceof GeneratedMessageLite) {
            return ((GeneratedMessageLite) messageLite).newMutableInstance();
        }
        return messageLite.newBuilderForType().buildPartial();
    }

    public boolean equals(Object obj, Object obj2) {
        if (!this.unknownFieldSchema.getFromMessage(obj).equals(this.unknownFieldSchema.getFromMessage(obj2))) {
            return false;
        }
        if (this.hasExtensions) {
            return this.extensionSchema.getExtensions(obj).equals(this.extensionSchema.getExtensions(obj2));
        }
        return true;
    }

    public int hashCode(Object obj) {
        int hashCode = this.unknownFieldSchema.getFromMessage(obj).hashCode();
        return this.hasExtensions ? (hashCode * 53) + this.extensionSchema.getExtensions(obj).hashCode() : hashCode;
    }

    public void mergeFrom(Object obj, Object obj2) {
        SchemaUtil.mergeUnknownFields(this.unknownFieldSchema, obj, obj2);
        if (this.hasExtensions) {
            SchemaUtil.mergeExtensions(this.extensionSchema, obj, obj2);
        }
    }

    public void writeTo(Object obj, Writer writer) {
        Iterator it = this.extensionSchema.getExtensions(obj).iterator();
        if (!it.hasNext()) {
            writeUnknownFieldsHelper(this.unknownFieldSchema, obj, writer);
        } else {
            MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(((Map.Entry) it.next()).getKey());
            throw null;
        }
    }

    private void writeUnknownFieldsHelper(UnknownFieldSchema unknownFieldSchema2, Object obj, Writer writer) {
        unknownFieldSchema2.writeAsMessageSetTo(unknownFieldSchema2.getFromMessage(obj), writer);
    }

    public void mergeFrom(Object obj, Reader reader, ExtensionRegistryLite extensionRegistryLite) {
        mergeFromHelper(this.unknownFieldSchema, this.extensionSchema, obj, reader, extensionRegistryLite);
    }

    private void mergeFromHelper(UnknownFieldSchema unknownFieldSchema2, ExtensionSchema extensionSchema2, Object obj, Reader reader, ExtensionRegistryLite extensionRegistryLite) {
        Object builderFromMessage = unknownFieldSchema2.getBuilderFromMessage(obj);
        FieldSet mutableExtensions = extensionSchema2.getMutableExtensions(obj);
        do {
            try {
                if (reader.getFieldNumber() == Integer.MAX_VALUE) {
                    unknownFieldSchema2.setBuilderToMessage(obj, builderFromMessage);
                    return;
                }
            } finally {
                unknownFieldSchema2.setBuilderToMessage(obj, builderFromMessage);
            }
        } while (parseMessageSetItemOrUnknownField(reader, extensionRegistryLite, extensionSchema2, mutableExtensions, unknownFieldSchema2, builderFromMessage));
    }

    public void makeImmutable(Object obj) {
        this.unknownFieldSchema.makeImmutable(obj);
        this.extensionSchema.makeImmutable(obj);
    }

    private boolean parseMessageSetItemOrUnknownField(Reader reader, ExtensionRegistryLite extensionRegistryLite, ExtensionSchema extensionSchema2, FieldSet fieldSet, UnknownFieldSchema unknownFieldSchema2, Object obj) {
        int tag = reader.getTag();
        int i = 0;
        if (tag == WireFormat.MESSAGE_SET_ITEM_TAG) {
            Object obj2 = null;
            ByteString byteString = null;
            while (reader.getFieldNumber() != Integer.MAX_VALUE) {
                int tag2 = reader.getTag();
                if (tag2 == WireFormat.MESSAGE_SET_TYPE_ID_TAG) {
                    i = reader.readUInt32();
                    obj2 = extensionSchema2.findExtensionByNumber(extensionRegistryLite, this.defaultInstance, i);
                } else if (tag2 == WireFormat.MESSAGE_SET_MESSAGE_TAG) {
                    if (obj2 != null) {
                        extensionSchema2.parseLengthPrefixedMessageSetItem(reader, obj2, extensionRegistryLite, fieldSet);
                    } else {
                        byteString = reader.readBytes();
                    }
                } else if (!reader.skipField()) {
                    break;
                }
            }
            if (reader.getTag() == WireFormat.MESSAGE_SET_ITEM_END_TAG) {
                if (byteString != null) {
                    if (obj2 != null) {
                        extensionSchema2.parseMessageSetItem(byteString, obj2, extensionRegistryLite, fieldSet);
                    } else {
                        unknownFieldSchema2.addLengthDelimited(obj, i, byteString);
                    }
                }
                return true;
            }
            throw InvalidProtocolBufferException.invalidEndTag();
        } else if (WireFormat.getTagWireType(tag) != 2) {
            return reader.skipField();
        } else {
            Object findExtensionByNumber = extensionSchema2.findExtensionByNumber(extensionRegistryLite, this.defaultInstance, WireFormat.getTagFieldNumber(tag));
            if (findExtensionByNumber == null) {
                return unknownFieldSchema2.mergeOneFieldFrom(obj, reader, 0);
            }
            extensionSchema2.parseLengthPrefixedMessageSetItem(reader, findExtensionByNumber, extensionRegistryLite, fieldSet);
            return true;
        }
    }

    public final boolean isInitialized(Object obj) {
        return this.extensionSchema.getExtensions(obj).isInitialized();
    }

    public int getSerializedSize(Object obj) {
        int unknownFieldsSerializedSize = getUnknownFieldsSerializedSize(this.unknownFieldSchema, obj);
        return this.hasExtensions ? unknownFieldsSerializedSize + this.extensionSchema.getExtensions(obj).getMessageSetSerializedSize() : unknownFieldsSerializedSize;
    }

    private int getUnknownFieldsSerializedSize(UnknownFieldSchema unknownFieldSchema2, Object obj) {
        return unknownFieldSchema2.getSerializedSizeAsMessageSet(unknownFieldSchema2.getFromMessage(obj));
    }
}
