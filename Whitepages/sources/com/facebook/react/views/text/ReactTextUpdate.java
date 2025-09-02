package com.facebook.react.views.text;

import android.text.Spannable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ReactTextUpdate {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final boolean containsImages;
    private final int jsEventCounter;
    private final int justificationMode;
    private final float paddingBottom;
    private final float paddingLeft;
    private final float paddingRight;
    private final float paddingTop;
    private final Spannable text;
    private final int textAlign;
    private final int textBreakStrategy;

    public static final ReactTextUpdate buildReactTextUpdateFromState(Spannable spannable, int i, int i2, int i3, int i4) {
        return Companion.buildReactTextUpdateFromState(spannable, i, i2, i3, i4);
    }

    public ReactTextUpdate(Spannable spannable, int i, boolean z, float f, float f2, float f3, float f4, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(spannable, "text");
        this.text = spannable;
        this.jsEventCounter = i;
        this.containsImages = z;
        this.paddingLeft = f;
        this.paddingTop = f2;
        this.paddingRight = f3;
        this.paddingBottom = f4;
        this.textAlign = i2;
        this.textBreakStrategy = i3;
        this.justificationMode = i4;
    }

    public final Spannable getText() {
        return this.text;
    }

    public final int getJsEventCounter() {
        return this.jsEventCounter;
    }

    public final boolean getContainsImages() {
        return this.containsImages;
    }

    public final float getPaddingLeft() {
        return this.paddingLeft;
    }

    public final float getPaddingTop() {
        return this.paddingTop;
    }

    public final float getPaddingRight() {
        return this.paddingRight;
    }

    public final float getPaddingBottom() {
        return this.paddingBottom;
    }

    public final int getTextAlign() {
        return this.textAlign;
    }

    public final int getTextBreakStrategy() {
        return this.textBreakStrategy;
    }

    public final int getJustificationMode() {
        return this.justificationMode;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ReactTextUpdate(Spannable spannable, int i, boolean z, float f, float f2, float f3, float f4, int i2) {
        this(spannable, i, z, f, f2, f3, f4, i2, 1, 0);
        Intrinsics.checkNotNullParameter(spannable, "text");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ReactTextUpdate(Spannable spannable, int i, boolean z, int i2, int i3, int i4) {
        this(spannable, i, z, -1.0f, -1.0f, -1.0f, -1.0f, i2, i3, i4);
        Intrinsics.checkNotNullParameter(spannable, "text");
    }

    public final boolean containsImages() {
        return this.containsImages;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ReactTextUpdate buildReactTextUpdateFromState(Spannable spannable, int i, int i2, int i3, int i4) {
            Intrinsics.checkNotNullParameter(spannable, "text");
            return new ReactTextUpdate(spannable, i, false, i2, i3, i4);
        }
    }
}
