package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zbg extends zbl {
    final /* synthetic */ Context zba;
    final /* synthetic */ GoogleSignInOptions zbb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zbg(GoogleApiClient googleApiClient, Context context, GoogleSignInOptions googleSignInOptions) {
        super(googleApiClient);
        this.zba = context;
        this.zbb = googleSignInOptions;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result createFailedResult(Status status) {
        return new GoogleSignInResult((GoogleSignInAccount) null, status);
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zbs) ((zbe) anyClient).getService()).zbe(new zbf(this, this.zba, this.zbb), this.zbb);
    }
}
