package com.facebook.react.uimanager.events;

import android.view.MotionEvent;
import androidx.core.util.Pools$SynchronizedPool;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.SoftAssertions;
import com.facebook.react.uimanager.events.TouchEventType;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class TouchEvent extends Event<TouchEvent> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Pools$SynchronizedPool EVENTS_POOL = new Pools$SynchronizedPool(3);
    private static final String TAG = TouchEvent.class.getSimpleName();
    private static final int TOUCH_EVENTS_POOL_SIZE = 3;
    public static final long UNSET = Long.MIN_VALUE;
    private short coalescingKey;
    private MotionEvent motionEvent;
    private TouchEventType touchEventType;
    private float viewX;
    private float viewY;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|5|6|7|8|9|11) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        static {
            /*
                com.facebook.react.uimanager.events.TouchEventType[] r0 = com.facebook.react.uimanager.events.TouchEventType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.react.uimanager.events.TouchEventType r1 = com.facebook.react.uimanager.events.TouchEventType.START     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.facebook.react.uimanager.events.TouchEventType r1 = com.facebook.react.uimanager.events.TouchEventType.END     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.facebook.react.uimanager.events.TouchEventType r1 = com.facebook.react.uimanager.events.TouchEventType.CANCEL     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.facebook.react.uimanager.events.TouchEventType r1 = com.facebook.react.uimanager.events.TouchEventType.MOVE     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.events.TouchEvent.WhenMappings.<clinit>():void");
        }
    }

    public /* synthetic */ TouchEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public static final TouchEvent obtain(int i, int i2, TouchEventType touchEventType2, MotionEvent motionEvent2, long j, float f, float f2, TouchEventCoalescingKeyHelper touchEventCoalescingKeyHelper) {
        return Companion.obtain(i, i2, touchEventType2, motionEvent2, j, f, f2, touchEventCoalescingKeyHelper);
    }

    public static final TouchEvent obtain(int i, TouchEventType touchEventType2, MotionEvent motionEvent2, long j, float f, float f2, TouchEventCoalescingKeyHelper touchEventCoalescingKeyHelper) {
        return Companion.obtain(i, touchEventType2, motionEvent2, j, f, f2, touchEventCoalescingKeyHelper);
    }

    private TouchEvent() {
    }

    public final float getViewX() {
        return this.viewX;
    }

    public final float getViewY() {
        return this.viewY;
    }

    public final MotionEvent getMotionEvent() {
        Object assertNotNull = Assertions.assertNotNull(this.motionEvent);
        Intrinsics.checkNotNullExpressionValue(assertNotNull, "assertNotNull(...)");
        return (MotionEvent) assertNotNull;
    }

    public final TouchEventType getTouchEventType() {
        Object assertNotNull = Assertions.assertNotNull(this.touchEventType);
        Intrinsics.checkNotNullExpressionValue(assertNotNull, "assertNotNull(...)");
        return (TouchEventType) assertNotNull;
    }

    /* access modifiers changed from: private */
    public final void init(int i, int i2, TouchEventType touchEventType2, MotionEvent motionEvent2, long j, float f, float f2, TouchEventCoalescingKeyHelper touchEventCoalescingKeyHelper) {
        super.init(i, i2, motionEvent2.getEventTime());
        short s = 0;
        SoftAssertions.assertCondition(j != Long.MIN_VALUE, "Gesture start time must be initialized");
        int action = motionEvent2.getAction() & 255;
        if (action == 0) {
            touchEventCoalescingKeyHelper.addCoalescingKey(j);
        } else if (action == 1) {
            touchEventCoalescingKeyHelper.removeCoalescingKey(j);
        } else if (action == 2) {
            s = touchEventCoalescingKeyHelper.getCoalescingKey(j);
        } else if (action == 3) {
            touchEventCoalescingKeyHelper.removeCoalescingKey(j);
        } else if (action == 5 || action == 6) {
            touchEventCoalescingKeyHelper.incrementCoalescingKey(j);
        } else {
            throw new RuntimeException("Unhandled MotionEvent action: " + action);
        }
        this.motionEvent = MotionEvent.obtain(motionEvent2);
        this.touchEventType = touchEventType2;
        this.coalescingKey = s;
        this.viewX = f;
        this.viewY = f2;
    }

    public void onDispose() {
        MotionEvent motionEvent2 = this.motionEvent;
        if (motionEvent2 != null) {
            motionEvent2.recycle();
        }
        this.motionEvent = null;
        try {
            EVENTS_POOL.release(this);
        } catch (IllegalStateException e) {
            String str = TAG;
            Intrinsics.checkNotNullExpressionValue(str, "TAG");
            ReactSoftExceptionLogger.logSoftException(str, e);
        }
    }

    public String getEventName() {
        TouchEventType.Companion companion = TouchEventType.Companion;
        Object assertNotNull = Assertions.assertNotNull(this.touchEventType);
        Intrinsics.checkNotNullExpressionValue(assertNotNull, "assertNotNull(...)");
        return companion.getJSEventName((TouchEventType) assertNotNull);
    }

    public boolean canCoalesce() {
        TouchEventType touchEventType2 = (TouchEventType) Assertions.assertNotNull(this.touchEventType);
        int i = touchEventType2 == null ? -1 : WhenMappings.$EnumSwitchMapping$0[touchEventType2.ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            return false;
        }
        if (i == 4) {
            return true;
        }
        TouchEventType touchEventType3 = this.touchEventType;
        throw new RuntimeException("Unknown touch event type: " + touchEventType3);
    }

    public short getCoalescingKey() {
        return this.coalescingKey;
    }

    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        Intrinsics.checkNotNullParameter(rCTEventEmitter, "rctEventEmitter");
        if (verifyMotionEvent()) {
            TouchesHelper.sendTouchesLegacy(rCTEventEmitter, this);
        }
    }

    public void dispatchModern(RCTModernEventEmitter rCTModernEventEmitter) {
        Intrinsics.checkNotNullParameter(rCTModernEventEmitter, "rctEventEmitter");
        if (verifyMotionEvent()) {
            rCTModernEventEmitter.receiveTouches(this);
        }
    }

    public int getEventCategory() {
        TouchEventType touchEventType2 = this.touchEventType;
        if (touchEventType2 == null) {
            return 2;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[touchEventType2.ordinal()];
        if (i == 1) {
            return 0;
        }
        if (i == 2 || i == 3) {
            return 1;
        }
        if (i == 4) {
            return 4;
        }
        throw new NoWhenBranchMatchedException();
    }

    private final boolean verifyMotionEvent() {
        if (this.motionEvent != null) {
            return true;
        }
        String str = TAG;
        Intrinsics.checkNotNullExpressionValue(str, "TAG");
        ReactSoftExceptionLogger.logSoftException(str, new IllegalStateException("Cannot dispatch a TouchEvent that has no MotionEvent; the TouchEvent has been recycled"));
        return false;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TouchEvent obtain(int i, TouchEventType touchEventType, MotionEvent motionEvent, long j, float f, float f2, TouchEventCoalescingKeyHelper touchEventCoalescingKeyHelper) {
            TouchEventCoalescingKeyHelper touchEventCoalescingKeyHelper2 = touchEventCoalescingKeyHelper;
            Intrinsics.checkNotNullParameter(touchEventCoalescingKeyHelper2, "touchEventCoalescingKeyHelper");
            return obtain(-1, i, touchEventType, (MotionEvent) Assertions.assertNotNull(motionEvent), j, f, f2, touchEventCoalescingKeyHelper2);
        }

        public final TouchEvent obtain(int i, int i2, TouchEventType touchEventType, MotionEvent motionEvent, long j, float f, float f2, TouchEventCoalescingKeyHelper touchEventCoalescingKeyHelper) {
            Intrinsics.checkNotNullParameter(touchEventCoalescingKeyHelper, "touchEventCoalescingKeyHelper");
            TouchEvent touchEvent = (TouchEvent) TouchEvent.EVENTS_POOL.acquire();
            if (touchEvent == null) {
                touchEvent = new TouchEvent((DefaultConstructorMarker) null);
            }
            Object assertNotNull = Assertions.assertNotNull(motionEvent);
            Intrinsics.checkNotNullExpressionValue(assertNotNull, "assertNotNull(...)");
            touchEvent.init(i, i2, touchEventType, (MotionEvent) assertNotNull, j, f, f2, touchEventCoalescingKeyHelper);
            return touchEvent;
        }
    }
}
