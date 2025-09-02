package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface;

public class RNGestureHandlerButtonManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNGestureHandlerButtonManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNGestureHandlerButtonManagerDelegate(U u) {
        super(u);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v11, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v12, resolved type: boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setProperty(T r5, java.lang.String r6, java.lang.Object r7) {
        /*
            r4 = this;
            r6.hashCode()
            r0 = 1
            r1 = 0
            r2 = -1
            int r3 = r6.hashCode()
            switch(r3) {
                case -2143114526: goto L_0x0078;
                case -1609594047: goto L_0x006d;
                case -775297261: goto L_0x0062;
                case 722830999: goto L_0x0057;
                case 737768677: goto L_0x004c;
                case 741115130: goto L_0x0041;
                case 1387411372: goto L_0x0036;
                case 1686617758: goto L_0x002b;
                case 1825644485: goto L_0x001d;
                case 1984457027: goto L_0x000f;
                default: goto L_0x000d;
            }
        L_0x000d:
            goto L_0x0082
        L_0x000f:
            java.lang.String r3 = "foreground"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x0019
            goto L_0x0082
        L_0x0019:
            r2 = 9
            goto L_0x0082
        L_0x001d:
            java.lang.String r3 = "borderless"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x0027
            goto L_0x0082
        L_0x0027:
            r2 = 8
            goto L_0x0082
        L_0x002b:
            java.lang.String r3 = "exclusive"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x0034
            goto L_0x0082
        L_0x0034:
            r2 = 7
            goto L_0x0082
        L_0x0036:
            java.lang.String r3 = "touchSoundDisabled"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x003f
            goto L_0x0082
        L_0x003f:
            r2 = 6
            goto L_0x0082
        L_0x0041:
            java.lang.String r3 = "borderWidth"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x004a
            goto L_0x0082
        L_0x004a:
            r2 = 5
            goto L_0x0082
        L_0x004c:
            java.lang.String r3 = "borderStyle"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x0055
            goto L_0x0082
        L_0x0055:
            r2 = 4
            goto L_0x0082
        L_0x0057:
            java.lang.String r3 = "borderColor"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x0060
            goto L_0x0082
        L_0x0060:
            r2 = 3
            goto L_0x0082
        L_0x0062:
            java.lang.String r3 = "rippleColor"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x006b
            goto L_0x0082
        L_0x006b:
            r2 = 2
            goto L_0x0082
        L_0x006d:
            java.lang.String r3 = "enabled"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x0076
            goto L_0x0082
        L_0x0076:
            r2 = r0
            goto L_0x0082
        L_0x0078:
            java.lang.String r3 = "rippleRadius"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x0081
            goto L_0x0082
        L_0x0081:
            r2 = r1
        L_0x0082:
            switch(r2) {
                case 0: goto L_0x0123;
                case 1: goto L_0x0112;
                case 2: goto L_0x0102;
                case 3: goto L_0x00f2;
                case 4: goto L_0x00e3;
                case 5: goto L_0x00d1;
                case 6: goto L_0x00c0;
                case 7: goto L_0x00ae;
                case 8: goto L_0x009c;
                case 9: goto L_0x008a;
                default: goto L_0x0085;
            }
        L_0x0085:
            super.setProperty(r5, r6, r7)
            goto L_0x0133
        L_0x008a:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface r6 = (com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface) r6
            if (r7 != 0) goto L_0x0091
            goto L_0x0097
        L_0x0091:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r1 = r7.booleanValue()
        L_0x0097:
            r6.setForeground(r5, r1)
            goto L_0x0133
        L_0x009c:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface r6 = (com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface) r6
            if (r7 != 0) goto L_0x00a3
            goto L_0x00a9
        L_0x00a3:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r1 = r7.booleanValue()
        L_0x00a9:
            r6.setBorderless(r5, r1)
            goto L_0x0133
        L_0x00ae:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface r6 = (com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface) r6
            if (r7 != 0) goto L_0x00b5
            goto L_0x00bb
        L_0x00b5:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r0 = r7.booleanValue()
        L_0x00bb:
            r6.setExclusive(r5, r0)
            goto L_0x0133
        L_0x00c0:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface r6 = (com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface) r6
            if (r7 != 0) goto L_0x00c7
            goto L_0x00cd
        L_0x00c7:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r1 = r7.booleanValue()
        L_0x00cd:
            r6.setTouchSoundDisabled(r5, r1)
            goto L_0x0133
        L_0x00d1:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface r6 = (com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface) r6
            if (r7 != 0) goto L_0x00d9
            r7 = 0
            goto L_0x00df
        L_0x00d9:
            java.lang.Double r7 = (java.lang.Double) r7
            float r7 = r7.floatValue()
        L_0x00df:
            r6.setBorderWidth(r5, r7)
            goto L_0x0133
        L_0x00e3:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface r6 = (com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface) r6
            if (r7 != 0) goto L_0x00ec
            java.lang.String r7 = "solid"
            goto L_0x00ee
        L_0x00ec:
            java.lang.String r7 = (java.lang.String) r7
        L_0x00ee:
            r6.setBorderStyle(r5, r7)
            goto L_0x0133
        L_0x00f2:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface r6 = (com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface) r6
            android.content.Context r0 = r5.getContext()
            java.lang.Integer r7 = com.facebook.react.bridge.ColorPropConverter.getColor(r7, r0)
            r6.setBorderColor(r5, r7)
            goto L_0x0133
        L_0x0102:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface r6 = (com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface) r6
            android.content.Context r0 = r5.getContext()
            java.lang.Integer r7 = com.facebook.react.bridge.ColorPropConverter.getColor(r7, r0)
            r6.setRippleColor(r5, r7)
            goto L_0x0133
        L_0x0112:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface r6 = (com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface) r6
            if (r7 != 0) goto L_0x0119
            goto L_0x011f
        L_0x0119:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r0 = r7.booleanValue()
        L_0x011f:
            r6.setEnabled(r5, r0)
            goto L_0x0133
        L_0x0123:
            U r6 = r4.mViewManager
            com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface r6 = (com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface) r6
            if (r7 != 0) goto L_0x012a
            goto L_0x0130
        L_0x012a:
            java.lang.Double r7 = (java.lang.Double) r7
            int r1 = r7.intValue()
        L_0x0130:
            r6.setRippleRadius(r5, r1)
        L_0x0133:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
