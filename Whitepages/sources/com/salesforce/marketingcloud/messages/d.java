package com.salesforce.marketingcloud.messages;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import androidx.collection.ArraySet;
import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.MarketingCloudConfig;
import com.salesforce.marketingcloud.alarms.a;
import com.salesforce.marketingcloud.alarms.b;
import com.salesforce.marketingcloud.internal.l;
import com.salesforce.marketingcloud.location.LatLon;
import com.salesforce.marketingcloud.messages.RegionMessageManager;
import com.salesforce.marketingcloud.messages.c;
import com.salesforce.marketingcloud.messages.geofence.GeofenceMessageResponse;
import com.salesforce.marketingcloud.messages.proximity.ProximityMessageResponse;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import com.salesforce.marketingcloud.notifications.a;
import com.salesforce.marketingcloud.storage.j;
import com.salesforce.marketingcloud.util.h;
import java.util.EnumSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

@SuppressLint({"UnknownNullness"})
public class d implements com.salesforce.marketingcloud.e, RegionMessageManager, b.C0003b, com.salesforce.marketingcloud.location.e, com.salesforce.marketingcloud.behaviors.b, c.b, c.a, com.salesforce.marketingcloud.location.c {
    private static final String A = "RegionMessageManager";
    private static final float B = 0.8f;
    static final String C = com.salesforce.marketingcloud.g.a(A);
    static final String w = "et_geo_enabled_key";
    static final String x = "et_region_message_toggled_key";
    static final String y = "et_proximity_enabled_key";
    static final int z = 5000;
    final j d;
    private final com.salesforce.marketingcloud.alarms.b e;
    private final com.salesforce.marketingcloud.location.f f;
    private final com.salesforce.marketingcloud.proximity.e g;
    private final MarketingCloudConfig h;
    private final String i;
    private final Context j;
    private final com.salesforce.marketingcloud.notifications.a k;
    private final com.salesforce.marketingcloud.behaviors.c l;
    private final com.salesforce.marketingcloud.http.c m;

    /* renamed from: n  reason: collision with root package name */
    private final Set<RegionMessageManager.GeofenceMessageResponseListener> f32n = new ArraySet();
    private final Set<RegionMessageManager.ProximityMessageResponseListener> o = new ArraySet();
    private final Set<RegionMessageManager.RegionTransitionEventListener> p;
    private final AtomicBoolean q;
    private final l r;
    private com.salesforce.marketingcloud.messages.geofence.a s;
    private com.salesforce.marketingcloud.messages.proximity.a t;
    private com.salesforce.marketingcloud.toggles.a u;
    private com.salesforce.marketingcloud.toggles.a v;

    class a extends com.salesforce.marketingcloud.internal.g {
        a(String str, Object... objArr) {
            super(str, objArr);
        }

        /* access modifiers changed from: protected */
        public void a() {
            LatLon f;
            j jVar = d.this.d;
            if (jVar != null && (f = jVar.r().f(d.this.d.b())) != null) {
                d.this.a(f);
            }
        }
    }

    class b extends com.salesforce.marketingcloud.internal.g {
        b(String str, Object... objArr) {
            super(str, objArr);
        }

        /* access modifiers changed from: protected */
        public void a() {
            LatLon f;
            j jVar = d.this.d;
            if (jVar != null && (f = jVar.r().f(d.this.d.b())) != null) {
                d.this.b(f);
            }
        }
    }

    class c extends com.salesforce.marketingcloud.internal.g {
        c(String str, Object... objArr) {
            super(str, objArr);
        }

        /* access modifiers changed from: protected */
        public void a() {
            d.this.d.t().l();
        }
    }

    /* renamed from: com.salesforce.marketingcloud.messages.d$d  reason: collision with other inner class name */
    class C0023d extends com.salesforce.marketingcloud.internal.g {
        final /* synthetic */ LatLon b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        C0023d(String str, Object[] objArr, LatLon latLon) {
            super(str, objArr);
            this.b = latLon;
        }

