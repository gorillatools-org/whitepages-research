package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.settings.SettingsProvider;
import java.lang.Thread;
import java.util.concurrent.atomic.AtomicBoolean;

class CrashlyticsUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private final CrashListener crashListener;
    private final Thread.UncaughtExceptionHandler defaultHandler;
    private final AtomicBoolean isHandlingException = new AtomicBoolean(false);
    private final CrashlyticsNativeComponent nativeComponent;
    private final SettingsProvider settingsProvider;

    interface CrashListener {
        void onUncaughtException(SettingsProvider settingsProvider, Thread thread, Throwable th);
    }

    public CrashlyticsUncaughtExceptionHandler(CrashListener crashListener2, SettingsProvider settingsProvider2, Thread.UncaughtExceptionHandler uncaughtExceptionHandler, CrashlyticsNativeComponent crashlyticsNativeComponent) {
        this.crashListener = crashListener2;
        this.settingsProvider = settingsProvider2;
        this.defaultHandler = uncaughtExceptionHandler;
        this.nativeComponent = crashlyticsNativeComponent;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0028, code lost:
        if (r7.defaultHandler != null) goto L_0x002a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002a, code lost:
        com.google.firebase.crashlytics.internal.Logger.getLogger().d("Completed exception processing. Invoking default exception handler.");
        r7.defaultHandler.uncaughtException(r8, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0037, code lost:
        com.google.firebase.crashlytics.internal.Logger.getLogger().d("Completed exception processing, but no default exception handler.");
        java.lang.System.exit(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0041, code lost:
        r7.isHandlingException.set(false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0052, code lost:
        if (r7.defaultHandler == null) goto L_0x0037;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0055, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void uncaughtException(java.lang.Thread r8, java.lang.Throwable r9) {
        /*
            r7 = this;
            java.lang.String r0 = "Completed exception processing, but no default exception handler."
            java.lang.String r1 = "Completed exception processing. Invoking default exception handler."
            java.util.concurrent.atomic.AtomicBoolean r2 = r7.isHandlingException
            r3 = 1
            r2.set(r3)
            r2 = 0
            boolean r4 = r7.shouldRecordUncaughtException(r8, r9)     // Catch:{ Exception -> 0x001b }
            if (r4 == 0) goto L_0x001d
            com.google.firebase.crashlytics.internal.common.CrashlyticsUncaughtExceptionHandler$CrashListener r4 = r7.crashListener     // Catch:{ Exception -> 0x001b }
            com.google.firebase.crashlytics.internal.settings.SettingsProvider r5 = r7.settingsProvider     // Catch:{ Exception -> 0x001b }
            r4.onUncaughtException(r5, r8, r9)     // Catch:{ Exception -> 0x001b }
            goto L_0x0026
        L_0x0019:
            r4 = move-exception
            goto L_0x0056
        L_0x001b:
            r4 = move-exception
            goto L_0x0047
        L_0x001d:
            com.google.firebase.crashlytics.internal.Logger r4 = com.google.firebase.crashlytics.internal.Logger.getLogger()     // Catch:{ Exception -> 0x001b }
            java.lang.String r5 = "Uncaught exception will not be recorded by Crashlytics."
            r4.d(r5)     // Catch:{ Exception -> 0x001b }
        L_0x0026:
            java.lang.Thread$UncaughtExceptionHandler r4 = r7.defaultHandler
            if (r4 == 0) goto L_0x0037
        L_0x002a:
            com.google.firebase.crashlytics.internal.Logger r0 = com.google.firebase.crashlytics.internal.Logger.getLogger()
            r0.d(r1)
            java.lang.Thread$UncaughtExceptionHandler r0 = r7.defaultHandler
            r0.uncaughtException(r8, r9)
            goto L_0x0041
        L_0x0037:
            com.google.firebase.crashlytics.internal.Logger r8 = com.google.firebase.crashlytics.internal.Logger.getLogger()
            r8.d(r0)
            java.lang.System.exit(r3)
        L_0x0041:
            java.util.concurrent.atomic.AtomicBoolean r8 = r7.isHandlingException
            r8.set(r2)
            goto L_0x0055
        L_0x0047:
            com.google.firebase.crashlytics.internal.Logger r5 = com.google.firebase.crashlytics.internal.Logger.getLogger()     // Catch:{ all -> 0x0019 }
            java.lang.String r6 = "An error occurred in the uncaught exception handler"
            r5.e(r6, r4)     // Catch:{ all -> 0x0019 }
            java.lang.Thread$UncaughtExceptionHandler r4 = r7.defaultHandler
            if (r4 == 0) goto L_0x0037
            goto L_0x002a
        L_0x0055:
            return
        L_0x0056:
            java.lang.Thread$UncaughtExceptionHandler r5 = r7.defaultHandler
            if (r5 == 0) goto L_0x0067
            com.google.firebase.crashlytics.internal.Logger r0 = com.google.firebase.crashlytics.internal.Logger.getLogger()
            r0.d(r1)
            java.lang.Thread$UncaughtExceptionHandler r0 = r7.defaultHandler
            r0.uncaughtException(r8, r9)
            goto L_0x0071
        L_0x0067:
            com.google.firebase.crashlytics.internal.Logger r8 = com.google.firebase.crashlytics.internal.Logger.getLogger()
            r8.d(r0)
            java.lang.System.exit(r3)
        L_0x0071:
            java.util.concurrent.atomic.AtomicBoolean r8 = r7.isHandlingException
            r8.set(r2)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.crashlytics.internal.common.CrashlyticsUncaughtExceptionHandler.uncaughtException(java.lang.Thread, java.lang.Throwable):void");
    }

    /* access modifiers changed from: package-private */
    public boolean isHandlingException() {
        return this.isHandlingException.get();
    }

    private boolean shouldRecordUncaughtException(Thread thread, Throwable th) {
        if (thread == null) {
            Logger.getLogger().e("Crashlytics will not record uncaught exception; null thread");
            return false;
        } else if (th == null) {
            Logger.getLogger().e("Crashlytics will not record uncaught exception; null throwable");
            return false;
        } else if (!this.nativeComponent.hasCrashDataForCurrentSession()) {
            return true;
        } else {
            Logger.getLogger().d("Crashlytics will not record uncaught exception; native crash exists for session.");
            return false;
        }
    }
}
