package com.facebook.appevents.internal;

import android.os.Bundle;
import com.facebook.AccessToken;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.InternalAppEventsLogger;
import com.facebook.internal.Logger;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import com.salesforce.marketingcloud.sfmcsdk.components.http.NetworkManager;
import java.util.Arrays;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

public final class SessionLogger {
    private static final long[] INACTIVE_SECONDS_QUANTA = {300000, 900000, 1800000, 3600000, 21600000, 43200000, NetworkManager.MAX_SERVER_RETRY, 172800000, 259200000, 604800000, 1209600000, 1814400000, 2419200000L, 5184000000L, 7776000000L, 10368000000L, 12960000000L, 15552000000L, 31536000000L};
    public static final SessionLogger INSTANCE = new SessionLogger();
    private static final String TAG = SessionLogger.class.getCanonicalName();

    private SessionLogger() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x003e A[Catch:{ all -> 0x001c }] */
    /* JADX WARNING: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void logActivateApp(java.lang.String r2, com.facebook.appevents.internal.SourceApplicationInfo r3, java.lang.String r4, android.content.Context r5) {
        /*
            java.lang.Class<com.facebook.appevents.internal.SessionLogger> r0 = com.facebook.appevents.internal.SessionLogger.class
            boolean r1 = com.facebook.internal.instrument.crashshield.CrashShieldHandler.isObjectCrashing(r0)
            if (r1 == 0) goto L_0x0009
            return
        L_0x0009:
            java.lang.String r1 = "activityName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r1)     // Catch:{ all -> 0x001c }
            java.lang.String r1 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r1)     // Catch:{ all -> 0x001c }
            if (r3 == 0) goto L_0x001e
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x001c }
            if (r3 != 0) goto L_0x0020
            goto L_0x001e
        L_0x001c:
            r2 = move-exception
            goto L_0x0042
        L_0x001e:
            java.lang.String r3 = "Unclassified"
        L_0x0020:
            android.os.Bundle r5 = new android.os.Bundle     // Catch:{ all -> 0x001c }
            r5.<init>()     // Catch:{ all -> 0x001c }
            java.lang.String r1 = "fb_mobile_launch_source"
            r5.putString(r1, r3)     // Catch:{ all -> 0x001c }
            com.facebook.appevents.InternalAppEventsLogger$Companion r3 = com.facebook.appevents.InternalAppEventsLogger.Companion     // Catch:{ all -> 0x001c }
            r1 = 0
            com.facebook.appevents.InternalAppEventsLogger r2 = r3.createInstance(r2, r4, r1)     // Catch:{ all -> 0x001c }
            java.lang.String r4 = "fb_mobile_activate_app"
            r2.logEvent(r4, r5)     // Catch:{ all -> 0x001c }
            com.facebook.appevents.AppEventsLogger$FlushBehavior r3 = r3.getFlushBehavior()     // Catch:{ all -> 0x001c }
            com.facebook.appevents.AppEventsLogger$FlushBehavior r4 = com.facebook.appevents.AppEventsLogger.FlushBehavior.EXPLICIT_ONLY     // Catch:{ all -> 0x001c }
            if (r3 == r4) goto L_0x0041
            r2.flush()     // Catch:{ all -> 0x001c }
        L_0x0041:
            return
        L_0x0042:
            com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r2, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.internal.SessionLogger.logActivateApp(java.lang.String, com.facebook.appevents.internal.SourceApplicationInfo, java.lang.String, android.content.Context):void");
    }

    public static final void logDeactivateApp(String str, SessionInfo sessionInfo, String str2) {
        long j;
        String str3;
        Class<SessionLogger> cls = SessionLogger.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(str, "activityName");
                if (sessionInfo != null) {
                    Long diskRestoreTime = sessionInfo.getDiskRestoreTime();
                    long j2 = 0;
                    if (diskRestoreTime != null) {
                        j = diskRestoreTime.longValue();
                    } else {
                        Long sessionLastEventTime = sessionInfo.getSessionLastEventTime();
                        j = 0 - (sessionLastEventTime != null ? sessionLastEventTime.longValue() : 0);
                    }
                    if (j < 0) {
                        INSTANCE.logClockSkewEvent();
                        j = 0;
                    }
                    long sessionLength = sessionInfo.getSessionLength();
                    if (sessionLength < 0) {
                        INSTANCE.logClockSkewEvent();
                        sessionLength = 0;
                    }
                    Bundle bundle = new Bundle();
                    bundle.putInt("fb_mobile_app_interruptions", sessionInfo.getInterruptionCount());
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String format = String.format(Locale.ROOT, "session_quanta_%d", Arrays.copyOf(new Object[]{Integer.valueOf(getQuantaIndex(j))}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                    bundle.putString("fb_mobile_time_between_sessions", format);
                    SourceApplicationInfo sourceApplicationInfo = sessionInfo.getSourceApplicationInfo();
                    if (sourceApplicationInfo == null || (str3 = sourceApplicationInfo.toString()) == null) {
                        str3 = "Unclassified";
                    }
                    bundle.putString("fb_mobile_launch_source", str3);
                    Long sessionLastEventTime2 = sessionInfo.getSessionLastEventTime();
                    if (sessionLastEventTime2 != null) {
                        j2 = sessionLastEventTime2.longValue();
                    }
                    bundle.putLong("_logTime", j2 / ((long) 1000));
                    InternalAppEventsLogger.Companion.createInstance(str, str2, (AccessToken) null).logEvent("fb_mobile_deactivate_app", ((double) sessionLength) / ((double) 1000), bundle);
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    private final void logClockSkewEvent() {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                Logger.Companion companion = Logger.Companion;
                LoggingBehavior loggingBehavior = LoggingBehavior.APP_EVENTS;
                String str = TAG;
                Intrinsics.checkNotNull(str);
                companion.log(loggingBehavior, str, "Clock skew detected");
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public static final int getQuantaIndex(long j) {
        Class<SessionLogger> cls = SessionLogger.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return 0;
        }
        int i = 0;
        while (true) {
            try {
                long[] jArr = INACTIVE_SECONDS_QUANTA;
                if (i >= jArr.length || jArr[i] >= j) {
                    return i;
                }
                i++;
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
                return 0;
            }
        }
        return i;
    }
}
