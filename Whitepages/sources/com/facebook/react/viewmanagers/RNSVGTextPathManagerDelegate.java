package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface;

public class RNSVGTextPathManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSVGTextPathManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGTextPathManagerDelegate(U u) {
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
                case -2012158909: goto L_0x0282;
                case -1993948267: goto L_0x0277;
                case -1603134955: goto L_0x026c;
                case -1274492040: goto L_0x0261;
                case -1267206133: goto L_0x0256;
                case -1171891896: goto L_0x024b;
                case -1139902161: goto L_0x0240;
                case -1081239615: goto L_0x0235;
                case -1077554975: goto L_0x0227;
                case -993894751: goto L_0x0219;
                case -933864895: goto L_0x020b;
                case -933857362: goto L_0x01fd;
                case -925180581: goto L_0x01ef;
                case -891980232: goto L_0x01e1;
                case -734428249: goto L_0x01d3;
                case -729118945: goto L_0x01c5;
                case -416535885: goto L_0x01b7;
                case -293492298: goto L_0x01a9;
                case -53677816: goto L_0x019b;
                case -44578051: goto L_0x018d;
                case 120: goto L_0x017f;
                case 121: goto L_0x0171;
                case 3220: goto L_0x0163;
                case 3221: goto L_0x0155;
                case 3143043: goto L_0x0147;
                case 3148879: goto L_0x0139;
                case 3211051: goto L_0x012b;
                case 3344108: goto L_0x011d;
                case 3373707: goto L_0x010f;
                case 3530071: goto L_0x0101;
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
                case 1027575302: goto L_0x0067;
                case 1054434908: goto L_0x0059;
                case 1637488243: goto L_0x004b;
                case 1671764162: goto L_0x003d;
                case 1790285174: goto L_0x002f;
                case 1847674614: goto L_0x0021;
                case 1924065902: goto L_0x0013;
                default: goto L_0x0011;
            }
        L_0x0011:
            goto L_0x028c
        L_0x0013:
            java.lang.String r6 = "strokeWidth"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x001d
            goto L_0x028c
        L_0x001d:
            r5 = 46
            goto L_0x028c
        L_0x0021:
            java.lang.String r6 = "responsible"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x002b
            goto L_0x028c
        L_0x002b:
            r5 = 45
            goto L_0x028c
        L_0x002f:
            java.lang.String r6 = "strokeLinejoin"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0039
            goto L_0x028c
        L_0x0039:
            r5 = 44
            goto L_0x028c
        L_0x003d:
            java.lang.String r6 = "display"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0047
            goto L_0x028c
        L_0x0047:
            r5 = 43
            goto L_0x028c
        L_0x004b:
            java.lang.String r6 = "textLength"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0055
            goto L_0x028c
        L_0x0055:
            r5 = 42
            goto L_0x028c
        L_0x0059:
            java.lang.String r6 = "midLine"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0063
            goto L_0x028c
        L_0x0063:
            r5 = 41
            goto L_0x028c
        L_0x0067:
            java.lang.String r6 = "strokeLinecap"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0071
            goto L_0x028c
        L_0x0071:
            r5 = 40
            goto L_0x028c
        L_0x0075:
            java.lang.String r6 = "clipRule"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x007f
            goto L_0x028c
        L_0x007f:
            r5 = 39
            goto L_0x028c
        L_0x0083:
            java.lang.String r6 = "clipPath"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x008d
            goto L_0x028c
        L_0x008d:
            r5 = 38
            goto L_0x028c
        L_0x0091:
            java.lang.String r6 = "inlineSize"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x009b
            goto L_0x028c
        L_0x009b:
            r5 = 37
            goto L_0x028c
        L_0x009f:
            java.lang.String r6 = "strokeDasharray"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00a9
            goto L_0x028c
        L_0x00a9:
            r5 = 36
            goto L_0x028c
        L_0x00ad:
            java.lang.String r6 = "fontSize"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00b7
            goto L_0x028c
        L_0x00b7:
            r5 = 35
            goto L_0x028c
        L_0x00bb:
            java.lang.String r6 = "baselineShift"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00c5
            goto L_0x028c
        L_0x00c5:
            r5 = 34
            goto L_0x028c
        L_0x00c9:
            java.lang.String r6 = "markerStart"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00d3
            goto L_0x028c
        L_0x00d3:
            r5 = 33
            goto L_0x028c
        L_0x00d7:
            java.lang.String r6 = "vectorEffect"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00e1
            goto L_0x028c
        L_0x00e1:
            r5 = 32
            goto L_0x028c
        L_0x00e5:
            java.lang.String r6 = "color"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00ef
            goto L_0x028c
        L_0x00ef:
            r5 = 31
            goto L_0x028c
        L_0x00f3:
            java.lang.String r6 = "strokeMiterlimit"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00fd
            goto L_0x028c
        L_0x00fd:
            r5 = 30
            goto L_0x028c
        L_0x0101:
            java.lang.String r6 = "side"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x010b
            goto L_0x028c
        L_0x010b:
            r5 = 29
            goto L_0x028c
        L_0x010f:
            java.lang.String r6 = "name"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0119
            goto L_0x028c
        L_0x0119:
            r5 = 28
            goto L_0x028c
        L_0x011d:
            java.lang.String r6 = "mask"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0127
            goto L_0x028c
        L_0x0127:
            r5 = 27
            goto L_0x028c
        L_0x012b:
            java.lang.String r6 = "href"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0135
            goto L_0x028c
        L_0x0135:
            r5 = 26
            goto L_0x028c
        L_0x0139:
            java.lang.String r6 = "font"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0143
            goto L_0x028c
        L_0x0143:
            r5 = 25
            goto L_0x028c
        L_0x0147:
            java.lang.String r6 = "fill"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0151
            goto L_0x028c
        L_0x0151:
            r5 = 24
            goto L_0x028c
        L_0x0155:
            java.lang.String r6 = "dy"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x015f
            goto L_0x028c
        L_0x015f:
            r5 = 23
            goto L_0x028c
        L_0x0163:
            java.lang.String r6 = "dx"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x016d
            goto L_0x028c
        L_0x016d:
            r5 = 22
            goto L_0x028c
        L_0x0171:
            java.lang.String r6 = "y"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x017b
            goto L_0x028c
        L_0x017b:
            r5 = 21
            goto L_0x028c
        L_0x017f:
            java.lang.String r6 = "x"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0189
            goto L_0x028c
        L_0x0189:
            r5 = 20
            goto L_0x028c
        L_0x018d:
            java.lang.String r6 = "strokeDashoffset"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0197
            goto L_0x028c
        L_0x0197:
            r5 = 19
            goto L_0x028c
        L_0x019b:
            java.lang.String r6 = "fillOpacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01a5
            goto L_0x028c
        L_0x01a5:
            r5 = 18
            goto L_0x028c
        L_0x01a9:
            java.lang.String r6 = "pointerEvents"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01b3
            goto L_0x028c
        L_0x01b3:
            r5 = 17
            goto L_0x028c
        L_0x01b7:
            java.lang.String r6 = "strokeOpacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01c1
            goto L_0x028c
        L_0x01c1:
            r5 = 16
            goto L_0x028c
        L_0x01c5:
            java.lang.String r6 = "fillRule"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01cf
            goto L_0x028c
        L_0x01cf:
            r5 = 15
            goto L_0x028c
        L_0x01d3:
            java.lang.String r6 = "fontWeight"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01dd
            goto L_0x028c
        L_0x01dd:
            r5 = 14
            goto L_0x028c
        L_0x01e1:
            java.lang.String r6 = "stroke"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01eb
            goto L_0x028c
        L_0x01eb:
            r5 = 13
            goto L_0x028c
        L_0x01ef:
            java.lang.String r6 = "rotate"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01f9
            goto L_0x028c
        L_0x01f9:
            r5 = 12
            goto L_0x028c
        L_0x01fd:
            java.lang.String r6 = "markerMid"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0207
            goto L_0x028c
        L_0x0207:
            r5 = 11
            goto L_0x028c
        L_0x020b:
            java.lang.String r6 = "markerEnd"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0215
            goto L_0x028c
        L_0x0215:
            r5 = 10
            goto L_0x028c
        L_0x0219:
            java.lang.String r6 = "propList"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0223
            goto L_0x028c
        L_0x0223:
            r5 = 9
            goto L_0x028c
        L_0x0227:
            java.lang.String r6 = "method"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0231
            goto L_0x028c
        L_0x0231:
            r5 = 8
            goto L_0x028c
        L_0x0235:
            java.lang.String r6 = "matrix"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x023e
            goto L_0x028c
        L_0x023e:
            r5 = 7
            goto L_0x028c
        L_0x0240:
            java.lang.String r6 = "verticalAlign"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0249
            goto L_0x028c
        L_0x0249:
            r5 = 6
            goto L_0x028c
        L_0x024b:
            java.lang.String r6 = "alignmentBaseline"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0254
            goto L_0x028c
        L_0x0254:
            r5 = 5
            goto L_0x028c
        L_0x0256:
            java.lang.String r6 = "opacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x025f
            goto L_0x028c
        L_0x025f:
            r5 = 4
            goto L_0x028c
        L_0x0261:
            java.lang.String r6 = "filter"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x026a
            goto L_0x028c
        L_0x026a:
            r5 = 3
            goto L_0x028c
        L_0x026c:
            java.lang.String r6 = "lengthAdjust"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0275
            goto L_0x028c
        L_0x0275:
            r5 = 2
            goto L_0x028c
        L_0x0277:
            java.lang.String r6 = "startOffset"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0280
            goto L_0x028c
        L_0x0280:
            r5 = r0
            goto L_0x028c
        L_0x0282:
            java.lang.String r6 = "spacing"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x028b
            goto L_0x028c
        L_0x028b:
            r5 = r3
        L_0x028c:
            switch(r5) {
                case 0: goto L_0x0547;
                case 1: goto L_0x053a;
                case 2: goto L_0x052c;
                case 3: goto L_0x051e;
                case 4: goto L_0x050f;
                case 5: goto L_0x0501;
                case 6: goto L_0x04f4;
                case 7: goto L_0x04ea;
                case 8: goto L_0x04db;
                case 9: goto L_0x04d0;
                case 10: goto L_0x04c1;
                case 11: goto L_0x04b2;
                case 12: goto L_0x04a4;
                case 13: goto L_0x0496;
                case 14: goto L_0x0488;
                case 15: goto L_0x0476;
                case 16: goto L_0x0464;
                case 17: goto L_0x0455;
                case 18: goto L_0x0443;
                case 19: goto L_0x0431;
                case 20: goto L_0x0423;
                case 21: goto L_0x0415;
                case 22: goto L_0x0407;
                case 23: goto L_0x03f9;
                case 24: goto L_0x03eb;
                case 25: goto L_0x03dd;
                case 26: goto L_0x03ce;
                case 27: goto L_0x03bf;
                case 28: goto L_0x03b0;
                case 29: goto L_0x03a1;
                case 30: goto L_0x038f;
                case 31: goto L_0x037e;
                case 32: goto L_0x036c;
                case 33: goto L_0x035d;
                case 34: goto L_0x034f;
                case 35: goto L_0x0341;
                case 36: goto L_0x0333;
                case 37: goto L_0x0325;
                case 38: goto L_0x0316;
                case 39: goto L_0x0304;
                case 40: goto L_0x02f2;
                case 41: goto L_0x02e3;
                case 42: goto L_0x02d5;
                case 43: goto L_0x02c6;
                case 44: goto L_0x02b4;
                case 45: goto L_0x02a2;
                case 46: goto L_0x0294;
                default: goto L_0x028f;
            }
        L_0x028f:
            super.setProperty(r8, r9, r10)
            goto L_0x0554
        L_0x0294:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStrokeWidth(r8, r0)
            goto L_0x0554
        L_0x02a2:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            if (r10 != 0) goto L_0x02a9
            goto L_0x02af
        L_0x02a9:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r3 = r10.booleanValue()
        L_0x02af:
            r9.setResponsible(r8, r3)
            goto L_0x0554
        L_0x02b4:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            if (r10 != 0) goto L_0x02bb
            goto L_0x02c1
        L_0x02bb:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x02c1:
            r9.setStrokeLinejoin(r8, r3)
            goto L_0x0554
        L_0x02c6:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            if (r10 != 0) goto L_0x02cd
            goto L_0x02d0
        L_0x02cd:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x02d0:
            r9.setDisplay(r8, r4)
            goto L_0x0554
        L_0x02d5:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setTextLength(r8, r0)
            goto L_0x0554
        L_0x02e3:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            if (r10 != 0) goto L_0x02ea
            goto L_0x02ed
        L_0x02ea:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x02ed:
            r9.setMidLine(r8, r4)
            goto L_0x0554
        L_0x02f2:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            if (r10 != 0) goto L_0x02f9
            goto L_0x02ff
        L_0x02f9:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x02ff:
            r9.setStrokeLinecap(r8, r3)
            goto L_0x0554
        L_0x0304:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            if (r10 != 0) goto L_0x030b
            goto L_0x0311
        L_0x030b:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x0311:
            r9.setClipRule(r8, r3)
            goto L_0x0554
        L_0x0316:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            if (r10 != 0) goto L_0x031d
            goto L_0x0320
        L_0x031d:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0320:
            r9.setClipPath(r8, r4)
            goto L_0x0554
        L_0x0325:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setInlineSize(r8, r0)
            goto L_0x0554
        L_0x0333:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStrokeDasharray(r8, r0)
            goto L_0x0554
        L_0x0341:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFontSize(r8, r0)
            goto L_0x0554
        L_0x034f:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setBaselineShift(r8, r0)
            goto L_0x0554
        L_0x035d:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            if (r10 != 0) goto L_0x0364
            goto L_0x0367
        L_0x0364:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0367:
            r9.setMarkerStart(r8, r4)
            goto L_0x0554
        L_0x036c:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            if (r10 != 0) goto L_0x0373
            goto L_0x0379
        L_0x0373:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x0379:
            r9.setVectorEffect(r8, r3)
            goto L_0x0554
        L_0x037e:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            android.content.Context r0 = r8.getContext()
            java.lang.Integer r10 = com.facebook.react.bridge.ColorPropConverter.getColor(r10, r0)
            r9.setColor(r8, r10)
            goto L_0x0554
        L_0x038f:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            if (r10 != 0) goto L_0x0396
            goto L_0x039c
        L_0x0396:
            java.lang.Double r10 = (java.lang.Double) r10
            float r1 = r10.floatValue()
        L_0x039c:
            r9.setStrokeMiterlimit(r8, r1)
            goto L_0x0554
        L_0x03a1:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            if (r10 != 0) goto L_0x03a8
            goto L_0x03ab
        L_0x03a8:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x03ab:
            r9.setSide(r8, r4)
            goto L_0x0554
        L_0x03b0:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            if (r10 != 0) goto L_0x03b7
            goto L_0x03ba
        L_0x03b7:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x03ba:
            r9.setName(r8, r4)
            goto L_0x0554
        L_0x03bf:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            if (r10 != 0) goto L_0x03c6
            goto L_0x03c9
        L_0x03c6:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x03c9:
            r9.setMask(r8, r4)
            goto L_0x0554
        L_0x03ce:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            if (r10 != 0) goto L_0x03d5
            goto L_0x03d8
        L_0x03d5:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x03d8:
            r9.setHref(r8, r4)
            goto L_0x0554
        L_0x03dd:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFont(r8, r0)
            goto L_0x0554
        L_0x03eb:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFill(r8, r0)
            goto L_0x0554
        L_0x03f9:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setDy(r8, r0)
            goto L_0x0554
        L_0x0407:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setDx(r8, r0)
            goto L_0x0554
        L_0x0415:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setY(r8, r0)
            goto L_0x0554
        L_0x0423:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setX(r8, r0)
            goto L_0x0554
        L_0x0431:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            if (r10 != 0) goto L_0x0438
            goto L_0x043e
        L_0x0438:
            java.lang.Double r10 = (java.lang.Double) r10
            float r1 = r10.floatValue()
        L_0x043e:
            r9.setStrokeDashoffset(r8, r1)
            goto L_0x0554
        L_0x0443:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            if (r10 != 0) goto L_0x044a
            goto L_0x0450
        L_0x044a:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x0450:
            r9.setFillOpacity(r8, r2)
            goto L_0x0554
        L_0x0455:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            if (r10 != 0) goto L_0x045c
            goto L_0x045f
        L_0x045c:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x045f:
            r9.setPointerEvents(r8, r4)
            goto L_0x0554
        L_0x0464:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            if (r10 != 0) goto L_0x046b
            goto L_0x0471
        L_0x046b:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x0471:
            r9.setStrokeOpacity(r8, r2)
            goto L_0x0554
        L_0x0476:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            if (r10 != 0) goto L_0x047d
            goto L_0x0483
        L_0x047d:
            java.lang.Double r10 = (java.lang.Double) r10
            int r0 = r10.intValue()
        L_0x0483:
            r9.setFillRule(r8, r0)
            goto L_0x0554
        L_0x0488:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFontWeight(r8, r0)
            goto L_0x0554
        L_0x0496:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStroke(r8, r0)
            goto L_0x0554
        L_0x04a4:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setRotate(r8, r0)
            goto L_0x0554
        L_0x04b2:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            if (r10 != 0) goto L_0x04b9
            goto L_0x04bc
        L_0x04b9:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x04bc:
            r9.setMarkerMid(r8, r4)
            goto L_0x0554
        L_0x04c1:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            if (r10 != 0) goto L_0x04c8
            goto L_0x04cb
        L_0x04c8:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x04cb:
            r9.setMarkerEnd(r8, r4)
            goto L_0x0554
        L_0x04d0:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            com.facebook.react.bridge.ReadableArray r10 = (com.facebook.react.bridge.ReadableArray) r10
            r9.setPropList(r8, r10)
            goto L_0x0554
        L_0x04db:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            if (r10 != 0) goto L_0x04e2
            goto L_0x04e5
        L_0x04e2:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x04e5:
            r9.setMethod(r8, r4)
            goto L_0x0554
        L_0x04ea:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            com.facebook.react.bridge.ReadableArray r10 = (com.facebook.react.bridge.ReadableArray) r10
            r9.setMatrix(r8, r10)
            goto L_0x0554
        L_0x04f4:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setVerticalAlign(r8, r0)
            goto L_0x0554
        L_0x0501:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            if (r10 != 0) goto L_0x0508
            goto L_0x050b
        L_0x0508:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x050b:
            r9.setAlignmentBaseline(r8, r4)
            goto L_0x0554
        L_0x050f:
            U r9 = r7.mViewManager
            if (r10 != 0) goto L_0x0514
            goto L_0x051a
        L_0x0514:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x051a:
            r9.setOpacity(r8, r2)
            goto L_0x0554
        L_0x051e:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            if (r10 != 0) goto L_0x0525
            goto L_0x0528
        L_0x0525:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0528:
            r9.setFilter(r8, r4)
            goto L_0x0554
        L_0x052c:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            if (r10 != 0) goto L_0x0533
            goto L_0x0536
        L_0x0533:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0536:
            r9.setLengthAdjust(r8, r4)
            goto L_0x0554
        L_0x053a:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStartOffset(r8, r0)
            goto L_0x0554
        L_0x0547:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface) r9
            if (r10 != 0) goto L_0x054e
            goto L_0x0551
        L_0x054e:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0551:
            r9.setSpacing(r8, r4)
        L_0x0554:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSVGTextPathManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
