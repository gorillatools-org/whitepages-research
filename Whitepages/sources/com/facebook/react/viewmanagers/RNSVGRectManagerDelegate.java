package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSVGRectManagerInterface;

public class RNSVGRectManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSVGRectManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGRectManagerDelegate(U u) {
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
                case -1274492040: goto L_0x01be;
                case -1267206133: goto L_0x01b3;
                case -1221029593: goto L_0x01a8;
                case -1081239615: goto L_0x019d;
                case -993894751: goto L_0x0192;
                case -933864895: goto L_0x0187;
                case -933857362: goto L_0x017c;
                case -891980232: goto L_0x0171;
                case -729118945: goto L_0x0163;
                case -416535885: goto L_0x0155;
                case -293492298: goto L_0x0147;
                case -53677816: goto L_0x0139;
                case -44578051: goto L_0x012b;
                case 120: goto L_0x011d;
                case 121: goto L_0x010f;
                case 3654: goto L_0x0101;
                case 3655: goto L_0x00f3;
                case 3143043: goto L_0x00e5;
                case 3344108: goto L_0x00d7;
                case 3373707: goto L_0x00c9;
                case 78845486: goto L_0x00bb;
                case 94842723: goto L_0x00ad;
                case 104482996: goto L_0x009f;
                case 113126854: goto L_0x0091;
                case 217109576: goto L_0x0083;
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
            goto L_0x01c8
        L_0x0013:
            java.lang.String r6 = "strokeWidth"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x001d
            goto L_0x01c8
        L_0x001d:
            r5 = 32
            goto L_0x01c8
        L_0x0021:
            java.lang.String r6 = "responsible"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x002b
            goto L_0x01c8
        L_0x002b:
            r5 = 31
            goto L_0x01c8
        L_0x002f:
            java.lang.String r6 = "strokeLinejoin"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0039
            goto L_0x01c8
        L_0x0039:
            r5 = 30
            goto L_0x01c8
        L_0x003d:
            java.lang.String r6 = "display"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0047
            goto L_0x01c8
        L_0x0047:
            r5 = 29
            goto L_0x01c8
        L_0x004b:
            java.lang.String r6 = "strokeLinecap"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0055
            goto L_0x01c8
        L_0x0055:
            r5 = 28
            goto L_0x01c8
        L_0x0059:
            java.lang.String r6 = "clipRule"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0063
            goto L_0x01c8
        L_0x0063:
            r5 = 27
            goto L_0x01c8
        L_0x0067:
            java.lang.String r6 = "clipPath"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0071
            goto L_0x01c8
        L_0x0071:
            r5 = 26
            goto L_0x01c8
        L_0x0075:
            java.lang.String r6 = "strokeDasharray"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x007f
            goto L_0x01c8
        L_0x007f:
            r5 = 25
            goto L_0x01c8
        L_0x0083:
            java.lang.String r6 = "markerStart"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x008d
            goto L_0x01c8
        L_0x008d:
            r5 = 24
            goto L_0x01c8
        L_0x0091:
            java.lang.String r6 = "width"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x009b
            goto L_0x01c8
        L_0x009b:
            r5 = 23
            goto L_0x01c8
        L_0x009f:
            java.lang.String r6 = "vectorEffect"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00a9
            goto L_0x01c8
        L_0x00a9:
            r5 = 22
            goto L_0x01c8
        L_0x00ad:
            java.lang.String r6 = "color"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00b7
            goto L_0x01c8
        L_0x00b7:
            r5 = 21
            goto L_0x01c8
        L_0x00bb:
            java.lang.String r6 = "strokeMiterlimit"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00c5
            goto L_0x01c8
        L_0x00c5:
            r5 = 20
            goto L_0x01c8
        L_0x00c9:
            java.lang.String r6 = "name"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00d3
            goto L_0x01c8
        L_0x00d3:
            r5 = 19
            goto L_0x01c8
        L_0x00d7:
            java.lang.String r6 = "mask"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00e1
            goto L_0x01c8
        L_0x00e1:
            r5 = 18
            goto L_0x01c8
        L_0x00e5:
            java.lang.String r6 = "fill"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00ef
            goto L_0x01c8
        L_0x00ef:
            r5 = 17
            goto L_0x01c8
        L_0x00f3:
            java.lang.String r6 = "ry"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00fd
            goto L_0x01c8
        L_0x00fd:
            r5 = 16
            goto L_0x01c8
        L_0x0101:
            java.lang.String r6 = "rx"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x010b
            goto L_0x01c8
        L_0x010b:
            r5 = 15
            goto L_0x01c8
        L_0x010f:
            java.lang.String r6 = "y"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0119
            goto L_0x01c8
        L_0x0119:
            r5 = 14
            goto L_0x01c8
        L_0x011d:
            java.lang.String r6 = "x"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0127
            goto L_0x01c8
        L_0x0127:
            r5 = 13
            goto L_0x01c8
        L_0x012b:
            java.lang.String r6 = "strokeDashoffset"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0135
            goto L_0x01c8
        L_0x0135:
            r5 = 12
            goto L_0x01c8
        L_0x0139:
            java.lang.String r6 = "fillOpacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0143
            goto L_0x01c8
        L_0x0143:
            r5 = 11
            goto L_0x01c8
        L_0x0147:
            java.lang.String r6 = "pointerEvents"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0151
            goto L_0x01c8
        L_0x0151:
            r5 = 10
            goto L_0x01c8
        L_0x0155:
            java.lang.String r6 = "strokeOpacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x015f
            goto L_0x01c8
        L_0x015f:
            r5 = 9
            goto L_0x01c8
        L_0x0163:
            java.lang.String r6 = "fillRule"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x016d
            goto L_0x01c8
        L_0x016d:
            r5 = 8
            goto L_0x01c8
        L_0x0171:
            java.lang.String r6 = "stroke"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x017a
            goto L_0x01c8
        L_0x017a:
            r5 = 7
            goto L_0x01c8
        L_0x017c:
            java.lang.String r6 = "markerMid"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0185
            goto L_0x01c8
        L_0x0185:
            r5 = 6
            goto L_0x01c8
        L_0x0187:
            java.lang.String r6 = "markerEnd"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0190
            goto L_0x01c8
        L_0x0190:
            r5 = 5
            goto L_0x01c8
        L_0x0192:
            java.lang.String r6 = "propList"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x019b
            goto L_0x01c8
        L_0x019b:
            r5 = 4
            goto L_0x01c8
        L_0x019d:
            java.lang.String r6 = "matrix"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01a6
            goto L_0x01c8
        L_0x01a6:
            r5 = 3
            goto L_0x01c8
        L_0x01a8:
            java.lang.String r6 = "height"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01b1
            goto L_0x01c8
        L_0x01b1:
            r5 = 2
            goto L_0x01c8
        L_0x01b3:
            java.lang.String r6 = "opacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01bc
            goto L_0x01c8
        L_0x01bc:
            r5 = r0
            goto L_0x01c8
        L_0x01be:
            java.lang.String r6 = "filter"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01c7
            goto L_0x01c8
        L_0x01c7:
            r5 = r3
        L_0x01c8:
            switch(r5) {
                case 0: goto L_0x03b8;
                case 1: goto L_0x03a9;
                case 2: goto L_0x039c;
                case 3: goto L_0x0392;
                case 4: goto L_0x0388;
                case 5: goto L_0x037a;
                case 6: goto L_0x036c;
                case 7: goto L_0x035f;
                case 8: goto L_0x034d;
                case 9: goto L_0x033b;
                case 10: goto L_0x032c;
                case 11: goto L_0x031a;
                case 12: goto L_0x0308;
                case 13: goto L_0x02fa;
                case 14: goto L_0x02ec;
                case 15: goto L_0x02de;
                case 16: goto L_0x02d0;
                case 17: goto L_0x02c2;
                case 18: goto L_0x02b3;
                case 19: goto L_0x02a4;
                case 20: goto L_0x0292;
                case 21: goto L_0x0281;
                case 22: goto L_0x026f;
                case 23: goto L_0x0261;
                case 24: goto L_0x0252;
                case 25: goto L_0x0244;
                case 26: goto L_0x0235;
                case 27: goto L_0x0223;
                case 28: goto L_0x0211;
                case 29: goto L_0x0202;
                case 30: goto L_0x01f0;
                case 31: goto L_0x01de;
                case 32: goto L_0x01d0;
                default: goto L_0x01cb;
            }
        L_0x01cb:
            super.setProperty(r8, r9, r10)
            goto L_0x03c5
        L_0x01d0:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStrokeWidth(r8, r0)
            goto L_0x03c5
        L_0x01de:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r9
            if (r10 != 0) goto L_0x01e5
            goto L_0x01eb
        L_0x01e5:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r3 = r10.booleanValue()
        L_0x01eb:
            r9.setResponsible(r8, r3)
            goto L_0x03c5
        L_0x01f0:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r9
            if (r10 != 0) goto L_0x01f7
            goto L_0x01fd
        L_0x01f7:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x01fd:
            r9.setStrokeLinejoin(r8, r3)
            goto L_0x03c5
        L_0x0202:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r9
            if (r10 != 0) goto L_0x0209
            goto L_0x020c
        L_0x0209:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x020c:
            r9.setDisplay(r8, r4)
            goto L_0x03c5
        L_0x0211:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r9
            if (r10 != 0) goto L_0x0218
            goto L_0x021e
        L_0x0218:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x021e:
            r9.setStrokeLinecap(r8, r3)
            goto L_0x03c5
        L_0x0223:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r9
            if (r10 != 0) goto L_0x022a
            goto L_0x0230
        L_0x022a:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x0230:
            r9.setClipRule(r8, r3)
            goto L_0x03c5
        L_0x0235:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r9
            if (r10 != 0) goto L_0x023c
            goto L_0x023f
        L_0x023c:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x023f:
            r9.setClipPath(r8, r4)
            goto L_0x03c5
        L_0x0244:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStrokeDasharray(r8, r0)
            goto L_0x03c5
        L_0x0252:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r9
            if (r10 != 0) goto L_0x0259
            goto L_0x025c
        L_0x0259:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x025c:
            r9.setMarkerStart(r8, r4)
            goto L_0x03c5
        L_0x0261:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setWidth(r8, r0)
            goto L_0x03c5
        L_0x026f:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r9
            if (r10 != 0) goto L_0x0276
            goto L_0x027c
        L_0x0276:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x027c:
            r9.setVectorEffect(r8, r3)
            goto L_0x03c5
        L_0x0281:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r9
            android.content.Context r0 = r8.getContext()
            java.lang.Integer r10 = com.facebook.react.bridge.ColorPropConverter.getColor(r10, r0)
            r9.setColor(r8, r10)
            goto L_0x03c5
        L_0x0292:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r9
            if (r10 != 0) goto L_0x0299
            goto L_0x029f
        L_0x0299:
            java.lang.Double r10 = (java.lang.Double) r10
            float r1 = r10.floatValue()
        L_0x029f:
            r9.setStrokeMiterlimit(r8, r1)
            goto L_0x03c5
        L_0x02a4:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r9
            if (r10 != 0) goto L_0x02ab
            goto L_0x02ae
        L_0x02ab:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x02ae:
            r9.setName(r8, r4)
            goto L_0x03c5
        L_0x02b3:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r9
            if (r10 != 0) goto L_0x02ba
            goto L_0x02bd
        L_0x02ba:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x02bd:
            r9.setMask(r8, r4)
            goto L_0x03c5
        L_0x02c2:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFill(r8, r0)
            goto L_0x03c5
        L_0x02d0:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setRy(r8, r0)
            goto L_0x03c5
        L_0x02de:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setRx(r8, r0)
            goto L_0x03c5
        L_0x02ec:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setY(r8, r0)
            goto L_0x03c5
        L_0x02fa:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setX(r8, r0)
            goto L_0x03c5
        L_0x0308:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r9
            if (r10 != 0) goto L_0x030f
            goto L_0x0315
        L_0x030f:
            java.lang.Double r10 = (java.lang.Double) r10
            float r1 = r10.floatValue()
        L_0x0315:
            r9.setStrokeDashoffset(r8, r1)
            goto L_0x03c5
        L_0x031a:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r9
            if (r10 != 0) goto L_0x0321
            goto L_0x0327
        L_0x0321:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x0327:
            r9.setFillOpacity(r8, r2)
            goto L_0x03c5
        L_0x032c:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r9
            if (r10 != 0) goto L_0x0333
            goto L_0x0336
        L_0x0333:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0336:
            r9.setPointerEvents(r8, r4)
            goto L_0x03c5
        L_0x033b:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r9
            if (r10 != 0) goto L_0x0342
            goto L_0x0348
        L_0x0342:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x0348:
            r9.setStrokeOpacity(r8, r2)
            goto L_0x03c5
        L_0x034d:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r9
            if (r10 != 0) goto L_0x0354
            goto L_0x035a
        L_0x0354:
            java.lang.Double r10 = (java.lang.Double) r10
            int r0 = r10.intValue()
        L_0x035a:
            r9.setFillRule(r8, r0)
            goto L_0x03c5
        L_0x035f:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStroke(r8, r0)
            goto L_0x03c5
        L_0x036c:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r9
            if (r10 != 0) goto L_0x0373
            goto L_0x0376
        L_0x0373:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0376:
            r9.setMarkerMid(r8, r4)
            goto L_0x03c5
        L_0x037a:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r9
            if (r10 != 0) goto L_0x0381
            goto L_0x0384
        L_0x0381:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0384:
            r9.setMarkerEnd(r8, r4)
            goto L_0x03c5
        L_0x0388:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r9
            com.facebook.react.bridge.ReadableArray r10 = (com.facebook.react.bridge.ReadableArray) r10
            r9.setPropList(r8, r10)
            goto L_0x03c5
        L_0x0392:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r9
            com.facebook.react.bridge.ReadableArray r10 = (com.facebook.react.bridge.ReadableArray) r10
            r9.setMatrix(r8, r10)
            goto L_0x03c5
        L_0x039c:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setHeight(r8, r0)
            goto L_0x03c5
        L_0x03a9:
            U r9 = r7.mViewManager
            if (r10 != 0) goto L_0x03ae
            goto L_0x03b4
        L_0x03ae:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x03b4:
            r9.setOpacity(r8, r2)
            goto L_0x03c5
        L_0x03b8:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGRectManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGRectManagerInterface) r9
            if (r10 != 0) goto L_0x03bf
            goto L_0x03c2
        L_0x03bf:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x03c2:
            r9.setFilter(r8, r4)
        L_0x03c5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSVGRectManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
