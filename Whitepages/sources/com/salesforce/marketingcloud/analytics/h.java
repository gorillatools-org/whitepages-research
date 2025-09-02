package com.salesforce.marketingcloud.analytics;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.MarketingCloudConfig;
import com.salesforce.marketingcloud.alarms.a;
import com.salesforce.marketingcloud.analytics.l;
import com.salesforce.marketingcloud.analytics.piwama.i;
import com.salesforce.marketingcloud.behaviors.c;
import com.salesforce.marketingcloud.e;
import com.salesforce.marketingcloud.internal.g;
import com.salesforce.marketingcloud.internal.l;
import com.salesforce.marketingcloud.messages.Region;
import com.salesforce.marketingcloud.messages.RegionMessageManager;
import com.salesforce.marketingcloud.messages.iam.InAppMessage;
import com.salesforce.marketingcloud.messages.inbox.InboxMessage;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import com.salesforce.marketingcloud.sfmcsdk.components.events.Event;
import com.salesforce.marketingcloud.storage.j;
import java.util.EnumSet;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"UnknownNullness"})
public final class h implements e, com.salesforce.marketingcloud.behaviors.b, j, RegionMessageManager.RegionTransitionEventListener, AnalyticsManager, g, f, m, n, l {
    private static final String s = "ETAnalyticsEnabled";
    private static final String t = "PIAnalyticsEnabled";
    private static final Object u = new Object();
    private final c d;
    private final EnumSet<com.salesforce.marketingcloud.behaviors.a> e;
    final j f;
    private final com.salesforce.marketingcloud.http.c g;
    private final String h;
    private final MarketingCloudConfig i;
    private final com.salesforce.marketingcloud.alarms.b j;
    private com.salesforce.marketingcloud.analytics.etanalytics.c k;
    com.salesforce.marketingcloud.analytics.etanalytics.b l;
    com.salesforce.marketingcloud.analytics.etanalytics.a m;

    /* renamed from: n  reason: collision with root package name */
    i f18n;
    com.salesforce.marketingcloud.analytics.stats.c o;
    private l p;
    private com.salesforce.marketingcloud.toggles.a q;
    private com.salesforce.marketingcloud.toggles.a r;

    class a extends g {
        final /* synthetic */ InboxMessage b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        a(String str, Object[] objArr, InboxMessage inboxMessage) {
            super(str, objArr);
            this.b = inboxMessage;
        }

        /* access modifiers changed from: protected */
        public void a() {
            if (this.b == null || !h.this.f.q().e(this.b.id())) {
                com.salesforce.marketingcloud.g.e(AnalyticsManager.TAG, "InboxMessage is a Legacy message, null or unknown.  Call to trackInboxOpenEvent() ignored.", new Object[0]);
                return;
            }
            com.salesforce.marketingcloud.analytics.etanalytics.a aVar = h.this.m;
            if (aVar != null) {
                aVar.trackInboxOpenEvent(this.b);
            }
            com.salesforce.marketingcloud.analytics.etanalytics.b bVar = h.this.l;
            if (bVar != null) {
                bVar.trackInboxOpenEvent(this.b);
            }
            i iVar = h.this.f18n;
            if (iVar != null) {
                iVar.trackInboxOpenEvent(this.b);
            }
            com.salesforce.marketingcloud.analytics.stats.c cVar = h.this.o;
            if (cVar != null) {
                cVar.trackInboxOpenEvent(this.b);
            }
        }
    }

