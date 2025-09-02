package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.Status;

final class zbf extends zba {
    final /* synthetic */ Context zba;
    final /* synthetic */ GoogleSignInOptions zbb;
    final /* synthetic */ zbg zbc;

    zbf(zbg zbg, Context context, GoogleSignInOptions googleSignInOptions) {
        this.zba = context;
        this.zbb = googleSignInOptions;
        this.zbc = zbg;
    }

    public final void zbd(GoogleSignInAccount googleSignInAccount, Status status) throws RemoteException {
        if (googleSignInAccount != null) {
            Context context = this.zba;
            zbn.zbc(context).zbe(this.zbb, googleSignInAccount);
        }
        this.zbc.setResult(new GoogleSignInResult(googleSignInAccount, status));
    }
}
