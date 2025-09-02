package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface;

public class RNSVGTSpanManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSVGTSpanManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGTSpanManagerDelegate(U u) {
        super(u);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v12, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v13, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v14, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v15, resolved type: boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setProperty(T r8, java.lang.String r9, java.lang.Object r10) {
        /*
            r7 = this;
            r0 = 1
            r9.hashCode()
            r1 = 0
            r2 = 1065353216(0x3f800000, float:1.0)
            r3 = 0
            r4 = 0
            r5 = -1
            int r6 = r9.hashCode()
            switch(r6) {
                case -1603134955: goto L_0x023c;
                case -1274492040: goto L_0x0231;
                case -1267206133: goto L_0x0226;
                case -1171891896: goto L_0x021b;
                case -1139902161: goto L_0x0210;
                case -1081239615: goto L_0x0205;
                case -993894751: goto L_0x01fa;
                case -933864895: goto L_0x01ef;
                case -933857362: goto L_0x01e1;
                case -925180581: goto L_0x01d3;
                case -891980232: goto L_0x01c5;
                case -734428249: goto L_0x01b7;
                case -729118945: goto L_0x01a9;
                case -416535885: goto L_0x019b;
                case -293492298: goto L_0x018d;
                case -53677816: goto L_0x017f;
                case -44578051: goto L_0x0171;
                case 120: goto L_0x0163;
                case 121: goto L_0x0155;
                case 3220: goto L_0x0147;
                case 3221: goto L_0x0139;
                case 3143043: goto L_0x012b;
                case 3148879: goto L_0x011d;
                case 3344108: goto L_0x010f;
                case 3373707: goto L_0x0101;
                case 78845486: goto L_0x00f3;
                case 94842723: goto L_0x00e5;
                case 104482996: goto L_0x00d7;
                case 217109576: goto L_0x00c9;
                case 275888445: goto L_0x00bb;
                case 365601008: goto L_0x00ad;
                case 401643183: goto L_0x009f;
                case 778043962: goto L_0x0091;
                case 917656469: goto L_0x0083;
                case 917735020: goto L_0x0075;
                case 951530617: goto L_0x0067;
                case 1027575302: goto L_0x0059;
                case 1637488243: goto L_0x004b;
                case 1671764162: goto L_0x003d;
                case 1790285174: goto L_0x002f;
                case 1847674614: goto L_0x0021;
                case 1924065902: goto L_0x0013;
                default: goto L_0x0011;
            }
        L_0x0011:
            goto L_0x0246
        L_0x0013:
            java.lang.String r6 = "strokeWidth"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x001d
            goto L_0x0246
        L_0x001d:
            r5 = 41
            goto L_0x0246
        L_0x0021:
            java.lang.String r6 = "responsible"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x002b
            goto L_0x0246
        L_0x002b:
            r5 = 40
            goto L_0x0246
        L_0x002f:
            java.lang.String r6 = "strokeLinejoin"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0039
            goto L_0x0246
        L_0x0039:
            r5 = 39
            goto L_0x0246
        L_0x003d:
            java.lang.String r6 = "display"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0047
            goto L_0x0246
        L_0x0047:
            r5 = 38
            goto L_0x0246
        L_0x004b:
            java.lang.String r6 = "textLength"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0055
            goto L_0x0246
        L_0x0055:
            r5 = 37
            goto L_0x0246
        L_0x0059:
            java.lang.String r6 = "strokeLinecap"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0063
            goto L_0x0246
        L_0x0063:
            r5 = 36
            goto L_0x0246
        L_0x0067:
            java.lang.String r6 = "content"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0071
            goto L_0x0246
        L_0x0071:
            r5 = 35
            goto L_0x0246
        L_0x0075:
            java.lang.String r6 = "clipRule"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x007f
            goto L_0x0246
        L_0x007f:
            r5 = 34
            goto L_0x0246
        L_0x0083:
            java.lang.String r6 = "clipPath"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x008d
            goto L_0x0246
        L_0x008d:
            r5 = 33
            goto L_0x0246
        L_0x0091:
            java.lang.String r6 = "inlineSize"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x009b
            goto L_0x0246
        L_0x009b:
            r5 = 32
            goto L_0x0246
        L_0x009f:
            java.lang.String r6 = "strokeDasharray"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00a9
            goto L_0x0246
        L_0x00a9:
            r5 = 31
            goto L_0x0246
        L_0x00ad:
            java.lang.String r6 = "fontSize"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00b7
            goto L_0x0246
        L_0x00b7:
            r5 = 30
            goto L_0x0246
        L_0x00bb:
            java.lang.String r6 = "baselineShift"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00c5
            goto L_0x0246
        L_0x00c5:
            r5 = 29
            goto L_0x0246
        L_0x00c9:
            java.lang.String r6 = "markerStart"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00d3
            goto L_0x0246
        L_0x00d3:
            r5 = 28
            goto L_0x0246
        L_0x00d7:
            java.lang.String r6 = "vectorEffect"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00e1
            goto L_0x0246
        L_0x00e1:
            r5 = 27
            goto L_0x0246
        L_0x00e5:
            java.lang.String r6 = "color"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00ef
            goto L_0x0246
        L_0x00ef:
            r5 = 26
            goto L_0x0246
        L_0x00f3:
            java.lang.String r6 = "strokeMiterlimit"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00fd
            goto L_0x0246
        L_0x00fd:
            r5 = 25
            goto L_0x0246
        L_0x0101:
            java.lang.String r6 = "name"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x010b
            goto L_0x0246
        L_0x010b:
            r5 = 24
            goto L_0x0246
        L_0x010f:
            java.lang.String r6 = "mask"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0119
            goto L_0x0246
        L_0x0119:
            r5 = 23
            goto L_0x0246
        L_0x011d:
            java.lang.String r6 = "font"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0127
            goto L_0x0246
        L_0x0127:
            r5 = 22
            goto L_0x0246
        L_0x012b:
            java.lang.String r6 = "fill"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0135
            goto L_0x0246
        L_0x0135:
            r5 = 21
            goto L_0x0246
        L_0x0139:
            java.lang.String r6 = "dy"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0143
            goto L_0x0246
        L_0x0143:
            r5 = 20
            goto L_0x0246
        L_0x0147:
            java.lang.String r6 = "dx"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0151
            goto L_0x0246
        L_0x0151:
            r5 = 19
            goto L_0x0246
        L_0x0155:
            java.lang.String r6 = "y"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x015f
            goto L_0x0246
        L_0x015f:
            r5 = 18
            goto L_0x0246
        L_0x0163:
            java.lang.String r6 = "x"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x016d
            goto L_0x0246
        L_0x016d:
            r5 = 17
            goto L_0x0246
        L_0x0171:
            java.lang.String r6 = "strokeDashoffset"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x017b
            goto L_0x0246
        L_0x017b:
            r5 = 16
            goto L_0x0246
        L_0x017f:
            java.lang.String r6 = "fillOpacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0189
            goto L_0x0246
        L_0x0189:
            r5 = 15
            goto L_0x0246
        L_0x018d:
            java.lang.String r6 = "pointerEvents"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0197
            goto L_0x0246
        L_0x0197:
            r5 = 14
            goto L_0x0246
        L_0x019b:
            java.lang.String r6 = "strokeOpacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01a5
            goto L_0x0246
        L_0x01a5:
            r5 = 13
            goto L_0x0246
        L_0x01a9:
            java.lang.String r6 = "fillRule"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01b3
            goto L_0x0246
        L_0x01b3:
            r5 = 12
            goto L_0x0246
        L_0x01b7:
            java.lang.String r6 = "fontWeight"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01c1
            goto L_0x0246
        L_0x01c1:
            r5 = 11
            goto L_0x0246
        L_0x01c5:
            java.lang.String r6 = "stroke"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01cf
            goto L_0x0246
        L_0x01cf:
            r5 = 10
            goto L_0x0246
        L_0x01d3:
            java.lang.String r6 = "rotate"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01dd
            goto L_0x0246
        L_0x01dd:
            r5 = 9
            goto L_0x0246
        L_0x01e1:
            java.lang.String r6 = "markerMid"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01eb
            goto L_0x0246
        L_0x01eb:
            r5 = 8
            goto L_0x0246
        L_0x01ef:
            java.lang.String r6 = "markerEnd"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01f8
            goto L_0x0246
        L_0x01f8:
            r5 = 7
            goto L_0x0246
        L_0x01fa:
            java.lang.String r6 = "propList"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0203
            goto L_0x0246
        L_0x0203:
            r5 = 6
            goto L_0x0246
        L_0x0205:
            java.lang.String r6 = "matrix"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x020e
            goto L_0x0246
        L_0x020e:
            r5 = 5
            goto L_0x0246
        L_0x0210:
            java.lang.String r6 = "verticalAlign"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0219
            goto L_0x0246
        L_0x0219:
            r5 = 4
            goto L_0x0246
        L_0x021b:
            java.lang.String r6 = "alignmentBaseline"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0224
            goto L_0x0246
        L_0x0224:
            r5 = 3
            goto L_0x0246
        L_0x0226:
            java.lang.String r6 = "opacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x022f
            goto L_0x0246
        L_0x022f:
            r5 = 2
            goto L_0x0246
        L_0x0231:
            java.lang.String r6 = "filter"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x023a
            goto L_0x0246
        L_0x023a:
            r5 = r0
            goto L_0x0246
        L_0x023c:
            java.lang.String r6 = "lengthAdjust"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0245
            goto L_0x0246
        L_0x0245:
            r5 = r3
        L_0x0246:
            switch(r5) {
                case 0: goto L_0x04b7;
                case 1: goto L_0x04a9;
                case 2: goto L_0x049a;
                case 3: goto L_0x048c;
                case 4: goto L_0x047f;
                case 5: goto L_0x0475;
                case 6: goto L_0x046b;
                case 7: goto L_0x045d;
                case 8: goto L_0x044e;
                case 9: goto L_0x0440;
                case 10: goto L_0x0432;
                case 11: goto L_0x0424;
                case 12: goto L_0x0412;
                case 13: goto L_0x0400;
                case 14: goto L_0x03f1;
                case 15: goto L_0x03df;
                case 16: goto L_0x03cd;
                case 17: goto L_0x03bf;
                case 18: goto L_0x03b1;
                case 19: goto L_0x03a3;
                case 20: goto L_0x0395;
                case 21: goto L_0x0387;
                case 22: goto L_0x0379;
                case 23: goto L_0x036a;
                case 24: goto L_0x035b;
                case 25: goto L_0x0349;
                case 26: goto L_0x0338;
                case 27: goto L_0x0326;
                case 28: goto L_0x0317;
                case 29: goto L_0x0309;
                case 30: goto L_0x02fb;
                case 31: goto L_0x02ed;
                case 32: goto L_0x02df;
                case 33: goto L_0x02d0;
                case 34: goto L_0x02be;
                case 35: goto L_0x02af;
                case 36: goto L_0x029d;
                case 37: goto L_0x028f;
                case 38: goto L_0x0280;
                case 39: goto L_0x026e;
                case 40: goto L_0x025c;
                case 41: goto L_0x024e;
                default: goto L_0x0249;
            }
        L_0x0249:
            super.setProperty(r8, r9, r10)
            goto L_0x04c4
        L_0x024e:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStrokeWidth(r8, r0)
            goto L_0x04c4
        L_0x025c:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            if (r10 != 0) goto L_0x0263
            goto L_0x0269
        L_0x0263:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r3 = r10.booleanValue()
        L_0x0269:
            r9.setResponsible(r8, r3)
            goto L_0x04c4
        L_0x026e:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            if (r10 != 0) goto L_0x0275
            goto L_0x027b
        L_0x0275:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x027b:
            r9.setStrokeLinejoin(r8, r3)
            goto L_0x04c4
        L_0x0280:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            if (r10 != 0) goto L_0x0287
            goto L_0x028a
        L_0x0287:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x028a:
            r9.setDisplay(r8, r4)
            goto L_0x04c4
        L_0x028f:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setTextLength(r8, r0)
            goto L_0x04c4
        L_0x029d:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            if (r10 != 0) goto L_0x02a4
            goto L_0x02aa
        L_0x02a4:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x02aa:
            r9.setStrokeLinecap(r8, r3)
            goto L_0x04c4
        L_0x02af:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            if (r10 != 0) goto L_0x02b6
            goto L_0x02b9
        L_0x02b6:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x02b9:
            r9.setContent(r8, r4)
            goto L_0x04c4
        L_0x02be:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            if (r10 != 0) goto L_0x02c5
            goto L_0x02cb
        L_0x02c5:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x02cb:
            r9.setClipRule(r8, r3)
            goto L_0x04c4
        L_0x02d0:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            if (r10 != 0) goto L_0x02d7
            goto L_0x02da
        L_0x02d7:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x02da:
            r9.setClipPath(r8, r4)
            goto L_0x04c4
        L_0x02df:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setInlineSize(r8, r0)
            goto L_0x04c4
        L_0x02ed:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStrokeDasharray(r8, r0)
            goto L_0x04c4
        L_0x02fb:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFontSize(r8, r0)
            goto L_0x04c4
        L_0x0309:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setBaselineShift(r8, r0)
            goto L_0x04c4
        L_0x0317:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            if (r10 != 0) goto L_0x031e
            goto L_0x0321
        L_0x031e:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0321:
            r9.setMarkerStart(r8, r4)
            goto L_0x04c4
        L_0x0326:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            if (r10 != 0) goto L_0x032d
            goto L_0x0333
        L_0x032d:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x0333:
            r9.setVectorEffect(r8, r3)
            goto L_0x04c4
        L_0x0338:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            android.content.Context r0 = r8.getContext()
            java.lang.Integer r10 = com.facebook.react.bridge.ColorPropConverter.getColor(r10, r0)
            r9.setColor(r8, r10)
            goto L_0x04c4
        L_0x0349:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            if (r10 != 0) goto L_0x0350
            goto L_0x0356
        L_0x0350:
            java.lang.Double r10 = (java.lang.Double) r10
            float r1 = r10.floatValue()
        L_0x0356:
            r9.setStrokeMiterlimit(r8, r1)
            goto L_0x04c4
        L_0x035b:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            if (r10 != 0) goto L_0x0362
            goto L_0x0365
        L_0x0362:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0365:
            r9.setName(r8, r4)
            goto L_0x04c4
        L_0x036a:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            if (r10 != 0) goto L_0x0371
            goto L_0x0374
        L_0x0371:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0374:
            r9.setMask(r8, r4)
            goto L_0x04c4
        L_0x0379:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFont(r8, r0)
            goto L_0x04c4
        L_0x0387:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFill(r8, r0)
            goto L_0x04c4
        L_0x0395:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setDy(r8, r0)
            goto L_0x04c4
        L_0x03a3:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setDx(r8, r0)
            goto L_0x04c4
        L_0x03b1:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setY(r8, r0)
            goto L_0x04c4
        L_0x03bf:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setX(r8, r0)
            goto L_0x04c4
        L_0x03cd:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            if (r10 != 0) goto L_0x03d4
            goto L_0x03da
        L_0x03d4:
            java.lang.Double r10 = (java.lang.Double) r10
            float r1 = r10.floatValue()
        L_0x03da:
            r9.setStrokeDashoffset(r8, r1)
            goto L_0x04c4
        L_0x03df:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            if (r10 != 0) goto L_0x03e6
            goto L_0x03ec
        L_0x03e6:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x03ec:
            r9.setFillOpacity(r8, r2)
            goto L_0x04c4
        L_0x03f1:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            if (r10 != 0) goto L_0x03f8
            goto L_0x03fb
        L_0x03f8:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x03fb:
            r9.setPointerEvents(r8, r4)
            goto L_0x04c4
        L_0x0400:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            if (r10 != 0) goto L_0x0407
            goto L_0x040d
        L_0x0407:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x040d:
            r9.setStrokeOpacity(r8, r2)
            goto L_0x04c4
        L_0x0412:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            if (r10 != 0) goto L_0x0419
            goto L_0x041f
        L_0x0419:
            java.lang.Double r10 = (java.lang.Double) r10
            int r0 = r10.intValue()
        L_0x041f:
            r9.setFillRule(r8, r0)
            goto L_0x04c4
        L_0x0424:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFontWeight(r8, r0)
            goto L_0x04c4
        L_0x0432:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStroke(r8, r0)
            goto L_0x04c4
        L_0x0440:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setRotate(r8, r0)
            goto L_0x04c4
        L_0x044e:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            if (r10 != 0) goto L_0x0455
            goto L_0x0458
        L_0x0455:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0458:
            r9.setMarkerMid(r8, r4)
            goto L_0x04c4
        L_0x045d:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            if (r10 != 0) goto L_0x0464
            goto L_0x0467
        L_0x0464:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0467:
            r9.setMarkerEnd(r8, r4)
            goto L_0x04c4
        L_0x046b:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            com.facebook.react.bridge.ReadableArray r10 = (com.facebook.react.bridge.ReadableArray) r10
            r9.setPropList(r8, r10)
            goto L_0x04c4
        L_0x0475:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            com.facebook.react.bridge.ReadableArray r10 = (com.facebook.react.bridge.ReadableArray) r10
            r9.setMatrix(r8, r10)
            goto L_0x04c4
        L_0x047f:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setVerticalAlign(r8, r0)
            goto L_0x04c4
        L_0x048c:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            if (r10 != 0) goto L_0x0493
            goto L_0x0496
        L_0x0493:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0496:
            r9.setAlignmentBaseline(r8, r4)
            goto L_0x04c4
        L_0x049a:
            U r9 = r7.mViewManager
            if (r10 != 0) goto L_0x049f
            goto L_0x04a5
        L_0x049f:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x04a5:
            r9.setOpacity(r8, r2)
            goto L_0x04c4
        L_0x04a9:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            if (r10 != 0) goto L_0x04b0
            goto L_0x04b3
        L_0x04b0:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x04b3:
            r9.setFilter(r8, r4)
            goto L_0x04c4
        L_0x04b7:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface) r9
            if (r10 != 0) goto L_0x04be
            goto L_0x04c1
        L_0x04be:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x04c1:
            r9.setLengthAdjust(r8, r4)
        L_0x04c4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSVGTSpanManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
