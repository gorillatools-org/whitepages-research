package androidx.profileinstaller;

import com.salesforce.marketingcloud.b;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;

abstract class Encoding {
    static int utf8Length(String str) {
        return str.getBytes(StandardCharsets.UTF_8).length;
    }

    static void writeUInt(OutputStream outputStream, long j, int i) {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) ((int) ((j >> (i2 * 8)) & 255));
        }
        outputStream.write(bArr);
    }

    static void writeUInt8(OutputStream outputStream, int i) {
        writeUInt(outputStream, (long) i, 1);
    }

    static void writeUInt16(OutputStream outputStream, int i) {
        writeUInt(outputStream, (long) i, 2);
    }

    static void writeUInt32(OutputStream outputStream, long j) {
        writeUInt(outputStream, j, 4);
    }

    static void writeString(OutputStream outputStream, String str) {
        outputStream.write(str.getBytes(StandardCharsets.UTF_8));
    }

    static int bitsToBytes(int i) {
        return ((i + 7) & -8) / 8;
    }

    static byte[] read(InputStream inputStream, int i) {
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int read = inputStream.read(bArr, i2, i - i2);
            if (read >= 0) {
                i2 += read;
            } else {
                throw error("Not enough bytes to read: " + i);
            }
        }
        return bArr;
    }

    static long readUInt(InputStream inputStream, int i) {
        byte[] read = read(inputStream, i);
        long j = 0;
        for (int i2 = 0; i2 < i; i2++) {
            j += ((long) (read[i2] & 255)) << (i2 * 8);
        }
        return j;
    }

    static int readUInt8(InputStream inputStream) {
        return (int) readUInt(inputStream, 1);
    }

    static int readUInt16(InputStream inputStream) {
        return (int) readUInt(inputStream, 2);
    }

    static long readUInt32(InputStream inputStream) {
        return readUInt(inputStream, 4);
    }

    static String readString(InputStream inputStream, int i) {
        return new String(read(inputStream, i), StandardCharsets.UTF_8);
    }

    static byte[] readCompressed(InputStream inputStream, int i, int i2) {
        Inflater inflater = new Inflater();
        try {
            byte[] bArr = new byte[i2];
            byte[] bArr2 = new byte[b.u];
            int i3 = 0;
            int i4 = 0;
            while (!inflater.finished() && !inflater.needsDictionary() && i3 < i) {
                int read = inputStream.read(bArr2);
                if (read >= 0) {
                    inflater.setInput(bArr2, 0, read);
                    i4 += inflater.inflate(bArr, i4, i2 - i4);
                    i3 += read;
                } else {
                    throw error("Invalid zip data. Stream ended after $totalBytesRead bytes. Expected " + i + " bytes");
                }
            }
            if (i3 != i) {
                throw error("Didn't read enough bytes during decompression. expected=" + i + " actual=" + i3);
            } else if (inflater.finished()) {
                inflater.end();
                return bArr;
            } else {
                throw error("Inflater did not finish");
            }
        } catch (DataFormatException e) {
            throw error(e.getMessage());
        } catch (Throwable th) {
            inflater.end();
            throw th;
        }
    }

    static void writeCompressed(OutputStream outputStream, byte[] bArr) {
        writeUInt32(outputStream, (long) bArr.length);
        byte[] compress = compress(bArr);
        writeUInt32(outputStream, (long) compress.length);
        outputStream.write(compress);
    }

    static byte[] compress(byte[] bArr) {
        DeflaterOutputStream deflaterOutputStream;
        Deflater deflater = new Deflater(1);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream, deflater);
            deflaterOutputStream.write(bArr);
            deflaterOutputStream.close();
            deflater.end();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            deflater.end();
            throw th;
        }
        throw th;
    }

    static void writeAll(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = new byte[512];
        while (true) {
            int read = inputStream.read(bArr);
            if (read > 0) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    static RuntimeException error(String str) {
        return new IllegalStateException(str);
    }
}
