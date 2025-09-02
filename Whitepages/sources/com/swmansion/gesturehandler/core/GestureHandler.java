package com.swmansion.gesturehandler.core;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.swmansion.gesturehandler.RNSVGHitTester;
import java.util.Arrays;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;

public abstract class GestureHandler {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static short nextEventCoalescingKey;
    /* access modifiers changed from: private */
    public static MotionEvent.PointerCoords[] pointerCoords;
    /* access modifiers changed from: private */
    public static MotionEvent.PointerProperties[] pointerProps;
    private int actionType;
    private int activationIndex;
    private WritableArray allTouchesPayload;
    private WritableArray changedTouchesPayload;
    private short eventCoalescingKey;
    private float[] hitSlop;
    private GestureHandlerInteractionController interactionController;
    private boolean isActive;
    private boolean isAwaiting;
    private boolean isEnabled;
    private boolean isWithinBounds;
    private float lastAbsolutePositionX;
    private float lastAbsolutePositionY;
    private float lastEventOffsetX;
    private float lastEventOffsetY;
    private boolean manualActivation;
    private int mouseButton;
    private boolean needsPointerData;
    private int numberOfPointers;
    private OnTouchEventListener onTouchEventListener;
    private GestureHandlerOrchestrator orchestrator;
    private int pointerType;
    private boolean shouldCancelWhenOutside;
    private boolean shouldResetProgress;
    private int state;
    private int tag;
    private int touchEventType;
    /* access modifiers changed from: private */
    public final int[] trackedPointerIDs = new int[12];
    private final PointerData[] trackedPointers;
    private int trackedPointersCount;
    /* access modifiers changed from: private */
    public int trackedPointersIDsCount;
    private View view;
    private final int[] windowOffset;
    private float x;
    private float y;

    /* access modifiers changed from: protected */
    public void onCancel() {
    }

    /* access modifiers changed from: protected */
    public abstract void onHandle(MotionEvent motionEvent, MotionEvent motionEvent2);

    /* access modifiers changed from: protected */
    public void onHandleHover(MotionEvent motionEvent, MotionEvent motionEvent2) {
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        Intrinsics.checkNotNullParameter(motionEvent2, "sourceEvent");
    }

    /* access modifiers changed from: protected */
    public void onPrepare() {
    }

    /* access modifiers changed from: protected */
    public void onReset() {
    }

    /* access modifiers changed from: protected */
    public void onStateChange(int i, int i2) {
    }

    public void resetProgress() {
    }

    public GestureHandler() {
        int[] iArr = new int[2];
        for (int i = 0; i < 2; i++) {
            iArr[i] = 0;
        }
        this.windowOffset = iArr;
        this.isEnabled = true;
        PointerData[] pointerDataArr = new PointerData[12];
        for (int i2 = 0; i2 < 12; i2++) {
            pointerDataArr[i2] = null;
        }
        this.trackedPointers = pointerDataArr;
        this.pointerType = 3;
    }

    public final int getTag() {
        return this.tag;
    }

    public final void setTag(int i) {
        this.tag = i;
    }

    public final View getView() {
        return this.view;
    }

    public final int getState() {
        return this.state;
    }

    public final boolean isWithinBounds() {
        return this.isWithinBounds;
    }

    public final boolean isEnabled() {
        return this.isEnabled;
    }

    public final int getActionType() {
        return this.actionType;
    }

    public final void setActionType(int i) {
        this.actionType = i;
    }

    public final int getTouchEventType() {
        return this.touchEventType;
    }

    public final int getTrackedPointersCount() {
        return this.trackedPointersCount;
    }

    public final boolean getNeedsPointerData() {
        return this.needsPointerData;
    }

    public final void setNeedsPointerData(boolean z) {
        this.needsPointerData = z;
    }

    public final short getEventCoalescingKey() {
        return this.eventCoalescingKey;
    }

    public final int getNumberOfPointers() {
        return this.numberOfPointers;
    }

    /* access modifiers changed from: protected */
    public final GestureHandlerOrchestrator getOrchestrator() {
        return this.orchestrator;
    }

    public final int getPointerType() {
        return this.pointerType;
    }

    /* access modifiers changed from: protected */
    public final GestureHandler self() {
        Intrinsics.checkNotNull(this, "null cannot be cast to non-null type ConcreteGestureHandlerT of com.swmansion.gesturehandler.core.GestureHandler");
        return this;
    }

    public final GestureHandler setEnabled(boolean z) {
        GestureHandler access$self = self();
        if (!(access$self.view == null || access$self.isEnabled == z)) {
            UiThreadUtil.runOnUiThread(new GestureHandler$$ExternalSyntheticLambda0(access$self));
        }
        access$self.isEnabled = z;
        return access$self;
    }

