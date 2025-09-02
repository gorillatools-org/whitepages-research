package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface;

public class RNSVGMarkerManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSVGMarkerManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGMarkerManagerDelegate(U u) {
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
                case -1567958285: goto L_0x023c;
                case -1274492040: goto L_0x0231;
                case -1267206133: goto L_0x0226;
                case -1081239615: goto L_0x021b;
                case -1008621499: goto L_0x0210;
                case -993894751: goto L_0x0205;
                case -933864895: goto L_0x01fa;
                case -933857362: goto L_0x01ef;
                case -891980232: goto L_0x01e1;
                case -734428249: goto L_0x01d3;
                case -729118945: goto L_0x01c5;
                case -416535885: goto L_0x01b7;
                case -293492298: goto L_0x01a9;
                case -53677816: goto L_0x019b;
                case -44578051: goto L_0x018d;
                case 3143043: goto L_0x017f;
                case 3148879: goto L_0x0171;
                case 3344108: goto L_0x0163;
                case 3351622: goto L_0x0155;
                case 3351623: goto L_0x0147;
                case 3373707: goto L_0x0139;
                case 3496485: goto L_0x012b;
                case 3496486: goto L_0x011d;
                case 78845486: goto L_0x010f;
                case 92903173: goto L_0x0101;
                case 94842723: goto L_0x00f3;
                case 104482996: goto L_0x00e5;
                case 217109576: goto L_0x00d7;
                case 218785621: goto L_0x00c9;
                case 220478892: goto L_0x00bb;
                case 240482938: goto L_0x00ad;
                case 365601008: goto L_0x009f;
                case 401643183: goto L_0x0091;
                case 917656469: goto L_0x0083;
                case 917735020: goto L_0x0075;
                case 1027575302: goto L_0x0067;
                case 1671764162: goto L_0x0059;
                case 1790285174: goto L_0x004b;
                case 1847674614: goto L_0x003d;
                case 1908075304: goto L_0x002f;
                case 1924065902: goto L_0x0021;
                case 2106883585: goto L_0x0013;
                default: goto L_0x0011;
            }
        L_0x0011:
            goto L_0x0246
        L_0x0013:
            java.lang.String r6 = "markerHeight"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x001d
            goto L_0x0246
        L_0x001d:
            r5 = 41
            goto L_0x0246
        L_0x0021:
            java.lang.String r6 = "strokeWidth"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x002b
            goto L_0x0246
        L_0x002b:
            r5 = 40
            goto L_0x0246
        L_0x002f:
            java.lang.String r6 = "meetOrSlice"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0039
            goto L_0x0246
        L_0x0039:
            r5 = 39
            goto L_0x0246
        L_0x003d:
            java.lang.String r6 = "responsible"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0047
            goto L_0x0246
        L_0x0047:
            r5 = 38
            goto L_0x0246
        L_0x004b:
            java.lang.String r6 = "strokeLinejoin"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0055
            goto L_0x0246
        L_0x0055:
            r5 = 37
            goto L_0x0246
        L_0x0059:
            java.lang.String r6 = "display"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0063
            goto L_0x0246
        L_0x0063:
            r5 = 36
            goto L_0x0246
        L_0x0067:
            java.lang.String r6 = "strokeLinecap"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0071
            goto L_0x0246
        L_0x0071:
            r5 = 35
            goto L_0x0246
        L_0x0075:
            java.lang.String r6 = "clipRule"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x007f
            goto L_0x0246
        L_0x007f:
            r5 = 34
            goto L_0x0246
        L_0x0083:
            java.lang.String r6 = "clipPath"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x008d
            goto L_0x0246
        L_0x008d:
            r5 = 33
            goto L_0x0246
        L_0x0091:
            java.lang.String r6 = "strokeDasharray"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x009b
            goto L_0x0246
        L_0x009b:
            r5 = 32
            goto L_0x0246
        L_0x009f:
            java.lang.String r6 = "fontSize"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00a9
            goto L_0x0246
        L_0x00a9:
            r5 = 31
            goto L_0x0246
        L_0x00ad:
            java.lang.String r6 = "vbWidth"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00b7
            goto L_0x0246
        L_0x00b7:
            r5 = 30
            goto L_0x0246
        L_0x00bb:
            java.lang.String r6 = "markerWidth"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00c5
            goto L_0x0246
        L_0x00c5:
            r5 = 29
            goto L_0x0246
        L_0x00c9:
            java.lang.String r6 = "markerUnits"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00d3
            goto L_0x0246
        L_0x00d3:
            r5 = 28
            goto L_0x0246
        L_0x00d7:
            java.lang.String r6 = "markerStart"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00e1
            goto L_0x0246
        L_0x00e1:
            r5 = 27
            goto L_0x0246
        L_0x00e5:
            java.lang.String r6 = "vectorEffect"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00ef
            goto L_0x0246
        L_0x00ef:
            r5 = 26
            goto L_0x0246
        L_0x00f3:
            java.lang.String r6 = "color"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00fd
            goto L_0x0246
        L_0x00fd:
            r5 = 25
            goto L_0x0246
        L_0x0101:
            java.lang.String r6 = "align"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x010b
            goto L_0x0246
        L_0x010b:
            r5 = 24
            goto L_0x0246
        L_0x010f:
            java.lang.String r6 = "strokeMiterlimit"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0119
            goto L_0x0246
        L_0x0119:
            r5 = 23
            goto L_0x0246
        L_0x011d:
            java.lang.String r6 = "refY"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0127
            goto L_0x0246
        L_0x0127:
            r5 = 22
            goto L_0x0246
        L_0x012b:
            java.lang.String r6 = "refX"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0135
            goto L_0x0246
        L_0x0135:
            r5 = 21
            goto L_0x0246
        L_0x0139:
            java.lang.String r6 = "name"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0143
            goto L_0x0246
        L_0x0143:
            r5 = 20
            goto L_0x0246
        L_0x0147:
            java.lang.String r6 = "minY"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0151
            goto L_0x0246
        L_0x0151:
            r5 = 19
            goto L_0x0246
        L_0x0155:
            java.lang.String r6 = "minX"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x015f
            goto L_0x0246
        L_0x015f:
            r5 = 18
            goto L_0x0246
        L_0x0163:
            java.lang.String r6 = "mask"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x016d
            goto L_0x0246
        L_0x016d:
            r5 = 17
            goto L_0x0246
        L_0x0171:
            java.lang.String r6 = "font"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x017b
            goto L_0x0246
        L_0x017b:
            r5 = 16
            goto L_0x0246
        L_0x017f:
            java.lang.String r6 = "fill"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0189
            goto L_0x0246
        L_0x0189:
            r5 = 15
            goto L_0x0246
        L_0x018d:
            java.lang.String r6 = "strokeDashoffset"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0197
            goto L_0x0246
        L_0x0197:
            r5 = 14
            goto L_0x0246
        L_0x019b:
            java.lang.String r6 = "fillOpacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01a5
            goto L_0x0246
        L_0x01a5:
            r5 = 13
            goto L_0x0246
        L_0x01a9:
            java.lang.String r6 = "pointerEvents"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01b3
            goto L_0x0246
        L_0x01b3:
            r5 = 12
            goto L_0x0246
        L_0x01b7:
            java.lang.String r6 = "strokeOpacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01c1
            goto L_0x0246
        L_0x01c1:
            r5 = 11
            goto L_0x0246
        L_0x01c5:
            java.lang.String r6 = "fillRule"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01cf
            goto L_0x0246
        L_0x01cf:
            r5 = 10
            goto L_0x0246
        L_0x01d3:
            java.lang.String r6 = "fontWeight"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01dd
            goto L_0x0246
        L_0x01dd:
            r5 = 9
            goto L_0x0246
        L_0x01e1:
            java.lang.String r6 = "stroke"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01eb
            goto L_0x0246
        L_0x01eb:
            r5 = 8
            goto L_0x0246
        L_0x01ef:
            java.lang.String r6 = "markerMid"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01f8
            goto L_0x0246
        L_0x01f8:
            r5 = 7
            goto L_0x0246
        L_0x01fa:
            java.lang.String r6 = "markerEnd"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0203
            goto L_0x0246
        L_0x0203:
            r5 = 6
            goto L_0x0246
        L_0x0205:
            java.lang.String r6 = "propList"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x020e
            goto L_0x0246
        L_0x020e:
            r5 = 5
            goto L_0x0246
        L_0x0210:
            java.lang.String r6 = "orient"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0219
            goto L_0x0246
        L_0x0219:
            r5 = 4
            goto L_0x0246
        L_0x021b:
            java.lang.String r6 = "matrix"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0224
            goto L_0x0246
        L_0x0224:
            r5 = 3
            goto L_0x0246
        L_0x0226:
            java.lang.String r6 = "opacity"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x022f
            goto L_0x0246
        L_0x022f:
            r5 = 2
            goto L_0x0246
        L_0x0231:
            java.lang.String r6 = "filter"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x023a
            goto L_0x0246
        L_0x023a:
            r5 = r0
            goto L_0x0246
        L_0x023c:
            java.lang.String r6 = "vbHeight"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0245
            goto L_0x0246
        L_0x0245:
            r5 = r3
        L_0x0246:
            switch(r5) {
                case 0: goto L_0x04c8;
                case 1: goto L_0x04ba;
                case 2: goto L_0x04ab;
                case 3: goto L_0x04a1;
                case 4: goto L_0x0493;
                case 5: goto L_0x0489;
                case 6: goto L_0x047b;
                case 7: goto L_0x046d;
                case 8: goto L_0x045f;
                case 9: goto L_0x0451;
                case 10: goto L_0x043f;
                case 11: goto L_0x042d;
                case 12: goto L_0x041e;
                case 13: goto L_0x040c;
                case 14: goto L_0x03fa;
                case 15: goto L_0x03ec;
                case 16: goto L_0x03de;
                case 17: goto L_0x03cf;
                case 18: goto L_0x03bd;
                case 19: goto L_0x03ab;
                case 20: goto L_0x039c;
                case 21: goto L_0x038e;
                case 22: goto L_0x0380;
                case 23: goto L_0x036e;
                case 24: goto L_0x035f;
                case 25: goto L_0x034e;
                case 26: goto L_0x033c;
                case 27: goto L_0x032d;
                case 28: goto L_0x031e;
                case 29: goto L_0x0310;
                case 30: goto L_0x02fe;
                case 31: goto L_0x02f0;
                case 32: goto L_0x02e2;
                case 33: goto L_0x02d3;
                case 34: goto L_0x02c1;
                case 35: goto L_0x02af;
                case 36: goto L_0x02a0;
                case 37: goto L_0x028e;
                case 38: goto L_0x027c;
                case 39: goto L_0x026a;
                case 40: goto L_0x025c;
                case 41: goto L_0x024e;
                default: goto L_0x0249;
            }
        L_0x0249:
            super.setProperty(r8, r9, r10)
            goto L_0x04d8
        L_0x024e:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setMarkerHeight(r8, r0)
            goto L_0x04d8
        L_0x025c:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStrokeWidth(r8, r0)
            goto L_0x04d8
        L_0x026a:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            if (r10 != 0) goto L_0x0271
            goto L_0x0277
        L_0x0271:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x0277:
            r9.setMeetOrSlice(r8, r3)
            goto L_0x04d8
        L_0x027c:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            if (r10 != 0) goto L_0x0283
            goto L_0x0289
        L_0x0283:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r3 = r10.booleanValue()
        L_0x0289:
            r9.setResponsible(r8, r3)
            goto L_0x04d8
        L_0x028e:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            if (r10 != 0) goto L_0x0295
            goto L_0x029b
        L_0x0295:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x029b:
            r9.setStrokeLinejoin(r8, r3)
            goto L_0x04d8
        L_0x02a0:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            if (r10 != 0) goto L_0x02a7
            goto L_0x02aa
        L_0x02a7:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x02aa:
            r9.setDisplay(r8, r4)
            goto L_0x04d8
        L_0x02af:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            if (r10 != 0) goto L_0x02b6
            goto L_0x02bc
        L_0x02b6:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x02bc:
            r9.setStrokeLinecap(r8, r3)
            goto L_0x04d8
        L_0x02c1:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            if (r10 != 0) goto L_0x02c8
            goto L_0x02ce
        L_0x02c8:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x02ce:
            r9.setClipRule(r8, r3)
            goto L_0x04d8
        L_0x02d3:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            if (r10 != 0) goto L_0x02da
            goto L_0x02dd
        L_0x02da:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x02dd:
            r9.setClipPath(r8, r4)
            goto L_0x04d8
        L_0x02e2:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStrokeDasharray(r8, r0)
            goto L_0x04d8
        L_0x02f0:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFontSize(r8, r0)
            goto L_0x04d8
        L_0x02fe:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            if (r10 != 0) goto L_0x0305
            goto L_0x030b
        L_0x0305:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x030b:
            r9.setVbWidth(r8, r2)
            goto L_0x04d8
        L_0x0310:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setMarkerWidth(r8, r0)
            goto L_0x04d8
        L_0x031e:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            if (r10 != 0) goto L_0x0325
            goto L_0x0328
        L_0x0325:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0328:
            r9.setMarkerUnits(r8, r4)
            goto L_0x04d8
        L_0x032d:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            if (r10 != 0) goto L_0x0334
            goto L_0x0337
        L_0x0334:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0337:
            r9.setMarkerStart(r8, r4)
            goto L_0x04d8
        L_0x033c:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            if (r10 != 0) goto L_0x0343
            goto L_0x0349
        L_0x0343:
            java.lang.Double r10 = (java.lang.Double) r10
            int r3 = r10.intValue()
        L_0x0349:
            r9.setVectorEffect(r8, r3)
            goto L_0x04d8
        L_0x034e:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            android.content.Context r0 = r8.getContext()
            java.lang.Integer r10 = com.facebook.react.bridge.ColorPropConverter.getColor(r10, r0)
            r9.setColor(r8, r10)
            goto L_0x04d8
        L_0x035f:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            if (r10 != 0) goto L_0x0366
            goto L_0x0369
        L_0x0366:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0369:
            r9.setAlign(r8, r4)
            goto L_0x04d8
        L_0x036e:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            if (r10 != 0) goto L_0x0375
            goto L_0x037b
        L_0x0375:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x037b:
            r9.setStrokeMiterlimit(r8, r2)
            goto L_0x04d8
        L_0x0380:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setRefY(r8, r0)
            goto L_0x04d8
        L_0x038e:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setRefX(r8, r0)
            goto L_0x04d8
        L_0x039c:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            if (r10 != 0) goto L_0x03a3
            goto L_0x03a6
        L_0x03a3:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x03a6:
            r9.setName(r8, r4)
            goto L_0x04d8
        L_0x03ab:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            if (r10 != 0) goto L_0x03b2
            goto L_0x03b8
        L_0x03b2:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x03b8:
            r9.setMinY(r8, r2)
            goto L_0x04d8
        L_0x03bd:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            if (r10 != 0) goto L_0x03c4
            goto L_0x03ca
        L_0x03c4:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x03ca:
            r9.setMinX(r8, r2)
            goto L_0x04d8
        L_0x03cf:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            if (r10 != 0) goto L_0x03d6
            goto L_0x03d9
        L_0x03d6:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x03d9:
            r9.setMask(r8, r4)
            goto L_0x04d8
        L_0x03de:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFont(r8, r0)
            goto L_0x04d8
        L_0x03ec:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFill(r8, r0)
            goto L_0x04d8
        L_0x03fa:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            if (r10 != 0) goto L_0x0401
            goto L_0x0407
        L_0x0401:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x0407:
            r9.setStrokeDashoffset(r8, r2)
            goto L_0x04d8
        L_0x040c:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            if (r10 != 0) goto L_0x0413
            goto L_0x0419
        L_0x0413:
            java.lang.Double r10 = (java.lang.Double) r10
            float r1 = r10.floatValue()
        L_0x0419:
            r9.setFillOpacity(r8, r1)
            goto L_0x04d8
        L_0x041e:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            if (r10 != 0) goto L_0x0425
            goto L_0x0428
        L_0x0425:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0428:
            r9.setPointerEvents(r8, r4)
            goto L_0x04d8
        L_0x042d:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            if (r10 != 0) goto L_0x0434
            goto L_0x043a
        L_0x0434:
            java.lang.Double r10 = (java.lang.Double) r10
            float r1 = r10.floatValue()
        L_0x043a:
            r9.setStrokeOpacity(r8, r1)
            goto L_0x04d8
        L_0x043f:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            if (r10 != 0) goto L_0x0446
            goto L_0x044c
        L_0x0446:
            java.lang.Double r10 = (java.lang.Double) r10
            int r0 = r10.intValue()
        L_0x044c:
            r9.setFillRule(r8, r0)
            goto L_0x04d8
        L_0x0451:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setFontWeight(r8, r0)
            goto L_0x04d8
        L_0x045f:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r10)
            r9.setStroke(r8, r0)
            goto L_0x04d8
        L_0x046d:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            if (r10 != 0) goto L_0x0474
            goto L_0x0477
        L_0x0474:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0477:
            r9.setMarkerMid(r8, r4)
            goto L_0x04d8
        L_0x047b:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            if (r10 != 0) goto L_0x0482
            goto L_0x0485
        L_0x0482:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x0485:
            r9.setMarkerEnd(r8, r4)
            goto L_0x04d8
        L_0x0489:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            com.facebook.react.bridge.ReadableArray r10 = (com.facebook.react.bridge.ReadableArray) r10
            r9.setPropList(r8, r10)
            goto L_0x04d8
        L_0x0493:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            if (r10 != 0) goto L_0x049a
            goto L_0x049d
        L_0x049a:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x049d:
            r9.setOrient(r8, r4)
            goto L_0x04d8
        L_0x04a1:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            com.facebook.react.bridge.ReadableArray r10 = (com.facebook.react.bridge.ReadableArray) r10
            r9.setMatrix(r8, r10)
            goto L_0x04d8
        L_0x04ab:
            U r9 = r7.mViewManager
            if (r10 != 0) goto L_0x04b0
            goto L_0x04b6
        L_0x04b0:
            java.lang.Double r10 = (java.lang.Double) r10
            float r1 = r10.floatValue()
        L_0x04b6:
            r9.setOpacity(r8, r1)
            goto L_0x04d8
        L_0x04ba:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            if (r10 != 0) goto L_0x04c1
            goto L_0x04c4
        L_0x04c1:
            r4 = r10
            java.lang.String r4 = (java.lang.String) r4
        L_0x04c4:
            r9.setFilter(r8, r4)
            goto L_0x04d8
        L_0x04c8:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface r9 = (com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface) r9
            if (r10 != 0) goto L_0x04cf
            goto L_0x04d5
        L_0x04cf:
            java.lang.Double r10 = (java.lang.Double) r10
            float r2 = r10.floatValue()
        L_0x04d5:
            r9.setVbHeight(r8, r2)
        L_0x04d8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSVGMarkerManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
