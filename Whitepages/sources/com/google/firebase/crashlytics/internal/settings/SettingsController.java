package com.google.firebase.crashlytics.internal.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import com.google.firebase.crashlytics.internal.common.CurrentTimeProvider;
import com.google.firebase.crashlytics.internal.common.DataCollectionArbiter;
import com.google.firebase.crashlytics.internal.common.DeliveryMechanism;
import com.google.firebase.crashlytics.internal.common.IdManager;
import com.google.firebase.crashlytics.internal.common.SystemCurrentTimeProvider;
import com.google.firebase.crashlytics.internal.concurrency.CrashlyticsWorkers;
import com.google.firebase.crashlytics.internal.network.HttpRequestFactory;
import com.google.firebase.crashlytics.internal.persistence.FileStore;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONObject;

public class SettingsController implements SettingsProvider {
    private static final String PREFS_BUILD_INSTANCE_IDENTIFIER = "existing_instance_identifier";
    private static final String SETTINGS_URL_FORMAT = "https://firebase-settings.crashlytics.com/spi/v2/platforms/android/gmp/%s/settings";
    /* access modifiers changed from: private */
    public final CachedSettingsIo cachedSettingsIo;
    private final Context context;
    private final CurrentTimeProvider currentTimeProvider;
    private final DataCollectionArbiter dataCollectionArbiter;
    /* access modifiers changed from: private */
    public final AtomicReference<Settings> settings;
    /* access modifiers changed from: private */
    public final SettingsJsonParser settingsJsonParser;
    /* access modifiers changed from: private */
    public final SettingsRequest settingsRequest;
    /* access modifiers changed from: private */
    public final SettingsSpiCall settingsSpiCall;
    /* access modifiers changed from: private */
    public final AtomicReference<TaskCompletionSource<Settings>> settingsTask = new AtomicReference<>(new TaskCompletionSource());

    SettingsController(Context context2, SettingsRequest settingsRequest2, CurrentTimeProvider currentTimeProvider2, SettingsJsonParser settingsJsonParser2, CachedSettingsIo cachedSettingsIo2, SettingsSpiCall settingsSpiCall2, DataCollectionArbiter dataCollectionArbiter2) {
        AtomicReference<Settings> atomicReference = new AtomicReference<>();
        this.settings = atomicReference;
        this.context = context2;
        this.settingsRequest = settingsRequest2;
        this.currentTimeProvider = currentTimeProvider2;
        this.settingsJsonParser = settingsJsonParser2;
        this.cachedSettingsIo = cachedSettingsIo2;
        this.settingsSpiCall = settingsSpiCall2;
        this.dataCollectionArbiter = dataCollectionArbiter2;
        atomicReference.set(DefaultSettingsJsonTransform.defaultSettings(currentTimeProvider2));
    }

    public static SettingsController create(Context context2, String str, IdManager idManager, HttpRequestFactory httpRequestFactory, String str2, String str3, FileStore fileStore, DataCollectionArbiter dataCollectionArbiter2) {
        String installerPackageName = idManager.getInstallerPackageName();
        SystemCurrentTimeProvider systemCurrentTimeProvider = new SystemCurrentTimeProvider();
        String str4 = str3;
        return new SettingsController(context2, new SettingsRequest(str, idManager.getModelName(), idManager.getOsBuildVersionString(), idManager.getOsDisplayVersionString(), idManager, CommonUtils.createInstanceIdFrom(CommonUtils.getMappingFileId(context2), str, str4, str2), str4, str2, DeliveryMechanism.determineFrom(installerPackageName).getId()), systemCurrentTimeProvider, new SettingsJsonParser(systemCurrentTimeProvider), new CachedSettingsIo(fileStore), new DefaultSettingsSpiCall(String.format(Locale.US, SETTINGS_URL_FORMAT, new Object[]{str}), httpRequestFactory), dataCollectionArbiter2);
    }

    public Task<Settings> getSettingsAsync() {
        return this.settingsTask.get().getTask();
    }

    public Settings getSettingsSync() {
        return this.settings.get();
    }

    public Task<Void> loadSettingsData(CrashlyticsWorkers crashlyticsWorkers) {
        return loadSettingsData(SettingsCacheBehavior.USE_CACHE, crashlyticsWorkers);
    }

