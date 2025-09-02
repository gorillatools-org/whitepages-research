package com.salesforce.marketingcloud.messages.iam;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.messages.RegionMessageManager;
import com.salesforce.marketingcloud.messages.iam.InAppMessage;
import com.salesforce.marketingcloud.messages.iam.SwipeDismissConstraintLayout;
import com.salesforce.marketingcloud.util.h;

abstract class f extends FragmentActivity implements View.OnClickListener, SwipeDismissConstraintLayout.SwipeDismissListener {
    private static final int c = 123;
    private static final String d = "completedEvent";
    private static final String e = g.a("IamBaseActivity");
    private k a;
    private j b;

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.salesforce.marketingcloud.messages.iam.InAppMessage$Button$ActionType[] r0 = com.salesforce.marketingcloud.messages.iam.InAppMessage.Button.ActionType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                com.salesforce.marketingcloud.messages.iam.InAppMessage$Button$ActionType r1 = com.salesforce.marketingcloud.messages.iam.InAppMessage.Button.ActionType.url     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.salesforce.marketingcloud.messages.iam.InAppMessage$Button$ActionType r1 = com.salesforce.marketingcloud.messages.iam.InAppMessage.Button.ActionType.pushSettings     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.salesforce.marketingcloud.messages.iam.InAppMessage$Button$ActionType r1 = com.salesforce.marketingcloud.messages.iam.InAppMessage.Button.ActionType.locationSettings     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.iam.f.a.<clinit>():void");
        }
    }

    f() {
    }

    @SuppressLint({"MissingPermission"})
    private void d() {
        if (h.b(this) && MarketingCloudSdk.isReady()) {
            RegionMessageManager regionMessageManager = MarketingCloudSdk.getInstance().getRegionMessageManager();
            try {
                if (regionMessageManager.enableGeofenceMessaging()) {
                    g.a(e, "Geofence messaging enabled from IAM action", new Object[0]);
                }
                if (regionMessageManager.enableProximityMessaging()) {
                    g.a(e, "Proximity messaging enabled from IAM action", new Object[0]);
                }
            } catch (Exception e2) {
                g.b(e, e2, "Unable to enable region messaging", new Object[0]);
            }
        }
    }

    private void e() {
        if (h.b(this)) {
            g.a(e, "Location permission already allowed.  Skipping action from button click.", new Object[0]);
            d();
            finish();
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.ACCESS_FINE_LOCATION")) {
            ActivityCompat.requestPermissions(this, h.b, c);
        } else {
            try {
                startActivityForResult(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS").setData(Uri.fromParts("package", getPackageName(), (String) null)), c);
            } catch (ActivityNotFoundException e2) {
                g.b(e, e2, "Unable to launch application settings page for location permission request.", new Object[0]);
            }
        }
    }

    private void f() {
        Intent putExtra = new Intent("android.settings.APP_NOTIFICATION_SETTINGS").putExtra("android.provider.extra.APP_PACKAGE", getPackageName());
        if (putExtra != null) {
            try {
                startActivity(putExtra);
            } catch (ActivityNotFoundException e2) {
                g.b(e, e2, "Unable to handle push settings button action.", new Object[0]);
            }
        } else {
            g.a(e, "Unable to launch notification settings for this device.", new Object[0]);
        }
        finish();
    }

    private void g() {
        try {
            int i = c().i();
            if (i != 0) {
                getWindow().setStatusBarColor(i);
            }
        } catch (Exception e2) {
            g.a(e, (Throwable) e2, "Failed to find status bar color from meta-data", new Object[0]);
        }
    }

    /* access modifiers changed from: protected */
    public long a() {
        k c2 = c();
        c2.j();
        return c2.b();
    }

    /* access modifiers changed from: protected */
    public InAppMessage b() {
        return this.a.d();
    }

    /* access modifiers changed from: protected */
    public k c() {
        return this.a;
    }

    public void finish() {
        k kVar = this.a;
        if (kVar != null) {
            kVar.a(this.b);
        }
        super.finish();
        overridePendingTransition(0, 0);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == c) {
            d();
        }
        finish();
    }

    public void onBackPressed() {
        this.b = j.b(this.a.c(), a());
        super.onBackPressed();
    }

    public void onClick(View view) {
        if (view.getTag() != null) {
            boolean z = view.getTag() instanceof InAppMessage.Button;
            Object tag = view.getTag();
            if (z) {
                b((InAppMessage.Button) tag);
            } else if (tag instanceof InAppMessage.CloseButton) {
                this.b = j.b(this.a.c(), a());
                finish();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent() != null) {
            this.a = (k) getIntent().getParcelableExtra("messageHandler");
        }
        k kVar = this.a;
        if (kVar == null || !kVar.a()) {
            finish();
            return;
        }
        g();
        if (bundle != null) {
            this.b = (j) bundle.getParcelable(d);
        }
    }

    public void onDismissed() {
        this.b = j.b(this.a.c(), a());
        finish();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        c().f();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        d();
        finish();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        c().g();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable(d, this.b);
    }

    public void onSwipeStarted() {
    }

    public void onViewSettled() {
    }

    private void a(InAppMessage.Button button) {
        PendingIntent a2 = c().a(this, button);
        if (a2 != null) {
            try {
                a2.send();
            } catch (PendingIntent.CanceledException e2) {
                g.b(e, e2, "Unable to launch url for button click", new Object[0]);
            }
        } else {
            g.a(e, "No PendingIntent returned for button click.", new Object[0]);
        }
        finish();
    }

    public void b(InAppMessage.Button button) {
        if (button != null) {
            this.b = j.a(this.a.c(), a(), button);
            int i = a.a[button.actionType().ordinal()];
            if (i == 1) {
                a(button);
            } else if (i == 2) {
                f();
            } else if (i != 3) {
                finish();
            } else {
                e();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(j jVar) {
        this.b = jVar;
    }
}
