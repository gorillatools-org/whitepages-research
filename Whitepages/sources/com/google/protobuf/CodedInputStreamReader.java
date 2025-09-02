package com.google.protobuf;

import com.google.protobuf.WireFormat;
import java.util.List;

final class CodedInputStreamReader implements Reader {
    private int endGroupTag;
    private final CodedInputStream input;
    private int nextTag = 0;
    private int tag;

    public static CodedInputStreamReader forCodedInput(CodedInputStream codedInputStream) {
        CodedInputStreamReader codedInputStreamReader = codedInputStream.wrapper;
        if (codedInputStreamReader != null) {
            return codedInputStreamReader;
        }
        return new CodedInputStreamReader(codedInputStream);
    }

    private CodedInputStreamReader(CodedInputStream codedInputStream) {
        CodedInputStream codedInputStream2 = (CodedInputStream) Internal.checkNotNull(codedInputStream, "input");
        this.input = codedInputStream2;
        codedInputStream2.wrapper = this;
    }

    public int getFieldNumber() {
        int i = this.nextTag;
        if (i != 0) {
            this.tag = i;
            this.nextTag = 0;
        } else {
            this.tag = this.input.readTag();
        }
        int i2 = this.tag;
        if (i2 == 0 || i2 == this.endGroupTag) {
            return Integer.MAX_VALUE;
        }
        return WireFormat.getTagFieldNumber(i2);
    }

    public int getTag() {
        return this.tag;
    }

    public boolean skipField() {
        int i;
        if (this.input.isAtEnd() || (i = this.tag) == this.endGroupTag) {
            return false;
        }
        return this.input.skipField(i);
    }

    private void requireWireType(int i) {
        if (WireFormat.getTagWireType(this.tag) != i) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
    }

    public double readDouble() {
        requireWireType(1);
        return this.input.readDouble();
    }

    public float readFloat() {
        requireWireType(5);
        return this.input.readFloat();
    }

    public long readUInt64() {
        requireWireType(0);
        return this.input.readUInt64();
    }

    public long readInt64() {
        requireWireType(0);
        return this.input.readInt64();
    }

    public int readInt32() {
        requireWireType(0);
        return this.input.readInt32();
    }

    public long readFixed64() {
        requireWireType(1);
        return this.input.readFixed64();
    }

    public int readFixed32() {
        requireWireType(5);
        return this.input.readFixed32();
    }

    public boolean readBool() {
        requireWireType(0);
        return this.input.readBool();
    }

    public String readString() {
        requireWireType(2);
        return this.input.readString();
    }

    public String readStringRequireUtf8() {
        requireWireType(2);
        return this.input.readStringRequireUtf8();
    }

    public Object readMessage(Class cls, ExtensionRegistryLite extensionRegistryLite) {
        requireWireType(2);
        return readMessage(Protobuf.getInstance().schemaFor(cls), extensionRegistryLite);
    }

    public Object readGroup(Class cls, ExtensionRegistryLite extensionRegistryLite) {
        requireWireType(3);
        return readGroup(Protobuf.getInstance().schemaFor(cls), extensionRegistryLite);
    }

    public void mergeMessageField(Object obj, Schema schema, ExtensionRegistryLite extensionRegistryLite) {
        requireWireType(2);
        mergeMessageFieldInternal(obj, schema, extensionRegistryLite);
    }

    private void mergeMessageFieldInternal(Object obj, Schema schema, ExtensionRegistryLite extensionRegistryLite) {
        int readUInt32 = this.input.readUInt32();
        CodedInputStream codedInputStream = this.input;
        if (codedInputStream.recursionDepth < codedInputStream.recursionLimit) {
            int pushLimit = codedInputStream.pushLimit(readUInt32);
            this.input.recursionDepth++;
            schema.mergeFrom(obj, this, extensionRegistryLite);
            this.input.checkLastTagWas(0);
            CodedInputStream codedInputStream2 = this.input;
            codedInputStream2.recursionDepth--;
            codedInputStream2.popLimit(pushLimit);
            return;
        }
        throw InvalidProtocolBufferException.recursionLimitExceeded();
    }

    private Object readMessage(Schema schema, ExtensionRegistryLite extensionRegistryLite) {
        Object newInstance = schema.newInstance();
        mergeMessageFieldInternal(newInstance, schema, extensionRegistryLite);
        schema.makeImmutable(newInstance);
        return newInstance;
    }

