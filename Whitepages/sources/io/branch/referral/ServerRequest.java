package io.branch.referral;

import android.content.Context;
import android.text.TextUtils;
import io.branch.referral.SystemObserver;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ServerRequest {
    private static final Defines$RequestPath[] initializationAndEventRoutes = {Defines$RequestPath.RegisterInstall, Defines$RequestPath.RegisterOpen, Defines$RequestPath.ContentEvent, Defines$RequestPath.TrackStandardEvent, Defines$RequestPath.TrackCustomEvent};
    public boolean constructError_ = false;
    private final Context context_;
    public int currentRetryCount = 0;
    private final Set locks_;
    private JSONObject params_;
    protected final PrefHelper prefHelper_;
    private long queueWaitTime_ = 0;
    final Defines$RequestPath requestPath_;

    public enum BRANCH_API_VERSION {
        V1,
        V1_LATD,
        V2
    }

    public enum PROCESS_WAIT_LOCK {
        SDK_INIT_WAIT_LOCK,
        GAID_FETCH_WAIT_LOCK,
        INTENT_PENDING_WAIT_LOCK,
        USER_SET_WAIT_LOCK,
        INSTALL_REFERRER_FETCH_WAIT_LOCK,
        USER_AGENT_STRING_LOCK
    }

    public abstract void clearCallbacks();

    public abstract void handleFailure(int i, String str);

    public boolean isGAdsParamsRequired() {
        return true;
    }

    public abstract boolean isGetRequest();

    /* access modifiers changed from: package-private */
    public boolean isPersistable() {
        return true;
    }

    public abstract void onRequestSucceeded(ServerResponse serverResponse, Branch branch);

    /* access modifiers changed from: protected */
    public boolean prepareExecuteWithoutTracking() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean shouldAddDMAParams() {
        return false;
    }

    public boolean shouldRetryOnFail() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean shouldUpdateLimitFacebookTracking() {
        return false;
    }

    public ServerRequest(Context context, Defines$RequestPath defines$RequestPath) {
        this.context_ = context;
        this.requestPath_ = defines$RequestPath;
        this.prefHelper_ = PrefHelper.getInstance(context);
        this.params_ = new JSONObject();
        this.locks_ = new HashSet();
    }

    protected ServerRequest(Defines$RequestPath defines$RequestPath, JSONObject jSONObject, Context context) {
        this.context_ = context;
        this.requestPath_ = defines$RequestPath;
        this.params_ = jSONObject;
        this.prefHelper_ = PrefHelper.getInstance(context);
        this.locks_ = new HashSet();
    }

    /* access modifiers changed from: package-private */
    public void addDMAParams() {
        if (this.prefHelper_.isDMAParamsInitialized()) {
            try {
                if (getBranchRemoteAPIVersion() == BRANCH_API_VERSION.V1) {
                    this.params_.put(Defines$Jsonkey.DMA_EEA.getKey(), this.prefHelper_.getEEARegion());
                    this.params_.put(Defines$Jsonkey.DMA_Ad_Personalization.getKey(), this.prefHelper_.getAdPersonalizationConsent());
                    this.params_.put(Defines$Jsonkey.DMA_Ad_User_Data.getKey(), this.prefHelper_.getAdUserDataUsageConsent());
                    return;
                }
                JSONObject optJSONObject = this.params_.optJSONObject(Defines$Jsonkey.UserData.getKey());
                if (optJSONObject != null) {
                    optJSONObject.put(Defines$Jsonkey.DMA_EEA.getKey(), this.prefHelper_.getEEARegion());
                    optJSONObject.put(Defines$Jsonkey.DMA_Ad_Personalization.getKey(), this.prefHelper_.getAdPersonalizationConsent());
                    optJSONObject.put(Defines$Jsonkey.DMA_Ad_User_Data.getKey(), this.prefHelper_.getAdUserDataUsageConsent());
                }
            } catch (JSONException e) {
                BranchLogger.d(e.getMessage());
            }
        }
    }

    public final String getRequestPath() {
        return this.requestPath_.getPath();
    }

    public String getRequestUrl() {
        return this.prefHelper_.getAPIBaseUrl() + this.requestPath_.getPath();
    }

    /* access modifiers changed from: protected */
    public void setPost(JSONObject jSONObject) {
        this.params_ = jSONObject;
        if (getBranchRemoteAPIVersion() == BRANCH_API_VERSION.V1) {
            DeviceInfo.getInstance().updateRequestWithV1Params(this, this.params_);
        } else {
            JSONObject jSONObject2 = new JSONObject();
            this.params_.put(Defines$Jsonkey.UserData.getKey(), jSONObject2);
            DeviceInfo.getInstance().updateRequestWithV2Params(this, this.prefHelper_, jSONObject2);
        }
        this.params_.put(Defines$Jsonkey.Debug.getKey(), Branch.isDeviceIDFetchDisabled());
    }

    public JSONObject getPost() {
        return this.params_;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0058, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        io.branch.referral.BranchLogger.w("Caught JSONException " + r6.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return r5.params_;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        return r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[ExcHandler: ConcurrentModificationException (unused java.util.ConcurrentModificationException), SYNTHETIC, Splitter:B:1:0x0005] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject getPostWithInstrumentationValues(java.util.concurrent.ConcurrentHashMap r6) {
        /*
            r5 = this;
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            org.json.JSONObject r1 = r5.params_     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x007d }
            if (r1 == 0) goto L_0x002e
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x007d }
            org.json.JSONObject r2 = r5.params_     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x007d }
            java.lang.String r2 = r2.toString()     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x007d }
            r1.<init>(r2)     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x007d }
            java.util.Iterator r2 = r1.keys()     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x007d }
        L_0x0018:
            boolean r3 = r2.hasNext()     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x007d }
            if (r3 == 0) goto L_0x002e
            java.lang.Object r3 = r2.next()     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x007d }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x007d }
            java.lang.Object r4 = r1.get(r3)     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x007d }
            r0.put(r3, r4)     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x007d }
            goto L_0x0018
        L_0x002c:
            r6 = move-exception
            goto L_0x0080
        L_0x002e:
            int r1 = r6.size()     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x007d }
            if (r1 <= 0) goto L_0x0087
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x007d }
            r1.<init>()     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x007d }
            java.util.Set r2 = r6.keySet()     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x007d }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ JSONException -> 0x0058, ConcurrentModificationException -> 0x007d }
        L_0x0041:
            boolean r3 = r2.hasNext()     // Catch:{ JSONException -> 0x0058, ConcurrentModificationException -> 0x007d }
            if (r3 == 0) goto L_0x005a
            java.lang.Object r3 = r2.next()     // Catch:{ JSONException -> 0x0058, ConcurrentModificationException -> 0x007d }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ JSONException -> 0x0058, ConcurrentModificationException -> 0x007d }
            java.lang.Object r4 = r6.get(r3)     // Catch:{ JSONException -> 0x0058, ConcurrentModificationException -> 0x007d }
            r1.put(r3, r4)     // Catch:{ JSONException -> 0x0058, ConcurrentModificationException -> 0x007d }
            r6.remove(r3)     // Catch:{ JSONException -> 0x0058, ConcurrentModificationException -> 0x007d }
            goto L_0x0041
        L_0x0058:
            r6 = move-exception
            goto L_0x0064
        L_0x005a:
            io.branch.referral.Defines$Jsonkey r6 = io.branch.referral.Defines$Jsonkey.Branch_Instrumentation     // Catch:{ JSONException -> 0x0058, ConcurrentModificationException -> 0x007d }
            java.lang.String r6 = r6.getKey()     // Catch:{ JSONException -> 0x0058, ConcurrentModificationException -> 0x007d }
            r0.put(r6, r1)     // Catch:{ JSONException -> 0x0058, ConcurrentModificationException -> 0x007d }
            goto L_0x0087
        L_0x0064:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x007d }
            r1.<init>()     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x007d }
            java.lang.String r2 = "Caught JSONException "
            r1.append(r2)     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x007d }
            java.lang.String r6 = r6.getMessage()     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x007d }
            r1.append(r6)     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x007d }
            java.lang.String r6 = r1.toString()     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x007d }
            io.branch.referral.BranchLogger.w(r6)     // Catch:{ JSONException -> 0x002c, ConcurrentModificationException -> 0x007d }
            goto L_0x0087
        L_0x007d:
            org.json.JSONObject r0 = r5.params_
            goto L_0x0087
        L_0x0080:
            java.lang.String r6 = r6.getMessage()
            io.branch.referral.BranchLogger.d(r6)
        L_0x0087:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.ServerRequest.getPostWithInstrumentationValues(java.util.concurrent.ConcurrentHashMap):org.json.JSONObject");
    }

    public JSONObject getGetParams() {
        return this.params_;
    }

    public JSONObject toJSON() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("REQ_POST", this.params_);
            jSONObject.put("REQ_POST_PATH", this.requestPath_.getPath());
            return jSONObject;
        } catch (JSONException e) {
            BranchLogger.w("Caught JSONException " + e.getMessage());
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0034 A[Catch:{ JSONException -> 0x0039 }] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0057 A[Catch:{ JSONException -> 0x005c }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x007e A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static io.branch.referral.ServerRequest fromJSON(org.json.JSONObject r7, android.content.Context r8) {
        /*
            java.lang.String r0 = "INITIATED_BY_CLIENT"
            java.lang.String r1 = "REQ_POST_PATH"
            java.lang.String r2 = "REQ_POST"
            java.lang.String r3 = "Caught JSONException "
            java.lang.String r4 = ""
            r5 = 0
            boolean r6 = r7.has(r2)     // Catch:{ JSONException -> 0x0016 }
            if (r6 == 0) goto L_0x002d
            org.json.JSONObject r2 = r7.getJSONObject(r2)     // Catch:{ JSONException -> 0x0016 }
            goto L_0x002e
        L_0x0016:
            r2 = move-exception
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r3)
            java.lang.String r2 = r2.getMessage()
            r6.append(r2)
            java.lang.String r2 = r6.toString()
            io.branch.referral.BranchLogger.w(r2)
        L_0x002d:
            r2 = r5
        L_0x002e:
            boolean r6 = r7.has(r1)     // Catch:{ JSONException -> 0x0039 }
            if (r6 == 0) goto L_0x0050
            java.lang.String r4 = r7.getString(r1)     // Catch:{ JSONException -> 0x0039 }
            goto L_0x0050
        L_0x0039:
            r1 = move-exception
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r3)
            java.lang.String r1 = r1.getMessage()
            r6.append(r1)
            java.lang.String r1 = r6.toString()
            io.branch.referral.BranchLogger.w(r1)
        L_0x0050:
            r1 = 1
            boolean r6 = r7.has(r0)     // Catch:{ JSONException -> 0x005c }
            if (r6 == 0) goto L_0x0073
            boolean r1 = r7.getBoolean(r0)     // Catch:{ JSONException -> 0x005c }
            goto L_0x0073
        L_0x005c:
            r7 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r3)
            java.lang.String r7 = r7.getMessage()
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            io.branch.referral.BranchLogger.w(r7)
        L_0x0073:
            boolean r7 = android.text.TextUtils.isEmpty(r4)
            if (r7 != 0) goto L_0x007e
            io.branch.referral.ServerRequest r7 = getExtendedServerRequest(r4, r2, r8, r1)
            return r7
        L_0x007e:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.ServerRequest.fromJSON(org.json.JSONObject, android.content.Context):io.branch.referral.ServerRequest");
    }

    private static ServerRequest getExtendedServerRequest(String str, JSONObject jSONObject, Context context, boolean z) {
        Defines$RequestPath defines$RequestPath = Defines$RequestPath.GetURL;
        if (str.equalsIgnoreCase(defines$RequestPath.getPath())) {
            return new ServerRequestCreateUrl(defines$RequestPath, jSONObject, context);
        }
        Defines$RequestPath defines$RequestPath2 = Defines$RequestPath.RegisterInstall;
        if (str.equalsIgnoreCase(defines$RequestPath2.getPath())) {
            return new ServerRequestRegisterInstall(defines$RequestPath2, jSONObject, context, z);
        }
        Defines$RequestPath defines$RequestPath3 = Defines$RequestPath.RegisterOpen;
        if (str.equalsIgnoreCase(defines$RequestPath3.getPath())) {
            return new ServerRequestRegisterOpen(defines$RequestPath3, jSONObject, context, z);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void updateGAdsParams() {
        BRANCH_API_VERSION branchRemoteAPIVersion = getBranchRemoteAPIVersion();
        int lATVal = DeviceInfo.getInstance().getSystemObserver().getLATVal();
        String aid = DeviceInfo.getInstance().getSystemObserver().getAID();
        if (!TextUtils.isEmpty(aid)) {
            updateAdvertisingIdsObject(aid);
            replaceHardwareIdOnValidAdvertisingId();
        }
        try {
            if (branchRemoteAPIVersion == BRANCH_API_VERSION.V1) {
                this.params_.put(Defines$Jsonkey.LATVal.getKey(), lATVal);
                if (!TextUtils.isEmpty(aid)) {
                    if (!SystemObserver.isHuaweiMobileServicesAvailable(this.context_)) {
                        this.params_.put(Defines$Jsonkey.GoogleAdvertisingID.getKey(), aid);
                    }
                    this.params_.remove(Defines$Jsonkey.UnidentifiedDevice.getKey());
                } else if (!payloadContainsDeviceIdentifiers(this.params_)) {
                    JSONObject jSONObject = this.params_;
                    Defines$Jsonkey defines$Jsonkey = Defines$Jsonkey.UnidentifiedDevice;
                    if (!jSONObject.optBoolean(defines$Jsonkey.getKey())) {
                        this.params_.put(defines$Jsonkey.getKey(), true);
                    }
                }
            } else {
                JSONObject optJSONObject = this.params_.optJSONObject(Defines$Jsonkey.UserData.getKey());
                if (optJSONObject != null) {
                    optJSONObject.put(Defines$Jsonkey.LimitedAdTracking.getKey(), lATVal);
                    if (!TextUtils.isEmpty(aid)) {
                        if (!SystemObserver.isHuaweiMobileServicesAvailable(this.context_)) {
                            optJSONObject.put(Defines$Jsonkey.AAID.getKey(), aid);
                        }
                        optJSONObject.remove(Defines$Jsonkey.UnidentifiedDevice.getKey());
                    } else if (!payloadContainsDeviceIdentifiers(optJSONObject)) {
                        Defines$Jsonkey defines$Jsonkey2 = Defines$Jsonkey.UnidentifiedDevice;
                        if (!optJSONObject.optBoolean(defines$Jsonkey2.getKey())) {
                            optJSONObject.put(defines$Jsonkey2.getKey(), true);
                        }
                    }
                }
            }
        } catch (JSONException e) {
            BranchLogger.w("Caught JSONException " + e.getMessage());
        }
    }

    private void updateAdvertisingIdsObject(String str) {
        String str2;
        try {
            if (SystemObserver.isFireOSDevice()) {
                str2 = Defines$Jsonkey.FireAdId.getKey();
            } else if (SystemObserver.isHuaweiMobileServicesAvailable(Branch.getInstance().getApplicationContext())) {
                str2 = Defines$Jsonkey.OpenAdvertisingID.getKey();
            } else {
                str2 = Defines$Jsonkey.AAID.getKey();
            }
            this.params_.put(Defines$Jsonkey.AdvertisingIDs.getKey(), new JSONObject().put(str2, str));
        } catch (JSONException e) {
            BranchLogger.w("Caught JSONException " + e.getMessage());
        }
    }

    private void replaceHardwareIdOnValidAdvertisingId() {
        try {
            SystemObserver.UniqueId hardwareID = DeviceInfo.getInstance().getHardwareID();
            this.params_.put(Defines$Jsonkey.HardwareID.getKey(), hardwareID.getId());
            this.params_.put(Defines$Jsonkey.IsHardwareIDReal.getKey(), hardwareID.isReal());
            JSONObject jSONObject = this.params_;
            Defines$Jsonkey defines$Jsonkey = Defines$Jsonkey.UserData;
            if (jSONObject.has(defines$Jsonkey.getKey())) {
                JSONObject jSONObject2 = this.params_.getJSONObject(defines$Jsonkey.getKey());
                Defines$Jsonkey defines$Jsonkey2 = Defines$Jsonkey.AndroidID;
                if (jSONObject2.has(defines$Jsonkey2.getKey())) {
                    jSONObject2.put(defines$Jsonkey2.getKey(), hardwareID.getId());
                }
            }
        } catch (JSONException e) {
            BranchLogger.w("Caught JSONException " + e.getMessage());
        }
    }

    private boolean payloadContainsDeviceIdentifiers(JSONObject jSONObject) {
        return jSONObject.has(Defines$Jsonkey.AndroidID.getKey()) || jSONObject.has(Defines$Jsonkey.RandomizedDeviceToken.getKey());
    }

    private void updateDeviceInfo() {
        JSONObject optJSONObject;
        if (getBranchRemoteAPIVersion() == BRANCH_API_VERSION.V2 && (optJSONObject = this.params_.optJSONObject(Defines$Jsonkey.UserData.getKey())) != null) {
            try {
                optJSONObject.put(Defines$Jsonkey.DeveloperIdentity.getKey(), this.prefHelper_.getIdentity());
                optJSONObject.put(Defines$Jsonkey.RandomizedDeviceToken.getKey(), this.prefHelper_.getRandomizedDeviceToken());
            } catch (JSONException e) {
                BranchLogger.w("Caught JSONException " + e.getMessage());
            }
        }
    }

    private void updateRequestMetadata() {
        try {
            JSONObject jSONObject = new JSONObject();
            Iterator<String> keys = this.prefHelper_.getRequestMetadata().keys();
            while (keys.hasNext()) {
                String next = keys.next();
                jSONObject.put(next, this.prefHelper_.getRequestMetadata().get(next));
            }
            JSONObject optJSONObject = this.params_.optJSONObject(Defines$Jsonkey.Metadata.getKey());
            if (optJSONObject != null) {
                Iterator<String> keys2 = optJSONObject.keys();
                while (keys2.hasNext()) {
                    String next2 = keys2.next();
                    jSONObject.put(next2, optJSONObject.get(next2));
                }
            }
            if ((this instanceof ServerRequestRegisterInstall) && this.prefHelper_.getInstallMetadata().length() > 0) {
                Iterator<String> keys3 = this.prefHelper_.getInstallMetadata().keys();
                while (keys3.hasNext()) {
                    String next3 = keys3.next();
                    this.params_.putOpt(next3, this.prefHelper_.getInstallMetadata().get(next3));
                }
            }
            this.params_.put(Defines$Jsonkey.Metadata.getKey(), jSONObject);
        } catch (JSONException e) {
            BranchLogger.w("Caught JSONException. Could not merge metadata, ignoring user metadata. " + e.getMessage());
        }
    }

    private void updateLimitFacebookTracking() {
        boolean isAppTrackingLimited;
        JSONObject optJSONObject = getBranchRemoteAPIVersion() == BRANCH_API_VERSION.V1 ? this.params_ : this.params_.optJSONObject(Defines$Jsonkey.UserData.getKey());
        if (optJSONObject != null && (isAppTrackingLimited = this.prefHelper_.isAppTrackingLimited())) {
            try {
                optJSONObject.putOpt(Defines$Jsonkey.limitFacebookTracking.getKey(), Boolean.valueOf(isAppTrackingLimited));
            } catch (JSONException e) {
                BranchLogger.w("Caught JSONException " + e.getMessage());
            }
        }
    }

    private void updateDisableAdNetworkCallouts() {
        boolean adNetworkCalloutsDisabled;
        JSONObject optJSONObject = getBranchRemoteAPIVersion() == BRANCH_API_VERSION.V1 ? this.params_ : this.params_.optJSONObject(Defines$Jsonkey.UserData.getKey());
        if (optJSONObject != null && (adNetworkCalloutsDisabled = this.prefHelper_.getAdNetworkCalloutsDisabled())) {
            try {
                optJSONObject.putOpt(Defines$Jsonkey.DisableAdNetworkCallouts.getKey(), Boolean.valueOf(adNetworkCalloutsDisabled));
            } catch (JSONException e) {
                BranchLogger.w("Caught JSONException " + e.getMessage());
            }
        }
    }

    private boolean prioritizeLinkAttribution(JSONObject jSONObject) {
        return Branch.isReferringLinkAttributionForPreinstalledAppsEnabled() && jSONObject.has(Defines$Jsonkey.LinkIdentifier.getKey());
    }

    private void removePreinstallData(JSONObject jSONObject) {
        jSONObject.remove(Defines$PreinstallKey.partner.getKey());
        jSONObject.remove(Defines$PreinstallKey.campaign.getKey());
        jSONObject.remove(Defines$Jsonkey.GooglePlayInstallReferrer.getKey());
    }

    /* access modifiers changed from: package-private */
    public void doFinalUpdateOnMainThread() {
        updateRequestMetadata();
        if (shouldUpdateLimitFacebookTracking()) {
            updateLimitFacebookTracking();
        }
        if (shouldAddDMAParams()) {
            addDMAParams();
        }
    }

    /* access modifiers changed from: package-private */
    public void doFinalUpdateOnBackgroundThread() {
        if (this instanceof ServerRequestInitSession) {
            ((ServerRequestInitSession) this).updateLinkReferrerParams();
            if (prioritizeLinkAttribution(this.params_)) {
                removePreinstallData(this.params_);
            }
        }
        updateDeviceInfo();
        updateDisableAdNetworkCallouts();
        if (isGAdsParamsRequired()) {
            updateGAdsParams();
        }
    }

    /* access modifiers changed from: protected */
    public boolean doesAppHasInternetPermission(Context context) {
        int checkCallingOrSelfPermission = context.checkCallingOrSelfPermission("android.permission.INTERNET");
        if (checkCallingOrSelfPermission != 0) {
            BranchLogger.v("Trouble executing your request. Please add 'android.permission.INTERNET' in your applications manifest file");
        }
        return checkCallingOrSelfPermission == 0;
    }

    public void onRequestQueued() {
        this.queueWaitTime_ = System.currentTimeMillis();
    }

    public void addProcessWaitLock(PROCESS_WAIT_LOCK process_wait_lock) {
        if (process_wait_lock != null) {
            this.locks_.add(process_wait_lock);
        }
    }

    public void removeProcessWaitLock(PROCESS_WAIT_LOCK process_wait_lock) {
        this.locks_.remove(process_wait_lock);
    }

    public String printWaitLocks() {
        return Arrays.toString(this.locks_.toArray());
    }

    public boolean isWaitingOnProcessToFinish() {
        return this.locks_.size() > 0;
    }

    public void onPreExecute() {
        BranchLogger.v("onPreExecute " + this);
        if ((this instanceof ServerRequestRegisterOpen) || (this instanceof ServerRequestLogEvent)) {
            try {
                ReferringUrlUtility referringUrlUtility = new ReferringUrlUtility(this.prefHelper_);
                referringUrlUtility.parseReferringURL(this.prefHelper_.getExternalIntentUri());
                JSONObject uRLQueryParamsForRequest = referringUrlUtility.getURLQueryParamsForRequest(this);
                Iterator<String> keys = uRLQueryParamsForRequest.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    this.params_.put(next, uRLQueryParamsForRequest.get(next));
                }
            } catch (Exception e) {
                BranchLogger.e("Caught exception in onPreExecute: " + e.getMessage() + " stacktrace " + BranchLogger.stackTraceToString(e));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void updateEnvironment(Context context, JSONObject jSONObject) {
        try {
            String key = (DeviceInfo.getInstance().isPackageInstalled() ? Defines$Jsonkey.NativeApp : Defines$Jsonkey.InstantApp).getKey();
            if (getBranchRemoteAPIVersion() == BRANCH_API_VERSION.V2) {
                JSONObject optJSONObject = jSONObject.optJSONObject(Defines$Jsonkey.UserData.getKey());
                if (optJSONObject != null) {
                    optJSONObject.put(Defines$Jsonkey.Environment.getKey(), key);
                    return;
                }
                return;
            }
            jSONObject.put(Defines$Jsonkey.Environment.getKey(), key);
        } catch (Exception e) {
            BranchLogger.d(e.getMessage());
        }
    }

    public BRANCH_API_VERSION getBranchRemoteAPIVersion() {
        return BRANCH_API_VERSION.V1;
    }

    /* access modifiers changed from: package-private */
    public boolean isInitializationOrEventRequest() {
        for (Defines$RequestPath equals : initializationAndEventRoutes) {
            if (equals.equals(this.requestPath_)) {
                return true;
            }
        }
        return false;
    }
}
