package io.branch.referral;

import android.content.Context;
import io.branch.referral.Branch;
import java.util.List;

public class BranchShortLinkBuilder extends BranchUrlBuilder {
    public /* bridge */ /* synthetic */ BranchUrlBuilder addParameters(String str, Object obj) {
        return super.addParameters(str, obj);
    }

    public /* bridge */ /* synthetic */ BranchUrlBuilder addTags(List list) {
        return super.addTags(list);
    }

    public BranchShortLinkBuilder(Context context) {
        super(context);
    }

    public BranchShortLinkBuilder setAlias(String str) {
        this.alias_ = str;
        return this;
    }

    public BranchShortLinkBuilder setChannel(String str) {
        this.channel_ = str;
        return this;
    }

    public BranchShortLinkBuilder setDuration(int i) {
        this.duration_ = i;
        return this;
    }

    public BranchShortLinkBuilder setFeature(String str) {
        this.feature_ = str;
        return this;
    }

    public BranchShortLinkBuilder setStage(String str) {
        this.stage_ = str;
        return this;
    }

    public BranchShortLinkBuilder setCampaign(String str) {
        this.campaign_ = str;
        return this;
    }

    public String getShortUrl() {
        return super.getUrl();
    }

    public void generateShortUrl(Branch.BranchLinkCreateListener branchLinkCreateListener) {
        super.generateUrlInternal(branchLinkCreateListener);
    }
}
