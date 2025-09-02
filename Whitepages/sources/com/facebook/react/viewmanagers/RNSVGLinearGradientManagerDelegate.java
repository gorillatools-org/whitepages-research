package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface;

public class RNSVGLinearGradientManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSVGLinearGradientManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGLinearGradientManagerDelegate(U u) {
        super(u);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v13, resolved type: boolean} */
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
                case -1932235233: goto L_0x00f6;
                case -1267206133: goto L_0x00eb;
                case -1081239615: goto L_0x00e0;
                case -933864895: goto L_0x00d5;
                case -933857362: goto L_0x00ca;
                case -293492298: goto L_0x00bf;
                case 3769: goto L_0x00b4;
                case 3770: goto L_0x00a9;
                case 3800: goto L_0x009b;
                case 3801: goto L_0x008d;
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
            goto L_0x0100
        L_0x000f:
            java.lang.String r3 = "responsible"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x0019
            goto L_0x0100
        L_0x0019:
            r2 = 18
            goto L_0x0100
        L_0x001d:
            java.lang.String r3 = "gradientTransform"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x0027
            goto L_0x0100
        L_0x0027:
            r2 = 17
            goto L_0x0100
        L_0x002b:
            java.lang.String r3 = "display"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x0035
            goto L_0x0100
        L_0x0035:
            r2 = 16
            goto L_0x0100
        L_0x0039:
            java.lang.String r3 = "clipRule"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x0043
            goto L_0x0100
        L_0x0043:
            r2 = 15
            goto L_0x0100
        L_0x0047:
            java.lang.String r3 = "clipPath"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x0051
            goto L_0x0100
        L_0x0051:
            r2 = 14
            goto L_0x0100
        L_0x0055:
            java.lang.String r3 = "markerStart"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x005f
            goto L_0x0100
        L_0x005f:
            r2 = 13
            goto L_0x0100
        L_0x0063:
            java.lang.String r3 = "gradient"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x006d
            goto L_0x0100
        L_0x006d:
            r2 = 12
            goto L_0x0100
        L_0x0071:
            java.lang.String r3 = "name"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x007b
            goto L_0x0100
        L_0x007b:
            r2 = 11
            goto L_0x0100
        L_0x007f:
            java.lang.String r3 = "mask"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x0089
            goto L_0x0100
        L_0x0089:
            r2 = 10
            goto L_0x0100
        L_0x008d:
            java.lang.String r3 = "y2"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x0097
            goto L_0x0100
        L_0x0097:
            r2 = 9
            goto L_0x0100
        L_0x009b:
            java.lang.String r3 = "y1"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x00a5
            goto L_0x0100
        L_0x00a5:
            r2 = 8
            goto L_0x0100
        L_0x00a9:
            java.lang.String r3 = "x2"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x00b2
            goto L_0x0100
        L_0x00b2:
            r2 = 7
            goto L_0x0100
        L_0x00b4:
            java.lang.String r3 = "x1"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x00bd
            goto L_0x0100
        L_0x00bd:
            r2 = 6
            goto L_0x0100
        L_0x00bf:
            java.lang.String r3 = "pointerEvents"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x00c8
            goto L_0x0100
        L_0x00c8:
            r2 = 5
            goto L_0x0100
        L_0x00ca:
            java.lang.String r3 = "markerMid"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x00d3
            goto L_0x0100
        L_0x00d3:
            r2 = 4
            goto L_0x0100
        L_0x00d5:
            java.lang.String r3 = "markerEnd"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x00de
            goto L_0x0100
        L_0x00de:
            r2 = 3
            goto L_0x0100
        L_0x00e0:
            java.lang.String r3 = "matrix"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x00e9
            goto L_0x0100
        L_0x00e9:
            r2 = 2
            goto L_0x0100
        L_0x00eb:
            java.lang.String r3 = "opacity"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x00f4
            goto L_0x0100
        L_0x00f4:
            r2 = 1
            goto L_0x0100
        L_0x00f6:
            java.lang.String r3 = "gradientUnits"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x00ff
            goto L_0x0100
        L_0x00ff:
            r2 = r0
        L_0x0100:
            switch(r2) {
                case 0: goto L_0x0209;
                case 1: goto L_0x01f8;
                case 2: goto L_0x01ee;
                case 3: goto L_0x01e0;
                case 4: goto L_0x01d2;
                case 5: goto L_0x01c4;
                case 6: goto L_0x01b7;
                case 7: goto L_0x01a9;
                case 8: goto L_0x019b;
                case 9: goto L_0x018d;
                case 10: goto L_0x017e;
                case 11: goto L_0x016f;
                case 12: goto L_0x0164;
                case 13: goto L_0x0155;
                case 14: goto L_0x0146;
                case 15: goto L_0x0134;
                case 16: goto L_0x0125;
                case 17: goto L_0x011a;
                case 18: goto L_0x0108;
                default: goto L_0x0103;
            }
        L_0x0103:
            super.setProperty(r5, r6, r7)
            goto L_0x0219
        L_0x0108:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r6
            if (r7 != 0) goto L_0x010f
            goto L_0x0115
        L_0x010f:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r0 = r7.booleanValue()
        L_0x0115:
            r6.setResponsible(r5, r0)
            goto L_0x0219
        L_0x011a:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r6
            com.facebook.react.bridge.ReadableArray r7 = (com.facebook.react.bridge.ReadableArray) r7
            r6.setGradientTransform(r5, r7)
            goto L_0x0219
        L_0x0125:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r6
            if (r7 != 0) goto L_0x012c
            goto L_0x012f
        L_0x012c:
            r1 = r7
            java.lang.String r1 = (java.lang.String) r1
        L_0x012f:
            r6.setDisplay(r5, r1)
            goto L_0x0219
        L_0x0134:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r6
            if (r7 != 0) goto L_0x013b
            goto L_0x0141
        L_0x013b:
            java.lang.Double r7 = (java.lang.Double) r7
            int r0 = r7.intValue()
        L_0x0141:
            r6.setClipRule(r5, r0)
            goto L_0x0219
        L_0x0146:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r6
            if (r7 != 0) goto L_0x014d
            goto L_0x0150
        L_0x014d:
            r1 = r7
            java.lang.String r1 = (java.lang.String) r1
        L_0x0150:
            r6.setClipPath(r5, r1)
            goto L_0x0219
        L_0x0155:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r6
            if (r7 != 0) goto L_0x015c
            goto L_0x015f
        L_0x015c:
            r1 = r7
            java.lang.String r1 = (java.lang.String) r1
        L_0x015f:
            r6.setMarkerStart(r5, r1)
            goto L_0x0219
        L_0x0164:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r6
            com.facebook.react.bridge.ReadableArray r7 = (com.facebook.react.bridge.ReadableArray) r7
            r6.setGradient(r5, r7)
            goto L_0x0219
        L_0x016f:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r6
            if (r7 != 0) goto L_0x0176
            goto L_0x0179
        L_0x0176:
            r1 = r7
            java.lang.String r1 = (java.lang.String) r1
        L_0x0179:
            r6.setName(r5, r1)
            goto L_0x0219
        L_0x017e:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r6
            if (r7 != 0) goto L_0x0185
            goto L_0x0188
        L_0x0185:
            r1 = r7
            java.lang.String r1 = (java.lang.String) r1
        L_0x0188:
            r6.setMask(r5, r1)
            goto L_0x0219
        L_0x018d:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r6
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r7)
            r6.setY2(r5, r0)
            goto L_0x0219
        L_0x019b:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r6
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r7)
            r6.setY1(r5, r0)
            goto L_0x0219
        L_0x01a9:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r6
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r7)
            r6.setX2(r5, r0)
            goto L_0x0219
        L_0x01b7:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r6
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r7)
            r6.setX1(r5, r0)
            goto L_0x0219
        L_0x01c4:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r6
            if (r7 != 0) goto L_0x01cb
            goto L_0x01ce
        L_0x01cb:
            r1 = r7
            java.lang.String r1 = (java.lang.String) r1
        L_0x01ce:
            r6.setPointerEvents(r5, r1)
            goto L_0x0219
        L_0x01d2:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r6
            if (r7 != 0) goto L_0x01d9
            goto L_0x01dc
        L_0x01d9:
            r1 = r7
            java.lang.String r1 = (java.lang.String) r1
        L_0x01dc:
            r6.setMarkerMid(r5, r1)
            goto L_0x0219
        L_0x01e0:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r6
            if (r7 != 0) goto L_0x01e7
            goto L_0x01ea
        L_0x01e7:
            r1 = r7
            java.lang.String r1 = (java.lang.String) r1
        L_0x01ea:
            r6.setMarkerEnd(r5, r1)
            goto L_0x0219
        L_0x01ee:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r6
            com.facebook.react.bridge.ReadableArray r7 = (com.facebook.react.bridge.ReadableArray) r7
            r6.setMatrix(r5, r7)
            goto L_0x0219
        L_0x01f8:
            U r6 = r4.mViewManager
            if (r7 != 0) goto L_0x01ff
            r7 = 1065353216(0x3f800000, float:1.0)
            goto L_0x0205
        L_0x01ff:
            java.lang.Double r7 = (java.lang.Double) r7
            float r7 = r7.floatValue()
        L_0x0205:
            r6.setOpacity(r5, r7)
            goto L_0x0219
        L_0x0209:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface) r6
            if (r7 != 0) goto L_0x0210
            goto L_0x0216
        L_0x0210:
            java.lang.Double r7 = (java.lang.Double) r7
            int r0 = r7.intValue()
        L_0x0216:
            r6.setGradientUnits(r5, r0)
        L_0x0219:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSVGLinearGradientManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
