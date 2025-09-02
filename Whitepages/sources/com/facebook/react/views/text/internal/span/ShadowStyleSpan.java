package com.facebook.react.views.text.internal.span;

import android.text.TextPaint;
import android.text.style.CharacterStyle;
import kotlin.jvm.internal.Intrinsics;

public final class ShadowStyleSpan extends CharacterStyle implements ReactSpan {
    private final int color;
    private final float dx;
    private final float dy;
    private final float radius;

    public final int getColor() {
        return this.color;
    }

    public ShadowStyleSpan(float f, float f2, float f3, int i) {
        this.dx = f;
        this.dy = f2;
        this.radius = f3;
        this.color = i;
    }

    public void updateDrawState(TextPaint textPaint) {
        Intrinsics.checkNotNullParameter(textPaint, "textPaint");
        textPaint.setShadowLayer(this.radius, this.dx, this.dy, this.color);
    }
}
