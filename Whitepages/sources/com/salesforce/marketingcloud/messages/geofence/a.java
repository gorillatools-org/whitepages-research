package com.salesforce.marketingcloud.messages.geofence;

import android.annotation.SuppressLint;
import android.location.Location;
import com.salesforce.marketingcloud.MarketingCloudConfig;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.http.c;
import com.salesforce.marketingcloud.internal.l;
import com.salesforce.marketingcloud.location.LatLon;
import com.salesforce.marketingcloud.location.f;
import com.salesforce.marketingcloud.messages.Message;
import com.salesforce.marketingcloud.messages.Region;
import com.salesforce.marketingcloud.messages.c;
import com.salesforce.marketingcloud.storage.j;
import com.salesforce.marketingcloud.storage.k;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

@SuppressLint({"UnknownNullness"})
public final class a implements com.salesforce.marketingcloud.messages.c, com.salesforce.marketingcloud.location.c, c.C0017c {
    static final String k = g.a("GeofenceMessageManager");
    final f d;
    final j e;
    final c.a f;
    final com.salesforce.marketingcloud.http.c g;
    private final l h;
    AtomicBoolean i = new AtomicBoolean(false);
    private c.b j;

    /* renamed from: com.salesforce.marketingcloud.messages.geofence.a$a  reason: collision with other inner class name */
    class C0024a implements MarketingCloudSdk.WhenReadyListener {
        final /* synthetic */ MarketingCloudConfig a;
        final /* synthetic */ String b;
        final /* synthetic */ LatLon c;

        C0024a(MarketingCloudConfig marketingCloudConfig, String str, LatLon latLon) {
            this.a = marketingCloudConfig;
            this.b = str;
            this.c = latLon;
        }

        public void ready(MarketingCloudSdk marketingCloudSdk) {
            a aVar = a.this;
            aVar.g.a(com.salesforce.marketingcloud.http.a.GEOFENCE_MESSAGE.a(this.a, aVar.e.c(), com.salesforce.marketingcloud.http.a.a(this.a.applicationId(), this.b, this.c)));
        }
    }

    class b extends com.salesforce.marketingcloud.internal.g {
        final /* synthetic */ String b;
        final /* synthetic */ int c;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        b(String str, Object[] objArr, String str2, int i) {
            super(str, objArr);
            this.b = str2;
            this.c = i;
        }

