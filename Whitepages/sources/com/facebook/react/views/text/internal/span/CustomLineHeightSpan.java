package com.facebook.react.views.text.internal.span;

import android.graphics.Paint;
import android.text.style.LineHeightSpan;
import kotlin.jvm.internal.Intrinsics;

public final class CustomLineHeightSpan implements LineHeightSpan, ReactSpan {
    private final int lineHeight;

    public CustomLineHeightSpan(float f) {
        this.lineHeight = (int) Math.ceil((double) f);
    }

    public final int getLineHeight() {
        return this.lineHeight;
    }

    public void chooseHeight(CharSequence charSequence, int i, int i2, int i3, int i4, Paint.FontMetricsInt fontMetricsInt) {
        Intrinsics.checkNotNullParameter(charSequence, "text");
        Intrinsics.checkNotNullParameter(fontMetricsInt, "fm");
        int i5 = this.lineHeight;
        int i6 = fontMetricsInt.ascent;
        double d = (double) (((float) (i5 - ((-i6) + fontMetricsInt.descent))) / 2.0f);
        fontMetricsInt.ascent = i6 - ((int) ((float) Math.ceil(d)));
        fontMetricsInt.descent += (int) ((float) Math.floor(d));
        if (i == 0) {
            fontMetricsInt.top = fontMetricsInt.ascent;
        }
        if (i2 == charSequence.length()) {
            fontMetricsInt.bottom = fontMetricsInt.descent;
        }
    }
}
