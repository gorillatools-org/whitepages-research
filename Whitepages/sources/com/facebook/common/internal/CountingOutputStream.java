package com.facebook.common.internal;

import java.io.FilterOutputStream;
import java.io.OutputStream;

public class CountingOutputStream extends FilterOutputStream {
    private long mCount = 0;

    public CountingOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    public long getCount() {
        return this.mCount;
    }

    public void write(byte[] bArr, int i, int i2) {
        this.out.write(bArr, i, i2);
        this.mCount += (long) i2;
    }

    public void write(int i) {
        this.out.write(i);
        this.mCount++;
    }

    public void close() {
        this.out.close();
    }
}
