package com.facebook.common.memory;

import java.io.InputStream;

public interface PooledByteBufferFactory {
    PooledByteBuffer newByteBuffer(InputStream inputStream);

    PooledByteBuffer newByteBuffer(InputStream inputStream, int i);

    PooledByteBuffer newByteBuffer(byte[] bArr);

    PooledByteBufferOutputStream newOutputStream();

    PooledByteBufferOutputStream newOutputStream(int i);
}
