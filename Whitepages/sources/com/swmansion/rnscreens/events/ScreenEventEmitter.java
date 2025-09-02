package com.swmansion.rnscreens.events;

import android.view.View;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.swmansion.rnscreens.Screen;
import com.swmansion.rnscreens.ScreenFragment;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

public final class ScreenEventEmitter {
    private final Screen screen;

    public ScreenEventEmitter(Screen screen2) {
        Intrinsics.checkNotNullParameter(screen2, "screen");
        this.screen = screen2;
    }

    public final EventDispatcher getReactEventDispatcher() {
        return this.screen.getReactEventDispatcher();
    }

    public final int getReactSurfaceId() {
        return UIManagerHelper.getSurfaceId((View) this.screen);
    }

    public final Unit dispatchOnWillAppear() {
        EventDispatcher reactEventDispatcher = getReactEventDispatcher();
        if (reactEventDispatcher == null) {
            return null;
        }
        reactEventDispatcher.dispatchEvent(new ScreenWillAppearEvent(getReactSurfaceId(), this.screen.getId()));
        return Unit.INSTANCE;
    }

    public final Unit dispatchOnAppear() {
        EventDispatcher reactEventDispatcher = getReactEventDispatcher();
        if (reactEventDispatcher == null) {
            return null;
        }
        reactEventDispatcher.dispatchEvent(new ScreenAppearEvent(getReactSurfaceId(), this.screen.getId()));
        return Unit.INSTANCE;
    }

    public final Unit dispatchOnWillDisappear() {
        EventDispatcher reactEventDispatcher = getReactEventDispatcher();
        if (reactEventDispatcher == null) {
            return null;
        }
        reactEventDispatcher.dispatchEvent(new ScreenWillDisappearEvent(getReactSurfaceId(), this.screen.getId()));
        return Unit.INSTANCE;
    }

    public final Unit dispatchOnDisappear() {
        EventDispatcher reactEventDispatcher = getReactEventDispatcher();
        if (reactEventDispatcher == null) {
            return null;
        }
        reactEventDispatcher.dispatchEvent(new ScreenDisappearEvent(getReactSurfaceId(), this.screen.getId()));
        return Unit.INSTANCE;
    }

    public final void dispatchTransitionProgress(float f, boolean z, boolean z2) {
        float coerceIn = RangesKt.coerceIn(f, 0.0f, 1.0f);
        short coalescingKey = ScreenFragment.Companion.getCoalescingKey(coerceIn);
        EventDispatcher reactEventDispatcher = getReactEventDispatcher();
        if (reactEventDispatcher != null) {
            reactEventDispatcher.dispatchEvent(new ScreenTransitionProgressEvent(getReactSurfaceId(), this.screen.getId(), coerceIn, z, z2, coalescingKey));
        }
    }
}
