package com.google.android.gms.auth.account;

import com.google.android.gms.auth.account.WorkAccountApi;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;

final class zzg implements PendingResultUtil.ResultConverter {
    zzg(WorkAccountClient workAccountClient) {
    }

    public final /* synthetic */ Object convert(Result result) {
        return ((WorkAccountApi.AddAccountResult) result).getAccount();
    }
}
