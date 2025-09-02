package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface;

public class RNSVGEllipseManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSVGEllipseManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGEllipseManagerDelegate(U u) {
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
                case -1274492040: goto L_0x01a2;
                case -1267206133: goto L_0x0197;
                case -1081239615: goto L_0x018c;
                case -993894751: goto L_0x0181;
                case -933864895: goto L_0x0176;
                case -933857362: goto L_0x016b;
                case -891980232: goto L_0x0160;
                case -729118945: goto L_0x0155;
                case -416535885: goto L_0x0147;
                case -293492298: goto L_0x0139;
                case -53677816: goto L_0x012b;
                case -44578051: goto L_0x011d;
                case 3189: goto L_0x010f;
                case 3190: goto L_0x0101;
                case 3654: goto L_0x00f3;
                case 3655: goto L_0x00e5;
                case 3143043: goto L_0x00d7;
                case 3344108: goto L_0x00c9;
                case 3373707: goto L_0x00bb;
                case 78845486: goto L_0x00ad;
                case 94842723: goto L_0x009f;
                case 104482996: goto L_0x0091;
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
            goto L_0x01ac
        L_0x0013:
            java.lang.String r6 = "strokeWidth"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x001d
            goto L_0x01ac
        L_0x001d:
            r5 = 30
            goto L_0x01ac
        L_0x0021:
            java.lang.String r6 = "responsible"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x002b
            goto L_0x01ac
        L_0x002b:
            r5 = 29
            goto L_0x01ac
        L_0x002f:
            java.lang.String r6 = "strokeLinejoin"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0039
            goto L_0x01ac
        L_0x0039:
            r5 = 28
            goto L_0x01ac
        L_0x003d:
            java.lang.String r6 = "display"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0047
            goto L_0x01ac
        L_0x0047:
            r5 = 27
            goto L_0x01ac
        L_0x004b:
            java.lang.String r6 = "strokeLinecap"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0055
            goto L_0x01ac
        L_0x0055:
            r5 = 26
            goto L_0x01ac
        L_0x0059:
            java.lang.String r6 = "clipRule"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0063
            goto L_0x01ac
        L_0x0063:
            r5 = 25
            goto L_0x01ac
        L_0x0067:
            java.lang.String r6 = "clipPath"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0071
            goto L_0x01ac
        L_0x0071:
            r5 = 24
            goto L_0x01ac
        L_0x0075:
            java.lang.String r6 = "strokeDasharray"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x007f
            goto L_0x01ac
        L_0x007f:
            r5 = 23
            goto L_0x01ac
        L_0x0083:
            java.lang.String r6 = "markerStart"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x008d
            goto L_0x01ac
        L_0x008d:
            r5 = 22
            goto L_0x01ac
        L_0x0091:
            java.lang.String r6 = "vectorEffect"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x009b
            goto L_0x01ac
        L_0x009b:
            r5 = 21
            goto L_0x01ac
        L_0x009f:
            java.lang.String r6 = "color"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00a9
            goto L_0x01ac
        L_0x00a9:
            r5 = 20
            goto L_0x01ac
        L_0x00ad:
            java.lang.String r6 = "strokeMiterlimit"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00b7
            goto L_0x01ac
        L_0x00b7:
            r5 = 19
            goto L_0x01ac
        L_0x00bb:
            java.lang.String r6 = "name"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00c5
            goto L_0x01ac
        L_0x00c5:
            r5 = 18
            goto L_0x01ac
        L_0x00c9:
            java.lang.String r6 = "mask"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00d3
            goto L_0x01ac
        L_0x00d3:
            r5 = 17
            goto L_0x01ac
        L_0x00d7:
            java.lang.String r6 = "fill"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00e1
            goto L_0x01ac
        L_0x00e1:
            r5 = 16
            goto L_0x01ac
        L_0x00e5:
            java.lang.String r6 = "ry"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00ef
            goto L_0x01ac
        L_0x00ef:
            r5 = 15
            goto L_0x01ac
        L_0x00f3:
            java.lang.String r6 = "rx"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00fd
            goto L_0x01ac
        L_0x00fd:
            r5 = 14
            goto L_0x01ac
        L_0x0101:
            java.lang.String r6 = "cy"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x010b
            goto L_0x01ac
        L_0x010b:
            r5 = 13
            goto L_0x01ac
        L_0x010f:
            java.lang.String r6 = "cx"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0119
            goto L_0x01ac
        L_0x0119:
            r5 = 12
            goto L_0x01ac
        L_0x011d:
            java.lang.String r6 = "strokeDashoffset"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0127
            goto L_0x01ac
        L_0x0127:
            r5 = 11
            goto L_0x01ac
        L_0x012b:
            java.lang.String r6 = "fillOpacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0135
            goto L_0x01ac
        L_0x0135:
            r5 = 10
            goto L_0x01ac
        L_0x0139:
            java.lang.String r6 = "pointerEvents"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0143
            goto L_0x01ac
        L_0x0143:
            r5 = 9
            goto L_0x01ac
        L_0x0147:
            java.lang.String r6 = "strokeOpacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0151
            goto L_0x01ac
        L_0x0151:
            r5 = 8
            goto L_0x01ac
        L_0x0155:
            java.lang.String r6 = "fillRule"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x015e
            goto L_0x01ac
        L_0x015e:
            r5 = 7
            goto L_0x01ac
        L_0x0160:
            java.lang.String r6 = "stroke"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0169
            goto L_0x01ac
        L_0x0169:
            r5 = 6
            goto L_0x01ac
        L_0x016b:
            java.lang.String r6 = "markerMid"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0174
            goto L_0x01ac
        L_0x0174:
            r5 = 5
            goto L_0x01ac
        L_0x0176:
            java.lang.String r6 = "markerEnd"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x017f
            goto L_0x01ac
        L_0x017f:
            r5 = 4
            goto L_0x01ac
        L_0x0181:
            java.lang.String r6 = "propList"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x018a
            goto L_0x01ac
        L_0x018a:
            r5 = 3
            goto L_0x01ac
        L_0x018c:
            java.lang.String r6 = "matrix"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0195
            goto L_0x01ac
        L_0x0195:
            r5 = 2
            goto L_0x01ac
        L_0x0197:
            java.lang.String r6 = "opacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01a0
            goto L_0x01ac
        L_0x01a0:
            r5 = r0
            goto L_0x01ac
        L_0x01a2:
            java.lang.String r6 = "filter"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01ab
            goto L_0x01ac
        L_0x01ab:
            r5 = r3
        L_0x01ac:
            switch(r5) {
                case 0: goto L_0x0380;
                case 1: goto L_0x0371;
                case 2: goto L_0x0367;
                case 3: goto L_0x035d;
                case 4: goto L_0x034f;
                case 5: goto L_0x0341;
                case 6: goto L_0x0334;
                case 7: goto L_0x0323;
                case 8: goto L_0x0311;
                case 9: goto L_0x0302;
                case 10: goto L_0x02f0;
                case 11: goto L_0x02de;
                case 12: goto L_0x02d0;
                case 13: goto L_0x02c2;
                case 14: goto L_0x02b4;
                case 15: goto L_0x02a6;
                case 16: goto L_0x0298;
                case 17: goto L_0x0289;
                case 18: goto L_0x027a;
                case 19: goto L_0x0268;
                case 20: goto L_0x0257;
                case 21: goto L_0x0245;
                case 22: goto L_0x0236;
                case 23: goto L_0x0228;
                case 24: goto L_0x0219;
                case 25: goto L_0x0207;
                case 26: goto L_0x01f5;
                case 27: goto L_0x01e6;
                case 28: goto L_0x01d4;
                case 29: goto L_0x01c2;
                case 30: goto L_0x01b4;
                default: goto L_0x01af;
            }
        L_0x01af:
            super.setProperty(r8, r9, r10)
            goto L_0x038d
        L_0x01b4:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStrokeWidth(r8, r0)
            goto L_0x038d
        L_0x01c2:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r9
            if (r10 != 0) goto L_0x01c9
            goto L_0x01cf
        L_0x01c9:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r3 = r10.booleanValue()
        L_0x01cf:
            r9.setResponsible(r8, r3)
            goto L_0x038d
        L_0x01d4:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r9
            if (r10 != 0) goto L_0x01db
            goto L_0x01e1
        L_0x01db:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x01e1:
            r9.setStrokeLinejoin(r8, r3)
            goto L_0x038d
        L_0x01e6:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r9
            if (r10 != 0) goto L_0x01ed
            goto L_0x01f0
        L_0x01ed:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x01f0:
            r9.setDisplay(r8, r4)
            goto L_0x038d
        L_0x01f5:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r9
            if (r10 != 0) goto L_0x01fc
            goto L_0x0202
        L_0x01fc:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x0202:
            r9.setStrokeLinecap(r8, r3)
            goto L_0x038d
        L_0x0207:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r9
            if (r10 != 0) goto L_0x020e
            goto L_0x0214
        L_0x020e:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x0214:
            r9.setClipRule(r8, r3)
            goto L_0x038d
        L_0x0219:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r9
            if (r10 != 0) goto L_0x0220
            goto L_0x0223
        L_0x0220:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0223:
            r9.setClipPath(r8, r4)
            goto L_0x038d
        L_0x0228:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStrokeDasharray(r8, r0)
            goto L_0x038d
        L_0x0236:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r9
            if (r10 != 0) goto L_0x023d
            goto L_0x0240
        L_0x023d:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0240:
            r9.setMarkerStart(r8, r4)
            goto L_0x038d
        L_0x0245:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r9
            if (r10 != 0) goto L_0x024c
            goto L_0x0252
        L_0x024c:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x0252:
            r9.setVectorEffect(r8, r3)
            goto L_0x038d
        L_0x0257:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r9
            android.content.Context r0 = r8.getContext()
            java.lang.Integer r10 = com.facebook.react.bridge.ColorPropConverter.getColor(r10, r0)
            r9.setColor(r8, r10)
            goto L_0x038d
        L_0x0268:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r9
            if (r10 != 0) goto L_0x026f
            goto L_0x0275
        L_0x026f:
            java.lang.Double r10 = (java.lang.Double) r10
            float r1 = r10.floatValue()
        L_0x0275:
            r9.setStrokeMiterlimit(r8, r1)
            goto L_0x038d
        L_0x027a:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r9
            if (r10 != 0) goto L_0x0281
            goto L_0x0284
        L_0x0281:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0284:
            r9.setName(r8, r4)
            goto L_0x038d
        L_0x0289:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r9
            if (r10 != 0) goto L_0x0290
            goto L_0x0293
        L_0x0290:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0293:
            r9.setMask(r8, r4)
            goto L_0x038d
        L_0x0298:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFill(r8, r0)
            goto L_0x038d
        L_0x02a6:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setRy(r8, r0)
            goto L_0x038d
        L_0x02b4:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setRx(r8, r0)
            goto L_0x038d
        L_0x02c2:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setCy(r8, r0)
            goto L_0x038d
        L_0x02d0:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setCx(r8, r0)
            goto L_0x038d
        L_0x02de:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r9
            if (r10 != 0) goto L_0x02e5
            goto L_0x02eb
        L_0x02e5:
            java.lang.Double r10 = (java.lang.Double) r10
            float r1 = r10.floatValue()
        L_0x02eb:
            r9.setStrokeDashoffset(r8, r1)
            goto L_0x038d
        L_0x02f0:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r9
            if (r10 != 0) goto L_0x02f7
            goto L_0x02fd
        L_0x02f7:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x02fd:
            r9.setFillOpacity(r8, r2)
            goto L_0x038d
        L_0x0302:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r9
            if (r10 != 0) goto L_0x0309
            goto L_0x030c
        L_0x0309:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x030c:
            r9.setPointerEvents(r8, r4)
            goto L_0x038d
        L_0x0311:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r9
            if (r10 != 0) goto L_0x0318
            goto L_0x031e
        L_0x0318:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x031e:
            r9.setStrokeOpacity(r8, r2)
            goto L_0x038d
        L_0x0323:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r9
            if (r10 != 0) goto L_0x032a
            goto L_0x0330
        L_0x032a:
            java.lang.Double r10 = (java.lang.Double) r10
            int r0 = r10.intValue()
        L_0x0330:
            r9.setFillRule(r8, r0)
            goto L_0x038d
        L_0x0334:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStroke(r8, r0)
            goto L_0x038d
        L_0x0341:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r9
            if (r10 != 0) goto L_0x0348
            goto L_0x034b
        L_0x0348:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x034b:
            r9.setMarkerMid(r8, r4)
            goto L_0x038d
        L_0x034f:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r9
            if (r10 != 0) goto L_0x0356
            goto L_0x0359
        L_0x0356:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0359:
            r9.setMarkerEnd(r8, r4)
            goto L_0x038d
        L_0x035d:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r9
            com.facebook.react.bridge.ReadableArray r10 = (com.facebook.react.bridge.ReadableArray) r10
            r9.setPropList(r8, r10)
            goto L_0x038d
        L_0x0367:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r9
            com.facebook.react.bridge.ReadableArray r10 = (com.facebook.react.bridge.ReadableArray) r10
            r9.setMatrix(r8, r10)
            goto L_0x038d
        L_0x0371:
            U r9 = r7.mViewManager
            if (r10 != 0) goto L_0x0376
            goto L_0x037c
        L_0x0376:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x037c:
            r9.setOpacity(r8, r2)
            goto L_0x038d
        L_0x0380:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface) r9
            if (r10 != 0) goto L_0x0387
            goto L_0x038a
        L_0x0387:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x038a:
            r9.setFilter(r8, r4)
        L_0x038d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSVGEllipseManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
