package com.facebook.react.uimanager;

import android.view.View;
import com.facebook.react.bridge.UiThreadUtil;
import kotlin.jvm.internal.Intrinsics;

public interface IViewGroupManager<T extends View> extends IViewManagerWithChildren {
    void addView(T t, View view, int i);

    View getChildAt(T t, int i);

    int getChildCount(T t);

    void removeViewAt(T t, int i);

    void removeAllViews(T t) {
        Intrinsics.checkNotNullParameter(t, "parent");
        UiThreadUtil.assertOnUiThread();
        int childCount = getChildCount(t);
        while (true) {
            childCount--;
            if (-1 < childCount) {
                removeViewAt(t, childCount);
            } else {
                return;
            }
        }
    }
}
