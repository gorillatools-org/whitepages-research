package com.facebook.react.bridge;

import java.util.Iterator;
import java.util.Map;
import kotlin.jvm.internal.markers.KMappedMarker;

public final class ReadableNativeMap$entryIterator$1$1 implements Iterator<Map.Entry<? extends String, ? extends Object>>, KMappedMarker {
    final /* synthetic */ String[] $iteratorKeys;
    final /* synthetic */ Object[] $iteratorValues;
    private int currentIndex;

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    ReadableNativeMap$entryIterator$1$1(String[] strArr, Object[] objArr) {
        this.$iteratorKeys = strArr;
        this.$iteratorValues = objArr;
    }

    public final int getCurrentIndex() {
        return this.currentIndex;
    }

    public final void setCurrentIndex(int i) {
        this.currentIndex = i;
    }

    public boolean hasNext() {
        return this.currentIndex < this.$iteratorKeys.length;
    }

    public Map.Entry<String, Object> next() {
        int i = this.currentIndex;
        this.currentIndex = i + 1;
        return new ReadableNativeMap$entryIterator$1$1$next$1(this.$iteratorKeys, i, this.$iteratorValues);
    }
}
