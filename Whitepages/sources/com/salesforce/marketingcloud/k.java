package com.salesforce.marketingcloud;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.collection.ArrayMap;
import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.alarms.a;
import com.salesforce.marketingcloud.alarms.b;
import com.salesforce.marketingcloud.analytics.l;
import com.salesforce.marketingcloud.b;
import com.salesforce.marketingcloud.http.c;
import com.salesforce.marketingcloud.internal.g;
import com.salesforce.marketingcloud.internal.l;
import com.salesforce.marketingcloud.storage.j;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"UnknownNullness"})
public class k implements e, com.salesforce.marketingcloud.behaviors.b, c.C0017c, b.C0003b {

    /* renamed from: n  reason: collision with root package name */
    private static final String f28n = g.a("SyncRouteComponent");
    private static final int o = 202;
    protected final MarketingCloudConfig d;
    protected final com.salesforce.marketingcloud.http.c e;
    protected final j f;
    protected final String g;
    private final l h;
    private final com.salesforce.marketingcloud.behaviors.c i;
    private final com.salesforce.marketingcloud.alarms.b j;
    private final com.salesforce.marketingcloud.analytics.l k;
    protected Map<d, e> l = new ArrayMap(d.values().length);
    private boolean m;

    class a implements MarketingCloudSdk.WhenReadyListener {
        a() {
        }

        public void ready(MarketingCloudSdk marketingCloudSdk) {
            k kVar = k.this;
            kVar.e.a(com.salesforce.marketingcloud.http.a.SYNC.a(kVar.d, kVar.f.c(), com.salesforce.marketingcloud.http.a.c(k.this.d.applicationId(), k.this.g), "{}"));
        }
    }

    class b extends g {
        final /* synthetic */ d b;
        final /* synthetic */ JSONObject c;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        b(String str, Object[] objArr, d dVar, JSONObject jSONObject) {
            super(str, objArr);
            this.b = dVar;
            this.c = jSONObject;
        }

        /* access modifiers changed from: protected */
        public void a() {
            e eVar = k.this.l.get(this.b);
            if (eVar != null) {
                eVar.onSyncReceived(this.b, this.c);
            }
        }
    }

