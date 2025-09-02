package com.salesforce.marketingcloud.analytics;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.messages.Region;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import com.salesforce.marketingcloud.util.j;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"UnknownNullness"})
public final class b {
    private static final String a = "~!AnalyticItem";
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 4;
    public static final int e = 5;
    public static final int f = 5;
    public static final int g = 6;
    public static final int h = 7;
    public static final int i = 10;
    public static final int j = 11;
    public static final int k = 12;
    public static final int l = 13;
    public static final int m = 14;

    /* renamed from: n  reason: collision with root package name */
    public static final int f17n = 15;
    public static final int o = 16;
    public static final int p = 17;
    public static final int q = 88888;
    public static final int r = 888;
    public static final int s = 8888;
    public static final List<Integer> t = Collections.unmodifiableList(Arrays.asList(new Integer[]{3, 14}));
    public static final int u = 0;
    public static final int v = 1;
    private String A;
    private int B;
    private int C;
    private boolean D;
    private String E;
    private String F;
    private final String G;
    private final Date w;
    private final int x;
    private final int y;
    private final List<String> z;

    @Retention(RetentionPolicy.SOURCE)
    public @interface a {
    }

    @SuppressLint({"UniqueConstants"})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.salesforce.marketingcloud.analytics.b$b  reason: collision with other inner class name */
    public @interface C0004b {
    }

    private b(Date date, int i2, int i3, List<String> list, String str, boolean z2, String str2) {
        ArrayList arrayList = new ArrayList();
        this.z = arrayList;
        this.w = (Date) j.a(date, "The Date is null.");
        boolean z3 = false;
        j.a(i2 == 0 || i2 == 1, "The Product Type must be one of AnalyticProductType");
        this.x = i2;
        j.a(i3 > 0 ? true : z3, "AnalyticType must be a valid int > 0.");
        this.y = i3;
        if (list != null && !list.isEmpty()) {
            arrayList.addAll(list);
        }
        this.G = str;
        this.F = a(str2);
        this.D = z2;
    }

    public int a() {
        return this.y;
    }

    public Date b() {
        return this.w;
    }

    public String c() {
        return this.F;
    }

    public int d() {
        return this.B;
    }

    public String e() {
        return this.E;
    }

    public String f() {
        return this.A;
    }

    public int g() {
        return this.C;
    }

    public boolean h() {
        return this.D;
    }

    public List<String> i() {
        List<String> list;
        synchronized (this.z) {
            list = this.z;
        }
        return list;
    }

    public int j() {
        return this.x;
    }

    public String k() {
        return this.G;
    }

    private String a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            String str2 = this.G;
            if (str2 != null) {
                jSONObject.put("requestId", str2);
            }
            if (TextUtils.isEmpty(str)) {
                return jSONObject.toString();
            }
            jSONObject.put("propertyBag", new JSONObject(str));
            return jSONObject.toString();
        } catch (JSONException e2) {
            g.e(a, e2, "unable to build et json payload", new Object[0]);
        }
    }

    public void b(String str) {
        this.F = str;
    }

    public void c(String str) {
        this.E = str;
    }

    public void d(String str) {
        this.A = str;
    }

    public static b a(Date date, int i2, int i3) {
        return a(date, i2, i3, Collections.emptyList(), (String) null, false);
    }

    public void b(int i2) {
        this.C = i2;
    }

    public static b a(Date date, int i2, int i3, NotificationMessage notificationMessage, boolean z2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(notificationMessage.id());
        Region region = notificationMessage.region();
        if (region != null) {
            arrayList.add(region.id());
        }
        return new b(date, i2, i3, arrayList, notificationMessage.requestId(), z2, notificationMessage.propertyBag());
    }

    public static b a(Date date, int i2, int i3, List<String> list, String str, boolean z2) {
        return new b(date, i2, i3, list, str, z2, (String) null);
    }

    public static b a(Date date, int i2, int i3, List<String> list, boolean z2) {
        return a(date, i2, i3, list, (String) null, z2);
    }

    public void a(int i2) {
        this.B = i2;
    }

    public void a(boolean z2) {
        this.D = z2;
    }
}
