package com.facebook.react.devsupport;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.core.util.Supplier;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.R;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.devsupport.interfaces.PausedInDebuggerOverlayManager;

class PausedInDebuggerOverlayDialogManager implements PausedInDebuggerOverlayManager {
    private final Supplier mContextSupplier;
    private Dialog mPausedInDebuggerDialog;

    public PausedInDebuggerOverlayDialogManager(Supplier supplier) {
        this.mContextSupplier = supplier;
    }

    public void showPausedInDebuggerOverlay(String str, DevSupportManager.PausedInDebuggerOverlayCommandListener pausedInDebuggerOverlayCommandListener) {
        UiThreadUtil.runOnUiThread(new PausedInDebuggerOverlayDialogManager$$ExternalSyntheticLambda1(this, pausedInDebuggerOverlayCommandListener, str));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showPausedInDebuggerOverlay$1(DevSupportManager.PausedInDebuggerOverlayCommandListener pausedInDebuggerOverlayCommandListener, String str) {
        Dialog dialog = this.mPausedInDebuggerDialog;
        if (dialog != null) {
            dialog.dismiss();
        }
        Context context = (Context) this.mContextSupplier.get();
        if (context != null) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.paused_in_debugger_view, (ViewGroup) null);
            ((View) Assertions.assertNotNull(inflate.findViewById(R.id.button))).setOnClickListener(new PausedInDebuggerOverlayDialogManager$$ExternalSyntheticLambda0(pausedInDebuggerOverlayCommandListener));
            ((TextView) Assertions.assertNotNull((TextView) inflate.findViewById(R.id.button_text))).setText(str);
            Dialog dialog2 = new Dialog(context, R.style.NoAnimationDialog);
            this.mPausedInDebuggerDialog = dialog2;
            dialog2.setContentView(inflate);
            this.mPausedInDebuggerDialog.setCancelable(false);
            Window window = this.mPausedInDebuggerDialog.getWindow();
            if (window != null) {
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.dimAmount = 0.2f;
                window.setAttributes(attributes);
                window.addFlags(2);
                window.setGravity(48);
                window.setElevation(0.0f);
                window.setBackgroundDrawable(new ColorDrawable(0));
                window.setBackgroundDrawableResource(R.drawable.paused_in_debugger_background);
            }
            this.mPausedInDebuggerDialog.show();
        }
    }

    public void hidePausedInDebuggerOverlay() {
        UiThreadUtil.runOnUiThread(new PausedInDebuggerOverlayDialogManager$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$hidePausedInDebuggerOverlay$2() {
        Dialog dialog = this.mPausedInDebuggerDialog;
        if (dialog != null) {
            dialog.dismiss();
            this.mPausedInDebuggerDialog = null;
        }
    }
}
