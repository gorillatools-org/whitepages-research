package com.salesforce.marketingcloud.sfmcsdk;

import android.os.Handler;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger;
import kotlin.jvm.internal.Intrinsics;

public final class WhenReadyHandler extends Handler {
    private final SFMCSdkReadyListener listener;

    public final SFMCSdkReadyListener getListener() {
        return this.listener;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public WhenReadyHandler(com.salesforce.marketingcloud.sfmcsdk.SFMCSdkReadyListener r2) {
        /*
            r1 = this;
            java.lang.String r0 = "listener"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            android.os.Looper r0 = android.os.Looper.myLooper()
            if (r0 != 0) goto L_0x000f
            android.os.Looper r0 = android.os.Looper.getMainLooper()
        L_0x000f:
            r1.<init>(r0)
            r1.listener = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.sfmcsdk.WhenReadyHandler.<init>(com.salesforce.marketingcloud.sfmcsdk.SFMCSdkReadyListener):void");
    }

    /* access modifiers changed from: private */
    /* renamed from: deliverSdk$lambda-0  reason: not valid java name */
    public static final void m810deliverSdk$lambda0(WhenReadyHandler whenReadyHandler, SFMCSdk sFMCSdk) {
        Intrinsics.checkNotNullParameter(whenReadyHandler, "this$0");
        Intrinsics.checkNotNullParameter(sFMCSdk, "$sdk");
        whenReadyHandler.execute(sFMCSdk, whenReadyHandler.listener);
    }

    public final void deliverSdk(SFMCSdk sFMCSdk) {
        Intrinsics.checkNotNullParameter(sFMCSdk, "sdk");
        post(new WhenReadyHandler$$ExternalSyntheticLambda0(this, sFMCSdk));
    }

    private final void execute(SFMCSdk sFMCSdk, SFMCSdkReadyListener sFMCSdkReadyListener) {
        try {
            sFMCSdkReadyListener.ready(sFMCSdk);
        } catch (Exception e) {
            SFMCSdkLogger.INSTANCE.e("~$WhenReadyHandler", e, new WhenReadyHandler$execute$1(sFMCSdkReadyListener));
        }
    }
}
