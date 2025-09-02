package com.salesforce.marketingcloud.analytics;

import com.salesforce.marketingcloud.internal.g;
import com.salesforce.marketingcloud.storage.a;

public class d extends g {
    private final a b;
    private final String[] c;

    public d(a aVar, String[] strArr) {
        super("delete_analytics", new Object[0]);
        this.b = aVar;
        this.c = strArr;
    }

    /* access modifiers changed from: protected */
    public void a() {
        this.b.a(this.c);
    }
}
