package com.facebook.login;

import com.facebook.login.LoginClient;

public final class LoginFragment$onCreateView$1 implements LoginClient.BackgroundProcessingListener {
    final /* synthetic */ LoginFragment this$0;

    LoginFragment$onCreateView$1(LoginFragment loginFragment) {
        this.this$0 = loginFragment;
    }

    public void onBackgroundProcessingStarted() {
        this.this$0.showSpinner();
    }

    public void onBackgroundProcessingStopped() {
        this.this$0.hideSpinner();
    }
}
