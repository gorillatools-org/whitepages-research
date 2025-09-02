package io.branch.referral.QRCode;

import android.content.Context;
import io.branch.referral.Branch;
import io.branch.referral.Defines$RequestPath;
import io.branch.referral.QRCode.BranchQRCode;
import io.branch.referral.ServerRequest;
import io.branch.referral.ServerResponse;
import org.json.JSONObject;

public class ServerRequestCreateQRCode extends ServerRequest {
    private BranchQRCode.BranchQRCodeRequestHandler callback_;
    private final Context context_;
    private JSONObject params_;
    private long queueWaitTime_ = 0;
    final Defines$RequestPath requestPath_;

    public boolean isGetRequest() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean prepareExecuteWithoutTracking() {
        return true;
    }

    protected ServerRequestCreateQRCode(Defines$RequestPath defines$RequestPath, JSONObject jSONObject, Context context, BranchQRCode.BranchQRCodeRequestHandler branchQRCodeRequestHandler) {
        super(Defines$RequestPath.QRCode, jSONObject, context);
        this.context_ = context;
        this.requestPath_ = defines$RequestPath;
        this.params_ = jSONObject;
        this.callback_ = branchQRCodeRequestHandler;
    }

    public void onRequestSucceeded(ServerResponse serverResponse, Branch branch) {
        this.callback_.onDataReceived(serverResponse);
    }

    public void handleFailure(int i, String str) {
        this.callback_.onFailure(new Exception("Failed server request: " + i + str));
    }

    public void onRequestQueued() {
        this.queueWaitTime_ = System.currentTimeMillis();
    }

    public void clearCallbacks() {
        this.callback_ = null;
    }
}
