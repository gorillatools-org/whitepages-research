package com.facebook.react.devsupport;

import com.facebook.react.devsupport.interfaces.DevSupportManager;

public final /* synthetic */ class PausedInDebuggerOverlayDialogManager$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ PausedInDebuggerOverlayDialogManager f$0;
    public final /* synthetic */ DevSupportManager.PausedInDebuggerOverlayCommandListener f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ PausedInDebuggerOverlayDialogManager$$ExternalSyntheticLambda1(PausedInDebuggerOverlayDialogManager pausedInDebuggerOverlayDialogManager, DevSupportManager.PausedInDebuggerOverlayCommandListener pausedInDebuggerOverlayCommandListener, String str) {
        this.f$0 = pausedInDebuggerOverlayDialogManager;
        this.f$1 = pausedInDebuggerOverlayCommandListener;
        this.f$2 = str;
    }

    public final void run() {
        this.f$0.lambda$showPausedInDebuggerOverlay$1(this.f$1, this.f$2);
    }
}
