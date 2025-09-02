package com.facebook.react;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.react.jstasks.HeadlessJsTaskConfig;
import com.facebook.react.jstasks.HeadlessJsTaskContext;
import com.facebook.react.jstasks.HeadlessJsTaskEventListener;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public abstract class HeadlessJsTaskService extends Service implements HeadlessJsTaskEventListener {
    private static PowerManager.WakeLock sWakeLock;
    /* access modifiers changed from: private */
    public final Set<Integer> mActiveTasks = new CopyOnWriteArraySet();

    /* access modifiers changed from: protected */
    public HeadlessJsTaskConfig getTaskConfig(Intent intent) {
        return null;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onHeadlessJsTaskStart(int i) {
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        HeadlessJsTaskConfig taskConfig = getTaskConfig(intent);
        if (taskConfig == null) {
            return 2;
        }
        startTask(taskConfig);
        return 3;
    }

    @SuppressLint({"WakelockTimeout"})
    public static void acquireWakeLockNow(Context context) {
        PowerManager.WakeLock wakeLock = sWakeLock;
        if (wakeLock == null || !wakeLock.isHeld()) {
            PowerManager.WakeLock newWakeLock = ((PowerManager) Assertions.assertNotNull((PowerManager) context.getSystemService("power"))).newWakeLock(1, HeadlessJsTaskService.class.getCanonicalName());
            sWakeLock = newWakeLock;
            newWakeLock.setReferenceCounted(false);
            sWakeLock.acquire();
        }
    }

    /* access modifiers changed from: protected */
    public void startTask(HeadlessJsTaskConfig headlessJsTaskConfig) {
        UiThreadUtil.assertOnUiThread();
        acquireWakeLockNow(this);
        ReactContext reactContext = getReactContext();
        if (reactContext == null) {
            createReactContextAndScheduleTask(headlessJsTaskConfig);
        } else {
            invokeStartTask(reactContext, headlessJsTaskConfig);
        }
    }

    /* access modifiers changed from: private */
    public void invokeStartTask(ReactContext reactContext, final HeadlessJsTaskConfig headlessJsTaskConfig) {
        final HeadlessJsTaskContext instance = HeadlessJsTaskContext.getInstance(reactContext);
        instance.addTaskEventListener(this);
        UiThreadUtil.runOnUiThread(new Runnable() {
            public void run() {
                HeadlessJsTaskService.this.mActiveTasks.add(Integer.valueOf(instance.startTask(headlessJsTaskConfig)));
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        ReactContext reactContext = getReactContext();
        if (reactContext != null) {
            HeadlessJsTaskContext.getInstance(reactContext).removeTaskEventListener(this);
        }
        PowerManager.WakeLock wakeLock = sWakeLock;
        if (wakeLock != null) {
            wakeLock.release();
        }
    }

    public void onHeadlessJsTaskFinish(int i) {
        this.mActiveTasks.remove(Integer.valueOf(i));
        if (this.mActiveTasks.size() == 0) {
            stopSelf();
        }
    }

    /* access modifiers changed from: protected */
    public ReactNativeHost getReactNativeHost() {
        return ((ReactApplication) getApplication()).getReactNativeHost();
    }

    /* access modifiers changed from: protected */
    public ReactHost getReactHost() {
        return ((ReactApplication) getApplication()).getReactHost();
    }

    /* access modifiers changed from: protected */
    public ReactContext getReactContext() {
        if (!ReactNativeFeatureFlags.enableBridgelessArchitecture()) {
            return getReactNativeHost().getReactInstanceManager().getCurrentReactContext();
        }
        ReactHost reactHost = getReactHost();
        Assertions.assertNotNull(reactHost, "getReactHost() is null in New Architecture");
        return reactHost.getCurrentReactContext();
    }

    private void createReactContextAndScheduleTask(final HeadlessJsTaskConfig headlessJsTaskConfig) {
        if (ReactNativeFeatureFlags.enableBridgelessArchitecture()) {
            final ReactHost reactHost = getReactHost();
            reactHost.addReactInstanceEventListener(new ReactInstanceEventListener() {
                public void onReactContextInitialized(ReactContext reactContext) {
                    HeadlessJsTaskService.this.invokeStartTask(reactContext, headlessJsTaskConfig);
                    reactHost.removeReactInstanceEventListener(this);
                }
            });
            reactHost.start();
            return;
        }
        final ReactInstanceManager reactInstanceManager = getReactNativeHost().getReactInstanceManager();
        reactInstanceManager.addReactInstanceEventListener(new ReactInstanceEventListener() {
            public void onReactContextInitialized(ReactContext reactContext) {
                HeadlessJsTaskService.this.invokeStartTask(reactContext, headlessJsTaskConfig);
                reactInstanceManager.removeReactInstanceEventListener(this);
            }
        });
        reactInstanceManager.createReactContextInBackground();
    }
}
