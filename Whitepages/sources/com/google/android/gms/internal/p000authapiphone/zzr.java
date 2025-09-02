package com.google.android.gms.internal.p000authapiphone;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.auth.api.phone.SmsCodeAutofillClient;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;

/* renamed from: com.google.android.gms.internal.auth-api-phone.zzr  reason: invalid package */
public final class zzr extends GoogleApi implements SmsCodeAutofillClient {
    private static final Api.ClientKey zza;
    private static final Api.AbstractClientBuilder zzb;
    private static final Api zzc;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        zza = clientKey;
        zzn zzn = new zzn();
        zzb = zzn;
        zzc = new Api("SmsCodeAutofill.API", zzn, clientKey);
    }

    public zzr(Activity activity) {
        super(activity, zzc, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public final Task<Integer> checkPermissionState() {
        return doRead(TaskApiCall.builder().setFeatures(zzac.zza).run(new zzk(this)).setMethodKey(1564).build());
    }

    public final Task<Boolean> hasOngoingSmsRequest(String str) {
        Preconditions.checkNotNull(str);
        Preconditions.checkArgument(!str.isEmpty(), "The package name cannot be empty.");
        return doRead(TaskApiCall.builder().setFeatures(zzac.zza).run(new zzl(this, str)).setMethodKey(1565).build());
    }

    public final Task<Void> startSmsCodeRetriever() {
        return doWrite(TaskApiCall.builder().setFeatures(zzac.zza).run(new zzm(this)).setMethodKey(1563).build());
    }

    public zzr(Context context) {
        super(context, zzc, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }
}
