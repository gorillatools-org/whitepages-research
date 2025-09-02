package okio;

import java.io.EOFException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import okio.internal.Buffer;

public final class RealBufferedSource implements BufferedSource {
    public final Buffer bufferField = new Buffer();
    public boolean closed;
    public final Source source;

    public long read(Buffer buffer, long j) {
        Intrinsics.checkNotNullParameter(buffer, "sink");
        if (j < 0) {
            throw new IllegalArgumentException(("byteCount < 0: " + j).toString());
        } else if (this.closed) {
            throw new IllegalStateException("closed");
        } else if (this.bufferField.size() == 0 && this.source.read(this.bufferField, 8192) == -1) {
            return -1;
        } else {
            return this.bufferField.read(buffer, Math.min(j, this.bufferField.size()));
        }
    }

    public boolean exhausted() {
        if (!this.closed) {
            return this.bufferField.exhausted() && this.source.read(this.bufferField, 8192) == -1;
        }
        throw new IllegalStateException("closed");
    }

    public RealBufferedSource(Source source2) {
        Intrinsics.checkNotNullParameter(source2, "source");
        this.source = source2;
    }

    public void require(long j) {
        if (!request(j)) {
            throw new EOFException();
        }
    }

    public boolean request(long j) {
        if (j < 0) {
            throw new IllegalArgumentException(("byteCount < 0: " + j).toString());
        } else if (!this.closed) {
            while (this.bufferField.size() < j) {
                if (this.source.read(this.bufferField, 8192) == -1) {
                    return false;
                }
            }
            return true;
        } else {
            throw new IllegalStateException("closed");
        }
    }

    public Buffer getBuffer() {
        return this.bufferField;
    }

    public int read(ByteBuffer byteBuffer) {
        Intrinsics.checkNotNullParameter(byteBuffer, "sink");
        if (this.bufferField.size() == 0 && this.source.read(this.bufferField, 8192) == -1) {
            return -1;
        }
        return this.bufferField.read(byteBuffer);
    }

    public byte[] readByteArray() {
        this.bufferField.writeAll(this.source);
        return this.bufferField.readByteArray();
    }

    public ByteString readByteString() {
        this.bufferField.writeAll(this.source);
        return this.bufferField.readByteString();
    }

    public String readString(Charset charset) {
        Intrinsics.checkNotNullParameter(charset, "charset");
        this.bufferField.writeAll(this.source);
        return this.bufferField.readString(charset);
    }

    public String readUtf8() {
        this.bufferField.writeAll(this.source);
        return this.bufferField.readUtf8();
    }

    public Buffer buffer() {
        return this.bufferField;
    }

    public byte readByte() {
        require(1);
        return this.bufferField.readByte();
    }

    public ByteString readByteString(long j) {
        require(j);
        return this.bufferField.readByteString(j);
    }

    public int select(Options options) {
        Intrinsics.checkNotNullParameter(options, "options");
        if (!this.closed) {
            while (true) {
                int selectPrefix = Buffer.selectPrefix(this.bufferField, options, true);
                if (selectPrefix == -2) {
                    if (this.source.read(this.bufferField, 8192) == -1) {
                        break;
                    }
                } else if (selectPrefix != -1) {
                    this.bufferField.skip((long) options.getByteStrings$okio()[selectPrefix].size());
                    return selectPrefix;
                }
            }
            return -1;
        }
        throw new IllegalStateException("closed");
    }

    public String readUtf8LineStrict() {
        return readUtf8LineStrict(Long.MAX_VALUE);
    }

    public byte[] readByteArray(long j) {
        require(j);
        return this.bufferField.readByteArray(j);
    }

    public long indexOf(byte b) {
        return indexOf(b, 0, Long.MAX_VALUE);
    }

