package io.branch.referral;

import android.content.Context;
import io.branch.referral.Branch;
import org.json.JSONException;
import org.json.JSONObject;

class ServerRequestRegisterInstall extends ServerRequestInitSession {
    public boolean isGetRequest() {
        return false;
    }

    public boolean shouldRetryOnFail() {
        return false;
    }

    ServerRequestRegisterInstall(Context context, Branch.BranchReferralInitListener branchReferralInitListener, boolean z) {
        super(context, Defines$RequestPath.RegisterInstall, z);
        this.callback_ = branchReferralInitListener;
        try {
            setPost(new JSONObject());
        } catch (JSONException e) {
            BranchLogger.w("Caught JSONException " + e.getMessage());
            this.constructError_ = true;
        }
    }

    ServerRequestRegisterInstall(Defines$RequestPath defines$RequestPath, JSONObject jSONObject, Context context, boolean z) {
        super(defines$RequestPath, jSONObject, context, z);
    }

    public void onPreExecute() {
        super.onPreExecute();
        long j = this.prefHelper_.getLong("bnc_referrer_click_ts");
        long j2 = this.prefHelper_.getLong("bnc_install_begin_ts");
        if (j > 0) {
            try {
                getPost().put(Defines$Jsonkey.ClickedReferrerTimeStamp.getKey(), j);
            } catch (JSONException e) {
                BranchLogger.w("Caught JSONException " + e.getMessage());
                return;
            }
        }
        if (j2 > 0) {
            getPost().put(Defines$Jsonkey.InstallBeginTimeStamp.getKey(), j2);
        }
        if (!AppStoreReferrer.getInstallationID().equals("bnc_no_value")) {
            getPost().put(Defines$Jsonkey.LinkClickID.getKey(), AppStoreReferrer.getInstallationID());
        }
    }

    public void onRequestSucceeded(ServerResponse serverResponse, Branch branch) {
        super.onRequestSucceeded(serverResponse, branch);
        try {
            this.prefHelper_.setUserURL(serverResponse.getObject().getString(Defines$Jsonkey.Link.getKey()));
            JSONObject object = serverResponse.getObject();
            Defines$Jsonkey defines$Jsonkey = Defines$Jsonkey.Data;
            if (object.has(defines$Jsonkey.getKey())) {
                JSONObject jSONObject = new JSONObject(serverResponse.getObject().getString(defines$Jsonkey.getKey()));
                Defines$Jsonkey defines$Jsonkey2 = Defines$Jsonkey.Clicked_Branch_Link;
                if (jSONObject.has(defines$Jsonkey2.getKey()) && jSONObject.getBoolean(defines$Jsonkey2.getKey()) && this.prefHelper_.getInstallParams().equals("bnc_no_value")) {
                    this.prefHelper_.setInstallParams(serverResponse.getObject().getString(defines$Jsonkey.getKey()));
                }
            }
            JSONObject object2 = serverResponse.getObject();
            Defines$Jsonkey defines$Jsonkey3 = Defines$Jsonkey.LinkClickID;
            if (object2.has(defines$Jsonkey3.getKey())) {
                this.prefHelper_.setLinkClickID(serverResponse.getObject().getString(defines$Jsonkey3.getKey()));
            } else {
                this.prefHelper_.setLinkClickID("bnc_no_value");
            }
            if (serverResponse.getObject().has(defines$Jsonkey.getKey())) {
                this.prefHelper_.setSessionParams(serverResponse.getObject().getString(defines$Jsonkey.getKey()));
            } else {
                this.prefHelper_.setSessionParams("bnc_no_value");
            }
            Branch.BranchReferralInitListener branchReferralInitListener = this.callback_;
            if (branchReferralInitListener != null) {
                branchReferralInitListener.onInitFinished(branch.getLatestReferringParams(), (BranchError) null);
            }
            this.prefHelper_.setAppVersion(DeviceInfo.getInstance().getAppVersion());
        } catch (Exception e) {
            BranchLogger.w("Caught Exception ServerRequestRegisterInstall onRequestSucceeded: " + e.getMessage());
        }
        onInitSessionCompleted(serverResponse, branch);
    }

    public void handleFailure(int i, String str) {
        if (this.callback_ != null) {
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
        BranchLogger.v(this + " clearCallbacks");
        this.callback_ = null;
    }
}