    static /* synthetic */ class c {
        static final /* synthetic */ int[] a;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.salesforce.marketingcloud.behaviors.a[] r0 = com.salesforce.marketingcloud.behaviors.a.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                com.salesforce.marketingcloud.behaviors.a r1 = com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_FOREGROUNDED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.salesforce.marketingcloud.behaviors.a r1 = com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_SDK_PUSH_RECEIVED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.k.c.<clinit>():void");
        }
    }

    public enum d {
        blocked,
        inAppMessages,
        triggers,
        pushFeaturesInUse,
        appConfig
    }

    public interface e {
        void onSyncReceived(d dVar, JSONObject jSONObject);
    }

    k(String str, MarketingCloudConfig marketingCloudConfig, j jVar, com.salesforce.marketingcloud.http.c cVar, com.salesforce.marketingcloud.behaviors.c cVar2, com.salesforce.marketingcloud.alarms.b bVar, l lVar, com.salesforce.marketingcloud.analytics.l lVar2) {
        this.g = str;
        this.d = marketingCloudConfig;
        this.f = jVar;
        this.i = cVar2;
        this.e = cVar;
        this.j = bVar;
        this.h = lVar;
        this.k = lVar2;
    }

    private void a() {
        if (c()) {
            MarketingCloudSdk.requestSdk(b());
        }
    }

    private boolean c() {
        return !this.m;
    }

    /* access modifiers changed from: package-private */
    public MarketingCloudSdk.WhenReadyListener b() {
        return new a();
    }

    public String componentName() {
        return "SyncRoute";
    }

    public JSONObject componentState() {
        return null;
    }

    public void controlChannelInit(int i2) {
        if (b.a(i2, b.c.RTBF.a)) {
            this.i.a((com.salesforce.marketingcloud.behaviors.b) this);
            this.e.a(com.salesforce.marketingcloud.http.a.SYNC);
            com.salesforce.marketingcloud.alarms.b bVar = this.j;
            a.C0001a aVar = a.C0001a.SYNC;
            bVar.e(aVar);
            this.j.d(aVar);
            this.m = true;
        }
    }

    public void init(InitializationStatus.a aVar, int i2) {
        if (b.a(i2, b.c.RTBF.a)) {
            this.m = true;
            return;
        }
        this.e.a(com.salesforce.marketingcloud.http.a.SYNC, (c.C0017c) this);
        this.i.a(this, EnumSet.of(com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_FOREGROUNDED, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_SDK_PUSH_RECEIVED));
        this.j.a((b.C0003b) this, a.C0001a.SYNC);
    }

    public void onBehavior(com.salesforce.marketingcloud.behaviors.a aVar, Bundle bundle) {
        int i2 = c.a[aVar.ordinal()];
        if (i2 != 1) {
            if (i2 == 2) {
                if (!bundle.containsKey("_sync")) {
                    if (bundle.containsKey("_nodes")) {
                        a(bundle.getString("_nodes"));
                        return;
                    }
                    return;
                }
            } else {
                return;
            }
        }
        a();
    }

    public void tearDown(boolean z) {
        this.i.a((com.salesforce.marketingcloud.behaviors.b) this);
        this.e.a(com.salesforce.marketingcloud.http.a.SYNC);
        com.salesforce.marketingcloud.alarms.b bVar = this.j;
        a.C0001a aVar = a.C0001a.SYNC;
        bVar.e(aVar);
        if (z) {
            this.j.d(aVar);
        }
    }

    private void a(String str) {
        if (str != null) {
            try {
                a(new JSONArray(str));
            } catch (Exception e2) {
                g.b(f28n, e2, "Failed to parse sync push message", new Object[0]);
            }
        }
    }

    private void a(JSONArray jSONArray) throws JSONException {
        a(jSONArray, (int) o);
    }

    private void a(JSONArray jSONArray, int i2) throws JSONException {
        String str;
        int length = jSONArray.length();
        int i3 = 0;
        while (i3 < length) {
            JSONObject jSONObject = jSONArray.getJSONObject(i3);
            try {
                str = jSONObject.optString("name");
                try {
                    d valueOf = d.valueOf(str);
                    if (i2 != o || valueOf == d.appConfig || valueOf == d.blocked) {
                        this.h.a().execute(new b(str + "-sync_node_process", new Object[0], valueOf, jSONObject));
                        i3++;
                    } else {
                        i3++;
                    }
                } catch (Exception unused) {
                    g.a(f28n, "Failed to process node %s sync route", str);
                    i3++;
                }
            } catch (Exception unused2) {
                str = null;
                g.a(f28n, "Failed to process node %s sync route", str);
                i3++;
            }
        }
    }

    public static boolean a(Map<String, String> map) {
        return map.containsKey("_sync") || map.containsKey("_nodes");
    }

    public void a(a.C0001a aVar) {
        if (aVar == a.C0001a.SYNC) {
            a();
        }
    }

    public void a(com.salesforce.marketingcloud.http.b bVar, com.salesforce.marketingcloud.http.d dVar) {
        if (dVar.g()) {
            this.j.d(a.C0001a.SYNC);
            com.salesforce.marketingcloud.http.a.a(dVar.d(), this.f.c());
            a(dVar.h());
            try {
                JSONArray jSONArray = new JSONObject(dVar.a()).getJSONArray("nodes");
                if (jSONArray != null) {
                    a(jSONArray, dVar.b());
                }
            } catch (Exception e2) {
                g.b(f28n, e2, "Failed to parse /sync route response", new Object[0]);
            }
        } else {
            this.j.b(a.C0001a.SYNC);
            g.b(f28n, "Sync route request failed with message: %s", dVar.e());
        }
    }

    public void a(d dVar, e eVar) {
        e eVar2 = this.l.get(dVar);
        if (eVar2 == null || eVar == null || eVar2 == eVar) {
            this.l.put(dVar, eVar);
        } else {
            g.e(f28n, "Node %s already assigned to listener %s.  %s was not added for the Node.", dVar, eVar2, eVar);
        }
    }

    public void a(EnumSet<d> enumSet, e eVar) {
        Iterator<d> it = enumSet.iterator();
        while (it.hasNext()) {
            a(it.next(), eVar);
        }
    }

    private void a(long j2) {
        JSONObject jSONObject = new JSONObject();
        try {
            l.a aVar = l.a.SYNC_API;
            jSONObject.put(aVar.b(), j2);
            if (com.salesforce.marketingcloud.config.a.e() != null && com.salesforce.marketingcloud.config.a.e().l()) {
                this.k.a(aVar, jSONObject);
            }
        } catch (JSONException e2) {
            g.b(f28n, e2, "Failed to log TelemetryEvent for Sync Route", new Object[0]);
        }
    }
}
