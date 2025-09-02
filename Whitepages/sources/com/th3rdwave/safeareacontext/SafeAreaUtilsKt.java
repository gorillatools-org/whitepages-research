package com.th3rdwave.safeareacontext;

import android.graphics.Insets;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import kotlin.jvm.internal.Intrinsics;

public abstract class SafeAreaUtilsKt {
    private static final EdgeInsets getRootWindowInsetsCompatR(View view) {
        Insets m;
        WindowInsets rootWindowInsets = view.getRootWindowInsets();
        if (rootWindowInsets == null || (m = rootWindowInsets.getInsets(WindowInsets.Type.statusBars() | WindowInsets.Type.displayCutout() | WindowInsets.Type.navigationBars() | WindowInsets.Type.captionBar())) == null) {
            return null;
        }
        return new EdgeInsets((float) m.top, (float) m.right, (float) m.bottom, (float) m.left);
    }

    private static final EdgeInsets getRootWindowInsetsCompatM(View view) {
        WindowInsets rootWindowInsets = view.getRootWindowInsets();
        if (rootWindowInsets == null) {
            return null;
        }
        return new EdgeInsets((float) rootWindowInsets.getSystemWindowInsetTop(), (float) rootWindowInsets.getSystemWindowInsetRight(), (float) Math.min(rootWindowInsets.getSystemWindowInsetBottom(), rootWindowInsets.getStableInsetBottom()), (float) rootWindowInsets.getSystemWindowInsetLeft());
    }

    private static final EdgeInsets getRootWindowInsetsCompat(View view) {
        if (Build.VERSION.SDK_INT >= 30) {
            return getRootWindowInsetsCompatR(view);
        }
        return getRootWindowInsetsCompatM(view);
    }

    public static final EdgeInsets getSafeAreaInsets(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (view.getHeight() == 0) {
            return null;
        }
        View rootView = view.getRootView();
        Intrinsics.checkNotNull(rootView);
        EdgeInsets rootWindowInsetsCompat = getRootWindowInsetsCompat(rootView);
        if (rootWindowInsetsCompat == null) {
            return null;
        }
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        return new EdgeInsets(Math.max(rootWindowInsetsCompat.getTop() - ((float) rect.top), 0.0f), Math.max(Math.min(((float) (rect.left + view.getWidth())) - ((float) rootView.getWidth()), 0.0f) + rootWindowInsetsCompat.getRight(), 0.0f), Math.max(Math.min(((float) (rect.top + view.getHeight())) - ((float) rootView.getHeight()), 0.0f) + rootWindowInsetsCompat.getBottom(), 0.0f), Math.max(rootWindowInsetsCompat.getLeft() - ((float) rect.left), 0.0f));
    }

    public static final Rect getFrame(ViewGroup viewGroup, View view) {
        Intrinsics.checkNotNullParameter(viewGroup, "rootView");
        Intrinsics.checkNotNullParameter(view, "view");
        if (view.getParent() == null) {
            return null;
        }
        Rect rect = new Rect();
        view.getDrawingRect(rect);
        try {
            viewGroup.offsetDescendantRectToMyCoords(view, rect);
            return new Rect((float) rect.left, (float) rect.top, (float) view.getWidth(), (float) view.getHeight());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }
}
