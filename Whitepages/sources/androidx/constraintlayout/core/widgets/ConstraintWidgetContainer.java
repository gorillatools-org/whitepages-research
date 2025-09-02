package androidx.constraintlayout.core.widgets;

import android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.Metrics;
import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.constraintlayout.core.widgets.analyzer.DependencyGraph;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class ConstraintWidgetContainer extends WidgetContainer {
    private WeakReference horizontalWrapMax = null;
    private WeakReference horizontalWrapMin = null;
    BasicMeasure mBasicMeasureSolver = new BasicMeasure(this);
    int mDebugSolverPassCount = 0;
    public DependencyGraph mDependencyGraph = new DependencyGraph(this);
    public boolean mGroupsWrapOptimized = false;
    private boolean mHeightMeasuredTooSmall = false;
    ChainHead[] mHorizontalChainsArray = new ChainHead[4];
    public int mHorizontalChainsSize = 0;
    public boolean mHorizontalWrapOptimized = false;
    private boolean mIsRtl = false;
    public BasicMeasure.Measure mMeasure = new BasicMeasure.Measure();
    protected BasicMeasure.Measurer mMeasurer = null;
    private int mOptimizationLevel = 257;
    int mPaddingBottom;
    int mPaddingLeft;
    int mPaddingRight;
    int mPaddingTop;
    public boolean mSkipSolver = false;
    protected LinearSystem mSystem = new LinearSystem();
    ChainHead[] mVerticalChainsArray = new ChainHead[4];
    public int mVerticalChainsSize = 0;
    public boolean mVerticalWrapOptimized = false;
    private boolean mWidthMeasuredTooSmall = false;
    public int mWrapFixedHeight = 0;
    public int mWrapFixedWidth = 0;
    private int pass;
    private WeakReference verticalWrapMax = null;
    private WeakReference verticalWrapMin = null;
    HashSet widgetsToAdd = new HashSet();

    public void invalidateGraph() {
        this.mDependencyGraph.invalidateGraph();
    }

    public void invalidateMeasures() {
        this.mDependencyGraph.invalidateMeasures();
    }

    public boolean directMeasure(boolean z) {
        return this.mDependencyGraph.directMeasure(z);
    }

    public boolean directMeasureSetup(boolean z) {
        return this.mDependencyGraph.directMeasureSetup(z);
    }

    public boolean directMeasureWithOrientation(boolean z, int i) {
        return this.mDependencyGraph.directMeasureWithOrientation(z, i);
    }

    public long measure(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        int i10 = i8;
        this.mPaddingLeft = i10;
        int i11 = i9;
        this.mPaddingTop = i11;
        return this.mBasicMeasureSolver.solverMeasure(this, i, i10, i11, i2, i3, i4, i5, i6, i7);
    }

    public void updateHierarchy() {
        this.mBasicMeasureSolver.updateHierarchy(this);
    }

    public void setMeasurer(BasicMeasure.Measurer measurer) {
        this.mMeasurer = measurer;
        this.mDependencyGraph.setMeasurer(measurer);
    }

    public BasicMeasure.Measurer getMeasurer() {
        return this.mMeasurer;
    }

    public void fillMetrics(Metrics metrics) {
        this.mSystem.fillMetrics(metrics);
    }

    public void setOptimizationLevel(int i) {
        this.mOptimizationLevel = i;
        LinearSystem.USE_DEPENDENCY_ORDERING = optimizeFor(512);
    }

    public int getOptimizationLevel() {
        return this.mOptimizationLevel;
    }

    public boolean optimizeFor(int i) {
        return (this.mOptimizationLevel & i) == i;
    }

    public void reset() {
        this.mSystem.reset();
        this.mPaddingLeft = 0;
        this.mPaddingRight = 0;
        this.mPaddingTop = 0;
        this.mPaddingBottom = 0;
        this.mSkipSolver = false;
        super.reset();
    }

    public boolean isWidthMeasuredTooSmall() {
        return this.mWidthMeasuredTooSmall;
    }

    public boolean isHeightMeasuredTooSmall() {
        return this.mHeightMeasuredTooSmall;
    }

    /* access modifiers changed from: package-private */
    public void addVerticalWrapMinVariable(ConstraintAnchor constraintAnchor) {
        WeakReference weakReference = this.verticalWrapMin;
        if (weakReference == null || weakReference.get() == null || constraintAnchor.getFinalValue() > ((ConstraintAnchor) this.verticalWrapMin.get()).getFinalValue()) {
            this.verticalWrapMin = new WeakReference(constraintAnchor);
        }
    }

    public void addHorizontalWrapMinVariable(ConstraintAnchor constraintAnchor) {
        WeakReference weakReference = this.horizontalWrapMin;
        if (weakReference == null || weakReference.get() == null || constraintAnchor.getFinalValue() > ((ConstraintAnchor) this.horizontalWrapMin.get()).getFinalValue()) {
            this.horizontalWrapMin = new WeakReference(constraintAnchor);
        }
    }

    /* access modifiers changed from: package-private */
    public void addVerticalWrapMaxVariable(ConstraintAnchor constraintAnchor) {
        WeakReference weakReference = this.verticalWrapMax;
        if (weakReference == null || weakReference.get() == null || constraintAnchor.getFinalValue() > ((ConstraintAnchor) this.verticalWrapMax.get()).getFinalValue()) {
            this.verticalWrapMax = new WeakReference(constraintAnchor);
        }
    }

    public void addHorizontalWrapMaxVariable(ConstraintAnchor constraintAnchor) {
        WeakReference weakReference = this.horizontalWrapMax;
        if (weakReference == null || weakReference.get() == null || constraintAnchor.getFinalValue() > ((ConstraintAnchor) this.horizontalWrapMax.get()).getFinalValue()) {
            this.horizontalWrapMax = new WeakReference(constraintAnchor);
        }
    }

    private void addMinWrap(ConstraintAnchor constraintAnchor, SolverVariable solverVariable) {
        this.mSystem.addGreaterThan(this.mSystem.createObjectVariable(constraintAnchor), solverVariable, 0, 5);
    }

    private void addMaxWrap(ConstraintAnchor constraintAnchor, SolverVariable solverVariable) {
        this.mSystem.addGreaterThan(solverVariable, this.mSystem.createObjectVariable(constraintAnchor), 0, 5);
    }

    public boolean addChildrenToSolver(LinearSystem linearSystem) {
        boolean optimizeFor = optimizeFor(64);
        addToSolver(linearSystem, optimizeFor);
        int size = this.mChildren.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            constraintWidget.setInBarrier(0, false);
            constraintWidget.setInBarrier(1, false);
            if (constraintWidget instanceof Barrier) {
                z = true;
            }
        }
        if (z) {
            for (int i2 = 0; i2 < size; i2++) {
                ConstraintWidget constraintWidget2 = (ConstraintWidget) this.mChildren.get(i2);
                if (constraintWidget2 instanceof Barrier) {
                    ((Barrier) constraintWidget2).markWidgets();
                }
            }
        }
        this.widgetsToAdd.clear();
        for (int i3 = 0; i3 < size; i3++) {
            ConstraintWidget constraintWidget3 = (ConstraintWidget) this.mChildren.get(i3);
            if (constraintWidget3.addFirst()) {
                constraintWidget3.addToSolver(linearSystem, optimizeFor);
            }
        }
        while (this.widgetsToAdd.size() > 0) {
            int size2 = this.widgetsToAdd.size();
            Iterator it = this.widgetsToAdd.iterator();
            if (it.hasNext()) {
                MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m((ConstraintWidget) it.next());
                throw null;
            } else if (size2 == this.widgetsToAdd.size()) {
                Iterator it2 = this.widgetsToAdd.iterator();
                while (it2.hasNext()) {
                    ((ConstraintWidget) it2.next()).addToSolver(linearSystem, optimizeFor);
                }
                this.widgetsToAdd.clear();
            }
        }
        if (LinearSystem.USE_DEPENDENCY_ORDERING) {
            HashSet hashSet = new HashSet();
            for (int i4 = 0; i4 < size; i4++) {
                ConstraintWidget constraintWidget4 = (ConstraintWidget) this.mChildren.get(i4);
                if (!constraintWidget4.addFirst()) {
                    hashSet.add(constraintWidget4);
                }
            }
            addChildrenToSolverByDependency(this, linearSystem, hashSet, getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT ? 0 : 1, false);
            Iterator it3 = hashSet.iterator();
            while (it3.hasNext()) {
                ConstraintWidget constraintWidget5 = (ConstraintWidget) it3.next();
                Optimizer.checkMatchParent(this, linearSystem, constraintWidget5);
                constraintWidget5.addToSolver(linearSystem, optimizeFor);
            }
        } else {
            for (int i5 = 0; i5 < size; i5++) {
                ConstraintWidget constraintWidget6 = (ConstraintWidget) this.mChildren.get(i5);
                if (constraintWidget6 instanceof ConstraintWidgetContainer) {
                    ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget6.mListDimensionBehaviors;
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[1];
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    if (dimensionBehaviour == dimensionBehaviour3) {
                        constraintWidget6.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                    }
                    if (dimensionBehaviour2 == dimensionBehaviour3) {
                        constraintWidget6.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                    }
                    constraintWidget6.addToSolver(linearSystem, optimizeFor);
                    if (dimensionBehaviour == dimensionBehaviour3) {
                        constraintWidget6.setHorizontalDimensionBehaviour(dimensionBehaviour);
                    }
                    if (dimensionBehaviour2 == dimensionBehaviour3) {
                        constraintWidget6.setVerticalDimensionBehaviour(dimensionBehaviour2);
                    }
                } else {
                    Optimizer.checkMatchParent(this, linearSystem, constraintWidget6);
                    if (!constraintWidget6.addFirst()) {
                        constraintWidget6.addToSolver(linearSystem, optimizeFor);
                    }
                }
            }
        }
        if (this.mHorizontalChainsSize > 0) {
            Chain.applyChainConstraints(this, linearSystem, (ArrayList) null, 0);
        }
        if (this.mVerticalChainsSize > 0) {
            Chain.applyChainConstraints(this, linearSystem, (ArrayList) null, 1);
        }
        return true;
    }

    public boolean updateChildrenFromSolver(LinearSystem linearSystem, boolean[] zArr) {
        zArr[2] = false;
        boolean optimizeFor = optimizeFor(64);
        updateFromSolver(linearSystem, optimizeFor);
        int size = this.mChildren.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            constraintWidget.updateFromSolver(linearSystem, optimizeFor);
            if (constraintWidget.hasDimensionOverride()) {
                z = true;
            }
        }
        return z;
    }

    public void updateFromRuns(boolean z, boolean z2) {
        super.updateFromRuns(z, z2);
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ((ConstraintWidget) this.mChildren.get(i)).updateFromRuns(z, z2);
        }
    }

    public void setRtl(boolean z) {
        this.mIsRtl = z;
    }

    public boolean isRtl() {
        return this.mIsRtl;
    }

    public static boolean measure(int i, ConstraintWidget constraintWidget, BasicMeasure.Measurer measurer, BasicMeasure.Measure measure, int i2) {
        int i3;
        int i4;
        if (measurer == null) {
            return false;
        }
        if (constraintWidget.getVisibility() == 8 || (constraintWidget instanceof Guideline) || (constraintWidget instanceof Barrier)) {
            measure.measuredWidth = 0;
            measure.measuredHeight = 0;
            return false;
        }
        measure.horizontalBehavior = constraintWidget.getHorizontalDimensionBehaviour();
        measure.verticalBehavior = constraintWidget.getVerticalDimensionBehaviour();
        measure.horizontalDimension = constraintWidget.getWidth();
        measure.verticalDimension = constraintWidget.getHeight();
        measure.measuredNeedsSolverPass = false;
        measure.measureStrategy = i2;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = measure.horizontalBehavior;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z = dimensionBehaviour == dimensionBehaviour2;
        boolean z2 = measure.verticalBehavior == dimensionBehaviour2;
        boolean z3 = z && constraintWidget.mDimensionRatio > 0.0f;
        boolean z4 = z2 && constraintWidget.mDimensionRatio > 0.0f;
        if (z && constraintWidget.hasDanglingDimension(0) && constraintWidget.mMatchConstraintDefaultWidth == 0 && !z3) {
            measure.horizontalBehavior = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            if (z2 && constraintWidget.mMatchConstraintDefaultHeight == 0) {
                measure.horizontalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
            }
            z = false;
        }
        if (z2 && constraintWidget.hasDanglingDimension(1) && constraintWidget.mMatchConstraintDefaultHeight == 0 && !z4) {
            measure.verticalBehavior = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            if (z && constraintWidget.mMatchConstraintDefaultWidth == 0) {
                measure.verticalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
            }
            z2 = false;
        }
        if (constraintWidget.isResolvedHorizontally()) {
            measure.horizontalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
            z = false;
        }
        if (constraintWidget.isResolvedVertically()) {
            measure.verticalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
            z2 = false;
        }
        if (z3) {
            if (constraintWidget.mResolvedMatchConstraintDefault[0] == 4) {
                measure.horizontalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
            } else if (!z2) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = measure.verticalBehavior;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.FIXED;
                if (dimensionBehaviour3 == dimensionBehaviour4) {
                    i4 = measure.verticalDimension;
                } else {
                    measure.horizontalBehavior = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    measurer.measure(constraintWidget, measure);
                    i4 = measure.measuredHeight;
                }
                measure.horizontalBehavior = dimensionBehaviour4;
                measure.horizontalDimension = (int) (constraintWidget.getDimensionRatio() * ((float) i4));
            }
        }
        if (z4) {
            if (constraintWidget.mResolvedMatchConstraintDefault[1] == 4) {
                measure.verticalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
            } else if (!z) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = measure.horizontalBehavior;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour6 = ConstraintWidget.DimensionBehaviour.FIXED;
                if (dimensionBehaviour5 == dimensionBehaviour6) {
                    i3 = measure.horizontalDimension;
                } else {
                    measure.verticalBehavior = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    measurer.measure(constraintWidget, measure);
                    i3 = measure.measuredWidth;
                }
                measure.verticalBehavior = dimensionBehaviour6;
                if (constraintWidget.getDimensionRatioSide() == -1) {
                    measure.verticalDimension = (int) (((float) i3) / constraintWidget.getDimensionRatio());
                } else {
                    measure.verticalDimension = (int) (constraintWidget.getDimensionRatio() * ((float) i3));
                }
            }
        }
        measurer.measure(constraintWidget, measure);
        constraintWidget.setWidth(measure.measuredWidth);
        constraintWidget.setHeight(measure.measuredHeight);
        constraintWidget.setHasBaseline(measure.measuredHasBaseline);
        constraintWidget.setBaselineDistance(measure.measuredBaseline);
        measure.measureStrategy = BasicMeasure.Measure.SELF_DIMENSIONS;
        return measure.measuredNeedsSolverPass;
    }

    /* JADX WARNING: type inference failed for: r6v1, types: [boolean] */
    /* JADX WARNING: type inference failed for: r6v3 */
    /* JADX WARNING: type inference failed for: r6v4 */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x030a  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x030c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void layout() {
        /*
            r18 = this;
            r1 = r18
            r2 = 0
            r1.mX = r2
            r1.mY = r2
            r1.mWidthMeasuredTooSmall = r2
            r1.mHeightMeasuredTooSmall = r2
            java.util.ArrayList r0 = r1.mChildren
            int r3 = r0.size()
            int r0 = r18.getWidth()
            int r0 = java.lang.Math.max(r2, r0)
            int r4 = r18.getHeight()
            int r4 = java.lang.Math.max(r2, r4)
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r5 = r1.mListDimensionBehaviors
            r6 = 1
            r7 = r5[r6]
            r5 = r5[r2]
            int r8 = r1.pass
            if (r8 != 0) goto L_0x0080
            int r8 = r1.mOptimizationLevel
            boolean r8 = androidx.constraintlayout.core.widgets.Optimizer.enabled(r8, r6)
            if (r8 == 0) goto L_0x0080
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer r8 = r18.getMeasurer()
            androidx.constraintlayout.core.widgets.analyzer.Direct.solvingPass(r1, r8)
            r8 = r2
        L_0x003c:
            if (r8 >= r3) goto L_0x0080
            java.util.ArrayList r9 = r1.mChildren
            java.lang.Object r9 = r9.get(r8)
            androidx.constraintlayout.core.widgets.ConstraintWidget r9 = (androidx.constraintlayout.core.widgets.ConstraintWidget) r9
            boolean r10 = r9.isMeasureRequested()
            if (r10 == 0) goto L_0x007d
            boolean r10 = r9 instanceof androidx.constraintlayout.core.widgets.Guideline
            if (r10 != 0) goto L_0x007d
            boolean r10 = r9 instanceof androidx.constraintlayout.core.widgets.Barrier
            if (r10 != 0) goto L_0x007d
            boolean r10 = r9.isInVirtualLayout()
            if (r10 != 0) goto L_0x007d
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r10 = r9.getDimensionBehaviour(r2)
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r11 = r9.getDimensionBehaviour(r6)
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r12 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r10 != r12) goto L_0x0071
            int r10 = r9.mMatchConstraintDefaultWidth
            if (r10 == r6) goto L_0x0071
            if (r11 != r12) goto L_0x0071
            int r10 = r9.mMatchConstraintDefaultHeight
            if (r10 == r6) goto L_0x0071
            goto L_0x007d
        L_0x0071:
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure r10 = new androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure
            r10.<init>()
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer r11 = r1.mMeasurer
            int r12 = androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measure.SELF_DIMENSIONS
            measure(r2, r9, r11, r10, r12)
        L_0x007d:
            int r8 = r8 + 1
            goto L_0x003c
        L_0x0080:
            r8 = 2
            if (r3 <= r8) goto L_0x00c9
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r5 == r9) goto L_0x0089
            if (r7 != r9) goto L_0x00c9
        L_0x0089:
            int r10 = r1.mOptimizationLevel
            r11 = 1024(0x400, float:1.435E-42)
            boolean r10 = androidx.constraintlayout.core.widgets.Optimizer.enabled(r10, r11)
            if (r10 == 0) goto L_0x00c9
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer r10 = r18.getMeasurer()
            boolean r10 = androidx.constraintlayout.core.widgets.analyzer.Grouping.simpleSolvingPass(r1, r10)
            if (r10 == 0) goto L_0x00c9
            if (r5 != r9) goto L_0x00b1
            int r10 = r18.getWidth()
            if (r0 >= r10) goto L_0x00ad
            if (r0 <= 0) goto L_0x00ad
            r1.setWidth(r0)
            r1.mWidthMeasuredTooSmall = r6
            goto L_0x00b1
        L_0x00ad:
            int r0 = r18.getWidth()
        L_0x00b1:
            if (r7 != r9) goto L_0x00c5
            int r9 = r18.getHeight()
            if (r4 >= r9) goto L_0x00c1
            if (r4 <= 0) goto L_0x00c1
            r1.setHeight(r4)
            r1.mHeightMeasuredTooSmall = r6
            goto L_0x00c5
        L_0x00c1:
            int r4 = r18.getHeight()
        L_0x00c5:
            r9 = r4
            r4 = r0
            r0 = r6
            goto L_0x00cc
        L_0x00c9:
            r9 = r4
            r4 = r0
            r0 = r2
        L_0x00cc:
            r10 = 64
            boolean r11 = r1.optimizeFor(r10)
            if (r11 != 0) goto L_0x00df
            r11 = 128(0x80, float:1.794E-43)
            boolean r11 = r1.optimizeFor(r11)
            if (r11 == 0) goto L_0x00dd
            goto L_0x00df
        L_0x00dd:
            r11 = r2
            goto L_0x00e0
        L_0x00df:
            r11 = r6
        L_0x00e0:
            androidx.constraintlayout.core.LinearSystem r12 = r1.mSystem
            r12.graphOptimizer = r2
            r12.newgraphOptimizer = r2
            int r13 = r1.mOptimizationLevel
            if (r13 == 0) goto L_0x00ee
            if (r11 == 0) goto L_0x00ee
            r12.newgraphOptimizer = r6
        L_0x00ee:
            java.util.ArrayList r11 = r1.mChildren
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r12 = r18.getHorizontalDimensionBehaviour()
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r13 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r12 == r13) goto L_0x0101
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r12 = r18.getVerticalDimensionBehaviour()
            if (r12 != r13) goto L_0x00ff
            goto L_0x0101
        L_0x00ff:
            r12 = r2
            goto L_0x0102
        L_0x0101:
            r12 = r6
        L_0x0102:
            r18.resetChains()
            r13 = r2
        L_0x0106:
            if (r13 >= r3) goto L_0x011c
            java.util.ArrayList r14 = r1.mChildren
            java.lang.Object r14 = r14.get(r13)
            androidx.constraintlayout.core.widgets.ConstraintWidget r14 = (androidx.constraintlayout.core.widgets.ConstraintWidget) r14
            boolean r15 = r14 instanceof androidx.constraintlayout.core.widgets.WidgetContainer
            if (r15 == 0) goto L_0x0119
            androidx.constraintlayout.core.widgets.WidgetContainer r14 = (androidx.constraintlayout.core.widgets.WidgetContainer) r14
            r14.layout()
        L_0x0119:
            int r13 = r13 + 1
            goto L_0x0106
        L_0x011c:
            boolean r10 = r1.optimizeFor(r10)
            r13 = r0
            r0 = r2
            r14 = r6
        L_0x0123:
            if (r14 == 0) goto L_0x0313
            int r15 = r0 + 1
            androidx.constraintlayout.core.LinearSystem r0 = r1.mSystem     // Catch:{ Exception -> 0x0149 }
            r0.reset()     // Catch:{ Exception -> 0x0149 }
            r18.resetChains()     // Catch:{ Exception -> 0x0149 }
            androidx.constraintlayout.core.LinearSystem r0 = r1.mSystem     // Catch:{ Exception -> 0x0149 }
            r1.createObjectVariables(r0)     // Catch:{ Exception -> 0x0149 }
            r0 = r2
        L_0x0135:
            if (r0 >= r3) goto L_0x014c
            java.util.ArrayList r6 = r1.mChildren     // Catch:{ Exception -> 0x0149 }
            java.lang.Object r6 = r6.get(r0)     // Catch:{ Exception -> 0x0149 }
            androidx.constraintlayout.core.widgets.ConstraintWidget r6 = (androidx.constraintlayout.core.widgets.ConstraintWidget) r6     // Catch:{ Exception -> 0x0149 }
            androidx.constraintlayout.core.LinearSystem r2 = r1.mSystem     // Catch:{ Exception -> 0x0149 }
            r6.createObjectVariables(r2)     // Catch:{ Exception -> 0x0149 }
            int r0 = r0 + 1
            r2 = 0
            r6 = 1
            goto L_0x0135
        L_0x0149:
            r0 = move-exception
            goto L_0x01d7
        L_0x014c:
            androidx.constraintlayout.core.LinearSystem r0 = r1.mSystem     // Catch:{ Exception -> 0x0149 }
            boolean r14 = r1.addChildrenToSolver(r0)     // Catch:{ Exception -> 0x0149 }
            java.lang.ref.WeakReference r0 = r1.verticalWrapMin     // Catch:{ Exception -> 0x0149 }
            r2 = 0
            if (r0 == 0) goto L_0x0172
            java.lang.Object r0 = r0.get()     // Catch:{ Exception -> 0x0149 }
            if (r0 == 0) goto L_0x0172
            java.lang.ref.WeakReference r0 = r1.verticalWrapMin     // Catch:{ Exception -> 0x0149 }
            java.lang.Object r0 = r0.get()     // Catch:{ Exception -> 0x0149 }
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = (androidx.constraintlayout.core.widgets.ConstraintAnchor) r0     // Catch:{ Exception -> 0x0149 }
            androidx.constraintlayout.core.LinearSystem r6 = r1.mSystem     // Catch:{ Exception -> 0x0149 }
            androidx.constraintlayout.core.widgets.ConstraintAnchor r8 = r1.mTop     // Catch:{ Exception -> 0x0149 }
            androidx.constraintlayout.core.SolverVariable r6 = r6.createObjectVariable(r8)     // Catch:{ Exception -> 0x0149 }
            r1.addMinWrap(r0, r6)     // Catch:{ Exception -> 0x0149 }
            r1.verticalWrapMin = r2     // Catch:{ Exception -> 0x0149 }
        L_0x0172:
            java.lang.ref.WeakReference r0 = r1.verticalWrapMax     // Catch:{ Exception -> 0x0149 }
            if (r0 == 0) goto L_0x0191
            java.lang.Object r0 = r0.get()     // Catch:{ Exception -> 0x0149 }
            if (r0 == 0) goto L_0x0191
            java.lang.ref.WeakReference r0 = r1.verticalWrapMax     // Catch:{ Exception -> 0x0149 }
            java.lang.Object r0 = r0.get()     // Catch:{ Exception -> 0x0149 }
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = (androidx.constraintlayout.core.widgets.ConstraintAnchor) r0     // Catch:{ Exception -> 0x0149 }
            androidx.constraintlayout.core.LinearSystem r6 = r1.mSystem     // Catch:{ Exception -> 0x0149 }
            androidx.constraintlayout.core.widgets.ConstraintAnchor r8 = r1.mBottom     // Catch:{ Exception -> 0x0149 }
            androidx.constraintlayout.core.SolverVariable r6 = r6.createObjectVariable(r8)     // Catch:{ Exception -> 0x0149 }
            r1.addMaxWrap(r0, r6)     // Catch:{ Exception -> 0x0149 }
            r1.verticalWrapMax = r2     // Catch:{ Exception -> 0x0149 }
        L_0x0191:
            java.lang.ref.WeakReference r0 = r1.horizontalWrapMin     // Catch:{ Exception -> 0x0149 }
            if (r0 == 0) goto L_0x01b0
            java.lang.Object r0 = r0.get()     // Catch:{ Exception -> 0x0149 }
            if (r0 == 0) goto L_0x01b0
            java.lang.ref.WeakReference r0 = r1.horizontalWrapMin     // Catch:{ Exception -> 0x0149 }
            java.lang.Object r0 = r0.get()     // Catch:{ Exception -> 0x0149 }
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = (androidx.constraintlayout.core.widgets.ConstraintAnchor) r0     // Catch:{ Exception -> 0x0149 }
            androidx.constraintlayout.core.LinearSystem r6 = r1.mSystem     // Catch:{ Exception -> 0x0149 }
            androidx.constraintlayout.core.widgets.ConstraintAnchor r8 = r1.mLeft     // Catch:{ Exception -> 0x0149 }
            androidx.constraintlayout.core.SolverVariable r6 = r6.createObjectVariable(r8)     // Catch:{ Exception -> 0x0149 }
            r1.addMinWrap(r0, r6)     // Catch:{ Exception -> 0x0149 }
            r1.horizontalWrapMin = r2     // Catch:{ Exception -> 0x0149 }
        L_0x01b0:
            java.lang.ref.WeakReference r0 = r1.horizontalWrapMax     // Catch:{ Exception -> 0x0149 }
            if (r0 == 0) goto L_0x01cf
            java.lang.Object r0 = r0.get()     // Catch:{ Exception -> 0x0149 }
            if (r0 == 0) goto L_0x01cf
            java.lang.ref.WeakReference r0 = r1.horizontalWrapMax     // Catch:{ Exception -> 0x0149 }
            java.lang.Object r0 = r0.get()     // Catch:{ Exception -> 0x0149 }
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = (androidx.constraintlayout.core.widgets.ConstraintAnchor) r0     // Catch:{ Exception -> 0x0149 }
            androidx.constraintlayout.core.LinearSystem r6 = r1.mSystem     // Catch:{ Exception -> 0x0149 }
            androidx.constraintlayout.core.widgets.ConstraintAnchor r8 = r1.mRight     // Catch:{ Exception -> 0x0149 }
            androidx.constraintlayout.core.SolverVariable r6 = r6.createObjectVariable(r8)     // Catch:{ Exception -> 0x0149 }
            r1.addMaxWrap(r0, r6)     // Catch:{ Exception -> 0x0149 }
            r1.horizontalWrapMax = r2     // Catch:{ Exception -> 0x0149 }
        L_0x01cf:
            if (r14 == 0) goto L_0x01f0
            androidx.constraintlayout.core.LinearSystem r0 = r1.mSystem     // Catch:{ Exception -> 0x0149 }
            r0.minimize()     // Catch:{ Exception -> 0x0149 }
            goto L_0x01f0
        L_0x01d7:
            r0.printStackTrace()
            java.io.PrintStream r2 = java.lang.System.out
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = "EXCEPTION : "
            r6.append(r8)
            r6.append(r0)
            java.lang.String r0 = r6.toString()
            r2.println(r0)
        L_0x01f0:
            if (r14 == 0) goto L_0x01fb
            androidx.constraintlayout.core.LinearSystem r0 = r1.mSystem
            boolean[] r2 = androidx.constraintlayout.core.widgets.Optimizer.flags
            boolean r0 = r1.updateChildrenFromSolver(r0, r2)
            goto L_0x0214
        L_0x01fb:
            androidx.constraintlayout.core.LinearSystem r0 = r1.mSystem
            r1.updateFromSolver(r0, r10)
            r0 = 0
        L_0x0201:
            if (r0 >= r3) goto L_0x0213
            java.util.ArrayList r2 = r1.mChildren
            java.lang.Object r2 = r2.get(r0)
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = (androidx.constraintlayout.core.widgets.ConstraintWidget) r2
            androidx.constraintlayout.core.LinearSystem r6 = r1.mSystem
            r2.updateFromSolver(r6, r10)
            int r0 = r0 + 1
            goto L_0x0201
        L_0x0213:
            r0 = 0
        L_0x0214:
            r2 = 8
            if (r12 == 0) goto L_0x0285
            if (r15 >= r2) goto L_0x0285
            boolean[] r6 = androidx.constraintlayout.core.widgets.Optimizer.flags
            r8 = 2
            boolean r6 = r6[r8]
            if (r6 == 0) goto L_0x0285
            r6 = 0
            r8 = 0
            r14 = 0
        L_0x0224:
            if (r6 >= r3) goto L_0x024e
            java.util.ArrayList r2 = r1.mChildren
            java.lang.Object r2 = r2.get(r6)
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = (androidx.constraintlayout.core.widgets.ConstraintWidget) r2
            r16 = r0
            int r0 = r2.mX
            int r17 = r2.getWidth()
            int r0 = r0 + r17
            int r14 = java.lang.Math.max(r14, r0)
            int r0 = r2.mY
            int r2 = r2.getHeight()
            int r0 = r0 + r2
            int r8 = java.lang.Math.max(r8, r0)
            int r6 = r6 + 1
            r0 = r16
            r2 = 8
            goto L_0x0224
        L_0x024e:
            r16 = r0
            int r0 = r1.mMinWidth
            int r0 = java.lang.Math.max(r0, r14)
            int r2 = r1.mMinHeight
            int r2 = java.lang.Math.max(r2, r8)
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r5 != r6) goto L_0x0271
            int r8 = r18.getWidth()
            if (r8 >= r0) goto L_0x0271
            r1.setWidth(r0)
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            r8 = 0
            r0[r8] = r6
            r13 = 1
            r16 = 1
        L_0x0271:
            if (r7 != r6) goto L_0x0287
            int r0 = r18.getHeight()
            if (r0 >= r2) goto L_0x0287
            r1.setHeight(r2)
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            r2 = 1
            r0[r2] = r6
            r13 = 1
            r16 = 1
            goto L_0x0287
        L_0x0285:
            r16 = r0
        L_0x0287:
            int r0 = r1.mMinWidth
            int r2 = r18.getWidth()
            int r0 = java.lang.Math.max(r0, r2)
            int r2 = r18.getWidth()
            if (r0 <= r2) goto L_0x02a4
            r1.setWidth(r0)
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r6 = 0
            r0[r6] = r2
            r13 = 1
            r16 = 1
        L_0x02a4:
            int r0 = r1.mMinHeight
            int r2 = r18.getHeight()
            int r0 = java.lang.Math.max(r0, r2)
            int r2 = r18.getHeight()
            if (r0 <= r2) goto L_0x02c2
            r1.setHeight(r0)
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r6 = 1
            r0[r6] = r2
            r2 = r6
            r16 = r2
            goto L_0x02c4
        L_0x02c2:
            r6 = 1
            r2 = r13
        L_0x02c4:
            if (r2 != 0) goto L_0x0303
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            r8 = 0
            r0 = r0[r8]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r13 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r0 != r13) goto L_0x02e5
            if (r4 <= 0) goto L_0x02e5
            int r0 = r18.getWidth()
            if (r0 <= r4) goto L_0x02e5
            r1.mWidthMeasuredTooSmall = r6
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r0[r8] = r2
            r1.setWidth(r4)
            r2 = r6
            r16 = r2
        L_0x02e5:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            r0 = r0[r6]
            if (r0 != r13) goto L_0x0303
            if (r9 <= 0) goto L_0x0303
            int r0 = r18.getHeight()
            if (r0 <= r9) goto L_0x0303
            r1.mHeightMeasuredTooSmall = r6
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r0[r6] = r2
            r1.setHeight(r9)
            r0 = 8
            r2 = 1
            r13 = 1
            goto L_0x0308
        L_0x0303:
            r13 = r2
            r2 = r16
            r0 = 8
        L_0x0308:
            if (r15 <= r0) goto L_0x030c
            r14 = 0
            goto L_0x030d
        L_0x030c:
            r14 = r2
        L_0x030d:
            r0 = r15
            r2 = 0
            r6 = 1
            r8 = 2
            goto L_0x0123
        L_0x0313:
            r1.mChildren = r11
            if (r13 == 0) goto L_0x031f
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            r2 = 0
            r0[r2] = r5
            r2 = 1
            r0[r2] = r7
        L_0x031f:
            androidx.constraintlayout.core.LinearSystem r0 = r1.mSystem
            androidx.constraintlayout.core.Cache r0 = r0.getCache()
            r1.resetSolverVariables(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.ConstraintWidgetContainer.layout():void");
    }

    public LinearSystem getSystem() {
        return this.mSystem;
    }

    private void resetChains() {
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
    }

    /* access modifiers changed from: package-private */
    public void addChain(ConstraintWidget constraintWidget, int i) {
        if (i == 0) {
            addHorizontalChain(constraintWidget);
        } else if (i == 1) {
            addVerticalChain(constraintWidget);
        }
    }

    private void addHorizontalChain(ConstraintWidget constraintWidget) {
        int i = this.mHorizontalChainsSize + 1;
        ChainHead[] chainHeadArr = this.mHorizontalChainsArray;
        if (i >= chainHeadArr.length) {
            this.mHorizontalChainsArray = (ChainHead[]) Arrays.copyOf(chainHeadArr, chainHeadArr.length * 2);
        }
        this.mHorizontalChainsArray[this.mHorizontalChainsSize] = new ChainHead(constraintWidget, 0, isRtl());
        this.mHorizontalChainsSize++;
    }

    private void addVerticalChain(ConstraintWidget constraintWidget) {
        int i = this.mVerticalChainsSize + 1;
        ChainHead[] chainHeadArr = this.mVerticalChainsArray;
        if (i >= chainHeadArr.length) {
            this.mVerticalChainsArray = (ChainHead[]) Arrays.copyOf(chainHeadArr, chainHeadArr.length * 2);
        }
        this.mVerticalChainsArray[this.mVerticalChainsSize] = new ChainHead(constraintWidget, 1, isRtl());
        this.mVerticalChainsSize++;
    }

    public void setPass(int i) {
        this.pass = i;
    }

    public void getSceneString(StringBuilder sb) {
        sb.append(this.stringId + ":{\n");
        sb.append("  actualWidth:" + this.mWidth);
        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        sb.append("  actualHeight:" + this.mHeight);
        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        Iterator it = getChildren().iterator();
        while (it.hasNext()) {
            ((ConstraintWidget) it.next()).getSceneString(sb);
            sb.append(",\n");
        }
        sb.append("}");
    }
}
