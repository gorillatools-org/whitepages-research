package com.salesforce.marketingcloud;

import android.annotation.SuppressLint;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.salesforce.marketingcloud.k;
import com.salesforce.marketingcloud.storage.f;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"UnknownNullness"})
public class b implements d, k.e {
    public static final int i = 0;
    public static final int j = 1;
    public static final int k = 2;
    public static final int l = 4;
    public static final int m = 8;

    /* renamed from: n  reason: collision with root package name */
    public static final int f21n = 16;
    public static final int o = 32;
    public static final int p = 64;
    public static final int q = 128;
    public static final int r = 256;
    public static final int s = 512;
    public static final int t = 1024;
    public static final int u = 2048;
    public static final int v = 4096;
    private static final int w = 1;
    private final f d;
    private final k e;
    private C0008b f;
    private c g;
    private c h;

    @Retention(RetentionPolicy.SOURCE)
    public @interface a {
    }

    /* renamed from: com.salesforce.marketingcloud.b$b  reason: collision with other inner class name */
    interface C0008b {
        void a(int i);
    }

    public enum c {
        RTBF(8191),
        ROP(8190),
        DNT(1888),
        NONE(0),
        NO_BEACON(64),
        NO_GEOFENCE(32),
        NO_LOCATION(96);
        
        public final int a;

        private c(int i2) {
            this.a = i2;
        }

        public static c a(String str) {
            try {
                return valueOf(str);
            } catch (Exception unused) {
                return NONE;
            }
        }
    }

    b(k kVar, f fVar) {
        this.e = kVar;
        this.d = fVar;
        c b = b(fVar);
        this.h = b;
        if (b != c.RTBF) {
            kVar.a(k.d.blocked, (k.e) this);
        }
    }

    public static boolean a(int i2, int i3) {
        return (i2 & i3) == i3;
    }

    private static c b(f fVar) {
        String a2 = fVar.a((String) null);
        return a2 != null ? c.a(a2) : c.NONE;
    }

    public static boolean c(int i2, int i3) {
        if (b(i2, i3)) {
            return false;
        }
        switch (i3) {
            case 2:
            case 256:
            case 512:
            case u /*2048*/:
                if (c.ROP.a == i2) {
                    return false;
                }
                break;
            case 4:
            case 8:
            case 16:
            case 32:
            case 64:
            case 128:
            case v /*4096*/:
                break;
            default:
                return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public int a() {
        return this.h.a;
    }

    public String componentName() {
        return "ControlChannel";
    }

    public JSONObject componentState() {
        try {
            return new JSONObject().put("flag", this.h.name());
        } catch (JSONException unused) {
            return null;
        }
    }

    public void onSyncReceived(k.d dVar, JSONObject jSONObject) {
        if (dVar == k.d.blocked && jSONObject.optInt("version", -1) == 1) {
            try {
                a(jSONObject.getJSONObject(FirebaseAnalytics.Param.ITEMS).getInt("blocked"));
            } catch (JSONException e2) {
                g.b(MarketingCloudSdk.u, e2, "Failed to parse [blocked] sync data.", new Object[0]);
            }
        }
    }

    public void tearDown(boolean z) {
        this.e.a(k.d.blocked, (k.e) null);
        this.f = null;
    }

    public static int a(f fVar) {
        return b(fVar).a;
    }

    public static boolean b(int i2, int i3) {
        return !a(i2, i3);
    }

    private synchronized void a(int i2) {
        try {
            c cVar = c.RTBF;
            if (!a(i2, cVar.a)) {
                cVar = c.ROP;
                if (!a(i2, cVar.a)) {
                    cVar = c.DNT;
                    if (!a(i2, cVar.a)) {
                        cVar = c.NONE;
                    }
                }
            }
            g.d(MarketingCloudSdk.u, "Control Channel blocked value %d received", Integer.valueOf(i2));
            this.d.b(cVar.name());
            if (cVar != this.h) {
                C0008b bVar = this.f;
                if (bVar != null) {
                    this.h = cVar;
                    bVar.a(cVar.a);
                } else {
                    this.g = cVar;
                }
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(C0008b bVar) {
        c cVar;
        this.f = bVar;
        if (!(bVar == null || (cVar = this.g) == null)) {
            this.h = cVar;
            this.g = null;
            bVar.a(cVar.a);
        }
    }
}
