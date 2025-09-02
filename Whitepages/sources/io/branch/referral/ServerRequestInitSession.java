package io.branch.referral;

import android.content.Context;
import android.text.TextUtils;
import com.salesforce.marketingcloud.sfmcsdk.components.http.NetworkManager;
import io.branch.coroutines.DeviceSignalsKt;
import io.branch.referral.Branch;
import io.branch.referral.ServerRequest;
import io.branch.referral.validators.DeepLinkRoutingValidator;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import org.json.JSONException;
import org.json.JSONObject;

abstract class ServerRequestInitSession extends ServerRequest {
    Branch.BranchReferralInitListener callback_;
    private final Context context_;
    boolean initiatedByClient;

    /* access modifiers changed from: protected */
    public boolean shouldAddDMAParams() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean shouldUpdateLimitFacebookTracking() {
        return true;
    }

    ServerRequestInitSession(Context context, Defines$RequestPath defines$RequestPath, boolean z) {
        super(context, defines$RequestPath);
        this.context_ = context;
        this.initiatedByClient = !z;
    }

    ServerRequestInitSession(Defines$RequestPath defines$RequestPath, JSONObject jSONObject, Context context, boolean z) {
        super(defines$RequestPath, jSONObject, context);
        this.context_ = context;
        this.initiatedByClient = !z;
    }

    /* access modifiers changed from: protected */
    public void setPost(JSONObject jSONObject) {
        super.setPost(jSONObject);
        this.prefHelper_.loadPartnerParams(jSONObject);
        String appVersion = DeviceInfo.getInstance().getAppVersion();
        if (!DeviceInfo.isNullOrEmptyOrBlank(appVersion)) {
            jSONObject.put(Defines$Jsonkey.AppVersion.getKey(), appVersion);
        }
        if (!TextUtils.isEmpty(this.prefHelper_.getInitialReferrer()) && !this.prefHelper_.getInitialReferrer().equals("bnc_no_value")) {
            jSONObject.put(Defines$Jsonkey.InitialReferrer.getKey(), this.prefHelper_.getInitialReferrer());
        }
        updateInstallStateAndTimestamps(jSONObject);
        updateEnvironment(this.context_, jSONObject);
        String str = Branch.installDeveloperId;
        if (!TextUtils.isEmpty(str) && !str.equals("bnc_no_value")) {
            jSONObject.put(Defines$Jsonkey.Identity.getKey(), str);
        }
    }

    public void onRequestSucceeded(ServerResponse serverResponse, Branch branch) {
        Branch.getInstance().unlockSDKInitWaitLock();
    }

    /* access modifiers changed from: package-private */
    public void onInitSessionCompleted(ServerResponse serverResponse, Branch branch) {
        DeepLinkRoutingValidator.validate(branch.currentActivityReference_);
        branch.updateSkipURLFormats();
        if (Branch.userAgentSync || !TextUtils.isEmpty(Branch._userAgentString)) {
            BranchLogger.v("Deferring userAgent string call for sync retrieval");
        } else {
            DeviceSignalsKt.getUserAgentAsync(branch.getApplicationContext(), new Continuation() {
                public CoroutineContext getContext() {
                    return EmptyCoroutineContext.INSTANCE;
                }

                public void resumeWith(Object obj) {
                    if (obj != null) {
                        BranchLogger.v("onInitSessionCompleted resumeWith userAgent " + obj + " on thread " + Thread.currentThread().getName());
                        Branch._userAgentString = (String) obj;
                    }
                    Branch.getInstance().requestQueue_.unlockProcessWait(ServerRequest.PROCESS_WAIT_LOCK.USER_AGENT_STRING_LOCK);
                    Branch.getInstance().requestQueue_.processNextQueueItem("getUserAgentAsync resumeWith");
                }
            });
        }
        BranchLogger.v("onInitSessionCompleted on thread " + Thread.currentThread().getName());
    }

    /* access modifiers changed from: package-private */
    public void updateLinkReferrerParams() {
        String linkClickIdentifier = this.prefHelper_.getLinkClickIdentifier();
        if (!linkClickIdentifier.equals("bnc_no_value")) {
            try {
                getPost().put(Defines$Jsonkey.LinkIdentifier.getKey(), linkClickIdentifier);
            } catch (JSONException e) {
                BranchLogger.w("Caught JSONException " + e.getMessage());
            }
        }
        String googleSearchInstallIdentifier = this.prefHelper_.getGoogleSearchInstallIdentifier();
        if (!googleSearchInstallIdentifier.equals("bnc_no_value")) {
            try {
                getPost().put(Defines$Jsonkey.GoogleSearchInstallReferrer.getKey(), googleSearchInstallIdentifier);
            } catch (JSONException e2) {
                BranchLogger.w("Caught JSONException " + e2.getMessage());
            }
        }
        String appStoreReferrer = this.prefHelper_.getAppStoreReferrer();
        if (!appStoreReferrer.equals("bnc_no_value")) {
            try {
                getPost().put(Defines$Jsonkey.GooglePlayInstallReferrer.getKey(), appStoreReferrer);
            } catch (JSONException e3) {
                BranchLogger.w("Caught JSONException " + e3.getMessage());
            }
        }
        String appStoreSource = this.prefHelper_.getAppStoreSource();
        if (!"bnc_no_value".equals(appStoreSource)) {
            try {
                if (appStoreSource.equals(Defines$Jsonkey.Meta_Install_Referrer.getKey())) {
                    getPost().put(Defines$Jsonkey.App_Store.getKey(), Defines$Jsonkey.Google_Play_Store.getKey());
                    getPost().put(Defines$Jsonkey.Is_Meta_Click_Through.getKey(), this.prefHelper_.getIsMetaClickThrough());
                } else {
                    getPost().put(Defines$Jsonkey.App_Store.getKey(), appStoreSource);
                }
            } catch (JSONException e4) {
                BranchLogger.w("Caught JSONException " + e4.getMessage());
            }
        }
        if (this.prefHelper_.isFullAppConversion()) {
            try {
                getPost().put(Defines$Jsonkey.AndroidAppLinkURL.getKey(), this.prefHelper_.getAppLink());
                getPost().put(Defines$Jsonkey.IsFullAppConv.getKey(), true);
            } catch (JSONException e5) {
                BranchLogger.w("Caught JSONException " + e5.getMessage());
            }
        }
    }