    public final GestureHandler setHitSlop(float f, float f2, float f3, float f4, float f5, float f6) {
        GestureHandler access$self = self();
        if (access$self.hitSlop == null) {
            access$self.hitSlop = new float[6];
        }
        float[] fArr = access$self.hitSlop;
        Intrinsics.checkNotNull(fArr);
        fArr[0] = f;
        float[] fArr2 = access$self.hitSlop;
        Intrinsics.checkNotNull(fArr2);
        fArr2[1] = f2;
        float[] fArr3 = access$self.hitSlop;
        Intrinsics.checkNotNull(fArr3);
        fArr3[2] = f3;
        float[] fArr4 = access$self.hitSlop;
        Intrinsics.checkNotNull(fArr4);
        fArr4[3] = f4;
        float[] fArr5 = access$self.hitSlop;
        Intrinsics.checkNotNull(fArr5);
        fArr5[4] = f5;
        float[] fArr6 = access$self.hitSlop;
        Intrinsics.checkNotNull(fArr6);
        fArr6[5] = f6;
        Companion companion = Companion;
        if (companion.hitSlopSet(f5) && companion.hitSlopSet(f) && companion.hitSlopSet(f3)) {
            throw new IllegalArgumentException("Cannot have all of left, right and width defined");
        } else if (companion.hitSlopSet(f5) && !companion.hitSlopSet(f) && !companion.hitSlopSet(f3)) {
            throw new IllegalArgumentException("When width is set one of left or right pads need to be defined");
        } else if (companion.hitSlopSet(f6) && companion.hitSlopSet(f4) && companion.hitSlopSet(f2)) {
            throw new IllegalArgumentException("Cannot have all of top, bottom and height defined");
        } else if (!companion.hitSlopSet(f6) || companion.hitSlopSet(f4) || companion.hitSlopSet(f2)) {
            return access$self;
        } else {
            throw new IllegalArgumentException("When height is set one of top or bottom pads need to be defined");
        }
    }

    public final GestureHandler setInteractionController(GestureHandlerInteractionController gestureHandlerInteractionController) {
        GestureHandler access$self = self();
        access$self.interactionController = gestureHandlerInteractionController;
        return access$self;
    }

    public final GestureHandler setManualActivation(boolean z) {
        GestureHandler access$self = self();
        access$self.manualActivation = z;
        return access$self;
    }

    public final GestureHandler setShouldCancelWhenOutside(boolean z) {
        GestureHandler access$self = self();
        access$self.shouldCancelWhenOutside = z;
        return access$self;
    }

    public final int getActivationIndex() {
        return this.activationIndex;
    }

    public final void setActivationIndex(int i) {
        this.activationIndex = i;
    }

    public final boolean isActive() {
        return this.isActive;
    }

    public final void setActive(boolean z) {
        this.isActive = z;
    }

    public final boolean isAwaiting() {
        return this.isAwaiting;
    }

    public final void setAwaiting(boolean z) {
        this.isAwaiting = z;
    }

    public final boolean getShouldResetProgress() {
        return this.shouldResetProgress;
    }

    public final void setShouldResetProgress(boolean z) {
        this.shouldResetProgress = z;
    }

    public void dispatchStateChange(int i, int i2) {
        OnTouchEventListener onTouchEventListener2 = this.onTouchEventListener;
        if (onTouchEventListener2 != null) {
            onTouchEventListener2.onStateChange(self(), i, i2);
        }
    }

    public void dispatchHandlerUpdate(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        OnTouchEventListener onTouchEventListener2 = this.onTouchEventListener;
        if (onTouchEventListener2 != null) {
            onTouchEventListener2.onHandlerUpdate(self(), motionEvent);
        }
    }

    public void dispatchTouchEvent() {
        OnTouchEventListener onTouchEventListener2;
        if (this.changedTouchesPayload != null && (onTouchEventListener2 = this.onTouchEventListener) != null) {
            onTouchEventListener2.onTouchEvent(self());
        }
    }

    public void resetConfig() {
        this.needsPointerData = false;
        this.manualActivation = false;
        this.shouldCancelWhenOutside = false;
        this.isEnabled = true;
        this.hitSlop = null;
    }