        /* access modifiers changed from: protected */
        public void a() {
            try {
                d dVar = d.this;
                boolean a = dVar.a(this.b, dVar.d.t().m(d.this.d.b()));
                d.this.d.r().a(this.b, d.this.d.b());
                if (a) {
                    d.this.a(this.b, (int) d.z);
                    d.this.a(this.b);
                    d.this.b(this.b);
                }
            } catch (Exception e) {
                com.salesforce.marketingcloud.g.b(d.C, e, "Unable to store last location", new Object[0]);
            }
        }
    }

    class e extends com.salesforce.marketingcloud.internal.g {
        final /* synthetic */ Region b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        e(String str, Object[] objArr, Region region) {
            super(str, objArr);
            this.b = region;
        }

        /* access modifiers changed from: protected */
        public void a() {
            try {
                d.this.d.t().a(this.b, d.this.d.b());
            } catch (Exception e) {
                com.salesforce.marketingcloud.g.b(d.C, e, "Unable to set magic region", new Object[0]);
            }
        }
    }

    class f implements a.b {
        final /* synthetic */ Message a;

        f(Message message) {
            this.a = message;
        }

        public void a(int i) {
            if (i != -1) {
                try {
                    com.salesforce.marketingcloud.internal.f.a(this.a, i);
                    d.this.d.s().a(this.a, d.this.d.b());
                } catch (Exception e) {
                    com.salesforce.marketingcloud.g.b(d.C, e, "Unable to update message id with notification id.", new Object[0]);
                }
            }
        }
    }

