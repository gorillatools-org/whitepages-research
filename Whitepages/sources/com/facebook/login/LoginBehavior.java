package com.facebook.login;

public enum LoginBehavior {
    NATIVE_WITH_FALLBACK(true, true, true, false, true, true, true),
    NATIVE_ONLY(true, true, false, false, false, true, true),
    KATANA_ONLY(false, true, false, false, false, false, false),
    WEB_ONLY(false, false, true, false, true, false, false),
    DIALOG_ONLY(false, true, true, false, true, true, true),
    DEVICE_AUTH(false, false, false, true, false, false, false);
    
    private final boolean allowsCustomTabAuth;
    private final boolean allowsDeviceAuth;
    private final boolean allowsFacebookLiteAuth;
    private final boolean allowsGetTokenAuth;
    private final boolean allowsInstagramAppAuth;
    private final boolean allowsKatanaAuth;
    private final boolean allowsWebViewAuth;

    private LoginBehavior(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        this.allowsGetTokenAuth = z;
        this.allowsKatanaAuth = z2;
        this.allowsWebViewAuth = z3;
        this.allowsDeviceAuth = z4;
        this.allowsCustomTabAuth = z5;
        this.allowsFacebookLiteAuth = z6;
        this.allowsInstagramAppAuth = z7;
    }

    public final boolean allowsGetTokenAuth() {
        return this.allowsGetTokenAuth;
    }

    public final boolean allowsKatanaAuth() {
        return this.allowsKatanaAuth;
    }

    public final boolean allowsWebViewAuth() {
        return this.allowsWebViewAuth;
    }

    public final boolean allowsDeviceAuth() {
        return this.allowsDeviceAuth;
    }

    public final boolean allowsCustomTabAuth() {
        return this.allowsCustomTabAuth;
    }

    public final boolean allowsInstagramAppAuth() {
        return this.allowsInstagramAppAuth;
    }
}
