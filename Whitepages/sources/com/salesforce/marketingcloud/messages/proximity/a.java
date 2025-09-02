package com.salesforce.marketingcloud.messages.proximity;

import android.annotation.SuppressLint;
import com.salesforce.marketingcloud.MarketingCloudConfig;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.http.c;
import com.salesforce.marketingcloud.internal.l;
import com.salesforce.marketingcloud.location.LatLon;
import com.salesforce.marketingcloud.messages.Message;
import com.salesforce.marketingcloud.messages.Region;
import com.salesforce.marketingcloud.messages.c;
import com.salesforce.marketingcloud.proximity.e;
import com.salesforce.marketingcloud.storage.j;
import com.salesforce.marketingcloud.storage.k;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONObject;

@SuppressLint({"UnknownNullness"})
public final class a implements com.salesforce.marketingcloud.messages.c, e.a, c.C0017c {
    static final String j = g.a("ProximityMessageManager");
    final j d;
    final com.salesforce.marketingcloud.proximity.e e;
    final c.a f;
    final com.salesforce.marketingcloud.http.c g;
    private final l h;
    private c.b i;

    /* renamed from: com.salesforce.marketingcloud.messages.proximity.a$a  reason: collision with other inner class name */
    class C0027a implements MarketingCloudSdk.WhenReadyListener {
        final /* synthetic */ MarketingCloudConfig a;
        final /* synthetic */ String b;
        final /* synthetic */ LatLon c;

        C0027a(MarketingCloudConfig marketingCloudConfig, String str, LatLon latLon) {
            this.a = marketingCloudConfig;
            this.b = str;
            this.c = latLon;
        }

        public void ready(MarketingCloudSdk marketingCloudSdk) {
            a aVar = a.this;
            aVar.g.a(com.salesforce.marketingcloud.http.a.PROXIMITY_MESSAGES.a(this.a, aVar.d.c(), com.salesforce.marketingcloud.http.a.a(this.a.applicationId(), this.b, this.c)));
        }
    }

    class b extends com.salesforce.marketingcloud.internal.g {
        b(String str, Object... objArr) {
            super(str, objArr);
        }

        /* access modifiers changed from: protected */
        public void a() {
            a.this.d.t().f(3);
        }
    }

    class c extends com.salesforce.marketingcloud.internal.g {
        final /* synthetic */ com.salesforce.marketingcloud.proximity.c b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        c(String str, Object[] objArr, com.salesforce.marketingcloud.proximity.c cVar) {
            super(str, objArr);
            this.b = cVar;
        }

        /* access modifiers changed from: protected */
        public void a() {
            try {
                com.salesforce.marketingcloud.storage.l t = a.this.d.t();
                Region a = t.a(this.b.f(), a.this.d.b());
                if (a == null) {
                    g.a(a.j, "BeaconRegion [%s] did not have matching Region in storage.", this.b);
                } else if (!com.salesforce.marketingcloud.internal.j.a(a)) {
                    g.d(a.j, "Region [%s] was entered.  Will attempt to show associated message.", a.id());
                    com.salesforce.marketingcloud.internal.j.a(a, true);
                    t.a(a.id(), true);
                    a.this.f.b(a);
                    List<String> c2 = t.c(a.id(), 5);
                    if (!c2.isEmpty()) {
                        k s = a.this.d.s();
                        com.salesforce.marketingcloud.util.c b2 = a.this.d.b();
                        for (String next : c2) {
                            Message a2 = s.a(next, b2);
                            if (a2 != null) {
                                a.this.f.a(a, a2);
                            } else {
                                g.a(a.j, "Message with id [%s] not found", next);
                            }
                        }
                    }
                } else {
                    g.a(a.j, "Ignoring entry event.  Already inside Region [%s]", a);
                }
            } catch (Exception e) {
                g.b(a.j, e, "Proximity region (%s) was entered, but failed to check for associated message", this.b.f());
            }
        }
    }

    class d implements Runnable {
        final /* synthetic */ com.salesforce.marketingcloud.proximity.c a;

        d(com.salesforce.marketingcloud.proximity.c cVar) {
            this.a = cVar;
        }

        public void run() {
            com.salesforce.marketingcloud.storage.l t = a.this.d.t();
            Region a2 = t.a(this.a.f(), a.this.d.b());
            if (a2 == null) {
                g.a(a.j, "BeaconRegion [%s] did not have matching Region in storage.", this.a);
            } else if (com.salesforce.marketingcloud.internal.j.a(a2)) {
                com.salesforce.marketingcloud.internal.j.a(a2, false);
                a.this.f.a(a2);
                t.a(a2.id(), false);
            } else {
                g.a(a.j, "Ignoring exit event.  Was not inside BeaconRegion [%s]", this.a);
            }
        }
    }

    class e extends com.salesforce.marketingcloud.internal.g {
        final /* synthetic */ ProximityMessageResponse b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        e(String str, Object[] objArr, ProximityMessageResponse proximityMessageResponse) {
            super(str, objArr);
            this.b = proximityMessageResponse;
        }

