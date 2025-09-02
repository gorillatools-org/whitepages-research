package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import java.util.ArrayList;
import java.util.Iterator;

public class ChainRun extends WidgetRun {
    private int chainStyle;
    ArrayList widgets = new ArrayList();

    public ChainRun(ConstraintWidget constraintWidget, int i) {
        super(constraintWidget);
        this.orientation = i;
        build();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ChainRun ");
        sb.append(this.orientation == 0 ? "horizontal : " : "vertical : ");
        Iterator it = this.widgets.iterator();
        while (it.hasNext()) {
            sb.append("<");
            sb.append((WidgetRun) it.next());
            sb.append("> ");
        }
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public boolean supportsWrapComputation() {
        int size = this.widgets.size();
        for (int i = 0; i < size; i++) {
            if (!((WidgetRun) this.widgets.get(i)).supportsWrapComputation()) {
                return false;
            }
        }
        return true;
    }

    public long getWrapDimension() {
        int size = this.widgets.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            WidgetRun widgetRun = (WidgetRun) this.widgets.get(i);
            j = j + ((long) widgetRun.start.margin) + widgetRun.getWrapDimension() + ((long) widgetRun.end.margin);
        }
        return j;
    }

    private void build() {
        ConstraintWidget constraintWidget;
        ConstraintWidget constraintWidget2 = this.widget;
        ConstraintWidget previousChainMember = constraintWidget2.getPreviousChainMember(this.orientation);
        while (true) {
            ConstraintWidget constraintWidget3 = previousChainMember;
            constraintWidget = constraintWidget2;
            constraintWidget2 = constraintWidget3;
            if (constraintWidget2 == null) {
                break;
            }
            previousChainMember = constraintWidget2.getPreviousChainMember(this.orientation);
        }
        this.widget = constraintWidget;
        this.widgets.add(constraintWidget.getRun(this.orientation));
        ConstraintWidget nextChainMember = constraintWidget.getNextChainMember(this.orientation);
        while (nextChainMember != null) {
            this.widgets.add(nextChainMember.getRun(this.orientation));
            nextChainMember = nextChainMember.getNextChainMember(this.orientation);
        }
        Iterator it = this.widgets.iterator();
        while (it.hasNext()) {
            WidgetRun widgetRun = (WidgetRun) it.next();
            int i = this.orientation;
            if (i == 0) {
                widgetRun.widget.horizontalChainRun = this;
            } else if (i == 1) {
                widgetRun.widget.verticalChainRun = this;
            }
        }
        if (this.orientation == 0 && ((ConstraintWidgetContainer) this.widget.getParent()).isRtl() && this.widgets.size() > 1) {
            ArrayList arrayList = this.widgets;
            this.widget = ((WidgetRun) arrayList.get(arrayList.size() - 1)).widget;
        }
        this.chainStyle = this.orientation == 0 ? this.widget.getHorizontalChainStyle() : this.widget.getVerticalChainStyle();
    }

