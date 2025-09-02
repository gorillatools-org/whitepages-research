package com.salesforce.marketingcloud.messages.iam;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;
import com.salesforce.marketingcloud.MCKeep;
import com.salesforce.marketingcloud.R;

@SuppressLint({"UnknownNullness"})
@MCKeep
public class SwipeDismissConstraintLayout extends ConstraintLayout {
    private static final float DRAG_SENSITIVITY = 1.0f;
    ViewDragHelper dragHelper;
    int draggingState;
    SwipeDismissListener listener;
    float minScaledFlingVelocity;
    View swipeTarget;
    private int swipeTargetId;

    @MCKeep
    public interface SwipeDismissListener {
        void onDismissed();

        void onSwipeStarted();

        void onViewSettled();
    }

    public class a extends ViewDragHelper.Callback {
        private int a;

        public a() {
        }

        private boolean a(View view, float f) {
            if (Math.abs(f) <= SwipeDismissConstraintLayout.this.minScaledFlingVelocity) {
                return false;
            }
            int left = view.getLeft();
            int i = this.a;
            return (left < i && f < 0.0f) || (left > i && f > 0.0f);
        }

        public int clampViewPositionHorizontal(View view, int i, int i2) {
            return MathUtils.clamp(this.a - view.getWidth(), i, this.a + view.getWidth());
        }

        public int clampViewPositionVertical(View view, int i, int i2) {
            return view.getTop();
        }

        public int getViewHorizontalDragRange(View view) {
            return view.getWidth();
        }

        public void onViewCaptured(View view, int i) {
            this.a = (int) (((float) (SwipeDismissConstraintLayout.this.getWidth() - view.getWidth())) * 0.5f);
            ViewParent parent = view.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
            SwipeDismissListener swipeDismissListener = SwipeDismissConstraintLayout.this.listener;
            if (swipeDismissListener != null) {
                swipeDismissListener.onSwipeStarted();
            }
        }

        public void onViewDragStateChanged(int i) {
            SwipeDismissConstraintLayout.this.draggingState = i;
        }

        public void onViewReleased(View view, float f, float f2) {
            boolean z;
            int i;
            int width = view.getWidth();
            if (a(view, f)) {
                int x = (int) (((float) this.a) - SwipeDismissConstraintLayout.this.getX());
                int left = view.getLeft();
                int i2 = this.a;
                i = left < i2 ? (i2 - width) - x : i2 + width + x;
                z = true;
            } else {
                i = this.a;
                z = false;
            }
            if (SwipeDismissConstraintLayout.this.dragHelper.settleCapturedViewAt(i, view.getTop())) {
                ViewCompat.postOnAnimation(view, new b(view, z));
            } else {
                SwipeDismissListener swipeDismissListener = SwipeDismissConstraintLayout.this.listener;
                if (swipeDismissListener != null) {
                    if (z) {
                        swipeDismissListener.onDismissed();
                    } else {
                        swipeDismissListener.onViewSettled();
                    }
                }
            }
            SwipeDismissConstraintLayout.this.invalidate();
        }

        public boolean tryCaptureView(View view, int i) {
            return view == SwipeDismissConstraintLayout.this.swipeTarget;
        }
    }

    private class b implements Runnable {
        private final View a;
        private final boolean b;

        b(View view, boolean z) {
            this.a = view;
            this.b = z;
        }

        public void run() {
            ViewDragHelper viewDragHelper = SwipeDismissConstraintLayout.this.dragHelper;
            if (viewDragHelper == null || !viewDragHelper.continueSettling(true)) {
                SwipeDismissListener swipeDismissListener = SwipeDismissConstraintLayout.this.listener;
                if (swipeDismissListener == null) {
                    return;
                }
                if (this.b) {
                    swipeDismissListener.onDismissed();
                } else {
                    swipeDismissListener.onViewSettled();
                }
            } else {
                ViewCompat.postOnAnimation(this.a, this);
            }
        }
    }

    public SwipeDismissConstraintLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    /* JADX INFO: finally extract failed */
    private void init(Context context, AttributeSet attributeSet) {
        if (!isInEditMode()) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.SwipeDismissConstraintLayout, 0, 0);
            try {
                this.swipeTargetId = obtainStyledAttributes.getResourceId(R.styleable.SwipeDismissConstraintLayout_swipeTargetId, 0);
                obtainStyledAttributes.recycle();
                this.dragHelper = ViewDragHelper.create(this, DRAG_SENSITIVITY, new a());
                this.minScaledFlingVelocity = (float) ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
            } catch (Throwable th) {
                obtainStyledAttributes.recycle();
                throw th;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0023, code lost:
        r2 = r2[1];
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isTarget(android.view.MotionEvent r6) {
        /*
            r5 = this;
            android.view.View r0 = r5.swipeTarget
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            r2 = 2
            int[] r2 = new int[r2]
            r0.getLocationOnScreen(r2)
            float r0 = r6.getRawX()
            int r0 = (int) r0
            float r6 = r6.getRawY()
            int r6 = (int) r6
            r3 = r2[r1]
            if (r0 <= r3) goto L_0x0032
            android.view.View r4 = r5.swipeTarget
            int r4 = r4.getMeasuredWidth()
            int r3 = r3 + r4
            if (r0 >= r3) goto L_0x0032
            r0 = 1
            r2 = r2[r0]
            if (r6 <= r2) goto L_0x0032
            android.view.View r3 = r5.swipeTarget
            int r3 = r3.getMeasuredWidth()
            int r2 = r2 + r3
            if (r6 >= r2) goto L_0x0032
            r1 = r0
        L_0x0032:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.iam.SwipeDismissConstraintLayout.isTarget(android.view.MotionEvent):boolean");
    }

    /* access modifiers changed from: package-private */
    public boolean isMoving() {
        int i = this.draggingState;
        return i == 1 || i == 2;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        this.swipeTarget = findViewById(this.swipeTargetId);
        super.onFinishInflate();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return isTarget(motionEvent) && this.dragHelper.shouldInterceptTouchEvent(motionEvent);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isTarget(motionEvent) && !isMoving()) {
            return super.onTouchEvent(motionEvent);
        }
        this.dragHelper.processTouchEvent(motionEvent);
        return true;
    }

    public void setListener(SwipeDismissListener swipeDismissListener) {
        this.listener = swipeDismissListener;
    }

    public SwipeDismissConstraintLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }
}