    public Task<Void> loadSettingsData(SettingsCacheBehavior settingsCacheBehavior, final CrashlyticsWorkers crashlyticsWorkers) {
        Settings cachedSettingsData;
        if (buildInstanceIdentifierChanged() || (cachedSettingsData = getCachedSettingsData(settingsCacheBehavior)) == null) {
            Settings cachedSettingsData2 = getCachedSettingsData(SettingsCacheBehavior.IGNORE_CACHE_EXPIRATION);
            if (cachedSettingsData2 != null) {
                this.settings.set(cachedSettingsData2);
                this.settingsTask.get().trySetResult(cachedSettingsData2);
            }
            return this.dataCollectionArbiter.waitForDataCollectionPermission().onSuccessTask(crashlyticsWorkers.common, new SuccessContinuation<Void, Void>() {
                public Task<Void> then(Void voidR) throws Exception {
                    JSONObject jSONObject = (JSONObject) crashlyticsWorkers.network.getExecutor().submit(new SettingsController$1$$ExternalSyntheticLambda0(this)).get();
                    if (jSONObject != null) {
                        Settings parseSettingsJson = SettingsController.this.settingsJsonParser.parseSettingsJson(jSONObject);
                        SettingsController.this.cachedSettingsIo.writeCachedSettings(parseSettingsJson.expiresAtMillis, jSONObject);
                        SettingsController.this.logSettings(jSONObject, "Loaded settings: ");
                        SettingsController settingsController = SettingsController.this;
                        boolean unused = settingsController.setStoredBuildInstanceIdentifier(settingsController.settingsRequest.instanceId);
                        SettingsController.this.settings.set(parseSettingsJson);
                        ((TaskCompletionSource) SettingsController.this.settingsTask.get()).trySetResult(parseSettingsJson);
                    }
                    return Tasks.forResult(null);
                }

                /* access modifiers changed from: private */
                public /* synthetic */ JSONObject lambda$then$0() throws Exception {
                    return SettingsController.this.settingsSpiCall.invoke(SettingsController.this.settingsRequest, true);
                }
            });
        }
        this.settings.set(cachedSettingsData);
        this.settingsTask.get().trySetResult(cachedSettingsData);
        return Tasks.forResult(null);
    }

    private Settings getCachedSettingsData(SettingsCacheBehavior settingsCacheBehavior) {
        Settings settings2 = null;
        try {
            if (SettingsCacheBehavior.SKIP_CACHE_LOOKUP.equals(settingsCacheBehavior)) {
                return null;
            }
            JSONObject readCachedSettings = this.cachedSettingsIo.readCachedSettings();
            if (readCachedSettings != null) {
                Settings parseSettingsJson = this.settingsJsonParser.parseSettingsJson(readCachedSettings);
                if (parseSettingsJson != null) {
                    logSettings(readCachedSettings, "Loaded cached settings: ");
                    long currentTimeMillis = this.currentTimeProvider.getCurrentTimeMillis();
                    if (!SettingsCacheBehavior.IGNORE_CACHE_EXPIRATION.equals(settingsCacheBehavior)) {
                        if (parseSettingsJson.isExpired(currentTimeMillis)) {
                            Logger.getLogger().v("Cached settings have expired.");
                            return null;
                        }
                    }
                    try {
                        Logger.getLogger().v("Returning cached settings.");
                        return parseSettingsJson;
                    } catch (Exception e) {
                        e = e;
                        settings2 = parseSettingsJson;
                        Logger.getLogger().e("Failed to get cached settings", e);
                        return settings2;
                    }
                } else {
                    Logger.getLogger().e("Failed to parse cached settings data.", (Throwable) null);
                    return null;
                }
            } else {
                Logger.getLogger().d("No cached settings data found.");
                return null;
            }
        } catch (Exception e2) {
            e = e2;
            Logger.getLogger().e("Failed to get cached settings", e);
            return settings2;
        }
    }

    /* access modifiers changed from: private */
    public void logSettings(JSONObject jSONObject, String str) {
        Logger logger = Logger.getLogger();
        logger.d(str + jSONObject.toString());
    }

    private String getStoredBuildInstanceIdentifier() {
        return CommonUtils.getSharedPrefs(this.context).getString(PREFS_BUILD_INSTANCE_IDENTIFIER, "");
    }

    /* access modifiers changed from: private */
    @SuppressLint({"CommitPrefEdits"})
    public boolean setStoredBuildInstanceIdentifier(String str) {
        SharedPreferences.Editor edit = CommonUtils.getSharedPrefs(this.context).edit();
        edit.putString(PREFS_BUILD_INSTANCE_IDENTIFIER, str);
        edit.apply();
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean buildInstanceIdentifierChanged() {
        return !getStoredBuildInstanceIdentifier().equals(this.settingsRequest.instanceId);
    }
}
