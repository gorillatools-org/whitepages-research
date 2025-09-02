package com.google.protobuf;

abstract class UnknownFieldSchema {
    private static volatile int recursionLimit = 100;

    /* access modifiers changed from: package-private */
    public abstract void addFixed32(Object obj, int i, int i2);

    /* access modifiers changed from: package-private */
    public abstract void addFixed64(Object obj, int i, long j);

    /* access modifiers changed from: package-private */
    public abstract void addGroup(Object obj, int i, Object obj2);

    /* access modifiers changed from: package-private */
    public abstract void addLengthDelimited(Object obj, int i, ByteString byteString);

    /* access modifiers changed from: package-private */
    public abstract void addVarint(Object obj, int i, long j);

    /* access modifiers changed from: package-private */
    public abstract Object getBuilderFromMessage(Object obj);

    /* access modifiers changed from: package-private */
    public abstract Object getFromMessage(Object obj);

    /* access modifiers changed from: package-private */
    public abstract int getSerializedSize(Object obj);

    /* access modifiers changed from: package-private */
    public abstract int getSerializedSizeAsMessageSet(Object obj);

    /* access modifiers changed from: package-private */
    public abstract void makeImmutable(Object obj);

    /* access modifiers changed from: package-private */
    public abstract Object merge(Object obj, Object obj2);

    /* access modifiers changed from: package-private */
    public abstract Object newBuilder();

    /* access modifiers changed from: package-private */
    public abstract void setBuilderToMessage(Object obj, Object obj2);

    /* access modifiers changed from: package-private */
    public abstract void setToMessage(Object obj, Object obj2);

    /* access modifiers changed from: package-private */
    public abstract boolean shouldDiscardUnknownFields(Reader reader);

    /* access modifiers changed from: package-private */
    public abstract Object toImmutable(Object obj);

    /* access modifiers changed from: package-private */
    public abstract void writeAsMessageSetTo(Object obj, Writer writer);

    /* access modifiers changed from: package-private */
    public abstract void writeTo(Object obj, Writer writer);

    UnknownFieldSchema() {
    }

    /* access modifiers changed from: package-private */
    public final boolean mergeOneFieldFrom(Object obj, Reader reader, int i) {
        int tag = reader.getTag();
        int tagFieldNumber = WireFormat.getTagFieldNumber(tag);
        int tagWireType = WireFormat.getTagWireType(tag);
        if (tagWireType == 0) {
            addVarint(obj, tagFieldNumber, reader.readInt64());
            return true;
        } else if (tagWireType == 1) {
            addFixed64(obj, tagFieldNumber, reader.readFixed64());
            return true;
        } else if (tagWireType == 2) {
            addLengthDelimited(obj, tagFieldNumber, reader.readBytes());
            return true;
        } else if (tagWireType == 3) {
            Object newBuilder = newBuilder();
            int makeTag = WireFormat.makeTag(tagFieldNumber, 4);
            int i2 = i + 1;
            if (i2 < recursionLimit) {
                mergeFrom(newBuilder, reader, i2);
                if (makeTag == reader.getTag()) {
                    addGroup(obj, tagFieldNumber, toImmutable(newBuilder));
                    return true;
                }
                throw InvalidProtocolBufferException.invalidEndTag();
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        } else if (tagWireType == 4) {
            return false;
        } else {
            if (tagWireType == 5) {
                addFixed32(obj, tagFieldNumber, reader.readFixed32());
                return true;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP:0: B:0:0x0000->B:3:0x000d, LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void mergeFrom(java.lang.Object r3, com.google.protobuf.Reader r4, int r5) {
        /*
            r2 = this;
        L_0x0000:
            int r0 = r4.getFieldNumber()
            r1 = 2147483647(0x7fffffff, float:NaN)
            if (r0 == r1) goto L_0x000f
            boolean r0 = r2.mergeOneFieldFrom(r3, r4, r5)
            if (r0 != 0) goto L_0x0000
        L_0x000f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.UnknownFieldSchema.mergeFrom(java.lang.Object, com.google.protobuf.Reader, int):void");
    }
}
