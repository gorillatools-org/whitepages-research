package com.google.android.gms.internal.p001authapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.auth.api.identity.CredentialSavingClient;
import com.google.android.gms.auth.api.identity.SaveAccountLinkingTokenRequest;
import com.google.android.gms.auth.api.identity.SaveAccountLinkingTokenResult;
import com.google.android.gms.auth.api.identity.SavePasswordRequest;
import com.google.android.gms.auth.api.identity.SavePasswordResult;
import com.google.android.gms.auth.api.identity.zbf;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.tasks.Task;

/* renamed from: com.google.android.gms.internal.auth-api.zbaf  reason: invalid package */
public final class zbaf extends GoogleApi implements CredentialSavingClient {
    private static final Api.ClientKey zba;
    private static final Api.AbstractClientBuilder zbb;
    private static final Api zbc;
    private final String zbd = zbas.zba();

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        zba = clientKey;
        zbac zbac = new zbac();
        zbb = zbac;
        zbc = new Api("Auth.Api.Identity.CredentialSaving.API", zbac, clientKey);
    }

    public zbaf(Activity activity, zbf zbf) {
        super(activity, zbc, zbf, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public final Status getStatusFromIntent(Intent intent) {
        if (intent == null) {
            return Status.RESULT_INTERNAL_ERROR;
        }
        Status status = (Status) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "status", Status.CREATOR);
        return status == null ? Status.RESULT_INTERNAL_ERROR : status;
    }

    public final Task<SaveAccountLinkingTokenResult> saveAccountLinkingToken(SaveAccountLinkingTokenRequest saveAccountLinkingTokenRequest) {
        Preconditions.checkNotNull(saveAccountLinkingTokenRequest);
        SaveAccountLinkingTokenRequest.Builder zba2 = SaveAccountLinkingTokenRequest.zba(saveAccountLinkingTokenRequest);
        zba2.zba(this.zbd);
        return doRead(TaskApiCall.builder().setFeatures(zbar.zbg).run(new zbaa(this, zba2.build())).setAutoResolveMissingFeatures(false).setMethodKey(1535).build());
    }

    public final Task<SavePasswordResult> savePassword(SavePasswordRequest savePasswordRequest) {
        Preconditions.checkNotNull(savePasswordRequest);
        SavePasswordRequest.Builder zba2 = SavePasswordRequest.zba(savePasswordRequest);
        zba2.zba(this.zbd);
        return doRead(TaskApiCall.builder().setFeatures(zbar.zbe).run(new zbab(this, zba2.build())).setAutoResolveMissingFeatures(false).setMethodKey(1536).build());
    }

    public zbaf(Context context, zbf zbf) {
        super(context, zbc, zbf, GoogleApi.Settings.DEFAULT_SETTINGS);
    }
}