    public void mergeGroupField(Object obj, Schema schema, ExtensionRegistryLite extensionRegistryLite) {
        requireWireType(3);
        mergeGroupFieldInternal(obj, schema, extensionRegistryLite);
    }

    private void mergeGroupFieldInternal(Object obj, Schema schema, ExtensionRegistryLite extensionRegistryLite) {
        int i = this.endGroupTag;
        this.endGroupTag = WireFormat.makeTag(WireFormat.getTagFieldNumber(this.tag), 4);
        try {
            schema.mergeFrom(obj, this, extensionRegistryLite);
            if (this.tag != this.endGroupTag) {
                throw InvalidProtocolBufferException.parseFailure();
            }
        } finally {
            this.endGroupTag = i;
        }
    }

    private Object readGroup(Schema schema, ExtensionRegistryLite extensionRegistryLite) {
        Object newInstance = schema.newInstance();
        mergeGroupFieldInternal(newInstance, schema, extensionRegistryLite);
        schema.makeImmutable(newInstance);
        return newInstance;
    }

    public ByteString readBytes() {
        requireWireType(2);
        return this.input.readBytes();
    }

    public int readUInt32() {
        requireWireType(0);
        return this.input.readUInt32();
    }

    public int readEnum() {
        requireWireType(0);
        return this.input.readEnum();
    }

    public int readSFixed32() {
        requireWireType(5);
        return this.input.readSFixed32();
    }

    public long readSFixed64() {
        requireWireType(1);
        return this.input.readSFixed64();
    }

    public int readSInt32() {
        requireWireType(0);
        return this.input.readSInt32();
    }

    public long readSInt64() {
        requireWireType(0);
        return this.input.readSInt64();
    }

