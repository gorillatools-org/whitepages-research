package com.reactnativecommunity.netinfo;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.reactnativecommunity.netinfo.AmazonFireDeviceConnectivityPoller;

@ReactModule(name = "RNCNetInfo")
public class NetInfoModule extends ReactContextBaseJavaModule implements AmazonFireDeviceConnectivityPoller.ConnectivityChangedCallback {
    public static final String NAME = "RNCNetInfo";
    private final AmazonFireDeviceConnectivityPoller mAmazonConnectivityChecker;
    private final ConnectivityReceiver mConnectivityReceiver;
    private int numberOfListeners = 0;

    public NetInfoModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mConnectivityReceiver = new NetworkCallbackConnectivityReceiver(reactApplicationContext);
        this.mAmazonConnectivityChecker = new AmazonFireDeviceConnectivityPoller(reactApplicationContext, this);
    }

    public void initialize() {
        this.mConnectivityReceiver.register();
        this.mAmazonConnectivityChecker.register();
    }

    public void onCatalystInstanceDestroy() {
        invalidate();
    }

    public void invalidate() {
        this.mAmazonConnectivityChecker.unregister();
        this.mConnectivityReceiver.unregister();
        this.mConnectivityReceiver.hasListener = false;
    }

    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void getCurrentState(String str, Promise promise) {
        this.mConnectivityReceiver.getCurrentState(str, promise);
    }

    public void onAmazonFireDeviceConnectivityChanged(boolean z) {
        this.mConnectivityReceiver.setIsInternetReachableOverride(z);
    }

    @ReactMethod
    public void addListener(String str) {
        this.numberOfListeners++;
        this.mConnectivityReceiver.hasListener = true;
    }

    @ReactMethod
    public void removeListeners(Integer num) {
        int intValue = this.numberOfListeners - num.intValue();
        this.numberOfListeners = intValue;
        if (intValue == 0) {
            this.mConnectivityReceiver.hasListener = false;
        }
    }
}
