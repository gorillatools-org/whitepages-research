package io.branch.referral;

import android.content.Context;
import io.branch.referral.Branch;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collection;
import org.json.JSONException;
import org.json.JSONObject;

class ServerRequestCreateUrl extends ServerRequest {
    private Branch.BranchLinkCreateListener callback_;
    private boolean defaultToLongUrl_;
    private boolean isAsync_;
    private BranchLinkData linkPost_;

    public boolean isGetRequest() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isPersistable() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean prepareExecuteWithoutTracking() {
        return true;
    }

    public ServerRequestCreateUrl(Context context, String str, int i, int i2, Collection collection, String str2, String str3, String str4, String str5, JSONObject jSONObject, Branch.BranchLinkCreateListener branchLinkCreateListener, boolean z, boolean z2) {
        super(context, Defines$RequestPath.GetURL);
        this.callback_ = branchLinkCreateListener;
        this.isAsync_ = z;
        this.defaultToLongUrl_ = z2;
        this.linkPost_ = new BranchLinkData();
        try {
            if (!this.prefHelper_.getLinkClickID().equals("bnc_no_value")) {
                this.linkPost_.put(Defines$Jsonkey.LinkClickID.getKey(), this.prefHelper_.getLinkClickID());
            }
            this.linkPost_.putType(i);
            this.linkPost_.putDuration(i2);
            this.linkPost_.putTags(collection);
            this.linkPost_.putAlias(str);
            this.linkPost_.putChannel(str2);
            this.linkPost_.putFeature(str3);
            this.linkPost_.putStage(str4);
            this.linkPost_.putCampaign(str5);
            this.linkPost_.putParams(jSONObject);
            this.linkPost_.putSource();
            setPost(this.linkPost_);
            this.linkPost_.remove("anon_id");
            this.linkPost_.remove("is_hardware_id_real");
            this.linkPost_.remove("hardware_id");
        } catch (JSONException e) {
            BranchLogger.w("Caught JSONException " + e.getMessage());
            this.constructError_ = true;
        }
    }

    public ServerRequestCreateUrl(Defines$RequestPath defines$RequestPath, JSONObject jSONObject, Context context) {
        super(defines$RequestPath, jSONObject, context);
        this.isAsync_ = true;
        this.defaultToLongUrl_ = true;
    }

    public BranchLinkData getLinkPost() {
        return this.linkPost_;
    }

    /* access modifiers changed from: package-private */
    public boolean isDefaultToLongUrl() {
        return this.defaultToLongUrl_;
    }

    public boolean handleErrors(Context context) {
        if (super.doesAppHasInternetPermission(context)) {
            return false;
        }
        Branch.BranchLinkCreateListener branchLinkCreateListener = this.callback_;
        if (branchLinkCreateListener == null) {
            return true;
        }
        branchLinkCreateListener.onLinkCreate((String) null, new BranchError("Trouble creating a URL.", -102));
        return true;
    }

    public void onRequestSucceeded(ServerResponse serverResponse, Branch branch) {
        try {
            String string = serverResponse.getObject().getString("url");
            Branch.BranchLinkCreateListener branchLinkCreateListener = this.callback_;
            if (branchLinkCreateListener != null) {
                branchLinkCreateListener.onLinkCreate(string, (BranchError) null);
            }
        } catch (Exception e) {
            BranchLogger.e("Caught Exception ServerRequestCreateUrl onRequestSucceeded: " + e.getMessage() + " stacktrace: " + BranchLogger.stackTraceToString(e));
        }
    }

    public void onUrlAvailable(String str) {
        Branch.BranchLinkCreateListener branchLinkCreateListener = this.callback_;
        if (branchLinkCreateListener != null) {
            branchLinkCreateListener.onLinkCreate(str, (BranchError) null);
        }
    }

