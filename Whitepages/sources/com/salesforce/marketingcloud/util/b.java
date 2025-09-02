package com.salesforce.marketingcloud.util;

import android.os.Build;

public final class b {
    private static final String a = "Amazon";

    private b() {
    }

    public static boolean a() {
        return Build.MANUFACTURER.equalsIgnoreCase(a);
    }
}
