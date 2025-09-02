package com.google.firebase.messaging;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.PowerManager;
import android.util.Log;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class SyncTask implements Runnable {
    /* access modifiers changed from: private */
    public final FirebaseMessaging firebaseMessaging;
    private final long nextDelaySeconds;
    @SuppressLint({"ThreadPoolCreation"})
    ExecutorService processorExecutor = new ThreadPoolExecutor(0, 1, 30, TimeUnit.SECONDS, new LinkedBlockingQueue(), new NamedThreadFactory("firebase-iid-executor"));
    private final PowerManager.WakeLock syncWakeLock;

    @SuppressLint({"InvalidWakeLockTag"})
    public SyncTask(FirebaseMessaging firebaseMessaging2, long j) {
        this.firebaseMessaging = firebaseMessaging2;
        this.nextDelaySeconds = j;
        PowerManager.WakeLock newWakeLock = ((PowerManager) getContext().getSystemService("power")).newWakeLock(1, "fiid-sync");
        this.syncWakeLock = newWakeLock;
        newWakeLock.setReferenceCounted(false);
    }

    @SuppressLint({"WakelockTimeout"})
    public void run() {
        if (ServiceStarter.getInstance().hasWakeLockPermission(getContext())) {
            this.syncWakeLock.acquire();
        }
        try {
            this.firebaseMessaging.setSyncScheduledOrRunning(true);
            if (!this.firebaseMessaging.isGmsCorePresent()) {
                this.firebaseMessaging.setSyncScheduledOrRunning(false);
                if (ServiceStarter.getInstance().hasWakeLockPermission(getContext())) {
                    this.syncWakeLock.release();
                }
            } else if (!ServiceStarter.getInstance().hasAccessNetworkStatePermission(getContext()) || isDeviceConnected()) {
                if (maybeRefreshToken()) {
                    this.firebaseMessaging.setSyncScheduledOrRunning(false);
                } else {
                    this.firebaseMessaging.syncWithDelaySecondsInternal(this.nextDelaySeconds);
                }
                if (!ServiceStarter.getInstance().hasWakeLockPermission(getContext())) {
                    return;
                }
                this.syncWakeLock.release();
            } else {
                new ConnectivityChangeReceiver(this).registerReceiver();
                if (ServiceStarter.getInstance().hasWakeLockPermission(getContext())) {
                    this.syncWakeLock.release();
                }
            }
        } catch (IOException e) {
            Log.e(Constants.TAG, "Topic sync or token retrieval failed on hard failure exceptions: " + e.getMessage() + ". Won't retry the operation.");
            this.firebaseMessaging.setSyncScheduledOrRunning(false);
            if (!ServiceStarter.getInstance().hasWakeLockPermission(getContext())) {
            }
        } catch (Throwable th) {
            if (ServiceStarter.getInstance().hasWakeLockPermission(getContext())) {
                this.syncWakeLock.release();
            }
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean maybeRefreshToken() throws IOException {
        try {
            if (this.firebaseMessaging.blockingGetToken() == null) {
                Log.e(Constants.TAG, "Token retrieval failed: null");
                return false;
            } else if (!Log.isLoggable(Constants.TAG, 3)) {
                return true;
            } else {
                Log.d(Constants.TAG, "Token successfully retrieved");
                return true;
            }
        } catch (IOException e) {
            if (GmsRpc.isErrorMessageForRetryableError(e.getMessage())) {
                Log.w(Constants.TAG, "Token retrieval failed: " + e.getMessage() + ". Will retry token retrieval");
                return false;
            } else if (e.getMessage() == null) {
                Log.w(Constants.TAG, "Token retrieval failed without exception message. Will retry token retrieval");
                return false;
            } else {
                throw e;
            }
        } catch (SecurityException unused) {
            Log.w(Constants.TAG, "Token retrieval failed with SecurityException. Will retry token retrieval");
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public Context getContext() {
        return this.firebaseMessaging.getApplicationContext();
    }

    /* access modifiers changed from: package-private */
    public boolean isDeviceConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    static class ConnectivityChangeReceiver extends BroadcastReceiver {
        private Context receiverContext;
        private SyncTask task;

        public ConnectivityChangeReceiver(SyncTask syncTask) {
            this.task = syncTask;
        }

        public void registerReceiver() {
            if (SyncTask.isDebugLogEnabled()) {
                Log.d(Constants.TAG, "Connectivity change received registered");
            }
            IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
            SyncTask syncTask = this.task;
            if (syncTask != null) {
                Context context = syncTask.getContext();
                this.receiverContext = context;
                context.registerReceiver(this, intentFilter);
            }
        }

        public void onReceive(Context context, Intent intent) {
            SyncTask syncTask = this.task;
            if (syncTask != null && syncTask.isDeviceConnected()) {
                if (SyncTask.isDebugLogEnabled()) {
                    Log.d(Constants.TAG, "Connectivity changed. Starting background sync.");
                }
                this.task.firebaseMessaging.enqueueTaskWithDelaySeconds(this.task, 0);
                Context context2 = this.receiverContext;
                if (context2 != null) {
                    context2.unregisterReceiver(this);
                }
                this.task = null;
            }
        }
    }

    static boolean isDebugLogEnabled() {
        return Log.isLoggable(Constants.TAG, 3);
    }
}
