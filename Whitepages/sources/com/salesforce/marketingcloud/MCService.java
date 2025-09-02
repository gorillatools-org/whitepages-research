package com.salesforce.marketingcloud;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.salesforce.marketingcloud.alarms.b;
import com.salesforce.marketingcloud.http.c;
import com.salesforce.marketingcloud.http.d;

@SuppressLint({"UnknownNullness"})
public class MCService extends c {
    static final String k = "com.salesforce.marketingcloud.HTTP_REQUEST";
    static final String l = "com.salesforce.marketingcloud.ALARM_WAKE";
    static final String m = "com.salesforce.marketingcloud.SYSTEM_BEHAVIOR";

    /* renamed from: n  reason: collision with root package name */
    static final String f14n = "com.salesforce.marketingcloud.TOKEN_REQUEST";
    private static final String o = "behavior";
    private static final String p = "data";
    private static final String q = "alarmName";
    private static final String r = "senderId";
    private static final int s = 3000;

    class a implements OnCompleteListener<String> {
        final /* synthetic */ String[] a;
        final /* synthetic */ Context b;
        final /* synthetic */ String c;

        a(String[] strArr, Context context, String str) {
            this.a = strArr;
            this.b = context;
            this.c = str;
        }

        public void onComplete(Task<String> task) {
            if (task.isSuccessful()) {
                this.a[0] = task.getResult();
            }
            com.salesforce.marketingcloud.messages.push.a.a(this.b, !TextUtils.isEmpty(this.a[0]), this.c, this.a[0]);
        }
    }

    public static void a(Context context, String str) {
        g.d(c.h, "enqueueAlarmWake - %s", str);
        Bundle bundle = new Bundle();
        bundle.putString(q, str);
        a(context, l, bundle);
    }

    public static void b(Context context, String str) {
        g.d(c.h, "enqueueTokenRequest", new Object[0]);
        Bundle bundle = new Bundle();
        bundle.putString(r, str);
        a(context, f14n, bundle);
    }

