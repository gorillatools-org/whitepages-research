package com.microsoft.codepush.react;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.ChoreographerCompat;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.core.ReactChoreographer;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CodePushNativeModule extends ReactContextBaseJavaModule {
    private boolean _allowed = true;
    private boolean _restartInProgress = false;
    private ArrayList<Boolean> _restartQueue = new ArrayList<>();
    private String mBinaryContentsHash = null;
    private String mClientUniqueId = null;
    /* access modifiers changed from: private */
    public CodePush mCodePush;
    /* access modifiers changed from: private */
    public LifecycleEventListener mLifecycleEventListener = null;
    /* access modifiers changed from: private */
    public int mMinimumBackgroundDuration = 0;
    /* access modifiers changed from: private */
    public SettingsManager mSettingsManager;
    /* access modifiers changed from: private */
    public CodePushTelemetryManager mTelemetryManager;
    /* access modifiers changed from: private */
    public CodePushUpdateManager mUpdateManager;

    @ReactMethod
    public void addListener(String str) {
    }

    @ReactMethod
    public void removeListeners(Integer num) {
    }

    public CodePushNativeModule(ReactApplicationContext reactApplicationContext, CodePush codePush, CodePushUpdateManager codePushUpdateManager, CodePushTelemetryManager codePushTelemetryManager, SettingsManager settingsManager) {
        super(reactApplicationContext);
        this.mCodePush = codePush;
        this.mSettingsManager = settingsManager;
        this.mTelemetryManager = codePushTelemetryManager;
        this.mUpdateManager = codePushUpdateManager;
        this.mBinaryContentsHash = CodePushUpdateUtils.getHashForBinaryContents(reactApplicationContext, codePush.isDebugMode());
        this.mClientUniqueId = Settings.Secure.getString(reactApplicationContext.getContentResolver(), "android_id");
    }

    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        hashMap.put("codePushInstallModeImmediate", Integer.valueOf(CodePushInstallMode.IMMEDIATE.getValue()));
        hashMap.put("codePushInstallModeOnNextRestart", Integer.valueOf(CodePushInstallMode.ON_NEXT_RESTART.getValue()));
        hashMap.put("codePushInstallModeOnNextResume", Integer.valueOf(CodePushInstallMode.ON_NEXT_RESUME.getValue()));
        hashMap.put("codePushInstallModeOnNextSuspend", Integer.valueOf(CodePushInstallMode.ON_NEXT_SUSPEND.getValue()));
        hashMap.put("codePushUpdateStateRunning", Integer.valueOf(CodePushUpdateState.RUNNING.getValue()));
        hashMap.put("codePushUpdateStatePending", Integer.valueOf(CodePushUpdateState.PENDING.getValue()));
        hashMap.put("codePushUpdateStateLatest", Integer.valueOf(CodePushUpdateState.LATEST.getValue()));
        return hashMap;
    }

    public String getName() {
        return "CodePush";
    }

    /* access modifiers changed from: private */
    public void loadBundleLegacy() {
        final Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            this.mCodePush.invalidateCurrentInstance();
            currentActivity.runOnUiThread(new Runnable() {
                public void run() {
                    currentActivity.recreate();
                }
            });
        }
    }

    private void setJSBundle(ReactInstanceManager reactInstanceManager, String str) throws IllegalAccessException {
        JSBundleLoader jSBundleLoader;
        try {
            if (str.toLowerCase().startsWith("assets://")) {
                jSBundleLoader = JSBundleLoader.createAssetLoader(getReactApplicationContext(), str, false);
            } else {
                jSBundleLoader = JSBundleLoader.createFileLoader(str);
            }
            Field declaredField = reactInstanceManager.getClass().getDeclaredField("mBundleLoader");
            declaredField.setAccessible(true);
            declaredField.set(reactInstanceManager, jSBundleLoader);
        } catch (Exception unused) {
            CodePushUtils.log("Unable to set JSBundle - CodePush may not support this version of React Native");
            throw new IllegalAccessException("Could not setJSBundle");
        }
    }

    private void loadBundle() {
        clearLifecycleEventListener();
        try {
            this.mCodePush.clearDebugCacheIfNeeded(resolveInstanceManager());
        } catch (Exception unused) {
            this.mCodePush.clearDebugCacheIfNeeded((ReactInstanceManager) null);
        }
        try {
            final ReactInstanceManager resolveInstanceManager = resolveInstanceManager();
            if (resolveInstanceManager != null) {
                CodePush codePush = this.mCodePush;
                setJSBundle(resolveInstanceManager, codePush.getJSBundleFileInternal(codePush.getAssetsBundleFileName()));
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        try {
                            resolveInstanceManager.recreateReactContextInBackground();
                            CodePushNativeModule.this.mCodePush.initializeUpdateAfterRestart();
                        } catch (Exception unused) {
                            CodePushNativeModule.this.loadBundleLegacy();
                        }
                    }
                });
            }
        } catch (Exception e) {
            CodePushUtils.log("Failed to load the bundle, falling back to restarting the Activity (if it exists). " + e.getMessage());
            loadBundleLegacy();
        }
    }

    private void resetReactRootViews(ReactInstanceManager reactInstanceManager) throws NoSuchFieldException, IllegalAccessException {
        Field declaredField = reactInstanceManager.getClass().getDeclaredField("mAttachedRootViews");
        declaredField.setAccessible(true);
        List<ReactRootView> list = (List) declaredField.get(reactInstanceManager);
        for (ReactRootView reactRootView : list) {
            reactRootView.removeAllViews();
            reactRootView.setId(-1);
        }
        declaredField.set(reactInstanceManager, list);
    }

    private void clearLifecycleEventListener() {
        if (this.mLifecycleEventListener != null) {
            getReactApplicationContext().removeLifecycleEventListener(this.mLifecycleEventListener);
            this.mLifecycleEventListener = null;
        }
    }

    private ReactInstanceManager resolveInstanceManager() throws NoSuchFieldException, IllegalAccessException {
        ReactInstanceManager reactInstanceManager = CodePush.getReactInstanceManager();
        if (reactInstanceManager != null) {
            return reactInstanceManager;
        }
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            return null;
        }
        return ((ReactApplication) currentActivity.getApplication()).getReactNativeHost().getReactInstanceManager();
    }

    /* access modifiers changed from: private */
    public void restartAppInternal(boolean z) {
        if (this._restartInProgress) {
            CodePushUtils.log("Restart request queued until the current restart is completed");
            this._restartQueue.add(Boolean.valueOf(z));
        } else if (!this._allowed) {
            CodePushUtils.log("Restart request queued until restarts are re-allowed");
            this._restartQueue.add(Boolean.valueOf(z));
        } else {
            this._restartInProgress = true;
            if (!z || this.mSettingsManager.isPendingUpdate((String) null)) {
                loadBundle();
                CodePushUtils.log("Restarting app");
                return;
            }
            this._restartInProgress = false;
            if (this._restartQueue.size() > 0) {
                boolean booleanValue = this._restartQueue.get(0).booleanValue();
                this._restartQueue.remove(0);
                restartAppInternal(booleanValue);
            }
        }
    }

    @ReactMethod
    public void allow(Promise promise) {
        CodePushUtils.log("Re-allowing restarts");
        this._allowed = true;
        if (this._restartQueue.size() > 0) {
            CodePushUtils.log("Executing pending restart");
            boolean booleanValue = this._restartQueue.get(0).booleanValue();
            this._restartQueue.remove(0);
            restartAppInternal(booleanValue);
        }
        promise.resolve((Object) null);
    }

    @ReactMethod
    public void clearPendingRestart(Promise promise) {
        this._restartQueue.clear();
        promise.resolve((Object) null);
    }

    @ReactMethod
    public void disallow(Promise promise) {
        CodePushUtils.log("Disallowing restarts");
        this._allowed = false;
        promise.resolve((Object) null);
    }

    @ReactMethod
    public void restartApp(boolean z, Promise promise) {
        try {
            restartAppInternal(z);
            promise.resolve((Object) null);
        } catch (CodePushUnknownException e) {
            CodePushUtils.log((Throwable) e);
            promise.reject((Throwable) e);
        }
    }

    @ReactMethod
    public void downloadUpdate(final ReadableMap readableMap, final boolean z, final Promise promise) {
        new AsyncTask() {
            /* access modifiers changed from: protected */
            public Void doInBackground(Void... voidArr) {
                try {
                    JSONObject convertReadableToJsonObject = CodePushUtils.convertReadableToJsonObject(readableMap);
                    CodePushUtils.setJSONValueForKey(convertReadableToJsonObject, "binaryModifiedTime", "" + CodePushNativeModule.this.mCodePush.getBinaryResourcesModifiedTime());
                    CodePushNativeModule.this.mUpdateManager.downloadPackage(convertReadableToJsonObject, CodePushNativeModule.this.mCodePush.getAssetsBundleFileName(), new DownloadProgressCallback() {
                        /* access modifiers changed from: private */
                        public boolean hasScheduledNextFrame = false;
                        /* access modifiers changed from: private */
                        public DownloadProgress latestDownloadProgress = null;

                        public void call(DownloadProgress downloadProgress) {
                            if (z) {
                                this.latestDownloadProgress = downloadProgress;
                                if (downloadProgress.isCompleted()) {
                                    dispatchDownloadProgressEvent();
                                } else if (!this.hasScheduledNextFrame) {
                                    this.hasScheduledNextFrame = true;
                                    CodePushNativeModule.this.getReactApplicationContext().runOnUiQueueThread(new Runnable() {
                                        public void run() {
                                            ReactChoreographer.getInstance().postFrameCallback(ReactChoreographer.CallbackType.TIMERS_EVENTS, new ChoreographerCompat.FrameCallback() {
                                                public void doFrame(long j) {
                                                    if (!AnonymousClass1.this.latestDownloadProgress.isCompleted()) {
                                                        AnonymousClass1.this.dispatchDownloadProgressEvent();
                                                    }
                                                    boolean unused = AnonymousClass1.this.hasScheduledNextFrame = false;
                                                }
                                            });
                                        }
                                    });
                                }
                            }
                        }

                        public void dispatchDownloadProgressEvent() {
                            ((DeviceEventManagerModule.RCTDeviceEventEmitter) CodePushNativeModule.this.getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("CodePushDownloadProgress", this.latestDownloadProgress.createWritableMap());
                        }
                    }, CodePushNativeModule.this.mCodePush.getPublicKey());
                    promise.resolve(CodePushUtils.convertJsonObjectToWritable(CodePushNativeModule.this.mUpdateManager.getPackage(CodePushUtils.tryGetString(readableMap, "packageHash"))));
                    return null;
                } catch (CodePushInvalidUpdateException e) {
                    CodePushUtils.log((Throwable) e);
                    CodePushNativeModule.this.mSettingsManager.saveFailedUpdate(CodePushUtils.convertReadableToJsonObject(readableMap));
                    promise.reject((Throwable) e);
                    return null;
                } catch (CodePushUnknownException | IOException e2) {
                    CodePushUtils.log(e2);
                    promise.reject(e2);
                    return null;
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void getConfiguration(Promise promise) {
        try {
            WritableMap createMap = Arguments.createMap();
            createMap.putString(RemoteConfigConstants.RequestFieldKey.APP_VERSION, this.mCodePush.getAppVersion());
            createMap.putString("clientUniqueId", this.mClientUniqueId);
            createMap.putString("deploymentKey", this.mCodePush.getDeploymentKey());
            createMap.putString("serverUrl", this.mCodePush.getServerUrl());
            String str = this.mBinaryContentsHash;
            if (str != null) {
                createMap.putString("packageHash", str);
            }
            promise.resolve(createMap);
        } catch (CodePushUnknownException e) {
            CodePushUtils.log((Throwable) e);
            promise.reject((Throwable) e);
        }
    }

    @ReactMethod
    public void getUpdateMetadata(final int i, final Promise promise) {
        new AsyncTask() {
            /* access modifiers changed from: protected */
            public Void doInBackground(Void... voidArr) {
                try {
                    JSONObject currentPackage = CodePushNativeModule.this.mUpdateManager.getCurrentPackage();
                    if (currentPackage == null) {
                        promise.resolve((Object) null);
                        return null;
                    }
                    Boolean bool = Boolean.FALSE;
                    if (currentPackage.has("packageHash")) {
                        bool = Boolean.valueOf(CodePushNativeModule.this.mSettingsManager.isPendingUpdate(currentPackage.optString("packageHash", (String) null)));
                    }
                    if (i == CodePushUpdateState.PENDING.getValue() && !bool.booleanValue()) {
                        promise.resolve((Object) null);
                        return null;
                    } else if (i != CodePushUpdateState.RUNNING.getValue() || !bool.booleanValue()) {
                        if (CodePushNativeModule.this.mCodePush.isRunningBinaryVersion()) {
                            CodePushUtils.setJSONValueForKey(currentPackage, "_isDebugOnly", Boolean.TRUE);
                        }
                        CodePushUtils.setJSONValueForKey(currentPackage, "isPending", bool);
                        promise.resolve(CodePushUtils.convertJsonObjectToWritable(currentPackage));
                        return null;
                    } else {
                        JSONObject previousPackage = CodePushNativeModule.this.mUpdateManager.getPreviousPackage();
                        if (previousPackage == null) {
                            promise.resolve((Object) null);
                            return null;
                        }
                        promise.resolve(CodePushUtils.convertJsonObjectToWritable(previousPackage));
                        return null;
                    }
                } catch (CodePushMalformedDataException e) {
                    CodePushUtils.log(e.getMessage());
                    CodePushNativeModule.this.clearUpdates();
                    promise.resolve((Object) null);
                } catch (CodePushUnknownException e2) {
                    CodePushUtils.log((Throwable) e2);
                    promise.reject((Throwable) e2);
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void getNewStatusReport(final Promise promise) {
        new AsyncTask() {
            /* access modifiers changed from: protected */
            public Void doInBackground(Void... voidArr) {
                WritableMap updateReport;
                try {
                    if (CodePushNativeModule.this.mCodePush.needToReportRollback()) {
                        CodePushNativeModule.this.mCodePush.setNeedToReportRollback(false);
                        JSONArray failedUpdates = CodePushNativeModule.this.mSettingsManager.getFailedUpdates();
                        if (failedUpdates != null && failedUpdates.length() > 0) {
                            WritableMap rollbackReport = CodePushNativeModule.this.mTelemetryManager.getRollbackReport(CodePushUtils.convertJsonObjectToWritable(failedUpdates.getJSONObject(failedUpdates.length() - 1)));
                            if (rollbackReport != null) {
                                promise.resolve(rollbackReport);
                                return null;
                            }
                        }
                    } else if (CodePushNativeModule.this.mCodePush.didUpdate()) {
                        JSONObject currentPackage = CodePushNativeModule.this.mUpdateManager.getCurrentPackage();
                        if (!(currentPackage == null || (updateReport = CodePushNativeModule.this.mTelemetryManager.getUpdateReport(CodePushUtils.convertJsonObjectToWritable(currentPackage))) == null)) {
                            promise.resolve(updateReport);
                            return null;
                        }
                    } else if (CodePushNativeModule.this.mCodePush.isRunningBinaryVersion()) {
                        WritableMap binaryUpdateReport = CodePushNativeModule.this.mTelemetryManager.getBinaryUpdateReport(CodePushNativeModule.this.mCodePush.getAppVersion());
                        if (binaryUpdateReport != null) {
                            promise.resolve(binaryUpdateReport);
                            return null;
                        }
                    } else {
                        WritableMap retryStatusReport = CodePushNativeModule.this.mTelemetryManager.getRetryStatusReport();
                        if (retryStatusReport != null) {
                            promise.resolve(retryStatusReport);
                            return null;
                        }
                    }
                    promise.resolve("");
                } catch (JSONException e) {
                    throw new CodePushUnknownException("Unable to read failed updates information stored in SharedPreferences.", e);
                } catch (CodePushUnknownException e2) {
                    CodePushUtils.log((Throwable) e2);
                    promise.reject((Throwable) e2);
                }
                return null;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void installUpdate(ReadableMap readableMap, int i, int i2, Promise promise) {
        final ReadableMap readableMap2 = readableMap;
        final int i3 = i;
        final int i4 = i2;
        final Promise promise2 = promise;
        new AsyncTask() {
            /* access modifiers changed from: protected */
            public Void doInBackground(Void... voidArr) {
                try {
                    CodePushNativeModule.this.mUpdateManager.installPackage(CodePushUtils.convertReadableToJsonObject(readableMap2), CodePushNativeModule.this.mSettingsManager.isPendingUpdate((String) null));
                    String tryGetString = CodePushUtils.tryGetString(readableMap2, "packageHash");
                    if (tryGetString != null) {
                        CodePushNativeModule.this.mSettingsManager.savePendingUpdate(tryGetString, false);
                        if (!(i3 == CodePushInstallMode.ON_NEXT_RESUME.getValue() || i3 == CodePushInstallMode.IMMEDIATE.getValue())) {
                            if (i3 == CodePushInstallMode.ON_NEXT_SUSPEND.getValue()) {
                            }
                            promise2.resolve("");
                            return null;
                        }
                        int unused = CodePushNativeModule.this.mMinimumBackgroundDuration = i4;
                        if (CodePushNativeModule.this.mLifecycleEventListener == null) {
                            LifecycleEventListener unused2 = CodePushNativeModule.this.mLifecycleEventListener = new LifecycleEventListener() {
                                private Handler appSuspendHandler = new Handler(Looper.getMainLooper());
                                private Date lastPausedDate = null;
                                private Runnable loadBundleRunnable = new Runnable() {
                                    public void run() {
                                        CodePushUtils.log("Loading bundle on suspend");
                                        CodePushNativeModule.this.restartAppInternal(false);
                                    }
                                };

                                public void onHostDestroy() {
                                }

                                public void onHostResume() {
                                    this.appSuspendHandler.removeCallbacks(this.loadBundleRunnable);
                                    if (this.lastPausedDate != null) {
                                        long time = (new Date().getTime() - this.lastPausedDate.getTime()) / 1000;
                                        if (i3 == CodePushInstallMode.IMMEDIATE.getValue() || time >= ((long) CodePushNativeModule.this.mMinimumBackgroundDuration)) {
                                            CodePushUtils.log("Loading bundle on resume");
                                            CodePushNativeModule.this.restartAppInternal(false);
                                        }
                                    }
                                }

                                public void onHostPause() {
                                    this.lastPausedDate = new Date();
                                    if (i3 == CodePushInstallMode.ON_NEXT_SUSPEND.getValue() && CodePushNativeModule.this.mSettingsManager.isPendingUpdate((String) null)) {
                                        this.appSuspendHandler.postDelayed(this.loadBundleRunnable, (long) (i4 * 1000));
                                    }
                                }
                            };
                            CodePushNativeModule.this.getReactApplicationContext().addLifecycleEventListener(CodePushNativeModule.this.mLifecycleEventListener);
                        }
                        promise2.resolve("");
                        return null;
                    }
                    throw new CodePushUnknownException("Update package to be installed has no hash.");
                } catch (CodePushUnknownException e) {
                    CodePushUtils.log((Throwable) e);
                    promise2.reject((Throwable) e);
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void isFailedUpdate(String str, Promise promise) {
        try {
            promise.resolve(Boolean.valueOf(this.mSettingsManager.isFailedHash(str)));
        } catch (CodePushUnknownException e) {
            CodePushUtils.log((Throwable) e);
            promise.reject((Throwable) e);
        }
    }

    @ReactMethod
    public void getLatestRollbackInfo(Promise promise) {
        try {
            JSONObject latestRollbackInfo = this.mSettingsManager.getLatestRollbackInfo();
            if (latestRollbackInfo != null) {
                promise.resolve(CodePushUtils.convertJsonObjectToWritable(latestRollbackInfo));
            } else {
                promise.resolve((Object) null);
            }
        } catch (CodePushUnknownException e) {
            CodePushUtils.log((Throwable) e);
            promise.reject((Throwable) e);
        }
    }

    @ReactMethod
    public void setLatestRollbackInfo(String str, Promise promise) {
        try {
            this.mSettingsManager.setLatestRollbackInfo(str);
            promise.resolve((Object) null);
        } catch (CodePushUnknownException e) {
            CodePushUtils.log((Throwable) e);
            promise.reject((Throwable) e);
        }
    }

    @ReactMethod
    public void isFirstRun(String str, Promise promise) {
        try {
            promise.resolve(Boolean.valueOf(this.mCodePush.didUpdate() && str != null && str.length() > 0 && str.equals(this.mUpdateManager.getCurrentPackageHash())));
        } catch (CodePushUnknownException e) {
            CodePushUtils.log((Throwable) e);
            promise.reject((Throwable) e);
        }
    }

    @ReactMethod
    public void notifyApplicationReady(Promise promise) {
        try {
            this.mSettingsManager.removePendingUpdate();
            promise.resolve("");
        } catch (CodePushUnknownException e) {
            CodePushUtils.log((Throwable) e);
            promise.reject((Throwable) e);
        }
    }

    @ReactMethod
    public void recordStatusReported(ReadableMap readableMap) {
        try {
            this.mTelemetryManager.recordStatusReported(readableMap);
        } catch (CodePushUnknownException e) {
            CodePushUtils.log((Throwable) e);
        }
    }

    @ReactMethod
    public void saveStatusReportForRetry(ReadableMap readableMap) {
        try {
            this.mTelemetryManager.saveStatusReportForRetry(readableMap);
        } catch (CodePushUnknownException e) {
            CodePushUtils.log((Throwable) e);
        }
    }

    @ReactMethod
    public void downloadAndReplaceCurrentBundle(String str) {
        try {
            if (CodePush.isUsingTestConfiguration()) {
                this.mUpdateManager.downloadAndReplaceCurrentBundle(str, this.mCodePush.getAssetsBundleFileName());
            }
        } catch (IOException e) {
            throw new CodePushUnknownException("Unable to replace current bundle", e);
        } catch (CodePushMalformedDataException | CodePushUnknownException e2) {
            CodePushUtils.log(e2);
        }
    }

    @ReactMethod
    public void clearUpdates() {
        CodePushUtils.log("Clearing updates.");
        this.mCodePush.clearUpdates();
    }
}
