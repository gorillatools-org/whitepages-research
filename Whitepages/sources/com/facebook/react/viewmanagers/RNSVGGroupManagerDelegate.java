package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSVGGroupManagerInterface;

public class RNSVGGroupManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSVGGroupManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGGroupManagerDelegate(U u) {
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
                case -1274492040: goto L_0x0194;
                case -1267206133: goto L_0x0189;
                case -1081239615: goto L_0x017e;
                case -993894751: goto L_0x0173;
                case -933864895: goto L_0x0168;
                case -933857362: goto L_0x015d;
                case -891980232: goto L_0x0152;
                case -734428249: goto L_0x0147;
                case -729118945: goto L_0x0139;
                case -416535885: goto L_0x012b;
                case -293492298: goto L_0x011d;
                case -53677816: goto L_0x010f;
                case -44578051: goto L_0x0101;
                case 3143043: goto L_0x00f3;
                case 3148879: goto L_0x00e5;
                case 3344108: goto L_0x00d7;
                case 3373707: goto L_0x00c9;
                case 78845486: goto L_0x00bb;
                case 94842723: goto L_0x00ad;
                case 104482996: goto L_0x009f;
                case 217109576: goto L_0x0091;
                case 365601008: goto L_0x0083;
                case 401643183: goto L_0x0075;
                case 917656469: goto L_0x0067;
                case 917735020: goto L_0x0059;
                case 1027575302: goto L_0x004b;
                case 1671764162: goto L_0x003d;
                case 1790285174: goto L_0x002f;
                case 1847674614: goto L_0x0021;
                case 1924065902: goto L_0x0013;
                default: goto L_0x0011;
            }
        L_0x0011:
            goto L_0x019e
        L_0x0013:
            java.lang.String r6 = "strokeWidth"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x001d
            goto L_0x019e
        L_0x001d:
            r5 = 29
            goto L_0x019e
        L_0x0021:
            java.lang.String r6 = "responsible"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x002b
            goto L_0x019e
        L_0x002b:
            r5 = 28
            goto L_0x019e
        L_0x002f:
            java.lang.String r6 = "strokeLinejoin"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0039
            goto L_0x019e
        L_0x0039:
            r5 = 27
            goto L_0x019e
        L_0x003d:
            java.lang.String r6 = "display"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0047
            goto L_0x019e
        L_0x0047:
            r5 = 26
            goto L_0x019e
        L_0x004b:
            java.lang.String r6 = "strokeLinecap"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0055
            goto L_0x019e
        L_0x0055:
            r5 = 25
            goto L_0x019e
        L_0x0059:
            java.lang.String r6 = "clipRule"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0063
            goto L_0x019e
        L_0x0063:
            r5 = 24
            goto L_0x019e
        L_0x0067:
            java.lang.String r6 = "clipPath"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0071
            goto L_0x019e
        L_0x0071:
            r5 = 23
            goto L_0x019e
        L_0x0075:
            java.lang.String r6 = "strokeDasharray"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x007f
            goto L_0x019e
        L_0x007f:
            r5 = 22
            goto L_0x019e
        L_0x0083:
            java.lang.String r6 = "fontSize"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x008d
            goto L_0x019e
        L_0x008d:
            r5 = 21
            goto L_0x019e
        L_0x0091:
            java.lang.String r6 = "markerStart"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x009b
            goto L_0x019e
        L_0x009b:
            r5 = 20
            goto L_0x019e
        L_0x009f:
            java.lang.String r6 = "vectorEffect"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00a9
            goto L_0x019e
        L_0x00a9:
            r5 = 19
            goto L_0x019e
        L_0x00ad:
            java.lang.String r6 = "color"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00b7
            goto L_0x019e
        L_0x00b7:
            r5 = 18
            goto L_0x019e
        L_0x00bb:
            java.lang.String r6 = "strokeMiterlimit"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00c5
            goto L_0x019e
        L_0x00c5:
            r5 = 17
            goto L_0x019e
        L_0x00c9:
            java.lang.String r6 = "name"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00d3
            goto L_0x019e
        L_0x00d3:
            r5 = 16
            goto L_0x019e
        L_0x00d7:
            java.lang.String r6 = "mask"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00e1
            goto L_0x019e
        L_0x00e1:
            r5 = 15
            goto L_0x019e
        L_0x00e5:
            java.lang.String r6 = "font"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00ef
            goto L_0x019e
        L_0x00ef:
            r5 = 14
            goto L_0x019e
        L_0x00f3:
            java.lang.String r6 = "fill"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00fd
            goto L_0x019e
        L_0x00fd:
            r5 = 13
            goto L_0x019e
        L_0x0101:
            java.lang.String r6 = "strokeDashoffset"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x010b
            goto L_0x019e
        L_0x010b:
            r5 = 12
            goto L_0x019e
        L_0x010f:
            java.lang.String r6 = "fillOpacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0119
            goto L_0x019e
        L_0x0119:
            r5 = 11
            goto L_0x019e
        L_0x011d:
            java.lang.String r6 = "pointerEvents"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0127
            goto L_0x019e
        L_0x0127:
            r5 = 10
            goto L_0x019e
        L_0x012b:
            java.lang.String r6 = "strokeOpacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0135
            goto L_0x019e
        L_0x0135:
            r5 = 9
            goto L_0x019e
        L_0x0139:
            java.lang.String r6 = "fillRule"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0143
            goto L_0x019e
        L_0x0143:
            r5 = 8
            goto L_0x019e
        L_0x0147:
            java.lang.String r6 = "fontWeight"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0150
            goto L_0x019e
        L_0x0150:
            r5 = 7
            goto L_0x019e
        L_0x0152:
            java.lang.String r6 = "stroke"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x015b
            goto L_0x019e
        L_0x015b:
            r5 = 6
            goto L_0x019e
        L_0x015d:
            java.lang.String r6 = "markerMid"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0166
            goto L_0x019e
        L_0x0166:
            r5 = 5
            goto L_0x019e
        L_0x0168:
            java.lang.String r6 = "markerEnd"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0171
            goto L_0x019e
        L_0x0171:
            r5 = 4
            goto L_0x019e
        L_0x0173:
            java.lang.String r6 = "propList"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x017c
            goto L_0x019e
        L_0x017c:
            r5 = 3
            goto L_0x019e
        L_0x017e:
            java.lang.String r6 = "matrix"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0187
            goto L_0x019e
        L_0x0187:
            r5 = 2
            goto L_0x019e
        L_0x0189:
            java.lang.String r6 = "opacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0192
            goto L_0x019e
        L_0x0192:
            r5 = r0
            goto L_0x019e
        L_0x0194:
            java.lang.String r6 = "filter"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x019d
            goto L_0x019e
        L_0x019d:
            r5 = r3
        L_0x019e:
            switch(r5) {
                case 0: goto L_0x0364;
                case 1: goto L_0x0355;
                case 2: goto L_0x034b;
                case 3: goto L_0x0341;
                case 4: goto L_0x0333;
                case 5: goto L_0x0325;
                case 6: goto L_0x0318;
                case 7: goto L_0x030b;
                case 8: goto L_0x02f9;
                case 9: goto L_0x02e7;
                case 10: goto L_0x02d8;
                case 11: goto L_0x02c6;
                case 12: goto L_0x02b4;
                case 13: goto L_0x02a6;
                case 14: goto L_0x0298;
                case 15: goto L_0x0289;
                case 16: goto L_0x027a;
                case 17: goto L_0x0268;
                case 18: goto L_0x0257;
                case 19: goto L_0x0245;
                case 20: goto L_0x0236;
                case 21: goto L_0x0228;
                case 22: goto L_0x021a;
                case 23: goto L_0x020b;
                case 24: goto L_0x01f9;
                case 25: goto L_0x01e7;
                case 26: goto L_0x01d8;
                case 27: goto L_0x01c6;
                case 28: goto L_0x01b4;
                case 29: goto L_0x01a6;
                default: goto L_0x01a1;
            }
        L_0x01a1:
            super.setProperty(r8, r9, r10)
            goto L_0x0371
        L_0x01a6:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGGroupManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGGroupManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStrokeWidth(r8, r0)
            goto L_0x0371
        L_0x01b4:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGGroupManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGGroupManagerInterface) r9
            if (r10 != 0) goto L_0x01bb
            goto L_0x01c1
        L_0x01bb:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r3 = r10.booleanValue()
        L_0x01c1:
            r9.setResponsible(r8, r3)
            goto L_0x0371
        L_0x01c6:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGGroupManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGGroupManagerInterface) r9
            if (r10 != 0) goto L_0x01cd
            goto L_0x01d3
        L_0x01cd:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x01d3:
            r9.setStrokeLinejoin(r8, r3)
            goto L_0x0371
        L_0x01d8:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGGroupManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGGroupManagerInterface) r9
            if (r10 != 0) goto L_0x01df
            goto L_0x01e2
        L_0x01df:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x01e2:
            r9.setDisplay(r8, r4)
            goto L_0x0371
        L_0x01e7:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGGroupManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGGroupManagerInterface) r9
            if (r10 != 0) goto L_0x01ee
            goto L_0x01f4
        L_0x01ee:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x01f4:
            r9.setStrokeLinecap(r8, r3)
            goto L_0x0371
        L_0x01f9:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGGroupManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGGroupManagerInterface) r9
            if (r10 != 0) goto L_0x0200
            goto L_0x0206
        L_0x0200:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x0206:
            r9.setClipRule(r8, r3)
            goto L_0x0371
        L_0x020b:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGGroupManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGGroupManagerInterface) r9
            if (r10 != 0) goto L_0x0212
            goto L_0x0215
        L_0x0212:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0215:
            r9.setClipPath(r8, r4)
            goto L_0x0371
        L_0x021a:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGGroupManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGGroupManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStrokeDasharray(r8, r0)
            goto L_0x0371
        L_0x0228:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGGroupManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGGroupManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFontSize(r8, r0)
            goto L_0x0371
        L_0x0236:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGGroupManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGGroupManagerInterface) r9
            if (r10 != 0) goto L_0x023d
            goto L_0x0240
        L_0x023d:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0240:
            r9.setMarkerStart(r8, r4)
            goto L_0x0371
        L_0x0245:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGGroupManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGGroupManagerInterface) r9
            if (r10 != 0) goto L_0x024c
            goto L_0x0252
        L_0x024c:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x0252:
            r9.setVectorEffect(r8, r3)
            goto L_0x0371
        L_0x0257:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGGroupManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGGroupManagerInterface) r9
            android.content.Context r0 = r8.getContext()
            java.lang.Integer r10 = com.facebook.react.bridge.ColorPropConverter.getColor(r10, r0)
            r9.setColor(r8, r10)
            goto L_0x0371
        L_0x0268:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGGroupManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGGroupManagerInterface) r9
            if (r10 != 0) goto L_0x026f
            goto L_0x0275
        L_0x026f:
            java.lang.Double r10 = (java.lang.Double) r10
            float r1 = r10.floatValue()
        L_0x0275:
            r9.setStrokeMiterlimit(r8, r1)
            goto L_0x0371
        L_0x027a:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGGroupManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGGroupManagerInterface) r9
            if (r10 != 0) goto L_0x0281
            goto L_0x0284
        L_0x0281:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0284:
            r9.setName(r8, r4)
            goto L_0x0371
        L_0x0289:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGGroupManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGGroupManagerInterface) r9
            if (r10 != 0) goto L_0x0290
            goto L_0x0293
        L_0x0290:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0293:
            r9.setMask(r8, r4)
            goto L_0x0371
        L_0x0298:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGGroupManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGGroupManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFont(r8, r0)
            goto L_0x0371
        L_0x02a6:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGGroupManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGGroupManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFill(r8, r0)
            goto L_0x0371
        L_0x02b4:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGGroupManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGGroupManagerInterface) r9
            if (r10 != 0) goto L_0x02bb
            goto L_0x02c1
        L_0x02bb:
            java.lang.Double r10 = (java.lang.Double) r10
            float r1 = r10.floatValue()
        L_0x02c1:
            r9.setStrokeDashoffset(r8, r1)
            goto L_0x0371
        L_0x02c6:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGGroupManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGGroupManagerInterface) r9
            if (r10 != 0) goto L_0x02cd
            goto L_0x02d3
        L_0x02cd:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x02d3:
            r9.setFillOpacity(r8, r2)
            goto L_0x0371
        L_0x02d8:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGGroupManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGGroupManagerInterface) r9
            if (r10 != 0) goto L_0x02df
            goto L_0x02e2
        L_0x02df:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x02e2:
            r9.setPointerEvents(r8, r4)
            goto L_0x0371
        L_0x02e7:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGGroupManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGGroupManagerInterface) r9
            if (r10 != 0) goto L_0x02ee
            goto L_0x02f4
        L_0x02ee:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x02f4:
            r9.setStrokeOpacity(r8, r2)
            goto L_0x0371
        L_0x02f9:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGGroupManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGGroupManagerInterface) r9
            if (r10 != 0) goto L_0x0300
            goto L_0x0306
        L_0x0300:
            java.lang.Double r10 = (java.lang.Double) r10
            int r0 = r10.intValue()
        L_0x0306:
            r9.setFillRule(r8, r0)
            goto L_0x0371
        L_0x030b:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGGroupManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGGroupManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFontWeight(r8, r0)
            goto L_0x0371
        L_0x0318:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGGroupManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGGroupManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStroke(r8, r0)
            goto L_0x0371
        L_0x0325:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGGroupManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGGroupManagerInterface) r9
            if (r10 != 0) goto L_0x032c
            goto L_0x032f
        L_0x032c:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x032f:
            r9.setMarkerMid(r8, r4)
            goto L_0x0371
        L_0x0333:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGGroupManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGGroupManagerInterface) r9
            if (r10 != 0) goto L_0x033a
            goto L_0x033d
        L_0x033a:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x033d:
            r9.setMarkerEnd(r8, r4)
            goto L_0x0371
        L_0x0341:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGGroupManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGGroupManagerInterface) r9
            com.facebook.react.bridge.ReadableArray r10 = (com.facebook.react.bridge.ReadableArray) r10
            r9.setPropList(r8, r10)
            goto L_0x0371
        L_0x034b:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGGroupManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGGroupManagerInterface) r9
            com.facebook.react.bridge.ReadableArray r10 = (com.facebook.react.bridge.ReadableArray) r10
            r9.setMatrix(r8, r10)
            goto L_0x0371
        L_0x0355:
            U r9 = r7.mViewManager
            if (r10 != 0) goto L_0x035a
            goto L_0x0360
        L_0x035a:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x0360:
            r9.setOpacity(r8, r2)
            goto L_0x0371
        L_0x0364:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGGroupManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGGroupManagerInterface) r9
            if (r10 != 0) goto L_0x036b
            goto L_0x036e
        L_0x036b:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x036e:
            r9.setFilter(r8, r4)
        L_0x0371:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSVGGroupManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
