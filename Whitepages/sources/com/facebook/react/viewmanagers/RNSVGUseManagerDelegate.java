package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSVGUseManagerInterface;

public class RNSVGUseManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSVGUseManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGUseManagerDelegate(U u) {
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
                case -1274492040: goto L_0x01b0;
                case -1267206133: goto L_0x01a5;
                case -1221029593: goto L_0x019a;
                case -1081239615: goto L_0x018f;
                case -993894751: goto L_0x0184;
                case -933864895: goto L_0x0179;
                case -933857362: goto L_0x016e;
                case -891980232: goto L_0x0163;
                case -729118945: goto L_0x0155;
                case -416535885: goto L_0x0147;
                case -293492298: goto L_0x0139;
                case -53677816: goto L_0x012b;
                case -44578051: goto L_0x011d;
                case 120: goto L_0x010f;
                case 121: goto L_0x0101;
                case 3143043: goto L_0x00f3;
                case 3211051: goto L_0x00e5;
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
            goto L_0x01ba
        L_0x0013:
            java.lang.String r6 = "strokeWidth"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x001d
            goto L_0x01ba
        L_0x001d:
            r5 = 31
            goto L_0x01ba
        L_0x0021:
            java.lang.String r6 = "responsible"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x002b
            goto L_0x01ba
        L_0x002b:
            r5 = 30
            goto L_0x01ba
        L_0x002f:
            java.lang.String r6 = "strokeLinejoin"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0039
            goto L_0x01ba
        L_0x0039:
            r5 = 29
            goto L_0x01ba
        L_0x003d:
            java.lang.String r6 = "display"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0047
            goto L_0x01ba
        L_0x0047:
            r5 = 28
            goto L_0x01ba
        L_0x004b:
            java.lang.String r6 = "strokeLinecap"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0055
            goto L_0x01ba
        L_0x0055:
            r5 = 27
            goto L_0x01ba
        L_0x0059:
            java.lang.String r6 = "clipRule"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0063
            goto L_0x01ba
        L_0x0063:
            r5 = 26
            goto L_0x01ba
        L_0x0067:
            java.lang.String r6 = "clipPath"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0071
            goto L_0x01ba
        L_0x0071:
            r5 = 25
            goto L_0x01ba
        L_0x0075:
            java.lang.String r6 = "strokeDasharray"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x007f
            goto L_0x01ba
        L_0x007f:
            r5 = 24
            goto L_0x01ba
        L_0x0083:
            java.lang.String r6 = "markerStart"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x008d
            goto L_0x01ba
        L_0x008d:
            r5 = 23
            goto L_0x01ba
        L_0x0091:
            java.lang.String r6 = "width"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x009b
            goto L_0x01ba
        L_0x009b:
            r5 = 22
            goto L_0x01ba
        L_0x009f:
            java.lang.String r6 = "vectorEffect"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00a9
            goto L_0x01ba
        L_0x00a9:
            r5 = 21
            goto L_0x01ba
        L_0x00ad:
            java.lang.String r6 = "color"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00b7
            goto L_0x01ba
        L_0x00b7:
            r5 = 20
            goto L_0x01ba
        L_0x00bb:
            java.lang.String r6 = "strokeMiterlimit"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00c5
            goto L_0x01ba
        L_0x00c5:
            r5 = 19
            goto L_0x01ba
        L_0x00c9:
            java.lang.String r6 = "name"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00d3
            goto L_0x01ba
        L_0x00d3:
            r5 = 18
            goto L_0x01ba
        L_0x00d7:
            java.lang.String r6 = "mask"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00e1
            goto L_0x01ba
        L_0x00e1:
            r5 = 17
            goto L_0x01ba
        L_0x00e5:
            java.lang.String r6 = "href"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00ef
            goto L_0x01ba
        L_0x00ef:
            r5 = 16
            goto L_0x01ba
        L_0x00f3:
            java.lang.String r6 = "fill"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00fd
            goto L_0x01ba
        L_0x00fd:
            r5 = 15
            goto L_0x01ba
        L_0x0101:
            java.lang.String r6 = "y"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x010b
            goto L_0x01ba
        L_0x010b:
            r5 = 14
            goto L_0x01ba
        L_0x010f:
            java.lang.String r6 = "x"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0119
            goto L_0x01ba
        L_0x0119:
            r5 = 13
            goto L_0x01ba
        L_0x011d:
            java.lang.String r6 = "strokeDashoffset"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0127
            goto L_0x01ba
        L_0x0127:
            r5 = 12
            goto L_0x01ba
        L_0x012b:
            java.lang.String r6 = "fillOpacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0135
            goto L_0x01ba
        L_0x0135:
            r5 = 11
            goto L_0x01ba
        L_0x0139:
            java.lang.String r6 = "pointerEvents"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0143
            goto L_0x01ba
        L_0x0143:
            r5 = 10
            goto L_0x01ba
        L_0x0147:
            java.lang.String r6 = "strokeOpacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0151
            goto L_0x01ba
        L_0x0151:
            r5 = 9
            goto L_0x01ba
        L_0x0155:
            java.lang.String r6 = "fillRule"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x015f
            goto L_0x01ba
        L_0x015f:
            r5 = 8
            goto L_0x01ba
        L_0x0163:
            java.lang.String r6 = "stroke"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x016c
            goto L_0x01ba
        L_0x016c:
            r5 = 7
            goto L_0x01ba
        L_0x016e:
            java.lang.String r6 = "markerMid"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0177
            goto L_0x01ba
        L_0x0177:
            r5 = 6
            goto L_0x01ba
        L_0x0179:
            java.lang.String r6 = "markerEnd"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0182
            goto L_0x01ba
        L_0x0182:
            r5 = 5
            goto L_0x01ba
        L_0x0184:
            java.lang.String r6 = "propList"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x018d
            goto L_0x01ba
        L_0x018d:
            r5 = 4
            goto L_0x01ba
        L_0x018f:
            java.lang.String r6 = "matrix"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0198
            goto L_0x01ba
        L_0x0198:
            r5 = 3
            goto L_0x01ba
        L_0x019a:
            java.lang.String r6 = "height"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01a3
            goto L_0x01ba
        L_0x01a3:
            r5 = 2
            goto L_0x01ba
        L_0x01a5:
            java.lang.String r6 = "opacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01ae
            goto L_0x01ba
        L_0x01ae:
            r5 = r0
            goto L_0x01ba
        L_0x01b0:
            java.lang.String r6 = "filter"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01b9
            goto L_0x01ba
        L_0x01b9:
            r5 = r3
        L_0x01ba:
            switch(r5) {
                case 0: goto L_0x039d;
                case 1: goto L_0x038e;
                case 2: goto L_0x0381;
                case 3: goto L_0x0377;
                case 4: goto L_0x036d;
                case 5: goto L_0x035f;
                case 6: goto L_0x0351;
                case 7: goto L_0x0344;
                case 8: goto L_0x0332;
                case 9: goto L_0x0320;
                case 10: goto L_0x0311;
                case 11: goto L_0x02ff;
                case 12: goto L_0x02ed;
                case 13: goto L_0x02df;
                case 14: goto L_0x02d1;
                case 15: goto L_0x02c3;
                case 16: goto L_0x02b4;
                case 17: goto L_0x02a5;
                case 18: goto L_0x0296;
                case 19: goto L_0x0284;
                case 20: goto L_0x0273;
                case 21: goto L_0x0261;
                case 22: goto L_0x0253;
                case 23: goto L_0x0244;
                case 24: goto L_0x0236;
                case 25: goto L_0x0227;
                case 26: goto L_0x0215;
                case 27: goto L_0x0203;
                case 28: goto L_0x01f4;
                case 29: goto L_0x01e2;
                case 30: goto L_0x01d0;
                case 31: goto L_0x01c2;
                default: goto L_0x01bd;
            }
        L_0x01bd:
            super.setProperty(r8, r9, r10)
            goto L_0x03aa
        L_0x01c2:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGUseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGUseManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStrokeWidth(r8, r0)
            goto L_0x03aa
        L_0x01d0:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGUseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGUseManagerInterface) r9
            if (r10 != 0) goto L_0x01d7
            goto L_0x01dd
        L_0x01d7:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r3 = r10.booleanValue()
        L_0x01dd:
            r9.setResponsible(r8, r3)
            goto L_0x03aa
        L_0x01e2:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGUseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGUseManagerInterface) r9
            if (r10 != 0) goto L_0x01e9
            goto L_0x01ef
        L_0x01e9:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x01ef:
            r9.setStrokeLinejoin(r8, r3)
            goto L_0x03aa
        L_0x01f4:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGUseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGUseManagerInterface) r9
            if (r10 != 0) goto L_0x01fb
            goto L_0x01fe
        L_0x01fb:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x01fe:
            r9.setDisplay(r8, r4)
            goto L_0x03aa
        L_0x0203:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGUseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGUseManagerInterface) r9
            if (r10 != 0) goto L_0x020a
            goto L_0x0210
        L_0x020a:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x0210:
            r9.setStrokeLinecap(r8, r3)
            goto L_0x03aa
        L_0x0215:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGUseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGUseManagerInterface) r9
            if (r10 != 0) goto L_0x021c
            goto L_0x0222
        L_0x021c:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x0222:
            r9.setClipRule(r8, r3)
            goto L_0x03aa
        L_0x0227:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGUseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGUseManagerInterface) r9
            if (r10 != 0) goto L_0x022e
            goto L_0x0231
        L_0x022e:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0231:
            r9.setClipPath(r8, r4)
            goto L_0x03aa
        L_0x0236:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGUseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGUseManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStrokeDasharray(r8, r0)
            goto L_0x03aa
        L_0x0244:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGUseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGUseManagerInterface) r9
            if (r10 != 0) goto L_0x024b
            goto L_0x024e
        L_0x024b:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x024e:
            r9.setMarkerStart(r8, r4)
            goto L_0x03aa
        L_0x0253:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGUseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGUseManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setWidth(r8, r0)
            goto L_0x03aa
        L_0x0261:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGUseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGUseManagerInterface) r9
            if (r10 != 0) goto L_0x0268
            goto L_0x026e
        L_0x0268:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x026e:
            r9.setVectorEffect(r8, r3)
            goto L_0x03aa
        L_0x0273:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGUseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGUseManagerInterface) r9
            android.content.Context r0 = r8.getContext()
            java.lang.Integer r10 = com.facebook.react.bridge.ColorPropConverter.getColor(r10, r0)
            r9.setColor(r8, r10)
            goto L_0x03aa
        L_0x0284:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGUseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGUseManagerInterface) r9
            if (r10 != 0) goto L_0x028b
            goto L_0x0291
        L_0x028b:
            java.lang.Double r10 = (java.lang.Double) r10
            float r1 = r10.floatValue()
        L_0x0291:
            r9.setStrokeMiterlimit(r8, r1)
            goto L_0x03aa
        L_0x0296:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGUseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGUseManagerInterface) r9
            if (r10 != 0) goto L_0x029d
            goto L_0x02a0
        L_0x029d:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x02a0:
            r9.setName(r8, r4)
            goto L_0x03aa
        L_0x02a5:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGUseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGUseManagerInterface) r9
            if (r10 != 0) goto L_0x02ac
            goto L_0x02af
        L_0x02ac:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x02af:
            r9.setMask(r8, r4)
            goto L_0x03aa
        L_0x02b4:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGUseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGUseManagerInterface) r9
            if (r10 != 0) goto L_0x02bb
            goto L_0x02be
        L_0x02bb:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x02be:
            r9.setHref(r8, r4)
            goto L_0x03aa
        L_0x02c3:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGUseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGUseManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFill(r8, r0)
            goto L_0x03aa
        L_0x02d1:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGUseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGUseManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setY(r8, r0)
            goto L_0x03aa
        L_0x02df:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGUseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGUseManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setX(r8, r0)
            goto L_0x03aa
        L_0x02ed:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGUseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGUseManagerInterface) r9
            if (r10 != 0) goto L_0x02f4
            goto L_0x02fa
        L_0x02f4:
            java.lang.Double r10 = (java.lang.Double) r10
            float r1 = r10.floatValue()
        L_0x02fa:
            r9.setStrokeDashoffset(r8, r1)
            goto L_0x03aa
        L_0x02ff:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGUseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGUseManagerInterface) r9
            if (r10 != 0) goto L_0x0306
            goto L_0x030c
        L_0x0306:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x030c:
            r9.setFillOpacity(r8, r2)
            goto L_0x03aa
        L_0x0311:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGUseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGUseManagerInterface) r9
            if (r10 != 0) goto L_0x0318
            goto L_0x031b
        L_0x0318:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x031b:
            r9.setPointerEvents(r8, r4)
            goto L_0x03aa
        L_0x0320:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGUseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGUseManagerInterface) r9
            if (r10 != 0) goto L_0x0327
            goto L_0x032d
        L_0x0327:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x032d:
            r9.setStrokeOpacity(r8, r2)
            goto L_0x03aa
        L_0x0332:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGUseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGUseManagerInterface) r9
            if (r10 != 0) goto L_0x0339
            goto L_0x033f
        L_0x0339:
            java.lang.Double r10 = (java.lang.Double) r10
            int r0 = r10.intValue()
        L_0x033f:
            r9.setFillRule(r8, r0)
            goto L_0x03aa
        L_0x0344:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGUseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGUseManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStroke(r8, r0)
            goto L_0x03aa
        L_0x0351:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGUseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGUseManagerInterface) r9
            if (r10 != 0) goto L_0x0358
            goto L_0x035b
        L_0x0358:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x035b:
            r9.setMarkerMid(r8, r4)
            goto L_0x03aa
        L_0x035f:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGUseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGUseManagerInterface) r9
            if (r10 != 0) goto L_0x0366
            goto L_0x0369
        L_0x0366:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0369:
            r9.setMarkerEnd(r8, r4)
            goto L_0x03aa
        L_0x036d:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGUseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGUseManagerInterface) r9
            com.facebook.react.bridge.ReadableArray r10 = (com.facebook.react.bridge.ReadableArray) r10
            r9.setPropList(r8, r10)
            goto L_0x03aa
        L_0x0377:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGUseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGUseManagerInterface) r9
            com.facebook.react.bridge.ReadableArray r10 = (com.facebook.react.bridge.ReadableArray) r10
            r9.setMatrix(r8, r10)
            goto L_0x03aa
        L_0x0381:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGUseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGUseManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setHeight(r8, r0)
            goto L_0x03aa
        L_0x038e:
            U r9 = r7.mViewManager
            if (r10 != 0) goto L_0x0393
            goto L_0x0399
        L_0x0393:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x0399:
            r9.setOpacity(r8, r2)
            goto L_0x03aa
        L_0x039d:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGUseManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGUseManagerInterface) r9
            if (r10 != 0) goto L_0x03a4
            goto L_0x03a7
        L_0x03a4:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x03a7:
            r9.setFilter(r8, r4)
        L_0x03aa:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSVGUseManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
