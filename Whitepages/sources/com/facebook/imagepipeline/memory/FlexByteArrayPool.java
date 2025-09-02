package com.facebook.imagepipeline.memory;

import com.facebook.common.references.CloseableReference;

public abstract class FlexByteArrayPool {
    public abstract CloseableReference get(int i);
}
