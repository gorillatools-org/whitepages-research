package com.salesforce.marketingcloud.proximity;

import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.proximity.e;
import java.util.List;
import org.json.JSONObject;

class d extends e {
    private final boolean i;
    private final JSONObject j;

    public d(boolean z, JSONObject jSONObject) {
        this.i = z;
        this.j = jSONObject;
    }

    /* access modifiers changed from: protected */
    public void a(InitializationStatus.a aVar) {
        aVar.d(this.i);
    }

    public void b(List<c> list) {
        g.d(e.h, "unmonitorBeaconRegions call ignored because of unsupported device.", new Object[0]);
    }

    public void c() {
        g.d(e.h, "stopMonitoringBeaconRegions() call ignored because of unsupported device.", new Object[0]);
    }

    public JSONObject componentState() {
        return this.j;
    }

    public void a(List<c> list) {
        g.d(e.h, "monitorBeaconRegions call ignored because of unsupported device.", new Object[0]);
    }

    public void b(e.a aVar) {
        g.d(e.h, "unregisterProximityEventListener(%s) call ignored because of unsupported device.", aVar != null ? aVar.getClass().getSimpleName() : "null");
    }

    public void a(e.a aVar) {
        g.d(e.h, "registerProximityEventListener(%s) call ignored because of unsupported device.", aVar != null ? aVar.getClass().getSimpleName() : "null");
    }
}
