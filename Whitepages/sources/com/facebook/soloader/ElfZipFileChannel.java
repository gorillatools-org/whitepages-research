package com.facebook.soloader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ElfZipFileChannel implements ElfByteChannel {
    private InputStream mIs;
    private final long mLength;
    private boolean mOpened = true;
    private long mPos = 0;
    private final ZipEntry mZipEntry;
    private final ZipFile mZipFile;

    public ElfZipFileChannel(ZipFile zipFile, ZipEntry zipEntry) {
        this.mZipFile = zipFile;
        this.mZipEntry = zipEntry;
        this.mLength = zipEntry.getSize();
        InputStream inputStream = zipFile.getInputStream(zipEntry);
        this.mIs = inputStream;
        if (inputStream == null) {
            throw new IOException(zipEntry.getName() + "'s InputStream is null");
        }
    }

    public ElfByteChannel position(long j) {
        InputStream inputStream = this.mIs;
        if (inputStream != null) {
            long j2 = this.mPos;
            if (j == j2) {
                return this;
            }
            long j3 = this.mLength;
            if (j > j3) {
                j = j3;
            }
            if (j >= j2) {
                inputStream.skip(j - j2);
            } else {
                inputStream.close();
                InputStream inputStream2 = this.mZipFile.getInputStream(this.mZipEntry);
                this.mIs = inputStream2;
                if (inputStream2 != null) {
                    inputStream2.skip(j);
                } else {
                    throw new IOException(this.mZipEntry.getName() + "'s InputStream is null");
                }
            }
            this.mPos = j;
            return this;
        }
        throw new IOException(this.mZipEntry.getName() + "'s InputStream is null");
    }

    public int read(ByteBuffer byteBuffer) {
        return read(byteBuffer, this.mPos);
    }

    public int read(ByteBuffer byteBuffer, long j) {
        if (this.mIs != null) {
            int remaining = byteBuffer.remaining();
            long j2 = this.mLength - j;
            if (j2 <= 0) {
                return -1;
            }
            int i = (int) j2;
            if (remaining > i) {
                remaining = i;
            }
            position(j);
            if (byteBuffer.hasArray()) {
                this.mIs.read(byteBuffer.array(), 0, remaining);
                byteBuffer.position(byteBuffer.position() + remaining);
            } else {
                byte[] bArr = new byte[remaining];
                this.mIs.read(bArr, 0, remaining);
                byteBuffer.put(bArr, 0, remaining);
            }
            this.mPos += (long) remaining;
            return remaining;
        }
        throw new IOException("InputStream is null");
    }

    public int write(ByteBuffer byteBuffer) {
        throw new UnsupportedOperationException("ElfZipFileChannel doesn't support write");
    }

    public boolean isOpen() {
        return this.mOpened;
    }

    public void close() {
        InputStream inputStream = this.mIs;
        if (inputStream != null) {
            inputStream.close();
            this.mOpened = false;
        }
    }
}
