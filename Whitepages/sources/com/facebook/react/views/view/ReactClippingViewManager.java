package com.facebook.react.views.view;

import android.view.View;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.view.ReactViewGroup;
import kotlin.jvm.internal.Intrinsics;

public abstract class ReactClippingViewManager<T extends ReactViewGroup> extends ViewGroupManager<T> {
    @ReactProp(name = "removeClippedSubviews")
    public void setRemoveClippedSubviews(T t, boolean z) {
        Intrinsics.checkNotNullParameter(t, "view");
        UiThreadUtil.assertOnUiThread();
        t.setRemoveClippedSubviews(z);
    }

    public void addView(T t, View view, int i) {
        Intrinsics.checkNotNullParameter(t, "parent");
        Intrinsics.checkNotNullParameter(view, "child");
        UiThreadUtil.assertOnUiThread();
        if (t.getRemoveClippedSubviews()) {
            t.addViewWithSubviewClippingEnabled(view, i);
        } else {
            t.addView(view, i);
        }
    }

    public int getChildCount(T t) {
        Intrinsics.checkNotNullParameter(t, "parent");
        if (t.getRemoveClippedSubviews()) {
            return t.getAllChildrenCount();
        }
        return t.getChildCount();
    }

    public View getChildAt(T t, int i) {
        Intrinsics.checkNotNullParameter(t, "parent");
        if (t.getRemoveClippedSubviews()) {
            return t.getChildAtWithSubviewClippingEnabled(i);
        }
        return t.getChildAt(i);
    }

    public void removeViewAt(T t, int i) {
        Intrinsics.checkNotNullParameter(t, "parent");
        UiThreadUtil.assertOnUiThread();
        if (t.getRemoveClippedSubviews()) {
            View childAt = getChildAt(t, i);
            if (childAt != null) {
                t.removeViewWithSubviewClippingEnabled(childAt);
                return;
            }
            return;
        }
        t.removeViewAt(i);
    }

    public void removeAllViews(T t) {
        Intrinsics.checkNotNullParameter(t, "parent");
        UiThreadUtil.assertOnUiThread();
        if (t.getRemoveClippedSubviews()) {
            t.removeAllViewsWithSubviewClippingEnabled();
        } else {
            t.removeAllViews();
        }
    }
}
