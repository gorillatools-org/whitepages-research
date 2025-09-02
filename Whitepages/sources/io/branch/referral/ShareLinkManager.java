package io.branch.referral;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import io.branch.referral.Branch;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class ShareLinkManager {
    /* access modifiers changed from: private */
    public static int ICON_SIZER = 2;
    /* access modifiers changed from: private */
    public static int viewItemMinHeight_ = 100;
    /* access modifiers changed from: private */
    public final int BG_COLOR_DISABLED = Color.argb(20, 17, 4, 56);
    /* access modifiers changed from: private */
    public final int BG_COLOR_ENABLED = Color.argb(60, 17, 4, 56);
    /* access modifiers changed from: private */
    public BranchShareSheetBuilder builder_;
    Branch.BranchLinkShareListener callback_;
    Context context_;
    /* access modifiers changed from: private */
    public List displayedAppList_;
    private List excludeFromShareSheet = new ArrayList();
    /* access modifiers changed from: private */
    public int iconSize_ = 50;
    private List includeInShareSheet = new ArrayList();
    /* access modifiers changed from: private */
    public boolean isShareInProgress_ = false;
    final int leftMargin = 100;
    final int padding = 5;
    private int shareDialogThemeID_ = -1;
    AnimatedDialog shareDlg_;
    private Intent shareLinkIntent_;

    ShareLinkManager() {
    }

    /* access modifiers changed from: package-private */
    public Dialog shareLink(BranchShareSheetBuilder branchShareSheetBuilder) {
        this.builder_ = branchShareSheetBuilder;
        this.context_ = branchShareSheetBuilder.getActivity();
        this.callback_ = branchShareSheetBuilder.getCallback();
        branchShareSheetBuilder.getChannelPropertiesCallback();
        Intent intent = new Intent("android.intent.action.SEND");
        this.shareLinkIntent_ = intent;
        intent.setType("text/plain");
        this.shareDialogThemeID_ = branchShareSheetBuilder.getStyleResourceID();
        this.includeInShareSheet = branchShareSheetBuilder.getIncludedInShareSheet();
        this.excludeFromShareSheet = branchShareSheetBuilder.getExcludedFromShareSheet();
        this.iconSize_ = branchShareSheetBuilder.getIconSize();
        try {
            createShareDialog(branchShareSheetBuilder.getPreferredOptions());
        } catch (Exception e) {
            BranchLogger.e("Caught Exception" + e.getMessage());
            Branch.BranchLinkShareListener branchLinkShareListener = this.callback_;
            if (branchLinkShareListener != null) {
                branchLinkShareListener.onLinkShareResponse((String) null, (String) null, new BranchError("Trouble sharing link", -110));
            } else {
                BranchLogger.w("Unable create share options. Couldn't find applications on device to share the link.");
            }
        }
        return this.shareDlg_;
    }

    /* access modifiers changed from: package-private */
    public void cancelShareLinkDialog(boolean z) {
        AnimatedDialog animatedDialog = this.shareDlg_;
        if (animatedDialog != null && animatedDialog.isShowing()) {
            if (z) {
                this.shareDlg_.cancel();
            } else {
                this.shareDlg_.dismiss();
            }
        }
    }

    private void createShareDialog(List list) {
        final ListView listView;
        List<ResolveInfo> queryIntentActivities = this.context_.getPackageManager().queryIntentActivities(this.shareLinkIntent_, 65536);
        final ArrayList arrayList = new ArrayList(getExplicitlyIncludedMatchingApps(queryIntentActivities));
        List preferredMatchingApps = getPreferredMatchingApps(queryIntentActivities, list);
        arrayList.removeAll(preferredMatchingApps);
        arrayList.addAll(0, preferredMatchingApps);
        arrayList.add(new CopyLinkItem());
        preferredMatchingApps.add(new CopyLinkItem());
        filterOutExplicitlyExcludedApps(arrayList);
        if (preferredMatchingApps.size() > 1) {
            if (arrayList.size() > preferredMatchingApps.size()) {
                preferredMatchingApps.add(new MoreShareItem());
            }
            this.displayedAppList_ = preferredMatchingApps;
        } else {
            this.displayedAppList_ = arrayList;
        }
        final ChooserArrayAdapter chooserArrayAdapter = new ChooserArrayAdapter();
        if (this.shareDialogThemeID_ > 1) {
            listView = new ListView(this.context_, (AttributeSet) null, 0, this.shareDialogThemeID_);
        } else {
            listView = new ListView(this.context_);
        }
        listView.setHorizontalFadingEdgeEnabled(false);
        listView.setBackgroundColor(-1);
        listView.setSelector(new ColorDrawable(0));
        if (this.builder_.getSharingTitleView() != null) {
            listView.addHeaderView(this.builder_.getSharingTitleView(), (Object) null, false);
        } else if (!TextUtils.isEmpty(this.builder_.getSharingTitle())) {
            TextView textView = new TextView(this.context_);
            textView.setText(this.builder_.getSharingTitle());
            textView.setBackgroundColor(this.BG_COLOR_DISABLED);
            textView.setTextColor(this.BG_COLOR_DISABLED);
            textView.setTextAppearance(this.context_, 16973892);
            textView.setTextColor(this.context_.getResources().getColor(17170432));
            textView.setPadding(100, 5, 5, 5);
            listView.addHeaderView(textView, (Object) null, false);
        }
        listView.setAdapter(chooserArrayAdapter);
        if (this.builder_.getDividerHeight() >= 0) {
            listView.setDividerHeight(this.builder_.getDividerHeight());
        } else if (this.builder_.getIsFullWidthStyle()) {
            listView.setDividerHeight(0);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView adapterView, View view, int i, long j) {
                String str;
                if (view != null) {
                    if (view.getTag() instanceof MoreShareItem) {
                        List unused = ShareLinkManager.this.displayedAppList_ = arrayList;
                        chooserArrayAdapter.notifyDataSetChanged();
                    } else if (view.getTag() instanceof ResolveInfo) {
                        ResolveInfo resolveInfo = (ResolveInfo) view.getTag();
                        ShareLinkManager shareLinkManager = ShareLinkManager.this;
                        if (shareLinkManager.callback_ != null) {
                            PackageManager packageManager = shareLinkManager.context_.getPackageManager();
                            if (ShareLinkManager.this.context_ == null || resolveInfo.loadLabel(packageManager) == null) {
                                str = "";
                            } else {
                                str = resolveInfo.loadLabel(packageManager).toString();
                            }
                            ShareLinkManager.this.builder_.getShortLinkBuilder().setChannel(resolveInfo.loadLabel(packageManager).toString());
                            ShareLinkManager.this.callback_.onChannelSelected(str);
                        }
                        chooserArrayAdapter.selectedPos = i - listView.getHeaderViewsCount();
                        chooserArrayAdapter.notifyDataSetChanged();
                        ShareLinkManager.this.invokeSharingClient(resolveInfo);
                        AnimatedDialog animatedDialog = ShareLinkManager.this.shareDlg_;
                        if (animatedDialog != null) {
                            animatedDialog.cancel();
                        }
                    }
                }
            }
        });
        if (this.builder_.getDialogThemeResourceID() > 0) {
            this.shareDlg_ = new AnimatedDialog(this.context_, this.builder_.getDialogThemeResourceID());
        } else {
            this.shareDlg_ = new AnimatedDialog(this.context_, this.builder_.getIsFullWidthStyle());
        }
        this.shareDlg_.setContentView(listView);
        this.shareDlg_.show();
        Branch.BranchLinkShareListener branchLinkShareListener = this.callback_;
        if (branchLinkShareListener != null) {
            branchLinkShareListener.onShareLinkDialogLaunched();
        }
        this.shareDlg_.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                Branch.BranchLinkShareListener branchLinkShareListener = ShareLinkManager.this.callback_;
                if (branchLinkShareListener != null) {
                    branchLinkShareListener.onShareLinkDialogDismissed();
                    ShareLinkManager.this.callback_ = null;
                }
                if (!ShareLinkManager.this.isShareInProgress_) {
                    ShareLinkManager shareLinkManager = ShareLinkManager.this;
                    shareLinkManager.context_ = null;
                    BranchShareSheetBuilder unused = shareLinkManager.builder_ = null;
                }
                ShareLinkManager.this.shareDlg_ = null;
            }
        });
        this.shareDlg_.setOnKeyListener(new DialogInterface.OnKeyListener() {
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() != 1) {
                    return false;
                }
                if (i == 4) {
                    ShareLinkManager.this.shareDlg_.dismiss();
                } else if (i == 23 || i == 66) {
                    ChooserArrayAdapter chooserArrayAdapter = chooserArrayAdapter;
                    int i2 = chooserArrayAdapter.selectedPos;
                    if (i2 < 0 || i2 >= chooserArrayAdapter.getCount()) {
                        return false;
                    }
                    ListView listView = listView;
                    ChooserArrayAdapter chooserArrayAdapter2 = chooserArrayAdapter;
                    View view = chooserArrayAdapter2.getView(chooserArrayAdapter2.selectedPos, (View) null, (ViewGroup) null);
                    int i3 = chooserArrayAdapter.selectedPos;
                    listView.performItemClick(view, i3, listView.getItemIdAtPosition(i3));
                    return false;
                } else if (i == 19) {
                    ChooserArrayAdapter chooserArrayAdapter3 = chooserArrayAdapter;
                    int i4 = chooserArrayAdapter3.selectedPos;
                    if (i4 > 0) {
                        chooserArrayAdapter3.selectedPos = i4 - 1;
                        chooserArrayAdapter3.notifyDataSetChanged();
                    }
                } else if (i != 20) {
                    return false;
                } else {
                    ChooserArrayAdapter chooserArrayAdapter4 = chooserArrayAdapter;
                    if (chooserArrayAdapter4.selectedPos < chooserArrayAdapter4.getCount() - 1) {
                        ChooserArrayAdapter chooserArrayAdapter5 = chooserArrayAdapter;
                        chooserArrayAdapter5.selectedPos++;
                        chooserArrayAdapter5.notifyDataSetChanged();
                    }
                }
                return true;
            }
        });
    }

    private List getPreferredMatchingApps(List list, List list2) {
        ActivityInfo activityInfo;
        SharingHelper$SHARE_WITH sharingHelper$SHARE_WITH;
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ResolveInfo resolveInfo = (ResolveInfo) it.next();
            if (!(resolveInfo == null || (activityInfo = resolveInfo.activityInfo) == null)) {
                String str = activityInfo.packageName;
                Iterator it2 = list2.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        sharingHelper$SHARE_WITH = null;
                        break;
                    }
                    sharingHelper$SHARE_WITH = (SharingHelper$SHARE_WITH) it2.next();
                    if (str.toLowerCase().contains(sharingHelper$SHARE_WITH.toString().toLowerCase())) {
                        break;
                    }
                }
                if (sharingHelper$SHARE_WITH != null) {
                    arrayList.add(resolveInfo);
                }
            }
        }
        return arrayList;
    }

    private List getExplicitlyIncludedMatchingApps(List list) {
        ArrayList arrayList = new ArrayList();
        if (this.includeInShareSheet.size() <= 0) {
            return list;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ResolveInfo resolveInfo = (ResolveInfo) it.next();
            if (this.includeInShareSheet.contains(resolveInfo.activityInfo.packageName)) {
                arrayList.add(resolveInfo);
            }
        }
        return arrayList;
    }

    private void filterOutExplicitlyExcludedApps(List list) {
        ActivityInfo activityInfo;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ResolveInfo resolveInfo = (ResolveInfo) it.next();
            if (!(resolveInfo == null || (activityInfo = resolveInfo.activityInfo) == null || !this.excludeFromShareSheet.contains(activityInfo.packageName))) {
                it.remove();
            }
        }
    }

    /* access modifiers changed from: private */
    public void invokeSharingClient(final ResolveInfo resolveInfo) {
        this.isShareInProgress_ = true;
        final String charSequence = resolveInfo.loadLabel(this.context_.getPackageManager()).toString();
        this.builder_.getShortLinkBuilder().generateShortUrl(new Branch.BranchLinkCreateListener() {
            public void onLinkCreate(String str, BranchError branchError) {
                if (branchError == null) {
                    ShareLinkManager.this.shareWithClient(resolveInfo, str, charSequence);
                    return;
                }
                String defaultURL = ShareLinkManager.this.builder_.getDefaultURL();
                if (defaultURL == null || defaultURL.trim().length() <= 0) {
                    Branch.BranchLinkShareListener branchLinkShareListener = ShareLinkManager.this.callback_;
                    if (branchLinkShareListener != null) {
                        branchLinkShareListener.onLinkShareResponse(str, charSequence, branchError);
                    } else {
                        BranchLogger.v("Unable to share link " + branchError.getMessage());
                    }
                    if (branchError.getErrorCode() == -113 || branchError.getErrorCode() == -117) {
                        ShareLinkManager.this.shareWithClient(resolveInfo, str, charSequence);
                        return;
                    }
                    ShareLinkManager.this.cancelShareLinkDialog(false);
                    boolean unused = ShareLinkManager.this.isShareInProgress_ = false;
                    return;
                }
                ShareLinkManager.this.shareWithClient(resolveInfo, defaultURL, charSequence);
            }
        });
    }

    /* access modifiers changed from: private */
    public void shareWithClient(ResolveInfo resolveInfo, String str, String str2) {
        Branch.BranchLinkShareListener branchLinkShareListener = this.callback_;
        if (branchLinkShareListener != null) {
            branchLinkShareListener.onLinkShareResponse(str, str2, (BranchError) null);
        } else {
            BranchLogger.v("Shared link with " + str2);
        }
        if (resolveInfo instanceof CopyLinkItem) {
            addLinkToClipBoard(str, this.builder_.getShareMsg());
            return;
        }
        this.shareLinkIntent_.setPackage(resolveInfo.activityInfo.packageName);
        String shareSub = this.builder_.getShareSub();
        String shareMsg = this.builder_.getShareMsg();
        if (shareSub != null && shareSub.trim().length() > 0) {
            this.shareLinkIntent_.putExtra("android.intent.extra.SUBJECT", shareSub);
        }
        Intent intent = this.shareLinkIntent_;
        intent.putExtra("android.intent.extra.TEXT", shareMsg + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + str);
        this.context_.startActivity(this.shareLinkIntent_);
    }

    private void addLinkToClipBoard(String str, String str2) {
        ((ClipboardManager) this.context_.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(str2, str));
        Toast.makeText(this.context_, this.builder_.getUrlCopiedMessage(), 0).show();
    }

    private class ChooserArrayAdapter extends BaseAdapter {
        public int selectedPos;

        public long getItemId(int i) {
            return (long) i;
        }

        private ChooserArrayAdapter() {
            this.selectedPos = -1;
        }

        public int getCount() {
            return ShareLinkManager.this.displayedAppList_.size();
        }

        public Object getItem(int i) {
            return ShareLinkManager.this.displayedAppList_.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ShareItemView shareItemView;
            if (view == null) {
                ShareLinkManager shareLinkManager = ShareLinkManager.this;
                shareItemView = new ShareItemView(shareLinkManager.context_);
            } else {
                shareItemView = (ShareItemView) view;
            }
            ResolveInfo resolveInfo = (ResolveInfo) ShareLinkManager.this.displayedAppList_.get(i);
            shareItemView.setLabel(resolveInfo.loadLabel(ShareLinkManager.this.context_.getPackageManager()).toString(), resolveInfo.loadIcon(ShareLinkManager.this.context_.getPackageManager()), i == this.selectedPos);
            shareItemView.setTag(resolveInfo);
            return shareItemView;
        }

        public boolean isEnabled(int i) {
            return this.selectedPos < 0;
        }
    }

    private class ShareItemView extends TextView {
        Context context_;
        int iconSizeDP_;

        public ShareItemView(Context context) {
            super(context);
            this.context_ = context;
            setPadding(100, 5, 5, 5);
            setGravity(8388627);
            setMinWidth(this.context_.getResources().getDisplayMetrics().widthPixels);
            this.iconSizeDP_ = ShareLinkManager.this.iconSize_ != 0 ? BranchUtil.dpToPx(context, ShareLinkManager.this.iconSize_) : 0;
        }

        public void setLabel(String str, Drawable drawable, boolean z) {
            setText("\t" + str);
            setTag(str);
            if (drawable == null) {
                setTextAppearance(this.context_, 16973890);
                setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
            } else {
                int i = this.iconSizeDP_;
                if (i != 0) {
                    drawable.setBounds(0, 0, i, i);
                    setCompoundDrawables(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
                } else {
                    setCompoundDrawablesWithIntrinsicBounds(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
                }
                setTextAppearance(this.context_, 16973892);
                int unused = ShareLinkManager.viewItemMinHeight_ = Math.max(ShareLinkManager.viewItemMinHeight_, (drawable.getCurrent().getBounds().centerY() * ShareLinkManager.ICON_SIZER) + 5);
            }
            setMinHeight(ShareLinkManager.viewItemMinHeight_);
            setTextColor(this.context_.getResources().getColor(17170444));
            if (z) {
                setBackgroundColor(ShareLinkManager.this.BG_COLOR_ENABLED);
            } else {
                setBackgroundColor(ShareLinkManager.this.BG_COLOR_DISABLED);
            }
        }
    }

    private class MoreShareItem extends ResolveInfo {
        private MoreShareItem() {
        }

        public CharSequence loadLabel(PackageManager packageManager) {
            return ShareLinkManager.this.builder_.getMoreOptionText();
        }

        public Drawable loadIcon(PackageManager packageManager) {
            return ShareLinkManager.this.builder_.getMoreOptionIcon();
        }
    }

    private class CopyLinkItem extends ResolveInfo {
        private CopyLinkItem() {
        }

        public CharSequence loadLabel(PackageManager packageManager) {
            return ShareLinkManager.this.builder_.getCopyURlText();
        }

        public Drawable loadIcon(PackageManager packageManager) {
            return ShareLinkManager.this.builder_.getCopyUrlIcon();
        }
    }
}
