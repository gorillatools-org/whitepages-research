package com.facebook.imagepipeline.memory;

public interface PoolBackend {
    Object get(int i);

    int getSize(Object obj);

    Object pop();

    void put(Object obj);
}
