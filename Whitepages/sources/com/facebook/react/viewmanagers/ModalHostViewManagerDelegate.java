package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.ModalHostViewManagerInterface;

public class ModalHostViewManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & ModalHostViewManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public ModalHostViewManagerDelegate(U u) {
        super(u);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v13, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v15, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v16, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v17, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v18, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v19, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v20, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v21, resolved type: boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setProperty(T r4, java.lang.String r5, java.lang.Object r6) {
        /*
            r3 = this;
            r5.hashCode()
            r0 = 0
            r1 = -1
            int r2 = r5.hashCode()
            switch(r2) {
                case -1851617609: goto L_0x0077;
                case -1850124175: goto L_0x006c;
                case -1726194350: goto L_0x0061;
                case -1618432855: goto L_0x0056;
                case -1156137512: goto L_0x004b;
                case -795203165: goto L_0x0040;
                case 466743410: goto L_0x0035;
                case 1116050554: goto L_0x002a;
                case 1195991583: goto L_0x001c;
                case 2031205598: goto L_0x000e;
                default: goto L_0x000c;
            }
        L_0x000c:
            goto L_0x0081
        L_0x000e:
            java.lang.String r2 = "animationType"
            boolean r2 = r5.equals(r2)
            if (r2 != 0) goto L_0x0018
            goto L_0x0081
        L_0x0018:
            r1 = 9
            goto L_0x0081
        L_0x001c:
            java.lang.String r2 = "hardwareAccelerated"
            boolean r2 = r5.equals(r2)
            if (r2 != 0) goto L_0x0026
            goto L_0x0081
        L_0x0026:
            r1 = 8
            goto L_0x0081
        L_0x002a:
            java.lang.String r2 = "navigationBarTranslucent"
            boolean r2 = r5.equals(r2)
            if (r2 != 0) goto L_0x0033
            goto L_0x0081
        L_0x0033:
            r1 = 7
            goto L_0x0081
        L_0x0035:
            java.lang.String r2 = "visible"
            boolean r2 = r5.equals(r2)
            if (r2 != 0) goto L_0x003e
            goto L_0x0081
        L_0x003e:
            r1 = 6
            goto L_0x0081
        L_0x0040:
            java.lang.String r2 = "animated"
            boolean r2 = r5.equals(r2)
            if (r2 != 0) goto L_0x0049
            goto L_0x0081
        L_0x0049:
            r1 = 5
            goto L_0x0081
        L_0x004b:
            java.lang.String r2 = "statusBarTranslucent"
            boolean r2 = r5.equals(r2)
            if (r2 != 0) goto L_0x0054
            goto L_0x0081
        L_0x0054:
            r1 = 4
            goto L_0x0081
        L_0x0056:
            java.lang.String r2 = "identifier"
            boolean r2 = r5.equals(r2)
            if (r2 != 0) goto L_0x005f
            goto L_0x0081
        L_0x005f:
            r1 = 3
            goto L_0x0081
        L_0x0061:
            java.lang.String r2 = "transparent"
            boolean r2 = r5.equals(r2)
            if (r2 != 0) goto L_0x006a
            goto L_0x0081
        L_0x006a:
            r1 = 2
            goto L_0x0081
        L_0x006c:
            java.lang.String r2 = "supportedOrientations"
            boolean r2 = r5.equals(r2)
            if (r2 != 0) goto L_0x0075
            goto L_0x0081
        L_0x0075:
            r1 = 1
            goto L_0x0081
        L_0x0077:
            java.lang.String r2 = "presentationStyle"
            boolean r2 = r5.equals(r2)
            if (r2 != 0) goto L_0x0080
            goto L_0x0081
        L_0x0080:
            r1 = r0
        L_0x0081:
            switch(r1) {
                case 0: goto L_0x0117;
                case 1: goto L_0x010d;
                case 2: goto L_0x00fc;
                case 3: goto L_0x00eb;
                case 4: goto L_0x00da;
                case 5: goto L_0x00c9;
                case 6: goto L_0x00b8;
                case 7: goto L_0x00a6;
                case 8: goto L_0x0094;
                case 9: goto L_0x0089;
                default: goto L_0x0084;
            }
        L_0x0084:
            super.setProperty(r4, r5, r6)
            goto L_0x0120
        L_0x0089:
            U r5 = r3.mViewManager
            com.facebook.react.viewmanagers.ModalHostViewManagerInterface r5 = (com.facebook.react.viewmanagers.ModalHostViewManagerInterface) r5
            java.lang.String r6 = (java.lang.String) r6
            r5.setAnimationType(r4, r6)
            goto L_0x0120
        L_0x0094:
            U r5 = r3.mViewManager
            com.facebook.react.viewmanagers.ModalHostViewManagerInterface r5 = (com.facebook.react.viewmanagers.ModalHostViewManagerInterface) r5
            if (r6 != 0) goto L_0x009b
            goto L_0x00a1
        L_0x009b:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r0 = r6.booleanValue()
        L_0x00a1:
            r5.setHardwareAccelerated(r4, r0)
            goto L_0x0120
        L_0x00a6:
            U r5 = r3.mViewManager
            com.facebook.react.viewmanagers.ModalHostViewManagerInterface r5 = (com.facebook.react.viewmanagers.ModalHostViewManagerInterface) r5
            if (r6 != 0) goto L_0x00ad
            goto L_0x00b3
        L_0x00ad:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r0 = r6.booleanValue()
        L_0x00b3:
            r5.setNavigationBarTranslucent(r4, r0)
            goto L_0x0120
        L_0x00b8:
            U r5 = r3.mViewManager
            com.facebook.react.viewmanagers.ModalHostViewManagerInterface r5 = (com.facebook.react.viewmanagers.ModalHostViewManagerInterface) r5
            if (r6 != 0) goto L_0x00bf
            goto L_0x00c5
        L_0x00bf:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r0 = r6.booleanValue()
        L_0x00c5:
            r5.setVisible(r4, r0)
            goto L_0x0120
        L_0x00c9:
            U r5 = r3.mViewManager
            com.facebook.react.viewmanagers.ModalHostViewManagerInterface r5 = (com.facebook.react.viewmanagers.ModalHostViewManagerInterface) r5
            if (r6 != 0) goto L_0x00d0
            goto L_0x00d6
        L_0x00d0:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r0 = r6.booleanValue()
        L_0x00d6:
            r5.setAnimated(r4, r0)
            goto L_0x0120
        L_0x00da:
            U r5 = r3.mViewManager
            com.facebook.react.viewmanagers.ModalHostViewManagerInterface r5 = (com.facebook.react.viewmanagers.ModalHostViewManagerInterface) r5
            if (r6 != 0) goto L_0x00e1
            goto L_0x00e7
        L_0x00e1:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r0 = r6.booleanValue()
        L_0x00e7:
            r5.setStatusBarTranslucent(r4, r0)
            goto L_0x0120
        L_0x00eb:
            U r5 = r3.mViewManager
            com.facebook.react.viewmanagers.ModalHostViewManagerInterface r5 = (com.facebook.react.viewmanagers.ModalHostViewManagerInterface) r5
            if (r6 != 0) goto L_0x00f2
            goto L_0x00f8
        L_0x00f2:
            java.lang.Double r6 = (java.lang.Double) r6
            int r0 = r6.intValue()
        L_0x00f8:
            r5.setIdentifier(r4, r0)
            goto L_0x0120
        L_0x00fc:
            U r5 = r3.mViewManager
            com.facebook.react.viewmanagers.ModalHostViewManagerInterface r5 = (com.facebook.react.viewmanagers.ModalHostViewManagerInterface) r5
            if (r6 != 0) goto L_0x0103
            goto L_0x0109
        L_0x0103:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r0 = r6.booleanValue()
        L_0x0109:
            r5.setTransparent(r4, r0)
            goto L_0x0120
        L_0x010d:
            U r5 = r3.mViewManager
            com.facebook.react.viewmanagers.ModalHostViewManagerInterface r5 = (com.facebook.react.viewmanagers.ModalHostViewManagerInterface) r5
            com.facebook.react.bridge.ReadableArray r6 = (com.facebook.react.bridge.ReadableArray) r6
            r5.setSupportedOrientations(r4, r6)
            goto L_0x0120
        L_0x0117:
            U r5 = r3.mViewManager
            com.facebook.react.viewmanagers.ModalHostViewManagerInterface r5 = (com.facebook.react.viewmanagers.ModalHostViewManagerInterface) r5
            java.lang.String r6 = (java.lang.String) r6
            r5.setPresentationStyle(r4, r6)
        L_0x0120:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.ModalHostViewManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
