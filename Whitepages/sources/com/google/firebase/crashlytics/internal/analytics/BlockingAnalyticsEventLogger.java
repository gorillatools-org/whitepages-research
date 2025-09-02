package com.google.firebase.crashlytics.internal.analytics;

import android.os.Bundle;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class BlockingAnalyticsEventLogger implements AnalyticsEventReceiver, AnalyticsEventLogger {
    static final String APP_EXCEPTION_EVENT_NAME = "_ae";
    private final CrashlyticsOriginAnalyticsEventLogger baseAnalyticsEventLogger;
    private boolean callbackReceived = false;
    private CountDownLatch eventLatch;
    private final Object latchLock = new Object();
    private final TimeUnit timeUnit;
    private final int timeout;

    public BlockingAnalyticsEventLogger(CrashlyticsOriginAnalyticsEventLogger crashlyticsOriginAnalyticsEventLogger, int i, TimeUnit timeUnit2) {
        this.baseAnalyticsEventLogger = crashlyticsOriginAnalyticsEventLogger;
        this.timeout = i;
        this.timeUnit = timeUnit2;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:11|12) */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        com.google.firebase.crashlytics.internal.Logger.getLogger().e("Interrupted while awaiting app exception callback from Analytics listener.");
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0061 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void logEvent(java.lang.String r6, android.os.Bundle r7) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.latchLock
            monitor-enter(r0)
            com.google.firebase.crashlytics.internal.Logger r1 = com.google.firebase.crashlytics.internal.Logger.getLogger()     // Catch:{ all -> 0x0055 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0055 }
            r2.<init>()     // Catch:{ all -> 0x0055 }
            java.lang.String r3 = "Logging event "
            r2.append(r3)     // Catch:{ all -> 0x0055 }
            r2.append(r6)     // Catch:{ all -> 0x0055 }
            java.lang.String r3 = " to Firebase Analytics with params "
            r2.append(r3)     // Catch:{ all -> 0x0055 }
            r2.append(r7)     // Catch:{ all -> 0x0055 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0055 }
            r1.v(r2)     // Catch:{ all -> 0x0055 }
            java.util.concurrent.CountDownLatch r1 = new java.util.concurrent.CountDownLatch     // Catch:{ all -> 0x0055 }
            r2 = 1
            r1.<init>(r2)     // Catch:{ all -> 0x0055 }
            r5.eventLatch = r1     // Catch:{ all -> 0x0055 }
            r1 = 0
            r5.callbackReceived = r1     // Catch:{ all -> 0x0055 }
            com.google.firebase.crashlytics.internal.analytics.CrashlyticsOriginAnalyticsEventLogger r1 = r5.baseAnalyticsEventLogger     // Catch:{ all -> 0x0055 }
            r1.logEvent(r6, r7)     // Catch:{ all -> 0x0055 }
            com.google.firebase.crashlytics.internal.Logger r6 = com.google.firebase.crashlytics.internal.Logger.getLogger()     // Catch:{ all -> 0x0055 }
            java.lang.String r7 = "Awaiting app exception callback from Analytics..."
            r6.v(r7)     // Catch:{ all -> 0x0055 }
            java.util.concurrent.CountDownLatch r6 = r5.eventLatch     // Catch:{ InterruptedException -> 0x0061 }
            int r7 = r5.timeout     // Catch:{ InterruptedException -> 0x0061 }
            long r3 = (long) r7     // Catch:{ InterruptedException -> 0x0061 }
            java.util.concurrent.TimeUnit r7 = r5.timeUnit     // Catch:{ InterruptedException -> 0x0061 }
            boolean r6 = r6.await(r3, r7)     // Catch:{ InterruptedException -> 0x0061 }
            if (r6 == 0) goto L_0x0057
            r5.callbackReceived = r2     // Catch:{ InterruptedException -> 0x0061 }
            com.google.firebase.crashlytics.internal.Logger r6 = com.google.firebase.crashlytics.internal.Logger.getLogger()     // Catch:{ InterruptedException -> 0x0061 }
            java.lang.String r7 = "App exception callback received from Analytics listener."
            r6.v(r7)     // Catch:{ InterruptedException -> 0x0061 }
            goto L_0x006a
        L_0x0055:
            r6 = move-exception
            goto L_0x006f
        L_0x0057:
            com.google.firebase.crashlytics.internal.Logger r6 = com.google.firebase.crashlytics.internal.Logger.getLogger()     // Catch:{ InterruptedException -> 0x0061 }
            java.lang.String r7 = "Timeout exceeded while awaiting app exception callback from Analytics listener."
            r6.w(r7)     // Catch:{ InterruptedException -> 0x0061 }
            goto L_0x006a
        L_0x0061:
            com.google.firebase.crashlytics.internal.Logger r6 = com.google.firebase.crashlytics.internal.Logger.getLogger()     // Catch:{ all -> 0x0055 }
            java.lang.String r7 = "Interrupted while awaiting app exception callback from Analytics listener."
            r6.e(r7)     // Catch:{ all -> 0x0055 }
        L_0x006a:
            r6 = 0
            r5.eventLatch = r6     // Catch:{ all -> 0x0055 }
            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            return
        L_0x006f:
            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.crashlytics.internal.analytics.BlockingAnalyticsEventLogger.logEvent(java.lang.String, android.os.Bundle):void");
    }

    public void onEvent(String str, Bundle bundle) {
        CountDownLatch countDownLatch = this.eventLatch;
        if (countDownLatch != null && APP_EXCEPTION_EVENT_NAME.equals(str)) {
            countDownLatch.countDown();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isCallbackReceived() {
        return this.callbackReceived;
    }
}
