package com.salesforce.marketingcloud.sfmcsdk.components.utils;

import java.util.Arrays;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

public abstract class NamedRunnable implements Runnable {
    private final String name;

    /* access modifiers changed from: protected */
    public abstract void execute();

    public NamedRunnable(String str, Object... objArr) {
        Intrinsics.checkNotNullParameter(str, "format");
        Intrinsics.checkNotNullParameter(objArr, "args");
        StringBuilder sb = new StringBuilder();
        sb.append("sdk_");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.US;
        Object[] copyOf = Arrays.copyOf(objArr, objArr.length);
        String format = String.format(locale, str, Arrays.copyOf(copyOf, copyOf.length));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        sb.append(format);
        this.name = sb.toString();
    }

    public final String getName() {
        return this.name;
    }

    public void run() {
        String name2 = Thread.currentThread().getName();
        Thread.currentThread().setName(this.name);
        try {
            execute();
        } finally {
            Thread.currentThread().setName(name2);
        }
    }
}
