package com.google.android.gms.auth.api.identity;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p001authapi.zbaf;
import com.google.android.gms.internal.p001authapi.zbap;
import com.google.android.gms.internal.p001authapi.zbz;

public final class Identity {
    private Identity() {
    }

    public static AuthorizationClient getAuthorizationClient(Activity activity) {
        return new zbz((Activity) Preconditions.checkNotNull(activity), new zba((String) null));
    }

    public static CredentialSavingClient getCredentialSavingClient(Activity activity) {
        return new zbaf((Activity) Preconditions.checkNotNull(activity), new zbf());
    }

    public static SignInClient getSignInClient(Activity activity) {
        return new zbap((Activity) Preconditions.checkNotNull(activity), new zbs());
    }

    public static AuthorizationClient getAuthorizationClient(Context context) {
        return new zbz((Context) Preconditions.checkNotNull(context), new zba((String) null));
    }

    public static CredentialSavingClient getCredentialSavingClient(Context context) {
        return new zbaf((Context) Preconditions.checkNotNull(context), new zbf());
    }

    public static SignInClient getSignInClient(Context context) {
        return new zbap((Context) Preconditions.checkNotNull(context), new zbs());
    }
}
