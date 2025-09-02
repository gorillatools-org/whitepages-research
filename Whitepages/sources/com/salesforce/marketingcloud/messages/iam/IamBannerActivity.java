package com.salesforce.marketingcloud.messages.iam;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.salesforce.marketingcloud.R;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.messages.iam.InAppMessage;

public class IamBannerActivity extends f {
    private static final String i = g.a("IamBaseActivity");
    private a f;
    private boolean g;
    private long h;

    class a extends a {
        a(long j, long j2) {
            super(j, j2);
        }

        public void onFinish() {
            IamBannerActivity.this.h();
        }
    }

    private int a(InAppMessage inAppMessage) {
        return inAppMessage.type() == InAppMessage.Type.bannerTop ? R.anim.mcsdk_iam_slide_in_from_top : R.anim.mcsdk_iam_slide_in_from_bottom;
    }

    private int b(InAppMessage inAppMessage) {
        return inAppMessage.type() == InAppMessage.Type.bannerTop ? R.anim.mcsdk_iam_slide_out_from_top : R.anim.mcsdk_iam_slide_out_from_bottom;
    }

    public /* bridge */ /* synthetic */ void finish() {
        super.finish();
    }

    /* access modifiers changed from: package-private */
    public void h() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Fragment findFragmentById = supportFragmentManager.findFragmentById(16908290);
        if (findFragmentById != null) {
            supportFragmentManager.beginTransaction().setCustomAnimations(0, b(c().d())).remove(findFragmentById).commitAllowingStateLoss();
        }
        a(j.a(c().c(), a()));
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
            k c = c();
            InAppMessage d = c.d();
            findViewById(16908290).setBackgroundDrawable(new ColorDrawable(g.a(this, d.windowColor(), R.color.mcsdk_iam_default_window_background)));
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            if (supportFragmentManager.findFragmentById(16908290) == null) {
                this.g = true;
                supportFragmentManager.beginTransaction().setCustomAnimations(a(d), 0).add(16908290, (Fragment) c.a(c)).commit();
            }
        }
    }

    public /* bridge */ /* synthetic */ void onDismissed() {
        super.onDismissed();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        a aVar = this.f;
        if (aVar != null) {
            aVar.cancel();
            this.f = null;
        }
    }

    public /* bridge */ /* synthetic */ void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        long displayDuration = b().displayDuration();
        long integer = this.g ? (long) (((double) getResources().getInteger(R.integer.mcsdk_iam_banner_animation_duration)) * -1.0d) : 0;
        this.g = false;
        a(displayDuration, integer);
    }

    public void onSwipeStarted() {
        super.onSwipeStarted();
        a aVar = this.f;
        if (aVar != null) {
            aVar.cancel();
            this.h = this.f.a();
            this.f = null;
        }
    }

    public void onViewSettled() {
        super.onViewSettled();
        a(b().displayDuration(), this.h);
    }

    private void a(long j, long j2) {
        if (j > 0) {
            g.d(i, "Banner dismiss timer set.  Will auto dismiss in %dms", Long.valueOf(j - j2));
            a aVar = new a(j, j2);
            this.f = aVar;
            aVar.start();
        }
    }

    public /* bridge */ /* synthetic */ void b(InAppMessage.Button button) {
        super.b(button);
    }
}
