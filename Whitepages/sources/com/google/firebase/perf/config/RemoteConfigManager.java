package com.google.firebase.perf.config;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.annotation.Keep;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.StartupTime;
import com.google.firebase.inject.Provider;
import com.google.firebase.perf.config.ConfigurationConstants;
import com.google.firebase.perf.logging.AndroidLogger;
import com.google.firebase.perf.util.Optional;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue;
import com.google.firebase.remoteconfig.RemoteConfigComponent;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Keep
public class RemoteConfigManager {
    private static final long FETCH_NEVER_HAPPENED_TIMESTAMP_MS = 0;
    private static final String FIREPERF_FRC_NAMESPACE_NAME = "fireperf";
    private static final long MIN_APP_START_CONFIG_FETCH_DELAY_MS = 5000;
    private static final int RANDOM_APP_START_CONFIG_FETCH_DELAY_MS = 25000;
    private static final long TIME_AFTER_WHICH_A_FETCH_IS_CONSIDERED_STALE_MS = TimeUnit.HOURS.toMillis(12);
    private static final RemoteConfigManager instance = new RemoteConfigManager();
    private static final AndroidLogger logger = AndroidLogger.getInstance();
    private final ConcurrentHashMap<String, FirebaseRemoteConfigValue> allRcConfigMap;
    private final long appStartConfigFetchDelayInMs;
    private final long appStartTimeInMs;
    private final DeviceCacheManager cache;
    private final Executor executor;
    private FirebaseRemoteConfig firebaseRemoteConfig;
    private long firebaseRemoteConfigLastFetchTimestampMs;
    private Provider<RemoteConfigComponent> firebaseRemoteConfigProvider;

    @SuppressLint({"ThreadPoolCreation"})
    private RemoteConfigManager() {
        this(DeviceCacheManager.getInstance(), new ThreadPoolExecutor(0, 1, 0, TimeUnit.SECONDS, new LinkedBlockingQueue()), (FirebaseRemoteConfig) null, MIN_APP_START_CONFIG_FETCH_DELAY_MS + ((long) new Random().nextInt(RANDOM_APP_START_CONFIG_FETCH_DELAY_MS)), getInitialStartupMillis());
    }

    static long getInitialStartupMillis() {
        StartupTime startupTime;
        try {
            startupTime = (StartupTime) FirebaseApp.getInstance().get(StartupTime.class);
        } catch (IllegalStateException unused) {
            logger.debug("Unable to get StartupTime instance.");
            startupTime = null;
        }
        if (startupTime != null) {
            return startupTime.getEpochMillis();
        }
        return System.currentTimeMillis();
    }

    RemoteConfigManager(DeviceCacheManager deviceCacheManager, Executor executor2, FirebaseRemoteConfig firebaseRemoteConfig2, long j, long j2) {
        ConcurrentHashMap<String, FirebaseRemoteConfigValue> concurrentHashMap;
        this.firebaseRemoteConfigLastFetchTimestampMs = 0;
        this.cache = deviceCacheManager;
        this.executor = executor2;
        this.firebaseRemoteConfig = firebaseRemoteConfig2;
        if (firebaseRemoteConfig2 == null) {
            concurrentHashMap = new ConcurrentHashMap<>();
        } else {
            concurrentHashMap = new ConcurrentHashMap<>(firebaseRemoteConfig2.getAll());
        }
        this.allRcConfigMap = concurrentHashMap;
        this.appStartTimeInMs = j2;
        this.appStartConfigFetchDelayInMs = j;
    }

    public static RemoteConfigManager getInstance() {
        return instance;
    }

    public void setFirebaseRemoteConfigProvider(Provider<RemoteConfigComponent> provider) {
        this.firebaseRemoteConfigProvider = provider;
    }

    public Optional<Double> getDouble(String str) {
        if (str == null) {
            logger.debug("The key to get Remote Config double value is null.");
            return Optional.absent();
        }
        FirebaseRemoteConfigValue remoteConfigValue = getRemoteConfigValue(str);
        if (remoteConfigValue != null) {
            try {
                return Optional.of(Double.valueOf(remoteConfigValue.asDouble()));
            } catch (IllegalArgumentException unused) {
                if (!remoteConfigValue.asString().isEmpty()) {
                    logger.debug("Could not parse value: '%s' for key: '%s'.", remoteConfigValue.asString(), str);
                }
            }
        }
        return Optional.absent();
    }

    public Optional<Long> getLong(String str) {
        if (str == null) {
            logger.debug("The key to get Remote Config long value is null.");
            return Optional.absent();
        }
        FirebaseRemoteConfigValue remoteConfigValue = getRemoteConfigValue(str);
        if (remoteConfigValue != null) {
            try {
                return Optional.of(Long.valueOf(remoteConfigValue.asLong()));
            } catch (IllegalArgumentException unused) {
                if (!remoteConfigValue.asString().isEmpty()) {
                    logger.debug("Could not parse value: '%s' for key: '%s'.", remoteConfigValue.asString(), str);
                }
            }
        }
        return Optional.absent();
    }

