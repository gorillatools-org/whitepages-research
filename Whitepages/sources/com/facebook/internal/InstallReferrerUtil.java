package com.facebook.internal;

import com.android.installreferrer.api.InstallReferrerClient;
import com.facebook.FacebookSdk;
import kotlin.jvm.internal.Intrinsics;

public final class InstallReferrerUtil {
    public static final InstallReferrerUtil INSTANCE = new InstallReferrerUtil();

    public interface Callback {
        void onReceiveReferrerUrl(String str);
    }

    private InstallReferrerUtil() {
    }

    public static final void tryUpdateReferrerInfo(Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        InstallReferrerUtil installReferrerUtil = INSTANCE;
        if (!installReferrerUtil.isUpdated()) {
            installReferrerUtil.tryConnectReferrerInfo(callback);
        }
    }

    private final void tryConnectReferrerInfo(Callback callback) {
        InstallReferrerClient build = InstallReferrerClient.newBuilder(FacebookSdk.getApplicationContext()).build();
        try {
            build.startConnection(new InstallReferrerUtil$tryConnectReferrerInfo$installReferrerStateListener$1(build, callback));
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: private */
    public final void updateReferrer() {
        FacebookSdk.getApplicationContext().getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).edit().putBoolean("is_referrer_updated", true).apply();
    }

    private final boolean isUpdated() {
        return FacebookSdk.getApplicationContext().getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).getBoolean("is_referrer_updated", false);
    }
}
