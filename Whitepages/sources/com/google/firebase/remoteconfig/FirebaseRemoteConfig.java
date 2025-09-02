package com.google.firebase.remoteconfig;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.abt.AbtException;
import com.google.firebase.abt.FirebaseABTesting;
import com.google.firebase.concurrent.FirebaseExecutors;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.remoteconfig.internal.ConfigCacheClient;
import com.google.firebase.remoteconfig.internal.ConfigContainer;
import com.google.firebase.remoteconfig.internal.ConfigFetchHandler;
import com.google.firebase.remoteconfig.internal.ConfigGetParameterHandler;
import com.google.firebase.remoteconfig.internal.ConfigRealtimeHandler;
import com.google.firebase.remoteconfig.internal.ConfigSharedPrefsClient;
import com.google.firebase.remoteconfig.internal.DefaultsXmlParser;
import com.google.firebase.remoteconfig.internal.rollouts.RolloutsStateSubscriptionsHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FirebaseRemoteConfig {
    public static final boolean DEFAULT_VALUE_FOR_BOOLEAN = false;
    public static final byte[] DEFAULT_VALUE_FOR_BYTE_ARRAY = new byte[0];
    public static final double DEFAULT_VALUE_FOR_DOUBLE = 0.0d;
    public static final long DEFAULT_VALUE_FOR_LONG = 0;
    public static final String DEFAULT_VALUE_FOR_STRING = "";
    public static final int LAST_FETCH_STATUS_FAILURE = 1;
    public static final int LAST_FETCH_STATUS_NO_FETCH_YET = 0;
    public static final int LAST_FETCH_STATUS_SUCCESS = -1;
    public static final int LAST_FETCH_STATUS_THROTTLED = 2;
    public static final String TAG = "FirebaseRemoteConfig";
    public static final int VALUE_SOURCE_DEFAULT = 1;
    public static final int VALUE_SOURCE_REMOTE = 2;
    public static final int VALUE_SOURCE_STATIC = 0;
    private final ConfigCacheClient activatedConfigsCache;
    private final ConfigRealtimeHandler configRealtimeHandler;
    private final Context context;
    private final ConfigCacheClient defaultConfigsCache;
    private final Executor executor;
    private final ConfigFetchHandler fetchHandler;
    private final ConfigCacheClient fetchedConfigsCache;
    private final FirebaseABTesting firebaseAbt;
    private final FirebaseApp firebaseApp;
    private final FirebaseInstallationsApi firebaseInstallations;
    private final ConfigSharedPrefsClient frcSharedPrefs;
    private final ConfigGetParameterHandler getHandler;
    private final RolloutsStateSubscriptionsHandler rolloutsStateSubscriptionsHandler;

    public static FirebaseRemoteConfig getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    public static FirebaseRemoteConfig getInstance(FirebaseApp firebaseApp2) {
        return ((RemoteConfigComponent) firebaseApp2.get(RemoteConfigComponent.class)).getDefault();
    }

    FirebaseRemoteConfig(Context context2, FirebaseApp firebaseApp2, FirebaseInstallationsApi firebaseInstallationsApi, FirebaseABTesting firebaseABTesting, Executor executor2, ConfigCacheClient configCacheClient, ConfigCacheClient configCacheClient2, ConfigCacheClient configCacheClient3, ConfigFetchHandler configFetchHandler, ConfigGetParameterHandler configGetParameterHandler, ConfigSharedPrefsClient configSharedPrefsClient, ConfigRealtimeHandler configRealtimeHandler2, RolloutsStateSubscriptionsHandler rolloutsStateSubscriptionsHandler2) {
        this.context = context2;
        this.firebaseApp = firebaseApp2;
        this.firebaseInstallations = firebaseInstallationsApi;
        this.firebaseAbt = firebaseABTesting;
        this.executor = executor2;
        this.fetchedConfigsCache = configCacheClient;
        this.activatedConfigsCache = configCacheClient2;
        this.defaultConfigsCache = configCacheClient3;
        this.fetchHandler = configFetchHandler;
        this.getHandler = configGetParameterHandler;
        this.frcSharedPrefs = configSharedPrefsClient;
        this.configRealtimeHandler = configRealtimeHandler2;
        this.rolloutsStateSubscriptionsHandler = rolloutsStateSubscriptionsHandler2;
    }

    public Task<FirebaseRemoteConfigInfo> ensureInitialized() {
        Task<ConfigContainer> task = this.activatedConfigsCache.get();
        Task<ConfigContainer> task2 = this.defaultConfigsCache.get();
        Task<ConfigContainer> task3 = this.fetchedConfigsCache.get();
        Task call = Tasks.call(this.executor, new FirebaseRemoteConfig$$ExternalSyntheticLambda7(this));
        return Tasks.whenAllComplete((Task<?>[]) new Task[]{task, task2, task3, call, this.firebaseInstallations.getId(), this.firebaseInstallations.getToken(false)}).continueWith(this.executor, new FirebaseRemoteConfig$$ExternalSyntheticLambda8(call));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ FirebaseRemoteConfigInfo lambda$ensureInitialized$0(Task task, Task task2) throws Exception {
        return (FirebaseRemoteConfigInfo) task.getResult();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Task lambda$fetchAndActivate$1(Void voidR) throws Exception {
        return activate();
    }

    public Task<Boolean> fetchAndActivate() {
        return fetch().onSuccessTask(this.executor, new FirebaseRemoteConfig$$ExternalSyntheticLambda9(this));
    }

    public Task<Boolean> activate() {
        Task<ConfigContainer> task = this.fetchedConfigsCache.get();
        Task<ConfigContainer> task2 = this.activatedConfigsCache.get();
        return Tasks.whenAllComplete((Task<?>[]) new Task[]{task, task2}).continueWithTask(this.executor, new FirebaseRemoteConfig$$ExternalSyntheticLambda0(this, task, task2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Task lambda$activate$2(Task task, Task task2, Task task3) throws Exception {
        if (!task.isSuccessful() || task.getResult() == null) {
            return Tasks.forResult(Boolean.FALSE);
        }
        ConfigContainer configContainer = (ConfigContainer) task.getResult();
        if (!task2.isSuccessful() || isFetchedFresh(configContainer, (ConfigContainer) task2.getResult())) {
            return this.activatedConfigsCache.put(configContainer).continueWith(this.executor, new FirebaseRemoteConfig$$ExternalSyntheticLambda2(this));
        }
        return Tasks.forResult(Boolean.FALSE);
    }

    public Task<Void> fetch() {
        return this.fetchHandler.fetch().onSuccessTask(FirebaseExecutors.directExecutor(), new FirebaseRemoteConfig$$ExternalSyntheticLambda10());
    }

    public Task<Void> fetch(long j) {
        return this.fetchHandler.fetch(j).onSuccessTask(FirebaseExecutors.directExecutor(), new FirebaseRemoteConfig$$ExternalSyntheticLambda3());
    }

    public String getString(String str) {
        return this.getHandler.getString(str);
    }

    public boolean getBoolean(String str) {
        return this.getHandler.getBoolean(str);
    }

    public double getDouble(String str) {
        return this.getHandler.getDouble(str);
    }

    public long getLong(String str) {
        return this.getHandler.getLong(str);
    }

    public FirebaseRemoteConfigValue getValue(String str) {
        return this.getHandler.getValue(str);
    }

    public Set<String> getKeysByPrefix(String str) {
        return this.getHandler.getKeysByPrefix(str);
    }

    public Map<String, FirebaseRemoteConfigValue> getAll() {
        return this.getHandler.getAll();
    }

    public FirebaseRemoteConfigInfo getInfo() {
        return this.frcSharedPrefs.getInfo();
    }

    public Task<Void> setConfigSettingsAsync(FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
        return Tasks.call(this.executor, new FirebaseRemoteConfig$$ExternalSyntheticLambda6(this, firebaseRemoteConfigSettings));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Void lambda$setConfigSettingsAsync$5(FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) throws Exception {
        this.frcSharedPrefs.setConfigSettings(firebaseRemoteConfigSettings);
        return null;
    }

    public Task<Void> setDefaultsAsync(Map<String, Object> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : map.entrySet()) {
            Object value = next.getValue();
            if (value instanceof byte[]) {
                hashMap.put((String) next.getKey(), new String((byte[]) value));
            } else {
                hashMap.put((String) next.getKey(), value.toString());
            }
        }
        return setDefaultsWithStringsMapAsync(hashMap);
    }

    public Task<Void> setDefaultsAsync(int i) {
        return setDefaultsWithStringsMapAsync(DefaultsXmlParser.getDefaultsFromXml(this.context, i));
    }

    public Task<Void> reset() {
        return Tasks.call(this.executor, new FirebaseRemoteConfig$$ExternalSyntheticLambda5(this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Void lambda$reset$6() throws Exception {
        this.activatedConfigsCache.clear();
        this.fetchedConfigsCache.clear();
        this.defaultConfigsCache.clear();
        this.frcSharedPrefs.clear();
        return null;
    }

    public ConfigUpdateListenerRegistration addOnConfigUpdateListener(ConfigUpdateListener configUpdateListener) {
        return this.configRealtimeHandler.addRealtimeConfigUpdateListener(configUpdateListener);
    }

    /* access modifiers changed from: package-private */
    public void startLoadingConfigsFromDisk() {
        this.activatedConfigsCache.get();
        this.defaultConfigsCache.get();
        this.fetchedConfigsCache.get();
    }

    public void schedule(Runnable runnable) {
        this.executor.execute(runnable);
    }

    /* access modifiers changed from: private */
    public boolean processActivatePutTask(Task<ConfigContainer> task) {
        if (!task.isSuccessful()) {
            return false;
        }
        this.fetchedConfigsCache.clear();
        ConfigContainer result = task.getResult();
        if (result != null) {
            updateAbtWithActivatedExperiments(result.getAbtExperiments());
            this.rolloutsStateSubscriptionsHandler.publishActiveRolloutsState(result);
            return true;
        }
        Log.e(TAG, "Activated configs written to disk are null.");
        return true;
    }

    private Task<Void> setDefaultsWithStringsMapAsync(Map<String, String> map) {
        try {
            return this.defaultConfigsCache.put(ConfigContainer.newBuilder().replaceConfigsWith(map).build()).onSuccessTask(FirebaseExecutors.directExecutor(), new FirebaseRemoteConfig$$ExternalSyntheticLambda1());
        } catch (JSONException e) {
            Log.e(TAG, "The provided defaults map could not be processed.", e);
            return Tasks.forResult(null);
        }
    }

    public Task<Void> setCustomSignals(CustomSignals customSignals) {
        return Tasks.call(this.executor, new FirebaseRemoteConfig$$ExternalSyntheticLambda4(this, customSignals));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Void lambda$setCustomSignals$8(CustomSignals customSignals) throws Exception {
        this.frcSharedPrefs.setCustomSignals(customSignals.customSignals);
        return null;
    }

    /* access modifiers changed from: package-private */
    public void updateAbtWithActivatedExperiments(JSONArray jSONArray) {
        if (this.firebaseAbt != null) {
            try {
                this.firebaseAbt.replaceAllExperiments(toExperimentInfoMaps(jSONArray));
            } catch (JSONException e) {
                Log.e(TAG, "Could not parse ABT experiments from the JSON response.", e);
            } catch (AbtException e2) {
                Log.w(TAG, "Could not update ABT experiments.", e2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setConfigUpdateBackgroundState(boolean z) {
        this.configRealtimeHandler.setBackgroundState(z);
    }

    static List<Map<String, String>> toExperimentInfoMaps(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            HashMap hashMap = new HashMap();
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, jSONObject.getString(next));
            }
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public RolloutsStateSubscriptionsHandler getRolloutsStateSubscriptionsHandler() {
        return this.rolloutsStateSubscriptionsHandler;
    }

    private static boolean isFetchedFresh(ConfigContainer configContainer, ConfigContainer configContainer2) {
        return configContainer2 == null || !configContainer.getFetchTime().equals(configContainer2.getFetchTime());
    }
}
