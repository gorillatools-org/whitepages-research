package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.HelperWidget;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class DependencyGraph {
    private ConstraintWidgetContainer container;
    private ConstraintWidgetContainer mContainer;
    ArrayList mGroups = new ArrayList();
    private BasicMeasure.Measure mMeasure = new BasicMeasure.Measure();
    private BasicMeasure.Measurer mMeasurer = null;
    private boolean mNeedBuildGraph = true;
    private boolean mNeedRedoMeasures = true;
    private ArrayList mRuns = new ArrayList();
    private ArrayList runGroups = new ArrayList();

    public DependencyGraph(ConstraintWidgetContainer constraintWidgetContainer) {
        this.container = constraintWidgetContainer;
        this.mContainer = constraintWidgetContainer;
    }

    public void setMeasurer(BasicMeasure.Measurer measurer) {
        this.mMeasurer = measurer;
    }

    private int computeWrap(ConstraintWidgetContainer constraintWidgetContainer, int i) {
        int size = this.mGroups.size();
        long j = 0;
        for (int i2 = 0; i2 < size; i2++) {
            j = Math.max(j, ((RunGroup) this.mGroups.get(i2)).computeWrapSize(constraintWidgetContainer, i));
        }
        return (int) j;
    }

    public boolean directMeasure(boolean z) {
        boolean z2;
        boolean z3 = false;
        if (this.mNeedBuildGraph || this.mNeedRedoMeasures) {
            Iterator it = this.container.mChildren.iterator();
            while (it.hasNext()) {
                ConstraintWidget constraintWidget = (ConstraintWidget) it.next();
                constraintWidget.ensureWidgetRuns();
                constraintWidget.measured = false;
                constraintWidget.horizontalRun.reset();
                constraintWidget.verticalRun.reset();
            }
            this.container.ensureWidgetRuns();
            ConstraintWidgetContainer constraintWidgetContainer = this.container;
            constraintWidgetContainer.measured = false;
            constraintWidgetContainer.horizontalRun.reset();
            this.container.verticalRun.reset();
            this.mNeedRedoMeasures = false;
        }
        if (basicMeasureWidgets(this.mContainer)) {
            return false;
        }
        this.container.setX(0);
        this.container.setY(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.container.getDimensionBehaviour(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = this.container.getDimensionBehaviour(1);
        if (this.mNeedBuildGraph) {
            buildGraph();
        }
        int x = this.container.getX();
        int y = this.container.getY();
        this.container.horizontalRun.start.resolve(x);
        this.container.verticalRun.start.resolve(y);
        measureWidgets();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (dimensionBehaviour == dimensionBehaviour3 || dimensionBehaviour2 == dimensionBehaviour3) {
            if (z) {
                Iterator it2 = this.mRuns.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (!((WidgetRun) it2.next()).supportsWrapComputation()) {
                            z = false;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            if (z && dimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.container.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                ConstraintWidgetContainer constraintWidgetContainer2 = this.container;
                constraintWidgetContainer2.setWidth(computeWrap(constraintWidgetContainer2, 0));
                ConstraintWidgetContainer constraintWidgetContainer3 = this.container;
                constraintWidgetContainer3.horizontalRun.dimension.resolve(constraintWidgetContainer3.getWidth());
            }
            if (z && dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.container.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                ConstraintWidgetContainer constraintWidgetContainer4 = this.container;
                constraintWidgetContainer4.setHeight(computeWrap(constraintWidgetContainer4, 1));
                ConstraintWidgetContainer constraintWidgetContainer5 = this.container;
                constraintWidgetContainer5.verticalRun.dimension.resolve(constraintWidgetContainer5.getHeight());
            }
        }
        ConstraintWidgetContainer constraintWidgetContainer6 = this.container;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = constraintWidgetContainer6.mListDimensionBehaviors[0];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.FIXED;
        if (dimensionBehaviour4 == dimensionBehaviour5 || dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int width = constraintWidgetContainer6.getWidth() + x;
            this.container.horizontalRun.end.resolve(width);
            this.container.horizontalRun.dimension.resolve(width - x);
            measureWidgets();
            ConstraintWidgetContainer constraintWidgetContainer7 = this.container;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour6 = constraintWidgetContainer7.mListDimensionBehaviors[1];
            if (dimensionBehaviour6 == dimensionBehaviour5 || dimensionBehaviour6 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int height = constraintWidgetContainer7.getHeight() + y;
                this.container.verticalRun.end.resolve(height);
                this.container.verticalRun.dimension.resolve(height - y);
            }
            measureWidgets();
            z2 = true;
        } else {
            z2 = false;
        }
        Iterator it3 = this.mRuns.iterator();
        while (it3.hasNext()) {
            WidgetRun widgetRun = (WidgetRun) it3.next();
            if (widgetRun.widget != this.container || widgetRun.resolved) {
                widgetRun.applyToWidget();
            }
        }
        Iterator it4 = this.mRuns.iterator();
        while (true) {
            if (!it4.hasNext()) {
                z3 = true;
                break;
            }
            WidgetRun widgetRun2 = (WidgetRun) it4.next();
            if ((z2 || widgetRun2.widget != this.container) && (!widgetRun2.start.resolved || ((!widgetRun2.end.resolved && !(widgetRun2 instanceof GuidelineReference)) || (!widgetRun2.dimension.resolved && !(widgetRun2 instanceof ChainRun) && !(widgetRun2 instanceof GuidelineReference))))) {
                break;
            }
        }
        this.container.setHorizontalDimensionBehaviour(dimensionBehaviour);
        this.container.setVerticalDimensionBehaviour(dimensionBehaviour2);
        return z3;
    }

    public boolean directMeasureSetup(boolean z) {
        if (this.mNeedBuildGraph) {
            Iterator it = this.container.mChildren.iterator();
            while (it.hasNext()) {
                ConstraintWidget constraintWidget = (ConstraintWidget) it.next();
                constraintWidget.ensureWidgetRuns();
                constraintWidget.measured = false;
                HorizontalWidgetRun horizontalWidgetRun = constraintWidget.horizontalRun;
                horizontalWidgetRun.dimension.resolved = false;
                horizontalWidgetRun.resolved = false;
                horizontalWidgetRun.reset();
                VerticalWidgetRun verticalWidgetRun = constraintWidget.verticalRun;
                verticalWidgetRun.dimension.resolved = false;
                verticalWidgetRun.resolved = false;
                verticalWidgetRun.reset();
            }
            this.container.ensureWidgetRuns();
            ConstraintWidgetContainer constraintWidgetContainer = this.container;
            constraintWidgetContainer.measured = false;
            HorizontalWidgetRun horizontalWidgetRun2 = constraintWidgetContainer.horizontalRun;
            horizontalWidgetRun2.dimension.resolved = false;
            horizontalWidgetRun2.resolved = false;
            horizontalWidgetRun2.reset();
            VerticalWidgetRun verticalWidgetRun2 = this.container.verticalRun;
            verticalWidgetRun2.dimension.resolved = false;
            verticalWidgetRun2.resolved = false;
            verticalWidgetRun2.reset();
            buildGraph();
        }
        if (basicMeasureWidgets(this.mContainer)) {
            return false;
        }
        this.container.setX(0);
        this.container.setY(0);
        this.container.horizontalRun.start.resolve(0);
        this.container.verticalRun.start.resolve(0);
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0111  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x013e A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean directMeasureWithOrientation(boolean r10, int r11) {
        /*
            r9 = this;
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r0 = r9.container
            r1 = 0
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r0 = r0.getDimensionBehaviour(r1)
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r2 = r9.container
            r3 = 1
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r2 = r2.getDimensionBehaviour(r3)
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r4 = r9.container
            int r4 = r4.getX()
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r5 = r9.container
            int r5 = r5.getY()
            if (r10 == 0) goto L_0x0088
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r0 == r6) goto L_0x0022
            if (r2 != r6) goto L_0x0088
        L_0x0022:
            java.util.ArrayList r6 = r9.mRuns
            java.util.Iterator r6 = r6.iterator()
        L_0x0028:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x003f
            java.lang.Object r7 = r6.next()
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r7 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r7
            int r8 = r7.orientation
            if (r8 != r11) goto L_0x0028
            boolean r7 = r7.supportsWrapComputation()
            if (r7 != 0) goto L_0x0028
            r10 = r1
        L_0x003f:
            if (r11 != 0) goto L_0x0065
            if (r10 == 0) goto L_0x0088
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r0 != r10) goto L_0x0088
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r10 = r9.container
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r10.setHorizontalDimensionBehaviour(r6)
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r10 = r9.container
            int r6 = r9.computeWrap(r10, r1)
            r10.setWidth(r6)
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r10 = r9.container
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r6 = r10.horizontalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r6 = r6.dimension
            int r10 = r10.getWidth()
            r6.resolve(r10)
            goto L_0x0088
        L_0x0065:
            if (r10 == 0) goto L_0x0088
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r2 != r10) goto L_0x0088
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r10 = r9.container
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r10.setVerticalDimensionBehaviour(r6)
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r10 = r9.container
            int r6 = r9.computeWrap(r10, r3)
            r10.setHeight(r6)
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r10 = r9.container
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r6 = r10.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r6 = r6.dimension
            int r10 = r10.getHeight()
            r6.resolve(r10)
        L_0x0088:
            if (r11 != 0) goto L_0x00b2
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r10 = r9.container
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r5 = r10.mListDimensionBehaviors
            r5 = r5[r1]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r5 == r6) goto L_0x0098
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_PARENT
            if (r5 != r6) goto L_0x00c1
        L_0x0098:
            int r10 = r10.getWidth()
            int r10 = r10 + r4
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r5 = r9.container
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r5 = r5.horizontalRun
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r5 = r5.end
            r5.resolve(r10)
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r5 = r9.container
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r5 = r5.horizontalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r5 = r5.dimension
            int r10 = r10 - r4
            r5.resolve(r10)
        L_0x00b0:
            r10 = r3
            goto L_0x00dc
        L_0x00b2:
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r10 = r9.container
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r4 = r10.mListDimensionBehaviors
            r4 = r4[r3]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r4 == r6) goto L_0x00c3
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_PARENT
            if (r4 != r6) goto L_0x00c1
            goto L_0x00c3
        L_0x00c1:
            r10 = r1
            goto L_0x00dc
        L_0x00c3:
            int r10 = r10.getHeight()
            int r10 = r10 + r5
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r4 = r9.container
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r4 = r4.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r4 = r4.end
            r4.resolve(r10)
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r4 = r9.container
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r4 = r4.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r4 = r4.dimension
            int r10 = r10 - r5
            r4.resolve(r10)
            goto L_0x00b0
        L_0x00dc:
            r9.measureWidgets()
            java.util.ArrayList r4 = r9.mRuns
            java.util.Iterator r4 = r4.iterator()
        L_0x00e5:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0105
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r5 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r5
            int r6 = r5.orientation
            if (r6 == r11) goto L_0x00f6
            goto L_0x00e5
        L_0x00f6:
            androidx.constraintlayout.core.widgets.ConstraintWidget r6 = r5.widget
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r7 = r9.container
            if (r6 != r7) goto L_0x0101
            boolean r6 = r5.resolved
            if (r6 != 0) goto L_0x0101
            goto L_0x00e5
        L_0x0101:
            r5.applyToWidget()
            goto L_0x00e5
        L_0x0105:
            java.util.ArrayList r4 = r9.mRuns
            java.util.Iterator r4 = r4.iterator()
        L_0x010b:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x013e
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r5 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r5
            int r6 = r5.orientation
            if (r6 == r11) goto L_0x011c
            goto L_0x010b
        L_0x011c:
            if (r10 != 0) goto L_0x0125
            androidx.constraintlayout.core.widgets.ConstraintWidget r6 = r5.widget
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r7 = r9.container
            if (r6 != r7) goto L_0x0125
            goto L_0x010b
        L_0x0125:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r6 = r5.start
            boolean r6 = r6.resolved
            if (r6 != 0) goto L_0x012c
            goto L_0x013f
        L_0x012c:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r6 = r5.end
            boolean r6 = r6.resolved
            if (r6 != 0) goto L_0x0133
            goto L_0x013f
        L_0x0133:
            boolean r6 = r5 instanceof androidx.constraintlayout.core.widgets.analyzer.ChainRun
            if (r6 != 0) goto L_0x010b
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r5 = r5.dimension
            boolean r5 = r5.resolved
            if (r5 != 0) goto L_0x010b
            goto L_0x013f
        L_0x013e:
            r1 = r3
        L_0x013f:
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r10 = r9.container
            r10.setHorizontalDimensionBehaviour(r0)
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r10 = r9.container
            r10.setVerticalDimensionBehaviour(r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.DependencyGraph.directMeasureWithOrientation(boolean, int):boolean");
    }

    private void measure(ConstraintWidget constraintWidget, ConstraintWidget.DimensionBehaviour dimensionBehaviour, int i, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, int i2) {
        BasicMeasure.Measure measure = this.mMeasure;
        measure.horizontalBehavior = dimensionBehaviour;
        measure.verticalBehavior = dimensionBehaviour2;
        measure.horizontalDimension = i;
        measure.verticalDimension = i2;
        this.mMeasurer.measure(constraintWidget, measure);
        constraintWidget.setWidth(this.mMeasure.measuredWidth);
        constraintWidget.setHeight(this.mMeasure.measuredHeight);
        constraintWidget.setHasBaseline(this.mMeasure.measuredHasBaseline);
        constraintWidget.setBaselineDistance(this.mMeasure.measuredBaseline);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0280, code lost:
        r4 = r0.mListDimensionBehaviors;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean basicMeasureWidgets(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r17) {
        /*
            r16 = this;
            r0 = r17
            java.util.ArrayList r1 = r0.mChildren
            java.util.Iterator r1 = r1.iterator()
        L_0x0008:
            boolean r2 = r1.hasNext()
            r3 = 0
            if (r2 == 0) goto L_0x0338
            java.lang.Object r2 = r1.next()
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = (androidx.constraintlayout.core.widgets.ConstraintWidget) r2
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r4 = r2.mListDimensionBehaviors
            r5 = r4[r3]
            r10 = 1
            r4 = r4[r10]
            int r6 = r2.getVisibility()
            r7 = 8
            if (r6 != r7) goto L_0x0027
            r2.measured = r10
            goto L_0x0008
        L_0x0027:
            float r6 = r2.mMatchConstraintPercentWidth
            r11 = 1065353216(0x3f800000, float:1.0)
            int r6 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
            r7 = 2
            if (r6 >= 0) goto L_0x0036
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r5 != r6) goto L_0x0036
            r2.mMatchConstraintDefaultWidth = r7
        L_0x0036:
            float r6 = r2.mMatchConstraintPercentHeight
            int r6 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
            if (r6 >= 0) goto L_0x0042
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r4 != r6) goto L_0x0042
            r2.mMatchConstraintDefaultHeight = r7
        L_0x0042:
            float r6 = r2.getDimensionRatio()
            r8 = 0
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            r8 = 3
            if (r6 <= 0) goto L_0x0078
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r5 != r6) goto L_0x005b
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r4 == r9) goto L_0x0058
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r4 != r9) goto L_0x005b
        L_0x0058:
            r2.mMatchConstraintDefaultWidth = r8
            goto L_0x0078
        L_0x005b:
            if (r4 != r6) goto L_0x0068
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r5 == r9) goto L_0x0065
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r5 != r9) goto L_0x0068
        L_0x0065:
            r2.mMatchConstraintDefaultHeight = r8
            goto L_0x0078
        L_0x0068:
            if (r5 != r6) goto L_0x0078
            if (r4 != r6) goto L_0x0078
            int r6 = r2.mMatchConstraintDefaultWidth
            if (r6 != 0) goto L_0x0072
            r2.mMatchConstraintDefaultWidth = r8
        L_0x0072:
            int r6 = r2.mMatchConstraintDefaultHeight
            if (r6 != 0) goto L_0x0078
            r2.mMatchConstraintDefaultHeight = r8
        L_0x0078:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r5 != r6) goto L_0x008e
            int r9 = r2.mMatchConstraintDefaultWidth
            if (r9 != r10) goto L_0x008e
            androidx.constraintlayout.core.widgets.ConstraintAnchor r9 = r2.mLeft
            androidx.constraintlayout.core.widgets.ConstraintAnchor r9 = r9.mTarget
            if (r9 == 0) goto L_0x008c
            androidx.constraintlayout.core.widgets.ConstraintAnchor r9 = r2.mRight
            androidx.constraintlayout.core.widgets.ConstraintAnchor r9 = r9.mTarget
            if (r9 != 0) goto L_0x008e
        L_0x008c:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r5 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
        L_0x008e:
            r9 = r5
            if (r4 != r6) goto L_0x00a3
            int r5 = r2.mMatchConstraintDefaultHeight
            if (r5 != r10) goto L_0x00a3
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = r2.mTop
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = r5.mTarget
            if (r5 == 0) goto L_0x00a1
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = r2.mBottom
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = r5.mTarget
            if (r5 != 0) goto L_0x00a3
        L_0x00a1:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r4 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
        L_0x00a3:
            r12 = r4
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r4 = r2.horizontalRun
            r4.dimensionBehavior = r9
            int r5 = r2.mMatchConstraintDefaultWidth
            r4.matchConstraintsType = r5
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r4 = r2.verticalRun
            r4.dimensionBehavior = r12
            int r13 = r2.mMatchConstraintDefaultHeight
            r4.matchConstraintsType = r13
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r4 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_PARENT
            if (r9 == r4) goto L_0x00c0
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r14 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r9 == r14) goto L_0x00c0
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r14 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r9 != r14) goto L_0x00cc
        L_0x00c0:
            if (r12 == r4) goto L_0x02e2
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r14 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r12 == r14) goto L_0x02e2
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r14 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r12 != r14) goto L_0x00cc
            goto L_0x02e2
        L_0x00cc:
            r14 = 1056964608(0x3f000000, float:0.5)
            if (r9 != r6) goto L_0x0199
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r15 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r12 == r15) goto L_0x00d8
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r11 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r12 != r11) goto L_0x0199
        L_0x00d8:
            if (r5 != r8) goto L_0x0113
            if (r12 != r15) goto L_0x00e6
            r7 = 0
            r9 = 0
            r4 = r16
            r5 = r2
            r6 = r15
            r8 = r15
            r4.measure(r5, r6, r7, r8, r9)
        L_0x00e6:
            int r9 = r2.getHeight()
            float r3 = (float) r9
            float r4 = r2.mDimensionRatio
            float r3 = r3 * r4
            float r3 = r3 + r14
            int r7 = (int) r3
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r8 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r4 = r16
            r5 = r2
            r6 = r8
            r4.measure(r5, r6, r7, r8, r9)
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r3 = r2.horizontalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r3 = r3.dimension
            int r4 = r2.getWidth()
            r3.resolve(r4)
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r3 = r2.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r3 = r3.dimension
            int r4 = r2.getHeight()
            r3.resolve(r4)
            r2.measured = r10
            goto L_0x0008
        L_0x0113:
            if (r5 != r10) goto L_0x012b
            r7 = 0
            r9 = 0
            r4 = r16
            r5 = r2
            r6 = r15
            r8 = r12
            r4.measure(r5, r6, r7, r8, r9)
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r3 = r2.horizontalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r3 = r3.dimension
            int r2 = r2.getWidth()
            r3.wrapValue = r2
            goto L_0x0008
        L_0x012b:
            if (r5 != r7) goto L_0x0167
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r11 = r0.mListDimensionBehaviors
            r11 = r11[r3]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r15 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r11 == r15) goto L_0x0137
            if (r11 != r4) goto L_0x0199
        L_0x0137:
            float r3 = r2.mMatchConstraintPercentWidth
            int r4 = r17.getWidth()
            float r4 = (float) r4
            float r3 = r3 * r4
            float r3 = r3 + r14
            int r7 = (int) r3
            int r9 = r2.getHeight()
            r4 = r16
            r5 = r2
            r6 = r15
            r8 = r12
            r4.measure(r5, r6, r7, r8, r9)
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r3 = r2.horizontalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r3 = r3.dimension
            int r4 = r2.getWidth()
            r3.resolve(r4)
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r3 = r2.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r3 = r3.dimension
            int r4 = r2.getHeight()
            r3.resolve(r4)
            r2.measured = r10
            goto L_0x0008
        L_0x0167:
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r11 = r2.mListAnchors
            r7 = r11[r3]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r7 = r7.mTarget
            if (r7 == 0) goto L_0x0175
            r7 = r11[r10]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r7 = r7.mTarget
            if (r7 != 0) goto L_0x0199
        L_0x0175:
            r7 = 0
            r9 = 0
            r4 = r16
            r5 = r2
            r6 = r15
            r8 = r12
            r4.measure(r5, r6, r7, r8, r9)
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r3 = r2.horizontalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r3 = r3.dimension
            int r4 = r2.getWidth()
            r3.resolve(r4)
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r3 = r2.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r3 = r3.dimension
            int r4 = r2.getHeight()
            r3.resolve(r4)
            r2.measured = r10
            goto L_0x0008
        L_0x0199:
            if (r12 != r6) goto L_0x0272
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r11 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r9 == r11) goto L_0x01a3
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r7 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r9 != r7) goto L_0x0272
        L_0x01a3:
            if (r13 != r8) goto L_0x01e9
            if (r9 != r11) goto L_0x01b1
            r7 = 0
            r9 = 0
            r4 = r16
            r5 = r2
            r6 = r11
            r8 = r11
            r4.measure(r5, r6, r7, r8, r9)
        L_0x01b1:
            int r7 = r2.getWidth()
            float r3 = r2.mDimensionRatio
            int r4 = r2.getDimensionRatioSide()
            r5 = -1
            if (r4 != r5) goto L_0x01c2
            r4 = 1065353216(0x3f800000, float:1.0)
            float r3 = r4 / r3
        L_0x01c2:
            float r4 = (float) r7
            float r4 = r4 * r3
            float r4 = r4 + r14
            int r9 = (int) r4
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r8 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r4 = r16
            r5 = r2
            r6 = r8
            r4.measure(r5, r6, r7, r8, r9)
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r3 = r2.horizontalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r3 = r3.dimension
            int r4 = r2.getWidth()
            r3.resolve(r4)
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r3 = r2.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r3 = r3.dimension
            int r4 = r2.getHeight()
            r3.resolve(r4)
            r2.measured = r10
            goto L_0x0008
        L_0x01e9:
            if (r13 != r10) goto L_0x0202
            r7 = 0
            r3 = 0
            r4 = r16
            r5 = r2
            r6 = r9
            r8 = r11
            r9 = r3
            r4.measure(r5, r6, r7, r8, r9)
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r3 = r2.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r3 = r3.dimension
            int r2 = r2.getHeight()
            r3.wrapValue = r2
            goto L_0x0008
        L_0x0202:
            r7 = 2
            if (r13 != r7) goto L_0x023f
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r7 = r0.mListDimensionBehaviors
            r7 = r7[r10]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r8 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r7 == r8) goto L_0x020f
            if (r7 != r4) goto L_0x0272
        L_0x020f:
            float r3 = r2.mMatchConstraintPercentHeight
            int r7 = r2.getWidth()
            int r4 = r17.getHeight()
            float r4 = (float) r4
            float r3 = r3 * r4
            float r3 = r3 + r14
            int r3 = (int) r3
            r4 = r16
            r5 = r2
            r6 = r9
            r9 = r3
            r4.measure(r5, r6, r7, r8, r9)
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r3 = r2.horizontalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r3 = r3.dimension
            int r4 = r2.getWidth()
            r3.resolve(r4)
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r3 = r2.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r3 = r3.dimension
            int r4 = r2.getHeight()
            r3.resolve(r4)
            r2.measured = r10
            goto L_0x0008
        L_0x023f:
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r4 = r2.mListAnchors
            r7 = 2
            r15 = r4[r7]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r7 = r15.mTarget
            if (r7 == 0) goto L_0x024e
            r4 = r4[r8]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 != 0) goto L_0x0272
        L_0x024e:
            r7 = 0
            r9 = 0
            r4 = r16
            r5 = r2
            r6 = r11
            r8 = r12
            r4.measure(r5, r6, r7, r8, r9)
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r3 = r2.horizontalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r3 = r3.dimension
            int r4 = r2.getWidth()
            r3.resolve(r4)
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r3 = r2.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r3 = r3.dimension
            int r4 = r2.getHeight()
            r3.resolve(r4)
            r2.measured = r10
            goto L_0x0008
        L_0x0272:
            if (r9 != r6) goto L_0x0008
            if (r12 != r6) goto L_0x0008
            if (r5 == r10) goto L_0x02c1
            if (r13 != r10) goto L_0x027b
            goto L_0x02c1
        L_0x027b:
            r4 = 2
            if (r13 != r4) goto L_0x0008
            if (r5 != r4) goto L_0x0008
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r4 = r0.mListDimensionBehaviors
            r3 = r4[r3]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r8 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            if (r3 != r8) goto L_0x0008
            r3 = r4[r10]
            if (r3 != r8) goto L_0x0008
            float r3 = r2.mMatchConstraintPercentWidth
            float r4 = r2.mMatchConstraintPercentHeight
            int r5 = r17.getWidth()
            float r5 = (float) r5
            float r3 = r3 * r5
            float r3 = r3 + r14
            int r7 = (int) r3
            int r3 = r17.getHeight()
            float r3 = (float) r3
            float r4 = r4 * r3
            float r4 = r4 + r14
            int r9 = (int) r4
            r4 = r16
            r5 = r2
            r6 = r8
            r4.measure(r5, r6, r7, r8, r9)
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r3 = r2.horizontalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r3 = r3.dimension
            int r4 = r2.getWidth()
            r3.resolve(r4)
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r3 = r2.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r3 = r3.dimension
            int r4 = r2.getHeight()
            r3.resolve(r4)
            r2.measured = r10
            goto L_0x0008
        L_0x02c1:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r8 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r7 = 0
            r9 = 0
            r4 = r16
            r5 = r2
            r6 = r8
            r4.measure(r5, r6, r7, r8, r9)
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r3 = r2.horizontalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r3 = r3.dimension
            int r4 = r2.getWidth()
            r3.wrapValue = r4
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r3 = r2.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r3 = r3.dimension
            int r2 = r2.getHeight()
            r3.wrapValue = r2
            goto L_0x0008
        L_0x02e2:
            int r3 = r2.getWidth()
            if (r9 != r4) goto L_0x02fb
            int r3 = r17.getWidth()
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = r2.mLeft
            int r5 = r5.mMargin
            int r3 = r3 - r5
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = r2.mRight
            int r5 = r5.mMargin
            int r3 = r3 - r5
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r5 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r7 = r3
            r6 = r5
            goto L_0x02fd
        L_0x02fb:
            r7 = r3
            r6 = r9
        L_0x02fd:
            int r3 = r2.getHeight()
            if (r12 != r4) goto L_0x0316
            int r3 = r17.getHeight()
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r2.mTop
            int r4 = r4.mMargin
            int r3 = r3 - r4
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r2.mBottom
            int r4 = r4.mMargin
            int r3 = r3 - r4
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r4 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r9 = r3
            r8 = r4
            goto L_0x0318
        L_0x0316:
            r9 = r3
            r8 = r12
        L_0x0318:
            r4 = r16
            r5 = r2
            r4.measure(r5, r6, r7, r8, r9)
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r3 = r2.horizontalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r3 = r3.dimension
            int r4 = r2.getWidth()
            r3.resolve(r4)
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r3 = r2.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r3 = r3.dimension
            int r4 = r2.getHeight()
            r3.resolve(r4)
            r2.measured = r10
            goto L_0x0008
        L_0x0338:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.DependencyGraph.basicMeasureWidgets(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer):boolean");
    }

    public void measureWidgets() {
        DimensionDependency dimensionDependency;
        Iterator it = this.container.mChildren.iterator();
        while (it.hasNext()) {
            ConstraintWidget constraintWidget = (ConstraintWidget) it.next();
            if (!constraintWidget.measured) {
                ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.mListDimensionBehaviors;
                boolean z = false;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[1];
                int i = constraintWidget.mMatchConstraintDefaultWidth;
                int i2 = constraintWidget.mMatchConstraintDefaultHeight;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                boolean z2 = dimensionBehaviour == dimensionBehaviour3 || (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && i == 1);
                if (dimensionBehaviour2 == dimensionBehaviour3 || (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && i2 == 1)) {
                    z = true;
                }
                DimensionDependency dimensionDependency2 = constraintWidget.horizontalRun.dimension;
                boolean z3 = dimensionDependency2.resolved;
                DimensionDependency dimensionDependency3 = constraintWidget.verticalRun.dimension;
                boolean z4 = dimensionDependency3.resolved;
                if (z3 && z4) {
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.FIXED;
                    measure(constraintWidget, dimensionBehaviour4, dimensionDependency2.value, dimensionBehaviour4, dimensionDependency3.value);
                    constraintWidget.measured = true;
                } else if (z3 && z) {
                    measure(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, dimensionDependency2.value, dimensionBehaviour3, dimensionDependency3.value);
                    if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        constraintWidget.verticalRun.dimension.wrapValue = constraintWidget.getHeight();
                    } else {
                        constraintWidget.verticalRun.dimension.resolve(constraintWidget.getHeight());
                        constraintWidget.measured = true;
                    }
                } else if (z4 && z2) {
                    measure(constraintWidget, dimensionBehaviour3, dimensionDependency2.value, ConstraintWidget.DimensionBehaviour.FIXED, dimensionDependency3.value);
                    if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        constraintWidget.horizontalRun.dimension.wrapValue = constraintWidget.getWidth();
                    } else {
                        constraintWidget.horizontalRun.dimension.resolve(constraintWidget.getWidth());
                        constraintWidget.measured = true;
                    }
                }
                if (constraintWidget.measured && (dimensionDependency = constraintWidget.verticalRun.baselineDimension) != null) {
                    dimensionDependency.resolve(constraintWidget.getBaselineDistance());
                }
            }
        }
    }

    public void invalidateGraph() {
        this.mNeedBuildGraph = true;
    }

    public void invalidateMeasures() {
        this.mNeedRedoMeasures = true;
    }

    public void buildGraph() {
        buildGraph(this.mRuns);
        this.mGroups.clear();
        RunGroup.index = 0;
        findGroup(this.container.horizontalRun, 0, this.mGroups);
        findGroup(this.container.verticalRun, 1, this.mGroups);
        this.mNeedBuildGraph = false;
    }

    public void buildGraph(ArrayList arrayList) {
        arrayList.clear();
        this.mContainer.horizontalRun.clear();
        this.mContainer.verticalRun.clear();
        arrayList.add(this.mContainer.horizontalRun);
        arrayList.add(this.mContainer.verticalRun);
        Iterator it = this.mContainer.mChildren.iterator();
        HashSet hashSet = null;
        while (it.hasNext()) {
            ConstraintWidget constraintWidget = (ConstraintWidget) it.next();
            if (constraintWidget instanceof Guideline) {
                arrayList.add(new GuidelineReference(constraintWidget));
            } else {
                if (constraintWidget.isInHorizontalChain()) {
                    if (constraintWidget.horizontalChainRun == null) {
                        constraintWidget.horizontalChainRun = new ChainRun(constraintWidget, 0);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(constraintWidget.horizontalChainRun);
                } else {
                    arrayList.add(constraintWidget.horizontalRun);
                }
                if (constraintWidget.isInVerticalChain()) {
                    if (constraintWidget.verticalChainRun == null) {
                        constraintWidget.verticalChainRun = new ChainRun(constraintWidget, 1);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(constraintWidget.verticalChainRun);
                } else {
                    arrayList.add(constraintWidget.verticalRun);
                }
                if (constraintWidget instanceof HelperWidget) {
                    arrayList.add(new HelperReferences(constraintWidget));
                }
            }
        }
        if (hashSet != null) {
            arrayList.addAll(hashSet);
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            ((WidgetRun) it2.next()).clear();
        }
        Iterator it3 = arrayList.iterator();
        while (it3.hasNext()) {
            WidgetRun widgetRun = (WidgetRun) it3.next();
            if (widgetRun.widget != this.mContainer) {
                widgetRun.apply();
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: androidx.constraintlayout.core.widgets.analyzer.DependencyNode} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: androidx.constraintlayout.core.widgets.analyzer.DependencyNode} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void applyGroup(androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9, int r10, int r11, androidx.constraintlayout.core.widgets.analyzer.DependencyNode r12, java.util.ArrayList r13, androidx.constraintlayout.core.widgets.analyzer.RunGroup r14) {
        /*
            r8 = this;
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r9 = r9.run
            androidx.constraintlayout.core.widgets.analyzer.RunGroup r0 = r9.runGroup
            if (r0 != 0) goto L_0x0107
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r0 = r8.container
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r1 = r0.horizontalRun
            if (r9 == r1) goto L_0x0107
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r0 = r0.verticalRun
            if (r9 != r0) goto L_0x0012
            goto L_0x0107
        L_0x0012:
            if (r14 != 0) goto L_0x001c
            androidx.constraintlayout.core.widgets.analyzer.RunGroup r14 = new androidx.constraintlayout.core.widgets.analyzer.RunGroup
            r14.<init>(r9, r11)
            r13.add(r14)
        L_0x001c:
            r9.runGroup = r14
            r14.add(r9)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r11 = r9.start
            java.util.List r11 = r11.dependencies
            java.util.Iterator r11 = r11.iterator()
        L_0x0029:
            boolean r0 = r11.hasNext()
            if (r0 == 0) goto L_0x0046
            java.lang.Object r0 = r11.next()
            androidx.constraintlayout.core.widgets.analyzer.Dependency r0 = (androidx.constraintlayout.core.widgets.analyzer.Dependency) r0
            boolean r1 = r0 instanceof androidx.constraintlayout.core.widgets.analyzer.DependencyNode
            if (r1 == 0) goto L_0x0029
            r1 = r0
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = (androidx.constraintlayout.core.widgets.analyzer.DependencyNode) r1
            r3 = 0
            r0 = r8
            r2 = r10
            r4 = r12
            r5 = r13
            r6 = r14
            r0.applyGroup(r1, r2, r3, r4, r5, r6)
            goto L_0x0029
        L_0x0046:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r11 = r9.end
            java.util.List r11 = r11.dependencies
            java.util.Iterator r11 = r11.iterator()
        L_0x004e:
            boolean r0 = r11.hasNext()
            if (r0 == 0) goto L_0x006b
            java.lang.Object r0 = r11.next()
            androidx.constraintlayout.core.widgets.analyzer.Dependency r0 = (androidx.constraintlayout.core.widgets.analyzer.Dependency) r0
            boolean r1 = r0 instanceof androidx.constraintlayout.core.widgets.analyzer.DependencyNode
            if (r1 == 0) goto L_0x004e
            r1 = r0
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = (androidx.constraintlayout.core.widgets.analyzer.DependencyNode) r1
            r3 = 1
            r0 = r8
            r2 = r10
            r4 = r12
            r5 = r13
            r6 = r14
            r0.applyGroup(r1, r2, r3, r4, r5, r6)
            goto L_0x004e
        L_0x006b:
            r11 = 1
            if (r10 != r11) goto L_0x009a
            boolean r0 = r9 instanceof androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun
            if (r0 == 0) goto L_0x009a
            r0 = r9
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r0 = (androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun) r0
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r0 = r0.baseline
            java.util.List r0 = r0.dependencies
            java.util.Iterator r7 = r0.iterator()
        L_0x007d:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto L_0x009a
            java.lang.Object r0 = r7.next()
            androidx.constraintlayout.core.widgets.analyzer.Dependency r0 = (androidx.constraintlayout.core.widgets.analyzer.Dependency) r0
            boolean r1 = r0 instanceof androidx.constraintlayout.core.widgets.analyzer.DependencyNode
            if (r1 == 0) goto L_0x007d
            r1 = r0
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = (androidx.constraintlayout.core.widgets.analyzer.DependencyNode) r1
            r3 = 2
            r0 = r8
            r2 = r10
            r4 = r12
            r5 = r13
            r6 = r14
            r0.applyGroup(r1, r2, r3, r4, r5, r6)
            goto L_0x007d
        L_0x009a:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r0 = r9.start
            java.util.List r0 = r0.targets
            java.util.Iterator r7 = r0.iterator()
        L_0x00a2:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto L_0x00bd
            java.lang.Object r0 = r7.next()
            r1 = r0
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = (androidx.constraintlayout.core.widgets.analyzer.DependencyNode) r1
            if (r1 != r12) goto L_0x00b3
            r14.dual = r11
        L_0x00b3:
            r3 = 0
            r0 = r8
            r2 = r10
            r4 = r12
            r5 = r13
            r6 = r14
            r0.applyGroup(r1, r2, r3, r4, r5, r6)
            goto L_0x00a2
        L_0x00bd:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r0 = r9.end
            java.util.List r0 = r0.targets
            java.util.Iterator r7 = r0.iterator()
        L_0x00c5:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto L_0x00e0
            java.lang.Object r0 = r7.next()
            r1 = r0
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = (androidx.constraintlayout.core.widgets.analyzer.DependencyNode) r1
            if (r1 != r12) goto L_0x00d6
            r14.dual = r11
        L_0x00d6:
            r3 = 1
            r0 = r8
            r2 = r10
            r4 = r12
            r5 = r13
            r6 = r14
            r0.applyGroup(r1, r2, r3, r4, r5, r6)
            goto L_0x00c5
        L_0x00e0:
            if (r10 != r11) goto L_0x0107
            boolean r11 = r9 instanceof androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun
            if (r11 == 0) goto L_0x0107
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r9 = (androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun) r9
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r9.baseline
            java.util.List r9 = r9.targets
            java.util.Iterator r9 = r9.iterator()
        L_0x00f0:
            boolean r11 = r9.hasNext()
            if (r11 == 0) goto L_0x0107
            java.lang.Object r11 = r9.next()
            r1 = r11
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = (androidx.constraintlayout.core.widgets.analyzer.DependencyNode) r1
            r3 = 2
            r0 = r8
            r2 = r10
            r4 = r12
            r5 = r13
            r6 = r14
            r0.applyGroup(r1, r2, r3, r4, r5, r6)
            goto L_0x00f0
        L_0x0107:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.DependencyGraph.applyGroup(androidx.constraintlayout.core.widgets.analyzer.DependencyNode, int, int, androidx.constraintlayout.core.widgets.analyzer.DependencyNode, java.util.ArrayList, androidx.constraintlayout.core.widgets.analyzer.RunGroup):void");
    }

    private void findGroup(WidgetRun widgetRun, int i, ArrayList arrayList) {
        for (Dependency dependency : widgetRun.start.dependencies) {
            if (dependency instanceof DependencyNode) {
                applyGroup((DependencyNode) dependency, i, 0, widgetRun.end, arrayList, (RunGroup) null);
            } else if (dependency instanceof WidgetRun) {
                applyGroup(((WidgetRun) dependency).start, i, 0, widgetRun.end, arrayList, (RunGroup) null);
            }
        }
        for (Dependency dependency2 : widgetRun.end.dependencies) {
            if (dependency2 instanceof DependencyNode) {
                applyGroup((DependencyNode) dependency2, i, 1, widgetRun.start, arrayList, (RunGroup) null);
            } else if (dependency2 instanceof WidgetRun) {
                applyGroup(((WidgetRun) dependency2).end, i, 1, widgetRun.start, arrayList, (RunGroup) null);
            }
        }
        if (i == 1) {
            for (Dependency dependency3 : ((VerticalWidgetRun) widgetRun).baseline.dependencies) {
                if (dependency3 instanceof DependencyNode) {
                    applyGroup((DependencyNode) dependency3, i, 2, (DependencyNode) null, arrayList, (RunGroup) null);
                }
            }
        }
    }
}
