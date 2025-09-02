package com.salesforce.marketingcloud.proximity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.collection.ArraySet;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.proximity.e;
import com.salesforce.marketingcloud.util.h;
import com.salesforce.marketingcloud.util.j;
import java.util.List;
import java.util.Set;
import org.altbeacon.beacon.service.BeaconService;
import org.json.JSONException;
import org.json.JSONObject;

class b extends e {
    private final Context i;
    private final Set<e.a> j;
    private final a k;
    private BroadcastReceiver l;
    private int m;

    /* renamed from: n  reason: collision with root package name */
    private int f38n;

    class a extends BroadcastReceiver {
        a() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                g.d(e.h, "Received null intent.", new Object[0]);
                return;
            }
            String action = intent.getAction();
            if (action == null) {
                g.d(e.h, "Received null action", new Object[0]);
            } else if (action.equals(e.d)) {
                b.this.a((c) intent.getParcelableExtra(e.f));
            } else if (!action.equals(e.e)) {
                g.a(e.h, "Received unknown action: ", action);
            } else {
                b.this.b((c) intent.getParcelableExtra(e.f));
            }
        }
    }

    public b(Context context) {
        this(context, (ProximityNotificationCustomizationOptions) null);
    }

    /* access modifiers changed from: package-private */
    public void a(c cVar) {
        synchronized (this.j) {
            try {
                this.m++;
                if (cVar != null && !this.j.isEmpty()) {
                    g.c(e.h, "Entered %s", cVar);
                    for (e.a next : this.j) {
                        if (next != null) {
                            next.b(cVar);
                        }
                    }
                }
            } finally {
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void b(c cVar) {
        synchronized (this.j) {
            try {
                this.f38n++;
                if (cVar != null && !this.j.isEmpty()) {
                    g.c(e.h, "Exited %s", cVar);
                    for (e.a next : this.j) {
                        if (next != null) {
                            next.a(cVar);
                        }
                    }
                }
            } finally {
            }
        }
    }

    public boolean b() {
        return true;
    }

    public void c() {
        a aVar = this.k;
        if (aVar != null) {
            aVar.d();
        }
    }

    public JSONObject componentState() {
        JSONObject jSONObject;
        try {
            jSONObject = e.a();
            try {
                jSONObject.put("enteredEvents", this.m);
                jSONObject.put("exitedEvents", this.f38n);
            } catch (JSONException e) {
                e = e;
            }
        } catch (JSONException e2) {
            e = e2;
            jSONObject = null;
            g.b(e.h, e, "Failed to create component state.", new Object[0]);
            return jSONObject;
        }
        return jSONObject;
    }

    public void tearDown(boolean z) {
        c();
        Context context = this.i;
        if (context != null && this.l != null) {
            LocalBroadcastManager.getInstance(context).unregisterReceiver(this.l);
        }
    }

    public b(Context context, ProximityNotificationCustomizationOptions proximityNotificationCustomizationOptions) throws IllegalStateException {
        this.j = new ArraySet();
        j.a(context, "Context is null");
        this.i = context;
        if (h.b(context.getPackageManager(), new Intent(context, BeaconService.class))) {
            this.k = new a(context, proximityNotificationCustomizationOptions);
            return;
        }
        throw new IllegalStateException("AltBeacon service not found");
    }

    /* access modifiers changed from: protected */
    public void a(InitializationStatus.a aVar) {
        aVar.d(false);
        this.l = new a();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(e.d);
        intentFilter.addAction(e.e);
        LocalBroadcastManager.getInstance(this.i).registerReceiver(this.l, intentFilter);
    }

    public void a(List<c> list) {
        if (list != null) {
            g.c(e.h, "monitorBeaconRegions(%d region)", Integer.valueOf(list.size()));
            this.k.a(list);
        }
    }

    public void b(List<c> list) {
        if (list != null) {
            g.c(e.h, "unmonitorBeaconRegions(%d region)", Integer.valueOf(list.size()));
            this.k.b(list);
        }
    }

    public void a(e.a aVar) {
        synchronized (this.j) {
            if (aVar != null) {
                try {
                    this.j.add(aVar);
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public void b(e.a aVar) {
        synchronized (this.j) {
            this.j.remove(aVar);
        }
    }
}
