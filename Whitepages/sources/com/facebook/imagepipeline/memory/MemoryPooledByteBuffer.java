package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;

public class MemoryPooledByteBuffer implements PooledByteBuffer {
    CloseableReference mBufRef;
    private final int mSize;

    public MemoryPooledByteBuffer(CloseableReference closeableReference, int i) {
        Preconditions.checkNotNull(closeableReference);
        Preconditions.checkArgument(Boolean.valueOf(i >= 0 && i <= ((MemoryChunk) closeableReference.get()).getSize()));
        this.mBufRef = closeableReference.clone();
        this.mSize = i;
    }

    public synchronized int size() {
        ensureValid();
        return this.mSize;
    }

    public synchronized byte read(int i) {
        ensureValid();
        boolean z = false;
        Preconditions.checkArgument(Boolean.valueOf(i >= 0));
        if (i < this.mSize) {
            z = true;
        }
        Preconditions.checkArgument(Boolean.valueOf(z));
        Preconditions.checkNotNull(this.mBufRef);
        return ((MemoryChunk) this.mBufRef.get()).read(i);
    }

    public synchronized int read(int i, byte[] bArr, int i2, int i3) {
        ensureValid();
        Preconditions.checkArgument(Boolean.valueOf(i + i3 <= this.mSize));
        Preconditions.checkNotNull(this.mBufRef);
        return ((MemoryChunk) this.mBufRef.get()).read(i, bArr, i2, i3);
    }

    public synchronized boolean isClosed() {
        return !CloseableReference.isValid(this.mBufRef);
    }

    public synchronized void close() {
        CloseableReference.closeSafely(this.mBufRef);
        this.mBufRef = null;
    }

    /* access modifiers changed from: package-private */
    public synchronized void ensureValid() {
        if (isClosed()) {
            throw new PooledByteBuffer.ClosedException();
        }
    }
}
