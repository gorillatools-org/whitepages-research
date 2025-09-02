package com.facebook.react.views.text.internal.span;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

public final class ReactOpacitySpan extends CharacterStyle implements UpdateAppearance, ReactSpan {
    private final float opacity;

    public final float getOpacity() {
        return this.opacity;
    }

    public ReactOpacitySpan(float f) {
        this.opacity = f;
    }

    public void updateDrawState(TextPaint textPaint) {
        Intrinsics.checkNotNullParameter(textPaint, "paint");
        textPaint.setAlpha(MathKt.roundToInt(((float) Color.alpha(textPaint.getColor())) * this.opacity));
        int i = textPaint.bgColor;
        if (i != 0) {
            textPaint.bgColor = Color.argb(MathKt.roundToInt(((float) Color.alpha(i)) * this.opacity), Color.red(textPaint.bgColor), Color.green(textPaint.bgColor), Color.blue(textPaint.bgColor));
        }
    }
}
