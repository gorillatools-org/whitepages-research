package com.swmansion.rnscreens.bottomsheet;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewGroup;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ReactCompoundViewGroup;
import com.facebook.react.uimanager.ReactPointerEventsView;
import com.swmansion.rnscreens.ext.NumericExtKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class DimmingView extends ViewGroup implements ReactCompoundViewGroup, ReactPointerEventsView {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final DimmingViewPointerEventsProxy pointerEventsProxy;

    public PointerEvents getPointerEvents() {
        return this.pointerEventsProxy.getPointerEvents();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DimmingView(Context context, float f, DimmingViewPointerEventsProxy dimmingViewPointerEventsProxy) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dimmingViewPointerEventsProxy, "pointerEventsProxy");
        this.pointerEventsProxy = dimmingViewPointerEventsProxy;
        dimmingViewPointerEventsProxy.setPointerEventsImpl(new DimmingViewPointerEventsImpl(this));
        setBackgroundColor(-16777216);
        setAlpha(f);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DimmingView(Context context, float f) {
        this(context, f, new DimmingViewPointerEventsProxy((DimmingViewPointerEventsImpl) null));
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final boolean getBlockGestures$react_native_screens_release() {
        return !NumericExtKt.equalWithRespectToEps$default(getAlpha(), 0.0f, 0.0f, 2, (Object) null);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (getBlockGestures$react_native_screens_release()) {
            callOnClick();
        }
        return getBlockGestures$react_native_screens_release();
    }

    public int reactTagForTouch(float f, float f2) {
        throw new IllegalStateException("[RNScreens] DimmingView should never be asked for the view tag!");
    }

    public boolean interceptsTouchEvent(float f, float f2) {
        return getBlockGestures$react_native_screens_release();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.pointerEventsProxy.setPointerEventsImpl((DimmingViewPointerEventsImpl) null);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
