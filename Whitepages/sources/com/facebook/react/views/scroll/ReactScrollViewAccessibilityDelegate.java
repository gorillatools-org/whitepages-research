package com.facebook.react.views.scroll;

import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import app.notifee.core.event.LogEvent;
import com.facebook.react.R;
import com.facebook.react.bridge.AssertionException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ReactAccessibilityDelegate;
import kotlin.jvm.internal.Intrinsics;

public final class ReactScrollViewAccessibilityDelegate extends AccessibilityDelegateCompat {
    private final String TAG;

    public ReactScrollViewAccessibilityDelegate() {
        String simpleName = ReactScrollViewAccessibilityDelegate.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "getSimpleName(...)");
        this.TAG = simpleName;
    }

    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        Intrinsics.checkNotNullParameter(view, "host");
        Intrinsics.checkNotNullParameter(accessibilityEvent, "event");
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        if ((view instanceof ReactScrollView) || (view instanceof ReactHorizontalScrollView)) {
            onInitializeAccessibilityEventInternal(view, accessibilityEvent);
            return;
        }
        String str = this.TAG;
        String simpleName = view.getClass().getSimpleName();
        ReactSoftExceptionLogger.logSoftException(str, new AssertionException("ReactScrollViewAccessibilityDelegate should only be used with ReactScrollView or ReactHorizontalScrollView, not with class: " + simpleName));
    }

    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        Intrinsics.checkNotNullParameter(view, "host");
        Intrinsics.checkNotNullParameter(accessibilityNodeInfoCompat, LogEvent.LEVEL_INFO);
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        if ((view instanceof ReactScrollView) || (view instanceof ReactHorizontalScrollView)) {
            onInitializeAccessibilityNodeInfoInternal(view, accessibilityNodeInfoCompat);
            return;
        }
        String str = this.TAG;
        String simpleName = view.getClass().getSimpleName();
        ReactSoftExceptionLogger.logSoftException(str, new AssertionException("ReactScrollViewAccessibilityDelegate should only be used with ReactScrollView or ReactHorizontalScrollView, not with class: " + simpleName));
    }

    private final void onInitializeAccessibilityEventInternal(View view, AccessibilityEvent accessibilityEvent) {
        boolean z;
        Object tag = view.getTag(R.id.accessibility_collection);
        Integer num = null;
        ReadableMap readableMap = tag instanceof ReadableMap ? (ReadableMap) tag : null;
        if (readableMap != null) {
            accessibilityEvent.setItemCount(readableMap.getInt("itemCount"));
            ViewGroup viewGroup = view instanceof ViewGroup ? (ViewGroup) view : null;
            int i = 0;
            View childAt = viewGroup != null ? viewGroup.getChildAt(0) : null;
            ViewGroup viewGroup2 = childAt instanceof ViewGroup ? (ViewGroup) childAt : null;
            if (viewGroup2 != null) {
                int childCount = viewGroup2.getChildCount();
                Integer num2 = null;
                while (i < childCount) {
                    View childAt2 = viewGroup2.getChildAt(i);
                    if (view instanceof ReactScrollView) {
                        z = ((ReactScrollView) view).isPartiallyScrolledInView(childAt2);
                    } else if (view instanceof ReactHorizontalScrollView) {
                        z = ((ReactHorizontalScrollView) view).isPartiallyScrolledInView(childAt2);
                    } else {
                        return;
                    }
                    Object tag2 = childAt2.getTag(R.id.accessibility_collection_item);
                    Intrinsics.checkNotNull(tag2, "null cannot be cast to non-null type com.facebook.react.bridge.ReadableMap");
                    ReadableMap readableMap2 = (ReadableMap) tag2;
                    if (childAt2 instanceof ViewGroup) {
                        ((ViewGroup) childAt2).getChildCount();
                        if (z) {
                            if (num == null) {
                                num = Integer.valueOf(readableMap2.getInt("itemIndex"));
                            }
                            num2 = Integer.valueOf(readableMap2.getInt("itemIndex"));
                        }
                        if (!(num == null || num2 == null)) {
                            accessibilityEvent.setFromIndex(num.intValue());
                            accessibilityEvent.setToIndex(num2.intValue());
                        }
                        i++;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    private final void onInitializeAccessibilityNodeInfoInternal(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        ReactAccessibilityDelegate.AccessibilityRole fromViewTag = ReactAccessibilityDelegate.AccessibilityRole.fromViewTag(view);
        if (fromViewTag != null) {
            ReactAccessibilityDelegate.setRole(accessibilityNodeInfoCompat, fromViewTag, view.getContext());
        }
        Object tag = view.getTag(R.id.accessibility_collection);
        ReadableMap readableMap = tag instanceof ReadableMap ? (ReadableMap) tag : null;
        if (readableMap != null) {
            accessibilityNodeInfoCompat.setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(readableMap.getInt("rowCount"), readableMap.getInt("columnCount"), readableMap.getBoolean("hierarchical")));
        }
        if (view instanceof ReactScrollView) {
            accessibilityNodeInfoCompat.setScrollable(((ReactScrollView) view).getScrollEnabled());
        } else if (view instanceof ReactHorizontalScrollView) {
            accessibilityNodeInfoCompat.setScrollable(((ReactHorizontalScrollView) view).getScrollEnabled());
        }
    }
}
