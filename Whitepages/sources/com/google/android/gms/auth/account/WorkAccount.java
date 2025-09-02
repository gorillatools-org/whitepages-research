package com.google.android.gms.auth.account;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.auth.zzal;

public class WorkAccount {
    public static final Api<Api.ApiOptions.NoOptions> API;
    @Deprecated
    public static final WorkAccountApi WorkAccountApi = new zzal();
    private static final Api.ClientKey zza;
    private static final Api.AbstractClientBuilder zzb;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        zza = clientKey;
        zzf zzf = new zzf();
        zzb = zzf;
        API = new Api<>("WorkAccount.API", zzf, clientKey);
    }

    private WorkAccount() {
    }

    public static WorkAccountClient getClient(Activity activity) {
        return new WorkAccountClient(activity);
    }

    public static WorkAccountClient getClient(Context context) {
        return new WorkAccountClient(context);
    }
}
