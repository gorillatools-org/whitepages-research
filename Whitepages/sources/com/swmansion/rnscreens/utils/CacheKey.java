package com.swmansion.rnscreens.utils;

final class CacheKey {
    private final int fontSize;
    private final boolean isTitleEmpty;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CacheKey)) {
            return false;
        }
        CacheKey cacheKey = (CacheKey) obj;
        return this.fontSize == cacheKey.fontSize && this.isTitleEmpty == cacheKey.isTitleEmpty;
    }

    public int hashCode() {
        return (Integer.hashCode(this.fontSize) * 31) + Boolean.hashCode(this.isTitleEmpty);
    }

    public String toString() {
        return "CacheKey(fontSize=" + this.fontSize + ", isTitleEmpty=" + this.isTitleEmpty + ')';
    }

    public CacheKey(int i, boolean z) {
        this.fontSize = i;
        this.isTitleEmpty = z;
    }

    public final int getFontSize() {
        return this.fontSize;
    }
}
