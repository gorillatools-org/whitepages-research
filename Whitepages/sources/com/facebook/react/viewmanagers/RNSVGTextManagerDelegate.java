package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSVGTextManagerInterface;

public class RNSVGTextManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSVGTextManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGTextManagerDelegate(U u) {
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
                case -1603134955: goto L_0x022e;
                case -1274492040: goto L_0x0223;
                case -1267206133: goto L_0x0218;
                case -1171891896: goto L_0x020d;
                case -1139902161: goto L_0x0202;
                case -1081239615: goto L_0x01f7;
                case -993894751: goto L_0x01ec;
                case -933864895: goto L_0x01e1;
                case -933857362: goto L_0x01d3;
                case -925180581: goto L_0x01c5;
                case -891980232: goto L_0x01b7;
                case -734428249: goto L_0x01a9;
                case -729118945: goto L_0x019b;
                case -416535885: goto L_0x018d;
                case -293492298: goto L_0x017f;
                case -53677816: goto L_0x0171;
                case -44578051: goto L_0x0163;
                case 120: goto L_0x0155;
                case 121: goto L_0x0147;
                case 3220: goto L_0x0139;
                case 3221: goto L_0x012b;
                case 3143043: goto L_0x011d;
                case 3148879: goto L_0x010f;
                case 3344108: goto L_0x0101;
                case 3373707: goto L_0x00f3;
                case 78845486: goto L_0x00e5;
                case 94842723: goto L_0x00d7;
                case 104482996: goto L_0x00c9;
                case 217109576: goto L_0x00bb;
                case 275888445: goto L_0x00ad;
                case 365601008: goto L_0x009f;
                case 401643183: goto L_0x0091;
                case 778043962: goto L_0x0083;
                case 917656469: goto L_0x0075;
                case 917735020: goto L_0x0067;
                case 1027575302: goto L_0x0059;
                case 1637488243: goto L_0x004b;
                case 1671764162: goto L_0x003d;
                case 1790285174: goto L_0x002f;
                case 1847674614: goto L_0x0021;
                case 1924065902: goto L_0x0013;
                default: goto L_0x0011;
            }
        L_0x0011:
            goto L_0x0238
        L_0x0013:
            java.lang.String r6 = "strokeWidth"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x001d
            goto L_0x0238
        L_0x001d:
            r5 = 40
            goto L_0x0238
        L_0x0021:
            java.lang.String r6 = "responsible"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x002b
            goto L_0x0238
        L_0x002b:
            r5 = 39
            goto L_0x0238
        L_0x002f:
            java.lang.String r6 = "strokeLinejoin"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0039
            goto L_0x0238
        L_0x0039:
            r5 = 38
            goto L_0x0238
        L_0x003d:
            java.lang.String r6 = "display"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0047
            goto L_0x0238
        L_0x0047:
            r5 = 37
            goto L_0x0238
        L_0x004b:
            java.lang.String r6 = "textLength"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0055
            goto L_0x0238
        L_0x0055:
            r5 = 36
            goto L_0x0238
        L_0x0059:
            java.lang.String r6 = "strokeLinecap"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0063
            goto L_0x0238
        L_0x0063:
            r5 = 35
            goto L_0x0238
        L_0x0067:
            java.lang.String r6 = "clipRule"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0071
            goto L_0x0238
        L_0x0071:
            r5 = 34
            goto L_0x0238
        L_0x0075:
            java.lang.String r6 = "clipPath"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x007f
            goto L_0x0238
        L_0x007f:
            r5 = 33
            goto L_0x0238
        L_0x0083:
            java.lang.String r6 = "inlineSize"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x008d
            goto L_0x0238
        L_0x008d:
            r5 = 32
            goto L_0x0238
        L_0x0091:
            java.lang.String r6 = "strokeDasharray"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x009b
            goto L_0x0238
        L_0x009b:
            r5 = 31
            goto L_0x0238
        L_0x009f:
            java.lang.String r6 = "fontSize"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00a9
            goto L_0x0238
        L_0x00a9:
            r5 = 30
            goto L_0x0238
        L_0x00ad:
            java.lang.String r6 = "baselineShift"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00b7
            goto L_0x0238
        L_0x00b7:
            r5 = 29
            goto L_0x0238
        L_0x00bb:
            java.lang.String r6 = "markerStart"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00c5
            goto L_0x0238
        L_0x00c5:
            r5 = 28
            goto L_0x0238
        L_0x00c9:
            java.lang.String r6 = "vectorEffect"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00d3
            goto L_0x0238
        L_0x00d3:
            r5 = 27
            goto L_0x0238
        L_0x00d7:
            java.lang.String r6 = "color"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00e1
            goto L_0x0238
        L_0x00e1:
            r5 = 26
            goto L_0x0238
        L_0x00e5:
            java.lang.String r6 = "strokeMiterlimit"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00ef
            goto L_0x0238
        L_0x00ef:
            r5 = 25
            goto L_0x0238
        L_0x00f3:
            java.lang.String r6 = "name"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00fd
            goto L_0x0238
        L_0x00fd:
            r5 = 24
            goto L_0x0238
        L_0x0101:
            java.lang.String r6 = "mask"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x010b
            goto L_0x0238
        L_0x010b:
            r5 = 23
            goto L_0x0238
        L_0x010f:
            java.lang.String r6 = "font"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0119
            goto L_0x0238
        L_0x0119:
            r5 = 22
            goto L_0x0238
        L_0x011d:
            java.lang.String r6 = "fill"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0127
            goto L_0x0238
        L_0x0127:
            r5 = 21
            goto L_0x0238
        L_0x012b:
            java.lang.String r6 = "dy"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0135
            goto L_0x0238
        L_0x0135:
            r5 = 20
            goto L_0x0238
        L_0x0139:
            java.lang.String r6 = "dx"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0143
            goto L_0x0238
        L_0x0143:
            r5 = 19
            goto L_0x0238
        L_0x0147:
            java.lang.String r6 = "y"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0151
            goto L_0x0238
        L_0x0151:
            r5 = 18
            goto L_0x0238
        L_0x0155:
            java.lang.String r6 = "x"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x015f
            goto L_0x0238
        L_0x015f:
            r5 = 17
            goto L_0x0238
        L_0x0163:
            java.lang.String r6 = "strokeDashoffset"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x016d
            goto L_0x0238
        L_0x016d:
            r5 = 16
            goto L_0x0238
        L_0x0171:
            java.lang.String r6 = "fillOpacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x017b
            goto L_0x0238
        L_0x017b:
            r5 = 15
            goto L_0x0238
        L_0x017f:
            java.lang.String r6 = "pointerEvents"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0189
            goto L_0x0238
        L_0x0189:
            r5 = 14
            goto L_0x0238
        L_0x018d:
            java.lang.String r6 = "strokeOpacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0197
            goto L_0x0238
        L_0x0197:
            r5 = 13
            goto L_0x0238
        L_0x019b:
            java.lang.String r6 = "fillRule"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01a5
            goto L_0x0238
        L_0x01a5:
            r5 = 12
            goto L_0x0238
        L_0x01a9:
            java.lang.String r6 = "fontWeight"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01b3
            goto L_0x0238
        L_0x01b3:
            r5 = 11
            goto L_0x0238
        L_0x01b7:
            java.lang.String r6 = "stroke"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01c1
            goto L_0x0238
        L_0x01c1:
            r5 = 10
            goto L_0x0238
        L_0x01c5:
            java.lang.String r6 = "rotate"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01cf
            goto L_0x0238
        L_0x01cf:
            r5 = 9
            goto L_0x0238
        L_0x01d3:
            java.lang.String r6 = "markerMid"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01dd
            goto L_0x0238
        L_0x01dd:
            r5 = 8
            goto L_0x0238
        L_0x01e1:
            java.lang.String r6 = "markerEnd"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01ea
            goto L_0x0238
        L_0x01ea:
            r5 = 7
            goto L_0x0238
        L_0x01ec:
            java.lang.String r6 = "propList"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01f5
            goto L_0x0238
        L_0x01f5:
            r5 = 6
            goto L_0x0238
        L_0x01f7:
            java.lang.String r6 = "matrix"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0200
            goto L_0x0238
        L_0x0200:
            r5 = 5
            goto L_0x0238
        L_0x0202:
            java.lang.String r6 = "verticalAlign"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x020b
            goto L_0x0238
        L_0x020b:
            r5 = 4
            goto L_0x0238
        L_0x020d:
            java.lang.String r6 = "alignmentBaseline"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0216
            goto L_0x0238
        L_0x0216:
            r5 = 3
            goto L_0x0238
        L_0x0218:
            java.lang.String r6 = "opacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0221
            goto L_0x0238
        L_0x0221:
            r5 = 2
            goto L_0x0238
        L_0x0223:
            java.lang.String r6 = "filter"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x022c
            goto L_0x0238
        L_0x022c:
            r5 = r0
            goto L_0x0238
        L_0x022e:
            java.lang.String r6 = "lengthAdjust"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0237
            goto L_0x0238
        L_0x0237:
            r5 = r3
        L_0x0238:
            switch(r5) {
                case 0: goto L_0x049a;
                case 1: goto L_0x048c;
                case 2: goto L_0x047d;
                case 3: goto L_0x046f;
                case 4: goto L_0x0462;
                case 5: goto L_0x0458;
                case 6: goto L_0x044e;
                case 7: goto L_0x0440;
                case 8: goto L_0x0431;
                case 9: goto L_0x0423;
                case 10: goto L_0x0415;
                case 11: goto L_0x0407;
                case 12: goto L_0x03f5;
                case 13: goto L_0x03e3;
                case 14: goto L_0x03d4;
                case 15: goto L_0x03c2;
                case 16: goto L_0x03b0;
                case 17: goto L_0x03a2;
                case 18: goto L_0x0394;
                case 19: goto L_0x0386;
                case 20: goto L_0x0378;
                case 21: goto L_0x036a;
                case 22: goto L_0x035c;
                case 23: goto L_0x034d;
                case 24: goto L_0x033e;
                case 25: goto L_0x032c;
                case 26: goto L_0x031b;
                case 27: goto L_0x0309;
                case 28: goto L_0x02fa;
                case 29: goto L_0x02ec;
                case 30: goto L_0x02de;
                case 31: goto L_0x02d0;
                case 32: goto L_0x02c2;
                case 33: goto L_0x02b3;
                case 34: goto L_0x02a1;
                case 35: goto L_0x028f;
                case 36: goto L_0x0281;
                case 37: goto L_0x0272;
                case 38: goto L_0x0260;
                case 39: goto L_0x024e;
                case 40: goto L_0x0240;
                default: goto L_0x023b;
            }
        L_0x023b:
            super.setProperty(r8, r9, r10)
            goto L_0x04a7
        L_0x0240:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStrokeWidth(r8, r0)
            goto L_0x04a7
        L_0x024e:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            if (r10 != 0) goto L_0x0255
            goto L_0x025b
        L_0x0255:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r3 = r10.booleanValue()
        L_0x025b:
            r9.setResponsible(r8, r3)
            goto L_0x04a7
        L_0x0260:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            if (r10 != 0) goto L_0x0267
            goto L_0x026d
        L_0x0267:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x026d:
            r9.setStrokeLinejoin(r8, r3)
            goto L_0x04a7
        L_0x0272:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            if (r10 != 0) goto L_0x0279
            goto L_0x027c
        L_0x0279:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x027c:
            r9.setDisplay(r8, r4)
            goto L_0x04a7
        L_0x0281:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setTextLength(r8, r0)
            goto L_0x04a7
        L_0x028f:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            if (r10 != 0) goto L_0x0296
            goto L_0x029c
        L_0x0296:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x029c:
            r9.setStrokeLinecap(r8, r3)
            goto L_0x04a7
        L_0x02a1:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            if (r10 != 0) goto L_0x02a8
            goto L_0x02ae
        L_0x02a8:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x02ae:
            r9.setClipRule(r8, r3)
            goto L_0x04a7
        L_0x02b3:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            if (r10 != 0) goto L_0x02ba
            goto L_0x02bd
        L_0x02ba:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x02bd:
            r9.setClipPath(r8, r4)
            goto L_0x04a7
        L_0x02c2:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setInlineSize(r8, r0)
            goto L_0x04a7
        L_0x02d0:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStrokeDasharray(r8, r0)
            goto L_0x04a7
        L_0x02de:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFontSize(r8, r0)
            goto L_0x04a7
        L_0x02ec:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setBaselineShift(r8, r0)
            goto L_0x04a7
        L_0x02fa:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            if (r10 != 0) goto L_0x0301
            goto L_0x0304
        L_0x0301:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0304:
            r9.setMarkerStart(r8, r4)
            goto L_0x04a7
        L_0x0309:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            if (r10 != 0) goto L_0x0310
            goto L_0x0316
        L_0x0310:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x0316:
            r9.setVectorEffect(r8, r3)
            goto L_0x04a7
        L_0x031b:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            android.content.Context r0 = r8.getContext()
            java.lang.Integer r10 = com.facebook.react.bridge.ColorPropConverter.getColor(r10, r0)
            r9.setColor(r8, r10)
            goto L_0x04a7
        L_0x032c:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            if (r10 != 0) goto L_0x0333
            goto L_0x0339
        L_0x0333:
            java.lang.Double r10 = (java.lang.Double) r10
            float r1 = r10.floatValue()
        L_0x0339:
            r9.setStrokeMiterlimit(r8, r1)
            goto L_0x04a7
        L_0x033e:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            if (r10 != 0) goto L_0x0345
            goto L_0x0348
        L_0x0345:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0348:
            r9.setName(r8, r4)
            goto L_0x04a7
        L_0x034d:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            if (r10 != 0) goto L_0x0354
            goto L_0x0357
        L_0x0354:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0357:
            r9.setMask(r8, r4)
            goto L_0x04a7
        L_0x035c:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFont(r8, r0)
            goto L_0x04a7
        L_0x036a:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFill(r8, r0)
            goto L_0x04a7
        L_0x0378:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setDy(r8, r0)
            goto L_0x04a7
        L_0x0386:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setDx(r8, r0)
            goto L_0x04a7
        L_0x0394:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setY(r8, r0)
            goto L_0x04a7
        L_0x03a2:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setX(r8, r0)
            goto L_0x04a7
        L_0x03b0:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            if (r10 != 0) goto L_0x03b7
            goto L_0x03bd
        L_0x03b7:
            java.lang.Double r10 = (java.lang.Double) r10
            float r1 = r10.floatValue()
        L_0x03bd:
            r9.setStrokeDashoffset(r8, r1)
            goto L_0x04a7
        L_0x03c2:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            if (r10 != 0) goto L_0x03c9
            goto L_0x03cf
        L_0x03c9:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x03cf:
            r9.setFillOpacity(r8, r2)
            goto L_0x04a7
        L_0x03d4:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            if (r10 != 0) goto L_0x03db
            goto L_0x03de
        L_0x03db:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x03de:
            r9.setPointerEvents(r8, r4)
            goto L_0x04a7
        L_0x03e3:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            if (r10 != 0) goto L_0x03ea
            goto L_0x03f0
        L_0x03ea:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x03f0:
            r9.setStrokeOpacity(r8, r2)
            goto L_0x04a7
        L_0x03f5:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            if (r10 != 0) goto L_0x03fc
            goto L_0x0402
        L_0x03fc:
            java.lang.Double r10 = (java.lang.Double) r10
            int r0 = r10.intValue()
        L_0x0402:
            r9.setFillRule(r8, r0)
            goto L_0x04a7
        L_0x0407:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFontWeight(r8, r0)
            goto L_0x04a7
        L_0x0415:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStroke(r8, r0)
            goto L_0x04a7
        L_0x0423:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setRotate(r8, r0)
            goto L_0x04a7
        L_0x0431:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            if (r10 != 0) goto L_0x0438
            goto L_0x043b
        L_0x0438:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x043b:
            r9.setMarkerMid(r8, r4)
            goto L_0x04a7
        L_0x0440:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            if (r10 != 0) goto L_0x0447
            goto L_0x044a
        L_0x0447:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x044a:
            r9.setMarkerEnd(r8, r4)
            goto L_0x04a7
        L_0x044e:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            com.facebook.react.bridge.ReadableArray r10 = (com.facebook.react.bridge.ReadableArray) r10
            r9.setPropList(r8, r10)
            goto L_0x04a7
        L_0x0458:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            com.facebook.react.bridge.ReadableArray r10 = (com.facebook.react.bridge.ReadableArray) r10
            r9.setMatrix(r8, r10)
            goto L_0x04a7
        L_0x0462:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setVerticalAlign(r8, r0)
            goto L_0x04a7
        L_0x046f:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            if (r10 != 0) goto L_0x0476
            goto L_0x0479
        L_0x0476:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0479:
            r9.setAlignmentBaseline(r8, r4)
            goto L_0x04a7
        L_0x047d:
            U r9 = r7.mViewManager
            if (r10 != 0) goto L_0x0482
            goto L_0x0488
        L_0x0482:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x0488:
            r9.setOpacity(r8, r2)
            goto L_0x04a7
        L_0x048c:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            if (r10 != 0) goto L_0x0493
            goto L_0x0496
        L_0x0493:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0496:
            r9.setFilter(r8, r4)
            goto L_0x04a7
        L_0x049a:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextManagerInterface) r9
            if (r10 != 0) goto L_0x04a1
            goto L_0x04a4
        L_0x04a1:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x04a4:
            r9.setLengthAdjust(r8, r4)
        L_0x04a7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSVGTextManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
