package com.salesforce.marketingcloud.analytics.stats;

import com.salesforce.marketingcloud.analytics.AnalyticsManager;
import com.salesforce.marketingcloud.internal.g;
import com.salesforce.marketingcloud.storage.d;
import com.salesforce.marketingcloud.util.c;

public class a extends g {
    private final d b;
    private final c c;
    private final b d;

    public a(d dVar, c cVar, b bVar) {
        super("add_device_stat", new Object[0]);
        this.b = dVar;
        this.c = cVar;
        this.d = bVar;
    }

    /* access modifiers changed from: protected */
    public void a() {
        try {
            this.b.a(this.d, this.c);
        } catch (Exception e) {
            com.salesforce.marketingcloud.g.b(AnalyticsManager.TAG, e, "Unable to record device stat [%d].", Integer.valueOf(this.d.d()));
        }
    }
}
