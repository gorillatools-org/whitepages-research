package com.swmansion.gesturehandler.core;

import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class FlingGestureHandler extends GestureHandler {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final double MAX_AXIAL_DEVIATION;
    private static final double MAX_DIAGONAL_DEVIATION;
    private int direction = 1;
    private final Runnable failDelayed = new FlingGestureHandler$$ExternalSyntheticLambda0(this);
    private Handler handler;
    private final long maxDurationMs = 800;
    private int maxNumberOfPointersSimultaneously;
    private final long minVelocity = 2000;
    private int numberOfPointersRequired = 1;
    private VelocityTracker velocityTracker;

    public final void setNumberOfPointersRequired(int i) {
        this.numberOfPointersRequired = i;
    }

    public final void setDirection(int i) {
        this.direction = i;
    }

    /* access modifiers changed from: private */
    public static final void failDelayed$lambda$0(FlingGestureHandler flingGestureHandler) {
        flingGestureHandler.fail();
    }

    public void resetConfig() {
        super.resetConfig();
        this.numberOfPointersRequired = 1;
        this.direction = 1;
    }

    private final void startFling(MotionEvent motionEvent) {
        this.velocityTracker = VelocityTracker.obtain();
        begin();
        this.maxNumberOfPointersSimultaneously = 1;
        Handler handler2 = this.handler;
        if (handler2 == null) {
            this.handler = new Handler(Looper.getMainLooper());
        } else {
            Intrinsics.checkNotNull(handler2);
            handler2.removeCallbacksAndMessages((Object) null);
        }
        Handler handler3 = this.handler;
        Intrinsics.checkNotNull(handler3);
        handler3.postDelayed(this.failDelayed, this.maxDurationMs);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00cc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean tryEndFling(android.view.MotionEvent r11) {
        /*
            r10 = this;
            android.view.VelocityTracker r0 = r10.velocityTracker
            r10.addVelocityMovement(r0, r11)
            com.swmansion.gesturehandler.core.Vector$Companion r11 = com.swmansion.gesturehandler.core.Vector.Companion
            android.view.VelocityTracker r0 = r10.velocityTracker
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            com.swmansion.gesturehandler.core.Vector r11 = r11.fromVelocity(r0)
            r0 = 2
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1 = 1
            java.lang.Integer r2 = java.lang.Integer.valueOf(r1)
            r3 = 4
            java.lang.Integer r4 = java.lang.Integer.valueOf(r3)
            r5 = 8
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.Integer[] r0 = new java.lang.Integer[]{r0, r2, r4, r5}
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>(r3)
            r4 = 0
            r5 = r4
        L_0x0030:
            if (r5 >= r3) goto L_0x0048
            r6 = r0[r5]
            int r6 = r6.intValue()
            double r7 = MAX_AXIAL_DEVIATION
            boolean r6 = tryEndFling$getVelocityAlignment(r10, r11, r6, r7)
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)
            r2.add(r6)
            int r5 = r5 + 1
            goto L_0x0030
        L_0x0048:
            r0 = 5
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r5 = 9
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r6 = 6
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r7 = 10
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            java.lang.Integer[] r0 = new java.lang.Integer[]{r0, r5, r6, r7}
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>(r3)
            r6 = r4
        L_0x0068:
            if (r6 >= r3) goto L_0x0080
            r7 = r0[r6]
            int r7 = r7.intValue()
            double r8 = MAX_DIAGONAL_DEVIATION
            boolean r7 = tryEndFling$getVelocityAlignment(r10, r11, r7, r8)
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)
            r5.add(r7)
            int r6 = r6 + 1
            goto L_0x0068
        L_0x0080:
            boolean r0 = r2.isEmpty()
            if (r0 == 0) goto L_0x0088
        L_0x0086:
            r0 = r4
            goto L_0x009f
        L_0x0088:
            java.util.Iterator r0 = r2.iterator()
        L_0x008c:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0086
            java.lang.Object r2 = r0.next()
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L_0x008c
            r0 = r1
        L_0x009f:
            boolean r2 = r5.isEmpty()
            if (r2 == 0) goto L_0x00a7
        L_0x00a5:
            r2 = r4
            goto L_0x00be
        L_0x00a7:
            java.util.Iterator r2 = r5.iterator()
        L_0x00ab:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x00a5
            java.lang.Object r3 = r2.next()
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r3 = r3.booleanValue()
            if (r3 == 0) goto L_0x00ab
            r2 = r1
        L_0x00be:
            r0 = r0 | r2
            double r2 = r11.getMagnitude()
            long r5 = r10.minVelocity
            double r5 = (double) r5
            int r11 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r11 <= 0) goto L_0x00cc
            r11 = r1
            goto L_0x00cd
        L_0x00cc:
            r11 = r4
        L_0x00cd:
            int r2 = r10.maxNumberOfPointersSimultaneously
            int r3 = r10.numberOfPointersRequired
            if (r2 != r3) goto L_0x00e4
            if (r0 == 0) goto L_0x00e4
            if (r11 == 0) goto L_0x00e4
            android.os.Handler r11 = r10.handler
            kotlin.jvm.internal.Intrinsics.checkNotNull(r11)
            r0 = 0
            r11.removeCallbacksAndMessages(r0)
            r10.activate()
            goto L_0x00e5
        L_0x00e4:
            r1 = r4
        L_0x00e5:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.core.FlingGestureHandler.tryEndFling(android.view.MotionEvent):boolean");
    }

    private static final boolean tryEndFling$getVelocityAlignment(FlingGestureHandler flingGestureHandler, Vector vector, int i, double d) {
        return (flingGestureHandler.direction & i) == i && vector.isSimilar(Vector.Companion.fromDirection(i), d);
    }

    public void activate(boolean z) {
        super.activate(z);
        end();
    }

    private final void endFling(MotionEvent motionEvent) {
        if (!tryEndFling(motionEvent)) {
            fail();
        }
    }

    /* access modifiers changed from: protected */
    public void onHandle(MotionEvent motionEvent, MotionEvent motionEvent2) {
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        Intrinsics.checkNotNullParameter(motionEvent2, "sourceEvent");
        if (shouldActivateWithMouse(motionEvent2)) {
            int state = getState();
            if (state == 0) {
                startFling(motionEvent2);
            }
            if (state == 2) {
                tryEndFling(motionEvent2);
                if (motionEvent2.getPointerCount() > this.maxNumberOfPointersSimultaneously) {
                    this.maxNumberOfPointersSimultaneously = motionEvent2.getPointerCount();
                }
                if (motionEvent2.getActionMasked() == 1) {
                    endFling(motionEvent2);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCancel() {
        Handler handler2 = this.handler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages((Object) null);
        }
    }

    /* access modifiers changed from: protected */
    public void onReset() {
        VelocityTracker velocityTracker2 = this.velocityTracker;
        if (velocityTracker2 != null) {
            velocityTracker2.recycle();
        }
        this.velocityTracker = null;
        Handler handler2 = this.handler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages((Object) null);
        }
    }

    private final void addVelocityMovement(VelocityTracker velocityTracker2, MotionEvent motionEvent) {
        float rawX = motionEvent.getRawX() - motionEvent.getX();
        float rawY = motionEvent.getRawY() - motionEvent.getY();
        motionEvent.offsetLocation(rawX, rawY);
        Intrinsics.checkNotNull(velocityTracker2);
        velocityTracker2.addMovement(motionEvent);
        motionEvent.offsetLocation(-rawX, -rawY);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        GestureUtils gestureUtils = GestureUtils.INSTANCE;
        MAX_AXIAL_DEVIATION = gestureUtils.coneToDeviation(30.0d);
        MAX_DIAGONAL_DEVIATION = gestureUtils.coneToDeviation(60.0d);
    }
}
