package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface;

public class RNSVGSymbolManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSVGSymbolManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGSymbolManagerDelegate(U u) {
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
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v12, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v13, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v14, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v15, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v16, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v17, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v18, resolved type: boolean} */
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
                case -1567958285: goto L_0x01e8;
                case -1274492040: goto L_0x01dd;
                case -1267206133: goto L_0x01d2;
                case -1081239615: goto L_0x01c7;
                case -993894751: goto L_0x01bc;
                case -933864895: goto L_0x01b1;
                case -933857362: goto L_0x01a6;
                case -891980232: goto L_0x019b;
                case -734428249: goto L_0x018d;
                case -729118945: goto L_0x017f;
                case -416535885: goto L_0x0171;
                case -293492298: goto L_0x0163;
                case -53677816: goto L_0x0155;
                case -44578051: goto L_0x0147;
                case 3143043: goto L_0x0139;
                case 3148879: goto L_0x012b;
                case 3344108: goto L_0x011d;
                case 3351622: goto L_0x010f;
                case 3351623: goto L_0x0101;
                case 3373707: goto L_0x00f3;
                case 78845486: goto L_0x00e5;
                case 92903173: goto L_0x00d7;
                case 94842723: goto L_0x00c9;
                case 104482996: goto L_0x00bb;
                case 217109576: goto L_0x00ad;
                case 240482938: goto L_0x009f;
                case 365601008: goto L_0x0091;
                case 401643183: goto L_0x0083;
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
            goto L_0x01f2
        L_0x0013:
            java.lang.String r6 = "strokeWidth"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x001d
            goto L_0x01f2
        L_0x001d:
            r5 = 35
            goto L_0x01f2
        L_0x0021:
            java.lang.String r6 = "meetOrSlice"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x002b
            goto L_0x01f2
        L_0x002b:
            r5 = 34
            goto L_0x01f2
        L_0x002f:
            java.lang.String r6 = "responsible"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0039
            goto L_0x01f2
        L_0x0039:
            r5 = 33
            goto L_0x01f2
        L_0x003d:
            java.lang.String r6 = "strokeLinejoin"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0047
            goto L_0x01f2
        L_0x0047:
            r5 = 32
            goto L_0x01f2
        L_0x004b:
            java.lang.String r6 = "display"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0055
            goto L_0x01f2
        L_0x0055:
            r5 = 31
            goto L_0x01f2
        L_0x0059:
            java.lang.String r6 = "strokeLinecap"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0063
            goto L_0x01f2
        L_0x0063:
            r5 = 30
            goto L_0x01f2
        L_0x0067:
            java.lang.String r6 = "clipRule"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0071
            goto L_0x01f2
        L_0x0071:
            r5 = 29
            goto L_0x01f2
        L_0x0075:
            java.lang.String r6 = "clipPath"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x007f
            goto L_0x01f2
        L_0x007f:
            r5 = 28
            goto L_0x01f2
        L_0x0083:
            java.lang.String r6 = "strokeDasharray"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x008d
            goto L_0x01f2
        L_0x008d:
            r5 = 27
            goto L_0x01f2
        L_0x0091:
            java.lang.String r6 = "fontSize"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x009b
            goto L_0x01f2
        L_0x009b:
            r5 = 26
            goto L_0x01f2
        L_0x009f:
            java.lang.String r6 = "vbWidth"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00a9
            goto L_0x01f2
        L_0x00a9:
            r5 = 25
            goto L_0x01f2
        L_0x00ad:
            java.lang.String r6 = "markerStart"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00b7
            goto L_0x01f2
        L_0x00b7:
            r5 = 24
            goto L_0x01f2
        L_0x00bb:
            java.lang.String r6 = "vectorEffect"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00c5
            goto L_0x01f2
        L_0x00c5:
            r5 = 23
            goto L_0x01f2
        L_0x00c9:
            java.lang.String r6 = "color"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00d3
            goto L_0x01f2
        L_0x00d3:
            r5 = 22
            goto L_0x01f2
        L_0x00d7:
            java.lang.String r6 = "align"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00e1
            goto L_0x01f2
        L_0x00e1:
            r5 = 21
            goto L_0x01f2
        L_0x00e5:
            java.lang.String r6 = "strokeMiterlimit"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00ef
            goto L_0x01f2
        L_0x00ef:
            r5 = 20
            goto L_0x01f2
        L_0x00f3:
            java.lang.String r6 = "name"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00fd
            goto L_0x01f2
        L_0x00fd:
            r5 = 19
            goto L_0x01f2
        L_0x0101:
            java.lang.String r6 = "minY"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x010b
            goto L_0x01f2
        L_0x010b:
            r5 = 18
            goto L_0x01f2
        L_0x010f:
            java.lang.String r6 = "minX"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0119
            goto L_0x01f2
        L_0x0119:
            r5 = 17
            goto L_0x01f2
        L_0x011d:
            java.lang.String r6 = "mask"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0127
            goto L_0x01f2
        L_0x0127:
            r5 = 16
            goto L_0x01f2
        L_0x012b:
            java.lang.String r6 = "font"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0135
            goto L_0x01f2
        L_0x0135:
            r5 = 15
            goto L_0x01f2
        L_0x0139:
            java.lang.String r6 = "fill"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0143
            goto L_0x01f2
        L_0x0143:
            r5 = 14
            goto L_0x01f2
        L_0x0147:
            java.lang.String r6 = "strokeDashoffset"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0151
            goto L_0x01f2
        L_0x0151:
            r5 = 13
            goto L_0x01f2
        L_0x0155:
            java.lang.String r6 = "fillOpacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x015f
            goto L_0x01f2
        L_0x015f:
            r5 = 12
            goto L_0x01f2
        L_0x0163:
            java.lang.String r6 = "pointerEvents"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x016d
            goto L_0x01f2
        L_0x016d:
            r5 = 11
            goto L_0x01f2
        L_0x0171:
            java.lang.String r6 = "strokeOpacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x017b
            goto L_0x01f2
        L_0x017b:
            r5 = 10
            goto L_0x01f2
        L_0x017f:
            java.lang.String r6 = "fillRule"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0189
            goto L_0x01f2
        L_0x0189:
            r5 = 9
            goto L_0x01f2
        L_0x018d:
            java.lang.String r6 = "fontWeight"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0197
            goto L_0x01f2
        L_0x0197:
            r5 = 8
            goto L_0x01f2
        L_0x019b:
            java.lang.String r6 = "stroke"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01a4
            goto L_0x01f2
        L_0x01a4:
            r5 = 7
            goto L_0x01f2
        L_0x01a6:
            java.lang.String r6 = "markerMid"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01af
            goto L_0x01f2
        L_0x01af:
            r5 = 6
            goto L_0x01f2
        L_0x01b1:
            java.lang.String r6 = "markerEnd"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01ba
            goto L_0x01f2
        L_0x01ba:
            r5 = 5
            goto L_0x01f2
        L_0x01bc:
            java.lang.String r6 = "propList"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01c5
            goto L_0x01f2
        L_0x01c5:
            r5 = 4
            goto L_0x01f2
        L_0x01c7:
            java.lang.String r6 = "matrix"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01d0
            goto L_0x01f2
        L_0x01d0:
            r5 = 3
            goto L_0x01f2
        L_0x01d2:
            java.lang.String r6 = "opacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01db
            goto L_0x01f2
        L_0x01db:
            r5 = 2
            goto L_0x01f2
        L_0x01dd:
            java.lang.String r6 = "filter"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01e6
            goto L_0x01f2
        L_0x01e6:
            r5 = r0
            goto L_0x01f2
        L_0x01e8:
            java.lang.String r6 = "vbHeight"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01f1
            goto L_0x01f2
        L_0x01f1:
            r5 = r3
        L_0x01f2:
            switch(r5) {
                case 0: goto L_0x041e;
                case 1: goto L_0x0410;
                case 2: goto L_0x0401;
                case 3: goto L_0x03f7;
                case 4: goto L_0x03ed;
                case 5: goto L_0x03df;
                case 6: goto L_0x03d1;
                case 7: goto L_0x03c4;
                case 8: goto L_0x03b6;
                case 9: goto L_0x03a4;
                case 10: goto L_0x0392;
                case 11: goto L_0x0383;
                case 12: goto L_0x0371;
                case 13: goto L_0x035f;
                case 14: goto L_0x0351;
                case 15: goto L_0x0343;
                case 16: goto L_0x0334;
                case 17: goto L_0x0322;
                case 18: goto L_0x0310;
                case 19: goto L_0x0301;
                case 20: goto L_0x02ef;
                case 21: goto L_0x02e0;
                case 22: goto L_0x02cf;
                case 23: goto L_0x02bd;
                case 24: goto L_0x02ae;
                case 25: goto L_0x029c;
                case 26: goto L_0x028e;
                case 27: goto L_0x0280;
                case 28: goto L_0x0271;
                case 29: goto L_0x025f;
                case 30: goto L_0x024d;
                case 31: goto L_0x023e;
                case 32: goto L_0x022c;
                case 33: goto L_0x021a;
                case 34: goto L_0x0208;
                case 35: goto L_0x01fa;
                default: goto L_0x01f5;
            }
        L_0x01f5:
            super.setProperty(r8, r9, r10)
            goto L_0x042e
        L_0x01fa:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStrokeWidth(r8, r0)
            goto L_0x042e
        L_0x0208:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            if (r10 != 0) goto L_0x020f
            goto L_0x0215
        L_0x020f:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x0215:
            r9.setMeetOrSlice(r8, r3)
            goto L_0x042e
        L_0x021a:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            if (r10 != 0) goto L_0x0221
            goto L_0x0227
        L_0x0221:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r3 = r10.booleanValue()
        L_0x0227:
            r9.setResponsible(r8, r3)
            goto L_0x042e
        L_0x022c:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            if (r10 != 0) goto L_0x0233
            goto L_0x0239
        L_0x0233:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x0239:
            r9.setStrokeLinejoin(r8, r3)
            goto L_0x042e
        L_0x023e:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            if (r10 != 0) goto L_0x0245
            goto L_0x0248
        L_0x0245:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0248:
            r9.setDisplay(r8, r4)
            goto L_0x042e
        L_0x024d:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            if (r10 != 0) goto L_0x0254
            goto L_0x025a
        L_0x0254:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x025a:
            r9.setStrokeLinecap(r8, r3)
            goto L_0x042e
        L_0x025f:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            if (r10 != 0) goto L_0x0266
            goto L_0x026c
        L_0x0266:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x026c:
            r9.setClipRule(r8, r3)
            goto L_0x042e
        L_0x0271:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            if (r10 != 0) goto L_0x0278
            goto L_0x027b
        L_0x0278:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x027b:
            r9.setClipPath(r8, r4)
            goto L_0x042e
        L_0x0280:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStrokeDasharray(r8, r0)
            goto L_0x042e
        L_0x028e:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFontSize(r8, r0)
            goto L_0x042e
        L_0x029c:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            if (r10 != 0) goto L_0x02a3
            goto L_0x02a9
        L_0x02a3:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x02a9:
            r9.setVbWidth(r8, r2)
            goto L_0x042e
        L_0x02ae:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            if (r10 != 0) goto L_0x02b5
            goto L_0x02b8
        L_0x02b5:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x02b8:
            r9.setMarkerStart(r8, r4)
            goto L_0x042e
        L_0x02bd:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            if (r10 != 0) goto L_0x02c4
            goto L_0x02ca
        L_0x02c4:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x02ca:
            r9.setVectorEffect(r8, r3)
            goto L_0x042e
        L_0x02cf:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            android.content.Context r0 = r8.getContext()
            java.lang.Integer r10 = com.facebook.react.bridge.ColorPropConverter.getColor(r10, r0)
            r9.setColor(r8, r10)
            goto L_0x042e
        L_0x02e0:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            if (r10 != 0) goto L_0x02e7
            goto L_0x02ea
        L_0x02e7:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x02ea:
            r9.setAlign(r8, r4)
            goto L_0x042e
        L_0x02ef:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            if (r10 != 0) goto L_0x02f6
            goto L_0x02fc
        L_0x02f6:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x02fc:
            r9.setStrokeMiterlimit(r8, r2)
            goto L_0x042e
        L_0x0301:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            if (r10 != 0) goto L_0x0308
            goto L_0x030b
        L_0x0308:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x030b:
            r9.setName(r8, r4)
            goto L_0x042e
        L_0x0310:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            if (r10 != 0) goto L_0x0317
            goto L_0x031d
        L_0x0317:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x031d:
            r9.setMinY(r8, r2)
            goto L_0x042e
        L_0x0322:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            if (r10 != 0) goto L_0x0329
            goto L_0x032f
        L_0x0329:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x032f:
            r9.setMinX(r8, r2)
            goto L_0x042e
        L_0x0334:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            if (r10 != 0) goto L_0x033b
            goto L_0x033e
        L_0x033b:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x033e:
            r9.setMask(r8, r4)
            goto L_0x042e
        L_0x0343:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFont(r8, r0)
            goto L_0x042e
        L_0x0351:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFill(r8, r0)
            goto L_0x042e
        L_0x035f:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            if (r10 != 0) goto L_0x0366
            goto L_0x036c
        L_0x0366:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x036c:
            r9.setStrokeDashoffset(r8, r2)
            goto L_0x042e
        L_0x0371:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            if (r10 != 0) goto L_0x0378
            goto L_0x037e
        L_0x0378:
            java.lang.Double r10 = (java.lang.Double) r10
            float r1 = r10.floatValue()
        L_0x037e:
            r9.setFillOpacity(r8, r1)
            goto L_0x042e
        L_0x0383:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            if (r10 != 0) goto L_0x038a
            goto L_0x038d
        L_0x038a:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x038d:
            r9.setPointerEvents(r8, r4)
            goto L_0x042e
        L_0x0392:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            if (r10 != 0) goto L_0x0399
            goto L_0x039f
        L_0x0399:
            java.lang.Double r10 = (java.lang.Double) r10
            float r1 = r10.floatValue()
        L_0x039f:
            r9.setStrokeOpacity(r8, r1)
            goto L_0x042e
        L_0x03a4:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            if (r10 != 0) goto L_0x03ab
            goto L_0x03b1
        L_0x03ab:
            java.lang.Double r10 = (java.lang.Double) r10
            int r0 = r10.intValue()
        L_0x03b1:
            r9.setFillRule(r8, r0)
            goto L_0x042e
        L_0x03b6:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFontWeight(r8, r0)
            goto L_0x042e
        L_0x03c4:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStroke(r8, r0)
            goto L_0x042e
        L_0x03d1:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            if (r10 != 0) goto L_0x03d8
            goto L_0x03db
        L_0x03d8:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x03db:
            r9.setMarkerMid(r8, r4)
            goto L_0x042e
        L_0x03df:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            if (r10 != 0) goto L_0x03e6
            goto L_0x03e9
        L_0x03e6:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x03e9:
            r9.setMarkerEnd(r8, r4)
            goto L_0x042e
        L_0x03ed:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            com.facebook.react.bridge.ReadableArray r10 = (com.facebook.react.bridge.ReadableArray) r10
            r9.setPropList(r8, r10)
            goto L_0x042e
        L_0x03f7:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            com.facebook.react.bridge.ReadableArray r10 = (com.facebook.react.bridge.ReadableArray) r10
            r9.setMatrix(r8, r10)
            goto L_0x042e
        L_0x0401:
            U r9 = r7.mViewManager
            if (r10 != 0) goto L_0x0406
            goto L_0x040c
        L_0x0406:
            java.lang.Double r10 = (java.lang.Double) r10
            float r1 = r10.floatValue()
        L_0x040c:
            r9.setOpacity(r8, r1)
            goto L_0x042e
        L_0x0410:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            if (r10 != 0) goto L_0x0417
            goto L_0x041a
        L_0x0417:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x041a:
            r9.setFilter(r8, r4)
            goto L_0x042e
        L_0x041e:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface) r9
            if (r10 != 0) goto L_0x0425
            goto L_0x042b
        L_0x0425:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x042b:
            r9.setVbHeight(r8, r2)
        L_0x042e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSVGSymbolManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
