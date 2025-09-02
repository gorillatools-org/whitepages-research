package com.facebook.react.views.modal;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import kotlin.jvm.internal.Intrinsics;

public final class ModalHostHelper {
    public static final ModalHostHelper INSTANCE = new ModalHostHelper();
    private static final Point MAX_POINT = new Point();
    private static final Point MIN_POINT = new Point();
    private static final Point SIZE_POINT = new Point();

    private ModalHostHelper() {
    }

    public static final Point getModalHostSize(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("window");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        Display defaultDisplay = ((WindowManager) systemService).getDefaultDisplay();
        Point point = MIN_POINT;
        Point point2 = MAX_POINT;
        defaultDisplay.getCurrentSizeRange(point, point2);
        Point point3 = SIZE_POINT;
        defaultDisplay.getSize(point3);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{16843277});
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "obtainStyledAttributes(...)");
        int i = 0;
        boolean z = obtainStyledAttributes.getBoolean(0, false);
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (z && identifier > 0) {
            i = (int) resources.getDimension(identifier);
        }
        if (point3.x < point3.y) {
            return new Point(point.x, point2.y + i);
        }
        return new Point(point2.x, point.y + i);
    }
}
