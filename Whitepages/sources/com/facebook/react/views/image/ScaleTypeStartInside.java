package com.facebook.react.views.image;

import android.graphics.Matrix;
import android.graphics.Rect;
import com.facebook.drawee.drawable.ScalingUtils$AbstractScaleType;
import com.facebook.drawee.drawable.ScalingUtils$ScaleType;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

public final class ScaleTypeStartInside extends ScalingUtils$AbstractScaleType {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final ScalingUtils$ScaleType INSTANCE = new ScaleTypeStartInside();

    public void getTransformImpl(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
        Intrinsics.checkNotNullParameter(matrix, "outTransform");
        Intrinsics.checkNotNullParameter(rect, "parentRect");
        float coerceAtMost = RangesKt.coerceAtMost(Math.min(f3, f4), 1.0f);
        matrix.setScale(coerceAtMost, coerceAtMost);
        matrix.postTranslate((float) Math.round((float) rect.left), (float) Math.round((float) rect.top));
    }

    public String toString() {
        return "start_inside";
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ScalingUtils$ScaleType getINSTANCE() {
            return ScaleTypeStartInside.INSTANCE;
        }
    }
}