        /* access modifiers changed from: protected */
        public void a() {
            com.salesforce.marketingcloud.util.c b2 = a.this.d.b();
            com.salesforce.marketingcloud.storage.l t = a.this.d.t();
            List<Region> a = t.a(3, a.this.d.b());
            if (!a.isEmpty()) {
                Collections.sort(a);
            }
            t.f(3);
            k s = a.this.d.s();
            if (!this.b.beacons().isEmpty()) {
                ArrayList arrayList = new ArrayList();
                for (Region next : this.b.beacons()) {
                    try {
                        boolean z = false;
                        for (Message next2 : next.messages()) {
                            com.salesforce.marketingcloud.messages.b.a(next2, s, b2);
                            s.a(next2, b2);
                            z = true;
                        }
                        if (z) {
                            int binarySearch = Collections.binarySearch(a, next);
                            if (binarySearch >= 0) {
                                com.salesforce.marketingcloud.internal.j.a(next, com.salesforce.marketingcloud.internal.j.a(a.remove(binarySearch)));
                            }
                            t.a(next, b2);
                            arrayList.add(new com.salesforce.marketingcloud.proximity.c(next));
                        }
                    } catch (Exception e) {
                        g.b(a.j, e, "Unable to start monitoring proximity region: %s", next.id());
                    }
                }
                g.a(a.j, "Monitoring beacons from request [%s]", arrayList);
                a.this.e.a((List<com.salesforce.marketingcloud.proximity.c>) arrayList);
            }
            if (!a.isEmpty()) {
                ArrayList arrayList2 = new ArrayList(a.size());
                for (Region cVar : a) {
                    arrayList2.add(new com.salesforce.marketingcloud.proximity.c(cVar));
                }
                g.a(a.j, "Unmonitoring beacons [%s]", arrayList2);
                a.this.e.b((List<com.salesforce.marketingcloud.proximity.c>) arrayList2);
            }
        }
    }

    public a(j jVar, com.salesforce.marketingcloud.proximity.e eVar, com.salesforce.marketingcloud.http.c cVar, l lVar, c.a aVar) {
        this.d = jVar;
        this.e = eVar;
        this.g = cVar;
        this.h = lVar;
        this.f = aVar;
        cVar.a(com.salesforce.marketingcloud.http.a.PROXIMITY_MESSAGES, (c.C0017c) this);
    }

    public void a() {
        this.e.a((e.a) this);
        this.g.a(com.salesforce.marketingcloud.http.a.PROXIMITY_MESSAGES, (c.C0017c) this);
    }

    public void b() {
        this.e.c();
        this.e.b((e.a) this);
        this.g.a(com.salesforce.marketingcloud.http.a.PROXIMITY_MESSAGES);
        this.h.b().execute(new b("disable_beacon_tracking", new Object[0]));
    }

    public void c() {
        g.c(j, "monitorStoredRegions", new Object[0]);
        try {
            List<Region> a = this.d.t().a(3, this.d.b());
            if (!a.isEmpty()) {
                ArrayList arrayList = new ArrayList(a.size());
                for (Region cVar : a) {
                    arrayList.add(new com.salesforce.marketingcloud.proximity.c(cVar));
                }
                g.a(j, "Monitoring beacons [%s]", arrayList);
                this.e.a((List<com.salesforce.marketingcloud.proximity.c>) arrayList);
            }
        } catch (Exception unused) {
            g.b(j, "Unable to monitor stored proximity regions.", new Object[0]);
        }
    }

    public boolean d() {
        return this.e.b();
    }

    /* access modifiers changed from: package-private */
    public void a(ProximityMessageResponse proximityMessageResponse) {
        g.c(j, "Proximity message request contained %d regions", Integer.valueOf(proximityMessageResponse.beacons().size()));
        c.b bVar = this.i;
        if (bVar != null) {
            bVar.a(proximityMessageResponse);
        }
        this.h.b().execute(new e("beacon_response", new Object[0], proximityMessageResponse));
    }

    public void b(com.salesforce.marketingcloud.proximity.c cVar) {
        g.d(j, "Proximity region (%s) entered.", cVar.f());
        this.h.b().execute(new c("", new Object[0], cVar));
    }

    public void a(com.salesforce.marketingcloud.http.b bVar, com.salesforce.marketingcloud.http.d dVar) {
        if (dVar.g()) {
            try {
                a(new ProximityMessageResponse(new JSONObject(dVar.a())));
            } catch (Exception e2) {
                g.b(j, e2, "Error parsing response.", new Object[0]);
            }
        } else {
            g.c(j, "Request failed: %d - %s", Integer.valueOf(dVar.b()), dVar.e());
        }
    }

    public void a(com.salesforce.marketingcloud.proximity.c cVar) {
        g.d(j, "Proximity region (%s) exited.", cVar.f());
        this.h.b().execute(new d(cVar));
    }

    public static void a(j jVar, com.salesforce.marketingcloud.proximity.e eVar, com.salesforce.marketingcloud.http.c cVar, boolean z) {
        eVar.c();
        if (z) {
            jVar.t().f(3);
            jVar.s().e(5);
        }
        cVar.a(com.salesforce.marketingcloud.http.a.PROXIMITY_MESSAGES);
    }

    public void a(LatLon latLon, String str, MarketingCloudConfig marketingCloudConfig, c.b bVar) {
        this.i = bVar;
        try {
            MarketingCloudSdk.requestSdk(new C0027a(marketingCloudConfig, str, latLon));
        } catch (Exception e2) {
            g.b(j, e2, "Failed to update proximity messages", new Object[0]);
        }
    }
}
