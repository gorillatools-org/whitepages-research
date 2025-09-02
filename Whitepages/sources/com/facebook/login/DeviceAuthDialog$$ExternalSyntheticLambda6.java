package com.facebook.login;

import android.content.DialogInterface;

public final /* synthetic */ class DeviceAuthDialog$$ExternalSyntheticLambda6 implements DialogInterface.OnClickListener {
    public final /* synthetic */ DeviceAuthDialog f$0;

    public /* synthetic */ DeviceAuthDialog$$ExternalSyntheticLambda6(DeviceAuthDialog deviceAuthDialog) {
        this.f$0 = deviceAuthDialog;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        DeviceAuthDialog.presentConfirmation$lambda$8(this.f$0, dialogInterface, i);
    }
}
