package com.salesforce.marketingcloud.analytics.piwama;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import com.salesforce.marketingcloud.MarketingCloudConfig;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.analytics.PiCart;
import com.salesforce.marketingcloud.analytics.PiOrder;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.http.c;
import com.salesforce.marketingcloud.internal.l;
import com.salesforce.marketingcloud.messages.Region;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import com.salesforce.marketingcloud.storage.j;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

@SuppressLint({"UnknownNullness"})
public class i extends com.salesforce.marketingcloud.analytics.i implements c.C0017c {
    static final String h = "user_id";
    static final String i = "session_id";
    static final int j = 100;
    private static final int k = 30;
    private static final String l = "et_background_time_cache";
    static final String m = g.a("PiWamaAnalytic");

    /* renamed from: n  reason: collision with root package name */
    private static final int f19n = 1;
    private static j o;
    final j d;
    final com.salesforce.marketingcloud.http.c e;
    private final MarketingCloudConfig f;
    final l g;

    class a extends com.salesforce.marketingcloud.internal.g {
        final /* synthetic */ j b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        a(String str, Object[] objArr, j jVar) {
            super(str, objArr);
            this.b = jVar;
        }

        /* access modifiers changed from: protected */
        public void a() {
            this.b.m().g(1);
        }
    }

    class b implements Comparator<com.salesforce.marketingcloud.analytics.b> {
        b() {
        }

        /* renamed from: a */
        public int compare(com.salesforce.marketingcloud.analytics.b bVar, com.salesforce.marketingcloud.analytics.b bVar2) {
            if (bVar.f() == null) {
                return bVar2.f() == null ? 0 : -1;
            }
            if (bVar2.f() == null) {
                return 1;
            }
            return bVar.f().compareTo(bVar2.f());
        }
    }

    class c extends com.salesforce.marketingcloud.internal.g {
        final /* synthetic */ long b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        c(String str, Object[] objArr, long j) {
            super(str, objArr);
            this.b = j;
        }

        /* access modifiers changed from: protected */
        public void a() {
            try {
                com.salesforce.marketingcloud.storage.a m = i.this.d.m();
                for (com.salesforce.marketingcloud.analytics.b next : m.i(i.this.d.b())) {
                    int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(this.b - next.b().getTime());
                    if (seconds > 0) {
                        next.b(seconds);
                        next.a(true);
                        next.d(i.this.a());
                        m.b(next, i.this.d.b());
                    }
                }
                com.salesforce.marketingcloud.analytics.b a = com.salesforce.marketingcloud.analytics.b.a(new Date(this.b), 1, 2);
                a.d(i.this.a());
                a.a(true);
                a.c(new b(new Date(this.b)).c().toString());
                m.a(a, i.this.d.b());
            } catch (Exception e) {
                g.b(i.m, e, "Failed to update our PiWama TimeInApp.", new Object[0]);
            }
        }
    }

    class d extends com.salesforce.marketingcloud.internal.g {
        final /* synthetic */ NotificationMessage b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        d(String str, Object[] objArr, NotificationMessage notificationMessage) {
            super(str, objArr);
            this.b = notificationMessage;
        }

