package com.facebook.imagepipeline.memory;

import com.facebook.common.memory.PooledByteBufferOutputStream;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class MemoryPooledByteBufferOutputStream extends PooledByteBufferOutputStream {
    private CloseableReference bufRef;
    private int count;
    private final MemoryChunkPool pool;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ MemoryPooledByteBufferOutputStream(MemoryChunkPool memoryChunkPool, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(memoryChunkPool, (i2 & 2) != 0 ? memoryChunkPool.getMinBufferSize() : i);
    }

    public MemoryPooledByteBufferOutputStream(MemoryChunkPool memoryChunkPool, int i) {
        Intrinsics.checkNotNullParameter(memoryChunkPool, "pool");
        if (i > 0) {
            this.pool = memoryChunkPool;
            this.count = 0;
            this.bufRef = CloseableReference.of(memoryChunkPool.get(i), (ResourceReleaser) memoryChunkPool);
            return;
        }
        throw new IllegalStateException("Check failed.");
    }

    public MemoryPooledByteBuffer toByteBuffer() {
        ensureValid();
        CloseableReference closeableReference = this.bufRef;
        if (closeableReference != null) {
            return new MemoryPooledByteBuffer(closeableReference, this.count);
        }
        throw new IllegalStateException("Required value was null.");
    }

    public int size() {
        return this.count;
    }

    public void write(int i) {
        write(new byte[]{(byte) i});
    }

    public void write(byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(bArr, "buffer");
        if (i < 0 || i2 < 0 || i + i2 > bArr.length) {
            throw new ArrayIndexOutOfBoundsException("length=" + bArr.length + "; regionStart=" + i + "; regionLength=" + i2);
        }
        ensureValid();
        realloc(this.count + i2);
        CloseableReference closeableReference = this.bufRef;
        if (closeableReference != null) {
            ((MemoryChunk) closeableReference.get()).write(this.count, bArr, i, i2);
            this.count += i2;
            return;
        }
        throw new IllegalStateException("Required value was null.");
    }

    public void close() {
        CloseableReference.closeSafely(this.bufRef);
        this.bufRef = null;
        this.count = -1;
        super.close();
    }

    public final void realloc(int i) {
        ensureValid();
        CloseableReference closeableReference = this.bufRef;
        if (closeableReference != null) {
            Intrinsics.checkNotNull(closeableReference);
            if (i > ((MemoryChunk) closeableReference.get()).getSize()) {
                Object obj = this.pool.get(i);
                Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
                MemoryChunk memoryChunk = (MemoryChunk) obj;
                CloseableReference closeableReference2 = this.bufRef;
                if (closeableReference2 != null) {
                    Intrinsics.checkNotNull(closeableReference2);
                    ((MemoryChunk) closeableReference2.get()).copy(0, memoryChunk, 0, this.count);
                    CloseableReference closeableReference3 = this.bufRef;
                    Intrinsics.checkNotNull(closeableReference3);
                    closeableReference3.close();
                    this.bufRef = CloseableReference.of((Object) memoryChunk, (ResourceReleaser) this.pool);
                    return;
                }
                throw new IllegalStateException("Required value was null.");
            }
            return;
        }
        throw new IllegalStateException("Required value was null.");
    }

    private final void ensureValid() {
        if (!CloseableReference.isValid(this.bufRef)) {
            throw new InvalidStreamException();
        }
    }

    public static final class InvalidStreamException extends RuntimeException {
        public InvalidStreamException() {
            super("OutputStream no longer valid");
        }
    }
}
