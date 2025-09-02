package com.facebook.react.devsupport;

import android.view.View;
import com.facebook.react.devsupport.interfaces.DevSupportManager;

public final /* synthetic */ class PausedInDebuggerOverlayDialogManager$$ExternalSyntheticLambda0 implements View.OnClickListener {
    public final /* synthetic */ DevSupportManager.PausedInDebuggerOverlayCommandListener f$0;

    public /* synthetic */ PausedInDebuggerOverlayDialogManager$$ExternalSyntheticLambda0(DevSupportManager.PausedInDebuggerOverlayCommandListener pausedInDebuggerOverlayCommandListener) {
        this.f$0 = pausedInDebuggerOverlayCommandListener;
    }

    public final void onClick(View view) {
        this.f$0.onResume();
    }
}
