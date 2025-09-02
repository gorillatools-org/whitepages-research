package com.facebook.react.uimanager;

import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewParent;
import com.facebook.infer.annotation.Assertions;
import kotlin.jvm.internal.Intrinsics;

public final class RootViewUtil {
    public static final RootViewUtil INSTANCE = new RootViewUtil();

    private RootViewUtil() {
    }

    public static final RootView getRootView(View view) {
        Intrinsics.checkNotNullParameter(view, "reactView");
        while (!(view instanceof RootView)) {
            ViewParent parent = view.getParent();
            if (parent == null) {
                return null;
            }
            Assertions.assertCondition(parent instanceof View);
            view = (View) parent;
        }
        return (RootView) view;
    }

    public static final Point getViewportOffset(View view) {
        Intrinsics.checkNotNullParameter(view, "v");
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        Rect rect = new Rect();
        view.getWindowVisibleDisplayFrame(rect);
        iArr[0] = iArr[0] - rect.left;
        iArr[1] = iArr[1] - rect.top;
        return new Point(iArr[0], iArr[1]);
    }
}
