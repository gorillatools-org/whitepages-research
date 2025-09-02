package com.salesforce.marketingcloud.internal;

import java.util.Locale;

public abstract class g implements Runnable {
    public final String a;

    public g(String str, Object... objArr) {
        this.a = "mcsdk_" + String.format(Locale.US, str, objArr);
    }

    /* access modifiers changed from: protected */
    public abstract void a();

    public final void run() {
        String name = Thread.currentThread().getName();
        Thread.currentThread().setName(this.a);
        try {
            a();
        } finally {
            Thread.currentThread().setName(name);
        }
    }
}
