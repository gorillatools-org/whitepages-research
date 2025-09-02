package com.salesforce.marketingcloud.location;

import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.MarketingCloudConfig;
import com.salesforce.marketingcloud.g;
import java.util.List;
import org.json.JSONObject;

final class a extends f {
    private final JSONObject q;
    private final Boolean r;
    private final Exception s;
    private final boolean t;
    private final boolean u;

    a(MarketingCloudConfig marketingCloudConfig, Boolean bool, boolean z, Exception exc) {
        this.t = marketingCloudConfig.geofencingEnabled();
        this.u = marketingCloudConfig.proximityEnabled();
        this.r = bool;
        this.s = exc;
        this.q = f.a(marketingCloudConfig, bool, z, exc);
    }

    /* access modifiers changed from: protected */
    public void a(InitializationStatus.a aVar) {
        if (this.t || this.u) {
            aVar.b(true);
            Exception exc = this.s;
            if (exc != null) {
                String message = exc.getMessage();
                if (message != null) {
                    aVar.a(message);
                }
                Exception exc2 = this.s;
                if (exc2 instanceof g) {
                    aVar.a(((g) exc2).a());
                    return;
                }
                return;
            }
            Boolean bool = this.r;
            if (bool != null && !bool.booleanValue()) {
                aVar.a(f.e);
                return;
            }
            return;
        }
        aVar.b(false);
    }

    public void b() {
        g.e(f.h, "LocationManager unavailable. unmonitorAllGeofences ignored", new Object[0]);
    }

    public JSONObject componentState() {
        return this.q;
    }

    public void a(b... bVarArr) {
        g.e(f.h, "LocationManager unavailable. monitorGeofences ignored", new Object[0]);
    }

    public void b(c cVar) {
        g.e(f.h, "LocationManager unavailable. unregisterForGeofenceRegionEvents ignored", new Object[0]);
    }

    public void a(c cVar) {
        g.e(f.h, "LocationManager unavailable. registerForGeofenceRegionEvents ignored", new Object[0]);
    }

    public void b(e eVar) {
        g.e(f.h, "LocationManager unavailable. unregisterForLocationUpdate ignored", new Object[0]);
    }

    public void a(e eVar) {
        g.e(f.h, "LocationManager unavailable. registerForLocationUpdate ignored", new Object[0]);
    }

    public void a(List<String> list) {
        g.e(f.h, "LocationManager unavailable. unmonitorGeofences ignored", new Object[0]);
    }
}