    static /* synthetic */ class b {
        static final /* synthetic */ int[] a;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.salesforce.marketingcloud.behaviors.a[] r0 = com.salesforce.marketingcloud.behaviors.a.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                com.salesforce.marketingcloud.behaviors.a r1 = com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_BACKGROUNDED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.salesforce.marketingcloud.behaviors.a r1 = com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_FOREGROUNDED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.salesforce.marketingcloud.behaviors.a r1 = com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_DEVICE_SHUTDOWN     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.salesforce.marketingcloud.behaviors.a r1 = com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_DEVICE_BOOT_COMPLETE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.analytics.h.b.<clinit>():void");
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    h(MarketingCloudConfig marketingCloudConfig, j jVar, String str, com.salesforce.marketingcloud.alarms.b bVar, c cVar, com.salesforce.marketingcloud.http.c cVar2, com.salesforce.marketingcloud.analytics.etanalytics.a aVar, com.salesforce.marketingcloud.analytics.etanalytics.b bVar2, i iVar, l lVar, com.salesforce.marketingcloud.analytics.stats.c cVar3, com.salesforce.marketingcloud.analytics.etanalytics.c cVar4) {
        this(marketingCloudConfig, jVar, str, bVar, cVar, cVar2, lVar);
        this.m = aVar;
        this.l = bVar2;
        this.f18n = iVar;
        this.o = cVar3;
        this.k = cVar4;
    }

    private void a(Bundle bundle) {
        long j2 = bundle.getLong("timestamp", 0);
        com.salesforce.marketingcloud.analytics.etanalytics.a aVar = this.m;
        if (aVar != null) {
            aVar.a(j2);
        }
        com.salesforce.marketingcloud.analytics.etanalytics.b bVar = this.l;
        if (bVar != null) {
            bVar.a(j2);
        }
        i iVar = this.f18n;
        if (iVar != null) {
            iVar.a(j2);
            this.f18n.c();
        }
        com.salesforce.marketingcloud.analytics.stats.c cVar = this.o;
        if (cVar != null) {
            cVar.a(j2);
            this.o.a();
        }
        com.salesforce.marketingcloud.analytics.etanalytics.c cVar2 = this.k;
        if (cVar2 != null) {
            cVar2.a();
        }
    }

    private void b(Bundle bundle) {
        long j2 = bundle.getLong("timestamp", System.currentTimeMillis());
        com.salesforce.marketingcloud.analytics.etanalytics.c cVar = this.k;
        if (cVar != null) {
            cVar.a();
        }
        com.salesforce.marketingcloud.analytics.etanalytics.a aVar = this.m;
        if (aVar != null) {
            aVar.b(j2);
        }
        com.salesforce.marketingcloud.analytics.etanalytics.b bVar = this.l;
        if (bVar != null) {
            bVar.b(j2);
        }
        i iVar = this.f18n;
        if (iVar != null) {
            iVar.c();
            this.f18n.b(j2);
        }
        com.salesforce.marketingcloud.analytics.stats.c cVar2 = this.o;
        if (cVar2 != null) {
            cVar2.a();
            this.o.b(j2);
        }
    }

    private void c(Bundle bundle) {
        long j2 = bundle.getLong("timestamp", 0);
        com.salesforce.marketingcloud.analytics.etanalytics.a aVar = this.m;
        if (aVar != null) {
            aVar.c(j2);
        }
        com.salesforce.marketingcloud.analytics.etanalytics.b bVar = this.l;
        if (bVar != null) {
            bVar.c(j2);
        }
        i iVar = this.f18n;
        if (iVar != null) {
            iVar.c(j2);
        }
        com.salesforce.marketingcloud.analytics.stats.c cVar = this.o;
        if (cVar != null) {
            cVar.c(j2);
        }
    }

    public boolean areAnalyticsEnabled() {
        if (com.salesforce.marketingcloud.b.a(com.salesforce.marketingcloud.b.a(this.f.o()), 256)) {
            return false;
        }
        if (this.q == null) {
            this.q = a(s, (com.salesforce.marketingcloud.toggles.a) null);
        }
        com.salesforce.marketingcloud.toggles.a aVar = this.q;
        return aVar == com.salesforce.marketingcloud.toggles.a.ENABLED || (aVar == com.salesforce.marketingcloud.toggles.a.UNKNOWN && this.i.analyticsEnabled());
    }

    public boolean arePiAnalyticsEnabled() {
        if (com.salesforce.marketingcloud.b.a(com.salesforce.marketingcloud.b.a(this.f.o()), 512)) {
            return false;
        }
        if (this.r == null) {
            this.r = a(t, (com.salesforce.marketingcloud.toggles.a) null);
        }
        com.salesforce.marketingcloud.toggles.a aVar = this.r;
        return aVar == com.salesforce.marketingcloud.toggles.a.ENABLED || (aVar == com.salesforce.marketingcloud.toggles.a.UNKNOWN && this.i.piAnalyticsEnabled());
    }

    public String componentName() {
        return "AnalyticsManager";
    }

    public JSONObject componentState() {
        JSONObject jSONObject = new JSONObject();
        try {
            boolean z = false;
            jSONObject.put("bet_analytics", this.m != null);
            jSONObject.put("et_analytics", this.l != null);
            jSONObject.put("pi_analytics", this.f18n != null);
            if (this.o != null) {
                z = true;
            }
            jSONObject.put("device_stats", z);
            i iVar = this.f18n;
            if (iVar != null) {
                jSONObject.put("predictive_intelligence_identifier", iVar.getPiIdentifier());
            }
            jSONObject.put("analyticsEnabled", areAnalyticsEnabled());
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public void controlChannelInit(int i2) {
        int i3 = i2;
        boolean a2 = com.salesforce.marketingcloud.b.a(i3, 256);
        if (com.salesforce.marketingcloud.b.a(i3, com.salesforce.marketingcloud.b.u)) {
            com.salesforce.marketingcloud.analytics.etanalytics.a aVar = this.m;
            if (aVar != null) {
                aVar.a(false);
                this.m = null;
            }
            com.salesforce.marketingcloud.analytics.etanalytics.a.a(this.f, this.p, com.salesforce.marketingcloud.b.c(i3, com.salesforce.marketingcloud.b.u));
            com.salesforce.marketingcloud.analytics.stats.c cVar = this.o;
            if (cVar != null) {
                cVar.a(false);
                this.o = null;
            }
            com.salesforce.marketingcloud.analytics.stats.c.a(this.f, com.salesforce.marketingcloud.b.c(i3, com.salesforce.marketingcloud.b.u));
        } else {
            this.m = new com.salesforce.marketingcloud.analytics.etanalytics.a(this.f, this.p);
            MarketingCloudConfig marketingCloudConfig = this.i;
            this.o = new com.salesforce.marketingcloud.analytics.stats.c(marketingCloudConfig, this.h, marketingCloudConfig.analyticsEnabled() && !a2, this.f, this.g, this.j, this.p);
        }
        if (a2) {
            com.salesforce.marketingcloud.analytics.etanalytics.b bVar = this.l;
            if (bVar != null) {
                bVar.a(false);
                this.l = null;
            }
            com.salesforce.marketingcloud.analytics.etanalytics.b.a(this.f, this.p, com.salesforce.marketingcloud.b.c(i3, 256));
        } else if (this.l == null && this.i.analyticsEnabled()) {
            this.l = new com.salesforce.marketingcloud.analytics.etanalytics.b(this.f, this.p);
        }
        if (com.salesforce.marketingcloud.b.a(i3, 512)) {
            i iVar = this.f18n;
            if (iVar != null) {
                iVar.a(false);
                this.f18n = null;
            }
            i.a(this.f, this.g, this.p, com.salesforce.marketingcloud.b.c(i3, 512));
        } else if (this.f18n == null && this.i.piAnalyticsEnabled()) {
            this.f18n = new i(this.i, this.f, this.g, this.p);
        }
        if (this.m == null && this.l == null) {
            this.j.d(a.C0001a.ET_ANALYTICS);
            com.salesforce.marketingcloud.analytics.etanalytics.c cVar2 = this.k;
            if (cVar2 != null) {
                cVar2.b();
                this.k = null;
            }
        } else if (this.k == null) {
            this.k = new com.salesforce.marketingcloud.analytics.etanalytics.c(this.i, this.h, this.f, this.g, this.j, this.p);
        }
    }

    public void disableAnalytics() {
        synchronized (u) {
            try {
                this.q = com.salesforce.marketingcloud.toggles.a.DISABLED;
                this.f.f().edit().putString(s, this.q.name()).apply();
                com.salesforce.marketingcloud.analytics.etanalytics.b bVar = this.l;
                if (bVar != null) {
                    bVar.a(true);
                    this.l = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void disablePiAnalytics() {
        synchronized (u) {
            try {
                this.r = com.salesforce.marketingcloud.toggles.a.DISABLED;
                this.f.f().edit().putString(t, this.r.name()).apply();
                String str = AnalyticsManager.TAG;
                com.salesforce.marketingcloud.g.a(str, "Pi Analytics runtime toggle set to " + this.r.name(), new Object[0]);
                i iVar = this.f18n;
                if (iVar != null) {
                    iVar.a(true);
                    this.f18n = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void enableAnalytics() {
        synchronized (u) {
            try {
                if (!com.salesforce.marketingcloud.b.a(com.salesforce.marketingcloud.b.a(this.f.o()), 256)) {
                    this.q = com.salesforce.marketingcloud.toggles.a.ENABLED;
                    this.f.f().edit().putString(s, this.q.name()).apply();
                    this.l = new com.salesforce.marketingcloud.analytics.etanalytics.b(this.f, this.p);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void enablePiAnalytics() {
        synchronized (u) {
            try {
                if (!com.salesforce.marketingcloud.b.a(com.salesforce.marketingcloud.b.a(this.f.o()), 512)) {
                    this.r = com.salesforce.marketingcloud.toggles.a.ENABLED;
                    this.f.f().edit().putString(t, this.r.name()).apply();
                    String str = AnalyticsManager.TAG;
                    com.salesforce.marketingcloud.g.a(str, "Pi Analytics runtime toggle set to " + this.r.name(), new Object[0]);
                    this.f18n = new i(this.i, this.f, this.g, this.p);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public String getPiIdentifier() {
        i iVar = this.f18n;
        if (iVar != null) {
            return iVar.getPiIdentifier();
        }
        return null;
    }

    public void init(InitializationStatus.a aVar, int i2) {
        boolean a2 = a(i2, a(s, this.q));
        if (com.salesforce.marketingcloud.b.b(i2, com.salesforce.marketingcloud.b.u)) {
            this.m = new com.salesforce.marketingcloud.analytics.etanalytics.a(this.f, this.p);
            this.o = new com.salesforce.marketingcloud.analytics.stats.c(this.i, this.h, a2, this.f, this.g, this.j, this.p);
        }
        b(i2, a(t, this.r));
        if (!(this.m == null && this.l == null)) {
            this.k = new com.salesforce.marketingcloud.analytics.etanalytics.c(this.i, this.h, this.f, this.g, this.j, this.p);
        }
        this.d.a(this, this.e);
    }

    public void onBehavior(com.salesforce.marketingcloud.behaviors.a aVar, Bundle bundle) {
        int i2 = b.a[aVar.ordinal()];
        if (i2 == 1) {
            a(bundle);
        } else if (i2 == 2) {
            b(bundle);
        } else if (i2 == 3 || i2 == 4) {
            c(bundle);
        }
    }

    public void onTransitionEvent(int i2, Region region) {
        if (i2 == 1) {
            com.salesforce.marketingcloud.analytics.etanalytics.a aVar = this.m;
            if (aVar != null) {
                aVar.a(region);
            }
            com.salesforce.marketingcloud.analytics.etanalytics.b bVar = this.l;
            if (bVar != null) {
                bVar.a(region);
            }
            i iVar = this.f18n;
            if (iVar != null) {
                iVar.a(region);
            }
            com.salesforce.marketingcloud.analytics.stats.c cVar = this.o;
            if (cVar != null) {
                cVar.a(region);
            }
        } else if (i2 == 2) {
            com.salesforce.marketingcloud.analytics.etanalytics.a aVar2 = this.m;
            if (aVar2 != null) {
                aVar2.b(region);
            }
            com.salesforce.marketingcloud.analytics.etanalytics.b bVar2 = this.l;
            if (bVar2 != null) {
                bVar2.b(region);
            }
            i iVar2 = this.f18n;
            if (iVar2 != null) {
                iVar2.b(region);
            }
            com.salesforce.marketingcloud.analytics.stats.c cVar2 = this.o;
            if (cVar2 != null) {
                cVar2.b(region);
            }
        }
    }

    public void setPiIdentifier(String str) {
        if (str == null || TextUtils.getTrimmedLength(str) != 0) {
            i iVar = this.f18n;
            if (iVar != null) {
                iVar.setPiIdentifier(str);
                return;
            }
            return;
        }
        com.salesforce.marketingcloud.g.e(AnalyticsManager.TAG, "Call to setPiIdentifier() ignored. Predictive Intelligence Identifier contained only whitespace.", new Object[0]);
    }

    public void tearDown(boolean z) {
        this.d.a((com.salesforce.marketingcloud.behaviors.b) this);
        com.salesforce.marketingcloud.analytics.etanalytics.a aVar = this.m;
        if (aVar != null) {
            aVar.a(z);
            this.m = null;
        }
        com.salesforce.marketingcloud.analytics.etanalytics.b bVar = this.l;
        if (bVar != null) {
            bVar.a(z);
            this.l = null;
        }
        com.salesforce.marketingcloud.analytics.etanalytics.c cVar = this.k;
        if (cVar != null) {
            cVar.b();
            this.k = null;
        }
        com.salesforce.marketingcloud.analytics.stats.c cVar2 = this.o;
        if (cVar2 != null) {
            cVar2.a(z);
            this.o = null;
        }
        i iVar = this.f18n;
        if (iVar != null) {
            iVar.a(z);
            this.f18n = null;
        }
    }

    public void trackCartContents(PiCart piCart) {
        if (piCart == null) {
            com.salesforce.marketingcloud.g.e(AnalyticsManager.TAG, "PiCart may not be null.  We could not complete your trackCartContents() request.", new Object[0]);
            return;
        }
        com.salesforce.marketingcloud.analytics.etanalytics.a aVar = this.m;
        if (aVar != null) {
            aVar.trackCartContents(piCart);
        }
        com.salesforce.marketingcloud.analytics.etanalytics.b bVar = this.l;
        if (bVar != null) {
            bVar.trackCartContents(piCart);
        }
        i iVar = this.f18n;
        if (iVar != null) {
            iVar.trackCartContents(piCart);
        }
        com.salesforce.marketingcloud.analytics.stats.c cVar = this.o;
        if (cVar != null) {
            cVar.trackCartContents(piCart);
        }
    }

    public void trackCartConversion(PiOrder piOrder) {
        if (piOrder == null) {
            com.salesforce.marketingcloud.g.e(AnalyticsManager.TAG, "PiOrder may not be null.  We could not complete your trackCartConversion() request.", new Object[0]);
            return;
        }
        com.salesforce.marketingcloud.analytics.etanalytics.a aVar = this.m;
        if (aVar != null) {
            aVar.trackCartConversion(piOrder);
        }
        com.salesforce.marketingcloud.analytics.etanalytics.b bVar = this.l;
        if (bVar != null) {
            bVar.trackCartConversion(piOrder);
        }
        i iVar = this.f18n;
        if (iVar != null) {
            iVar.trackCartConversion(piOrder);
        }
        com.salesforce.marketingcloud.analytics.stats.c cVar = this.o;
        if (cVar != null) {
            cVar.trackCartConversion(piOrder);
        }
    }

    public void trackInboxOpenEvent(InboxMessage inboxMessage) {
        this.p.b().execute(new a("track_inbox_open", new Object[0], inboxMessage));
    }

    public void trackPageView(String str) {
        trackPageView(str, (String) null, (String) null, (String) null);
    }

    public h(MarketingCloudConfig marketingCloudConfig, j jVar, String str, com.salesforce.marketingcloud.alarms.b bVar, c cVar, com.salesforce.marketingcloud.http.c cVar2, l lVar) {
        this.e = EnumSet.of(com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_BACKGROUNDED, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_FOREGROUNDED, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_DEVICE_SHUTDOWN, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_DEVICE_BOOT_COMPLETE);
        this.f = (j) com.salesforce.marketingcloud.util.j.a(jVar, "MCStorage may not be null.");
        this.d = (c) com.salesforce.marketingcloud.util.j.a(cVar, "BehaviorManager may not be null.");
        this.g = cVar2;
        this.h = str;
        this.i = marketingCloudConfig;
        this.j = bVar;
        this.p = lVar;
    }

    private void b(int i2, com.salesforce.marketingcloud.toggles.a aVar) {
        if (!com.salesforce.marketingcloud.b.a(i2, 512)) {
            this.r = aVar;
            if (aVar == com.salesforce.marketingcloud.toggles.a.ENABLED || (this.i.piAnalyticsEnabled() && aVar == com.salesforce.marketingcloud.toggles.a.UNKNOWN)) {
                this.f18n = new i(this.i, this.f, this.g, this.p);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(int i2, com.salesforce.marketingcloud.toggles.a aVar) {
        boolean z = false;
        if (com.salesforce.marketingcloud.b.a(i2, 256)) {
            return false;
        }
        this.q = aVar;
        if (aVar == com.salesforce.marketingcloud.toggles.a.ENABLED || (this.i.analyticsEnabled() && aVar == com.salesforce.marketingcloud.toggles.a.UNKNOWN)) {
            z = true;
        }
        if (z) {
            this.l = new com.salesforce.marketingcloud.analytics.etanalytics.b(this.f, this.p);
        }
        return z;
    }

    public void trackPageView(String str, String str2) {
        trackPageView(str, str2, (String) null, (String) null);
    }

    private com.salesforce.marketingcloud.toggles.a a(String str, com.salesforce.marketingcloud.toggles.a aVar) {
        if (aVar != null) {
            return aVar;
        }
        String string = this.f.f().getString(str, (String) null);
        return string == null ? com.salesforce.marketingcloud.toggles.a.UNKNOWN : com.salesforce.marketingcloud.toggles.a.valueOf(string);
    }

    public void b(InAppMessage inAppMessage) {
        if (inAppMessage == null) {
            com.salesforce.marketingcloud.g.e(AnalyticsManager.TAG, "InAppMessage is null.  Call to onInAppMessageDownloaded() ignored.", new Object[0]);
            return;
        }
        com.salesforce.marketingcloud.analytics.etanalytics.a aVar = this.m;
        if (aVar != null) {
            aVar.b(inAppMessage);
        }
        com.salesforce.marketingcloud.analytics.etanalytics.b bVar = this.l;
        if (bVar != null) {
            bVar.b(inAppMessage);
        }
        i iVar = this.f18n;
        if (iVar != null) {
            iVar.b(inAppMessage);
        }
        com.salesforce.marketingcloud.analytics.stats.c cVar = this.o;
        if (cVar != null) {
            cVar.b(inAppMessage);
        }
    }

    public void trackPageView(String str, String str2, String str3) {
        trackPageView(str, str2, str3, (String) null);
    }

    public void a(e eVar, Event... eventArr) {
        com.salesforce.marketingcloud.analytics.etanalytics.a aVar = this.m;
        if (aVar != null) {
            aVar.a(eVar, eventArr);
        }
        com.salesforce.marketingcloud.analytics.etanalytics.b bVar = this.l;
        if (bVar != null) {
            bVar.a(eVar, eventArr);
        }
        i iVar = this.f18n;
        if (iVar != null) {
            iVar.a(eVar, eventArr);
        }
        com.salesforce.marketingcloud.analytics.stats.c cVar = this.o;
        if (cVar != null) {
            cVar.a(eVar, eventArr);
        }
    }

    public void b(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() <= 0) {
            com.salesforce.marketingcloud.g.e(AnalyticsManager.TAG, "Information not valid. Call to onSyncGateTimeOutEvent() ignored", new Object[0]);
            return;
        }
        com.salesforce.marketingcloud.analytics.etanalytics.a aVar = this.m;
        if (aVar != null) {
            aVar.b(jSONObject);
        }
        com.salesforce.marketingcloud.analytics.etanalytics.b bVar = this.l;
        if (bVar != null) {
            bVar.b(jSONObject);
        }
        i iVar = this.f18n;
        if (iVar != null) {
            iVar.b(jSONObject);
        }
        com.salesforce.marketingcloud.analytics.stats.c cVar = this.o;
        if (cVar != null) {
            cVar.b(jSONObject);
        }
    }

    public void trackPageView(String str, String str2, String str3, String str4) {
        if (TextUtils.isEmpty(str)) {
            com.salesforce.marketingcloud.g.e(AnalyticsManager.TAG, "url may not be null or empty.  We could not complete your trackPageView() request.", new Object[0]);
            return;
        }
        com.salesforce.marketingcloud.analytics.etanalytics.a aVar = this.m;
        if (aVar != null) {
            aVar.trackPageView(str, str2, str3, str4);
        }
        com.salesforce.marketingcloud.analytics.etanalytics.b bVar = this.l;
        if (bVar != null) {
            bVar.trackPageView(str, str2, str3, str4);
        }
        i iVar = this.f18n;
        if (iVar != null) {
            iVar.trackPageView(str, str2, str3, str4);
        }
        com.salesforce.marketingcloud.analytics.stats.c cVar = this.o;
        if (cVar != null) {
            cVar.trackPageView(str, str2, str3, str4);
        }
    }

    public void a(InAppMessage inAppMessage) {
        if (inAppMessage == null) {
            com.salesforce.marketingcloud.g.e(AnalyticsManager.TAG, "InAppMessage is null.  Call to onIamDisplayed() ignored.", new Object[0]);
            return;
        }
        com.salesforce.marketingcloud.analytics.etanalytics.a aVar = this.m;
        if (aVar != null) {
            aVar.a(inAppMessage);
        }
        com.salesforce.marketingcloud.analytics.etanalytics.b bVar = this.l;
        if (bVar != null) {
            bVar.a(inAppMessage);
        }
        i iVar = this.f18n;
        if (iVar != null) {
            iVar.a(inAppMessage);
        }
        com.salesforce.marketingcloud.analytics.stats.c cVar = this.o;
        if (cVar != null) {
            cVar.a(inAppMessage);
        }
    }

    public void b(NotificationMessage notificationMessage) {
        com.salesforce.marketingcloud.analytics.etanalytics.a aVar = this.m;
        if (aVar != null) {
            aVar.b(notificationMessage);
        }
        com.salesforce.marketingcloud.analytics.etanalytics.b bVar = this.l;
        if (bVar != null) {
            bVar.b(notificationMessage);
        }
        i iVar = this.f18n;
        if (iVar != null) {
            iVar.b(notificationMessage);
        }
        com.salesforce.marketingcloud.analytics.stats.c cVar = this.o;
        if (cVar != null) {
            cVar.b(notificationMessage);
        }
    }

    public void a(InAppMessage inAppMessage, com.salesforce.marketingcloud.messages.iam.j jVar) {
        if (inAppMessage == null || jVar == null) {
            com.salesforce.marketingcloud.g.e(AnalyticsManager.TAG, "InAppMessage or MessageCompletedEvent is null.  Call to onInAppMessageCompleted() ignored.", new Object[0]);
            return;
        }
        com.salesforce.marketingcloud.analytics.etanalytics.a aVar = this.m;
        if (aVar != null) {
            aVar.a(inAppMessage, jVar);
        }
        com.salesforce.marketingcloud.analytics.etanalytics.b bVar = this.l;
        if (bVar != null) {
            bVar.a(inAppMessage, jVar);
        }
        i iVar = this.f18n;
        if (iVar != null) {
            iVar.a(inAppMessage, jVar);
        }
        com.salesforce.marketingcloud.analytics.stats.c cVar = this.o;
        if (cVar != null) {
            cVar.a(inAppMessage, jVar);
        }
    }

    public void a(InAppMessage inAppMessage, JSONObject jSONObject) {
        if (inAppMessage == null || jSONObject == null || jSONObject.length() <= 0) {
            com.salesforce.marketingcloud.g.e(AnalyticsManager.TAG, "Message and/or Information not valid. Call to onInAppMessageThrottled() ignored", new Object[0]);
            return;
        }
        com.salesforce.marketingcloud.analytics.etanalytics.a aVar = this.m;
        if (aVar != null) {
            aVar.a(inAppMessage, jSONObject);
        }
        com.salesforce.marketingcloud.analytics.etanalytics.b bVar = this.l;
        if (bVar != null) {
            bVar.a(inAppMessage, jSONObject);
        }
        i iVar = this.f18n;
        if (iVar != null) {
            iVar.a(inAppMessage, jSONObject);
        }
        com.salesforce.marketingcloud.analytics.stats.c cVar = this.o;
        if (cVar != null) {
            cVar.a(inAppMessage, jSONObject);
        }
    }

    public void a(String str, String str2, List<String> list) {
        if (str == null || str2 == null || list == null || list.isEmpty()) {
            com.salesforce.marketingcloud.g.e(AnalyticsManager.TAG, "messageId, activityInstanceId or reasons is null.  Call to onInAppMessageValidationError() ignored.", new Object[0]);
            return;
        }
        com.salesforce.marketingcloud.analytics.etanalytics.a aVar = this.m;
        if (aVar != null) {
            aVar.a(str, str2, list);
        }
        com.salesforce.marketingcloud.analytics.etanalytics.b bVar = this.l;
        if (bVar != null) {
            bVar.a(str, str2, list);
        }
        i iVar = this.f18n;
        if (iVar != null) {
            iVar.a(str, str2, list);
        }
        com.salesforce.marketingcloud.analytics.stats.c cVar = this.o;
        if (cVar != null) {
            cVar.a(str, str2, list);
        }
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() <= 0) {
            com.salesforce.marketingcloud.g.e(AnalyticsManager.TAG, "Information not valid. Call to onInvalidConfigEvent() ignored", new Object[0]);
            return;
        }
        com.salesforce.marketingcloud.analytics.etanalytics.a aVar = this.m;
        if (aVar != null) {
            aVar.a(jSONObject);
        }
        com.salesforce.marketingcloud.analytics.etanalytics.b bVar = this.l;
        if (bVar != null) {
            bVar.a(jSONObject);
        }
        i iVar = this.f18n;
        if (iVar != null) {
            iVar.a(jSONObject);
        }
        com.salesforce.marketingcloud.analytics.stats.c cVar = this.o;
        if (cVar != null) {
            cVar.a(jSONObject);
        }
    }

    public void a(InboxMessage inboxMessage) {
        if (inboxMessage == null) {
            com.salesforce.marketingcloud.g.e(AnalyticsManager.TAG, "InboxMessage is null.  Call to onMessageDownloaded() ignored.", new Object[0]);
            return;
        }
        com.salesforce.marketingcloud.analytics.etanalytics.a aVar = this.m;
        if (aVar != null) {
            aVar.a(inboxMessage);
        }
        com.salesforce.marketingcloud.analytics.etanalytics.b bVar = this.l;
        if (bVar != null) {
            bVar.a(inboxMessage);
        }
        i iVar = this.f18n;
        if (iVar != null) {
            iVar.a(inboxMessage);
        }
        com.salesforce.marketingcloud.analytics.stats.c cVar = this.o;
        if (cVar != null) {
            cVar.a(inboxMessage);
        }
    }

    public void a(l.a aVar, JSONObject jSONObject) {
        com.salesforce.marketingcloud.analytics.etanalytics.a aVar2 = this.m;
        if (aVar2 != null) {
            aVar2.a(aVar, jSONObject);
        }
        com.salesforce.marketingcloud.analytics.etanalytics.b bVar = this.l;
        if (bVar != null) {
            bVar.a(aVar, jSONObject);
        }
        i iVar = this.f18n;
        if (iVar != null) {
            iVar.a(aVar, jSONObject);
        }
        com.salesforce.marketingcloud.analytics.stats.c cVar = this.o;
        if (cVar != null) {
            cVar.a(aVar, jSONObject);
        }
    }

    public void a(String str, String str2, String str3, String str4) {
        if (str == null || str2 == null || str3 == null) {
            com.salesforce.marketingcloud.g.e(AnalyticsManager.TAG, "triggerId, outcomeId or outcomeType is null.  Call to onTriggerSuccessEvent() ignored.", new Object[0]);
            return;
        }
        com.salesforce.marketingcloud.analytics.etanalytics.a aVar = this.m;
        if (aVar != null) {
            aVar.a(str, str2, str3, str4);
        }
        com.salesforce.marketingcloud.analytics.etanalytics.b bVar = this.l;
        if (bVar != null) {
            bVar.a(str, str2, str3, str4);
        }
        i iVar = this.f18n;
        if (iVar != null) {
            iVar.a(str, str2, str3, str4);
        }
        com.salesforce.marketingcloud.analytics.stats.c cVar = this.o;
        if (cVar != null) {
            cVar.a(str, str2, str3, str4);
        }
    }

    public void a(NotificationMessage notificationMessage) {
        com.salesforce.marketingcloud.analytics.etanalytics.a aVar = this.m;
        if (aVar != null) {
            aVar.a(notificationMessage);
        }
        com.salesforce.marketingcloud.analytics.etanalytics.b bVar = this.l;
        if (bVar != null) {
            bVar.a(notificationMessage);
        }
        i iVar = this.f18n;
        if (iVar != null) {
            iVar.a(notificationMessage);
        }
        com.salesforce.marketingcloud.analytics.stats.c cVar = this.o;
        if (cVar != null) {
            cVar.a(notificationMessage);
        }
    }

    public void a(NotificationMessage notificationMessage, boolean z) {
        com.salesforce.marketingcloud.analytics.etanalytics.a aVar = this.m;
        if (aVar != null) {
            aVar.a(notificationMessage, z);
        }
        com.salesforce.marketingcloud.analytics.etanalytics.b bVar = this.l;
        if (bVar != null) {
            bVar.a(notificationMessage, z);
        }
        i iVar = this.f18n;
        if (iVar != null) {
            iVar.a(notificationMessage, z);
        }
    }
}
