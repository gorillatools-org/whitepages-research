package com.google.android.gms.auth;

public class UserRecoverableNotifiedException extends GoogleAuthException {
    public UserRecoverableNotifiedException(String str) {
        super(str);
    }

    public UserRecoverableNotifiedException(String str, Throwable th) {
        super(str, th);
    }
}
