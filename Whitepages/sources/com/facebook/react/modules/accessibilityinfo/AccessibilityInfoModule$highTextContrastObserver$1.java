package com.facebook.react.modules.accessibilityinfo;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;

public final class AccessibilityInfoModule$highTextContrastObserver$1 extends ContentObserver {
    final /* synthetic */ AccessibilityInfoModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AccessibilityInfoModule$highTextContrastObserver$1(AccessibilityInfoModule accessibilityInfoModule, Handler handler) {
        super(handler);
        this.this$0 = accessibilityInfoModule;
    }

    public void onChange(boolean z) {
        onChange(z, (Uri) null);
    }

    public void onChange(boolean z, Uri uri) {
        if (this.this$0.getReactApplicationContext().hasActiveReactInstance()) {
            this.this$0.updateAndSendHighTextContrastChangeEvent();
        }
    }
}
