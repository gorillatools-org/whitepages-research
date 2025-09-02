package com.swmansion.gesturehandler.core;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class GestureHandlerOrchestrator {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final Comparator handlersComparator = new GestureHandlerOrchestrator$$ExternalSyntheticLambda0();
    /* access modifiers changed from: private */
    public static final Matrix inverseMatrix = new Matrix();
    /* access modifiers changed from: private */
    public static final float[] matrixTransformCoords = new float[2];
    private static final float[] tempCoords = new float[2];
    private static final PointF tempPoint = new PointF();
    private int activationIndex;
    private final ArrayList awaitingHandlers = new ArrayList();
    private final HashSet awaitingHandlersTags = new HashSet();
    private boolean finishedHandlersCleanupScheduled;
    private final ArrayList gestureHandlers = new ArrayList();
    private final GestureHandlerRegistry handlerRegistry;
    private int handlingChangeSemaphore;
    private boolean isHandlingTouch;
    private float minimumAlphaForTraversal;
    private final ArrayList preparedHandlers = new ArrayList();
    private final ViewConfigurationHelper viewConfigHelper;
    private final ViewGroup wrapperView;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|5|6|7|8|9|11) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        static {
            /*
                com.swmansion.gesturehandler.core.PointerEventsConfig[] r0 = com.swmansion.gesturehandler.core.PointerEventsConfig.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.swmansion.gesturehandler.core.PointerEventsConfig r1 = com.swmansion.gesturehandler.core.PointerEventsConfig.NONE     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.swmansion.gesturehandler.core.PointerEventsConfig r1 = com.swmansion.gesturehandler.core.PointerEventsConfig.BOX_ONLY     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.swmansion.gesturehandler.core.PointerEventsConfig r1 = com.swmansion.gesturehandler.core.PointerEventsConfig.BOX_NONE     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.swmansion.gesturehandler.core.PointerEventsConfig r1 = com.swmansion.gesturehandler.core.PointerEventsConfig.AUTO     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.core.GestureHandlerOrchestrator.WhenMappings.<clinit>():void");
        }
    }

    public GestureHandlerOrchestrator(ViewGroup viewGroup, GestureHandlerRegistry gestureHandlerRegistry, ViewConfigurationHelper viewConfigurationHelper) {
        Intrinsics.checkNotNullParameter(viewGroup, "wrapperView");
        Intrinsics.checkNotNullParameter(gestureHandlerRegistry, "handlerRegistry");
        Intrinsics.checkNotNullParameter(viewConfigurationHelper, "viewConfigHelper");
        this.wrapperView = viewGroup;
        this.handlerRegistry = gestureHandlerRegistry;
        this.viewConfigHelper = viewConfigurationHelper;
    }

    public final void setMinimumAlphaForTraversal(float f) {
        this.minimumAlphaForTraversal = f;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0015, code lost:
        if (r1 != 7) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onTouchEvent(android.view.MotionEvent r4) {
        /*
            r3 = this;
            java.lang.String r0 = "event"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            r0 = 1
            r3.isHandlingTouch = r0
            int r1 = r4.getActionMasked()
            if (r1 == 0) goto L_0x001c
            r2 = 3
            if (r1 == r2) goto L_0x0018
            r2 = 5
            if (r1 == r2) goto L_0x001c
            r2 = 7
            if (r1 == r2) goto L_0x001c
            goto L_0x001f
        L_0x0018:
            r3.cancelAll()
            goto L_0x001f
        L_0x001c:
            r3.extractGestureHandlers(r4)
        L_0x001f:
            r3.deliverEventToGestureHandlers(r4)
            r4 = 0
            r3.isHandlingTouch = r4
            boolean r4 = r3.finishedHandlersCleanupScheduled
            if (r4 == 0) goto L_0x0030
            int r4 = r3.handlingChangeSemaphore
            if (r4 != 0) goto L_0x0030
            r3.cleanupFinishedHandlers()
        L_0x0030:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.core.GestureHandlerOrchestrator.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public final ArrayList getHandlersForView(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        return this.handlerRegistry.getHandlersForView(view);
    }

    private final void scheduleFinishedHandlersCleanup() {
        if (this.isHandlingTouch || this.handlingChangeSemaphore != 0) {
            this.finishedHandlersCleanupScheduled = true;
        } else {
            cleanupFinishedHandlers();
        }
    }

    private final void cleanupFinishedHandlers() {
        for (GestureHandler gestureHandler : CollectionsKt.asReversedMutable(this.gestureHandlers)) {
            if (Companion.isFinished(gestureHandler.getState()) && !gestureHandler.isAwaiting()) {
                gestureHandler.reset();
                gestureHandler.setActive(false);
                gestureHandler.setAwaiting(false);
                gestureHandler.setActivationIndex(Integer.MAX_VALUE);
            }
        }
        CollectionsKt.removeAll(this.gestureHandlers, new GestureHandlerOrchestrator$$ExternalSyntheticLambda1());
        this.finishedHandlersCleanupScheduled = false;
    }

    /* access modifiers changed from: private */
    public static final boolean cleanupFinishedHandlers$lambda$1(GestureHandler gestureHandler) {
        Intrinsics.checkNotNullParameter(gestureHandler, "it");
        return Companion.isFinished(gestureHandler.getState()) && !gestureHandler.isAwaiting();
    }

    private final boolean hasOtherHandlerToWaitFor(GestureHandler gestureHandler) {
        ArrayList<GestureHandler> arrayList = this.gestureHandlers;
        if (arrayList != null && arrayList.isEmpty()) {
            return false;
        }
        for (GestureHandler gestureHandler2 : arrayList) {
            Companion companion = Companion;
            if (!companion.isFinished(gestureHandler2.getState()) && companion.shouldHandlerWaitForOther(gestureHandler, gestureHandler2)) {
                return true;
            }
        }
        return false;
    }

    private final boolean shouldBeCancelledByFinishedHandler(GestureHandler gestureHandler) {
        ArrayList<GestureHandler> arrayList = this.gestureHandlers;
        if (arrayList != null && arrayList.isEmpty()) {
            return false;
        }
        for (GestureHandler gestureHandler2 : arrayList) {
            if (Companion.shouldHandlerWaitForOther(gestureHandler, gestureHandler2) && gestureHandler2.getState() == 5) {
                return true;
            }
        }
        return false;
    }

    private final boolean shouldBeCancelledByActiveHandler(GestureHandler gestureHandler) {
        ArrayList<GestureHandler> arrayList = this.gestureHandlers;
        if (arrayList != null && arrayList.isEmpty()) {
            return false;
        }
        for (GestureHandler gestureHandler2 : arrayList) {
            if (gestureHandler.hasCommonPointers(gestureHandler2) && gestureHandler2.getState() == 4 && !Companion.canRunSimultaneously(gestureHandler, gestureHandler2) && gestureHandler.isDescendantOf(gestureHandler2)) {
                return true;
            }
        }
        return false;
    }

    private final void tryActivate(GestureHandler gestureHandler) {
        if (shouldBeCancelledByFinishedHandler(gestureHandler) || shouldBeCancelledByActiveHandler(gestureHandler)) {
            gestureHandler.cancel();
        } else if (hasOtherHandlerToWaitFor(gestureHandler)) {
            addAwaitingHandler(gestureHandler);
        } else {
            makeActive(gestureHandler);
            gestureHandler.setAwaiting(false);
        }
    }

    private final void cleanupAwaitingHandlers() {
        for (GestureHandler gestureHandler : CollectionsKt.toList(this.awaitingHandlers)) {
            if (!gestureHandler.isAwaiting()) {
                this.awaitingHandlers.remove(gestureHandler);
                this.awaitingHandlersTags.remove(Integer.valueOf(gestureHandler.getTag()));
            }
        }
    }

    public final void onHandlerStateChange(GestureHandler gestureHandler, int i, int i2) {
        Intrinsics.checkNotNullParameter(gestureHandler, "handler");
        this.handlingChangeSemaphore++;
        if (Companion.isFinished(i)) {
            for (GestureHandler gestureHandler2 : CollectionsKt.toList(this.awaitingHandlers)) {
                if (Companion.shouldHandlerWaitForOther(gestureHandler2, gestureHandler) && this.awaitingHandlersTags.contains(Integer.valueOf(gestureHandler2.getTag()))) {
                    if (i == 5) {
                        gestureHandler2.cancel();
                        if (gestureHandler2.getState() == 5) {
                            gestureHandler2.dispatchStateChange(3, 2);
                        }
                        gestureHandler2.setAwaiting(false);
                    } else {
                        tryActivate(gestureHandler2);
                    }
                }
            }
            cleanupAwaitingHandlers();
        }
        if (i == 4) {
            tryActivate(gestureHandler);
        } else if (i2 == 4 || i2 == 5) {
            if (gestureHandler.isActive()) {
                gestureHandler.dispatchStateChange(i, i2);
            } else if (i2 == 4 && (i == 3 || i == 1)) {
                gestureHandler.dispatchStateChange(i, 2);
            }
        } else if (!(i2 == 0 && i == 3)) {
            gestureHandler.dispatchStateChange(i, i2);
        }
        this.handlingChangeSemaphore--;
        scheduleFinishedHandlersCleanup();
    }

    private final void makeActive(GestureHandler gestureHandler) {
        int state = gestureHandler.getState();
        gestureHandler.setAwaiting(false);
        gestureHandler.setActive(true);
        gestureHandler.setShouldResetProgress(true);
        int i = this.activationIndex;
        this.activationIndex = i + 1;
        gestureHandler.setActivationIndex(i);
        for (GestureHandler gestureHandler2 : CollectionsKt.asReversedMutable(this.gestureHandlers)) {
            if (Companion.shouldHandlerBeCancelledBy(gestureHandler2, gestureHandler)) {
                gestureHandler2.cancel();
            }
        }
        for (GestureHandler gestureHandler3 : CollectionsKt.reversed(this.awaitingHandlers)) {
            if (Companion.shouldHandlerBeCancelledBy(gestureHandler3, gestureHandler)) {
                gestureHandler3.setAwaiting(false);
            }
        }
        cleanupAwaitingHandlers();
        if (state != 1 && state != 3) {
            gestureHandler.dispatchStateChange(4, 2);
            if (state != 4) {
                gestureHandler.dispatchStateChange(5, 4);
                if (state != 5) {
                    gestureHandler.dispatchStateChange(0, 5);
                }
            }
        }
    }

    private final void deliverEventToGestureHandlers(MotionEvent motionEvent) {
        this.preparedHandlers.clear();
        this.preparedHandlers.addAll(this.gestureHandlers);
        CollectionsKt.sortWith(this.preparedHandlers, handlersComparator);
        Iterator it = this.preparedHandlers.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            deliverEventToGestureHandler((GestureHandler) it.next(), motionEvent);
        }
    }

    private final void cancelAll() {
        for (GestureHandler cancel : CollectionsKt.reversed(this.awaitingHandlers)) {
            cancel.cancel();
        }
        this.preparedHandlers.clear();
        this.preparedHandlers.addAll(this.gestureHandlers);
        for (GestureHandler cancel2 : CollectionsKt.reversed(this.gestureHandlers)) {
            cancel2.cancel();
        }
    }

    private final void deliverEventToGestureHandler(GestureHandler gestureHandler, MotionEvent motionEvent) {
        if (!isViewAttachedUnderWrapper(gestureHandler.getView())) {
            gestureHandler.cancel();
        } else if (gestureHandler.wantEvents()) {
            int actionMasked = motionEvent.getActionMasked();
            View view = gestureHandler.getView();
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            Intrinsics.checkNotNullExpressionValue(obtain, "obtain(...)");
            MotionEvent transformEventToViewCoords = transformEventToViewCoords(view, obtain);
            if (gestureHandler.getNeedsPointerData() && gestureHandler.getState() != 0) {
                gestureHandler.updatePointerData(transformEventToViewCoords, motionEvent);
            }
            if (!gestureHandler.isAwaiting() || actionMasked != 2) {
                boolean z = gestureHandler.getState() == 0;
                gestureHandler.handle(transformEventToViewCoords, motionEvent);
                if (gestureHandler.isActive()) {
                    if (gestureHandler.getShouldResetProgress()) {
                        gestureHandler.setShouldResetProgress(false);
                        gestureHandler.resetProgress();
                    }
                    gestureHandler.dispatchHandlerUpdate(transformEventToViewCoords);
                }
                if (gestureHandler.getNeedsPointerData() && z) {
                    gestureHandler.updatePointerData(transformEventToViewCoords, motionEvent);
                }
                if (actionMasked == 1 || actionMasked == 6 || actionMasked == 10) {
                    gestureHandler.stopTrackingPointer(transformEventToViewCoords.getPointerId(transformEventToViewCoords.getActionIndex()));
                }
            }
            transformEventToViewCoords.recycle();
        }
    }

    private final boolean isViewAttachedUnderWrapper(View view) {
        if (view == null) {
            return false;
        }
        if (view == this.wrapperView) {
            return true;
        }
        ViewParent parent = view.getParent();
        while (parent != null && parent != this.wrapperView) {
            parent = parent.getParent();
        }
        if (parent == this.wrapperView) {
            return true;
        }
        return false;
    }

    public final boolean isAnyHandlerActive() {
        ArrayList<GestureHandler> arrayList = this.gestureHandlers;
        if (arrayList != null && arrayList.isEmpty()) {
            return false;
        }
        for (GestureHandler state : arrayList) {
            if (state.getState() == 4) {
                return true;
            }
        }
        return false;
    }

    public final MotionEvent transformEventToViewCoords(View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        if (view == null) {
            return motionEvent;
        }
        ViewParent parent = view.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (!Intrinsics.areEqual((Object) viewGroup, (Object) this.wrapperView)) {
            transformEventToViewCoords(viewGroup, motionEvent);
        }
        if (viewGroup != null) {
            motionEvent.setLocation((motionEvent.getX() + ((float) viewGroup.getScrollX())) - ((float) view.getLeft()), (motionEvent.getY() + ((float) viewGroup.getScrollY())) - ((float) view.getTop()));
        }
        if (!view.getMatrix().isIdentity()) {
            Matrix matrix = view.getMatrix();
            Matrix matrix2 = inverseMatrix;
            matrix.invert(matrix2);
            motionEvent.transform(matrix2);
        }
        return motionEvent;
    }

    public final PointF transformPointToViewCoords(View view, PointF pointF) {
        Intrinsics.checkNotNullParameter(pointF, "point");
        if (view == null) {
            return pointF;
        }
        ViewParent parent = view.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (!Intrinsics.areEqual((Object) viewGroup, (Object) this.wrapperView)) {
            transformPointToViewCoords(viewGroup, pointF);
        }
        if (viewGroup != null) {
            pointF.x += (float) (viewGroup.getScrollX() - view.getLeft());
            pointF.y += (float) (viewGroup.getScrollY() - view.getTop());
        }
        if (!view.getMatrix().isIdentity()) {
            Matrix matrix = view.getMatrix();
            Matrix matrix2 = inverseMatrix;
            matrix.invert(matrix2);
            float[] fArr = tempCoords;
            fArr[0] = pointF.x;
            fArr[1] = pointF.y;
            matrix2.mapPoints(fArr);
            pointF.x = fArr[0];
            pointF.y = fArr[1];
        }
        return pointF;
    }

    private final void addAwaitingHandler(GestureHandler gestureHandler) {
        if (!this.awaitingHandlers.contains(gestureHandler)) {
            this.awaitingHandlers.add(gestureHandler);
            this.awaitingHandlersTags.add(Integer.valueOf(gestureHandler.getTag()));
            gestureHandler.setAwaiting(true);
            int i = this.activationIndex;
            this.activationIndex = i + 1;
            gestureHandler.setActivationIndex(i);
        }
    }

    private final void recordHandlerIfNotPresent(GestureHandler gestureHandler, View view) {
        if (!this.gestureHandlers.contains(gestureHandler)) {
            this.gestureHandlers.add(gestureHandler);
            gestureHandler.setActive(false);
            gestureHandler.setAwaiting(false);
            gestureHandler.setActivationIndex(Integer.MAX_VALUE);
            gestureHandler.prepare(view, this);
        }
    }

    private final boolean isViewOverflowingParent(View view) {
        ViewParent parent = view.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup == null) {
            return false;
        }
        Matrix matrix = view.getMatrix();
        float[] fArr = matrixTransformCoords;
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        matrix.mapPoints(fArr);
        float left = fArr[0] + ((float) view.getLeft());
        float top = fArr[1] + ((float) view.getTop());
        if (left < 0.0f || left + ((float) view.getWidth()) > ((float) viewGroup.getWidth()) || top < 0.0f || top + ((float) view.getHeight()) > ((float) viewGroup.getHeight())) {
            return true;
        }
        return false;
    }

    private final boolean extractAncestorHandlers(View view, float[] fArr, int i) {
        boolean z = false;
        for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) parent;
                ArrayList handlersForView = this.handlerRegistry.getHandlersForView((View) parent);
                if (handlersForView != null) {
                    synchronized (handlersForView) {
                        try {
                            Iterator it = handlersForView.iterator();
                            Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
                            while (it.hasNext()) {
                                GestureHandler gestureHandler = (GestureHandler) it.next();
                                if (gestureHandler.isEnabled() && gestureHandler.isWithinBounds(view, fArr[0], fArr[1])) {
                                    recordHandlerIfNotPresent(gestureHandler, viewGroup);
                                    gestureHandler.startTrackingPointer(i);
                                    z = true;
                                }
                            }
                            Unit unit = Unit.INSTANCE;
                        } finally {
                        }
                    }
                }
            }
        }
        return z;
    }

    private final boolean shouldHandlerSkipHoverEvents(GestureHandler gestureHandler, int i) {
        return !(gestureHandler instanceof HoverGestureHandler) && !(gestureHandler instanceof RNGestureHandlerRootHelper.RootViewGestureHandler) && CollectionsKt.listOf(10, 9, 7).contains(Integer.valueOf(i));
    }

    private final boolean recordViewHandlersForPointer(View view, float[] fArr, int i, MotionEvent motionEvent) {
        boolean z;
        ArrayList handlersForView = this.handlerRegistry.getHandlersForView(view);
        if (handlersForView != null) {
            synchronized (handlersForView) {
                try {
                    Iterator it = handlersForView.iterator();
                    Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
                    z = false;
                    while (it.hasNext()) {
                        GestureHandler gestureHandler = (GestureHandler) it.next();
                        if (gestureHandler.isEnabled()) {
                            if (gestureHandler.isWithinBounds(view, fArr[0], fArr[1])) {
                                if (!shouldHandlerSkipHoverEvents(gestureHandler, motionEvent.getAction())) {
                                    recordHandlerIfNotPresent(gestureHandler, view);
                                    gestureHandler.startTrackingPointer(i);
                                    z = true;
                                }
                            }
                        }
                    }
                    Unit unit = Unit.INSTANCE;
                } finally {
                }
            }
        } else {
            z = false;
        }
        float width = (float) view.getWidth();
        float f = fArr[0];
        if (0.0f <= f && f <= width) {
            float height = (float) view.getHeight();
            float f2 = fArr[1];
            if (0.0f <= f2 && f2 <= height && isViewOverflowingParent(view) && extractAncestorHandlers(view, fArr, i)) {
                return true;
            }
        }
        return z;
    }

    private final void extractGestureHandlers(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        int pointerId = motionEvent.getPointerId(actionIndex);
        float[] fArr = tempCoords;
        fArr[0] = motionEvent.getX(actionIndex);
        fArr[1] = motionEvent.getY(actionIndex);
        traverseWithPointerEvents(this.wrapperView, fArr, pointerId, motionEvent);
        extractGestureHandlers(this.wrapperView, fArr, pointerId, motionEvent);
    }

    private final boolean extractGestureHandlers(ViewGroup viewGroup, float[] fArr, int i, MotionEvent motionEvent) {
        boolean z;
        float[] fArr2 = fArr;
        for (int childCount = viewGroup.getChildCount() - 1; -1 < childCount; childCount--) {
            View childInDrawingOrderAtIndex = this.viewConfigHelper.getChildInDrawingOrderAtIndex(viewGroup, childCount);
            if (canReceiveEvents(childInDrawingOrderAtIndex)) {
                PointF pointF = tempPoint;
                Companion companion = Companion;
                companion.transformPointToChildViewCoords(fArr2[0], fArr2[1], viewGroup, childInDrawingOrderAtIndex, pointF);
                float f = fArr2[0];
                float f2 = fArr2[1];
                fArr2[0] = pointF.x;
                fArr2[1] = pointF.y;
                if (!isClipping(childInDrawingOrderAtIndex) || companion.isTransformedTouchPointInView(fArr2[0], fArr2[1], childInDrawingOrderAtIndex)) {
                    z = traverseWithPointerEvents(childInDrawingOrderAtIndex, fArr2, i, motionEvent);
                } else {
                    int i2 = i;
                    MotionEvent motionEvent2 = motionEvent;
                    z = false;
                }
                fArr2[0] = f;
                fArr2[1] = f2;
                if (z) {
                    return true;
                }
            } else {
                int i3 = i;
                MotionEvent motionEvent3 = motionEvent;
            }
        }
        return false;
    }

    private final boolean traverseWithPointerEvents(View view, float[] fArr, int i, MotionEvent motionEvent) {
        int i2 = WhenMappings.$EnumSwitchMapping$0[this.viewConfigHelper.getPointerEventsConfigForView(view).ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 == 4) {
                        boolean extractGestureHandlers = view instanceof ViewGroup ? extractGestureHandlers((ViewGroup) view, fArr, i, motionEvent) : false;
                        if (recordViewHandlersForPointer(view, fArr, i, motionEvent) || extractGestureHandlers || Companion.shouldHandlerlessViewBecomeTouchTarget(view, fArr)) {
                            return true;
                        }
                    } else {
                        throw new NoWhenBranchMatchedException();
                    }
                } else if (view instanceof ViewGroup) {
                    boolean extractGestureHandlers2 = extractGestureHandlers((ViewGroup) view, fArr, i, motionEvent);
                    if (!extractGestureHandlers2) {
                        return extractGestureHandlers2;
                    }
                    recordViewHandlersForPointer(view, fArr, i, motionEvent);
                    return extractGestureHandlers2;
                } else if (view instanceof EditText) {
                    return recordViewHandlersForPointer(view, fArr, i, motionEvent);
                }
            } else if (recordViewHandlersForPointer(view, fArr, i, motionEvent) || Companion.shouldHandlerlessViewBecomeTouchTarget(view, fArr)) {
                return true;
            }
        }
        return false;
    }

    private final boolean canReceiveEvents(View view) {
        return view.getVisibility() == 0 && view.getAlpha() >= this.minimumAlphaForTraversal;
    }

    private final boolean isClipping(View view) {
        return !(view instanceof ViewGroup) || this.viewConfigHelper.isViewClippingChildren((ViewGroup) view);
    }

    public final void activateNativeHandlersForView(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        ArrayList<GestureHandler> handlersForView = this.handlerRegistry.getHandlersForView(view);
        if (handlersForView != null) {
            for (GestureHandler gestureHandler : handlersForView) {
                if (gestureHandler instanceof NativeViewGestureHandler) {
                    recordHandlerIfNotPresent(gestureHandler, view);
                    ((NativeViewGestureHandler) gestureHandler).withMarkedAsInBounds(new GestureHandlerOrchestrator$$ExternalSyntheticLambda2(gestureHandler));
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static final Unit activateNativeHandlersForView$lambda$14$lambda$13(GestureHandler gestureHandler) {
        NativeViewGestureHandler nativeViewGestureHandler = (NativeViewGestureHandler) gestureHandler;
        nativeViewGestureHandler.begin();
        nativeViewGestureHandler.activate();
        nativeViewGestureHandler.end();
        return Unit.INSTANCE;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final boolean isFinished(int i) {
            return i == 3 || i == 1 || i == 5;
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final boolean shouldHandlerlessViewBecomeTouchTarget(View view, float[] fArr) {
            if ((!(view instanceof ViewGroup) || view.getBackground() != null) && isTransformedTouchPointInView(fArr[0], fArr[1], view)) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: private */
        public final void transformPointToChildViewCoords(float f, float f2, ViewGroup viewGroup, View view, PointF pointF) {
            float scrollX = (f + ((float) viewGroup.getScrollX())) - ((float) view.getLeft());
            float scrollY = (f2 + ((float) viewGroup.getScrollY())) - ((float) view.getTop());
            Matrix matrix = view.getMatrix();
            if (!matrix.isIdentity()) {
                float[] access$getMatrixTransformCoords$cp = GestureHandlerOrchestrator.matrixTransformCoords;
                access$getMatrixTransformCoords$cp[0] = scrollX;
                access$getMatrixTransformCoords$cp[1] = scrollY;
                matrix.invert(GestureHandlerOrchestrator.inverseMatrix);
                GestureHandlerOrchestrator.inverseMatrix.mapPoints(access$getMatrixTransformCoords$cp);
                float f3 = access$getMatrixTransformCoords$cp[0];
                scrollY = access$getMatrixTransformCoords$cp[1];
                scrollX = f3;
            }
            pointF.set(scrollX, scrollY);
        }

        /* access modifiers changed from: private */
        public final boolean isTransformedTouchPointInView(float f, float f2, View view) {
            return 0.0f <= f && f <= ((float) view.getWidth()) && 0.0f <= f2 && f2 <= ((float) view.getHeight());
        }

        /* access modifiers changed from: private */
        public final boolean shouldHandlerWaitForOther(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
            return gestureHandler != gestureHandler2 && (gestureHandler.shouldWaitForHandlerFailure(gestureHandler2) || gestureHandler2.shouldRequireToWaitForFailure(gestureHandler));
        }

        /* access modifiers changed from: private */
        public final boolean canRunSimultaneously(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
            return gestureHandler == gestureHandler2 || gestureHandler.shouldRecognizeSimultaneously(gestureHandler2) || gestureHandler2.shouldRecognizeSimultaneously(gestureHandler);
        }

        /* access modifiers changed from: private */
        public final boolean shouldHandlerBeCancelledBy(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
            if (!gestureHandler.hasCommonPointers(gestureHandler2) || canRunSimultaneously(gestureHandler, gestureHandler2)) {
                return false;
            }
            if (gestureHandler == gestureHandler2 || (!gestureHandler.isAwaiting() && gestureHandler.getState() != 4)) {
                return true;
            }
            return gestureHandler.shouldBeCancelledBy(gestureHandler2);
        }
    }

    /* access modifiers changed from: private */
    public static final int handlersComparator$lambda$15(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        if ((gestureHandler.isActive() && gestureHandler2.isActive()) || (gestureHandler.isAwaiting() && gestureHandler2.isAwaiting())) {
            return Integer.signum(gestureHandler2.getActivationIndex() - gestureHandler.getActivationIndex());
        }
        if (!gestureHandler.isActive()) {
            if (!gestureHandler2.isActive()) {
                if (!gestureHandler.isAwaiting()) {
                    if (!gestureHandler2.isAwaiting()) {
                        return 0;
                    }
                }
            }
            return 1;
        }
        return -1;
    }
}
