package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSVGPatternManagerInterface;

public class RNSVGPatternManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSVGPatternManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGPatternManagerDelegate(U u) {
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
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v10, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v12, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v13, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v15, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v16, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v17, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v18, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v19, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v20, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v21, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v22, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v23, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v24, resolved type: boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setProperty(T r8, java.lang.String r9, java.lang.Object r10) {
        /*
            r7 = this;
            r0 = 1
            r9.hashCode()
            r1 = 1065353216(0x3f800000, float:1.0)
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = -1
            int r6 = r9.hashCode()
            switch(r6) {
                case -1567958285: goto L_0x024a;
                case -1274492040: goto L_0x023f;
                case -1267206133: goto L_0x0234;
                case -1221029593: goto L_0x0229;
                case -1081239615: goto L_0x021e;
                case -993894751: goto L_0x0213;
                case -933864895: goto L_0x0208;
                case -933857362: goto L_0x01fd;
                case -891980232: goto L_0x01ef;
                case -734428249: goto L_0x01e1;
                case -729118945: goto L_0x01d3;
                case -416535885: goto L_0x01c5;
                case -293492298: goto L_0x01b7;
                case -207800897: goto L_0x01a9;
                case -128680410: goto L_0x019b;
                case -53677816: goto L_0x018d;
                case -44578051: goto L_0x017f;
                case 120: goto L_0x0171;
                case 121: goto L_0x0163;
                case 3143043: goto L_0x0155;
                case 3148879: goto L_0x0147;
                case 3344108: goto L_0x0139;
                case 3351622: goto L_0x012b;
                case 3351623: goto L_0x011d;
                case 3373707: goto L_0x010f;
                case 78845486: goto L_0x0101;
                case 92903173: goto L_0x00f3;
                case 94842723: goto L_0x00e5;
                case 104482996: goto L_0x00d7;
                case 113126854: goto L_0x00c9;
                case 217109576: goto L_0x00bb;
                case 240482938: goto L_0x00ad;
                case 365601008: goto L_0x009f;
                case 401643183: goto L_0x0091;
                case 746561980: goto L_0x0083;
                case 917656469: goto L_0x0075;
                case 917735020: goto L_0x0067;
                case 1027575302: goto L_0x0059;
                case 1671764162: goto L_0x004b;
                case 1790285174: goto L_0x003d;
                case 1847674614: goto L_0x002f;
                case 1908075304: goto L_0x0021;
                case 1924065902: goto L_0x0013;
                default: goto L_0x0011;
            }
        L_0x0011:
            goto L_0x0254
        L_0x0013:
            java.lang.String r6 = "strokeWidth"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x001d
            goto L_0x0254
        L_0x001d:
            r5 = 42
            goto L_0x0254
        L_0x0021:
            java.lang.String r6 = "meetOrSlice"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x002b
            goto L_0x0254
        L_0x002b:
            r5 = 41
            goto L_0x0254
        L_0x002f:
            java.lang.String r6 = "responsible"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0039
            goto L_0x0254
        L_0x0039:
            r5 = 40
            goto L_0x0254
        L_0x003d:
            java.lang.String r6 = "strokeLinejoin"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0047
            goto L_0x0254
        L_0x0047:
            r5 = 39
            goto L_0x0254
        L_0x004b:
            java.lang.String r6 = "display"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0055
            goto L_0x0254
        L_0x0055:
            r5 = 38
            goto L_0x0254
        L_0x0059:
            java.lang.String r6 = "strokeLinecap"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0063
            goto L_0x0254
        L_0x0063:
            r5 = 37
            goto L_0x0254
        L_0x0067:
            java.lang.String r6 = "clipRule"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0071
            goto L_0x0254
        L_0x0071:
            r5 = 36
            goto L_0x0254
        L_0x0075:
            java.lang.String r6 = "clipPath"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x007f
            goto L_0x0254
        L_0x007f:
            r5 = 35
            goto L_0x0254
        L_0x0083:
            java.lang.String r6 = "patternTransform"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x008d
            goto L_0x0254
        L_0x008d:
            r5 = 34
            goto L_0x0254
        L_0x0091:
            java.lang.String r6 = "strokeDasharray"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x009b
            goto L_0x0254
        L_0x009b:
            r5 = 33
            goto L_0x0254
        L_0x009f:
            java.lang.String r6 = "fontSize"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00a9
            goto L_0x0254
        L_0x00a9:
            r5 = 32
            goto L_0x0254
        L_0x00ad:
            java.lang.String r6 = "vbWidth"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00b7
            goto L_0x0254
        L_0x00b7:
            r5 = 31
            goto L_0x0254
        L_0x00bb:
            java.lang.String r6 = "markerStart"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00c5
            goto L_0x0254
        L_0x00c5:
            r5 = 30
            goto L_0x0254
        L_0x00c9:
            java.lang.String r6 = "width"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00d3
            goto L_0x0254
        L_0x00d3:
            r5 = 29
            goto L_0x0254
        L_0x00d7:
            java.lang.String r6 = "vectorEffect"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00e1
            goto L_0x0254
        L_0x00e1:
            r5 = 28
            goto L_0x0254
        L_0x00e5:
            java.lang.String r6 = "color"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00ef
            goto L_0x0254
        L_0x00ef:
            r5 = 27
            goto L_0x0254
        L_0x00f3:
            java.lang.String r6 = "align"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00fd
            goto L_0x0254
        L_0x00fd:
            r5 = 26
            goto L_0x0254
        L_0x0101:
            java.lang.String r6 = "strokeMiterlimit"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x010b
            goto L_0x0254
        L_0x010b:
            r5 = 25
            goto L_0x0254
        L_0x010f:
            java.lang.String r6 = "name"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0119
            goto L_0x0254
        L_0x0119:
            r5 = 24
            goto L_0x0254
        L_0x011d:
            java.lang.String r6 = "minY"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0127
            goto L_0x0254
        L_0x0127:
            r5 = 23
            goto L_0x0254
        L_0x012b:
            java.lang.String r6 = "minX"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0135
            goto L_0x0254
        L_0x0135:
            r5 = 22
            goto L_0x0254
        L_0x0139:
            java.lang.String r6 = "mask"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0143
            goto L_0x0254
        L_0x0143:
            r5 = 21
            goto L_0x0254
        L_0x0147:
            java.lang.String r6 = "font"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0151
            goto L_0x0254
        L_0x0151:
            r5 = 20
            goto L_0x0254
        L_0x0155:
            java.lang.String r6 = "fill"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x015f
            goto L_0x0254
        L_0x015f:
            r5 = 19
            goto L_0x0254
        L_0x0163:
            java.lang.String r6 = "y"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x016d
            goto L_0x0254
        L_0x016d:
            r5 = 18
            goto L_0x0254
        L_0x0171:
            java.lang.String r6 = "x"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x017b
            goto L_0x0254
        L_0x017b:
            r5 = 17
            goto L_0x0254
        L_0x017f:
            java.lang.String r6 = "strokeDashoffset"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0189
            goto L_0x0254
        L_0x0189:
            r5 = 16
            goto L_0x0254
        L_0x018d:
            java.lang.String r6 = "fillOpacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0197
            goto L_0x0254
        L_0x0197:
            r5 = 15
            goto L_0x0254
        L_0x019b:
            java.lang.String r6 = "patternContentUnits"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01a5
            goto L_0x0254
        L_0x01a5:
            r5 = 14
            goto L_0x0254
        L_0x01a9:
            java.lang.String r6 = "patternUnits"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01b3
            goto L_0x0254
        L_0x01b3:
            r5 = 13
            goto L_0x0254
        L_0x01b7:
            java.lang.String r6 = "pointerEvents"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01c1
            goto L_0x0254
        L_0x01c1:
            r5 = 12
            goto L_0x0254
        L_0x01c5:
            java.lang.String r6 = "strokeOpacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01cf
            goto L_0x0254
        L_0x01cf:
            r5 = 11
            goto L_0x0254
        L_0x01d3:
            java.lang.String r6 = "fillRule"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01dd
            goto L_0x0254
        L_0x01dd:
            r5 = 10
            goto L_0x0254
        L_0x01e1:
            java.lang.String r6 = "fontWeight"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01eb
            goto L_0x0254
        L_0x01eb:
            r5 = 9
            goto L_0x0254
        L_0x01ef:
            java.lang.String r6 = "stroke"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01f9
            goto L_0x0254
        L_0x01f9:
            r5 = 8
            goto L_0x0254
        L_0x01fd:
            java.lang.String r6 = "markerMid"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0206
            goto L_0x0254
        L_0x0206:
            r5 = 7
            goto L_0x0254
        L_0x0208:
            java.lang.String r6 = "markerEnd"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0211
            goto L_0x0254
        L_0x0211:
            r5 = 6
            goto L_0x0254
        L_0x0213:
            java.lang.String r6 = "propList"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x021c
            goto L_0x0254
        L_0x021c:
            r5 = 5
            goto L_0x0254
        L_0x021e:
            java.lang.String r6 = "matrix"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0227
            goto L_0x0254
        L_0x0227:
            r5 = 4
            goto L_0x0254
        L_0x0229:
            java.lang.String r6 = "height"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0232
            goto L_0x0254
        L_0x0232:
            r5 = 3
            goto L_0x0254
        L_0x0234:
            java.lang.String r6 = "opacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x023d
            goto L_0x0254
        L_0x023d:
            r5 = 2
            goto L_0x0254
        L_0x023f:
            java.lang.String r6 = "filter"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0248
            goto L_0x0254
        L_0x0248:
            r5 = r0
            goto L_0x0254
        L_0x024a:
            java.lang.String r6 = "vbHeight"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0253
            goto L_0x0254
        L_0x0253:
            r5 = r3
        L_0x0254:
            switch(r5) {
                case 0: goto L_0x04e7;
                case 1: goto L_0x04d9;
                case 2: goto L_0x04ca;
                case 3: goto L_0x04bd;
                case 4: goto L_0x04b3;
                case 5: goto L_0x04a9;
                case 6: goto L_0x049b;
                case 7: goto L_0x048d;
                case 8: goto L_0x047f;
                case 9: goto L_0x0471;
                case 10: goto L_0x045f;
                case 11: goto L_0x044d;
                case 12: goto L_0x043e;
                case 13: goto L_0x042c;
                case 14: goto L_0x041a;
                case 15: goto L_0x0408;
                case 16: goto L_0x03f6;
                case 17: goto L_0x03e8;
                case 18: goto L_0x03da;
                case 19: goto L_0x03cc;
                case 20: goto L_0x03be;
                case 21: goto L_0x03af;
                case 22: goto L_0x039d;
                case 23: goto L_0x038b;
                case 24: goto L_0x037c;
                case 25: goto L_0x036a;
                case 26: goto L_0x035b;
                case 27: goto L_0x034a;
                case 28: goto L_0x0338;
                case 29: goto L_0x032a;
                case 30: goto L_0x031b;
                case 31: goto L_0x0309;
                case 32: goto L_0x02fb;
                case 33: goto L_0x02ed;
                case 34: goto L_0x02e2;
                case 35: goto L_0x02d3;
                case 36: goto L_0x02c1;
                case 37: goto L_0x02af;
                case 38: goto L_0x02a0;
                case 39: goto L_0x028e;
                case 40: goto L_0x027c;
                case 41: goto L_0x026a;
                case 42: goto L_0x025c;
                default: goto L_0x0257;
            }
        L_0x0257:
            super.setProperty(r8, r9, r10)
            goto L_0x04f7
        L_0x025c:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStrokeWidth(r8, r0)
            goto L_0x04f7
        L_0x026a:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            if (r10 != 0) goto L_0x0271
            goto L_0x0277
        L_0x0271:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x0277:
            r9.setMeetOrSlice(r8, r3)
            goto L_0x04f7
        L_0x027c:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            if (r10 != 0) goto L_0x0283
            goto L_0x0289
        L_0x0283:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r3 = r10.booleanValue()
        L_0x0289:
            r9.setResponsible(r8, r3)
            goto L_0x04f7
        L_0x028e:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            if (r10 != 0) goto L_0x0295
            goto L_0x029b
        L_0x0295:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x029b:
            r9.setStrokeLinejoin(r8, r3)
            goto L_0x04f7
        L_0x02a0:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            if (r10 != 0) goto L_0x02a7
            goto L_0x02aa
        L_0x02a7:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x02aa:
            r9.setDisplay(r8, r4)
            goto L_0x04f7
        L_0x02af:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            if (r10 != 0) goto L_0x02b6
            goto L_0x02bc
        L_0x02b6:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x02bc:
            r9.setStrokeLinecap(r8, r3)
            goto L_0x04f7
        L_0x02c1:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            if (r10 != 0) goto L_0x02c8
            goto L_0x02ce
        L_0x02c8:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x02ce:
            r9.setClipRule(r8, r3)
            goto L_0x04f7
        L_0x02d3:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            if (r10 != 0) goto L_0x02da
            goto L_0x02dd
        L_0x02da:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x02dd:
            r9.setClipPath(r8, r4)
            goto L_0x04f7
        L_0x02e2:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            com.facebook.react.bridge.ReadableArray r10 = (com.facebook.react.bridge.ReadableArray) r10
            r9.setPatternTransform(r8, r10)
            goto L_0x04f7
        L_0x02ed:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStrokeDasharray(r8, r0)
            goto L_0x04f7
        L_0x02fb:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFontSize(r8, r0)
            goto L_0x04f7
        L_0x0309:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            if (r10 != 0) goto L_0x0310
            goto L_0x0316
        L_0x0310:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x0316:
            r9.setVbWidth(r8, r2)
            goto L_0x04f7
        L_0x031b:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            if (r10 != 0) goto L_0x0322
            goto L_0x0325
        L_0x0322:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0325:
            r9.setMarkerStart(r8, r4)
            goto L_0x04f7
        L_0x032a:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setWidth(r8, r0)
            goto L_0x04f7
        L_0x0338:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            if (r10 != 0) goto L_0x033f
            goto L_0x0345
        L_0x033f:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x0345:
            r9.setVectorEffect(r8, r3)
            goto L_0x04f7
        L_0x034a:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            android.content.Context r0 = r8.getContext()
            java.lang.Integer r10 = com.facebook.react.bridge.ColorPropConverter.getColor(r10, r0)
            r9.setColor(r8, r10)
            goto L_0x04f7
        L_0x035b:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            if (r10 != 0) goto L_0x0362
            goto L_0x0365
        L_0x0362:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0365:
            r9.setAlign(r8, r4)
            goto L_0x04f7
        L_0x036a:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            if (r10 != 0) goto L_0x0371
            goto L_0x0377
        L_0x0371:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x0377:
            r9.setStrokeMiterlimit(r8, r2)
            goto L_0x04f7
        L_0x037c:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            if (r10 != 0) goto L_0x0383
            goto L_0x0386
        L_0x0383:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0386:
            r9.setName(r8, r4)
            goto L_0x04f7
        L_0x038b:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            if (r10 != 0) goto L_0x0392
            goto L_0x0398
        L_0x0392:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x0398:
            r9.setMinY(r8, r2)
            goto L_0x04f7
        L_0x039d:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            if (r10 != 0) goto L_0x03a4
            goto L_0x03aa
        L_0x03a4:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x03aa:
            r9.setMinX(r8, r2)
            goto L_0x04f7
        L_0x03af:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            if (r10 != 0) goto L_0x03b6
            goto L_0x03b9
        L_0x03b6:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x03b9:
            r9.setMask(r8, r4)
            goto L_0x04f7
        L_0x03be:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFont(r8, r0)
            goto L_0x04f7
        L_0x03cc:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFill(r8, r0)
            goto L_0x04f7
        L_0x03da:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setY(r8, r0)
            goto L_0x04f7
        L_0x03e8:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setX(r8, r0)
            goto L_0x04f7
        L_0x03f6:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            if (r10 != 0) goto L_0x03fd
            goto L_0x0403
        L_0x03fd:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x0403:
            r9.setStrokeDashoffset(r8, r2)
            goto L_0x04f7
        L_0x0408:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            if (r10 != 0) goto L_0x040f
            goto L_0x0415
        L_0x040f:
            java.lang.Double r10 = (java.lang.Double) r10
            float r1 = r10.floatValue()
        L_0x0415:
            r9.setFillOpacity(r8, r1)
            goto L_0x04f7
        L_0x041a:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            if (r10 != 0) goto L_0x0421
            goto L_0x0427
        L_0x0421:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x0427:
            r9.setPatternContentUnits(r8, r3)
            goto L_0x04f7
        L_0x042c:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            if (r10 != 0) goto L_0x0433
            goto L_0x0439
        L_0x0433:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x0439:
            r9.setPatternUnits(r8, r3)
            goto L_0x04f7
        L_0x043e:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            if (r10 != 0) goto L_0x0445
            goto L_0x0448
        L_0x0445:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0448:
            r9.setPointerEvents(r8, r4)
            goto L_0x04f7
        L_0x044d:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            if (r10 != 0) goto L_0x0454
            goto L_0x045a
        L_0x0454:
            java.lang.Double r10 = (java.lang.Double) r10
            float r1 = r10.floatValue()
        L_0x045a:
            r9.setStrokeOpacity(r8, r1)
            goto L_0x04f7
        L_0x045f:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            if (r10 != 0) goto L_0x0466
            goto L_0x046c
        L_0x0466:
            java.lang.Double r10 = (java.lang.Double) r10
            int r0 = r10.intValue()
        L_0x046c:
            r9.setFillRule(r8, r0)
            goto L_0x04f7
        L_0x0471:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFontWeight(r8, r0)
            goto L_0x04f7
        L_0x047f:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStroke(r8, r0)
            goto L_0x04f7
        L_0x048d:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            if (r10 != 0) goto L_0x0494
            goto L_0x0497
        L_0x0494:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0497:
            r9.setMarkerMid(r8, r4)
            goto L_0x04f7
        L_0x049b:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            if (r10 != 0) goto L_0x04a2
            goto L_0x04a5
        L_0x04a2:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x04a5:
            r9.setMarkerEnd(r8, r4)
            goto L_0x04f7
        L_0x04a9:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            com.facebook.react.bridge.ReadableArray r10 = (com.facebook.react.bridge.ReadableArray) r10
            r9.setPropList(r8, r10)
            goto L_0x04f7
        L_0x04b3:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            com.facebook.react.bridge.ReadableArray r10 = (com.facebook.react.bridge.ReadableArray) r10
            r9.setMatrix(r8, r10)
            goto L_0x04f7
        L_0x04bd:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setHeight(r8, r0)
            goto L_0x04f7
        L_0x04ca:
            U r9 = r7.mViewManager
            if (r10 != 0) goto L_0x04cf
            goto L_0x04d5
        L_0x04cf:
            java.lang.Double r10 = (java.lang.Double) r10
            float r1 = r10.floatValue()
        L_0x04d5:
            r9.setOpacity(r8, r1)
            goto L_0x04f7
        L_0x04d9:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            if (r10 != 0) goto L_0x04e0
            goto L_0x04e3
        L_0x04e0:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x04e3:
            r9.setFilter(r8, r4)
            goto L_0x04f7
        L_0x04e7:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGPatternManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGPatternManagerInterface) r9
            if (r10 != 0) goto L_0x04ee
            goto L_0x04f4
        L_0x04ee:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x04f4:
            r9.setVbHeight(r8, r2)
        L_0x04f7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSVGPatternManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
