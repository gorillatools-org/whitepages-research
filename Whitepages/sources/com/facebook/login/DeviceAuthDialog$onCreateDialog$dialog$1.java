package com.facebook.login;

import android.app.Dialog;
import androidx.fragment.app.FragmentActivity;

public final class DeviceAuthDialog$onCreateDialog$dialog$1 extends Dialog {
    final /* synthetic */ DeviceAuthDialog this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DeviceAuthDialog$onCreateDialog$dialog$1(DeviceAuthDialog deviceAuthDialog, FragmentActivity fragmentActivity, int i) {
        super(fragmentActivity, i);
        this.this$0 = deviceAuthDialog;
    }

    public void onBackPressed() {
        if (this.this$0.onBackButtonPressed()) {
            super.onBackPressed();
        }
    }
}
