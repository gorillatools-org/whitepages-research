package com.salesforce.marketingcloud.messages.iam;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import com.salesforce.marketingcloud.R;
import com.salesforce.marketingcloud.messages.iam.InAppMessage;

public class IamFullscreenActivity extends f {
    protected View f;
    private k g;
    private InAppMessage h;

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;
        static final /* synthetic */ int[] b;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002e */
        static {
            /*
                com.salesforce.marketingcloud.messages.iam.InAppMessage$Type[] r0 = com.salesforce.marketingcloud.messages.iam.InAppMessage.Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                b = r0
                r1 = 1
                com.salesforce.marketingcloud.messages.iam.InAppMessage$Type r2 = com.salesforce.marketingcloud.messages.iam.InAppMessage.Type.full     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = b     // Catch:{ NoSuchFieldError -> 0x001d }
                com.salesforce.marketingcloud.messages.iam.InAppMessage$Type r3 = com.salesforce.marketingcloud.messages.iam.InAppMessage.Type.fullImageFill     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                com.salesforce.marketingcloud.messages.iam.InAppMessage$LayoutOrder[] r2 = com.salesforce.marketingcloud.messages.iam.InAppMessage.LayoutOrder.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                a = r2
                com.salesforce.marketingcloud.messages.iam.InAppMessage$LayoutOrder r3 = com.salesforce.marketingcloud.messages.iam.InAppMessage.LayoutOrder.ImageTitleBody     // Catch:{ NoSuchFieldError -> 0x002e }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x002e }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x002e }
            L_0x002e:
                int[] r1 = a     // Catch:{ NoSuchFieldError -> 0x0038 }
                com.salesforce.marketingcloud.messages.iam.InAppMessage$LayoutOrder r2 = com.salesforce.marketingcloud.messages.iam.InAppMessage.LayoutOrder.TitleImageBody     // Catch:{ NoSuchFieldError -> 0x0038 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0038 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0038 }
            L_0x0038:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.iam.IamFullscreenActivity.a.<clinit>():void");
        }
    }

    private int a(InAppMessage inAppMessage) {
        int i = R.layout.mcsdk_iam_full_inset_itb;
        int i2 = a.b[inAppMessage.type().ordinal()];
        if (i2 != 1) {
            return i2 != 2 ? i : (inAppMessage.media() == null || inAppMessage.media().size() != InAppMessage.Media.ImageSize.e2e) ? R.layout.mcsdk_iam_fif_inset_itb : R.layout.mcsdk_iam_fif_e2e_itb;
        }
        int i3 = a.a[inAppMessage.layoutOrder().ordinal()];
        return i3 != 1 ? i3 != 2 ? i : (inAppMessage.media() == null || inAppMessage.media().size() != InAppMessage.Media.ImageSize.e2e) ? R.layout.mcsdk_iam_full_inset_tib : R.layout.mcsdk_iam_full_e2e_tib : (inAppMessage.media() == null || inAppMessage.media().size() != InAppMessage.Media.ImageSize.e2e) ? R.layout.mcsdk_iam_full_inset_itb : R.layout.mcsdk_iam_full_e2e_itb;
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

    public void onConfigurationChanged(Configuration configuration) {
        k kVar;
        super.onConfigurationChanged(configuration);
        InAppMessage inAppMessage = this.h;
        if (inAppMessage != null && inAppMessage.type() == InAppMessage.Type.fullImageFill && configuration.orientation == 2 && (kVar = this.g) != null) {
            kVar.a(j.a(kVar.c(), this.g.b()));
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!isFinishing()) {
            this.f = findViewById(16908290);
            k c = c();
            this.g = c;
            InAppMessage d = c.d();
            this.h = d;
            setContentView(a(d));
            new e(this, this.g.k()).a(this.f, this.g);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        View view = this.f;
        if (view != null) {
            ViewCompat.setOnApplyWindowInsetsListener(view, (OnApplyWindowInsetsListener) null);
        }
        super.onDestroy();
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
