package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.Helper;
import androidx.constraintlayout.core.widgets.Optimizer;
import java.util.ArrayList;

public class BasicMeasure {
    private ConstraintWidgetContainer constraintWidgetContainer;
    private Measure mMeasure = new Measure();
    private final ArrayList mVariableDimensionsWidgets = new ArrayList();

    public static class Measure {
        public static int SELF_DIMENSIONS = 0;
        public static int TRY_GIVEN_DIMENSIONS = 1;
        public static int USE_GIVEN_DIMENSIONS = 2;
        public ConstraintWidget.DimensionBehaviour horizontalBehavior;
        public int horizontalDimension;
        public int measureStrategy;
        public int measuredBaseline;
        public boolean measuredHasBaseline;
        public int measuredHeight;
        public boolean measuredNeedsSolverPass;
        public int measuredWidth;
        public ConstraintWidget.DimensionBehaviour verticalBehavior;
        public int verticalDimension;
    }

    public interface Measurer {
        void didMeasures();

        void measure(ConstraintWidget constraintWidget, Measure measure);
    }

    public void updateHierarchy(ConstraintWidgetContainer constraintWidgetContainer2) {
        this.mVariableDimensionsWidgets.clear();
        int size = constraintWidgetContainer2.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) constraintWidgetContainer2.mChildren.get(i);
            ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = constraintWidget.getHorizontalDimensionBehaviour();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
            if (horizontalDimensionBehaviour == dimensionBehaviour || constraintWidget.getVerticalDimensionBehaviour() == dimensionBehaviour) {
                this.mVariableDimensionsWidgets.add(constraintWidget);
            }
        }
        constraintWidgetContainer2.invalidateGraph();
    }

    public BasicMeasure(ConstraintWidgetContainer constraintWidgetContainer2) {
        this.constraintWidgetContainer = constraintWidgetContainer2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0089, code lost:
        if (r8 != r9) goto L_0x0093;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0090, code lost:
        if (r5.mDimensionRatio <= 0.0f) goto L_0x0093;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void measureChildren(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r13) {
        /*
            r12 = this;
            java.util.ArrayList r0 = r13.mChildren
            int r0 = r0.size()
            r1 = 64
            boolean r1 = r13.optimizeFor(r1)
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer r2 = r13.getMeasurer()
            r3 = 0
            r4 = r3
        L_0x0012:
            if (r4 >= r0) goto L_0x00a0
            java.util.ArrayList r5 = r13.mChildren
            java.lang.Object r5 = r5.get(r4)
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = (androidx.constraintlayout.core.widgets.ConstraintWidget) r5
            boolean r6 = r5 instanceof androidx.constraintlayout.core.widgets.Guideline
            if (r6 == 0) goto L_0x0022
            goto L_0x009c
        L_0x0022:
            boolean r6 = r5 instanceof androidx.constraintlayout.core.widgets.Barrier
            if (r6 == 0) goto L_0x0028
            goto L_0x009c
        L_0x0028:
            boolean r6 = r5.isInVirtualLayout()
            if (r6 == 0) goto L_0x0030
            goto L_0x009c
        L_0x0030:
            if (r1 == 0) goto L_0x0047
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r6 = r5.horizontalRun
            if (r6 == 0) goto L_0x0047
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r7 = r5.verticalRun
            if (r7 == 0) goto L_0x0047
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r6 = r6.dimension
            boolean r6 = r6.resolved
            if (r6 == 0) goto L_0x0047
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r6 = r7.dimension
            boolean r6 = r6.resolved
            if (r6 == 0) goto L_0x0047
            goto L_0x009c
        L_0x0047:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r6 = r5.getDimensionBehaviour(r3)
            r7 = 1
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r8 = r5.getDimensionBehaviour(r7)
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r6 != r9) goto L_0x0060
            int r10 = r5.mMatchConstraintDefaultWidth
            if (r10 == r7) goto L_0x0060
            if (r8 != r9) goto L_0x0060
            int r10 = r5.mMatchConstraintDefaultHeight
            if (r10 == r7) goto L_0x0060
            r10 = r7
            goto L_0x0061
        L_0x0060:
            r10 = r3
        L_0x0061:
            if (r10 != 0) goto L_0x0093
            boolean r11 = r13.optimizeFor(r7)
            if (r11 == 0) goto L_0x0093
            if (r6 != r9) goto L_0x0078
            int r11 = r5.mMatchConstraintDefaultWidth
            if (r11 != 0) goto L_0x0078
            if (r8 == r9) goto L_0x0078
            boolean r11 = r5.isInHorizontalChain()
            if (r11 != 0) goto L_0x0078
            r10 = r7
        L_0x0078:
            if (r8 != r9) goto L_0x0087
            int r11 = r5.mMatchConstraintDefaultHeight
            if (r11 != 0) goto L_0x0087
            if (r6 == r9) goto L_0x0087
            boolean r11 = r5.isInHorizontalChain()
            if (r11 != 0) goto L_0x0087
            r10 = r7
        L_0x0087:
            if (r6 == r9) goto L_0x008b
            if (r8 != r9) goto L_0x0093
        L_0x008b:
            float r6 = r5.mDimensionRatio
            r8 = 0
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 <= 0) goto L_0x0093
            goto L_0x0094
        L_0x0093:
            r7 = r10
        L_0x0094:
            if (r7 == 0) goto L_0x0097
            goto L_0x009c
        L_0x0097:
            int r6 = androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measure.SELF_DIMENSIONS
            r12.measure(r2, r5, r6)
        L_0x009c:
            int r4 = r4 + 1
            goto L_0x0012
        L_0x00a0:
            r2.didMeasures()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.measureChildren(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer):void");
    }

    private void solveLinearSystem(ConstraintWidgetContainer constraintWidgetContainer2, String str, int i, int i2, int i3) {
        int minWidth = constraintWidgetContainer2.getMinWidth();
        int minHeight = constraintWidgetContainer2.getMinHeight();
        constraintWidgetContainer2.setMinWidth(0);
        constraintWidgetContainer2.setMinHeight(0);
        constraintWidgetContainer2.setWidth(i2);
        constraintWidgetContainer2.setHeight(i3);
        constraintWidgetContainer2.setMinWidth(minWidth);
        constraintWidgetContainer2.setMinHeight(minHeight);
        this.constraintWidgetContainer.setPass(i);
        this.constraintWidgetContainer.layout();
    }

    public long solverMeasure(ConstraintWidgetContainer constraintWidgetContainer2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        int i10;
        boolean z;
        boolean z2;
        int i11;
        boolean z3;
        int i12;
        boolean z4;
        BasicMeasure basicMeasure = this;
        ConstraintWidgetContainer constraintWidgetContainer3 = constraintWidgetContainer2;
        int i13 = i;
        int i14 = i4;
        int i15 = i6;
        Measurer measurer = constraintWidgetContainer2.getMeasurer();
        int size = constraintWidgetContainer3.mChildren.size();
        int width = constraintWidgetContainer2.getWidth();
        int height = constraintWidgetContainer2.getHeight();
        boolean enabled = Optimizer.enabled(i13, 128);
        int i16 = 1;
        boolean z5 = enabled || Optimizer.enabled(i13, 64);
        if (z5) {
            int i17 = 0;
            while (true) {
                if (i17 >= size) {
                    break;
                }
                ConstraintWidget constraintWidget = (ConstraintWidget) constraintWidgetContainer3.mChildren.get(i17);
                ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = constraintWidget.getHorizontalDimensionBehaviour();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                boolean z6 = (horizontalDimensionBehaviour == dimensionBehaviour) && (constraintWidget.getVerticalDimensionBehaviour() == dimensionBehaviour) && constraintWidget.getDimensionRatio() > 0.0f;
                if ((!constraintWidget.isInHorizontalChain() || !z6) && ((!constraintWidget.isInVerticalChain() || !z6) && !constraintWidget.isInHorizontalChain() && !constraintWidget.isInVerticalChain())) {
                    i17++;
                }
            }
            z5 = false;
        }
        if (z5) {
            boolean z7 = LinearSystem.USE_DEPENDENCY_ORDERING;
        }
        boolean z8 = z5 & ((i14 == 1073741824 && i15 == 1073741824) || enabled);
        int i18 = 2;
        if (z8) {
            int min = Math.min(constraintWidgetContainer2.getMaxWidth(), i5);
            int min2 = Math.min(constraintWidgetContainer2.getMaxHeight(), i7);
            if (i14 == 1073741824 && constraintWidgetContainer2.getWidth() != min) {
                constraintWidgetContainer3.setWidth(min);
                constraintWidgetContainer2.invalidateGraph();
            }
            if (i15 == 1073741824 && constraintWidgetContainer2.getHeight() != min2) {
                constraintWidgetContainer3.setHeight(min2);
                constraintWidgetContainer2.invalidateGraph();
            }
            if (i14 == 1073741824 && i15 == 1073741824) {
                z2 = constraintWidgetContainer3.directMeasure(enabled);
                i10 = 2;
                z = false;
            } else {
                boolean directMeasureSetup = constraintWidgetContainer3.directMeasureSetup(enabled);
                z = false;
                if (i14 == 1073741824) {
                    directMeasureSetup &= constraintWidgetContainer3.directMeasureWithOrientation(enabled, 0);
                    i10 = 1;
                } else {
                    i10 = 0;
                }
                if (i15 == 1073741824) {
                    z2 = constraintWidgetContainer3.directMeasureWithOrientation(enabled, 1) & directMeasureSetup;
                    i10++;
                } else {
                    z2 = directMeasureSetup;
                }
            }
            if (z2) {
                constraintWidgetContainer3.updateFromRuns(i14 == 1073741824 ? true : z, i15 == 1073741824 ? true : z);
            }
        } else {
            z = false;
            z2 = false;
            i10 = 0;
        }
        if (z2 && i10 == 2) {
            return 0;
        }
        int optimizationLevel = constraintWidgetContainer2.getOptimizationLevel();
        if (size > 0) {
            measureChildren(constraintWidgetContainer2);
        }
        updateHierarchy(constraintWidgetContainer2);
        int size2 = basicMeasure.mVariableDimensionsWidgets.size();
        if (size > 0) {
            solveLinearSystem(constraintWidgetContainer2, "First pass", 0, width, height);
        }
        if (size2 > 0) {
            ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour2 = constraintWidgetContainer2.getHorizontalDimensionBehaviour();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            boolean z9 = horizontalDimensionBehaviour2 == dimensionBehaviour2 ? true : z;
            boolean z10 = constraintWidgetContainer2.getVerticalDimensionBehaviour() == dimensionBehaviour2 ? true : z;
            int max = Math.max(constraintWidgetContainer2.getWidth(), basicMeasure.constraintWidgetContainer.getMinWidth());
            int max2 = Math.max(constraintWidgetContainer2.getHeight(), basicMeasure.constraintWidgetContainer.getMinHeight());
            for (int i19 = z; i19 < size2; i19++) {
                ConstraintWidget constraintWidget2 = (ConstraintWidget) basicMeasure.mVariableDimensionsWidgets.get(i19);
            }
            int i20 = 0;
            while (i20 < i18) {
                int i21 = 0;
                boolean z11 = false;
                while (i21 < size2) {
                    ConstraintWidget constraintWidget3 = (ConstraintWidget) basicMeasure.mVariableDimensionsWidgets.get(i21);
                    if (!(constraintWidget3 instanceof Helper) && !(constraintWidget3 instanceof Guideline)) {
                        i11 = size2;
                        if (constraintWidget3.getVisibility() != 8 && (!z8 || !constraintWidget3.horizontalRun.dimension.resolved || !constraintWidget3.verticalRun.dimension.resolved)) {
                            int width2 = constraintWidget3.getWidth();
                            int height2 = constraintWidget3.getHeight();
                            z3 = z8;
                            int baselineDistance = constraintWidget3.getBaselineDistance();
                            int i22 = Measure.TRY_GIVEN_DIMENSIONS;
                            if (i20 == 1) {
                                i22 = Measure.USE_GIVEN_DIMENSIONS;
                            }
                            int width3 = constraintWidget3.getWidth();
                            boolean measure = z11 | basicMeasure.measure(measurer, constraintWidget3, i22);
                            int height3 = constraintWidget3.getHeight();
                            if (width3 != width2) {
                                constraintWidget3.setWidth(width3);
                                if (z9 && constraintWidget3.getRight() > max) {
                                    max = Math.max(max, constraintWidget3.getRight() + constraintWidget3.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin());
                                }
                                z4 = true;
                            } else {
                                z4 = measure;
                            }
                            if (height3 != height2) {
                                constraintWidget3.setHeight(height3);
                                if (z10 && constraintWidget3.getBottom() > max2) {
                                    max2 = Math.max(max2, constraintWidget3.getBottom() + constraintWidget3.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin());
                                }
                                z11 = true;
                            } else {
                                z11 = z4;
                            }
                            if (constraintWidget3.hasBaseline() && baselineDistance != constraintWidget3.getBaselineDistance()) {
                                i12 = 1;
                                z11 = true;
                                i21 += i12;
                                ConstraintWidgetContainer constraintWidgetContainer4 = constraintWidgetContainer2;
                                size2 = i11;
                                i16 = i12;
                                z8 = z3;
                                basicMeasure = this;
                            }
                            i12 = 1;
                            i21 += i12;
                            ConstraintWidgetContainer constraintWidgetContainer42 = constraintWidgetContainer2;
                            size2 = i11;
                            i16 = i12;
                            z8 = z3;
                            basicMeasure = this;
                        }
                    } else {
                        i11 = size2;
                    }
                    z3 = z8;
                    i12 = 1;
                    i21 += i12;
                    ConstraintWidgetContainer constraintWidgetContainer422 = constraintWidgetContainer2;
                    size2 = i11;
                    i16 = i12;
                    z8 = z3;
                    basicMeasure = this;
                }
                boolean z12 = z8;
                int i23 = size2;
                int i24 = i16;
                if (!z11) {
                    break;
                }
                i20 += i24;
                solveLinearSystem(constraintWidgetContainer2, "intermediate pass", i20, width, height);
                ConstraintWidgetContainer constraintWidgetContainer5 = constraintWidgetContainer2;
                size2 = i23;
                i16 = i24;
                z8 = z12;
                i18 = 2;
                basicMeasure = this;
            }
        }
        constraintWidgetContainer2.setOptimizationLevel(optimizationLevel);
        return 0;
    }

    private boolean measure(Measurer measurer, ConstraintWidget constraintWidget, int i) {
        this.mMeasure.horizontalBehavior = constraintWidget.getHorizontalDimensionBehaviour();
        this.mMeasure.verticalBehavior = constraintWidget.getVerticalDimensionBehaviour();
        this.mMeasure.horizontalDimension = constraintWidget.getWidth();
        this.mMeasure.verticalDimension = constraintWidget.getHeight();
        Measure measure = this.mMeasure;
        measure.measuredNeedsSolverPass = false;
        measure.measureStrategy = i;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = measure.horizontalBehavior;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z = dimensionBehaviour == dimensionBehaviour2;
        boolean z2 = measure.verticalBehavior == dimensionBehaviour2;
        boolean z3 = z && constraintWidget.mDimensionRatio > 0.0f;
        boolean z4 = z2 && constraintWidget.mDimensionRatio > 0.0f;
        if (z3 && constraintWidget.mResolvedMatchConstraintDefault[0] == 4) {
            measure.horizontalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        if (z4 && constraintWidget.mResolvedMatchConstraintDefault[1] == 4) {
            measure.verticalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        measurer.measure(constraintWidget, measure);
        constraintWidget.setWidth(this.mMeasure.measuredWidth);
        constraintWidget.setHeight(this.mMeasure.measuredHeight);
        constraintWidget.setHasBaseline(this.mMeasure.measuredHasBaseline);
        constraintWidget.setBaselineDistance(this.mMeasure.measuredBaseline);
        Measure measure2 = this.mMeasure;
        measure2.measureStrategy = Measure.SELF_DIMENSIONS;
        return measure2.measuredNeedsSolverPass;
    }
}