    public Optional<Boolean> getBoolean(String str) {
        if (str == null) {
            logger.debug("The key to get Remote Config boolean value is null.");
            return Optional.absent();
        }
        FirebaseRemoteConfigValue remoteConfigValue = getRemoteConfigValue(str);
        if (remoteConfigValue != null) {
            try {
                return Optional.of(Boolean.valueOf(remoteConfigValue.asBoolean()));
            } catch (IllegalArgumentException unused) {
                if (!remoteConfigValue.asString().isEmpty()) {
                    logger.debug("Could not parse value: '%s' for key: '%s'.", remoteConfigValue.asString(), str);
                }
            }
        }
        return Optional.absent();
    }

    public Optional<String> getString(String str) {
        if (str == null) {
            logger.debug("The key to get Remote Config String value is null.");
            return Optional.absent();
        }
        FirebaseRemoteConfigValue remoteConfigValue = getRemoteConfigValue(str);
        if (remoteConfigValue != null) {
            return Optional.of(remoteConfigValue.asString());
        }
        return Optional.absent();
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> T getRemoteConfigValueOrDefault(java.lang.String r5, T r6) {
        /*
            r4 = this;
            com.google.firebase.remoteconfig.FirebaseRemoteConfigValue r0 = r4.getRemoteConfigValue(r5)
            if (r0 == 0) goto L_0x0068
            boolean r1 = r6 instanceof java.lang.Boolean     // Catch:{ IllegalArgumentException -> 0x004f }
            if (r1 == 0) goto L_0x0014
            boolean r1 = r0.asBoolean()     // Catch:{ IllegalArgumentException -> 0x004f }
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r1)     // Catch:{ IllegalArgumentException -> 0x004f }
        L_0x0012:
            r6 = r5
            goto L_0x0068
        L_0x0014:
            boolean r1 = r6 instanceof java.lang.Double     // Catch:{ IllegalArgumentException -> 0x004f }
            if (r1 == 0) goto L_0x0021
            double r1 = r0.asDouble()     // Catch:{ IllegalArgumentException -> 0x004f }
            java.lang.Double r5 = java.lang.Double.valueOf(r1)     // Catch:{ IllegalArgumentException -> 0x004f }
            goto L_0x0012
        L_0x0021:
            boolean r1 = r6 instanceof java.lang.Long     // Catch:{ IllegalArgumentException -> 0x004f }
            if (r1 != 0) goto L_0x0046
            boolean r1 = r6 instanceof java.lang.Integer     // Catch:{ IllegalArgumentException -> 0x004f }
            if (r1 == 0) goto L_0x002a
            goto L_0x0046
        L_0x002a:
            boolean r1 = r6 instanceof java.lang.String     // Catch:{ IllegalArgumentException -> 0x004f }
            if (r1 == 0) goto L_0x0033
            java.lang.String r5 = r0.asString()     // Catch:{ IllegalArgumentException -> 0x004f }
            goto L_0x0012
        L_0x0033:
            java.lang.String r1 = r0.asString()     // Catch:{ IllegalArgumentException -> 0x004f }
            com.google.firebase.perf.logging.AndroidLogger r2 = logger     // Catch:{ IllegalArgumentException -> 0x0044 }
            java.lang.String r3 = "No matching type found for the defaultValue: '%s', using String."
            java.lang.Object[] r6 = new java.lang.Object[]{r6}     // Catch:{ IllegalArgumentException -> 0x0044 }
            r2.debug(r3, r6)     // Catch:{ IllegalArgumentException -> 0x0044 }
            r6 = r1
            goto L_0x0068
        L_0x0044:
            r6 = r1
            goto L_0x004f
        L_0x0046:
            long r1 = r0.asLong()     // Catch:{ IllegalArgumentException -> 0x004f }
            java.lang.Long r5 = java.lang.Long.valueOf(r1)     // Catch:{ IllegalArgumentException -> 0x004f }
            goto L_0x0012
        L_0x004f:
            java.lang.String r1 = r0.asString()
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x0068
            com.google.firebase.perf.logging.AndroidLogger r1 = logger
            java.lang.String r0 = r0.asString()
            java.lang.Object[] r5 = new java.lang.Object[]{r0, r5}
            java.lang.String r0 = "Could not parse value: '%s' for key: '%s'."
            r1.debug(r0, r5)
        L_0x0068:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.perf.config.RemoteConfigManager.getRemoteConfigValueOrDefault(java.lang.String, java.lang.Object):java.lang.Object");
    }

    private FirebaseRemoteConfigValue getRemoteConfigValue(String str) {
        triggerRemoteConfigFetchIfNecessary();
        if (!isFirebaseRemoteConfigAvailable() || !this.allRcConfigMap.containsKey(str)) {
            return null;
        }
        FirebaseRemoteConfigValue firebaseRemoteConfigValue = this.allRcConfigMap.get(str);
        if (firebaseRemoteConfigValue.getSource() != 2) {
            return null;
        }
        logger.debug("Fetched value: '%s' for key: '%s' from Firebase Remote Config.", firebaseRemoteConfigValue.asString(), str);
        return firebaseRemoteConfigValue;
    }

    public boolean isLastFetchFailed() {
        FirebaseRemoteConfig firebaseRemoteConfig2 = this.firebaseRemoteConfig;
        if (firebaseRemoteConfig2 == null || firebaseRemoteConfig2.getInfo().getLastFetchStatus() == 1 || this.firebaseRemoteConfig.getInfo().getLastFetchStatus() == 2) {
            return true;
        }
        return false;
    }

    private void triggerRemoteConfigFetchIfNecessary() {
        if (isFirebaseRemoteConfigAvailable()) {
            if (this.allRcConfigMap.isEmpty()) {
                this.allRcConfigMap.putAll(this.firebaseRemoteConfig.getAll());
            }
            if (shouldFetchAndActivateRemoteConfigValues()) {
                triggerFirebaseRemoteConfigFetchAndActivateOnSuccessfulFetch();
            }
        }
    }

    private void triggerFirebaseRemoteConfigFetchAndActivateOnSuccessfulFetch() {
        this.firebaseRemoteConfigLastFetchTimestampMs = getCurrentSystemTimeMillis();
        this.firebaseRemoteConfig.fetchAndActivate().addOnSuccessListener(this.executor, new RemoteConfigManager$$ExternalSyntheticLambda0(this)).addOnFailureListener(this.executor, (OnFailureListener) new RemoteConfigManager$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$triggerFirebaseRemoteConfigFetchAndActivateOnSuccessfulFetch$0(Boolean bool) {
        syncConfigValues(this.firebaseRemoteConfig.getAll());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$triggerFirebaseRemoteConfigFetchAndActivateOnSuccessfulFetch$1(Exception exc) {
        logger.warn("Call to Remote Config failed: %s. This may cause a degraded experience with Firebase Performance. Please reach out to Firebase Support https://firebase.google.com/support/", exc);
        this.firebaseRemoteConfigLastFetchTimestampMs = 0;
    }

    /* access modifiers changed from: protected */
    public void syncConfigValues(Map<String, FirebaseRemoteConfigValue> map) {
        this.allRcConfigMap.putAll(map);
        for (String next : this.allRcConfigMap.keySet()) {
            if (!map.containsKey(next)) {
                this.allRcConfigMap.remove(next);
            }
        }
        ConfigurationConstants.ExperimentTTID instance2 = ConfigurationConstants.ExperimentTTID.getInstance();
        FirebaseRemoteConfigValue firebaseRemoteConfigValue = this.allRcConfigMap.get(instance2.getRemoteConfigFlag());
        if (firebaseRemoteConfigValue != null) {
            try {
                this.cache.setValue(instance2.getDeviceCacheFlag(), firebaseRemoteConfigValue.asBoolean());
            } catch (Exception unused) {
                logger.debug("ExperimentTTID remote config flag has invalid value, expected boolean.");
            }
        } else {
            logger.debug("ExperimentTTID remote config flag does not exist.");
        }
    }

    /* access modifiers changed from: protected */
    public long getCurrentSystemTimeMillis() {
        return System.currentTimeMillis();
    }

    public boolean isFirebaseRemoteConfigAvailable() {
        Provider<RemoteConfigComponent> provider;
        RemoteConfigComponent remoteConfigComponent;
        if (!(this.firebaseRemoteConfig != null || (provider = this.firebaseRemoteConfigProvider) == null || (remoteConfigComponent = provider.get()) == null)) {
            this.firebaseRemoteConfig = remoteConfigComponent.get(FIREPERF_FRC_NAMESPACE_NAME);
        }
        return this.firebaseRemoteConfig != null;
    }

    private boolean shouldFetchAndActivateRemoteConfigValues() {
        long currentSystemTimeMillis = getCurrentSystemTimeMillis();
        return hasAppStartConfigFetchDelayElapsed(currentSystemTimeMillis) && hasLastFetchBecomeStale(currentSystemTimeMillis);
    }

    private boolean hasAppStartConfigFetchDelayElapsed(long j) {
        return j - this.appStartTimeInMs >= this.appStartConfigFetchDelayInMs;
    }

    private boolean hasLastFetchBecomeStale(long j) {
        return j - this.firebaseRemoteConfigLastFetchTimestampMs > TIME_AFTER_WHICH_A_FETCH_IS_CONSIDERED_STALE_MS;
    }

    public static int getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            return 0;
        }
    }
}
