package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

@Deprecated
public class GoogleSignInResult implements Result {
    private final Status zba;
    private final GoogleSignInAccount zbb;

    public GoogleSignInResult(GoogleSignInAccount googleSignInAccount, Status status) {
        this.zbb = googleSignInAccount;
        this.zba = status;
    }

    public GoogleSignInAccount getSignInAccount() {
        return this.zbb;
    }

    public Status getStatus() {
        return this.zba;
    }

    public boolean isSuccess() {
        return this.zba.isSuccess();
    }
}
