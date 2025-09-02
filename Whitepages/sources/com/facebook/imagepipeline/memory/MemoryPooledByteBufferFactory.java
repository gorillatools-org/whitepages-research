package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Throwables;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteStreams;
import java.io.IOException;
import java.io.InputStream;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class MemoryPooledByteBufferFactory implements PooledByteBufferFactory {
    private final MemoryChunkPool pool;
    private final PooledByteStreams pooledByteStreams;

    public MemoryPooledByteBufferFactory(MemoryChunkPool memoryChunkPool, PooledByteStreams pooledByteStreams2) {
        Intrinsics.checkNotNullParameter(memoryChunkPool, "pool");
        Intrinsics.checkNotNullParameter(pooledByteStreams2, "pooledByteStreams");
        this.pool = memoryChunkPool;
        this.pooledByteStreams = pooledByteStreams2;
    }

    public MemoryPooledByteBuffer newByteBuffer(InputStream inputStream) {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        MemoryPooledByteBufferOutputStream memoryPooledByteBufferOutputStream = new MemoryPooledByteBufferOutputStream(this.pool, 0, 2, (DefaultConstructorMarker) null);
        try {
            return newByteBuf(inputStream, memoryPooledByteBufferOutputStream);
        } finally {
            memoryPooledByteBufferOutputStream.close();
        }
    }

    public MemoryPooledByteBuffer newByteBuffer(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "bytes");
        MemoryPooledByteBufferOutputStream memoryPooledByteBufferOutputStream = new MemoryPooledByteBufferOutputStream(this.pool, bArr.length);
        try {
            memoryPooledByteBufferOutputStream.write(bArr, 0, bArr.length);
            MemoryPooledByteBuffer byteBuffer = memoryPooledByteBufferOutputStream.toByteBuffer();
            memoryPooledByteBufferOutputStream.close();
            return byteBuffer;
        } catch (IOException e) {
            throw Throwables.propagate(e);
        } catch (Throwable th) {
            memoryPooledByteBufferOutputStream.close();
            throw th;
        }
    }

    public MemoryPooledByteBuffer newByteBuffer(InputStream inputStream, int i) {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        MemoryPooledByteBufferOutputStream memoryPooledByteBufferOutputStream = new MemoryPooledByteBufferOutputStream(this.pool, i);
        try {
            return newByteBuf(inputStream, memoryPooledByteBufferOutputStream);
        } finally {
            memoryPooledByteBufferOutputStream.close();
        }
    }

    public final MemoryPooledByteBuffer newByteBuf(InputStream inputStream, MemoryPooledByteBufferOutputStream memoryPooledByteBufferOutputStream) {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        Intrinsics.checkNotNullParameter(memoryPooledByteBufferOutputStream, "outputStream");
        this.pooledByteStreams.copy(inputStream, memoryPooledByteBufferOutputStream);
        return memoryPooledByteBufferOutputStream.toByteBuffer();
    }

    public MemoryPooledByteBufferOutputStream newOutputStream() {
        return new MemoryPooledByteBufferOutputStream(this.pool, 0, 2, (DefaultConstructorMarker) null);
    }

    public MemoryPooledByteBufferOutputStream newOutputStream(int i) {
        return new MemoryPooledByteBufferOutputStream(this.pool, i);
    }
}
