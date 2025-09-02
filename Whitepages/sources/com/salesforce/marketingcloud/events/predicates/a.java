package com.salesforce.marketingcloud.events.predicates;

public class a extends f {
    private final f[] e;

    public a(f... fVarArr) {
        this.e = fVarArr;
    }

    /* access modifiers changed from: protected */
    public boolean a() {
        for (f b : this.e) {
            if (!b.b()) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public String c() {
        return "And";
    }
}
