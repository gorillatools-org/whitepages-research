package com.facebook.react.views.text.internal.span;

import android.text.SpannableStringBuilder;
import com.facebook.common.logging.FLog;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class SetSpanOperation {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int SPAN_MAX_PRIORITY = 255;
    private static final String TAG = "SetSpanOperation";
    private final int end;
    private final int start;
    public final ReactSpan what;

    public SetSpanOperation(int i, int i2, ReactSpan reactSpan) {
        Intrinsics.checkNotNullParameter(reactSpan, "what");
        this.start = i;
        this.end = i2;
        this.what = reactSpan;
    }

    public final void execute(SpannableStringBuilder spannableStringBuilder, int i) {
        Intrinsics.checkNotNullParameter(spannableStringBuilder, "builder");
        if (i >= 0) {
            int i2 = this.start == 0 ? 18 : 34;
            int i3 = 255 - i;
            if (i3 < 0) {
                FLog.w(TAG, "Text tree size exceeded the limit, styling may become unpredictable");
            }
            spannableStringBuilder.setSpan(this.what, this.start, this.end, ((Math.max(i3, 0) << 16) & 16711680) | (i2 & -16711681));
            return;
        }
        throw new IllegalStateException("Check failed.");
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
