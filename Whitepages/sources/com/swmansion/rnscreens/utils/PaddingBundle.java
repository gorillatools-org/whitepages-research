package com.swmansion.rnscreens.utils;

public final class PaddingBundle {
    private final float height;
    private final float paddingEnd;
    private final float paddingStart;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PaddingBundle)) {
            return false;
        }
        PaddingBundle paddingBundle = (PaddingBundle) obj;
        return Float.compare(this.height, paddingBundle.height) == 0 && Float.compare(this.paddingStart, paddingBundle.paddingStart) == 0 && Float.compare(this.paddingEnd, paddingBundle.paddingEnd) == 0;
    }

    public int hashCode() {
        return (((Float.hashCode(this.height) * 31) + Float.hashCode(this.paddingStart)) * 31) + Float.hashCode(this.paddingEnd);
    }

    public String toString() {
        return "PaddingBundle(height=" + this.height + ", paddingStart=" + this.paddingStart + ", paddingEnd=" + this.paddingEnd + ')';
    }

    public PaddingBundle(float f, float f2, float f3) {
        this.height = f;
        this.paddingStart = f2;
        this.paddingEnd = f3;
    }

    public final float getHeight() {
        return this.height;
    }

    public final float getPaddingStart() {
        return this.paddingStart;
    }

    public final float getPaddingEnd() {
        return this.paddingEnd;
    }
}
