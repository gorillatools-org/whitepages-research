package com.salesforce.marketingcloud.location;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import com.salesforce.marketingcloud.MarketingCloudConfig;
import com.salesforce.marketingcloud.g;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"UnknownNullness"})
public abstract class f extends com.salesforce.marketingcloud.f {
    public static final String d = "NO_GPS_HARDWARE";
    public static final String e = "RECEIVER_NOT_DECLARED_IN_MANIFEST";
    public static final int f = -1;
    private static final String g = "LocationManager";
    static final String h = g.a(g);
    protected static final String i = "com.salesforce.marketingcloud.location.LOCATION_UPDATE";
    protected static final String j = "com.salesforce.marketingcloud.location.GEOFENCE_ERROR";
    protected static final String k = "com.salesforce.marketingcloud.location.GEOFENCE_EVENT";
    protected static final String l = "extra_location";
    protected static final String m = "extra_transition";

    /* renamed from: n  reason: collision with root package name */
    protected static final String f29n = "extra_fence_ids";
    protected static final String o = "extra_error_code";
    protected static final String p = "extra_error_message";

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: java.lang.Exception} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: java.lang.Boolean} */
    /* JADX WARNING: type inference failed for: r5v1, types: [java.lang.Exception] */
    /* JADX WARNING: type inference failed for: r5v3 */
    /* JADX WARNING: type inference failed for: r5v4 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.salesforce.marketingcloud.location.f a(android.content.Context r5, com.salesforce.marketingcloud.MarketingCloudConfig r6) {
        /*
            boolean r0 = com.salesforce.marketingcloud.util.d.b()
            r1 = 0
            if (r0 == 0) goto L_0x0029
            boolean r2 = com.salesforce.marketingcloud.location.LocationReceiver.a((android.content.Context) r5)
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r2)
            if (r2 == 0) goto L_0x0026
            com.salesforce.marketingcloud.location.h r1 = new com.salesforce.marketingcloud.location.h     // Catch:{ Exception -> 0x0017 }
            r1.<init>((android.content.Context) r5, (com.salesforce.marketingcloud.MarketingCloudConfig) r6)     // Catch:{ Exception -> 0x0017 }
            return r1
        L_0x0017:
            r5 = move-exception
            r1 = r5
            java.lang.String r5 = h
            java.lang.String r2 = "LocationManager"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            java.lang.String r4 = "Unable to create real instance of %s"
            com.salesforce.marketingcloud.g.b(r5, r1, r4, r2)
        L_0x0026:
            r5 = r1
            r1 = r3
            goto L_0x0034
        L_0x0029:
            java.lang.String r5 = h
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r3 = "GooglePlayServices Location dependency missing from build."
            com.salesforce.marketingcloud.g.e((java.lang.String) r5, (java.lang.String) r3, (java.lang.Object[]) r2)
            r5 = r1
        L_0x0034:
            com.salesforce.marketingcloud.location.a r2 = new com.salesforce.marketingcloud.location.a
            r2.<init>(r6, r1, r0, r5)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.location.f.a(android.content.Context, com.salesforce.marketingcloud.MarketingCloudConfig):com.salesforce.marketingcloud.location.f");
    }

    public abstract void a(c cVar);

    public abstract void a(e eVar);

    public abstract void a(List<String> list);

    public abstract void a(b... bVarArr);

    public boolean a() {
        return false;
    }

    public abstract void b();

    public abstract void b(c cVar);

    public abstract void b(e eVar);

    public final String componentName() {
        return g;
    }

    public static Intent a(int i2, String str) {
        return new Intent(j).putExtra(o, i2).putExtra(p, str);
    }

    public static Intent a(int i2, List<String> list, Location location) {
        Intent intent = new Intent(k);
        intent.putExtra(m, i2);
        if (list instanceof ArrayList) {
            intent.putStringArrayListExtra(f29n, (ArrayList) list);
        } else {
            intent.putStringArrayListExtra(f29n, new ArrayList(list));
        }
        if (location != null) {
            intent.putExtra(l, location);
        }
        return intent;
    }

    private static JSONObject a(MarketingCloudConfig marketingCloudConfig) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("geofencingEnabled", marketingCloudConfig.geofencingEnabled());
            jSONObject.put("proximityEnabled", marketingCloudConfig.proximityEnabled());
        } catch (Exception e2) {
            g.b(h, e2, "Error creating LocationManager state.", new Object[0]);
        }
        return jSONObject;
    }

    static JSONObject a(MarketingCloudConfig marketingCloudConfig, int i2, String str) {
        JSONObject a = a(marketingCloudConfig);
        try {
            a.put("apiCode", i2);
            a.put("apiMessage", str);
        } catch (Exception e2) {
            g.b(h, e2, "Error creating LocationManager state.", new Object[0]);
        }
        return a;
    }

    static JSONObject a(MarketingCloudConfig marketingCloudConfig, Boolean bool, boolean z, Exception exc) {
        JSONObject a = a(marketingCloudConfig);
        try {
            a.put("serviceAvailable", bool);
            a.put("gmsLocationDependencyAvailable", z);
            if (exc != null) {
                a.put("exceptionMessage", exc.getMessage());
            }
        } catch (JSONException e2) {
            g.b(h, e2, "Error creating LocationManager state.", new Object[0]);
        }
        return a;
    }

    public static Intent a(Location location) {
        return new Intent(i).putExtra(l, location);
    }
}
