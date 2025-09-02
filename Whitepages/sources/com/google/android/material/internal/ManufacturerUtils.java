package com.google.android.material.internal;

import android.os.Build;
import java.util.Locale;

public abstract class ManufacturerUtils {
    public static boolean isMeizuDevice() {
        return Build.MANUFACTURER.toLowerCase(Locale.ENGLISH).equals("meizu");
    }
}
