package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface;

public class RNSVGForeignObjectManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSVGForeignObjectManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGForeignObjectManagerDelegate(U u) {
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
                case -1274492040: goto L_0x01cc;
                case -1267206133: goto L_0x01c1;
                case -1221029593: goto L_0x01b6;
                case -1081239615: goto L_0x01ab;
                case -993894751: goto L_0x01a0;
                case -933864895: goto L_0x0195;
                case -933857362: goto L_0x018a;
                case -891980232: goto L_0x017f;
                case -734428249: goto L_0x0171;
                case -729118945: goto L_0x0163;
                case -416535885: goto L_0x0155;
                case -293492298: goto L_0x0147;
                case -53677816: goto L_0x0139;
                case -44578051: goto L_0x012b;
                case 120: goto L_0x011d;
                case 121: goto L_0x010f;
                case 3143043: goto L_0x0101;
                case 3148879: goto L_0x00f3;
                case 3344108: goto L_0x00e5;
                case 3373707: goto L_0x00d7;
                case 78845486: goto L_0x00c9;
                case 94842723: goto L_0x00bb;
                case 104482996: goto L_0x00ad;
                case 113126854: goto L_0x009f;
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
            goto L_0x01d6
        L_0x0013:
            java.lang.String r6 = "strokeWidth"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x001d
            goto L_0x01d6
        L_0x001d:
            r5 = 33
            goto L_0x01d6
        L_0x0021:
            java.lang.String r6 = "responsible"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x002b
            goto L_0x01d6
        L_0x002b:
            r5 = 32
            goto L_0x01d6
        L_0x002f:
            java.lang.String r6 = "strokeLinejoin"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0039
            goto L_0x01d6
        L_0x0039:
            r5 = 31
            goto L_0x01d6
        L_0x003d:
            java.lang.String r6 = "display"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0047
            goto L_0x01d6
        L_0x0047:
            r5 = 30
            goto L_0x01d6
        L_0x004b:
            java.lang.String r6 = "strokeLinecap"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0055
            goto L_0x01d6
        L_0x0055:
            r5 = 29
            goto L_0x01d6
        L_0x0059:
            java.lang.String r6 = "clipRule"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0063
            goto L_0x01d6
        L_0x0063:
            r5 = 28
            goto L_0x01d6
        L_0x0067:
            java.lang.String r6 = "clipPath"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0071
            goto L_0x01d6
        L_0x0071:
            r5 = 27
            goto L_0x01d6
        L_0x0075:
            java.lang.String r6 = "strokeDasharray"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x007f
            goto L_0x01d6
        L_0x007f:
            r5 = 26
            goto L_0x01d6
        L_0x0083:
            java.lang.String r6 = "fontSize"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x008d
            goto L_0x01d6
        L_0x008d:
            r5 = 25
            goto L_0x01d6
        L_0x0091:
            java.lang.String r6 = "markerStart"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x009b
            goto L_0x01d6
        L_0x009b:
            r5 = 24
            goto L_0x01d6
        L_0x009f:
            java.lang.String r6 = "width"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00a9
            goto L_0x01d6
        L_0x00a9:
            r5 = 23
            goto L_0x01d6
        L_0x00ad:
            java.lang.String r6 = "vectorEffect"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00b7
            goto L_0x01d6
        L_0x00b7:
            r5 = 22
            goto L_0x01d6
        L_0x00bb:
            java.lang.String r6 = "color"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00c5
            goto L_0x01d6
        L_0x00c5:
            r5 = 21
            goto L_0x01d6
        L_0x00c9:
            java.lang.String r6 = "strokeMiterlimit"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00d3
            goto L_0x01d6
        L_0x00d3:
            r5 = 20
            goto L_0x01d6
        L_0x00d7:
            java.lang.String r6 = "name"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00e1
            goto L_0x01d6
        L_0x00e1:
            r5 = 19
            goto L_0x01d6
        L_0x00e5:
            java.lang.String r6 = "mask"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00ef
            goto L_0x01d6
        L_0x00ef:
            r5 = 18
            goto L_0x01d6
        L_0x00f3:
            java.lang.String r6 = "font"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00fd
            goto L_0x01d6
        L_0x00fd:
            r5 = 17
            goto L_0x01d6
        L_0x0101:
            java.lang.String r6 = "fill"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x010b
            goto L_0x01d6
        L_0x010b:
            r5 = 16
            goto L_0x01d6
        L_0x010f:
            java.lang.String r6 = "y"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0119
            goto L_0x01d6
        L_0x0119:
            r5 = 15
            goto L_0x01d6
        L_0x011d:
            java.lang.String r6 = "x"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0127
            goto L_0x01d6
        L_0x0127:
            r5 = 14
            goto L_0x01d6
        L_0x012b:
            java.lang.String r6 = "strokeDashoffset"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0135
            goto L_0x01d6
        L_0x0135:
            r5 = 13
            goto L_0x01d6
        L_0x0139:
            java.lang.String r6 = "fillOpacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0143
            goto L_0x01d6
        L_0x0143:
            r5 = 12
            goto L_0x01d6
        L_0x0147:
            java.lang.String r6 = "pointerEvents"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0151
            goto L_0x01d6
        L_0x0151:
            r5 = 11
            goto L_0x01d6
        L_0x0155:
            java.lang.String r6 = "strokeOpacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x015f
            goto L_0x01d6
        L_0x015f:
            r5 = 10
            goto L_0x01d6
        L_0x0163:
            java.lang.String r6 = "fillRule"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x016d
            goto L_0x01d6
        L_0x016d:
            r5 = 9
            goto L_0x01d6
        L_0x0171:
            java.lang.String r6 = "fontWeight"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x017b
            goto L_0x01d6
        L_0x017b:
            r5 = 8
            goto L_0x01d6
        L_0x017f:
            java.lang.String r6 = "stroke"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0188
            goto L_0x01d6
        L_0x0188:
            r5 = 7
            goto L_0x01d6
        L_0x018a:
            java.lang.String r6 = "markerMid"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0193
            goto L_0x01d6
        L_0x0193:
            r5 = 6
            goto L_0x01d6
        L_0x0195:
            java.lang.String r6 = "markerEnd"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x019e
            goto L_0x01d6
        L_0x019e:
            r5 = 5
            goto L_0x01d6
        L_0x01a0:
            java.lang.String r6 = "propList"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01a9
            goto L_0x01d6
        L_0x01a9:
            r5 = 4
            goto L_0x01d6
        L_0x01ab:
            java.lang.String r6 = "matrix"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01b4
            goto L_0x01d6
        L_0x01b4:
            r5 = 3
            goto L_0x01d6
        L_0x01b6:
            java.lang.String r6 = "height"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01bf
            goto L_0x01d6
        L_0x01bf:
            r5 = 2
            goto L_0x01d6
        L_0x01c1:
            java.lang.String r6 = "opacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01ca
            goto L_0x01d6
        L_0x01ca:
            r5 = r0
            goto L_0x01d6
        L_0x01cc:
            java.lang.String r6 = "filter"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01d5
            goto L_0x01d6
        L_0x01d5:
            r5 = r3
        L_0x01d6:
            switch(r5) {
                case 0: goto L_0x03d4;
                case 1: goto L_0x03c5;
                case 2: goto L_0x03b8;
                case 3: goto L_0x03ae;
                case 4: goto L_0x03a4;
                case 5: goto L_0x0396;
                case 6: goto L_0x0388;
                case 7: goto L_0x037b;
                case 8: goto L_0x036d;
                case 9: goto L_0x035b;
                case 10: goto L_0x0349;
                case 11: goto L_0x033a;
                case 12: goto L_0x0328;
                case 13: goto L_0x0316;
                case 14: goto L_0x0308;
                case 15: goto L_0x02fa;
                case 16: goto L_0x02ec;
                case 17: goto L_0x02de;
                case 18: goto L_0x02cf;
                case 19: goto L_0x02c0;
                case 20: goto L_0x02ae;
                case 21: goto L_0x029d;
                case 22: goto L_0x028b;
                case 23: goto L_0x027d;
                case 24: goto L_0x026e;
                case 25: goto L_0x0260;
                case 26: goto L_0x0252;
                case 27: goto L_0x0243;
                case 28: goto L_0x0231;
                case 29: goto L_0x021f;
                case 30: goto L_0x0210;
                case 31: goto L_0x01fe;
                case 32: goto L_0x01ec;
                case 33: goto L_0x01de;
                default: goto L_0x01d9;
            }
        L_0x01d9:
            super.setProperty(r8, r9, r10)
            goto L_0x03e1
        L_0x01de:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStrokeWidth(r8, r0)
            goto L_0x03e1
        L_0x01ec:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r9
            if (r10 != 0) goto L_0x01f3
            goto L_0x01f9
        L_0x01f3:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r3 = r10.booleanValue()
        L_0x01f9:
            r9.setResponsible(r8, r3)
            goto L_0x03e1
        L_0x01fe:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r9
            if (r10 != 0) goto L_0x0205
            goto L_0x020b
        L_0x0205:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x020b:
            r9.setStrokeLinejoin(r8, r3)
            goto L_0x03e1
        L_0x0210:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r9
            if (r10 != 0) goto L_0x0217
            goto L_0x021a
        L_0x0217:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x021a:
            r9.setDisplay(r8, r4)
            goto L_0x03e1
        L_0x021f:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r9
            if (r10 != 0) goto L_0x0226
            goto L_0x022c
        L_0x0226:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x022c:
            r9.setStrokeLinecap(r8, r3)
            goto L_0x03e1
        L_0x0231:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r9
            if (r10 != 0) goto L_0x0238
            goto L_0x023e
        L_0x0238:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x023e:
            r9.setClipRule(r8, r3)
            goto L_0x03e1
        L_0x0243:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r9
            if (r10 != 0) goto L_0x024a
            goto L_0x024d
        L_0x024a:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x024d:
            r9.setClipPath(r8, r4)
            goto L_0x03e1
        L_0x0252:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStrokeDasharray(r8, r0)
            goto L_0x03e1
        L_0x0260:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFontSize(r8, r0)
            goto L_0x03e1
        L_0x026e:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r9
            if (r10 != 0) goto L_0x0275
            goto L_0x0278
        L_0x0275:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0278:
            r9.setMarkerStart(r8, r4)
            goto L_0x03e1
        L_0x027d:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setWidth(r8, r0)
            goto L_0x03e1
        L_0x028b:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r9
            if (r10 != 0) goto L_0x0292
            goto L_0x0298
        L_0x0292:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x0298:
            r9.setVectorEffect(r8, r3)
            goto L_0x03e1
        L_0x029d:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r9
            android.content.Context r0 = r8.getContext()
            java.lang.Integer r10 = com.facebook.react.bridge.ColorPropConverter.getColor(r10, r0)
            r9.setColor(r8, r10)
            goto L_0x03e1
        L_0x02ae:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r9
            if (r10 != 0) goto L_0x02b5
            goto L_0x02bb
        L_0x02b5:
            java.lang.Double r10 = (java.lang.Double) r10
            float r1 = r10.floatValue()
        L_0x02bb:
            r9.setStrokeMiterlimit(r8, r1)
            goto L_0x03e1
        L_0x02c0:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r9
            if (r10 != 0) goto L_0x02c7
            goto L_0x02ca
        L_0x02c7:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x02ca:
            r9.setName(r8, r4)
            goto L_0x03e1
        L_0x02cf:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r9
            if (r10 != 0) goto L_0x02d6
            goto L_0x02d9
        L_0x02d6:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x02d9:
            r9.setMask(r8, r4)
            goto L_0x03e1
        L_0x02de:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFont(r8, r0)
            goto L_0x03e1
        L_0x02ec:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFill(r8, r0)
            goto L_0x03e1
        L_0x02fa:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setY(r8, r0)
            goto L_0x03e1
        L_0x0308:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setX(r8, r0)
            goto L_0x03e1
        L_0x0316:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r9
            if (r10 != 0) goto L_0x031d
            goto L_0x0323
        L_0x031d:
            java.lang.Double r10 = (java.lang.Double) r10
            float r1 = r10.floatValue()
        L_0x0323:
            r9.setStrokeDashoffset(r8, r1)
            goto L_0x03e1
        L_0x0328:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r9
            if (r10 != 0) goto L_0x032f
            goto L_0x0335
        L_0x032f:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x0335:
            r9.setFillOpacity(r8, r2)
            goto L_0x03e1
        L_0x033a:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r9
            if (r10 != 0) goto L_0x0341
            goto L_0x0344
        L_0x0341:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0344:
            r9.setPointerEvents(r8, r4)
            goto L_0x03e1
        L_0x0349:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r9
            if (r10 != 0) goto L_0x0350
            goto L_0x0356
        L_0x0350:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x0356:
            r9.setStrokeOpacity(r8, r2)
            goto L_0x03e1
        L_0x035b:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r9
            if (r10 != 0) goto L_0x0362
            goto L_0x0368
        L_0x0362:
            java.lang.Double r10 = (java.lang.Double) r10
            int r0 = r10.intValue()
        L_0x0368:
            r9.setFillRule(r8, r0)
            goto L_0x03e1
        L_0x036d:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFontWeight(r8, r0)
            goto L_0x03e1
        L_0x037b:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStroke(r8, r0)
            goto L_0x03e1
        L_0x0388:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r9
            if (r10 != 0) goto L_0x038f
            goto L_0x0392
        L_0x038f:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0392:
            r9.setMarkerMid(r8, r4)
            goto L_0x03e1
        L_0x0396:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r9
            if (r10 != 0) goto L_0x039d
            goto L_0x03a0
        L_0x039d:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x03a0:
            r9.setMarkerEnd(r8, r4)
            goto L_0x03e1
        L_0x03a4:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r9
            com.facebook.react.bridge.ReadableArray r10 = (com.facebook.react.bridge.ReadableArray) r10
            r9.setPropList(r8, r10)
            goto L_0x03e1
        L_0x03ae:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r9
            com.facebook.react.bridge.ReadableArray r10 = (com.facebook.react.bridge.ReadableArray) r10
            r9.setMatrix(r8, r10)
            goto L_0x03e1
        L_0x03b8:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setHeight(r8, r0)
            goto L_0x03e1
        L_0x03c5:
            U r9 = r7.mViewManager
            if (r10 != 0) goto L_0x03ca
            goto L_0x03d0
        L_0x03ca:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x03d0:
            r9.setOpacity(r8, r2)
            goto L_0x03e1
        L_0x03d4:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface) r9
            if (r10 != 0) goto L_0x03db
            goto L_0x03de
        L_0x03db:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x03de:
            r9.setFilter(r8, r4)
        L_0x03e1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSVGForeignObjectManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
