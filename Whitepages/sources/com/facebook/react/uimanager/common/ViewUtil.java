package com.facebook.react.uimanager.common;

import android.view.View;
import kotlin.jvm.internal.Intrinsics;

public final class ViewUtil {
    public static final ViewUtil INSTANCE = new ViewUtil();
    public static final int NO_SURFACE_ID = -1;

    private ViewUtil() {
    }

    public static final int getUIManagerType(int i) {
        return i % 2 == 0 ? 2 : 1;
    }

    public static final int getUIManagerType(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        return getUIManagerType(view.getId());
    }

    public static final int getUIManagerType(int i, int i2) {
        int i3 = i2 == -1 ? 1 : 2;
        if (i3 == 1 && !isRootTag(i) && i % 2 == 0) {
            return 2;
        }
        return i3;
    }

    public static final boolean isRootTag(int i) {
        return i % 10 == 1;
    }
}
