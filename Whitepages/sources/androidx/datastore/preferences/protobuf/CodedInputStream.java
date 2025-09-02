package androidx.datastore.preferences.protobuf;

import com.salesforce.marketingcloud.b;
import java.io.InputStream;
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
        return newInstance(inputStream, b.v);
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
                if (i2 >= 0) {
                    int i3 = this.currentLimit;
                    if (i2 <= i3) {
                        this.currentLimit = i2;
                        recomputeBufferSizeAfterLimit();
                        return i3;
                    }
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
                throw InvalidProtocolBufferException.parseFailure();
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
                androidx.datastore.preferences.protobuf.InvalidProtocolBufferException r8 = androidx.datastore.preferences.protobuf.InvalidProtocolBufferException.truncatedMessage()
                throw r8
            L_0x0092:
                androidx.datastore.preferences.protobuf.InvalidProtocolBufferException r8 = androidx.datastore.preferences.protobuf.InvalidProtocolBufferException.negativeSize()
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.CodedInputStream.StreamDecoder.skipRawBytesSlowPath(int):void");
        }
    }
}
