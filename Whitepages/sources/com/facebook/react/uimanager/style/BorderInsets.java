package com.facebook.react.uimanager.style;

import android.content.Context;
import android.graphics.RectF;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import kotlin.jvm.internal.Intrinsics;

public final class BorderInsets {
    private final Float[] edgeInsets = new Float[LogicalEdge.values().length];

    public final void setBorderWidth(LogicalEdge logicalEdge, Float f) {
        Intrinsics.checkNotNullParameter(logicalEdge, "edge");
        this.edgeInsets[logicalEdge.ordinal()] = f;
    }

    public final RectF resolve(int i, Context context) {
        RectF rectF;
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        float f9;
        Intrinsics.checkNotNullParameter(context, "context");
        float f10 = 0.0f;
        if (i == 0) {
            Float f11 = this.edgeInsets[LogicalEdge.START.ordinal()];
            if (f11 == null && (f11 = this.edgeInsets[LogicalEdge.LEFT.ordinal()]) == null && (f11 = this.edgeInsets[LogicalEdge.HORIZONTAL.ordinal()]) == null && (f11 = this.edgeInsets[LogicalEdge.ALL.ordinal()]) == null) {
                f = 0.0f;
            } else {
                f = f11.floatValue();
            }
            Float f12 = this.edgeInsets[LogicalEdge.BLOCK_START.ordinal()];
            if (f12 == null && (f12 = this.edgeInsets[LogicalEdge.TOP.ordinal()]) == null && (f12 = this.edgeInsets[LogicalEdge.BLOCK.ordinal()]) == null && (f12 = this.edgeInsets[LogicalEdge.VERTICAL.ordinal()]) == null && (f12 = this.edgeInsets[LogicalEdge.ALL.ordinal()]) == null) {
                f2 = 0.0f;
            } else {
                f2 = f12.floatValue();
            }
            Float f13 = this.edgeInsets[LogicalEdge.END.ordinal()];
            if (f13 == null && (f13 = this.edgeInsets[LogicalEdge.RIGHT.ordinal()]) == null && (f13 = this.edgeInsets[LogicalEdge.HORIZONTAL.ordinal()]) == null && (f13 = this.edgeInsets[LogicalEdge.ALL.ordinal()]) == null) {
                f3 = 0.0f;
            } else {
                f3 = f13.floatValue();
            }
            Float f14 = this.edgeInsets[LogicalEdge.BLOCK_END.ordinal()];
            if (!(f14 == null && (f14 = this.edgeInsets[LogicalEdge.BOTTOM.ordinal()]) == null && (f14 = this.edgeInsets[LogicalEdge.BLOCK.ordinal()]) == null && (f14 = this.edgeInsets[LogicalEdge.VERTICAL.ordinal()]) == null && (f14 = this.edgeInsets[LogicalEdge.ALL.ordinal()]) == null)) {
                f10 = f14.floatValue();
            }
            rectF = new RectF(f, f2, f3, f10);
        } else if (i != 1) {
            throw new IllegalArgumentException("Expected resolved layout direction");
        } else if (I18nUtil.Companion.getInstance().doLeftAndRightSwapInRTL(context)) {
            Float f15 = this.edgeInsets[LogicalEdge.END.ordinal()];
            if (f15 == null && (f15 = this.edgeInsets[LogicalEdge.RIGHT.ordinal()]) == null && (f15 = this.edgeInsets[LogicalEdge.HORIZONTAL.ordinal()]) == null && (f15 = this.edgeInsets[LogicalEdge.ALL.ordinal()]) == null) {
                f7 = 0.0f;
            } else {
                f7 = f15.floatValue();
            }
            Float f16 = this.edgeInsets[LogicalEdge.BLOCK_START.ordinal()];
            if (f16 == null && (f16 = this.edgeInsets[LogicalEdge.TOP.ordinal()]) == null && (f16 = this.edgeInsets[LogicalEdge.BLOCK.ordinal()]) == null && (f16 = this.edgeInsets[LogicalEdge.VERTICAL.ordinal()]) == null && (f16 = this.edgeInsets[LogicalEdge.ALL.ordinal()]) == null) {
                f8 = 0.0f;
            } else {
                f8 = f16.floatValue();
            }
            Float f17 = this.edgeInsets[LogicalEdge.START.ordinal()];
            if (f17 == null && (f17 = this.edgeInsets[LogicalEdge.LEFT.ordinal()]) == null && (f17 = this.edgeInsets[LogicalEdge.HORIZONTAL.ordinal()]) == null && (f17 = this.edgeInsets[LogicalEdge.ALL.ordinal()]) == null) {
                f9 = 0.0f;
            } else {
                f9 = f17.floatValue();
            }
            Float f18 = this.edgeInsets[LogicalEdge.BLOCK_END.ordinal()];
            if (!(f18 == null && (f18 = this.edgeInsets[LogicalEdge.BOTTOM.ordinal()]) == null && (f18 = this.edgeInsets[LogicalEdge.BLOCK.ordinal()]) == null && (f18 = this.edgeInsets[LogicalEdge.VERTICAL.ordinal()]) == null && (f18 = this.edgeInsets[LogicalEdge.ALL.ordinal()]) == null)) {
                f10 = f18.floatValue();
            }
            rectF = new RectF(f7, f8, f9, f10);
        } else {
            Float f19 = this.edgeInsets[LogicalEdge.END.ordinal()];
            if (f19 == null && (f19 = this.edgeInsets[LogicalEdge.LEFT.ordinal()]) == null && (f19 = this.edgeInsets[LogicalEdge.HORIZONTAL.ordinal()]) == null && (f19 = this.edgeInsets[LogicalEdge.ALL.ordinal()]) == null) {
                f4 = 0.0f;
            } else {
                f4 = f19.floatValue();
            }
            Float f20 = this.edgeInsets[LogicalEdge.BLOCK_START.ordinal()];
            if (f20 == null && (f20 = this.edgeInsets[LogicalEdge.TOP.ordinal()]) == null && (f20 = this.edgeInsets[LogicalEdge.BLOCK.ordinal()]) == null && (f20 = this.edgeInsets[LogicalEdge.VERTICAL.ordinal()]) == null && (f20 = this.edgeInsets[LogicalEdge.ALL.ordinal()]) == null) {
                f5 = 0.0f;
            } else {
                f5 = f20.floatValue();
            }
            Float f21 = this.edgeInsets[LogicalEdge.START.ordinal()];
            if (f21 == null && (f21 = this.edgeInsets[LogicalEdge.RIGHT.ordinal()]) == null && (f21 = this.edgeInsets[LogicalEdge.HORIZONTAL.ordinal()]) == null && (f21 = this.edgeInsets[LogicalEdge.ALL.ordinal()]) == null) {
                f6 = 0.0f;
            } else {
                f6 = f21.floatValue();
            }
            Float f22 = this.edgeInsets[LogicalEdge.BLOCK_END.ordinal()];
            if (!(f22 == null && (f22 = this.edgeInsets[LogicalEdge.BOTTOM.ordinal()]) == null && (f22 = this.edgeInsets[LogicalEdge.BLOCK.ordinal()]) == null && (f22 = this.edgeInsets[LogicalEdge.VERTICAL.ordinal()]) == null && (f22 = this.edgeInsets[LogicalEdge.ALL.ordinal()]) == null)) {
                f10 = f22.floatValue();
            }
            rectF = new RectF(f4, f5, f6, f10);
        }
        return rectF;
    }
}