    public void handleFailure(int i, String str) {
        if (this.callback_ != null) {
            String longUrl = this.defaultToLongUrl_ ? getLongUrl() : null;
            Branch.BranchLinkCreateListener branchLinkCreateListener = this.callback_;
            branchLinkCreateListener.onLinkCreate(longUrl, new BranchError("Trouble creating a URL. " + str, i));
        }
    }

    public String getLongUrl() {
        if (!this.prefHelper_.getUserURL().equals("bnc_no_value")) {
            return generateLongUrlWithParams(this.prefHelper_.getUserURL());
        }
        return generateLongUrlWithParams("https://bnc.lt/a/" + this.prefHelper_.getBranchKey());
    }

    public void handleDuplicateURLError() {
        Branch.BranchLinkCreateListener branchLinkCreateListener = this.callback_;
        if (branchLinkCreateListener != null) {
            branchLinkCreateListener.onLinkCreate((String) null, new BranchError("Trouble creating a URL.", -105));
        }
    }

    public void clearCallbacks() {
        this.callback_ = null;
    }

    public boolean isAsync() {
        return this.isAsync_;
    }

    private String generateLongUrlWithParams(String str) {
        String str2;
        try {
            String str3 = "";
            if (Branch.getInstance().isTrackingDisabled()) {
                if (!str.contains("https://bnc.lt/a/")) {
                    str = str.replace(new URL(str).getQuery(), str3);
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            if (str.contains("?")) {
                str2 = str3;
            } else {
                str2 = "?";
            }
            sb.append(str2);
            str = sb.toString();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            if (!str.endsWith("?")) {
                str3 = "&";
            }
            sb2.append(str3);
            str = sb2.toString();
            Collection<String> tags = this.linkPost_.getTags();
            if (tags != null) {
                for (String str4 : tags) {
                    if (str4 != null && str4.length() > 0) {
                        str = str + Defines$LinkParam.Tags + "=" + URLEncoder.encode(str4, "UTF8") + "&";
                    }
                }
            }
            String alias = this.linkPost_.getAlias();
            if (alias != null && alias.length() > 0) {
                str = str + Defines$LinkParam.Alias + "=" + URLEncoder.encode(alias, "UTF8") + "&";
            }
            String channel = this.linkPost_.getChannel();
            if (channel != null && channel.length() > 0) {
                str = str + Defines$LinkParam.Channel + "=" + URLEncoder.encode(channel, "UTF8") + "&";
            }
            String feature = this.linkPost_.getFeature();
            if (feature != null && feature.length() > 0) {
                str = str + Defines$LinkParam.Feature + "=" + URLEncoder.encode(feature, "UTF8") + "&";
            }
            String stage = this.linkPost_.getStage();
            if (stage != null && stage.length() > 0) {
                str = str + Defines$LinkParam.Stage + "=" + URLEncoder.encode(stage, "UTF8") + "&";
            }
            String campaign = this.linkPost_.getCampaign();
            if (campaign != null && campaign.length() > 0) {
                str = str + Defines$LinkParam.Campaign + "=" + URLEncoder.encode(campaign, "UTF8") + "&";
            }
            String str5 = ((str + Defines$LinkParam.Type + "=" + ((long) this.linkPost_.getType()) + "&") + Defines$LinkParam.Duration + "=" + ((long) this.linkPost_.getDuration())) + "&source=" + Defines$Jsonkey.URLSource.getKey();
            JSONObject params = this.linkPost_.getParams();
            if (params == null || params.length() <= 0) {
                return str5;
            }
            return str5 + "&data=" + URLEncoder.encode(Base64.encodeToString(params.toString().getBytes(), 2), "UTF8");
        } catch (Exception e) {
            BranchLogger.e("Caught Exception ServerRequestCreateUrl generateLongUrlWithParams: " + e.getMessage() + " stacktrace: " + BranchLogger.stackTraceToString(e));
            this.callback_.onLinkCreate((String) null, new BranchError("Trouble creating a URL.", -116));
            return str;
        }
    }
}
