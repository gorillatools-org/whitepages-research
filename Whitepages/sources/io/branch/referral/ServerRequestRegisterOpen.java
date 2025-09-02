package io.branch.referral;

import android.content.Context;
import io.branch.referral.Branch;
import org.json.JSONException;
import org.json.JSONObject;

class ServerRequestRegisterOpen extends ServerRequestInitSession {
    public boolean isGetRequest() {
        return false;
    }

    public boolean shouldRetryOnFail() {
        return false;
    }

    ServerRequestRegisterOpen(Context context, Branch.BranchReferralInitListener branchReferralInitListener, boolean z) {
        super(context, Defines$RequestPath.RegisterOpen, z);
        this.callback_ = branchReferralInitListener;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Defines$Jsonkey.RandomizedDeviceToken.getKey(), this.prefHelper_.getRandomizedDeviceToken());
            jSONObject.put(Defines$Jsonkey.RandomizedBundleToken.getKey(), this.prefHelper_.getRandomizedBundleToken());
            setPost(jSONObject);
        } catch (JSONException e) {
            BranchLogger.w("Caught JSONException " + e.getMessage());
            this.constructError_ = true;
        }
    }

    ServerRequestRegisterOpen(Defines$RequestPath defines$RequestPath, JSONObject jSONObject, Context context, boolean z) {
        super(defines$RequestPath, jSONObject, context, z);
    }

    public void onPreExecute() {
        super.onPreExecute();
        if (Branch.getInstance().isInstantDeepLinkPossible()) {
            Branch.BranchReferralInitListener branchReferralInitListener = this.callback_;
            if (branchReferralInitListener != null) {
                branchReferralInitListener.onInitFinished(Branch.getInstance().getLatestReferringParams(), (BranchError) null);
            }
            Branch.getInstance().requestQueue_.addExtraInstrumentationData(Defines$Jsonkey.InstantDeepLinkSession.getKey(), "true");
            Branch.getInstance().setInstantDeepLinkPossible(false);
        }
    }

    public void onRequestSucceeded(ServerResponse serverResponse, Branch branch) {
        super.onRequestSucceeded(serverResponse, branch);
        BranchLogger.v("onRequestSucceeded " + this + " " + serverResponse + " on callback " + this.callback_);
        try {
            JSONObject object = serverResponse.getObject();
            Defines$Jsonkey defines$Jsonkey = Defines$Jsonkey.LinkClickID;
            if (object.has(defines$Jsonkey.getKey())) {
                this.prefHelper_.setLinkClickID(serverResponse.getObject().getString(defines$Jsonkey.getKey()));
            } else {
                this.prefHelper_.setLinkClickID("bnc_no_value");
            }
            JSONObject object2 = serverResponse.getObject();
            Defines$Jsonkey defines$Jsonkey2 = Defines$Jsonkey.Data;
            if (object2.has(defines$Jsonkey2.getKey())) {
                this.prefHelper_.setSessionParams(serverResponse.getObject().getString(defines$Jsonkey2.getKey()));
            } else {
                this.prefHelper_.setSessionParams("bnc_no_value");
            }
            if (this.callback_ != null && !Branch.getInstance().isIDLSession()) {
                this.callback_.onInitFinished(branch.getLatestReferringParams(), (BranchError) null);
            }
            this.prefHelper_.setAppVersion(DeviceInfo.getInstance().getAppVersion());
        } catch (Exception e) {
            BranchLogger.w("Caught Exception ServerRequestRegisterOpen onRequestSucceeded: " + e.getMessage());
        }
        onInitSessionCompleted(serverResponse, branch);
    }

    public void handleFailure(int i, String str) {
        if (this.callback_ != null && !Branch.getInstance().isIDLSession()) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("error_message", "Trouble reaching server. Please try again in a few minutes");
            } catch (JSONException e) {
                BranchLogger.w("Caught JSONException " + e.getMessage());
            }
            Branch.BranchReferralInitListener branchReferralInitListener = this.callback_;
            branchReferralInitListener.onInitFinished(jSONObject, new BranchError("Trouble initializing Branch. " + str, i));
        }
    }

    public void clearCallbacks() {
        BranchLogger.v(this + " clearCallbacks " + this.callback_);
        this.callback_ = null;
    }
}
