package com.facebook.react.devsupport;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class DoubleTapReloadRecognizer {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final long DOUBLE_TAP_DELAY = 200;
    private boolean doRefresh;

    public final boolean didDoubleTapR(int i, View view) {
        if (i == 46 && !(view instanceof EditText)) {
            if (this.doRefresh) {
                this.doRefresh = false;
                return true;
            }
            this.doRefresh = true;
            new Handler(Looper.getMainLooper()).postDelayed(new DoubleTapReloadRecognizer$$ExternalSyntheticLambda0(this), DOUBLE_TAP_DELAY);
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static final void didDoubleTapR$lambda$0(DoubleTapReloadRecognizer doubleTapReloadRecognizer) {
        doubleTapReloadRecognizer.doRefresh = false;
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