    static /* synthetic */ class g {
        static final /* synthetic */ int[] a;
        static final /* synthetic */ int[] b;

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|(2:7|8)|9|(3:11|12|14)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|(2:7|8)|9|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.salesforce.marketingcloud.behaviors.a[] r0 = com.salesforce.marketingcloud.behaviors.a.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                b = r0
                r1 = 1
                com.salesforce.marketingcloud.behaviors.a r2 = com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_DEVICE_BOOT_COMPLETE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = b     // Catch:{ NoSuchFieldError -> 0x001d }
                com.salesforce.marketingcloud.behaviors.a r2 = com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_PACKAGE_REPLACED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r3 = 2
                r0[r2] = r3     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = b     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.salesforce.marketingcloud.behaviors.a r2 = com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_DEVICE_SHUTDOWN     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3 = 3
                r0[r2] = r3     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = b     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.salesforce.marketingcloud.behaviors.a r2 = com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_FOREGROUNDED     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r3 = 4
                r0[r2] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                com.salesforce.marketingcloud.alarms.a$a[] r0 = com.salesforce.marketingcloud.alarms.a.C0001a.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                com.salesforce.marketingcloud.alarms.a$a r2 = com.salesforce.marketingcloud.alarms.a.C0001a.FETCH_REGION_MESSAGES_DAILY     // Catch:{ NoSuchFieldError -> 0x0044 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0044 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0044 }
            L_0x0044:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.d.g.<clinit>():void");
        }
    }

    public d(Context context, MarketingCloudConfig marketingCloudConfig, j jVar, String str, com.salesforce.marketingcloud.location.f fVar, com.salesforce.marketingcloud.proximity.e eVar, com.salesforce.marketingcloud.behaviors.c cVar, com.salesforce.marketingcloud.alarms.b bVar, com.salesforce.marketingcloud.http.c cVar2, com.salesforce.marketingcloud.notifications.a aVar, l lVar, RegionMessageManager.RegionTransitionEventListener regionTransitionEventListener) {
        ArraySet arraySet = new ArraySet();
        this.p = arraySet;
        this.q = new AtomicBoolean(false);
        this.j = context;
        this.d = jVar;
        this.f = fVar;
        this.g = eVar;
        this.k = aVar;
        this.e = bVar;
        this.l = cVar;
        this.m = cVar2;
        this.i = str;
        this.h = marketingCloudConfig;
        arraySet.add(regionTransitionEventListener);
        this.r = lVar;
    }

    private boolean a(boolean z2) {
        if (com.salesforce.marketingcloud.b.a(com.salesforce.marketingcloud.b.a(this.d.o()), 32)) {
            return false;
        }
        if (z2 || !isGeofenceMessagingEnabled()) {
            com.salesforce.marketingcloud.messages.geofence.a aVar = this.s;
            if (aVar != null && !aVar.d()) {
                com.salesforce.marketingcloud.g.a(C, "Geofence messaging was not enabled due to device limitation.", new Object[0]);
                return false;
            } else if (h.b(this.j)) {
                return true;
            } else {
                f();
                return false;
            }
        } else {
            com.salesforce.marketingcloud.g.a(C, "Geofence messaging is already enabled", new Object[0]);
            return false;
        }
    }

    private boolean b(boolean z2) {
        if (com.salesforce.marketingcloud.b.a(com.salesforce.marketingcloud.b.a(this.d.o()), 64)) {
            return false;
        }
        if (!z2 && isProximityMessagingEnabled()) {
            com.salesforce.marketingcloud.g.a(C, "Proximity messaging is already enabled.", new Object[0]);
            return false;
        } else if (this.t == null) {
            com.salesforce.marketingcloud.g.a(C, "Proximity messaging was not enabled while configuring the SDK.  Messaging will not be enabled.", new Object[0]);
            return false;
        } else if (Build.VERSION.SDK_INT >= 31 && this.h.proximityNotificationCustomizationOptions() == null) {
            com.salesforce.marketingcloud.g.a(C, "Proximity messaging configuration is not passed while configuring the SDK.  Messaging will not be enabled.", new Object[0]);
            return false;
        } else if (!this.t.d() || !this.f.a()) {
            com.salesforce.marketingcloud.g.a(C, "Proximity messaging was not enabled due to device limitation.", new Object[0]);
            return false;
        } else if (!h.b(this.j)) {
            f();
            return false;
        } else if (h.c(this.j)) {
            return true;
        } else {
            e();
            return false;
        }
    }

    private synchronized boolean c(boolean z2) {
        if (!com.salesforce.marketingcloud.util.d.b()) {
            com.salesforce.marketingcloud.g.e(C, "GooglePlayServices Location dependency missing from build.", new Object[0]);
            return false;
        } else if (!a(z2)) {
            return false;
        } else {
            com.salesforce.marketingcloud.g.d(C, "Enabling geofence messaging", new Object[0]);
            if (!z2) {
                this.d.f().edit().putBoolean(w, true).apply();
                this.d.f().edit().putBoolean(x, true).apply();
                this.u = com.salesforce.marketingcloud.toggles.a.ENABLED;
                Bundle bundle = new Bundle();
                bundle.putBoolean(RegionMessageManager.BUNDLE_KEY_MESSAGING_ENABLED, true);
                com.salesforce.marketingcloud.behaviors.c.a(this.j, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_CUSTOMER_FENCE_MESSAGING_TOGGLED, bundle);
            }
            this.s.a();
            return c();
        }
    }

    private boolean d(boolean z2) {
        if (!com.salesforce.marketingcloud.util.d.a()) {
            com.salesforce.marketingcloud.g.e(C, "If you wish to use proximity messenger then you need to add the AltBeacon dependency.", new Object[0]);
            return false;
        } else if (!b(z2)) {
            return false;
        } else {
            com.salesforce.marketingcloud.g.d(C, "Enabling proximity messaging.", new Object[0]);
            if (!z2) {
                j jVar = this.d;
                if (jVar != null) {
                    jVar.f().edit().putBoolean(y, true).apply();
                    this.d.f().edit().putBoolean(x, true).apply();
                }
                this.v = com.salesforce.marketingcloud.toggles.a.ENABLED;
                Bundle bundle = new Bundle();
                bundle.putBoolean(RegionMessageManager.BUNDLE_KEY_MESSAGING_ENABLED, true);
                com.salesforce.marketingcloud.behaviors.c.a(this.j, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_CUSTOMER_PROXIMITY_MESSAGING_TOGGLED, bundle);
            } else {
                this.t.c();
            }
            this.t.a();
            return c();
        }
    }

    private void e() {
        if (Build.VERSION.SDK_INT >= 31) {
            com.salesforce.marketingcloud.g.a(C, "Missing %s or %s", "android.permission.BLUETOOTH_SCAN", "android.permission.BLUETOOTH_CONNECT");
        }
    }

    private void f() {
        if (Build.VERSION.SDK_INT < 29) {
            com.salesforce.marketingcloud.g.a(C, "Missing %s", "android.permission.ACCESS_FINE_LOCATION");
        } else {
            com.salesforce.marketingcloud.g.a(C, "Missing %s or %s", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_BACKGROUND_LOCATION");
        }
    }

    private void g() {
        if (isGeofenceMessagingEnabled() && a(true)) {
            this.s.c();
        }
        if (isProximityMessagingEnabled() && b(true)) {
            this.t.c();
        }
    }

    private void h() {
        this.r.b().execute(new c("reset_flags", new Object[0]));
    }

    private void i() {
        if (isGeofenceMessagingEnabled()) {
            this.r.b().execute(new a("update_geofence", new Object[0]));
        }
    }

    private void j() {
        if (isProximityMessagingEnabled()) {
            this.r.b().execute(new b("update_proximity", new Object[0]));
        }
    }

    public final String componentName() {
        return A;
    }

    public final JSONObject componentState() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("geofenceMessagingEnabled", isGeofenceMessagingEnabled());
            jSONObject.put("proximityMessagingEnabled", isProximityMessagingEnabled());
            com.salesforce.marketingcloud.storage.l t2 = this.d.t();
            com.salesforce.marketingcloud.util.c b2 = this.d.b();
            if (t2 != null) {
                Region m2 = t2.m(b2);
                if (m2 != null) {
                    jSONObject.put("magic_fence", m2);
                }
                jSONObject.put("geofence_regions", t2.a(1, b2));
                jSONObject.put("geofence_region_messages", this.d.s().a(b2));
                jSONObject.put("proximity_regions", t2.a(3, b2));
                jSONObject.put("proximity_region_messages", this.d.s().b(b2));
                jSONObject.put("boot_complete_permission", h.a(this.j, "android.permission.RECEIVE_BOOT_COMPLETED"));
            }
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.a(C, (Throwable) e2, "Error creating RegionMessageManager state.", new Object[0]);
        }
        return jSONObject;
    }

    public final synchronized void controlChannelInit(int i2) {
        try {
            if (com.salesforce.marketingcloud.b.a(i2, 32)) {
                disableGeofenceMessaging();
                this.s = null;
                com.salesforce.marketingcloud.messages.geofence.a.a(this.d, this.f, this.m, com.salesforce.marketingcloud.b.c(i2, 32));
            } else if (this.s == null && this.h.geofencingEnabled()) {
                a((InitializationStatus.a) null, i2);
            }
            if (com.salesforce.marketingcloud.b.a(i2, 64)) {
                disableProximityMessaging();
                this.t = null;
                com.salesforce.marketingcloud.messages.proximity.a.a(this.d, this.g, this.m, com.salesforce.marketingcloud.b.c(i2, 64));
            } else if (this.t == null && this.h.proximityEnabled()) {
                b((InitializationStatus.a) null, i2);
            }
            if (com.salesforce.marketingcloud.b.a(i2, 96)) {
                this.f.b((com.salesforce.marketingcloud.location.c) this);
                this.f.b((com.salesforce.marketingcloud.location.e) this);
                this.l.a((com.salesforce.marketingcloud.behaviors.b) this);
                this.d.r().g();
                com.salesforce.marketingcloud.alarms.b bVar = this.e;
                a.C0001a aVar = a.C0001a.FETCH_REGION_MESSAGES_DAILY;
                bVar.e(aVar);
                this.e.d(aVar);
            } else {
                this.l.a(this, EnumSet.of(com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_DEVICE_BOOT_COMPLETE, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_PACKAGE_REPLACED, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_DEVICE_SHUTDOWN, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_FOREGROUNDED));
                this.f.a((com.salesforce.marketingcloud.location.c) this);
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public final synchronized void disableGeofenceMessaging() {
        try {
            com.salesforce.marketingcloud.g.a(C, "Disabling geofence messaging", new Object[0]);
            if (isGeofenceMessagingEnabled()) {
                j jVar = this.d;
                if (jVar != null) {
                    jVar.f().edit().putBoolean(w, false).apply();
                }
                this.u = com.salesforce.marketingcloud.toggles.a.DISABLED;
                Bundle bundle = new Bundle();
                bundle.putBoolean(RegionMessageManager.BUNDLE_KEY_MESSAGING_ENABLED, false);
                com.salesforce.marketingcloud.behaviors.c.a(this.j, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_CUSTOMER_FENCE_MESSAGING_TOGGLED, bundle);
                com.salesforce.marketingcloud.messages.geofence.a aVar = this.s;
                if (aVar != null) {
                    aVar.b();
                }
            }
            a();
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public final synchronized void disableProximityMessaging() {
        try {
            com.salesforce.marketingcloud.g.a(C, "Disabling proximity messaging", new Object[0]);
            if (isProximityMessagingEnabled()) {
                j jVar = this.d;
                if (jVar != null) {
                    jVar.f().edit().putBoolean(y, false).apply();
                }
                this.v = com.salesforce.marketingcloud.toggles.a.DISABLED;
                Bundle bundle = new Bundle();
                bundle.putBoolean(RegionMessageManager.BUNDLE_KEY_MESSAGING_ENABLED, false);
                com.salesforce.marketingcloud.behaviors.c.a(this.j, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_CUSTOMER_PROXIMITY_MESSAGING_TOGGLED, bundle);
                com.salesforce.marketingcloud.messages.proximity.a aVar = this.t;
                if (aVar != null) {
                    aVar.b();
                }
            }
            a();
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    @SuppressLint({"MissingPermission"})
    public final synchronized boolean enableGeofenceMessaging() {
        return c(false);
    }

    @SuppressLint({"MissingPermission"})
    public final synchronized boolean enableProximityMessaging() {
        return d(false);
    }

    public final synchronized void init(InitializationStatus.a aVar, int i2) {
        try {
            a(aVar, i2);
            b(aVar, i2);
            if (this.s == null) {
                if (this.t != null) {
                }
            }
            this.l.a(this, EnumSet.of(com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_DEVICE_BOOT_COMPLETE, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_PACKAGE_REPLACED, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_DEVICE_SHUTDOWN, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_FOREGROUNDED));
            this.f.a((com.salesforce.marketingcloud.location.c) this);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public final boolean isGeofenceMessagingEnabled() {
        if (com.salesforce.marketingcloud.b.a(com.salesforce.marketingcloud.b.a(this.d.o()), 32)) {
            return false;
        }
        if (this.u == null) {
            this.u = a(w, (com.salesforce.marketingcloud.toggles.a) null);
        }
        com.salesforce.marketingcloud.toggles.a aVar = this.u;
        return aVar == com.salesforce.marketingcloud.toggles.a.ENABLED || (aVar == com.salesforce.marketingcloud.toggles.a.UNKNOWN && this.h.geofencingEnabled());
    }

    public final boolean isProximityMessagingEnabled() {
        if (com.salesforce.marketingcloud.b.a(com.salesforce.marketingcloud.b.a(this.d.o()), 64)) {
            return false;
        }
        if (this.v == null) {
            this.v = a(y, (com.salesforce.marketingcloud.toggles.a) null);
        }
        com.salesforce.marketingcloud.toggles.a aVar = this.v;
        return aVar == com.salesforce.marketingcloud.toggles.a.ENABLED || (aVar == com.salesforce.marketingcloud.toggles.a.UNKNOWN && this.h.proximityEnabled());
    }

    public final void onBehavior(com.salesforce.marketingcloud.behaviors.a aVar, Bundle bundle) {
        if (aVar != null) {
            int i2 = g.b[aVar.ordinal()];
            if (i2 == 1) {
                h();
            } else if (i2 != 2) {
                if (i2 == 3) {
                    h();
                    return;
                } else if (i2 == 4) {
                    i();
                    j();
                    if (isGeofenceMessagingEnabled() || isProximityMessagingEnabled()) {
                        com.salesforce.marketingcloud.alarms.b bVar = this.e;
                        a.C0001a aVar2 = a.C0001a.FETCH_REGION_MESSAGES_DAILY;
                        bVar.d(aVar2);
                        this.e.b(aVar2);
                        return;
                    }
                    return;
                } else {
                    return;
                }
            }
            g();
        }
    }

    public final void registerGeofenceMessageResponseListener(RegionMessageManager.GeofenceMessageResponseListener geofenceMessageResponseListener) {
        if (geofenceMessageResponseListener != null) {
            synchronized (this.f32n) {
                this.f32n.add(geofenceMessageResponseListener);
            }
        }
    }

    public final void registerProximityMessageResponseListener(RegionMessageManager.ProximityMessageResponseListener proximityMessageResponseListener) {
        if (proximityMessageResponseListener != null) {
            synchronized (this.o) {
                this.o.add(proximityMessageResponseListener);
            }
        }
    }

    public final void registerRegionTransitionEventListener(RegionMessageManager.RegionTransitionEventListener regionTransitionEventListener) {
        if (regionTransitionEventListener != null) {
            synchronized (this.p) {
                this.p.add(regionTransitionEventListener);
            }
        }
    }

    public void tearDown(boolean z2) {
    }

    public final void unregisterGeofenceMessageResponseListener(RegionMessageManager.GeofenceMessageResponseListener geofenceMessageResponseListener) {
        synchronized (this.f32n) {
            this.f32n.remove(geofenceMessageResponseListener);
        }
    }

    public final void unregisterProximityMessageResponseListener(RegionMessageManager.ProximityMessageResponseListener proximityMessageResponseListener) {
        synchronized (this.o) {
            this.o.remove(proximityMessageResponseListener);
        }
    }

    public final void unregisterRegionTransitionEventListener(RegionMessageManager.RegionTransitionEventListener regionTransitionEventListener) {
        synchronized (this.p) {
            this.p.remove(regionTransitionEventListener);
        }
    }

    private void a() {
        if (!isProximityMessagingEnabled() && !isGeofenceMessagingEnabled()) {
            this.e.d(a.C0001a.FETCH_REGION_MESSAGES_DAILY);
        }
    }

    private void b() {
        disableProximityMessaging();
        disableGeofenceMessaging();
    }

    @SuppressLint({"MissingPermission"})
    private boolean c() {
        if (this.s == null && this.t == null) {
            return false;
        }
        if (this.q.compareAndSet(false, true)) {
            try {
                this.f.a((com.salesforce.marketingcloud.location.e) this);
            } catch (Exception e2) {
                com.salesforce.marketingcloud.g.b(C, e2, "Unable to request location update", new Object[0]);
                b();
                return false;
            }
        }
        this.e.b(a.C0001a.FETCH_REGION_MESSAGES_DAILY);
        return true;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000d, code lost:
        r0 = r3.d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean d() {
        /*
            r3 = this;
            boolean r0 = r3.isProximityMessagingEnabled()
            r1 = 0
            if (r0 != 0) goto L_0x000d
            boolean r0 = r3.isGeofenceMessagingEnabled()
            if (r0 == 0) goto L_0x001e
        L_0x000d:
            com.salesforce.marketingcloud.storage.j r0 = r3.d
            if (r0 == 0) goto L_0x001e
            android.content.SharedPreferences r0 = r0.f()
            java.lang.String r2 = "et_region_message_toggled_key"
            boolean r0 = r0.getBoolean(r2, r1)
            if (r0 == 0) goto L_0x001e
            r1 = 1
        L_0x001e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.d.d():boolean");
    }

    private com.salesforce.marketingcloud.toggles.a a(String str, com.salesforce.marketingcloud.toggles.a aVar) {
        if (aVar == null) {
            return this.d.f().getBoolean(str, false) ? com.salesforce.marketingcloud.toggles.a.ENABLED : com.salesforce.marketingcloud.toggles.a.DISABLED;
        }
        return aVar;
    }

    private void b(InitializationStatus.a aVar, int i2) {
        if (!com.salesforce.marketingcloud.b.a(i2, 64)) {
            this.t = new com.salesforce.marketingcloud.messages.proximity.a(this.d, this.g, this.m, this.r, this);
            this.e.a((b.C0003b) this, a.C0001a.FETCH_REGION_MESSAGES_DAILY);
            if (isProximityMessagingEnabled()) {
                if (!d(true)) {
                    disableProximityMessaging();
                }
                if (aVar != null) {
                    aVar.c(true ^ h.b(this.j));
                }
            }
        }
    }

    private static com.salesforce.marketingcloud.location.b c(Region region) {
        return new com.salesforce.marketingcloud.location.b(region.id(), B * ((float) region.radius()), region.center().latitude(), region.center().longitude(), 2);
    }

    private void d(Region region) {
        this.r.b().execute(new e("storing_fence", new Object[0], region));
    }

    private void a(InitializationStatus.a aVar, int i2) {
        if (!com.salesforce.marketingcloud.b.a(i2, 32)) {
            this.s = new com.salesforce.marketingcloud.messages.geofence.a(this.d, this.f, this.m, this.r, this);
            this.e.a((b.C0003b) this, a.C0001a.FETCH_REGION_MESSAGES_DAILY);
            if (isGeofenceMessagingEnabled()) {
                if (!c(true)) {
                    disableGeofenceMessaging();
                }
                if (aVar != null) {
                    aVar.c(true ^ h.b(this.j));
                }
            }
        }
    }

    private void b(MessageResponse messageResponse) {
        if (messageResponse instanceof GeofenceMessageResponse) {
            synchronized (this.f32n) {
                if (!this.f32n.isEmpty()) {
                    for (RegionMessageManager.GeofenceMessageResponseListener next : this.f32n) {
                        if (next != null) {
                            try {
                                next.onGeofenceMessageResponse((GeofenceMessageResponse) messageResponse);
                            } catch (Exception e2) {
                                com.salesforce.marketingcloud.g.b(C, e2, "%s threw an exception while processing the geofence response", next.getClass().getName());
                            }
                        }
                    }
                }
            }
        } else if (messageResponse instanceof ProximityMessageResponse) {
            synchronized (this.o) {
                if (!this.o.isEmpty()) {
                    for (RegionMessageManager.ProximityMessageResponseListener next2 : this.o) {
                        if (next2 != null) {
                            try {
                                next2.onProximityMessageResponse((ProximityMessageResponse) messageResponse);
                            } catch (Exception e3) {
                                com.salesforce.marketingcloud.g.b(C, e3, "%s threw an exception while processing the proximity response", next2.getClass().getName());
                            }
                        }
                    }
                }
            }
        }
    }

    private void a(int i2, Region region) {
        synchronized (this.p) {
            if (!this.p.isEmpty()) {
                for (RegionMessageManager.RegionTransitionEventListener next : this.p) {
                    if (next != null) {
                        try {
                            next.onTransitionEvent(i2, region);
                        } catch (Exception e2) {
                            com.salesforce.marketingcloud.g.b(C, e2, "%s threw an exception while processing the region (%s) transition (%d)", next.getClass().getName(), region.id(), Integer.valueOf(i2));
                        }
                    }
                }
            }
        }
    }

    public void b(Region region) {
        a(1, region);
    }

    public final void a(a.C0001a aVar) {
        if (g.a[aVar.ordinal()] == 1) {
            i();
            j();
            if (isGeofenceMessagingEnabled() || isProximityMessagingEnabled()) {
                this.e.b(a.C0001a.FETCH_REGION_MESSAGES_DAILY);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void b(LatLon latLon) {
        com.salesforce.marketingcloud.messages.proximity.a aVar;
        if (!isProximityMessagingEnabled() || (aVar = this.t) == null || latLon == null) {
            com.salesforce.marketingcloud.g.a(C, "Tried to update proximity messages, but was not enabled.", new Object[0]);
        } else {
            aVar.a(latLon, this.i, this.h, (c.b) this);
        }
    }

    public void a(Location location) {
        this.q.set(false);
        if (location != null) {
            try {
                this.r.b().execute(new C0023d("store_latlon", new Object[0], new LatLon(location.getLatitude(), location.getLongitude())));
            } catch (Exception e2) {
                com.salesforce.marketingcloud.g.b(C, e2, "Unable to make geofence message request after location update", new Object[0]);
            }
        }
    }

    public final void a(MessageResponse messageResponse) {
        if (messageResponse != null) {
            b(messageResponse);
            try {
                Region a2 = com.salesforce.marketingcloud.internal.j.a(messageResponse.getRefreshCenter(), messageResponse.getRefreshRadius());
                d(a2);
                this.f.a(c(a2));
            } catch (Exception e2) {
                com.salesforce.marketingcloud.g.b(C, e2, "Failed to updated radius for magic region.", new Object[0]);
            }
        }
    }

    public final void a(int i2, String str) {
        com.salesforce.marketingcloud.g.a(C, "Region error %d - %s", Integer.valueOf(i2), str);
    }

    @SuppressLint({"MissingPermission"})
    public final void a(String str, int i2, Location location) {
        if (i2 == 2 && Region.MAGIC_REGION_ID.equals(str)) {
            String str2 = C;
            com.salesforce.marketingcloud.g.d(str2, "MagicRegion exited", new Object[0]);
            if (!h.b(this.j)) {
                com.salesforce.marketingcloud.g.a(str2, "MagicRegion exited, but was missing location permission.", new Object[0]);
                b();
            } else if (location != null) {
                a(location);
            } else {
                this.f.a((com.salesforce.marketingcloud.location.e) this);
            }
        }
    }

    public void a(Region region) {
        a(2, region);
    }

    /* access modifiers changed from: package-private */
    public void a(LatLon latLon, int i2) {
        if (h.b(this.j)) {
            Region a2 = com.salesforce.marketingcloud.internal.j.a(latLon, i2);
            d(a2);
            this.f.a(c(a2));
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(LatLon latLon, Region region) {
        boolean z2 = true;
        if (region != null) {
            try {
                float[] fArr = {0.0f, 0.0f, 0.0f, 0.0f};
                Location.distanceBetween(latLon.latitude(), latLon.longitude(), region.center().latitude(), region.center().longitude(), fArr);
                if (!d()) {
                    if (fArr[0] <= ((float) region.radius()) * B) {
                        z2 = false;
                    }
                }
            } catch (Exception unused) {
                com.salesforce.marketingcloud.g.b(C, "An error occurred while calculating distance between last known location and the current location.", new Object[0]);
            }
        }
        j jVar = this.d;
        if (jVar != null) {
            jVar.f().edit().remove(x).apply();
        }
        return z2;
    }

    public final void a(Region region, Message message) {
        if (region != null && message != null) {
            com.salesforce.marketingcloud.g.d(C, "showMessage(%s, %s)", region.id(), message.id());
            NotificationMessage a2 = com.salesforce.marketingcloud.internal.h.a(message, region);
            if (a2 != null && b.c(message)) {
                try {
                    b.a(message, this.d);
                    this.k.a(a2, new f(message));
                } catch (Exception e2) {
                    com.salesforce.marketingcloud.g.b(C, e2, "Failed to show message", new Object[0]);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(LatLon latLon) {
        com.salesforce.marketingcloud.messages.geofence.a aVar;
        if (!isGeofenceMessagingEnabled() || (aVar = this.s) == null || latLon == null) {
            com.salesforce.marketingcloud.g.a(C, "Tried to update geofence messages, but was not enabled.", new Object[0]);
        } else {
            aVar.a(latLon, this.i, this.h, (c.b) this);
        }
    }
}