    private static void c(Context context, String str) {
        if (str == null) {
            g.d(c.h, "alarm name not provided", new Object[0]);
            return;
        }
        g.d(c.h, "handleAlarmWakeup - %s", str);
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(b.j).putExtra("com.salesforce.marketingcloud.WAKE_FOR_ALARM", str));
    }

    static void d(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            g.a(c.h, "Unable to refresh system token.  SenderId was invalid", new Object[0]);
            return;
        }
        g.d(c.h, "handleTokenRequest", new Object[0]);
        try {
            FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new a(new String[]{null}, context, str));
        } catch (Exception e) {
            g.b(c.h, e, "Failed to retrieve InstanceId from Firebase.", new Object[0]);
        }
    }

    public /* bridge */ /* synthetic */ IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    public /* bridge */ /* synthetic */ void onCreate() {
        super.onCreate();
    }

    public /* bridge */ /* synthetic */ void onDestroy() {
        super.onDestroy();
    }

    public /* bridge */ /* synthetic */ int onStartCommand(Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }

    public static void a(Context context, com.salesforce.marketingcloud.http.b bVar) {
        g.d(c.h, "handleHttpRequest - %s", bVar.r());
        a(context, k, bVar.s());
    }

    static void b(Context context, com.salesforce.marketingcloud.http.b bVar) {
        if (bVar == null) {
            g.d(c.h, "request was null", new Object[0]);
            return;
        }
        g.d(c.h, "handleHttpRequest - %s", bVar.r());
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(c.j).putExtra(c.l, bVar.s()).putExtra(c.k, a(context) ? bVar.j() : d.a("No connectivity", -1)));
    }

    public /* bridge */ /* synthetic */ boolean c() {
        return super.c();
    }

    public /* bridge */ /* synthetic */ boolean d() {
        return super.d();
    }

    static void a(Context context, com.salesforce.marketingcloud.behaviors.a aVar, Bundle bundle) {
        g.d(c.h, "enqueueSystemBehavior - %s", aVar);
        Bundle bundle2 = new Bundle();
        bundle2.putString(o, aVar.a);
        bundle2.putBundle("data", bundle);
        a(context, m, bundle2);
    }

    private static void b(Context context, com.salesforce.marketingcloud.behaviors.a aVar, Bundle bundle) {
        if (aVar == null) {
            g.d(c.h, "Behavior was null", new Object[0]);
            return;
        }
        g.d(c.h, "handleSystemBehavior - %s", aVar);
        com.salesforce.marketingcloud.behaviors.c.a(context, aVar, bundle);
    }

    private static void a(Context context, String str, Bundle bundle) {
        c.a(context, MCService.class, 3000, new Intent(str).putExtras(bundle));
    }

    public /* bridge */ /* synthetic */ void b(boolean z) {
        super.b(z);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000a, code lost:
        r1 = r1.getActiveNetworkInfo();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(android.content.Context r1) {
        /*
            java.lang.String r0 = "connectivity"
            java.lang.Object r1 = r1.getSystemService(r0)
            android.net.ConnectivityManager r1 = (android.net.ConnectivityManager) r1
            if (r1 == 0) goto L_0x0018
            android.net.NetworkInfo r1 = r1.getActiveNetworkInfo()
            if (r1 == 0) goto L_0x0018
            boolean r1 = r1.isConnectedOrConnecting()
            if (r1 == 0) goto L_0x0018
            r1 = 1
            goto L_0x0019
        L_0x0018:
            r1 = 0
        L_0x0019:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.MCService.a(android.content.Context):boolean");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004d, code lost:
        if (r1.equals(l) == false) goto L_0x0024;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(android.content.Intent r8) {
        /*
            r7 = this;
            r0 = 0
            java.lang.String r1 = r8.getAction()
            if (r1 != 0) goto L_0x0008
            return
        L_0x0008:
            android.content.Context r2 = r7.getApplicationContext()
            r3 = 500(0x1f4, double:2.47E-321)
            r5 = 50
            boolean r3 = com.salesforce.marketingcloud.util.l.a(r3, r5)
            if (r3 == 0) goto L_0x008a
            com.salesforce.marketingcloud.MarketingCloudSdk r3 = com.salesforce.marketingcloud.MarketingCloudSdk.getInstance()
            if (r3 == 0) goto L_0x008a
            r3 = -1
            int r4 = r1.hashCode()
            switch(r4) {
                case -1341919505: goto L_0x0047;
                case -525195028: goto L_0x003c;
                case 352488053: goto L_0x0031;
                case 848031877: goto L_0x0026;
                default: goto L_0x0024;
            }
        L_0x0024:
            r0 = r3
            goto L_0x0050
        L_0x0026:
            java.lang.String r0 = "com.salesforce.marketingcloud.SYSTEM_BEHAVIOR"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x002f
            goto L_0x0024
        L_0x002f:
            r0 = 3
            goto L_0x0050
        L_0x0031:
            java.lang.String r0 = "com.salesforce.marketingcloud.HTTP_REQUEST"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x003a
            goto L_0x0024
        L_0x003a:
            r0 = 2
            goto L_0x0050
        L_0x003c:
            java.lang.String r0 = "com.salesforce.marketingcloud.TOKEN_REQUEST"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x0045
            goto L_0x0024
        L_0x0045:
            r0 = 1
            goto L_0x0050
        L_0x0047:
            java.lang.String r4 = "com.salesforce.marketingcloud.ALARM_WAKE"
            boolean r1 = r1.equals(r4)
            if (r1 != 0) goto L_0x0050
            goto L_0x0024
        L_0x0050:
            switch(r0) {
                case 0: goto L_0x0080;
                case 1: goto L_0x0076;
                case 2: goto L_0x0068;
                case 3: goto L_0x0054;
                default: goto L_0x0053;
            }
        L_0x0053:
            goto L_0x0093
        L_0x0054:
            java.lang.String r0 = "behavior"
            java.lang.String r0 = r8.getStringExtra(r0)
            com.salesforce.marketingcloud.behaviors.a r0 = com.salesforce.marketingcloud.behaviors.a.a(r0)
            java.lang.String r1 = "data"
            android.os.Bundle r8 = r8.getBundleExtra(r1)
            b(r2, r0, r8)
            goto L_0x0093
        L_0x0068:
            android.os.Bundle r8 = r8.getExtras()
            if (r8 == 0) goto L_0x0093
            com.salesforce.marketingcloud.http.b r8 = com.salesforce.marketingcloud.http.b.a((android.os.Bundle) r8)
            b((android.content.Context) r2, (com.salesforce.marketingcloud.http.b) r8)
            goto L_0x0093
        L_0x0076:
            java.lang.String r0 = "senderId"
            java.lang.String r8 = r8.getStringExtra(r0)
            d(r2, r8)
            goto L_0x0093
        L_0x0080:
            java.lang.String r0 = "alarmName"
            java.lang.String r8 = r8.getStringExtra(r0)
            c(r2, r8)
            goto L_0x0093
        L_0x008a:
            java.lang.String r8 = com.salesforce.marketingcloud.c.h
            java.lang.Object[] r0 = new java.lang.Object[r0]
            java.lang.String r1 = "MarketingCloudSdk#init must be called in your application's onCreate"
            com.salesforce.marketingcloud.g.e((java.lang.String) r8, (java.lang.String) r1, (java.lang.Object[]) r0)
        L_0x0093:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.MCService.a(android.content.Intent):void");
    }
}