        /* access modifiers changed from: protected */
        public void a() {
            try {
                com.salesforce.marketingcloud.storage.l t = a.this.e.t();
                Region a = t.a(this.b, a.this.e.b());
                int i = 0;
                if (a == null) {
                    g.c(a.k, "Removing stale geofence from being monitored.", new Object[0]);
                    a.this.d.a((List<String>) Collections.singletonList(this.b));
                    return;
                }
                int i2 = this.c;
                if (i2 == 1) {
                    a.this.f.b(a);
                    i = 3;
                } else if (i2 == 2) {
                    a.this.f.a(a);
                    i = 4;
                }
                if (i != 0) {
                    List<String> c2 = t.c(a.id(), i);
                    if (!c2.isEmpty()) {
                        k s = a.this.e.s();
                        com.salesforce.marketingcloud.util.c b2 = a.this.e.b();
                        for (String next : c2) {
                            Message a2 = s.a(next, b2);
                            if (a2 != null) {
                                a.this.f.a(a, a2);
                            } else {
                                g.a(a.k, "Message with id [%s] not found", next);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                g.b(a.k, e, "Geofence (%s - %d) was tripped, but failed to check for associated message", this.b, Integer.valueOf(this.c));
            }
        }
    }

    class c extends com.salesforce.marketingcloud.internal.g {
        c(String str, Object... objArr) {
            super(str, objArr);
        }

        /* access modifiers changed from: protected */
        public void a() {
            List<String> d = a.this.e.t().d(1);
            if (!d.isEmpty()) {
                a.this.d.a(d);
            }
            a.this.e.t().f(1);
        }
    }

    class d extends com.salesforce.marketingcloud.internal.g {
        final /* synthetic */ GeofenceMessageResponse b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        d(String str, Object[] objArr, GeofenceMessageResponse geofenceMessageResponse) {
            super(str, objArr);
            this.b = geofenceMessageResponse;
        }

        /* access modifiers changed from: protected */
        public void a() {
            com.salesforce.marketingcloud.storage.l t = a.this.e.t();
            List<String> d = t.d(1);
            t.f(1);
            k s = a.this.e.s();
            com.salesforce.marketingcloud.util.c b2 = a.this.e.b();
            if (!this.b.fences().isEmpty()) {
                ArrayList<Region> arrayList = new ArrayList<>();
                for (Region next : this.b.fences()) {
                    try {
                        boolean z = false;
                        for (Message next2 : next.messages()) {
                            com.salesforce.marketingcloud.messages.b.a(next2, s, b2);
                            s.a(next2, b2);
                            z = true;
                        }
                        if (z) {
                            if (!d.remove(next.id())) {
                                arrayList.add(next);
                            }
                            t.a(next, b2);
                        }
                    } catch (Exception e) {
                        g.b(a.k, e, "Unable to start monitoring geofence region: %s", next.id());
                    }
                }
                for (Region a : arrayList) {
                    a.this.d.a(a.a(a));
                }
            }
            if (!d.isEmpty()) {
                a.this.d.a(d);
            }
            a.this.i.set(true);
        }
    }

    class e extends com.salesforce.marketingcloud.internal.g {
        e(String str, Object... objArr) {
            super(str, objArr);
        }

        /* access modifiers changed from: protected */
        public void a() {
            if (a.this.i.get()) {
                g.d(a.k, "Attempt to monitor fences from DB ignored, because they're already monitored.", new Object[0]);
            }
            g.d(a.k, "monitorStoredRegions", new Object[0]);
            try {
                List<Region> a = a.this.e.t().a(1, a.this.e.b());
                if (!a.isEmpty()) {
                    for (Region a2 : a) {
                        a.this.d.a(a.a(a2));
                    }
                }
            } catch (Exception e) {
                g.b(a.k, e, "Unable to monitor stored geofence regions.", new Object[0]);
            }
        }
    }

    public a(j jVar, f fVar, com.salesforce.marketingcloud.http.c cVar, l lVar, c.a aVar) {
        this.e = jVar;
        this.d = fVar;
        this.g = cVar;
        this.f = aVar;
        this.h = lVar;
        cVar.a(com.salesforce.marketingcloud.http.a.GEOFENCE_MESSAGE, (c.C0017c) this);
    }

    private static int a(int i2) {
        if (i2 < 100) {
            return 100;
        }
        return i2;
    }

    public void a() {
        this.d.a((com.salesforce.marketingcloud.location.c) this);
        this.g.a(com.salesforce.marketingcloud.http.a.GEOFENCE_MESSAGE, (c.C0017c) this);
    }

    public void b() {
        f fVar = this.d;
        if (fVar != null) {
            fVar.b((com.salesforce.marketingcloud.location.c) this);
            if (this.e != null) {
                this.h.b().execute(new c("disable_fence_tracking", new Object[0]));
            }
        }
        this.g.a(com.salesforce.marketingcloud.http.a.GEOFENCE_MESSAGE);
        this.i.set(false);
    }

    public void c() {
        this.h.b().execute(new e("monitor_stored_regions", new Object[0]));
    }

    public boolean d() {
        return this.d.a();
    }

    static com.salesforce.marketingcloud.location.b a(Region region) {
        return new com.salesforce.marketingcloud.location.b(region.id(), (float) a(region.radius()), region.center().latitude(), region.center().longitude(), 3);
    }

    /* access modifiers changed from: package-private */
    public void a(GeofenceMessageResponse geofenceMessageResponse) {
        g.c(k, "Geofence message request contained %d regions", Integer.valueOf(geofenceMessageResponse.fences().size()));
        c.b bVar = this.j;
        if (bVar != null) {
            bVar.a(geofenceMessageResponse);
        }
        this.h.b().execute(new d("fence_response", new Object[0], geofenceMessageResponse));
    }

    public void a(int i2, String str) {
        g.a(k, "Region error %d - %s", Integer.valueOf(i2), str);
    }

    public void a(String str, int i2, Location location) {
        String str2 = k;
        g.d(str2, "Geofence (%s - %s) was tripped.", str, Integer.valueOf(i2));
        if (i2 == 4) {
            g.d(str2, "Dwell transition ignore for %s", str);
            return;
        }
        this.h.b().execute(new b("fence_event", new Object[0], str, i2));
    }

    public void a(com.salesforce.marketingcloud.http.b bVar, com.salesforce.marketingcloud.http.d dVar) {
        if (dVar.g()) {
            try {
                a(new GeofenceMessageResponse(new JSONObject(dVar.a())));
            } catch (Exception e2) {
                g.b(k, e2, "Error parsing response.", new Object[0]);
            }
        } else {
            g.c(k, "Request failed: %d - %s", Integer.valueOf(dVar.b()), dVar.e());
        }
    }

    public static void a(j jVar, f fVar, com.salesforce.marketingcloud.http.c cVar, boolean z) {
        List<String> d2 = jVar.t().d(1);
        if (!d2.isEmpty()) {
            fVar.a(d2);
        }
        if (z) {
            jVar.t().f(1);
            k s = jVar.s();
            s.e(3);
            s.e(4);
        }
        cVar.a(com.salesforce.marketingcloud.http.a.GEOFENCE_MESSAGE);
    }

    public void a(LatLon latLon, String str, MarketingCloudConfig marketingCloudConfig, c.b bVar) {
        this.j = bVar;
        try {
            MarketingCloudSdk.requestSdk(new C0024a(marketingCloudConfig, str, latLon));
        } catch (Exception e2) {
            g.b(k, e2, "Failed to update geofence messages", new Object[0]);
        }
    }
}
