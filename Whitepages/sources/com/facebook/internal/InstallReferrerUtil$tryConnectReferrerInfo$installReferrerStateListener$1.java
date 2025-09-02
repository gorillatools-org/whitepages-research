package com.facebook.internal;

import android.os.RemoteException;
import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import com.facebook.internal.InstallReferrerUtil;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class InstallReferrerUtil$tryConnectReferrerInfo$installReferrerStateListener$1 implements InstallReferrerStateListener {
    final /* synthetic */ InstallReferrerUtil.Callback $callback;
    final /* synthetic */ InstallReferrerClient $referrerClient;

    public void onInstallReferrerServiceDisconnected() {
    }

    InstallReferrerUtil$tryConnectReferrerInfo$installReferrerStateListener$1(InstallReferrerClient installReferrerClient, InstallReferrerUtil.Callback callback) {
        this.$referrerClient = installReferrerClient;
        this.$callback = callback;
    }

    public void onInstallReferrerSetupFinished(int i) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            if (i == 0) {
                try {
                    ReferrerDetails installReferrer = this.$referrerClient.getInstallReferrer();
                    Intrinsics.checkNotNullExpressionValue(installReferrer, "{\n                      …rer\n                    }");
                    String installReferrer2 = installReferrer.getInstallReferrer();
                    if (installReferrer2 != null && (StringsKt.contains$default((CharSequence) installReferrer2, (CharSequence) "fb", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) installReferrer2, (CharSequence) "facebook", false, 2, (Object) null))) {
                        this.$callback.onReceiveReferrerUrl(installReferrer2);
                    }
                    InstallReferrerUtil.INSTANCE.updateReferrer();
                } catch (RemoteException unused) {
                    return;
                }
            } else if (i == 2) {
                try {
                    InstallReferrerUtil.INSTANCE.updateReferrer();
                } catch (Throwable th) {
                    CrashShieldHandler.handleThrowable(th, this);
                    return;
                }
            }
            try {
                this.$referrerClient.endConnection();
            } catch (Exception unused2) {
            }
        }
    }
}
