package io.branch.referral.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import io.branch.referral.SharingHelper$SHARE_WITH;
import java.util.ArrayList;
import java.util.List;

public class ShareSheetStyle {
    final Context context_;
    private String copyURlText_;
    private Drawable copyUrlIcon_;
    private String defaultURL_;
    private int dialogThemeResourceID_ = -1;
    private int dividerHeight_ = -1;
    private List excludeFromShareSheet = new ArrayList();
    private int iconSize_ = 50;
    private List includeInShareSheet = new ArrayList();
    private final String messageBody_;
    private final String messageTitle_;
    private Drawable moreOptionIcon_;
    private String moreOptionText_;
    private final ArrayList preferredOptions_;
    private boolean setFullWidthStyle_;
    private View sharingTitleView_ = null;
    private String sharingTitle_ = null;
    private int styleResourceID_ = -1;
    private String urlCopiedMessage_;

    public ShareSheetStyle(Context context, String str, String str2) {
        this.context_ = context;
        this.moreOptionIcon_ = null;
        this.moreOptionText_ = null;
        this.copyUrlIcon_ = null;
        this.copyURlText_ = null;
        this.urlCopiedMessage_ = null;
        this.preferredOptions_ = new ArrayList();
        this.defaultURL_ = null;
        this.messageTitle_ = str;
        this.messageBody_ = str2;
    }

    public ShareSheetStyle setMoreOptionStyle(Drawable drawable, String str) {
        this.moreOptionIcon_ = drawable;
        this.moreOptionText_ = str;
        return this;
    }

    public ShareSheetStyle setCopyUrlStyle(Drawable drawable, String str, String str2) {
        this.copyUrlIcon_ = drawable;
        this.copyURlText_ = str;
        this.urlCopiedMessage_ = str2;
        return this;
    }

    public ShareSheetStyle addPreferredSharingOption(SharingHelper$SHARE_WITH sharingHelper$SHARE_WITH) {
        this.preferredOptions_.add(sharingHelper$SHARE_WITH);
        return this;
    }

    public List getExcludedFromShareSheet() {
        return this.excludeFromShareSheet;
    }

    public List getIncludedInShareSheet() {
        return this.includeInShareSheet;
    }

    public ArrayList getPreferredOptions() {
        return this.preferredOptions_;
    }

    public Drawable getCopyUrlIcon() {
        return this.copyUrlIcon_;
    }

    public Drawable getMoreOptionIcon() {
        return this.moreOptionIcon_;
    }

    public String getMessageBody() {
        return this.messageBody_;
    }

    public String getMessageTitle() {
        return this.messageTitle_;
    }

    public String getCopyURlText() {
        return this.copyURlText_;
    }

    public String getDefaultURL() {
        return this.defaultURL_;
    }

    public String getMoreOptionText() {
        return this.moreOptionText_;
    }

    public String getUrlCopiedMessage() {
        return this.urlCopiedMessage_;
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

    public boolean getIsFullWidthStyle() {
        return this.setFullWidthStyle_;
    }

    public int getDialogThemeResourceID() {
        return this.dialogThemeResourceID_;
    }

    public int getStyleResourceID() {
        return this.styleResourceID_;
    }

    public int getIconSize() {
        return this.iconSize_;
    }
}
