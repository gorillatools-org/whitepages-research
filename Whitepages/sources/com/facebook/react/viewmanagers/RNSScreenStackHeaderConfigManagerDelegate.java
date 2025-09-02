package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface;

public class RNSScreenStackHeaderConfigManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSScreenStackHeaderConfigManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSScreenStackHeaderConfigManagerDelegate(U u) {
        super(u);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v12, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v13, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v15, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v17, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v18, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v19, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v21, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v23, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v25, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v26, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v27, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v28, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v29, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v30, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v31, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v32, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v33, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v34, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v35, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v36, resolved type: boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setProperty(T r6, java.lang.String r7, java.lang.Object r8) {
        /*
            r5 = this;
            r0 = 1
            r7.hashCode()
            r1 = 0
            r2 = 0
            r3 = -1
            int r4 = r7.hashCode()
            switch(r4) {
                case -1822687399: goto L_0x0175;
                case -1799367701: goto L_0x016a;
                case -1795707688: goto L_0x015f;
                case -1774658170: goto L_0x0154;
                case -1715368693: goto L_0x0149;
                case -1503810304: goto L_0x013e;
                case -1225100257: goto L_0x0133;
                case -1217487446: goto L_0x0128;
                case -1094575123: goto L_0x011a;
                case -1093089076: goto L_0x010c;
                case -1063138943: goto L_0x00fe;
                case -962590849: goto L_0x00f0;
                case -389245640: goto L_0x00e2;
                case -140063148: goto L_0x00d4;
                case 347216: goto L_0x00c6;
                case 94842723: goto L_0x00b8;
                case 110371416: goto L_0x00aa;
                case 183888321: goto L_0x009c;
                case 243070244: goto L_0x008e;
                case 339462402: goto L_0x0080;
                case 490615652: goto L_0x0072;
                case 1038753243: goto L_0x0064;
                case 1287124693: goto L_0x0056;
                case 1324688817: goto L_0x0048;
                case 1518161768: goto L_0x003a;
                case 1564506303: goto L_0x002c;
                case 2029798365: goto L_0x001e;
                case 2099541337: goto L_0x0010;
                default: goto L_0x000e;
            }
        L_0x000e:
            goto L_0x017f
        L_0x0010:
            java.lang.String r4 = "topInsetEnabled"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x001a
            goto L_0x017f
        L_0x001a:
            r3 = 27
            goto L_0x017f
        L_0x001e:
            java.lang.String r4 = "largeTitle"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0028
            goto L_0x017f
        L_0x0028:
            r3 = 26
            goto L_0x017f
        L_0x002c:
            java.lang.String r4 = "largeTitleHideShadow"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0036
            goto L_0x017f
        L_0x0036:
            r3 = 25
            goto L_0x017f
        L_0x003a:
            java.lang.String r4 = "titleFontSize"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0044
            goto L_0x017f
        L_0x0044:
            r3 = 24
            goto L_0x017f
        L_0x0048:
            java.lang.String r4 = "backTitle"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0052
            goto L_0x017f
        L_0x0052:
            r3 = 23
            goto L_0x017f
        L_0x0056:
            java.lang.String r4 = "backgroundColor"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0060
            goto L_0x017f
        L_0x0060:
            r3 = 22
            goto L_0x017f
        L_0x0064:
            java.lang.String r4 = "hideBackButton"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x006e
            goto L_0x017f
        L_0x006e:
            r3 = 21
            goto L_0x017f
        L_0x0072:
            java.lang.String r4 = "largeTitleFontWeight"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x007c
            goto L_0x017f
        L_0x007c:
            r3 = 20
            goto L_0x017f
        L_0x0080:
            java.lang.String r4 = "hideShadow"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x008a
            goto L_0x017f
        L_0x008a:
            r3 = 19
            goto L_0x017f
        L_0x008e:
            java.lang.String r4 = "backTitleFontFamily"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0098
            goto L_0x017f
        L_0x0098:
            r3 = 18
            goto L_0x017f
        L_0x009c:
            java.lang.String r4 = "backTitleFontSize"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x00a6
            goto L_0x017f
        L_0x00a6:
            r3 = 17
            goto L_0x017f
        L_0x00aa:
            java.lang.String r4 = "title"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x00b4
            goto L_0x017f
        L_0x00b4:
            r3 = 16
            goto L_0x017f
        L_0x00b8:
            java.lang.String r4 = "color"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x00c2
            goto L_0x017f
        L_0x00c2:
            r3 = 15
            goto L_0x017f
        L_0x00c6:
            java.lang.String r4 = "largeTitleFontFamily"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x00d0
            goto L_0x017f
        L_0x00d0:
            r3 = 14
            goto L_0x017f
        L_0x00d4:
            java.lang.String r4 = "backButtonInCustomView"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x00de
            goto L_0x017f
        L_0x00de:
            r3 = 13
            goto L_0x017f
        L_0x00e2:
            java.lang.String r4 = "largeTitleBackgroundColor"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x00ec
            goto L_0x017f
        L_0x00ec:
            r3 = 12
            goto L_0x017f
        L_0x00f0:
            java.lang.String r4 = "direction"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x00fa
            goto L_0x017f
        L_0x00fa:
            r3 = 11
            goto L_0x017f
        L_0x00fe:
            java.lang.String r4 = "backTitleVisible"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0108
            goto L_0x017f
        L_0x0108:
            r3 = 10
            goto L_0x017f
        L_0x010c:
            java.lang.String r4 = "backButtonDisplayMode"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0116
            goto L_0x017f
        L_0x0116:
            r3 = 9
            goto L_0x017f
        L_0x011a:
            java.lang.String r4 = "largeTitleFontSize"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0124
            goto L_0x017f
        L_0x0124:
            r3 = 8
            goto L_0x017f
        L_0x0128:
            java.lang.String r4 = "hidden"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0131
            goto L_0x017f
        L_0x0131:
            r3 = 7
            goto L_0x017f
        L_0x0133:
            java.lang.String r4 = "titleFontWeight"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x013c
            goto L_0x017f
        L_0x013c:
            r3 = 6
            goto L_0x017f
        L_0x013e:
            java.lang.String r4 = "disableBackButtonMenu"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0147
            goto L_0x017f
        L_0x0147:
            r3 = 5
            goto L_0x017f
        L_0x0149:
            java.lang.String r4 = "titleFontFamily"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0152
            goto L_0x017f
        L_0x0152:
            r3 = 4
            goto L_0x017f
        L_0x0154:
            java.lang.String r4 = "largeTitleColor"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x015d
            goto L_0x017f
        L_0x015d:
            r3 = 3
            goto L_0x017f
        L_0x015f:
            java.lang.String r4 = "blurEffect"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0168
            goto L_0x017f
        L_0x0168:
            r3 = 2
            goto L_0x017f
        L_0x016a:
            java.lang.String r4 = "titleColor"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0173
            goto L_0x017f
        L_0x0173:
            r3 = r0
            goto L_0x017f
        L_0x0175:
            java.lang.String r4 = "translucent"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x017e
            goto L_0x017f
        L_0x017e:
            r3 = r2
        L_0x017f:
            switch(r3) {
                case 0: goto L_0x0338;
                case 1: goto L_0x0328;
                case 2: goto L_0x031e;
                case 3: goto L_0x030e;
                case 4: goto L_0x0300;
                case 5: goto L_0x02ef;
                case 6: goto L_0x02e1;
                case 7: goto L_0x02cf;
                case 8: goto L_0x02bd;
                case 9: goto L_0x02b2;
                case 10: goto L_0x02a0;
                case 11: goto L_0x0295;
                case 12: goto L_0x0284;
                case 13: goto L_0x0272;
                case 14: goto L_0x0263;
                case 15: goto L_0x0252;
                case 16: goto L_0x0243;
                case 17: goto L_0x0231;
                case 18: goto L_0x0222;
                case 19: goto L_0x0210;
                case 20: goto L_0x0201;
                case 21: goto L_0x01ef;
                case 22: goto L_0x01de;
                case 23: goto L_0x01cf;
                case 24: goto L_0x01bd;
                case 25: goto L_0x01ab;
                case 26: goto L_0x0199;
                case 27: goto L_0x0187;
                default: goto L_0x0182;
            }
        L_0x0182:
            super.setProperty(r6, r7, r8)
            goto L_0x0348
        L_0x0187:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r7 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r7
            if (r8 != 0) goto L_0x018e
            goto L_0x0194
        L_0x018e:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r2 = r8.booleanValue()
        L_0x0194:
            r7.setTopInsetEnabled(r6, r2)
            goto L_0x0348
        L_0x0199:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r7 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r7
            if (r8 != 0) goto L_0x01a0
            goto L_0x01a6
        L_0x01a0:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r2 = r8.booleanValue()
        L_0x01a6:
            r7.setLargeTitle(r6, r2)
            goto L_0x0348
        L_0x01ab:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r7 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r7
            if (r8 != 0) goto L_0x01b2
            goto L_0x01b8
        L_0x01b2:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r2 = r8.booleanValue()
        L_0x01b8:
            r7.setLargeTitleHideShadow(r6, r2)
            goto L_0x0348
        L_0x01bd:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r7 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r7
            if (r8 != 0) goto L_0x01c4
            goto L_0x01ca
        L_0x01c4:
            java.lang.Double r8 = (java.lang.Double) r8
            int r2 = r8.intValue()
        L_0x01ca:
            r7.setTitleFontSize(r6, r2)
            goto L_0x0348
        L_0x01cf:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r7 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r7
            if (r8 != 0) goto L_0x01d6
            goto L_0x01d9
        L_0x01d6:
            r1 = r8
            java.lang.String r1 = (java.lang.String) r1
        L_0x01d9:
            r7.setBackTitle(r6, r1)
            goto L_0x0348
        L_0x01de:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r7 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r7
            android.content.Context r0 = r6.getContext()
            java.lang.Integer r8 = com.facebook.react.bridge.ColorPropConverter.getColor(r8, r0)
            r7.setBackgroundColor(r6, r8)
            goto L_0x0348
        L_0x01ef:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r7 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r7
            if (r8 != 0) goto L_0x01f6
            goto L_0x01fc
        L_0x01f6:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r2 = r8.booleanValue()
        L_0x01fc:
            r7.setHideBackButton(r6, r2)
            goto L_0x0348
        L_0x0201:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r7 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r7
            if (r8 != 0) goto L_0x0208
            goto L_0x020b
        L_0x0208:
            r1 = r8
            java.lang.String r1 = (java.lang.String) r1
        L_0x020b:
            r7.setLargeTitleFontWeight(r6, r1)
            goto L_0x0348
        L_0x0210:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r7 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r7
            if (r8 != 0) goto L_0x0217
            goto L_0x021d
        L_0x0217:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r2 = r8.booleanValue()
        L_0x021d:
            r7.setHideShadow(r6, r2)
            goto L_0x0348
        L_0x0222:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r7 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r7
            if (r8 != 0) goto L_0x0229
            goto L_0x022c
        L_0x0229:
            r1 = r8
            java.lang.String r1 = (java.lang.String) r1
        L_0x022c:
            r7.setBackTitleFontFamily(r6, r1)
            goto L_0x0348
        L_0x0231:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r7 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r7
            if (r8 != 0) goto L_0x0238
            goto L_0x023e
        L_0x0238:
            java.lang.Double r8 = (java.lang.Double) r8
            int r2 = r8.intValue()
        L_0x023e:
            r7.setBackTitleFontSize(r6, r2)
            goto L_0x0348
        L_0x0243:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r7 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r7
            if (r8 != 0) goto L_0x024a
            goto L_0x024d
        L_0x024a:
            r1 = r8
            java.lang.String r1 = (java.lang.String) r1
        L_0x024d:
            r7.setTitle(r6, r1)
            goto L_0x0348
        L_0x0252:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r7 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r7
            android.content.Context r0 = r6.getContext()
            java.lang.Integer r8 = com.facebook.react.bridge.ColorPropConverter.getColor(r8, r0)
            r7.setColor(r6, r8)
            goto L_0x0348
        L_0x0263:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r7 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r7
            if (r8 != 0) goto L_0x026a
            goto L_0x026d
        L_0x026a:
            r1 = r8
            java.lang.String r1 = (java.lang.String) r1
        L_0x026d:
            r7.setLargeTitleFontFamily(r6, r1)
            goto L_0x0348
        L_0x0272:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r7 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r7
            if (r8 != 0) goto L_0x0279
            goto L_0x027f
        L_0x0279:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r2 = r8.booleanValue()
        L_0x027f:
            r7.setBackButtonInCustomView(r6, r2)
            goto L_0x0348
        L_0x0284:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r7 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r7
            android.content.Context r0 = r6.getContext()
            java.lang.Integer r8 = com.facebook.react.bridge.ColorPropConverter.getColor(r8, r0)
            r7.setLargeTitleBackgroundColor(r6, r8)
            goto L_0x0348
        L_0x0295:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r7 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r7
            java.lang.String r8 = (java.lang.String) r8
            r7.setDirection(r6, r8)
            goto L_0x0348
        L_0x02a0:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r7 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r7
            if (r8 != 0) goto L_0x02a7
            goto L_0x02ad
        L_0x02a7:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r0 = r8.booleanValue()
        L_0x02ad:
            r7.setBackTitleVisible(r6, r0)
            goto L_0x0348
        L_0x02b2:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r7 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r7
            java.lang.String r8 = (java.lang.String) r8
            r7.setBackButtonDisplayMode(r6, r8)
            goto L_0x0348
        L_0x02bd:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r7 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r7
            if (r8 != 0) goto L_0x02c4
            goto L_0x02ca
        L_0x02c4:
            java.lang.Double r8 = (java.lang.Double) r8
            int r2 = r8.intValue()
        L_0x02ca:
            r7.setLargeTitleFontSize(r6, r2)
            goto L_0x0348
        L_0x02cf:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r7 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r7
            if (r8 != 0) goto L_0x02d6
            goto L_0x02dc
        L_0x02d6:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r2 = r8.booleanValue()
        L_0x02dc:
            r7.setHidden(r6, r2)
            goto L_0x0348
        L_0x02e1:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r7 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r7
            if (r8 != 0) goto L_0x02e8
            goto L_0x02eb
        L_0x02e8:
            r1 = r8
            java.lang.String r1 = (java.lang.String) r1
        L_0x02eb:
            r7.setTitleFontWeight(r6, r1)
            goto L_0x0348
        L_0x02ef:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r7 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r7
            if (r8 != 0) goto L_0x02f6
            goto L_0x02fc
        L_0x02f6:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r2 = r8.booleanValue()
        L_0x02fc:
            r7.setDisableBackButtonMenu(r6, r2)
            goto L_0x0348
        L_0x0300:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r7 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r7
            if (r8 != 0) goto L_0x0307
            goto L_0x030a
        L_0x0307:
            r1 = r8
            java.lang.String r1 = (java.lang.String) r1
        L_0x030a:
            r7.setTitleFontFamily(r6, r1)
            goto L_0x0348
        L_0x030e:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r7 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r7
            android.content.Context r0 = r6.getContext()
            java.lang.Integer r8 = com.facebook.react.bridge.ColorPropConverter.getColor(r8, r0)
            r7.setLargeTitleColor(r6, r8)
            goto L_0x0348
        L_0x031e:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r7 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r7
            java.lang.String r8 = (java.lang.String) r8
            r7.setBlurEffect(r6, r8)
            goto L_0x0348
        L_0x0328:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r7 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r7
            android.content.Context r0 = r6.getContext()
            java.lang.Integer r8 = com.facebook.react.bridge.ColorPropConverter.getColor(r8, r0)
            r7.setTitleColor(r6, r8)
            goto L_0x0348
        L_0x0338:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface r7 = (com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerInterface) r7
            if (r8 != 0) goto L_0x033f
            goto L_0x0345
        L_0x033f:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r2 = r8.booleanValue()
        L_0x0345:
            r7.setTranslucent(r6, r2)
        L_0x0348:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSScreenStackHeaderConfigManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
