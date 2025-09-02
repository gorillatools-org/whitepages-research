package com.facebook.imagepipeline.memory;

import java.nio.ByteBuffer;

public interface MemoryChunk {
    void close();

    void copy(int i, MemoryChunk memoryChunk, int i2, int i3);

    ByteBuffer getByteBuffer();

    long getNativePtr();

    int getSize();

    long getUniqueId();

    boolean isClosed();

    byte read(int i);

    int read(int i, byte[] bArr, int i2, int i3);

    int write(int i, byte[] bArr, int i2, int i3);
}
