package com.salesforce.marketingcloud.http;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Build;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.salesforce.marketingcloud.MarketingCloudConfig;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.http.b;
import com.salesforce.marketingcloud.location.LatLon;
import com.salesforce.marketingcloud.storage.c;
import com.salesforce.marketingcloud.storage.j;
import com.salesforce.marketingcloud.util.l;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@SuppressLint({"UnknownNullness"})
public enum a {
    ET_ANALYTICS("POST", 1, "/device/v1/event/analytic", "application/json", "application/json", "analytics_next_retry_time"),
    PI_ANALYTICS("POST", 2, "{0}", "application/json", "application/json", "piwama_next_retry_time"),
    INBOX_MESSAGE("GET", 1, "/device/v1/{0}/message/?deviceid={1}&messagetype=8&contenttype=2", "application/json", "application/json", "inbox_next_retry_time"),
    USER_INITIATED_INBOX_MESSAGE(r1.f, r1.a, r1.b, r1.d, r1.e, r1.c, 60000),
    INBOX_STATUS("PATCH", 1, "/device/v1/{0}/message", "application/json", "application/json", "inbox_status_next_retry_time"),
    GEOFENCE_MESSAGE("GET", 1, "/device/v1/location/{0}/fence/?latitude={1,number,#.########}&longitude={2,number,#.########}&deviceid={3}", "application/json", "application/json", "geofence_next_retry_time"),
    PROXIMITY_MESSAGES("GET", 1, "/device/v1/location/{0}/proximity/?latitude={1,number,#.########}&longitude={2,number,#.########}&deviceid={3}", "application/json", "application/json", "proximity_next_retry_time"),
    REGISTRATION("POST", 1, "/device/v1/registration", "application/json", "application/json", "registration_next_retry_time", 60000),
    SYNC("POST", 1, "/device/v1/{0}/sync/{1}", "application/json", "application/json", "sync_next_retry_time"),
    DEVICE_STATS("POST", 1, "/devicestatistics/v1/analytic", "application/json", "application/json", "et_device_stats_retry_after"),
    EVENTS("POST", 1, "/devicestatistics/v1/event", "application/json", "application/json", "et_events_retry_after");
    
    public static final String s = "X-Subscriber-Token";
    private static final String t = "Bearer %s";
    private static final String u = null;
    private static final long v = 86400000;
    public final int a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final long g;

    /* renamed from: com.salesforce.marketingcloud.http.a$a  reason: collision with other inner class name */
    private static class C0015a {
        static final int a = 1;
        static final int b = 2;

        private C0015a() {
        }
    }

    static {
        u = String.format(l.a, "MarketingCloudSdk/%s (Android %s; %%s; %s/%s) %%s/%%s", new Object[]{MarketingCloudSdk.getSdkVersionName(), Build.VERSION.RELEASE, Build.MANUFACTURER, Build.MODEL});
    }

    private a(String str, int i, String str2, String str3, String str4, String str5) {
        this(r12, r13, str, i, str2, str3, str4, str5, 0);
    }

    private String b(MarketingCloudConfig marketingCloudConfig) {
        return String.format(l.a, u, new Object[]{Locale.getDefault(), marketingCloudConfig.appPackageName(), marketingCloudConfig.appVersionName()});
    }

    /* access modifiers changed from: package-private */
    public long c(SharedPreferences sharedPreferences) {
        return sharedPreferences.getLong(this.c, 0);
    }

    private a(String str, int i, String str2, String str3, String str4, String str5, long j) {
        this.f = str;
        this.a = i;
        this.b = str2;
        this.d = str3;
        this.e = str4;
        this.c = str5;
        this.g = j < 0 ? 0 : j;
    }

    public static Object[] b(String str, String str2) {
        return new Object[]{str, str2};
    }

    public static Object[] c(String str, String str2) {
        return new Object[]{str, str2};
    }

    /* access modifiers changed from: package-private */
    public long a(SharedPreferences sharedPreferences) {
        if (this.g <= 0) {
            return 0;
        }
        return sharedPreferences.getLong(this.c + "_device", 0);
    }

    private String a(String str, String str2) throws MalformedURLException {
        if (str.endsWith(RemoteSettings.FORWARD_SLASH_STRING)) {
            str = str.substring(0, str.length() - 1);
        }
        return new URL(String.format(l.a, "%s%s", new Object[]{str, str2})).toString();
    }

