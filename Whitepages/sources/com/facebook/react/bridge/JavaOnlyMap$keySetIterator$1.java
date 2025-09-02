package com.facebook.react.bridge;

import java.util.Iterator;
import java.util.Map;

public final class JavaOnlyMap$keySetIterator$1 implements ReadableMapKeySetIterator {
    private final Iterator<Map.Entry<String, Object>> iterator;

    JavaOnlyMap$keySetIterator$1(JavaOnlyMap javaOnlyMap) {
        this.iterator = javaOnlyMap.backingMap.entrySet().iterator();
    }

    public boolean hasNextKey() {
        return this.iterator.hasNext();
    }

    public String nextKey() {
        return (String) this.iterator.next().getKey();
    }
}
