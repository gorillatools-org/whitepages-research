package com.facebook.internal;

import android.os.Bundle;
import com.facebook.FacebookException;
import com.facebook.internal.WebDialog;

public final /* synthetic */ class FacebookDialogFragment$$ExternalSyntheticLambda1 implements WebDialog.OnCompleteListener {
    public final /* synthetic */ FacebookDialogFragment f$0;

    public /* synthetic */ FacebookDialogFragment$$ExternalSyntheticLambda1(FacebookDialogFragment facebookDialogFragment) {
        this.f$0 = facebookDialogFragment;
    }

    public final void onComplete(Bundle bundle, FacebookException facebookException) {
        FacebookDialogFragment.initDialog$lambda$1(this.f$0, bundle, facebookException);
    }
}
