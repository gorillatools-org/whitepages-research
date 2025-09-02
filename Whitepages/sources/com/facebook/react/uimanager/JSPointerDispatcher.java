package com.facebook.react.uimanager;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.TouchTargetHelper;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.events.PointerEvent;
import com.facebook.react.uimanager.events.PointerEventHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JSPointerDispatcher {
    private static final float ONMOVE_EPSILON = 0.1f;
    private static final String TAG = "PointerEvents";
    private static final int UNSELECTED_VIEW_TAG = -1;
    private static final int UNSET_POINTER_ID = -1;
    private static final int[] sRootScreenCoords = {0, 0};
    private int mChildHandlingNativeGesture = -1;
    private int mCoalescingKey = 0;
    private Map<Integer, List<TouchTargetHelper.ViewTarget>> mCurrentlyDownPointerIdsToHitPath;
    private Set<Integer> mHoveringPointerIds = new HashSet();
    private int mLastButtonState = 0;
    private Map<Integer, float[]> mLastEventCoordinatesByPointerId;
    private Map<Integer, List<TouchTargetHelper.ViewTarget>> mLastHitPathByPointerId;
    private int mPrimaryPointerId = -1;
    private final ViewGroup mRootViewGroup;

    public JSPointerDispatcher(ViewGroup viewGroup) {
        this.mRootViewGroup = viewGroup;
        this.mCurrentlyDownPointerIdsToHitPath = new HashMap();
    }

    public void onChildStartedNativeGesture(View view, MotionEvent motionEvent, EventDispatcher eventDispatcher) {
        if (this.mChildHandlingNativeGesture == -1 && view != null) {
            MotionEvent convertMotionToRootFrame = convertMotionToRootFrame(view, motionEvent);
            convertMotionToRootFrame.setAction(3);
            handleMotionEvent(convertMotionToRootFrame, eventDispatcher, false);
            this.mChildHandlingNativeGesture = view.getId();
        }
    }

    private MotionEvent convertMotionToRootFrame(View view, MotionEvent motionEvent) {
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        int[] iArr = new int[2];
        this.mRootViewGroup.getLocationOnScreen(iArr);
        obtain.setLocation(motionEvent.getRawX() - ((float) iArr[0]), motionEvent.getRawY() - ((float) iArr[1]));
        return obtain;
    }

    public void onChildEndedNativeGesture() {
        this.mChildHandlingNativeGesture = -1;
    }

    private static List<TouchTargetHelper.ViewTarget> findHitPathIntersection(List<TouchTargetHelper.ViewTarget> list, List<TouchTargetHelper.ViewTarget> list2) {
        if (list.isEmpty()) {
            return new ArrayList();
        }
        if (list2.isEmpty()) {
            return new ArrayList();
        }
        HashSet hashSet = new HashSet(list);
        ArrayList arrayList = new ArrayList();
        for (TouchTargetHelper.ViewTarget next : list2) {
            if (hashSet.contains(next)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    private void onUp(int i, PointerEvent.PointerEventState pointerEventState, MotionEvent motionEvent, EventDispatcher eventDispatcher) {
        int activePointerId = pointerEventState.getActivePointerId();
        List list = pointerEventState.getHitPathByPointerId().get(Integer.valueOf(activePointerId));
        if (isAnyoneListeningForBubblingEvent(list, PointerEventHelper.EVENT.UP, PointerEventHelper.EVENT.UP_CAPTURE)) {
            eventDispatcher.dispatchEvent(PointerEvent.obtain(PointerEventHelper.POINTER_UP, i, pointerEventState, motionEvent));
        }
        if (!this.mHoveringPointerIds.contains(Integer.valueOf(activePointerId))) {
            if (isAnyoneListeningForBubblingEvent(list, PointerEventHelper.EVENT.OUT, PointerEventHelper.EVENT.OUT_CAPTURE)) {
                eventDispatcher.dispatchEvent(PointerEvent.obtain(PointerEventHelper.POINTER_OUT, i, pointerEventState, motionEvent));
            }
            dispatchEventForViewTargets(PointerEventHelper.POINTER_LEAVE, pointerEventState, motionEvent, filterByShouldDispatch(list, PointerEventHelper.EVENT.LEAVE, PointerEventHelper.EVENT.LEAVE_CAPTURE, false), eventDispatcher);
        }
        List remove = this.mCurrentlyDownPointerIdsToHitPath.remove(Integer.valueOf(activePointerId));
        if (remove != null && isAnyoneListeningForBubblingEvent(list, PointerEventHelper.EVENT.CLICK, PointerEventHelper.EVENT.CLICK_CAPTURE)) {
            List<TouchTargetHelper.ViewTarget> findHitPathIntersection = findHitPathIntersection(remove, list);
            if (!findHitPathIntersection.isEmpty()) {
                eventDispatcher.dispatchEvent(PointerEvent.obtain(PointerEventHelper.CLICK, findHitPathIntersection.get(0).getViewId(), pointerEventState, motionEvent));
            }
        }
        if (motionEvent.getActionMasked() == 1) {
            this.mPrimaryPointerId = -1;
        }
        this.mHoveringPointerIds.remove(Integer.valueOf(activePointerId));
    }

    private void incrementCoalescingKey() {
        this.mCoalescingKey = (this.mCoalescingKey + 1) % Integer.MAX_VALUE;
    }

    private short getCoalescingKey() {
        return (short) (65535 & this.mCoalescingKey);
    }

    private void onDown(int i, PointerEvent.PointerEventState pointerEventState, MotionEvent motionEvent, EventDispatcher eventDispatcher) {
        List list = pointerEventState.getHitPathByPointerId().get(Integer.valueOf(pointerEventState.getActivePointerId()));
        incrementCoalescingKey();
        if (!this.mHoveringPointerIds.contains(Integer.valueOf(pointerEventState.getActivePointerId()))) {
            if (isAnyoneListeningForBubblingEvent(list, PointerEventHelper.EVENT.OVER, PointerEventHelper.EVENT.OVER_CAPTURE)) {
                eventDispatcher.dispatchEvent(PointerEvent.obtain(PointerEventHelper.POINTER_OVER, i, pointerEventState, motionEvent));
            }
            List<TouchTargetHelper.ViewTarget> filterByShouldDispatch = filterByShouldDispatch(list, PointerEventHelper.EVENT.ENTER, PointerEventHelper.EVENT.ENTER_CAPTURE, false);
            Collections.reverse(filterByShouldDispatch);
            dispatchEventForViewTargets(PointerEventHelper.POINTER_ENTER, pointerEventState, motionEvent, filterByShouldDispatch, eventDispatcher);
        }
        if (isAnyoneListeningForBubblingEvent(list, PointerEventHelper.EVENT.CLICK, PointerEventHelper.EVENT.CLICK_CAPTURE)) {
            this.mCurrentlyDownPointerIdsToHitPath.put(Integer.valueOf(pointerEventState.getActivePointerId()), new ArrayList(list));
        }
        if (isAnyoneListeningForBubblingEvent(list, PointerEventHelper.EVENT.DOWN, PointerEventHelper.EVENT.DOWN_CAPTURE)) {
            eventDispatcher.dispatchEvent(PointerEvent.obtain(PointerEventHelper.POINTER_DOWN, i, pointerEventState, motionEvent));
        }
    }

    private float[] eventCoordsToScreenCoords(float[] fArr) {
        ViewGroup viewGroup = this.mRootViewGroup;
        int[] iArr = sRootScreenCoords;
        viewGroup.getLocationOnScreen(iArr);
        return new float[]{fArr[0] + ((float) iArr[0]), fArr[1] + ((float) iArr[1])};
    }

    private PointerEvent.PointerEventState createEventState(int i, MotionEvent motionEvent) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        HashMap hashMap4 = new HashMap();
        for (int i2 = 0; i2 < motionEvent.getPointerCount(); i2++) {
            float[] fArr = new float[2];
            float[] fArr2 = {motionEvent.getX(i2), motionEvent.getY(i2)};
            List<TouchTargetHelper.ViewTarget> findTargetPathAndCoordinatesForTouch = TouchTargetHelper.findTargetPathAndCoordinatesForTouch(fArr2[0], fArr2[1], this.mRootViewGroup, fArr);
            int pointerId = motionEvent.getPointerId(i2);
            hashMap.put(Integer.valueOf(pointerId), fArr);
            hashMap2.put(Integer.valueOf(pointerId), findTargetPathAndCoordinatesForTouch);
            hashMap3.put(Integer.valueOf(pointerId), fArr2);
            hashMap4.put(Integer.valueOf(pointerId), eventCoordsToScreenCoords(fArr2));
        }
        return new PointerEvent.PointerEventState(this.mPrimaryPointerId, i, this.mLastButtonState, UIManagerHelper.getSurfaceId((View) this.mRootViewGroup), hashMap, hashMap2, hashMap3, hashMap4, this.mHoveringPointerIds);
    }

    public void handleMotionEvent(MotionEvent motionEvent, EventDispatcher eventDispatcher, boolean z) {
        int i;
        View view;
        if (this.mChildHandlingNativeGesture == -1) {
            int actionMasked = motionEvent.getActionMasked();
            int pointerId = motionEvent.getPointerId(motionEvent.getActionIndex());
            if (actionMasked == 0) {
                this.mPrimaryPointerId = motionEvent.getPointerId(0);
            } else if (actionMasked == 7) {
                this.mHoveringPointerIds.add(Integer.valueOf(pointerId));
            }
            PointerEvent.PointerEventState createEventState = createEventState(pointerId, motionEvent);
            boolean z2 = z && motionEvent.getActionMasked() == 10;
            if (z2) {
                Map<Integer, List<TouchTargetHelper.ViewTarget>> map = this.mLastHitPathByPointerId;
                List list = map != null ? map.get(Integer.valueOf(createEventState.getActivePointerId())) : null;
                if (list != null && !list.isEmpty()) {
                    TouchTargetHelper.ViewTarget viewTarget = (TouchTargetHelper.ViewTarget) list.get(list.size() - 1);
                    i = viewTarget.getViewId();
                    view = viewTarget.getView();
                    createEventState.getHitPathByPointerId().put(Integer.valueOf(pointerId), new ArrayList());
                } else {
                    return;
                }
            } else {
                List list2 = createEventState.getHitPathByPointerId().get(Integer.valueOf(pointerId));
                if (list2 != null && !list2.isEmpty()) {
                    TouchTargetHelper.ViewTarget viewTarget2 = (TouchTargetHelper.ViewTarget) list2.get(0);
                    i = viewTarget2.getViewId();
                    view = viewTarget2.getView();
                } else {
                    return;
                }
            }
            handleHitStateDivergence(i, createEventState, motionEvent, eventDispatcher);
            switch (actionMasked) {
                case 0:
                case 5:
                    onDown(i, createEventState, motionEvent, eventDispatcher);
                    break;
                case 1:
                case 6:
                    incrementCoalescingKey();
                    onUp(i, createEventState, motionEvent, eventDispatcher);
                    break;
                case 2:
                    onMove(i, createEventState, motionEvent, eventDispatcher);
                    break;
                case 3:
                    dispatchCancelEventForTarget(view, createEventState, motionEvent, eventDispatcher);
                    handleHitStateDivergence(-1, createEventState, motionEvent, eventDispatcher);
                    break;
                case 7:
                    float[] fArr = createEventState.getEventCoordinatesByPointerId().get(Integer.valueOf(pointerId));
                    Map<Integer, float[]> map2 = this.mLastEventCoordinatesByPointerId;
                    if (qualifiedMove(fArr, (map2 == null || !map2.containsKey(Integer.valueOf(pointerId))) ? new float[]{0.0f, 0.0f} : this.mLastEventCoordinatesByPointerId.get(Integer.valueOf(pointerId)))) {
                        onMove(i, createEventState, motionEvent, eventDispatcher);
                        break;
                    } else {
                        return;
                    }
                    break;
                case 9:
                    return;
                case 10:
                    if (z2) {
                        onMove(i, createEventState, motionEvent, eventDispatcher);
                        break;
                    }
                    break;
                default:
                    FLog.w(ReactConstants.TAG, "Motion Event was ignored. Action=" + actionMasked + " Target=" + i);
                    return;
            }
            this.mLastEventCoordinatesByPointerId = new HashMap(createEventState.getEventCoordinatesByPointerId());
            this.mLastButtonState = motionEvent.getButtonState();
            this.mHoveringPointerIds.retainAll(this.mLastEventCoordinatesByPointerId.keySet());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x000a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean isAnyoneListeningForBubblingEvent(java.util.List<com.facebook.react.uimanager.TouchTargetHelper.ViewTarget> r2, com.facebook.react.uimanager.events.PointerEventHelper.EVENT r3, com.facebook.react.uimanager.events.PointerEventHelper.EVENT r4) {
        /*
            java.util.Iterator r2 = r2.iterator()
        L_0x0004:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x0026
            java.lang.Object r0 = r2.next()
            com.facebook.react.uimanager.TouchTargetHelper$ViewTarget r0 = (com.facebook.react.uimanager.TouchTargetHelper.ViewTarget) r0
            android.view.View r1 = r0.getView()
            boolean r1 = com.facebook.react.uimanager.events.PointerEventHelper.isListening(r1, r3)
            if (r1 != 0) goto L_0x0024
            android.view.View r0 = r0.getView()
            boolean r0 = com.facebook.react.uimanager.events.PointerEventHelper.isListening(r0, r4)
            if (r0 == 0) goto L_0x0004
        L_0x0024:
            r2 = 1
            return r2
        L_0x0026:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.JSPointerDispatcher.isAnyoneListeningForBubblingEvent(java.util.List, com.facebook.react.uimanager.events.PointerEventHelper$EVENT, com.facebook.react.uimanager.events.PointerEventHelper$EVENT):boolean");
    }

    private static List<TouchTargetHelper.ViewTarget> filterByShouldDispatch(List<TouchTargetHelper.ViewTarget> list, PointerEventHelper.EVENT event, PointerEventHelper.EVENT event2, boolean z) {
        ArrayList arrayList = new ArrayList(list);
        if (z) {
            return arrayList;
        }
        boolean z2 = false;
        for (int size = list.size() - 1; size >= 0; size--) {
            View view = list.get(size).getView();
            if (!z2 && !PointerEventHelper.isListening(view, event2) && !PointerEventHelper.isListening(view, event)) {
                arrayList.remove(size);
            } else if (!z2 && PointerEventHelper.isListening(view, event2)) {
                z2 = true;
            }
        }
        return arrayList;
    }

    private static void dispatchEventForViewTargets(String str, PointerEvent.PointerEventState pointerEventState, MotionEvent motionEvent, List<TouchTargetHelper.ViewTarget> list, EventDispatcher eventDispatcher) {
        for (TouchTargetHelper.ViewTarget viewId : list) {
            eventDispatcher.dispatchEvent(PointerEvent.obtain(str, viewId.getViewId(), pointerEventState, motionEvent));
        }
    }

    private static boolean qualifiedMove(float[] fArr, float[] fArr2) {
        if (Math.abs(fArr2[0] - fArr[0]) > ONMOVE_EPSILON || Math.abs(fArr2[1] - fArr[1]) > ONMOVE_EPSILON) {
            return true;
        }
        return false;
    }

    private void handleHitStateDivergence(int i, PointerEvent.PointerEventState pointerEventState, MotionEvent motionEvent, EventDispatcher eventDispatcher) {
        List list;
        List list2;
        int activePointerId = pointerEventState.getActivePointerId();
        if (i != -1) {
            list = pointerEventState.getHitPathByPointerId().get(Integer.valueOf(activePointerId));
        } else {
            list = new ArrayList();
        }
        Map<Integer, List<TouchTargetHelper.ViewTarget>> map = this.mLastHitPathByPointerId;
        if (map == null || !map.containsKey(Integer.valueOf(activePointerId))) {
            list2 = new ArrayList();
        } else {
            list2 = this.mLastHitPathByPointerId.get(Integer.valueOf(activePointerId));
        }
        int i2 = 0;
        boolean z = false;
        boolean z2 = false;
        while (i2 < Math.min(list.size(), list2.size()) && ((TouchTargetHelper.ViewTarget) list.get((list.size() - 1) - i2)).equals(list2.get((list2.size() - 1) - i2))) {
            View view = ((TouchTargetHelper.ViewTarget) list.get((list.size() - 1) - i2)).getView();
            if (!z && PointerEventHelper.isListening(view, PointerEventHelper.EVENT.ENTER_CAPTURE)) {
                z = true;
            }
            if (!z2 && PointerEventHelper.isListening(view, PointerEventHelper.EVENT.LEAVE_CAPTURE)) {
                z2 = true;
            }
            i2++;
        }
        if (i2 < Math.max(list.size(), list2.size())) {
            incrementCoalescingKey();
            if (list2.size() > 0) {
                int viewId = ((TouchTargetHelper.ViewTarget) list2.get(0)).getViewId();
                if (isAnyoneListeningForBubblingEvent(list2, PointerEventHelper.EVENT.OUT, PointerEventHelper.EVENT.OUT_CAPTURE)) {
                    eventDispatcher.dispatchEvent(PointerEvent.obtain(PointerEventHelper.POINTER_OUT, viewId, pointerEventState, motionEvent));
                }
                List<TouchTargetHelper.ViewTarget> filterByShouldDispatch = filterByShouldDispatch(list2.subList(0, list2.size() - i2), PointerEventHelper.EVENT.LEAVE, PointerEventHelper.EVENT.LEAVE_CAPTURE, z2);
                if (filterByShouldDispatch.size() > 0) {
                    dispatchEventForViewTargets(PointerEventHelper.POINTER_LEAVE, pointerEventState, motionEvent, filterByShouldDispatch, eventDispatcher);
                }
            }
            if (isAnyoneListeningForBubblingEvent(list, PointerEventHelper.EVENT.OVER, PointerEventHelper.EVENT.OVER_CAPTURE)) {
                eventDispatcher.dispatchEvent(PointerEvent.obtain(PointerEventHelper.POINTER_OVER, i, pointerEventState, motionEvent));
            }
            List<TouchTargetHelper.ViewTarget> filterByShouldDispatch2 = filterByShouldDispatch(list.subList(0, list.size() - i2), PointerEventHelper.EVENT.ENTER, PointerEventHelper.EVENT.ENTER_CAPTURE, z);
            if (filterByShouldDispatch2.size() > 0) {
                Collections.reverse(filterByShouldDispatch2);
                dispatchEventForViewTargets(PointerEventHelper.POINTER_ENTER, pointerEventState, motionEvent, filterByShouldDispatch2, eventDispatcher);
            }
        }
        HashMap hashMap = new HashMap(pointerEventState.getHitPathByPointerId());
        if (i == -1) {
            hashMap.remove(Integer.valueOf(activePointerId));
        }
        this.mLastHitPathByPointerId = hashMap;
    }

    private void onMove(int i, PointerEvent.PointerEventState pointerEventState, MotionEvent motionEvent, EventDispatcher eventDispatcher) {
        if (isAnyoneListeningForBubblingEvent(pointerEventState.getHitPathByPointerId().get(Integer.valueOf(pointerEventState.getActivePointerId())), PointerEventHelper.EVENT.MOVE, PointerEventHelper.EVENT.MOVE_CAPTURE)) {
            eventDispatcher.dispatchEvent(PointerEvent.obtain(PointerEventHelper.POINTER_MOVE, i, pointerEventState, motionEvent, getCoalescingKey()));
        }
    }

    private void dispatchCancelEventForTarget(View view, PointerEvent.PointerEventState pointerEventState, MotionEvent motionEvent, EventDispatcher eventDispatcher) {
        Assertions.assertCondition(this.mChildHandlingNativeGesture == -1, "Expected to not have already sent a cancel for this gesture");
        List list = pointerEventState.getHitPathByPointerId().get(Integer.valueOf(pointerEventState.getActivePointerId()));
        if (!list.isEmpty() && view != null) {
            if (isAnyoneListeningForBubblingEvent(list, PointerEventHelper.EVENT.CANCEL, PointerEventHelper.EVENT.CANCEL_CAPTURE)) {
                int viewId = ((TouchTargetHelper.ViewTarget) list.get(0)).getViewId();
                int[] childOffsetRelativeToRoot = getChildOffsetRelativeToRoot(view);
                ((EventDispatcher) Assertions.assertNotNull(eventDispatcher)).dispatchEvent(PointerEvent.obtain(PointerEventHelper.POINTER_CANCEL, viewId, normalizeToRoot(pointerEventState, (float) childOffsetRelativeToRoot[0], (float) childOffsetRelativeToRoot[1]), motionEvent));
            }
            incrementCoalescingKey();
            this.mPrimaryPointerId = -1;
        }
    }

    private int[] getChildOffsetRelativeToRoot(View view) {
        Rect rect = new Rect(0, 0, 1, 1);
        this.mRootViewGroup.offsetDescendantRectToMyCoords(view, rect);
        return new int[]{rect.top, rect.left};
    }

    private PointerEvent.PointerEventState normalizeToRoot(PointerEvent.PointerEventState pointerEventState, float f, float f2) {
        HashMap hashMap = new HashMap(pointerEventState.getOffsetByPointerId());
        HashMap hashMap2 = new HashMap(pointerEventState.getEventCoordinatesByPointerId());
        HashMap hashMap3 = new HashMap(pointerEventState.getScreenCoordinatesByPointerId());
        float[] fArr = {f, f2};
        for (Map.Entry value : hashMap.entrySet()) {
            value.setValue(fArr);
        }
        float[] fArr2 = {0.0f, 0.0f};
        for (Map.Entry value2 : hashMap2.entrySet()) {
            value2.setValue(fArr2);
        }
        float[] eventCoordsToScreenCoords = eventCoordsToScreenCoords(fArr);
        for (Map.Entry value3 : hashMap3.entrySet()) {
            value3.setValue(eventCoordsToScreenCoords);
        }
        return new PointerEvent.PointerEventState(pointerEventState.getPrimaryPointerId(), pointerEventState.getActivePointerId(), pointerEventState.getLastButtonState(), pointerEventState.getSurfaceId(), hashMap, new HashMap(pointerEventState.getHitPathByPointerId()), hashMap2, hashMap3, new HashSet(pointerEventState.getHoveringPointerIds()));
    }

    private static void debugPrintHitPath(List<TouchTargetHelper.ViewTarget> list) {
        StringBuilder sb = new StringBuilder("hitPath: ");
        for (TouchTargetHelper.ViewTarget viewId : list) {
            sb.append(String.format("%d, ", new Object[]{Integer.valueOf(viewId.getViewId())}));
        }
        FLog.d(TAG, sb.toString());
    }
}
