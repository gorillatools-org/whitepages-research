package com.google.firebase.messaging;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class WithinAppServiceConnection implements ServiceConnection {
    private WithinAppServiceBinder binder;
    private boolean connectionInProgress;
    private final Intent connectionIntent;
    private final Context context;
    private final Queue<BindRequest> intentQueue;
    private final ScheduledExecutorService scheduledExecutorService;

    static class BindRequest {
        final Intent intent;
        private final TaskCompletionSource<Void> taskCompletionSource = new TaskCompletionSource<>();

        BindRequest(Intent intent2) {
            this.intent = intent2;
        }

        /* access modifiers changed from: package-private */
        public void arrangeTimeout(ScheduledExecutorService scheduledExecutorService) {
            getTask().addOnCompleteListener((Executor) scheduledExecutorService, new WithinAppServiceConnection$BindRequest$$ExternalSyntheticLambda1(scheduledExecutorService.schedule(new WithinAppServiceConnection$BindRequest$$ExternalSyntheticLambda0(this), 20, TimeUnit.SECONDS)));
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$arrangeTimeout$0() {
            Log.w(Constants.TAG, "Service took too long to process intent: " + this.intent.getAction() + " finishing.");
            finish();
        }

        /* access modifiers changed from: package-private */
        public Task<Void> getTask() {
            return this.taskCompletionSource.getTask();
        }

        /* access modifiers changed from: package-private */
        public void finish() {
            this.taskCompletionSource.trySetResult(null);
        }
    }

    WithinAppServiceConnection(Context context2, String str) {
        this(context2, str, createScheduledThreadPoolExecutor());
    }

    @SuppressLint({"ThreadPoolCreation"})
    private static ScheduledThreadPoolExecutor createScheduledThreadPoolExecutor() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        scheduledThreadPoolExecutor.setKeepAliveTime(40, TimeUnit.SECONDS);
        scheduledThreadPoolExecutor.allowCoreThreadTimeOut(true);
        return scheduledThreadPoolExecutor;
    }

    WithinAppServiceConnection(Context context2, String str, ScheduledExecutorService scheduledExecutorService2) {
        this.intentQueue = new ArrayDeque();
        this.connectionInProgress = false;
        Context applicationContext = context2.getApplicationContext();
        this.context = applicationContext;
        this.connectionIntent = new Intent(str).setPackage(applicationContext.getPackageName());
        this.scheduledExecutorService = scheduledExecutorService2;
    }

    /* access modifiers changed from: package-private */
    public synchronized Task<Void> sendIntent(Intent intent) {
        BindRequest bindRequest;
        try {
            if (Log.isLoggable(Constants.TAG, 3)) {
                Log.d(Constants.TAG, "new intent queued in the bind-strategy delivery");
            }
            bindRequest = new BindRequest(intent);
            bindRequest.arrangeTimeout(this.scheduledExecutorService);
            this.intentQueue.add(bindRequest);
            flushQueue();
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return bindRequest.getTask();
    }

    private synchronized void flushQueue() {
        try {
            if (Log.isLoggable(Constants.TAG, 3)) {
                Log.d(Constants.TAG, "flush queue called");
            }
            while (!this.intentQueue.isEmpty()) {
                if (Log.isLoggable(Constants.TAG, 3)) {
                    Log.d(Constants.TAG, "found intent to be delivered");
                }
                WithinAppServiceBinder withinAppServiceBinder = this.binder;
                if (withinAppServiceBinder == null || !withinAppServiceBinder.isBinderAlive()) {
                    startConnectionIfNeeded();
                    return;
                }
                if (Log.isLoggable(Constants.TAG, 3)) {
                    Log.d(Constants.TAG, "binder is alive, sending the intent.");
                }
                this.binder.send(this.intentQueue.poll());
            }
        } finally {
            while (true) {
            }
        }
    }

    private void startConnectionIfNeeded() {
        if (Log.isLoggable(Constants.TAG, 3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("binder is dead. start connection? ");
            sb.append(!this.connectionInProgress);
            Log.d(Constants.TAG, sb.toString());
        }
        if (!this.connectionInProgress) {
            this.connectionInProgress = true;
            try {
                if (!ConnectionTracker.getInstance().bindService(this.context, this.connectionIntent, this, 65)) {
                    Log.e(Constants.TAG, "binding to the service failed");
                    this.connectionInProgress = false;
                    finishAllInQueue();
                }
            } catch (SecurityException e) {
                Log.e(Constants.TAG, "Exception while binding the service", e);
            }
        }
    }

    private void finishAllInQueue() {
        while (!this.intentQueue.isEmpty()) {
            this.intentQueue.poll().finish();
        }
    }

    public synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        try {
            if (Log.isLoggable(Constants.TAG, 3)) {
                Log.d(Constants.TAG, "onServiceConnected: " + componentName);
            }
            this.connectionInProgress = false;
            if (!(iBinder instanceof WithinAppServiceBinder)) {
                Log.e(Constants.TAG, "Invalid service connection: " + iBinder);
                finishAllInQueue();
                return;
            }
            this.binder = (WithinAppServiceBinder) iBinder;
            flushQueue();
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable(Constants.TAG, 3)) {
            Log.d(Constants.TAG, "onServiceDisconnected: " + componentName);
        }
        flushQueue();
    }
}
