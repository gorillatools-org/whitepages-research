package com.RNAppleAuthentication;

public interface SignInWithAppleCallback {
    void onSignInWithAppleCancel();

    void onSignInWithAppleFailure(Throwable th);

    void onSignInWithAppleSuccess(String str, String str2, String str3, String str4);
}
