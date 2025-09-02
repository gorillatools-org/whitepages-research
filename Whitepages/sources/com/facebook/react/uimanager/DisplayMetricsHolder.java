package com.facebook.react.uimanager;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import kotlin.jvm.internal.Intrinsics;

public final class DisplayMetricsHolder {
    private static final String INITIALIZATION_MISSING_MESSAGE = "DisplayMetricsHolder must be initialized with initDisplayMetricsIfNotInitialized or initDisplayMetrics";
    public static final DisplayMetricsHolder INSTANCE = new DisplayMetricsHolder();
    private static DisplayMetrics screenDisplayMetrics;
    private static DisplayMetrics windowDisplayMetrics;

    private static /* synthetic */ void getScreenDisplayMetrics$annotations() {
    }

    private static /* synthetic */ void getWindowDisplayMetrics$annotations() {
    }

    private DisplayMetricsHolder() {
    }

    public static final DisplayMetrics getWindowDisplayMetrics() {
        DisplayMetrics displayMetrics = windowDisplayMetrics;
        if (displayMetrics != null) {
            Intrinsics.checkNotNull(displayMetrics, "null cannot be cast to non-null type android.util.DisplayMetrics");
            return displayMetrics;
        }
        throw new IllegalStateException(INITIALIZATION_MISSING_MESSAGE);
    }

    public static final void setWindowDisplayMetrics(DisplayMetrics displayMetrics) {
        windowDisplayMetrics = displayMetrics;
    }

    public static final DisplayMetrics getScreenDisplayMetrics() {
        DisplayMetrics displayMetrics = screenDisplayMetrics;
        if (displayMetrics != null) {
            Intrinsics.checkNotNull(displayMetrics, "null cannot be cast to non-null type android.util.DisplayMetrics");
            return displayMetrics;
        }
        throw new IllegalStateException(INITIALIZATION_MISSING_MESSAGE);
    }

    public static final void setScreenDisplayMetrics(DisplayMetrics displayMetrics) {
        screenDisplayMetrics = displayMetrics;
    }

    public static final void initDisplayMetricsIfNotInitialized(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (screenDisplayMetrics == null) {
            initDisplayMetrics(context);
        }
    }

    public static final void initDisplayMetrics(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        windowDisplayMetrics = displayMetrics;
        DisplayMetrics displayMetrics2 = new DisplayMetrics();
        displayMetrics2.setTo(displayMetrics);
        Object systemService = context.getSystemService("window");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        ((WindowManager) systemService).getDefaultDisplay().getRealMetrics(displayMetrics2);
        screenDisplayMetrics = displayMetrics2;
    }

    public static final WritableMap getDisplayMetricsWritableMap(double d) {
        if (windowDisplayMetrics == null) {
            throw new IllegalStateException(INITIALIZATION_MISSING_MESSAGE);
        } else if (screenDisplayMetrics != null) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            DisplayMetricsHolder displayMetricsHolder = INSTANCE;
            DisplayMetrics displayMetrics = windowDisplayMetrics;
            Intrinsics.checkNotNull(displayMetrics, "null cannot be cast to non-null type android.util.DisplayMetrics");
            writableNativeMap.putMap("windowPhysicalPixels", displayMetricsHolder.getPhysicalPixelsWritableMap(displayMetrics, d));
            DisplayMetrics displayMetrics2 = screenDisplayMetrics;
            Intrinsics.checkNotNull(displayMetrics2, "null cannot be cast to non-null type android.util.DisplayMetrics");
            writableNativeMap.putMap("screenPhysicalPixels", displayMetricsHolder.getPhysicalPixelsWritableMap(displayMetrics2, d));
            return writableNativeMap;
        } else {
            throw new IllegalStateException(INITIALIZATION_MISSING_MESSAGE);
        }
    }

    private final WritableMap getPhysicalPixelsWritableMap(DisplayMetrics displayMetrics, double d) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putInt("width", displayMetrics.widthPixels);
        writableNativeMap.putInt("height", displayMetrics.heightPixels);
        writableNativeMap.putDouble("scale", (double) displayMetrics.density);
        writableNativeMap.putDouble("fontScale", d);
        writableNativeMap.putDouble("densityDpi", (double) displayMetrics.densityDpi);
        return writableNativeMap;
    }
}
