package io.branch.referral;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.webkit.URLUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class PrefHelper {
    private static String customCDNBaseURL_ = null;
    private static String customServerURL_ = null;
    public static String fbAppId_ = null;
    private static PrefHelper prefHelper_ = null;
    private static boolean useEUEndpoint_ = false;
    private final SharedPreferences appSharedPrefs_;
    private final JSONObject installMetadata = new JSONObject();
    final BranchPartnerParameters partnerParams_ = new BranchPartnerParameters();
    private SharedPreferences.Editor prefsEditor_;
    private final JSONObject requestMetadata = new JSONObject();
    private final JSONObject secondaryRequestMetadata = new JSONObject();

    private PrefHelper(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("branch_referral_shared_pref", 0);
        this.appSharedPrefs_ = sharedPreferences;
        this.prefsEditor_ = sharedPreferences.edit();
    }

    public static PrefHelper getInstance(Context context) {
        if (prefHelper_ == null) {
            prefHelper_ = new PrefHelper(context);
        }
        return prefHelper_;
    }

    static void setAPIUrl(String str) {
        customServerURL_ = str;
    }

    public String getAPIBaseUrl() {
        if (URLUtil.isHttpsUrl(customServerURL_)) {
            return customServerURL_;
        }
        if (useEUEndpoint_) {
            return "https://api3-eu.branch.io/";
        }
        return "https://api2.branch.io/";
    }

    static String getCDNBaseUrl() {
        if (!TextUtils.isEmpty(customCDNBaseURL_)) {
            return customCDNBaseURL_;
        }
        return "https://cdn.branch.io/";
    }

    public int getTimeout() {
        return getInteger("bnc_timeout", 5500);
    }

    public int getTaskTimeout() {
        return getInteger("bnc_timeout", 5500) + getInteger("bnc_connect_timeout", 10000);
    }

    public int getConnectTimeout() {
        return getInteger("bnc_connect_timeout", 10000);
    }

    public int getRetryCount() {
        return getInteger("bnc_retry_count", 3);
    }

    public int getRetryInterval() {
        return getInteger("bnc_retry_interval", 1000);
    }

    public int getNoConnectionRetryMax() {
        return getInteger("bnc_no_connection_retry_max", 3);
    }

    public void setAppVersion(String str) {
        setString("bnc_app_version", str);
    }

    public String getAppVersion() {
        return getString("bnc_app_version");
    }

    public boolean setBranchKey(String str) {
        if (getString("bnc_branch_key").equals(str)) {
            return false;
        }
        clearPrefOnBranchKeyChange();
        setString("bnc_branch_key", str);
        if (Branch.getInstance() == null) {
            return true;
        }
        Branch.getInstance().linkCache_.clear();
        Branch.getInstance().requestQueue_.clear();
        return true;
    }

    public String getBranchKey() {
        return getString("bnc_branch_key");
    }

    public void setRandomizedDeviceToken(String str) {
        setString("bnc_randomized_device_token", str);
    }

    public String getRandomizedDeviceToken() {
        String string = getString("bnc_randomized_device_token");
        if (TextUtils.isEmpty(string) || string.equals("bnc_no_value")) {
            return getString("bnc_device_fingerprint_id");
        }
        return string;
    }

    public void setSessionID(String str) {
        setString("bnc_session_id", str);
    }

    public String getSessionID() {
        return getString("bnc_session_id");
    }

    public void setRandomizedBundleToken(String str) {
        setString("bnc_randomized_bundle_token", str);
    }

    public String getRandomizedBundleToken() {
        String string = getString("bnc_randomized_bundle_token");
        if (TextUtils.isEmpty(string) || string.equals("bnc_no_value")) {
            return getString("bnc_identity_id");
        }
        return string;
    }

    public void setIdentity(String str) {
        setString("bnc_identity", str);
    }

    public String getIdentity() {
        return getString("bnc_identity");
    }

    public void setLinkClickID(String str) {
        setString("bnc_link_click_id", str);
    }

    public void setRandomlyGeneratedUuid(String str) {
        setString("bnc_randomly_generated_uuid", str);
    }

    public String getRandomlyGeneratedUuid() {
        return getString("bnc_randomly_generated_uuid");
    }

    public void setAnonID(String str) {
        setString("bnc_anon_id", str);
    }

    public String getAnonID() {
        return getString("bnc_anon_id");
    }

    public String getLinkClickID() {
        return getString("bnc_link_click_id");
    }

    public boolean getAdNetworkCalloutsDisabled() {
        return getBool("bnc_ad_network_callouts_disabled");
    }

    public void setExternalIntentUri(String str) {
        setString("bnc_external_intent_uri", str);
    }

    public String getExternalIntentUri() {
        return getString("bnc_external_intent_uri");
    }

    public void setExternalIntentExtra(String str) {
        setString("bnc_external_intent_extra", str);
    }

    public String getExternalIntentExtra() {
        return getString("bnc_external_intent_extra");
    }

    public void setLinkClickIdentifier(String str) {
        setString("bnc_link_click_identifier", str);
    }

    public String getLinkClickIdentifier() {
        return getString("bnc_link_click_identifier");
    }

    public void setGoogleSearchInstallIdentifier(String str) {
        setString("bnc_google_search_install_identifier", str);
    }

    public String getGoogleSearchInstallIdentifier() {
        return getString("bnc_google_search_install_identifier");
    }

    public void setAppStoreReferrer(String str) {
        setString("bnc_google_play_install_referrer_extras", str);
    }

    public String getAppStoreReferrer() {
        return getString("bnc_google_play_install_referrer_extras");
    }

    public void setAppStoreSource(String str) {
        if (!TextUtils.isEmpty(str)) {
            setString("bnc_app_store_source", str);
        }
    }

    public String getAppStoreSource() {
        return getString("bnc_app_store_source");
    }

    public void setIsMetaClickThrough(boolean z) {
        setBool("bnc_is_meta_clickthrough", Boolean.valueOf(z));
    }

    public boolean getIsMetaClickThrough() {
        return getBool("bnc_is_meta_clickthrough");
    }

    public void setReferringUrlQueryParameters(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() == 0) {
            setString("bnc_referringUrlQueryParameters", "bnc_no_value");
        } else {
            setString("bnc_referringUrlQueryParameters", jSONObject.toString());
        }
    }

    public JSONObject getReferringURLQueryParameters() {
        String string = getString("bnc_referringUrlQueryParameters");
        JSONObject jSONObject = new JSONObject();
        if (TextUtils.isEmpty(string) || "bnc_no_value".equals(string)) {
            return jSONObject;
        }
        try {
            return new JSONObject(string);
        } catch (JSONException e) {
            BranchLogger.w("Unable to get URL query parameters as string: " + e);
            return jSONObject;
        }
    }

    public String getReferrerGclid() {
        String string = getString("bnc_gclid_json_object");
        if (string.equals("bnc_no_value")) {
            return "bnc_no_value";
        }
        try {
            JSONObject jSONObject = new JSONObject(string);
            if (((Long) jSONObject.get("bnc_gclid_expiration_date")).longValue() - System.currentTimeMillis() > 0) {
                return jSONObject.getString("bnc_gclid_value");
            }
            removePrefValue("bnc_gclid_json_object");
            return null;
        } catch (JSONException e) {
            removePrefValue("bnc_gclid_json_object");
            e.printStackTrace();
            return null;
        }
    }

    public void clearGclid() {
        removePrefValue("bnc_gclid_json_object");
    }

    public long getReferrerGclidValidForWindow() {
        return getLong("bnc_gclid_expiration_window", 2592000000L);
    }

    public void setAppLink(String str) {
        setString("bnc_app_link", str);
    }

    public String getAppLink() {
        return getString("bnc_app_link");
    }

    public void setIsFullAppConversion(boolean z) {
        setBool("bnc_is_full_app_conversion", Boolean.valueOf(z));
    }

    public boolean isFullAppConversion() {
        return getBool("bnc_is_full_app_conversion");
    }

    public void setPushIdentifier(String str) {
        setString("bnc_push_identifier", str);
    }

    public String getPushIdentifier() {
        return getString("bnc_push_identifier");
    }

    public String getSessionParams() {
        return getString("bnc_session_params");
    }

    public void setSessionParams(String str) {
        setString("bnc_session_params", str);
    }

    public String getInstallParams() {
        return getString("bnc_install_params");
    }

    public void setInstallParams(String str) {
        setString("bnc_install_params", str);
    }

    public void setInstallReferrerParams(String str) {
        setString("bnc_install_referrer", str);
    }

    public void setUserURL(String str) {
        setString("bnc_user_url", str);
    }

    public String getUserURL() {
        return getString("bnc_user_url");
    }

    /* access modifiers changed from: package-private */
    public boolean isAppTrackingLimited() {
        return getBool("bnc_limit_facebook_tracking");
    }

    /* access modifiers changed from: package-private */
    public boolean isDMAParamsInitialized() {
        return hasPrefValue("bnc_dma_eea");
    }

    /* access modifiers changed from: package-private */
    public void setEEARegion(boolean z) {
        setBool("bnc_dma_eea", Boolean.valueOf(z));
    }

    /* access modifiers changed from: package-private */
    public boolean getEEARegion() {
        return getBool("bnc_dma_eea");
    }

    /* access modifiers changed from: package-private */
    public void setAdPersonalizationConsent(boolean z) {
        setBool("bnc_dma_ad_personalization", Boolean.valueOf(z));
    }

    /* access modifiers changed from: package-private */
    public boolean getAdPersonalizationConsent() {
        return getBool("bnc_dma_ad_personalization");
    }

    /* access modifiers changed from: package-private */
    public void setAdUserDataUsageConsent(boolean z) {
        setBool("bnc_dma_ad_user_data", Boolean.valueOf(z));
    }

    /* access modifiers changed from: package-private */
    public boolean getAdUserDataUsageConsent() {
        return getBool("bnc_dma_ad_user_data");
    }

    public void clearUserValues() {
        Iterator it = getActions().iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            setActionTotalCount(str, 0);
            setActionUniqueCount(str, 0);
        }
        setActions(new ArrayList());
    }

    private ArrayList getActions() {
        String string = getString("bnc_actions");
        if (string.equals("bnc_no_value")) {
            return new ArrayList();
        }
        return deserializeString(string);
    }

    private void setActions(ArrayList arrayList) {
        if (arrayList.size() == 0) {
            setString("bnc_actions", "bnc_no_value");
        } else {
            setString("bnc_actions", serializeArrayList(arrayList));
        }
    }

    public void setActionTotalCount(String str, int i) {
        ArrayList actions = getActions();
        if (!actions.contains(str)) {
            actions.add(str);
            setActions(actions);
        }
        setInteger("bnc_total_base_" + str, i);
    }

    public void setActionUniqueCount(String str, int i) {
        setInteger("bnc_balance_base_" + str, i);
    }

    public void setInitialReferrer(String str) {
        setString("bnc_initial_referrer", str);
    }

    public String getInitialReferrer() {
        return getString("bnc_initial_referrer");
    }

    private String serializeArrayList(ArrayList arrayList) {
        Iterator it = arrayList.iterator();
        String str = "";
        while (it.hasNext()) {
            str = str + ((String) it.next()) + ",";
        }
        return str.substring(0, str.length() - 1);
    }

    private ArrayList deserializeString(String str) {
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, str.split(","));
        return arrayList;
    }

    public boolean hasPrefValue(String str) {
        return this.appSharedPrefs_.contains(str);
    }

    public void removePrefValue(String str) {
        this.prefsEditor_.remove(str).apply();
    }

    public int getInteger(String str, int i) {
        return this.appSharedPrefs_.getInt(str, i);
    }

    public long getLong(String str) {
        return getLong(str, 0);
    }

    public long getLong(String str, long j) {
        return this.appSharedPrefs_.getLong(str, j);
    }

    public String getString(String str) {
        return this.appSharedPrefs_.getString(str, "bnc_no_value");
    }

    public boolean getBool(String str) {
        return this.appSharedPrefs_.getBoolean(str, false);
    }

    public void setInteger(String str, int i) {
        this.prefsEditor_.putInt(str, i).apply();
    }

    public void setLong(String str, long j) {
        this.prefsEditor_.putLong(str, j).apply();
    }

    public void setString(String str, String str2) {
        this.prefsEditor_.putString(str, str2).apply();
    }

    public void setBool(String str, Boolean bool) {
        this.prefsEditor_.putBoolean(str, bool.booleanValue()).apply();
    }

    private void clearPrefOnBranchKeyChange() {
        String linkClickID = getLinkClickID();
        String linkClickIdentifier = getLinkClickIdentifier();
        String appLink = getAppLink();
        String pushIdentifier = getPushIdentifier();
        this.prefsEditor_.clear();
        setLinkClickID(linkClickID);
        setLinkClickIdentifier(linkClickIdentifier);
        setAppLink(appLink);
        setPushIdentifier(pushIdentifier);
        this.prefsEditor_.apply();
    }

    public void setRequestMetadata(String str, String str2) {
        if (str != null) {
            if (this.requestMetadata.has(str) && str2 == null) {
                this.requestMetadata.remove(str);
            }
            try {
                this.requestMetadata.put(str, str2);
            } catch (JSONException unused) {
            }
        }
    }

    public JSONObject getRequestMetadata() {
        return this.requestMetadata;
    }

    /* access modifiers changed from: package-private */
    public void addInstallMetadata(String str, String str2) {
        if (str != null) {
            try {
                this.installMetadata.putOpt(str, str2);
            } catch (JSONException e) {
                BranchLogger.d(e.getMessage());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public String getInstallMetaData(String str) {
        if (str == null) {
            return null;
        }
        try {
            return this.installMetadata.get(str).toString();
        } catch (JSONException e) {
            BranchLogger.d(e.getMessage());
            return null;
        }
    }

    public JSONObject getInstallMetadata() {
        return this.installMetadata;
    }

    public void loadPartnerParams(JSONObject jSONObject) {
        loadPartnerParams(jSONObject, this.partnerParams_);
    }

    static void loadPartnerParams(JSONObject jSONObject, BranchPartnerParameters branchPartnerParameters) {
        if (jSONObject != null) {
            JSONObject jSONObject2 = new JSONObject();
            for (Map.Entry entry : branchPartnerParameters.allParams().entrySet()) {
                JSONObject jSONObject3 = new JSONObject();
                for (Map.Entry entry2 : ((ConcurrentHashMap) entry.getValue()).entrySet()) {
                    jSONObject3.put((String) entry2.getKey(), entry2.getValue());
                }
                jSONObject2.put((String) entry.getKey(), jSONObject3);
            }
            jSONObject.put(Defines$Jsonkey.PartnerData.getKey(), jSONObject2);
        }
    }
}
