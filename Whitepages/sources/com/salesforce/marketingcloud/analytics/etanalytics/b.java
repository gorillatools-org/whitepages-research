package com.salesforce.marketingcloud.analytics.etanalytics;

import com.salesforce.marketingcloud.analytics.AnalyticsManager;
import com.salesforce.marketingcloud.analytics.i;
import com.salesforce.marketingcloud.internal.g;
import com.salesforce.marketingcloud.internal.l;
import com.salesforce.marketingcloud.messages.Region;
import com.salesforce.marketingcloud.messages.inbox.InboxMessage;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import com.salesforce.marketingcloud.storage.j;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class b extends i {
    private static final int f = 0;
    final j d;
    private final l e;

    class a extends g {
        final /* synthetic */ j b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        a(String str, Object[] objArr, j jVar) {
            super(str, objArr);
            this.b = jVar;
        }

        /* access modifiers changed from: protected */
        public void a() {
            this.b.m().a(0);
        }
    }

    /* renamed from: com.salesforce.marketingcloud.analytics.etanalytics.b$b  reason: collision with other inner class name */
    class C0006b extends g {
        final /* synthetic */ long b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        C0006b(String str, Object[] objArr, long j) {
            super(str, objArr);
            this.b = j;
        }

        /* access modifiers changed from: protected */
        public void a() {
            if (!b.this.d.m().c(0)) {
                try {
                    b.this.d.m().a(com.salesforce.marketingcloud.analytics.b.a(new Date(this.b), 0, 4), b.this.d.b());
                } catch (Exception e) {
                    com.salesforce.marketingcloud.g.b(AnalyticsManager.TAG, e, "Failed to create our EtAnalyticItem for TimeInApp.", new Object[0]);
                }
            }
        }
    }

    class c extends g {
        final /* synthetic */ long b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        c(String str, Object[] objArr, long j) {
            super(str, objArr);
            this.b = j;
        }

        /* access modifiers changed from: protected */
        public void a() {
            try {
                List<com.salesforce.marketingcloud.analytics.b> g = b.this.d.m().g(b.this.d.b());
                if (!g.isEmpty()) {
                    for (com.salesforce.marketingcloud.analytics.b next : g) {
                        int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(this.b - next.b().getTime());
                        if (seconds > 0) {
                            next.b(seconds);
                            next.a(true);
                            b.this.d.m().b(next, b.this.d.b());
                        }
                    }
                }
            } catch (Exception e) {
                com.salesforce.marketingcloud.g.b(AnalyticsManager.TAG, e, "Failed to update our EtAnalytic TimeInApp.", new Object[0]);
            }
        }
    }

    class d extends g {
        final /* synthetic */ long b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        d(String str, Object[] objArr, long j) {
            super(str, objArr);
            this.b = j;
        }

        /* access modifiers changed from: protected */
        public void a() {
            try {
                List<com.salesforce.marketingcloud.analytics.b> h = b.this.d.m().h(b.this.d.b());
                if (!h.isEmpty()) {
                    for (com.salesforce.marketingcloud.analytics.b next : h) {
                        int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(this.b - next.b().getTime());
                        if (seconds > 0) {
                            next.b(seconds);
                            next.a(true);
                            b.this.d.m().b(next, b.this.d.b());
                        }
                    }
                }
            } catch (Exception e) {
                com.salesforce.marketingcloud.g.b(AnalyticsManager.TAG, e, "Failed to update local storage for stopTimeInAllRegions.", new Object[0]);
            }
        }
    }

    class e extends g {
        final /* synthetic */ Region b;
        final /* synthetic */ Date c;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        e(String str, Object[] objArr, Region region, Date date) {
            super(str, objArr);
            this.b = region;
            this.c = date;
        }

        /* access modifiers changed from: protected */
        public void a() {
            try {
                List<com.salesforce.marketingcloud.analytics.b> b2 = b.this.d.m().b(this.b, b.this.d.b());
                if (!b2.isEmpty()) {
                    for (com.salesforce.marketingcloud.analytics.b next : b2) {
                        int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(this.c.getTime() - next.b().getTime());
                        if (seconds > 0) {
                            next.b(seconds);
                            next.a(true);
                            b.this.d.m().b(next, b.this.d.b());
                        }
                    }
                }
            } catch (Exception e) {
                com.salesforce.marketingcloud.g.b(AnalyticsManager.TAG, e, "Failed to record EtAnalyticItem for stopTimeInRegion.", new Object[0]);
            }
        }
    }

    public b(j jVar, l lVar) {
        this.d = jVar;
        this.e = lVar;
    }

    private static void a(l lVar, j jVar) {
        lVar.b().execute(new a("delete_analytics", new Object[0], jVar));
    }

    public void b(Region region) {
        Date date = new Date();
        b(region, date);
        if (region.regionType() != 3) {
            this.e.b().execute(new com.salesforce.marketingcloud.analytics.a(this.d.m(), this.d.b(), com.salesforce.marketingcloud.analytics.b.a(date, 0, 7, (List<String>) Collections.singletonList(region.id()), true)));
        }
    }

    public void c(long j) {
        this.e.b().execute(new d("end_region_counter", new Object[0], j));
    }

    public void trackInboxOpenEvent(InboxMessage inboxMessage) {
        if (inboxMessage == null) {
            com.salesforce.marketingcloud.g.e(AnalyticsManager.TAG, "InboxMessage was null. Call to trackInboxOpenEvent() ignored.", new Object[0]);
        } else {
            this.e.b().execute(new com.salesforce.marketingcloud.analytics.a(this.d.m(), this.d.b(), com.salesforce.marketingcloud.analytics.b.a(new Date(), 0, 15, Collections.singletonList(inboxMessage.id()), com.salesforce.marketingcloud.internal.b.b(inboxMessage), true)));
        }
    }

    public void a(long j) {
        this.e.b().execute(new c("end_app_counter", new Object[0], j));
    }

    public void b(long j) {
        this.e.b().execute(new C0006b("start_app_counter", new Object[0], j));
    }

    private void b(Region region, Date date) {
        this.e.b().execute(new e("end_region_counter", new Object[0], region, date));
    }

    public void a(NotificationMessage notificationMessage) {
        a(System.currentTimeMillis());
        this.e.b().execute(new com.salesforce.marketingcloud.analytics.a(this.d.m(), this.d.b(), com.salesforce.marketingcloud.analytics.b.a(new Date(), 0, 5, notificationMessage, false)));
    }

    public void a(Region region) {
        Date date = new Date();
        a(region, date);
        this.e.b().execute(new com.salesforce.marketingcloud.analytics.a(this.d.m(), this.d.b(), com.salesforce.marketingcloud.analytics.b.a(date, 0, region.regionType() == 1 ? 6 : 12, (List<String>) Collections.singletonList(region.id()), true)));
    }

    public static void a(j jVar, l lVar, boolean z) {
        if (z) {
            a(lVar, jVar);
        }
    }

    private void a(Region region, Date date) {
        this.e.b().execute(new com.salesforce.marketingcloud.analytics.a(this.d.m(), this.d.b(), com.salesforce.marketingcloud.analytics.b.a(date, 0, region.regionType() == 1 ? 11 : 13, (List<String>) Collections.singletonList(region.id()), false)));
    }

    public void a(boolean z) {
        if (z) {
            a(this.e, this.d);
        }
    }
}
