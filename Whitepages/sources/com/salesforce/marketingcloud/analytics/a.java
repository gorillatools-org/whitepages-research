package com.salesforce.marketingcloud.analytics;

import com.salesforce.marketingcloud.internal.g;
import com.salesforce.marketingcloud.util.c;

public class a extends g {
    private final com.salesforce.marketingcloud.storage.a b;
    private final c c;
    private final b d;

    public a(com.salesforce.marketingcloud.storage.a aVar, c cVar, b bVar) {
        super("add_analytic", new Object[0]);
        this.b = aVar;
        this.c = cVar;
        this.d = bVar;
    }

    /* access modifiers changed from: protected */
    public void a() {
        try {
            this.b.a(this.d, this.c);
        } catch (Exception e) {
            com.salesforce.marketingcloud.g.b(AnalyticsManager.TAG, e, "Unable to record analytic [%d].", Integer.valueOf(this.d.a()));
        }
    }
}
