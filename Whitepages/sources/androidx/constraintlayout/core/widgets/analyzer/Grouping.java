package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.HelperWidget;
import java.util.ArrayList;

public abstract class Grouping {
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r3 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean validInGroup(androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour r5, androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour r6, androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour r7, androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour r8) {
        /*
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r0 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r1 = 1
            r2 = 0
            if (r7 == r0) goto L_0x0013
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r7 == r3) goto L_0x0013
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r4 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_PARENT
            if (r7 != r4) goto L_0x0011
            if (r5 == r3) goto L_0x0011
            goto L_0x0013
        L_0x0011:
            r5 = r2
            goto L_0x0014
        L_0x0013:
            r5 = r1
        L_0x0014:
            if (r8 == r0) goto L_0x0023
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r7 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r8 == r7) goto L_0x0023
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r0 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_PARENT
            if (r8 != r0) goto L_0x0021
            if (r6 == r7) goto L_0x0021
            goto L_0x0023
        L_0x0021:
            r6 = r2
            goto L_0x0024
        L_0x0023:
            r6 = r1
        L_0x0024:
            if (r5 != 0) goto L_0x002a
            if (r6 == 0) goto L_0x0029
            goto L_0x002a
        L_0x0029:
            return r2
        L_0x002a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.Grouping.validInGroup(androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour, androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour, androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour, androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:172:0x034c  */
    /* JADX WARNING: Removed duplicated region for block: B:183:0x0388  */
    /* JADX WARNING: Removed duplicated region for block: B:186:0x038d A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean simpleSolvingPass(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r16, androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measurer r17) {
        /*
            r0 = r16
            java.util.ArrayList r1 = r16.getChildren()
            int r2 = r1.size()
            r3 = 0
            r4 = r3
        L_0x000c:
            if (r4 >= r2) goto L_0x002e
            java.lang.Object r5 = r1.get(r4)
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = (androidx.constraintlayout.core.widgets.ConstraintWidget) r5
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r6 = r16.getHorizontalDimensionBehaviour()
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r7 = r16.getVerticalDimensionBehaviour()
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r8 = r5.getHorizontalDimensionBehaviour()
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r5 = r5.getVerticalDimensionBehaviour()
            boolean r5 = validInGroup(r6, r7, r8, r5)
            if (r5 != 0) goto L_0x002b
            return r3
        L_0x002b:
            int r4 = r4 + 1
            goto L_0x000c
        L_0x002e:
            r5 = r3
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
        L_0x0035:
            if (r5 >= r2) goto L_0x010e
            java.lang.Object r13 = r1.get(r5)
            androidx.constraintlayout.core.widgets.ConstraintWidget r13 = (androidx.constraintlayout.core.widgets.ConstraintWidget) r13
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r14 = r16.getHorizontalDimensionBehaviour()
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r15 = r16.getVerticalDimensionBehaviour()
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r4 = r13.getHorizontalDimensionBehaviour()
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r12 = r13.getVerticalDimensionBehaviour()
            boolean r4 = validInGroup(r14, r15, r4, r12)
            if (r4 != 0) goto L_0x005d
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure r4 = r0.mMeasure
            int r12 = androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measure.SELF_DIMENSIONS
            r14 = r17
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer.measure(r3, r13, r14, r4, r12)
            goto L_0x005f
        L_0x005d:
            r14 = r17
        L_0x005f:
            boolean r4 = r13 instanceof androidx.constraintlayout.core.widgets.Guideline
            if (r4 == 0) goto L_0x0087
            r12 = r13
            androidx.constraintlayout.core.widgets.Guideline r12 = (androidx.constraintlayout.core.widgets.Guideline) r12
            int r15 = r12.getOrientation()
            if (r15 != 0) goto L_0x0076
            if (r8 != 0) goto L_0x0073
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
        L_0x0073:
            r8.add(r12)
        L_0x0076:
            int r15 = r12.getOrientation()
            r3 = 1
            if (r15 != r3) goto L_0x0087
            if (r6 != 0) goto L_0x0084
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
        L_0x0084:
            r6.add(r12)
        L_0x0087:
            boolean r3 = r13 instanceof androidx.constraintlayout.core.widgets.HelperWidget
            if (r3 == 0) goto L_0x00cb
            boolean r3 = r13 instanceof androidx.constraintlayout.core.widgets.Barrier
            if (r3 == 0) goto L_0x00b4
            r3 = r13
            androidx.constraintlayout.core.widgets.Barrier r3 = (androidx.constraintlayout.core.widgets.Barrier) r3
            int r12 = r3.getOrientation()
            if (r12 != 0) goto L_0x00a2
            if (r7 != 0) goto L_0x009f
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
        L_0x009f:
            r7.add(r3)
        L_0x00a2:
            int r12 = r3.getOrientation()
            r15 = 1
            if (r12 != r15) goto L_0x00cb
            if (r9 != 0) goto L_0x00b0
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
        L_0x00b0:
            r9.add(r3)
            goto L_0x00cb
        L_0x00b4:
            r3 = r13
            androidx.constraintlayout.core.widgets.HelperWidget r3 = (androidx.constraintlayout.core.widgets.HelperWidget) r3
            if (r7 != 0) goto L_0x00be
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
        L_0x00be:
            r7.add(r3)
            if (r9 != 0) goto L_0x00c8
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
        L_0x00c8:
            r9.add(r3)
        L_0x00cb:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r13.mLeft
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 != 0) goto L_0x00e7
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r13.mRight
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 != 0) goto L_0x00e7
            if (r4 != 0) goto L_0x00e7
            boolean r3 = r13 instanceof androidx.constraintlayout.core.widgets.Barrier
            if (r3 != 0) goto L_0x00e7
            if (r10 != 0) goto L_0x00e4
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
        L_0x00e4:
            r10.add(r13)
        L_0x00e7:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r13.mTop
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 != 0) goto L_0x0109
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r13.mBottom
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 != 0) goto L_0x0109
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r13.mBaseline
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 != 0) goto L_0x0109
            if (r4 != 0) goto L_0x0109
            boolean r3 = r13 instanceof androidx.constraintlayout.core.widgets.Barrier
            if (r3 != 0) goto L_0x0109
            if (r11 != 0) goto L_0x0106
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
        L_0x0106:
            r11.add(r13)
        L_0x0109:
            int r5 = r5 + 1
            r3 = 0
            goto L_0x0035
        L_0x010e:
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            if (r6 == 0) goto L_0x012b
            java.util.Iterator r4 = r6.iterator()
        L_0x0119:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x012b
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.core.widgets.Guideline r5 = (androidx.constraintlayout.core.widgets.Guideline) r5
            r6 = 0
            r12 = 0
            findDependents(r5, r6, r3, r12)
            goto L_0x0119
        L_0x012b:
            r6 = 0
            r12 = 0
            if (r7 == 0) goto L_0x014c
            java.util.Iterator r4 = r7.iterator()
        L_0x0133:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x014c
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.core.widgets.HelperWidget r5 = (androidx.constraintlayout.core.widgets.HelperWidget) r5
            androidx.constraintlayout.core.widgets.analyzer.WidgetGroup r7 = findDependents(r5, r6, r3, r12)
            r5.addDependents(r3, r6, r7)
            r7.cleanup(r3)
            r6 = 0
            r12 = 0
            goto L_0x0133
        L_0x014c:
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.LEFT
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r0.getAnchor(r4)
            java.util.HashSet r5 = r4.getDependents()
            if (r5 == 0) goto L_0x0174
            java.util.HashSet r4 = r4.getDependents()
            java.util.Iterator r4 = r4.iterator()
        L_0x0160:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0174
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = (androidx.constraintlayout.core.widgets.ConstraintAnchor) r5
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = r5.mOwner
            r6 = 0
            r7 = 0
            findDependents(r5, r6, r3, r7)
            goto L_0x0160
        L_0x0174:
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.RIGHT
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r0.getAnchor(r4)
            java.util.HashSet r5 = r4.getDependents()
            if (r5 == 0) goto L_0x019c
            java.util.HashSet r4 = r4.getDependents()
            java.util.Iterator r4 = r4.iterator()
        L_0x0188:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x019c
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = (androidx.constraintlayout.core.widgets.ConstraintAnchor) r5
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = r5.mOwner
            r6 = 0
            r7 = 0
            findDependents(r5, r6, r3, r7)
            goto L_0x0188
        L_0x019c:
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.CENTER
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r0.getAnchor(r4)
            java.util.HashSet r5 = r4.getDependents()
            if (r5 == 0) goto L_0x01c4
            java.util.HashSet r4 = r4.getDependents()
            java.util.Iterator r4 = r4.iterator()
        L_0x01b0:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x01c4
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = (androidx.constraintlayout.core.widgets.ConstraintAnchor) r5
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = r5.mOwner
            r6 = 0
            r7 = 0
            findDependents(r5, r6, r3, r7)
            goto L_0x01b0
        L_0x01c4:
            r6 = 0
            r7 = 0
            if (r10 == 0) goto L_0x01dc
            java.util.Iterator r4 = r10.iterator()
        L_0x01cc:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x01dc
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = (androidx.constraintlayout.core.widgets.ConstraintWidget) r5
            findDependents(r5, r6, r3, r7)
            goto L_0x01cc
        L_0x01dc:
            if (r8 == 0) goto L_0x01f3
            java.util.Iterator r4 = r8.iterator()
        L_0x01e2:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x01f3
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.core.widgets.Guideline r5 = (androidx.constraintlayout.core.widgets.Guideline) r5
            r6 = 1
            findDependents(r5, r6, r3, r7)
            goto L_0x01e2
        L_0x01f3:
            r6 = 1
            if (r9 == 0) goto L_0x0213
            java.util.Iterator r4 = r9.iterator()
        L_0x01fa:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0213
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.core.widgets.HelperWidget r5 = (androidx.constraintlayout.core.widgets.HelperWidget) r5
            androidx.constraintlayout.core.widgets.analyzer.WidgetGroup r8 = findDependents(r5, r6, r3, r7)
            r5.addDependents(r3, r6, r8)
            r8.cleanup(r3)
            r6 = 1
            r7 = 0
            goto L_0x01fa
        L_0x0213:
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.TOP
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r0.getAnchor(r4)
            java.util.HashSet r5 = r4.getDependents()
            if (r5 == 0) goto L_0x023b
            java.util.HashSet r4 = r4.getDependents()
            java.util.Iterator r4 = r4.iterator()
        L_0x0227:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x023b
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = (androidx.constraintlayout.core.widgets.ConstraintAnchor) r5
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = r5.mOwner
            r6 = 0
            r7 = 1
            findDependents(r5, r7, r3, r6)
            goto L_0x0227
        L_0x023b:
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.BASELINE
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r0.getAnchor(r4)
            java.util.HashSet r5 = r4.getDependents()
            if (r5 == 0) goto L_0x0263
            java.util.HashSet r4 = r4.getDependents()
            java.util.Iterator r4 = r4.iterator()
        L_0x024f:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0263
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = (androidx.constraintlayout.core.widgets.ConstraintAnchor) r5
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = r5.mOwner
            r6 = 0
            r7 = 1
            findDependents(r5, r7, r3, r6)
            goto L_0x024f
        L_0x0263:
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.BOTTOM
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r0.getAnchor(r4)
            java.util.HashSet r5 = r4.getDependents()
            if (r5 == 0) goto L_0x028b
            java.util.HashSet r4 = r4.getDependents()
            java.util.Iterator r4 = r4.iterator()
        L_0x0277:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x028b
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = (androidx.constraintlayout.core.widgets.ConstraintAnchor) r5
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = r5.mOwner
            r6 = 0
            r7 = 1
            findDependents(r5, r7, r3, r6)
            goto L_0x0277
        L_0x028b:
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.CENTER
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r0.getAnchor(r4)
            java.util.HashSet r5 = r4.getDependents()
            if (r5 == 0) goto L_0x02b3
            java.util.HashSet r4 = r4.getDependents()
            java.util.Iterator r4 = r4.iterator()
        L_0x029f:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x02b3
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = (androidx.constraintlayout.core.widgets.ConstraintAnchor) r5
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = r5.mOwner
            r6 = 1
            r12 = 0
            findDependents(r5, r6, r3, r12)
            goto L_0x029f
        L_0x02b3:
            r6 = 1
            r12 = 0
            if (r11 == 0) goto L_0x02cb
            java.util.Iterator r4 = r11.iterator()
        L_0x02bb:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x02cb
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = (androidx.constraintlayout.core.widgets.ConstraintWidget) r5
            findDependents(r5, r6, r3, r12)
            goto L_0x02bb
        L_0x02cb:
            r4 = 0
        L_0x02cc:
            if (r4 >= r2) goto L_0x02f8
            java.lang.Object r5 = r1.get(r4)
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = (androidx.constraintlayout.core.widgets.ConstraintWidget) r5
            boolean r6 = r5.oppositeDimensionsTied()
            if (r6 == 0) goto L_0x02f5
            int r6 = r5.horizontalGroup
            androidx.constraintlayout.core.widgets.analyzer.WidgetGroup r6 = findGroup(r3, r6)
            int r5 = r5.verticalGroup
            androidx.constraintlayout.core.widgets.analyzer.WidgetGroup r5 = findGroup(r3, r5)
            if (r6 == 0) goto L_0x02f5
            if (r5 == 0) goto L_0x02f5
            r7 = 0
            r6.moveTo(r7, r5)
            r7 = 2
            r5.setOrientation(r7)
            r3.remove(r6)
        L_0x02f5:
            int r4 = r4 + 1
            goto L_0x02cc
        L_0x02f8:
            int r1 = r3.size()
            r2 = 1
            if (r1 > r2) goto L_0x0301
            r1 = 0
            return r1
        L_0x0301:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r1 = r16.getHorizontalDimensionBehaviour()
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r1 != r2) goto L_0x0343
            java.util.Iterator r1 = r3.iterator()
            r2 = r12
            r6 = 0
        L_0x030f:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x0334
            java.lang.Object r4 = r1.next()
            androidx.constraintlayout.core.widgets.analyzer.WidgetGroup r4 = (androidx.constraintlayout.core.widgets.analyzer.WidgetGroup) r4
            int r5 = r4.getOrientation()
            r7 = 1
            if (r5 != r7) goto L_0x0323
            goto L_0x030f
        L_0x0323:
            r5 = 0
            r4.setAuthoritative(r5)
            androidx.constraintlayout.core.LinearSystem r7 = r16.getSystem()
            int r7 = r4.measureWrap(r7, r5)
            if (r7 <= r6) goto L_0x030f
            r2 = r4
            r6 = r7
            goto L_0x030f
        L_0x0334:
            if (r2 == 0) goto L_0x0343
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r1 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r0.setHorizontalDimensionBehaviour(r1)
            r0.setWidth(r6)
            r1 = 1
            r2.setAuthoritative(r1)
            goto L_0x0344
        L_0x0343:
            r2 = r12
        L_0x0344:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r1 = r16.getVerticalDimensionBehaviour()
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r4 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r1 != r4) goto L_0x0388
            java.util.Iterator r1 = r3.iterator()
            r3 = r12
            r6 = 0
        L_0x0352:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x0377
            java.lang.Object r4 = r1.next()
            androidx.constraintlayout.core.widgets.analyzer.WidgetGroup r4 = (androidx.constraintlayout.core.widgets.analyzer.WidgetGroup) r4
            int r5 = r4.getOrientation()
            if (r5 != 0) goto L_0x0365
            goto L_0x0352
        L_0x0365:
            r5 = 0
            r4.setAuthoritative(r5)
            androidx.constraintlayout.core.LinearSystem r7 = r16.getSystem()
            r8 = 1
            int r7 = r4.measureWrap(r7, r8)
            if (r7 <= r6) goto L_0x0352
            r3 = r4
            r6 = r7
            goto L_0x0352
        L_0x0377:
            r5 = 0
            r8 = 1
            if (r3 == 0) goto L_0x038a
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r1 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r0.setVerticalDimensionBehaviour(r1)
            r0.setHeight(r6)
            r3.setAuthoritative(r8)
            r4 = r3
            goto L_0x038b
        L_0x0388:
            r5 = 0
            r8 = 1
        L_0x038a:
            r4 = r12
        L_0x038b:
            if (r2 != 0) goto L_0x0392
            if (r4 == 0) goto L_0x0390
            goto L_0x0392
        L_0x0390:
            r3 = r5
            goto L_0x0393
        L_0x0392:
            r3 = r8
        L_0x0393:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.Grouping.simpleSolvingPass(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer, androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer):boolean");
    }

    private static WidgetGroup findGroup(ArrayList arrayList, int i) {
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            WidgetGroup widgetGroup = (WidgetGroup) arrayList.get(i2);
            if (i == widgetGroup.id) {
                return widgetGroup;
            }
        }
        return null;
    }

    public static WidgetGroup findDependents(ConstraintWidget constraintWidget, int i, ArrayList arrayList, WidgetGroup widgetGroup) {
        int i2;
        int findGroupInDependents;
        if (i == 0) {
            i2 = constraintWidget.horizontalGroup;
        } else {
            i2 = constraintWidget.verticalGroup;
        }
        int i3 = 0;
        if (i2 != -1 && (widgetGroup == null || i2 != widgetGroup.id)) {
            int i4 = 0;
            while (true) {
                if (i4 >= arrayList.size()) {
                    break;
                }
                WidgetGroup widgetGroup2 = (WidgetGroup) arrayList.get(i4);
                if (widgetGroup2.getId() == i2) {
                    if (widgetGroup != null) {
                        widgetGroup.moveTo(i, widgetGroup2);
                        arrayList.remove(widgetGroup);
                    }
                    widgetGroup = widgetGroup2;
                } else {
                    i4++;
                }
            }
        } else if (i2 != -1) {
            return widgetGroup;
        }
        if (widgetGroup == null) {
            if ((constraintWidget instanceof HelperWidget) && (findGroupInDependents = ((HelperWidget) constraintWidget).findGroupInDependents(i)) != -1) {
                int i5 = 0;
                while (true) {
                    if (i5 >= arrayList.size()) {
                        break;
                    }
                    WidgetGroup widgetGroup3 = (WidgetGroup) arrayList.get(i5);
                    if (widgetGroup3.getId() == findGroupInDependents) {
                        widgetGroup = widgetGroup3;
                        break;
                    }
                    i5++;
                }
            }
            if (widgetGroup == null) {
                widgetGroup = new WidgetGroup(i);
            }
            arrayList.add(widgetGroup);
        }
        if (widgetGroup.add(constraintWidget)) {
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                ConstraintAnchor anchor = guideline.getAnchor();
                if (guideline.getOrientation() == 0) {
                    i3 = 1;
                }
                anchor.findDependents(i3, arrayList, widgetGroup);
            }
            if (i == 0) {
                constraintWidget.horizontalGroup = widgetGroup.getId();
                constraintWidget.mLeft.findDependents(i, arrayList, widgetGroup);
                constraintWidget.mRight.findDependents(i, arrayList, widgetGroup);
            } else {
                constraintWidget.verticalGroup = widgetGroup.getId();
                constraintWidget.mTop.findDependents(i, arrayList, widgetGroup);
                constraintWidget.mBaseline.findDependents(i, arrayList, widgetGroup);
                constraintWidget.mBottom.findDependents(i, arrayList, widgetGroup);
            }
            constraintWidget.mCenter.findDependents(i, arrayList, widgetGroup);
        }
        return widgetGroup;
    }
}
