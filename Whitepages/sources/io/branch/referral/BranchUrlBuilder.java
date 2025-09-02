package io.branch.referral;

import android.content.Context;
import io.branch.referral.Branch;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

abstract class BranchUrlBuilder {
    protected String alias_;
    protected Branch branchReferral_ = Branch.getInstance();
    protected String campaign_;
    protected String channel_;
    private final Context context_;
    private boolean defaultToLongUrl_ = true;
    protected int duration_ = 0;
    protected String feature_;
    protected JSONObject params_;
    protected String stage_;
    protected ArrayList tags_;
    protected int type_ = 0;

    protected BranchUrlBuilder(Context context) {
        this.context_ = context.getApplicationContext();
    }

    public BranchUrlBuilder addTags(List list) {
        if (this.tags_ == null) {
            this.tags_ = new ArrayList();
        }
        this.tags_.addAll(list);
        return this;
    }

    public BranchUrlBuilder addParameters(String str, Object obj) {
        try {
            if (this.params_ == null) {
                this.params_ = new JSONObject();
            }
            this.params_.put(str, obj);
        } catch (JSONException e) {
            BranchLogger.w("Caught JSONException" + e.getMessage());
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public String getUrl() {
        if (this.branchReferral_ == null) {
            return null;
        }
        return this.branchReferral_.generateShortLinkInternal(new ServerRequestCreateUrl(this.context_, this.alias_, this.type_, this.duration_, this.tags_, this.channel_, this.feature_, this.stage_, this.campaign_, this.params_, (Branch.BranchLinkCreateListener) null, false, this.defaultToLongUrl_));
    }

    /* access modifiers changed from: protected */
    public void generateUrlInternal(Branch.BranchLinkCreateListener branchLinkCreateListener) {
        Branch.BranchLinkCreateListener branchLinkCreateListener2 = branchLinkCreateListener;
        if (this.branchReferral_ != null) {
            this.branchReferral_.generateShortLinkInternal(new ServerRequestCreateUrl(this.context_, this.alias_, this.type_, this.duration_, this.tags_, this.channel_, this.feature_, this.stage_, this.campaign_, this.params_, branchLinkCreateListener, true, this.defaultToLongUrl_));
            return;
        }
        if (branchLinkCreateListener2 != null) {
            branchLinkCreateListener2.onLinkCreate((String) null, new BranchError("session has not been initialized", -101));
        }
        BranchLogger.w("Warning: User session has not been initialized");
    }
}
