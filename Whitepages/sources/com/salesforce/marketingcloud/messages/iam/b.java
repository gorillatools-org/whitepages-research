package com.salesforce.marketingcloud.messages.iam;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.salesforce.marketingcloud.R;
import com.salesforce.marketingcloud.messages.iam.InAppMessage;
import java.util.List;

class b extends d {

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.salesforce.marketingcloud.messages.iam.InAppMessage$Size[] r0 = com.salesforce.marketingcloud.messages.iam.InAppMessage.Size.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                com.salesforce.marketingcloud.messages.iam.InAppMessage$Size r1 = com.salesforce.marketingcloud.messages.iam.InAppMessage.Size.l     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.salesforce.marketingcloud.messages.iam.InAppMessage$Size r1 = com.salesforce.marketingcloud.messages.iam.InAppMessage.Size.m     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.salesforce.marketingcloud.messages.iam.InAppMessage$Size r1 = com.salesforce.marketingcloud.messages.iam.InAppMessage.Size.s     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.iam.b.a.<clinit>():void");
        }
    }

    b(View.OnClickListener onClickListener, Typeface typeface) {
        super(onClickListener, typeface);
    }

    /* access modifiers changed from: protected */
    public void a(View view, String str) {
    }

    /* access modifiers changed from: protected */
    public float b(Resources resources, InAppMessage.Size size) {
        if (size == null) {
            size = InAppMessage.Size.s;
        }
        int i = a.a[size.ordinal()];
        return resources.getDimension(i != 1 ? i != 2 ? R.dimen.mcsdk_iam_banner_btn_font_small : R.dimen.mcsdk_iam_banner_btn_font_medium : R.dimen.mcsdk_iam_banner_btn_font_large);
    }

    /* access modifiers changed from: protected */
    public int c() {
        return R.id.mcsdk_iam_close;
    }

    /* access modifiers changed from: protected */
    public int d() {
        return R.id.mcsdk_iam_media;
    }

    /* access modifiers changed from: protected */
    public int e() {
        return R.id.mcsdk_iam_media;
    }

    /* access modifiers changed from: protected */
    public int f() {
        return R.id.mcsdk_iam_container;
    }

    /* access modifiers changed from: protected */
    public int g() {
        return R.id.mcsdk_iam_parent;
    }

    /* access modifiers changed from: protected */
    public int h() {
        return R.id.mcsdk_iam_title;
    }

    /* access modifiers changed from: protected */
    public float a(Resources resources, InAppMessage.Size size) {
        if (size == null) {
            size = InAppMessage.Size.s;
        }
        int i = a.a[size.ordinal()];
        return resources.getDimension(i != 1 ? i != 2 ? R.dimen.mcsdk_iam_banner_body_font_small : R.dimen.mcsdk_iam_banner_body_font_medium : R.dimen.mcsdk_iam_banner_body_font_large);
    }

    /* access modifiers changed from: protected */
    public int b() {
        return R.id.mcsdk_iam_buttons;
    }

    /* access modifiers changed from: protected */
    public float c(Resources resources, InAppMessage.Size size) {
        if (size == null) {
            size = InAppMessage.Size.s;
        }
        int i = a.a[size.ordinal()];
        return resources.getDimension((i == 1 || i == 2) ? R.dimen.mcsdk_iam_banner_title_font_large : R.dimen.mcsdk_iam_banner_title_font_small);
    }

    /* access modifiers changed from: protected */
    public int a() {
        return R.id.mcsdk_iam_body;
    }

    /* access modifiers changed from: protected */
    public float b(Resources resources) {
        return resources.getDimension(R.dimen.mcsdk_button_group_horizontal_divider);
    }

    /* access modifiers changed from: protected */
    public float c(Resources resources) {
        return resources.getDimension(R.dimen.mcsdk_button_group_vertical_divider);
    }

    /* access modifiers changed from: protected */
    public float a(Resources resources) {
        return resources.getDimension(R.dimen.mcsdk_iam_banner_closebtn_hitbox_increase);
    }

    /* access modifiers changed from: protected */
    public void b(View view, k kVar) {
        ConstraintLayout constraintLayout;
        super.b(view, kVar);
        InAppMessage d = kVar.d();
        if (d.closeButton() != null && !d.a(d.title()) && (constraintLayout = (ConstraintLayout) view.findViewById(f())) != null) {
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(a(), 7, c(), 6);
            constraintSet.applyTo(constraintLayout);
        }
    }

    /* access modifiers changed from: protected */
    public void a(View view, InAppMessage.ButtonConfig buttonConfig, List<InAppMessage.Button> list) {
        super.a(view, InAppMessage.ButtonConfig.twoUp, list);
    }
}
