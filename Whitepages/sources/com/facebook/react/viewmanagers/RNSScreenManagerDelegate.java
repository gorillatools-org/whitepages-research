package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSScreenManagerInterface;

public class RNSScreenManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSScreenManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSScreenManagerDelegate(U u) {
        super(u);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v9, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v10, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v11, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v13, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v15, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v17, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v19, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v21, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v23, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v25, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v27, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v28, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v29, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v30, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v31, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v32, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v33, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v34, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v35, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v36, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v37, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v38, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v39, resolved type: boolean} */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setProperty(T r8, java.lang.String r9, java.lang.Object r10) {
        /*
            r7 = this;
            r0 = 24
            r9.hashCode()
            r1 = -1082130432(0xffffffffbf800000, float:-1.0)
            r2 = 1
            r3 = 0
            r4 = 0
            r5 = -1
            int r6 = r9.hashCode()
            switch(r6) {
                case -1937389126: goto L_0x01a0;
                case -1853558344: goto L_0x0194;
                case -1734097646: goto L_0x0188;
                case -1349152186: goto L_0x017c;
                case -1322084375: goto L_0x0170;
                case -1156137512: goto L_0x0164;
                case -1150711358: goto L_0x0158;
                case -1047235902: goto L_0x014c;
                case -973702878: goto L_0x013e;
                case -958765200: goto L_0x0130;
                case -952227806: goto L_0x0122;
                case -577711652: goto L_0x0114;
                case -462720700: goto L_0x0106;
                case -381571779: goto L_0x00f8;
                case -274098190: goto L_0x00ea;
                case -257141968: goto L_0x00dc;
                case -166356101: goto L_0x00ce;
                case 17337291: goto L_0x00c0;
                case 129956386: goto L_0x00b2;
                case 187703999: goto L_0x00a4;
                case 227582404: goto L_0x0096;
                case 241896530: goto L_0x0089;
                case 425064969: goto L_0x007c;
                case 1082157413: goto L_0x006f;
                case 1110843912: goto L_0x0063;
                case 1116050554: goto L_0x0056;
                case 1269009342: goto L_0x0049;
                case 1357942638: goto L_0x003c;
                case 1387359683: goto L_0x002f;
                case 1729091548: goto L_0x0022;
                case 2097450072: goto L_0x0015;
                default: goto L_0x0012;
            }
        L_0x0012:
            r6 = r5
            goto L_0x01ab
        L_0x0015:
            java.lang.String r6 = "sheetExpandsWhenScrolledToEdge"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x001e
            goto L_0x0012
        L_0x001e:
            r6 = 30
            goto L_0x01ab
        L_0x0022:
            java.lang.String r6 = "nativeBackButtonDismissalEnabled"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x002b
            goto L_0x0012
        L_0x002b:
            r6 = 29
            goto L_0x01ab
        L_0x002f:
            java.lang.String r6 = "statusBarAnimation"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0038
            goto L_0x0012
        L_0x0038:
            r6 = 28
            goto L_0x01ab
        L_0x003c:
            java.lang.String r6 = "sheetGrabberVisible"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0045
            goto L_0x0012
        L_0x0045:
            r6 = 27
            goto L_0x01ab
        L_0x0049:
            java.lang.String r6 = "sheetElevation"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0052
            goto L_0x0012
        L_0x0052:
            r6 = 26
            goto L_0x01ab
        L_0x0056:
            java.lang.String r6 = "navigationBarTranslucent"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x005f
            goto L_0x0012
        L_0x005f:
            r6 = 25
            goto L_0x01ab
        L_0x0063:
            java.lang.String r6 = "customAnimationOnSwipe"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x006c
            goto L_0x0012
        L_0x006c:
            r6 = r0
            goto L_0x01ab
        L_0x006f:
            java.lang.String r6 = "swipeDirection"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0078
            goto L_0x0012
        L_0x0078:
            r6 = 23
            goto L_0x01ab
        L_0x007c:
            java.lang.String r6 = "transitionDuration"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0085
            goto L_0x0012
        L_0x0085:
            r6 = 22
            goto L_0x01ab
        L_0x0089:
            java.lang.String r6 = "sheetLargestUndimmedDetent"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0092
            goto L_0x0012
        L_0x0092:
            r6 = 21
            goto L_0x01ab
        L_0x0096:
            java.lang.String r6 = "screenOrientation"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00a0
            goto L_0x0012
        L_0x00a0:
            r6 = 20
            goto L_0x01ab
        L_0x00a4:
            java.lang.String r6 = "gestureResponseDistance"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00ae
            goto L_0x0012
        L_0x00ae:
            r6 = 19
            goto L_0x01ab
        L_0x00b2:
            java.lang.String r6 = "fullScreenSwipeEnabled"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00bc
            goto L_0x0012
        L_0x00bc:
            r6 = 18
            goto L_0x01ab
        L_0x00c0:
            java.lang.String r6 = "statusBarHidden"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00ca
            goto L_0x0012
        L_0x00ca:
            r6 = 17
            goto L_0x01ab
        L_0x00ce:
            java.lang.String r6 = "preventNativeDismiss"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00d8
            goto L_0x0012
        L_0x00d8:
            r6 = 16
            goto L_0x01ab
        L_0x00dc:
            java.lang.String r6 = "replaceAnimation"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00e6
            goto L_0x0012
        L_0x00e6:
            r6 = 15
            goto L_0x01ab
        L_0x00ea:
            java.lang.String r6 = "sheetAllowedDetents"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x00f4
            goto L_0x0012
        L_0x00f4:
            r6 = 14
            goto L_0x01ab
        L_0x00f8:
            java.lang.String r6 = "sheetInitialDetent"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0102
            goto L_0x0012
        L_0x0102:
            r6 = 13
            goto L_0x01ab
        L_0x0106:
            java.lang.String r6 = "navigationBarColor"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0110
            goto L_0x0012
        L_0x0110:
            r6 = 12
            goto L_0x01ab
        L_0x0114:
            java.lang.String r6 = "stackAnimation"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x011e
            goto L_0x0012
        L_0x011e:
            r6 = 11
            goto L_0x01ab
        L_0x0122:
            java.lang.String r6 = "fullScreenSwipeShadowEnabled"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x012c
            goto L_0x0012
        L_0x012c:
            r6 = 10
            goto L_0x01ab
        L_0x0130:
            java.lang.String r6 = "statusBarStyle"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x013a
            goto L_0x0012
        L_0x013a:
            r6 = 9
            goto L_0x01ab
        L_0x013e:
            java.lang.String r6 = "statusBarColor"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0148
            goto L_0x0012
        L_0x0148:
            r6 = 8
            goto L_0x01ab
        L_0x014c:
            java.lang.String r6 = "activityState"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0156
            goto L_0x0012
        L_0x0156:
            r6 = 7
            goto L_0x01ab
        L_0x0158:
            java.lang.String r6 = "stackPresentation"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0162
            goto L_0x0012
        L_0x0162:
            r6 = 6
            goto L_0x01ab
        L_0x0164:
            java.lang.String r6 = "statusBarTranslucent"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x016e
            goto L_0x0012
        L_0x016e:
            r6 = 5
            goto L_0x01ab
        L_0x0170:
            java.lang.String r6 = "navigationBarHidden"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x017a
            goto L_0x0012
        L_0x017a:
            r6 = 4
            goto L_0x01ab
        L_0x017c:
            java.lang.String r6 = "sheetCornerRadius"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0186
            goto L_0x0012
        L_0x0186:
            r6 = 3
            goto L_0x01ab
        L_0x0188:
            java.lang.String r6 = "hideKeyboardOnSwipe"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x0192
            goto L_0x0012
        L_0x0192:
            r6 = 2
            goto L_0x01ab
        L_0x0194:
            java.lang.String r6 = "gestureEnabled"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x019e
            goto L_0x0012
        L_0x019e:
            r6 = r2
            goto L_0x01ab
        L_0x01a0:
            java.lang.String r6 = "homeIndicatorHidden"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x01aa
            goto L_0x0012
        L_0x01aa:
            r6 = r4
        L_0x01ab:
            switch(r6) {
                case 0: goto L_0x0397;
                case 1: goto L_0x0386;
                case 2: goto L_0x0375;
                case 3: goto L_0x0364;
                case 4: goto L_0x0353;
                case 5: goto L_0x0342;
                case 6: goto L_0x0337;
                case 7: goto L_0x0325;
                case 8: goto L_0x0314;
                case 9: goto L_0x0305;
                case 10: goto L_0x02f3;
                case 11: goto L_0x02e8;
                case 12: goto L_0x02d7;
                case 13: goto L_0x02c5;
                case 14: goto L_0x02ba;
                case 15: goto L_0x02af;
                case 16: goto L_0x029d;
                case 17: goto L_0x028b;
                case 18: goto L_0x0279;
                case 19: goto L_0x026e;
                case 20: goto L_0x025f;
                case 21: goto L_0x024d;
                case 22: goto L_0x0239;
                case 23: goto L_0x022e;
                case 24: goto L_0x021c;
                case 25: goto L_0x020a;
                case 26: goto L_0x01f8;
                case 27: goto L_0x01e6;
                case 28: goto L_0x01d7;
                case 29: goto L_0x01c5;
                case 30: goto L_0x01b3;
                default: goto L_0x01ae;
            }
        L_0x01ae:
            super.setProperty(r8, r9, r10)
            goto L_0x03a7
        L_0x01b3:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSScreenManagerInterface r9 = (com.facebook.react.viewmanagers.RNSScreenManagerInterface) r9
            if (r10 != 0) goto L_0x01ba
            goto L_0x01c0
        L_0x01ba:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r4 = r10.booleanValue()
        L_0x01c0:
            r9.setSheetExpandsWhenScrolledToEdge(r8, r4)
            goto L_0x03a7
        L_0x01c5:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSScreenManagerInterface r9 = (com.facebook.react.viewmanagers.RNSScreenManagerInterface) r9
            if (r10 != 0) goto L_0x01cc
            goto L_0x01d2
        L_0x01cc:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r4 = r10.booleanValue()
        L_0x01d2:
            r9.setNativeBackButtonDismissalEnabled(r8, r4)
            goto L_0x03a7
        L_0x01d7:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSScreenManagerInterface r9 = (com.facebook.react.viewmanagers.RNSScreenManagerInterface) r9
            if (r10 != 0) goto L_0x01de
            goto L_0x01e1
        L_0x01de:
            r3 = r10
            java.lang.String r3 = (java.lang.String) r3
        L_0x01e1:
            r9.setStatusBarAnimation(r8, r3)
            goto L_0x03a7
        L_0x01e6:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSScreenManagerInterface r9 = (com.facebook.react.viewmanagers.RNSScreenManagerInterface) r9
            if (r10 != 0) goto L_0x01ed
            goto L_0x01f3
        L_0x01ed:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r4 = r10.booleanValue()
        L_0x01f3:
            r9.setSheetGrabberVisible(r8, r4)
            goto L_0x03a7
        L_0x01f8:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSScreenManagerInterface r9 = (com.facebook.react.viewmanagers.RNSScreenManagerInterface) r9
            if (r10 != 0) goto L_0x01ff
            goto L_0x0205
        L_0x01ff:
            java.lang.Double r10 = (java.lang.Double) r10
            int r0 = r10.intValue()
        L_0x0205:
            r9.setSheetElevation(r8, r0)
            goto L_0x03a7
        L_0x020a:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSScreenManagerInterface r9 = (com.facebook.react.viewmanagers.RNSScreenManagerInterface) r9
            if (r10 != 0) goto L_0x0211
            goto L_0x0217
        L_0x0211:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r4 = r10.booleanValue()
        L_0x0217:
            r9.setNavigationBarTranslucent(r8, r4)
            goto L_0x03a7
        L_0x021c:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSScreenManagerInterface r9 = (com.facebook.react.viewmanagers.RNSScreenManagerInterface) r9
            if (r10 != 0) goto L_0x0223
            goto L_0x0229
        L_0x0223:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r4 = r10.booleanValue()
        L_0x0229:
            r9.setCustomAnimationOnSwipe(r8, r4)
            goto L_0x03a7
        L_0x022e:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSScreenManagerInterface r9 = (com.facebook.react.viewmanagers.RNSScreenManagerInterface) r9
            java.lang.String r10 = (java.lang.String) r10
            r9.setSwipeDirection(r8, r10)
            goto L_0x03a7
        L_0x0239:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSScreenManagerInterface r9 = (com.facebook.react.viewmanagers.RNSScreenManagerInterface) r9
            if (r10 != 0) goto L_0x0242
            r10 = 500(0x1f4, float:7.0E-43)
            goto L_0x0248
        L_0x0242:
            java.lang.Double r10 = (java.lang.Double) r10
            int r10 = r10.intValue()
        L_0x0248:
            r9.setTransitionDuration(r8, r10)
            goto L_0x03a7
        L_0x024d:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSScreenManagerInterface r9 = (com.facebook.react.viewmanagers.RNSScreenManagerInterface) r9
            if (r10 != 0) goto L_0x0254
            goto L_0x025a
        L_0x0254:
            java.lang.Double r10 = (java.lang.Double) r10
            int r5 = r10.intValue()
        L_0x025a:
            r9.setSheetLargestUndimmedDetent(r8, r5)
            goto L_0x03a7
        L_0x025f:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSScreenManagerInterface r9 = (com.facebook.react.viewmanagers.RNSScreenManagerInterface) r9
            if (r10 != 0) goto L_0x0266
            goto L_0x0269
        L_0x0266:
            r3 = r10
            java.lang.String r3 = (java.lang.String) r3
        L_0x0269:
            r9.setScreenOrientation(r8, r3)
            goto L_0x03a7
        L_0x026e:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSScreenManagerInterface r9 = (com.facebook.react.viewmanagers.RNSScreenManagerInterface) r9
            com.facebook.react.bridge.ReadableMap r10 = (com.facebook.react.bridge.ReadableMap) r10
            r9.setGestureResponseDistance(r8, r10)
            goto L_0x03a7
        L_0x0279:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSScreenManagerInterface r9 = (com.facebook.react.viewmanagers.RNSScreenManagerInterface) r9
            if (r10 != 0) goto L_0x0280
            goto L_0x0286
        L_0x0280:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r4 = r10.booleanValue()
        L_0x0286:
            r9.setFullScreenSwipeEnabled(r8, r4)
            goto L_0x03a7
        L_0x028b:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSScreenManagerInterface r9 = (com.facebook.react.viewmanagers.RNSScreenManagerInterface) r9
            if (r10 != 0) goto L_0x0292
            goto L_0x0298
        L_0x0292:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r4 = r10.booleanValue()
        L_0x0298:
            r9.setStatusBarHidden(r8, r4)
            goto L_0x03a7
        L_0x029d:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSScreenManagerInterface r9 = (com.facebook.react.viewmanagers.RNSScreenManagerInterface) r9
            if (r10 != 0) goto L_0x02a4
            goto L_0x02aa
        L_0x02a4:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r4 = r10.booleanValue()
        L_0x02aa:
            r9.setPreventNativeDismiss(r8, r4)
            goto L_0x03a7
        L_0x02af:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSScreenManagerInterface r9 = (com.facebook.react.viewmanagers.RNSScreenManagerInterface) r9
            java.lang.String r10 = (java.lang.String) r10
            r9.setReplaceAnimation(r8, r10)
            goto L_0x03a7
        L_0x02ba:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSScreenManagerInterface r9 = (com.facebook.react.viewmanagers.RNSScreenManagerInterface) r9
            com.facebook.react.bridge.ReadableArray r10 = (com.facebook.react.bridge.ReadableArray) r10
            r9.setSheetAllowedDetents(r8, r10)
            goto L_0x03a7
        L_0x02c5:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSScreenManagerInterface r9 = (com.facebook.react.viewmanagers.RNSScreenManagerInterface) r9
            if (r10 != 0) goto L_0x02cc
            goto L_0x02d2
        L_0x02cc:
            java.lang.Double r10 = (java.lang.Double) r10
            int r4 = r10.intValue()
        L_0x02d2:
            r9.setSheetInitialDetent(r8, r4)
            goto L_0x03a7
        L_0x02d7:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSScreenManagerInterface r9 = (com.facebook.react.viewmanagers.RNSScreenManagerInterface) r9
            android.content.Context r0 = r8.getContext()
            java.lang.Integer r10 = com.facebook.react.bridge.ColorPropConverter.getColor(r10, r0)
            r9.setNavigationBarColor(r8, r10)
            goto L_0x03a7
        L_0x02e8:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSScreenManagerInterface r9 = (com.facebook.react.viewmanagers.RNSScreenManagerInterface) r9
            java.lang.String r10 = (java.lang.String) r10
            r9.setStackAnimation(r8, r10)
            goto L_0x03a7
        L_0x02f3:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSScreenManagerInterface r9 = (com.facebook.react.viewmanagers.RNSScreenManagerInterface) r9
            if (r10 != 0) goto L_0x02fa
            goto L_0x0300
        L_0x02fa:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r2 = r10.booleanValue()
        L_0x0300:
            r9.setFullScreenSwipeShadowEnabled(r8, r2)
            goto L_0x03a7
        L_0x0305:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSScreenManagerInterface r9 = (com.facebook.react.viewmanagers.RNSScreenManagerInterface) r9
            if (r10 != 0) goto L_0x030c
            goto L_0x030f
        L_0x030c:
            r3 = r10
            java.lang.String r3 = (java.lang.String) r3
        L_0x030f:
            r9.setStatusBarStyle(r8, r3)
            goto L_0x03a7
        L_0x0314:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSScreenManagerInterface r9 = (com.facebook.react.viewmanagers.RNSScreenManagerInterface) r9
            android.content.Context r0 = r8.getContext()
            java.lang.Integer r10 = com.facebook.react.bridge.ColorPropConverter.getColor(r10, r0)
            r9.setStatusBarColor(r8, r10)
            goto L_0x03a7
        L_0x0325:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSScreenManagerInterface r9 = (com.facebook.react.viewmanagers.RNSScreenManagerInterface) r9
            if (r10 != 0) goto L_0x032c
            goto L_0x0332
        L_0x032c:
            java.lang.Double r10 = (java.lang.Double) r10
            float r1 = r10.floatValue()
        L_0x0332:
            r9.setActivityState(r8, r1)
            goto L_0x03a7
        L_0x0337:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSScreenManagerInterface r9 = (com.facebook.react.viewmanagers.RNSScreenManagerInterface) r9
            java.lang.String r10 = (java.lang.String) r10
            r9.setStackPresentation(r8, r10)
            goto L_0x03a7
        L_0x0342:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSScreenManagerInterface r9 = (com.facebook.react.viewmanagers.RNSScreenManagerInterface) r9
            if (r10 != 0) goto L_0x0349
            goto L_0x034f
        L_0x0349:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r4 = r10.booleanValue()
        L_0x034f:
            r9.setStatusBarTranslucent(r8, r4)
            goto L_0x03a7
        L_0x0353:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSScreenManagerInterface r9 = (com.facebook.react.viewmanagers.RNSScreenManagerInterface) r9
            if (r10 != 0) goto L_0x035a
            goto L_0x0360
        L_0x035a:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r4 = r10.booleanValue()
        L_0x0360:
            r9.setNavigationBarHidden(r8, r4)
            goto L_0x03a7
        L_0x0364:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSScreenManagerInterface r9 = (com.facebook.react.viewmanagers.RNSScreenManagerInterface) r9
            if (r10 != 0) goto L_0x036b
            goto L_0x0371
        L_0x036b:
            java.lang.Double r10 = (java.lang.Double) r10
            float r1 = r10.floatValue()
        L_0x0371:
            r9.setSheetCornerRadius(r8, r1)
            goto L_0x03a7
        L_0x0375:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSScreenManagerInterface r9 = (com.facebook.react.viewmanagers.RNSScreenManagerInterface) r9
            if (r10 != 0) goto L_0x037c
            goto L_0x0382
        L_0x037c:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r4 = r10.booleanValue()
        L_0x0382:
            r9.setHideKeyboardOnSwipe(r8, r4)
            goto L_0x03a7
        L_0x0386:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSScreenManagerInterface r9 = (com.facebook.react.viewmanagers.RNSScreenManagerInterface) r9
            if (r10 != 0) goto L_0x038d
            goto L_0x0393
        L_0x038d:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r2 = r10.booleanValue()
        L_0x0393:
            r9.setGestureEnabled(r8, r2)
            goto L_0x03a7
        L_0x0397:
            U r9 = r7.mViewManager
            com.facebook.react.viewmanagers.RNSScreenManagerInterface r9 = (com.facebook.react.viewmanagers.RNSScreenManagerInterface) r9
            if (r10 != 0) goto L_0x039e
            goto L_0x03a4
        L_0x039e:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r4 = r10.booleanValue()
        L_0x03a4:
            r9.setHomeIndicatorHidden(r8, r4)
        L_0x03a7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSScreenManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
