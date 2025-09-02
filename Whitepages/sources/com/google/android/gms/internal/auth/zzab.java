package com.google.android.gms.internal.auth;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.auth.AccountChangeEventsRequest;
import com.google.android.gms.auth.zzd;
import com.google.android.gms.auth.zze;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzab extends GoogleApi implements zzg {
    private static final Api.ClientKey zza;
    private static final Api.AbstractClientBuilder zzb;
    private static final Api zzc;
    private static final Logger zzd = zzd.zza("GoogleAuthServiceClient");
    private final Context zze;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        zza = clientKey;
        zzv zzv = new zzv();
        zzb = zzv;
        zzc = new Api("GoogleAuthService.API", zzv, clientKey);
    }

    zzab(Context context) {
        super(context, zzc, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
        this.zze = context;
    }

    static /* bridge */ /* synthetic */ void zzf(Status status, Object obj, TaskCompletionSource taskCompletionSource) {
        if (!TaskUtil.trySetResultOrApiException(status, obj, taskCompletionSource)) {
            zzd.w("The task is already complete.", new Object[0]);
        }
    }

    public final Task zza(zzbw zzbw) {
        return doWrite(TaskApiCall.builder().setFeatures(zze.zzl).run(new zzt(this, zzbw)).setMethodKey(1513).build());
    }

    public final Task zzb(AccountChangeEventsRequest accountChangeEventsRequest) {
        Preconditions.checkNotNull(accountChangeEventsRequest, "request cannot be null.");
        return doWrite(TaskApiCall.builder().setFeatures(zze.zzk).run(new zzu(this, accountChangeEventsRequest)).setMethodKey(1515).build());
    }

    public final Task zzc(Account account, String str, Bundle bundle) {
        Preconditions.checkNotNull(account, "Account name cannot be null!");
        Preconditions.checkNotEmpty(str, "Scope cannot be null!");
        return doWrite(TaskApiCall.builder().setFeatures(zze.zzl).run(new zzs(this, account, str, bundle)).setMethodKey(1512).build());
    }

    public final Task zzd(Account account) {
        Preconditions.checkNotNull(account, "account cannot be null.");
        return doWrite(TaskApiCall.builder().setFeatures(zze.zzk).run(new zzr(this, account)).setMethodKey(1517).build());
    }

    public final Task zze(String str) {
        Preconditions.checkNotNull(str, "Client package name cannot be null!");
        return doWrite(TaskApiCall.builder().setFeatures(zze.zzk).run(new zzq(this, str)).setMethodKey(1514).build());
    }
}
