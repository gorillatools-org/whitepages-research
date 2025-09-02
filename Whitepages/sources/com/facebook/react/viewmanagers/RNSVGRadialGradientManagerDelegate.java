package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface;

public class RNSVGRadialGradientManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSVGRadialGradientManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGRadialGradientManagerDelegate(U u) {
        super(u);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v13, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v14, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v15, resolved type: boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setProperty(T r5, java.lang.String r6, java.lang.Object r7) {
        /*
            r4 = this;
            r6.hashCode()
            r0 = 0
            r1 = 0
            r2 = -1
            int r3 = r6.hashCode()
            switch(r3) {
                case -1932235233: goto L_0x0112;
                case -1267206133: goto L_0x0107;
                case -1081239615: goto L_0x00fc;
                case -933864895: goto L_0x00f1;
                case -933857362: goto L_0x00e6;
                case -293492298: goto L_0x00db;
                case 3189: goto L_0x00d0;
                case 3190: goto L_0x00c5;
                case 3282: goto L_0x00b7;
                case 3283: goto L_0x00a9;
                case 3654: goto L_0x009b;
                case 3655: goto L_0x008d;
                case 3344108: goto L_0x007f;
                case 3373707: goto L_0x0071;
                case 89650992: goto L_0x0063;
                case 217109576: goto L_0x0055;
                case 917656469: goto L_0x0047;
                case 917735020: goto L_0x0039;
                case 1671764162: goto L_0x002b;
                case 1822665244: goto L_0x001d;
                case 1847674614: goto L_0x000f;
                default: goto L_0x000d;
            }
        L_0x000d:
            goto L_0x011c
        L_0x000f:
            java.lang.String r3 = "responsible"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x0019
            goto L_0x011c
        L_0x0019:
            r2 = 20
            goto L_0x011c
        L_0x001d:
            java.lang.String r3 = "gradientTransform"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x0027
            goto L_0x011c
        L_0x0027:
            r2 = 19
            goto L_0x011c
        L_0x002b:
            java.lang.String r3 = "display"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x0035
            goto L_0x011c
        L_0x0035:
            r2 = 18
            goto L_0x011c
        L_0x0039:
            java.lang.String r3 = "clipRule"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x0043
            goto L_0x011c
        L_0x0043:
            r2 = 17
            goto L_0x011c
        L_0x0047:
            java.lang.String r3 = "clipPath"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x0051
            goto L_0x011c
        L_0x0051:
            r2 = 16
            goto L_0x011c
        L_0x0055:
            java.lang.String r3 = "markerStart"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x005f
            goto L_0x011c
        L_0x005f:
            r2 = 15
            goto L_0x011c
        L_0x0063:
            java.lang.String r3 = "gradient"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x006d
            goto L_0x011c
        L_0x006d:
            r2 = 14
            goto L_0x011c
        L_0x0071:
            java.lang.String r3 = "name"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x007b
            goto L_0x011c
        L_0x007b:
            r2 = 13
            goto L_0x011c
        L_0x007f:
            java.lang.String r3 = "mask"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x0089
            goto L_0x011c
        L_0x0089:
            r2 = 12
            goto L_0x011c
        L_0x008d:
            java.lang.String r3 = "ry"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x0097
            goto L_0x011c
        L_0x0097:
            r2 = 11
            goto L_0x011c
        L_0x009b:
            java.lang.String r3 = "rx"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x00a5
            goto L_0x011c
        L_0x00a5:
            r2 = 10
            goto L_0x011c
        L_0x00a9:
            java.lang.String r3 = "fy"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x00b3
            goto L_0x011c
        L_0x00b3:
            r2 = 9
            goto L_0x011c
        L_0x00b7:
            java.lang.String r3 = "fx"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x00c1
            goto L_0x011c
        L_0x00c1:
            r2 = 8
            goto L_0x011c
        L_0x00c5:
            java.lang.String r3 = "cy"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x00ce
            goto L_0x011c
        L_0x00ce:
            r2 = 7
            goto L_0x011c
        L_0x00d0:
            java.lang.String r3 = "cx"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x00d9
            goto L_0x011c
        L_0x00d9:
            r2 = 6
            goto L_0x011c
        L_0x00db:
            java.lang.String r3 = "pointerEvents"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x00e4
            goto L_0x011c
        L_0x00e4:
            r2 = 5
            goto L_0x011c
        L_0x00e6:
            java.lang.String r3 = "markerMid"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x00ef
            goto L_0x011c
        L_0x00ef:
            r2 = 4
            goto L_0x011c
        L_0x00f1:
            java.lang.String r3 = "markerEnd"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x00fa
            goto L_0x011c
        L_0x00fa:
            r2 = 3
            goto L_0x011c
        L_0x00fc:
            java.lang.String r3 = "matrix"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x0105
            goto L_0x011c
        L_0x0105:
            r2 = 2
            goto L_0x011c
        L_0x0107:
            java.lang.String r3 = "opacity"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x0110
            goto L_0x011c
        L_0x0110:
            r2 = 1
            goto L_0x011c
        L_0x0112:
            java.lang.String r3 = "gradientUnits"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x011b
            goto L_0x011c
        L_0x011b:
            r2 = r0
        L_0x011c:
            switch(r2) {
                case 0: goto L_0x0241;
                case 1: goto L_0x0230;
                case 2: goto L_0x0226;
                case 3: goto L_0x0218;
                case 4: goto L_0x020a;
                case 5: goto L_0x01fc;
                case 6: goto L_0x01ef;
                case 7: goto L_0x01e1;
                case 8: goto L_0x01d3;
                case 9: goto L_0x01c5;
                case 10: goto L_0x01b7;
                case 11: goto L_0x01a9;
                case 12: goto L_0x019a;
                case 13: goto L_0x018b;
                case 14: goto L_0x0180;
                case 15: goto L_0x0171;
                case 16: goto L_0x0162;
                case 17: goto L_0x0150;
                case 18: goto L_0x0141;
                case 19: goto L_0x0136;
                case 20: goto L_0x0124;
                default: goto L_0x011f;
            }
        L_0x011f:
            super.setProperty(r5, r6, r7)
            goto L_0x0251
        L_0x0124:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface) r6
            if (r7 != 0) goto L_0x012b
            goto L_0x0131
        L_0x012b:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r0 = r7.booleanValue()
        L_0x0131:
            r6.setResponsible(r5, r0)
            goto L_0x0251
        L_0x0136:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface) r6
            com.facebook.react.bridge.ReadableArray r7 = (com.facebook.react.bridge.ReadableArray) r7
            r6.setGradientTransform(r5, r7)
            goto L_0x0251
        L_0x0141:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface) r6
            if (r7 != 0) goto L_0x0148
            goto L_0x014b
        L_0x0148:
            r1 = r7
            java.lang.String r1 = (java.lang.String) r1
        L_0x014b:
            r6.setDisplay(r5, r1)
            goto L_0x0251
        L_0x0150:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface) r6
            if (r7 != 0) goto L_0x0157
            goto L_0x015d
        L_0x0157:
            java.lang.Double r7 = (java.lang.Double) r7
            int r0 = r7.intValue()
        L_0x015d:
            r6.setClipRule(r5, r0)
            goto L_0x0251
        L_0x0162:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface) r6
            if (r7 != 0) goto L_0x0169
            goto L_0x016c
        L_0x0169:
            r1 = r7
            java.lang.String r1 = (java.lang.String) r1
        L_0x016c:
            r6.setClipPath(r5, r1)
            goto L_0x0251
        L_0x0171:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface) r6
            if (r7 != 0) goto L_0x0178
            goto L_0x017b
        L_0x0178:
            r1 = r7
            java.lang.String r1 = (java.lang.String) r1
        L_0x017b:
            r6.setMarkerStart(r5, r1)
            goto L_0x0251
        L_0x0180:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface) r6
            com.facebook.react.bridge.ReadableArray r7 = (com.facebook.react.bridge.ReadableArray) r7
            r6.setGradient(r5, r7)
            goto L_0x0251
        L_0x018b:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface) r6
            if (r7 != 0) goto L_0x0192
            goto L_0x0195
        L_0x0192:
            r1 = r7
            java.lang.String r1 = (java.lang.String) r1
        L_0x0195:
            r6.setName(r5, r1)
            goto L_0x0251
        L_0x019a:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface) r6
            if (r7 != 0) goto L_0x01a1
            goto L_0x01a4
        L_0x01a1:
            r1 = r7
            java.lang.String r1 = (java.lang.String) r1
        L_0x01a4:
            r6.setMask(r5, r1)
            goto L_0x0251
        L_0x01a9:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface) r6
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r7)
            r6.setRy(r5, r0)
            goto L_0x0251
        L_0x01b7:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface) r6
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r7)
            r6.setRx(r5, r0)
            goto L_0x0251
        L_0x01c5:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface) r6
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r7)
            r6.setFy(r5, r0)
            goto L_0x0251
        L_0x01d3:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface) r6
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r7)
            r6.setFx(r5, r0)
            goto L_0x0251
        L_0x01e1:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface) r6
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r7)
            r6.setCy(r5, r0)
            goto L_0x0251
        L_0x01ef:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface) r6
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r7)
            r6.setCx(r5, r0)
            goto L_0x0251
        L_0x01fc:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface) r6
            if (r7 != 0) goto L_0x0203
            goto L_0x0206
        L_0x0203:
            r1 = r7
            java.lang.String r1 = (java.lang.String) r1
        L_0x0206:
            r6.setPointerEvents(r5, r1)
            goto L_0x0251
        L_0x020a:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface) r6
            if (r7 != 0) goto L_0x0211
            goto L_0x0214
        L_0x0211:
            r1 = r7
            java.lang.String r1 = (java.lang.String) r1
        L_0x0214:
            r6.setMarkerMid(r5, r1)
            goto L_0x0251
        L_0x0218:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface) r6
            if (r7 != 0) goto L_0x021f
            goto L_0x0222
        L_0x021f:
            r1 = r7
            java.lang.String r1 = (java.lang.String) r1
        L_0x0222:
            r6.setMarkerEnd(r5, r1)
            goto L_0x0251
        L_0x0226:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface) r6
            com.facebook.react.bridge.ReadableArray r7 = (com.facebook.react.bridge.ReadableArray) r7
            r6.setMatrix(r5, r7)
            goto L_0x0251
        L_0x0230:
            U r6 = r4.mViewManager
            if (r7 != 0) goto L_0x0237
            r7 = 1065353216(0x3f800000, float:1.0)
            goto L_0x023d
        L_0x0237:
            java.lang.Double r7 = (java.lang.Double) r7
            float r7 = r7.floatValue()
        L_0x023d:
            r6.setOpacity(r5, r7)
            goto L_0x0251
        L_0x0241:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface) r6
            if (r7 != 0) goto L_0x0248
            goto L_0x024e
        L_0x0248:
            java.lang.Double r7 = (java.lang.Double) r7
            int r0 = r7.intValue()
        L_0x024e:
            r6.setGradientUnits(r5, r0)
        L_0x0251:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSVGRadialGradientManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
