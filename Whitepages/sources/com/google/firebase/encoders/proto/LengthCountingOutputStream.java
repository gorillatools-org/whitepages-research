package com.google.firebase.encoders.proto;

import java.io.OutputStream;

final class LengthCountingOutputStream extends OutputStream {
    private long length = 0;

    LengthCountingOutputStream() {
    }

    public void write(int i) {
        this.length++;
    }

    public void write(byte[] bArr) {
        this.length += (long) bArr.length;
    }

    public void write(byte[] bArr, int i, int i2) {
        int i3;
        if (i < 0 || i > bArr.length || i2 < 0 || (i3 = i + i2) > bArr.length || i3 < 0) {
            throw new IndexOutOfBoundsException();
        }
        this.length += (long) i2;
    }

    /* access modifiers changed from: package-private */
    public long getLength() {
        return this.length;
    }
}
