package io.branch.referral;

import android.content.Context;
import io.branch.referral.ServerRequest;
import org.json.JSONException;
import org.json.JSONObject;

public class ServerRequestGetLATD extends ServerRequest {
    private int attributionWindow;
    private BranchLastAttributedTouchDataListener callback;

    public interface BranchLastAttributedTouchDataListener {
        void onDataFetched(JSONObject jSONObject, BranchError branchError);
    }

    public boolean isGetRequest() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean shouldUpdateLimitFacebookTracking() {
        return true;
    }

    ServerRequestGetLATD(Context context, Defines$RequestPath defines$RequestPath, BranchLastAttributedTouchDataListener branchLastAttributedTouchDataListener, int i) {
        super(context, defines$RequestPath);
        this.callback = branchLastAttributedTouchDataListener;
        this.attributionWindow = i;
        JSONObject jSONObject = new JSONObject();
        try {
            setPost(jSONObject);
        } catch (JSONException e) {
            BranchLogger.w("Caught JSONException " + e.getMessage());
        }
        updateEnvironment(context, jSONObject);
    }

    /* access modifiers changed from: protected */
    public int getAttributionWindow() {
        return this.attributionWindow;
    }

    public void onRequestSucceeded(ServerResponse serverResponse, Branch branch) {
        BranchLastAttributedTouchDataListener branchLastAttributedTouchDataListener = this.callback;
        if (branchLastAttributedTouchDataListener != null) {
            if (serverResponse != null) {
                branchLastAttributedTouchDataListener.onDataFetched(serverResponse.getObject(), (BranchError) null);
            } else {
                handleFailure(-116, "Failed to get last attributed touch data");
            }
        }
    }

    public void handleFailure(int i, String str) {
        BranchLastAttributedTouchDataListener branchLastAttributedTouchDataListener = this.callback;
        if (branchLastAttributedTouchDataListener != null) {
            branchLastAttributedTouchDataListener.onDataFetched((JSONObject) null, new BranchError("Failed to get last attributed touch data", i));
        }
    }

    public void clearCallbacks() {
        this.callback = null;
    }

    public ServerRequest.BRANCH_API_VERSION getBranchRemoteAPIVersion() {
        return ServerRequest.BRANCH_API_VERSION.V1_LATD;
    }
}
