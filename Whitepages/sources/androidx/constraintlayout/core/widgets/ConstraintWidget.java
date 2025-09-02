package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.Cache;
import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.state.WidgetFrame;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.analyzer.ChainRun;
import androidx.constraintlayout.core.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun;
import androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun;
import androidx.constraintlayout.core.widgets.analyzer.WidgetRun;
import com.facebook.react.uimanager.ViewProps;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class ConstraintWidget {
    public static float DEFAULT_BIAS = 0.5f;
    private boolean OPTIMIZE_WRAP = false;
    private boolean OPTIMIZE_WRAP_ON_RESOLVED = true;
    public WidgetFrame frame = new WidgetFrame(this);
    private boolean hasBaseline = false;
    public ChainRun horizontalChainRun;
    public int horizontalGroup;
    public HorizontalWidgetRun horizontalRun = null;
    private boolean horizontalSolvingPass = false;
    private boolean inPlaceholder;
    public boolean[] isTerminalWidget = {true, true};
    protected ArrayList mAnchors;
    private boolean mAnimated;
    public ConstraintAnchor mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
    int mBaselineDistance;
    public ConstraintAnchor mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
    public ConstraintAnchor mCenter;
    ConstraintAnchor mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
    ConstraintAnchor mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
    private float mCircleConstraintAngle = 0.0f;
    private Object mCompanionWidget;
    private int mContainerItemSkip;
    private String mDebugName;
    public float mDimensionRatio;
    protected int mDimensionRatioSide;
    boolean mGroupsToSolver;
    int mHeight;
    private int mHeightOverride = -1;
    float mHorizontalBiasPercent;
    boolean mHorizontalChainFixedPosition;
    int mHorizontalChainStyle;
    ConstraintWidget mHorizontalNextWidget;
    public int mHorizontalResolution = -1;
    boolean mHorizontalWrapVisited;
    private boolean mInVirtualLayout = false;
    private boolean[] mIsInBarrier;
    private int mLastHorizontalMeasureSpec = 0;
    private int mLastVerticalMeasureSpec = 0;
    public ConstraintAnchor mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
    public ConstraintAnchor[] mListAnchors;
    public DimensionBehaviour[] mListDimensionBehaviors;
    protected ConstraintWidget[] mListNextMatchConstraintsWidget;
    public int mMatchConstraintDefaultHeight = 0;
    public int mMatchConstraintDefaultWidth = 0;
    public int mMatchConstraintMaxHeight = 0;
    public int mMatchConstraintMaxWidth = 0;
    public int mMatchConstraintMinHeight = 0;
    public int mMatchConstraintMinWidth = 0;
    public float mMatchConstraintPercentHeight = 1.0f;
    public float mMatchConstraintPercentWidth = 1.0f;
    private int[] mMaxDimension = {Integer.MAX_VALUE, Integer.MAX_VALUE};
    private boolean mMeasureRequested = true;
    protected int mMinHeight;
    protected int mMinWidth;
    protected ConstraintWidget[] mNextChainWidget;
    protected int mOffsetX;
    protected int mOffsetY;
    public ConstraintWidget mParent;
    int mRelX;
    int mRelY;
    float mResolvedDimensionRatio = 1.0f;
    int mResolvedDimensionRatioSide = -1;
    boolean mResolvedHasRatio = false;
    public int[] mResolvedMatchConstraintDefault = new int[2];
    public ConstraintAnchor mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
    public ConstraintAnchor mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
    private String mType;
    float mVerticalBiasPercent;
    boolean mVerticalChainFixedPosition;
    int mVerticalChainStyle;
    ConstraintWidget mVerticalNextWidget;
    public int mVerticalResolution = -1;
    boolean mVerticalWrapVisited;
    private int mVisibility;
    public float[] mWeight;
    int mWidth;
    private int mWidthOverride = -1;
    private int mWrapBehaviorInParent = 0;
    protected int mX;
    protected int mY;
    public boolean measured = false;
    private boolean resolvedHorizontal = false;
    private boolean resolvedVertical = false;
    public WidgetRun[] run = new WidgetRun[2];
    public String stringId;
    public ChainRun verticalChainRun;
    public int verticalGroup;
    public VerticalWidgetRun verticalRun = null;
    private boolean verticalSolvingPass = false;

    public enum DimensionBehaviour {
        FIXED,
        WRAP_CONTENT,
        MATCH_CONSTRAINT,
        MATCH_PARENT
    }

    public WidgetRun getRun(int i) {
        if (i == 0) {
            return this.horizontalRun;
        }
        if (i == 1) {
            return this.verticalRun;
        }
        return null;
    }

    public void setFinalLeft(int i) {
        this.mLeft.setFinalValue(i);
        this.mX = i;
    }

    public void setFinalTop(int i) {
        this.mTop.setFinalValue(i);
        this.mY = i;
    }

    public boolean isHorizontalSolvingPassDone() {
        return this.horizontalSolvingPass;
    }

    public boolean isVerticalSolvingPassDone() {
        return this.verticalSolvingPass;
    }

    public void markHorizontalSolvingPassDone() {
        this.horizontalSolvingPass = true;
    }

    public void markVerticalSolvingPassDone() {
        this.verticalSolvingPass = true;
    }

    public void setFinalHorizontal(int i, int i2) {
        if (!this.resolvedHorizontal) {
            this.mLeft.setFinalValue(i);
            this.mRight.setFinalValue(i2);
            this.mX = i;
            this.mWidth = i2 - i;
            this.resolvedHorizontal = true;
        }
    }

    public void setFinalVertical(int i, int i2) {
        if (!this.resolvedVertical) {
            this.mTop.setFinalValue(i);
            this.mBottom.setFinalValue(i2);
            this.mY = i;
            this.mHeight = i2 - i;
            if (this.hasBaseline) {
                this.mBaseline.setFinalValue(i + this.mBaselineDistance);
            }
            this.resolvedVertical = true;
        }
    }

    public void setFinalBaseline(int i) {
        if (this.hasBaseline) {
            int i2 = i - this.mBaselineDistance;
            int i3 = this.mHeight + i2;
            this.mY = i2;
            this.mTop.setFinalValue(i2);
            this.mBottom.setFinalValue(i3);
            this.mBaseline.setFinalValue(i);
            this.resolvedVertical = true;
        }
    }

    public boolean isResolvedHorizontally() {
        return this.resolvedHorizontal || (this.mLeft.hasFinalValue() && this.mRight.hasFinalValue());
    }

    public boolean isResolvedVertically() {
        return this.resolvedVertical || (this.mTop.hasFinalValue() && this.mBottom.hasFinalValue());
    }

    public void resetFinalResolution() {
        this.resolvedHorizontal = false;
        this.resolvedVertical = false;
        this.horizontalSolvingPass = false;
        this.verticalSolvingPass = false;
        int size = this.mAnchors.size();
        for (int i = 0; i < size; i++) {
            ((ConstraintAnchor) this.mAnchors.get(i)).resetFinalResolution();
        }
    }

    public boolean hasDependencies() {
        int size = this.mAnchors.size();
        for (int i = 0; i < size; i++) {
            if (((ConstraintAnchor) this.mAnchors.get(i)).hasDependents()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasDanglingDimension(int i) {
        if (i == 0) {
            return (this.mLeft.mTarget != null ? 1 : 0) + (this.mRight.mTarget != null ? 1 : 0) < 2;
        }
        if ((this.mTop.mTarget != null ? 1 : 0) + (this.mBottom.mTarget != null ? 1 : 0) + (this.mBaseline.mTarget != null ? 1 : 0) < 2) {
            return true;
        }
        return false;
    }

    public boolean hasResolvedTargets(int i, int i2) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        if (i == 0) {
            ConstraintAnchor constraintAnchor3 = this.mLeft.mTarget;
            if (constraintAnchor3 != null && constraintAnchor3.hasFinalValue() && (constraintAnchor2 = this.mRight.mTarget) != null && constraintAnchor2.hasFinalValue()) {
                if ((this.mRight.mTarget.getFinalValue() - this.mRight.getMargin()) - (this.mLeft.mTarget.getFinalValue() + this.mLeft.getMargin()) >= i2) {
                    return true;
                }
                return false;
            }
        } else {
            ConstraintAnchor constraintAnchor4 = this.mTop.mTarget;
            if (constraintAnchor4 != null && constraintAnchor4.hasFinalValue() && (constraintAnchor = this.mBottom.mTarget) != null && constraintAnchor.hasFinalValue()) {
                if ((this.mBottom.mTarget.getFinalValue() - this.mBottom.getMargin()) - (this.mTop.mTarget.getFinalValue() + this.mTop.getMargin()) >= i2) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public boolean isInVirtualLayout() {
        return this.mInVirtualLayout;
    }

    public int getMaxHeight() {
        return this.mMaxDimension[1];
    }

    public int getMaxWidth() {
        return this.mMaxDimension[0];
    }

    public void setMaxWidth(int i) {
        this.mMaxDimension[0] = i;
    }

    public void setMaxHeight(int i) {
        this.mMaxDimension[1] = i;
    }

    public void setHasBaseline(boolean z) {
        this.hasBaseline = z;
    }

    public boolean isInPlaceholder() {
        return this.inPlaceholder;
    }

    public void setInPlaceholder(boolean z) {
        this.inPlaceholder = z;
    }

    /* access modifiers changed from: protected */
    public void setInBarrier(int i, boolean z) {
        this.mIsInBarrier[i] = z;
    }

    public boolean isInBarrier(int i) {
        return this.mIsInBarrier[i];
    }

    public void setMeasureRequested(boolean z) {
        this.mMeasureRequested = z;
    }

    public boolean isMeasureRequested() {
        return this.mMeasureRequested && this.mVisibility != 8;
    }

    public void setWrapBehaviorInParent(int i) {
        if (i >= 0 && i <= 3) {
            this.mWrapBehaviorInParent = i;
        }
    }

    public int getLastHorizontalMeasureSpec() {
        return this.mLastHorizontalMeasureSpec;
    }

    public int getLastVerticalMeasureSpec() {
        return this.mLastVerticalMeasureSpec;
    }

    public void setLastMeasureSpec(int i, int i2) {
        this.mLastHorizontalMeasureSpec = i;
        this.mLastVerticalMeasureSpec = i2;
        setMeasureRequested(false);
    }

    public void reset() {
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.mCenterX.reset();
        this.mCenterY.reset();
        this.mCenter.reset();
        this.mParent = null;
        this.mCircleConstraintAngle = 0.0f;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        DimensionBehaviour[] dimensionBehaviourArr = this.mListDimensionBehaviors;
        DimensionBehaviour dimensionBehaviour = DimensionBehaviour.FIXED;
        dimensionBehaviourArr[0] = dimensionBehaviour;
        dimensionBehaviourArr[1] = dimensionBehaviour;
        this.mCompanionWidget = null;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mType = null;
        this.mHorizontalWrapVisited = false;
        this.mVerticalWrapVisited = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mHorizontalChainFixedPosition = false;
        this.mVerticalChainFixedPosition = false;
        float[] fArr = this.mWeight;
        fArr[0] = -1.0f;
        fArr[1] = -1.0f;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        int[] iArr = this.mMaxDimension;
        iArr[0] = Integer.MAX_VALUE;
        iArr[1] = Integer.MAX_VALUE;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mMatchConstraintMaxWidth = Integer.MAX_VALUE;
        this.mMatchConstraintMaxHeight = Integer.MAX_VALUE;
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMinHeight = 0;
        this.mResolvedHasRatio = false;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mGroupsToSolver = false;
        boolean[] zArr = this.isTerminalWidget;
        zArr[0] = true;
        zArr[1] = true;
        this.mInVirtualLayout = false;
        boolean[] zArr2 = this.mIsInBarrier;
        zArr2[0] = false;
        zArr2[1] = false;
        this.mMeasureRequested = true;
        int[] iArr2 = this.mResolvedMatchConstraintDefault;
        iArr2[0] = 0;
        iArr2[1] = 0;
        this.mWidthOverride = -1;
        this.mHeightOverride = -1;
    }

    private void serializeAttribute(StringBuilder sb, String str, float f, float f2) {
        if (f != f2) {
            sb.append(str);
            sb.append(" :   ");
            sb.append(f);
            sb.append(",\n");
        }
    }

    private void serializeAttribute(StringBuilder sb, String str, int i, int i2) {
        if (i != i2) {
            sb.append(str);
            sb.append(" :   ");
            sb.append(i);
            sb.append(",\n");
        }
    }

    private void serializeDimensionRatio(StringBuilder sb, String str, float f, int i) {
        if (f != 0.0f) {
            sb.append(str);
            sb.append(" :  [");
            sb.append(f);
            sb.append(",");
            sb.append(i);
            sb.append("");
            sb.append("],\n");
        }
    }

    public boolean oppositeDimensionsTied() {
        DimensionBehaviour[] dimensionBehaviourArr = this.mListDimensionBehaviors;
        DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
        DimensionBehaviour dimensionBehaviour2 = DimensionBehaviour.MATCH_CONSTRAINT;
        return dimensionBehaviour == dimensionBehaviour2 && dimensionBehaviourArr[1] == dimensionBehaviour2;
    }

    public boolean hasDimensionOverride() {
        return (this.mWidthOverride == -1 && this.mHeightOverride == -1) ? false : true;
    }

    public ConstraintWidget() {
        ConstraintAnchor constraintAnchor = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mCenter = constraintAnchor;
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, constraintAnchor};
        this.mAnchors = new ArrayList();
        this.mIsInBarrier = new boolean[2];
        DimensionBehaviour dimensionBehaviour = DimensionBehaviour.FIXED;
        this.mListDimensionBehaviors = new DimensionBehaviour[]{dimensionBehaviour, dimensionBehaviour};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mRelX = 0;
        this.mRelY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mAnimated = false;
        this.mDebugName = null;
        this.mType = null;
        this.mGroupsToSolver = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mNextChainWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        this.horizontalGroup = -1;
        this.verticalGroup = -1;
        addAnchors();
    }

    public void ensureWidgetRuns() {
        if (this.horizontalRun == null) {
            this.horizontalRun = new HorizontalWidgetRun(this);
        }
        if (this.verticalRun == null) {
            this.verticalRun = new VerticalWidgetRun(this);
        }
    }

    public void resetSolverVariables(Cache cache) {
        this.mLeft.resetSolverVariable(cache);
        this.mTop.resetSolverVariable(cache);
        this.mRight.resetSolverVariable(cache);
        this.mBottom.resetSolverVariable(cache);
        this.mBaseline.resetSolverVariable(cache);
        this.mCenter.resetSolverVariable(cache);
        this.mCenterX.resetSolverVariable(cache);
        this.mCenterY.resetSolverVariable(cache);
    }

    private void addAnchors() {
        this.mAnchors.add(this.mLeft);
        this.mAnchors.add(this.mTop);
        this.mAnchors.add(this.mRight);
        this.mAnchors.add(this.mBottom);
        this.mAnchors.add(this.mCenterX);
        this.mAnchors.add(this.mCenterY);
        this.mAnchors.add(this.mCenter);
        this.mAnchors.add(this.mBaseline);
    }

    public ConstraintWidget getParent() {
        return this.mParent;
    }

    public void setParent(ConstraintWidget constraintWidget) {
        this.mParent = constraintWidget;
    }

    public void connectCircularConstraint(ConstraintWidget constraintWidget, float f, int i) {
        ConstraintAnchor.Type type = ConstraintAnchor.Type.CENTER;
        immediateConnect(type, constraintWidget, type, i, 0);
        this.mCircleConstraintAngle = f;
    }

    public void setVisibility(int i) {
        this.mVisibility = i;
    }

    public int getVisibility() {
        return this.mVisibility;
    }

    public String getDebugName() {
        return this.mDebugName;
    }

    public void setDebugName(String str) {
        this.mDebugName = str;
    }

    public void createObjectVariables(LinearSystem linearSystem) {
        linearSystem.createObjectVariable(this.mLeft);
        linearSystem.createObjectVariable(this.mTop);
        linearSystem.createObjectVariable(this.mRight);
        linearSystem.createObjectVariable(this.mBottom);
        if (this.mBaselineDistance > 0) {
            linearSystem.createObjectVariable(this.mBaseline);
        }
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        String str2 = "";
        if (this.mType != null) {
            str = "type: " + this.mType + " ";
        } else {
            str = str2;
        }
        sb.append(str);
        if (this.mDebugName != null) {
            str2 = "id: " + this.mDebugName + " ";
        }
        sb.append(str2);
        sb.append("(");
        sb.append(this.mX);
        sb.append(", ");
        sb.append(this.mY);
        sb.append(") - (");
        sb.append(this.mWidth);
        sb.append(" x ");
        sb.append(this.mHeight);
        sb.append(")");
        return sb.toString();
    }

    public int getX() {
        ConstraintWidget constraintWidget = this.mParent;
        if (constraintWidget == null || !(constraintWidget instanceof ConstraintWidgetContainer)) {
            return this.mX;
        }
        return ((ConstraintWidgetContainer) constraintWidget).mPaddingLeft + this.mX;
    }

    public int getY() {
        ConstraintWidget constraintWidget = this.mParent;
        if (constraintWidget == null || !(constraintWidget instanceof ConstraintWidgetContainer)) {
            return this.mY;
        }
        return ((ConstraintWidgetContainer) constraintWidget).mPaddingTop + this.mY;
    }

    public int getWidth() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mWidth;
    }

    public int getHeight() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mHeight;
    }

    public int getLength(int i) {
        if (i == 0) {
            return getWidth();
        }
        if (i == 1) {
            return getHeight();
        }
        return 0;
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public int getRight() {
        return getX() + this.mWidth;
    }

    public int getBottom() {
        return getY() + this.mHeight;
    }

    public int getHorizontalMargin() {
        ConstraintAnchor constraintAnchor = this.mLeft;
        int i = constraintAnchor != null ? constraintAnchor.mMargin : 0;
        ConstraintAnchor constraintAnchor2 = this.mRight;
        return constraintAnchor2 != null ? i + constraintAnchor2.mMargin : i;
    }

    public int getVerticalMargin() {
        int i = this.mLeft != null ? this.mTop.mMargin : 0;
        return this.mRight != null ? i + this.mBottom.mMargin : i;
    }

    public float getHorizontalBiasPercent() {
        return this.mHorizontalBiasPercent;
    }

    public float getVerticalBiasPercent() {
        return this.mVerticalBiasPercent;
    }

    public float getBiasPercent(int i) {
        if (i == 0) {
            return this.mHorizontalBiasPercent;
        }
        if (i == 1) {
            return this.mVerticalBiasPercent;
        }
        return -1.0f;
    }

    public boolean hasBaseline() {
        return this.hasBaseline;
    }

    public int getBaselineDistance() {
        return this.mBaselineDistance;
    }

    public Object getCompanionWidget() {
        return this.mCompanionWidget;
    }

    public void setX(int i) {
        this.mX = i;
    }

    public void setY(int i) {
        this.mY = i;
    }

    public void setOrigin(int i, int i2) {
        this.mX = i;
        this.mY = i2;
    }

    public void setWidth(int i) {
        this.mWidth = i;
        int i2 = this.mMinWidth;
        if (i < i2) {
            this.mWidth = i2;
        }
    }

    public void setHeight(int i) {
        this.mHeight = i;
        int i2 = this.mMinHeight;
        if (i < i2) {
            this.mHeight = i2;
        }
    }

    public void setHorizontalMatchStyle(int i, int i2, int i3, float f) {
        this.mMatchConstraintDefaultWidth = i;
        this.mMatchConstraintMinWidth = i2;
        if (i3 == Integer.MAX_VALUE) {
            i3 = 0;
        }
        this.mMatchConstraintMaxWidth = i3;
        this.mMatchConstraintPercentWidth = f;
        if (f > 0.0f && f < 1.0f && i == 0) {
            this.mMatchConstraintDefaultWidth = 2;
        }
    }

    public void setVerticalMatchStyle(int i, int i2, int i3, float f) {
        this.mMatchConstraintDefaultHeight = i;
        this.mMatchConstraintMinHeight = i2;
        if (i3 == Integer.MAX_VALUE) {
            i3 = 0;
        }
        this.mMatchConstraintMaxHeight = i3;
        this.mMatchConstraintPercentHeight = f;
        if (f > 0.0f && f < 1.0f && i == 0) {
            this.mMatchConstraintDefaultHeight = 2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setDimensionRatio(java.lang.String r9) {
        /*
            r8 = this;
            r0 = 0
            if (r9 == 0) goto L_0x0090
            int r1 = r9.length()
            if (r1 != 0) goto L_0x000b
            goto L_0x0090
        L_0x000b:
            int r1 = r9.length()
            r2 = 44
            int r2 = r9.indexOf(r2)
            r3 = 0
            r4 = 1
            r5 = -1
            if (r2 <= 0) goto L_0x0039
            int r6 = r1 + -1
            if (r2 >= r6) goto L_0x0039
            java.lang.String r6 = r9.substring(r3, r2)
            java.lang.String r7 = "W"
            boolean r7 = r6.equalsIgnoreCase(r7)
            if (r7 == 0) goto L_0x002b
            goto L_0x0036
        L_0x002b:
            java.lang.String r3 = "H"
            boolean r3 = r6.equalsIgnoreCase(r3)
            if (r3 == 0) goto L_0x0035
            r3 = r4
            goto L_0x0036
        L_0x0035:
            r3 = r5
        L_0x0036:
            int r2 = r2 + r4
            r5 = r3
            r3 = r2
        L_0x0039:
            r2 = 58
            int r2 = r9.indexOf(r2)
            if (r2 < 0) goto L_0x0077
            int r1 = r1 - r4
            if (r2 >= r1) goto L_0x0077
            java.lang.String r1 = r9.substring(r3, r2)
            int r2 = r2 + r4
            java.lang.String r9 = r9.substring(r2)
            int r2 = r1.length()
            if (r2 <= 0) goto L_0x0086
            int r2 = r9.length()
            if (r2 <= 0) goto L_0x0086
            float r1 = java.lang.Float.parseFloat(r1)     // Catch:{ NumberFormatException -> 0x0086 }
            float r9 = java.lang.Float.parseFloat(r9)     // Catch:{ NumberFormatException -> 0x0086 }
            int r2 = (r1 > r0 ? 1 : (r1 == r0 ? 0 : -1))
            if (r2 <= 0) goto L_0x0086
            int r2 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r2 <= 0) goto L_0x0086
            if (r5 != r4) goto L_0x0071
            float r9 = r9 / r1
            float r9 = java.lang.Math.abs(r9)     // Catch:{ NumberFormatException -> 0x0086 }
            goto L_0x0087
        L_0x0071:
            float r1 = r1 / r9
            float r9 = java.lang.Math.abs(r1)     // Catch:{ NumberFormatException -> 0x0086 }
            goto L_0x0087
        L_0x0077:
            java.lang.String r9 = r9.substring(r3)
            int r1 = r9.length()
            if (r1 <= 0) goto L_0x0086
            float r9 = java.lang.Float.parseFloat(r9)     // Catch:{ NumberFormatException -> 0x0086 }
            goto L_0x0087
        L_0x0086:
            r9 = r0
        L_0x0087:
            int r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r0 <= 0) goto L_0x008f
            r8.mDimensionRatio = r9
            r8.mDimensionRatioSide = r5
        L_0x008f:
            return
        L_0x0090:
            r8.mDimensionRatio = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.ConstraintWidget.setDimensionRatio(java.lang.String):void");
    }

    public float getDimensionRatio() {
        return this.mDimensionRatio;
    }

    public int getDimensionRatioSide() {
        return this.mDimensionRatioSide;
    }

    public void setHorizontalBiasPercent(float f) {
        this.mHorizontalBiasPercent = f;
    }

    public void setVerticalBiasPercent(float f) {
        this.mVerticalBiasPercent = f;
    }

    public void setMinWidth(int i) {
        if (i < 0) {
            this.mMinWidth = 0;
        } else {
            this.mMinWidth = i;
        }
    }

    public void setMinHeight(int i) {
        if (i < 0) {
            this.mMinHeight = 0;
        } else {
            this.mMinHeight = i;
        }
    }

    public void setFrame(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7 = i3 - i;
        int i8 = i4 - i2;
        this.mX = i;
        this.mY = i2;
        if (this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        DimensionBehaviour[] dimensionBehaviourArr = this.mListDimensionBehaviors;
        DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
        DimensionBehaviour dimensionBehaviour2 = DimensionBehaviour.FIXED;
        if (dimensionBehaviour == dimensionBehaviour2 && i7 < (i6 = this.mWidth)) {
            i7 = i6;
        }
        if (dimensionBehaviourArr[1] == dimensionBehaviour2 && i8 < (i5 = this.mHeight)) {
            i8 = i5;
        }
        this.mWidth = i7;
        this.mHeight = i8;
        int i9 = this.mMinHeight;
        if (i8 < i9) {
            this.mHeight = i9;
        }
        int i10 = this.mMinWidth;
        if (i7 < i10) {
            this.mWidth = i10;
        }
        int i11 = this.mMatchConstraintMaxWidth;
        if (i11 > 0 && dimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
            this.mWidth = Math.min(this.mWidth, i11);
        }
        int i12 = this.mMatchConstraintMaxHeight;
        if (i12 > 0 && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT) {
            this.mHeight = Math.min(this.mHeight, i12);
        }
        int i13 = this.mWidth;
        if (i7 != i13) {
            this.mWidthOverride = i13;
        }
        int i14 = this.mHeight;
        if (i8 != i14) {
            this.mHeightOverride = i14;
        }
    }

    public void setHorizontalDimension(int i, int i2) {
        this.mX = i;
        int i3 = i2 - i;
        this.mWidth = i3;
        int i4 = this.mMinWidth;
        if (i3 < i4) {
            this.mWidth = i4;
        }
    }

    public void setVerticalDimension(int i, int i2) {
        this.mY = i;
        int i3 = i2 - i;
        this.mHeight = i3;
        int i4 = this.mMinHeight;
        if (i3 < i4) {
            this.mHeight = i4;
        }
    }

    public void setBaselineDistance(int i) {
        this.mBaselineDistance = i;
        this.hasBaseline = i > 0;
    }

    public void setCompanionWidget(Object obj) {
        this.mCompanionWidget = obj;
    }

    public void setHorizontalWeight(float f) {
        this.mWeight[0] = f;
    }

    public void setVerticalWeight(float f) {
        this.mWeight[1] = f;
    }

    public void setHorizontalChainStyle(int i) {
        this.mHorizontalChainStyle = i;
    }

    public int getHorizontalChainStyle() {
        return this.mHorizontalChainStyle;
    }

    public void setVerticalChainStyle(int i) {
        this.mVerticalChainStyle = i;
    }

    public int getVerticalChainStyle() {
        return this.mVerticalChainStyle;
    }

    public boolean allowedInBarrier() {
        return this.mVisibility != 8;
    }

    public void immediateConnect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i, int i2) {
        getAnchor(type).connect(constraintWidget.getAnchor(type2), i, i2, true);
    }

    public ConstraintAnchor getAnchor(ConstraintAnchor.Type type) {
        switch (AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[type.ordinal()]) {
            case 1:
                return this.mLeft;
            case 2:
                return this.mTop;
            case 3:
                return this.mRight;
            case 4:
                return this.mBottom;
            case 5:
                return this.mBaseline;
            case 6:
                return this.mCenter;
            case 7:
                return this.mCenterX;
            case 8:
                return this.mCenterY;
            case 9:
                return null;
            default:
                throw new AssertionError(type.name());
        }
    }

    public DimensionBehaviour getHorizontalDimensionBehaviour() {
        return this.mListDimensionBehaviors[0];
    }

    public DimensionBehaviour getVerticalDimensionBehaviour() {
        return this.mListDimensionBehaviors[1];
    }

    public DimensionBehaviour getDimensionBehaviour(int i) {
        if (i == 0) {
            return getHorizontalDimensionBehaviour();
        }
        if (i == 1) {
            return getVerticalDimensionBehaviour();
        }
        return null;
    }

    public void setHorizontalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mListDimensionBehaviors[0] = dimensionBehaviour;
    }

    public void setVerticalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mListDimensionBehaviors[1] = dimensionBehaviour;
    }

    public boolean isInHorizontalChain() {
        ConstraintAnchor constraintAnchor = this.mLeft;
        ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
        if (constraintAnchor2 != null && constraintAnchor2.mTarget == constraintAnchor) {
            return true;
        }
        ConstraintAnchor constraintAnchor3 = this.mRight;
        ConstraintAnchor constraintAnchor4 = constraintAnchor3.mTarget;
        return constraintAnchor4 != null && constraintAnchor4.mTarget == constraintAnchor3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
        r3 = r2.mTop;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.constraintlayout.core.widgets.ConstraintWidget getPreviousChainMember(int r3) {
        /*
            r2 = this;
            if (r3 != 0) goto L_0x000f
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r2.mLeft
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r3.mTarget
            if (r0 == 0) goto L_0x001f
            androidx.constraintlayout.core.widgets.ConstraintAnchor r1 = r0.mTarget
            if (r1 != r3) goto L_0x001f
            androidx.constraintlayout.core.widgets.ConstraintWidget r3 = r0.mOwner
            return r3
        L_0x000f:
            r0 = 1
            if (r3 != r0) goto L_0x001f
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r2.mTop
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r3.mTarget
            if (r0 == 0) goto L_0x001f
            androidx.constraintlayout.core.widgets.ConstraintAnchor r1 = r0.mTarget
            if (r1 != r3) goto L_0x001f
            androidx.constraintlayout.core.widgets.ConstraintWidget r3 = r0.mOwner
            return r3
        L_0x001f:
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.ConstraintWidget.getPreviousChainMember(int):androidx.constraintlayout.core.widgets.ConstraintWidget");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
        r3 = r2.mBottom;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.constraintlayout.core.widgets.ConstraintWidget getNextChainMember(int r3) {
        /*
            r2 = this;
            if (r3 != 0) goto L_0x000f
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r2.mRight
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r3.mTarget
            if (r0 == 0) goto L_0x001f
            androidx.constraintlayout.core.widgets.ConstraintAnchor r1 = r0.mTarget
            if (r1 != r3) goto L_0x001f
            androidx.constraintlayout.core.widgets.ConstraintWidget r3 = r0.mOwner
            return r3
        L_0x000f:
            r0 = 1
            if (r3 != r0) goto L_0x001f
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r2.mBottom
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r3.mTarget
            if (r0 == 0) goto L_0x001f
            androidx.constraintlayout.core.widgets.ConstraintAnchor r1 = r0.mTarget
            if (r1 != r3) goto L_0x001f
            androidx.constraintlayout.core.widgets.ConstraintWidget r3 = r0.mOwner
            return r3
        L_0x001f:
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.ConstraintWidget.getNextChainMember(int):androidx.constraintlayout.core.widgets.ConstraintWidget");
    }

    public boolean isInVerticalChain() {
        ConstraintAnchor constraintAnchor = this.mTop;
        ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
        if (constraintAnchor2 != null && constraintAnchor2.mTarget == constraintAnchor) {
            return true;
        }
        ConstraintAnchor constraintAnchor3 = this.mBottom;
        ConstraintAnchor constraintAnchor4 = constraintAnchor3.mTarget;
        return constraintAnchor4 != null && constraintAnchor4.mTarget == constraintAnchor3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000e, code lost:
        r4 = r0[r4 + 1];
        r0 = r4.mTarget;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isChainHead(int r4) {
        /*
            r3 = this;
            int r4 = r4 * 2
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r0 = r3.mListAnchors
            r1 = r0[r4]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r1.mTarget
            if (r2 == 0) goto L_0x001b
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == r1) goto L_0x001b
            r1 = 1
            int r4 = r4 + r1
            r4 = r0[r4]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r4.mTarget
            if (r0 == 0) goto L_0x001b
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r0.mTarget
            if (r0 != r4) goto L_0x001b
            goto L_0x001c
        L_0x001b:
            r1 = 0
        L_0x001c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.ConstraintWidget.isChainHead(int):boolean");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r34v2, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v4, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v8, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v8, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v10, resolved type: int} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:179:0x02bc  */
    /* JADX WARNING: Removed duplicated region for block: B:183:0x02c6  */
    /* JADX WARNING: Removed duplicated region for block: B:186:0x02cb  */
    /* JADX WARNING: Removed duplicated region for block: B:195:0x02e4  */
    /* JADX WARNING: Removed duplicated region for block: B:196:0x02e6  */
    /* JADX WARNING: Removed duplicated region for block: B:198:0x02e9  */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x02ec  */
    /* JADX WARNING: Removed duplicated region for block: B:213:0x0327  */
    /* JADX WARNING: Removed duplicated region for block: B:222:0x0371  */
    /* JADX WARNING: Removed duplicated region for block: B:238:0x042c  */
    /* JADX WARNING: Removed duplicated region for block: B:255:0x0490  */
    /* JADX WARNING: Removed duplicated region for block: B:259:0x04a4  */
    /* JADX WARNING: Removed duplicated region for block: B:260:0x04a6  */
    /* JADX WARNING: Removed duplicated region for block: B:264:0x04ad  */
    /* JADX WARNING: Removed duplicated region for block: B:297:0x0544  */
    /* JADX WARNING: Removed duplicated region for block: B:298:0x0547  */
    /* JADX WARNING: Removed duplicated region for block: B:300:0x0588  */
    /* JADX WARNING: Removed duplicated region for block: B:303:0x0590  */
    /* JADX WARNING: Removed duplicated region for block: B:309:0x05be  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addToSolver(androidx.constraintlayout.core.LinearSystem r54, boolean r55) {
        /*
            r53 = this;
            r15 = r53
            r14 = r54
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r15.mLeft
            androidx.constraintlayout.core.SolverVariable r13 = r14.createObjectVariable(r0)
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r15.mRight
            androidx.constraintlayout.core.SolverVariable r12 = r14.createObjectVariable(r0)
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r15.mTop
            androidx.constraintlayout.core.SolverVariable r11 = r14.createObjectVariable(r0)
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r15.mBottom
            androidx.constraintlayout.core.SolverVariable r10 = r14.createObjectVariable(r0)
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r15.mBaseline
            androidx.constraintlayout.core.SolverVariable r9 = r14.createObjectVariable(r0)
            androidx.constraintlayout.core.widgets.ConstraintWidget r0 = r15.mParent
            r8 = 2
            r1 = 3
            r7 = 1
            r6 = 0
            if (r0 == 0) goto L_0x004f
            if (r0 == 0) goto L_0x0036
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r2 = r0.mListDimensionBehaviors
            r2 = r2[r6]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r2 != r3) goto L_0x0036
            r2 = r7
            goto L_0x0037
        L_0x0036:
            r2 = r6
        L_0x0037:
            if (r0 == 0) goto L_0x0043
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r0.mListDimensionBehaviors
            r0 = r0[r7]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r0 != r3) goto L_0x0043
            r0 = r7
            goto L_0x0044
        L_0x0043:
            r0 = r6
        L_0x0044:
            int r3 = r15.mWrapBehaviorInParent
            if (r3 == r7) goto L_0x0055
            if (r3 == r8) goto L_0x0052
            if (r3 == r1) goto L_0x004f
            r5 = r0
            r4 = r2
            goto L_0x0057
        L_0x004f:
            r4 = r6
            r5 = r4
            goto L_0x0057
        L_0x0052:
            r5 = r0
            r4 = r6
            goto L_0x0057
        L_0x0055:
            r4 = r2
            r5 = r6
        L_0x0057:
            int r0 = r15.mVisibility
            r3 = 8
            if (r0 != r3) goto L_0x0072
            boolean r0 = r15.mAnimated
            if (r0 != 0) goto L_0x0072
            boolean r0 = r53.hasDependencies()
            if (r0 != 0) goto L_0x0072
            boolean[] r0 = r15.mIsInBarrier
            boolean r2 = r0[r6]
            if (r2 != 0) goto L_0x0072
            boolean r0 = r0[r7]
            if (r0 != 0) goto L_0x0072
            return
        L_0x0072:
            boolean r0 = r15.resolvedHorizontal
            r2 = 5
            if (r0 != 0) goto L_0x007b
            boolean r8 = r15.resolvedVertical
            if (r8 == 0) goto L_0x00f8
        L_0x007b:
            if (r0 == 0) goto L_0x00aa
            int r0 = r15.mX
            r14.addEquality(r13, r0)
            int r0 = r15.mX
            int r8 = r15.mWidth
            int r0 = r0 + r8
            r14.addEquality(r12, r0)
            if (r4 == 0) goto L_0x00aa
            androidx.constraintlayout.core.widgets.ConstraintWidget r0 = r15.mParent
            if (r0 == 0) goto L_0x00aa
            boolean r8 = r15.OPTIMIZE_WRAP_ON_RESOLVED
            if (r8 == 0) goto L_0x00a1
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r0 = (androidx.constraintlayout.core.widgets.ConstraintWidgetContainer) r0
            androidx.constraintlayout.core.widgets.ConstraintAnchor r8 = r15.mLeft
            r0.addHorizontalWrapMinVariable(r8)
            androidx.constraintlayout.core.widgets.ConstraintAnchor r8 = r15.mRight
            r0.addHorizontalWrapMaxVariable(r8)
            goto L_0x00aa
        L_0x00a1:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r0.mRight
            androidx.constraintlayout.core.SolverVariable r0 = r14.createObjectVariable(r0)
            r14.addGreaterThan(r0, r12, r6, r2)
        L_0x00aa:
            boolean r0 = r15.resolvedVertical
            if (r0 == 0) goto L_0x00eb
            int r0 = r15.mY
            r14.addEquality(r11, r0)
            int r0 = r15.mY
            int r8 = r15.mHeight
            int r0 = r0 + r8
            r14.addEquality(r10, r0)
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r15.mBaseline
            boolean r0 = r0.hasDependents()
            if (r0 == 0) goto L_0x00cb
            int r0 = r15.mY
            int r8 = r15.mBaselineDistance
            int r0 = r0 + r8
            r14.addEquality(r9, r0)
        L_0x00cb:
            if (r5 == 0) goto L_0x00eb
            androidx.constraintlayout.core.widgets.ConstraintWidget r0 = r15.mParent
            if (r0 == 0) goto L_0x00eb
            boolean r8 = r15.OPTIMIZE_WRAP_ON_RESOLVED
            if (r8 == 0) goto L_0x00e2
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r0 = (androidx.constraintlayout.core.widgets.ConstraintWidgetContainer) r0
            androidx.constraintlayout.core.widgets.ConstraintAnchor r8 = r15.mTop
            r0.addVerticalWrapMinVariable(r8)
            androidx.constraintlayout.core.widgets.ConstraintAnchor r8 = r15.mBottom
            r0.addVerticalWrapMaxVariable(r8)
            goto L_0x00eb
        L_0x00e2:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r0.mBottom
            androidx.constraintlayout.core.SolverVariable r0 = r14.createObjectVariable(r0)
            r14.addGreaterThan(r0, r10, r6, r2)
        L_0x00eb:
            boolean r0 = r15.resolvedHorizontal
            if (r0 == 0) goto L_0x00f8
            boolean r0 = r15.resolvedVertical
            if (r0 == 0) goto L_0x00f8
            r15.resolvedHorizontal = r6
            r15.resolvedVertical = r6
            return
        L_0x00f8:
            boolean r0 = androidx.constraintlayout.core.LinearSystem.USE_DEPENDENCY_ORDERING
            if (r55 == 0) goto L_0x0180
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r0 = r15.horizontalRun
            if (r0 == 0) goto L_0x0180
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r8 = r15.verticalRun
            if (r8 == 0) goto L_0x0180
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r0.start
            boolean r1 = r2.resolved
            if (r1 == 0) goto L_0x0180
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r0 = r0.end
            boolean r0 = r0.resolved
            if (r0 == 0) goto L_0x0180
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r0 = r8.start
            boolean r0 = r0.resolved
            if (r0 == 0) goto L_0x0180
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r0 = r8.end
            boolean r0 = r0.resolved
            if (r0 == 0) goto L_0x0180
            int r0 = r2.value
            r14.addEquality(r13, r0)
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r0 = r15.horizontalRun
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r0 = r0.end
            int r0 = r0.value
            r14.addEquality(r12, r0)
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r0 = r15.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r0 = r0.start
            int r0 = r0.value
            r14.addEquality(r11, r0)
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r0 = r15.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r0 = r0.end
            int r0 = r0.value
            r14.addEquality(r10, r0)
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r0 = r15.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r0 = r0.baseline
            int r0 = r0.value
            r14.addEquality(r9, r0)
            androidx.constraintlayout.core.widgets.ConstraintWidget r0 = r15.mParent
            if (r0 == 0) goto L_0x017b
            if (r4 == 0) goto L_0x0162
            boolean[] r0 = r15.isTerminalWidget
            boolean r0 = r0[r6]
            if (r0 == 0) goto L_0x0162
            boolean r0 = r53.isInHorizontalChain()
            if (r0 != 0) goto L_0x0162
            androidx.constraintlayout.core.widgets.ConstraintWidget r0 = r15.mParent
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r0.mRight
            androidx.constraintlayout.core.SolverVariable r0 = r14.createObjectVariable(r0)
            r14.addGreaterThan(r0, r12, r6, r3)
        L_0x0162:
            if (r5 == 0) goto L_0x017b
            boolean[] r0 = r15.isTerminalWidget
            boolean r0 = r0[r7]
            if (r0 == 0) goto L_0x017b
            boolean r0 = r53.isInVerticalChain()
            if (r0 != 0) goto L_0x017b
            androidx.constraintlayout.core.widgets.ConstraintWidget r0 = r15.mParent
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r0.mBottom
            androidx.constraintlayout.core.SolverVariable r0 = r14.createObjectVariable(r0)
            r14.addGreaterThan(r0, r10, r6, r3)
        L_0x017b:
            r15.resolvedHorizontal = r6
            r15.resolvedVertical = r6
            return
        L_0x0180:
            androidx.constraintlayout.core.widgets.ConstraintWidget r0 = r15.mParent
            if (r0 == 0) goto L_0x01f1
            boolean r0 = r15.isChainHead(r6)
            if (r0 == 0) goto L_0x0193
            androidx.constraintlayout.core.widgets.ConstraintWidget r0 = r15.mParent
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r0 = (androidx.constraintlayout.core.widgets.ConstraintWidgetContainer) r0
            r0.addChain(r15, r6)
            r0 = r7
            goto L_0x0197
        L_0x0193:
            boolean r0 = r53.isInHorizontalChain()
        L_0x0197:
            boolean r1 = r15.isChainHead(r7)
            if (r1 == 0) goto L_0x01a6
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r15.mParent
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r1 = (androidx.constraintlayout.core.widgets.ConstraintWidgetContainer) r1
            r1.addChain(r15, r7)
            r1 = r7
            goto L_0x01aa
        L_0x01a6:
            boolean r1 = r53.isInVerticalChain()
        L_0x01aa:
            if (r0 != 0) goto L_0x01c9
            if (r4 == 0) goto L_0x01c9
            int r2 = r15.mVisibility
            if (r2 == r3) goto L_0x01c9
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r15.mLeft
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 != 0) goto L_0x01c9
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r15.mRight
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 != 0) goto L_0x01c9
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r15.mParent
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r2.mRight
            androidx.constraintlayout.core.SolverVariable r2 = r14.createObjectVariable(r2)
            r14.addGreaterThan(r2, r12, r6, r7)
        L_0x01c9:
            if (r1 != 0) goto L_0x01ec
            if (r5 == 0) goto L_0x01ec
            int r2 = r15.mVisibility
            if (r2 == r3) goto L_0x01ec
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r15.mTop
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 != 0) goto L_0x01ec
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r15.mBottom
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 != 0) goto L_0x01ec
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r15.mBaseline
            if (r2 != 0) goto L_0x01ec
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r15.mParent
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r2.mBottom
            androidx.constraintlayout.core.SolverVariable r2 = r14.createObjectVariable(r2)
            r14.addGreaterThan(r2, r10, r6, r7)
        L_0x01ec:
            r29 = r0
            r28 = r1
            goto L_0x01f5
        L_0x01f1:
            r28 = r6
            r29 = r28
        L_0x01f5:
            int r0 = r15.mWidth
            int r1 = r15.mMinWidth
            if (r0 >= r1) goto L_0x01fc
            goto L_0x01fd
        L_0x01fc:
            r1 = r0
        L_0x01fd:
            int r2 = r15.mHeight
            int r8 = r15.mMinHeight
            if (r2 >= r8) goto L_0x0204
            goto L_0x0205
        L_0x0204:
            r8 = r2
        L_0x0205:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r3 = r15.mListDimensionBehaviors
            r7 = r3[r6]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r22 = r1
            if (r7 == r6) goto L_0x0213
            r1 = 1
        L_0x0210:
            r20 = 1
            goto L_0x0215
        L_0x0213:
            r1 = 0
            goto L_0x0210
        L_0x0215:
            r3 = r3[r20]
            r23 = r8
            r27 = r9
            if (r3 == r6) goto L_0x021f
            r8 = 1
            goto L_0x0220
        L_0x021f:
            r8 = 0
        L_0x0220:
            int r9 = r15.mDimensionRatioSide
            r15.mResolvedDimensionRatioSide = r9
            r30 = r10
            float r10 = r15.mDimensionRatio
            r15.mResolvedDimensionRatio = r10
            r31 = r11
            int r11 = r15.mMatchConstraintDefaultWidth
            r32 = r12
            int r12 = r15.mMatchConstraintDefaultHeight
            r24 = 0
            int r24 = (r10 > r24 ? 1 : (r10 == r24 ? 0 : -1))
            r33 = r13
            if (r24 <= 0) goto L_0x02a7
            int r13 = r15.mVisibility
            r14 = 8
            if (r13 == r14) goto L_0x02a7
            if (r7 != r6) goto L_0x0245
            if (r11 != 0) goto L_0x0245
            r11 = 3
        L_0x0245:
            if (r3 != r6) goto L_0x024a
            if (r12 != 0) goto L_0x024a
            r12 = 3
        L_0x024a:
            if (r7 != r6) goto L_0x0257
            if (r3 != r6) goto L_0x0257
            r13 = 3
            if (r11 != r13) goto L_0x0258
            if (r12 != r13) goto L_0x0258
            r15.setupDimensionRatio(r4, r5, r1, r8)
            goto L_0x02a0
        L_0x0257:
            r13 = 3
        L_0x0258:
            r1 = 4
            if (r7 != r6) goto L_0x0277
            if (r11 != r13) goto L_0x0277
            r8 = 0
            r15.mResolvedDimensionRatioSide = r8
            float r0 = (float) r2
            float r10 = r10 * r0
            int r0 = (int) r10
            if (r3 == r6) goto L_0x026e
            r36 = r1
            r35 = r12
            r34 = r23
            r14 = 0
            r1 = r0
            goto L_0x02b0
        L_0x026e:
            r1 = r0
            r36 = r11
            r35 = r12
        L_0x0273:
            r34 = r23
        L_0x0275:
            r14 = 1
            goto L_0x02b0
        L_0x0277:
            if (r3 != r6) goto L_0x02a0
            if (r12 != r13) goto L_0x02a0
            r2 = 1
            r15.mResolvedDimensionRatioSide = r2
            r2 = -1
            if (r9 != r2) goto L_0x0286
            r2 = 1065353216(0x3f800000, float:1.0)
            float r2 = r2 / r10
            r15.mResolvedDimensionRatio = r2
        L_0x0286:
            float r2 = r15.mResolvedDimensionRatio
            float r0 = (float) r0
            float r2 = r2 * r0
            int r8 = (int) r2
            if (r7 == r6) goto L_0x0297
            r35 = r1
            r34 = r8
            r36 = r11
            r1 = r22
        L_0x0295:
            r14 = 0
            goto L_0x02b0
        L_0x0297:
            r34 = r8
            r36 = r11
            r35 = r12
            r1 = r22
            goto L_0x0275
        L_0x02a0:
            r36 = r11
            r35 = r12
            r1 = r22
            goto L_0x0273
        L_0x02a7:
            r36 = r11
            r35 = r12
            r1 = r22
            r34 = r23
            goto L_0x0295
        L_0x02b0:
            int[] r0 = r15.mResolvedMatchConstraintDefault
            r2 = 0
            r0[r2] = r36
            r2 = 1
            r0[r2] = r35
            r15.mResolvedHasRatio = r14
            if (r14 == 0) goto L_0x02c6
            int r0 = r15.mResolvedDimensionRatioSide
            r2 = -1
            if (r0 == 0) goto L_0x02c3
            if (r0 != r2) goto L_0x02c7
        L_0x02c3:
            r18 = 1
            goto L_0x02c9
        L_0x02c6:
            r2 = -1
        L_0x02c7:
            r18 = 0
        L_0x02c9:
            if (r14 == 0) goto L_0x02d5
            int r0 = r15.mResolvedDimensionRatioSide
            r3 = 1
            if (r0 == r3) goto L_0x02d2
            if (r0 != r2) goto L_0x02d5
        L_0x02d2:
            r37 = 1
            goto L_0x02d7
        L_0x02d5:
            r37 = 0
        L_0x02d7:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r15.mListDimensionBehaviors
            r2 = 0
            r0 = r0[r2]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r13 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r0 != r13) goto L_0x02e6
            boolean r0 = r15 instanceof androidx.constraintlayout.core.widgets.ConstraintWidgetContainer
            if (r0 == 0) goto L_0x02e6
            r9 = 1
            goto L_0x02e7
        L_0x02e6:
            r9 = 0
        L_0x02e7:
            if (r9 == 0) goto L_0x02ec
            r22 = 0
            goto L_0x02ee
        L_0x02ec:
            r22 = r1
        L_0x02ee:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r15.mCenter
            boolean r0 = r0.isConnected()
            r1 = 1
            r38 = r0 ^ 1
            boolean[] r0 = r15.mIsInBarrier
            r2 = 0
            boolean r23 = r0[r2]
            boolean r39 = r0[r1]
            int r0 = r15.mHorizontalResolution
            r40 = 0
            r8 = 2
            if (r0 == r8) goto L_0x0373
            boolean r0 = r15.resolvedHorizontal
            if (r0 != 0) goto L_0x0373
            if (r55 == 0) goto L_0x031b
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r0 = r15.horizontalRun
            if (r0 == 0) goto L_0x031b
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r0.start
            boolean r2 = r1.resolved
            if (r2 == 0) goto L_0x031b
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r0 = r0.end
            boolean r0 = r0.resolved
            if (r0 != 0) goto L_0x0325
        L_0x031b:
            r12 = r54
            r10 = r32
            r11 = r33
            r3 = 8
            goto L_0x0389
        L_0x0325:
            if (r55 == 0) goto L_0x0371
            int r0 = r1.value
            r12 = r54
            r11 = r33
            r12.addEquality(r11, r0)
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r0 = r15.horizontalRun
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r0 = r0.end
            int r0 = r0.value
            r10 = r32
            r12.addEquality(r10, r0)
            androidx.constraintlayout.core.widgets.ConstraintWidget r0 = r15.mParent
            if (r0 == 0) goto L_0x035b
            if (r4 == 0) goto L_0x035b
            boolean[] r0 = r15.isTerminalWidget
            r1 = 0
            boolean r0 = r0[r1]
            if (r0 == 0) goto L_0x035b
            boolean r0 = r53.isInHorizontalChain()
            if (r0 != 0) goto L_0x035b
            androidx.constraintlayout.core.widgets.ConstraintWidget r0 = r15.mParent
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r0.mRight
            androidx.constraintlayout.core.SolverVariable r0 = r12.createObjectVariable(r0)
            r3 = 8
            r12.addGreaterThan(r0, r10, r1, r3)
        L_0x035b:
            r46 = r4
            r47 = r5
            r48 = r6
            r52 = r13
            r32 = r14
            r49 = r27
            r50 = r30
            r51 = r31
            r30 = r10
            r31 = r11
            goto L_0x042a
        L_0x0371:
            r12 = r54
        L_0x0373:
            r46 = r4
            r47 = r5
            r48 = r6
            r52 = r13
            r49 = r27
            r50 = r30
            r51 = r31
            r30 = r32
            r31 = r33
            r32 = r14
            goto L_0x042a
        L_0x0389:
            androidx.constraintlayout.core.widgets.ConstraintWidget r0 = r15.mParent
            if (r0 == 0) goto L_0x0395
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r0.mRight
            androidx.constraintlayout.core.SolverVariable r0 = r12.createObjectVariable(r0)
            r7 = r0
            goto L_0x0397
        L_0x0395:
            r7 = r40
        L_0x0397:
            androidx.constraintlayout.core.widgets.ConstraintWidget r0 = r15.mParent
            if (r0 == 0) goto L_0x03a4
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r0.mLeft
            androidx.constraintlayout.core.SolverVariable r0 = r12.createObjectVariable(r0)
            r16 = r0
            goto L_0x03a6
        L_0x03a4:
            r16 = r40
        L_0x03a6:
            boolean[] r0 = r15.isTerminalWidget
            r19 = 0
            boolean r21 = r0[r19]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r15.mListDimensionBehaviors
            r32 = r0[r19]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r1 = r15.mLeft
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r15.mRight
            r33 = r2
            int r2 = r15.mX
            r41 = r2
            int r2 = r15.mMinWidth
            int[] r3 = r15.mMaxDimension
            r43 = r3[r19]
            float r3 = r15.mHorizontalBiasPercent
            r20 = 1
            r0 = r0[r20]
            if (r0 != r6) goto L_0x03cb
            r44 = r20
            goto L_0x03cd
        L_0x03cb:
            r44 = r19
        L_0x03cd:
            int r0 = r15.mMatchConstraintMinWidth
            r24 = r0
            int r0 = r15.mMatchConstraintMaxWidth
            r25 = r0
            float r0 = r15.mMatchConstraintPercentWidth
            r26 = r0
            r0 = 1
            r17 = r33
            r33 = r41
            r41 = r2
            r2 = r0
            r0 = r53
            r45 = r1
            r1 = r54
            r42 = r3
            r3 = r4
            r46 = r4
            r4 = r5
            r47 = r5
            r5 = r21
            r48 = r6
            r6 = r16
            r8 = r32
            r49 = r27
            r16 = r10
            r50 = r30
            r10 = r45
            r19 = r11
            r51 = r31
            r11 = r17
            r30 = r16
            r12 = r33
            r52 = r13
            r31 = r19
            r13 = r22
            r32 = r14
            r14 = r41
            r15 = r43
            r16 = r42
            r17 = r18
            r18 = r44
            r19 = r29
            r20 = r28
            r21 = r23
            r22 = r36
            r23 = r35
            r27 = r38
            r0.applyConstraints(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27)
        L_0x042a:
            if (r55 == 0) goto L_0x0490
            r15 = r53
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r0 = r15.verticalRun
            if (r0 == 0) goto L_0x0483
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r0.start
            boolean r2 = r1.resolved
            if (r2 == 0) goto L_0x0483
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r0 = r0.end
            boolean r0 = r0.resolved
            if (r0 == 0) goto L_0x0483
            int r0 = r1.value
            r14 = r54
            r13 = r51
            r14.addEquality(r13, r0)
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r0 = r15.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r0 = r0.end
            int r0 = r0.value
            r12 = r50
            r14.addEquality(r12, r0)
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r0 = r15.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r0 = r0.baseline
            int r0 = r0.value
            r1 = r49
            r14.addEquality(r1, r0)
            androidx.constraintlayout.core.widgets.ConstraintWidget r0 = r15.mParent
            if (r0 == 0) goto L_0x047d
            if (r28 != 0) goto L_0x047d
            if (r47 == 0) goto L_0x047d
            boolean[] r2 = r15.isTerminalWidget
            r11 = 1
            boolean r2 = r2[r11]
            if (r2 == 0) goto L_0x0479
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r0.mBottom
            androidx.constraintlayout.core.SolverVariable r0 = r14.createObjectVariable(r0)
            r2 = 8
            r10 = 0
            r14.addGreaterThan(r0, r12, r10, r2)
            goto L_0x0481
        L_0x0479:
            r2 = 8
            r10 = 0
            goto L_0x0481
        L_0x047d:
            r2 = 8
            r10 = 0
            r11 = 1
        L_0x0481:
            r7 = r10
            goto L_0x049f
        L_0x0483:
            r14 = r54
            r1 = r49
            r12 = r50
            r13 = r51
            r2 = 8
            r10 = 0
            r11 = 1
            goto L_0x049e
        L_0x0490:
            r2 = 8
            r10 = 0
            r11 = 1
            r15 = r53
            r14 = r54
            r1 = r49
            r12 = r50
            r13 = r51
        L_0x049e:
            r7 = r11
        L_0x049f:
            int r0 = r15.mVerticalResolution
            r3 = 2
            if (r0 != r3) goto L_0x04a6
            r6 = r10
            goto L_0x04a7
        L_0x04a6:
            r6 = r7
        L_0x04a7:
            if (r6 == 0) goto L_0x0588
            boolean r0 = r15.resolvedVertical
            if (r0 != 0) goto L_0x0588
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r15.mListDimensionBehaviors
            r0 = r0[r11]
            r3 = r52
            if (r0 != r3) goto L_0x04bb
            boolean r0 = r15 instanceof androidx.constraintlayout.core.widgets.ConstraintWidgetContainer
            if (r0 == 0) goto L_0x04bb
            r9 = r11
            goto L_0x04bc
        L_0x04bb:
            r9 = r10
        L_0x04bc:
            if (r9 == 0) goto L_0x04c0
            r34 = r10
        L_0x04c0:
            androidx.constraintlayout.core.widgets.ConstraintWidget r0 = r15.mParent
            if (r0 == 0) goto L_0x04cc
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r0.mBottom
            androidx.constraintlayout.core.SolverVariable r0 = r14.createObjectVariable(r0)
            r7 = r0
            goto L_0x04ce
        L_0x04cc:
            r7 = r40
        L_0x04ce:
            androidx.constraintlayout.core.widgets.ConstraintWidget r0 = r15.mParent
            if (r0 == 0) goto L_0x04da
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r0.mTop
            androidx.constraintlayout.core.SolverVariable r0 = r14.createObjectVariable(r0)
            r6 = r0
            goto L_0x04dc
        L_0x04da:
            r6 = r40
        L_0x04dc:
            int r0 = r15.mBaselineDistance
            if (r0 > 0) goto L_0x04e4
            int r0 = r15.mVisibility
            if (r0 != r2) goto L_0x0524
        L_0x04e4:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r15.mBaseline
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r0.mTarget
            if (r3 == 0) goto L_0x0511
            int r0 = r53.getBaselineDistance()
            r14.addEquality(r1, r13, r0, r2)
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r15.mBaseline
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r0.mTarget
            androidx.constraintlayout.core.SolverVariable r0 = r14.createObjectVariable(r0)
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r15.mBaseline
            int r3 = r3.getMargin()
            r14.addEquality(r1, r0, r3, r2)
            if (r47 == 0) goto L_0x050e
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r15.mBottom
            androidx.constraintlayout.core.SolverVariable r0 = r14.createObjectVariable(r0)
            r1 = 5
            r14.addGreaterThan(r7, r0, r10, r1)
        L_0x050e:
            r27 = r10
            goto L_0x0526
        L_0x0511:
            int r3 = r15.mVisibility
            if (r3 != r2) goto L_0x051d
            int r0 = r0.getMargin()
            r14.addEquality(r1, r13, r0, r2)
            goto L_0x0524
        L_0x051d:
            int r0 = r53.getBaselineDistance()
            r14.addEquality(r1, r13, r0, r2)
        L_0x0524:
            r27 = r38
        L_0x0526:
            boolean[] r0 = r15.isTerminalWidget
            boolean r5 = r0[r11]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r15.mListDimensionBehaviors
            r8 = r0[r11]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r15.mTop
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r15.mBottom
            int r1 = r15.mY
            int r2 = r15.mMinHeight
            int[] r10 = r15.mMaxDimension
            r16 = r10[r11]
            float r10 = r15.mVerticalBiasPercent
            r17 = 0
            r0 = r0[r17]
            r11 = r48
            if (r0 != r11) goto L_0x0547
            r18 = 1
            goto L_0x0549
        L_0x0547:
            r18 = r17
        L_0x0549:
            int r0 = r15.mMatchConstraintMinHeight
            r24 = r0
            int r0 = r15.mMatchConstraintMaxHeight
            r25 = r0
            float r0 = r15.mMatchConstraintPercentHeight
            r26 = r0
            r0 = 0
            r19 = r2
            r2 = r0
            r0 = r53
            r20 = r1
            r1 = r54
            r11 = r3
            r3 = r47
            r21 = r4
            r4 = r46
            r17 = r10
            r10 = r21
            r33 = r12
            r12 = r20
            r38 = r13
            r13 = r34
            r14 = r19
            r15 = r16
            r16 = r17
            r17 = r37
            r19 = r28
            r20 = r29
            r21 = r39
            r22 = r35
            r23 = r36
            r0.applyConstraints(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27)
            goto L_0x058c
        L_0x0588:
            r33 = r12
            r38 = r13
        L_0x058c:
            r7 = r53
            if (r32 == 0) goto L_0x05b6
            int r0 = r7.mResolvedDimensionRatioSide
            r6 = 8
            r1 = 1
            if (r0 != r1) goto L_0x05a7
            float r5 = r7.mResolvedDimensionRatio
            r0 = r54
            r1 = r33
            r2 = r38
            r3 = r30
            r4 = r31
            r0.addRatio(r1, r2, r3, r4, r5, r6)
            goto L_0x05b6
        L_0x05a7:
            float r5 = r7.mResolvedDimensionRatio
            r0 = r54
            r1 = r30
            r2 = r31
            r3 = r33
            r4 = r38
            r0.addRatio(r1, r2, r3, r4, r5, r6)
        L_0x05b6:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r7.mCenter
            boolean r0 = r0.isConnected()
            if (r0 == 0) goto L_0x05de
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r7.mCenter
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r0.getTarget()
            androidx.constraintlayout.core.widgets.ConstraintWidget r0 = r0.getOwner()
            float r1 = r7.mCircleConstraintAngle
            r2 = 1119092736(0x42b40000, float:90.0)
            float r1 = r1 + r2
            double r1 = (double) r1
            double r1 = java.lang.Math.toRadians(r1)
            float r1 = (float) r1
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r7.mCenter
            int r2 = r2.getMargin()
            r3 = r54
            r3.addCenterPoint(r7, r0, r1, r2)
        L_0x05de:
            r0 = 0
            r7.resolvedHorizontal = r0
            r7.resolvedVertical = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.ConstraintWidget.addToSolver(androidx.constraintlayout.core.LinearSystem, boolean):void");
    }

    /* access modifiers changed from: package-private */
    public boolean addFirst() {
        return this instanceof Guideline;
    }

    public void setupDimensionRatio(boolean z, boolean z2, boolean z3, boolean z4) {
        if (this.mResolvedDimensionRatioSide == -1) {
            if (z3 && !z4) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (!z3 && z4) {
                this.mResolvedDimensionRatioSide = 1;
                if (this.mDimensionRatioSide == -1) {
                    this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                }
            }
        }
        if (this.mResolvedDimensionRatioSide == 0 && (!this.mTop.isConnected() || !this.mBottom.isConnected())) {
            this.mResolvedDimensionRatioSide = 1;
        } else if (this.mResolvedDimensionRatioSide == 1 && (!this.mLeft.isConnected() || !this.mRight.isConnected())) {
            this.mResolvedDimensionRatioSide = 0;
        }
        if (this.mResolvedDimensionRatioSide == -1 && (!this.mTop.isConnected() || !this.mBottom.isConnected() || !this.mLeft.isConnected() || !this.mRight.isConnected())) {
            if (this.mTop.isConnected() && this.mBottom.isConnected()) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (this.mLeft.isConnected() && this.mRight.isConnected()) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide == -1) {
            int i = this.mMatchConstraintMinWidth;
            if (i > 0 && this.mMatchConstraintMinHeight == 0) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (i == 0 && this.mMatchConstraintMinHeight > 0) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:255:0x0497, code lost:
        if ((r4 instanceof androidx.constraintlayout.core.widgets.Barrier) != false) goto L_0x049c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x01db A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:232:0x041c  */
    /* JADX WARNING: Removed duplicated region for block: B:241:0x0465  */
    /* JADX WARNING: Removed duplicated region for block: B:246:0x0482 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:247:0x0483  */
    /* JADX WARNING: Removed duplicated region for block: B:274:0x04dd  */
    /* JADX WARNING: Removed duplicated region for block: B:304:0x0528  */
    /* JADX WARNING: Removed duplicated region for block: B:318:0x0546  */
    /* JADX WARNING: Removed duplicated region for block: B:319:0x054b  */
    /* JADX WARNING: Removed duplicated region for block: B:323:0x0557 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:341:0x0585 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:369:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:373:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00bf  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00e7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void applyConstraints(androidx.constraintlayout.core.LinearSystem r38, boolean r39, boolean r40, boolean r41, boolean r42, androidx.constraintlayout.core.SolverVariable r43, androidx.constraintlayout.core.SolverVariable r44, androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour r45, boolean r46, androidx.constraintlayout.core.widgets.ConstraintAnchor r47, androidx.constraintlayout.core.widgets.ConstraintAnchor r48, int r49, int r50, int r51, int r52, float r53, boolean r54, boolean r55, boolean r56, boolean r57, boolean r58, int r59, int r60, int r61, int r62, float r63, boolean r64) {
        /*
            r37 = this;
            r0 = r37
            r10 = r38
            r11 = r43
            r12 = r44
            r13 = r47
            r14 = r48
            r15 = r51
            r1 = r52
            r2 = r60
            r3 = r61
            r4 = r62
            androidx.constraintlayout.core.SolverVariable r9 = r10.createObjectVariable(r13)
            androidx.constraintlayout.core.SolverVariable r8 = r10.createObjectVariable(r14)
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = r47.getTarget()
            androidx.constraintlayout.core.SolverVariable r7 = r10.createObjectVariable(r5)
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = r48.getTarget()
            androidx.constraintlayout.core.SolverVariable r6 = r10.createObjectVariable(r5)
            androidx.constraintlayout.core.LinearSystem.getMetrics()
            boolean r16 = r47.isConnected()
            boolean r17 = r48.isConnected()
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = r0.mCenter
            boolean r18 = r5.isConnected()
            if (r17 == 0) goto L_0x0044
            int r5 = r16 + 1
            goto L_0x0046
        L_0x0044:
            r5 = r16
        L_0x0046:
            if (r18 == 0) goto L_0x004a
            int r5 = r5 + 1
        L_0x004a:
            if (r54 == 0) goto L_0x004f
            r19 = 3
            goto L_0x0051
        L_0x004f:
            r19 = r59
        L_0x0051:
            int[] r20 = androidx.constraintlayout.core.widgets.ConstraintWidget.AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$widgets$ConstraintWidget$DimensionBehaviour
            int r21 = r45.ordinal()
            r12 = r20[r21]
            r2 = 1
            if (r12 == r2) goto L_0x0065
            r2 = 2
            if (r12 == r2) goto L_0x0065
            r2 = 3
            if (r12 == r2) goto L_0x0065
            r2 = 4
            if (r12 == r2) goto L_0x006a
        L_0x0065:
            r12 = r19
        L_0x0067:
            r19 = 0
            goto L_0x0070
        L_0x006a:
            r12 = r19
            if (r12 == r2) goto L_0x0067
            r19 = 1
        L_0x0070:
            int r2 = r0.mWidthOverride
            r14 = -1
            if (r2 == r14) goto L_0x007d
            if (r39 == 0) goto L_0x007d
            r0.mWidthOverride = r14
            r50 = r2
            r19 = 0
        L_0x007d:
            int r2 = r0.mHeightOverride
            if (r2 == r14) goto L_0x0088
            if (r39 != 0) goto L_0x0088
            r0.mHeightOverride = r14
            r19 = 0
            goto L_0x008a
        L_0x0088:
            r2 = r50
        L_0x008a:
            int r14 = r0.mVisibility
            r50 = r2
            r2 = 8
            if (r14 != r2) goto L_0x0096
            r14 = 0
            r19 = 0
            goto L_0x0098
        L_0x0096:
            r14 = r50
        L_0x0098:
            if (r64 == 0) goto L_0x00ba
            if (r16 != 0) goto L_0x00aa
            if (r17 != 0) goto L_0x00aa
            if (r18 != 0) goto L_0x00aa
            r2 = r49
            r10.addEquality(r9, r2)
        L_0x00a5:
            r24 = r6
            r6 = 8
            goto L_0x00bd
        L_0x00aa:
            if (r16 == 0) goto L_0x00a5
            if (r17 != 0) goto L_0x00a5
            int r2 = r47.getMargin()
            r24 = r6
            r6 = 8
            r10.addEquality(r9, r7, r2, r6)
            goto L_0x00bd
        L_0x00ba:
            r24 = r6
            r6 = r2
        L_0x00bd:
            if (r19 != 0) goto L_0x00e7
            if (r46 == 0) goto L_0x00d6
            r2 = 3
            r6 = 0
            r10.addEquality(r8, r9, r6, r2)
            r2 = 8
            if (r15 <= 0) goto L_0x00cd
            r10.addGreaterThan(r8, r9, r15, r2)
        L_0x00cd:
            r6 = 2147483647(0x7fffffff, float:NaN)
            if (r1 >= r6) goto L_0x00da
            r10.addLowerThan(r8, r9, r1, r2)
            goto L_0x00da
        L_0x00d6:
            r2 = r6
            r10.addEquality(r8, r9, r14, r2)
        L_0x00da:
            r1 = r5
            r2 = r7
            r14 = r8
            r25 = r19
            r15 = r24
            r19 = r42
        L_0x00e3:
            r24 = r3
            goto L_0x01d9
        L_0x00e7:
            r1 = 2
            if (r5 == r1) goto L_0x010a
            if (r54 != 0) goto L_0x010a
            r1 = 1
            if (r12 == r1) goto L_0x00f1
            if (r12 != 0) goto L_0x010a
        L_0x00f1:
            int r1 = java.lang.Math.max(r3, r14)
            if (r4 <= 0) goto L_0x00fb
            int r1 = java.lang.Math.min(r4, r1)
        L_0x00fb:
            r2 = 8
            r10.addEquality(r8, r9, r1, r2)
            r19 = r42
            r1 = r5
            r2 = r7
            r14 = r8
            r15 = r24
            r25 = 0
            goto L_0x00e3
        L_0x010a:
            r1 = -2
            if (r3 != r1) goto L_0x010f
            r2 = r14
            goto L_0x0110
        L_0x010f:
            r2 = r3
        L_0x0110:
            if (r4 != r1) goto L_0x0114
            r1 = r14
            goto L_0x0115
        L_0x0114:
            r1 = r4
        L_0x0115:
            if (r14 <= 0) goto L_0x011b
            r3 = 1
            if (r12 == r3) goto L_0x011b
            r14 = 0
        L_0x011b:
            r3 = 8
            if (r2 <= 0) goto L_0x0126
            r10.addGreaterThan(r8, r9, r2, r3)
            int r14 = java.lang.Math.max(r14, r2)
        L_0x0126:
            r4 = 1
            if (r1 <= 0) goto L_0x0135
            if (r40 == 0) goto L_0x012e
            if (r12 != r4) goto L_0x012e
            goto L_0x0131
        L_0x012e:
            r10.addLowerThan(r8, r9, r1, r3)
        L_0x0131:
            int r14 = java.lang.Math.min(r14, r1)
        L_0x0135:
            if (r12 != r4) goto L_0x015c
            if (r40 == 0) goto L_0x013d
            r10.addEquality(r8, r9, r14, r3)
            goto L_0x014e
        L_0x013d:
            if (r56 == 0) goto L_0x0147
            r4 = 5
            r10.addEquality(r8, r9, r14, r4)
            r10.addLowerThan(r8, r9, r14, r3)
            goto L_0x014e
        L_0x0147:
            r4 = 5
            r10.addEquality(r8, r9, r14, r4)
            r10.addLowerThan(r8, r9, r14, r3)
        L_0x014e:
            r4 = r1
            r1 = r5
            r14 = r8
            r25 = r19
            r15 = r24
            r19 = r42
            r24 = r2
            r2 = r7
            goto L_0x01d9
        L_0x015c:
            r3 = 2
            if (r12 != r3) goto L_0x01c8
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r3 = r47.getType()
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.TOP
            if (r3 == r4) goto L_0x018b
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r3 = r47.getType()
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r6 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.BOTTOM
            if (r3 != r6) goto L_0x0170
            goto L_0x018b
        L_0x0170:
            androidx.constraintlayout.core.widgets.ConstraintWidget r3 = r0.mParent
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.LEFT
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r3.getAnchor(r4)
            androidx.constraintlayout.core.SolverVariable r3 = r10.createObjectVariable(r3)
            androidx.constraintlayout.core.widgets.ConstraintWidget r4 = r0.mParent
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r6 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.RIGHT
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r4.getAnchor(r6)
            androidx.constraintlayout.core.SolverVariable r4 = r10.createObjectVariable(r4)
        L_0x0188:
            r14 = r3
            r6 = r4
            goto L_0x01a2
        L_0x018b:
            androidx.constraintlayout.core.widgets.ConstraintWidget r3 = r0.mParent
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r3.getAnchor(r4)
            androidx.constraintlayout.core.SolverVariable r3 = r10.createObjectVariable(r3)
            androidx.constraintlayout.core.widgets.ConstraintWidget r4 = r0.mParent
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r6 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.BOTTOM
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r4.getAnchor(r6)
            androidx.constraintlayout.core.SolverVariable r4 = r10.createObjectVariable(r4)
            goto L_0x0188
        L_0x01a2:
            androidx.constraintlayout.core.ArrayRow r3 = r38.createRow()
            r4 = r8
            r46 = r1
            r1 = r5
            r5 = r9
            r15 = r24
            r52 = r2
            r2 = r7
            r7 = r14
            r14 = r8
            r8 = r63
            androidx.constraintlayout.core.ArrayRow r3 = r3.createRowDimensionRatio(r4, r5, r6, r7, r8)
            r10.addConstraint(r3)
            if (r40 == 0) goto L_0x01bf
            r19 = 0
        L_0x01bf:
            r4 = r46
            r24 = r52
            r25 = r19
            r19 = r42
            goto L_0x01d9
        L_0x01c8:
            r46 = r1
            r52 = r2
            r1 = r5
            r2 = r7
            r14 = r8
            r15 = r24
            r4 = r46
            r24 = r52
            r25 = r19
            r19 = 1
        L_0x01d9:
            if (r64 == 0) goto L_0x01dd
            if (r56 == 0) goto L_0x01e8
        L_0x01dd:
            r5 = r44
            r8 = r9
            r3 = 0
            r7 = 2
            r13 = 8
            r18 = 1
            goto L_0x0583
        L_0x01e8:
            if (r16 != 0) goto L_0x01f3
            if (r17 != 0) goto L_0x01f3
            if (r18 != 0) goto L_0x01f3
        L_0x01ee:
            r2 = r15
            r1 = 5
            r3 = 0
            goto L_0x0553
        L_0x01f3:
            if (r16 == 0) goto L_0x020c
            if (r17 != 0) goto L_0x020c
            androidx.constraintlayout.core.widgets.ConstraintAnchor r1 = r13.mTarget
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r1.mOwner
            if (r40 == 0) goto L_0x0204
            boolean r1 = r1 instanceof androidx.constraintlayout.core.widgets.Barrier
            if (r1 == 0) goto L_0x0204
            r2 = 8
            goto L_0x0205
        L_0x0204:
            r2 = 5
        L_0x0205:
            r20 = r40
            r1 = r2
        L_0x0208:
            r2 = r15
        L_0x0209:
            r3 = 0
            goto L_0x0555
        L_0x020c:
            if (r16 != 0) goto L_0x023e
            if (r17 == 0) goto L_0x023e
            int r1 = r48.getMargin()
            int r1 = -r1
            r2 = 8
            r10.addEquality(r14, r15, r1, r2)
            if (r40 == 0) goto L_0x01ee
            boolean r1 = r0.OPTIMIZE_WRAP
            if (r1 == 0) goto L_0x0234
            boolean r1 = r9.isFinalValue
            if (r1 == 0) goto L_0x0234
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r0.mParent
            if (r1 == 0) goto L_0x0234
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r1 = (androidx.constraintlayout.core.widgets.ConstraintWidgetContainer) r1
            if (r39 == 0) goto L_0x0230
            r1.addHorizontalWrapMinVariable(r13)
            goto L_0x01ee
        L_0x0230:
            r1.addVerticalWrapMinVariable(r13)
            goto L_0x01ee
        L_0x0234:
            r1 = 0
            r2 = 5
            r10.addGreaterThan(r9, r11, r1, r2)
            r3 = r1
            r1 = r2
            r2 = r15
            goto L_0x0553
        L_0x023e:
            r1 = 0
            if (r16 == 0) goto L_0x0550
            if (r17 == 0) goto L_0x0550
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r13.mTarget
            androidx.constraintlayout.core.widgets.ConstraintWidget r8 = r3.mOwner
            r6 = r48
            r7 = r1
            androidx.constraintlayout.core.widgets.ConstraintAnchor r1 = r6.mTarget
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = r1.mOwner
            androidx.constraintlayout.core.widgets.ConstraintWidget r3 = r37.getParent()
            r16 = 6
            if (r25 == 0) goto L_0x03ba
            if (r12 != 0) goto L_0x02be
            if (r4 != 0) goto L_0x0282
            if (r24 != 0) goto L_0x0282
            boolean r1 = r2.isFinalValue
            if (r1 == 0) goto L_0x0276
            boolean r1 = r15.isFinalValue
            if (r1 == 0) goto L_0x0276
            int r1 = r47.getMargin()
            r4 = 8
            r10.addEquality(r9, r2, r1, r4)
            int r1 = r48.getMargin()
            int r1 = -r1
            r10.addEquality(r14, r15, r1, r4)
            return
        L_0x0276:
            r4 = 8
            r1 = r4
            r17 = r1
            r18 = r7
            r23 = r18
            r21 = 1
            goto L_0x028d
        L_0x0282:
            r4 = 8
            r21 = r7
            r1 = 5
            r17 = 5
            r18 = 1
            r23 = 1
        L_0x028d:
            boolean r4 = r8 instanceof androidx.constraintlayout.core.widgets.Barrier
            if (r4 != 0) goto L_0x02ac
            boolean r4 = r5 instanceof androidx.constraintlayout.core.widgets.Barrier
            if (r4 == 0) goto L_0x0296
            goto L_0x02ac
        L_0x0296:
            r4 = r44
            r20 = r16
            r22 = r17
            r27 = r18
            r28 = r21
            r26 = r23
            r7 = 1
            r17 = 8
            r18 = 5
        L_0x02a7:
            r23 = r1
            r1 = 3
            goto L_0x040d
        L_0x02ac:
            r4 = r44
            r20 = r16
            r27 = r18
            r28 = r21
            r26 = r23
            r7 = 1
            r17 = 8
            r18 = 5
            r22 = 4
            goto L_0x02a7
        L_0x02be:
            r1 = 2
            r17 = 8
            if (r12 != r1) goto L_0x02ed
            boolean r1 = r8 instanceof androidx.constraintlayout.core.widgets.Barrier
            if (r1 != 0) goto L_0x02e0
            boolean r1 = r5 instanceof androidx.constraintlayout.core.widgets.Barrier
            if (r1 == 0) goto L_0x02cc
            goto L_0x02e0
        L_0x02cc:
            r4 = r44
            r28 = r7
            r20 = r16
            r1 = 3
            r7 = 1
            r18 = 5
            r22 = 5
        L_0x02d8:
            r23 = 5
        L_0x02da:
            r26 = 1
            r27 = 1
            goto L_0x040d
        L_0x02e0:
            r4 = r44
            r28 = r7
            r20 = r16
            r1 = 3
            r7 = 1
            r18 = 5
            r22 = 4
            goto L_0x02d8
        L_0x02ed:
            r1 = 1
            if (r12 != r1) goto L_0x02ff
            r4 = r44
            r28 = r7
            r20 = r16
            r23 = r17
            r1 = 3
            r7 = 1
            r18 = 5
            r22 = 4
            goto L_0x02da
        L_0x02ff:
            r1 = 3
            if (r12 != r1) goto L_0x03a8
            int r1 = r0.mResolvedDimensionRatioSide
            r7 = -1
            if (r1 != r7) goto L_0x032d
            if (r57 == 0) goto L_0x0322
            r4 = r44
            r23 = r17
            r1 = 3
            r7 = 1
            r18 = 5
            if (r40 == 0) goto L_0x031f
            r20 = 5
        L_0x0315:
            r22 = 5
            r26 = 1
            r27 = 1
            r28 = 1
            goto L_0x040d
        L_0x031f:
            r20 = 4
            goto L_0x0315
        L_0x0322:
            r4 = r44
            r20 = r17
            r23 = r20
            r1 = 3
            r7 = 1
            r18 = 5
            goto L_0x0315
        L_0x032d:
            if (r54 == 0) goto L_0x0352
            r1 = r60
            r7 = 2
            if (r1 == r7) goto L_0x033c
            r7 = 1
            if (r1 != r7) goto L_0x0338
            goto L_0x033d
        L_0x0338:
            r1 = r17
            r4 = 5
            goto L_0x033f
        L_0x033c:
            r7 = 1
        L_0x033d:
            r1 = 5
            r4 = 4
        L_0x033f:
            r23 = r1
            r22 = r4
            r26 = r7
            r27 = r26
            r28 = r27
            r20 = r16
            r1 = 3
            r18 = 5
            r4 = r44
            goto L_0x040d
        L_0x0352:
            r7 = 1
            if (r4 <= 0) goto L_0x0368
            r4 = r44
            r26 = r7
            r27 = r26
            r28 = r27
            r20 = r16
            r1 = 3
            r18 = 5
            r22 = 5
        L_0x0364:
            r23 = 5
            goto L_0x040d
        L_0x0368:
            if (r4 != 0) goto L_0x0398
            if (r24 != 0) goto L_0x0398
            if (r57 != 0) goto L_0x037e
            r4 = r44
            r26 = r7
            r27 = r26
            r28 = r27
            r20 = r16
            r22 = r17
            r1 = 3
            r18 = 5
            goto L_0x0364
        L_0x037e:
            if (r8 == r3) goto L_0x0384
            if (r5 == r3) goto L_0x0384
            r1 = 4
            goto L_0x0385
        L_0x0384:
            r1 = 5
        L_0x0385:
            r4 = r44
            r23 = r1
            r26 = r7
            r27 = r26
            r28 = r27
            r20 = r16
            r1 = 3
            r18 = 5
            r22 = 4
            goto L_0x040d
        L_0x0398:
            r4 = r44
            r26 = r7
            r27 = r26
            r28 = r27
            r20 = r16
            r1 = 3
            r18 = 5
            r22 = 4
            goto L_0x0364
        L_0x03a8:
            r7 = 1
            r4 = r44
            r20 = r16
            r18 = 5
            r22 = 4
            r23 = 5
            r26 = 0
            r27 = 0
        L_0x03b7:
            r28 = 0
            goto L_0x040d
        L_0x03ba:
            r7 = 1
            r17 = 8
            boolean r1 = r2.isFinalValue
            if (r1 == 0) goto L_0x03fd
            boolean r1 = r15.isFinalValue
            if (r1 == 0) goto L_0x03fd
            int r1 = r47.getMargin()
            int r3 = r48.getMargin()
            r4 = 8
            r54 = r38
            r55 = r9
            r56 = r2
            r57 = r1
            r58 = r53
            r59 = r15
            r60 = r14
            r61 = r3
            r62 = r4
            r54.addCentering(r55, r56, r57, r58, r59, r60, r61, r62)
            if (r40 == 0) goto L_0x03fc
            if (r19 == 0) goto L_0x03fc
            androidx.constraintlayout.core.widgets.ConstraintAnchor r1 = r6.mTarget
            if (r1 == 0) goto L_0x03f3
            int r1 = r48.getMargin()
            r4 = r44
            goto L_0x03f6
        L_0x03f3:
            r4 = r44
            r1 = 0
        L_0x03f6:
            if (r15 == r4) goto L_0x03fc
            r2 = 5
            r10.addGreaterThan(r4, r14, r1, r2)
        L_0x03fc:
            return
        L_0x03fd:
            r4 = r44
            r1 = 3
            r18 = 5
            r26 = r7
            r27 = r26
            r20 = r16
            r23 = r18
            r22 = 4
            goto L_0x03b7
        L_0x040d:
            if (r26 == 0) goto L_0x0418
            if (r2 != r15) goto L_0x0418
            if (r8 == r3) goto L_0x0418
            r26 = 0
            r29 = 0
            goto L_0x041a
        L_0x0418:
            r29 = r7
        L_0x041a:
            if (r27 == 0) goto L_0x0465
            if (r25 != 0) goto L_0x042f
            if (r55 != 0) goto L_0x042f
            if (r57 != 0) goto L_0x042f
            if (r2 != r11) goto L_0x042f
            if (r15 != r4) goto L_0x042f
            r23 = r17
            r27 = r23
            r20 = 0
            r29 = 0
            goto L_0x0433
        L_0x042f:
            r27 = r20
            r20 = r40
        L_0x0433:
            int r30 = r47.getMargin()
            int r31 = r48.getMargin()
            r13 = r1
            r1 = r38
            r42 = r2
            r13 = r17
            r17 = 4
            r36 = r18
            r18 = r7
            r7 = r36
            r2 = r9
            r32 = r3
            r3 = r42
            r4 = r30
            r33 = r5
            r5 = r53
            r6 = r15
            r7 = r14
            r34 = r8
            r8 = r31
            r35 = r9
            r9 = r27
            r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9)
        L_0x0462:
            r2 = r29
            goto L_0x0478
        L_0x0465:
            r42 = r2
            r32 = r3
            r33 = r5
            r18 = r7
            r34 = r8
            r35 = r9
            r13 = r17
            r17 = 4
            r20 = r40
            goto L_0x0462
        L_0x0478:
            int r1 = r0.mVisibility
            if (r1 != r13) goto L_0x0483
            boolean r1 = r48.hasDependents()
            if (r1 != 0) goto L_0x0483
            return
        L_0x0483:
            r1 = r42
            if (r26 == 0) goto L_0x04b9
            if (r20 == 0) goto L_0x049f
            if (r1 == r15) goto L_0x049f
            if (r25 != 0) goto L_0x049f
            r3 = r34
            boolean r4 = r3 instanceof androidx.constraintlayout.core.widgets.Barrier
            if (r4 != 0) goto L_0x049a
            r4 = r33
            boolean r5 = r4 instanceof androidx.constraintlayout.core.widgets.Barrier
            if (r5 == 0) goto L_0x04a3
            goto L_0x049c
        L_0x049a:
            r4 = r33
        L_0x049c:
            r5 = r16
            goto L_0x04a5
        L_0x049f:
            r4 = r33
            r3 = r34
        L_0x04a3:
            r5 = r23
        L_0x04a5:
            int r6 = r47.getMargin()
            r8 = r35
            r10.addGreaterThan(r8, r1, r6, r5)
            int r6 = r48.getMargin()
            int r6 = -r6
            r10.addLowerThan(r14, r15, r6, r5)
            r23 = r5
            goto L_0x04bf
        L_0x04b9:
            r4 = r33
            r3 = r34
            r8 = r35
        L_0x04bf:
            if (r20 == 0) goto L_0x04d5
            if (r58 == 0) goto L_0x04d5
            boolean r5 = r3 instanceof androidx.constraintlayout.core.widgets.Barrier
            if (r5 != 0) goto L_0x04d5
            boolean r5 = r4 instanceof androidx.constraintlayout.core.widgets.Barrier
            if (r5 != 0) goto L_0x04d5
            r5 = r32
            if (r4 == r5) goto L_0x04d7
            r6 = r16
            r7 = r6
            r2 = r18
            goto L_0x04db
        L_0x04d5:
            r5 = r32
        L_0x04d7:
            r6 = r22
            r7 = r23
        L_0x04db:
            if (r2 == 0) goto L_0x0526
            if (r28 == 0) goto L_0x0506
            if (r57 == 0) goto L_0x04e3
            if (r41 == 0) goto L_0x0506
        L_0x04e3:
            if (r3 == r5) goto L_0x04ea
            if (r4 != r5) goto L_0x04e8
            goto L_0x04ea
        L_0x04e8:
            r2 = r6
            goto L_0x04ec
        L_0x04ea:
            r2 = r16
        L_0x04ec:
            boolean r9 = r3 instanceof androidx.constraintlayout.core.widgets.Guideline
            if (r9 != 0) goto L_0x04f4
            boolean r9 = r4 instanceof androidx.constraintlayout.core.widgets.Guideline
            if (r9 == 0) goto L_0x04f5
        L_0x04f4:
            r2 = 5
        L_0x04f5:
            boolean r9 = r3 instanceof androidx.constraintlayout.core.widgets.Barrier
            if (r9 != 0) goto L_0x04fd
            boolean r9 = r4 instanceof androidx.constraintlayout.core.widgets.Barrier
            if (r9 == 0) goto L_0x04fe
        L_0x04fd:
            r2 = 5
        L_0x04fe:
            if (r57 == 0) goto L_0x0501
            r2 = 5
        L_0x0501:
            int r2 = java.lang.Math.max(r2, r6)
            goto L_0x0507
        L_0x0506:
            r2 = r6
        L_0x0507:
            if (r20 == 0) goto L_0x0517
            int r2 = java.lang.Math.min(r7, r2)
            if (r54 == 0) goto L_0x0517
            if (r57 != 0) goto L_0x0517
            if (r3 == r5) goto L_0x0515
            if (r4 != r5) goto L_0x0517
        L_0x0515:
            r2 = r17
        L_0x0517:
            int r3 = r47.getMargin()
            r10.addEquality(r8, r1, r3, r2)
            int r3 = r48.getMargin()
            int r3 = -r3
            r10.addEquality(r14, r15, r3, r2)
        L_0x0526:
            if (r20 == 0) goto L_0x0537
            if (r11 != r1) goto L_0x052f
            int r2 = r47.getMargin()
            goto L_0x0530
        L_0x052f:
            r2 = 0
        L_0x0530:
            if (r1 == r11) goto L_0x0537
            r1 = 5
            r10.addGreaterThan(r8, r11, r2, r1)
            goto L_0x0538
        L_0x0537:
            r1 = 5
        L_0x0538:
            if (r20 == 0) goto L_0x0208
            if (r25 == 0) goto L_0x0208
            r2 = r15
            if (r51 != 0) goto L_0x0209
            if (r24 != 0) goto L_0x0209
            if (r25 == 0) goto L_0x054b
            r3 = 3
            if (r12 != r3) goto L_0x054b
            r3 = 0
            r10.addGreaterThan(r14, r8, r3, r13)
            goto L_0x0555
        L_0x054b:
            r3 = 0
            r10.addGreaterThan(r14, r8, r3, r1)
            goto L_0x0555
        L_0x0550:
            r3 = r1
            r2 = r15
            r1 = 5
        L_0x0553:
            r20 = r40
        L_0x0555:
            if (r20 == 0) goto L_0x0582
            if (r19 == 0) goto L_0x0582
            r4 = r48
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = r4.mTarget
            if (r5 == 0) goto L_0x0563
            int r3 = r48.getMargin()
        L_0x0563:
            r5 = r44
            if (r2 == r5) goto L_0x0582
            boolean r2 = r0.OPTIMIZE_WRAP
            if (r2 == 0) goto L_0x057f
            boolean r2 = r14.isFinalValue
            if (r2 == 0) goto L_0x057f
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r0.mParent
            if (r2 == 0) goto L_0x057f
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r2 = (androidx.constraintlayout.core.widgets.ConstraintWidgetContainer) r2
            if (r39 == 0) goto L_0x057b
            r2.addHorizontalWrapMaxVariable(r4)
            goto L_0x057e
        L_0x057b:
            r2.addVerticalWrapMaxVariable(r4)
        L_0x057e:
            return
        L_0x057f:
            r10.addGreaterThan(r5, r14, r3, r1)
        L_0x0582:
            return
        L_0x0583:
            if (r1 >= r7) goto L_0x05bf
            if (r40 == 0) goto L_0x05bf
            if (r19 == 0) goto L_0x05bf
            r10.addGreaterThan(r8, r11, r3, r13)
            if (r39 != 0) goto L_0x0597
            androidx.constraintlayout.core.widgets.ConstraintAnchor r1 = r0.mBaseline
            androidx.constraintlayout.core.widgets.ConstraintAnchor r1 = r1.mTarget
            if (r1 != 0) goto L_0x0595
            goto L_0x0597
        L_0x0595:
            r2 = r3
            goto L_0x0599
        L_0x0597:
            r2 = r18
        L_0x0599:
            if (r39 != 0) goto L_0x05ba
            androidx.constraintlayout.core.widgets.ConstraintAnchor r1 = r0.mBaseline
            androidx.constraintlayout.core.widgets.ConstraintAnchor r1 = r1.mTarget
            if (r1 == 0) goto L_0x05ba
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r1.mOwner
            float r2 = r1.mDimensionRatio
            r4 = 0
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 == 0) goto L_0x05b9
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r1.mListDimensionBehaviors
            r2 = r1[r3]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r4 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r2 != r4) goto L_0x05b9
            r1 = r1[r18]
            if (r1 != r4) goto L_0x05b9
            r2 = r18
            goto L_0x05ba
        L_0x05b9:
            r2 = r3
        L_0x05ba:
            if (r2 == 0) goto L_0x05bf
            r10.addGreaterThan(r5, r14, r3, r13)
        L_0x05bf:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.ConstraintWidget.applyConstraints(androidx.constraintlayout.core.LinearSystem, boolean, boolean, boolean, boolean, androidx.constraintlayout.core.SolverVariable, androidx.constraintlayout.core.SolverVariable, androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour, boolean, androidx.constraintlayout.core.widgets.ConstraintAnchor, androidx.constraintlayout.core.widgets.ConstraintAnchor, int, int, int, int, float, boolean, boolean, boolean, boolean, boolean, int, int, int, int, float, boolean):void");
    }

    /* renamed from: androidx.constraintlayout.core.widgets.ConstraintWidget$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type;
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintWidget$DimensionBehaviour;

        /* JADX WARNING: Can't wrap try/catch for region: R(29:0|(2:1|2)|3|(2:5|6)|7|9|10|11|(2:13|14)|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|36) */
        /* JADX WARNING: Can't wrap try/catch for region: R(31:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|36) */
        /* JADX WARNING: Can't wrap try/catch for region: R(32:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|36) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0044 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x004e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0058 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0062 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x006d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0083 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x008f */
        static {
            /*
                androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r0 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintWidget$DimensionBehaviour = r0
                r1 = 1
                androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintWidget$DimensionBehaviour     // Catch:{ NoSuchFieldError -> 0x001d }
                androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintWidget$DimensionBehaviour     // Catch:{ NoSuchFieldError -> 0x0028 }
                androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r4 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_PARENT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                r3 = 4
                int[] r4 = $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintWidget$DimensionBehaviour     // Catch:{ NoSuchFieldError -> 0x0033 }
                androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r5 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                androidx.constraintlayout.core.widgets.ConstraintAnchor$Type[] r4 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type = r4
                androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r5 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.LEFT     // Catch:{ NoSuchFieldError -> 0x0044 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0044 }
                r4[r5] = r1     // Catch:{ NoSuchFieldError -> 0x0044 }
            L_0x0044:
                int[] r1 = $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type     // Catch:{ NoSuchFieldError -> 0x004e }
                androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.TOP     // Catch:{ NoSuchFieldError -> 0x004e }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x004e }
                r1[r4] = r0     // Catch:{ NoSuchFieldError -> 0x004e }
            L_0x004e:
                int[] r0 = $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type     // Catch:{ NoSuchFieldError -> 0x0058 }
                androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.RIGHT     // Catch:{ NoSuchFieldError -> 0x0058 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0058 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0058 }
            L_0x0058:
                int[] r0 = $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type     // Catch:{ NoSuchFieldError -> 0x0062 }
                androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.BOTTOM     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                int[] r0 = $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type     // Catch:{ NoSuchFieldError -> 0x006d }
                androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.BASELINE     // Catch:{ NoSuchFieldError -> 0x006d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006d }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006d }
            L_0x006d:
                int[] r0 = $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type     // Catch:{ NoSuchFieldError -> 0x0078 }
                androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.CENTER     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type     // Catch:{ NoSuchFieldError -> 0x0083 }
                androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.CENTER_X     // Catch:{ NoSuchFieldError -> 0x0083 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0083 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0083 }
            L_0x0083:
                int[] r0 = $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type     // Catch:{ NoSuchFieldError -> 0x008f }
                androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.CENTER_Y     // Catch:{ NoSuchFieldError -> 0x008f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x008f }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x008f }
            L_0x008f:
                int[] r0 = $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type     // Catch:{ NoSuchFieldError -> 0x009b }
                androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.NONE     // Catch:{ NoSuchFieldError -> 0x009b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009b }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009b }
            L_0x009b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.ConstraintWidget.AnonymousClass1.<clinit>():void");
        }
    }

    public void updateFromSolver(LinearSystem linearSystem, boolean z) {
        VerticalWidgetRun verticalWidgetRun;
        HorizontalWidgetRun horizontalWidgetRun;
        int objectVariableValue = linearSystem.getObjectVariableValue(this.mLeft);
        int objectVariableValue2 = linearSystem.getObjectVariableValue(this.mTop);
        int objectVariableValue3 = linearSystem.getObjectVariableValue(this.mRight);
        int objectVariableValue4 = linearSystem.getObjectVariableValue(this.mBottom);
        if (z && (horizontalWidgetRun = this.horizontalRun) != null) {
            DependencyNode dependencyNode = horizontalWidgetRun.start;
            if (dependencyNode.resolved) {
                DependencyNode dependencyNode2 = horizontalWidgetRun.end;
                if (dependencyNode2.resolved) {
                    objectVariableValue = dependencyNode.value;
                    objectVariableValue3 = dependencyNode2.value;
                }
            }
        }
        if (z && (verticalWidgetRun = this.verticalRun) != null) {
            DependencyNode dependencyNode3 = verticalWidgetRun.start;
            if (dependencyNode3.resolved) {
                DependencyNode dependencyNode4 = verticalWidgetRun.end;
                if (dependencyNode4.resolved) {
                    objectVariableValue2 = dependencyNode3.value;
                    objectVariableValue4 = dependencyNode4.value;
                }
            }
        }
        int i = objectVariableValue4 - objectVariableValue2;
        if (objectVariableValue3 - objectVariableValue < 0 || i < 0 || objectVariableValue == Integer.MIN_VALUE || objectVariableValue == Integer.MAX_VALUE || objectVariableValue2 == Integer.MIN_VALUE || objectVariableValue2 == Integer.MAX_VALUE || objectVariableValue3 == Integer.MIN_VALUE || objectVariableValue3 == Integer.MAX_VALUE || objectVariableValue4 == Integer.MIN_VALUE || objectVariableValue4 == Integer.MAX_VALUE) {
            objectVariableValue = 0;
            objectVariableValue4 = 0;
            objectVariableValue2 = 0;
            objectVariableValue3 = 0;
        }
        setFrame(objectVariableValue, objectVariableValue2, objectVariableValue3, objectVariableValue4);
    }

    public void updateFromRuns(boolean z, boolean z2) {
        int i;
        int i2;
        boolean isResolved = z & this.horizontalRun.isResolved();
        boolean isResolved2 = z2 & this.verticalRun.isResolved();
        HorizontalWidgetRun horizontalWidgetRun = this.horizontalRun;
        int i3 = horizontalWidgetRun.start.value;
        VerticalWidgetRun verticalWidgetRun = this.verticalRun;
        int i4 = verticalWidgetRun.start.value;
        int i5 = horizontalWidgetRun.end.value;
        int i6 = verticalWidgetRun.end.value;
        int i7 = i6 - i4;
        if (i5 - i3 < 0 || i7 < 0 || i3 == Integer.MIN_VALUE || i3 == Integer.MAX_VALUE || i4 == Integer.MIN_VALUE || i4 == Integer.MAX_VALUE || i5 == Integer.MIN_VALUE || i5 == Integer.MAX_VALUE || i6 == Integer.MIN_VALUE || i6 == Integer.MAX_VALUE) {
            i5 = 0;
            i3 = 0;
            i6 = 0;
            i4 = 0;
        }
        int i8 = i5 - i3;
        int i9 = i6 - i4;
        if (isResolved) {
            this.mX = i3;
        }
        if (isResolved2) {
            this.mY = i4;
        }
        if (this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        if (isResolved) {
            if (this.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED && i8 < (i2 = this.mWidth)) {
                i8 = i2;
            }
            this.mWidth = i8;
            int i10 = this.mMinWidth;
            if (i8 < i10) {
                this.mWidth = i10;
            }
        }
        if (isResolved2) {
            if (this.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED && i9 < (i = this.mHeight)) {
                i9 = i;
            }
            this.mHeight = i9;
            int i11 = this.mMinHeight;
            if (i9 < i11) {
                this.mHeight = i11;
            }
        }
    }

    public void addChildrenToSolverByDependency(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, HashSet hashSet, int i, boolean z) {
        if (z) {
            if (hashSet.contains(this)) {
                Optimizer.checkMatchParent(constraintWidgetContainer, linearSystem, this);
                hashSet.remove(this);
                addToSolver(linearSystem, constraintWidgetContainer.optimizeFor(64));
            } else {
                return;
            }
        }
        if (i == 0) {
            HashSet dependents = this.mLeft.getDependents();
            if (dependents != null) {
                Iterator it = dependents.iterator();
                while (it.hasNext()) {
                    ((ConstraintAnchor) it.next()).mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i, true);
                }
            }
            HashSet dependents2 = this.mRight.getDependents();
            if (dependents2 != null) {
                Iterator it2 = dependents2.iterator();
                while (it2.hasNext()) {
                    ((ConstraintAnchor) it2.next()).mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i, true);
                }
                return;
            }
            return;
        }
        HashSet dependents3 = this.mTop.getDependents();
        if (dependents3 != null) {
            Iterator it3 = dependents3.iterator();
            while (it3.hasNext()) {
                ((ConstraintAnchor) it3.next()).mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i, true);
            }
        }
        HashSet dependents4 = this.mBottom.getDependents();
        if (dependents4 != null) {
            Iterator it4 = dependents4.iterator();
            while (it4.hasNext()) {
                ((ConstraintAnchor) it4.next()).mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i, true);
            }
        }
        HashSet dependents5 = this.mBaseline.getDependents();
        if (dependents5 != null) {
            Iterator it5 = dependents5.iterator();
            while (it5.hasNext()) {
                ((ConstraintAnchor) it5.next()).mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i, true);
            }
        }
    }

    public void getSceneString(StringBuilder sb) {
        sb.append("  " + this.stringId + ":{\n");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("    actualWidth:");
        sb2.append(this.mWidth);
        sb.append(sb2.toString());
        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        sb.append("    actualHeight:" + this.mHeight);
        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        sb.append("    actualLeft:" + this.mX);
        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        sb.append("    actualTop:" + this.mY);
        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        getSceneString(sb, ViewProps.LEFT, this.mLeft);
        getSceneString(sb, ViewProps.TOP, this.mTop);
        getSceneString(sb, ViewProps.RIGHT, this.mRight);
        getSceneString(sb, ViewProps.BOTTOM, this.mBottom);
        getSceneString(sb, "baseline", this.mBaseline);
        getSceneString(sb, "centerX", this.mCenterX);
        getSceneString(sb, "centerY", this.mCenterY);
        getSceneString(sb, "    width", this.mWidth, this.mMinWidth, this.mMaxDimension[0], this.mWidthOverride, this.mMatchConstraintMinWidth, this.mMatchConstraintDefaultWidth, this.mMatchConstraintPercentWidth, this.mWeight[0]);
        getSceneString(sb, "    height", this.mHeight, this.mMinHeight, this.mMaxDimension[1], this.mHeightOverride, this.mMatchConstraintMinHeight, this.mMatchConstraintDefaultHeight, this.mMatchConstraintPercentHeight, this.mWeight[1]);
        serializeDimensionRatio(sb, "    dimensionRatio", this.mDimensionRatio, this.mDimensionRatioSide);
        serializeAttribute(sb, "    horizontalBias", this.mHorizontalBiasPercent, DEFAULT_BIAS);
        serializeAttribute(sb, "    verticalBias", this.mVerticalBiasPercent, DEFAULT_BIAS);
        serializeAttribute(sb, "    horizontalChainStyle", this.mHorizontalChainStyle, 0);
        serializeAttribute(sb, "    verticalChainStyle", this.mVerticalChainStyle, 0);
        sb.append("  }");
    }

    private void getSceneString(StringBuilder sb, String str, int i, int i2, int i3, int i4, int i5, int i6, float f, float f2) {
        sb.append(str);
        sb.append(" :  {\n");
        serializeAttribute(sb, "      size", i, 0);
        serializeAttribute(sb, "      min", i2, 0);
        serializeAttribute(sb, "      max", i3, Integer.MAX_VALUE);
        serializeAttribute(sb, "      matchMin", i5, 0);
        serializeAttribute(sb, "      matchDef", i6, 0);
        serializeAttribute(sb, "      matchPercent", f, 1.0f);
        sb.append("    },\n");
    }

    private void getSceneString(StringBuilder sb, String str, ConstraintAnchor constraintAnchor) {
        if (constraintAnchor.mTarget != null) {
            sb.append("    ");
            sb.append(str);
            sb.append(" : [ '");
            sb.append(constraintAnchor.mTarget);
            sb.append("'");
            if (!(constraintAnchor.mGoneMargin == Integer.MIN_VALUE && constraintAnchor.mMargin == 0)) {
                sb.append(",");
                sb.append(constraintAnchor.mMargin);
                if (constraintAnchor.mGoneMargin != Integer.MIN_VALUE) {
                    sb.append(",");
                    sb.append(constraintAnchor.mGoneMargin);
                    sb.append(",");
                }
            }
            sb.append(" ] ,\n");
        }
    }
}
