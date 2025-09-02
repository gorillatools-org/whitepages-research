package com.microsoft.codepush.react;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.devsupport.DevInternalSettings;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class CodePush implements ReactPackage {
    private static CodePush mCurrentInstance = null;
    private static String mPublicKey = null;
    private static String mServerUrl = "https://codepush.appcenter.ms/";
    private static String sAppVersion = null;
    private static boolean sIsRunningBinaryVersion = false;
    private static boolean sNeedToReportRollback = false;
    private static boolean sTestConfigurationFlag = false;
    private String mAssetsBundleFileName;
    private Context mContext;
    private String mDeploymentKey;
    private boolean mDidUpdate = false;
    private final boolean mIsDebugMode;
    private SettingsManager mSettingsManager;
    private CodePushTelemetryManager mTelemetryManager;
    private CodePushUpdateManager mUpdateManager;

    static ReactInstanceManager getReactInstanceManager() {
        return null;
    }

    public CodePush(String str, Context context, boolean z) {
        this.mContext = context.getApplicationContext();
        this.mUpdateManager = new CodePushUpdateManager(context.getFilesDir().getAbsolutePath());
        this.mTelemetryManager = new CodePushTelemetryManager(this.mContext);
        this.mDeploymentKey = str;
        this.mIsDebugMode = z;
        this.mSettingsManager = new SettingsManager(this.mContext);
        if (sAppVersion == null) {
            try {
                sAppVersion = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException e) {
                throw new CodePushUnknownException("Unable to get package info for " + this.mContext.getPackageName(), e);
            }
        }
        mCurrentInstance = this;
        String customPropertyFromStringsIfExist = getCustomPropertyFromStringsIfExist("PublicKey");
        if (customPropertyFromStringsIfExist != null) {
            mPublicKey = customPropertyFromStringsIfExist;
        }
        String customPropertyFromStringsIfExist2 = getCustomPropertyFromStringsIfExist("ServerUrl");
        if (customPropertyFromStringsIfExist2 != null) {
            mServerUrl = customPropertyFromStringsIfExist2;
        }
        clearDebugCacheIfNeeded((ReactInstanceManager) null);
        initializeUpdateAfterRestart();
    }

    private String getCustomPropertyFromStringsIfExist(String str) {
        String packageName = this.mContext.getPackageName();
        Resources resources = this.mContext.getResources();
        int identifier = resources.getIdentifier("CodePush" + str, "string", packageName);
        if (identifier == 0) {
            return null;
        }
        String string = this.mContext.getString(identifier);
        if (!string.isEmpty()) {
            return string;
        }
        CodePushUtils.log("Specified " + str + " is empty");
        return null;
    }

    private boolean isLiveReloadEnabled(ReactInstanceManager reactInstanceManager) {
        DevSupportManager devSupportManager;
        if (!(reactInstanceManager == null || (devSupportManager = reactInstanceManager.getDevSupportManager()) == null)) {
            DevInternalSettings devInternalSettings = (DevInternalSettings) devSupportManager.getDevSettings();
            Method[] methods = devInternalSettings.getClass().getMethods();
            int length = methods.length;
            int i = 0;
            while (i < length) {
                Method method = methods[i];
                if (method.getName().equals("isReloadOnJSChangeEnabled")) {
                    try {
                        return ((Boolean) method.invoke(devInternalSettings, (Object[]) null)).booleanValue();
                    } catch (Exception unused) {
                        return false;
                    }
                } else {
                    i++;
                }
            }
        }
        return false;
    }

    public void clearDebugCacheIfNeeded(ReactInstanceManager reactInstanceManager) {
        if (this.mIsDebugMode && this.mSettingsManager.isPendingUpdate((String) null) && !isLiveReloadEnabled(reactInstanceManager)) {
            File file = new File(this.mContext.getFilesDir(), "ReactNativeDevBundle.js");
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public boolean didUpdate() {
        return this.mDidUpdate;
    }

    public String getAppVersion() {
        return sAppVersion;
    }

    public String getAssetsBundleFileName() {
        return this.mAssetsBundleFileName;
    }

    public String getPublicKey() {
        return mPublicKey;
    }

    /* access modifiers changed from: package-private */
    public long getBinaryResourcesModifiedTime() {
        try {
            return Long.parseLong(this.mContext.getResources().getString(this.mContext.getResources().getIdentifier("CODE_PUSH_APK_BUILD_TIME", "string", this.mContext.getPackageName())).replaceAll("\"", ""));
        } catch (Exception e) {
            throw new CodePushUnknownException("Error in getting binary resources modified time", e);
        }
    }

    public String getDeploymentKey() {
        return this.mDeploymentKey;
    }

    public static String getJSBundleFile() {
        return getJSBundleFile("index.android.bundle");
    }

    public static String getJSBundleFile(String str) {
        CodePush codePush = mCurrentInstance;
        if (codePush != null) {
            return codePush.getJSBundleFileInternal(str);
        }
        throw new CodePushNotInitializedException("A CodePush instance has not been created yet. Have you added it to your app's list of ReactPackages?");
    }

    public String getJSBundleFileInternal(String str) {
        String str2;
        this.mAssetsBundleFileName = str;
        String str3 = "assets://" + str;
        try {
            str2 = this.mUpdateManager.getCurrentPackageBundlePath(this.mAssetsBundleFileName);
        } catch (CodePushMalformedDataException e) {
            CodePushUtils.log(e.getMessage());
            clearUpdates();
            str2 = null;
        }
        if (str2 == null) {
            CodePushUtils.logBundleUrl(str3);
            sIsRunningBinaryVersion = true;
            return str3;
        }
        JSONObject currentPackage = this.mUpdateManager.getCurrentPackage();
        if (isPackageBundleLatest(currentPackage)) {
            CodePushUtils.logBundleUrl(str2);
            sIsRunningBinaryVersion = false;
            return str2;
        }
        this.mDidUpdate = false;
        if (!this.mIsDebugMode || hasBinaryVersionChanged(currentPackage)) {
            clearUpdates();
        }
        CodePushUtils.logBundleUrl(str3);
        sIsRunningBinaryVersion = true;
        return str3;
    }

    public String getServerUrl() {
        return mServerUrl;
    }

    /* access modifiers changed from: package-private */
    public void initializeUpdateAfterRestart() {
        this.mDidUpdate = false;
        JSONObject pendingUpdate = this.mSettingsManager.getPendingUpdate();
        if (pendingUpdate != null) {
            JSONObject currentPackage = this.mUpdateManager.getCurrentPackage();
            if (currentPackage == null || (!isPackageBundleLatest(currentPackage) && hasBinaryVersionChanged(currentPackage))) {
                CodePushUtils.log("Skipping initializeUpdateAfterRestart(), binary version is newer");
                return;
            }
            try {
                if (pendingUpdate.getBoolean("isLoading")) {
                    CodePushUtils.log("Update did not finish loading the last time, rolling back to a previous version.");
                    sNeedToReportRollback = true;
                    rollbackPackage();
                    return;
                }
                this.mDidUpdate = true;
                this.mSettingsManager.savePendingUpdate(pendingUpdate.getString("hash"), true);
            } catch (JSONException e) {
                throw new CodePushUnknownException("Unable to read pending update metadata stored in SharedPreferences", e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void invalidateCurrentInstance() {
        mCurrentInstance = null;
    }

    /* access modifiers changed from: package-private */
    public boolean isDebugMode() {
        return this.mIsDebugMode;
    }

    /* access modifiers changed from: package-private */
    public boolean isRunningBinaryVersion() {
        return sIsRunningBinaryVersion;
    }

    private boolean isPackageBundleLatest(JSONObject jSONObject) {
        try {
            String optString = jSONObject.optString("binaryModifiedTime", (String) null);
            Long valueOf = optString != null ? Long.valueOf(Long.parseLong(optString)) : null;
            return valueOf != null && valueOf.longValue() == getBinaryResourcesModifiedTime() && (isUsingTestConfiguration() || sAppVersion.equals(jSONObject.optString(RemoteConfigConstants.RequestFieldKey.APP_VERSION, (String) null)));
        } catch (NumberFormatException e) {
            throw new CodePushUnknownException("Error in reading binary modified date from package metadata", e);
        }
    }

    private boolean hasBinaryVersionChanged(JSONObject jSONObject) {
        return !sAppVersion.equals(jSONObject.optString(RemoteConfigConstants.RequestFieldKey.APP_VERSION, (String) null));
    }

    /* access modifiers changed from: package-private */
    public boolean needToReportRollback() {
        return sNeedToReportRollback;
    }

    private void rollbackPackage() {
        this.mSettingsManager.saveFailedUpdate(this.mUpdateManager.getCurrentPackage());
        this.mUpdateManager.rollbackPackage();
        this.mSettingsManager.removePendingUpdate();
    }

    public void setNeedToReportRollback(boolean z) {
        sNeedToReportRollback = z;
    }

    public static boolean isUsingTestConfiguration() {
        return sTestConfigurationFlag;
    }

    public void clearUpdates() {
        this.mUpdateManager.clearUpdates();
        this.mSettingsManager.removePendingUpdate();
        this.mSettingsManager.removeFailedUpdates();
    }

    public List createNativeModules(ReactApplicationContext reactApplicationContext) {
        CodePushNativeModule codePushNativeModule = new CodePushNativeModule(reactApplicationContext, this, this.mUpdateManager, this.mTelemetryManager, this.mSettingsManager);
        CodePushDialog codePushDialog = new CodePushDialog(reactApplicationContext);
        ArrayList arrayList = new ArrayList();
        arrayList.add(codePushNativeModule);
        arrayList.add(codePushDialog);
        return arrayList;
    }

    public List createViewManagers(ReactApplicationContext reactApplicationContext) {
        return new ArrayList();
    }
}