    public void readDoubleList(List list) {
        int readTag;
        int readTag2;
        if (list instanceof DoubleArrayList) {
            DoubleArrayList doubleArrayList = (DoubleArrayList) list;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType == 1) {
                do {
                    doubleArrayList.addDouble(this.input.readDouble());
                    if (!this.input.isAtEnd()) {
                        readTag2 = this.input.readTag();
                    } else {
                        return;
                    }
                } while (readTag2 == this.tag);
                this.nextTag = readTag2;
            } else if (tagWireType == 2) {
                int readUInt32 = this.input.readUInt32();
                verifyPackedFixed64Length(readUInt32);
                int totalBytesRead = this.input.getTotalBytesRead() + readUInt32;
                do {
                    doubleArrayList.addDouble(this.input.readDouble());
                } while (this.input.getTotalBytesRead() < totalBytesRead);
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        } else {
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 == 1) {
                do {
                    list.add(Double.valueOf(this.input.readDouble()));
                    if (!this.input.isAtEnd()) {
                        readTag = this.input.readTag();
                    } else {
                        return;
                    }
                } while (readTag == this.tag);
                this.nextTag = readTag;
            } else if (tagWireType2 == 2) {
                int readUInt322 = this.input.readUInt32();
                verifyPackedFixed64Length(readUInt322);
                int totalBytesRead2 = this.input.getTotalBytesRead() + readUInt322;
                do {
                    list.add(Double.valueOf(this.input.readDouble()));
                } while (this.input.getTotalBytesRead() < totalBytesRead2);
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
    }

    public void readFloatList(List list) {
        int readTag;
        int readTag2;
        if (list instanceof FloatArrayList) {
            FloatArrayList floatArrayList = (FloatArrayList) list;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType == 2) {
                int readUInt32 = this.input.readUInt32();
                verifyPackedFixed32Length(readUInt32);
                int totalBytesRead = this.input.getTotalBytesRead() + readUInt32;
                do {
                    floatArrayList.addFloat(this.input.readFloat());
                } while (this.input.getTotalBytesRead() < totalBytesRead);
            } else if (tagWireType == 5) {
                do {
                    floatArrayList.addFloat(this.input.readFloat());
                    if (!this.input.isAtEnd()) {
                        readTag2 = this.input.readTag();
                    } else {
                        return;
                    }
                } while (readTag2 == this.tag);
                this.nextTag = readTag2;
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        } else {
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 == 2) {
                int readUInt322 = this.input.readUInt32();
                verifyPackedFixed32Length(readUInt322);
                int totalBytesRead2 = this.input.getTotalBytesRead() + readUInt322;
                do {
                    list.add(Float.valueOf(this.input.readFloat()));
                } while (this.input.getTotalBytesRead() < totalBytesRead2);
            } else if (tagWireType2 == 5) {
                do {
                    list.add(Float.valueOf(this.input.readFloat()));
                    if (!this.input.isAtEnd()) {
                        readTag = this.input.readTag();
                    } else {
                        return;
                    }
                } while (readTag == this.tag);
                this.nextTag = readTag;
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
    }

    public void readUInt64List(List list) {
        int readTag;
        int readTag2;
        if (list instanceof LongArrayList) {
            LongArrayList longArrayList = (LongArrayList) list;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType == 0) {
                do {
                    longArrayList.addLong(this.input.readUInt64());
                    if (!this.input.isAtEnd()) {
                        readTag2 = this.input.readTag();
                    } else {
                        return;
                    }
                } while (readTag2 == this.tag);
                this.nextTag = readTag2;
            } else if (tagWireType == 2) {
                int totalBytesRead = this.input.getTotalBytesRead() + this.input.readUInt32();
                do {
                    longArrayList.addLong(this.input.readUInt64());
                } while (this.input.getTotalBytesRead() < totalBytesRead);
                requirePosition(totalBytesRead);
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        } else {
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 == 0) {
                do {
                    list.add(Long.valueOf(this.input.readUInt64()));
                    if (!this.input.isAtEnd()) {
                        readTag = this.input.readTag();
                    } else {
                        return;
                    }
                } while (readTag == this.tag);
                this.nextTag = readTag;
            } else if (tagWireType2 == 2) {
                int totalBytesRead2 = this.input.getTotalBytesRead() + this.input.readUInt32();
                do {
                    list.add(Long.valueOf(this.input.readUInt64()));
                } while (this.input.getTotalBytesRead() < totalBytesRead2);
                requirePosition(totalBytesRead2);
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
    }

    public void readInt64List(List list) {
        int readTag;
        int readTag2;
        if (list instanceof LongArrayList) {
            LongArrayList longArrayList = (LongArrayList) list;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType == 0) {
                do {
                    longArrayList.addLong(this.input.readInt64());
                    if (!this.input.isAtEnd()) {
                        readTag2 = this.input.readTag();
                    } else {
                        return;
                    }
                } while (readTag2 == this.tag);
                this.nextTag = readTag2;
            } else if (tagWireType == 2) {
                int totalBytesRead = this.input.getTotalBytesRead() + this.input.readUInt32();
                do {
                    longArrayList.addLong(this.input.readInt64());
                } while (this.input.getTotalBytesRead() < totalBytesRead);
                requirePosition(totalBytesRead);
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        } else {
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 == 0) {
                do {
                    list.add(Long.valueOf(this.input.readInt64()));
                    if (!this.input.isAtEnd()) {
                        readTag = this.input.readTag();
                    } else {
                        return;
                    }
                } while (readTag == this.tag);
                this.nextTag = readTag;
            } else if (tagWireType2 == 2) {
                int totalBytesRead2 = this.input.getTotalBytesRead() + this.input.readUInt32();
                do {
                    list.add(Long.valueOf(this.input.readInt64()));
                } while (this.input.getTotalBytesRead() < totalBytesRead2);
                requirePosition(totalBytesRead2);
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
    }

    public void readInt32List(List list) {
        int readTag;
        int readTag2;
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType == 0) {
                do {
                    intArrayList.addInt(this.input.readInt32());
                    if (!this.input.isAtEnd()) {
                        readTag2 = this.input.readTag();
                    } else {
                        return;
                    }
                } while (readTag2 == this.tag);
                this.nextTag = readTag2;
            } else if (tagWireType == 2) {
                int totalBytesRead = this.input.getTotalBytesRead() + this.input.readUInt32();
                do {
                    intArrayList.addInt(this.input.readInt32());
                } while (this.input.getTotalBytesRead() < totalBytesRead);
                requirePosition(totalBytesRead);
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        } else {
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 == 0) {
                do {
                    list.add(Integer.valueOf(this.input.readInt32()));
                    if (!this.input.isAtEnd()) {
                        readTag = this.input.readTag();
                    } else {
                        return;
                    }
                } while (readTag == this.tag);
                this.nextTag = readTag;
            } else if (tagWireType2 == 2) {
                int totalBytesRead2 = this.input.getTotalBytesRead() + this.input.readUInt32();
                do {
                    list.add(Integer.valueOf(this.input.readInt32()));
                } while (this.input.getTotalBytesRead() < totalBytesRead2);
                requirePosition(totalBytesRead2);
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
    }

    public void readFixed64List(List list) {
        int readTag;
        int readTag2;
        if (list instanceof LongArrayList) {
            LongArrayList longArrayList = (LongArrayList) list;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType == 1) {
                do {
                    longArrayList.addLong(this.input.readFixed64());
                    if (!this.input.isAtEnd()) {
                        readTag2 = this.input.readTag();
                    } else {
                        return;
                    }
                } while (readTag2 == this.tag);
                this.nextTag = readTag2;
            } else if (tagWireType == 2) {
                int readUInt32 = this.input.readUInt32();
                verifyPackedFixed64Length(readUInt32);
                int totalBytesRead = this.input.getTotalBytesRead() + readUInt32;
                do {
                    longArrayList.addLong(this.input.readFixed64());
                } while (this.input.getTotalBytesRead() < totalBytesRead);
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        } else {
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 == 1) {
                do {
                    list.add(Long.valueOf(this.input.readFixed64()));
                    if (!this.input.isAtEnd()) {
                        readTag = this.input.readTag();
                    } else {
                        return;
                    }
                } while (readTag == this.tag);
                this.nextTag = readTag;
            } else if (tagWireType2 == 2) {
                int readUInt322 = this.input.readUInt32();
                verifyPackedFixed64Length(readUInt322);
                int totalBytesRead2 = this.input.getTotalBytesRead() + readUInt322;
                do {
                    list.add(Long.valueOf(this.input.readFixed64()));
                } while (this.input.getTotalBytesRead() < totalBytesRead2);
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
    }

    public void readFixed32List(List list) {
        int readTag;
        int readTag2;
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType == 2) {
                int readUInt32 = this.input.readUInt32();
                verifyPackedFixed32Length(readUInt32);
                int totalBytesRead = this.input.getTotalBytesRead() + readUInt32;
                do {
                    intArrayList.addInt(this.input.readFixed32());
                } while (this.input.getTotalBytesRead() < totalBytesRead);
            } else if (tagWireType == 5) {
                do {
                    intArrayList.addInt(this.input.readFixed32());
                    if (!this.input.isAtEnd()) {
                        readTag2 = this.input.readTag();
                    } else {
                        return;
                    }
                } while (readTag2 == this.tag);
                this.nextTag = readTag2;
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        } else {
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 == 2) {
                int readUInt322 = this.input.readUInt32();
                verifyPackedFixed32Length(readUInt322);
                int totalBytesRead2 = this.input.getTotalBytesRead() + readUInt322;
                do {
                    list.add(Integer.valueOf(this.input.readFixed32()));
                } while (this.input.getTotalBytesRead() < totalBytesRead2);
            } else if (tagWireType2 == 5) {
                do {
                    list.add(Integer.valueOf(this.input.readFixed32()));
                    if (!this.input.isAtEnd()) {
                        readTag = this.input.readTag();
                    } else {
                        return;
                    }
                } while (readTag == this.tag);
                this.nextTag = readTag;
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
    }

    public void readBoolList(List list) {
        int readTag;
        int readTag2;
        if (list instanceof BooleanArrayList) {
            BooleanArrayList booleanArrayList = (BooleanArrayList) list;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType == 0) {
                do {
                    booleanArrayList.addBoolean(this.input.readBool());
                    if (!this.input.isAtEnd()) {
                        readTag2 = this.input.readTag();
                    } else {
                        return;
                    }
                } while (readTag2 == this.tag);
                this.nextTag = readTag2;
            } else if (tagWireType == 2) {
                int totalBytesRead = this.input.getTotalBytesRead() + this.input.readUInt32();
                do {
                    booleanArrayList.addBoolean(this.input.readBool());
                } while (this.input.getTotalBytesRead() < totalBytesRead);
                requirePosition(totalBytesRead);
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        } else {
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 == 0) {
                do {
                    list.add(Boolean.valueOf(this.input.readBool()));
                    if (!this.input.isAtEnd()) {
                        readTag = this.input.readTag();
                    } else {
                        return;
                    }
                } while (readTag == this.tag);
                this.nextTag = readTag;
            } else if (tagWireType2 == 2) {
                int totalBytesRead2 = this.input.getTotalBytesRead() + this.input.readUInt32();
                do {
                    list.add(Boolean.valueOf(this.input.readBool()));
                } while (this.input.getTotalBytesRead() < totalBytesRead2);
                requirePosition(totalBytesRead2);
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
    }

    public void readStringList(List list) {
        readStringListInternal(list, false);
    }

    public void readStringListRequireUtf8(List list) {
        readStringListInternal(list, true);
    }

    public void readStringListInternal(List list, boolean z) {
        int readTag;
        int readTag2;
        if (WireFormat.getTagWireType(this.tag) != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        } else if (!(list instanceof LazyStringList) || z) {
            do {
                list.add(z ? readStringRequireUtf8() : readString());
                if (!this.input.isAtEnd()) {
                    readTag = this.input.readTag();
                } else {
                    return;
                }
            } while (readTag == this.tag);
            this.nextTag = readTag;
        } else {
            LazyStringList lazyStringList = (LazyStringList) list;
            do {
                lazyStringList.add(readBytes());
                if (!this.input.isAtEnd()) {
                    readTag2 = this.input.readTag();
                } else {
                    return;
                }
            } while (readTag2 == this.tag);
            this.nextTag = readTag2;
        }
    }

    public void readMessageList(List list, Schema schema, ExtensionRegistryLite extensionRegistryLite) {
        int readTag;
        if (WireFormat.getTagWireType(this.tag) == 2) {
            int i = this.tag;
            do {
                list.add(readMessage(schema, extensionRegistryLite));
                if (!this.input.isAtEnd() && this.nextTag == 0) {
                    readTag = this.input.readTag();
                } else {
                    return;
                }
            } while (readTag == i);
            this.nextTag = readTag;
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    public void readGroupList(List list, Schema schema, ExtensionRegistryLite extensionRegistryLite) {
        int readTag;
        if (WireFormat.getTagWireType(this.tag) == 3) {
            int i = this.tag;
            do {
                list.add(readGroup(schema, extensionRegistryLite));
                if (!this.input.isAtEnd() && this.nextTag == 0) {
                    readTag = this.input.readTag();
                } else {
                    return;
                }
            } while (readTag == i);
            this.nextTag = readTag;
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    public void readBytesList(List list) {
        int readTag;
        if (WireFormat.getTagWireType(this.tag) == 2) {
            do {
                list.add(readBytes());
                if (!this.input.isAtEnd()) {
                    readTag = this.input.readTag();
                } else {
                    return;
                }
            } while (readTag == this.tag);
            this.nextTag = readTag;
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    public void readUInt32List(List list) {
        int readTag;
        int readTag2;
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType == 0) {
                do {
                    intArrayList.addInt(this.input.readUInt32());
                    if (!this.input.isAtEnd()) {
                        readTag2 = this.input.readTag();
                    } else {
                        return;
                    }
                } while (readTag2 == this.tag);
                this.nextTag = readTag2;
            } else if (tagWireType == 2) {
                int totalBytesRead = this.input.getTotalBytesRead() + this.input.readUInt32();
                do {
                    intArrayList.addInt(this.input.readUInt32());
                } while (this.input.getTotalBytesRead() < totalBytesRead);
                requirePosition(totalBytesRead);
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        } else {
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 == 0) {
                do {
                    list.add(Integer.valueOf(this.input.readUInt32()));
                    if (!this.input.isAtEnd()) {
                        readTag = this.input.readTag();
                    } else {
                        return;
                    }
                } while (readTag == this.tag);
                this.nextTag = readTag;
            } else if (tagWireType2 == 2) {
                int totalBytesRead2 = this.input.getTotalBytesRead() + this.input.readUInt32();
                do {
                    list.add(Integer.valueOf(this.input.readUInt32()));
                } while (this.input.getTotalBytesRead() < totalBytesRead2);
                requirePosition(totalBytesRead2);
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
    }

    public void readEnumList(List list) {
        int readTag;
        int readTag2;
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType == 0) {
                do {
                    intArrayList.addInt(this.input.readEnum());
                    if (!this.input.isAtEnd()) {
                        readTag2 = this.input.readTag();
                    } else {
                        return;
                    }
                } while (readTag2 == this.tag);
                this.nextTag = readTag2;
            } else if (tagWireType == 2) {
                int totalBytesRead = this.input.getTotalBytesRead() + this.input.readUInt32();
                do {
                    intArrayList.addInt(this.input.readEnum());
                } while (this.input.getTotalBytesRead() < totalBytesRead);
                requirePosition(totalBytesRead);
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        } else {
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 == 0) {
                do {
                    list.add(Integer.valueOf(this.input.readEnum()));
                    if (!this.input.isAtEnd()) {
                        readTag = this.input.readTag();
                    } else {
                        return;
                    }
                } while (readTag == this.tag);
                this.nextTag = readTag;
            } else if (tagWireType2 == 2) {
                int totalBytesRead2 = this.input.getTotalBytesRead() + this.input.readUInt32();
                do {
                    list.add(Integer.valueOf(this.input.readEnum()));
                } while (this.input.getTotalBytesRead() < totalBytesRead2);
                requirePosition(totalBytesRead2);
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
    }

    public void readSFixed32List(List list) {
        int readTag;
        int readTag2;
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType == 2) {
                int readUInt32 = this.input.readUInt32();
                verifyPackedFixed32Length(readUInt32);
                int totalBytesRead = this.input.getTotalBytesRead() + readUInt32;
                do {
                    intArrayList.addInt(this.input.readSFixed32());
                } while (this.input.getTotalBytesRead() < totalBytesRead);
            } else if (tagWireType == 5) {
                do {
                    intArrayList.addInt(this.input.readSFixed32());
                    if (!this.input.isAtEnd()) {
                        readTag2 = this.input.readTag();
                    } else {
                        return;
                    }
                } while (readTag2 == this.tag);
                this.nextTag = readTag2;
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        } else {
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 == 2) {
                int readUInt322 = this.input.readUInt32();
                verifyPackedFixed32Length(readUInt322);
                int totalBytesRead2 = this.input.getTotalBytesRead() + readUInt322;
                do {
                    list.add(Integer.valueOf(this.input.readSFixed32()));
                } while (this.input.getTotalBytesRead() < totalBytesRead2);
            } else if (tagWireType2 == 5) {
                do {
                    list.add(Integer.valueOf(this.input.readSFixed32()));
                    if (!this.input.isAtEnd()) {
                        readTag = this.input.readTag();
                    } else {
                        return;
                    }
                } while (readTag == this.tag);
                this.nextTag = readTag;
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
    }

    public void readSFixed64List(List list) {
        int readTag;
        int readTag2;
        if (list instanceof LongArrayList) {
            LongArrayList longArrayList = (LongArrayList) list;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType == 1) {
                do {
                    longArrayList.addLong(this.input.readSFixed64());
                    if (!this.input.isAtEnd()) {
                        readTag2 = this.input.readTag();
                    } else {
                        return;
                    }
                } while (readTag2 == this.tag);
                this.nextTag = readTag2;
            } else if (tagWireType == 2) {
                int readUInt32 = this.input.readUInt32();
                verifyPackedFixed64Length(readUInt32);
                int totalBytesRead = this.input.getTotalBytesRead() + readUInt32;
                do {
                    longArrayList.addLong(this.input.readSFixed64());
                } while (this.input.getTotalBytesRead() < totalBytesRead);
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        } else {
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 == 1) {
                do {
                    list.add(Long.valueOf(this.input.readSFixed64()));
                    if (!this.input.isAtEnd()) {
                        readTag = this.input.readTag();
                    } else {
                        return;
                    }
                } while (readTag == this.tag);
                this.nextTag = readTag;
            } else if (tagWireType2 == 2) {
                int readUInt322 = this.input.readUInt32();
                verifyPackedFixed64Length(readUInt322);
                int totalBytesRead2 = this.input.getTotalBytesRead() + readUInt322;
                do {
                    list.add(Long.valueOf(this.input.readSFixed64()));
                } while (this.input.getTotalBytesRead() < totalBytesRead2);
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
    }

    public void readSInt32List(List list) {
        int readTag;
        int readTag2;
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType == 0) {
                do {
                    intArrayList.addInt(this.input.readSInt32());
                    if (!this.input.isAtEnd()) {
                        readTag2 = this.input.readTag();
                    } else {
                        return;
                    }
                } while (readTag2 == this.tag);
                this.nextTag = readTag2;
            } else if (tagWireType == 2) {
                int totalBytesRead = this.input.getTotalBytesRead() + this.input.readUInt32();
                do {
                    intArrayList.addInt(this.input.readSInt32());
                } while (this.input.getTotalBytesRead() < totalBytesRead);
                requirePosition(totalBytesRead);
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        } else {
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 == 0) {
                do {
                    list.add(Integer.valueOf(this.input.readSInt32()));
                    if (!this.input.isAtEnd()) {
                        readTag = this.input.readTag();
                    } else {
                        return;
                    }
                } while (readTag == this.tag);
                this.nextTag = readTag;
            } else if (tagWireType2 == 2) {
                int totalBytesRead2 = this.input.getTotalBytesRead() + this.input.readUInt32();
                do {
                    list.add(Integer.valueOf(this.input.readSInt32()));
                } while (this.input.getTotalBytesRead() < totalBytesRead2);
                requirePosition(totalBytesRead2);
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
    }

    public void readSInt64List(List list) {
        int readTag;
        int readTag2;
        if (list instanceof LongArrayList) {
            LongArrayList longArrayList = (LongArrayList) list;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType == 0) {
                do {
                    longArrayList.addLong(this.input.readSInt64());
                    if (!this.input.isAtEnd()) {
                        readTag2 = this.input.readTag();
                    } else {
                        return;
                    }
                } while (readTag2 == this.tag);
                this.nextTag = readTag2;
            } else if (tagWireType == 2) {
                int totalBytesRead = this.input.getTotalBytesRead() + this.input.readUInt32();
                do {
                    longArrayList.addLong(this.input.readSInt64());
                } while (this.input.getTotalBytesRead() < totalBytesRead);
                requirePosition(totalBytesRead);
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        } else {
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 == 0) {
                do {
                    list.add(Long.valueOf(this.input.readSInt64()));
                    if (!this.input.isAtEnd()) {
                        readTag = this.input.readTag();
                    } else {
                        return;
                    }
                } while (readTag == this.tag);
                this.nextTag = readTag;
            } else if (tagWireType2 == 2) {
                int totalBytesRead2 = this.input.getTotalBytesRead() + this.input.readUInt32();
                do {
                    list.add(Long.valueOf(this.input.readSInt64()));
                } while (this.input.getTotalBytesRead() < totalBytesRead2);
                requirePosition(totalBytesRead2);
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
    }

    private void verifyPackedFixed64Length(int i) {
        if ((i & 7) != 0) {
            throw InvalidProtocolBufferException.parseFailure();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0055, code lost:
        if (skipField() != false) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005d, code lost:
        throw new com.google.protobuf.InvalidProtocolBufferException(r6);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0051 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void readMap(java.util.Map r8, com.google.protobuf.MapEntryLite.Metadata r9, com.google.protobuf.ExtensionRegistryLite r10) {
        /*
            r7 = this;
            r0 = 2
            r7.requireWireType(r0)
            com.google.protobuf.CodedInputStream r1 = r7.input
            int r1 = r1.readUInt32()
            com.google.protobuf.CodedInputStream r2 = r7.input
            int r1 = r2.pushLimit(r1)
            java.lang.Object r2 = r9.defaultKey
            java.lang.Object r3 = r9.defaultValue
        L_0x0014:
            int r4 = r7.getFieldNumber()     // Catch:{ all -> 0x003a }
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r4 == r5) goto L_0x005e
            com.google.protobuf.CodedInputStream r5 = r7.input     // Catch:{ all -> 0x003a }
            boolean r5 = r5.isAtEnd()     // Catch:{ all -> 0x003a }
            if (r5 == 0) goto L_0x0026
            goto L_0x005e
        L_0x0026:
            r5 = 1
            java.lang.String r6 = "Unable to parse map entry."
            if (r4 == r5) goto L_0x0049
            if (r4 == r0) goto L_0x003c
            boolean r4 = r7.skipField()     // Catch:{ InvalidWireTypeException -> 0x0051 }
            if (r4 == 0) goto L_0x0034
            goto L_0x0014
        L_0x0034:
            com.google.protobuf.InvalidProtocolBufferException r4 = new com.google.protobuf.InvalidProtocolBufferException     // Catch:{ InvalidWireTypeException -> 0x0051 }
            r4.<init>((java.lang.String) r6)     // Catch:{ InvalidWireTypeException -> 0x0051 }
            throw r4     // Catch:{ InvalidWireTypeException -> 0x0051 }
        L_0x003a:
            r8 = move-exception
            goto L_0x0067
        L_0x003c:
            com.google.protobuf.WireFormat$FieldType r4 = r9.valueType     // Catch:{ InvalidWireTypeException -> 0x0051 }
            java.lang.Object r5 = r9.defaultValue     // Catch:{ InvalidWireTypeException -> 0x0051 }
            java.lang.Class r5 = r5.getClass()     // Catch:{ InvalidWireTypeException -> 0x0051 }
            java.lang.Object r3 = r7.readField(r4, r5, r10)     // Catch:{ InvalidWireTypeException -> 0x0051 }
            goto L_0x0014
        L_0x0049:
            com.google.protobuf.WireFormat$FieldType r4 = r9.keyType     // Catch:{ InvalidWireTypeException -> 0x0051 }
            r5 = 0
            java.lang.Object r2 = r7.readField(r4, r5, r5)     // Catch:{ InvalidWireTypeException -> 0x0051 }
            goto L_0x0014
        L_0x0051:
            boolean r4 = r7.skipField()     // Catch:{ all -> 0x003a }
            if (r4 == 0) goto L_0x0058
            goto L_0x0014
        L_0x0058:
            com.google.protobuf.InvalidProtocolBufferException r8 = new com.google.protobuf.InvalidProtocolBufferException     // Catch:{ all -> 0x003a }
            r8.<init>((java.lang.String) r6)     // Catch:{ all -> 0x003a }
            throw r8     // Catch:{ all -> 0x003a }
        L_0x005e:
            r8.put(r2, r3)     // Catch:{ all -> 0x003a }
            com.google.protobuf.CodedInputStream r8 = r7.input
            r8.popLimit(r1)
            return
        L_0x0067:
            com.google.protobuf.CodedInputStream r9 = r7.input
            r9.popLimit(r1)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStreamReader.readMap(java.util.Map, com.google.protobuf.MapEntryLite$Metadata, com.google.protobuf.ExtensionRegistryLite):void");
    }

    /* renamed from: com.google.protobuf.CodedInputStreamReader$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        /* JADX WARNING: Can't wrap try/catch for region: R(36:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|36) */
        /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0090 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x009c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x00a8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x00b4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x00c0 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.protobuf.WireFormat$FieldType[] r0 = com.google.protobuf.WireFormat.FieldType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$protobuf$WireFormat$FieldType = r0
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.BOOL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.BYTES     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.DOUBLE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.ENUM     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.FIXED32     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.FIXED64     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.FLOAT     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.INT32     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x006c }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.INT64     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.MESSAGE     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0084 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.SFIXED32     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0090 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.SFIXED64     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x009c }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.SINT32     // Catch:{ NoSuchFieldError -> 0x009c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x00a8 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.SINT64     // Catch:{ NoSuchFieldError -> 0x00a8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a8 }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a8 }
            L_0x00a8:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x00b4 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.STRING     // Catch:{ NoSuchFieldError -> 0x00b4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b4 }
                r2 = 15
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b4 }
            L_0x00b4:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x00c0 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.UINT32     // Catch:{ NoSuchFieldError -> 0x00c0 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c0 }
                r2 = 16
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00c0 }
            L_0x00c0:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x00cc }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.UINT64     // Catch:{ NoSuchFieldError -> 0x00cc }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00cc }
                r2 = 17
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00cc }
            L_0x00cc:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStreamReader.AnonymousClass1.<clinit>():void");
        }
    }

    private Object readField(WireFormat.FieldType fieldType, Class cls, ExtensionRegistryLite extensionRegistryLite) {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[fieldType.ordinal()]) {
            case 1:
                return Boolean.valueOf(readBool());
            case 2:
                return readBytes();
            case 3:
                return Double.valueOf(readDouble());
            case 4:
                return Integer.valueOf(readEnum());
            case 5:
                return Integer.valueOf(readFixed32());
            case 6:
                return Long.valueOf(readFixed64());
            case 7:
                return Float.valueOf(readFloat());
            case 8:
                return Integer.valueOf(readInt32());
            case 9:
                return Long.valueOf(readInt64());
            case 10:
                return readMessage(cls, extensionRegistryLite);
            case 11:
                return Integer.valueOf(readSFixed32());
            case 12:
                return Long.valueOf(readSFixed64());
            case 13:
                return Integer.valueOf(readSInt32());
            case 14:
                return Long.valueOf(readSInt64());
            case 15:
                return readStringRequireUtf8();
            case 16:
                return Integer.valueOf(readUInt32());
            case 17:
                return Long.valueOf(readUInt64());
            default:
                throw new IllegalArgumentException("unsupported field type.");
        }
    }

    private void verifyPackedFixed32Length(int i) {
        if ((i & 3) != 0) {
            throw InvalidProtocolBufferException.parseFailure();
        }
    }

    private void requirePosition(int i) {
        if (this.input.getTotalBytesRead() != i) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
    }
}
