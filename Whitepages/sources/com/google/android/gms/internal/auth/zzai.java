package com.google.android.gms.internal.auth;

import android.accounts.Account;
import com.google.android.gms.auth.account.WorkAccountApi;
import com.google.android.gms.common.api.Status;

final class zzai implements WorkAccountApi.AddAccountResult {
    private static final Account zza = new Account("DUMMY_NAME", "com.google");
    private final Status zzb;
    private final Account zzc;

    public zzai(Status status, Account account) {
        this.zzb = status;
        this.zzc = account == null ? zza : account;
    }

    public final Account getAccount() {
        return this.zzc;
    }

    public final Status getStatus() {
        return this.zzb;
    }
}
