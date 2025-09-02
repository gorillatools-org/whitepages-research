package com.salesforce.marketingcloud.events;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.analytics.h;
import com.salesforce.marketingcloud.analytics.l;
import com.salesforce.marketingcloud.analytics.m;
import com.salesforce.marketingcloud.analytics.n;
import com.salesforce.marketingcloud.k;
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdkComponents;
import com.salesforce.marketingcloud.sfmcsdk.components.events.Event;
import com.salesforce.marketingcloud.sfmcsdk.components.events.EventSubscriber;
import com.salesforce.marketingcloud.storage.j;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"UnknownNullness"})
public class c extends EventManager implements com.salesforce.marketingcloud.e, k.e, com.salesforce.marketingcloud.behaviors.b, EventSubscriber {
    public static final String r = "event_gate_time_mills";
    public static final String s = "event_max_display_in_session";
    public static final String t = "event_min_time_sec_in_session";
    static final String u = com.salesforce.marketingcloud.g.a("EventManager");
    private static final String v = "$opencount";
    private static final int w = 1;
    private static final EnumSet<k.d> x = EnumSet.of(k.d.triggers);
    protected final m d;
    final SFMCSdkComponents e;
    final j f;
    private final k g;
    private final com.salesforce.marketingcloud.behaviors.c h;
    private final f i;
    private final l j;
    private final n k;
    private final com.salesforce.marketingcloud.internal.l l;
    private final AtomicBoolean m = new AtomicBoolean(false);

    /* renamed from: n  reason: collision with root package name */
    private final Context f24n;
    protected CountDownLatch o = new CountDownLatch(1);
    protected com.salesforce.marketingcloud.config.a p;
    private com.salesforce.marketingcloud.registration.f q;

    class a extends com.salesforce.marketingcloud.internal.g {
        final /* synthetic */ Event[] b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        a(String str, Object[] objArr, Event... eventArr) {
            super(str, objArr);
            this.b = eventArr;
        }

