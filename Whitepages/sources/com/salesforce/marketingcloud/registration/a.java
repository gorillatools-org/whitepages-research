package com.salesforce.marketingcloud.registration;

import com.salesforce.marketingcloud.internal.g;
import com.salesforce.marketingcloud.storage.m;
import com.salesforce.marketingcloud.util.c;

public class a extends g {
    private final m b;
    private final c c;
    private final Registration d;
    private final boolean e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    a(m mVar, c cVar, Registration registration, boolean z) {
        super(z ? "update_registration" : "add_registration", new Object[0]);
        this.b = mVar;
        this.c = cVar;
        this.d = registration;
        this.e = z;
    }

    /* access modifiers changed from: protected */
    public void a() {
        try {
            if (this.e) {
                this.b.b(this.d, this.c);
            } else {
                this.b.a(this.d, this.c);
            }
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(RegistrationManager.a, e2, "Unable to %s registration", this.e ? "update" : "add");
        }
    }
}
