package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.CRC32;
import java.util.zip.Inflater;
import kotlin.jvm.internal.Intrinsics;

public final class GzipSource implements Source {
    private final CRC32 crc = new CRC32();
    private final Inflater inflater;
    private final InflaterSource inflaterSource;
    private byte section;
    private final RealBufferedSource source;

    public GzipSource(Source source2) {
        Intrinsics.checkNotNullParameter(source2, "source");
        RealBufferedSource realBufferedSource = new RealBufferedSource(source2);
        this.source = realBufferedSource;
        Inflater inflater2 = new Inflater(true);
        this.inflater = inflater2;
        this.inflaterSource = new InflaterSource((BufferedSource) realBufferedSource, inflater2);
    }

    public long read(Buffer buffer, long j) {
        Intrinsics.checkNotNullParameter(buffer, "sink");
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        if (i < 0) {
            throw new IllegalArgumentException(("byteCount < 0: " + j).toString());
        } else if (i == 0) {
            return 0;
        } else {
            if (this.section == 0) {
                consumeHeader();
                this.section = 1;
            }
            if (this.section == 1) {
                long size = buffer.size();
                long read = this.inflaterSource.read(buffer, j);
                if (read != -1) {
                    updateCrc(buffer, size, read);
                    return read;
                }
                this.section = 2;
            }
            if (this.section == 2) {
                consumeTrailer();
                this.section = 3;
                if (!this.source.exhausted()) {
                    throw new IOException("gzip finished without exhausting source");
                }
            }
            return -1;
        }
    }

    private final void consumeHeader() {
        this.source.require(10);
        byte b = this.source.bufferField.getByte(3);
        boolean z = ((b >> 1) & 1) == 1;
        if (z) {
            updateCrc(this.source.bufferField, 0, 10);
        }
        checkEqual("ID1ID2", 8075, this.source.readShort());
        this.source.skip(8);
        if (((b >> 2) & 1) == 1) {
            this.source.require(2);
            if (z) {
                updateCrc(this.source.bufferField, 0, 2);
            }
            long readShortLe = (long) (this.source.bufferField.readShortLe() & 65535);
            this.source.require(readShortLe);
            if (z) {
                updateCrc(this.source.bufferField, 0, readShortLe);
            }
            this.source.skip(readShortLe);
        }
        if (((b >> 3) & 1) == 1) {
            long indexOf = this.source.indexOf((byte) 0);
            if (indexOf != -1) {
                if (z) {
                    updateCrc(this.source.bufferField, 0, indexOf + 1);
                }
                this.source.skip(indexOf + 1);
            } else {
                throw new EOFException();
            }
        }
        if (((b >> 4) & 1) == 1) {
            long indexOf2 = this.source.indexOf((byte) 0);
            if (indexOf2 != -1) {
                if (z) {
                    updateCrc(this.source.bufferField, 0, indexOf2 + 1);
                }
                this.source.skip(indexOf2 + 1);
            } else {
                throw new EOFException();
            }
        }
        if (z) {
            checkEqual("FHCRC", this.source.readShortLe(), (short) ((int) this.crc.getValue()));
            this.crc.reset();
        }
    }

    private final void consumeTrailer() {
        checkEqual("CRC", this.source.readIntLe(), (int) this.crc.getValue());
        checkEqual("ISIZE", this.source.readIntLe(), (int) this.inflater.getBytesWritten());
    }

    public Timeout timeout() {
        return this.source.timeout();
    }

    public void close() {
        this.inflaterSource.close();
    }

    private final void updateCrc(Buffer buffer, long j, long j2) {
        Segment segment = buffer.head;
        Intrinsics.checkNotNull(segment);
        while (true) {
            int i = segment.limit;
            int i2 = segment.pos;
            if (j < ((long) (i - i2))) {
                break;
            }
            j -= (long) (i - i2);
            segment = segment.next;
            Intrinsics.checkNotNull(segment);
        }
        while (j2 > 0) {
            int i3 = (int) (((long) segment.pos) + j);
            int min = (int) Math.min((long) (segment.limit - i3), j2);
            this.crc.update(segment.data, i3, min);
            j2 -= (long) min;
            segment = segment.next;
            Intrinsics.checkNotNull(segment);
            j = 0;
        }
    }

    private final void checkEqual(String str, int i, int i2) {
        if (i2 != i) {
            String format = String.format("%s: actual 0x%08x != expected 0x%08x", Arrays.copyOf(new Object[]{str, Integer.valueOf(i2), Integer.valueOf(i)}, 3));
            Intrinsics.checkNotNullExpressionValue(format, "format(this, *args)");
            throw new IOException(format);
        }
    }
}
