package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import androidx.appcompat.R$styleable;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;

public abstract class LinearLayoutCompat extends ViewGroup {
    private boolean mBaselineAligned;
    private int mBaselineAlignedChildIndex;
    private int mBaselineChildTop;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDividerWidth;
    private int mGravity;
    private int[] mMaxAscent;
    private int[] mMaxDescent;
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;

    /* access modifiers changed from: package-private */
    public int getChildrenSkipCount(View view, int i) {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int getLocationOffset(View view) {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int getNextLocationOffset(View view) {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int measureNullChild(int i) {
        return 0;
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBaselineAligned = true;
        this.mBaselineAlignedChildIndex = -1;
        this.mBaselineChildTop = 0;
        this.mGravity = 8388659;
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, R$styleable.LinearLayoutCompat, i, 0);
        ViewCompat.saveAttributeDataForStyleable(this, context, R$styleable.LinearLayoutCompat, attributeSet, obtainStyledAttributes.getWrappedTypeArray(), i, 0);
        int i2 = obtainStyledAttributes.getInt(R$styleable.LinearLayoutCompat_android_orientation, -1);
        if (i2 >= 0) {
            setOrientation(i2);
        }
        int i3 = obtainStyledAttributes.getInt(R$styleable.LinearLayoutCompat_android_gravity, -1);
        if (i3 >= 0) {
            setGravity(i3);
        }
        boolean z = obtainStyledAttributes.getBoolean(R$styleable.LinearLayoutCompat_android_baselineAligned, true);
        if (!z) {
            setBaselineAligned(z);
        }
        this.mWeightSum = obtainStyledAttributes.getFloat(R$styleable.LinearLayoutCompat_android_weightSum, -1.0f);
        this.mBaselineAlignedChildIndex = obtainStyledAttributes.getInt(R$styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.mUseLargestChild = obtainStyledAttributes.getBoolean(R$styleable.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(obtainStyledAttributes.getDrawable(R$styleable.LinearLayoutCompat_divider));
        this.mShowDividers = obtainStyledAttributes.getInt(R$styleable.LinearLayoutCompat_showDividers, 0);
        this.mDividerPadding = obtainStyledAttributes.getDimensionPixelSize(R$styleable.LinearLayoutCompat_dividerPadding, 0);
        obtainStyledAttributes.recycle();
    }

    public void setShowDividers(int i) {
        if (i != this.mShowDividers) {
            requestLayout();
        }
        this.mShowDividers = i;
    }

    public int getShowDividers() {
        return this.mShowDividers;
    }

    public Drawable getDividerDrawable() {
        return this.mDivider;
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable != this.mDivider) {
            this.mDivider = drawable;
            boolean z = false;
            if (drawable != null) {
                this.mDividerWidth = drawable.getIntrinsicWidth();
                this.mDividerHeight = drawable.getIntrinsicHeight();
            } else {
                this.mDividerWidth = 0;
                this.mDividerHeight = 0;
            }
            if (drawable == null) {
                z = true;
            }
            setWillNotDraw(z);
            requestLayout();
        }
    }

    public void setDividerPadding(int i) {
        this.mDividerPadding = i;
    }

    public int getDividerPadding() {
        return this.mDividerPadding;
    }

    public int getDividerWidth() {
        return this.mDividerWidth;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.mDivider != null) {
            if (this.mOrientation == 1) {
                drawDividersVertical(canvas);
            } else {
                drawDividersHorizontal(canvas);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void drawDividersVertical(Canvas canvas) {
        int i;
        int virtualChildCount = getVirtualChildCount();
        for (int i2 = 0; i2 < virtualChildCount; i2++) {
            View virtualChildAt = getVirtualChildAt(i2);
            if (!(virtualChildAt == null || virtualChildAt.getVisibility() == 8 || !hasDividerBeforeChildAt(i2))) {
                drawHorizontalDivider(canvas, (virtualChildAt.getTop() - ((LayoutParams) virtualChildAt.getLayoutParams()).topMargin) - this.mDividerHeight);
            }
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 == null) {
                i = (getHeight() - getPaddingBottom()) - this.mDividerHeight;
            } else {
                i = virtualChildAt2.getBottom() + ((LayoutParams) virtualChildAt2.getLayoutParams()).bottomMargin;
            }
            drawHorizontalDivider(canvas, i);
        }
    }

    /* access modifiers changed from: package-private */
    public void drawDividersHorizontal(Canvas canvas) {
        int i;
        int left;
        int i2;
        int i3;
        int virtualChildCount = getVirtualChildCount();
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        for (int i4 = 0; i4 < virtualChildCount; i4++) {
            View virtualChildAt = getVirtualChildAt(i4);
            if (!(virtualChildAt == null || virtualChildAt.getVisibility() == 8 || !hasDividerBeforeChildAt(i4))) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (isLayoutRtl) {
                    i3 = virtualChildAt.getRight() + layoutParams.rightMargin;
                } else {
                    i3 = (virtualChildAt.getLeft() - layoutParams.leftMargin) - this.mDividerWidth;
                }
                drawVerticalDivider(canvas, i3);
            }
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 != null) {
                LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                if (isLayoutRtl) {
                    left = virtualChildAt2.getLeft() - layoutParams2.leftMargin;
                    i2 = this.mDividerWidth;
                } else {
                    i = virtualChildAt2.getRight() + layoutParams2.rightMargin;
                    drawVerticalDivider(canvas, i);
                }
            } else if (isLayoutRtl) {
                i = getPaddingLeft();
                drawVerticalDivider(canvas, i);
            } else {
                left = getWidth() - getPaddingRight();
                i2 = this.mDividerWidth;
            }
            i = left - i2;
            drawVerticalDivider(canvas, i);
        }
    }

    /* access modifiers changed from: package-private */
    public void drawHorizontalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(getPaddingLeft() + this.mDividerPadding, i, (getWidth() - getPaddingRight()) - this.mDividerPadding, this.mDividerHeight + i);
        this.mDivider.draw(canvas);
    }

    /* access modifiers changed from: package-private */
    public void drawVerticalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(i, getPaddingTop() + this.mDividerPadding, this.mDividerWidth + i, (getHeight() - getPaddingBottom()) - this.mDividerPadding);
        this.mDivider.draw(canvas);
    }

    public void setBaselineAligned(boolean z) {
        this.mBaselineAligned = z;
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.mUseLargestChild = z;
    }

    public int getBaseline() {
        int i;
        if (this.mBaselineAlignedChildIndex < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i2 = this.mBaselineAlignedChildIndex;
        if (childCount > i2) {
            View childAt = getChildAt(i2);
            int baseline = childAt.getBaseline();
            if (baseline != -1) {
                int i3 = this.mBaselineChildTop;
                if (this.mOrientation == 1 && (i = this.mGravity & 112) != 48) {
                    if (i == 16) {
                        i3 += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.mTotalLength) / 2;
                    } else if (i == 80) {
                        i3 = ((getBottom() - getTop()) - getPaddingBottom()) - this.mTotalLength;
                    }
                }
                return i3 + ((LayoutParams) childAt.getLayoutParams()).topMargin + baseline;
            } else if (this.mBaselineAlignedChildIndex == 0) {
                return -1;
            } else {
                throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
            }
        } else {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
    }

    public int getBaselineAlignedChildIndex() {
        return this.mBaselineAlignedChildIndex;
    }

    public void setBaselineAlignedChildIndex(int i) {
        if (i < 0 || i >= getChildCount()) {
            throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
        }
        this.mBaselineAlignedChildIndex = i;
    }

    /* access modifiers changed from: package-private */
    public View getVirtualChildAt(int i) {
        return getChildAt(i);
    }

    /* access modifiers changed from: package-private */
    public int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.mWeightSum;
    }

    public void setWeightSum(float f) {
        this.mWeightSum = Math.max(0.0f, f);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (this.mOrientation == 1) {
            measureVertical(i, i2);
        } else {
            measureHorizontal(i, i2);
        }
    }