    public void readFully(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "sink");
        try {
            require((long) bArr.length);
            this.bufferField.readFully(bArr);
        } catch (EOFException e) {
            int i = 0;
            while (this.bufferField.size() > 0) {
                Buffer buffer = this.bufferField;
                int read = buffer.read(bArr, i, (int) buffer.size());
                if (read != -1) {
                    i += read;
                } else {
                    throw new AssertionError();
                }
            }
            throw e;
        }
    }

    public long indexOf(ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "bytes");
        return indexOf(byteString, 0);
    }

    public long indexOfElement(ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "targetBytes");
        return indexOfElement(byteString, 0);
    }

    public boolean rangeEquals(long j, ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "bytes");
        return rangeEquals(j, byteString, 0, byteString.size());
    }

    public InputStream inputStream() {
        return new RealBufferedSource$inputStream$1(this);
    }

    public void readFully(Buffer buffer, long j) {
        Intrinsics.checkNotNullParameter(buffer, "sink");
        try {
            require(j);
            this.bufferField.readFully(buffer, j);
        } catch (EOFException e) {
            buffer.writeAll(this.bufferField);
            throw e;
        }
    }

    public long readAll(Sink sink) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        long j = 0;
        while (this.source.read(this.bufferField, 8192) != -1) {
            long completeSegmentByteCount = this.bufferField.completeSegmentByteCount();
            if (completeSegmentByteCount > 0) {
                j += completeSegmentByteCount;
                sink.write(this.bufferField, completeSegmentByteCount);
            }
        }
        if (this.bufferField.size() <= 0) {
            return j;
        }
        long size = j + this.bufferField.size();
        Buffer buffer = this.bufferField;
        sink.write(buffer, buffer.size());
        return size;
    }

    public boolean isOpen() {
        return !this.closed;
    }

    public String readUtf8(long j) {
        require(j);
        return this.bufferField.readUtf8(j);
    }

    public String readUtf8LineStrict(long j) {
        if (j >= 0) {
            long j2 = j == Long.MAX_VALUE ? Long.MAX_VALUE : j + 1;
            long indexOf = indexOf((byte) 10, 0, j2);
            if (indexOf != -1) {
                return Buffer.readUtf8Line(this.bufferField, indexOf);
            }
            if (j2 < Long.MAX_VALUE && request(j2) && this.bufferField.getByte(j2 - 1) == 13 && request(1 + j2) && this.bufferField.getByte(j2) == 10) {
                return Buffer.readUtf8Line(this.bufferField, j2);
            }
            Buffer buffer = new Buffer();
            Buffer buffer2 = this.bufferField;
            buffer2.copyTo(buffer, 0, Math.min((long) 32, buffer2.size()));
            throw new EOFException("\\n not found: limit=" + Math.min(this.bufferField.size(), j) + " content=" + buffer.readByteString().hex() + 8230);
        }
        throw new IllegalArgumentException(("limit < 0: " + j).toString());
    }

    public short readShort() {
        require(2);
        return this.bufferField.readShort();
    }

    public short readShortLe() {
        require(2);
        return this.bufferField.readShortLe();
    }

    public int readInt() {
        require(4);
        return this.bufferField.readInt();
    }

    public int readIntLe() {
        require(4);
        return this.bufferField.readIntLe();
    }

    public long readLong() {
        require(8);
        return this.bufferField.readLong();
    }

    public long readLongLe() {
        require(8);
        return this.bufferField.readLongLe();
    }

    public long readDecimalLong() {
        int i;
        require(1);
        long j = 0;
        while (true) {
            long j2 = j + 1;
            if (!request(j2)) {
                break;
            }
            byte b = this.bufferField.getByte(j);
            if ((b >= 48 && b <= 57) || (j == 0 && b == 45)) {
                j = j2;
            } else if (i == 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("Expected a digit or '-' but was 0x");
                String num = Integer.toString(b, CharsKt.checkRadix(CharsKt.checkRadix(16)));
                Intrinsics.checkNotNullExpressionValue(num, "toString(this, checkRadix(radix))");
                sb.append(num);
                throw new NumberFormatException(sb.toString());
            }
        }
        return this.bufferField.readDecimalLong();
    }

    public long readHexadecimalUnsignedLong() {
        require(1);
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (!request((long) i2)) {
                break;
            }
            byte b = this.bufferField.getByte((long) i);
            if ((b >= 48 && b <= 57) || ((b >= 97 && b <= 102) || (b >= 65 && b <= 70))) {
                i = i2;
            } else if (i == 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("Expected leading [0-9a-fA-F] character but was 0x");
                String num = Integer.toString(b, CharsKt.checkRadix(CharsKt.checkRadix(16)));
                Intrinsics.checkNotNullExpressionValue(num, "toString(this, checkRadix(radix))");
                sb.append(num);
                throw new NumberFormatException(sb.toString());
            }
        }
        return this.bufferField.readHexadecimalUnsignedLong();
    }

    public void skip(long j) {
        if (!this.closed) {
            while (j > 0) {
                if (this.bufferField.size() == 0 && this.source.read(this.bufferField, 8192) == -1) {
                    throw new EOFException();
                }
                long min = Math.min(j, this.bufferField.size());
                this.bufferField.skip(min);
                j -= min;
            }
            return;
        }
        throw new IllegalStateException("closed");
    }

    public long indexOf(byte b, long j, long j2) {
        if (this.closed) {
            throw new IllegalStateException("closed");
        } else if (0 > j || j > j2) {
            throw new IllegalArgumentException(("fromIndex=" + j + " toIndex=" + j2).toString());
        } else {
            while (j < j2) {
                long indexOf = this.bufferField.indexOf(b, j, j2);
                if (indexOf != -1) {
                    return indexOf;
                }
                long size = this.bufferField.size();
                if (size >= j2 || this.source.read(this.bufferField, 8192) == -1) {
                    return -1;
                }
                j = Math.max(j, size);
            }
            return -1;
        }
    }

    public long indexOf(ByteString byteString, long j) {
        Intrinsics.checkNotNullParameter(byteString, "bytes");
        if (!this.closed) {
            while (true) {
                long indexOf = this.bufferField.indexOf(byteString, j);
                if (indexOf != -1) {
                    return indexOf;
                }
                long size = this.bufferField.size();
                if (this.source.read(this.bufferField, 8192) == -1) {
                    return -1;
                }
                j = Math.max(j, (size - ((long) byteString.size())) + 1);
            }
        } else {
            throw new IllegalStateException("closed");
        }
    }

    public long indexOfElement(ByteString byteString, long j) {
        Intrinsics.checkNotNullParameter(byteString, "targetBytes");
        if (!this.closed) {
            while (true) {
                long indexOfElement = this.bufferField.indexOfElement(byteString, j);
                if (indexOfElement != -1) {
                    return indexOfElement;
                }
                long size = this.bufferField.size();
                if (this.source.read(this.bufferField, 8192) == -1) {
                    return -1;
                }
                j = Math.max(j, size);
            }
        } else {
            throw new IllegalStateException("closed");
        }
    }

    public boolean rangeEquals(long j, ByteString byteString, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteString, "bytes");
        if (this.closed) {
            throw new IllegalStateException("closed");
        } else if (j < 0 || i < 0 || i2 < 0 || byteString.size() - i < i2) {
            return false;
        } else {
            for (int i3 = 0; i3 < i2; i3++) {
                long j2 = ((long) i3) + j;
                if (!request(1 + j2) || this.bufferField.getByte(j2) != byteString.getByte(i + i3)) {
                    return false;
                }
            }
            return true;
        }
    }

    public BufferedSource peek() {
        return Okio.buffer((Source) new PeekSource(this));
    }

    public void close() {
        if (!this.closed) {
            this.closed = true;
            this.source.close();
            this.bufferField.clear();
        }
    }

    public Timeout timeout() {
        return this.source.timeout();
    }

    public String toString() {
        return "buffer(" + this.source + ')';
    }
}
