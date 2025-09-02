package com.facebook.appevents.ml;

import kotlin.collections.ArraysKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

public final class MTensor {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private int capacity;
    private float[] data;
    private int[] shape;

    public MTensor(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "shape");
        this.shape = iArr;
        int access$getCapacity = Companion.getCapacity(iArr);
        this.capacity = access$getCapacity;
        this.data = new float[access$getCapacity];
    }

    public final float[] getData() {
        return this.data;
    }

    public final int getShapeSize() {
        return this.shape.length;
    }

    public final int getShape(int i) {
        return this.shape[i];
    }

    public final void reshape(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "shape");
        this.shape = iArr;
        int access$getCapacity = Companion.getCapacity(iArr);
        float[] fArr = new float[access$getCapacity];
        System.arraycopy(this.data, 0, fArr, 0, Math.min(this.capacity, access$getCapacity));
        this.data = fArr;
        this.capacity = access$getCapacity;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final int getCapacity(int[] iArr) {
            if (iArr.length != 0) {
                int i = iArr[0];
                IntIterator it = new IntRange(1, ArraysKt.getLastIndex(iArr)).iterator();
                while (it.hasNext()) {
                    i *= iArr[it.nextInt()];
                }
                return i;
            }
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
    }
}