    /* access modifiers changed from: protected */
    public boolean hasDividerBeforeChildAt(int i) {
        if (i == 0) {
            return (this.mShowDividers & 1) != 0;
        }
        if (i == getChildCount()) {
            if ((this.mShowDividers & 4) != 0) {
                return true;
            }
            return false;
        } else if ((this.mShowDividers & 2) == 0) {
            return false;
        } else {
            for (int i2 = i - 1; i2 >= 0; i2--) {
                if (getChildAt(i2).getVisibility() != 8) {
                    return true;
                }
            }
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x031b, code lost:
        if (r14.width == -1) goto L_0x0322;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void measureVertical(int r34, int r35) {
        /*
            r33 = this;
            r7 = r33
            r8 = r34
            r9 = r35
            r10 = 0
            r7.mTotalLength = r10
            int r11 = r33.getVirtualChildCount()
            int r12 = android.view.View.MeasureSpec.getMode(r34)
            int r13 = android.view.View.MeasureSpec.getMode(r35)
            int r14 = r7.mBaselineAlignedChildIndex
            boolean r15 = r7.mUseLargestChild
            r16 = 1
            r17 = 0
            r1 = r10
            r2 = r1
            r3 = r2
            r4 = r3
            r5 = r4
            r6 = r5
            r18 = r6
            r20 = r18
            r19 = r16
            r0 = r17
        L_0x002b:
            r10 = 8
            r22 = r4
            if (r6 >= r11) goto L_0x019b
            android.view.View r4 = r7.getVirtualChildAt(r6)
            if (r4 != 0) goto L_0x0048
            int r4 = r7.mTotalLength
            int r10 = r7.measureNullChild(r6)
            int r4 = r4 + r10
            r7.mTotalLength = r4
            r24 = r13
            r4 = r22
            r22 = r11
            goto L_0x018f
        L_0x0048:
            r24 = r1
            int r1 = r4.getVisibility()
            if (r1 != r10) goto L_0x005f
            int r1 = r7.getChildrenSkipCount(r4, r6)
            int r6 = r6 + r1
            r4 = r22
            r1 = r24
            r22 = r11
            r24 = r13
            goto L_0x018f
        L_0x005f:
            boolean r1 = r7.hasDividerBeforeChildAt(r6)
            if (r1 == 0) goto L_0x006c
            int r1 = r7.mTotalLength
            int r10 = r7.mDividerHeight
            int r1 = r1 + r10
            r7.mTotalLength = r1
        L_0x006c:
            android.view.ViewGroup$LayoutParams r1 = r4.getLayoutParams()
            r10 = r1
            androidx.appcompat.widget.LinearLayoutCompat$LayoutParams r10 = (androidx.appcompat.widget.LinearLayoutCompat.LayoutParams) r10
            float r1 = r10.weight
            float r25 = r0 + r1
            r0 = 1073741824(0x40000000, float:2.0)
            if (r13 != r0) goto L_0x00a6
            int r0 = r10.height
            if (r0 != 0) goto L_0x00a6
            int r0 = (r1 > r17 ? 1 : (r1 == r17 ? 0 : -1))
            if (r0 <= 0) goto L_0x00a6
            int r0 = r7.mTotalLength
            int r1 = r10.topMargin
            int r1 = r1 + r0
            r26 = r2
            int r2 = r10.bottomMargin
            int r1 = r1 + r2
            int r0 = java.lang.Math.max(r0, r1)
            r7.mTotalLength = r0
            r0 = r3
            r3 = r4
            r31 = r5
            r18 = r16
            r8 = r24
            r29 = r26
            r24 = r13
            r13 = r22
            r22 = r11
            r11 = r6
            goto L_0x0114
        L_0x00a6:
            r26 = r2
            int r0 = r10.height
            if (r0 != 0) goto L_0x00b5
            int r0 = (r1 > r17 ? 1 : (r1 == r17 ? 0 : -1))
            if (r0 <= 0) goto L_0x00b5
            r0 = -2
            r10.height = r0
            r2 = 0
            goto L_0x00b7
        L_0x00b5:
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
        L_0x00b7:
            int r0 = (r25 > r17 ? 1 : (r25 == r17 ? 0 : -1))
            if (r0 != 0) goto L_0x00c0
            int r0 = r7.mTotalLength
            r27 = r0
            goto L_0x00c2
        L_0x00c0:
            r27 = 0
        L_0x00c2:
            r28 = 0
            r23 = 1073741824(0x40000000, float:2.0)
            r0 = r33
            r8 = r24
            r1 = r4
            r30 = r2
            r29 = r26
            r2 = r6
            r9 = r3
            r3 = r34
            r24 = r13
            r13 = r22
            r22 = r11
            r11 = r23
            r23 = r4
            r4 = r28
            r31 = r5
            r5 = r35
            r11 = r6
            r6 = r27
            r0.measureChildBeforeLayout(r1, r2, r3, r4, r5, r6)
            r1 = r30
            r0 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r1 == r0) goto L_0x00f1
            r10.height = r1
        L_0x00f1:
            int r0 = r23.getMeasuredHeight()
            int r1 = r7.mTotalLength
            int r2 = r1 + r0
            int r3 = r10.topMargin
            int r2 = r2 + r3
            int r3 = r10.bottomMargin
            int r2 = r2 + r3
            r3 = r23
            int r4 = r7.getNextLocationOffset(r3)
            int r2 = r2 + r4
            int r1 = java.lang.Math.max(r1, r2)
            r7.mTotalLength = r1
            if (r15 == 0) goto L_0x0113
            int r0 = java.lang.Math.max(r0, r9)
            goto L_0x0114
        L_0x0113:
            r0 = r9
        L_0x0114:
            if (r14 < 0) goto L_0x011e
            int r6 = r11 + 1
            if (r14 != r6) goto L_0x011e
            int r1 = r7.mTotalLength
            r7.mBaselineChildTop = r1
        L_0x011e:
            if (r11 >= r14) goto L_0x0126
            float r1 = r10.weight
            int r1 = (r1 > r17 ? 1 : (r1 == r17 ? 0 : -1))
            if (r1 > 0) goto L_0x0129
        L_0x0126:
            r1 = 1073741824(0x40000000, float:2.0)
            goto L_0x0131
        L_0x0129:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r1 = "A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex."
            r0.<init>(r1)
            throw r0
        L_0x0131:
            if (r12 == r1) goto L_0x013d
            int r1 = r10.width
            r2 = -1
            if (r1 != r2) goto L_0x013d
            r1 = r16
            r20 = r1
            goto L_0x013e
        L_0x013d:
            r1 = 0
        L_0x013e:
            int r2 = r10.leftMargin
            int r4 = r10.rightMargin
            int r2 = r2 + r4
            int r4 = r3.getMeasuredWidth()
            int r4 = r4 + r2
            r5 = r29
            int r5 = java.lang.Math.max(r5, r4)
            int r6 = r3.getMeasuredState()
            int r6 = android.view.View.combineMeasuredStates(r8, r6)
            if (r19 == 0) goto L_0x0160
            int r8 = r10.width
            r9 = -1
            if (r8 != r9) goto L_0x0160
            r19 = r16
            goto L_0x0162
        L_0x0160:
            r19 = 0
        L_0x0162:
            float r8 = r10.weight
            int r8 = (r8 > r17 ? 1 : (r8 == r17 ? 0 : -1))
            if (r8 <= 0) goto L_0x0171
            if (r1 == 0) goto L_0x016b
            goto L_0x016c
        L_0x016b:
            r2 = r4
        L_0x016c:
            int r4 = java.lang.Math.max(r13, r2)
            goto L_0x017f
        L_0x0171:
            if (r1 == 0) goto L_0x0176
        L_0x0173:
            r1 = r31
            goto L_0x0178
        L_0x0176:
            r2 = r4
            goto L_0x0173
        L_0x0178:
            int r1 = java.lang.Math.max(r1, r2)
            r31 = r1
            r4 = r13
        L_0x017f:
            int r1 = r7.getChildrenSkipCount(r3, r11)
            int r1 = r1 + r11
            r3 = r0
            r2 = r5
            r0 = r25
            r5 = r31
            r32 = r6
            r6 = r1
            r1 = r32
        L_0x018f:
            int r6 = r6 + 1
            r8 = r34
            r9 = r35
            r11 = r22
            r13 = r24
            goto L_0x002b
        L_0x019b:
            r8 = r1
            r9 = r3
            r1 = r5
            r24 = r13
            r13 = r22
            r5 = r2
            r22 = r11
            int r2 = r7.mTotalLength
            if (r2 <= 0) goto L_0x01b9
            r2 = r22
            boolean r3 = r7.hasDividerBeforeChildAt(r2)
            if (r3 == 0) goto L_0x01bb
            int r3 = r7.mTotalLength
            int r4 = r7.mDividerHeight
            int r3 = r3 + r4
            r7.mTotalLength = r3
            goto L_0x01bb
        L_0x01b9:
            r2 = r22
        L_0x01bb:
            r3 = r24
            if (r15 == 0) goto L_0x020a
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r3 == r4) goto L_0x01c5
            if (r3 != 0) goto L_0x020a
        L_0x01c5:
            r4 = 0
            r7.mTotalLength = r4
            r4 = 0
        L_0x01c9:
            if (r4 >= r2) goto L_0x020a
            android.view.View r6 = r7.getVirtualChildAt(r4)
            if (r6 != 0) goto L_0x01db
            int r6 = r7.mTotalLength
            int r11 = r7.measureNullChild(r4)
            int r6 = r6 + r11
            r7.mTotalLength = r6
            goto L_0x0205
        L_0x01db:
            int r11 = r6.getVisibility()
            if (r11 != r10) goto L_0x01e7
            int r6 = r7.getChildrenSkipCount(r6, r4)
            int r4 = r4 + r6
            goto L_0x0205
        L_0x01e7:
            android.view.ViewGroup$LayoutParams r11 = r6.getLayoutParams()
            androidx.appcompat.widget.LinearLayoutCompat$LayoutParams r11 = (androidx.appcompat.widget.LinearLayoutCompat.LayoutParams) r11
            int r14 = r7.mTotalLength
            int r21 = r14 + r9
            int r10 = r11.topMargin
            int r21 = r21 + r10
            int r10 = r11.bottomMargin
            int r21 = r21 + r10
            int r6 = r7.getNextLocationOffset(r6)
            int r6 = r21 + r6
            int r6 = java.lang.Math.max(r14, r6)
            r7.mTotalLength = r6
        L_0x0205:
            int r4 = r4 + 1
            r10 = 8
            goto L_0x01c9
        L_0x020a:
            int r4 = r7.mTotalLength
            int r6 = r33.getPaddingTop()
            int r10 = r33.getPaddingBottom()
            int r6 = r6 + r10
            int r4 = r4 + r6
            r7.mTotalLength = r4
            int r6 = r33.getSuggestedMinimumHeight()
            int r4 = java.lang.Math.max(r4, r6)
            r6 = r35
            r10 = r9
            r9 = 0
            int r4 = android.view.View.resolveSizeAndState(r4, r6, r9)
            r9 = 16777215(0xffffff, float:2.3509886E-38)
            r9 = r9 & r4
            int r11 = r7.mTotalLength
            int r9 = r9 - r11
            if (r18 != 0) goto L_0x0279
            if (r9 == 0) goto L_0x0238
            int r11 = (r0 > r17 ? 1 : (r0 == r17 ? 0 : -1))
            if (r11 <= 0) goto L_0x0238
            goto L_0x0279
        L_0x0238:
            int r0 = java.lang.Math.max(r1, r13)
            if (r15 == 0) goto L_0x0274
            r1 = 1073741824(0x40000000, float:2.0)
            if (r3 == r1) goto L_0x0274
            r1 = 0
        L_0x0243:
            if (r1 >= r2) goto L_0x0274
            android.view.View r3 = r7.getVirtualChildAt(r1)
            if (r3 == 0) goto L_0x0271
            int r9 = r3.getVisibility()
            r11 = 8
            if (r9 != r11) goto L_0x0254
            goto L_0x0271
        L_0x0254:
            android.view.ViewGroup$LayoutParams r9 = r3.getLayoutParams()
            androidx.appcompat.widget.LinearLayoutCompat$LayoutParams r9 = (androidx.appcompat.widget.LinearLayoutCompat.LayoutParams) r9
            float r9 = r9.weight
            int r9 = (r9 > r17 ? 1 : (r9 == r17 ? 0 : -1))
            if (r9 <= 0) goto L_0x0271
            int r9 = r3.getMeasuredWidth()
            r11 = 1073741824(0x40000000, float:2.0)
            int r9 = android.view.View.MeasureSpec.makeMeasureSpec(r9, r11)
            int r13 = android.view.View.MeasureSpec.makeMeasureSpec(r10, r11)
            r3.measure(r9, r13)
        L_0x0271:
            int r1 = r1 + 1
            goto L_0x0243
        L_0x0274:
            r11 = r34
            r1 = r8
            goto L_0x0367
        L_0x0279:
            float r10 = r7.mWeightSum
            int r11 = (r10 > r17 ? 1 : (r10 == r17 ? 0 : -1))
            if (r11 <= 0) goto L_0x0280
            r0 = r10
        L_0x0280:
            r10 = 0
            r7.mTotalLength = r10
            r11 = r9
            r9 = r1
            r1 = r8
            r8 = r10
        L_0x0287:
            if (r8 >= r2) goto L_0x0356
            android.view.View r13 = r7.getVirtualChildAt(r8)
            int r14 = r13.getVisibility()
            r15 = 8
            if (r14 != r15) goto L_0x029b
            r21 = r11
            r11 = r34
            goto L_0x034f
        L_0x029b:
            android.view.ViewGroup$LayoutParams r14 = r13.getLayoutParams()
            androidx.appcompat.widget.LinearLayoutCompat$LayoutParams r14 = (androidx.appcompat.widget.LinearLayoutCompat.LayoutParams) r14
            float r10 = r14.weight
            int r18 = (r10 > r17 ? 1 : (r10 == r17 ? 0 : -1))
            if (r18 <= 0) goto L_0x02fd
            float r15 = (float) r11
            float r15 = r15 * r10
            float r15 = r15 / r0
            int r15 = (int) r15
            float r0 = r0 - r10
            int r11 = r11 - r15
            int r10 = r33.getPaddingLeft()
            int r18 = r33.getPaddingRight()
            int r10 = r10 + r18
            r18 = r0
            int r0 = r14.leftMargin
            int r10 = r10 + r0
            int r0 = r14.rightMargin
            int r10 = r10 + r0
            int r0 = r14.width
            r21 = r11
            r11 = r34
            int r0 = android.view.ViewGroup.getChildMeasureSpec(r11, r10, r0)
            int r10 = r14.height
            if (r10 != 0) goto L_0x02de
            r10 = 1073741824(0x40000000, float:2.0)
            if (r3 == r10) goto L_0x02d2
            goto L_0x02e0
        L_0x02d2:
            if (r15 <= 0) goto L_0x02d5
            goto L_0x02d6
        L_0x02d5:
            r15 = 0
        L_0x02d6:
            int r15 = android.view.View.MeasureSpec.makeMeasureSpec(r15, r10)
            r13.measure(r0, r15)
            goto L_0x02f0
        L_0x02de:
            r10 = 1073741824(0x40000000, float:2.0)
        L_0x02e0:
            int r23 = r13.getMeasuredHeight()
            int r15 = r23 + r15
            if (r15 >= 0) goto L_0x02e9
            r15 = 0
        L_0x02e9:
            int r15 = android.view.View.MeasureSpec.makeMeasureSpec(r15, r10)
            r13.measure(r0, r15)
        L_0x02f0:
            int r0 = r13.getMeasuredState()
            r0 = r0 & -256(0xffffffffffffff00, float:NaN)
            int r1 = android.view.View.combineMeasuredStates(r1, r0)
            r0 = r18
            goto L_0x0302
        L_0x02fd:
            r10 = r11
            r11 = r34
            r21 = r10
        L_0x0302:
            int r10 = r14.leftMargin
            int r15 = r14.rightMargin
            int r10 = r10 + r15
            int r15 = r13.getMeasuredWidth()
            int r15 = r15 + r10
            int r5 = java.lang.Math.max(r5, r15)
            r18 = r0
            r0 = 1073741824(0x40000000, float:2.0)
            if (r12 == r0) goto L_0x031e
            int r0 = r14.width
            r23 = r1
            r1 = -1
            if (r0 != r1) goto L_0x0321
            goto L_0x0322
        L_0x031e:
            r23 = r1
            r1 = -1
        L_0x0321:
            r10 = r15
        L_0x0322:
            int r0 = java.lang.Math.max(r9, r10)
            if (r19 == 0) goto L_0x032f
            int r9 = r14.width
            if (r9 != r1) goto L_0x032f
            r9 = r16
            goto L_0x0330
        L_0x032f:
            r9 = 0
        L_0x0330:
            int r10 = r7.mTotalLength
            int r15 = r13.getMeasuredHeight()
            int r15 = r15 + r10
            int r1 = r14.topMargin
            int r15 = r15 + r1
            int r1 = r14.bottomMargin
            int r15 = r15 + r1
            int r1 = r7.getNextLocationOffset(r13)
            int r15 = r15 + r1
            int r1 = java.lang.Math.max(r10, r15)
            r7.mTotalLength = r1
            r19 = r9
            r1 = r23
            r9 = r0
            r0 = r18
        L_0x034f:
            int r8 = r8 + 1
            r11 = r21
            r10 = 0
            goto L_0x0287
        L_0x0356:
            r11 = r34
            int r0 = r7.mTotalLength
            int r3 = r33.getPaddingTop()
            int r8 = r33.getPaddingBottom()
            int r3 = r3 + r8
            int r0 = r0 + r3
            r7.mTotalLength = r0
            r0 = r9
        L_0x0367:
            if (r19 != 0) goto L_0x036e
            r3 = 1073741824(0x40000000, float:2.0)
            if (r12 == r3) goto L_0x036e
            goto L_0x036f
        L_0x036e:
            r0 = r5
        L_0x036f:
            int r3 = r33.getPaddingLeft()
            int r5 = r33.getPaddingRight()
            int r3 = r3 + r5
            int r0 = r0 + r3
            int r3 = r33.getSuggestedMinimumWidth()
            int r0 = java.lang.Math.max(r0, r3)
            int r0 = android.view.View.resolveSizeAndState(r0, r11, r1)
            r7.setMeasuredDimension(r0, r4)
            if (r20 == 0) goto L_0x038d
            r7.forceUniformWidth(r2, r6)
        L_0x038d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.LinearLayoutCompat.measureVertical(int, int):void");
    }

    private void forceUniformWidth(int i, int i2) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (layoutParams.width == -1) {
                    int i4 = layoutParams.height;
                    layoutParams.height = virtualChildAt.getMeasuredHeight();
                    measureChildWithMargins(virtualChildAt, makeMeasureSpec, 0, i2, 0);
                    layoutParams.height = i4;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:192:0x045b  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0170  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0175  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0197  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x01c0  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x01c3  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x01cb  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x01d9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void measureHorizontal(int r40, int r41) {
        /*
            r39 = this;
            r7 = r39
            r8 = r40
            r9 = r41
            r10 = 0
            r7.mTotalLength = r10
            int r11 = r39.getVirtualChildCount()
            int r12 = android.view.View.MeasureSpec.getMode(r40)
            int r13 = android.view.View.MeasureSpec.getMode(r41)
            int[] r0 = r7.mMaxAscent
            r14 = 4
            if (r0 == 0) goto L_0x001e
            int[] r0 = r7.mMaxDescent
            if (r0 != 0) goto L_0x0026
        L_0x001e:
            int[] r0 = new int[r14]
            r7.mMaxAscent = r0
            int[] r0 = new int[r14]
            r7.mMaxDescent = r0
        L_0x0026:
            int[] r15 = r7.mMaxAscent
            int[] r6 = r7.mMaxDescent
            r16 = 3
            r5 = -1
            r15[r16] = r5
            r17 = 2
            r15[r17] = r5
            r18 = 1
            r15[r18] = r5
            r15[r10] = r5
            r6[r16] = r5
            r6[r17] = r5
            r6[r18] = r5
            r6[r10] = r5
            boolean r4 = r7.mBaselineAligned
            boolean r3 = r7.mUseLargestChild
            r2 = 1073741824(0x40000000, float:2.0)
            if (r12 != r2) goto L_0x004c
            r19 = r18
            goto L_0x004e
        L_0x004c:
            r19 = r10
        L_0x004e:
            r20 = 0
            r1 = r10
            r14 = r1
            r21 = r14
            r22 = r21
            r23 = r22
            r24 = r23
            r25 = r24
            r27 = r25
            r26 = r18
            r0 = r20
        L_0x0062:
            r28 = r6
            r5 = 8
            if (r1 >= r11) goto L_0x0204
            android.view.View r6 = r7.getVirtualChildAt(r1)
            if (r6 != 0) goto L_0x0082
            int r5 = r7.mTotalLength
            int r6 = r7.measureNullChild(r1)
            int r5 = r5 + r6
            r7.mTotalLength = r5
        L_0x0077:
            r33 = r3
            r37 = r4
            r38 = r2
            r2 = r1
            r1 = r38
            goto L_0x01f1
        L_0x0082:
            int r10 = r6.getVisibility()
            if (r10 != r5) goto L_0x008e
            int r5 = r7.getChildrenSkipCount(r6, r1)
            int r1 = r1 + r5
            goto L_0x0077
        L_0x008e:
            boolean r5 = r7.hasDividerBeforeChildAt(r1)
            if (r5 == 0) goto L_0x009b
            int r5 = r7.mTotalLength
            int r10 = r7.mDividerWidth
            int r5 = r5 + r10
            r7.mTotalLength = r5
        L_0x009b:
            android.view.ViewGroup$LayoutParams r5 = r6.getLayoutParams()
            r10 = r5
            androidx.appcompat.widget.LinearLayoutCompat$LayoutParams r10 = (androidx.appcompat.widget.LinearLayoutCompat.LayoutParams) r10
            float r5 = r10.weight
            float r32 = r0 + r5
            if (r12 != r2) goto L_0x00ef
            int r0 = r10.width
            if (r0 != 0) goto L_0x00ef
            int r0 = (r5 > r20 ? 1 : (r5 == r20 ? 0 : -1))
            if (r0 <= 0) goto L_0x00ef
            if (r19 == 0) goto L_0x00bd
            int r0 = r7.mTotalLength
            int r5 = r10.leftMargin
            int r2 = r10.rightMargin
            int r5 = r5 + r2
            int r0 = r0 + r5
            r7.mTotalLength = r0
            goto L_0x00cb
        L_0x00bd:
            int r0 = r7.mTotalLength
            int r2 = r10.leftMargin
            int r2 = r2 + r0
            int r5 = r10.rightMargin
            int r2 = r2 + r5
            int r0 = java.lang.Math.max(r0, r2)
            r7.mTotalLength = r0
        L_0x00cb:
            if (r4 == 0) goto L_0x00e0
            r0 = 0
            int r2 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r0)
            r6.measure(r2, r2)
            r35 = r1
            r33 = r3
            r37 = r4
            r3 = r6
            r29 = -2
            goto L_0x0167
        L_0x00e0:
            r35 = r1
            r33 = r3
            r37 = r4
            r3 = r6
            r24 = r18
            r1 = 1073741824(0x40000000, float:2.0)
            r29 = -2
            goto L_0x0169
        L_0x00ef:
            int r0 = r10.width
            if (r0 != 0) goto L_0x00fc
            int r0 = (r5 > r20 ? 1 : (r5 == r20 ? 0 : -1))
            if (r0 <= 0) goto L_0x00fc
            r5 = -2
            r10.width = r5
            r2 = 0
            goto L_0x00ff
        L_0x00fc:
            r5 = -2
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
        L_0x00ff:
            int r0 = (r32 > r20 ? 1 : (r32 == r20 ? 0 : -1))
            if (r0 != 0) goto L_0x0108
            int r0 = r7.mTotalLength
            r29 = r0
            goto L_0x010a
        L_0x0108:
            r29 = 0
        L_0x010a:
            r34 = 0
            r0 = r39
            r35 = r1
            r1 = r6
            r36 = r2
            r2 = r35
            r33 = r3
            r3 = r40
            r37 = r4
            r4 = r29
            r29 = r5
            r9 = -1
            r5 = r41
            r31 = r6
            r9 = -2147483648(0xffffffff80000000, float:-0.0)
            r6 = r34
            r0.measureChildBeforeLayout(r1, r2, r3, r4, r5, r6)
            r0 = r36
            if (r0 == r9) goto L_0x0131
            r10.width = r0
        L_0x0131:
            int r0 = r31.getMeasuredWidth()
            if (r19 == 0) goto L_0x014a
            int r1 = r7.mTotalLength
            int r2 = r10.leftMargin
            int r2 = r2 + r0
            int r3 = r10.rightMargin
            int r2 = r2 + r3
            r3 = r31
            int r4 = r7.getNextLocationOffset(r3)
            int r2 = r2 + r4
            int r1 = r1 + r2
            r7.mTotalLength = r1
            goto L_0x0161
        L_0x014a:
            r3 = r31
            int r1 = r7.mTotalLength
            int r2 = r1 + r0
            int r4 = r10.leftMargin
            int r2 = r2 + r4
            int r4 = r10.rightMargin
            int r2 = r2 + r4
            int r4 = r7.getNextLocationOffset(r3)
            int r2 = r2 + r4
            int r1 = java.lang.Math.max(r1, r2)
            r7.mTotalLength = r1
        L_0x0161:
            if (r33 == 0) goto L_0x0167
            int r14 = java.lang.Math.max(r0, r14)
        L_0x0167:
            r1 = 1073741824(0x40000000, float:2.0)
        L_0x0169:
            if (r13 == r1) goto L_0x0175
            int r0 = r10.height
            r2 = -1
            if (r0 != r2) goto L_0x0175
            r0 = r18
            r27 = r0
            goto L_0x0176
        L_0x0175:
            r0 = 0
        L_0x0176:
            int r2 = r10.topMargin
            int r4 = r10.bottomMargin
            int r2 = r2 + r4
            int r4 = r3.getMeasuredHeight()
            int r4 = r4 + r2
            int r5 = r3.getMeasuredState()
            r6 = r25
            int r25 = android.view.View.combineMeasuredStates(r6, r5)
            if (r37 == 0) goto L_0x01b3
            int r5 = r3.getBaseline()
            r6 = -1
            if (r5 == r6) goto L_0x01b3
            int r6 = r10.gravity
            if (r6 >= 0) goto L_0x0199
            int r6 = r7.mGravity
        L_0x0199:
            r6 = r6 & 112(0x70, float:1.57E-43)
            r9 = 4
            int r6 = r6 >> r9
            r6 = r6 & -2
            int r6 = r6 >> 1
            r9 = r15[r6]
            int r9 = java.lang.Math.max(r9, r5)
            r15[r6] = r9
            r9 = r28[r6]
            int r5 = r4 - r5
            int r5 = java.lang.Math.max(r9, r5)
            r28[r6] = r5
        L_0x01b3:
            r5 = r21
            int r21 = java.lang.Math.max(r5, r4)
            if (r26 == 0) goto L_0x01c3
            int r5 = r10.height
            r6 = -1
            if (r5 != r6) goto L_0x01c3
            r26 = r18
            goto L_0x01c5
        L_0x01c3:
            r26 = 0
        L_0x01c5:
            float r5 = r10.weight
            int r5 = (r5 > r20 ? 1 : (r5 == r20 ? 0 : -1))
            if (r5 <= 0) goto L_0x01d9
            if (r0 == 0) goto L_0x01d0
        L_0x01cd:
            r10 = r23
            goto L_0x01d2
        L_0x01d0:
            r2 = r4
            goto L_0x01cd
        L_0x01d2:
            int r23 = java.lang.Math.max(r10, r2)
        L_0x01d6:
            r10 = r35
            goto L_0x01e9
        L_0x01d9:
            r10 = r23
            if (r0 == 0) goto L_0x01e0
        L_0x01dd:
            r4 = r22
            goto L_0x01e2
        L_0x01e0:
            r2 = r4
            goto L_0x01dd
        L_0x01e2:
            int r22 = java.lang.Math.max(r4, r2)
            r23 = r10
            goto L_0x01d6
        L_0x01e9:
            int r0 = r7.getChildrenSkipCount(r3, r10)
            int r0 = r0 + r10
            r2 = r0
            r0 = r32
        L_0x01f1:
            int r2 = r2 + 1
            r9 = r41
            r6 = r28
            r3 = r33
            r4 = r37
            r5 = -1
            r10 = 0
            r38 = r2
            r2 = r1
            r1 = r38
            goto L_0x0062
        L_0x0204:
            r1 = r2
            r33 = r3
            r37 = r4
            r2 = r21
            r4 = r22
            r10 = r23
            r6 = r25
            r9 = -2147483648(0xffffffff80000000, float:-0.0)
            r29 = -2
            int r3 = r7.mTotalLength
            if (r3 <= 0) goto L_0x0226
            boolean r3 = r7.hasDividerBeforeChildAt(r11)
            if (r3 == 0) goto L_0x0226
            int r3 = r7.mTotalLength
            int r1 = r7.mDividerWidth
            int r3 = r3 + r1
            r7.mTotalLength = r3
        L_0x0226:
            r1 = r15[r18]
            r3 = -1
            if (r1 != r3) goto L_0x023e
            r21 = 0
            r5 = r15[r21]
            if (r5 != r3) goto L_0x023e
            r5 = r15[r17]
            if (r5 != r3) goto L_0x023e
            r5 = r15[r16]
            if (r5 == r3) goto L_0x023a
            goto L_0x023e
        L_0x023a:
            r1 = r2
            r21 = r6
            goto L_0x026d
        L_0x023e:
            r3 = r15[r16]
            r5 = 0
            r9 = r15[r5]
            r5 = r15[r17]
            int r1 = java.lang.Math.max(r1, r5)
            int r1 = java.lang.Math.max(r9, r1)
            int r1 = java.lang.Math.max(r3, r1)
            r3 = r28[r16]
            r5 = 0
            r9 = r28[r5]
            r5 = r28[r18]
            r21 = r6
            r6 = r28[r17]
            int r5 = java.lang.Math.max(r5, r6)
            int r5 = java.lang.Math.max(r9, r5)
            int r3 = java.lang.Math.max(r3, r5)
            int r1 = r1 + r3
            int r1 = java.lang.Math.max(r2, r1)
        L_0x026d:
            if (r33 == 0) goto L_0x0277
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r12 == r2) goto L_0x0275
            if (r12 != 0) goto L_0x0277
        L_0x0275:
            r2 = 0
            goto L_0x027a
        L_0x0277:
            r23 = r1
            goto L_0x02d4
        L_0x027a:
            r7.mTotalLength = r2
            r2 = 0
        L_0x027d:
            if (r2 >= r11) goto L_0x0277
            android.view.View r3 = r7.getVirtualChildAt(r2)
            if (r3 != 0) goto L_0x028f
            int r3 = r7.mTotalLength
            int r5 = r7.measureNullChild(r2)
            int r3 = r3 + r5
            r7.mTotalLength = r3
            goto L_0x029c
        L_0x028f:
            int r5 = r3.getVisibility()
            r6 = 8
            if (r5 != r6) goto L_0x029f
            int r3 = r7.getChildrenSkipCount(r3, r2)
            int r2 = r2 + r3
        L_0x029c:
            r23 = r1
            goto L_0x02cf
        L_0x029f:
            android.view.ViewGroup$LayoutParams r5 = r3.getLayoutParams()
            androidx.appcompat.widget.LinearLayoutCompat$LayoutParams r5 = (androidx.appcompat.widget.LinearLayoutCompat.LayoutParams) r5
            if (r19 == 0) goto L_0x02b8
            int r6 = r7.mTotalLength
            int r9 = r5.leftMargin
            int r9 = r9 + r14
            int r5 = r5.rightMargin
            int r9 = r9 + r5
            int r3 = r7.getNextLocationOffset(r3)
            int r9 = r9 + r3
            int r6 = r6 + r9
            r7.mTotalLength = r6
            goto L_0x029c
        L_0x02b8:
            int r6 = r7.mTotalLength
            int r9 = r6 + r14
            r23 = r1
            int r1 = r5.leftMargin
            int r9 = r9 + r1
            int r1 = r5.rightMargin
            int r9 = r9 + r1
            int r1 = r7.getNextLocationOffset(r3)
            int r9 = r9 + r1
            int r1 = java.lang.Math.max(r6, r9)
            r7.mTotalLength = r1
        L_0x02cf:
            int r2 = r2 + 1
            r1 = r23
            goto L_0x027d
        L_0x02d4:
            int r1 = r7.mTotalLength
            int r2 = r39.getPaddingLeft()
            int r3 = r39.getPaddingRight()
            int r2 = r2 + r3
            int r1 = r1 + r2
            r7.mTotalLength = r1
            int r2 = r39.getSuggestedMinimumWidth()
            int r1 = java.lang.Math.max(r1, r2)
            r2 = 0
            int r1 = android.view.View.resolveSizeAndState(r1, r8, r2)
            r2 = 16777215(0xffffff, float:2.3509886E-38)
            r2 = r2 & r1
            int r3 = r7.mTotalLength
            int r2 = r2 - r3
            if (r24 != 0) goto L_0x0343
            if (r2 == 0) goto L_0x02ff
            int r5 = (r0 > r20 ? 1 : (r0 == r20 ? 0 : -1))
            if (r5 <= 0) goto L_0x02ff
            goto L_0x0343
        L_0x02ff:
            int r0 = java.lang.Math.max(r4, r10)
            if (r33 == 0) goto L_0x033b
            r2 = 1073741824(0x40000000, float:2.0)
            if (r12 == r2) goto L_0x033b
            r10 = 0
        L_0x030a:
            if (r10 >= r11) goto L_0x033b
            android.view.View r2 = r7.getVirtualChildAt(r10)
            if (r2 == 0) goto L_0x0338
            int r4 = r2.getVisibility()
            r5 = 8
            if (r4 != r5) goto L_0x031b
            goto L_0x0338
        L_0x031b:
            android.view.ViewGroup$LayoutParams r4 = r2.getLayoutParams()
            androidx.appcompat.widget.LinearLayoutCompat$LayoutParams r4 = (androidx.appcompat.widget.LinearLayoutCompat.LayoutParams) r4
            float r4 = r4.weight
            int r4 = (r4 > r20 ? 1 : (r4 == r20 ? 0 : -1))
            if (r4 <= 0) goto L_0x0338
            r4 = 1073741824(0x40000000, float:2.0)
            int r5 = android.view.View.MeasureSpec.makeMeasureSpec(r14, r4)
            int r6 = r2.getMeasuredHeight()
            int r6 = android.view.View.MeasureSpec.makeMeasureSpec(r6, r4)
            r2.measure(r5, r6)
        L_0x0338:
            int r10 = r10 + 1
            goto L_0x030a
        L_0x033b:
            r2 = r41
            r25 = r11
            r9 = r21
            goto L_0x04db
        L_0x0343:
            float r5 = r7.mWeightSum
            int r6 = (r5 > r20 ? 1 : (r5 == r20 ? 0 : -1))
            if (r6 <= 0) goto L_0x034a
            r0 = r5
        L_0x034a:
            r5 = -1
            r15[r16] = r5
            r15[r17] = r5
            r15[r18] = r5
            r6 = 0
            r15[r6] = r5
            r28[r16] = r5
            r28[r17] = r5
            r28[r18] = r5
            r28[r6] = r5
            r7.mTotalLength = r6
            r6 = r4
            r4 = r5
            r9 = r21
            r10 = 0
        L_0x0363:
            if (r10 >= r11) goto L_0x0485
            android.view.View r14 = r7.getVirtualChildAt(r10)
            if (r14 == 0) goto L_0x0373
            int r5 = r14.getVisibility()
            r3 = 8
            if (r5 != r3) goto L_0x037a
        L_0x0373:
            r3 = r2
            r25 = r11
            r2 = r41
            goto L_0x047b
        L_0x037a:
            android.view.ViewGroup$LayoutParams r5 = r14.getLayoutParams()
            androidx.appcompat.widget.LinearLayoutCompat$LayoutParams r5 = (androidx.appcompat.widget.LinearLayoutCompat.LayoutParams) r5
            float r3 = r5.weight
            int r23 = (r3 > r20 ? 1 : (r3 == r20 ? 0 : -1))
            if (r23 <= 0) goto L_0x03e2
            float r8 = (float) r2
            float r8 = r8 * r3
            float r8 = r8 / r0
            int r8 = (int) r8
            float r0 = r0 - r3
            int r2 = r2 - r8
            int r3 = r39.getPaddingTop()
            int r23 = r39.getPaddingBottom()
            int r3 = r3 + r23
            r23 = r0
            int r0 = r5.topMargin
            int r3 = r3 + r0
            int r0 = r5.bottomMargin
            int r3 = r3 + r0
            int r0 = r5.height
            r24 = r2
            r25 = r11
            r11 = -1
            r2 = r41
            int r0 = android.view.ViewGroup.getChildMeasureSpec(r2, r3, r0)
            int r3 = r5.width
            if (r3 != 0) goto L_0x03c0
            r3 = 1073741824(0x40000000, float:2.0)
            if (r12 == r3) goto L_0x03b4
            goto L_0x03c2
        L_0x03b4:
            if (r8 <= 0) goto L_0x03b7
            goto L_0x03b8
        L_0x03b7:
            r8 = 0
        L_0x03b8:
            int r8 = android.view.View.MeasureSpec.makeMeasureSpec(r8, r3)
            r14.measure(r8, r0)
            goto L_0x03d2
        L_0x03c0:
            r3 = 1073741824(0x40000000, float:2.0)
        L_0x03c2:
            int r30 = r14.getMeasuredWidth()
            int r8 = r30 + r8
            if (r8 >= 0) goto L_0x03cb
            r8 = 0
        L_0x03cb:
            int r8 = android.view.View.MeasureSpec.makeMeasureSpec(r8, r3)
            r14.measure(r8, r0)
        L_0x03d2:
            int r0 = r14.getMeasuredState()
            r3 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r0 = r0 & r3
            int r9 = android.view.View.combineMeasuredStates(r9, r0)
            r0 = r23
            r3 = r24
            goto L_0x03e8
        L_0x03e2:
            r3 = r2
            r25 = r11
            r11 = -1
            r2 = r41
        L_0x03e8:
            if (r19 == 0) goto L_0x0407
            int r8 = r7.mTotalLength
            int r23 = r14.getMeasuredWidth()
            int r11 = r5.leftMargin
            int r23 = r23 + r11
            int r11 = r5.rightMargin
            int r23 = r23 + r11
            int r11 = r7.getNextLocationOffset(r14)
            int r23 = r23 + r11
            int r8 = r8 + r23
            r7.mTotalLength = r8
            r23 = r0
        L_0x0404:
            r0 = 1073741824(0x40000000, float:2.0)
            goto L_0x0422
        L_0x0407:
            int r8 = r7.mTotalLength
            int r11 = r14.getMeasuredWidth()
            int r11 = r11 + r8
            r23 = r0
            int r0 = r5.leftMargin
            int r11 = r11 + r0
            int r0 = r5.rightMargin
            int r11 = r11 + r0
            int r0 = r7.getNextLocationOffset(r14)
            int r11 = r11 + r0
            int r0 = java.lang.Math.max(r8, r11)
            r7.mTotalLength = r0
            goto L_0x0404
        L_0x0422:
            if (r13 == r0) goto L_0x042c
            int r0 = r5.height
            r8 = -1
            if (r0 != r8) goto L_0x042c
            r0 = r18
            goto L_0x042d
        L_0x042c:
            r0 = 0
        L_0x042d:
            int r8 = r5.topMargin
            int r11 = r5.bottomMargin
            int r8 = r8 + r11
            int r11 = r14.getMeasuredHeight()
            int r11 = r11 + r8
            int r4 = java.lang.Math.max(r4, r11)
            if (r0 == 0) goto L_0x043e
            goto L_0x043f
        L_0x043e:
            r8 = r11
        L_0x043f:
            int r0 = java.lang.Math.max(r6, r8)
            if (r26 == 0) goto L_0x044d
            int r6 = r5.height
            r8 = -1
            if (r6 != r8) goto L_0x044e
            r6 = r18
            goto L_0x044f
        L_0x044d:
            r8 = -1
        L_0x044e:
            r6 = 0
        L_0x044f:
            if (r37 == 0) goto L_0x0476
            int r14 = r14.getBaseline()
            if (r14 == r8) goto L_0x0476
            int r5 = r5.gravity
            if (r5 >= 0) goto L_0x045d
            int r5 = r7.mGravity
        L_0x045d:
            r5 = r5 & 112(0x70, float:1.57E-43)
            r8 = 4
            int r5 = r5 >> r8
            r5 = r5 & -2
            int r5 = r5 >> 1
            r8 = r15[r5]
            int r8 = java.lang.Math.max(r8, r14)
            r15[r5] = r8
            r8 = r28[r5]
            int r11 = r11 - r14
            int r8 = java.lang.Math.max(r8, r11)
            r28[r5] = r8
        L_0x0476:
            r26 = r6
            r6 = r0
            r0 = r23
        L_0x047b:
            int r10 = r10 + 1
            r8 = r40
            r2 = r3
            r11 = r25
            r5 = -1
            goto L_0x0363
        L_0x0485:
            r2 = r41
            r25 = r11
            int r0 = r7.mTotalLength
            int r3 = r39.getPaddingLeft()
            int r5 = r39.getPaddingRight()
            int r3 = r3 + r5
            int r0 = r0 + r3
            r7.mTotalLength = r0
            r0 = r15[r18]
            r3 = -1
            if (r0 != r3) goto L_0x04ac
            r5 = 0
            r8 = r15[r5]
            if (r8 != r3) goto L_0x04ac
            r5 = r15[r17]
            if (r5 != r3) goto L_0x04ac
            r5 = r15[r16]
            if (r5 == r3) goto L_0x04aa
            goto L_0x04ac
        L_0x04aa:
            r0 = r4
            goto L_0x04d8
        L_0x04ac:
            r3 = r15[r16]
            r5 = 0
            r8 = r15[r5]
            r10 = r15[r17]
            int r0 = java.lang.Math.max(r0, r10)
            int r0 = java.lang.Math.max(r8, r0)
            int r0 = java.lang.Math.max(r3, r0)
            r3 = r28[r16]
            r5 = r28[r5]
            r8 = r28[r18]
            r10 = r28[r17]
            int r8 = java.lang.Math.max(r8, r10)
            int r5 = java.lang.Math.max(r5, r8)
            int r3 = java.lang.Math.max(r3, r5)
            int r0 = r0 + r3
            int r0 = java.lang.Math.max(r4, r0)
        L_0x04d8:
            r23 = r0
            r0 = r6
        L_0x04db:
            if (r26 != 0) goto L_0x04e2
            r3 = 1073741824(0x40000000, float:2.0)
            if (r13 == r3) goto L_0x04e2
            goto L_0x04e4
        L_0x04e2:
            r0 = r23
        L_0x04e4:
            int r3 = r39.getPaddingTop()
            int r4 = r39.getPaddingBottom()
            int r3 = r3 + r4
            int r0 = r0 + r3
            int r3 = r39.getSuggestedMinimumHeight()
            int r0 = java.lang.Math.max(r0, r3)
            r3 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r3 = r3 & r9
            r1 = r1 | r3
            int r3 = r9 << 16
            int r0 = android.view.View.resolveSizeAndState(r0, r2, r3)
            r7.setMeasuredDimension(r1, r0)
            if (r27 == 0) goto L_0x050c
            r0 = r40
            r1 = r25
            r7.forceUniformHeight(r1, r0)
        L_0x050c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.LinearLayoutCompat.measureHorizontal(int, int):void");
    }

    private void forceUniformHeight(int i, int i2) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (layoutParams.height == -1) {
                    int i4 = layoutParams.width;
                    layoutParams.width = virtualChildAt.getMeasuredWidth();
                    measureChildWithMargins(virtualChildAt, i2, 0, makeMeasureSpec, 0);
                    layoutParams.width = i4;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void measureChildBeforeLayout(View view, int i, int i2, int i3, int i4, int i5) {
        measureChildWithMargins(view, i2, i3, i4, i5);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.mOrientation == 1) {
            layoutVertical(i, i2, i3, i4);
        } else {
            layoutHorizontal(i, i2, i3, i4);
        }
    }

    /* access modifiers changed from: package-private */
    public void layoutVertical(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int paddingLeft = getPaddingLeft();
        int i10 = i3 - i;
        int paddingRight = i10 - getPaddingRight();
        int paddingRight2 = (i10 - paddingLeft) - getPaddingRight();
        int virtualChildCount = getVirtualChildCount();
        int i11 = this.mGravity;
        int i12 = i11 & 112;
        int i13 = i11 & 8388615;
        if (i12 == 16) {
            i5 = getPaddingTop() + (((i4 - i2) - this.mTotalLength) / 2);
        } else if (i12 != 80) {
            i5 = getPaddingTop();
        } else {
            i5 = ((getPaddingTop() + i4) - i2) - this.mTotalLength;
        }
        int i14 = 0;
        while (i14 < virtualChildCount) {
            View virtualChildAt = getVirtualChildAt(i14);
            if (virtualChildAt == null) {
                i5 += measureNullChild(i14);
            } else if (virtualChildAt.getVisibility() != 8) {
                int measuredWidth = virtualChildAt.getMeasuredWidth();
                int measuredHeight = virtualChildAt.getMeasuredHeight();
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                int i15 = layoutParams.gravity;
                if (i15 < 0) {
                    i15 = i13;
                }
                int absoluteGravity = GravityCompat.getAbsoluteGravity(i15, getLayoutDirection()) & 7;
                if (absoluteGravity == 1) {
                    i7 = ((paddingRight2 - measuredWidth) / 2) + paddingLeft + layoutParams.leftMargin;
                    i8 = layoutParams.rightMargin;
                    i9 = i7 - i8;
                } else if (absoluteGravity != 5) {
                    i9 = layoutParams.leftMargin + paddingLeft;
                } else {
                    i7 = paddingRight - measuredWidth;
                    i8 = layoutParams.rightMargin;
                    i9 = i7 - i8;
                }
                int i16 = i9;
                if (hasDividerBeforeChildAt(i14)) {
                    i5 += this.mDividerHeight;
                }
                int i17 = i5 + layoutParams.topMargin;
                setChildFrame(virtualChildAt, i16, i17 + getLocationOffset(virtualChildAt), measuredWidth, measuredHeight);
                i14 += getChildrenSkipCount(virtualChildAt, i14);
                i5 = i17 + measuredHeight + layoutParams.bottomMargin + getNextLocationOffset(virtualChildAt);
                i6 = 1;
                i14 += i6;
            }
            i6 = 1;
            i14 += i6;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0100  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void layoutHorizontal(int r25, int r26, int r27, int r28) {
        /*
            r24 = this;
            r6 = r24
            boolean r0 = androidx.appcompat.widget.ViewUtils.isLayoutRtl(r24)
            int r7 = r24.getPaddingTop()
            int r1 = r28 - r26
            int r2 = r24.getPaddingBottom()
            int r8 = r1 - r2
            int r1 = r1 - r7
            int r2 = r24.getPaddingBottom()
            int r9 = r1 - r2
            int r10 = r24.getVirtualChildCount()
            int r1 = r6.mGravity
            r2 = 8388615(0x800007, float:1.1754953E-38)
            r2 = r2 & r1
            r11 = r1 & 112(0x70, float:1.57E-43)
            boolean r12 = r6.mBaselineAligned
            int[] r13 = r6.mMaxAscent
            int[] r14 = r6.mMaxDescent
            int r1 = r24.getLayoutDirection()
            int r1 = androidx.core.view.GravityCompat.getAbsoluteGravity(r2, r1)
            r15 = 2
            r5 = 1
            if (r1 == r5) goto L_0x004b
            r2 = 5
            if (r1 == r2) goto L_0x003f
            int r1 = r24.getPaddingLeft()
            goto L_0x0056
        L_0x003f:
            int r1 = r24.getPaddingLeft()
            int r1 = r1 + r27
            int r1 = r1 - r25
            int r2 = r6.mTotalLength
            int r1 = r1 - r2
            goto L_0x0056
        L_0x004b:
            int r1 = r24.getPaddingLeft()
            int r2 = r27 - r25
            int r3 = r6.mTotalLength
            int r2 = r2 - r3
            int r2 = r2 / r15
            int r1 = r1 + r2
        L_0x0056:
            r2 = 0
            if (r0 == 0) goto L_0x0060
            int r0 = r10 + -1
            r16 = r0
            r17 = -1
            goto L_0x0064
        L_0x0060:
            r16 = r2
            r17 = r5
        L_0x0064:
            r3 = r2
        L_0x0065:
            if (r3 >= r10) goto L_0x014a
            int r0 = r17 * r3
            int r2 = r16 + r0
            android.view.View r0 = r6.getVirtualChildAt(r2)
            if (r0 != 0) goto L_0x0082
            int r0 = r6.measureNullChild(r2)
            int r1 = r1 + r0
            r21 = r5
            r23 = r7
            r19 = r10
            r20 = r11
        L_0x007e:
            r22 = -1
            goto L_0x013d
        L_0x0082:
            int r5 = r0.getVisibility()
            r15 = 8
            if (r5 == r15) goto L_0x0131
            int r15 = r0.getMeasuredWidth()
            int r5 = r0.getMeasuredHeight()
            android.view.ViewGroup$LayoutParams r18 = r0.getLayoutParams()
            r4 = r18
            androidx.appcompat.widget.LinearLayoutCompat$LayoutParams r4 = (androidx.appcompat.widget.LinearLayoutCompat.LayoutParams) r4
            r18 = r3
            if (r12 == 0) goto L_0x00aa
            int r3 = r4.height
            r19 = r10
            r10 = -1
            if (r3 == r10) goto L_0x00ac
            int r10 = r0.getBaseline()
            goto L_0x00ad
        L_0x00aa:
            r19 = r10
        L_0x00ac:
            r10 = -1
        L_0x00ad:
            int r3 = r4.gravity
            if (r3 >= 0) goto L_0x00b2
            r3 = r11
        L_0x00b2:
            r3 = r3 & 112(0x70, float:1.57E-43)
            r20 = r11
            r11 = 16
            if (r3 == r11) goto L_0x00ec
            r11 = 48
            if (r3 == r11) goto L_0x00dd
            r11 = 80
            if (r3 == r11) goto L_0x00c7
            r3 = r7
            r11 = -1
        L_0x00c4:
            r21 = 1
            goto L_0x00fa
        L_0x00c7:
            int r3 = r8 - r5
            int r11 = r4.bottomMargin
            int r3 = r3 - r11
            r11 = -1
            if (r10 == r11) goto L_0x00c4
            int r21 = r0.getMeasuredHeight()
            int r21 = r21 - r10
            r10 = 2
            r22 = r14[r10]
            int r22 = r22 - r21
            int r3 = r3 - r22
            goto L_0x00c4
        L_0x00dd:
            r11 = -1
            int r3 = r4.topMargin
            int r3 = r3 + r7
            if (r10 == r11) goto L_0x00c4
            r21 = 1
            r22 = r13[r21]
            int r22 = r22 - r10
            int r3 = r3 + r22
            goto L_0x00fa
        L_0x00ec:
            r11 = -1
            r21 = 1
            int r3 = r9 - r5
            r10 = 2
            int r3 = r3 / r10
            int r3 = r3 + r7
            int r10 = r4.topMargin
            int r3 = r3 + r10
            int r10 = r4.bottomMargin
            int r3 = r3 - r10
        L_0x00fa:
            boolean r10 = r6.hasDividerBeforeChildAt(r2)
            if (r10 == 0) goto L_0x0103
            int r10 = r6.mDividerWidth
            int r1 = r1 + r10
        L_0x0103:
            int r10 = r4.leftMargin
            int r10 = r10 + r1
            int r1 = r6.getLocationOffset(r0)
            int r22 = r10 + r1
            r25 = r0
            r0 = r24
            r1 = r25
            r11 = r2
            r2 = r22
            r23 = r7
            r22 = -1
            r7 = r4
            r4 = r15
            r0.setChildFrame(r1, r2, r3, r4, r5)
            int r0 = r7.rightMargin
            int r15 = r15 + r0
            r0 = r25
            int r1 = r6.getNextLocationOffset(r0)
            int r15 = r15 + r1
            int r10 = r10 + r15
            int r0 = r6.getChildrenSkipCount(r0, r11)
            int r3 = r18 + r0
            r1 = r10
            goto L_0x013d
        L_0x0131:
            r18 = r3
            r23 = r7
            r19 = r10
            r20 = r11
            r21 = 1
            goto L_0x007e
        L_0x013d:
            int r3 = r3 + 1
            r10 = r19
            r11 = r20
            r5 = r21
            r7 = r23
            r15 = 2
            goto L_0x0065
        L_0x014a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.LinearLayoutCompat.layoutHorizontal(int, int, int, int):void");
    }

    private void setChildFrame(View view, int i, int i2, int i3, int i4) {
        view.layout(i, i2, i3 + i, i4 + i2);
    }

    public void setOrientation(int i) {
        if (this.mOrientation != i) {
            this.mOrientation = i;
            requestLayout();
        }
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public void setGravity(int i) {
        if (this.mGravity != i) {
            if ((8388615 & i) == 0) {
                i |= 8388611;
            }
            if ((i & 112) == 0) {
                i |= 48;
            }
            this.mGravity = i;
            requestLayout();
        }
    }

    public int getGravity() {
        return this.mGravity;
    }

    public void setHorizontalGravity(int i) {
        int i2 = i & 8388615;
        int i3 = this.mGravity;
        if ((8388615 & i3) != i2) {
            this.mGravity = i2 | (-8388616 & i3);
            requestLayout();
        }
    }

    public void setVerticalGravity(int i) {
        int i2 = i & 112;
        int i3 = this.mGravity;
        if ((i3 & 112) != i2) {
            this.mGravity = i2 | (i3 & -113);
            requestLayout();
        }
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        int i = this.mOrientation;
        if (i == 0) {
            return new LayoutParams(-2, -2);
        }
        if (i == 1) {
            return new LayoutParams(-1, -2);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) (LayoutParams) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("androidx.appcompat.widget.LinearLayoutCompat");
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("androidx.appcompat.widget.LinearLayoutCompat");
    }

    public static class LayoutParams extends LinearLayout.LayoutParams {
        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }
    }
}