        /* access modifiers changed from: protected */
        public void a() {
            if (i.this.d.m().c(1)) {
                i.this.a(System.currentTimeMillis());
            }
            Date date = new Date();
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.b.id());
            Region region = this.b.region();
            if (region != null) {
                arrayList.add(region.id());
            }
            com.salesforce.marketingcloud.analytics.b a = com.salesforce.marketingcloud.analytics.b.a(date, 1, 5, (List<String>) arrayList, false);
            a.d(i.this.a());
            a.c(new e(date, true, a.i()).c().toString());
            i.this.g.b().execute(new com.salesforce.marketingcloud.analytics.a(i.this.d.m(), i.this.d.b(), a));
        }
    }

    class e extends com.salesforce.marketingcloud.internal.g {
        e(String str, Object... objArr) {
            super(str, objArr);
        }

        /* access modifiers changed from: protected */
        public void a() {
            i iVar = i.this;
            i.a(iVar.e, iVar.d.m().o(i.this.d.b()));
        }
    }

    public i(MarketingCloudConfig marketingCloudConfig, j jVar, com.salesforce.marketingcloud.http.c cVar, l lVar) {
        com.salesforce.marketingcloud.util.j.a(marketingCloudConfig, "MarketingCloudConfig may not be null.");
        this.d = (j) com.salesforce.marketingcloud.util.j.a(jVar, "MCStorage may not be null.");
        this.e = (com.salesforce.marketingcloud.http.c) com.salesforce.marketingcloud.util.j.a(cVar, "RequestManager may not be null.");
        this.f = marketingCloudConfig;
        o = a(marketingCloudConfig) ? new k(marketingCloudConfig, jVar) : new a(marketingCloudConfig, jVar);
        cVar.a(com.salesforce.marketingcloud.http.a.PI_ANALYTICS, (c.C0017c) this);
        this.g = lVar;
    }

    private void a(c cVar, long j2) throws IllegalArgumentException {
        JSONObject c2 = cVar.c();
        if (c2 != null) {
            try {
                com.salesforce.marketingcloud.analytics.b a2 = com.salesforce.marketingcloud.analytics.b.a(new Date(j2), 1, cVar.b());
                a2.d(a());
                a2.c(c2.toString());
                a2.a(true);
                if (!TextUtils.isEmpty(a2.e())) {
                    this.g.b().execute(new com.salesforce.marketingcloud.analytics.a(this.d.m(), this.d.b(), a2));
                }
            } catch (Exception e2) {
                g.b(m, e2, "Failed to record PiWamaItem in local storage.", new Object[0]);
                throw new IllegalArgumentException("Failed to record PiWamaItem in local storage.");
            }
        } else {
            throw new IllegalArgumentException("Failed to convert your input type to a JSON Object.");
        }
    }

    private void b() {
        long j2 = this.d.f().getLong(l, -1);
        if (j2 != -1) {
            this.d.f().edit().remove(l).apply();
            Calendar instance = Calendar.getInstance();
            instance.setTimeInMillis(j2);
            Calendar instance2 = Calendar.getInstance();
            instance2.add(12, -30);
            if (instance.before(instance2)) {
                this.d.c().a(com.salesforce.marketingcloud.storage.c.f);
            }
        }
    }

    public void c() {
        this.g.b().execute(new e("send_pi_analytics", new Object[0]));
    }

    public String getPiIdentifier() {
        return this.d.c().b("predictive_intelligence_identifier", (String) null);
    }

    public void setPiIdentifier(String str) {
        if (str == null) {
            this.d.c().a("predictive_intelligence_identifier");
        } else {
            this.d.c().a("predictive_intelligence_identifier", str.trim());
        }
    }

    public void trackCartContents(PiCart piCart) {
        if (piCart != null) {
            long currentTimeMillis = System.currentTimeMillis();
            try {
                a((c) new f(piCart, new Date(currentTimeMillis)), currentTimeMillis);
            } catch (Exception e2) {
                g.b(m, e2, "Failed to add PiWamaAnalytic for trackCartContents.  See LogCat for details.", new Object[0]);
            }
        }
    }

    public void trackCartConversion(PiOrder piOrder) {
        if (piOrder != null) {
            long currentTimeMillis = System.currentTimeMillis();
            try {
                a((c) new g(piOrder, new Date(currentTimeMillis)), currentTimeMillis);
            } catch (IllegalArgumentException e2) {
                g.b(m, e2, "Failed to add PiWamaAnalytic for trackCartConversion.  See LogCat for details.", new Object[0]);
            }
        }
    }

    public void trackPageView(String str, String str2, String str3, String str4) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            a((c) new h(str, str2, str3, str4, new Date(currentTimeMillis)), currentTimeMillis);
        } catch (IllegalArgumentException e2) {
            g.b(m, e2, "Failed to record PiWamaItem for trackPageView.", new Object[0]);
        }
    }

    private static void a(j jVar, l lVar) {
        lVar.b().execute(new a("deleting_pi_analytics", new Object[0], jVar));
    }

    static List<List<com.salesforce.marketingcloud.analytics.b>> b(List<com.salesforce.marketingcloud.analytics.b> list) {
        int size = list.size();
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        for (int i3 = size; i3 > 0; i3 -= 100) {
            int i4 = i2 * 100;
            i2++;
            int i5 = i2 * 100;
            if (i5 > size) {
                i5 = i3 + i4;
            }
            arrayList.add(new ArrayList(list.subList(i4, i5)));
        }
        return arrayList;
    }

    public void a(long j2) {
        this.d.f().edit().putLong(l, j2).apply();
        this.g.b().execute(new c("end_time_in_app", new Object[0], j2));
    }

    public void b(long j2) {
        Date date = new Date(j2);
        b();
        if (!this.d.m().c(1)) {
            try {
                com.salesforce.marketingcloud.analytics.b a2 = com.salesforce.marketingcloud.analytics.b.a(date, 1, 5);
                a2.c(new e(date, false, Collections.emptyList()).c().toString());
                this.g.b().execute(new com.salesforce.marketingcloud.analytics.a(this.d.m(), this.d.b(), a2));
            } catch (Exception e2) {
                g.b(m, e2, "Failed to create WamaItem for TimeInApp.", new Object[0]);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public String a() {
        String piIdentifier = getPiIdentifier();
        return (piIdentifier != null || !this.f.useLegacyPiIdentifier()) ? piIdentifier : com.salesforce.marketingcloud.registration.d.a(this.d);
    }

    private boolean a(MarketingCloudConfig marketingCloudConfig) {
        String trim = marketingCloudConfig.predictiveIntelligenceServerUrl().toLowerCase(Locale.ENGLISH).trim();
        return !trim.startsWith("https://stage.app.igodigital.com/api/v1/collect/qa/qa1s1/process_batch") && !trim.startsWith("https://stage.app.igodigital.com/api/v1/collect/qa/qa3s1/process_batch") && !trim.startsWith("https://app.igodigital.com/api/v1/collect/process_batch");
    }

    public void a(com.salesforce.marketingcloud.http.b bVar, com.salesforce.marketingcloud.http.d dVar) {
        if (dVar.g()) {
            try {
                JSONObject jSONObject = new JSONObject(dVar.a());
                a(com.salesforce.marketingcloud.analytics.c.a(bVar.q() != null ? bVar.q() : ""), jSONObject.getString("user_id"), jSONObject.getString(i));
            } catch (Exception e2) {
                g.b(m, e2, "Error parsing response.", new Object[0]);
            }
        } else {
            g.c(m, "Request failed: %d - %s", Integer.valueOf(dVar.b()), dVar.e());
        }
    }

    private void a(String[] strArr, String str, String str2) {
        this.d.c().a(com.salesforce.marketingcloud.storage.c.g, str);
        this.d.c().a(com.salesforce.marketingcloud.storage.c.f, str2);
        if (strArr != null) {
            this.g.b().execute(new com.salesforce.marketingcloud.analytics.d(this.d.m(), strArr));
        }
    }

    public void a(NotificationMessage notificationMessage) {
        try {
            this.g.b().execute(new d("notification_opened", new Object[0], notificationMessage));
        } catch (Exception e2) {
            g.b(m, e2, "Failed to store our WamaItem for message opened.", new Object[0]);
        }
    }

    static List<List<com.salesforce.marketingcloud.analytics.b>> a(List<com.salesforce.marketingcloud.analytics.b> list) {
        ArrayList arrayList = new ArrayList();
        Collections.sort(list, new b());
        ArrayList arrayList2 = new ArrayList();
        String str = null;
        for (com.salesforce.marketingcloud.analytics.b next : list) {
            if ((str == null || !str.equals(next.f())) && next.f() != null) {
                if (!arrayList2.isEmpty()) {
                    arrayList.add(arrayList2);
                }
                str = next.f();
                arrayList2 = new ArrayList();
            }
            arrayList2.add(next);
        }
        arrayList.add(arrayList2);
        return arrayList;
    }

    static void a(com.salesforce.marketingcloud.http.c cVar, List<com.salesforce.marketingcloud.analytics.b> list) {
        MarketingCloudSdk instance;
        if ((MarketingCloudSdk.isReady() || MarketingCloudSdk.isInitializing()) && (instance = MarketingCloudSdk.getInstance()) != null && !list.isEmpty()) {
            for (List<com.salesforce.marketingcloud.analytics.b> b2 : a(list)) {
                for (List next : b(b2)) {
                    com.salesforce.marketingcloud.http.b a2 = o.a(instance.getRegistrationManager(), instance.getPushMessageManager(), instance.getRegionMessageManager(), (List<com.salesforce.marketingcloud.analytics.b>) next);
                    a2.a(com.salesforce.marketingcloud.analytics.c.a((List<com.salesforce.marketingcloud.analytics.b>) next));
                    cVar.a(a2);
                }
            }
        }
    }

    public static void a(j jVar, com.salesforce.marketingcloud.http.c cVar, l lVar, boolean z) {
        if (z) {
            a(jVar, lVar);
            jVar.c().a("predictive_intelligence_identifier");
        }
        cVar.a(com.salesforce.marketingcloud.http.a.PI_ANALYTICS);
    }

    public void a(boolean z) {
        if (z) {
            a(this.d, this.g);
        }
        com.salesforce.marketingcloud.http.c cVar = this.e;
        if (cVar != null) {
            cVar.a(com.salesforce.marketingcloud.http.a.PI_ANALYTICS);
        }
    }
}
