package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.DynamicFromObject;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSVGFeCompositeManagerInterface;

public class RNSVGFeCompositeManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSVGFeCompositeManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGFeCompositeManagerDelegate(U u) {
        super(u);
    }

    public void setProperty(T t, String str, Object obj) {
        str.hashCode();
        String str2 = null;
        float f = 0.0f;
        char c = 65535;
        switch (str.hashCode()) {
            case -1221029593:
                if (str.equals("height")) {
                    c = 0;
                    break;
                }
                break;
            case -934426595:
                if (str.equals("result")) {
                    c = 1;
                    break;
                }
                break;
            case 120:
                if (str.equals("x")) {
                    c = 2;
                    break;
                }
                break;
            case 121:
                if (str.equals("y")) {
                    c = 3;
                    break;
                }
                break;
            case 3366:
                if (str.equals("k1")) {
                    c = 4;
                    break;
                }
                break;
            case 3367:
                if (str.equals("k2")) {
                    c = 5;
                    break;
                }
                break;
            case 3368:
                if (str.equals("k3")) {
                    c = 6;
                    break;
                }
                break;
            case 3369:
                if (str.equals("k4")) {
                    c = 7;
                    break;
                }
                break;
            case 104364:
                if (str.equals("in1")) {
                    c = 8;
                    break;
                }
                break;
            case 104365:
                if (str.equals("in2")) {
                    c = 9;
                    break;
                }
                break;
            case 113126854:
                if (str.equals("width")) {
                    c = 10;
                    break;
                }
                break;
            case 1662708749:
                if (str.equals("operator1")) {
                    c = 11;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                ((RNSVGFeCompositeManagerInterface) this.mViewManager).setHeight(t, new DynamicFromObject(obj));
                return;
            case 1:
                RNSVGFeCompositeManagerInterface rNSVGFeCompositeManagerInterface = (RNSVGFeCompositeManagerInterface) this.mViewManager;
                if (obj != null) {
                    str2 = (String) obj;
                }
                rNSVGFeCompositeManagerInterface.setResult(t, str2);
                return;
            case 2:
                ((RNSVGFeCompositeManagerInterface) this.mViewManager).setX(t, new DynamicFromObject(obj));
                return;
            case 3:
                ((RNSVGFeCompositeManagerInterface) this.mViewManager).setY(t, new DynamicFromObject(obj));
                return;
            case 4:
                RNSVGFeCompositeManagerInterface rNSVGFeCompositeManagerInterface2 = (RNSVGFeCompositeManagerInterface) this.mViewManager;
                if (obj != null) {
                    f = ((Double) obj).floatValue();
                }
                rNSVGFeCompositeManagerInterface2.setK1(t, f);
                return;
            case 5:
                RNSVGFeCompositeManagerInterface rNSVGFeCompositeManagerInterface3 = (RNSVGFeCompositeManagerInterface) this.mViewManager;
                if (obj != null) {
                    f = ((Double) obj).floatValue();
                }
                rNSVGFeCompositeManagerInterface3.setK2(t, f);
                return;
            case 6:
                RNSVGFeCompositeManagerInterface rNSVGFeCompositeManagerInterface4 = (RNSVGFeCompositeManagerInterface) this.mViewManager;
                if (obj != null) {
                    f = ((Double) obj).floatValue();
                }
                rNSVGFeCompositeManagerInterface4.setK3(t, f);
                return;
            case 7:
                RNSVGFeCompositeManagerInterface rNSVGFeCompositeManagerInterface5 = (RNSVGFeCompositeManagerInterface) this.mViewManager;
                if (obj != null) {
                    f = ((Double) obj).floatValue();
                }
                rNSVGFeCompositeManagerInterface5.setK4(t, f);
                return;
            case 8:
                RNSVGFeCompositeManagerInterface rNSVGFeCompositeManagerInterface6 = (RNSVGFeCompositeManagerInterface) this.mViewManager;
                if (obj != null) {
                    str2 = (String) obj;
                }
                rNSVGFeCompositeManagerInterface6.setIn1(t, str2);
                return;
            case 9:
                RNSVGFeCompositeManagerInterface rNSVGFeCompositeManagerInterface7 = (RNSVGFeCompositeManagerInterface) this.mViewManager;
                if (obj != null) {
                    str2 = (String) obj;
                }
                rNSVGFeCompositeManagerInterface7.setIn2(t, str2);
                return;
            case 10:
                ((RNSVGFeCompositeManagerInterface) this.mViewManager).setWidth(t, new DynamicFromObject(obj));
                return;
            case 11:
                ((RNSVGFeCompositeManagerInterface) this.mViewManager).setOperator1(t, (String) obj);
                return;
            default:
                super.setProperty(t, str, obj);
                return;
        }
    }
}
