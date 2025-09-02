package com.salesforce.marketingcloud.events.predicates;

import android.annotation.SuppressLint;
import com.salesforce.marketingcloud.events.g;
import com.salesforce.marketingcloud.g;

@SuppressLint({"UnknownNullness"})
public abstract class h<T> extends f {
    private static final String i = g.a("ValuePredicate");
    private final Object e;
    private final g.a f;
    private final Object g;
    private final String h;

    h(Object obj, g.a aVar, Object obj2) {
        this.e = obj;
        this.f = aVar;
        this.g = obj2;
        this.h = String.format("%s %s %s", new Object[]{obj, aVar, obj2});
    }

    /* access modifiers changed from: protected */
    public abstract T a(Object obj);

    /* access modifiers changed from: protected */
    public final boolean a() {
        try {
            return a(a(this.e), this.f, a(this.g));
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(i, e2, "Unable to evaluate predicate.  Returning default value of 'false'", new Object[0]);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public abstract boolean a(T t, g.a aVar, T t2) throws UnsupportedOperationException;

    /* access modifiers changed from: protected */
    public String c() {
        return this.h;
    }
}
