package com.google.android.gms.fido;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.fido.fido2.Fido2ApiClient;
import com.google.android.gms.fido.fido2.Fido2PrivilegedApiClient;
import com.google.android.gms.fido.u2f.U2fApiClient;
import com.google.android.gms.internal.fido.zzaa;
import com.google.android.gms.internal.fido.zzab;

public class Fido {
    public static final String FIDO2_KEY_CREDENTIAL_EXTRA = "FIDO2_CREDENTIAL_EXTRA";
    @Deprecated
    public static final String FIDO2_KEY_ERROR_EXTRA = "FIDO2_ERROR_EXTRA";
    @Deprecated
    public static final String FIDO2_KEY_RESPONSE_EXTRA = "FIDO2_RESPONSE_EXTRA";
    public static final String KEY_RESPONSE_EXTRA = "RESPONSE_EXTRA";
    public static final Api.ClientKey zza;
    public static final Api zzb;
    public static final zzaa zzc = new zzaa();

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        zza = clientKey;
        zzb = new Api("Fido.U2F_ZERO_PARTY_API", new zzab(), clientKey);
    }

    private Fido() {
    }

    public static Fido2ApiClient getFido2ApiClient(Activity activity) {
        return new Fido2ApiClient(activity);
    }

    public static Fido2PrivilegedApiClient getFido2PrivilegedApiClient(Activity activity) {
        return new Fido2PrivilegedApiClient(activity);
    }

    public static U2fApiClient getU2fApiClient(Activity activity) {
        return new U2fApiClient(activity);
    }

    public static Fido2ApiClient getFido2ApiClient(Context context) {
        return new Fido2ApiClient(context);
    }

    public static Fido2PrivilegedApiClient getFido2PrivilegedApiClient(Context context) {
        return new Fido2PrivilegedApiClient(context);
    }

    public static U2fApiClient getU2fApiClient(Context context) {
        return new U2fApiClient(context);
    }
}
