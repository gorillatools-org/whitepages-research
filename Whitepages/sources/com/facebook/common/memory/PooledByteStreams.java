package com.facebook.common.memory;

import com.facebook.common.internal.Preconditions;
import java.io.InputStream;
import java.io.OutputStream;
import okhttp3.internal.http2.Http2;

public class PooledByteStreams {
    private final ByteArrayPool mByteArrayPool;
    private final int mTempBufSize;

    public PooledByteStreams(ByteArrayPool byteArrayPool) {
        this(byteArrayPool, Http2.INITIAL_MAX_FRAME_SIZE);
    }

    public PooledByteStreams(ByteArrayPool byteArrayPool, int i) {
        Preconditions.checkArgument(Boolean.valueOf(i > 0));
        this.mTempBufSize = i;
        this.mByteArrayPool = byteArrayPool;
    }

    public long copy(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = (byte[]) this.mByteArrayPool.get(this.mTempBufSize);
        long j = 0;
        while (true) {
            try {
                int read = inputStream.read(bArr, 0, this.mTempBufSize);
                if (read == -1) {
                    return j;
                }
                outputStream.write(bArr, 0, read);
                j += (long) read;
            } finally {
                this.mByteArrayPool.release(bArr);
            }
        }
    }
}
