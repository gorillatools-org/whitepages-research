package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSVGDefsManagerInterface;

public class RNSVGDefsManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSVGDefsManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGDefsManagerDelegate(U u) {
        super(u);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: boolean} */
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
                case -1267206133: goto L_0x0094;
                case -1081239615: goto L_0x0089;
                case -933864895: goto L_0x007e;
                case -933857362: goto L_0x0073;
                case -293492298: goto L_0x0068;
                case 3344108: goto L_0x005d;
                case 3373707: goto L_0x0052;
                case 217109576: goto L_0x0047;
                case 917656469: goto L_0x0039;
                case 917735020: goto L_0x002b;
                case 1671764162: goto L_0x001d;
                case 1847674614: goto L_0x000f;
                default: goto L_0x000d;
            }
        L_0x000d:
            goto L_0x009e
        L_0x000f:
            java.lang.String r3 = "responsible"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x0019
            goto L_0x009e
        L_0x0019:
            r2 = 11
            goto L_0x009e
        L_0x001d:
            java.lang.String r3 = "display"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x0027
            goto L_0x009e
        L_0x0027:
            r2 = 10
            goto L_0x009e
        L_0x002b:
            java.lang.String r3 = "clipRule"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x0035
            goto L_0x009e
        L_0x0035:
            r2 = 9
            goto L_0x009e
        L_0x0039:
            java.lang.String r3 = "clipPath"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x0043
            goto L_0x009e
        L_0x0043:
            r2 = 8
            goto L_0x009e
        L_0x0047:
            java.lang.String r3 = "markerStart"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x0050
            goto L_0x009e
        L_0x0050:
            r2 = 7
            goto L_0x009e
        L_0x0052:
            java.lang.String r3 = "name"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x005b
            goto L_0x009e
        L_0x005b:
            r2 = 6
            goto L_0x009e
        L_0x005d:
            java.lang.String r3 = "mask"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x0066
            goto L_0x009e
        L_0x0066:
            r2 = 5
            goto L_0x009e
        L_0x0068:
            java.lang.String r3 = "pointerEvents"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x0071
            goto L_0x009e
        L_0x0071:
            r2 = 4
            goto L_0x009e
        L_0x0073:
            java.lang.String r3 = "markerMid"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x007c
            goto L_0x009e
        L_0x007c:
            r2 = 3
            goto L_0x009e
        L_0x007e:
            java.lang.String r3 = "markerEnd"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x0087
            goto L_0x009e
        L_0x0087:
            r2 = 2
            goto L_0x009e
        L_0x0089:
            java.lang.String r3 = "matrix"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x0092
            goto L_0x009e
        L_0x0092:
            r2 = 1
            goto L_0x009e
        L_0x0094:
            java.lang.String r3 = "opacity"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x009d
            goto L_0x009e
        L_0x009d:
            r2 = r0
        L_0x009e:
            switch(r2) {
                case 0: goto L_0x0147;
                case 1: goto L_0x013d;
                case 2: goto L_0x012f;
                case 3: goto L_0x0121;
                case 4: goto L_0x0113;
                case 5: goto L_0x0105;
                case 6: goto L_0x00f7;
                case 7: goto L_0x00e8;
                case 8: goto L_0x00d9;
                case 9: goto L_0x00c7;
                case 10: goto L_0x00b8;
                case 11: goto L_0x00a6;
                default: goto L_0x00a1;
            }
        L_0x00a1:
            super.setProperty(r5, r6, r7)
            goto L_0x0157
        L_0x00a6:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGDefsManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGDefsManagerInterface) r6
            if (r7 != 0) goto L_0x00ad
            goto L_0x00b3
        L_0x00ad:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r0 = r7.booleanValue()
        L_0x00b3:
            r6.setResponsible(r5, r0)
            goto L_0x0157
        L_0x00b8:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGDefsManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGDefsManagerInterface) r6
            if (r7 != 0) goto L_0x00bf
            goto L_0x00c2
        L_0x00bf:
            r1 = r7
            java.lang.String r1 = (java.lang.String) r1
        L_0x00c2:
            r6.setDisplay(r5, r1)
            goto L_0x0157
        L_0x00c7:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGDefsManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGDefsManagerInterface) r6
            if (r7 != 0) goto L_0x00ce
            goto L_0x00d4
        L_0x00ce:
            java.lang.Double r7 = (java.lang.Double) r7
            int r0 = r7.intValue()
        L_0x00d4:
            r6.setClipRule(r5, r0)
            goto L_0x0157
        L_0x00d9:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGDefsManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGDefsManagerInterface) r6
            if (r7 != 0) goto L_0x00e0
            goto L_0x00e3
        L_0x00e0:
            r1 = r7
            java.lang.String r1 = (java.lang.String) r1
        L_0x00e3:
            r6.setClipPath(r5, r1)
            goto L_0x0157
        L_0x00e8:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGDefsManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGDefsManagerInterface) r6
            if (r7 != 0) goto L_0x00ef
            goto L_0x00f2
        L_0x00ef:
            r1 = r7
            java.lang.String r1 = (java.lang.String) r1
        L_0x00f2:
            r6.setMarkerStart(r5, r1)
            goto L_0x0157
        L_0x00f7:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGDefsManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGDefsManagerInterface) r6
            if (r7 != 0) goto L_0x00fe
            goto L_0x0101
        L_0x00fe:
            r1 = r7
            java.lang.String r1 = (java.lang.String) r1
        L_0x0101:
            r6.setName(r5, r1)
            goto L_0x0157
        L_0x0105:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGDefsManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGDefsManagerInterface) r6
            if (r7 != 0) goto L_0x010c
            goto L_0x010f
        L_0x010c:
            r1 = r7
            java.lang.String r1 = (java.lang.String) r1
        L_0x010f:
            r6.setMask(r5, r1)
            goto L_0x0157
        L_0x0113:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGDefsManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGDefsManagerInterface) r6
            if (r7 != 0) goto L_0x011a
            goto L_0x011d
        L_0x011a:
            r1 = r7
            java.lang.String r1 = (java.lang.String) r1
        L_0x011d:
            r6.setPointerEvents(r5, r1)
            goto L_0x0157
        L_0x0121:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGDefsManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGDefsManagerInterface) r6
            if (r7 != 0) goto L_0x0128
            goto L_0x012b
        L_0x0128:
            r1 = r7
            java.lang.String r1 = (java.lang.String) r1
        L_0x012b:
            r6.setMarkerMid(r5, r1)
            goto L_0x0157
        L_0x012f:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGDefsManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGDefsManagerInterface) r6
            if (r7 != 0) goto L_0x0136
            goto L_0x0139
        L_0x0136:
            r1 = r7
            java.lang.String r1 = (java.lang.String) r1
        L_0x0139:
            r6.setMarkerEnd(r5, r1)
            goto L_0x0157
        L_0x013d:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNSVGDefsManagerInterface r6 = (com.facebook.react.viewmanagers.RNSVGDefsManagerInterface) r6
            com.facebook.react.bridge.ReadableArray r7 = (com.facebook.react.bridge.ReadableArray) r7
            r6.setMatrix(r5, r7)
            goto L_0x0157
        L_0x0147:
            U r6 = r4.mViewManager
            if (r7 != 0) goto L_0x014e
            r7 = 1065353216(0x3f800000, float:1.0)
            goto L_0x0154
        L_0x014e:
            java.lang.Double r7 = (java.lang.Double) r7
            float r7 = r7.floatValue()
        L_0x0154:
            r6.setOpacity(r5, r7)
        L_0x0157:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSVGDefsManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
