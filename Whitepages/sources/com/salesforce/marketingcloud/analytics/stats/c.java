package com.salesforce.marketingcloud.analytics.stats;

import androidx.collection.ArrayMap;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.salesforce.marketingcloud.MarketingCloudConfig;
import com.salesforce.marketingcloud.alarms.a;
import com.salesforce.marketingcloud.alarms.b;
import com.salesforce.marketingcloud.analytics.i;
import com.salesforce.marketingcloud.analytics.l;
import com.salesforce.marketingcloud.config.b;
import com.salesforce.marketingcloud.http.c;
import com.salesforce.marketingcloud.internal.l;
import com.salesforce.marketingcloud.messages.iam.InAppMessage;
import com.salesforce.marketingcloud.sfmcsdk.components.events.Event;
import com.salesforce.marketingcloud.storage.db.k;
import com.salesforce.marketingcloud.storage.j;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class c extends i implements c.C0017c, b.C0003b {
    static final String k = com.salesforce.marketingcloud.g.a("DeviceStats");
    private static final int l = 1000;
    private static final int m = 1;
    final String d;
    final j e;
    final com.salesforce.marketingcloud.http.c f;
    final MarketingCloudConfig g;
    final com.salesforce.marketingcloud.alarms.b h;
    protected final l i;
    public final boolean j;

    class a extends com.salesforce.marketingcloud.internal.g {
        a(String str, Object... objArr) {
            super(str, objArr);
        }

        /* access modifiers changed from: protected */
        public void a() {
            com.salesforce.marketingcloud.storage.d n2 = c.this.e.n();
            com.salesforce.marketingcloud.util.c b2 = c.this.e.b();
            List<b> j = n2.j(b2);
            if (!j.isEmpty()) {
                Date date = new Date();
                for (b next : j) {
                    try {
                        next.a(date);
                        n2.a(next, b2);
                    } catch (Exception e) {
                        com.salesforce.marketingcloud.g.b(c.k, e, "Unable to update sync event analytic [%s]", Integer.valueOf(next.d()));
                    }
                }
            }
            com.salesforce.marketingcloud.g.c(c.k, "Handling app close and sending stats.", new Object[0]);
        }
    }

    class b extends com.salesforce.marketingcloud.internal.g {
        final /* synthetic */ InAppMessage b;
        final /* synthetic */ JSONObject c;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        b(String str, Object[] objArr, InAppMessage inAppMessage, JSONObject jSONObject) {
            super(str, objArr);
            this.b = inAppMessage;
            this.c = jSONObject;
        }

        /* access modifiers changed from: protected */
        public void a() {
            if (com.salesforce.marketingcloud.config.a.e() == null || com.salesforce.marketingcloud.config.a.e().h()) {
                com.salesforce.marketingcloud.g.c(c.k, "InAppMessage throttled event stat for message id %s", this.b.id());
                Date date = new Date();
                try {
                    c.this.i.b().execute(new a(c.this.e.n(), c.this.e.b(), b.a(b.l, date, d.a(c.this.g.applicationId(), c.this.d, date, this.b.id(), com.salesforce.marketingcloud.internal.a.a(this.b), this.c), true)));
                } catch (JSONException e) {
                    com.salesforce.marketingcloud.g.b(c.k, e, "Failed to record iam throttled event stat.", new Object[0]);
                }
            }
        }
    }

    /* renamed from: com.salesforce.marketingcloud.analytics.stats.c$c  reason: collision with other inner class name */
    class C0007c extends com.salesforce.marketingcloud.internal.g {
        final /* synthetic */ JSONObject b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        C0007c(String str, Object[] objArr, JSONObject jSONObject) {
            super(str, objArr);
            this.b = jSONObject;
        }

        /* access modifiers changed from: protected */
        public void a() {
            if (com.salesforce.marketingcloud.config.a.e() == null || com.salesforce.marketingcloud.config.a.e().h()) {
                try {
                    Date date = new Date();
                    c.this.i.b().execute(new a(c.this.e.n(), c.this.e.b(), b.a(b.l, date, d.a(c.this.g.applicationId(), c.this.d, date, (String) null, (String) null, this.b), true)));
                } catch (Exception e) {
                    com.salesforce.marketingcloud.g.b(c.k, e, "Failed to record syncGateTimeOut Event stat.", new Object[0]);
                }
            }
        }
    }

    class d extends com.salesforce.marketingcloud.internal.g {
        final /* synthetic */ JSONObject b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        d(String str, Object[] objArr, JSONObject jSONObject) {
            super(str, objArr);
            this.b = jSONObject;
        }

        /* access modifiers changed from: protected */
        public void a() {
            if (com.salesforce.marketingcloud.config.a.e() == null || com.salesforce.marketingcloud.config.a.e().h()) {
                try {
                    Date date = new Date();
                    c.this.i.b().execute(new a(c.this.e.n(), c.this.e.b(), b.a(b.l, date, d.a(c.this.g.applicationId(), c.this.d, date, (String) null, (String) null, this.b), true)));
                } catch (JSONException e) {
                    com.salesforce.marketingcloud.g.b(c.k, e, "Failed to record onInvalidConfig Event stat.", new Object[0]);
                }
            }
        }
    }

    class e extends com.salesforce.marketingcloud.internal.g {
        final /* synthetic */ JSONObject b;
        final /* synthetic */ l.a c;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        e(String str, Object[] objArr, JSONObject jSONObject, l.a aVar) {
            super(str, objArr);
            this.b = jSONObject;
            this.c = aVar;
        }

        /* access modifiers changed from: protected */
        public void a() {
            try {
                if (com.salesforce.marketingcloud.config.a.e() == null || com.salesforce.marketingcloud.config.a.e().l()) {
                    Date date = new Date();
                    c.this.i.b().execute(new a(c.this.e.n(), c.this.e.b(), b.a(b.m, date, d.a(c.this.g.applicationId(), c.this.d, date, this.b), true)));
                }
            } catch (Exception e) {
                com.salesforce.marketingcloud.g.b(c.k, e, "Failed to record onTelemetryEvent stat. %s", this.c.name());
            }
        }
    }

    class f extends com.salesforce.marketingcloud.internal.g {
        final /* synthetic */ Event[] b;
        final /* synthetic */ Date c;
        final /* synthetic */ com.salesforce.marketingcloud.analytics.e d;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        f(String str, Object[] objArr, Event[] eventArr, Date date, com.salesforce.marketingcloud.analytics.e eVar) {
            super(str, objArr);
            this.b = eventArr;
            this.c = date;
            this.d = eVar;
        }

        /* access modifiers changed from: protected */
        public void a() {
            Event[] eventArr;
            com.salesforce.marketingcloud.config.a e2 = com.salesforce.marketingcloud.config.a.e();
            if (e2 != null) {
                Event[] eventArr2 = this.b;
                int length = eventArr2.length;
                Boolean bool = null;
                Boolean bool2 = null;
                Boolean bool3 = null;
                int i = 0;
                Boolean bool4 = null;
                while (i < length) {
                    Event event = eventArr2[i];
                    try {
                        if (e2.b(event.name())) {
                            int i2 = h.a[event.getCategory().ordinal()];
                            if (i2 == 1) {
                                if (bool4 == null) {
                                    bool4 = Boolean.valueOf(e2.g());
                                }
                                if (!bool4.booleanValue()) {
                                }
                            } else if (i2 == 2) {
                                if (bool == null) {
                                    bool = Boolean.valueOf(e2.i());
                                }
                                if (!bool.booleanValue()) {
                                }
                            } else if (i2 == 3) {
                                if (bool2 == null) {
                                    bool2 = Boolean.valueOf(e2.j());
                                }
                                if (!bool2.booleanValue()) {
                                }
                            } else if (i2 == 4) {
                                if (bool3 == null) {
                                    bool3 = Boolean.valueOf(e2.k());
                                }
                                if (!bool3.booleanValue()) {
                                }
                            }
                            com.salesforce.marketingcloud.g.c(c.k, "Event tracked %s( %s ) with Attributes: %s", event.getClass().getSimpleName(), event.name(), event.attributes());
                            eventArr = eventArr2;
                            try {
                                c.this.i.b().execute(new a(c.this.e.n(), c.this.e.b(), b.a(105, this.c, d.a(c.this.g.applicationId(), c.this.d, this.c, event.name(), event.id, event.toJson().getJSONObject(k.a.h), this.d.e(), e2.a(event.name())), true)));
                            } catch (Exception e3) {
                                e = e3;
                            }
                            i++;
                            eventArr2 = eventArr;
                        }
                        eventArr = eventArr2;
                    } catch (Exception e4) {
                        e = e4;
                        eventArr = eventArr2;
                        com.salesforce.marketingcloud.g.b(c.k, "Failed to record event in devstats", e);
                        i++;
                        eventArr2 = eventArr;
                    }
                    i++;
                    eventArr2 = eventArr;
                }
            }
        }
    }

    class g extends com.salesforce.marketingcloud.internal.g {
        final /* synthetic */ com.salesforce.marketingcloud.http.a b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        g(String str, Object[] objArr, com.salesforce.marketingcloud.http.a aVar) {
            super(str, objArr);
            this.b = aVar;
        }

        /* access modifiers changed from: protected */
        public void a() {
            com.salesforce.marketingcloud.http.a aVar = this.b;
            com.salesforce.marketingcloud.http.a aVar2 = com.salesforce.marketingcloud.http.a.EVENTS;
            if (aVar != aVar2 || com.salesforce.marketingcloud.http.a.a(c.this.e)) {
                com.salesforce.marketingcloud.http.a aVar3 = this.b;
                com.salesforce.marketingcloud.http.a aVar4 = com.salesforce.marketingcloud.http.a.DEVICE_STATS;
                List<b> k = aVar3 == aVar4 ? c.this.e.n().k(c.this.e.b()) : c.this.e.n().p(c.this.e.b());
                if (!k.isEmpty()) {
                    com.salesforce.marketingcloud.g.c(c.k, "Preparing payload for device statistics.", new Object[0]);
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("applicationId", c.this.g.applicationId());
                        jSONObject.put("deviceId", c.this.d);
                        JSONArray jSONArray = new JSONArray();
                        JSONObject jSONObject2 = new JSONObject();
                        jSONArray.put(jSONObject2);
                        jSONObject.put("nodes", jSONArray);
                        jSONObject2.put("version", 1);
                        jSONObject2.put("name", "event");
                        Integer num = 1000;
                        String str = null;
                        if (this.b == aVar2) {
                            com.salesforce.marketingcloud.config.b a = com.salesforce.marketingcloud.config.a.e() != null ? com.salesforce.marketingcloud.config.a.e().a(c.this.e, b.C0011b.EVENTS.name()) : null;
                            if (a != null) {
                                str = a.f();
                                if (a.e() != null) {
                                    num = a.e();
                                }
                            }
                        }
                        for (Map.Entry next : c.this.a(k, num.intValue()).entrySet()) {
                            jSONObject2.put(FirebaseAnalytics.Param.ITEMS, next.getValue());
                            com.salesforce.marketingcloud.http.a aVar5 = this.b;
                            c cVar = c.this;
                            com.salesforce.marketingcloud.http.b a2 = aVar5.a(cVar.g, cVar.e.c(), jSONObject.toString(), str);
                            a2.a((String) next.getKey());
                            c.this.f.a(a2);
                        }
                    } catch (Exception e) {
                        com.salesforce.marketingcloud.g.b(c.k, e, "Failed to start sync events request.", new Object[0]);
                    }
                } else {
                    com.salesforce.marketingcloud.http.a aVar6 = this.b;
                    if (aVar6 == aVar4) {
                        c.this.h.d(a.C0001a.DEVICE_STATS);
                    } else if (aVar6 == aVar2) {
                        c.this.h.d(a.C0001a.EVENTS);
                    }
                }
            } else {
                c.this.h.d(a.C0001a.EVENTS);
            }
        }
    }

    static /* synthetic */ class h {
        static final /* synthetic */ int[] a;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.salesforce.marketingcloud.sfmcsdk.components.events.Event$Category[] r0 = com.salesforce.marketingcloud.sfmcsdk.components.events.Event.Category.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                com.salesforce.marketingcloud.sfmcsdk.components.events.Event$Category r1 = com.salesforce.marketingcloud.sfmcsdk.components.events.Event.Category.APPLICATION     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.salesforce.marketingcloud.sfmcsdk.components.events.Event$Category r1 = com.salesforce.marketingcloud.sfmcsdk.components.events.Event.Category.ENGAGEMENT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.salesforce.marketingcloud.sfmcsdk.components.events.Event$Category r1 = com.salesforce.marketingcloud.sfmcsdk.components.events.Event.Category.IDENTITY     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.salesforce.marketingcloud.sfmcsdk.components.events.Event$Category r1 = com.salesforce.marketingcloud.sfmcsdk.components.events.Event.Category.SYSTEM     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.analytics.stats.c.h.<clinit>():void");
        }
    }

    public c(MarketingCloudConfig marketingCloudConfig, String str, boolean z, j jVar, com.salesforce.marketingcloud.http.c cVar, com.salesforce.marketingcloud.alarms.b bVar, com.salesforce.marketingcloud.internal.l lVar) {
        this.g = marketingCloudConfig;
        this.d = str;
        this.j = z;
        this.e = jVar;
        this.f = cVar;
        this.h = bVar;
        this.i = lVar;
        cVar.a(com.salesforce.marketingcloud.http.a.DEVICE_STATS, (c.C0017c) this);
        cVar.a(com.salesforce.marketingcloud.http.a.EVENTS, (c.C0017c) this);
        bVar.a((b.C0003b) this, a.C0001a.DEVICE_STATS, a.C0001a.EVENTS);
    }

    public void a(long j2) {
        this.i.b().execute(new a("stats_app_close", new Object[0]));
    }

    public void b(InAppMessage inAppMessage) {
        if (!this.j) {
            com.salesforce.marketingcloud.g.a(k, "Track user is false.  Ignoring onInAppMessageDownloaded event.", new Object[0]);
            return;
        }
        try {
            com.salesforce.marketingcloud.g.c(k, "Creating download event stat for message id %s", inAppMessage.id());
            Date date = new Date();
            this.i.b().execute(new a(this.e.n(), this.e.b(), b.a(101, date, d.b(this.g.applicationId(), this.d, date, inAppMessage.id(), com.salesforce.marketingcloud.internal.a.a(inAppMessage)), true)));
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(k, e2, "Failed to record analytic event for In App Message Downloaded", new Object[0]);
        }
    }

    public void a(a.C0001a aVar) {
        if (aVar == a.C0001a.DEVICE_STATS) {
            com.salesforce.marketingcloud.g.c(k, "Handling alarm to send stats", new Object[0]);
        } else if (aVar == a.C0001a.EVENTS) {
            com.salesforce.marketingcloud.g.c(k, "Handling alarm to send stats", new Object[0]);
        } else {
            return;
        }
        a();
    }

    public void b(JSONObject jSONObject) {
        try {
            this.i.b().execute(new C0007c("onSyncGateTimedOutEvent", new Object[0], jSONObject));
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(k, e2, "Failed to track syncGateTimeOut Event stat.", new Object[0]);
        }
    }

    public void a(com.salesforce.marketingcloud.analytics.e eVar, Event... eventArr) {
        try {
            this.i.b().execute(new f("track_events", new Object[0], eventArr, new Date(), eVar));
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(k, e2, "Failed to record iam displayed event stat.", new Object[0]);
        }
    }

    public void a(InAppMessage inAppMessage) {
        try {
            com.salesforce.marketingcloud.g.c(k, "InAppMessage displayed event stat for message id %s", inAppMessage.id());
            Date date = new Date();
            this.i.b().execute(new a(this.e.n(), this.e.b(), b.a(104, date, d.a(this.g.applicationId(), this.d, date, inAppMessage.id(), com.salesforce.marketingcloud.internal.a.a(inAppMessage)), true)));
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(k, e2, "Failed to record iam displayed event stat.", new Object[0]);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0053 A[Catch:{ Exception -> 0x0042 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.salesforce.marketingcloud.messages.iam.InAppMessage r17, com.salesforce.marketingcloud.messages.iam.j r18) {
        /*
            r16 = this;
            r1 = r16
            boolean r0 = r1.j
            r2 = 0
            if (r0 != 0) goto L_0x0011
            java.lang.String r0 = k
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r3 = "Track user is false.  Ignoring onInAppMessageCompleted event."
            com.salesforce.marketingcloud.g.a((java.lang.String) r0, (java.lang.String) r3, (java.lang.Object[]) r2)
            return
        L_0x0011:
            java.lang.String r0 = k     // Catch:{ Exception -> 0x0042 }
            java.lang.String r3 = "Creating display event stat for message id %s"
            java.lang.String r4 = r17.id()     // Catch:{ Exception -> 0x0042 }
            java.lang.Object[] r4 = new java.lang.Object[]{r4}     // Catch:{ Exception -> 0x0042 }
            com.salesforce.marketingcloud.g.c((java.lang.String) r0, (java.lang.String) r3, (java.lang.Object[]) r4)     // Catch:{ Exception -> 0x0042 }
            com.salesforce.marketingcloud.messages.iam.InAppMessage$Button r0 = r18.a()     // Catch:{ Exception -> 0x0042 }
            java.lang.String r3 = r18.d()     // Catch:{ Exception -> 0x0042 }
            int r4 = r3.hashCode()     // Catch:{ Exception -> 0x0042 }
            r5 = -935167046(0xffffffffc8427bba, float:-199150.9)
            r6 = 1
            if (r4 == r5) goto L_0x0045
            r5 = 2117198997(0x7e31e495, float:5.9115055E37)
            if (r4 == r5) goto L_0x0038
            goto L_0x004f
        L_0x0038:
            java.lang.String r4 = "buttonClicked"
            boolean r3 = r3.equals(r4)     // Catch:{ Exception -> 0x0042 }
            if (r3 == 0) goto L_0x004f
            r3 = r6
            goto L_0x0050
        L_0x0042:
            r0 = move-exception
            goto L_0x00b9
        L_0x0045:
            java.lang.String r4 = "autoDismissed"
            boolean r3 = r3.equals(r4)     // Catch:{ Exception -> 0x0042 }
            if (r3 == 0) goto L_0x004f
            r3 = r2
            goto L_0x0050
        L_0x004f:
            r3 = -1
        L_0x0050:
            r4 = 0
            if (r3 == 0) goto L_0x0056
            if (r3 == r6) goto L_0x0059
            r6 = 3
        L_0x0056:
            r15 = r4
            r14 = r6
            goto L_0x0061
        L_0x0059:
            if (r0 == 0) goto L_0x005f
            java.lang.String r4 = r0.id()     // Catch:{ Exception -> 0x0042 }
        L_0x005f:
            r6 = 2
            goto L_0x0056
        L_0x0061:
            java.util.Date r0 = new java.util.Date     // Catch:{ Exception -> 0x0042 }
            java.util.Date r3 = r18.c()     // Catch:{ Exception -> 0x0042 }
            long r3 = r3.getTime()     // Catch:{ Exception -> 0x0042 }
            long r5 = r18.b()     // Catch:{ Exception -> 0x0042 }
            long r3 = r3 + r5
            r0.<init>(r3)     // Catch:{ Exception -> 0x0042 }
            com.salesforce.marketingcloud.MarketingCloudConfig r3 = r1.g     // Catch:{ Exception -> 0x0042 }
            java.lang.String r7 = r3.applicationId()     // Catch:{ Exception -> 0x0042 }
            java.lang.String r8 = r1.d     // Catch:{ Exception -> 0x0042 }
            java.lang.String r10 = r17.id()     // Catch:{ Exception -> 0x0042 }
            java.lang.String r11 = com.salesforce.marketingcloud.internal.a.a(r17)     // Catch:{ Exception -> 0x0042 }
            long r3 = r18.b()     // Catch:{ Exception -> 0x0042 }
            double r3 = (double) r3     // Catch:{ Exception -> 0x0042 }
            r5 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r3 = r3 / r5
            double r3 = java.lang.Math.ceil(r3)     // Catch:{ Exception -> 0x0042 }
            long r12 = (long) r3     // Catch:{ Exception -> 0x0042 }
            r9 = r0
            com.salesforce.marketingcloud.analytics.stats.d r3 = com.salesforce.marketingcloud.analytics.stats.d.a((java.lang.String) r7, (java.lang.String) r8, (java.util.Date) r9, (java.lang.String) r10, (java.lang.String) r11, (long) r12, (int) r14, (java.lang.String) r15)     // Catch:{ Exception -> 0x0042 }
            com.salesforce.marketingcloud.internal.l r4 = r1.i     // Catch:{ Exception -> 0x0042 }
            java.util.concurrent.ExecutorService r4 = r4.b()     // Catch:{ Exception -> 0x0042 }
            com.salesforce.marketingcloud.analytics.stats.a r5 = new com.salesforce.marketingcloud.analytics.stats.a     // Catch:{ Exception -> 0x0042 }
            com.salesforce.marketingcloud.storage.j r6 = r1.e     // Catch:{ Exception -> 0x0042 }
            com.salesforce.marketingcloud.storage.d r6 = r6.n()     // Catch:{ Exception -> 0x0042 }
            com.salesforce.marketingcloud.storage.j r7 = r1.e     // Catch:{ Exception -> 0x0042 }
            com.salesforce.marketingcloud.util.c r7 = r7.b()     // Catch:{ Exception -> 0x0042 }
            r8 = 100
            com.salesforce.marketingcloud.analytics.stats.b r0 = com.salesforce.marketingcloud.analytics.stats.b.a(r8, r0, r3, r2)     // Catch:{ Exception -> 0x0042 }
            r5.<init>(r6, r7, r0)     // Catch:{ Exception -> 0x0042 }
            r4.execute(r5)     // Catch:{ Exception -> 0x0042 }
            goto L_0x00c2
        L_0x00b9:
            java.lang.String r3 = k
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r4 = "Failed to record analytic event for In App Message Displayed"
            com.salesforce.marketingcloud.g.b(r3, r0, r4, r2)
        L_0x00c2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.analytics.stats.c.a(com.salesforce.marketingcloud.messages.iam.InAppMessage, com.salesforce.marketingcloud.messages.iam.j):void");
    }

    public void a(InAppMessage inAppMessage, JSONObject jSONObject) {
        try {
            this.i.b().execute(new b("onInAppMessageThrottled", new Object[0], inAppMessage, jSONObject));
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(k, e2, "Failed to track iam throttled event stat.", new Object[0]);
        }
    }

    public void a(String str, String str2, List<String> list) {
        com.salesforce.marketingcloud.g.c(k, "Creating message validation error event stat for message id %s", str);
        try {
            Date date = new Date();
            this.i.b().execute(new a(this.e.n(), this.e.b(), b.a(b.i, date, d.a(this.g.applicationId(), this.d, date, str, str2, list), true)));
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(k, e2, "Failed to record validation event stat.", new Object[0]);
        }
    }

    public void a(JSONObject jSONObject) {
        try {
            this.i.b().execute(new d("onInvalidConfigEvent", new Object[0], jSONObject));
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(k, e2, "Failed to track onInvalidConfig Event stat.", new Object[0]);
        }
    }

    public void a(com.salesforce.marketingcloud.http.b bVar, com.salesforce.marketingcloud.http.d dVar) {
        if (dVar.g()) {
            if (bVar.p() == com.salesforce.marketingcloud.http.a.DEVICE_STATS) {
                this.h.c(a.C0001a.DEVICE_STATS);
            } else if (bVar.p() == com.salesforce.marketingcloud.http.a.EVENTS) {
                this.h.c(a.C0001a.EVENTS);
            }
            if (bVar.q() != null) {
                String[] a2 = com.salesforce.marketingcloud.analytics.c.a(bVar.q());
                com.salesforce.marketingcloud.g.c(k, "Removing events %s from DB", Arrays.toString(a2));
                this.e.n().c(a2);
                return;
            }
            return;
        }
        com.salesforce.marketingcloud.g.c(k, "Request failed: %d - %s", Integer.valueOf(dVar.b()), dVar.e());
        if (bVar.p() == com.salesforce.marketingcloud.http.a.DEVICE_STATS) {
            this.h.b(a.C0001a.DEVICE_STATS);
        } else if (bVar.p() == com.salesforce.marketingcloud.http.a.EVENTS) {
            this.h.b(a.C0001a.EVENTS);
        }
        if (bVar.q() != null) {
            this.e.n().d(com.salesforce.marketingcloud.analytics.c.a(bVar.q()));
        }
    }

    public void a(l.a aVar, JSONObject jSONObject) {
        try {
            this.i.b().execute(new e("onTelemetryEvent", new Object[0], jSONObject, aVar));
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(k, e2, "Failed to track onTelemetryEvent stat. %s", aVar.name());
        }
    }

    public void a(String str, String str2, String str3, String str4) {
        if (!this.j) {
            com.salesforce.marketingcloud.g.a(k, "Track user is false.  Ignoring onTriggerSuccessEvent event.", new Object[0]);
            return;
        }
        com.salesforce.marketingcloud.g.c(k, "Creating trigger event stat for message id %s", str);
        try {
            Date date = new Date();
            this.i.b().execute(new a(this.e.n(), this.e.b(), b.a(102, date, d.a(this.g.applicationId(), this.d, date, str2, str4, str, str3), true)));
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(k, e2, "Failed to record device stat for successful trigger event", new Object[0]);
        }
    }

    /* access modifiers changed from: package-private */
    public Map<String, JSONArray> a(List<b> list, int i2) {
        boolean z;
        int i3 = i2;
        int size = list.size();
        int ceil = (int) Math.ceil(((double) size) / ((double) i3));
        ArrayMap arrayMap = new ArrayMap(ceil);
        for (int i4 = 0; i4 < ceil; i4++) {
            StringBuilder sb = new StringBuilder();
            JSONArray jSONArray = new JSONArray();
            int i5 = i4 * i3;
            boolean z2 = true;
            int i6 = i5;
            while (i6 < size && i6 < i5 + i3) {
                b bVar = list.get(i6);
                if (z2) {
                    z = false;
                } else {
                    sb.append(',');
                    z = z2;
                }
                sb.append(bVar.b());
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("eventType", bVar.d());
                    jSONObject.put("event", bVar.c().a);
                    jSONArray.put(jSONObject);
                } catch (JSONException e2) {
                    com.salesforce.marketingcloud.g.b(k, e2, "Unable to add device stats to payload.", new Object[0]);
                }
                i6++;
                z2 = z;
            }
            List<b> list2 = list;
            arrayMap.put(sb.toString(), jSONArray);
        }
        return arrayMap;
    }

    public void a() {
        a(com.salesforce.marketingcloud.http.a.DEVICE_STATS);
        a(com.salesforce.marketingcloud.http.a.EVENTS);
    }

    /* access modifiers changed from: package-private */
    public void a(com.salesforce.marketingcloud.http.a aVar) {
        this.i.b().execute(new g("send_stats", new Object[0], aVar));
    }

    public static void a(j jVar, boolean z) {
        if (z) {
            jVar.n().f();
        }
    }

    public void a(boolean z) {
        this.f.a(com.salesforce.marketingcloud.http.a.DEVICE_STATS);
        this.f.a(com.salesforce.marketingcloud.http.a.EVENTS);
        com.salesforce.marketingcloud.alarms.b bVar = this.h;
        a.C0001a aVar = a.C0001a.DEVICE_STATS;
        a.C0001a aVar2 = a.C0001a.EVENTS;
        bVar.e(aVar, aVar2);
        if (z) {
            this.h.d(aVar, aVar2);
        }
    }
}