    /* access modifiers changed from: package-private */
    public void b(SharedPreferences sharedPreferences) {
        if (this.g > 0) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putLong(this.c + "_device", this.g + System.currentTimeMillis()).apply();
        }
    }

    private String a(MarketingCloudConfig marketingCloudConfig) {
        return this.a == 1 ? marketingCloudConfig.marketingCloudServerUrl() : marketingCloudConfig.predictiveIntelligenceServerUrl();
    }

    public static Object[] a(String str) {
        return new Object[]{str};
    }

    /* access modifiers changed from: package-private */
    public void a(SharedPreferences sharedPreferences, d dVar) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (dVar.g() && this.g > 0) {
            edit.putLong(this.c + "_device", dVar.c() + this.g);
        }
        List list = dVar.d().get("Retry-After");
        if (list != null && !list.isEmpty()) {
            try {
                long parseLong = Long.parseLong((String) list.get(0)) * 1000;
                String str = this.c;
                long c2 = dVar.c();
                if (parseLong > 86400000) {
                    parseLong = 86400000;
                }
                edit.putLong(str, c2 + parseLong);
            } catch (Exception e2) {
                g.a("MCRequest", (Throwable) e2, "Unable to parse Retry-After value.", new Object[0]);
            }
        }
        edit.apply();
    }

    public static Object[] a(String str, String str2, LatLon latLon) {
        return new Object[]{str, Double.valueOf(latLon.latitude()), Double.valueOf(latLon.longitude()), str2};
    }

    public static void a(Map<String, List<String>> map, c cVar) {
        List list;
        String str;
        if (map != null && !map.isEmpty() && (list = map.get(s)) != null && !list.isEmpty() && (str = (String) list.get(0)) != null && !str.isEmpty()) {
            cVar.a(c.j, str);
        }
    }

    public static boolean a(j jVar) {
        return jVar.c().b(c.j, (String) null) != null;
    }

    public b a(MarketingCloudConfig marketingCloudConfig, c cVar, String str) {
        return a(marketingCloudConfig, cVar, a(marketingCloudConfig), this.b, str, (Map<String, String>) null);
    }

    public b a(MarketingCloudConfig marketingCloudConfig, c cVar, String str, String str2) {
        String a2 = a(marketingCloudConfig);
        if (str2 == null) {
            str2 = this.b;
        }
        return a(marketingCloudConfig, cVar, a2, str2, str, (Map<String, String>) null);
    }

    private b a(MarketingCloudConfig marketingCloudConfig, c cVar, String str, String str2, String str3, Map<String, String> map) {
        try {
            String a2 = a(str, str2);
            g.d("MCRequest", "Executing %s request ...", a2);
            b.a d2 = b.b().b(this.f).a(this).a(this.d).d(a2);
            if (str3 != null) {
                d2.c(str3);
            }
            d2.a("User-Agent", b(marketingCloudConfig));
            d2.a("Authorization", String.format(l.a, t, new Object[]{marketingCloudConfig.accessToken()}));
            d2.a("Accept", this.e);
            d2.a("X-SDK-Version", MarketingCloudSdk.getSdkVersionName());
            String b2 = cVar.b(c.j, (String) null);
            if (b2 != null) {
                d2.a(s, b2);
            }
            if (map != null && !map.isEmpty()) {
                for (Map.Entry next : map.entrySet()) {
                    d2.a((String) next.getKey(), (String) next.getValue());
                }
            }
            return d2.a();
        } catch (Exception e2) {
            g.b("MCRequest", e2, "Failed to execute request.", new Object[0]);
            return null;
        }
    }

    public b a(MarketingCloudConfig marketingCloudConfig, c cVar, Object[] objArr) {
        return a(marketingCloudConfig, cVar, a(marketingCloudConfig), new MessageFormat(this.b, l.a).format(objArr), (String) null, (Map<String, String>) null);
    }

    public b a(MarketingCloudConfig marketingCloudConfig, c cVar, Object[] objArr, String str) {
        return a(marketingCloudConfig, cVar, a(marketingCloudConfig), new MessageFormat(this.b, l.a).format(objArr), str, (Map<String, String>) null);
    }

    public b a(MarketingCloudConfig marketingCloudConfig, c cVar, Object[] objArr, String str, Map<String, String> map) {
        return a(marketingCloudConfig, cVar, a(marketingCloudConfig), new MessageFormat(this.b, l.a).format(objArr), str, map);
    }
}
