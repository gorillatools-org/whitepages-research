package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSVGMaskManagerInterface;

public class RNSVGMaskManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSVGMaskManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGMaskManagerDelegate(U u) {
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
            r1 = 0
            r2 = 1065353216(0x3f800000, float:1.0)
            r3 = 0
            r4 = 0
            r5 = -1
            int r6 = r9.hashCode()
            switch(r6) {
                case -1274492040: goto L_0x01f6;
                case -1267206133: goto L_0x01eb;
                case -1221029593: goto L_0x01e0;
                case -1081239615: goto L_0x01d5;
                case -993894751: goto L_0x01ca;
                case -933864895: goto L_0x01bf;
                case -933857362: goto L_0x01b4;
                case -891980232: goto L_0x01a9;
                case -734428249: goto L_0x019b;
                case -729118945: goto L_0x018d;
                case -416535885: goto L_0x017f;
                case -293492298: goto L_0x0171;
                case -61221917: goto L_0x0163;
                case -53677816: goto L_0x0155;
                case -44578051: goto L_0x0147;
                case 120: goto L_0x0139;
                case 121: goto L_0x012b;
                case 3143043: goto L_0x011d;
                case 3148879: goto L_0x010f;
                case 3344108: goto L_0x0101;
                case 3373707: goto L_0x00f3;
                case 78845486: goto L_0x00e5;
                case 94842723: goto L_0x00d7;
                case 104482996: goto L_0x00c9;
                case 113126854: goto L_0x00bb;
                case 217109576: goto L_0x00ad;
                case 275100742: goto L_0x009f;
                case 365601008: goto L_0x0091;
                case 401643183: goto L_0x0083;
                case 917656469: goto L_0x0075;
                case 917735020: goto L_0x0067;
                case 1027575302: goto L_0x0059;
                case 1671764162: goto L_0x004b;
                case 1790285174: goto L_0x003d;
                case 1847674614: goto L_0x002f;
                case 1924065902: goto L_0x0021;
                case 2037673858: goto L_0x0013;
                default: goto L_0x0011;
            }
        L_0x0011:
            goto L_0x0200
        L_0x0013:
            java.lang.String r6 = "maskContentUnits"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x001d
            goto L_0x0200
        L_0x001d:
            r5 = 36
            goto L_0x0200
        L_0x0021:
            java.lang.String r6 = "strokeWidth"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x002b
            goto L_0x0200
        L_0x002b:
            r5 = 35
            goto L_0x0200
        L_0x002f:
            java.lang.String r6 = "responsible"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0039
            goto L_0x0200
        L_0x0039:
            r5 = 34
            goto L_0x0200
        L_0x003d:
            java.lang.String r6 = "strokeLinejoin"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0047
            goto L_0x0200
        L_0x0047:
            r5 = 33
            goto L_0x0200
        L_0x004b:
            java.lang.String r6 = "display"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0055
            goto L_0x0200
        L_0x0055:
            r5 = 32
            goto L_0x0200
        L_0x0059:
            java.lang.String r6 = "strokeLinecap"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0063
            goto L_0x0200
        L_0x0063:
            r5 = 31
            goto L_0x0200
        L_0x0067:
            java.lang.String r6 = "clipRule"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0071
            goto L_0x0200
        L_0x0071:
            r5 = 30
            goto L_0x0200
        L_0x0075:
            java.lang.String r6 = "clipPath"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x007f
            goto L_0x0200
        L_0x007f:
            r5 = 29
            goto L_0x0200
        L_0x0083:
            java.lang.String r6 = "strokeDasharray"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x008d
            goto L_0x0200
        L_0x008d:
            r5 = 28
            goto L_0x0200
        L_0x0091:
            java.lang.String r6 = "fontSize"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x009b
            goto L_0x0200
        L_0x009b:
            r5 = 27
            goto L_0x0200
        L_0x009f:
            java.lang.String r6 = "maskType"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00a9
            goto L_0x0200
        L_0x00a9:
            r5 = 26
            goto L_0x0200
        L_0x00ad:
            java.lang.String r6 = "markerStart"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00b7
            goto L_0x0200
        L_0x00b7:
            r5 = 25
            goto L_0x0200
        L_0x00bb:
            java.lang.String r6 = "width"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00c5
            goto L_0x0200
        L_0x00c5:
            r5 = 24
            goto L_0x0200
        L_0x00c9:
            java.lang.String r6 = "vectorEffect"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00d3
            goto L_0x0200
        L_0x00d3:
            r5 = 23
            goto L_0x0200
        L_0x00d7:
            java.lang.String r6 = "color"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00e1
            goto L_0x0200
        L_0x00e1:
            r5 = 22
            goto L_0x0200
        L_0x00e5:
            java.lang.String r6 = "strokeMiterlimit"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00ef
            goto L_0x0200
        L_0x00ef:
            r5 = 21
            goto L_0x0200
        L_0x00f3:
            java.lang.String r6 = "name"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00fd
            goto L_0x0200
        L_0x00fd:
            r5 = 20
            goto L_0x0200
        L_0x0101:
            java.lang.String r6 = "mask"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x010b
            goto L_0x0200
        L_0x010b:
            r5 = 19
            goto L_0x0200
        L_0x010f:
            java.lang.String r6 = "font"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0119
            goto L_0x0200
        L_0x0119:
            r5 = 18
            goto L_0x0200
        L_0x011d:
            java.lang.String r6 = "fill"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0127
            goto L_0x0200
        L_0x0127:
            r5 = 17
            goto L_0x0200
        L_0x012b:
            java.lang.String r6 = "y"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0135
            goto L_0x0200
        L_0x0135:
            r5 = 16
            goto L_0x0200
        L_0x0139:
            java.lang.String r6 = "x"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0143
            goto L_0x0200
        L_0x0143:
            r5 = 15
            goto L_0x0200
        L_0x0147:
            java.lang.String r6 = "strokeDashoffset"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0151
            goto L_0x0200
        L_0x0151:
            r5 = 14
            goto L_0x0200
        L_0x0155:
            java.lang.String r6 = "fillOpacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x015f
            goto L_0x0200
        L_0x015f:
            r5 = 13
            goto L_0x0200
        L_0x0163:
            java.lang.String r6 = "maskUnits"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x016d
            goto L_0x0200
        L_0x016d:
            r5 = 12
            goto L_0x0200
        L_0x0171:
            java.lang.String r6 = "pointerEvents"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x017b
            goto L_0x0200
        L_0x017b:
            r5 = 11
            goto L_0x0200
        L_0x017f:
            java.lang.String r6 = "strokeOpacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0189
            goto L_0x0200
        L_0x0189:
            r5 = 10
            goto L_0x0200
        L_0x018d:
            java.lang.String r6 = "fillRule"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0197
            goto L_0x0200
        L_0x0197:
            r5 = 9
            goto L_0x0200
        L_0x019b:
            java.lang.String r6 = "fontWeight"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01a5
            goto L_0x0200
        L_0x01a5:
            r5 = 8
            goto L_0x0200
        L_0x01a9:
            java.lang.String r6 = "stroke"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01b2
            goto L_0x0200
        L_0x01b2:
            r5 = 7
            goto L_0x0200
        L_0x01b4:
            java.lang.String r6 = "markerMid"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01bd
            goto L_0x0200
        L_0x01bd:
            r5 = 6
            goto L_0x0200
        L_0x01bf:
            java.lang.String r6 = "markerEnd"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01c8
            goto L_0x0200
        L_0x01c8:
            r5 = 5
            goto L_0x0200
        L_0x01ca:
            java.lang.String r6 = "propList"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01d3
            goto L_0x0200
        L_0x01d3:
            r5 = 4
            goto L_0x0200
        L_0x01d5:
            java.lang.String r6 = "matrix"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01de
            goto L_0x0200
        L_0x01de:
            r5 = 3
            goto L_0x0200
        L_0x01e0:
            java.lang.String r6 = "height"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01e9
            goto L_0x0200
        L_0x01e9:
            r5 = 2
            goto L_0x0200
        L_0x01eb:
            java.lang.String r6 = "opacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01f4
            goto L_0x0200
        L_0x01f4:
            r5 = r0
            goto L_0x0200
        L_0x01f6:
            java.lang.String r6 = "filter"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01ff
            goto L_0x0200
        L_0x01ff:
            r5 = r3
        L_0x0200:
            switch(r5) {
                case 0: goto L_0x0434;
                case 1: goto L_0x0425;
                case 2: goto L_0x0418;
                case 3: goto L_0x040e;
                case 4: goto L_0x0404;
                case 5: goto L_0x03f6;
                case 6: goto L_0x03e8;
                case 7: goto L_0x03db;
                case 8: goto L_0x03cd;
                case 9: goto L_0x03bb;
                case 10: goto L_0x03a9;
                case 11: goto L_0x039a;
                case 12: goto L_0x0388;
                case 13: goto L_0x0376;
                case 14: goto L_0x0364;
                case 15: goto L_0x0356;
                case 16: goto L_0x0348;
                case 17: goto L_0x033a;
                case 18: goto L_0x032c;
                case 19: goto L_0x031d;
                case 20: goto L_0x030e;
                case 21: goto L_0x02fc;
                case 22: goto L_0x02eb;
                case 23: goto L_0x02d9;
                case 24: goto L_0x02cb;
                case 25: goto L_0x02bc;
                case 26: goto L_0x02aa;
                case 27: goto L_0x029c;
                case 28: goto L_0x028e;
                case 29: goto L_0x027f;
                case 30: goto L_0x026d;
                case 31: goto L_0x025b;
                case 32: goto L_0x024c;
                case 33: goto L_0x023a;
                case 34: goto L_0x0228;
                case 35: goto L_0x021a;
                case 36: goto L_0x0208;
                default: goto L_0x0203;
            }
        L_0x0203:
            super.setProperty(r8, r9, r10)
            goto L_0x0441
        L_0x0208:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            if (r10 != 0) goto L_0x020f
            goto L_0x0215
        L_0x020f:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x0215:
            r9.setMaskContentUnits(r8, r3)
            goto L_0x0441
        L_0x021a:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStrokeWidth(r8, r0)
            goto L_0x0441
        L_0x0228:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            if (r10 != 0) goto L_0x022f
            goto L_0x0235
        L_0x022f:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r3 = r10.booleanValue()
        L_0x0235:
            r9.setResponsible(r8, r3)
            goto L_0x0441
        L_0x023a:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            if (r10 != 0) goto L_0x0241
            goto L_0x0247
        L_0x0241:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x0247:
            r9.setStrokeLinejoin(r8, r3)
            goto L_0x0441
        L_0x024c:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            if (r10 != 0) goto L_0x0253
            goto L_0x0256
        L_0x0253:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0256:
            r9.setDisplay(r8, r4)
            goto L_0x0441
        L_0x025b:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            if (r10 != 0) goto L_0x0262
            goto L_0x0268
        L_0x0262:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x0268:
            r9.setStrokeLinecap(r8, r3)
            goto L_0x0441
        L_0x026d:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            if (r10 != 0) goto L_0x0274
            goto L_0x027a
        L_0x0274:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x027a:
            r9.setClipRule(r8, r3)
            goto L_0x0441
        L_0x027f:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            if (r10 != 0) goto L_0x0286
            goto L_0x0289
        L_0x0286:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0289:
            r9.setClipPath(r8, r4)
            goto L_0x0441
        L_0x028e:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStrokeDasharray(r8, r0)
            goto L_0x0441
        L_0x029c:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFontSize(r8, r0)
            goto L_0x0441
        L_0x02aa:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            if (r10 != 0) goto L_0x02b1
            goto L_0x02b7
        L_0x02b1:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x02b7:
            r9.setMaskType(r8, r3)
            goto L_0x0441
        L_0x02bc:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            if (r10 != 0) goto L_0x02c3
            goto L_0x02c6
        L_0x02c3:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x02c6:
            r9.setMarkerStart(r8, r4)
            goto L_0x0441
        L_0x02cb:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setWidth(r8, r0)
            goto L_0x0441
        L_0x02d9:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            if (r10 != 0) goto L_0x02e0
            goto L_0x02e6
        L_0x02e0:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x02e6:
            r9.setVectorEffect(r8, r3)
            goto L_0x0441
        L_0x02eb:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            android.content.Context r0 = r8.getContext()
            java.lang.Integer r10 = com.facebook.react.bridge.ColorPropConverter.getColor(r10, r0)
            r9.setColor(r8, r10)
            goto L_0x0441
        L_0x02fc:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            if (r10 != 0) goto L_0x0303
            goto L_0x0309
        L_0x0303:
            java.lang.Double r10 = (java.lang.Double) r10
            float r1 = r10.floatValue()
        L_0x0309:
            r9.setStrokeMiterlimit(r8, r1)
            goto L_0x0441
        L_0x030e:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            if (r10 != 0) goto L_0x0315
            goto L_0x0318
        L_0x0315:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0318:
            r9.setName(r8, r4)
            goto L_0x0441
        L_0x031d:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            if (r10 != 0) goto L_0x0324
            goto L_0x0327
        L_0x0324:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0327:
            r9.setMask(r8, r4)
            goto L_0x0441
        L_0x032c:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFont(r8, r0)
            goto L_0x0441
        L_0x033a:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFill(r8, r0)
            goto L_0x0441
        L_0x0348:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setY(r8, r0)
            goto L_0x0441
        L_0x0356:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setX(r8, r0)
            goto L_0x0441
        L_0x0364:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            if (r10 != 0) goto L_0x036b
            goto L_0x0371
        L_0x036b:
            java.lang.Double r10 = (java.lang.Double) r10
            float r1 = r10.floatValue()
        L_0x0371:
            r9.setStrokeDashoffset(r8, r1)
            goto L_0x0441
        L_0x0376:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            if (r10 != 0) goto L_0x037d
            goto L_0x0383
        L_0x037d:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x0383:
            r9.setFillOpacity(r8, r2)
            goto L_0x0441
        L_0x0388:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            if (r10 != 0) goto L_0x038f
            goto L_0x0395
        L_0x038f:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x0395:
            r9.setMaskUnits(r8, r3)
            goto L_0x0441
        L_0x039a:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            if (r10 != 0) goto L_0x03a1
            goto L_0x03a4
        L_0x03a1:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x03a4:
            r9.setPointerEvents(r8, r4)
            goto L_0x0441
        L_0x03a9:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            if (r10 != 0) goto L_0x03b0
            goto L_0x03b6
        L_0x03b0:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x03b6:
            r9.setStrokeOpacity(r8, r2)
            goto L_0x0441
        L_0x03bb:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            if (r10 != 0) goto L_0x03c2
            goto L_0x03c8
        L_0x03c2:
            java.lang.Double r10 = (java.lang.Double) r10
            int r0 = r10.intValue()
        L_0x03c8:
            r9.setFillRule(r8, r0)
            goto L_0x0441
        L_0x03cd:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFontWeight(r8, r0)
            goto L_0x0441
        L_0x03db:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStroke(r8, r0)
            goto L_0x0441
        L_0x03e8:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            if (r10 != 0) goto L_0x03ef
            goto L_0x03f2
        L_0x03ef:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x03f2:
            r9.setMarkerMid(r8, r4)
            goto L_0x0441
        L_0x03f6:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            if (r10 != 0) goto L_0x03fd
            goto L_0x0400
        L_0x03fd:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0400:
            r9.setMarkerEnd(r8, r4)
            goto L_0x0441
        L_0x0404:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            com.facebook.react.bridge.ReadableArray r10 = (com.facebook.react.bridge.ReadableArray) r10
            r9.setPropList(r8, r10)
            goto L_0x0441
        L_0x040e:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            com.facebook.react.bridge.ReadableArray r10 = (com.facebook.react.bridge.ReadableArray) r10
            r9.setMatrix(r8, r10)
            goto L_0x0441
        L_0x0418:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setHeight(r8, r0)
            goto L_0x0441
        L_0x0425:
            U r9 = r7.mViewManager
            if (r10 != 0) goto L_0x042a
            goto L_0x0430
        L_0x042a:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x0430:
            r9.setOpacity(r8, r2)
            goto L_0x0441
        L_0x0434:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMaskManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMaskManagerInterface) r9
            if (r10 != 0) goto L_0x043b
            goto L_0x043e
        L_0x043b:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x043e:
            r9.setFilter(r8, r4)
        L_0x0441:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSVGMaskManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
