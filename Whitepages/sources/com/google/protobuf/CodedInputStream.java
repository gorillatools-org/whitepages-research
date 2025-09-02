package com.google.protobuf;

import com.salesforce.marketingcloud.b;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class CodedInputStream {
    private static volatile int defaultRecursionLimit = 100;
    int recursionDepth;
    int recursionLimit;
    private boolean shouldDiscardUnknownFields;
    int sizeLimit;
    CodedInputStreamReader wrapper;

    public static int decodeZigZag32(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    public static long decodeZigZag64(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    public abstract void checkLastTagWas(int i);

    public abstract int getTotalBytesRead();

    public abstract boolean isAtEnd();

    public abstract void popLimit(int i);

    public abstract int pushLimit(int i);

    public abstract boolean readBool();

    public abstract ByteString readBytes();

    public abstract double readDouble();

    public abstract int readEnum();

    public abstract int readFixed32();

    public abstract long readFixed64();

    public abstract float readFloat();

    public abstract int readInt32();

    public abstract long readInt64();

    public abstract int readSFixed32();

    public abstract long readSFixed64();

    public abstract int readSInt32();

    public abstract long readSInt64();

    public abstract String readString();

    public abstract String readStringRequireUtf8();

    public abstract int readTag();

    public abstract int readUInt32();

    public abstract long readUInt64();

    public abstract boolean skipField(int i);

    public static CodedInputStream newInstance(InputStream inputStream) {
        return newInstance(inputStream, (int) b.v);
    }

    public static CodedInputStream newInstance(InputStream inputStream, int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("bufferSize must be > 0");
        } else if (inputStream == null) {
            return newInstance(Internal.EMPTY_BYTE_ARRAY);
        } else {
            return new StreamDecoder(inputStream, i);
        }
    }

    public static CodedInputStream newInstance(byte[] bArr) {
        return newInstance(bArr, 0, bArr.length);
    }

    public static CodedInputStream newInstance(byte[] bArr, int i, int i2) {
        return newInstance(bArr, i, i2, false);
    }

    static CodedInputStream newInstance(byte[] bArr, int i, int i2, boolean z) {
        ArrayDecoder arrayDecoder = new ArrayDecoder(bArr, i, i2, z);
        try {
            arrayDecoder.pushLimit(i2);
            return arrayDecoder;
        } catch (InvalidProtocolBufferException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static CodedInputStream newInstance(ByteBuffer byteBuffer) {
        return newInstance(byteBuffer, false);
    }

    static CodedInputStream newInstance(ByteBuffer byteBuffer, boolean z) {
        if (byteBuffer.hasArray()) {
            return newInstance(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining(), z);
        }
        if (byteBuffer.isDirect() && UnsafeDirectNioDecoder.isSupported()) {
            return new UnsafeDirectNioDecoder(byteBuffer, z);
        }
        int remaining = byteBuffer.remaining();
        byte[] bArr = new byte[remaining];
        byteBuffer.duplicate().get(bArr);
        return newInstance(bArr, 0, remaining, true);
    }

    public void checkRecursionLimit() {
        if (this.recursionDepth >= this.recursionLimit) {
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }
    }

    private CodedInputStream() {
        this.recursionLimit = defaultRecursionLimit;
        this.sizeLimit = Integer.MAX_VALUE;
        this.shouldDiscardUnknownFields = false;
    }

    public void skipMessage() {
        int readTag;
        do {
            readTag = readTag();
            if (readTag != 0) {
                checkRecursionLimit();
                this.recursionDepth++;
                this.recursionDepth--;
            } else {
                return;
            }
        } while (skipField(readTag));
    }

    public static int readRawVarint32(int i, InputStream inputStream) {
        if ((i & 128) == 0) {
            return i;
        }
        int i2 = i & 127;
        int i3 = 7;
        while (i3 < 32) {
            int read = inputStream.read();
            if (read != -1) {
                i2 |= (read & 127) << i3;
                if ((read & 128) == 0) {
                    return i2;
                }
                i3 += 7;
            } else {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }
        while (i3 < 64) {
            int read2 = inputStream.read();
            if (read2 == -1) {
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if ((read2 & 128) == 0) {
                return i2;
            } else {
                i3 += 7;
            }
        }
        throw InvalidProtocolBufferException.malformedVarint();
    }

    private static final class ArrayDecoder extends CodedInputStream {
        private final byte[] buffer;
        private int bufferSizeAfterLimit;
        private int currentLimit;
        private boolean enableAliasing;
        private final boolean immutable;
        private int lastTag;
        private int limit;
        private int pos;
        private int startPos;

        private ArrayDecoder(byte[] bArr, int i, int i2, boolean z) {
            super();
            this.currentLimit = Integer.MAX_VALUE;
            this.buffer = bArr;
            this.limit = i2 + i;
            this.pos = i;
            this.startPos = i;
            this.immutable = z;
        }

        public int readTag() {
            if (isAtEnd()) {
                this.lastTag = 0;
                return 0;
            }
            int readRawVarint32 = readRawVarint32();
            this.lastTag = readRawVarint32;
            if (WireFormat.getTagFieldNumber(readRawVarint32) != 0) {
                return this.lastTag;
            }
            throw InvalidProtocolBufferException.invalidTag();
        }

        public void checkLastTagWas(int i) {
            if (this.lastTag != i) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        public boolean skipField(int i) {
            int tagWireType = WireFormat.getTagWireType(i);
            if (tagWireType == 0) {
                skipRawVarint();
                return true;
            } else if (tagWireType == 1) {
                skipRawBytes(8);
                return true;
            } else if (tagWireType == 2) {
                skipRawBytes(readRawVarint32());
                return true;
            } else if (tagWireType == 3) {
                skipMessage();
                checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(i), 4));
                return true;
            } else if (tagWireType == 4) {
                return false;
            } else {
                if (tagWireType == 5) {
                    skipRawBytes(4);
                    return true;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        public double readDouble() {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        public float readFloat() {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        public long readUInt64() {
            return readRawVarint64();
        }

        public long readInt64() {
            return readRawVarint64();
        }

        public int readInt32() {
            return readRawVarint32();
        }

        public long readFixed64() {
            return readRawLittleEndian64();
        }

        public int readFixed32() {
            return readRawLittleEndian32();
        }

        public boolean readBool() {
            return readRawVarint64() != 0;
        }

        public String readString() {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                int i = this.limit;
                int i2 = this.pos;
                if (readRawVarint32 <= i - i2) {
                    String str = new String(this.buffer, i2, readRawVarint32, Internal.UTF_8);
                    this.pos += readRawVarint32;
                    return str;
                }
            }
            if (readRawVarint32 == 0) {
                return "";
            }
            if (readRawVarint32 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public String readStringRequireUtf8() {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                int i = this.limit;
                int i2 = this.pos;
                if (readRawVarint32 <= i - i2) {
                    String decodeUtf8 = Utf8.decodeUtf8(this.buffer, i2, readRawVarint32);
                    this.pos += readRawVarint32;
                    return decodeUtf8;
                }
            }
            if (readRawVarint32 == 0) {
                return "";
            }
            if (readRawVarint32 <= 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public ByteString readBytes() {
            ByteString byteString;
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                int i = this.limit;
                int i2 = this.pos;
                if (readRawVarint32 <= i - i2) {
                    if (!this.immutable || !this.enableAliasing) {
                        byteString = ByteString.copyFrom(this.buffer, i2, readRawVarint32);
                    } else {
                        byteString = ByteString.wrap(this.buffer, i2, readRawVarint32);
                    }
                    this.pos += readRawVarint32;
                    return byteString;
                }
            }
            if (readRawVarint32 == 0) {
                return ByteString.EMPTY;
            }
            return ByteString.wrap(readRawBytes(readRawVarint32));
        }

        public int readUInt32() {
            return readRawVarint32();
        }

        public int readEnum() {
            return readRawVarint32();
        }

        public int readSFixed32() {
            return readRawLittleEndian32();
        }

        public long readSFixed64() {
            return readRawLittleEndian64();
        }

        public int readSInt32() {
            return CodedInputStream.decodeZigZag32(readRawVarint32());
        }

        public long readSInt64() {
            return CodedInputStream.decodeZigZag64(readRawVarint64());
        }

        public int readRawVarint32() {
            byte b;
            byte b2;
            int i = this.pos;
            int i2 = this.limit;
            if (i2 != i) {
                byte[] bArr = this.buffer;
                int i3 = i + 1;
                byte b3 = bArr[i];
                if (b3 >= 0) {
                    this.pos = i3;
                    return b3;
                } else if (i2 - i3 >= 9) {
                    int i4 = i + 2;
                    byte b4 = (bArr[i3] << 7) ^ b3;
                    if (b4 < 0) {
                        b = b4 ^ Byte.MIN_VALUE;
                    } else {
                        int i5 = i + 3;
                        byte b5 = (bArr[i4] << 14) ^ b4;
                        if (b5 >= 0) {
                            b2 = b5 ^ 16256;
                        } else {
                            int i6 = i + 4;
                            byte b6 = b5 ^ (bArr[i5] << 21);
                            if (b6 < 0) {
                                b = -2080896 ^ b6;
                            } else {
                                i5 = i + 5;
                                byte b7 = bArr[i6];
                                byte b8 = (b6 ^ (b7 << 28)) ^ 266354560;
                                if (b7 < 0) {
                                    i6 = i + 6;
                                    if (bArr[i5] < 0) {
                                        i5 = i + 7;
                                        if (bArr[i6] < 0) {
                                            i6 = i + 8;
                                            if (bArr[i5] < 0) {
                                                i5 = i + 9;
                                                if (bArr[i6] < 0) {
                                                    int i7 = i + 10;
                                                    if (bArr[i5] >= 0) {
                                                        byte b9 = b8;
                                                        i4 = i7;
                                                        b = b9;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    b = b8;
                                }
                                b2 = b8;
                            }
                            i4 = i6;
                        }
                        i4 = i5;
                    }
                    this.pos = i4;
                    return b;
                }
            }
            return (int) readRawVarint64SlowPath();
        }

        private void skipRawVarint() {
            if (this.limit - this.pos >= 10) {
                skipRawVarintFastPath();
            } else {
                skipRawVarintSlowPath();
            }
        }

        private void skipRawVarintFastPath() {
            int i = 0;
            while (i < 10) {
                byte[] bArr = this.buffer;
                int i2 = this.pos;
                this.pos = i2 + 1;
                if (bArr[i2] < 0) {
                    i++;
                } else {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private void skipRawVarintSlowPath() {
            int i = 0;
            while (i < 10) {
                if (readRawByte() < 0) {
                    i++;
                } else {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public long readRawVarint64() {
            long j;
            long j2;
            long j3;
            int i = this.pos;
            int i2 = this.limit;
            if (i2 != i) {
                byte[] bArr = this.buffer;
                int i3 = i + 1;
                byte b = bArr[i];
                if (b >= 0) {
                    this.pos = i3;
                    return (long) b;
                } else if (i2 - i3 >= 9) {
                    int i4 = i + 2;
                    byte b2 = (bArr[i3] << 7) ^ b;
                    if (b2 < 0) {
                        j = (long) (b2 ^ Byte.MIN_VALUE);
                    } else {
                        int i5 = i + 3;
                        byte b3 = (bArr[i4] << 14) ^ b2;
                        if (b3 >= 0) {
                            j = (long) (b3 ^ 16256);
                            i4 = i5;
                        } else {
                            int i6 = i + 4;
                            byte b4 = b3 ^ (bArr[i5] << 21);
                            if (b4 < 0) {
                                i4 = i6;
                                j = (long) (-2080896 ^ b4);
                            } else {
                                long j4 = (long) b4;
                                int i7 = i + 5;
                                long j5 = j4 ^ (((long) bArr[i6]) << 28);
                                if (j5 >= 0) {
                                    j3 = 266354560;
                                } else {
                                    int i8 = i + 6;
                                    long j6 = j5 ^ (((long) bArr[i7]) << 35);
                                    if (j6 < 0) {
                                        j2 = -34093383808L;
                                    } else {
                                        i7 = i + 7;
                                        j5 = j6 ^ (((long) bArr[i8]) << 42);
                                        if (j5 >= 0) {
                                            j3 = 4363953127296L;
                                        } else {
                                            i8 = i + 8;
                                            j6 = j5 ^ (((long) bArr[i7]) << 49);
                                            if (j6 < 0) {
                                                j2 = -558586000294016L;
                                            } else {
                                                i4 = i + 9;
                                                long j7 = (j6 ^ (((long) bArr[i8]) << 56)) ^ 71499008037633920L;
                                                if (j7 < 0) {
                                                    int i9 = i + 10;
                                                    if (((long) bArr[i4]) >= 0) {
                                                        i4 = i9;
                                                    }
                                                }
                                                j = j7;
                                            }
                                        }
                                    }
                                    j = j6 ^ j2;
                                    i4 = i8;
                                }
                                j = j5 ^ j3;
                            }
                        }
                    }
                    this.pos = i4;
                    return j;
                }
            }
            return readRawVarint64SlowPath();
        }

        /* access modifiers changed from: package-private */
        public long readRawVarint64SlowPath() {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                byte readRawByte = readRawByte();
                j |= ((long) (readRawByte & Byte.MAX_VALUE)) << i;
                if ((readRawByte & 128) == 0) {
                    return j;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public int readRawLittleEndian32() {
            int i = this.pos;
            if (this.limit - i >= 4) {
                byte[] bArr = this.buffer;
                this.pos = i + 4;
                return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public long readRawLittleEndian64() {
            int i = this.pos;
            if (this.limit - i >= 8) {
                byte[] bArr = this.buffer;
                this.pos = i + 8;
                return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public int pushLimit(int i) {
            if (i >= 0) {
                int totalBytesRead = i + getTotalBytesRead();
                if (totalBytesRead >= 0) {
                    int i2 = this.currentLimit;
                    if (totalBytesRead <= i2) {
                        this.currentLimit = totalBytesRead;
                        recomputeBufferSizeAfterLimit();
                        return i2;
                    }
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
                throw InvalidProtocolBufferException.parseFailure();
            }
            throw InvalidProtocolBufferException.negativeSize();
        }

        private void recomputeBufferSizeAfterLimit() {
            int i = this.limit + this.bufferSizeAfterLimit;
            this.limit = i;
            int i2 = i - this.startPos;
            int i3 = this.currentLimit;
            if (i2 > i3) {
                int i4 = i2 - i3;
                this.bufferSizeAfterLimit = i4;
                this.limit = i - i4;
                return;
            }
            this.bufferSizeAfterLimit = 0;
        }

        public void popLimit(int i) {
            this.currentLimit = i;
            recomputeBufferSizeAfterLimit();
        }

        public boolean isAtEnd() {
            return this.pos == this.limit;
        }

        public int getTotalBytesRead() {
            return this.pos - this.startPos;
        }

        public byte readRawByte() {
            int i = this.pos;
            if (i != this.limit) {
                byte[] bArr = this.buffer;
                this.pos = i + 1;
                return bArr[i];
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public byte[] readRawBytes(int i) {
            if (i > 0) {
                int i2 = this.limit;
                int i3 = this.pos;
                if (i <= i2 - i3) {
                    int i4 = i + i3;
                    this.pos = i4;
                    return Arrays.copyOfRange(this.buffer, i3, i4);
                }
            }
            if (i > 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if (i == 0) {
                return Internal.EMPTY_BYTE_ARRAY;
            } else {
                throw InvalidProtocolBufferException.negativeSize();
            }
        }

        public void skipRawBytes(int i) {
            if (i >= 0) {
                int i2 = this.limit;
                int i3 = this.pos;
                if (i <= i2 - i3) {
                    this.pos = i3 + i;
                    return;
                }
            }
            if (i < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }
    }

    private static final class UnsafeDirectNioDecoder extends CodedInputStream {
        private final long address;
        private final ByteBuffer buffer;
        private int bufferSizeAfterLimit;
        private int currentLimit;
        private boolean enableAliasing;
        private final boolean immutable;
        private int lastTag;
        private long limit;
        private long pos;
        private long startPos;

        static boolean isSupported() {
            return UnsafeUtil.hasUnsafeByteBufferOperations();
        }

        private UnsafeDirectNioDecoder(ByteBuffer byteBuffer, boolean z) {
            super();
            this.currentLimit = Integer.MAX_VALUE;
            this.buffer = byteBuffer;
            long addressOffset = UnsafeUtil.addressOffset(byteBuffer);
            this.address = addressOffset;
            this.limit = ((long) byteBuffer.limit()) + addressOffset;
            long position = addressOffset + ((long) byteBuffer.position());
            this.pos = position;
            this.startPos = position;
            this.immutable = z;
        }

        public int readTag() {
            if (isAtEnd()) {
                this.lastTag = 0;
                return 0;
            }
            int readRawVarint32 = readRawVarint32();
            this.lastTag = readRawVarint32;
            if (WireFormat.getTagFieldNumber(readRawVarint32) != 0) {
                return this.lastTag;
            }
            throw InvalidProtocolBufferException.invalidTag();
        }

        public void checkLastTagWas(int i) {
            if (this.lastTag != i) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        public boolean skipField(int i) {
            int tagWireType = WireFormat.getTagWireType(i);
            if (tagWireType == 0) {
                skipRawVarint();
                return true;
            } else if (tagWireType == 1) {
                skipRawBytes(8);
                return true;
            } else if (tagWireType == 2) {
                skipRawBytes(readRawVarint32());
                return true;
            } else if (tagWireType == 3) {
                skipMessage();
                checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(i), 4));
                return true;
            } else if (tagWireType == 4) {
                return false;
            } else {
                if (tagWireType == 5) {
                    skipRawBytes(4);
                    return true;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        public double readDouble() {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        public float readFloat() {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        public long readUInt64() {
            return readRawVarint64();
        }

        public long readInt64() {
            return readRawVarint64();
        }

        public int readInt32() {
            return readRawVarint32();
        }

        public long readFixed64() {
            return readRawLittleEndian64();
        }

        public int readFixed32() {
            return readRawLittleEndian32();
        }

        public boolean readBool() {
            return readRawVarint64() != 0;
        }

        public String readString() {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0 && readRawVarint32 <= remaining()) {
                byte[] bArr = new byte[readRawVarint32];
                long j = (long) readRawVarint32;
                UnsafeUtil.copyMemory(this.pos, bArr, 0, j);
                String str = new String(bArr, Internal.UTF_8);
                this.pos += j;
                return str;
            } else if (readRawVarint32 == 0) {
                return "";
            } else {
                if (readRawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        public String readStringRequireUtf8() {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0 && readRawVarint32 <= remaining()) {
                String decodeUtf8 = Utf8.decodeUtf8(this.buffer, bufferPos(this.pos), readRawVarint32);
                this.pos += (long) readRawVarint32;
                return decodeUtf8;
            } else if (readRawVarint32 == 0) {
                return "";
            } else {
                if (readRawVarint32 <= 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        public ByteString readBytes() {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 <= 0 || readRawVarint32 > remaining()) {
                if (readRawVarint32 == 0) {
                    return ByteString.EMPTY;
                }
                if (readRawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if (!this.immutable || !this.enableAliasing) {
                byte[] bArr = new byte[readRawVarint32];
                long j = (long) readRawVarint32;
                UnsafeUtil.copyMemory(this.pos, bArr, 0, j);
                this.pos += j;
                return ByteString.wrap(bArr);
            } else {
                long j2 = this.pos;
                long j3 = (long) readRawVarint32;
                ByteBuffer slice = slice(j2, j2 + j3);
                this.pos += j3;
                return ByteString.wrap(slice);
            }
        }

        public int readUInt32() {
            return readRawVarint32();
        }

        public int readEnum() {
            return readRawVarint32();
        }

        public int readSFixed32() {
            return readRawLittleEndian32();
        }

        public long readSFixed64() {
            return readRawLittleEndian64();
        }

        public int readSInt32() {
            return CodedInputStream.decodeZigZag32(readRawVarint32());
        }

        public long readSInt64() {
            return CodedInputStream.decodeZigZag64(readRawVarint64());
        }

        /* JADX WARNING: Code restructure failed: missing block: B:29:0x008c, code lost:
            if (com.google.protobuf.UnsafeUtil.getByte(r3) < 0) goto L_0x008e;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int readRawVarint32() {
            /*
                r9 = this;
                long r0 = r9.pos
                long r2 = r9.limit
                int r2 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
                if (r2 != 0) goto L_0x000a
                goto L_0x008e
            L_0x000a:
                r2 = 1
                long r2 = r2 + r0
                byte r4 = com.google.protobuf.UnsafeUtil.getByte(r0)
                if (r4 < 0) goto L_0x0016
                r9.pos = r2
                return r4
            L_0x0016:
                long r5 = r9.limit
                long r5 = r5 - r2
                r7 = 9
                int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
                if (r5 >= 0) goto L_0x0021
                goto L_0x008e
            L_0x0021:
                r5 = 2
                long r5 = r5 + r0
                byte r2 = com.google.protobuf.UnsafeUtil.getByte(r2)
                int r2 = r2 << 7
                r2 = r2 ^ r4
                if (r2 >= 0) goto L_0x0031
                r0 = r2 ^ -128(0xffffffffffffff80, float:NaN)
                goto L_0x0098
            L_0x0031:
                r3 = 3
                long r3 = r3 + r0
                byte r5 = com.google.protobuf.UnsafeUtil.getByte(r5)
                int r5 = r5 << 14
                r2 = r2 ^ r5
                if (r2 < 0) goto L_0x0041
                r0 = r2 ^ 16256(0x3f80, float:2.278E-41)
            L_0x003f:
                r5 = r3
                goto L_0x0098
            L_0x0041:
                r5 = 4
                long r5 = r5 + r0
                byte r3 = com.google.protobuf.UnsafeUtil.getByte(r3)
                int r3 = r3 << 21
                r2 = r2 ^ r3
                if (r2 >= 0) goto L_0x0052
                r0 = -2080896(0xffffffffffe03f80, float:NaN)
                r0 = r0 ^ r2
                goto L_0x0098
            L_0x0052:
                r3 = 5
                long r3 = r3 + r0
                byte r5 = com.google.protobuf.UnsafeUtil.getByte(r5)
                int r6 = r5 << 28
                r2 = r2 ^ r6
                r6 = 266354560(0xfe03f80, float:2.2112565E-29)
                r2 = r2 ^ r6
                if (r5 >= 0) goto L_0x0096
                r5 = 6
                long r5 = r5 + r0
                byte r3 = com.google.protobuf.UnsafeUtil.getByte(r3)
                if (r3 >= 0) goto L_0x0094
                r3 = 7
                long r3 = r3 + r0
                byte r5 = com.google.protobuf.UnsafeUtil.getByte(r5)
                if (r5 >= 0) goto L_0x0096
                r5 = 8
                long r5 = r5 + r0
                byte r3 = com.google.protobuf.UnsafeUtil.getByte(r3)
                if (r3 >= 0) goto L_0x0094
                long r3 = r0 + r7
                byte r5 = com.google.protobuf.UnsafeUtil.getByte(r5)
                if (r5 >= 0) goto L_0x0096
                r5 = 10
                long r5 = r5 + r0
                byte r0 = com.google.protobuf.UnsafeUtil.getByte(r3)
                if (r0 >= 0) goto L_0x0094
            L_0x008e:
                long r0 = r9.readRawVarint64SlowPath()
                int r0 = (int) r0
                return r0
            L_0x0094:
                r0 = r2
                goto L_0x0098
            L_0x0096:
                r0 = r2
                goto L_0x003f
            L_0x0098:
                r9.pos = r5
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.UnsafeDirectNioDecoder.readRawVarint32():int");
        }

        private void skipRawVarint() {
            if (remaining() >= 10) {
                skipRawVarintFastPath();
            } else {
                skipRawVarintSlowPath();
            }
        }

        private void skipRawVarintFastPath() {
            int i = 0;
            while (i < 10) {
                long j = this.pos;
                this.pos = 1 + j;
                if (UnsafeUtil.getByte(j) < 0) {
                    i++;
                } else {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private void skipRawVarintSlowPath() {
            int i = 0;
            while (i < 10) {
                if (readRawByte() < 0) {
                    i++;
                } else {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public long readRawVarint64() {
            long j;
            long j2;
            long j3;
            byte b;
            long j4 = this.pos;
            if (this.limit != j4) {
                long j5 = 1 + j4;
                byte b2 = UnsafeUtil.getByte(j4);
                if (b2 >= 0) {
                    this.pos = j5;
                    return (long) b2;
                } else if (this.limit - j5 >= 9) {
                    long j6 = 2 + j4;
                    byte b3 = (UnsafeUtil.getByte(j5) << 7) ^ b2;
                    if (b3 < 0) {
                        b = b3 ^ Byte.MIN_VALUE;
                    } else {
                        long j7 = 3 + j4;
                        byte b4 = b3 ^ (UnsafeUtil.getByte(j6) << 14);
                        if (b4 >= 0) {
                            j = (long) (b4 ^ 16256);
                            j6 = j7;
                        } else {
                            j6 = 4 + j4;
                            byte b5 = b4 ^ (UnsafeUtil.getByte(j7) << 21);
                            if (b5 < 0) {
                                b = -2080896 ^ b5;
                            } else {
                                long j8 = 5 + j4;
                                long j9 = ((long) b5) ^ (((long) UnsafeUtil.getByte(j6)) << 28);
                                if (j9 >= 0) {
                                    j3 = 266354560;
                                } else {
                                    long j10 = 6 + j4;
                                    long j11 = j9 ^ (((long) UnsafeUtil.getByte(j8)) << 35);
                                    if (j11 < 0) {
                                        j2 = -34093383808L;
                                    } else {
                                        j8 = 7 + j4;
                                        j9 = j11 ^ (((long) UnsafeUtil.getByte(j10)) << 42);
                                        if (j9 >= 0) {
                                            j3 = 4363953127296L;
                                        } else {
                                            j10 = 8 + j4;
                                            j11 = j9 ^ (((long) UnsafeUtil.getByte(j8)) << 49);
                                            if (j11 < 0) {
                                                j2 = -558586000294016L;
                                            } else {
                                                long j12 = j4 + 9;
                                                long j13 = (j11 ^ (((long) UnsafeUtil.getByte(j10)) << 56)) ^ 71499008037633920L;
                                                if (j13 < 0) {
                                                    long j14 = j4 + 10;
                                                    if (((long) UnsafeUtil.getByte(j12)) >= 0) {
                                                        j6 = j14;
                                                        j = j13;
                                                    }
                                                } else {
                                                    j = j13;
                                                    j6 = j12;
                                                }
                                            }
                                        }
                                    }
                                    j = j2 ^ j11;
                                    j6 = j10;
                                }
                                j = j3 ^ j9;
                                j6 = j8;
                            }
                        }
                        this.pos = j6;
                        return j;
                    }
                    j = (long) b;
                    this.pos = j6;
                    return j;
                }
            }
            return readRawVarint64SlowPath();
        }

        /* access modifiers changed from: package-private */
        public long readRawVarint64SlowPath() {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                byte readRawByte = readRawByte();
                j |= ((long) (readRawByte & Byte.MAX_VALUE)) << i;
                if ((readRawByte & 128) == 0) {
                    return j;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public int readRawLittleEndian32() {
            long j = this.pos;
            if (this.limit - j >= 4) {
                this.pos = 4 + j;
                return ((UnsafeUtil.getByte(j + 3) & 255) << 24) | (UnsafeUtil.getByte(j) & 255) | ((UnsafeUtil.getByte(1 + j) & 255) << 8) | ((UnsafeUtil.getByte(2 + j) & 255) << 16);
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public long readRawLittleEndian64() {
            long j = this.pos;
            if (this.limit - j >= 8) {
                this.pos = 8 + j;
                return ((((long) UnsafeUtil.getByte(j + 7)) & 255) << 56) | (((long) UnsafeUtil.getByte(j)) & 255) | ((((long) UnsafeUtil.getByte(1 + j)) & 255) << 8) | ((((long) UnsafeUtil.getByte(2 + j)) & 255) << 16) | ((((long) UnsafeUtil.getByte(3 + j)) & 255) << 24) | ((((long) UnsafeUtil.getByte(4 + j)) & 255) << 32) | ((((long) UnsafeUtil.getByte(5 + j)) & 255) << 40) | ((((long) UnsafeUtil.getByte(6 + j)) & 255) << 48);
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public int pushLimit(int i) {
            if (i >= 0) {
                int totalBytesRead = i + getTotalBytesRead();
                int i2 = this.currentLimit;
                if (totalBytesRead <= i2) {
                    this.currentLimit = totalBytesRead;
                    recomputeBufferSizeAfterLimit();
                    return i2;
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            throw InvalidProtocolBufferException.negativeSize();
        }

        public void popLimit(int i) {
            this.currentLimit = i;
            recomputeBufferSizeAfterLimit();
        }

        public boolean isAtEnd() {
            return this.pos == this.limit;
        }

        public int getTotalBytesRead() {
            return (int) (this.pos - this.startPos);
        }

        public byte readRawByte() {
            long j = this.pos;
            if (j != this.limit) {
                this.pos = 1 + j;
                return UnsafeUtil.getByte(j);
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public void skipRawBytes(int i) {
            if (i >= 0 && i <= remaining()) {
                this.pos += (long) i;
            } else if (i < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            } else {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        private void recomputeBufferSizeAfterLimit() {
            long j = this.limit + ((long) this.bufferSizeAfterLimit);
            this.limit = j;
            int i = (int) (j - this.startPos);
            int i2 = this.currentLimit;
            if (i > i2) {
                int i3 = i - i2;
                this.bufferSizeAfterLimit = i3;
                this.limit = j - ((long) i3);
                return;
            }
            this.bufferSizeAfterLimit = 0;
        }

        private int remaining() {
            return (int) (this.limit - this.pos);
        }

        private int bufferPos(long j) {
            return (int) (j - this.address);
        }

        private ByteBuffer slice(long j, long j2) {
            int position = this.buffer.position();
            int limit2 = this.buffer.limit();
            ByteBuffer byteBuffer = this.buffer;
            try {
                byteBuffer.position(bufferPos(j));
                byteBuffer.limit(bufferPos(j2));
                ByteBuffer slice = this.buffer.slice();
                byteBuffer.position(position);
                byteBuffer.limit(limit2);
                return slice;
            } catch (IllegalArgumentException e) {
                InvalidProtocolBufferException truncatedMessage = InvalidProtocolBufferException.truncatedMessage();
                truncatedMessage.initCause(e);
                throw truncatedMessage;
            } catch (Throwable th) {
                byteBuffer.position(position);
                byteBuffer.limit(limit2);
                throw th;
            }
        }
    }

    private static final class StreamDecoder extends CodedInputStream {
        private final byte[] buffer;
        private int bufferSize;
        private int bufferSizeAfterLimit;
        private int currentLimit;
        private final InputStream input;
        private int lastTag;
        private int pos;
        private int totalBytesRetired;

        private StreamDecoder(InputStream inputStream, int i) {
            super();
            this.currentLimit = Integer.MAX_VALUE;
            Internal.checkNotNull(inputStream, "input");
            this.input = inputStream;
            this.buffer = new byte[i];
            this.bufferSize = 0;
            this.pos = 0;
            this.totalBytesRetired = 0;
        }

        private static int read(InputStream inputStream, byte[] bArr, int i, int i2) {
            try {
                return inputStream.read(bArr, i, i2);
            } catch (InvalidProtocolBufferException e) {
                e.setThrownFromInputStream();
                throw e;
            }
        }

        private static long skip(InputStream inputStream, long j) {
            try {
                return inputStream.skip(j);
            } catch (InvalidProtocolBufferException e) {
                e.setThrownFromInputStream();
                throw e;
            }
        }

        private static int available(InputStream inputStream) {
            try {
                return inputStream.available();
            } catch (InvalidProtocolBufferException e) {
                e.setThrownFromInputStream();
                throw e;
            }
        }

        public int readTag() {
            if (isAtEnd()) {
                this.lastTag = 0;
                return 0;
            }
            int readRawVarint32 = readRawVarint32();
            this.lastTag = readRawVarint32;
            if (WireFormat.getTagFieldNumber(readRawVarint32) != 0) {
                return this.lastTag;
            }
            throw InvalidProtocolBufferException.invalidTag();
        }

        public void checkLastTagWas(int i) {
            if (this.lastTag != i) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        public boolean skipField(int i) {
            int tagWireType = WireFormat.getTagWireType(i);
            if (tagWireType == 0) {
                skipRawVarint();
                return true;
            } else if (tagWireType == 1) {
                skipRawBytes(8);
                return true;
            } else if (tagWireType == 2) {
                skipRawBytes(readRawVarint32());
                return true;
            } else if (tagWireType == 3) {
                skipMessage();
                checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(i), 4));
                return true;
            } else if (tagWireType == 4) {
                return false;
            } else {
                if (tagWireType == 5) {
                    skipRawBytes(4);
                    return true;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        public double readDouble() {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        public float readFloat() {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        public long readUInt64() {
            return readRawVarint64();
        }

        public long readInt64() {
            return readRawVarint64();
        }

        public int readInt32() {
            return readRawVarint32();
        }

        public long readFixed64() {
            return readRawLittleEndian64();
        }

        public int readFixed32() {
            return readRawLittleEndian32();
        }

        public boolean readBool() {
            return readRawVarint64() != 0;
        }

        public String readString() {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                int i = this.bufferSize;
                int i2 = this.pos;
                if (readRawVarint32 <= i - i2) {
                    String str = new String(this.buffer, i2, readRawVarint32, Internal.UTF_8);
                    this.pos += readRawVarint32;
                    return str;
                }
            }
            if (readRawVarint32 == 0) {
                return "";
            }
            if (readRawVarint32 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            } else if (readRawVarint32 > this.bufferSize) {
                return new String(readRawBytesSlowPath(readRawVarint32, false), Internal.UTF_8);
            } else {
                refillBuffer(readRawVarint32);
                String str2 = new String(this.buffer, this.pos, readRawVarint32, Internal.UTF_8);
                this.pos += readRawVarint32;
                return str2;
            }
        }

        public String readStringRequireUtf8() {
            byte[] bArr;
            int readRawVarint32 = readRawVarint32();
            int i = this.pos;
            int i2 = this.bufferSize;
            if (readRawVarint32 <= i2 - i && readRawVarint32 > 0) {
                bArr = this.buffer;
                this.pos = i + readRawVarint32;
            } else if (readRawVarint32 == 0) {
                return "";
            } else {
                if (readRawVarint32 >= 0) {
                    i = 0;
                    if (readRawVarint32 <= i2) {
                        refillBuffer(readRawVarint32);
                        bArr = this.buffer;
                        this.pos = readRawVarint32;
                    } else {
                        bArr = readRawBytesSlowPath(readRawVarint32, false);
                    }
                } else {
                    throw InvalidProtocolBufferException.negativeSize();
                }
            }
            return Utf8.decodeUtf8(bArr, i, readRawVarint32);
        }

        public ByteString readBytes() {
            int readRawVarint32 = readRawVarint32();
            int i = this.bufferSize;
            int i2 = this.pos;
            if (readRawVarint32 <= i - i2 && readRawVarint32 > 0) {
                ByteString copyFrom = ByteString.copyFrom(this.buffer, i2, readRawVarint32);
                this.pos += readRawVarint32;
                return copyFrom;
            } else if (readRawVarint32 == 0) {
                return ByteString.EMPTY;
            } else {
                if (readRawVarint32 >= 0) {
                    return readBytesSlowPath(readRawVarint32);
                }
                throw InvalidProtocolBufferException.negativeSize();
            }
        }

        public int readUInt32() {
            return readRawVarint32();
        }

        public int readEnum() {
            return readRawVarint32();
        }

        public int readSFixed32() {
            return readRawLittleEndian32();
        }

        public long readSFixed64() {
            return readRawLittleEndian64();
        }

        public int readSInt32() {
            return CodedInputStream.decodeZigZag32(readRawVarint32());
        }

        public long readSInt64() {
            return CodedInputStream.decodeZigZag64(readRawVarint64());
        }

        public int readRawVarint32() {
            byte b;
            byte b2;
            int i = this.pos;
            int i2 = this.bufferSize;
            if (i2 != i) {
                byte[] bArr = this.buffer;
                int i3 = i + 1;
                byte b3 = bArr[i];
                if (b3 >= 0) {
                    this.pos = i3;
                    return b3;
                } else if (i2 - i3 >= 9) {
                    int i4 = i + 2;
                    byte b4 = (bArr[i3] << 7) ^ b3;
                    if (b4 < 0) {
                        b = b4 ^ Byte.MIN_VALUE;
                    } else {
                        int i5 = i + 3;
                        byte b5 = (bArr[i4] << 14) ^ b4;
                        if (b5 >= 0) {
                            b2 = b5 ^ 16256;
                        } else {
                            int i6 = i + 4;
                            byte b6 = b5 ^ (bArr[i5] << 21);
                            if (b6 < 0) {
                                b = -2080896 ^ b6;
                            } else {
                                i5 = i + 5;
                                byte b7 = bArr[i6];
                                byte b8 = (b6 ^ (b7 << 28)) ^ 266354560;
                                if (b7 < 0) {
                                    i6 = i + 6;
                                    if (bArr[i5] < 0) {
                                        i5 = i + 7;
                                        if (bArr[i6] < 0) {
                                            i6 = i + 8;
                                            if (bArr[i5] < 0) {
                                                i5 = i + 9;
                                                if (bArr[i6] < 0) {
                                                    int i7 = i + 10;
                                                    if (bArr[i5] >= 0) {
                                                        byte b9 = b8;
                                                        i4 = i7;
                                                        b = b9;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    b = b8;
                                }
                                b2 = b8;
                            }
                            i4 = i6;
                        }
                        i4 = i5;
                    }
                    this.pos = i4;
                    return b;
                }
            }
            return (int) readRawVarint64SlowPath();
        }

        private void skipRawVarint() {
            if (this.bufferSize - this.pos >= 10) {
                skipRawVarintFastPath();
            } else {
                skipRawVarintSlowPath();
            }
        }

        private void skipRawVarintFastPath() {
            int i = 0;
            while (i < 10) {
                byte[] bArr = this.buffer;
                int i2 = this.pos;
                this.pos = i2 + 1;
                if (bArr[i2] < 0) {
                    i++;
                } else {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private void skipRawVarintSlowPath() {
            int i = 0;
            while (i < 10) {
                if (readRawByte() < 0) {
                    i++;
                } else {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public long readRawVarint64() {
            long j;
            long j2;
            long j3;
            int i = this.pos;
            int i2 = this.bufferSize;
            if (i2 != i) {
                byte[] bArr = this.buffer;
                int i3 = i + 1;
                byte b = bArr[i];
                if (b >= 0) {
                    this.pos = i3;
                    return (long) b;
                } else if (i2 - i3 >= 9) {
                    int i4 = i + 2;
                    byte b2 = (bArr[i3] << 7) ^ b;
                    if (b2 < 0) {
                        j = (long) (b2 ^ Byte.MIN_VALUE);
                    } else {
                        int i5 = i + 3;
                        byte b3 = (bArr[i4] << 14) ^ b2;
                        if (b3 >= 0) {
                            j = (long) (b3 ^ 16256);
                            i4 = i5;
                        } else {
                            int i6 = i + 4;
                            byte b4 = b3 ^ (bArr[i5] << 21);
                            if (b4 < 0) {
                                i4 = i6;
                                j = (long) (-2080896 ^ b4);
                            } else {
                                long j4 = (long) b4;
                                int i7 = i + 5;
                                long j5 = j4 ^ (((long) bArr[i6]) << 28);
                                if (j5 >= 0) {
                                    j3 = 266354560;
                                } else {
                                    int i8 = i + 6;
                                    long j6 = j5 ^ (((long) bArr[i7]) << 35);
                                    if (j6 < 0) {
                                        j2 = -34093383808L;
                                    } else {
                                        i7 = i + 7;
                                        j5 = j6 ^ (((long) bArr[i8]) << 42);
                                        if (j5 >= 0) {
                                            j3 = 4363953127296L;
                                        } else {
                                            i8 = i + 8;
                                            j6 = j5 ^ (((long) bArr[i7]) << 49);
                                            if (j6 < 0) {
                                                j2 = -558586000294016L;
                                            } else {
                                                i4 = i + 9;
                                                long j7 = (j6 ^ (((long) bArr[i8]) << 56)) ^ 71499008037633920L;
                                                if (j7 < 0) {
                                                    int i9 = i + 10;
                                                    if (((long) bArr[i4]) >= 0) {
                                                        i4 = i9;
                                                    }
                                                }
                                                j = j7;
                                            }
                                        }
                                    }
                                    j = j6 ^ j2;
                                    i4 = i8;
                                }
                                j = j5 ^ j3;
                            }
                        }
                    }
                    this.pos = i4;
                    return j;
                }
            }
            return readRawVarint64SlowPath();
        }

        /* access modifiers changed from: package-private */
        public long readRawVarint64SlowPath() {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                byte readRawByte = readRawByte();
                j |= ((long) (readRawByte & Byte.MAX_VALUE)) << i;
                if ((readRawByte & 128) == 0) {
                    return j;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public int readRawLittleEndian32() {
            int i = this.pos;
            if (this.bufferSize - i < 4) {
                refillBuffer(4);
                i = this.pos;
            }
            byte[] bArr = this.buffer;
            this.pos = i + 4;
            return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
        }

        public long readRawLittleEndian64() {
            int i = this.pos;
            if (this.bufferSize - i < 8) {
                refillBuffer(8);
                i = this.pos;
            }
            byte[] bArr = this.buffer;
            this.pos = i + 8;
            return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
        }

        public int pushLimit(int i) {
            if (i >= 0) {
                int i2 = i + this.totalBytesRetired + this.pos;
                int i3 = this.currentLimit;
                if (i2 <= i3) {
                    this.currentLimit = i2;
                    recomputeBufferSizeAfterLimit();
                    return i3;
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            throw InvalidProtocolBufferException.negativeSize();
        }

        private void recomputeBufferSizeAfterLimit() {
            int i = this.bufferSize + this.bufferSizeAfterLimit;
            this.bufferSize = i;
            int i2 = this.totalBytesRetired + i;
            int i3 = this.currentLimit;
            if (i2 > i3) {
                int i4 = i2 - i3;
                this.bufferSizeAfterLimit = i4;
                this.bufferSize = i - i4;
                return;
            }
            this.bufferSizeAfterLimit = 0;
        }

        public void popLimit(int i) {
            this.currentLimit = i;
            recomputeBufferSizeAfterLimit();
        }

        public boolean isAtEnd() {
            return this.pos == this.bufferSize && !tryRefillBuffer(1);
        }

        public int getTotalBytesRead() {
            return this.totalBytesRetired + this.pos;
        }

        private void refillBuffer(int i) {
            if (tryRefillBuffer(i)) {
                return;
            }
            if (i > (this.sizeLimit - this.totalBytesRetired) - this.pos) {
                throw InvalidProtocolBufferException.sizeLimitExceeded();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        private boolean tryRefillBuffer(int i) {
            int i2 = this.pos;
            int i3 = i2 + i;
            int i4 = this.bufferSize;
            if (i3 > i4) {
                int i5 = this.sizeLimit;
                int i6 = this.totalBytesRetired;
                if (i > (i5 - i6) - i2 || i6 + i2 + i > this.currentLimit) {
                    return false;
                }
                if (i2 > 0) {
                    if (i4 > i2) {
                        byte[] bArr = this.buffer;
                        System.arraycopy(bArr, i2, bArr, 0, i4 - i2);
                    }
                    this.totalBytesRetired += i2;
                    this.bufferSize -= i2;
                    this.pos = 0;
                }
                InputStream inputStream = this.input;
                byte[] bArr2 = this.buffer;
                int i7 = this.bufferSize;
                int read = read(inputStream, bArr2, i7, Math.min(bArr2.length - i7, (this.sizeLimit - this.totalBytesRetired) - i7));
                if (read == 0 || read < -1 || read > this.buffer.length) {
                    throw new IllegalStateException(this.input.getClass() + "#read(byte[]) returned invalid result: " + read + "\nThe InputStream implementation is buggy.");
                } else if (read <= 0) {
                    return false;
                } else {
                    this.bufferSize += read;
                    recomputeBufferSizeAfterLimit();
                    if (this.bufferSize >= i) {
                        return true;
                    }
                    return tryRefillBuffer(i);
                }
            } else {
                throw new IllegalStateException("refillBuffer() called when " + i + " bytes were already available in buffer");
            }
        }

        public byte readRawByte() {
            if (this.pos == this.bufferSize) {
                refillBuffer(1);
            }
            byte[] bArr = this.buffer;
            int i = this.pos;
            this.pos = i + 1;
            return bArr[i];
        }

        private byte[] readRawBytesSlowPath(int i, boolean z) {
            byte[] readRawBytesSlowPathOneChunk = readRawBytesSlowPathOneChunk(i);
            if (readRawBytesSlowPathOneChunk != null) {
                return z ? (byte[]) readRawBytesSlowPathOneChunk.clone() : readRawBytesSlowPathOneChunk;
            }
            int i2 = this.pos;
            int i3 = this.bufferSize;
            int i4 = i3 - i2;
            this.totalBytesRetired += i3;
            this.pos = 0;
            this.bufferSize = 0;
            List<byte[]> readRawBytesSlowPathRemainingChunks = readRawBytesSlowPathRemainingChunks(i - i4);
            byte[] bArr = new byte[i];
            System.arraycopy(this.buffer, i2, bArr, 0, i4);
            for (byte[] bArr2 : readRawBytesSlowPathRemainingChunks) {
                System.arraycopy(bArr2, 0, bArr, i4, bArr2.length);
                i4 += bArr2.length;
            }
            return bArr;
        }

        private byte[] readRawBytesSlowPathOneChunk(int i) {
            if (i == 0) {
                return Internal.EMPTY_BYTE_ARRAY;
            }
            if (i >= 0) {
                int i2 = this.totalBytesRetired;
                int i3 = this.pos;
                int i4 = i2 + i3 + i;
                if (i4 - this.sizeLimit <= 0) {
                    int i5 = this.currentLimit;
                    if (i4 <= i5) {
                        int i6 = this.bufferSize - i3;
                        int i7 = i - i6;
                        if (i7 >= 4096 && i7 > available(this.input)) {
                            return null;
                        }
                        byte[] bArr = new byte[i];
                        System.arraycopy(this.buffer, this.pos, bArr, 0, i6);
                        this.totalBytesRetired += this.bufferSize;
                        this.pos = 0;
                        this.bufferSize = 0;
                        while (i6 < i) {
                            int read = read(this.input, bArr, i6, i - i6);
                            if (read != -1) {
                                this.totalBytesRetired += read;
                                i6 += read;
                            } else {
                                throw InvalidProtocolBufferException.truncatedMessage();
                            }
                        }
                        return bArr;
                    }
                    skipRawBytes((i5 - i2) - i3);
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
                throw InvalidProtocolBufferException.sizeLimitExceeded();
            }
            throw InvalidProtocolBufferException.negativeSize();
        }

        private List readRawBytesSlowPathRemainingChunks(int i) {
            ArrayList arrayList = new ArrayList();
            while (i > 0) {
                int min = Math.min(i, b.v);
                byte[] bArr = new byte[min];
                int i2 = 0;
                while (i2 < min) {
                    int read = this.input.read(bArr, i2, min - i2);
                    if (read != -1) {
                        this.totalBytesRetired += read;
                        i2 += read;
                    } else {
                        throw InvalidProtocolBufferException.truncatedMessage();
                    }
                }
                i -= min;
                arrayList.add(bArr);
            }
            return arrayList;
        }

        private ByteString readBytesSlowPath(int i) {
            byte[] readRawBytesSlowPathOneChunk = readRawBytesSlowPathOneChunk(i);
            if (readRawBytesSlowPathOneChunk != null) {
                return ByteString.copyFrom(readRawBytesSlowPathOneChunk);
            }
            int i2 = this.pos;
            int i3 = this.bufferSize;
            int i4 = i3 - i2;
            this.totalBytesRetired += i3;
            this.pos = 0;
            this.bufferSize = 0;
            List<byte[]> readRawBytesSlowPathRemainingChunks = readRawBytesSlowPathRemainingChunks(i - i4);
            byte[] bArr = new byte[i];
            System.arraycopy(this.buffer, i2, bArr, 0, i4);
            for (byte[] bArr2 : readRawBytesSlowPathRemainingChunks) {
                System.arraycopy(bArr2, 0, bArr, i4, bArr2.length);
                i4 += bArr2.length;
            }
            return ByteString.wrap(bArr);
        }

        public void skipRawBytes(int i) {
            int i2 = this.bufferSize;
            int i3 = this.pos;
            if (i > i2 - i3 || i < 0) {
                skipRawBytesSlowPath(i);
            } else {
                this.pos = i3 + i;
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0057, code lost:
            throw new java.lang.IllegalStateException(r7.input.getClass() + "#skip returned invalid result: " + r1 + "\nThe InputStream implementation is buggy.");
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void skipRawBytesSlowPath(int r8) {
            /*
                r7 = this;
                if (r8 < 0) goto L_0x0092
                int r0 = r7.totalBytesRetired
                int r1 = r7.pos
                int r2 = r0 + r1
                int r2 = r2 + r8
                int r3 = r7.currentLimit
                if (r2 > r3) goto L_0x0088
                int r0 = r0 + r1
                r7.totalBytesRetired = r0
                int r0 = r7.bufferSize
                int r0 = r0 - r1
                r1 = 0
                r7.bufferSize = r1
                r7.pos = r1
            L_0x0018:
                if (r0 >= r8) goto L_0x0062
                int r1 = r8 - r0
                java.io.InputStream r2 = r7.input     // Catch:{ all -> 0x0058 }
                long r3 = (long) r1     // Catch:{ all -> 0x0058 }
                long r1 = skip(r2, r3)     // Catch:{ all -> 0x0058 }
                r5 = 0
                int r5 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
                if (r5 < 0) goto L_0x0033
                int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
                if (r3 > 0) goto L_0x0033
                if (r5 != 0) goto L_0x0030
                goto L_0x0062
            L_0x0030:
                int r1 = (int) r1     // Catch:{ all -> 0x0058 }
                int r0 = r0 + r1
                goto L_0x0018
            L_0x0033:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0058 }
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0058 }
                r3.<init>()     // Catch:{ all -> 0x0058 }
                java.io.InputStream r4 = r7.input     // Catch:{ all -> 0x0058 }
                java.lang.Class r4 = r4.getClass()     // Catch:{ all -> 0x0058 }
                r3.append(r4)     // Catch:{ all -> 0x0058 }
                java.lang.String r4 = "#skip returned invalid result: "
                r3.append(r4)     // Catch:{ all -> 0x0058 }
                r3.append(r1)     // Catch:{ all -> 0x0058 }
                java.lang.String r1 = "\nThe InputStream implementation is buggy."
                r3.append(r1)     // Catch:{ all -> 0x0058 }
                java.lang.String r1 = r3.toString()     // Catch:{ all -> 0x0058 }
                r8.<init>(r1)     // Catch:{ all -> 0x0058 }
                throw r8     // Catch:{ all -> 0x0058 }
            L_0x0058:
                r8 = move-exception
                int r1 = r7.totalBytesRetired
                int r1 = r1 + r0
                r7.totalBytesRetired = r1
                r7.recomputeBufferSizeAfterLimit()
                throw r8
            L_0x0062:
                int r1 = r7.totalBytesRetired
                int r1 = r1 + r0
                r7.totalBytesRetired = r1
                r7.recomputeBufferSizeAfterLimit()
                if (r0 >= r8) goto L_0x0087
                int r0 = r7.bufferSize
                int r1 = r7.pos
                int r1 = r0 - r1
                r7.pos = r0
                r0 = 1
                r7.refillBuffer(r0)
            L_0x0078:
                int r2 = r8 - r1
                int r3 = r7.bufferSize
                if (r2 <= r3) goto L_0x0085
                int r1 = r1 + r3
                r7.pos = r3
                r7.refillBuffer(r0)
                goto L_0x0078
            L_0x0085:
                r7.pos = r2
            L_0x0087:
                return
            L_0x0088:
                int r3 = r3 - r0
                int r3 = r3 - r1
                r7.skipRawBytes(r3)
                com.google.protobuf.InvalidProtocolBufferException r8 = com.google.protobuf.InvalidProtocolBufferException.truncatedMessage()
                throw r8
            L_0x0092:
                com.google.protobuf.InvalidProtocolBufferException r8 = com.google.protobuf.InvalidProtocolBufferException.negativeSize()
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.StreamDecoder.skipRawBytesSlowPath(int):void");
        }
    }
}
