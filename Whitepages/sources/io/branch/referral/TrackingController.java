package io.branch.referral;

import android.content.Context;
import io.branch.referral.Branch;
import org.json.JSONObject;

public class TrackingController {
    private boolean trackingDisabled = true;

    TrackingController(Context context) {
        updateTrackingState(context);
    }

    /* access modifiers changed from: package-private */
    public void disableTracking(Context context, boolean z, Branch.TrackingStateCallback trackingStateCallback) {
        boolean z2 = this.trackingDisabled;
        if (z2 != z) {
            this.trackingDisabled = z;
            PrefHelper.getInstance(context).setBool("bnc_tracking_state", Boolean.valueOf(z));
            if (z) {
                onTrackingDisabled(context);
                if (trackingStateCallback != null) {
                    trackingStateCallback.onTrackingStateChanged(true, (JSONObject) null, (BranchError) null);
                    return;
                }
                return;
            }
            onTrackingEnabled(new TrackingController$$ExternalSyntheticLambda0(trackingStateCallback));
        } else if (trackingStateCallback != null) {
            trackingStateCallback.onTrackingStateChanged(z2, Branch.getInstance().getFirstReferringParams(), (BranchError) null);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$disableTracking$0(Branch.TrackingStateCallback trackingStateCallback, JSONObject jSONObject, BranchError branchError) {
        if (trackingStateCallback != null) {
            trackingStateCallback.onTrackingStateChanged(false, jSONObject, branchError);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isTrackingDisabled() {
        return this.trackingDisabled;
    }

    public static boolean isTrackingDisabled(Context context) {
        return PrefHelper.getInstance(context).getBool("bnc_tracking_state");
    }

    /* access modifiers changed from: package-private */
    public void updateTrackingState(Context context) {
        this.trackingDisabled = PrefHelper.getInstance(context).getBool("bnc_tracking_state");
    }

    private void onTrackingDisabled(Context context) {
        Branch.getInstance().clearPendingRequests();
        PrefHelper instance = PrefHelper.getInstance(context);
        instance.setSessionID("bnc_no_value");
        instance.setLinkClickID("bnc_no_value");
        instance.setLinkClickIdentifier("bnc_no_value");
        instance.setAppLink("bnc_no_value");
        instance.setInstallReferrerParams("bnc_no_value");
        instance.setAppStoreReferrer("bnc_no_value");
        instance.setAppStoreSource("bnc_no_value");
        instance.setGoogleSearchInstallIdentifier("bnc_no_value");
        instance.setInitialReferrer("bnc_no_value");
        instance.setExternalIntentUri("bnc_no_value");
        instance.setExternalIntentExtra("bnc_no_value");
        instance.setSessionParams("bnc_no_value");
        instance.setAnonID("bnc_no_value");
        instance.setReferringUrlQueryParameters(new JSONObject());
        Branch.getInstance().clearPartnerParameters();
    }

    private void onTrackingEnabled(Branch.BranchReferralInitListener branchReferralInitListener) {
        Branch instance = Branch.getInstance();
        if (instance != null) {
            instance.registerAppInit(instance.getInstallOrOpenRequest(branchReferralInitListener, true), true, false);
        }
    }
}
