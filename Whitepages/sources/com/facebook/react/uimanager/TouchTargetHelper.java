package com.facebook.react.uimanager;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.touch.ReactHitSlopView;
import com.facebook.react.uimanager.common.ViewUtil;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class TouchTargetHelper {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final float[] mEventCoords = new float[2];
    private static final Matrix mInverseMatrix = new Matrix();
    private static final float[] mMatrixTransformCoords = new float[2];
    private static final PointF mTempPoint = new PointF();

    private enum TouchTargetReturnType {
        SELF,
        CHILD
    }

    public static int findTargetTagForTouch(float f, float f2, ViewGroup viewGroup) {
        return findTargetTagAndCoordinatesForTouch(f, f2, viewGroup, mEventCoords, (int[]) null);
    }

    public static int findTargetTagForTouch(float f, float f2, ViewGroup viewGroup, int[] iArr) {
        return findTargetTagAndCoordinatesForTouch(f, f2, viewGroup, mEventCoords, iArr);
    }

    public static int findTargetTagAndCoordinatesForTouch(float f, float f2, ViewGroup viewGroup, float[] fArr, int[] iArr) {
        View findClosestReactAncestor;
        UiThreadUtil.assertOnUiThread();
        int id = viewGroup.getId();
        fArr[0] = f;
        fArr[1] = f2;
        View findTouchTargetViewWithPointerEvents = findTouchTargetViewWithPointerEvents(fArr, viewGroup, (List<ViewTarget>) null);
        if (findTouchTargetViewWithPointerEvents == null || (findClosestReactAncestor = findClosestReactAncestor(findTouchTargetViewWithPointerEvents)) == null) {
            return id;
        }
        if (iArr != null) {
            iArr[0] = findClosestReactAncestor.getId();
        }
        return getTouchTargetForView(findClosestReactAncestor, fArr[0], fArr[1]);
    }

    @SuppressLint({"ResourceType"})
    public static List<ViewTarget> findTargetPathAndCoordinatesForTouch(float f, float f2, ViewGroup viewGroup, float[] fArr) {
        UiThreadUtil.assertOnUiThread();
        fArr[0] = f;
        fArr[1] = f2;
        List<ViewTarget> arrayList = new ArrayList<>();
        View findTouchTargetViewWithPointerEvents = findTouchTargetViewWithPointerEvents(fArr, viewGroup, arrayList);
        if (findTouchTargetViewWithPointerEvents != null) {
            int i = 0;
            while (findTouchTargetViewWithPointerEvents != null && findTouchTargetViewWithPointerEvents.getId() <= 0) {
                findTouchTargetViewWithPointerEvents = (View) findTouchTargetViewWithPointerEvents.getParent();
                i++;
            }
            if (i > 0 && i <= arrayList.size()) {
                arrayList = arrayList.subList(i, arrayList.size());
            }
            int touchTargetForView = getTouchTargetForView(findTouchTargetViewWithPointerEvents, fArr[0], fArr[1]);
            if (touchTargetForView != findTouchTargetViewWithPointerEvents.getId()) {
                arrayList.add(0, new ViewTarget(touchTargetForView, (View) null));
            }
        }
        return arrayList;
    }

    @SuppressLint({"ResourceType"})
    private static View findClosestReactAncestor(View view) {
        while (view != null && view.getId() <= 0) {
            view = (View) view.getParent();
        }
        return view;
    }

    private static View findTouchTargetView(float[] fArr, View view, EnumSet<TouchTargetReturnType> enumSet, List<ViewTarget> list) {
        if (enumSet.contains(TouchTargetReturnType.CHILD) && (view instanceof ViewGroup)) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (!isTouchPointInView(fArr[0], fArr[1], view)) {
                if (view instanceof ReactOverflowViewWithInset) {
                    if (ViewUtil.getUIManagerType(view.getId()) == 2 && !isTouchPointInViewWithOverflowInset(fArr[0], fArr[1], view)) {
                        return null;
                    }
                    String overflow = ((ReactOverflowViewWithInset) view).getOverflow();
                    if (ViewProps.HIDDEN.equals(overflow) || ViewProps.SCROLL.equals(overflow)) {
                        return null;
                    }
                }
                if (viewGroup.getClipChildren()) {
                    return null;
                }
            }
            int childCount = viewGroup.getChildCount();
            ReactZIndexedViewGroup reactZIndexedViewGroup = viewGroup instanceof ReactZIndexedViewGroup ? (ReactZIndexedViewGroup) viewGroup : null;
            for (int i = childCount - 1; i >= 0; i--) {
                View childAt = viewGroup.getChildAt(reactZIndexedViewGroup != null ? reactZIndexedViewGroup.getZIndexMappedChildIndex(i) : i);
                PointF pointF = mTempPoint;
                getChildPoint(fArr[0], fArr[1], viewGroup, childAt, pointF);
                float f = fArr[0];
                float f2 = fArr[1];
                fArr[0] = pointF.x;
                fArr[1] = pointF.y;
                View findTouchTargetViewWithPointerEvents = findTouchTargetViewWithPointerEvents(fArr, childAt, list);
                if (findTouchTargetViewWithPointerEvents != null) {
                    return findTouchTargetViewWithPointerEvents;
                }
                fArr[0] = f;
                fArr[1] = f2;
            }
        }
        if (!enumSet.contains(TouchTargetReturnType.SELF) || !isTouchPointInView(fArr[0], fArr[1], view)) {
            return null;
        }
        return view;
    }

    private static boolean isTouchPointInView(float f, float f2, View view) {
        if (view instanceof ReactHitSlopView) {
            ReactHitSlopView reactHitSlopView = (ReactHitSlopView) view;
            if (reactHitSlopView.getHitSlopRect() != null) {
                Rect hitSlopRect = reactHitSlopView.getHitSlopRect();
                return f >= ((float) (-hitSlopRect.left)) && f < ((float) (view.getWidth() + hitSlopRect.right)) && f2 >= ((float) (-hitSlopRect.top)) && f2 < ((float) (view.getHeight() + hitSlopRect.bottom));
            }
        }
        return f >= 0.0f && f < ((float) view.getWidth()) && f2 >= 0.0f && f2 < ((float) view.getHeight());
    }

    private static boolean isTouchPointInViewWithOverflowInset(float f, float f2, View view) {
        if (!(view instanceof ReactOverflowViewWithInset)) {
            return false;
        }
        Rect overflowInset = ((ReactOverflowViewWithInset) view).getOverflowInset();
        if (f < ((float) overflowInset.left) || f >= ((float) (view.getWidth() - overflowInset.right)) || f2 < ((float) overflowInset.top) || f2 >= ((float) (view.getHeight() - overflowInset.bottom))) {
            return false;
        }
        return true;
    }

    private static void getChildPoint(float f, float f2, ViewGroup viewGroup, View view, PointF pointF) {
        float scrollX = (f + ((float) viewGroup.getScrollX())) - ((float) view.getLeft());
        float scrollY = (f2 + ((float) viewGroup.getScrollY())) - ((float) view.getTop());
        Matrix matrix = view.getMatrix();
        if (!matrix.isIdentity()) {
            float[] fArr = mMatrixTransformCoords;
            fArr[0] = scrollX;
            fArr[1] = scrollY;
            Matrix matrix2 = mInverseMatrix;
            matrix.invert(matrix2);
            matrix2.mapPoints(fArr);
            float f3 = fArr[0];
            scrollY = fArr[1];
            scrollX = f3;
        }
        pointF.set(scrollX, scrollY);
    }

    private static View findTouchTargetViewWithPointerEvents(float[] fArr, View view, List<ViewTarget> list) {
        PointerEvents pointerEvents;
        if (view instanceof ReactPointerEventsView) {
            pointerEvents = ((ReactPointerEventsView) view).getPointerEvents();
        } else {
            pointerEvents = PointerEvents.AUTO;
        }
        if (!view.isEnabled()) {
            if (pointerEvents == PointerEvents.AUTO) {
                pointerEvents = PointerEvents.BOX_NONE;
            } else if (pointerEvents == PointerEvents.BOX_ONLY) {
                pointerEvents = PointerEvents.NONE;
            }
        }
        if (pointerEvents == PointerEvents.NONE) {
            return null;
        }
        if (pointerEvents == PointerEvents.BOX_ONLY) {
            View findTouchTargetView = findTouchTargetView(fArr, view, EnumSet.of(TouchTargetReturnType.SELF), list);
            if (!(findTouchTargetView == null || list == null)) {
                list.add(new ViewTarget(view.getId(), view));
            }
            return findTouchTargetView;
        } else if (pointerEvents == PointerEvents.BOX_NONE) {
            View findTouchTargetView2 = findTouchTargetView(fArr, view, EnumSet.of(TouchTargetReturnType.CHILD), list);
            if (findTouchTargetView2 != null) {
                if (list != null) {
                    list.add(new ViewTarget(view.getId(), view));
                }
                return findTouchTargetView2;
            } else if (!(view instanceof ReactCompoundView) || !isTouchPointInView(fArr[0], fArr[1], view) || ((ReactCompoundView) view).reactTagForTouch(fArr[0], fArr[1]) == view.getId()) {
                return null;
            } else {
                if (list != null) {
                    list.add(new ViewTarget(view.getId(), view));
                }
                return view;
            }
        } else {
            if (pointerEvents != PointerEvents.AUTO) {
                FLog.w(ReactConstants.TAG, "Unknown pointer event type: " + pointerEvents.toString());
            }
            if (!(view instanceof ReactCompoundViewGroup) || !isTouchPointInView(fArr[0], fArr[1], view) || !((ReactCompoundViewGroup) view).interceptsTouchEvent(fArr[0], fArr[1])) {
                View findTouchTargetView3 = findTouchTargetView(fArr, view, EnumSet.of(TouchTargetReturnType.SELF, TouchTargetReturnType.CHILD), list);
                if (!(findTouchTargetView3 == null || list == null)) {
                    list.add(new ViewTarget(view.getId(), view));
                }
                return findTouchTargetView3;
            }
            if (list != null) {
                list.add(new ViewTarget(view.getId(), view));
            }
            return view;
        }
    }

    private static int getTouchTargetForView(View view, float f, float f2) {
        if (view instanceof ReactCompoundView) {
            return ((ReactCompoundView) view).reactTagForTouch(f, f2);
        }
        return view.getId();
    }

    public static class ViewTarget {
        private final View mView;
        private final int mViewId;

        private ViewTarget(int i, View view) {
            this.mViewId = i;
            this.mView = view;
        }

        public int getViewId() {
            return this.mViewId;
        }

        public View getView() {
            return this.mView;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ViewTarget)) {
                return false;
            }
            if (((ViewTarget) obj).getViewId() == this.mViewId) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return Integer.valueOf(this.mViewId).hashCode();
        }
    }
}
