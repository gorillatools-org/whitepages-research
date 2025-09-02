package com.swmansion.gesturehandler.core;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

public final class LongPressGestureHandler extends GestureHandler {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private int currentPointers;
    private final float defaultMaxDistSq;
    private Handler handler;
    private float maxDistSq;
    private long minDurationMs = 500;
    private int numberOfPointersRequired;
    private long previousTime;
    private long startTime;
    private float startX;
    private float startY;

    public LongPressGestureHandler(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        setShouldCancelWhenOutside(true);
        float f = context.getResources().getDisplayMetrics().density * 10.0f;
        float f2 = f * f;
        this.defaultMaxDistSq = f2;
        this.maxDistSq = f2;
        this.numberOfPointersRequired = 1;
    }

    public final void setMinDurationMs(long j) {
        this.minDurationMs = j;
    }

    public final int getDuration() {
        return (int) (this.previousTime - this.startTime);
    }

    public void resetConfig() {
        super.resetConfig();
        this.minDurationMs = 500;
        this.maxDistSq = this.defaultMaxDistSq;
    }

    public final LongPressGestureHandler setMaxDist(float f) {
        this.maxDistSq = f * f;
        return this;
    }

    public final LongPressGestureHandler setNumberOfPointers(int i) {
        this.numberOfPointersRequired = i;
        return this;
    }

    static /* synthetic */ Pair getAverageCoords$default(LongPressGestureHandler longPressGestureHandler, MotionEvent motionEvent, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return longPressGestureHandler.getAverageCoords(motionEvent, z);
    }

    private final Pair getAverageCoords(MotionEvent motionEvent, boolean z) {
        if (!z) {
            IntRange until = RangesKt.until(0, motionEvent.getPointerCount());
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
            Iterator it = until.iterator();
            while (it.hasNext()) {
                arrayList.add(Float.valueOf(motionEvent.getX(((IntIterator) it).nextInt())));
            }
            float averageOfFloat = (float) CollectionsKt.averageOfFloat(arrayList);
            IntRange until2 = RangesKt.until(0, motionEvent.getPointerCount());
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(until2, 10));
            Iterator it2 = until2.iterator();
            while (it2.hasNext()) {
                arrayList2.add(Float.valueOf(motionEvent.getY(((IntIterator) it2).nextInt())));
            }
            return new Pair(Float.valueOf(averageOfFloat), Float.valueOf((float) CollectionsKt.averageOfFloat(arrayList2)));
        }
        int pointerCount = motionEvent.getPointerCount();
        float f = 0.0f;
        float f2 = 0.0f;
        for (int i = 0; i < pointerCount; i++) {
            if (i != motionEvent.getActionIndex()) {
                f += motionEvent.getX(i);
                f2 += motionEvent.getY(i);
            }
        }
        return new Pair(Float.valueOf(f / ((float) (motionEvent.getPointerCount() - 1))), Float.valueOf(f2 / ((float) (motionEvent.getPointerCount() - 1))));
    }

    /* access modifiers changed from: protected */
    public void onHandle(MotionEvent motionEvent, MotionEvent motionEvent2) {
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        Intrinsics.checkNotNullParameter(motionEvent2, "sourceEvent");
        if (shouldActivateWithMouse(motionEvent2)) {
            if (getState() == 0) {
                long uptimeMillis = SystemClock.uptimeMillis();
                this.previousTime = uptimeMillis;
                this.startTime = uptimeMillis;
                begin();
                Pair averageCoords$default = getAverageCoords$default(this, motionEvent2, false, 2, (Object) null);
                float floatValue = ((Number) averageCoords$default.component1()).floatValue();
                float floatValue2 = ((Number) averageCoords$default.component2()).floatValue();
                this.startX = floatValue;
                this.startY = floatValue2;
                this.currentPointers++;
            }
            if (motionEvent2.getActionMasked() == 5) {
                this.currentPointers++;
                Pair averageCoords$default2 = getAverageCoords$default(this, motionEvent2, false, 2, (Object) null);
                float floatValue3 = ((Number) averageCoords$default2.component1()).floatValue();
                float floatValue4 = ((Number) averageCoords$default2.component2()).floatValue();
                this.startX = floatValue3;
                this.startY = floatValue4;
                if (this.currentPointers > this.numberOfPointersRequired) {
                    fail();
                    this.currentPointers = 0;
                }
            }
            if (getState() == 2 && this.currentPointers == this.numberOfPointersRequired && (motionEvent2.getActionMasked() == 0 || motionEvent2.getActionMasked() == 5)) {
                Handler handler2 = new Handler(Looper.getMainLooper());
                this.handler = handler2;
                long j = this.minDurationMs;
                if (j > 0) {
                    Intrinsics.checkNotNull(handler2);
                    handler2.postDelayed(new LongPressGestureHandler$$ExternalSyntheticLambda0(this), this.minDurationMs);
                } else if (j == 0) {
                    activate();
                }
            }
            if (motionEvent2.getActionMasked() == 1 || motionEvent2.getActionMasked() == 12) {
                this.currentPointers--;
                Handler handler3 = this.handler;
                if (handler3 != null) {
                    handler3.removeCallbacksAndMessages((Object) null);
                    this.handler = null;
                }
                if (getState() == 4) {
                    end();
                } else {
                    fail();
                }
            } else if (motionEvent2.getActionMasked() == 6) {
                int i = this.currentPointers - 1;
                this.currentPointers = i;
                if (i >= this.numberOfPointersRequired || getState() == 4) {
                    Pair averageCoords = getAverageCoords(motionEvent2, true);
                    float floatValue5 = ((Number) averageCoords.component1()).floatValue();
                    float floatValue6 = ((Number) averageCoords.component2()).floatValue();
                    this.startX = floatValue5;
                    this.startY = floatValue6;
                    return;
                }
                fail();
                this.currentPointers = 0;
            } else {
                Pair averageCoords$default3 = getAverageCoords$default(this, motionEvent2, false, 2, (Object) null);
                float floatValue7 = ((Number) averageCoords$default3.component1()).floatValue();
                float floatValue8 = ((Number) averageCoords$default3.component2()).floatValue();
                float f = floatValue7 - this.startX;
                float f2 = floatValue8 - this.startY;
                if ((f * f) + (f2 * f2) <= this.maxDistSq) {
                    return;
                }
                if (getState() == 4) {
                    cancel();
                } else {
                    fail();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void onHandle$lambda$2(LongPressGestureHandler longPressGestureHandler) {
        longPressGestureHandler.activate();
    }

    /* access modifiers changed from: protected */
    public void onStateChange(int i, int i2) {
        Handler handler2 = this.handler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages((Object) null);
            this.handler = null;
        }
    }

    public void dispatchStateChange(int i, int i2) {
        this.previousTime = SystemClock.uptimeMillis();
        super.dispatchStateChange(i, i2);
    }

    public void dispatchHandlerUpdate(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        this.previousTime = SystemClock.uptimeMillis();
        super.dispatchHandlerUpdate(motionEvent);
    }

    /* access modifiers changed from: protected */
    public void onReset() {
        super.onReset();
        this.currentPointers = 0;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
