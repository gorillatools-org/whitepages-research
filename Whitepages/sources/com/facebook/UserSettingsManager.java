package com.facebook;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import com.facebook.GraphRequest;
import com.facebook.appevents.InternalAppEventsLogger;
import com.facebook.hermes.intl.Constants;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

public final class UserSettingsManager {
    public static final UserSettingsManager INSTANCE = new UserSettingsManager();
    private static final String TAG = UserSettingsManager.class.getName();
    private static final UserSetting advertiserIDCollectionEnabled = new UserSetting(true, "com.facebook.sdk.AdvertiserIDCollectionEnabled");
    private static final UserSetting autoInitEnabled = new UserSetting(true, "com.facebook.sdk.AutoInitEnabled");
    private static final UserSetting autoLogAppEventsEnabledLocally = new UserSetting(true, "com.facebook.sdk.AutoLogAppEventsEnabled");
    private static final UserSetting codelessSetupEnabled = new UserSetting(false, "auto_event_setup_enabled");
    private static final AtomicBoolean isFetchingCodelessStatus = new AtomicBoolean(false);
    private static final AtomicBoolean isInitialized = new AtomicBoolean(false);
    private static final UserSetting monitorEnabled = new UserSetting(true, "com.facebook.sdk.MonitorEnabled");
    private static SharedPreferences userSettingPref;

    private UserSettingsManager() {
    }

