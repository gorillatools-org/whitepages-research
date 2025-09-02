package com.facebook.react.views.text.internal.span;

import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import kotlin.jvm.internal.Intrinsics;

public final class CustomLetterSpacingSpan extends MetricAffectingSpan implements ReactSpan {
    private final float spacing;

    public CustomLetterSpacingSpan(float f) {
        this.spacing = f;
    }

    public final float getSpacing() {
        return this.spacing;
    }

    public void updateDrawState(TextPaint textPaint) {
        Intrinsics.checkNotNullParameter(textPaint, "paint");
        apply(textPaint);
    }

    public void updateMeasureState(TextPaint textPaint) {
        Intrinsics.checkNotNullParameter(textPaint, "paint");
        apply(textPaint);
    }

    private final void apply(TextPaint textPaint) {
        if (!Float.isNaN(this.spacing)) {
            textPaint.setLetterSpacing(this.spacing);
        }
    }
}
