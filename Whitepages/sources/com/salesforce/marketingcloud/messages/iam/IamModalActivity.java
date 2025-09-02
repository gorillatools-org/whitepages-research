package com.salesforce.marketingcloud.messages.iam;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import com.salesforce.marketingcloud.R;
import com.salesforce.marketingcloud.messages.iam.InAppMessage;

@SuppressLint({"UnknownNullness"})
public class IamModalActivity extends f implements View.OnClickListener {

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.salesforce.marketingcloud.messages.iam.InAppMessage$LayoutOrder[] r0 = com.salesforce.marketingcloud.messages.iam.InAppMessage.LayoutOrder.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                com.salesforce.marketingcloud.messages.iam.InAppMessage$LayoutOrder r1 = com.salesforce.marketingcloud.messages.iam.InAppMessage.LayoutOrder.ImageTitleBody     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.salesforce.marketingcloud.messages.iam.InAppMessage$LayoutOrder r1 = com.salesforce.marketingcloud.messages.iam.InAppMessage.LayoutOrder.TitleImageBody     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.iam.IamModalActivity.a.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public int a(InAppMessage inAppMessage) {
        int i = R.layout.mcsdk_iam_modal_inset_itb;
        InAppMessage.Media media = inAppMessage.media();
        int i2 = a.a[inAppMessage.layoutOrder().ordinal()];
        return i2 != 1 ? i2 != 2 ? i : (media == null || media.size() != InAppMessage.Media.ImageSize.e2e) ? R.layout.mcsdk_iam_modal_inset_tib : R.layout.mcsdk_iam_modal_e2e_tib : (media == null || media.size() != InAppMessage.Media.ImageSize.e2e) ? R.layout.mcsdk_iam_modal_inset_itb : R.layout.mcsdk_iam_modal_e2e_itb;
    }

    public /* bridge */ /* synthetic */ void b(InAppMessage.Button button) {
        super.b(button);
    }

    public /* bridge */ /* synthetic */ void finish() {
        super.finish();
    }

    public /* bridge */ /* synthetic */ void onBackPressed() {
        super.onBackPressed();
    }

    public /* bridge */ /* synthetic */ void onClick(View view) {
        super.onClick(view);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!isFinishing()) {
            setContentView(a(c().d()));
            new l(this, c().k()).a(findViewById(16908290), c());
        }
    }

    public /* bridge */ /* synthetic */ void onDismissed() {
        super.onDismissed();
    }

    public /* bridge */ /* synthetic */ void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    public /* bridge */ /* synthetic */ void onSwipeStarted() {
        super.onSwipeStarted();
    }

    public /* bridge */ /* synthetic */ void onViewSettled() {
        super.onViewSettled();
    }
}
