package io.branch.referral;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import io.branch.referral.Branch;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class BranchShareSheetBuilder {
    private final Activity activity_;
    private Branch.BranchLinkShareListener callback_;
    private String copyURlText_;
    private Drawable copyUrlIcon_;
    private String defaultURL_;
    private int dialogThemeResourceID_;
    private int dividerHeight_;
    private List excludeFromShareSheet;
    private int iconSize_;
    private List includeInShareSheet;
    private Drawable moreOptionIcon_;
    private String moreOptionText_;
    private ArrayList preferredOptions_;
    private boolean setFullWidthStyle_;
    private String shareMsg_;
    private String shareSub_;
    private View sharingTitleView_;
    private String sharingTitle_;
    private BranchShortLinkBuilder shortLinkBuilder_;
    private int styleResourceID_;
    private String urlCopiedMessage_;

    public Branch.IChannelProperties getChannelPropertiesCallback() {
        return null;
    }

    public BranchShareSheetBuilder setChannelProperties(Branch.IChannelProperties iChannelProperties) {
        return this;
    }

    public BranchShareSheetBuilder(Activity activity, JSONObject jSONObject) {
        this.dividerHeight_ = -1;
        this.sharingTitle_ = null;
        this.sharingTitleView_ = null;
        this.iconSize_ = 50;
        this.includeInShareSheet = new ArrayList();
        this.excludeFromShareSheet = new ArrayList();
        this.activity_ = activity;
        this.shortLinkBuilder_ = new BranchShortLinkBuilder(activity);
        try {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                this.shortLinkBuilder_.addParameters(next, jSONObject.get(next));
            }
        } catch (Exception e) {
            BranchLogger.d(e.getMessage());
        }
        this.shareMsg_ = "";
        this.callback_ = null;
        this.preferredOptions_ = new ArrayList();
        this.defaultURL_ = null;
        this.moreOptionIcon_ = BranchUtil.getDrawable(activity.getApplicationContext(), 17301573);
        this.moreOptionText_ = "More...";
        this.copyUrlIcon_ = BranchUtil.getDrawable(activity.getApplicationContext(), 17301582);
        this.copyURlText_ = "Copy link";
        this.urlCopiedMessage_ = "Copied link to clipboard!";
        if (Branch.getInstance().getDeviceInfo().isTV()) {
            excludeFromShareSheet("com.google.android.tv.frameworkpackagestubs");
        }
    }

    public BranchShareSheetBuilder(Activity activity, BranchShortLinkBuilder branchShortLinkBuilder) {
        this(activity, new JSONObject());
        this.shortLinkBuilder_ = branchShortLinkBuilder;
    }

    public BranchShareSheetBuilder setMessage(String str) {
        this.shareMsg_ = str;
        return this;
    }

    public BranchShareSheetBuilder setSubject(String str) {
        this.shareSub_ = str;
        return this;
    }

    public BranchShareSheetBuilder setCallback(Branch.BranchLinkShareListener branchLinkShareListener) {
        this.callback_ = branchLinkShareListener;
        return this;
    }

    public BranchShareSheetBuilder addPreferredSharingOptions(ArrayList arrayList) {
        this.preferredOptions_.addAll(arrayList);
        return this;
    }

    public BranchShareSheetBuilder setDefaultURL(String str) {
        this.defaultURL_ = str;
        return this;
    }

    public BranchShareSheetBuilder setMoreOptionStyle(Drawable drawable, String str) {
        this.moreOptionIcon_ = drawable;
        this.moreOptionText_ = str;
        return this;
    }

    public BranchShareSheetBuilder setCopyUrlStyle(Drawable drawable, String str, String str2) {
        this.copyUrlIcon_ = drawable;
        this.copyURlText_ = str;
        this.urlCopiedMessage_ = str2;
        return this;
    }

    public BranchShareSheetBuilder setAsFullWidthStyle(boolean z) {
        this.setFullWidthStyle_ = z;
        return this;
    }

    public BranchShareSheetBuilder setDialogThemeResourceID(int i) {
        this.dialogThemeResourceID_ = i;
        return this;
    }

    public BranchShareSheetBuilder setDividerHeight(int i) {
        this.dividerHeight_ = i;
        return this;
    }

    public BranchShareSheetBuilder setSharingTitle(String str) {
        this.sharingTitle_ = str;
        return this;
    }

    public BranchShareSheetBuilder setSharingTitle(View view) {
        this.sharingTitleView_ = view;
        return this;
    }

    public BranchShareSheetBuilder setIconSize(int i) {
        this.iconSize_ = i;
        return this;
    }

    public BranchShareSheetBuilder excludeFromShareSheet(String str) {
        this.excludeFromShareSheet.add(str);
        return this;
    }

    public BranchShareSheetBuilder excludeFromShareSheet(List list) {
        this.excludeFromShareSheet.addAll(list);
        return this;
    }

    public BranchShareSheetBuilder includeInShareSheet(List list) {
        this.includeInShareSheet.addAll(list);
        return this;
    }

    public void setStyleResourceID(int i) {
        this.styleResourceID_ = i;
    }

    public void shareLink() {
        Branch.getInstance().shareLink(this);
    }

    public Activity getActivity() {
        return this.activity_;
    }

    public ArrayList getPreferredOptions() {
        return this.preferredOptions_;
    }

    /* access modifiers changed from: package-private */
    public List getExcludedFromShareSheet() {
        return this.excludeFromShareSheet;
    }

    /* access modifiers changed from: package-private */
    public List getIncludedInShareSheet() {
        return this.includeInShareSheet;
    }

    public String getShareMsg() {
        return this.shareMsg_;
    }

    public String getShareSub() {
        return this.shareSub_;
    }

    public Branch.BranchLinkShareListener getCallback() {
        return this.callback_;
    }

    public String getDefaultURL() {
        return this.defaultURL_;
    }

    public Drawable getMoreOptionIcon() {
        return this.moreOptionIcon_;
    }

    public String getMoreOptionText() {
        return this.moreOptionText_;
    }

    public Drawable getCopyUrlIcon() {
        return this.copyUrlIcon_;
    }

    public String getCopyURlText() {
        return this.copyURlText_;
    }

    public String getUrlCopiedMessage() {
        return this.urlCopiedMessage_;
    }

    public BranchShortLinkBuilder getShortLinkBuilder() {
        return this.shortLinkBuilder_;
    }

    public boolean getIsFullWidthStyle() {
        return this.setFullWidthStyle_;
    }

    public int getDialogThemeResourceID() {
        return this.dialogThemeResourceID_;
    }

    public int getDividerHeight() {
        return this.dividerHeight_;
    }

    public String getSharingTitle() {
        return this.sharingTitle_;
    }

    public View getSharingTitleView() {
        return this.sharingTitleView_;
    }

    public int getStyleResourceID() {
        return this.styleResourceID_;
    }

    public int getIconSize() {
        return this.iconSize_;
    }
}
