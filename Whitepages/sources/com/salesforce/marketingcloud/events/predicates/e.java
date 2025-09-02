package com.salesforce.marketingcloud.events.predicates;

import kotlin.jvm.internal.Intrinsics;

public final class e extends f {
    private f[] e;

    public e(f... fVarArr) {
        Intrinsics.checkNotNullParameter(fVarArr, "predicates");
        this.e = fVarArr;
    }

    /* access modifiers changed from: protected */
    public boolean a() {
        for (f b : this.e) {
            if (b.b()) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public String c() {
        return "Or";
    }
}
