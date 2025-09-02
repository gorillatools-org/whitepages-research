package com.swmansion.rnscreens.ext;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import kotlin.jvm.internal.Intrinsics;

public abstract class ViewExtKt {
    public static final ViewGroup parentAsViewGroup(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            return (ViewGroup) parent;
        }
        return null;
    }

    public static final View recycle(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        ViewGroup parentAsViewGroup = parentAsViewGroup(view);
        if (parentAsViewGroup != null) {
            parentAsViewGroup.endViewTransition(view);
            parentAsViewGroup.removeView(view);
        }
        view.setVisibility(0);
        view.setTranslationY(0.0f);
        return view;
    }
}