        /* access modifiers changed from: protected */
        public void a() {
            int i = c.this.f.f().getInt(c.r, 0);
            if (i > 0) {
                try {
                    if (!c.this.o.await((long) i, TimeUnit.MILLISECONDS)) {
                        com.salesforce.marketingcloud.g.e(c.u, "Track await time of %s milliseconds exceeded!", Integer.valueOf(i));
                        c.this.o.countDown();
                        if (c.this.p.h()) {
                            c.this.d.b(new JSONObject().put(com.salesforce.marketingcloud.config.a.f, i));
                        }
                    }
                } catch (InterruptedException e) {
                    com.salesforce.marketingcloud.g.b(c.u, e, "Encountered exception while awaiting at track.", new Object[0]);
                } catch (JSONException e2) {
                    com.salesforce.marketingcloud.g.b(c.u, e2, "Failed to log analytics for onSyncGateTimedOut", new Object[0]);
                }
            }
            Event[] eventArr = this.b;
            ArrayList arrayList = null;
            if (eventArr != null && eventArr.length > 0) {
                for (Event event : eventArr) {
                    if (event != null) {
                        com.salesforce.marketingcloud.g.a(c.u, "(%s) event logged with attributes %s", event.name(), event.attributes());
                        c cVar = c.this;
                        List<e> a = cVar.a(event, cVar.a(event));
                        if (a != null) {
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                            }
                            arrayList.addAll(a);
                        }
                    }
                }
            }
            if (arrayList != null) {
                c.this.a((List<e>) arrayList);
            }
        }
    }

    class b extends com.salesforce.marketingcloud.internal.g {
        final /* synthetic */ Event[] b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        b(String str, Object[] objArr, Event... eventArr) {
            super(str, objArr);
            this.b = eventArr;
        }

        /* access modifiers changed from: protected */
        public void a() {
            Event[] eventArr = this.b;
            if (eventArr != null && eventArr.length > 0) {
                c cVar = c.this;
                SFMCSdkComponents sFMCSdkComponents = cVar.e;
                if (sFMCSdkComponents != null) {
                    sFMCSdkComponents.getEventManager().track(d.b(this.b, EnumSet.of(Event.Producer.PUSH)));
                } else {
                    cVar.a(eventArr);
                }
            }
        }
    }

    /* renamed from: com.salesforce.marketingcloud.events.c$c  reason: collision with other inner class name */
    class C0012c extends ArrayList<Object> {
        final /* synthetic */ h a;

        C0012c(h hVar) {
            this.a = hVar;
            add(Integer.valueOf(c.this.f.v().b(hVar)));
        }
    }

    class d extends com.salesforce.marketingcloud.internal.g {
        d(String str, Object... objArr) {
            super(str, objArr);
        }

        /* access modifiers changed from: protected */
        public void a() {
            c.this.f.v().k();
            c.this.a(new a());
        }
    }

    class e extends com.salesforce.marketingcloud.internal.g {
        e(String str, Object... objArr) {
            super(str, objArr);
        }

        /* access modifiers changed from: protected */
        public void a() {
            com.salesforce.marketingcloud.g.c(c.u, "Purged %d outdated debug/telemetry events.", Integer.valueOf(c.this.f.n().a()));
        }
    }

    class f extends com.salesforce.marketingcloud.internal.g {
        f(String str, Object... objArr) {
            super(str, objArr);
        }

        /* access modifiers changed from: protected */
        public void a() {
            com.salesforce.marketingcloud.g.c(c.u, "Purged %d outdated analytic events.", Integer.valueOf(c.this.f.m().a()));
        }
    }

    static /* synthetic */ class g {
        static final /* synthetic */ int[] a;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.salesforce.marketingcloud.events.g$b[] r0 = com.salesforce.marketingcloud.events.g.b.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                com.salesforce.marketingcloud.events.g$b r1 = com.salesforce.marketingcloud.events.g.b.INT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.salesforce.marketingcloud.events.g$b r1 = com.salesforce.marketingcloud.events.g.b.DOUBLE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.salesforce.marketingcloud.events.g$b r1 = com.salesforce.marketingcloud.events.g.b.BOOL     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.salesforce.marketingcloud.events.g$b r1 = com.salesforce.marketingcloud.events.g.b.STRING     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.events.c.g.<clinit>():void");
        }
    }

    public c(Context context, com.salesforce.marketingcloud.registration.f fVar, j jVar, k kVar, com.salesforce.marketingcloud.behaviors.c cVar, h hVar, com.salesforce.marketingcloud.internal.l lVar, SFMCSdkComponents sFMCSdkComponents, com.salesforce.marketingcloud.config.a aVar, f fVar2) {
        this.f24n = context;
        this.q = fVar;
        this.f = jVar;
        this.g = kVar;
        this.h = cVar;
        this.d = hVar;
        this.k = hVar;
        this.i = fVar2;
        this.l = lVar;
        this.e = sFMCSdkComponents;
        this.j = hVar;
        this.p = aVar;
    }

    private com.salesforce.marketingcloud.events.predicates.f a(h hVar, Event event, List<g> list) {
        ArrayList arrayList;
        if (list == null || list.size() == 0) {
            return com.salesforce.marketingcloud.events.predicates.f.b;
        }
        Map<String, List<Object>> a2 = d.a(event);
        Map<String, List<Object>> a3 = a(hVar);
        a3.putAll(a2);
        String g2 = hVar.g();
        if (g2 != null) {
            HashMap hashMap = new HashMap(list.size());
            for (g next : list) {
                hashMap.put(Integer.valueOf(next.f()), next);
            }
            arrayList = new ArrayList(hashMap.size());
            for (String parseInt : g2.split(g2.contains("||") ? "\\|\\|" : "&&")) {
                arrayList.add(a(a3, (g) hashMap.get(Integer.valueOf(Integer.parseInt(parseInt)))));
            }
        } else {
            ArrayList arrayList2 = new ArrayList(list.size());
            for (g a4 : list) {
                arrayList2.add(a(a3, a4));
            }
            arrayList = arrayList2;
        }
        return (g2 == null || !g2.contains("||")) ? new com.salesforce.marketingcloud.events.predicates.a((com.salesforce.marketingcloud.events.predicates.f[]) arrayList.toArray(new com.salesforce.marketingcloud.events.predicates.f[0])) : new com.salesforce.marketingcloud.events.predicates.e((com.salesforce.marketingcloud.events.predicates.f[]) arrayList.toArray(new com.salesforce.marketingcloud.events.predicates.f[0]));
    }

    private void b() {
        SFMCSdkComponents sFMCSdkComponents = this.e;
        if (sFMCSdkComponents != null) {
            sFMCSdkComponents.getEventManager().unsubscribe(this);
        }
    }

    public String componentName() {
        return "Event";
    }

    public JSONObject componentState() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(com.salesforce.marketingcloud.storage.db.m.g, this.f.v().m());
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(u, e2, "Unable to compile componentState for EventComponent", new Object[0]);
        }
        return jSONObject;
    }

    public void controlChannelInit(int i2) {
        if (com.salesforce.marketingcloud.b.a(i2, com.salesforce.marketingcloud.b.v)) {
            this.m.set(true);
            this.g.a(x, (k.e) null);
            this.h.a((com.salesforce.marketingcloud.behaviors.b) this);
            if (com.salesforce.marketingcloud.b.c(i2, com.salesforce.marketingcloud.b.v)) {
                this.f.v().b((Collection<String>) Collections.emptyList());
            }
            b();
            return;
        }
        this.g.a(x, (k.e) this);
        this.h.a(this, EnumSet.of(com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_FOREGROUNDED));
        a();
        this.m.set(false);
    }

    public void init(InitializationStatus.a aVar, int i2) {
        if (com.salesforce.marketingcloud.b.b(i2, com.salesforce.marketingcloud.b.v)) {
            this.g.a(x, (k.e) this);
            this.h.a(this, EnumSet.of(com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_FOREGROUNDED));
            a();
            return;
        }
        b();
        this.m.set(true);
    }

    public void onBehavior(com.salesforce.marketingcloud.behaviors.a aVar, Bundle bundle) {
        if (!this.m.get() && aVar == com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_FOREGROUNDED) {
            if (this.o.getCount() <= 0) {
                this.o = new CountDownLatch(1);
            }
            try {
                this.l.b().execute(new d("app_foreground_trigger", new Object[0]));
                this.l.b().execute(new e("dev_stats_db_cleanup", new Object[0]));
                this.l.b().execute(new f("analytic_item_db_cleanup", new Object[0]));
            } catch (Exception e2) {
                com.salesforce.marketingcloud.g.e(u, e2, "An error occurred while triggering app foreground", new Object[0]);
            }
        }
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0017 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onEventPublished(com.salesforce.marketingcloud.sfmcsdk.components.events.Event... r6) {
        /*
            r5 = this;
            com.salesforce.marketingcloud.sfmcsdk.SFMCSdkComponents r0 = r5.e     // Catch:{ Exception -> 0x000a }
            r1 = 0
            if (r0 == 0) goto L_0x000c
            com.salesforce.marketingcloud.sfmcsdk.components.identity.Identity r0 = r0.getIdentity()     // Catch:{ Exception -> 0x000a }
            goto L_0x000d
        L_0x000a:
            r6 = move-exception
            goto L_0x0031
        L_0x000c:
            r0 = r1
        L_0x000d:
            com.salesforce.marketingcloud.MarketingCloudSdk r2 = com.salesforce.marketingcloud.MarketingCloudSdk.getInstance()     // Catch:{ Exception -> 0x0017 }
            if (r2 == 0) goto L_0x0017
            com.salesforce.marketingcloud.messages.push.PushMessageManager r1 = r2.getPushMessageManager()     // Catch:{ Exception -> 0x0017 }
        L_0x0017:
            com.salesforce.marketingcloud.events.Event[] r2 = com.salesforce.marketingcloud.events.d.a((java.lang.Object[]) r6)     // Catch:{ Exception -> 0x000a }
            r5.a((com.salesforce.marketingcloud.events.Event[]) r2)     // Catch:{ Exception -> 0x000a }
            com.salesforce.marketingcloud.analytics.e r2 = new com.salesforce.marketingcloud.analytics.e     // Catch:{ Exception -> 0x000a }
            com.salesforce.marketingcloud.registration.f r3 = r5.q     // Catch:{ Exception -> 0x000a }
            android.content.Context r4 = r5.f24n     // Catch:{ Exception -> 0x000a }
            boolean r4 = com.salesforce.marketingcloud.util.h.b(r4)     // Catch:{ Exception -> 0x000a }
            r2.<init>(r3, r1, r4, r0)     // Catch:{ Exception -> 0x000a }
            com.salesforce.marketingcloud.analytics.n r0 = r5.k     // Catch:{ Exception -> 0x000a }
            r0.a(r2, r6)     // Catch:{ Exception -> 0x000a }
            goto L_0x003b
        L_0x0031:
            java.lang.String r0 = u
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r2 = "Could not process events from onEventPublished()"
            com.salesforce.marketingcloud.g.b(r0, r6, r2, r1)
        L_0x003b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.events.c.onEventPublished(com.salesforce.marketingcloud.sfmcsdk.components.events.Event[]):void");
    }

    public void onSyncReceived(k.d dVar, JSONObject jSONObject) {
        if (!this.m.get() && x.contains(dVar)) {
            if (jSONObject.optInt("version") != 1) {
                com.salesforce.marketingcloud.g.b(u, "Unable to handle sync payload due to version mismatch", new Object[0]);
            } else if (dVar == k.d.triggers) {
                a(jSONObject);
            }
        }
    }

    public void tearDown(boolean z) {
        this.g.a(x, (k.e) null);
        this.h.a((com.salesforce.marketingcloud.behaviors.b) this);
        b();
    }

    public void track(Event... eventArr) {
        if (!this.m.get()) {
            try {
                this.l.b().execute(new b("trigger_event", new Object[0], eventArr));
            } catch (Exception e2) {
                com.salesforce.marketingcloud.g.e(u, e2, "An error occurred while triggering track event", new Object[0]);
            }
        }
    }

    protected c(Context context, com.salesforce.marketingcloud.registration.f fVar, j jVar, k kVar, com.salesforce.marketingcloud.behaviors.c cVar, m mVar, n nVar, com.salesforce.marketingcloud.internal.l lVar, SFMCSdkComponents sFMCSdkComponents, f fVar2, com.salesforce.marketingcloud.config.a aVar, l lVar2) {
        this.f24n = context;
        this.q = fVar;
        this.f = jVar;
        this.g = kVar;
        this.h = cVar;
        this.d = mVar;
        this.k = nVar;
        this.i = fVar2;
        this.l = lVar;
        this.e = sFMCSdkComponents;
        this.j = lVar2;
        this.p = aVar;
    }

    /* access modifiers changed from: package-private */
    public List<e> a(Event event, List<h> list) {
        ArrayList arrayList = null;
        if (!(list == null || list.size() == 0)) {
            try {
                for (h next : list) {
                    if (a(next, event, next.k()).b()) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        for (e next2 : next.j()) {
                            arrayList.add(next2);
                            try {
                                this.d.a(next.h(), next2.e(), next2.f(), next2.d());
                            } catch (Exception e2) {
                                com.salesforce.marketingcloud.g.b(u, e2, "Failed to log analytics for trigger [%s]", next.h());
                            }
                        }
                    }
                }
            } catch (IllegalArgumentException e3) {
                com.salesforce.marketingcloud.g.b(u, e3, "An outcome could not be reached with the given trigger(s) for the event.", new Object[0]);
            }
        }
        return arrayList;
    }

    private com.salesforce.marketingcloud.events.predicates.f a(Map<String, List<Object>> map, g gVar) {
        int i2;
        if (gVar == null) {
            return com.salesforce.marketingcloud.events.predicates.f.c;
        }
        ArrayList arrayList = new ArrayList();
        List list = map.get(gVar.g().toLowerCase(Locale.getDefault()));
        if (list != null) {
            i2 = 0;
            for (Object next : list) {
                if (next instanceof List) {
                    try {
                        for (Object a2 : (List) next) {
                            arrayList.add(a(a2, gVar));
                            i2++;
                        }
                    } catch (Exception unused) {
                    }
                } else {
                    arrayList.add(a(next, gVar));
                    i2++;
                }
            }
        } else {
            i2 = 0;
        }
        if (i2 > 1) {
            return new com.salesforce.marketingcloud.events.predicates.e((com.salesforce.marketingcloud.events.predicates.f[]) arrayList.toArray(new com.salesforce.marketingcloud.events.predicates.f[0]));
        }
        return i2 == 1 ? new com.salesforce.marketingcloud.events.predicates.a((com.salesforce.marketingcloud.events.predicates.f[]) arrayList.toArray(new com.salesforce.marketingcloud.events.predicates.f[0])) : com.salesforce.marketingcloud.events.predicates.f.c;
    }

    private com.salesforce.marketingcloud.events.predicates.f a(Object obj, g gVar) {
        int i2 = g.a[gVar.j().ordinal()];
        if (i2 == 1) {
            return new com.salesforce.marketingcloud.events.predicates.d(obj, gVar.h(), gVar.i());
        }
        if (i2 == 2) {
            return new com.salesforce.marketingcloud.events.predicates.c(obj, gVar.h(), gVar.i());
        }
        if (i2 != 3) {
            return i2 != 4 ? com.salesforce.marketingcloud.events.predicates.f.c : new com.salesforce.marketingcloud.events.predicates.g(obj, gVar.h(), gVar.i());
        }
        return new com.salesforce.marketingcloud.events.predicates.b(obj, gVar.h(), gVar.i());
    }

    private Map<String, List<Object>> a(h hVar) {
        HashMap hashMap = new HashMap();
        hashMap.put(v, new C0012c(hVar));
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    public void a(List<e> list) {
        f fVar;
        TreeSet treeSet = null;
        for (e next : list) {
            if ("iam".equals(next.f())) {
                if (treeSet == null) {
                    treeSet = new TreeSet();
                }
                treeSet.add(next.e());
            }
        }
        if (treeSet != null && (fVar = this.i) != null) {
            fVar.handleOutcomes(treeSet);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Event... eventArr) {
        if (!this.m.get()) {
            try {
                this.l.b().execute(new a("trigger_event", new Object[0], eventArr));
            } catch (Exception e2) {
                com.salesforce.marketingcloud.g.e(u, e2, "An error occurred while processing the event", new Object[0]);
            }
        }
    }

    private void a(JSONObject jSONObject) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(FirebaseAnalytics.Param.ITEMS);
            int length = jSONArray.length();
            com.salesforce.marketingcloud.g.a(u, "%d triggers received from sync.", Integer.valueOf(length));
            TreeSet treeSet = new TreeSet();
            com.salesforce.marketingcloud.storage.n v2 = this.f.v();
            for (int i2 = 0; i2 < length; i2++) {
                try {
                    h hVar = new h(jSONArray.getJSONObject(i2));
                    v2.a(hVar);
                    treeSet.add(hVar.h());
                } catch (Exception e2) {
                    com.salesforce.marketingcloud.g.b(u, e2, "Unable to parse trigger from payload", new Object[0]);
                }
            }
            v2.b((Collection<String>) treeSet);
            JSONObject jSONObject2 = new JSONObject();
            l.a aVar = l.a.TRIGGER_PROCESS;
            jSONObject2.put(aVar.b(), System.currentTimeMillis() - currentTimeMillis);
            if (this.p.l()) {
                this.j.a(aVar, jSONObject2);
            }
        } catch (JSONException e3) {
            com.salesforce.marketingcloud.g.b(u, e3, "Unable to parse trigger sync payload", new Object[0]);
        }
        this.o.countDown();
    }

    private void a() {
        SFMCSdkComponents sFMCSdkComponents = this.e;
        if (sFMCSdkComponents != null) {
            sFMCSdkComponents.getEventManager().subscribe(this);
        }
    }

    /* access modifiers changed from: package-private */
    public List<h> a(Event event) {
        return this.f.v().g(event.name());
    }
}
