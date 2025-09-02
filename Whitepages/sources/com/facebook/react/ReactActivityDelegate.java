package com.facebook.react;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.react.modules.core.PermissionListener;
import com.facebook.systrace.Systrace;

public class ReactActivityDelegate {
    private final Activity mActivity;
    private final String mMainComponentName;
    private PermissionListener mPermissionListener;
    private Callback mPermissionsCallback;
    private ReactDelegate mReactDelegate;

    /* access modifiers changed from: protected */
    public ReactRootView createRootView() {
        return null;
    }

    /* access modifiers changed from: protected */
    public Bundle getLaunchOptions() {
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean isWideColorGamutEnabled() {
        return false;
    }

    @Deprecated
    public ReactActivityDelegate(Activity activity, String str) {
        this.mActivity = activity;
        this.mMainComponentName = str;
    }

    public ReactActivityDelegate(ReactActivity reactActivity, String str) {
        this.mActivity = reactActivity;
        this.mMainComponentName = str;
    }

    /* access modifiers changed from: protected */
    public Bundle composeLaunchOptions() {
        return getLaunchOptions();
    }

    /* access modifiers changed from: protected */
    public ReactNativeHost getReactNativeHost() {
        return ((ReactApplication) getPlainActivity().getApplication()).getReactNativeHost();
    }

    public ReactHost getReactHost() {
        return ((ReactApplication) getPlainActivity().getApplication()).getReactHost();
    }

    /* access modifiers changed from: protected */
    public ReactDelegate getReactDelegate() {
        return this.mReactDelegate;
    }

    public ReactInstanceManager getReactInstanceManager() {
        return this.mReactDelegate.getReactInstanceManager();
    }

    public String getMainComponentName() {
        return this.mMainComponentName;
    }

    public void onCreate(Bundle bundle) {
        Systrace.traceSection(0, "ReactActivityDelegate.onCreate::init", new ReactActivityDelegate$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0() {
        String mainComponentName = getMainComponentName();
        Bundle composeLaunchOptions = composeLaunchOptions();
        if (isWideColorGamutEnabled()) {
            this.mActivity.getWindow().setColorMode(1);
        }
        if (ReactNativeFeatureFlags.enableBridgelessArchitecture()) {
            this.mReactDelegate = new ReactDelegate(getPlainActivity(), getReactHost(), mainComponentName, composeLaunchOptions);
        } else {
            this.mReactDelegate = new ReactDelegate(getPlainActivity(), getReactNativeHost(), mainComponentName, composeLaunchOptions, isFabricEnabled()) {
                /* access modifiers changed from: protected */
                public ReactRootView createRootView() {
                    ReactRootView createRootView = ReactActivityDelegate.this.createRootView();
                    return createRootView == null ? super.createRootView() : createRootView;
                }
            };
        }
        if (mainComponentName != null) {
            loadApp(mainComponentName);
        }
    }

    /* access modifiers changed from: protected */
    public void loadApp(String str) {
        this.mReactDelegate.loadApp(str);
        getPlainActivity().setContentView(this.mReactDelegate.getReactRootView());
    }

    public void onUserLeaveHint() {
        ReactDelegate reactDelegate = this.mReactDelegate;
        if (reactDelegate != null) {
            reactDelegate.onUserLeaveHint();
        }
    }

    public void onPause() {
        this.mReactDelegate.onHostPause();
    }

    public void onResume() {
        this.mReactDelegate.onHostResume();
        Callback callback = this.mPermissionsCallback;
        if (callback != null) {
            callback.invoke(new Object[0]);
            this.mPermissionsCallback = null;
        }
    }

    public void onDestroy() {
        this.mReactDelegate.onHostDestroy();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        this.mReactDelegate.onActivityResult(i, i2, intent, true);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return this.mReactDelegate.onKeyDown(i, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return this.mReactDelegate.shouldShowDevMenuOrReload(i, keyEvent);
    }

    public boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        return this.mReactDelegate.onKeyLongPress(i);
    }

    public boolean onBackPressed() {
        return this.mReactDelegate.onBackPressed();
    }

    public boolean onNewIntent(Intent intent) {
        return this.mReactDelegate.onNewIntent(intent);
    }

    public void onWindowFocusChanged(boolean z) {
        this.mReactDelegate.onWindowFocusChanged(z);
    }

    public void onConfigurationChanged(Configuration configuration) {
        this.mReactDelegate.onConfigurationChanged(configuration);
    }

    public void requestPermissions(String[] strArr, int i, PermissionListener permissionListener) {
        this.mPermissionListener = permissionListener;
        getPlainActivity().requestPermissions(strArr, i);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.mPermissionsCallback = new ReactActivityDelegate$$ExternalSyntheticLambda1(this, i, strArr, iArr);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onRequestPermissionsResult$1(int i, String[] strArr, int[] iArr, Object[] objArr) {
        PermissionListener permissionListener = this.mPermissionListener;
        if (permissionListener != null && permissionListener.onRequestPermissionsResult(i, strArr, iArr)) {
            this.mPermissionListener = null;
        }
    }

    /* access modifiers changed from: protected */
    public Context getContext() {
        return (Context) Assertions.assertNotNull(this.mActivity);
    }

    /* access modifiers changed from: protected */
    public Activity getPlainActivity() {
        return (Activity) getContext();
    }

    /* access modifiers changed from: protected */
    public ReactActivity getReactActivity() {
        return (ReactActivity) getContext();
    }

    public ReactContext getCurrentReactContext() {
        return this.mReactDelegate.getCurrentReactContext();
    }

    /* access modifiers changed from: protected */
    public boolean isFabricEnabled() {
        return ReactNativeFeatureFlags.enableFabricRenderer();
    }
}
