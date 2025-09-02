package com.facebook.react.uimanager.util;

import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.R;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;

public final class ReactFindViewUtil {
    public static final ReactFindViewUtil INSTANCE = new ReactFindViewUtil();
    private static final Map<OnMultipleViewsFoundListener, Set<String>> onMultipleViewsFoundListener = new HashMap();
    private static final List<OnViewFoundListener> onViewFoundListeners = new ArrayList();

    public interface OnMultipleViewsFoundListener {
        void onViewFound(View view, String str);
    }

    public interface OnViewFoundListener {
        String getNativeId();

        void onViewFound(View view);
    }

    private ReactFindViewUtil() {
    }

    public static final View findView(View view, String str) {
        Intrinsics.checkNotNullParameter(view, "root");
        Intrinsics.checkNotNullParameter(str, "nativeId");
        if (Intrinsics.areEqual((Object) INSTANCE.getNativeId(view), (Object) str)) {
            return view;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            Intrinsics.checkNotNullExpressionValue(childAt, "getChildAt(...)");
            View findView = findView(childAt, str);
            if (findView != null) {
                return findView;
            }
        }
        return null;
    }

    public static final void findView(View view, OnViewFoundListener onViewFoundListener) {
        Intrinsics.checkNotNullParameter(view, "root");
        Intrinsics.checkNotNullParameter(onViewFoundListener, "onViewFoundListener");
        View findView = findView(view, onViewFoundListener.getNativeId());
        if (findView != null) {
            onViewFoundListener.onViewFound(findView);
        }
        addViewListener(onViewFoundListener);
    }

    public static final void addViewListener(OnViewFoundListener onViewFoundListener) {
        Intrinsics.checkNotNullParameter(onViewFoundListener, "onViewFoundListener");
        onViewFoundListeners.add(onViewFoundListener);
    }

    public static final void removeViewListener(OnViewFoundListener onViewFoundListener) {
        Intrinsics.checkNotNullParameter(onViewFoundListener, "onViewFoundListener");
        onViewFoundListeners.remove(onViewFoundListener);
    }

    public static final void addViewsListener(OnMultipleViewsFoundListener onMultipleViewsFoundListener2, Set<String> set) {
        Intrinsics.checkNotNullParameter(onMultipleViewsFoundListener2, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Intrinsics.checkNotNullParameter(set, "ids");
        onMultipleViewsFoundListener.put(onMultipleViewsFoundListener2, set);
    }

    public static final void removeViewsListener(OnMultipleViewsFoundListener onMultipleViewsFoundListener2) {
        Intrinsics.checkNotNullParameter(onMultipleViewsFoundListener2, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        onMultipleViewsFoundListener.remove(onMultipleViewsFoundListener2);
    }

    public static final void notifyViewRendered(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        String nativeId = INSTANCE.getNativeId(view);
        if (nativeId != null) {
            Iterator<OnViewFoundListener> it = onViewFoundListeners.iterator();
            while (it.hasNext()) {
                OnViewFoundListener next = it.next();
                if (Intrinsics.areEqual((Object) nativeId, (Object) next.getNativeId())) {
                    next.onViewFound(view);
                    it.remove();
                }
            }
            for (Map.Entry next2 : onMultipleViewsFoundListener.entrySet()) {
                OnMultipleViewsFoundListener onMultipleViewsFoundListener2 = (OnMultipleViewsFoundListener) next2.getKey();
                if (((Set) next2.getValue()).contains(nativeId)) {
                    onMultipleViewsFoundListener2.onViewFound(view, nativeId);
                }
            }
        }
    }

    private final String getNativeId(View view) {
        Object tag = view.getTag(R.id.view_tag_native_id);
        if (tag instanceof String) {
            return (String) tag;
        }
        return null;
    }
}
