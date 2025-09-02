package com.facebook.react.runtime;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import com.facebook.common.logging.FLog;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.JSPointerDispatcher;
import com.facebook.react.uimanager.JSTouchDispatcher;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.systrace.Systrace;
import java.util.Objects;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ReactSurfaceView extends ReactRootView {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "ReactSurfaceView";
    private int heightMeasureSpec;
    private JSPointerDispatcher jsPointerDispatcher;
    private final JSTouchDispatcher jsTouchDispatcher = new JSTouchDispatcher(this);
    private final ReactSurfaceImpl surface;
    private boolean wasMeasured;
    private int widthMeasureSpec;

    public int getUIManagerType() {
        return 2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReactSurfaceView(Context context, ReactSurfaceImpl reactSurfaceImpl) {
        super(context);
        Intrinsics.checkNotNullParameter(reactSurfaceImpl, "surface");
        this.surface = reactSurfaceImpl;
        if (ReactFeatureFlags.dispatchPointerEvents) {
            this.jsPointerDispatcher = new JSPointerDispatcher(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        Systrace.beginSection(0, "ReactSurfaceView.onMeasure");
        int mode = View.MeasureSpec.getMode(i);
        if (mode == Integer.MIN_VALUE || mode == 0) {
            int childCount = getChildCount();
            int i5 = 0;
            for (int i6 = 0; i6 < childCount; i6++) {
                View childAt = getChildAt(i6);
                i5 = Math.max(i5, childAt.getLeft() + childAt.getMeasuredWidth() + childAt.getPaddingLeft() + childAt.getPaddingRight());
            }
            i3 = i5;
        } else {
            i3 = View.MeasureSpec.getSize(i);
        }
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode2 == Integer.MIN_VALUE || mode2 == 0) {
            int childCount2 = getChildCount();
            int i7 = 0;
            for (int i8 = 0; i8 < childCount2; i8++) {
                View childAt2 = getChildAt(i8);
                i7 = Math.max(i7, childAt2.getTop() + childAt2.getMeasuredHeight() + childAt2.getPaddingTop() + childAt2.getPaddingBottom());
            }
            i4 = i7;
        } else {
            i4 = View.MeasureSpec.getSize(i2);
        }
        setMeasuredDimension(i3, i4);
        this.wasMeasured = true;
        this.widthMeasureSpec = i;
        this.heightMeasureSpec = i2;
        Point viewportOffset = getViewportOffset();
        this.surface.updateLayoutSpecs(i, i2, viewportOffset.x, viewportOffset.y);
        Systrace.endSection(0);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.wasMeasured && z) {
            Point viewportOffset = getViewportOffset();
            this.surface.updateLayoutSpecs(this.widthMeasureSpec, this.heightMeasureSpec, viewportOffset.x, viewportOffset.y);
        }
    }

    private final Point getViewportOffset() {
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        Rect rect = new Rect();
        getWindowVisibleDisplayFrame(rect);
        iArr[0] = iArr[0] - rect.left;
        iArr[1] = iArr[1] - rect.top;
        return new Point(iArr[0], iArr[1]);
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    public void onChildStartedNativeGesture(View view, MotionEvent motionEvent) {
        JSPointerDispatcher jSPointerDispatcher;
        Intrinsics.checkNotNullParameter(motionEvent, "ev");
        EventDispatcher eventDispatcher = this.surface.getEventDispatcher();
        if (eventDispatcher != null) {
            this.jsTouchDispatcher.onChildStartedNativeGesture(motionEvent, eventDispatcher);
            if (view != null && (jSPointerDispatcher = this.jsPointerDispatcher) != null) {
                jSPointerDispatcher.onChildStartedNativeGesture(view, motionEvent, eventDispatcher);
            }
        }
    }

    public void onChildEndedNativeGesture(View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(view, "childView");
        Intrinsics.checkNotNullParameter(motionEvent, "ev");
        EventDispatcher eventDispatcher = this.surface.getEventDispatcher();
        if (eventDispatcher != null) {
            this.jsTouchDispatcher.onChildEndedNativeGesture(motionEvent, eventDispatcher);
            JSPointerDispatcher jSPointerDispatcher = this.jsPointerDispatcher;
            if (jSPointerDispatcher != null) {
                jSPointerDispatcher.onChildEndedNativeGesture();
            }
        }
    }

    public void handleException(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "t");
        ReactHostImpl reactHost = this.surface.getReactHost();
        Intrinsics.checkNotNullExpressionValue(reactHost, "getReactHost(...)");
        String objects = Objects.toString(th.getMessage(), "");
        Intrinsics.checkNotNull(objects);
        reactHost.handleHostException(new IllegalViewOperationException(objects, this, th));
    }

    public void setIsFabric(boolean z) {
        super.setIsFabric(true);
    }

    public String getJSModuleName() {
        String moduleName = this.surface.getModuleName();
        Intrinsics.checkNotNullExpressionValue(moduleName, "<get-moduleName>(...)");
        return moduleName;
    }

    /* access modifiers changed from: protected */
    public void dispatchJSTouchEvent(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        EventDispatcher eventDispatcher = this.surface.getEventDispatcher();
        if (eventDispatcher != null) {
            this.jsTouchDispatcher.handleTouchEvent(motionEvent, eventDispatcher, this.surface.getReactHost().getCurrentReactContext());
        } else {
            FLog.w(TAG, "Unable to dispatch touch events to JS as the React instance has not been attached");
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchJSPointerEvent(MotionEvent motionEvent, boolean z) {
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        if (this.jsPointerDispatcher != null) {
            EventDispatcher eventDispatcher = this.surface.getEventDispatcher();
            if (eventDispatcher != null) {
                JSPointerDispatcher jSPointerDispatcher = this.jsPointerDispatcher;
                if (jSPointerDispatcher != null) {
                    jSPointerDispatcher.handleMotionEvent(motionEvent, eventDispatcher, z);
                    return;
                }
                return;
            }
            FLog.w(TAG, "Unable to dispatch pointer events to JS as the React instance has not been attached");
        } else if (ReactFeatureFlags.dispatchPointerEvents) {
            FLog.w(TAG, "Unable to dispatch pointer events to JS before the dispatcher is available");
        }
    }

    public boolean hasActiveReactContext() {
        return this.surface.isAttached() && this.surface.getReactHost().getCurrentReactContext() != null;
    }

    public boolean hasActiveReactInstance() {
        return this.surface.isAttached() && this.surface.getReactHost().isInstanceInitialized();
    }

    public ReactContext getCurrentReactContext() {
        if (this.surface.isAttached()) {
            return this.surface.getReactHost().getCurrentReactContext();
        }
        return null;
    }

    public boolean isViewAttachedToReactInstance() {
        return this.surface.isAttached();
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
