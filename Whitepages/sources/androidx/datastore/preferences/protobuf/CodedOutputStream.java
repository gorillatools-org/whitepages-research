package androidx.datastore.preferences.protobuf;

import androidx.datastore.preferences.protobuf.Utf8;
import com.salesforce.marketingcloud.b;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class CodedOutputStream extends ByteOutput {
    /* access modifiers changed from: private */
    public static final boolean HAS_UNSAFE_ARRAY_OPERATIONS = UnsafeUtil.hasUnsafeArrayOperations();
    private static final Logger logger = Logger.getLogger(CodedOutputStream.class.getName());
    private boolean serializationDeterministic;
    CodedOutputStreamWriter wrapper;

    public static int computeBoolSizeNoTag(boolean z) {
        return 1;
    }

    public static int computeDoubleSizeNoTag(double d) {
        return 8;
    }

    public static int computeFixed32SizeNoTag(int i) {
        return 4;
    }

    public static int computeFixed64SizeNoTag(long j) {
        return 8;
    }

    public static int computeFloatSizeNoTag(float f) {
        return 4;
    }

    static int computePreferredBufferSize(int i) {
        return i > 4096 ? b.v : i;
    }

    public static int computeSFixed32SizeNoTag(int i) {
        return 4;
    }

    public static int computeSFixed64SizeNoTag(long j) {
        return 8;
    }

    public static int encodeZigZag32(int i) {
        return (i >> 31) ^ (i << 1);
    }

    public static long encodeZigZag64(long j) {
        return (j >> 63) ^ (j << 1);
    }

    public abstract void flush();

    public abstract void write(byte b);

    public abstract void writeBool(int i, boolean z);

    /* access modifiers changed from: package-private */
    public abstract void writeByteArrayNoTag(byte[] bArr, int i, int i2);

    public abstract void writeBytes(int i, ByteString byteString);

    public abstract void writeBytesNoTag(ByteString byteString);

    public abstract void writeFixed32(int i, int i2);

    public abstract void writeFixed32NoTag(int i);

    public abstract void writeFixed64(int i, long j);

    public abstract void writeFixed64NoTag(long j);

    public abstract void writeInt32(int i, int i2);

    public abstract void writeInt32NoTag(int i);

    public abstract void writeLazy(byte[] bArr, int i, int i2);

    /* access modifiers changed from: package-private */
    public abstract void writeMessage(int i, MessageLite messageLite, Schema schema);

    public abstract void writeMessageNoTag(MessageLite messageLite);

    public abstract void writeMessageSetExtension(int i, MessageLite messageLite);

    public abstract void writeRawMessageSetExtension(int i, ByteString byteString);

    public abstract void writeString(int i, String str);

    public abstract void writeStringNoTag(String str);

    public abstract void writeTag(int i, int i2);

    public abstract void writeUInt32(int i, int i2);

    public abstract void writeUInt32NoTag(int i);

    public abstract void writeUInt64(int i, long j);

    public abstract void writeUInt64NoTag(long j);

    public static CodedOutputStream newInstance(OutputStream outputStream, int i) {
        return new OutputStreamEncoder(outputStream, i);
    }

    /* access modifiers changed from: package-private */
    public boolean isSerializationDeterministic() {
        return this.serializationDeterministic;
    }

    private CodedOutputStream() {
    }

    public final void writeSInt32(int i, int i2) {
        writeUInt32(i, encodeZigZag32(i2));
    }

    public final void writeSFixed32(int i, int i2) {
        writeFixed32(i, i2);
    }

    public final void writeInt64(int i, long j) {
        writeUInt64(i, j);
    }

    public final void writeSInt64(int i, long j) {
        writeUInt64(i, encodeZigZag64(j));
    }

    public final void writeSFixed64(int i, long j) {
        writeFixed64(i, j);
    }

    public final void writeFloat(int i, float f) {
        writeFixed32(i, Float.floatToRawIntBits(f));
    }

    public final void writeDouble(int i, double d) {
        writeFixed64(i, Double.doubleToRawLongBits(d));
    }

    public final void writeEnum(int i, int i2) {
        writeInt32(i, i2);
    }

    public final void writeSInt32NoTag(int i) {
        writeUInt32NoTag(encodeZigZag32(i));
    }

    public final void writeSFixed32NoTag(int i) {
        writeFixed32NoTag(i);
    }

    public final void writeInt64NoTag(long j) {
        writeUInt64NoTag(j);
    }

    public final void writeSInt64NoTag(long j) {
        writeUInt64NoTag(encodeZigZag64(j));
    }

    public final void writeSFixed64NoTag(long j) {
        writeFixed64NoTag(j);
    }

    public final void writeFloatNoTag(float f) {
        writeFixed32NoTag(Float.floatToRawIntBits(f));
    }

    public final void writeDoubleNoTag(double d) {
        writeFixed64NoTag(Double.doubleToRawLongBits(d));
    }

    public final void writeBoolNoTag(boolean z) {
        write(z ? (byte) 1 : 0);
    }

    public final void writeEnumNoTag(int i) {
        writeInt32NoTag(i);
    }

    public final void writeByteArrayNoTag(byte[] bArr) {
        writeByteArrayNoTag(bArr, 0, bArr.length);
    }

    public static int computeInt32Size(int i, int i2) {
        return computeTagSize(i) + computeInt32SizeNoTag(i2);
    }

    public static int computeUInt32Size(int i, int i2) {
        return computeTagSize(i) + computeUInt32SizeNoTag(i2);
    }

    public static int computeSInt32Size(int i, int i2) {
        return computeTagSize(i) + computeSInt32SizeNoTag(i2);
    }

    public static int computeFixed32Size(int i, int i2) {
        return computeTagSize(i) + computeFixed32SizeNoTag(i2);
    }

    public static int computeSFixed32Size(int i, int i2) {
        return computeTagSize(i) + computeSFixed32SizeNoTag(i2);
    }

    public static int computeInt64Size(int i, long j) {
        return computeTagSize(i) + computeInt64SizeNoTag(j);
    }

    public static int computeUInt64Size(int i, long j) {
        return computeTagSize(i) + computeUInt64SizeNoTag(j);
    }

    public static int computeSInt64Size(int i, long j) {
        return computeTagSize(i) + computeSInt64SizeNoTag(j);
    }

    public static int computeFixed64Size(int i, long j) {
        return computeTagSize(i) + computeFixed64SizeNoTag(j);
    }

    public static int computeSFixed64Size(int i, long j) {
        return computeTagSize(i) + computeSFixed64SizeNoTag(j);
    }

    public static int computeFloatSize(int i, float f) {
        return computeTagSize(i) + computeFloatSizeNoTag(f);
    }

    public static int computeDoubleSize(int i, double d) {
        return computeTagSize(i) + computeDoubleSizeNoTag(d);
    }

    public static int computeBoolSize(int i, boolean z) {
        return computeTagSize(i) + computeBoolSizeNoTag(z);
    }

    public static int computeEnumSize(int i, int i2) {
        return computeTagSize(i) + computeEnumSizeNoTag(i2);
    }

    public static int computeStringSize(int i, String str) {
        return computeTagSize(i) + computeStringSizeNoTag(str);
    }

    public static int computeBytesSize(int i, ByteString byteString) {
        return computeTagSize(i) + computeBytesSizeNoTag(byteString);
    }

    static int computeMessageSize(int i, MessageLite messageLite, Schema schema) {
        return computeTagSize(i) + computeMessageSizeNoTag(messageLite, schema);
    }

    public static int computeRawMessageSetExtensionSize(int i, ByteString byteString) {
        return (computeTagSize(1) * 2) + computeUInt32Size(2, i) + computeBytesSize(3, byteString);
    }

    public static int computeTagSize(int i) {
        return computeUInt32SizeNoTag(WireFormat.makeTag(i, 0));
    }

    public static int computeInt32SizeNoTag(int i) {
        return computeUInt64SizeNoTag((long) i);
    }

    public static int computeUInt32SizeNoTag(int i) {
        return (352 - (Integer.numberOfLeadingZeros(i) * 9)) >>> 6;
    }

    public static int computeSInt32SizeNoTag(int i) {
        return computeUInt32SizeNoTag(encodeZigZag32(i));
    }

    public static int computeInt64SizeNoTag(long j) {
        return computeUInt64SizeNoTag(j);
    }

    public static int computeUInt64SizeNoTag(long j) {
        return (640 - (Long.numberOfLeadingZeros(j) * 9)) >>> 6;
    }

    public static int computeSInt64SizeNoTag(long j) {
        return computeUInt64SizeNoTag(encodeZigZag64(j));
    }

    public static int computeEnumSizeNoTag(int i) {
        return computeInt32SizeNoTag(i);
    }

    public static int computeStringSizeNoTag(String str) {
        int i;
        try {
            i = Utf8.encodedLength(str);
        } catch (Utf8.UnpairedSurrogateException unused) {
            i = str.getBytes(Internal.UTF_8).length;
        }
        return computeLengthDelimitedFieldSize(i);
    }

    public static int computeBytesSizeNoTag(ByteString byteString) {
        return computeLengthDelimitedFieldSize(byteString.size());
    }

    public static int computeByteArraySizeNoTag(byte[] bArr) {
        return computeLengthDelimitedFieldSize(bArr.length);
    }

    public static int computeMessageSizeNoTag(MessageLite messageLite) {
        return computeLengthDelimitedFieldSize(messageLite.getSerializedSize());
    }

    static int computeMessageSizeNoTag(MessageLite messageLite, Schema schema) {
        return computeLengthDelimitedFieldSize(((AbstractMessageLite) messageLite).getSerializedSize(schema));
    }

    static int computeLengthDelimitedFieldSize(int i) {
        return computeUInt32SizeNoTag(i) + i;
    }

    public static class OutOfSpaceException extends IOException {
        private static final long serialVersionUID = -6947486886997889499L;

        OutOfSpaceException(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }
    }

    /* access modifiers changed from: package-private */
    public final void inefficientWriteStringNoTag(String str, Utf8.UnpairedSurrogateException unpairedSurrogateException) {
        logger.log(Level.WARNING, "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", unpairedSurrogateException);
        byte[] bytes = str.getBytes(Internal.UTF_8);
        try {
            writeUInt32NoTag(bytes.length);
            writeLazy(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfSpaceException(e);
        }
    }

    public final void writeGroup(int i, MessageLite messageLite) {
        writeTag(i, 3);
        writeGroupNoTag(messageLite);
        writeTag(i, 4);
    }

    /* access modifiers changed from: package-private */
    public final void writeGroup(int i, MessageLite messageLite, Schema schema) {
        writeTag(i, 3);
        writeGroupNoTag(messageLite, schema);
        writeTag(i, 4);
    }

    public final void writeGroupNoTag(MessageLite messageLite) {
        messageLite.writeTo(this);
    }

    /* access modifiers changed from: package-private */
    public final void writeGroupNoTag(MessageLite messageLite, Schema schema) {
        schema.writeTo(messageLite, this.wrapper);
    }

    static int computeGroupSize(int i, MessageLite messageLite, Schema schema) {
        return (computeTagSize(i) * 2) + computeGroupSizeNoTag(messageLite, schema);
    }

    public static int computeGroupSizeNoTag(MessageLite messageLite) {
        return messageLite.getSerializedSize();
    }

    static int computeGroupSizeNoTag(MessageLite messageLite, Schema schema) {
        return ((AbstractMessageLite) messageLite).getSerializedSize(schema);
    }

    private static abstract class AbstractBufferedEncoder extends CodedOutputStream {
        final byte[] buffer;
        final int limit;
        int position;
        int totalBytesWritten;

        AbstractBufferedEncoder(int i) {
            super();
            if (i >= 0) {
                byte[] bArr = new byte[Math.max(i, 20)];
                this.buffer = bArr;
                this.limit = bArr.length;
                return;
            }
            throw new IllegalArgumentException("bufferSize must be >= 0");
        }

        /* access modifiers changed from: package-private */
        public final void buffer(byte b) {
            byte[] bArr = this.buffer;
            int i = this.position;
            this.position = i + 1;
            bArr[i] = b;
            this.totalBytesWritten++;
        }

        /* access modifiers changed from: package-private */
        public final void bufferTag(int i, int i2) {
            bufferUInt32NoTag(WireFormat.makeTag(i, i2));
        }

        /* access modifiers changed from: package-private */
        public final void bufferInt32NoTag(int i) {
            if (i >= 0) {
                bufferUInt32NoTag(i);
            } else {
                bufferUInt64NoTag((long) i);
            }
        }

        /* access modifiers changed from: package-private */
        public final void bufferUInt32NoTag(int i) {
            if (CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS) {
                long j = (long) this.position;
                while ((i & -128) != 0) {
                    byte[] bArr = this.buffer;
                    int i2 = this.position;
                    this.position = i2 + 1;
                    UnsafeUtil.putByte(bArr, (long) i2, (byte) ((i | 128) & 255));
                    i >>>= 7;
                }
                byte[] bArr2 = this.buffer;
                int i3 = this.position;
                this.position = i3 + 1;
                UnsafeUtil.putByte(bArr2, (long) i3, (byte) i);
                this.totalBytesWritten += (int) (((long) this.position) - j);
                return;
            }
            while ((i & -128) != 0) {
                byte[] bArr3 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                bArr3[i4] = (byte) ((i | 128) & 255);
                this.totalBytesWritten++;
                i >>>= 7;
            }
            byte[] bArr4 = this.buffer;
            int i5 = this.position;
            this.position = i5 + 1;
            bArr4[i5] = (byte) i;
            this.totalBytesWritten++;
        }

        /* access modifiers changed from: package-private */
        public final void bufferUInt64NoTag(long j) {
            if (CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS) {
                long j2 = (long) this.position;
                while ((j & -128) != 0) {
                    byte[] bArr = this.buffer;
                    int i = this.position;
                    this.position = i + 1;
                    UnsafeUtil.putByte(bArr, (long) i, (byte) ((((int) j) | 128) & 255));
                    j >>>= 7;
                }
                byte[] bArr2 = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                UnsafeUtil.putByte(bArr2, (long) i2, (byte) ((int) j));
                this.totalBytesWritten += (int) (((long) this.position) - j2);
                return;
            }
            while ((j & -128) != 0) {
                byte[] bArr3 = this.buffer;
                int i3 = this.position;
                this.position = i3 + 1;
                bArr3[i3] = (byte) ((((int) j) | 128) & 255);
                this.totalBytesWritten++;
                j >>>= 7;
            }
            byte[] bArr4 = this.buffer;
            int i4 = this.position;
            this.position = i4 + 1;
            bArr4[i4] = (byte) ((int) j);
            this.totalBytesWritten++;
        }

        /* access modifiers changed from: package-private */
        public final void bufferFixed32NoTag(int i) {
            byte[] bArr = this.buffer;
            int i2 = this.position;
            int i3 = i2 + 1;
            this.position = i3;
            bArr[i2] = (byte) (i & 255);
            int i4 = i2 + 2;
            this.position = i4;
            bArr[i3] = (byte) ((i >> 8) & 255);
            int i5 = i2 + 3;
            this.position = i5;
            bArr[i4] = (byte) ((i >> 16) & 255);
            this.position = i2 + 4;
            bArr[i5] = (byte) ((i >> 24) & 255);
            this.totalBytesWritten += 4;
        }

        /* access modifiers changed from: package-private */
        public final void bufferFixed64NoTag(long j) {
            byte[] bArr = this.buffer;
            int i = this.position;
            int i2 = i + 1;
            this.position = i2;
            bArr[i] = (byte) ((int) (j & 255));
            int i3 = i + 2;
            this.position = i3;
            bArr[i2] = (byte) ((int) ((j >> 8) & 255));
            int i4 = i + 3;
            this.position = i4;
            bArr[i3] = (byte) ((int) ((j >> 16) & 255));
            int i5 = i + 4;
            this.position = i5;
            bArr[i4] = (byte) ((int) (255 & (j >> 24)));
            int i6 = i + 5;
            this.position = i6;
            bArr[i5] = (byte) (((int) (j >> 32)) & 255);
            int i7 = i + 6;
            this.position = i7;
            bArr[i6] = (byte) (((int) (j >> 40)) & 255);
            int i8 = i + 7;
            this.position = i8;
            bArr[i7] = (byte) (((int) (j >> 48)) & 255);
            this.position = i + 8;
            bArr[i8] = (byte) (((int) (j >> 56)) & 255);
            this.totalBytesWritten += 8;
        }
    }

    private static final class OutputStreamEncoder extends AbstractBufferedEncoder {
        private final OutputStream out;

        OutputStreamEncoder(OutputStream outputStream, int i) {
            super(i);
            if (outputStream != null) {
                this.out = outputStream;
                return;
            }
            throw new NullPointerException("out");
        }

        public void writeTag(int i, int i2) {
            writeUInt32NoTag(WireFormat.makeTag(i, i2));
        }

        public void writeInt32(int i, int i2) {
            flushIfNotAvailable(20);
            bufferTag(i, 0);
            bufferInt32NoTag(i2);
        }

        public void writeUInt32(int i, int i2) {
            flushIfNotAvailable(20);
            bufferTag(i, 0);
            bufferUInt32NoTag(i2);
        }

        public void writeFixed32(int i, int i2) {
            flushIfNotAvailable(14);
            bufferTag(i, 5);
            bufferFixed32NoTag(i2);
        }

        public void writeUInt64(int i, long j) {
            flushIfNotAvailable(20);
            bufferTag(i, 0);
            bufferUInt64NoTag(j);
        }

        public void writeFixed64(int i, long j) {
            flushIfNotAvailable(18);
            bufferTag(i, 1);
            bufferFixed64NoTag(j);
        }

        public void writeBool(int i, boolean z) {
            flushIfNotAvailable(11);
            bufferTag(i, 0);
            buffer(z ? (byte) 1 : 0);
        }

        public void writeString(int i, String str) {
            writeTag(i, 2);
            writeStringNoTag(str);
        }

        public void writeBytes(int i, ByteString byteString) {
            writeTag(i, 2);
            writeBytesNoTag(byteString);
        }

        public void writeBytesNoTag(ByteString byteString) {
            writeUInt32NoTag(byteString.size());
            byteString.writeTo(this);
        }

        public void writeByteArrayNoTag(byte[] bArr, int i, int i2) {
            writeUInt32NoTag(i2);
            write(bArr, i, i2);
        }

        public void writeMessage(int i, MessageLite messageLite) {
            writeTag(i, 2);
            writeMessageNoTag(messageLite);
        }

        /* access modifiers changed from: package-private */
        public void writeMessage(int i, MessageLite messageLite, Schema schema) {
            writeTag(i, 2);
            writeMessageNoTag(messageLite, schema);
        }

        public void writeMessageSetExtension(int i, MessageLite messageLite) {
            writeTag(1, 3);
            writeUInt32(2, i);
            writeMessage(3, messageLite);
            writeTag(1, 4);
        }

        public void writeRawMessageSetExtension(int i, ByteString byteString) {
            writeTag(1, 3);
            writeUInt32(2, i);
            writeBytes(3, byteString);
            writeTag(1, 4);
        }

        public void writeMessageNoTag(MessageLite messageLite) {
            writeUInt32NoTag(messageLite.getSerializedSize());
            messageLite.writeTo(this);
        }

        /* access modifiers changed from: package-private */
        public void writeMessageNoTag(MessageLite messageLite, Schema schema) {
            writeUInt32NoTag(((AbstractMessageLite) messageLite).getSerializedSize(schema));
            schema.writeTo(messageLite, this.wrapper);
        }

        public void write(byte b) {
            if (this.position == this.limit) {
                doFlush();
            }
            buffer(b);
        }

        public void writeInt32NoTag(int i) {
            if (i >= 0) {
                writeUInt32NoTag(i);
            } else {
                writeUInt64NoTag((long) i);
            }
        }

        public void writeUInt32NoTag(int i) {
            flushIfNotAvailable(5);
            bufferUInt32NoTag(i);
        }

        public void writeFixed32NoTag(int i) {
            flushIfNotAvailable(4);
            bufferFixed32NoTag(i);
        }

        public void writeUInt64NoTag(long j) {
            flushIfNotAvailable(10);
            bufferUInt64NoTag(j);
        }

        public void writeFixed64NoTag(long j) {
            flushIfNotAvailable(8);
            bufferFixed64NoTag(j);
        }

        public void writeStringNoTag(String str) {
            int i;
            int i2;
            try {
                int length = str.length() * 3;
                int computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(length);
                int i3 = computeUInt32SizeNoTag + length;
                int i4 = this.limit;
                if (i3 > i4) {
                    byte[] bArr = new byte[length];
                    int encode = Utf8.encode(str, bArr, 0, length);
                    writeUInt32NoTag(encode);
                    writeLazy(bArr, 0, encode);
                    return;
                }
                if (i3 > i4 - this.position) {
                    doFlush();
                }
                int computeUInt32SizeNoTag2 = CodedOutputStream.computeUInt32SizeNoTag(str.length());
                i = this.position;
                if (computeUInt32SizeNoTag2 == computeUInt32SizeNoTag) {
                    int i5 = i + computeUInt32SizeNoTag2;
                    this.position = i5;
                    int encode2 = Utf8.encode(str, this.buffer, i5, this.limit - i5);
                    this.position = i;
                    i2 = (encode2 - i) - computeUInt32SizeNoTag2;
                    bufferUInt32NoTag(i2);
                    this.position = encode2;
                } else {
                    i2 = Utf8.encodedLength(str);
                    bufferUInt32NoTag(i2);
                    this.position = Utf8.encode(str, this.buffer, this.position, i2);
                }
                this.totalBytesWritten += i2;
            } catch (Utf8.UnpairedSurrogateException e) {
                this.totalBytesWritten -= this.position - i;
                this.position = i;
                throw e;
            } catch (ArrayIndexOutOfBoundsException e2) {
                throw new OutOfSpaceException(e2);
            } catch (Utf8.UnpairedSurrogateException e3) {
                inefficientWriteStringNoTag(str, e3);
            }
        }

        public void flush() {
            if (this.position > 0) {
                doFlush();
            }
        }

        public void write(byte[] bArr, int i, int i2) {
            int i3 = this.limit;
            int i4 = this.position;
            if (i3 - i4 >= i2) {
                System.arraycopy(bArr, i, this.buffer, i4, i2);
                this.position += i2;
                this.totalBytesWritten += i2;
                return;
            }
            int i5 = i3 - i4;
            System.arraycopy(bArr, i, this.buffer, i4, i5);
            int i6 = i + i5;
            int i7 = i2 - i5;
            this.position = this.limit;
            this.totalBytesWritten += i5;
            doFlush();
            if (i7 <= this.limit) {
                System.arraycopy(bArr, i6, this.buffer, 0, i7);
                this.position = i7;
            } else {
                this.out.write(bArr, i6, i7);
            }
            this.totalBytesWritten += i7;
        }

        public void writeLazy(byte[] bArr, int i, int i2) {
            write(bArr, i, i2);
        }

        private void flushIfNotAvailable(int i) {
            if (this.limit - this.position < i) {
                doFlush();
            }
        }

        private void doFlush() {
            this.out.write(this.buffer, 0, this.position);
            this.position = 0;
        }
    }
}
