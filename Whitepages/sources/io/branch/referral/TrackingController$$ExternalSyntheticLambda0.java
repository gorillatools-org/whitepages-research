package io.branch.referral;

import io.branch.referral.Branch;
import org.json.JSONObject;

public final /* synthetic */ class TrackingController$$ExternalSyntheticLambda0 implements Branch.BranchReferralInitListener {
    public /* synthetic */ TrackingController$$ExternalSyntheticLambda0(Branch.TrackingStateCallback trackingStateCallback) {
    }

    public final void onInitFinished(JSONObject jSONObject, BranchError branchError) {
        TrackingController.lambda$disableTracking$0((Branch.TrackingStateCallback) null, jSONObject, branchError);
    }
}
