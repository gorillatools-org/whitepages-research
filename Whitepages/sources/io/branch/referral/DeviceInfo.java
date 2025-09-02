package io.branch.referral;

import android.app.UiModeManager;
import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import io.branch.coroutines.DeviceSignalsKt;
import io.branch.referral.ServerRequest;
import io.branch.referral.SystemObserver;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import org.json.JSONException;
import org.json.JSONObject;

class DeviceInfo {
    private final Context context_;
    private final SystemObserver systemObserver_ = new SystemObserverInstance();

    static DeviceInfo getInstance() {
        Branch instance = Branch.getInstance();
        if (instance == null) {
            return null;
        }
        return instance.getDeviceInfo();
    }

    DeviceInfo(Context context) {
        this.context_ = context;
    }

    /* access modifiers changed from: package-private */
    public void updateRequestWithV1Params(ServerRequest serverRequest, JSONObject jSONObject) {
        try {
            SystemObserver.UniqueId hardwareID = getHardwareID();
            if (!isNullOrEmptyOrBlank(hardwareID.getId())) {
                jSONObject.put(Defines$Jsonkey.HardwareID.getKey(), hardwareID.getId());
                jSONObject.put(Defines$Jsonkey.IsHardwareIDReal.getKey(), hardwareID.isReal());
            }
            String anonID = SystemObserver.getAnonID(this.context_);
            if (!isNullOrEmptyOrBlank(anonID)) {
                jSONObject.put(Defines$Jsonkey.AnonID.getKey(), anonID);
            }
            String phoneBrand = SystemObserver.getPhoneBrand();
            if (!isNullOrEmptyOrBlank(phoneBrand)) {
                jSONObject.put(Defines$Jsonkey.Brand.getKey(), phoneBrand);
            }
            String phoneModel = SystemObserver.getPhoneModel();
            if (!isNullOrEmptyOrBlank(phoneModel)) {
                jSONObject.put(Defines$Jsonkey.Model.getKey(), phoneModel);
            }
            DisplayMetrics screenDisplay = SystemObserver.getScreenDisplay(this.context_);
            jSONObject.put(Defines$Jsonkey.ScreenDpi.getKey(), screenDisplay.densityDpi);
            jSONObject.put(Defines$Jsonkey.ScreenHeight.getKey(), screenDisplay.heightPixels);
            jSONObject.put(Defines$Jsonkey.ScreenWidth.getKey(), screenDisplay.widthPixels);
            jSONObject.put(Defines$Jsonkey.WiFi.getKey(), SystemObserver.getWifiConnected(this.context_));
            jSONObject.put(Defines$Jsonkey.UIMode.getKey(), SystemObserver.getUIMode(this.context_));
            String os = SystemObserver.getOS(this.context_);
            if (!isNullOrEmptyOrBlank(os)) {
                jSONObject.put(Defines$Jsonkey.OS.getKey(), os);
            }
            jSONObject.put(Defines$Jsonkey.APILevel.getKey(), SystemObserver.getAPILevel());
            if (Branch.getPluginName() != null) {
                jSONObject.put(Defines$Jsonkey.PluginName.getKey(), Branch.getPluginName());
                jSONObject.put(Defines$Jsonkey.PluginVersion.getKey(), Branch.getPluginVersion());
            }
            String iSO2CountryCode = SystemObserver.getISO2CountryCode();
            if (!TextUtils.isEmpty(iSO2CountryCode)) {
                jSONObject.put(Defines$Jsonkey.Country.getKey(), iSO2CountryCode);
            }
            String iSO2LanguageCode = SystemObserver.getISO2LanguageCode();
            if (!TextUtils.isEmpty(iSO2LanguageCode)) {
                jSONObject.put(Defines$Jsonkey.Language.getKey(), iSO2LanguageCode);
            }
            String localIPAddress = SystemObserver.getLocalIPAddress();
            if (!TextUtils.isEmpty(localIPAddress)) {
                jSONObject.put(Defines$Jsonkey.LocalIP.getKey(), localIPAddress);
            }
            if (serverRequest.isInitializationOrEventRequest()) {
                jSONObject.put(Defines$Jsonkey.CPUType.getKey(), SystemObserver.getCPUType());
                jSONObject.put(Defines$Jsonkey.DeviceBuildId.getKey(), SystemObserver.getDeviceBuildId());
                jSONObject.put(Defines$Jsonkey.Locale.getKey(), SystemObserver.getLocale());
                jSONObject.put(Defines$Jsonkey.ConnectionType.getKey(), SystemObserver.getConnectionType(this.context_));
                jSONObject.put(Defines$Jsonkey.DeviceCarrier.getKey(), SystemObserver.getCarrier(this.context_));
                jSONObject.put(Defines$Jsonkey.OSVersionAndroid.getKey(), SystemObserver.getOSVersion());
            }
        } catch (JSONException e) {
            BranchLogger.w("Caught JSONException" + e.getMessage());
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isTV() {
        UiModeManager uiModeManager = (UiModeManager) this.context_.getSystemService("uimode");
        if (uiModeManager == null) {
            BranchLogger.v("uiModeManager is null, mark this as a non-TV device by default.");
            return false;
        } else if (uiModeManager.getCurrentModeType() == 4) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void updateRequestWithV2Params(ServerRequest serverRequest, PrefHelper prefHelper, JSONObject jSONObject) {
        try {
            SystemObserver.UniqueId hardwareID = getHardwareID();
            if (!isNullOrEmptyOrBlank(hardwareID.getId())) {
                jSONObject.put(Defines$Jsonkey.AndroidID.getKey(), hardwareID.getId());
            }
            String anonID = SystemObserver.getAnonID(this.context_);
            if (!isNullOrEmptyOrBlank(anonID)) {
                jSONObject.put(Defines$Jsonkey.AnonID.getKey(), anonID);
            }
            String phoneBrand = SystemObserver.getPhoneBrand();
            if (!isNullOrEmptyOrBlank(phoneBrand)) {
                jSONObject.put(Defines$Jsonkey.Brand.getKey(), phoneBrand);
            }
            String phoneModel = SystemObserver.getPhoneModel();
            if (!isNullOrEmptyOrBlank(phoneModel)) {
                jSONObject.put(Defines$Jsonkey.Model.getKey(), phoneModel);
            }
            DisplayMetrics screenDisplay = SystemObserver.getScreenDisplay(this.context_);
            jSONObject.put(Defines$Jsonkey.ScreenDpi.getKey(), screenDisplay.densityDpi);
            jSONObject.put(Defines$Jsonkey.ScreenHeight.getKey(), screenDisplay.heightPixels);
            jSONObject.put(Defines$Jsonkey.ScreenWidth.getKey(), screenDisplay.widthPixels);
            jSONObject.put(Defines$Jsonkey.UIMode.getKey(), SystemObserver.getUIMode(this.context_));
            String os = SystemObserver.getOS(this.context_);
            if (!isNullOrEmptyOrBlank(os)) {
                jSONObject.put(Defines$Jsonkey.OS.getKey(), os);
            }
            jSONObject.put(Defines$Jsonkey.APILevel.getKey(), SystemObserver.getAPILevel());
            if (Branch.getPluginName() != null) {
                jSONObject.put(Defines$Jsonkey.PluginName.getKey(), Branch.getPluginName());
                jSONObject.put(Defines$Jsonkey.PluginVersion.getKey(), Branch.getPluginVersion());
            }
            String iSO2CountryCode = SystemObserver.getISO2CountryCode();
            if (!TextUtils.isEmpty(iSO2CountryCode)) {
                jSONObject.put(Defines$Jsonkey.Country.getKey(), iSO2CountryCode);
            }
            String iSO2LanguageCode = SystemObserver.getISO2LanguageCode();
            if (!TextUtils.isEmpty(iSO2LanguageCode)) {
                jSONObject.put(Defines$Jsonkey.Language.getKey(), iSO2LanguageCode);
            }
            String localIPAddress = SystemObserver.getLocalIPAddress();
            if (!TextUtils.isEmpty(localIPAddress)) {
                jSONObject.put(Defines$Jsonkey.LocalIP.getKey(), localIPAddress);
            }
            if (prefHelper != null) {
                if (!isNullOrEmptyOrBlank(prefHelper.getRandomizedDeviceToken())) {
                    jSONObject.put(Defines$Jsonkey.RandomizedDeviceToken.getKey(), prefHelper.getRandomizedDeviceToken());
                }
                String identity = prefHelper.getIdentity();
                if (!isNullOrEmptyOrBlank(identity)) {
                    jSONObject.put(Defines$Jsonkey.DeveloperIdentity.getKey(), identity);
                }
                String appStoreSource = prefHelper.getAppStoreSource();
                if (!"bnc_no_value".equals(appStoreSource)) {
                    jSONObject.put(Defines$Jsonkey.App_Store.getKey(), appStoreSource);
                }
            }
            jSONObject.put(Defines$Jsonkey.AppVersion.getKey(), getAppVersion());
            jSONObject.put(Defines$Jsonkey.SDK.getKey(), "android");
            jSONObject.put(Defines$Jsonkey.SdkVersion.getKey(), Branch.getSdkVersionNumber());
            setPostUserAgent(jSONObject);
            if (serverRequest instanceof ServerRequestGetLATD) {
                jSONObject.put(Defines$Jsonkey.LATDAttributionWindow.getKey(), ((ServerRequestGetLATD) serverRequest).getAttributionWindow());
            }
            if (serverRequest.isInitializationOrEventRequest()) {
                jSONObject.put(Defines$Jsonkey.CPUType.getKey(), SystemObserver.getCPUType());
                jSONObject.put(Defines$Jsonkey.DeviceBuildId.getKey(), SystemObserver.getDeviceBuildId());
                jSONObject.put(Defines$Jsonkey.Locale.getKey(), SystemObserver.getLocale());
                jSONObject.put(Defines$Jsonkey.ConnectionType.getKey(), SystemObserver.getConnectionType(this.context_));
                jSONObject.put(Defines$Jsonkey.DeviceCarrier.getKey(), SystemObserver.getCarrier(this.context_));
                jSONObject.put(Defines$Jsonkey.OSVersionAndroid.getKey(), SystemObserver.getOSVersion());
            }
        } catch (JSONException e) {
            BranchLogger.w("Caught JSONException" + e.getMessage());
        }
    }

    private void setPostUserAgent(final JSONObject jSONObject) {
        BranchLogger.v("setPostUserAgent " + Thread.currentThread().getName());
        try {
            if (!TextUtils.isEmpty(Branch._userAgentString)) {
                BranchLogger.v("userAgent was cached: " + Branch._userAgentString);
                jSONObject.put(Defines$Jsonkey.UserAgent.getKey(), Branch._userAgentString);
                Branch.getInstance().requestQueue_.unlockProcessWait(ServerRequest.PROCESS_WAIT_LOCK.USER_AGENT_STRING_LOCK);
                Branch.getInstance().requestQueue_.processNextQueueItem("setPostUserAgent");
            } else if (Branch.userAgentSync) {
                BranchLogger.v("Start invoking getUserAgentSync from thread " + Thread.currentThread().getName());
                DeviceSignalsKt.getUserAgentSync(this.context_, new Continuation() {
                    public CoroutineContext getContext() {
                        return EmptyCoroutineContext.INSTANCE;
                    }

                    public void resumeWith(Object obj) {
                        if (obj != null) {
                            Branch._userAgentString = (String) obj;
                            BranchLogger.v("onUserAgentStringFetchFinished getUserAgentSync resumeWith releasing lock");
                            try {
                                jSONObject.put(Defines$Jsonkey.UserAgent.getKey(), Branch._userAgentString);
                            } catch (JSONException e) {
                                BranchLogger.w("Caught JSONException " + e.getMessage());
                            }
                        }
                        Branch.getInstance().requestQueue_.unlockProcessWait(ServerRequest.PROCESS_WAIT_LOCK.USER_AGENT_STRING_LOCK);
                        Branch.getInstance().requestQueue_.processNextQueueItem("onUserAgentStringFetchFinished");
                    }
                });
            } else {
                DeviceSignalsKt.getUserAgentAsync(this.context_, new Continuation() {
                    public CoroutineContext getContext() {
                        return EmptyCoroutineContext.INSTANCE;
                    }

                    public void resumeWith(Object obj) {
                        if (obj != null) {
                            Branch._userAgentString = (String) obj;
                            BranchLogger.v("onUserAgentStringFetchFinished getUserAgentAsync resumeWith releasing lock");
                            try {
                                jSONObject.put(Defines$Jsonkey.UserAgent.getKey(), Branch._userAgentString);
                            } catch (JSONException e) {
                                BranchLogger.w("Caught JSONException " + e.getMessage());
                            }
                        }
                        Branch.getInstance().requestQueue_.unlockProcessWait(ServerRequest.PROCESS_WAIT_LOCK.USER_AGENT_STRING_LOCK);
                        Branch.getInstance().requestQueue_.processNextQueueItem("getUserAgentAsync resumeWith");
                    }
                });
            }
        } catch (Exception e) {
            BranchLogger.w("Caught exception trying to set userAgent " + e.getMessage());
        }
    }

    public String getAppVersion() {
        return SystemObserver.getAppVersion(this.context_);
    }

    public long getFirstInstallTime() {
        return SystemObserver.getFirstInstallTime(this.context_);
    }

    public long getLastUpdateTime() {
        return SystemObserver.getLastUpdateTime(this.context_);
    }

    public boolean isPackageInstalled() {
        return SystemObserver.isPackageInstalled(this.context_);
    }

    public SystemObserver.UniqueId getHardwareID() {
        getSystemObserver();
        return SystemObserver.getUniqueID(this.context_, Branch.isDeviceIDFetchDisabled());
    }

    private class SystemObserverInstance extends SystemObserver {
        public SystemObserverInstance() {
        }
    }

    /* access modifiers changed from: package-private */
    public SystemObserver getSystemObserver() {
        return this.systemObserver_;
    }

    public static boolean isNullOrEmptyOrBlank(String str) {
        return TextUtils.isEmpty(str) || str.equals("bnc_no_value");
    }
}
