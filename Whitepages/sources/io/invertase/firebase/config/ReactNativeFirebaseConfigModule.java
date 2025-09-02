package io.invertase.firebase.config;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.devsupport.StackTraceHelper;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.ConfigUpdate;
import com.google.firebase.remoteconfig.ConfigUpdateListener;
import com.google.firebase.remoteconfig.ConfigUpdateListenerRegistration;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigFetchThrottledException;
import com.salesforce.marketingcloud.messages.iam.j;
import io.invertase.firebase.common.ReactNativeFirebaseEvent;
import io.invertase.firebase.common.ReactNativeFirebaseEventEmitter;
import io.invertase.firebase.common.ReactNativeFirebaseModule;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ReactNativeFirebaseConfigModule extends ReactNativeFirebaseModule {
    private static final String SERVICE_NAME = "Config";
    private static HashMap<String, ConfigUpdateListenerRegistration> mConfigUpdateRegistrations = new HashMap<>();
    private final UniversalFirebaseConfigModule module;

    ReactNativeFirebaseConfigModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext, SERVICE_NAME);
        this.module = new UniversalFirebaseConfigModule(reactApplicationContext, SERVICE_NAME);
    }

    public void invalidate() {
        super.invalidate();
        Iterator<Map.Entry<String, ConfigUpdateListenerRegistration>> it = mConfigUpdateRegistrations.entrySet().iterator();
        while (it.hasNext()) {
            ((ConfigUpdateListenerRegistration) it.next().getValue()).remove();
            it.remove();
        }
    }

    @ReactMethod
    public void activate(String str, Promise promise) {
        this.module.activate(str).addOnCompleteListener(new ReactNativeFirebaseConfigModule$$ExternalSyntheticLambda1(this, promise));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$activate$0(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(resultWithConstants(task.getResult()));
        } else {
            ReactNativeFirebaseModule.rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    @ReactMethod
    public void fetch(String str, double d, Promise promise) {
        this.module.fetch(str, (long) d).addOnCompleteListener(new ReactNativeFirebaseConfigModule$$ExternalSyntheticLambda4(this, promise));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$fetch$1(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(resultWithConstants(task.getResult()));
        } else {
            rejectPromiseWithConfigException(promise, task.getException());
        }
    }

    @ReactMethod
    public void fetchAndActivate(String str, Promise promise) {
        this.module.fetchAndActivate(str).addOnCompleteListener(new ReactNativeFirebaseConfigModule$$ExternalSyntheticLambda3(this, promise));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$fetchAndActivate$2(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(resultWithConstants(task.getResult()));
        } else {
            rejectPromiseWithConfigException(promise, task.getException());
        }
    }

    @ReactMethod
    public void reset(String str, Promise promise) {
        this.module.reset(str).addOnCompleteListener(new ReactNativeFirebaseConfigModule$$ExternalSyntheticLambda0(this, promise));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$reset$3(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(resultWithConstants(task.getResult()));
        } else {
            rejectPromiseWithConfigException(promise, task.getException());
        }
    }

    @ReactMethod
    public void setConfigSettings(String str, ReadableMap readableMap, Promise promise) {
        this.module.setConfigSettings(str, Arguments.toBundle(readableMap)).addOnCompleteListener(new ReactNativeFirebaseConfigModule$$ExternalSyntheticLambda2(this, promise));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setConfigSettings$4(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(resultWithConstants(task.getResult()));
        } else {
            ReactNativeFirebaseModule.rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    @ReactMethod
    public void setDefaults(String str, ReadableMap readableMap, Promise promise) {
        this.module.setDefaults(str, readableMap.toHashMap()).addOnCompleteListener(new ReactNativeFirebaseConfigModule$$ExternalSyntheticLambda5(this, promise));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setDefaults$5(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(resultWithConstants(task.getResult()));
        } else {
            ReactNativeFirebaseModule.rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    @ReactMethod
    public void setDefaultsFromResource(String str, String str2, Promise promise) {
        this.module.setDefaultsFromResource(str, str2).addOnCompleteListener(new ReactNativeFirebaseConfigModule$$ExternalSyntheticLambda7(this, promise));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setDefaultsFromResource$6(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(resultWithConstants(task.getResult()));
            return;
        }
        Exception exception = task.getException();
        if (exception != null && exception.getMessage().equals("resource_not_found")) {
            ReactNativeFirebaseModule.rejectPromiseWithCodeAndMessage(promise, "resource_not_found", "The specified resource name was not found.");
        }
        ReactNativeFirebaseModule.rejectPromiseWithExceptionMap(promise, task.getException());
    }

    @ReactMethod
    public void ensureInitialized(String str, Promise promise) {
        this.module.ensureInitialized(str).addOnCompleteListener(new ReactNativeFirebaseConfigModule$$ExternalSyntheticLambda6(this, promise));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$ensureInitialized$7(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(resultWithConstants((Object) null));
        } else {
            ReactNativeFirebaseModule.rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    @ReactMethod
    public void onConfigUpdated(final String str) {
        if (mConfigUpdateRegistrations.get(str) == null) {
            mConfigUpdateRegistrations.put(str, FirebaseRemoteConfig.getInstance(FirebaseApp.getInstance(str)).addOnConfigUpdateListener(new ConfigUpdateListener() {
                public void onUpdate(ConfigUpdate configUpdate) {
                    ReactNativeFirebaseEventEmitter sharedInstance = ReactNativeFirebaseEventEmitter.getSharedInstance();
                    ArrayList arrayList = new ArrayList(configUpdate.getUpdatedKeys());
                    HashMap hashMap = new HashMap();
                    hashMap.put("appName", str);
                    hashMap.put("resultType", FirebaseAnalytics.Param.SUCCESS);
                    hashMap.put("updatedKeys", arrayList);
                    sharedInstance.sendEvent(new ReactNativeFirebaseEvent("on_config_updated", Arguments.makeNativeMap((Map<String, Object>) hashMap), str));
                }

                public void onError(FirebaseRemoteConfigException firebaseRemoteConfigException) {
                    ReactNativeFirebaseEventEmitter sharedInstance = ReactNativeFirebaseEventEmitter.getSharedInstance();
                    WritableMap createMap = Arguments.createMap();
                    createMap.putString("resultType", "error");
                    createMap.putString("appName", str);
                    int i = AnonymousClass2.$SwitchMap$com$google$firebase$remoteconfig$FirebaseRemoteConfigException$Code[firebaseRemoteConfigException.getCode().ordinal()];
                    if (i == 1) {
                        createMap.putString("code", "config_update_stream_error");
                    } else if (i == 2) {
                        createMap.putString("code", "config_update_message_invalid");
                    } else if (i == 3) {
                        createMap.putString("code", "config_update_not_fetched");
                    } else if (i == 4) {
                        createMap.putString("code", "config_update_unavailable");
                    } else if (i != 5) {
                        createMap.putString("code", "internal");
                    } else {
                        createMap.putString("code", j.h);
                    }
                    createMap.putString(StackTraceHelper.MESSAGE_KEY, firebaseRemoteConfigException.getMessage());
                    createMap.putString("nativeErrorMessage", firebaseRemoteConfigException.getMessage());
                    sharedInstance.sendEvent(new ReactNativeFirebaseEvent("on_config_updated", createMap, str));
                }
            }));
        }
    }

    /* renamed from: io.invertase.firebase.config.ReactNativeFirebaseConfigModule$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$remoteconfig$FirebaseRemoteConfigException$Code;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.firebase.remoteconfig.FirebaseRemoteConfigException$Code[] r0 = com.google.firebase.remoteconfig.FirebaseRemoteConfigException.Code.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$firebase$remoteconfig$FirebaseRemoteConfigException$Code = r0
                com.google.firebase.remoteconfig.FirebaseRemoteConfigException$Code r1 = com.google.firebase.remoteconfig.FirebaseRemoteConfigException.Code.CONFIG_UPDATE_STREAM_ERROR     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$firebase$remoteconfig$FirebaseRemoteConfigException$Code     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.firebase.remoteconfig.FirebaseRemoteConfigException$Code r1 = com.google.firebase.remoteconfig.FirebaseRemoteConfigException.Code.CONFIG_UPDATE_MESSAGE_INVALID     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$firebase$remoteconfig$FirebaseRemoteConfigException$Code     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.firebase.remoteconfig.FirebaseRemoteConfigException$Code r1 = com.google.firebase.remoteconfig.FirebaseRemoteConfigException.Code.CONFIG_UPDATE_NOT_FETCHED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$firebase$remoteconfig$FirebaseRemoteConfigException$Code     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.firebase.remoteconfig.FirebaseRemoteConfigException$Code r1 = com.google.firebase.remoteconfig.FirebaseRemoteConfigException.Code.CONFIG_UPDATE_UNAVAILABLE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$firebase$remoteconfig$FirebaseRemoteConfigException$Code     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.firebase.remoteconfig.FirebaseRemoteConfigException$Code r1 = com.google.firebase.remoteconfig.FirebaseRemoteConfigException.Code.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.invertase.firebase.config.ReactNativeFirebaseConfigModule.AnonymousClass2.<clinit>():void");
        }
    }

    @ReactMethod
    public void removeConfigUpdateRegistration(String str) {
        ConfigUpdateListenerRegistration configUpdateListenerRegistration = mConfigUpdateRegistrations.get(str);
        if (configUpdateListenerRegistration != null) {
            configUpdateListenerRegistration.remove();
            mConfigUpdateRegistrations.remove(str);
        }
    }

    @ReactMethod
    public void setCustomSignals(String str, ReadableMap readableMap, Promise promise) {
        this.module.setCustomSignals(str, readableMap.toHashMap()).addOnCompleteListener(new ReactNativeFirebaseConfigModule$$ExternalSyntheticLambda8(this, promise));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setCustomSignals$8(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(resultWithConstants(task.getResult()));
        } else {
            ReactNativeFirebaseModule.rejectPromiseWithExceptionMap(promise, task.getException());
        }
    }

    private WritableMap resultWithConstants(Object obj) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("result", obj);
        hashMap.put("constants", this.module.getConstantsForApp(FirebaseApp.DEFAULT_APP_NAME));
        return Arguments.makeNativeMap((Map<String, Object>) hashMap);
    }

    private void rejectPromiseWithConfigException(Promise promise, Exception exc) {
        if (exc == null) {
            ReactNativeFirebaseModule.rejectPromiseWithCodeAndMessage(promise, j.h, "Operation cannot be completed successfully, due to an unknown error.");
        } else if (exc.getCause() instanceof FirebaseRemoteConfigFetchThrottledException) {
            ReactNativeFirebaseModule.rejectPromiseWithCodeAndMessage(promise, "throttled", "fetch() operation cannot be completed successfully, due to throttling.", exc.getMessage());
        } else {
            ReactNativeFirebaseModule.rejectPromiseWithCodeAndMessage(promise, "failure", "fetch() operation cannot be completed successfully.", exc.getMessage());
        }
    }

    public Map<String, Object> getConstants() {
        return this.module.getConstants();
    }
}
