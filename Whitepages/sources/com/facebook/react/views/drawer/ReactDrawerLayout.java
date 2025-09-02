package com.facebook.react.views.drawer;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import app.notifee.core.event.LogEvent;
import com.facebook.common.logging.FLog;
import com.facebook.react.R;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.ReactAccessibilityDelegate;
import com.facebook.react.uimanager.events.NativeGestureUtil;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ReactDrawerLayout extends DrawerLayout {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int DEFAULT_DRAWER_WIDTH = -1;
    private boolean dragging;
    private int drawerPosition = 8388611;
    private int drawerWidth = -1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReactDrawerLayout(ReactContext reactContext) {
        super(reactContext);
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegateCompat() {
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                Intrinsics.checkNotNullParameter(view, "host");
                Intrinsics.checkNotNullParameter(accessibilityNodeInfoCompat, LogEvent.LEVEL_INFO);
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                ReactAccessibilityDelegate.AccessibilityRole fromViewTag = ReactAccessibilityDelegate.AccessibilityRole.fromViewTag(view);
                if (fromViewTag != null) {
                    accessibilityNodeInfoCompat.setClassName(ReactAccessibilityDelegate.AccessibilityRole.getValue(fromViewTag));
                }
            }

            public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
                Intrinsics.checkNotNullParameter(view, "host");
                Intrinsics.checkNotNullParameter(accessibilityEvent, "event");
                super.onInitializeAccessibilityEvent(view, accessibilityEvent);
                Object tag = view.getTag(R.id.accessibility_role);
                if (tag instanceof ReactAccessibilityDelegate.AccessibilityRole) {
                    accessibilityEvent.setClassName(ReactAccessibilityDelegate.AccessibilityRole.getValue((ReactAccessibilityDelegate.AccessibilityRole) tag));
                }
            }
        });
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "ev");
        try {
            if (!super.onInterceptTouchEvent(motionEvent)) {
                return false;
            }
            NativeGestureUtil.notifyNativeGestureStarted(this, motionEvent);
            this.dragging = true;
            return true;
        } catch (IllegalArgumentException e) {
            FLog.w(ReactConstants.TAG, "Error intercepting touch event.", (Throwable) e);
            return false;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "ev");
        if (motionEvent.getActionMasked() == 1 && this.dragging) {
            NativeGestureUtil.notifyNativeGestureEnded(this, motionEvent);
            this.dragging = false;
        }
        return super.onTouchEvent(motionEvent);
    }

    @SuppressLint({"WrongConstant"})
    public final void openDrawer$ReactAndroid_release() {
        openDrawer(this.drawerPosition);
    }

    @SuppressLint({"WrongConstant"})
    public final void closeDrawer$ReactAndroid_release() {
        closeDrawer(this.drawerPosition);
    }

    public final void setDrawerPosition$ReactAndroid_release(int i) {
        this.drawerPosition = i;
        setDrawerProperties$ReactAndroid_release();
    }

    public final void setDrawerWidth$ReactAndroid_release(int i) {
        this.drawerWidth = i;
        setDrawerProperties$ReactAndroid_release();
    }

    public final void setDrawerProperties$ReactAndroid_release() {
        if (getChildCount() == 2) {
            View childAt = getChildAt(1);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.drawerlayout.widget.DrawerLayout.LayoutParams");
            DrawerLayout.LayoutParams layoutParams2 = (DrawerLayout.LayoutParams) layoutParams;
            layoutParams2.gravity = this.drawerPosition;
            layoutParams2.width = this.drawerWidth;
            childAt.setLayoutParams(layoutParams2);
            childAt.setClickable(true);
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
