package com.facebook.react.views.modal;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.views.modal.ReactModalHostView;
import kotlin.jvm.internal.Intrinsics;

public final class ReactModalHostView$showOrUpdate$1 implements DialogInterface.OnKeyListener {
    final /* synthetic */ ReactModalHostView this$0;

    ReactModalHostView$showOrUpdate$1(ReactModalHostView reactModalHostView) {
        this.this$0 = reactModalHostView;
    }

    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(dialogInterface, "dialog");
        Intrinsics.checkNotNullParameter(keyEvent, "event");
        if (keyEvent.getAction() != 1) {
            return false;
        }
        if (i == 4 || i == 111) {
            ReactModalHostView.OnRequestCloseListener onRequestCloseListener = this.this$0.getOnRequestCloseListener();
            if (onRequestCloseListener != null) {
                onRequestCloseListener.onRequestClose(dialogInterface);
                return true;
            }
            throw new IllegalStateException("onRequestClose callback must be set if back key is expected to close the modal");
        }
        Context context = this.this$0.getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
        Activity currentActivity = ((ReactContext) context).getCurrentActivity();
        if (currentActivity != null) {
            return currentActivity.onKeyUp(i, keyEvent);
        }
        return false;
    }
}
