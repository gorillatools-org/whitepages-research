package io.invertase.firebase.config;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import com.facebook.hermes.intl.Constants;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.CustomSignals;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue;
import com.salesforce.marketingcloud.messages.iam.j;
import io.invertase.firebase.common.UniversalFirebaseModule;
import java.util.HashMap;
import java.util.Map;

public class UniversalFirebaseConfigModule extends UniversalFirebaseModule {
    private static final String SOURCE = "source";
    private static final String VALUE = "value";

    UniversalFirebaseConfigModule(Context context, String str) {
        super(context, str);
    }

    /* access modifiers changed from: package-private */
    public Task<Boolean> activate(String str) {
        return FirebaseRemoteConfig.getInstance(FirebaseApp.getInstance(str)).activate();
    }

    /* access modifiers changed from: package-private */
    public Task<Void> fetch(String str, long j) {
        return Tasks.call(getExecutor(), new UniversalFirebaseConfigModule$$ExternalSyntheticLambda0(FirebaseApp.getInstance(str), j));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Void lambda$fetch$0(FirebaseApp firebaseApp, long j) throws Exception {
        FirebaseRemoteConfig instance = FirebaseRemoteConfig.getInstance(firebaseApp);
        Tasks.await(j == -1 ? instance.fetch() : instance.fetch(j));
        return null;
    }

    /* access modifiers changed from: package-private */
    public Task<Boolean> fetchAndActivate(String str) {
        return FirebaseRemoteConfig.getInstance(FirebaseApp.getInstance(str)).fetchAndActivate();
    }

    /* access modifiers changed from: package-private */
    public Task<Void> reset(String str) {
        return FirebaseRemoteConfig.getInstance(FirebaseApp.getInstance(str)).reset();
    }

    /* access modifiers changed from: package-private */
    public Task<Void> setConfigSettings(String str, Bundle bundle) {
        return Tasks.call(getExecutor(), new UniversalFirebaseConfigModule$$ExternalSyntheticLambda1(bundle, FirebaseApp.getInstance(str)));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Void lambda$setConfigSettings$1(Bundle bundle, FirebaseApp firebaseApp) throws Exception {
        FirebaseRemoteConfigSettings.Builder builder = new FirebaseRemoteConfigSettings.Builder();
        if (bundle.containsKey("minimumFetchInterval")) {
            builder.setMinimumFetchIntervalInSeconds((long) bundle.getDouble("minimumFetchInterval"));
        }
        if (bundle.containsKey("fetchTimeout")) {
            builder.setFetchTimeoutInSeconds((long) bundle.getDouble("fetchTimeout"));
        }
        FirebaseRemoteConfig.getInstance(firebaseApp).setConfigSettingsAsync(builder.build());
        return null;
    }

    /* access modifiers changed from: package-private */
    public Task<Void> setDefaultsFromResource(String str, String str2) {
        return Tasks.call(getExecutor(), new UniversalFirebaseConfigModule$$ExternalSyntheticLambda3(this, str2, FirebaseApp.getInstance(str)));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Void lambda$setDefaultsFromResource$2(String str, FirebaseApp firebaseApp) throws Exception {
        XmlResourceParser xmlResourceParser;
        int xmlResourceIdByName = getXmlResourceIdByName(str);
        try {
            xmlResourceParser = getApplicationContext().getResources().getXml(xmlResourceIdByName);
        } catch (Resources.NotFoundException unused) {
            xmlResourceParser = null;
        }
        if (xmlResourceParser != null) {
            Tasks.await(FirebaseRemoteConfig.getInstance(firebaseApp).setDefaultsAsync(xmlResourceIdByName));
            return null;
        }
        throw new Exception("resource_not_found");
    }

    /* access modifiers changed from: package-private */
    public Task<Void> setDefaults(String str, HashMap<String, Object> hashMap) {
        return FirebaseRemoteConfig.getInstance(FirebaseApp.getInstance(str)).setDefaultsAsync((Map<String, Object>) hashMap);
    }

    /* access modifiers changed from: package-private */
    public Task<FirebaseRemoteConfigInfo> ensureInitialized(String str) {
        Task<FirebaseRemoteConfigInfo> ensureInitialized = FirebaseRemoteConfig.getInstance(FirebaseApp.getInstance(str)).ensureInitialized();
        try {
            Tasks.await(fetchAndActivate(str));
        } catch (Exception unused) {
        }
        return ensureInitialized;
    }

    /* access modifiers changed from: package-private */
    public Map<String, Object> getAllValuesForApp(String str) {
        Map<String, FirebaseRemoteConfigValue> all = FirebaseRemoteConfig.getInstance(FirebaseApp.getInstance(str)).getAll();
        HashMap hashMap = new HashMap(all.size());
        for (Map.Entry next : all.entrySet()) {
            hashMap.put((String) next.getKey(), convertRemoteConfigValue((FirebaseRemoteConfigValue) next.getValue()));
        }
        return hashMap;
    }

    @SuppressLint({"DiscouragedApi"})
    private int getXmlResourceIdByName(String str) {
        return getApplicationContext().getResources().getIdentifier(str, "xml", getApplicationContext().getPackageName());
    }

    private String lastFetchStatusToString(int i) {
        if (i == -1) {
            return FirebaseAnalytics.Param.SUCCESS;
        }
        if (i == 0) {
            return "no_fetch_yet";
        }
        if (i == 1) {
            return "failure";
        }
        if (i != 2) {
            return j.h;
        }
        return "throttled";
    }

    private Bundle convertRemoteConfigValue(FirebaseRemoteConfigValue firebaseRemoteConfigValue) {
        Bundle bundle = new Bundle(2);
        bundle.putString("value", firebaseRemoteConfigValue.asString());
        int source = firebaseRemoteConfigValue.getSource();
        if (source == 1) {
            bundle.putString("source", Constants.COLLATION_DEFAULT);
        } else if (source != 2) {
            bundle.putString("source", "static");
        } else {
            bundle.putString("source", "remote");
        }
        return bundle;
    }

    /* access modifiers changed from: package-private */
    public Task<Void> setCustomSignals(String str, HashMap<String, Object> hashMap) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        getExecutor().execute(new UniversalFirebaseConfigModule$$ExternalSyntheticLambda2(hashMap, FirebaseApp.getInstance(str), taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$setCustomSignals$3(HashMap hashMap, FirebaseApp firebaseApp, TaskCompletionSource taskCompletionSource) {
        try {
            CustomSignals.Builder builder = new CustomSignals.Builder();
            for (Map.Entry entry : hashMap.entrySet()) {
                Object value = entry.getValue();
                if (value instanceof String) {
                    builder.put((String) entry.getKey(), (String) value);
                } else if (value instanceof Long) {
                    builder.put((String) entry.getKey(), ((Long) value).longValue());
                } else if (value instanceof Integer) {
                    builder.put((String) entry.getKey(), ((Integer) value).longValue());
                } else if (value instanceof Double) {
                    builder.put((String) entry.getKey(), ((Double) value).doubleValue());
                } else if (value == null) {
                    builder.put((String) entry.getKey(), (String) null);
                }
            }
            Tasks.await(FirebaseRemoteConfig.getInstance(firebaseApp).setCustomSignals(builder.build()));
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    public Map<String, Object> getConstantsForApp(String str) {
        HashMap hashMap = new HashMap();
        FirebaseRemoteConfigInfo info = FirebaseRemoteConfig.getInstance(FirebaseApp.getInstance(str)).getInfo();
        FirebaseRemoteConfigSettings configSettings = info.getConfigSettings();
        hashMap.put("values", getAllValuesForApp(str));
        hashMap.put("lastFetchTime", Long.valueOf(info.getFetchTimeMillis()));
        hashMap.put("lastFetchStatus", lastFetchStatusToString(info.getLastFetchStatus()));
        hashMap.put("minimumFetchInterval", Long.valueOf(configSettings.getMinimumFetchIntervalInSeconds()));
        hashMap.put("fetchTimeout", Long.valueOf(configSettings.getFetchTimeoutInSeconds()));
        return hashMap;
    }
}
