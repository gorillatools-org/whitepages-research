package com.facebook.internal.instrument.anrreport;

import android.app.ActivityManager;
import android.os.Looper;
import android.os.Process;
import com.facebook.FacebookSdk;
import com.facebook.internal.instrument.InstrumentData;
import com.facebook.internal.instrument.InstrumentUtility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.Intrinsics;

public final class ANRDetector {
    public static final ANRDetector INSTANCE = new ANRDetector();
    private static final Runnable anrDetectorRunnable = new ANRDetector$$ExternalSyntheticLambda0();
    private static final int myUid = Process.myUid();
    private static String previousStackTrace = "";
    private static final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    private ANRDetector() {
    }

    /* access modifiers changed from: private */
    public static final void anrDetectorRunnable$lambda$0() {
        Class<ANRDetector> cls = ANRDetector.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Object systemService = FacebookSdk.getApplicationContext().getSystemService("activity");
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
                checkProcessError((ActivityManager) systemService);
            } catch (Exception unused) {
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final void checkProcessError(ActivityManager activityManager) {
        Class<ANRDetector> cls = ANRDetector.class;
        if (!CrashShieldHandler.isObjectCrashing(cls) && activityManager != null) {
            try {
                List<ActivityManager.ProcessErrorStateInfo> processesInErrorState = activityManager.getProcessesInErrorState();
                if (processesInErrorState != null) {
                    for (ActivityManager.ProcessErrorStateInfo processErrorStateInfo : processesInErrorState) {
                        if (processErrorStateInfo.condition == 2 && processErrorStateInfo.uid == myUid) {
                            Thread thread = Looper.getMainLooper().getThread();
                            Intrinsics.checkNotNullExpressionValue(thread, "getMainLooper().thread");
                            String stackTrace = InstrumentUtility.getStackTrace(thread);
                            if (!Intrinsics.areEqual((Object) stackTrace, (Object) previousStackTrace)) {
                                if (InstrumentUtility.isSDKRelatedThread(thread)) {
                                    previousStackTrace = stackTrace;
                                    InstrumentData.Builder.build(processErrorStateInfo.shortMsg, stackTrace).save();
                                }
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final void start() {
        Class<ANRDetector> cls = ANRDetector.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                scheduledExecutorService.scheduleWithFixedDelay(anrDetectorRunnable, 0, 500, TimeUnit.MILLISECONDS);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }
}