    public final boolean hasCommonPointers(GestureHandler gestureHandler) {
        Intrinsics.checkNotNullParameter(gestureHandler, "other");
        int length = this.trackedPointerIDs.length;
        for (int i = 0; i < length; i++) {
            if (this.trackedPointerIDs[i] != -1 && gestureHandler.trackedPointerIDs[i] != -1) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static final void setEnabled$lambda$3$lambda$2(GestureHandler gestureHandler) {
        gestureHandler.cancel();
    }

    public final GestureHandler setMouseButton(int i) {
        this.mouseButton = i;
        return this;
    }

    public final void prepare(View view2, GestureHandlerOrchestrator gestureHandlerOrchestrator) {
        if (this.view == null && this.orchestrator == null) {
            Arrays.fill(this.trackedPointerIDs, -1);
            this.trackedPointersIDsCount = 0;
            this.state = 0;
            this.view = view2;
            this.orchestrator = gestureHandlerOrchestrator;
            View view3 = null;
            Activity activity = getActivity(view2 != null ? view2.getContext() : null);
            if (activity != null) {
                view3 = activity.findViewById(16908290);
            }
            if (view3 != null) {
                view3.getLocationOnScreen(this.windowOffset);
            } else {
                int[] iArr = this.windowOffset;
                iArr[0] = 0;
                iArr[1] = 0;
            }
            onPrepare();
            return;
        }
        throw new IllegalStateException("Already prepared or hasn't been reset");
    }

    private final Activity getActivity(Context context) {
        if (context instanceof ReactContext) {
            return ((ReactContext) context).getCurrentActivity();
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return getActivity(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    private final int findNextLocalPointerId() {
        int[] iArr;
        int i = 0;
        while (i < this.trackedPointersIDsCount) {
            int i2 = 0;
            while (true) {
                iArr = this.trackedPointerIDs;
                if (i2 < iArr.length && iArr[i2] != i) {
                    i2++;
                }
            }
            if (i2 == iArr.length) {
                return i;
            }
            i++;
        }
        return i;
    }

    public final void startTrackingPointer(int i) {
        int[] iArr = this.trackedPointerIDs;
        if (iArr[i] == -1) {
            iArr[i] = findNextLocalPointerId();
            this.trackedPointersIDsCount++;
        }
    }

    public final void stopTrackingPointer(int i) {
        int[] iArr = this.trackedPointerIDs;
        if (iArr[i] != -1) {
            iArr[i] = -1;
            this.trackedPointersIDsCount--;
        }
    }

    private final boolean needAdapt(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() != this.trackedPointersIDsCount) {
            return true;
        }
        int length = this.trackedPointerIDs.length;
        for (int i = 0; i < length; i++) {
            int i2 = this.trackedPointerIDs[i];
            if (i2 != -1 && i2 != i) {
                return true;
            }
        }
        return false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v0, resolved type: android.view.MotionEvent$PointerProperties[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v1, resolved type: android.view.MotionEvent$PointerProperties[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v2, resolved type: android.view.MotionEvent$PointerProperties[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: android.view.MotionEvent$PointerCoords} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v4, resolved type: android.view.MotionEvent$PointerProperties[]} */
    /* JADX WARNING: type inference failed for: r9v3, types: [android.view.MotionEvent$PointerCoords[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x00b6 A[EDGE_INSN: B:77:0x00b6->B:42:0x00b6 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final android.view.MotionEvent adaptEvent(android.view.MotionEvent r26) {
        /*
            r25 = this;
            r1 = r25
            r2 = r26
            boolean r0 = r25.needAdapt(r26)
            if (r0 != 0) goto L_0x000b
            return r2
        L_0x000b:
            int r0 = r26.getActionMasked()
            r3 = 2
            r4 = 5
            r5 = 0
            r6 = -1
            r7 = 1
            if (r0 == 0) goto L_0x0036
            r8 = 6
            if (r0 == r7) goto L_0x0020
            if (r0 == r4) goto L_0x0036
            if (r0 == r8) goto L_0x0020
            r3 = r0
            r0 = r6
            goto L_0x004b
        L_0x0020:
            int r0 = r26.getActionIndex()
            int r4 = r2.getPointerId(r0)
            int[] r9 = r1.trackedPointerIDs
            r4 = r9[r4]
            if (r4 == r6) goto L_0x004b
            int r3 = r1.trackedPointersIDsCount
            if (r3 != r7) goto L_0x0034
            r3 = r7
            goto L_0x004b
        L_0x0034:
            r3 = r8
            goto L_0x004b
        L_0x0036:
            int r0 = r26.getActionIndex()
            int r8 = r2.getPointerId(r0)
            int[] r9 = r1.trackedPointerIDs
            r8 = r9[r8]
            if (r8 == r6) goto L_0x004b
            int r3 = r1.trackedPointersIDsCount
            if (r3 != r7) goto L_0x004a
            r3 = r5
            goto L_0x004b
        L_0x004a:
            r3 = r4
        L_0x004b:
            com.swmansion.gesturehandler.core.GestureHandler$Companion r4 = Companion
            int r7 = r1.trackedPointersIDsCount
            r4.initPointerProps(r7)
            float r4 = r26.getRawX()
            float r7 = r26.getX()
            float r4 = r4 - r7
            float r7 = r26.getRawY()
            float r8 = r26.getY()
            float r7 = r7 - r8
            r2.offsetLocation(r4, r7)
            int r8 = r26.getPointerCount()
            r13 = r3
            r14 = r5
        L_0x006d:
            java.lang.String r3 = "pointerCoords"
            java.lang.String r9 = "pointerProps"
            r10 = 0
            if (r5 >= r8) goto L_0x00b6
            int r11 = r2.getPointerId(r5)
            int[] r12 = r1.trackedPointerIDs
            r12 = r12[r11]
            if (r12 == r6) goto L_0x00b3
            android.view.MotionEvent$PointerProperties[] r12 = pointerProps
            if (r12 != 0) goto L_0x0086
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r9)
            r12 = r10
        L_0x0086:
            r12 = r12[r14]
            r2.getPointerProperties(r5, r12)
            android.view.MotionEvent$PointerProperties[] r12 = pointerProps
            if (r12 != 0) goto L_0x0093
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r9)
            r12 = r10
        L_0x0093:
            r9 = r12[r14]
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)
            int[] r12 = r1.trackedPointerIDs
            r11 = r12[r11]
            r9.id = r11
            android.view.MotionEvent$PointerCoords[] r9 = pointerCoords
            if (r9 != 0) goto L_0x00a6
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            goto L_0x00a7
        L_0x00a6:
            r10 = r9
        L_0x00a7:
            r3 = r10[r14]
            r2.getPointerCoords(r5, r3)
            if (r5 != r0) goto L_0x00b1
            int r3 = r14 << 8
            r13 = r13 | r3
        L_0x00b1:
            int r14 = r14 + 1
        L_0x00b3:
            int r5 = r5 + 1
            goto L_0x006d
        L_0x00b6:
            android.view.MotionEvent$PointerProperties[] r0 = pointerProps
            if (r0 != 0) goto L_0x00be
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r9)
            r0 = r10
        L_0x00be:
            int r0 = r0.length
            if (r0 != 0) goto L_0x00c2
            goto L_0x00cd
        L_0x00c2:
            android.view.MotionEvent$PointerCoords[] r0 = pointerCoords
            if (r0 != 0) goto L_0x00ca
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r0 = r10
        L_0x00ca:
            int r0 = r0.length
            if (r0 != 0) goto L_0x00ff
        L_0x00cd:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "pointerCoords.size="
            r2.append(r4)
            android.view.MotionEvent$PointerCoords[] r4 = pointerCoords
            if (r4 != 0) goto L_0x00e1
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r4 = r10
        L_0x00e1:
            int r3 = r4.length
            r2.append(r3)
            java.lang.String r3 = ", pointerProps.size="
            r2.append(r3)
            android.view.MotionEvent$PointerProperties[] r3 = pointerProps
            if (r3 != 0) goto L_0x00f2
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r9)
            goto L_0x00f3
        L_0x00f2:
            r10 = r3
        L_0x00f3:
            int r3 = r10.length
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        L_0x00ff:
            long r5 = r26.getDownTime()     // Catch:{ IllegalArgumentException -> 0x0110 }
            long r11 = r26.getEventTime()     // Catch:{ IllegalArgumentException -> 0x0110 }
            android.view.MotionEvent$PointerProperties[] r0 = pointerProps     // Catch:{ IllegalArgumentException -> 0x0110 }
            if (r0 != 0) goto L_0x0112
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r9)     // Catch:{ IllegalArgumentException -> 0x0110 }
            r15 = r10
            goto L_0x0113
        L_0x0110:
            r0 = move-exception
            goto L_0x014d
        L_0x0112:
            r15 = r0
        L_0x0113:
            android.view.MotionEvent$PointerCoords[] r0 = pointerCoords     // Catch:{ IllegalArgumentException -> 0x0110 }
            if (r0 != 0) goto L_0x011d
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)     // Catch:{ IllegalArgumentException -> 0x0110 }
            r16 = r10
            goto L_0x011f
        L_0x011d:
            r16 = r0
        L_0x011f:
            int r17 = r26.getMetaState()     // Catch:{ IllegalArgumentException -> 0x0110 }
            int r18 = r26.getButtonState()     // Catch:{ IllegalArgumentException -> 0x0110 }
            float r19 = r26.getXPrecision()     // Catch:{ IllegalArgumentException -> 0x0110 }
            float r20 = r26.getYPrecision()     // Catch:{ IllegalArgumentException -> 0x0110 }
            int r21 = r26.getDeviceId()     // Catch:{ IllegalArgumentException -> 0x0110 }
            int r22 = r26.getEdgeFlags()     // Catch:{ IllegalArgumentException -> 0x0110 }
            int r23 = r26.getSource()     // Catch:{ IllegalArgumentException -> 0x0110 }
            int r24 = r26.getFlags()     // Catch:{ IllegalArgumentException -> 0x0110 }
            r9 = r5
            android.view.MotionEvent r0 = android.view.MotionEvent.obtain(r9, r11, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24)     // Catch:{ IllegalArgumentException -> 0x0110 }
            float r3 = -r4
            float r4 = -r7
            r2.offsetLocation(r3, r4)
            r0.offsetLocation(r3, r4)
            return r0
        L_0x014d:
            com.swmansion.gesturehandler.core.GestureHandler$AdaptEventException r3 = new com.swmansion.gesturehandler.core.GestureHandler$AdaptEventException
            r3.<init>(r1, r2, r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.core.GestureHandler.adaptEvent(android.view.MotionEvent):android.view.MotionEvent");
    }

    public static final class AdaptEventException extends Exception {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public AdaptEventException(GestureHandler gestureHandler, MotionEvent motionEvent, IllegalArgumentException illegalArgumentException) {
            super(StringsKt.trimIndent("\n    handler: " + Reflection.getOrCreateKotlinClass(gestureHandler.getClass()).getSimpleName() + "\n    state: " + gestureHandler.getState() + "\n    view: " + gestureHandler.getView() + "\n    orchestrator: " + gestureHandler.getOrchestrator() + "\n    isEnabled: " + gestureHandler.isEnabled() + "\n    isActive: " + gestureHandler.isActive() + "\n    isAwaiting: " + gestureHandler.isAwaiting() + "\n    trackedPointersCount: " + gestureHandler.trackedPointersIDsCount + "\n    trackedPointers: " + ArraysKt.joinToString$default(gestureHandler.trackedPointerIDs, (CharSequence) ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null) + "\n    while handling event: " + motionEvent + "\n    "), illegalArgumentException);
            Intrinsics.checkNotNullParameter(gestureHandler, "handler");
            Intrinsics.checkNotNullParameter(motionEvent, "event");
            Intrinsics.checkNotNullParameter(illegalArgumentException, "e");
        }
    }

    public final void handle(MotionEvent motionEvent, MotionEvent motionEvent2) {
        int i;
        Intrinsics.checkNotNullParameter(motionEvent, "transformedEvent");
        Intrinsics.checkNotNullParameter(motionEvent2, "sourceEvent");
        if (this.isEnabled && (i = this.state) != 3 && i != 1 && i != 5 && this.trackedPointersIDsCount >= 1) {
            try {
                MotionEvent[] motionEventArr = {adaptEvent(motionEvent), adaptEvent(motionEvent2)};
                MotionEvent motionEvent3 = motionEventArr[0];
                MotionEvent motionEvent4 = motionEventArr[1];
                this.x = motionEvent3.getX();
                this.y = motionEvent3.getY();
                this.numberOfPointers = motionEvent3.getPointerCount();
                boolean isWithinBounds2 = isWithinBounds(this.view, this.x, this.y);
                this.isWithinBounds = isWithinBounds2;
                if (!this.shouldCancelWhenOutside || isWithinBounds2) {
                    GestureUtils gestureUtils = GestureUtils.INSTANCE;
                    this.lastAbsolutePositionX = gestureUtils.getLastPointerX(motionEvent3, true);
                    this.lastAbsolutePositionY = gestureUtils.getLastPointerY(motionEvent3, true);
                    this.lastEventOffsetX = motionEvent3.getRawX() - motionEvent3.getX();
                    this.lastEventOffsetY = motionEvent3.getRawY() - motionEvent3.getY();
                    if (motionEvent2.getAction() == 0 || motionEvent2.getAction() == 9 || motionEvent2.getAction() == 7) {
                        setPointerType(motionEvent2);
                    }
                    if (motionEvent2.getAction() == 9 || motionEvent2.getAction() == 7 || motionEvent2.getAction() == 10) {
                        onHandleHover(motionEvent3, motionEvent4);
                    } else {
                        onHandle(motionEvent3, motionEvent4);
                    }
                    if (!Intrinsics.areEqual((Object) motionEvent3, (Object) motionEvent)) {
                        motionEvent3.recycle();
                    }
                    if (!Intrinsics.areEqual((Object) motionEvent4, (Object) motionEvent2)) {
                        motionEvent4.recycle();
                        return;
                    }
                    return;
                }
                int i2 = this.state;
                if (i2 == 4) {
                    cancel();
                } else if (i2 == 2) {
                    fail();
                }
            } catch (AdaptEventException unused) {
                fail();
            }
        }
    }

    private final void dispatchTouchDownEvent(MotionEvent motionEvent, MotionEvent motionEvent2) {
        this.changedTouchesPayload = null;
        this.touchEventType = 1;
        int pointerId = motionEvent.getPointerId(motionEvent.getActionIndex());
        this.trackedPointers[pointerId] = new PointerData(pointerId, motionEvent.getX(motionEvent.getActionIndex()), motionEvent.getY(motionEvent.getActionIndex()), (motionEvent2.getX(motionEvent.getActionIndex()) + (motionEvent2.getRawX() - motionEvent2.getX())) - ((float) this.windowOffset[0]), (motionEvent2.getY(motionEvent.getActionIndex()) + (motionEvent2.getRawY() - motionEvent2.getY())) - ((float) this.windowOffset[1]));
        this.trackedPointersCount++;
        PointerData pointerData = this.trackedPointers[pointerId];
        Intrinsics.checkNotNull(pointerData);
        addChangedPointer(pointerData);
        extractAllPointersData();
        dispatchTouchEvent();
    }

    private final void dispatchTouchUpEvent(MotionEvent motionEvent, MotionEvent motionEvent2) {
        extractAllPointersData();
        this.changedTouchesPayload = null;
        this.touchEventType = 3;
        int pointerId = motionEvent.getPointerId(motionEvent.getActionIndex());
        this.trackedPointers[pointerId] = new PointerData(pointerId, motionEvent.getX(motionEvent.getActionIndex()), motionEvent.getY(motionEvent.getActionIndex()), (motionEvent2.getX(motionEvent.getActionIndex()) + (motionEvent2.getRawX() - motionEvent2.getX())) - ((float) this.windowOffset[0]), (motionEvent2.getY(motionEvent.getActionIndex()) + (motionEvent2.getRawY() - motionEvent2.getY())) - ((float) this.windowOffset[1]));
        PointerData pointerData = this.trackedPointers[pointerId];
        Intrinsics.checkNotNull(pointerData);
        addChangedPointer(pointerData);
        this.trackedPointers[pointerId] = null;
        this.trackedPointersCount--;
        dispatchTouchEvent();
    }

    private final void dispatchTouchMoveEvent(MotionEvent motionEvent, MotionEvent motionEvent2) {
        this.changedTouchesPayload = null;
        this.touchEventType = 2;
        float rawX = motionEvent2.getRawX() - motionEvent2.getX();
        float rawY = motionEvent2.getRawY() - motionEvent2.getY();
        int pointerCount = motionEvent.getPointerCount();
        int i = 0;
        for (int i2 = 0; i2 < pointerCount; i2++) {
            PointerData pointerData = this.trackedPointers[motionEvent.getPointerId(i2)];
            if (!(pointerData == null || (pointerData.getX() == motionEvent.getX(i2) && pointerData.getY() == motionEvent.getY(i2)))) {
                pointerData.setX(motionEvent.getX(i2));
                pointerData.setY(motionEvent.getY(i2));
                pointerData.setAbsoluteX((motionEvent2.getX(i2) + rawX) - ((float) this.windowOffset[0]));
                pointerData.setAbsoluteY((motionEvent2.getY(i2) + rawY) - ((float) this.windowOffset[1]));
                addChangedPointer(pointerData);
                i++;
            }
        }
        if (i > 0) {
            extractAllPointersData();
            dispatchTouchEvent();
        }
    }

    public final void updatePointerData(MotionEvent motionEvent, MotionEvent motionEvent2) {
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        Intrinsics.checkNotNullParameter(motionEvent2, "sourceEvent");
        if (motionEvent.getActionMasked() == 0 || motionEvent.getActionMasked() == 5) {
            dispatchTouchDownEvent(motionEvent, motionEvent2);
            dispatchTouchMoveEvent(motionEvent, motionEvent2);
        } else if (motionEvent.getActionMasked() == 1 || motionEvent.getActionMasked() == 6) {
            dispatchTouchMoveEvent(motionEvent, motionEvent2);
            dispatchTouchUpEvent(motionEvent, motionEvent2);
        } else if (motionEvent.getActionMasked() == 2) {
            dispatchTouchMoveEvent(motionEvent, motionEvent2);
        }
    }

    private final void extractAllPointersData() {
        this.allTouchesPayload = null;
        for (PointerData pointerData : this.trackedPointers) {
            if (pointerData != null) {
                addPointerToAll(pointerData);
            }
        }
    }

    private final void cancelPointers() {
        this.touchEventType = 4;
        this.changedTouchesPayload = null;
        extractAllPointersData();
        for (PointerData pointerData : this.trackedPointers) {
            if (pointerData != null) {
                addChangedPointer(pointerData);
            }
        }
        this.trackedPointersCount = 0;
        ArraysKt.fill$default((Object[]) this.trackedPointers, (Object) null, 0, 0, 6, (Object) null);
        dispatchTouchEvent();
    }

    private final void addChangedPointer(PointerData pointerData) {
        if (this.changedTouchesPayload == null) {
            this.changedTouchesPayload = Arguments.createArray();
        }
        WritableArray writableArray = this.changedTouchesPayload;
        Intrinsics.checkNotNull(writableArray);
        writableArray.pushMap(createPointerData(pointerData));
    }

    private final void addPointerToAll(PointerData pointerData) {
        if (this.allTouchesPayload == null) {
            this.allTouchesPayload = Arguments.createArray();
        }
        WritableArray writableArray = this.allTouchesPayload;
        Intrinsics.checkNotNull(writableArray);
        writableArray.pushMap(createPointerData(pointerData));
    }

    private final WritableMap createPointerData(PointerData pointerData) {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("id", pointerData.getPointerId());
        createMap.putDouble("x", (double) PixelUtil.toDIPFromPixel(pointerData.getX()));
        createMap.putDouble("y", (double) PixelUtil.toDIPFromPixel(pointerData.getY()));
        createMap.putDouble("absoluteX", (double) PixelUtil.toDIPFromPixel(pointerData.getAbsoluteX()));
        createMap.putDouble("absoluteY", (double) PixelUtil.toDIPFromPixel(pointerData.getAbsoluteY()));
        return createMap;
    }

    public final WritableArray consumeChangedTouchesPayload() {
        WritableArray writableArray = this.changedTouchesPayload;
        this.changedTouchesPayload = null;
        return writableArray;
    }

    public final WritableArray consumeAllTouchesPayload() {
        WritableArray writableArray = this.allTouchesPayload;
        this.allTouchesPayload = null;
        return writableArray;
    }

    private final void moveToState(int i) {
        UiThreadUtil.assertOnUiThread();
        if (this.state != i) {
            if (this.trackedPointersCount > 0 && (i == 5 || i == 3 || i == 1)) {
                cancelPointers();
            }
            int i2 = this.state;
            this.state = i;
            if (i == 4) {
                short s = nextEventCoalescingKey;
                nextEventCoalescingKey = (short) (s + 1);
                this.eventCoalescingKey = s;
            }
            GestureHandlerOrchestrator gestureHandlerOrchestrator = this.orchestrator;
            Intrinsics.checkNotNull(gestureHandlerOrchestrator);
            gestureHandlerOrchestrator.onHandlerStateChange(this, i, i2);
            onStateChange(i, i2);
        }
    }

    public final boolean wantEvents() {
        int i;
        if (!this.isEnabled || (i = this.state) == 1 || i == 3 || i == 5 || this.trackedPointersIDsCount <= 0) {
            return false;
        }
        return true;
    }

    public boolean shouldRequireToWaitForFailure(GestureHandler gestureHandler) {
        GestureHandlerInteractionController gestureHandlerInteractionController;
        Intrinsics.checkNotNullParameter(gestureHandler, "handler");
        if (gestureHandler == this || (gestureHandlerInteractionController = this.interactionController) == null) {
            return false;
        }
        return gestureHandlerInteractionController.shouldRequireHandlerToWaitForFailure(this, gestureHandler);
    }

    public final boolean shouldWaitForHandlerFailure(GestureHandler gestureHandler) {
        GestureHandlerInteractionController gestureHandlerInteractionController;
        Intrinsics.checkNotNullParameter(gestureHandler, "handler");
        if (gestureHandler == this || (gestureHandlerInteractionController = this.interactionController) == null) {
            return false;
        }
        return gestureHandlerInteractionController.shouldWaitForHandlerFailure(this, gestureHandler);
    }

    public boolean shouldRecognizeSimultaneously(GestureHandler gestureHandler) {
        Intrinsics.checkNotNullParameter(gestureHandler, "handler");
        if (gestureHandler == this) {
            return true;
        }
        GestureHandlerInteractionController gestureHandlerInteractionController = this.interactionController;
        if (gestureHandlerInteractionController != null) {
            return gestureHandlerInteractionController.shouldRecognizeSimultaneously(this, gestureHandler);
        }
        return false;
    }

    public boolean shouldBeCancelledBy(GestureHandler gestureHandler) {
        GestureHandlerInteractionController gestureHandlerInteractionController;
        Intrinsics.checkNotNullParameter(gestureHandler, "handler");
        if (gestureHandler == this || (gestureHandlerInteractionController = this.interactionController) == null) {
            return false;
        }
        return gestureHandlerInteractionController.shouldHandlerBeCancelledBy(this, gestureHandler);
    }

    public final boolean isWithinBounds(View view2, float f, float f2) {
        float f3;
        View view3 = view2;
        float f4 = f;
        float f5 = f2;
        RNSVGHitTester.Companion companion = RNSVGHitTester.Companion;
        Intrinsics.checkNotNull(view2);
        if (companion.isSvgElement(view3)) {
            return companion.hitTest(view3, f4, f5);
        }
        float width = (float) view2.getWidth();
        float height = (float) view2.getHeight();
        float[] fArr = this.hitSlop;
        float f6 = 0.0f;
        if (fArr != null) {
            float f7 = fArr[0];
            float f8 = fArr[1];
            float f9 = fArr[2];
            float f10 = fArr[3];
            Companion companion2 = Companion;
            float f11 = companion2.hitSlopSet(f7) ? 0.0f - f7 : 0.0f;
            if (companion2.hitSlopSet(f8)) {
                f6 = 0.0f - f8;
            }
            if (companion2.hitSlopSet(f9)) {
                width += f9;
            }
            if (companion2.hitSlopSet(f10)) {
                height += f10;
            }
            float f12 = fArr[4];
            float f13 = fArr[5];
            if (companion2.hitSlopSet(f12)) {
                if (!companion2.hitSlopSet(f7)) {
                    f11 = width - f12;
                } else if (!companion2.hitSlopSet(f9)) {
                    width = f12 + f11;
                }
            }
            if (companion2.hitSlopSet(f13)) {
                if (!companion2.hitSlopSet(f8)) {
                    f6 = height - f13;
                } else if (!companion2.hitSlopSet(f10)) {
                    height = f13 + f6;
                }
            }
            f3 = f6;
            f6 = f11;
        } else {
            f3 = 0.0f;
        }
        if (f6 > f4 || f4 > width || f3 > f5 || f5 > height) {
            return false;
        }
        return true;
    }

    public final void cancel() {
        int i = this.state;
        if (i == 4 || i == 0 || i == 2 || this.isAwaiting) {
            onCancel();
            moveToState(3);
        }
    }

    public final void fail() {
        int i = this.state;
        if (i == 4 || i == 0 || i == 2) {
            moveToState(1);
        }
    }

    public final void activate() {
        activate(false);
    }

    public void activate(boolean z) {
        if (!this.manualActivation || z) {
            int i = this.state;
            if (i == 0 || i == 2) {
                moveToState(4);
            }
        }
    }

    public final void begin() {
        if (this.state == 0) {
            moveToState(2);
        }
    }

    public final void end() {
        int i = this.state;
        if (i == 2 || i == 4) {
            moveToState(5);
        }
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x002f A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001a  */
    public final boolean isDescendantOf(com.swmansion.gesturehandler.core.GestureHandler r4) {
        /*
            r3 = this;
            java.lang.String r0 = "of"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            android.view.View r0 = r3.view
            r1 = 0
            if (r0 == 0) goto L_0x000f
            android.view.ViewParent r0 = r0.getParent()
            goto L_0x0010
        L_0x000f:
            r0 = r1
        L_0x0010:
            boolean r2 = r0 instanceof android.view.View
            if (r2 == 0) goto L_0x0017
            android.view.View r0 = (android.view.View) r0
            goto L_0x0018
        L_0x0017:
            r0 = r1
        L_0x0018:
            if (r0 == 0) goto L_0x002f
            android.view.View r2 = r4.view
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r2)
            if (r2 == 0) goto L_0x0024
            r4 = 1
            return r4
        L_0x0024:
            android.view.ViewParent r0 = r0.getParent()
            boolean r2 = r0 instanceof android.view.View
            if (r2 == 0) goto L_0x0017
            android.view.View r0 = (android.view.View) r0
            goto L_0x0018
        L_0x002f:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.core.GestureHandler.isDescendantOf(com.swmansion.gesturehandler.core.GestureHandler):boolean");
    }

    private final boolean isButtonInConfig(int i) {
        int i2 = this.mouseButton;
        return i2 == 0 ? i == 1 : (i & i2) != 0;
    }

    /* access modifiers changed from: protected */
    public final boolean shouldActivateWithMouse(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "sourceEvent");
        if (motionEvent.getToolType(0) == 3) {
            if (motionEvent.getAction() == 0 || motionEvent.getAction() == 1 || motionEvent.getAction() == 6 || motionEvent.getAction() == 5 || (motionEvent.getAction() != 2 && !isButtonInConfig(motionEvent.getActionButton()))) {
                return false;
            }
            return motionEvent.getAction() != 2 || isButtonInConfig(motionEvent.getButtonState());
        }
    }

    /* access modifiers changed from: protected */
    public final PointF transformPoint(PointF pointF) {
        PointF transformPointToViewCoords;
        Intrinsics.checkNotNullParameter(pointF, "point");
        GestureHandlerOrchestrator gestureHandlerOrchestrator = this.orchestrator;
        if (gestureHandlerOrchestrator != null && (transformPointToViewCoords = gestureHandlerOrchestrator.transformPointToViewCoords(this.view, pointF)) != null) {
            return transformPointToViewCoords;
        }
        pointF.x = Float.NaN;
        pointF.y = Float.NaN;
        return pointF;
    }

    public final void reset() {
        this.view = null;
        this.orchestrator = null;
        Arrays.fill(this.trackedPointerIDs, -1);
        this.trackedPointersIDsCount = 0;
        this.trackedPointersCount = 0;
        ArraysKt.fill$default((Object[]) this.trackedPointers, (Object) null, 0, 0, 6, (Object) null);
        this.touchEventType = 0;
        onReset();
    }

    public final void withMarkedAsInBounds(Function0 function0) {
        Intrinsics.checkNotNullParameter(function0, "closure");
        this.isWithinBounds = true;
        function0.invoke();
        this.isWithinBounds = false;
    }

    private final void setPointerType(MotionEvent motionEvent) {
        int toolType = motionEvent.getToolType(motionEvent.getActionIndex());
        int i = 1;
        if (toolType == 1) {
            i = 0;
        } else if (toolType != 2) {
            i = 3;
            if (toolType == 3) {
                i = 2;
            }
        }
        this.pointerType = i;
    }

    public final GestureHandler setOnTouchEventListener(OnTouchEventListener onTouchEventListener2) {
        this.onTouchEventListener = onTouchEventListener2;
        return this;
    }

    public String toString() {
        String str;
        View view2 = this.view;
        if (view2 == null) {
            str = null;
        } else {
            Intrinsics.checkNotNull(view2);
            str = view2.getClass().getSimpleName();
        }
        return getClass().getSimpleName() + "@[" + this.tag + "]:" + str;
    }

    public final float getLastRelativePositionX() {
        return this.lastAbsolutePositionX;
    }

    public final float getLastRelativePositionY() {
        return this.lastAbsolutePositionY;
    }

    public final float getLastPositionInWindowX() {
        return (this.lastAbsolutePositionX + this.lastEventOffsetX) - ((float) this.windowOffset[0]);
    }

    public final float getLastPositionInWindowY() {
        return (this.lastAbsolutePositionY + this.lastEventOffsetY) - ((float) this.windowOffset[1]);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final void initPointerProps(int i) {
            if (GestureHandler.pointerProps == null) {
                GestureHandler.pointerProps = new MotionEvent.PointerProperties[12];
                GestureHandler.pointerCoords = new MotionEvent.PointerCoords[12];
            }
            while (i > 0) {
                MotionEvent.PointerProperties[] access$getPointerProps$cp = GestureHandler.pointerProps;
                MotionEvent.PointerCoords[] pointerCoordsArr = null;
                if (access$getPointerProps$cp == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("pointerProps");
                    access$getPointerProps$cp = null;
                }
                int i2 = i - 1;
                if (access$getPointerProps$cp[i2] == null) {
                    MotionEvent.PointerProperties[] access$getPointerProps$cp2 = GestureHandler.pointerProps;
                    if (access$getPointerProps$cp2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("pointerProps");
                        access$getPointerProps$cp2 = null;
                    }
                    access$getPointerProps$cp2[i2] = new MotionEvent.PointerProperties();
                    MotionEvent.PointerCoords[] access$getPointerCoords$cp = GestureHandler.pointerCoords;
                    if (access$getPointerCoords$cp == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("pointerCoords");
                    } else {
                        pointerCoordsArr = access$getPointerCoords$cp;
                    }
                    pointerCoordsArr[i2] = new MotionEvent.PointerCoords();
                    i--;
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: private */
        public final boolean hitSlopSet(float f) {
            return !Float.isNaN(f);
        }
    }

    private static final class PointerData {
        private float absoluteX;
        private float absoluteY;
        private final int pointerId;
        private float x;
        private float y;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PointerData)) {
                return false;
            }
            PointerData pointerData = (PointerData) obj;
            return this.pointerId == pointerData.pointerId && Float.compare(this.x, pointerData.x) == 0 && Float.compare(this.y, pointerData.y) == 0 && Float.compare(this.absoluteX, pointerData.absoluteX) == 0 && Float.compare(this.absoluteY, pointerData.absoluteY) == 0;
        }

        public int hashCode() {
            return (((((((Integer.hashCode(this.pointerId) * 31) + Float.hashCode(this.x)) * 31) + Float.hashCode(this.y)) * 31) + Float.hashCode(this.absoluteX)) * 31) + Float.hashCode(this.absoluteY);
        }

        public String toString() {
            return "PointerData(pointerId=" + this.pointerId + ", x=" + this.x + ", y=" + this.y + ", absoluteX=" + this.absoluteX + ", absoluteY=" + this.absoluteY + ')';
        }

        public PointerData(int i, float f, float f2, float f3, float f4) {
            this.pointerId = i;
            this.x = f;
            this.y = f2;
            this.absoluteX = f3;
            this.absoluteY = f4;
        }

        public final int getPointerId() {
            return this.pointerId;
        }

        public final float getX() {
            return this.x;
        }

        public final void setX(float f) {
            this.x = f;
        }

        public final float getY() {
            return this.y;
        }

        public final void setY(float f) {
            this.y = f;
        }

        public final float getAbsoluteX() {
            return this.absoluteX;
        }

        public final void setAbsoluteX(float f) {
            this.absoluteX = f;
        }

        public final float getAbsoluteY() {
            return this.absoluteY;
        }

        public final void setAbsoluteY(float f) {
            this.absoluteY = f;
        }
    }
}