    private final void initializeIfNotInitialized() {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                if (FacebookSdk.isInitialized() && isInitialized.compareAndSet(false, true)) {
                    SharedPreferences sharedPreferences = FacebookSdk.getApplicationContext().getSharedPreferences("com.facebook.sdk.USER_SETTINGS", 0);
                    Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getApplicationContext()\n…GS, Context.MODE_PRIVATE)");
                    userSettingPref = sharedPreferences;
                    initializeUserSetting(autoLogAppEventsEnabledLocally, advertiserIDCollectionEnabled, autoInitEnabled);
                    initializeCodelessSetupEnabledAsync();
                    logWarnings();
                    logIfSDKSettingsChanged();
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    private final void initializeUserSetting(UserSetting... userSettingArr) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                for (UserSetting userSetting : userSettingArr) {
                    if (userSetting == codelessSetupEnabled) {
                        initializeCodelessSetupEnabledAsync();
                    } else if (userSetting.getValue() == null) {
                        readSettingFromCache(userSetting);
                        if (userSetting.getValue() == null) {
                            loadSettingFromManifest(userSetting);
                        }
                    } else {
                        writeSettingToCache(userSetting);
                    }
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    private final void initializeCodelessSetupEnabledAsync() {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                UserSetting userSetting = codelessSetupEnabled;
                readSettingFromCache(userSetting);
                long currentTimeMillis = System.currentTimeMillis();
                if (userSetting.getValue() == null || currentTimeMillis - userSetting.getLastTS() >= 604800000) {
                    userSetting.setValue((Boolean) null);
                    userSetting.setLastTS(0);
                    if (isFetchingCodelessStatus.compareAndSet(false, true)) {
                        FacebookSdk.getExecutor().execute(new UserSettingsManager$$ExternalSyntheticLambda0(currentTimeMillis));
                    }
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void initializeCodelessSetupEnabledAsync$lambda$0(long j) {
        FetchedAppSettings queryAppSettings;
        Class<UserSettingsManager> cls = UserSettingsManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                if (advertiserIDCollectionEnabled.getValue() && (queryAppSettings = FetchedAppSettingsManager.queryAppSettings(FacebookSdk.getApplicationId(), false)) != null && queryAppSettings.getCodelessEventsEnabled()) {
                    AttributionIdentifiers attributionIdentifiers = AttributionIdentifiers.Companion.getAttributionIdentifiers(FacebookSdk.getApplicationContext());
                    String androidAdvertiserId = (attributionIdentifiers == null || attributionIdentifiers.getAndroidAdvertiserId() == null) ? null : attributionIdentifiers.getAndroidAdvertiserId();
                    if (androidAdvertiserId != null) {
                        Bundle bundle = new Bundle();
                        bundle.putString("advertiser_id", androidAdvertiserId);
                        bundle.putString("fields", "auto_event_setup_enabled");
                        GraphRequest newGraphPathRequest = GraphRequest.Companion.newGraphPathRequest((AccessToken) null, "app", (GraphRequest.Callback) null);
                        newGraphPathRequest.setParameters(bundle);
                        JSONObject jSONObject = newGraphPathRequest.executeAndWait().getJSONObject();
                        if (jSONObject != null) {
                            UserSetting userSetting = codelessSetupEnabled;
                            userSetting.setValue(Boolean.valueOf(jSONObject.optBoolean("auto_event_setup_enabled", false)));
                            userSetting.setLastTS(j);
                            INSTANCE.writeSettingToCache(userSetting);
                        }
                    }
                }
                isFetchingCodelessStatus.set(false);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    private final void writeSettingToCache(UserSetting userSetting) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                validateInitialized();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("value", userSetting.getValue());
                jSONObject.put("last_timestamp", userSetting.getLastTS());
                SharedPreferences sharedPreferences = userSettingPref;
                if (sharedPreferences == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("userSettingPref");
                    sharedPreferences = null;
                }
                sharedPreferences.edit().putString(userSetting.getKey(), jSONObject.toString()).apply();
                logIfSDKSettingsChanged();
            } catch (Exception e) {
                Utility.logd(TAG, e);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    private final void readSettingFromCache(UserSetting userSetting) {
        String str = "";
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                validateInitialized();
                SharedPreferences sharedPreferences = userSettingPref;
                if (sharedPreferences == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("userSettingPref");
                    sharedPreferences = null;
                }
                String string = sharedPreferences.getString(userSetting.getKey(), str);
                if (string != null) {
                    str = string;
                }
                if (str.length() > 0) {
                    JSONObject jSONObject = new JSONObject(str);
                    userSetting.setValue(Boolean.valueOf(jSONObject.getBoolean("value")));
                    userSetting.setLastTS(jSONObject.getLong("last_timestamp"));
                }
            } catch (JSONException e) {
                Utility.logd(TAG, (Exception) e);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    private final void loadSettingFromManifest(UserSetting userSetting) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                validateInitialized();
                Context applicationContext = FacebookSdk.getApplicationContext();
                ApplicationInfo applicationInfo = applicationContext.getPackageManager().getApplicationInfo(applicationContext.getPackageName(), 128);
                Intrinsics.checkNotNullExpressionValue(applicationInfo, "ctx.packageManager.getAp…ageManager.GET_META_DATA)");
                Bundle bundle = applicationInfo.metaData;
                if (bundle != null && bundle.containsKey(userSetting.getKey())) {
                    userSetting.setValue(Boolean.valueOf(applicationInfo.metaData.getBoolean(userSetting.getKey(), userSetting.getDefaultVal())));
                }
            } catch (PackageManager.NameNotFoundException e) {
                Utility.logd(TAG, (Exception) e);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    private final void logWarnings() {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                Context applicationContext = FacebookSdk.getApplicationContext();
                ApplicationInfo applicationInfo = applicationContext.getPackageManager().getApplicationInfo(applicationContext.getPackageName(), 128);
                Intrinsics.checkNotNullExpressionValue(applicationInfo, "ctx.packageManager.getAp…ageManager.GET_META_DATA)");
                Bundle bundle = applicationInfo.metaData;
                if (bundle != null) {
                    if (!bundle.containsKey("com.facebook.sdk.AdvertiserIDCollectionEnabled")) {
                        Log.w(TAG, "You haven't set a value for AdvertiserIDCollectionEnabled. Set the flag to TRUE if you want to collect Advertiser ID for better advertising and analytics results. To request user consent before collecting data, set the flag value to FALSE, then change to TRUE once user consent is received. Learn more: https://developers.facebook.com/docs/app-events/getting-started-app-events-android#disable-auto-events.");
                    }
                    if (!getAdvertiserIDCollectionEnabled()) {
                        Log.w(TAG, "The value for AdvertiserIDCollectionEnabled is currently set to FALSE so you're sending app events without collecting Advertiser ID. This can affect the quality of your advertising and analytics results.");
                    }
                }
            } catch (PackageManager.NameNotFoundException unused) {
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    private final void logIfSDKSettingsChanged() {
        int i;
        int i2;
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                if (isInitialized.get() && FacebookSdk.isInitialized()) {
                    Context applicationContext = FacebookSdk.getApplicationContext();
                    int i3 = 0;
                    boolean value = autoInitEnabled.getValue() | ((autoLogAppEventsEnabledLocally.getValue() ? 1 : 0) << true) | ((advertiserIDCollectionEnabled.getValue() ? 1 : 0) << true) | ((monitorEnabled.getValue() ? 1 : 0) << true);
                    SharedPreferences sharedPreferences = userSettingPref;
                    SharedPreferences sharedPreferences2 = null;
                    if (sharedPreferences == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("userSettingPref");
                        sharedPreferences = null;
                    }
                    int i4 = sharedPreferences.getInt("com.facebook.sdk.USER_SETTINGS_BITMASK", 0);
                    if (i4 != value) {
                        SharedPreferences sharedPreferences3 = userSettingPref;
                        if (sharedPreferences3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("userSettingPref");
                        } else {
                            sharedPreferences2 = sharedPreferences3;
                        }
                        sharedPreferences2.edit().putInt("com.facebook.sdk.USER_SETTINGS_BITMASK", value ? 1 : 0).apply();
                        try {
                            ApplicationInfo applicationInfo = applicationContext.getPackageManager().getApplicationInfo(applicationContext.getPackageName(), 128);
                            Intrinsics.checkNotNullExpressionValue(applicationInfo, "ctx.packageManager.getAp…ageManager.GET_META_DATA)");
                            if (applicationInfo.metaData != null) {
                                String[] strArr = {"com.facebook.sdk.AutoInitEnabled", "com.facebook.sdk.AutoLogAppEventsEnabled", "com.facebook.sdk.AdvertiserIDCollectionEnabled", "com.facebook.sdk.MonitorEnabled"};
                                boolean[] zArr = {true, true, true, true};
                                i = 0;
                                i2 = 0;
                                while (i3 < 4) {
                                    try {
                                        i2 |= (applicationInfo.metaData.containsKey(strArr[i3]) ? 1 : 0) << i3;
                                        i |= (applicationInfo.metaData.getBoolean(strArr[i3], zArr[i3]) ? 1 : 0) << i3;
                                        i3++;
                                    } catch (PackageManager.NameNotFoundException unused) {
                                        i3 = i;
                                        i = i3;
                                        i3 = i2;
                                        InternalAppEventsLogger internalAppEventsLogger = new InternalAppEventsLogger(applicationContext);
                                        Bundle bundle = new Bundle();
                                        bundle.putInt(Constants.COLLATION_OPTION_USAGE, i3);
                                        bundle.putInt("initial", i);
                                        bundle.putInt("previous", i4);
                                        bundle.putInt("current", value);
                                        internalAppEventsLogger.logChangedSettingsEvent(bundle);
                                    }
                                }
                                i3 = i2;
                                InternalAppEventsLogger internalAppEventsLogger2 = new InternalAppEventsLogger(applicationContext);
                                Bundle bundle2 = new Bundle();
                                bundle2.putInt(Constants.COLLATION_OPTION_USAGE, i3);
                                bundle2.putInt("initial", i);
                                bundle2.putInt("previous", i4);
                                bundle2.putInt("current", value);
                                internalAppEventsLogger2.logChangedSettingsEvent(bundle2);
                            }
                            i = 0;
                            InternalAppEventsLogger internalAppEventsLogger22 = new InternalAppEventsLogger(applicationContext);
                            Bundle bundle22 = new Bundle();
                            bundle22.putInt(Constants.COLLATION_OPTION_USAGE, i3);
                            bundle22.putInt("initial", i);
                            bundle22.putInt("previous", i4);
                            bundle22.putInt("current", value);
                            internalAppEventsLogger22.logChangedSettingsEvent(bundle22);
                        } catch (PackageManager.NameNotFoundException unused2) {
                            i2 = 0;
                            i = i3;
                            i3 = i2;
                            InternalAppEventsLogger internalAppEventsLogger222 = new InternalAppEventsLogger(applicationContext);
                            Bundle bundle222 = new Bundle();
                            bundle222.putInt(Constants.COLLATION_OPTION_USAGE, i3);
                            bundle222.putInt("initial", i);
                            bundle222.putInt("previous", i4);
                            bundle222.putInt("current", value);
                            internalAppEventsLogger222.logChangedSettingsEvent(bundle222);
                        }
                    }
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public static final void logIfAutoAppLinkEnabled() {
        Class<UserSettingsManager> cls = UserSettingsManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Context applicationContext = FacebookSdk.getApplicationContext();
                ApplicationInfo applicationInfo = applicationContext.getPackageManager().getApplicationInfo(applicationContext.getPackageName(), 128);
                Intrinsics.checkNotNullExpressionValue(applicationInfo, "ctx.packageManager.getAp…ageManager.GET_META_DATA)");
                Bundle bundle = applicationInfo.metaData;
                if (bundle != null && bundle.getBoolean("com.facebook.sdk.AutoAppLinkEnabled", false)) {
                    InternalAppEventsLogger internalAppEventsLogger = new InternalAppEventsLogger(applicationContext);
                    Bundle bundle2 = new Bundle();
                    if (!Utility.isAutoAppLinkSetup()) {
                        bundle2.putString("SchemeWarning", "You haven't set the Auto App Link URL scheme: fb<YOUR APP ID> in AndroidManifest");
                        Log.w(TAG, "You haven't set the Auto App Link URL scheme: fb<YOUR APP ID> in AndroidManifest");
                    }
                    internalAppEventsLogger.logEvent("fb_auto_applink", bundle2);
                }
            } catch (PackageManager.NameNotFoundException unused) {
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    private final void validateInitialized() {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                if (!isInitialized.get()) {
                    throw new FacebookSdkNotInitializedException("The UserSettingManager has not been initialized successfully");
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public static final boolean getAutoInitEnabled() {
        Class<UserSettingsManager> cls = UserSettingsManager.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return false;
        }
        try {
            INSTANCE.initializeIfNotInitialized();
            return autoInitEnabled.getValue();
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return false;
        }
    }

    public static final boolean getAutoLogAppEventsEnabled() {
        Class<UserSettingsManager> cls = UserSettingsManager.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return false;
        }
        try {
            UserSettingsManager userSettingsManager = INSTANCE;
            userSettingsManager.initializeIfNotInitialized();
            return userSettingsManager.checkAutoLogAppEventsEnabled();
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return false;
        }
    }

    private final boolean checkAutoLogAppEventsEnabled() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return false;
        }
        try {
            Map cachedMigratedAutoLogValuesInAppSettings = FetchedAppSettingsManager.getCachedMigratedAutoLogValuesInAppSettings();
            if (cachedMigratedAutoLogValuesInAppSettings != null) {
                if (!cachedMigratedAutoLogValuesInAppSettings.isEmpty()) {
                    Boolean bool = (Boolean) cachedMigratedAutoLogValuesInAppSettings.get("auto_log_app_events_enabled");
                    Boolean bool2 = (Boolean) cachedMigratedAutoLogValuesInAppSettings.get("auto_log_app_events_default");
                    if (bool != null) {
                        return bool.booleanValue();
                    }
                    Boolean checkClientSideConfiguration = checkClientSideConfiguration();
                    if (checkClientSideConfiguration != null) {
                        return checkClientSideConfiguration.booleanValue();
                    }
                    if (bool2 != null) {
                        return bool2.booleanValue();
                    }
                    return true;
                }
            }
            return autoLogAppEventsEnabledLocally.getValue();
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return false;
        }
    }

    private final Boolean checkClientSideConfiguration() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            Boolean readAutoLogAppEventsSettingFromCache = readAutoLogAppEventsSettingFromCache();
            if (readAutoLogAppEventsSettingFromCache != null) {
                return readAutoLogAppEventsSettingFromCache;
            }
            Boolean loadAutoLogAppEventsSettingFromManifest = loadAutoLogAppEventsSettingFromManifest();
            if (loadAutoLogAppEventsSettingFromManifest != null) {
                return loadAutoLogAppEventsSettingFromManifest;
            }
            return null;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    private static final Boolean readAutoLogAppEventsSettingFromCache() {
        String str = "";
        Class<UserSettingsManager> cls = UserSettingsManager.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            INSTANCE.validateInitialized();
            SharedPreferences sharedPreferences = userSettingPref;
            if (sharedPreferences == null) {
                Intrinsics.throwUninitializedPropertyAccessException("userSettingPref");
                sharedPreferences = null;
            }
            String string = sharedPreferences.getString(autoLogAppEventsEnabledLocally.getKey(), str);
            if (string != null) {
                str = string;
            }
            if (str.length() > 0) {
                return Boolean.valueOf(new JSONObject(str).getBoolean("value"));
            }
        } catch (JSONException e) {
            Utility.logd(TAG, (Exception) e);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
        return null;
    }

    private final Boolean loadAutoLogAppEventsSettingFromManifest() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            validateInitialized();
            Context applicationContext = FacebookSdk.getApplicationContext();
            ApplicationInfo applicationInfo = applicationContext.getPackageManager().getApplicationInfo(applicationContext.getPackageName(), 128);
            Intrinsics.checkNotNullExpressionValue(applicationInfo, "ctx.packageManager.getAp…ageManager.GET_META_DATA)");
            Bundle bundle = applicationInfo.metaData;
            if (bundle != null) {
                UserSetting userSetting = autoLogAppEventsEnabledLocally;
                if (bundle.containsKey(userSetting.getKey())) {
                    return Boolean.valueOf(applicationInfo.metaData.getBoolean(userSetting.getKey()));
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            Utility.logd(TAG, (Exception) e);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
        return null;
    }

    public static final boolean getAdvertiserIDCollectionEnabled() {
        Class<UserSettingsManager> cls = UserSettingsManager.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return false;
        }
        try {
            INSTANCE.initializeIfNotInitialized();
            return advertiserIDCollectionEnabled.getValue();
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return false;
        }
    }

    public static final boolean getCodelessSetupEnabled() {
        Class<UserSettingsManager> cls = UserSettingsManager.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return false;
        }
        try {
            INSTANCE.initializeIfNotInitialized();
            return codelessSetupEnabled.getValue();
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return false;
        }
    }

    private static final class UserSetting {
        private boolean defaultVal;
        private String key;
        private long lastTS;
        private Boolean value;

        public UserSetting(boolean z, String str) {
            Intrinsics.checkNotNullParameter(str, "key");
            this.defaultVal = z;
            this.key = str;
        }

        public final boolean getDefaultVal() {
            return this.defaultVal;
        }

        public final String getKey() {
            return this.key;
        }

        public final Boolean getValue() {
            return this.value;
        }

        public final void setValue(Boolean bool) {
            this.value = bool;
        }

        public final long getLastTS() {
            return this.lastTS;
        }

        public final void setLastTS(long j) {
            this.lastTS = j;
        }

        /* renamed from: getValue  reason: collision with other method in class */
        public final boolean m48getValue() {
            Boolean bool = this.value;
            return bool != null ? bool.booleanValue() : this.defaultVal;
        }
    }
}
