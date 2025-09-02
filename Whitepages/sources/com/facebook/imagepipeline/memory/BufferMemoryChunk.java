package com.facebook.imagepipeline.memory;

import android.util.Log;
import com.facebook.common.internal.Preconditions;
import java.io.Closeable;
import java.nio.ByteBuffer;

public class BufferMemoryChunk implements MemoryChunk, Closeable {
    private ByteBuffer mBuffer;
    private final long mId = ((long) System.identityHashCode(this));
    private final int mSize;

    public BufferMemoryChunk(int i) {
        this.mBuffer = ByteBuffer.allocateDirect(i);
        this.mSize = i;
    }

    public synchronized void close() {
        this.mBuffer = null;
    }

    public synchronized boolean isClosed() {
        return this.mBuffer == null;
    }

    public int getSize() {
        return this.mSize;
    }

    public synchronized int write(int i, byte[] bArr, int i2, int i3) {
        int adjustByteCount;
        Preconditions.checkNotNull(bArr);
        Preconditions.checkState(!isClosed());
        Preconditions.checkNotNull(this.mBuffer);
        adjustByteCount = MemoryChunkUtil.adjustByteCount(i, i3, this.mSize);
        MemoryChunkUtil.checkBounds(i, bArr.length, i2, adjustByteCount, this.mSize);
        this.mBuffer.position(i);
        this.mBuffer.put(bArr, i2, adjustByteCount);
        return adjustByteCount;
    }

    public synchronized int read(int i, byte[] bArr, int i2, int i3) {
        int adjustByteCount;
        Preconditions.checkNotNull(bArr);
        Preconditions.checkState(!isClosed());
        Preconditions.checkNotNull(this.mBuffer);
        adjustByteCount = MemoryChunkUtil.adjustByteCount(i, i3, this.mSize);
        MemoryChunkUtil.checkBounds(i, bArr.length, i2, adjustByteCount, this.mSize);
        this.mBuffer.position(i);
        this.mBuffer.get(bArr, i2, adjustByteCount);
        return adjustByteCount;
    }

    public synchronized byte read(int i) {
        boolean z = false;
        Preconditions.checkState(!isClosed());
        Preconditions.checkArgument(Boolean.valueOf(i >= 0));
        if (i < this.mSize) {
            z = true;
        }
        Preconditions.checkArgument(Boolean.valueOf(z));
        Preconditions.checkNotNull(this.mBuffer);
        return this.mBuffer.get(i);
    }

    public void copy(int i, MemoryChunk memoryChunk, int i2, int i3) {
        Preconditions.checkNotNull(memoryChunk);
        if (memoryChunk.getUniqueId() == getUniqueId()) {
            Log.w("BufferMemoryChunk", "Copying from BufferMemoryChunk " + Long.toHexString(getUniqueId()) + " to BufferMemoryChunk " + Long.toHexString(memoryChunk.getUniqueId()) + " which are the same ");
            Preconditions.checkArgument(Boolean.FALSE);
        }
        if (memoryChunk.getUniqueId() < getUniqueId()) {
            synchronized (memoryChunk) {
                synchronized (this) {
                    doCopy(i, memoryChunk, i2, i3);
                }
            }
            return;
        }
        synchronized (this) {
            synchronized (memoryChunk) {
                doCopy(i, memoryChunk, i2, i3);
            }
        }
    }

    public long getNativePtr() {
        throw new UnsupportedOperationException("Cannot get the pointer of a BufferMemoryChunk");
    }

    public synchronized ByteBuffer getByteBuffer() {
        return this.mBuffer;
    }

    public long getUniqueId() {
        return this.mId;
    }

    private void doCopy(int i, MemoryChunk memoryChunk, int i2, int i3) {
        if (memoryChunk instanceof BufferMemoryChunk) {
            Preconditions.checkState(!isClosed());
            Preconditions.checkState(!memoryChunk.isClosed());
            Preconditions.checkNotNull(this.mBuffer);
            MemoryChunkUtil.checkBounds(i, memoryChunk.getSize(), i2, i3, this.mSize);
            this.mBuffer.position(i);
            ByteBuffer byteBuffer = (ByteBuffer) Preconditions.checkNotNull(memoryChunk.getByteBuffer());
            byteBuffer.position(i2);
            byte[] bArr = new byte[i3];
            this.mBuffer.get(bArr, 0, i3);
            byteBuffer.put(bArr, 0, i3);
            return;
        }
        throw new IllegalArgumentException("Cannot copy two incompatible MemoryChunks");
    }
}
