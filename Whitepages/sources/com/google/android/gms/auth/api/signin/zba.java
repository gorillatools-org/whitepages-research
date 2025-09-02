package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;

final class zba implements PendingResultUtil.ResultConverter {
    private zba() {
        throw null;
    }

    /* synthetic */ zba(zbb zbb) {
    }

    public final /* synthetic */ Object convert(Result result) {
        return ((GoogleSignInResult) result).getSignInAccount();
    }
}
