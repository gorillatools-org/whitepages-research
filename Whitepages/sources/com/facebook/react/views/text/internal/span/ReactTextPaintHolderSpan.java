package com.facebook.react.views.text.internal.span;

import android.text.TextPaint;
import kotlin.jvm.internal.Intrinsics;

public final class ReactTextPaintHolderSpan implements ReactSpan {
    private final TextPaint textPaint;

    public static /* synthetic */ ReactTextPaintHolderSpan copy$default(ReactTextPaintHolderSpan reactTextPaintHolderSpan, TextPaint textPaint2, int i, Object obj) {
        if ((i & 1) != 0) {
            textPaint2 = reactTextPaintHolderSpan.textPaint;
        }
        return reactTextPaintHolderSpan.copy(textPaint2);
    }

    public final TextPaint component1() {
        return this.textPaint;
    }

    public final ReactTextPaintHolderSpan copy(TextPaint textPaint2) {
        Intrinsics.checkNotNullParameter(textPaint2, "textPaint");
        return new ReactTextPaintHolderSpan(textPaint2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ReactTextPaintHolderSpan) && Intrinsics.areEqual((Object) this.textPaint, (Object) ((ReactTextPaintHolderSpan) obj).textPaint);
    }

    public int hashCode() {
        return this.textPaint.hashCode();
    }

    public String toString() {
        TextPaint textPaint2 = this.textPaint;
        return "ReactTextPaintHolderSpan(textPaint=" + textPaint2 + ")";
    }

    public ReactTextPaintHolderSpan(TextPaint textPaint2) {
        Intrinsics.checkNotNullParameter(textPaint2, "textPaint");
        this.textPaint = textPaint2;
    }

    public final TextPaint getTextPaint() {
        return this.textPaint;
    }
}
