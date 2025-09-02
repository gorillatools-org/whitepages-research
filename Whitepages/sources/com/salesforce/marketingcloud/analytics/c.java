package com.salesforce.marketingcloud.analytics;

import android.annotation.SuppressLint;
import java.util.List;

@SuppressLint({"UnknownNullness"})
public final class c {
    private c() {
    }

    public static String[] a(String str) {
        return str.split("\\s*,\\s*");
    }

    public static String a(List<b> list) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (b next : list) {
            if (next != null) {
                if (z) {
                    z = false;
                } else {
                    sb.append(',');
                }
                sb.append(next.d());
            }
        }
        return sb.toString();
    }
}
