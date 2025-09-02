package com.salesforce.marketingcloud.location;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.location.Location;
import androidx.collection.ArraySet;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.MarketingCloudConfig;
import com.salesforce.marketingcloud.g;
import java.util.List;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

class h extends f {
    final d q;
    final Set<e> r = new ArraySet();
    private final Set<c> s = new ArraySet();
    private MarketingCloudConfig t;
    private int u;
    private int v;
    private String w;
    private int x;
    private Context y;
    private BroadcastReceiver z;

    class a extends BroadcastReceiver {
        a() {
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onReceive(android.content.Context r5, android.content.Intent r6) {
            /*
                r4 = this;
                r5 = -1
                r0 = 0
                if (r6 != 0) goto L_0x000e
                java.lang.String r5 = com.salesforce.marketingcloud.location.f.h
                java.lang.Object[] r6 = new java.lang.Object[r0]
                java.lang.String r0 = "Received null intent"
                com.salesforce.marketingcloud.g.d((java.lang.String) r5, (java.lang.String) r0, (java.lang.Object[]) r6)
                return
            L_0x000e:
                java.lang.String r1 = r6.getAction()
                if (r1 != 0) goto L_0x001e
                java.lang.String r5 = com.salesforce.marketingcloud.location.f.h
                java.lang.Object[] r6 = new java.lang.Object[r0]
                java.lang.String r0 = "Received null action"
                com.salesforce.marketingcloud.g.d((java.lang.String) r5, (java.lang.String) r0, (java.lang.Object[]) r6)
                return
            L_0x001e:
                java.lang.String r2 = "extra_location"
                int r3 = r1.hashCode()
                switch(r3) {
                    case -284548713: goto L_0x003f;
                    case 557677285: goto L_0x0034;
                    case 557783927: goto L_0x0029;
                    default: goto L_0x0027;
                }
            L_0x0027:
                r3 = r5
                goto L_0x0049
            L_0x0029:
                java.lang.String r3 = "com.salesforce.marketingcloud.location.GEOFENCE_EVENT"
                boolean r3 = r1.equals(r3)
                if (r3 != 0) goto L_0x0032
                goto L_0x0027
            L_0x0032:
                r3 = 2
                goto L_0x0049
            L_0x0034:
                java.lang.String r3 = "com.salesforce.marketingcloud.location.GEOFENCE_ERROR"
                boolean r3 = r1.equals(r3)
                if (r3 != 0) goto L_0x003d
                goto L_0x0027
            L_0x003d:
                r3 = 1
                goto L_0x0049
            L_0x003f:
                java.lang.String r3 = "com.salesforce.marketingcloud.location.LOCATION_UPDATE"
                boolean r3 = r1.equals(r3)
                if (r3 != 0) goto L_0x0048
                goto L_0x0027
            L_0x0048:
                r3 = r0
            L_0x0049:
                switch(r3) {
                    case 0: goto L_0x0098;
                    case 1: goto L_0x0082;
                    case 2: goto L_0x0058;
                    default: goto L_0x004c;
                }
            L_0x004c:
                java.lang.String r5 = com.salesforce.marketingcloud.location.f.h
                java.lang.Object[] r6 = new java.lang.Object[]{r1}
                java.lang.String r0 = "Received unknown action: %s"
                com.salesforce.marketingcloud.g.a((java.lang.String) r5, (java.lang.String) r0, (java.lang.Object[]) r6)
                goto L_0x00ac
            L_0x0058:
                java.lang.String r0 = "extra_transition"
                int r0 = r6.getIntExtra(r0, r5)
                if (r0 != r5) goto L_0x0061
                return
            L_0x0061:
                java.lang.String r5 = com.salesforce.marketingcloud.location.f.h
                java.lang.Integer r1 = java.lang.Integer.valueOf(r0)
                java.lang.Object[] r1 = new java.lang.Object[]{r1}
                java.lang.String r3 = "Received geofence transition %d"
                com.salesforce.marketingcloud.g.a((java.lang.String) r5, (java.lang.String) r3, (java.lang.Object[]) r1)
                com.salesforce.marketingcloud.location.h r5 = com.salesforce.marketingcloud.location.h.this
                java.lang.String r1 = "extra_fence_ids"
                java.util.ArrayList r1 = r6.getStringArrayListExtra(r1)
                android.os.Parcelable r6 = r6.getParcelableExtra(r2)
                android.location.Location r6 = (android.location.Location) r6
                r5.b(r0, r1, r6)
                goto L_0x00ac
            L_0x0082:
                java.lang.String r0 = "extra_error_code"
                int r0 = r6.getIntExtra(r0, r5)
                java.lang.String r1 = "extra_error_message"
                java.lang.String r6 = r6.getStringExtra(r1)
                if (r0 == r5) goto L_0x00ac
                if (r6 == 0) goto L_0x00ac
                com.salesforce.marketingcloud.location.h r5 = com.salesforce.marketingcloud.location.h.this
                r5.b(r0, r6)
                goto L_0x00ac
            L_0x0098:
                java.lang.String r5 = com.salesforce.marketingcloud.location.f.h
                java.lang.Object[] r0 = new java.lang.Object[r0]
                java.lang.String r1 = "Received location update."
                com.salesforce.marketingcloud.g.a((java.lang.String) r5, (java.lang.String) r1, (java.lang.Object[]) r0)
                com.salesforce.marketingcloud.location.h r5 = com.salesforce.marketingcloud.location.h.this
                android.os.Parcelable r6 = r6.getParcelableExtra(r2)
                android.location.Location r6 = (android.location.Location) r6
                r5.b((android.location.Location) r6)
            L_0x00ac:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.location.h.a.onReceive(android.content.Context, android.content.Intent):void");
        }
    }

    h(Context context, MarketingCloudConfig marketingCloudConfig) throws IllegalStateException {
        this.y = context;
        this.q = new d(context);
        this.t = marketingCloudConfig;
    }

    /* access modifiers changed from: protected */
    public void a(InitializationStatus.a aVar) {
        this.z = new a();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.salesforce.marketingcloud.location.LOCATION_UPDATE");
        intentFilter.addAction("com.salesforce.marketingcloud.location.GEOFENCE_EVENT");
        intentFilter.addAction("com.salesforce.marketingcloud.location.GEOFENCE_ERROR");
        LocalBroadcastManager.getInstance(this.y).registerReceiver(this.z, intentFilter);
        aVar.a(this.q.c());
        aVar.a(this.q.b());
        aVar.b(!this.q.d());
    }

    /* access modifiers changed from: package-private */
    public void b(int i, String str) {
        synchronized (this.s) {
            try {
                if (!this.s.isEmpty()) {
                    for (c next : this.s) {
                        if (next != null) {
                            next.a(i, str);
                        }
                    }
                }
            } finally {
            }
        }
    }

    public JSONObject componentState() {
        JSONObject a2 = f.a(this.t, this.q.c(), this.q.b());
        try {
            a2.put("locationRequests", this.u);
            a2.put("locationsReceived", this.v);
            a2.put("lastLocationRequester", this.w);
            a2.put("geofenceEvents", this.x);
        } catch (JSONException e) {
            g.b(f.h, e, "Error creating state for RealLocationManager.", new Object[0]);
        }
        return a2;
    }

    public void tearDown(boolean z2) {
        d dVar = this.q;
        if (dVar != null && z2) {
            dVar.a();
        }
        Context context = this.y;
        if (context != null && this.z != null) {
            LocalBroadcastManager.getInstance(context).unregisterReceiver(this.z);
        }
    }

    h(Context context, d dVar) {
        this.y = context;
        this.q = dVar;
    }

    public boolean a() {
        return this.q.d();
    }

    /* access modifiers changed from: package-private */
    public void b(int i, List<String> list, Location location) {
        String str = f.h;
        g.d(str, "onGeofenceRegionEvent", new Object[0]);
        if (list == null || list.isEmpty()) {
            g.c(str, "No fenceIds were provided.", new Object[0]);
            return;
        }
        this.x++;
        synchronized (this.s) {
            try {
                if (!this.s.isEmpty()) {
                    for (c next : this.s) {
                        if (next != null) {
                            for (String next2 : list) {
                                g.a(f.h, "Notifiying %s of geofence [%s] region event [d]", next.getClass().getName(), next2, Integer.valueOf(i));
                                next.a(next2, i, location);
                            }
                        }
                    }
                } else {
                    g.c(str, "Geofence region event occured with no one listening.", new Object[0]);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void a(b... bVarArr) {
        if (bVarArr == null || bVarArr.length == 0) {
            g.a(f.h, "monitorGeofences - No geofenceRegions provided.", new Object[0]);
            return;
        }
        g.d(f.h, "Monitoring %s fence(s).", Integer.valueOf(bVarArr.length));
        this.q.a(bVarArr);
    }

    /* access modifiers changed from: package-private */
    public void b(Location location) {
        if (location != null) {
            this.v++;
            synchronized (this.r) {
                try {
                    if (!this.r.isEmpty()) {
                        for (e next : this.r) {
                            if (next != null) {
                                next.a(location);
                            }
                        }
                        this.r.clear();
                    }
                } finally {
                }
            }
        }
    }

    public void a(c cVar) {
        g.d(f.h, "registerForGeofenceRegionEvents(%s)", cVar.getClass().getName());
        synchronized (this.s) {
            this.s.add(cVar);
        }
    }

    public void b() {
        this.q.a();
    }

    @SuppressLint({"MissingPermission"})
    public void a(e eVar) {
        boolean z2;
        if (eVar != null) {
            synchronized (this.r) {
                try {
                    z2 = this.r.add(eVar) && this.r.size() == 1;
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
            if (z2) {
                this.u++;
                this.w = eVar.getClass().getName();
                this.q.e();
            }
        }
    }

    public void b(c cVar) {
        if (cVar != null) {
            synchronized (this.s) {
                this.s.remove(cVar);
            }
        }
    }

    public void a(List<String> list) {
        if (list == null || list.size() == 0) {
            g.c(f.h, "unmonitorGeofences - No geofenceRegionIds provided.", new Object[0]);
        } else {
            this.q.a(list);
        }
    }

    public void b(e eVar) {
        synchronized (this.r) {
            this.r.remove(eVar);
        }
    }
}
