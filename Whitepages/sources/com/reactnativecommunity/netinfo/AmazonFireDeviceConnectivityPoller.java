package com.reactnativecommunity.netinfo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;

public class AmazonFireDeviceConnectivityPoller {
    /* access modifiers changed from: private */
    public final ConnectivityChangedCallback callback;
    /* access modifiers changed from: private */
    public final Runnable checker = new PollerTask();
    /* access modifiers changed from: private */
    public final Context context;
    /* access modifiers changed from: private */
    public Handler handler;
    /* access modifiers changed from: private */
    public boolean pollerRunning = false;
    private final Receiver receiver = new Receiver();

    public interface ConnectivityChangedCallback {
        void onAmazonFireDeviceConnectivityChanged(boolean z);
    }

    AmazonFireDeviceConnectivityPoller(Context context2, ConnectivityChangedCallback connectivityChangedCallback) {
        this.context = context2;
        this.callback = connectivityChangedCallback;
    }

    public void register() {
        if (isFireOsDevice()) {
            registerReceiver();
            startPoller();
        }
    }

    public void unregister() {
        if (isFireOsDevice()) {
            stopPoller();
            unregisterReceiver();
        }
    }

    private boolean isFireOsDevice() {
        if (Build.MANUFACTURER.equals("Amazon")) {
            String str = Build.MODEL;
            if (str.startsWith("AF") || str.startsWith("KF")) {
                return true;
            }
        }
        return false;
    }

    private void registerReceiver() {
        if (!this.receiver.registered) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.amazon.tv.networkmonitor.INTERNET_DOWN");
            intentFilter.addAction("com.amazon.tv.networkmonitor.INTERNET_UP");
            NetInfoUtils.compatRegisterReceiver(this.context, this.receiver, intentFilter, false);
            this.receiver.registered = true;
        }
    }

    private void startPoller() {
        if (!this.pollerRunning) {
            Handler handler2 = new Handler();
            this.handler = handler2;
            this.pollerRunning = true;
            handler2.post(this.checker);
        }
    }

    private void unregisterReceiver() {
        Receiver receiver2 = this.receiver;
        if (receiver2.registered) {
            this.context.unregisterReceiver(receiver2);
            this.receiver.registered = false;
        }
    }

    private void stopPoller() {
        if (this.pollerRunning) {
            this.pollerRunning = false;
            this.handler.removeCallbacksAndMessages((Object) null);
            this.handler = null;
        }
    }

    private class Receiver extends BroadcastReceiver {
        private Boolean lastIsConnected;
        boolean registered;

        private Receiver() {
            this.registered = false;
        }

        public void onReceive(Context context, Intent intent) {
            boolean z;
            String action = intent == null ? null : intent.getAction();
            if ("com.amazon.tv.networkmonitor.INTERNET_DOWN".equals(action)) {
                z = false;
            } else if ("com.amazon.tv.networkmonitor.INTERNET_UP".equals(action)) {
                z = true;
            } else {
                return;
            }
            Boolean bool = this.lastIsConnected;
            if (bool == null || bool.booleanValue() != z) {
                this.lastIsConnected = Boolean.valueOf(z);
                AmazonFireDeviceConnectivityPoller.this.callback.onAmazonFireDeviceConnectivityChanged(z);
            }
        }
    }

    private class PollerTask implements Runnable {
        private PollerTask() {
        }

        public void run() {
            if (AmazonFireDeviceConnectivityPoller.this.pollerRunning) {
                AmazonFireDeviceConnectivityPoller.this.context.sendBroadcast(new Intent("com.amazon.tv.networkmonitor.CONNECTIVITY_CHECK"));
                AmazonFireDeviceConnectivityPoller.this.handler.postDelayed(AmazonFireDeviceConnectivityPoller.this.checker, 10000);
            }
        }
    }
}
