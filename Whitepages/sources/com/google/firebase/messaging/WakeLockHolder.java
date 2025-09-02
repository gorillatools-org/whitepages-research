package com.google.firebase.messaging;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.stats.WakeLock;
import java.util.concurrent.TimeUnit;

final class WakeLockHolder {
    private static final String EXTRA_WAKEFUL_INTENT = "com.google.firebase.iid.WakeLockHolder.wakefulintent";
    static final long WAKE_LOCK_ACQUIRE_TIMEOUT_MILLIS = TimeUnit.MINUTES.toMillis(1);
    private static final Object syncObject = new Object();
    private static WakeLock wakeLock;

    WakeLockHolder() {
    }

    private static void checkAndInitWakeLock(Context context) {
        if (wakeLock == null) {
            WakeLock wakeLock2 = new WakeLock(context, 1, "wake:com.google.firebase.iid.WakeLockHolder");
            wakeLock = wakeLock2;
            wakeLock2.setReferenceCounted(true);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0023, code lost:
        return r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static android.content.ComponentName startWakefulService(android.content.Context r3, android.content.Intent r4) {
        /*
            java.lang.Object r0 = syncObject
            monitor-enter(r0)
            checkAndInitWakeLock(r3)     // Catch:{ all -> 0x0017 }
            boolean r1 = isWakefulIntent(r4)     // Catch:{ all -> 0x0017 }
            r2 = 1
            setAsWakefulIntent(r4, r2)     // Catch:{ all -> 0x0017 }
            android.content.ComponentName r3 = r3.startService(r4)     // Catch:{ all -> 0x0017 }
            if (r3 != 0) goto L_0x0019
            monitor-exit(r0)     // Catch:{ all -> 0x0017 }
            r3 = 0
            return r3
        L_0x0017:
            r3 = move-exception
            goto L_0x0024
        L_0x0019:
            if (r1 != 0) goto L_0x0022
            com.google.android.gms.stats.WakeLock r4 = wakeLock     // Catch:{ all -> 0x0017 }
            long r1 = WAKE_LOCK_ACQUIRE_TIMEOUT_MILLIS     // Catch:{ all -> 0x0017 }
            r4.acquire(r1)     // Catch:{ all -> 0x0017 }
        L_0x0022:
            monitor-exit(r0)     // Catch:{ all -> 0x0017 }
            return r3
        L_0x0024:
            monitor-exit(r0)     // Catch:{ all -> 0x0017 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.WakeLockHolder.startWakefulService(android.content.Context, android.content.Intent):android.content.ComponentName");
    }

    @SuppressLint({"TaskMainThread"})
    static void sendWakefulServiceIntent(Context context, WithinAppServiceConnection withinAppServiceConnection, Intent intent) {
        synchronized (syncObject) {
            try {
                checkAndInitWakeLock(context);
                boolean isWakefulIntent = isWakefulIntent(intent);
                setAsWakefulIntent(intent, true);
                if (!isWakefulIntent) {
                    wakeLock.acquire(WAKE_LOCK_ACQUIRE_TIMEOUT_MILLIS);
                }
                withinAppServiceConnection.sendIntent(intent).addOnCompleteListener(new WakeLockHolder$$ExternalSyntheticLambda0(intent));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private static void setAsWakefulIntent(Intent intent, boolean z) {
        intent.putExtra(EXTRA_WAKEFUL_INTENT, z);
    }

    static boolean isWakefulIntent(Intent intent) {
        return intent.getBooleanExtra(EXTRA_WAKEFUL_INTENT, false);
    }

    /* access modifiers changed from: package-private */
    public static void completeWakefulIntent(Intent intent) {
        synchronized (syncObject) {
            try {
                if (wakeLock != null && isWakefulIntent(intent)) {
                    setAsWakefulIntent(intent, false);
                    wakeLock.release();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    static void acquireWakeLock(Intent intent, long j) {
        synchronized (syncObject) {
            try {
                if (wakeLock != null) {
                    setAsWakefulIntent(intent, true);
                    wakeLock.acquire(j);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    static void initWakeLock(Context context) {
        synchronized (syncObject) {
            checkAndInitWakeLock(context);
        }
    }

    static void reset() {
        synchronized (syncObject) {
            wakeLock = null;
        }
    }
}