    /* access modifiers changed from: package-private */
    public void clear() {
        this.runGroup = null;
        Iterator it = this.widgets.iterator();
        while (it.hasNext()) {
            ((WidgetRun) it.next()).clear();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:58:0x00d7  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00e9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void update(androidx.constraintlayout.core.widgets.analyzer.Dependency r27) {
        /*
            r26 = this;
            r0 = r26
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r0.start
            boolean r1 = r1.resolved
            if (r1 == 0) goto L_0x0425
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r0.end
            boolean r1 = r1.resolved
            if (r1 != 0) goto L_0x0010
            goto L_0x0425
        L_0x0010:
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r0.widget
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r1.getParent()
            boolean r2 = r1 instanceof androidx.constraintlayout.core.widgets.ConstraintWidgetContainer
            if (r2 == 0) goto L_0x0021
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r1 = (androidx.constraintlayout.core.widgets.ConstraintWidgetContainer) r1
            boolean r1 = r1.isRtl()
            goto L_0x0022
        L_0x0021:
            r1 = 0
        L_0x0022:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r0.end
            int r2 = r2.value
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r4 = r0.start
            int r4 = r4.value
            int r2 = r2 - r4
            java.util.ArrayList r4 = r0.widgets
            int r4 = r4.size()
            r5 = 0
        L_0x0032:
            r6 = -1
            r7 = 8
            if (r5 >= r4) goto L_0x004a
            java.util.ArrayList r8 = r0.widgets
            java.lang.Object r8 = r8.get(r5)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r8 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r8
            androidx.constraintlayout.core.widgets.ConstraintWidget r8 = r8.widget
            int r8 = r8.getVisibility()
            if (r8 != r7) goto L_0x004b
            int r5 = r5 + 1
            goto L_0x0032
        L_0x004a:
            r5 = r6
        L_0x004b:
            int r8 = r4 + -1
            r9 = r8
        L_0x004e:
            if (r9 < 0) goto L_0x0064
            java.util.ArrayList r10 = r0.widgets
            java.lang.Object r10 = r10.get(r9)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r10 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r10
            androidx.constraintlayout.core.widgets.ConstraintWidget r10 = r10.widget
            int r10 = r10.getVisibility()
            if (r10 != r7) goto L_0x0063
            int r9 = r9 + -1
            goto L_0x004e
        L_0x0063:
            r6 = r9
        L_0x0064:
            r9 = 0
        L_0x0065:
            r11 = 2
            if (r9 >= r11) goto L_0x0109
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
        L_0x006f:
            if (r13 >= r4) goto L_0x00fb
            java.util.ArrayList r3 = r0.widgets
            java.lang.Object r3 = r3.get(r13)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r3 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r3
            androidx.constraintlayout.core.widgets.ConstraintWidget r11 = r3.widget
            int r11 = r11.getVisibility()
            if (r11 != r7) goto L_0x0083
            goto L_0x00f4
        L_0x0083:
            int r16 = r16 + 1
            if (r13 <= 0) goto L_0x008e
            if (r13 < r5) goto L_0x008e
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r11 = r3.start
            int r11 = r11.margin
            int r14 = r14 + r11
        L_0x008e:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r11 = r3.dimension
            int r7 = r11.value
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r10 = r3.dimensionBehavior
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r12 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r10 == r12) goto L_0x009a
            r10 = 1
            goto L_0x009b
        L_0x009a:
            r10 = 0
        L_0x009b:
            if (r10 == 0) goto L_0x00bd
            int r11 = r0.orientation
            if (r11 != 0) goto L_0x00ac
            androidx.constraintlayout.core.widgets.ConstraintWidget r12 = r3.widget
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r12 = r12.horizontalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r12 = r12.dimension
            boolean r12 = r12.resolved
            if (r12 != 0) goto L_0x00ac
            return
        L_0x00ac:
            r12 = 1
            if (r11 != r12) goto L_0x00ba
            androidx.constraintlayout.core.widgets.ConstraintWidget r11 = r3.widget
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r11 = r11.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r11 = r11.dimension
            boolean r11 = r11.resolved
            if (r11 != 0) goto L_0x00ba
            return
        L_0x00ba:
            r19 = r7
            goto L_0x00d3
        L_0x00bd:
            r19 = r7
            r12 = 1
            int r7 = r3.matchConstraintsType
            if (r7 != r12) goto L_0x00cc
            if (r9 != 0) goto L_0x00cc
            int r7 = r11.wrapValue
            int r15 = r15 + 1
        L_0x00ca:
            r10 = 1
            goto L_0x00d5
        L_0x00cc:
            boolean r7 = r11.resolved
            if (r7 == 0) goto L_0x00d3
            r7 = r19
            goto L_0x00ca
        L_0x00d3:
            r7 = r19
        L_0x00d5:
            if (r10 != 0) goto L_0x00e9
            int r15 = r15 + 1
            androidx.constraintlayout.core.widgets.ConstraintWidget r7 = r3.widget
            float[] r7 = r7.mWeight
            int r10 = r0.orientation
            r7 = r7[r10]
            r10 = 0
            int r11 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r11 < 0) goto L_0x00ea
            float r17 = r17 + r7
            goto L_0x00ea
        L_0x00e9:
            int r14 = r14 + r7
        L_0x00ea:
            if (r13 >= r8) goto L_0x00f4
            if (r13 >= r6) goto L_0x00f4
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r3 = r3.end
            int r3 = r3.margin
            int r3 = -r3
            int r14 = r14 + r3
        L_0x00f4:
            int r13 = r13 + 1
            r7 = 8
            r11 = 2
            goto L_0x006f
        L_0x00fb:
            if (r14 < r2) goto L_0x0106
            if (r15 != 0) goto L_0x0100
            goto L_0x0106
        L_0x0100:
            int r9 = r9 + 1
            r7 = 8
            goto L_0x0065
        L_0x0106:
            r3 = r16
            goto L_0x010e
        L_0x0109:
            r3 = 0
            r14 = 0
            r15 = 0
            r17 = 0
        L_0x010e:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r7 = r0.start
            int r7 = r7.value
            if (r1 == 0) goto L_0x0118
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r7 = r0.end
            int r7 = r7.value
        L_0x0118:
            r9 = 1056964608(0x3f000000, float:0.5)
            if (r14 <= r2) goto L_0x012f
            r10 = 1073741824(0x40000000, float:2.0)
            if (r1 == 0) goto L_0x0128
            int r11 = r14 - r2
            float r11 = (float) r11
            float r11 = r11 / r10
            float r11 = r11 + r9
            int r10 = (int) r11
            int r7 = r7 + r10
            goto L_0x012f
        L_0x0128:
            int r11 = r14 - r2
            float r11 = (float) r11
            float r11 = r11 / r10
            float r11 = r11 + r9
            int r10 = (int) r11
            int r7 = r7 - r10
        L_0x012f:
            if (r15 <= 0) goto L_0x0223
            int r10 = r2 - r14
            float r10 = (float) r10
            float r11 = (float) r15
            float r11 = r10 / r11
            float r11 = r11 + r9
            int r11 = (int) r11
            r12 = 0
            r13 = 0
        L_0x013b:
            if (r12 >= r4) goto L_0x01d7
            java.util.ArrayList r9 = r0.widgets
            java.lang.Object r9 = r9.get(r12)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r9 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r9
            r19 = r11
            androidx.constraintlayout.core.widgets.ConstraintWidget r11 = r9.widget
            int r11 = r11.getVisibility()
            r20 = r14
            r14 = 8
            if (r11 != r14) goto L_0x015d
        L_0x0153:
            r23 = r1
            r24 = r3
            r21 = r7
            r22 = r10
            goto L_0x01c5
        L_0x015d:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r11 = r9.dimensionBehavior
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r14 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r11 != r14) goto L_0x0153
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r11 = r9.dimension
            boolean r14 = r11.resolved
            if (r14 != 0) goto L_0x0153
            r14 = 0
            int r18 = (r17 > r14 ? 1 : (r17 == r14 ? 0 : -1))
            if (r18 <= 0) goto L_0x0180
            androidx.constraintlayout.core.widgets.ConstraintWidget r14 = r9.widget
            float[] r14 = r14.mWeight
            r21 = r7
            int r7 = r0.orientation
            r7 = r14[r7]
            float r7 = r7 * r10
            float r7 = r7 / r17
            r14 = 1056964608(0x3f000000, float:0.5)
            float r7 = r7 + r14
            int r7 = (int) r7
            goto L_0x0184
        L_0x0180:
            r21 = r7
            r7 = r19
        L_0x0184:
            int r14 = r0.orientation
            if (r14 != 0) goto L_0x0193
            androidx.constraintlayout.core.widgets.ConstraintWidget r14 = r9.widget
            r22 = r10
            int r10 = r14.mMatchConstraintMaxWidth
            int r14 = r14.mMatchConstraintMinWidth
            r23 = r1
            goto L_0x01a2
        L_0x0193:
            r22 = r10
            androidx.constraintlayout.core.widgets.ConstraintWidget r10 = r9.widget
            int r14 = r10.mMatchConstraintMaxHeight
            int r10 = r10.mMatchConstraintMinHeight
            r23 = r1
            r25 = r14
            r14 = r10
            r10 = r25
        L_0x01a2:
            int r1 = r9.matchConstraintsType
            r24 = r3
            r3 = 1
            if (r1 != r3) goto L_0x01b0
            int r1 = r11.wrapValue
            int r1 = java.lang.Math.min(r7, r1)
            goto L_0x01b1
        L_0x01b0:
            r1 = r7
        L_0x01b1:
            int r1 = java.lang.Math.max(r14, r1)
            if (r10 <= 0) goto L_0x01bb
            int r1 = java.lang.Math.min(r10, r1)
        L_0x01bb:
            if (r1 == r7) goto L_0x01c0
            int r13 = r13 + 1
            r7 = r1
        L_0x01c0:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r1 = r9.dimension
            r1.resolve(r7)
        L_0x01c5:
            int r12 = r12 + 1
            r11 = r19
            r14 = r20
            r7 = r21
            r10 = r22
            r1 = r23
            r3 = r24
            r9 = 1056964608(0x3f000000, float:0.5)
            goto L_0x013b
        L_0x01d7:
            r23 = r1
            r24 = r3
            r21 = r7
            r20 = r14
            if (r13 <= 0) goto L_0x0214
            int r15 = r15 - r13
            r1 = 0
            r14 = 0
        L_0x01e4:
            if (r1 >= r4) goto L_0x0216
            java.util.ArrayList r3 = r0.widgets
            java.lang.Object r3 = r3.get(r1)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r3 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r3
            androidx.constraintlayout.core.widgets.ConstraintWidget r7 = r3.widget
            int r7 = r7.getVisibility()
            r9 = 8
            if (r7 != r9) goto L_0x01f9
            goto L_0x0211
        L_0x01f9:
            if (r1 <= 0) goto L_0x0202
            if (r1 < r5) goto L_0x0202
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r7 = r3.start
            int r7 = r7.margin
            int r14 = r14 + r7
        L_0x0202:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r7 = r3.dimension
            int r7 = r7.value
            int r14 = r14 + r7
            if (r1 >= r8) goto L_0x0211
            if (r1 >= r6) goto L_0x0211
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r3 = r3.end
            int r3 = r3.margin
            int r3 = -r3
            int r14 = r14 + r3
        L_0x0211:
            int r1 = r1 + 1
            goto L_0x01e4
        L_0x0214:
            r14 = r20
        L_0x0216:
            int r1 = r0.chainStyle
            r3 = 2
            if (r1 != r3) goto L_0x0221
            if (r13 != 0) goto L_0x0221
            r1 = 0
            r0.chainStyle = r1
            goto L_0x022d
        L_0x0221:
            r1 = 0
            goto L_0x022d
        L_0x0223:
            r23 = r1
            r24 = r3
            r21 = r7
            r20 = r14
            r1 = 0
            r3 = 2
        L_0x022d:
            if (r14 <= r2) goto L_0x0231
            r0.chainStyle = r3
        L_0x0231:
            if (r24 <= 0) goto L_0x0239
            if (r15 != 0) goto L_0x0239
            if (r5 != r6) goto L_0x0239
            r0.chainStyle = r3
        L_0x0239:
            int r3 = r0.chainStyle
            r7 = 1
            if (r3 != r7) goto L_0x02de
            r9 = r24
            if (r9 <= r7) goto L_0x0247
            int r2 = r2 - r14
            int r3 = r9 + -1
            int r2 = r2 / r3
            goto L_0x024e
        L_0x0247:
            if (r9 != r7) goto L_0x024d
            int r2 = r2 - r14
            r3 = 2
            int r2 = r2 / r3
            goto L_0x024e
        L_0x024d:
            r2 = r1
        L_0x024e:
            if (r15 <= 0) goto L_0x0251
            r2 = r1
        L_0x0251:
            r3 = r1
            r7 = r21
        L_0x0254:
            if (r3 >= r4) goto L_0x0425
            if (r23 == 0) goto L_0x025d
            int r1 = r3 + 1
            int r1 = r4 - r1
            goto L_0x025e
        L_0x025d:
            r1 = r3
        L_0x025e:
            java.util.ArrayList r9 = r0.widgets
            java.lang.Object r1 = r9.get(r1)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r1 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r1
            androidx.constraintlayout.core.widgets.ConstraintWidget r9 = r1.widget
            int r9 = r9.getVisibility()
            r10 = 8
            if (r9 != r10) goto L_0x027b
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.start
            r9.resolve(r7)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r1.end
            r1.resolve(r7)
            goto L_0x02da
        L_0x027b:
            if (r3 <= 0) goto L_0x0282
            if (r23 == 0) goto L_0x0281
            int r7 = r7 - r2
            goto L_0x0282
        L_0x0281:
            int r7 = r7 + r2
        L_0x0282:
            if (r3 <= 0) goto L_0x0293
            if (r3 < r5) goto L_0x0293
            if (r23 == 0) goto L_0x028e
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.start
            int r9 = r9.margin
            int r7 = r7 - r9
            goto L_0x0293
        L_0x028e:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.start
            int r9 = r9.margin
            int r7 = r7 + r9
        L_0x0293:
            if (r23 == 0) goto L_0x029b
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.end
            r9.resolve(r7)
            goto L_0x02a0
        L_0x029b:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.start
            r9.resolve(r7)
        L_0x02a0:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r9 = r1.dimension
            int r10 = r9.value
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r11 = r1.dimensionBehavior
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r12 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r11 != r12) goto L_0x02b1
            int r11 = r1.matchConstraintsType
            r12 = 1
            if (r11 != r12) goto L_0x02b1
            int r10 = r9.wrapValue
        L_0x02b1:
            if (r23 == 0) goto L_0x02b5
            int r7 = r7 - r10
            goto L_0x02b6
        L_0x02b5:
            int r7 = r7 + r10
        L_0x02b6:
            if (r23 == 0) goto L_0x02bf
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.start
            r9.resolve(r7)
        L_0x02bd:
            r9 = 1
            goto L_0x02c5
        L_0x02bf:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.end
            r9.resolve(r7)
            goto L_0x02bd
        L_0x02c5:
            r1.resolved = r9
            if (r3 >= r8) goto L_0x02da
            if (r3 >= r6) goto L_0x02da
            if (r23 == 0) goto L_0x02d4
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r1.end
            int r1 = r1.margin
            int r1 = -r1
            int r7 = r7 - r1
            goto L_0x02da
        L_0x02d4:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r1.end
            int r1 = r1.margin
            int r1 = -r1
            int r7 = r7 + r1
        L_0x02da:
            int r3 = r3 + 1
            goto L_0x0254
        L_0x02de:
            r9 = r24
            if (r3 != 0) goto L_0x0375
            int r2 = r2 - r14
            r3 = 1
            int r7 = r9 + 1
            int r2 = r2 / r7
            if (r15 <= 0) goto L_0x02ea
            r2 = r1
        L_0x02ea:
            r3 = r1
            r7 = r21
        L_0x02ed:
            if (r3 >= r4) goto L_0x0425
            if (r23 == 0) goto L_0x02f6
            int r1 = r3 + 1
            int r1 = r4 - r1
            goto L_0x02f7
        L_0x02f6:
            r1 = r3
        L_0x02f7:
            java.util.ArrayList r9 = r0.widgets
            java.lang.Object r1 = r9.get(r1)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r1 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r1
            androidx.constraintlayout.core.widgets.ConstraintWidget r9 = r1.widget
            int r9 = r9.getVisibility()
            r10 = 8
            if (r9 != r10) goto L_0x0314
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.start
            r9.resolve(r7)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r1.end
            r1.resolve(r7)
            goto L_0x0371
        L_0x0314:
            if (r23 == 0) goto L_0x0318
            int r7 = r7 - r2
            goto L_0x0319
        L_0x0318:
            int r7 = r7 + r2
        L_0x0319:
            if (r3 <= 0) goto L_0x032a
            if (r3 < r5) goto L_0x032a
            if (r23 == 0) goto L_0x0325
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.start
            int r9 = r9.margin
            int r7 = r7 - r9
            goto L_0x032a
        L_0x0325:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.start
            int r9 = r9.margin
            int r7 = r7 + r9
        L_0x032a:
            if (r23 == 0) goto L_0x0332
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.end
            r9.resolve(r7)
            goto L_0x0337
        L_0x0332:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.start
            r9.resolve(r7)
        L_0x0337:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r9 = r1.dimension
            int r10 = r9.value
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r11 = r1.dimensionBehavior
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r12 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r11 != r12) goto L_0x034c
            int r11 = r1.matchConstraintsType
            r12 = 1
            if (r11 != r12) goto L_0x034c
            int r9 = r9.wrapValue
            int r10 = java.lang.Math.min(r10, r9)
        L_0x034c:
            if (r23 == 0) goto L_0x0350
            int r7 = r7 - r10
            goto L_0x0351
        L_0x0350:
            int r7 = r7 + r10
        L_0x0351:
            if (r23 == 0) goto L_0x0359
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.start
            r9.resolve(r7)
            goto L_0x035e
        L_0x0359:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.end
            r9.resolve(r7)
        L_0x035e:
            if (r3 >= r8) goto L_0x0371
            if (r3 >= r6) goto L_0x0371
            if (r23 == 0) goto L_0x036b
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r1.end
            int r1 = r1.margin
            int r1 = -r1
            int r7 = r7 - r1
            goto L_0x0371
        L_0x036b:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r1.end
            int r1 = r1.margin
            int r1 = -r1
            int r7 = r7 + r1
        L_0x0371:
            int r3 = r3 + 1
            goto L_0x02ed
        L_0x0375:
            r7 = 2
            if (r3 != r7) goto L_0x0425
            int r3 = r0.orientation
            if (r3 != 0) goto L_0x0383
            androidx.constraintlayout.core.widgets.ConstraintWidget r3 = r0.widget
            float r3 = r3.getHorizontalBiasPercent()
            goto L_0x0389
        L_0x0383:
            androidx.constraintlayout.core.widgets.ConstraintWidget r3 = r0.widget
            float r3 = r3.getVerticalBiasPercent()
        L_0x0389:
            if (r23 == 0) goto L_0x038f
            r7 = 1065353216(0x3f800000, float:1.0)
            float r3 = r7 - r3
        L_0x038f:
            int r2 = r2 - r14
            float r2 = (float) r2
            float r2 = r2 * r3
            r3 = 1056964608(0x3f000000, float:0.5)
            float r2 = r2 + r3
            int r2 = (int) r2
            if (r2 < 0) goto L_0x039a
            if (r15 <= 0) goto L_0x039b
        L_0x039a:
            r2 = r1
        L_0x039b:
            if (r23 == 0) goto L_0x03a0
            int r7 = r21 - r2
            goto L_0x03a2
        L_0x03a0:
            int r7 = r21 + r2
        L_0x03a2:
            r3 = r1
        L_0x03a3:
            if (r3 >= r4) goto L_0x0425
            if (r23 == 0) goto L_0x03ac
            int r1 = r3 + 1
            int r1 = r4 - r1
            goto L_0x03ad
        L_0x03ac:
            r1 = r3
        L_0x03ad:
            java.util.ArrayList r2 = r0.widgets
            java.lang.Object r1 = r2.get(r1)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r1 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r1
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r1.widget
            int r2 = r2.getVisibility()
            r9 = 8
            if (r2 != r9) goto L_0x03cb
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r1.start
            r2.resolve(r7)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r1.end
            r1.resolve(r7)
            r12 = 1
            goto L_0x0421
        L_0x03cb:
            if (r3 <= 0) goto L_0x03dc
            if (r3 < r5) goto L_0x03dc
            if (r23 == 0) goto L_0x03d7
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r1.start
            int r2 = r2.margin
            int r7 = r7 - r2
            goto L_0x03dc
        L_0x03d7:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r1.start
            int r2 = r2.margin
            int r7 = r7 + r2
        L_0x03dc:
            if (r23 == 0) goto L_0x03e4
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r1.end
            r2.resolve(r7)
            goto L_0x03e9
        L_0x03e4:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r1.start
            r2.resolve(r7)
        L_0x03e9:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r2 = r1.dimension
            int r10 = r2.value
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r11 = r1.dimensionBehavior
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r12 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r11 != r12) goto L_0x03fb
            int r11 = r1.matchConstraintsType
            r12 = 1
            if (r11 != r12) goto L_0x03fc
            int r10 = r2.wrapValue
            goto L_0x03fc
        L_0x03fb:
            r12 = 1
        L_0x03fc:
            if (r23 == 0) goto L_0x0400
            int r7 = r7 - r10
            goto L_0x0401
        L_0x0400:
            int r7 = r7 + r10
        L_0x0401:
            if (r23 == 0) goto L_0x0409
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r1.start
            r2.resolve(r7)
            goto L_0x040e
        L_0x0409:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r1.end
            r2.resolve(r7)
        L_0x040e:
            if (r3 >= r8) goto L_0x0421
            if (r3 >= r6) goto L_0x0421
            if (r23 == 0) goto L_0x041b
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r1.end
            int r1 = r1.margin
            int r1 = -r1
            int r7 = r7 - r1
            goto L_0x0421
        L_0x041b:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r1.end
            int r1 = r1.margin
            int r1 = -r1
            int r7 = r7 + r1
        L_0x0421:
            int r3 = r3 + 1
            goto L_0x03a3
        L_0x0425:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.ChainRun.update(androidx.constraintlayout.core.widgets.analyzer.Dependency):void");
    }

    public void applyToWidget() {
        for (int i = 0; i < this.widgets.size(); i++) {
            ((WidgetRun) this.widgets.get(i)).applyToWidget();
        }
    }

    private ConstraintWidget getFirstVisibleWidget() {
        for (int i = 0; i < this.widgets.size(); i++) {
            WidgetRun widgetRun = (WidgetRun) this.widgets.get(i);
            if (widgetRun.widget.getVisibility() != 8) {
                return widgetRun.widget;
            }
        }
        return null;
    }

    private ConstraintWidget getLastVisibleWidget() {
        for (int size = this.widgets.size() - 1; size >= 0; size--) {
            WidgetRun widgetRun = (WidgetRun) this.widgets.get(size);
            if (widgetRun.widget.getVisibility() != 8) {
                return widgetRun.widget;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void apply() {
        Iterator it = this.widgets.iterator();
        while (it.hasNext()) {
            ((WidgetRun) it.next()).apply();
        }
        int size = this.widgets.size();
        if (size >= 1) {
            ConstraintWidget constraintWidget = ((WidgetRun) this.widgets.get(0)).widget;
            ConstraintWidget constraintWidget2 = ((WidgetRun) this.widgets.get(size - 1)).widget;
            if (this.orientation == 0) {
                ConstraintAnchor constraintAnchor = constraintWidget.mLeft;
                ConstraintAnchor constraintAnchor2 = constraintWidget2.mRight;
                DependencyNode target = getTarget(constraintAnchor, 0);
                int margin = constraintAnchor.getMargin();
                ConstraintWidget firstVisibleWidget = getFirstVisibleWidget();
                if (firstVisibleWidget != null) {
                    margin = firstVisibleWidget.mLeft.getMargin();
                }
                if (target != null) {
                    addTarget(this.start, target, margin);
                }
                DependencyNode target2 = getTarget(constraintAnchor2, 0);
                int margin2 = constraintAnchor2.getMargin();
                ConstraintWidget lastVisibleWidget = getLastVisibleWidget();
                if (lastVisibleWidget != null) {
                    margin2 = lastVisibleWidget.mRight.getMargin();
                }
                if (target2 != null) {
                    addTarget(this.end, target2, -margin2);
                }
            } else {
                ConstraintAnchor constraintAnchor3 = constraintWidget.mTop;
                ConstraintAnchor constraintAnchor4 = constraintWidget2.mBottom;
                DependencyNode target3 = getTarget(constraintAnchor3, 1);
                int margin3 = constraintAnchor3.getMargin();
                ConstraintWidget firstVisibleWidget2 = getFirstVisibleWidget();
                if (firstVisibleWidget2 != null) {
                    margin3 = firstVisibleWidget2.mTop.getMargin();
                }
                if (target3 != null) {
                    addTarget(this.start, target3, margin3);
                }
                DependencyNode target4 = getTarget(constraintAnchor4, 1);
                int margin4 = constraintAnchor4.getMargin();
                ConstraintWidget lastVisibleWidget2 = getLastVisibleWidget();
                if (lastVisibleWidget2 != null) {
                    margin4 = lastVisibleWidget2.mBottom.getMargin();
                }
                if (target4 != null) {
                    addTarget(this.end, target4, -margin4);
                }
            }
            this.start.updateDelegate = this;
            this.end.updateDelegate = this;
        }
    }
}
