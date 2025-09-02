package com.facebook.react.views.scroll;

import android.os.SystemClock;
import androidx.core.util.Pools$SynchronizedPool;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.views.scroll.ScrollEventType;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ScrollEvent extends Event<ScrollEvent> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Pools$SynchronizedPool EVENTS_POOL = new Pools$SynchronizedPool(3);
    private static final String TAG = ScrollEvent.class.getSimpleName();
    private int contentHeight;
    private int contentWidth;
    private ScrollEventType scrollEventType;
    private int scrollViewHeight;
    private int scrollViewWidth;
    private float scrollX;
    private float scrollY;
    private long timestamp;
    private float xVelocity;
    private float yVelocity;

    public /* synthetic */ ScrollEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public static final ScrollEvent obtain(int i, int i2, ScrollEventType scrollEventType2, float f, float f2, float f3, float f4, int i3, int i4, int i5, int i6) {
        return Companion.obtain(i, i2, scrollEventType2, f, f2, f3, f4, i3, i4, i5, i6);
    }

    public static final ScrollEvent obtain(int i, ScrollEventType scrollEventType2, float f, float f2, float f3, float f4, int i2, int i3, int i4, int i5) {
        return Companion.obtain(i, scrollEventType2, f, f2, f3, f4, i2, i3, i4, i5);
    }

    private ScrollEvent() {
    }

    public void onDispose() {
        try {
            EVENTS_POOL.release(this);
        } catch (IllegalStateException e) {
            String str = TAG;
            Intrinsics.checkNotNullExpressionValue(str, "TAG");
            ReactSoftExceptionLogger.logSoftException(str, e);
        }
    }

    /* access modifiers changed from: private */
    public final void init(int i, int i2, ScrollEventType scrollEventType2, float f, float f2, float f3, float f4, int i3, int i4, int i5, int i6) {
        super.init(i, i2);
        this.scrollEventType = scrollEventType2;
        this.scrollX = f;
        this.scrollY = f2;
        this.xVelocity = f3;
        this.yVelocity = f4;
        this.contentWidth = i3;
        this.contentHeight = i4;
        this.scrollViewWidth = i5;
        this.scrollViewHeight = i6;
        this.timestamp = SystemClock.uptimeMillis();
    }

    public String getEventName() {
        ScrollEventType.Companion companion = ScrollEventType.Companion;
        Object assertNotNull = Assertions.assertNotNull(this.scrollEventType);
        Intrinsics.checkNotNullExpressionValue(assertNotNull, "assertNotNull(...)");
        return companion.getJSEventName((ScrollEventType) assertNotNull);
    }

    public boolean canCoalesce() {
        return this.scrollEventType == ScrollEventType.SCROLL;
    }

    /* access modifiers changed from: protected */
    public WritableMap getEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble(ViewProps.TOP, 0.0d);
        createMap.putDouble(ViewProps.BOTTOM, 0.0d);
        createMap.putDouble(ViewProps.LEFT, 0.0d);
        createMap.putDouble(ViewProps.RIGHT, 0.0d);
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putDouble("x", (double) PixelUtil.toDIPFromPixel(this.scrollX));
        createMap2.putDouble("y", (double) PixelUtil.toDIPFromPixel(this.scrollY));
        WritableMap createMap3 = Arguments.createMap();
        createMap3.putDouble("width", (double) PixelUtil.toDIPFromPixel((float) this.contentWidth));
        createMap3.putDouble("height", (double) PixelUtil.toDIPFromPixel((float) this.contentHeight));
        WritableMap createMap4 = Arguments.createMap();
        createMap4.putDouble("width", (double) PixelUtil.toDIPFromPixel((float) this.scrollViewWidth));
        createMap4.putDouble("height", (double) PixelUtil.toDIPFromPixel((float) this.scrollViewHeight));
        WritableMap createMap5 = Arguments.createMap();
        createMap5.putDouble("x", (double) this.xVelocity);
        createMap5.putDouble("y", (double) this.yVelocity);
        WritableMap createMap6 = Arguments.createMap();
        createMap6.putMap("contentInset", createMap);
        createMap6.putMap("contentOffset", createMap2);
        createMap6.putMap("contentSize", createMap3);
        createMap6.putMap("layoutMeasurement", createMap4);
        createMap6.putMap("velocity", createMap5);
        createMap6.putInt("target", getViewTag());
        createMap6.putDouble("timestamp", (double) this.timestamp);
        createMap6.putBoolean("responderIgnoreScroll", true);
        Intrinsics.checkNotNull(createMap6);
        return createMap6;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ScrollEvent obtain(int i, int i2, ScrollEventType scrollEventType, float f, float f2, float f3, float f4, int i3, int i4, int i5, int i6) {
            ScrollEvent scrollEvent = (ScrollEvent) ScrollEvent.EVENTS_POOL.acquire();
            if (scrollEvent == null) {
                scrollEvent = new ScrollEvent((DefaultConstructorMarker) null);
            }
            scrollEvent.init(i, i2, scrollEventType, f, f2, f3, f4, i3, i4, i5, i6);
            return scrollEvent;
        }

        public final ScrollEvent obtain(int i, ScrollEventType scrollEventType, float f, float f2, float f3, float f4, int i2, int i3, int i4, int i5) {
            return obtain(-1, i, scrollEventType, f, f2, f3, f4, i2, i3, i4, i5);
        }
    }
}
