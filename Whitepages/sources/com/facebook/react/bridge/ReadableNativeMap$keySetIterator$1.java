package com.facebook.react.bridge;

public final class ReadableNativeMap$keySetIterator$1 implements ReadableMapKeySetIterator {
    final /* synthetic */ String[] $iteratorKeys;
    private int currentIndex;

    ReadableNativeMap$keySetIterator$1(String[] strArr) {
        this.$iteratorKeys = strArr;
    }

    public final int getCurrentIndex() {
        return this.currentIndex;
    }

    public final void setCurrentIndex(int i) {
        this.currentIndex = i;
    }

    public boolean hasNextKey() {
        return this.currentIndex < this.$iteratorKeys.length;
    }

    public String nextKey() {
        String[] strArr = this.$iteratorKeys;
        int i = this.currentIndex;
        this.currentIndex = i + 1;
        return strArr[i];
    }
}
