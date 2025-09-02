package com.swmansion.gesturehandler;

import android.view.View;
import android.view.ViewParent;
import com.horcrux.svg.SvgView;
import com.horcrux.svg.VirtualView;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public abstract class RNSVGHitTester {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final SvgView getRootSvgView(View view) {
            SvgView svgView;
            SvgView svgView2;
            if (view instanceof VirtualView) {
                svgView = ((VirtualView) view).getSvgView();
                Intrinsics.checkNotNull(svgView);
            } else {
                Intrinsics.checkNotNull(view, "null cannot be cast to non-null type com.horcrux.svg.SvgView");
                svgView = (SvgView) view;
            }
            while (true) {
                ViewParent parent = svgView.getParent();
                Intrinsics.checkNotNullExpressionValue(parent, "getParent(...)");
                if (!isSvgElement(parent)) {
                    return svgView;
                }
                if (svgView.getParent() instanceof VirtualView) {
                    ViewParent parent2 = svgView.getParent();
                    Intrinsics.checkNotNull(parent2, "null cannot be cast to non-null type com.horcrux.svg.VirtualView");
                    svgView2 = ((VirtualView) parent2).getSvgView();
                    Intrinsics.checkNotNull(svgView2);
                } else {
                    ViewParent parent3 = svgView.getParent();
                    Intrinsics.checkNotNull(parent3, "null cannot be cast to non-null type com.horcrux.svg.SvgView");
                    svgView2 = (SvgView) parent3;
                }
            }
        }

        public final boolean isSvgElement(Object obj) {
            Intrinsics.checkNotNullParameter(obj, "view");
            return (obj instanceof VirtualView) || (obj instanceof SvgView);
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x005b  */
        /* JADX WARNING: Removed duplicated region for block: B:21:0x007a  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean hitTest(android.view.View r11, float r12, float r13) {
            /*
                r10 = this;
                java.lang.String r0 = "view"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
                com.horcrux.svg.SvgView r0 = r10.getRootSvgView(r11)
                r1 = 0
                int[] r2 = new int[]{r1, r1}
                int[] r3 = new int[]{r1, r1}
                r11.getLocationOnScreen(r2)
                r0.getLocationOnScreen(r3)
                r4 = r2[r1]
                float r4 = (float) r4
                float r4 = r4 + r12
                r5 = r3[r1]
                float r5 = (float) r5
                float r4 = r4 - r5
                r5 = 1
                r2 = r2[r5]
                float r2 = (float) r2
                float r2 = r2 + r13
                r3 = r3[r5]
                float r3 = (float) r3
                float r2 = r2 - r3
                int r0 = r0.reactTagForTouch(r4, r2)
                int r2 = r11.getId()
                if (r2 != r0) goto L_0x0035
                r2 = r5
                goto L_0x0036
            L_0x0035:
                r2 = r1
            L_0x0036:
                int r3 = r11.getWidth()
                double r3 = (double) r3
                double r6 = (double) r12
                r8 = 0
                int r12 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
                if (r12 > 0) goto L_0x0056
                int r12 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
                if (r12 > 0) goto L_0x0056
                int r12 = r11.getHeight()
                double r3 = (double) r12
                double r12 = (double) r13
                int r6 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
                if (r6 > 0) goto L_0x0056
                int r12 = (r12 > r3 ? 1 : (r12 == r3 ? 0 : -1))
                if (r12 > 0) goto L_0x0056
                r12 = r5
                goto L_0x0057
            L_0x0056:
                r12 = r1
            L_0x0057:
                boolean r13 = r11 instanceof com.horcrux.svg.SvgView
                if (r13 == 0) goto L_0x007a
                android.view.ViewGroup r11 = (android.view.ViewGroup) r11
                kotlin.sequences.Sequence r11 = androidx.core.view.ViewGroupKt.getChildren(r11)
                com.swmansion.gesturehandler.RNSVGHitTester$Companion$$ExternalSyntheticLambda0 r13 = new com.swmansion.gesturehandler.RNSVGHitTester$Companion$$ExternalSyntheticLambda0
                r13.<init>()
                kotlin.sequences.Sequence r11 = kotlin.sequences.SequencesKt.map(r11, r13)
                java.lang.Integer r13 = java.lang.Integer.valueOf(r0)
                boolean r11 = kotlin.sequences.SequencesKt.contains(r11, r13)
                if (r2 != 0) goto L_0x0076
                if (r11 == 0) goto L_0x0079
            L_0x0076:
                if (r12 == 0) goto L_0x0079
                r1 = r5
            L_0x0079:
                return r1
            L_0x007a:
                if (r2 == 0) goto L_0x007f
                if (r12 == 0) goto L_0x007f
                r1 = r5
            L_0x007f:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.RNSVGHitTester.Companion.hitTest(android.view.View, float, float):boolean");
        }

        /* access modifiers changed from: private */
        public static final int hitTest$lambda$0(View view) {
            Intrinsics.checkNotNullParameter(view, "it");
            return view.getId();
        }
    }
}
