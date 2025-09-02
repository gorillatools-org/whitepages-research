package com.facebook.appevents;

import android.preference.PreferenceManager;
import android.util.Log;
import com.facebook.FacebookSdk;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public final class AnalyticsUserIDStore {
    public static final AnalyticsUserIDStore INSTANCE = new AnalyticsUserIDStore();
    private static final String TAG = AnalyticsUserIDStore.class.getSimpleName();
    private static volatile boolean initialized;
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static String userID;

    private AnalyticsUserIDStore() {
    }

    public static final void initStore() {
        if (!initialized) {
            InternalAppEventsLogger.Companion.getAnalyticsExecutor().execute(new AnalyticsUserIDStore$$ExternalSyntheticLambda0());
        }
    }

    /* access modifiers changed from: private */
    public static final void initStore$lambda$0() {
        INSTANCE.initAndWait();
    }

    public static final String getUserID() {
        if (!initialized) {
            Log.w(TAG, "initStore should have been called before calling setUserID");
            INSTANCE.initAndWait();
        }
        ReentrantReadWriteLock reentrantReadWriteLock = lock;
        reentrantReadWriteLock.readLock().lock();
        try {
            String str = userID;
            reentrantReadWriteLock.readLock().unlock();
            return str;
        } catch (Throwable th) {
            lock.readLock().unlock();
            throw th;
        }
    }

    private final void initAndWait() {
        if (!initialized) {
            ReentrantReadWriteLock reentrantReadWriteLock = lock;
            reentrantReadWriteLock.writeLock().lock();
            try {
                if (initialized) {
                    reentrantReadWriteLock.writeLock().unlock();
                    return;
                }
                userID = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext()).getString("com.facebook.appevents.AnalyticsUserIDStore.userID", (String) null);
                initialized = true;
                reentrantReadWriteLock.writeLock().unlock();
            } catch (Throwable th) {
                lock.writeLock().unlock();
                throw th;
            }
        }
    }
}
