package com.google.protobuf;

import java.nio.ByteBuffer;

public abstract class ByteOutput {
    public abstract void writeLazy(ByteBuffer byteBuffer);

    public abstract void writeLazy(byte[] bArr, int i, int i2);
}
