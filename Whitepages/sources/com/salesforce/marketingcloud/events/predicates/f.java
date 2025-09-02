package com.salesforce.marketingcloud.events.predicates;

import com.facebook.hermes.intl.Constants;
import com.salesforce.marketingcloud.g;

public abstract class f {
    public static final f b = new a();
    public static final f c = new b();
    private static final String d = g.a("Predicate");
    private Boolean a;

    class a extends f {
        a() {
        }

        /* access modifiers changed from: protected */
        public boolean a() {
            return true;
        }

        /* access modifiers changed from: protected */
        public String c() {
            return "true";
        }
    }

    class b extends f {
        b() {
        }

        /* access modifiers changed from: protected */
        public boolean a() {
            return false;
        }

        /* access modifiers changed from: protected */
        public String c() {
            return Constants.CASEFIRST_FALSE;
        }
    }

    /* access modifiers changed from: protected */
    public abstract boolean a();

    public final boolean b() {
        if (this.a == null) {
            this.a = Boolean.valueOf(a());
        }
        g.d(d, "%s %s", c(), this.a.booleanValue() ? "passed" : "failed");
        return this.a.booleanValue();
    }

    /* access modifiers changed from: protected */
    public abstract String c();
}