    public void onPreExecute() {
        super.onPreExecute();
        JSONObject post = getPost();
        try {
            if (!this.prefHelper_.getAppLink().equals("bnc_no_value")) {
                post.put(Defines$Jsonkey.AndroidAppLinkURL.getKey(), this.prefHelper_.getAppLink());
            }
            if (!this.prefHelper_.getPushIdentifier().equals("bnc_no_value")) {
                post.put(Defines$Jsonkey.AndroidPushIdentifier.getKey(), this.prefHelper_.getPushIdentifier());
            }
            if (!this.prefHelper_.getExternalIntentUri().equals("bnc_no_value")) {
                post.put(Defines$Jsonkey.External_Intent_URI.getKey(), this.prefHelper_.getExternalIntentUri());
            }
            if (!this.prefHelper_.getExternalIntentExtra().equals("bnc_no_value")) {
                post.put(Defines$Jsonkey.External_Intent_Extra.getKey(), this.prefHelper_.getExternalIntentExtra());
            }
        } catch (JSONException e) {
            BranchLogger.w("Caught JSONException " + e.getMessage());
        }
        Branch.expectDelayedSessionInitialization(false);
    }

    private void updateInstallStateAndTimestamps(JSONObject jSONObject) {
        String appVersion = DeviceInfo.getInstance().getAppVersion();
        long firstInstallTime = DeviceInfo.getInstance().getFirstInstallTime();
        long lastUpdateTime = DeviceInfo.getInstance().getLastUpdateTime();
        int i = 2;
        if ("bnc_no_value".equals(this.prefHelper_.getAppVersion())) {
            if (lastUpdateTime - firstInstallTime < NetworkManager.MAX_SERVER_RETRY) {
                i = 0;
            }
        } else if (this.prefHelper_.getAppVersion().equals(appVersion)) {
            i = 1;
        }
        jSONObject.put(Defines$Jsonkey.Update.getKey(), i);
        jSONObject.put(Defines$Jsonkey.FirstInstallTime.getKey(), firstInstallTime);
        jSONObject.put(Defines$Jsonkey.LastUpdateTime.getKey(), lastUpdateTime);
        long j = this.prefHelper_.getLong("bnc_original_install_time");
        if (j == 0) {
            this.prefHelper_.setLong("bnc_original_install_time", firstInstallTime);
        } else {
            firstInstallTime = j;
        }
        jSONObject.put(Defines$Jsonkey.OriginalInstallTime.getKey(), firstInstallTime);
        long j2 = this.prefHelper_.getLong("bnc_last_known_update_time");
        if (j2 < lastUpdateTime) {
            this.prefHelper_.setLong("bnc_previous_update_time", j2);
            this.prefHelper_.setLong("bnc_last_known_update_time", lastUpdateTime);
        }
        jSONObject.put(Defines$Jsonkey.PreviousUpdateTime.getKey(), this.prefHelper_.getLong("bnc_previous_update_time"));
    }

    /* access modifiers changed from: protected */
    public boolean prepareExecuteWithoutTracking() {
        JSONObject post = getPost();
        if (!post.has(Defines$Jsonkey.AndroidAppLinkURL.getKey()) && !post.has(Defines$Jsonkey.AndroidPushIdentifier.getKey()) && !post.has(Defines$Jsonkey.LinkIdentifier.getKey())) {
            return super.prepareExecuteWithoutTracking();
        }
        post.remove(Defines$Jsonkey.RandomizedDeviceToken.getKey());
        post.remove(Defines$Jsonkey.RandomizedBundleToken.getKey());
        post.remove(Defines$Jsonkey.External_Intent_Extra.getKey());
        post.remove(Defines$Jsonkey.External_Intent_URI.getKey());
        post.remove(Defines$Jsonkey.FirstInstallTime.getKey());
        post.remove(Defines$Jsonkey.LastUpdateTime.getKey());
        post.remove(Defines$Jsonkey.OriginalInstallTime.getKey());
        post.remove(Defines$Jsonkey.PreviousUpdateTime.getKey());
        post.remove(Defines$Jsonkey.InstallBeginTimeStamp.getKey());
        post.remove(Defines$Jsonkey.ClickedReferrerTimeStamp.getKey());
        post.remove(Defines$Jsonkey.HardwareID.getKey());
        post.remove(Defines$Jsonkey.IsHardwareIDReal.getKey());
        post.remove(Defines$Jsonkey.LocalIP.getKey());
        post.remove(Defines$Jsonkey.ReferrerGclid.getKey());
        post.remove(Defines$Jsonkey.Identity.getKey());
        post.remove(Defines$Jsonkey.AnonID.getKey());
        try {
            post.put(Defines$Jsonkey.TrackingDisabled.getKey(), true);
        } catch (JSONException e) {
            BranchLogger.w("Caught JSONException " + e.getMessage());
        }
        return true;
    }

    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        try {
            json.put("INITIATED_BY_CLIENT", this.initiatedByClient);
        } catch (JSONException e) {
            BranchLogger.w("Caught JSONException " + e.getMessage());
        }
        return json;
    }
}
