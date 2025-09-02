package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface;

public class RNSVGSvgViewAndroidManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSVGSvgViewAndroidManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGSvgViewAndroidManagerDelegate(U u) {
        super(u);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v12, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v13, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v14, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v15, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v17, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v19, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v20, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v21, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v22, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v23, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v24, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v25, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v26, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v27, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v28, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v29, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v30, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v31, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v32, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v33, resolved type: boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setProperty(T r6, java.lang.String r7, java.lang.Object r8) {
        /*
            r5 = this;
            r7.hashCode()
            r0 = 0
            r1 = 0
            r2 = 0
            r3 = -1
            int r4 = r7.hashCode()
            switch(r4) {
                case -2064426617: goto L_0x028d;
                case -1989576717: goto L_0x0282;
                case -1697814026: goto L_0x0277;
                case -1567958285: goto L_0x026c;
                case -1470826662: goto L_0x0261;
                case -1308858324: goto L_0x0256;
                case -1228066334: goto L_0x024b;
                case -1141400650: goto L_0x0240;
                case -1122140597: goto L_0x0232;
                case -867333731: goto L_0x0224;
                case -679581037: goto L_0x0216;
                case -631506969: goto L_0x0208;
                case -631278772: goto L_0x01fa;
                case -483490364: goto L_0x01ec;
                case -329721498: goto L_0x01de;
                case -293492298: goto L_0x01d0;
                case -252105751: goto L_0x01c2;
                case -242276144: goto L_0x01b4;
                case -223134121: goto L_0x01a6;
                case -148030058: goto L_0x0198;
                case -109689771: goto L_0x018a;
                case -27894242: goto L_0x017c;
                case 3351622: goto L_0x016e;
                case 3351623: goto L_0x0160;
                case 92903173: goto L_0x0152;
                case 94842723: goto L_0x0144;
                case 240482938: goto L_0x0136;
                case 306963138: goto L_0x0128;
                case 333432965: goto L_0x011a;
                case 503397728: goto L_0x010c;
                case 581268560: goto L_0x00fe;
                case 588239831: goto L_0x00f0;
                case 660795168: goto L_0x00e2;
                case 684610594: goto L_0x00d4;
                case 722830999: goto L_0x00c6;
                case 737768677: goto L_0x00b8;
                case 762983977: goto L_0x00aa;
                case 910681861: goto L_0x009c;
                case 926871597: goto L_0x008e;
                case 1220735892: goto L_0x0080;
                case 1349188574: goto L_0x0072;
                case 1629011506: goto L_0x0064;
                case 1667773924: goto L_0x0056;
                case 1735382270: goto L_0x0048;
                case 1747724810: goto L_0x003a;
                case 1908075304: goto L_0x002c;
                case 1910855543: goto L_0x001e;
                case 2119889261: goto L_0x0010;
                default: goto L_0x000e;
            }
        L_0x000e:
            goto L_0x0297
        L_0x0010:
            java.lang.String r4 = "borderStartColor"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x001a
            goto L_0x0297
        L_0x001a:
            r3 = 47
            goto L_0x0297
        L_0x001e:
            java.lang.String r4 = "nextFocusRight"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0028
            goto L_0x0297
        L_0x0028:
            r3 = 46
            goto L_0x0297
        L_0x002c:
            java.lang.String r4 = "meetOrSlice"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0036
            goto L_0x0297
        L_0x0036:
            r3 = 45
            goto L_0x0297
        L_0x003a:
            java.lang.String r4 = "nativeBackgroundAndroid"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0044
            goto L_0x0297
        L_0x0044:
            r3 = 44
            goto L_0x0297
        L_0x0048:
            java.lang.String r4 = "borderEndEndRadius"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0052
            goto L_0x0297
        L_0x0052:
            r3 = 43
            goto L_0x0297
        L_0x0056:
            java.lang.String r4 = "needsOffscreenAlphaCompositing"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0060
            goto L_0x0297
        L_0x0060:
            r3 = 42
            goto L_0x0297
        L_0x0064:
            java.lang.String r4 = "focusable"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x006e
            goto L_0x0297
        L_0x006e:
            r3 = 41
            goto L_0x0297
        L_0x0072:
            java.lang.String r4 = "borderRadius"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x007c
            goto L_0x0297
        L_0x007c:
            r3 = 40
            goto L_0x0297
        L_0x0080:
            java.lang.String r4 = "borderEndColor"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x008a
            goto L_0x0297
        L_0x008a:
            r3 = 39
            goto L_0x0297
        L_0x008e:
            java.lang.String r4 = "hitSlop"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0098
            goto L_0x0297
        L_0x0098:
            r3 = 38
            goto L_0x0297
        L_0x009c:
            java.lang.String r4 = "borderEndStartRadius"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x00a6
            goto L_0x0297
        L_0x00a6:
            r3 = 37
            goto L_0x0297
        L_0x00aa:
            java.lang.String r4 = "borderBlockEndColor"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x00b4
            goto L_0x0297
        L_0x00b4:
            r3 = 36
            goto L_0x0297
        L_0x00b8:
            java.lang.String r4 = "borderStyle"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x00c2
            goto L_0x0297
        L_0x00c2:
            r3 = 35
            goto L_0x0297
        L_0x00c6:
            java.lang.String r4 = "borderColor"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x00d0
            goto L_0x0297
        L_0x00d0:
            r3 = 34
            goto L_0x0297
        L_0x00d4:
            java.lang.String r4 = "borderBlockColor"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x00de
            goto L_0x0297
        L_0x00de:
            r3 = 33
            goto L_0x0297
        L_0x00e2:
            java.lang.String r4 = "nextFocusUp"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x00ec
            goto L_0x0297
        L_0x00ec:
            r3 = 32
            goto L_0x0297
        L_0x00f0:
            java.lang.String r4 = "borderBottomRightRadius"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x00fa
            goto L_0x0297
        L_0x00fa:
            r3 = 31
            goto L_0x0297
        L_0x00fe:
            java.lang.String r4 = "borderBottomLeftRadius"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0108
            goto L_0x0297
        L_0x0108:
            r3 = 30
            goto L_0x0297
        L_0x010c:
            java.lang.String r4 = "nextFocusForward"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0116
            goto L_0x0297
        L_0x0116:
            r3 = 29
            goto L_0x0297
        L_0x011a:
            java.lang.String r4 = "borderTopRightRadius"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0124
            goto L_0x0297
        L_0x0124:
            r3 = 28
            goto L_0x0297
        L_0x0128:
            java.lang.String r4 = "borderBlockStartColor"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0132
            goto L_0x0297
        L_0x0132:
            r3 = 27
            goto L_0x0297
        L_0x0136:
            java.lang.String r4 = "vbWidth"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0140
            goto L_0x0297
        L_0x0140:
            r3 = 26
            goto L_0x0297
        L_0x0144:
            java.lang.String r4 = "color"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x014e
            goto L_0x0297
        L_0x014e:
            r3 = 25
            goto L_0x0297
        L_0x0152:
            java.lang.String r4 = "align"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x015c
            goto L_0x0297
        L_0x015c:
            r3 = 24
            goto L_0x0297
        L_0x0160:
            java.lang.String r4 = "minY"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x016a
            goto L_0x0297
        L_0x016a:
            r3 = 23
            goto L_0x0297
        L_0x016e:
            java.lang.String r4 = "minX"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0178
            goto L_0x0297
        L_0x0178:
            r3 = 22
            goto L_0x0297
        L_0x017c:
            java.lang.String r4 = "borderStartStartRadius"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0186
            goto L_0x0297
        L_0x0186:
            r3 = 21
            goto L_0x0297
        L_0x018a:
            java.lang.String r4 = "nativeForegroundAndroid"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0194
            goto L_0x0297
        L_0x0194:
            r3 = 20
            goto L_0x0297
        L_0x0198:
            java.lang.String r4 = "borderBottomEndRadius"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x01a2
            goto L_0x0297
        L_0x01a2:
            r3 = 19
            goto L_0x0297
        L_0x01a6:
            java.lang.String r4 = "borderStartEndRadius"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x01b0
            goto L_0x0297
        L_0x01b0:
            r3 = 18
            goto L_0x0297
        L_0x01b4:
            java.lang.String r4 = "borderLeftColor"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x01be
            goto L_0x0297
        L_0x01be:
            r3 = 17
            goto L_0x0297
        L_0x01c2:
            java.lang.String r4 = "removeClippedSubviews"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x01cc
            goto L_0x0297
        L_0x01cc:
            r3 = 16
            goto L_0x0297
        L_0x01d0:
            java.lang.String r4 = "pointerEvents"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x01da
            goto L_0x0297
        L_0x01da:
            r3 = 15
            goto L_0x0297
        L_0x01de:
            java.lang.String r4 = "bbWidth"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x01e8
            goto L_0x0297
        L_0x01e8:
            r3 = 14
            goto L_0x0297
        L_0x01ec:
            java.lang.String r4 = "borderTopEndRadius"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x01f6
            goto L_0x0297
        L_0x01f6:
            r3 = 13
            goto L_0x0297
        L_0x01fa:
            java.lang.String r4 = "nextFocusLeft"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0204
            goto L_0x0297
        L_0x0204:
            r3 = 12
            goto L_0x0297
        L_0x0208:
            java.lang.String r4 = "nextFocusDown"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0212
            goto L_0x0297
        L_0x0212:
            r3 = 11
            goto L_0x0297
        L_0x0216:
            java.lang.String r4 = "hasTVPreferredFocus"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0220
            goto L_0x0297
        L_0x0220:
            r3 = 10
            goto L_0x0297
        L_0x0224:
            java.lang.String r4 = "borderBottomStartRadius"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x022e
            goto L_0x0297
        L_0x022e:
            r3 = 9
            goto L_0x0297
        L_0x0232:
            java.lang.String r4 = "borderTopStartRadius"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x023c
            goto L_0x0297
        L_0x023c:
            r3 = 8
            goto L_0x0297
        L_0x0240:
            java.lang.String r4 = "accessible"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0249
            goto L_0x0297
        L_0x0249:
            r3 = 7
            goto L_0x0297
        L_0x024b:
            java.lang.String r4 = "borderTopLeftRadius"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0254
            goto L_0x0297
        L_0x0254:
            r3 = 6
            goto L_0x0297
        L_0x0256:
            java.lang.String r4 = "borderBottomColor"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x025f
            goto L_0x0297
        L_0x025f:
            r3 = 5
            goto L_0x0297
        L_0x0261:
            java.lang.String r4 = "borderTopColor"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x026a
            goto L_0x0297
        L_0x026a:
            r3 = 4
            goto L_0x0297
        L_0x026c:
            java.lang.String r4 = "vbHeight"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0275
            goto L_0x0297
        L_0x0275:
            r3 = 3
            goto L_0x0297
        L_0x0277:
            java.lang.String r4 = "backfaceVisibility"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0280
            goto L_0x0297
        L_0x0280:
            r3 = 2
            goto L_0x0297
        L_0x0282:
            java.lang.String r4 = "borderRightColor"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x028b
            goto L_0x0297
        L_0x028b:
            r3 = 1
            goto L_0x0297
        L_0x028d:
            java.lang.String r4 = "bbHeight"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0296
            goto L_0x0297
        L_0x0296:
            r3 = r2
        L_0x0297:
            switch(r3) {
                case 0: goto L_0x0585;
                case 1: goto L_0x0575;
                case 2: goto L_0x0567;
                case 3: goto L_0x0556;
                case 4: goto L_0x0546;
                case 5: goto L_0x0536;
                case 6: goto L_0x0529;
                case 7: goto L_0x0518;
                case 8: goto L_0x050a;
                case 9: goto L_0x04fc;
                case 10: goto L_0x04ea;
                case 11: goto L_0x04d8;
                case 12: goto L_0x04c6;
                case 13: goto L_0x04b8;
                case 14: goto L_0x04aa;
                case 15: goto L_0x049b;
                case 16: goto L_0x0489;
                case 17: goto L_0x0478;
                case 18: goto L_0x046a;
                case 19: goto L_0x045c;
                case 20: goto L_0x0451;
                case 21: goto L_0x0443;
                case 22: goto L_0x0431;
                case 23: goto L_0x041f;
                case 24: goto L_0x0410;
                case 25: goto L_0x03ff;
                case 26: goto L_0x03ed;
                case 27: goto L_0x03dc;
                case 28: goto L_0x03ce;
                case 29: goto L_0x03bc;
                case 30: goto L_0x03ae;
                case 31: goto L_0x03a0;
                case 32: goto L_0x038e;
                case 33: goto L_0x037d;
                case 34: goto L_0x036c;
                case 35: goto L_0x035d;
                case 36: goto L_0x034c;
                case 37: goto L_0x033e;
                case 38: goto L_0x0330;
                case 39: goto L_0x031f;
                case 40: goto L_0x0311;
                case 41: goto L_0x02ff;
                case 42: goto L_0x02ed;
                case 43: goto L_0x02df;
                case 44: goto L_0x02d4;
                case 45: goto L_0x02c2;
                case 46: goto L_0x02b0;
                case 47: goto L_0x029f;
                default: goto L_0x029a;
            }
        L_0x029a:
            super.setProperty(r6, r7, r8)
            goto L_0x0591
        L_0x029f:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            android.content.Context r0 = r6.getContext()
            java.lang.Integer r8 = com.facebook.react.bridge.ColorPropConverter.getColor(r8, r0)
            r7.setBorderStartColor(r6, r8)
            goto L_0x0591
        L_0x02b0:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            if (r8 != 0) goto L_0x02b7
            goto L_0x02bd
        L_0x02b7:
            java.lang.Double r8 = (java.lang.Double) r8
            int r2 = r8.intValue()
        L_0x02bd:
            r7.setNextFocusRight(r6, r2)
            goto L_0x0591
        L_0x02c2:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            if (r8 != 0) goto L_0x02c9
            goto L_0x02cf
        L_0x02c9:
            java.lang.Double r8 = (java.lang.Double) r8
            int r2 = r8.intValue()
        L_0x02cf:
            r7.setMeetOrSlice(r6, r2)
            goto L_0x0591
        L_0x02d4:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            com.facebook.react.bridge.ReadableMap r8 = (com.facebook.react.bridge.ReadableMap) r8
            r7.setNativeBackgroundAndroid(r6, r8)
            goto L_0x0591
        L_0x02df:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r8)
            r7.setBorderEndEndRadius(r6, r0)
            goto L_0x0591
        L_0x02ed:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            if (r8 != 0) goto L_0x02f4
            goto L_0x02fa
        L_0x02f4:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r2 = r8.booleanValue()
        L_0x02fa:
            r7.setNeedsOffscreenAlphaCompositing(r6, r2)
            goto L_0x0591
        L_0x02ff:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            if (r8 != 0) goto L_0x0306
            goto L_0x030c
        L_0x0306:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r2 = r8.booleanValue()
        L_0x030c:
            r7.setFocusable(r6, r2)
            goto L_0x0591
        L_0x0311:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r8)
            r7.setBorderRadius(r6, r0)
            goto L_0x0591
        L_0x031f:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            android.content.Context r0 = r6.getContext()
            java.lang.Integer r8 = com.facebook.react.bridge.ColorPropConverter.getColor(r8, r0)
            r7.setBorderEndColor(r6, r8)
            goto L_0x0591
        L_0x0330:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r8)
            r7.setHitSlop(r6, r0)
            goto L_0x0591
        L_0x033e:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r8)
            r7.setBorderEndStartRadius(r6, r0)
            goto L_0x0591
        L_0x034c:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            android.content.Context r0 = r6.getContext()
            java.lang.Integer r8 = com.facebook.react.bridge.ColorPropConverter.getColor(r8, r0)
            r7.setBorderBlockEndColor(r6, r8)
            goto L_0x0591
        L_0x035d:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            if (r8 != 0) goto L_0x0364
            goto L_0x0367
        L_0x0364:
            r1 = r8
            java.lang.String r1 = (java.lang.String) r1
        L_0x0367:
            r7.setBorderStyle(r6, r1)
            goto L_0x0591
        L_0x036c:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            android.content.Context r0 = r6.getContext()
            java.lang.Integer r8 = com.facebook.react.bridge.ColorPropConverter.getColor(r8, r0)
            r7.setBorderColor(r6, r8)
            goto L_0x0591
        L_0x037d:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            android.content.Context r0 = r6.getContext()
            java.lang.Integer r8 = com.facebook.react.bridge.ColorPropConverter.getColor(r8, r0)
            r7.setBorderBlockColor(r6, r8)
            goto L_0x0591
        L_0x038e:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            if (r8 != 0) goto L_0x0395
            goto L_0x039b
        L_0x0395:
            java.lang.Double r8 = (java.lang.Double) r8
            int r2 = r8.intValue()
        L_0x039b:
            r7.setNextFocusUp(r6, r2)
            goto L_0x0591
        L_0x03a0:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r8)
            r7.setBorderBottomRightRadius(r6, r0)
            goto L_0x0591
        L_0x03ae:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r8)
            r7.setBorderBottomLeftRadius(r6, r0)
            goto L_0x0591
        L_0x03bc:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            if (r8 != 0) goto L_0x03c3
            goto L_0x03c9
        L_0x03c3:
            java.lang.Double r8 = (java.lang.Double) r8
            int r2 = r8.intValue()
        L_0x03c9:
            r7.setNextFocusForward(r6, r2)
            goto L_0x0591
        L_0x03ce:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r8)
            r7.setBorderTopRightRadius(r6, r0)
            goto L_0x0591
        L_0x03dc:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            android.content.Context r0 = r6.getContext()
            java.lang.Integer r8 = com.facebook.react.bridge.ColorPropConverter.getColor(r8, r0)
            r7.setBorderBlockStartColor(r6, r8)
            goto L_0x0591
        L_0x03ed:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            if (r8 != 0) goto L_0x03f4
            goto L_0x03fa
        L_0x03f4:
            java.lang.Double r8 = (java.lang.Double) r8
            float r0 = r8.floatValue()
        L_0x03fa:
            r7.setVbWidth(r6, r0)
            goto L_0x0591
        L_0x03ff:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            android.content.Context r0 = r6.getContext()
            java.lang.Integer r8 = com.facebook.react.bridge.ColorPropConverter.getColor(r8, r0)
            r7.setColor(r6, r8)
            goto L_0x0591
        L_0x0410:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            if (r8 != 0) goto L_0x0417
            goto L_0x041a
        L_0x0417:
            r1 = r8
            java.lang.String r1 = (java.lang.String) r1
        L_0x041a:
            r7.setAlign(r6, r1)
            goto L_0x0591
        L_0x041f:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            if (r8 != 0) goto L_0x0426
            goto L_0x042c
        L_0x0426:
            java.lang.Double r8 = (java.lang.Double) r8
            float r0 = r8.floatValue()
        L_0x042c:
            r7.setMinY(r6, r0)
            goto L_0x0591
        L_0x0431:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            if (r8 != 0) goto L_0x0438
            goto L_0x043e
        L_0x0438:
            java.lang.Double r8 = (java.lang.Double) r8
            float r0 = r8.floatValue()
        L_0x043e:
            r7.setMinX(r6, r0)
            goto L_0x0591
        L_0x0443:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r8)
            r7.setBorderStartStartRadius(r6, r0)
            goto L_0x0591
        L_0x0451:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            com.facebook.react.bridge.ReadableMap r8 = (com.facebook.react.bridge.ReadableMap) r8
            r7.setNativeForegroundAndroid(r6, r8)
            goto L_0x0591
        L_0x045c:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r8)
            r7.setBorderBottomEndRadius(r6, r0)
            goto L_0x0591
        L_0x046a:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r8)
            r7.setBorderStartEndRadius(r6, r0)
            goto L_0x0591
        L_0x0478:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            android.content.Context r0 = r6.getContext()
            java.lang.Integer r8 = com.facebook.react.bridge.ColorPropConverter.getColor(r8, r0)
            r7.setBorderLeftColor(r6, r8)
            goto L_0x0591
        L_0x0489:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            if (r8 != 0) goto L_0x0490
            goto L_0x0496
        L_0x0490:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r2 = r8.booleanValue()
        L_0x0496:
            r7.setRemoveClippedSubviews(r6, r2)
            goto L_0x0591
        L_0x049b:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            if (r8 != 0) goto L_0x04a2
            goto L_0x04a5
        L_0x04a2:
            r1 = r8
            java.lang.String r1 = (java.lang.String) r1
        L_0x04a5:
            r7.setPointerEvents(r6, r1)
            goto L_0x0591
        L_0x04aa:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r8)
            r7.setBbWidth(r6, r0)
            goto L_0x0591
        L_0x04b8:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r8)
            r7.setBorderTopEndRadius(r6, r0)
            goto L_0x0591
        L_0x04c6:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            if (r8 != 0) goto L_0x04cd
            goto L_0x04d3
        L_0x04cd:
            java.lang.Double r8 = (java.lang.Double) r8
            int r2 = r8.intValue()
        L_0x04d3:
            r7.setNextFocusLeft(r6, r2)
            goto L_0x0591
        L_0x04d8:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            if (r8 != 0) goto L_0x04df
            goto L_0x04e5
        L_0x04df:
            java.lang.Double r8 = (java.lang.Double) r8
            int r2 = r8.intValue()
        L_0x04e5:
            r7.setNextFocusDown(r6, r2)
            goto L_0x0591
        L_0x04ea:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            if (r8 != 0) goto L_0x04f1
            goto L_0x04f7
        L_0x04f1:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r2 = r8.booleanValue()
        L_0x04f7:
            r7.setHasTVPreferredFocus(r6, r2)
            goto L_0x0591
        L_0x04fc:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r8)
            r7.setBorderBottomStartRadius(r6, r0)
            goto L_0x0591
        L_0x050a:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r8)
            r7.setBorderTopStartRadius(r6, r0)
            goto L_0x0591
        L_0x0518:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            if (r8 != 0) goto L_0x051f
            goto L_0x0525
        L_0x051f:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r2 = r8.booleanValue()
        L_0x0525:
            r7.setAccessible(r6, r2)
            goto L_0x0591
        L_0x0529:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r8)
            r7.setBorderTopLeftRadius(r6, r0)
            goto L_0x0591
        L_0x0536:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            android.content.Context r0 = r6.getContext()
            java.lang.Integer r8 = com.facebook.react.bridge.ColorPropConverter.getColor(r8, r0)
            r7.setBorderBottomColor(r6, r8)
            goto L_0x0591
        L_0x0546:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            android.content.Context r0 = r6.getContext()
            java.lang.Integer r8 = com.facebook.react.bridge.ColorPropConverter.getColor(r8, r0)
            r7.setBorderTopColor(r6, r8)
            goto L_0x0591
        L_0x0556:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            if (r8 != 0) goto L_0x055d
            goto L_0x0563
        L_0x055d:
            java.lang.Double r8 = (java.lang.Double) r8
            float r0 = r8.floatValue()
        L_0x0563:
            r7.setVbHeight(r6, r0)
            goto L_0x0591
        L_0x0567:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            if (r8 != 0) goto L_0x056e
            goto L_0x0571
        L_0x056e:
            r1 = r8
            java.lang.String r1 = (java.lang.String) r1
        L_0x0571:
            r7.setBackfaceVisibility(r6, r1)
            goto L_0x0591
        L_0x0575:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            android.content.Context r0 = r6.getContext()
            java.lang.Integer r8 = com.facebook.react.bridge.ColorPropConverter.getColor(r8, r0)
            r7.setBorderRightColor(r6, r8)
            goto L_0x0591
        L_0x0585:
            U r7 = r5.mViewManager
            com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface r7 = (com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface) r7
            com.facebook.react.bridge.DynamicFromObject r0 = new com.facebook.react.bridge.DynamicFromObject
            r0.<init>(r8)
            r7.setBbHeight(r6, r0)
        L_0x0591:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerDelegate.setProperty(android.view.View, java.lang.String, java.lang.Object):void");
    }
}
