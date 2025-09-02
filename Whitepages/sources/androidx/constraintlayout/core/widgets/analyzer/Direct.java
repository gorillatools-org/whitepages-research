package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.Barrier;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import java.util.ArrayList;

public abstract class Direct {
    private static int hcount = 0;
    private static BasicMeasure.Measure measure = new BasicMeasure.Measure();
    private static int vcount = 0;

    public static void solvingPass(ConstraintWidgetContainer constraintWidgetContainer, BasicMeasure.Measurer measurer) {
        ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = constraintWidgetContainer.getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour verticalDimensionBehaviour = constraintWidgetContainer.getVerticalDimensionBehaviour();
        hcount = 0;
        vcount = 0;
        constraintWidgetContainer.resetFinalResolution();
        ArrayList children = constraintWidgetContainer.getChildren();
        int size = children.size();
        for (int i = 0; i < size; i++) {
            ((ConstraintWidget) children.get(i)).resetFinalResolution();
        }
        boolean isRtl = constraintWidgetContainer.isRtl();
        if (horizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.FIXED) {
            constraintWidgetContainer.setFinalHorizontal(0, constraintWidgetContainer.getWidth());
        } else {
            constraintWidgetContainer.setFinalLeft(0);
        }
        boolean z = false;
        boolean z2 = false;
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) children.get(i2);
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                if (guideline.getOrientation() == 1) {
                    if (guideline.getRelativeBegin() != -1) {
                        guideline.setFinalValue(guideline.getRelativeBegin());
                    } else if (guideline.getRelativeEnd() != -1 && constraintWidgetContainer.isResolvedHorizontally()) {
                        guideline.setFinalValue(constraintWidgetContainer.getWidth() - guideline.getRelativeEnd());
                    } else if (constraintWidgetContainer.isResolvedHorizontally()) {
                        guideline.setFinalValue((int) ((guideline.getRelativePercent() * ((float) constraintWidgetContainer.getWidth())) + 0.5f));
                    }
                    z = true;
                }
            } else if ((constraintWidget instanceof Barrier) && ((Barrier) constraintWidget).getOrientation() == 0) {
                z2 = true;
            }
        }
        if (z) {
            for (int i3 = 0; i3 < size; i3++) {
                ConstraintWidget constraintWidget2 = (ConstraintWidget) children.get(i3);
                if (constraintWidget2 instanceof Guideline) {
                    Guideline guideline2 = (Guideline) constraintWidget2;
                    if (guideline2.getOrientation() == 1) {
                        horizontalSolvingPass(0, guideline2, measurer, isRtl);
                    }
                }
            }
        }
        horizontalSolvingPass(0, constraintWidgetContainer, measurer, isRtl);
        if (z2) {
            for (int i4 = 0; i4 < size; i4++) {
                ConstraintWidget constraintWidget3 = (ConstraintWidget) children.get(i4);
                if (constraintWidget3 instanceof Barrier) {
                    Barrier barrier = (Barrier) constraintWidget3;
                    if (barrier.getOrientation() == 0) {
                        solveBarrier(0, barrier, measurer, 0, isRtl);
                    }
                }
            }
        }
        if (verticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.FIXED) {
            constraintWidgetContainer.setFinalVertical(0, constraintWidgetContainer.getHeight());
        } else {
            constraintWidgetContainer.setFinalTop(0);
        }
        boolean z3 = false;
        boolean z4 = false;
        for (int i5 = 0; i5 < size; i5++) {
            ConstraintWidget constraintWidget4 = (ConstraintWidget) children.get(i5);
            if (constraintWidget4 instanceof Guideline) {
                Guideline guideline3 = (Guideline) constraintWidget4;
                if (guideline3.getOrientation() == 0) {
                    if (guideline3.getRelativeBegin() != -1) {
                        guideline3.setFinalValue(guideline3.getRelativeBegin());
                    } else if (guideline3.getRelativeEnd() != -1 && constraintWidgetContainer.isResolvedVertically()) {
                        guideline3.setFinalValue(constraintWidgetContainer.getHeight() - guideline3.getRelativeEnd());
                    } else if (constraintWidgetContainer.isResolvedVertically()) {
                        guideline3.setFinalValue((int) ((guideline3.getRelativePercent() * ((float) constraintWidgetContainer.getHeight())) + 0.5f));
                    }
                    z3 = true;
                }
            } else if ((constraintWidget4 instanceof Barrier) && ((Barrier) constraintWidget4).getOrientation() == 1) {
                z4 = true;
            }
        }
        if (z3) {
            for (int i6 = 0; i6 < size; i6++) {
                ConstraintWidget constraintWidget5 = (ConstraintWidget) children.get(i6);
                if (constraintWidget5 instanceof Guideline) {
                    Guideline guideline4 = (Guideline) constraintWidget5;
                    if (guideline4.getOrientation() == 0) {
                        verticalSolvingPass(1, guideline4, measurer);
                    }
                }
            }
        }
        verticalSolvingPass(0, constraintWidgetContainer, measurer);
        if (z4) {
            for (int i7 = 0; i7 < size; i7++) {
                ConstraintWidget constraintWidget6 = (ConstraintWidget) children.get(i7);
                if (constraintWidget6 instanceof Barrier) {
                    Barrier barrier2 = (Barrier) constraintWidget6;
                    if (barrier2.getOrientation() == 1) {
                        solveBarrier(0, barrier2, measurer, 1, isRtl);
                    }
                }
            }
        }
        for (int i8 = 0; i8 < size; i8++) {
            ConstraintWidget constraintWidget7 = (ConstraintWidget) children.get(i8);
            if (constraintWidget7.isMeasureRequested() && canMeasure(0, constraintWidget7)) {
                ConstraintWidgetContainer.measure(0, constraintWidget7, measurer, measure, BasicMeasure.Measure.SELF_DIMENSIONS);
                if (!(constraintWidget7 instanceof Guideline)) {
                    horizontalSolvingPass(0, constraintWidget7, measurer, isRtl);
                    verticalSolvingPass(0, constraintWidget7, measurer);
                } else if (((Guideline) constraintWidget7).getOrientation() == 0) {
                    verticalSolvingPass(0, constraintWidget7, measurer);
                } else {
                    horizontalSolvingPass(0, constraintWidget7, measurer, isRtl);
                }
            }
        }
    }

    private static void solveBarrier(int i, Barrier barrier, BasicMeasure.Measurer measurer, int i2, boolean z) {
        if (!barrier.allSolved()) {
            return;
        }
        if (i2 == 0) {
            horizontalSolvingPass(i + 1, barrier, measurer, z);
        } else {
            verticalSolvingPass(i + 1, barrier, measurer);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0084, code lost:
        r11 = r12.mRight.mTarget;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0094, code lost:
        r11 = r12.mLeft.mTarget;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void horizontalSolvingPass(int r16, androidx.constraintlayout.core.widgets.ConstraintWidget r17, androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measurer r18, boolean r19) {
        /*
            r0 = r17
            r1 = r18
            r2 = r19
            boolean r3 = r17.isHorizontalSolvingPassDone()
            if (r3 == 0) goto L_0x000d
            return
        L_0x000d:
            int r3 = hcount
            r4 = 1
            int r3 = r3 + r4
            hcount = r3
            boolean r3 = r0 instanceof androidx.constraintlayout.core.widgets.ConstraintWidgetContainer
            if (r3 != 0) goto L_0x002f
            boolean r3 = r17.isMeasureRequested()
            if (r3 == 0) goto L_0x002f
            int r3 = r16 + 1
            boolean r5 = canMeasure(r3, r0)
            if (r5 == 0) goto L_0x002f
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure r5 = new androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure
            r5.<init>()
            int r6 = androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measure.SELF_DIMENSIONS
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer.measure(r3, r0, r1, r5, r6)
        L_0x002f:
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r3 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.LEFT
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r0.getAnchor(r3)
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r5 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.RIGHT
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = r0.getAnchor(r5)
            int r6 = r3.getFinalValue()
            int r7 = r5.getFinalValue()
            java.util.HashSet r8 = r3.getDependents()
            r9 = 0
            r10 = 8
            if (r8 == 0) goto L_0x0131
            boolean r8 = r3.hasFinalValue()
            if (r8 == 0) goto L_0x0131
            java.util.HashSet r3 = r3.getDependents()
            java.util.Iterator r3 = r3.iterator()
        L_0x005a:
            boolean r8 = r3.hasNext()
            if (r8 == 0) goto L_0x0131
            java.lang.Object r8 = r3.next()
            androidx.constraintlayout.core.widgets.ConstraintAnchor r8 = (androidx.constraintlayout.core.widgets.ConstraintAnchor) r8
            androidx.constraintlayout.core.widgets.ConstraintWidget r12 = r8.mOwner
            int r13 = r16 + 1
            boolean r14 = canMeasure(r13, r12)
            boolean r15 = r12.isMeasureRequested()
            if (r15 == 0) goto L_0x0080
            if (r14 == 0) goto L_0x0080
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure r15 = new androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure
            r15.<init>()
            int r11 = androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measure.SELF_DIMENSIONS
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer.measure(r13, r12, r1, r15, r11)
        L_0x0080:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r11 = r12.mLeft
            if (r8 != r11) goto L_0x0090
            androidx.constraintlayout.core.widgets.ConstraintAnchor r11 = r12.mRight
            androidx.constraintlayout.core.widgets.ConstraintAnchor r11 = r11.mTarget
            if (r11 == 0) goto L_0x0090
            boolean r11 = r11.hasFinalValue()
            if (r11 != 0) goto L_0x00a0
        L_0x0090:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r11 = r12.mRight
            if (r8 != r11) goto L_0x00a2
            androidx.constraintlayout.core.widgets.ConstraintAnchor r11 = r12.mLeft
            androidx.constraintlayout.core.widgets.ConstraintAnchor r11 = r11.mTarget
            if (r11 == 0) goto L_0x00a2
            boolean r11 = r11.hasFinalValue()
            if (r11 == 0) goto L_0x00a2
        L_0x00a0:
            r11 = r4
            goto L_0x00a3
        L_0x00a2:
            r11 = 0
        L_0x00a3:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r15 = r12.getHorizontalDimensionBehaviour()
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r4 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r15 != r4) goto L_0x00e6
            if (r14 == 0) goto L_0x00ae
            goto L_0x00e6
        L_0x00ae:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r8 = r12.getHorizontalDimensionBehaviour()
            if (r8 != r4) goto L_0x00ec
            int r4 = r12.mMatchConstraintMaxWidth
            if (r4 < 0) goto L_0x00ec
            int r4 = r12.mMatchConstraintMinWidth
            if (r4 < 0) goto L_0x00ec
            int r4 = r12.getVisibility()
            if (r4 == r10) goto L_0x00ce
            int r4 = r12.mMatchConstraintDefaultWidth
            if (r4 != 0) goto L_0x00ec
            float r4 = r12.getDimensionRatio()
            int r4 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r4 != 0) goto L_0x00ec
        L_0x00ce:
            boolean r4 = r12.isInHorizontalChain()
            if (r4 != 0) goto L_0x00ec
            boolean r4 = r12.isInVirtualLayout()
            if (r4 != 0) goto L_0x00ec
            if (r11 == 0) goto L_0x00ec
            boolean r4 = r12.isInHorizontalChain()
            if (r4 != 0) goto L_0x00ec
            solveHorizontalMatchConstraint(r13, r0, r1, r12, r2)
            goto L_0x00ec
        L_0x00e6:
            boolean r4 = r12.isMeasureRequested()
            if (r4 == 0) goto L_0x00ef
        L_0x00ec:
            r4 = 1
            goto L_0x005a
        L_0x00ef:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r12.mLeft
            if (r8 != r4) goto L_0x010a
            androidx.constraintlayout.core.widgets.ConstraintAnchor r14 = r12.mRight
            androidx.constraintlayout.core.widgets.ConstraintAnchor r14 = r14.mTarget
            if (r14 != 0) goto L_0x010a
            int r4 = r4.getMargin()
            int r4 = r4 + r6
            int r8 = r12.getWidth()
            int r8 = r8 + r4
            r12.setFinalHorizontal(r4, r8)
            horizontalSolvingPass(r13, r12, r1, r2)
            goto L_0x00ec
        L_0x010a:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r14 = r12.mRight
            if (r8 != r14) goto L_0x0125
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 != 0) goto L_0x0125
            int r4 = r14.getMargin()
            int r4 = r6 - r4
            int r8 = r12.getWidth()
            int r8 = r4 - r8
            r12.setFinalHorizontal(r8, r4)
            horizontalSolvingPass(r13, r12, r1, r2)
            goto L_0x00ec
        L_0x0125:
            if (r11 == 0) goto L_0x00ec
            boolean r4 = r12.isInHorizontalChain()
            if (r4 != 0) goto L_0x00ec
            solveHorizontalCenterConstraints(r13, r1, r12, r2)
            goto L_0x00ec
        L_0x0131:
            boolean r3 = r0 instanceof androidx.constraintlayout.core.widgets.Guideline
            if (r3 == 0) goto L_0x0136
            return
        L_0x0136:
            java.util.HashSet r3 = r5.getDependents()
            if (r3 == 0) goto L_0x0225
            boolean r3 = r5.hasFinalValue()
            if (r3 == 0) goto L_0x0225
            java.util.HashSet r3 = r5.getDependents()
            java.util.Iterator r3 = r3.iterator()
        L_0x014a:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0225
            java.lang.Object r4 = r3.next()
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = (androidx.constraintlayout.core.widgets.ConstraintAnchor) r4
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = r4.mOwner
            r6 = 1
            int r8 = r16 + 1
            boolean r11 = canMeasure(r8, r5)
            boolean r12 = r5.isMeasureRequested()
            if (r12 == 0) goto L_0x0171
            if (r11 == 0) goto L_0x0171
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure r12 = new androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure
            r12.<init>()
            int r13 = androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measure.SELF_DIMENSIONS
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer.measure(r8, r5, r1, r12, r13)
        L_0x0171:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r12 = r5.mLeft
            if (r4 != r12) goto L_0x0181
            androidx.constraintlayout.core.widgets.ConstraintAnchor r12 = r5.mRight
            androidx.constraintlayout.core.widgets.ConstraintAnchor r12 = r12.mTarget
            if (r12 == 0) goto L_0x0181
            boolean r12 = r12.hasFinalValue()
            if (r12 != 0) goto L_0x0191
        L_0x0181:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r12 = r5.mRight
            if (r4 != r12) goto L_0x0193
            androidx.constraintlayout.core.widgets.ConstraintAnchor r12 = r5.mLeft
            androidx.constraintlayout.core.widgets.ConstraintAnchor r12 = r12.mTarget
            if (r12 == 0) goto L_0x0193
            boolean r12 = r12.hasFinalValue()
            if (r12 == 0) goto L_0x0193
        L_0x0191:
            r12 = r6
            goto L_0x0194
        L_0x0193:
            r12 = 0
        L_0x0194:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r13 = r5.getHorizontalDimensionBehaviour()
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r14 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r13 != r14) goto L_0x01d8
            if (r11 == 0) goto L_0x019f
            goto L_0x01d8
        L_0x019f:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r4 = r5.getHorizontalDimensionBehaviour()
            if (r4 != r14) goto L_0x014a
            int r4 = r5.mMatchConstraintMaxWidth
            if (r4 < 0) goto L_0x014a
            int r4 = r5.mMatchConstraintMinWidth
            if (r4 < 0) goto L_0x014a
            int r4 = r5.getVisibility()
            if (r4 == r10) goto L_0x01bf
            int r4 = r5.mMatchConstraintDefaultWidth
            if (r4 != 0) goto L_0x014a
            float r4 = r5.getDimensionRatio()
            int r4 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r4 != 0) goto L_0x014a
        L_0x01bf:
            boolean r4 = r5.isInHorizontalChain()
            if (r4 != 0) goto L_0x014a
            boolean r4 = r5.isInVirtualLayout()
            if (r4 != 0) goto L_0x014a
            if (r12 == 0) goto L_0x014a
            boolean r4 = r5.isInHorizontalChain()
            if (r4 != 0) goto L_0x014a
            solveHorizontalMatchConstraint(r8, r0, r1, r5, r2)
            goto L_0x014a
        L_0x01d8:
            boolean r11 = r5.isMeasureRequested()
            if (r11 == 0) goto L_0x01e0
            goto L_0x014a
        L_0x01e0:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r11 = r5.mLeft
            if (r4 != r11) goto L_0x01fc
            androidx.constraintlayout.core.widgets.ConstraintAnchor r13 = r5.mRight
            androidx.constraintlayout.core.widgets.ConstraintAnchor r13 = r13.mTarget
            if (r13 != 0) goto L_0x01fc
            int r4 = r11.getMargin()
            int r4 = r4 + r7
            int r11 = r5.getWidth()
            int r11 = r11 + r4
            r5.setFinalHorizontal(r4, r11)
            horizontalSolvingPass(r8, r5, r1, r2)
            goto L_0x014a
        L_0x01fc:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r13 = r5.mRight
            if (r4 != r13) goto L_0x0218
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r11.mTarget
            if (r4 != 0) goto L_0x0218
            int r4 = r13.getMargin()
            int r4 = r7 - r4
            int r11 = r5.getWidth()
            int r11 = r4 - r11
            r5.setFinalHorizontal(r11, r4)
            horizontalSolvingPass(r8, r5, r1, r2)
            goto L_0x014a
        L_0x0218:
            if (r12 == 0) goto L_0x014a
            boolean r4 = r5.isInHorizontalChain()
            if (r4 != 0) goto L_0x014a
            solveHorizontalCenterConstraints(r8, r1, r5, r2)
            goto L_0x014a
        L_0x0225:
            r17.markHorizontalSolvingPassDone()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.Direct.horizontalSolvingPass(int, androidx.constraintlayout.core.widgets.ConstraintWidget, androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer, boolean):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0082, code lost:
        r14 = r11.mBottom.mTarget;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0092, code lost:
        r14 = r11.mTop.mTarget;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void verticalSolvingPass(int r16, androidx.constraintlayout.core.widgets.ConstraintWidget r17, androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measurer r18) {
        /*
            r0 = r17
            r1 = r18
            boolean r2 = r17.isVerticalSolvingPassDone()
            if (r2 == 0) goto L_0x000b
            return
        L_0x000b:
            int r2 = vcount
            r3 = 1
            int r2 = r2 + r3
            vcount = r2
            boolean r2 = r0 instanceof androidx.constraintlayout.core.widgets.ConstraintWidgetContainer
            if (r2 != 0) goto L_0x002d
            boolean r2 = r17.isMeasureRequested()
            if (r2 == 0) goto L_0x002d
            int r2 = r16 + 1
            boolean r4 = canMeasure(r2, r0)
            if (r4 == 0) goto L_0x002d
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure r4 = new androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure
            r4.<init>()
            int r5 = androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measure.SELF_DIMENSIONS
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer.measure(r2, r0, r1, r4, r5)
        L_0x002d:
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r2 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.TOP
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r0.getAnchor(r2)
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.BOTTOM
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r0.getAnchor(r4)
            int r5 = r2.getFinalValue()
            int r6 = r4.getFinalValue()
            java.util.HashSet r7 = r2.getDependents()
            r8 = 0
            r9 = 8
            if (r7 == 0) goto L_0x0132
            boolean r7 = r2.hasFinalValue()
            if (r7 == 0) goto L_0x0132
            java.util.HashSet r2 = r2.getDependents()
            java.util.Iterator r2 = r2.iterator()
        L_0x0058:
            boolean r7 = r2.hasNext()
            if (r7 == 0) goto L_0x0132
            java.lang.Object r7 = r2.next()
            androidx.constraintlayout.core.widgets.ConstraintAnchor r7 = (androidx.constraintlayout.core.widgets.ConstraintAnchor) r7
            androidx.constraintlayout.core.widgets.ConstraintWidget r11 = r7.mOwner
            int r12 = r16 + 1
            boolean r13 = canMeasure(r12, r11)
            boolean r14 = r11.isMeasureRequested()
            if (r14 == 0) goto L_0x007e
            if (r13 == 0) goto L_0x007e
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure r14 = new androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure
            r14.<init>()
            int r15 = androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measure.SELF_DIMENSIONS
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer.measure(r12, r11, r1, r14, r15)
        L_0x007e:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r14 = r11.mTop
            if (r7 != r14) goto L_0x008e
            androidx.constraintlayout.core.widgets.ConstraintAnchor r14 = r11.mBottom
            androidx.constraintlayout.core.widgets.ConstraintAnchor r14 = r14.mTarget
            if (r14 == 0) goto L_0x008e
            boolean r14 = r14.hasFinalValue()
            if (r14 != 0) goto L_0x009e
        L_0x008e:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r14 = r11.mBottom
            if (r7 != r14) goto L_0x00a0
            androidx.constraintlayout.core.widgets.ConstraintAnchor r14 = r11.mTop
            androidx.constraintlayout.core.widgets.ConstraintAnchor r14 = r14.mTarget
            if (r14 == 0) goto L_0x00a0
            boolean r14 = r14.hasFinalValue()
            if (r14 == 0) goto L_0x00a0
        L_0x009e:
            r14 = r3
            goto L_0x00a1
        L_0x00a0:
            r14 = 0
        L_0x00a1:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r15 = r11.getVerticalDimensionBehaviour()
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r15 != r10) goto L_0x00e5
            if (r13 == 0) goto L_0x00ac
            goto L_0x00e5
        L_0x00ac:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r7 = r11.getVerticalDimensionBehaviour()
            if (r7 != r10) goto L_0x0058
            int r7 = r11.mMatchConstraintMaxHeight
            if (r7 < 0) goto L_0x0058
            int r7 = r11.mMatchConstraintMinHeight
            if (r7 < 0) goto L_0x0058
            int r7 = r11.getVisibility()
            if (r7 == r9) goto L_0x00cc
            int r7 = r11.mMatchConstraintDefaultHeight
            if (r7 != 0) goto L_0x0058
            float r7 = r11.getDimensionRatio()
            int r7 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r7 != 0) goto L_0x0058
        L_0x00cc:
            boolean r7 = r11.isInVerticalChain()
            if (r7 != 0) goto L_0x0058
            boolean r7 = r11.isInVirtualLayout()
            if (r7 != 0) goto L_0x0058
            if (r14 == 0) goto L_0x0058
            boolean r7 = r11.isInVerticalChain()
            if (r7 != 0) goto L_0x0058
            solveVerticalMatchConstraint(r12, r0, r1, r11)
            goto L_0x0058
        L_0x00e5:
            boolean r10 = r11.isMeasureRequested()
            if (r10 == 0) goto L_0x00ed
            goto L_0x0058
        L_0x00ed:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r10 = r11.mTop
            if (r7 != r10) goto L_0x0109
            androidx.constraintlayout.core.widgets.ConstraintAnchor r13 = r11.mBottom
            androidx.constraintlayout.core.widgets.ConstraintAnchor r13 = r13.mTarget
            if (r13 != 0) goto L_0x0109
            int r7 = r10.getMargin()
            int r7 = r7 + r5
            int r10 = r11.getHeight()
            int r10 = r10 + r7
            r11.setFinalVertical(r7, r10)
            verticalSolvingPass(r12, r11, r1)
            goto L_0x0058
        L_0x0109:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r13 = r11.mBottom
            if (r7 != r13) goto L_0x0125
            androidx.constraintlayout.core.widgets.ConstraintAnchor r7 = r10.mTarget
            if (r7 != 0) goto L_0x0125
            int r7 = r13.getMargin()
            int r7 = r5 - r7
            int r10 = r11.getHeight()
            int r10 = r7 - r10
            r11.setFinalVertical(r10, r7)
            verticalSolvingPass(r12, r11, r1)
            goto L_0x0058
        L_0x0125:
            if (r14 == 0) goto L_0x0058
            boolean r7 = r11.isInVerticalChain()
            if (r7 != 0) goto L_0x0058
            solveVerticalCenterConstraints(r12, r1, r11)
            goto L_0x0058
        L_0x0132:
            boolean r2 = r0 instanceof androidx.constraintlayout.core.widgets.Guideline
            if (r2 == 0) goto L_0x0137
            return
        L_0x0137:
            java.util.HashSet r2 = r4.getDependents()
            if (r2 == 0) goto L_0x0225
            boolean r2 = r4.hasFinalValue()
            if (r2 == 0) goto L_0x0225
            java.util.HashSet r2 = r4.getDependents()
            java.util.Iterator r2 = r2.iterator()
        L_0x014b:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x0225
            java.lang.Object r4 = r2.next()
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = (androidx.constraintlayout.core.widgets.ConstraintAnchor) r4
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = r4.mOwner
            int r7 = r16 + 1
            boolean r10 = canMeasure(r7, r5)
            boolean r11 = r5.isMeasureRequested()
            if (r11 == 0) goto L_0x0171
            if (r10 == 0) goto L_0x0171
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure r11 = new androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure
            r11.<init>()
            int r12 = androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measure.SELF_DIMENSIONS
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer.measure(r7, r5, r1, r11, r12)
        L_0x0171:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r11 = r5.mTop
            if (r4 != r11) goto L_0x0181
            androidx.constraintlayout.core.widgets.ConstraintAnchor r11 = r5.mBottom
            androidx.constraintlayout.core.widgets.ConstraintAnchor r11 = r11.mTarget
            if (r11 == 0) goto L_0x0181
            boolean r11 = r11.hasFinalValue()
            if (r11 != 0) goto L_0x0191
        L_0x0181:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r11 = r5.mBottom
            if (r4 != r11) goto L_0x0193
            androidx.constraintlayout.core.widgets.ConstraintAnchor r11 = r5.mTop
            androidx.constraintlayout.core.widgets.ConstraintAnchor r11 = r11.mTarget
            if (r11 == 0) goto L_0x0193
            boolean r11 = r11.hasFinalValue()
            if (r11 == 0) goto L_0x0193
        L_0x0191:
            r11 = r3
            goto L_0x0194
        L_0x0193:
            r11 = 0
        L_0x0194:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r12 = r5.getVerticalDimensionBehaviour()
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r13 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r12 != r13) goto L_0x01d8
            if (r10 == 0) goto L_0x019f
            goto L_0x01d8
        L_0x019f:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r4 = r5.getVerticalDimensionBehaviour()
            if (r4 != r13) goto L_0x014b
            int r4 = r5.mMatchConstraintMaxHeight
            if (r4 < 0) goto L_0x014b
            int r4 = r5.mMatchConstraintMinHeight
            if (r4 < 0) goto L_0x014b
            int r4 = r5.getVisibility()
            if (r4 == r9) goto L_0x01bf
            int r4 = r5.mMatchConstraintDefaultHeight
            if (r4 != 0) goto L_0x014b
            float r4 = r5.getDimensionRatio()
            int r4 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r4 != 0) goto L_0x014b
        L_0x01bf:
            boolean r4 = r5.isInVerticalChain()
            if (r4 != 0) goto L_0x014b
            boolean r4 = r5.isInVirtualLayout()
            if (r4 != 0) goto L_0x014b
            if (r11 == 0) goto L_0x014b
            boolean r4 = r5.isInVerticalChain()
            if (r4 != 0) goto L_0x014b
            solveVerticalMatchConstraint(r7, r0, r1, r5)
            goto L_0x014b
        L_0x01d8:
            boolean r10 = r5.isMeasureRequested()
            if (r10 == 0) goto L_0x01e0
            goto L_0x014b
        L_0x01e0:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r10 = r5.mTop
            if (r4 != r10) goto L_0x01fc
            androidx.constraintlayout.core.widgets.ConstraintAnchor r12 = r5.mBottom
            androidx.constraintlayout.core.widgets.ConstraintAnchor r12 = r12.mTarget
            if (r12 != 0) goto L_0x01fc
            int r4 = r10.getMargin()
            int r4 = r4 + r6
            int r10 = r5.getHeight()
            int r10 = r10 + r4
            r5.setFinalVertical(r4, r10)
            verticalSolvingPass(r7, r5, r1)
            goto L_0x014b
        L_0x01fc:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r12 = r5.mBottom
            if (r4 != r12) goto L_0x0218
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r10.mTarget
            if (r4 != 0) goto L_0x0218
            int r4 = r12.getMargin()
            int r4 = r6 - r4
            int r10 = r5.getHeight()
            int r10 = r4 - r10
            r5.setFinalVertical(r10, r4)
            verticalSolvingPass(r7, r5, r1)
            goto L_0x014b
        L_0x0218:
            if (r11 == 0) goto L_0x014b
            boolean r4 = r5.isInVerticalChain()
            if (r4 != 0) goto L_0x014b
            solveVerticalCenterConstraints(r7, r1, r5)
            goto L_0x014b
        L_0x0225:
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r2 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.BASELINE
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r0.getAnchor(r2)
            java.util.HashSet r4 = r2.getDependents()
            if (r4 == 0) goto L_0x028a
            boolean r4 = r2.hasFinalValue()
            if (r4 == 0) goto L_0x028a
            int r4 = r2.getFinalValue()
            java.util.HashSet r2 = r2.getDependents()
            java.util.Iterator r2 = r2.iterator()
        L_0x0243:
            boolean r5 = r2.hasNext()
            if (r5 == 0) goto L_0x028a
            java.lang.Object r5 = r2.next()
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = (androidx.constraintlayout.core.widgets.ConstraintAnchor) r5
            androidx.constraintlayout.core.widgets.ConstraintWidget r6 = r5.mOwner
            int r7 = r16 + 1
            boolean r8 = canMeasure(r7, r6)
            boolean r9 = r6.isMeasureRequested()
            if (r9 == 0) goto L_0x0269
            if (r8 == 0) goto L_0x0269
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure r9 = new androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure
            r9.<init>()
            int r10 = androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measure.SELF_DIMENSIONS
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer.measure(r7, r6, r1, r9, r10)
        L_0x0269:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r9 = r6.getVerticalDimensionBehaviour()
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r9 != r10) goto L_0x0273
            if (r8 == 0) goto L_0x0243
        L_0x0273:
            boolean r8 = r6.isMeasureRequested()
            if (r8 == 0) goto L_0x027a
            goto L_0x0243
        L_0x027a:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r8 = r6.mBaseline
            if (r5 != r8) goto L_0x0243
            int r5 = r5.getMargin()
            int r5 = r5 + r4
            r6.setFinalBaseline(r5)
            verticalSolvingPass(r7, r6, r1)
            goto L_0x0243
        L_0x028a:
            r17.markVerticalSolvingPassDone()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.Direct.verticalSolvingPass(int, androidx.constraintlayout.core.widgets.ConstraintWidget, androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer):void");
    }

    private static void solveHorizontalCenterConstraints(int i, BasicMeasure.Measurer measurer, ConstraintWidget constraintWidget, boolean z) {
        float horizontalBiasPercent = constraintWidget.getHorizontalBiasPercent();
        int finalValue = constraintWidget.mLeft.mTarget.getFinalValue();
        int finalValue2 = constraintWidget.mRight.mTarget.getFinalValue();
        int margin = constraintWidget.mLeft.getMargin() + finalValue;
        int margin2 = finalValue2 - constraintWidget.mRight.getMargin();
        if (finalValue == finalValue2) {
            horizontalBiasPercent = 0.5f;
        } else {
            finalValue = margin;
            finalValue2 = margin2;
        }
        int width = constraintWidget.getWidth();
        int i2 = (finalValue2 - finalValue) - width;
        if (finalValue > finalValue2) {
            i2 = (finalValue - finalValue2) - width;
        }
        int i3 = ((int) (i2 > 0 ? (horizontalBiasPercent * ((float) i2)) + 0.5f : horizontalBiasPercent * ((float) i2))) + finalValue;
        int i4 = i3 + width;
        if (finalValue > finalValue2) {
            i4 = i3 - width;
        }
        constraintWidget.setFinalHorizontal(i3, i4);
        horizontalSolvingPass(i + 1, constraintWidget, measurer, z);
    }

    private static void solveVerticalCenterConstraints(int i, BasicMeasure.Measurer measurer, ConstraintWidget constraintWidget) {
        float verticalBiasPercent = constraintWidget.getVerticalBiasPercent();
        int finalValue = constraintWidget.mTop.mTarget.getFinalValue();
        int finalValue2 = constraintWidget.mBottom.mTarget.getFinalValue();
        int margin = constraintWidget.mTop.getMargin() + finalValue;
        int margin2 = finalValue2 - constraintWidget.mBottom.getMargin();
        if (finalValue == finalValue2) {
            verticalBiasPercent = 0.5f;
        } else {
            finalValue = margin;
            finalValue2 = margin2;
        }
        int height = constraintWidget.getHeight();
        int i2 = (finalValue2 - finalValue) - height;
        if (finalValue > finalValue2) {
            i2 = (finalValue - finalValue2) - height;
        }
        int i3 = (int) (i2 > 0 ? (verticalBiasPercent * ((float) i2)) + 0.5f : verticalBiasPercent * ((float) i2));
        int i4 = finalValue + i3;
        int i5 = i4 + height;
        if (finalValue > finalValue2) {
            i4 = finalValue - i3;
            i5 = i4 - height;
        }
        constraintWidget.setFinalVertical(i4, i5);
        verticalSolvingPass(i + 1, constraintWidget, measurer);
    }

    private static void solveHorizontalMatchConstraint(int i, ConstraintWidget constraintWidget, BasicMeasure.Measurer measurer, ConstraintWidget constraintWidget2, boolean z) {
        int i2;
        float horizontalBiasPercent = constraintWidget2.getHorizontalBiasPercent();
        int finalValue = constraintWidget2.mLeft.mTarget.getFinalValue() + constraintWidget2.mLeft.getMargin();
        int finalValue2 = constraintWidget2.mRight.mTarget.getFinalValue() - constraintWidget2.mRight.getMargin();
        if (finalValue2 >= finalValue) {
            int width = constraintWidget2.getWidth();
            if (constraintWidget2.getVisibility() != 8) {
                int i3 = constraintWidget2.mMatchConstraintDefaultWidth;
                if (i3 == 2) {
                    if (constraintWidget instanceof ConstraintWidgetContainer) {
                        i2 = constraintWidget.getWidth();
                    } else {
                        i2 = constraintWidget.getParent().getWidth();
                    }
                    width = (int) (constraintWidget2.getHorizontalBiasPercent() * 0.5f * ((float) i2));
                } else if (i3 == 0) {
                    width = finalValue2 - finalValue;
                }
                width = Math.max(constraintWidget2.mMatchConstraintMinWidth, width);
                int i4 = constraintWidget2.mMatchConstraintMaxWidth;
                if (i4 > 0) {
                    width = Math.min(i4, width);
                }
            }
            int i5 = finalValue + ((int) ((horizontalBiasPercent * ((float) ((finalValue2 - finalValue) - width))) + 0.5f));
            constraintWidget2.setFinalHorizontal(i5, width + i5);
            horizontalSolvingPass(i + 1, constraintWidget2, measurer, z);
        }
    }

    private static void solveVerticalMatchConstraint(int i, ConstraintWidget constraintWidget, BasicMeasure.Measurer measurer, ConstraintWidget constraintWidget2) {
        int i2;
        float verticalBiasPercent = constraintWidget2.getVerticalBiasPercent();
        int finalValue = constraintWidget2.mTop.mTarget.getFinalValue() + constraintWidget2.mTop.getMargin();
        int finalValue2 = constraintWidget2.mBottom.mTarget.getFinalValue() - constraintWidget2.mBottom.getMargin();
        if (finalValue2 >= finalValue) {
            int height = constraintWidget2.getHeight();
            if (constraintWidget2.getVisibility() != 8) {
                int i3 = constraintWidget2.mMatchConstraintDefaultHeight;
                if (i3 == 2) {
                    if (constraintWidget instanceof ConstraintWidgetContainer) {
                        i2 = constraintWidget.getHeight();
                    } else {
                        i2 = constraintWidget.getParent().getHeight();
                    }
                    height = (int) (verticalBiasPercent * 0.5f * ((float) i2));
                } else if (i3 == 0) {
                    height = finalValue2 - finalValue;
                }
                height = Math.max(constraintWidget2.mMatchConstraintMinHeight, height);
                int i4 = constraintWidget2.mMatchConstraintMaxHeight;
                if (i4 > 0) {
                    height = Math.min(i4, height);
                }
            }
            int i5 = finalValue + ((int) ((verticalBiasPercent * ((float) ((finalValue2 - finalValue) - height))) + 0.5f));
            constraintWidget2.setFinalVertical(i5, height + i5);
            verticalSolvingPass(i + 1, constraintWidget2, measurer);
        }
    }

    private static boolean canMeasure(int i, ConstraintWidget constraintWidget) {
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2;
        ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = constraintWidget.getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour verticalDimensionBehaviour = constraintWidget.getVerticalDimensionBehaviour();
        ConstraintWidgetContainer constraintWidgetContainer = constraintWidget.getParent() != null ? (ConstraintWidgetContainer) constraintWidget.getParent() : null;
        if (constraintWidgetContainer != null) {
            ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour2 = constraintWidgetContainer.getHorizontalDimensionBehaviour();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        if (constraintWidgetContainer != null) {
            ConstraintWidget.DimensionBehaviour verticalDimensionBehaviour2 = constraintWidgetContainer.getVerticalDimensionBehaviour();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.FIXED;
        boolean z = horizontalDimensionBehaviour == dimensionBehaviour5 || constraintWidget.isResolvedHorizontally() || horizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || (horizontalDimensionBehaviour == (dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) && constraintWidget.mMatchConstraintDefaultWidth == 0 && constraintWidget.mDimensionRatio == 0.0f && constraintWidget.hasDanglingDimension(0)) || (horizontalDimensionBehaviour == dimensionBehaviour2 && constraintWidget.mMatchConstraintDefaultWidth == 1 && constraintWidget.hasResolvedTargets(0, constraintWidget.getWidth()));
        boolean z2 = verticalDimensionBehaviour == dimensionBehaviour5 || constraintWidget.isResolvedVertically() || verticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || (verticalDimensionBehaviour == (dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) && constraintWidget.mMatchConstraintDefaultHeight == 0 && constraintWidget.mDimensionRatio == 0.0f && constraintWidget.hasDanglingDimension(1)) || (verticalDimensionBehaviour == dimensionBehaviour && constraintWidget.mMatchConstraintDefaultHeight == 1 && constraintWidget.hasResolvedTargets(1, constraintWidget.getHeight()));
        if (constraintWidget.mDimensionRatio > 0.0f && (z || z2)) {
            return true;
        }
        if (!z || !z2) {
            return false;
        }
        return true;
    }
}
