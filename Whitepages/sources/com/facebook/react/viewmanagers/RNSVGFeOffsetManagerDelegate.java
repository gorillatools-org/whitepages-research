package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.DynamicFromObject;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSVGFeOffsetManagerInterface;

public class RNSVGFeOffsetManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSVGFeOffsetManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGFeOffsetManagerDelegate(U u) {
        super(u);
    }

    public void setProperty(T t, String str, Object obj) {
        str.hashCode();
        String str2 = null;
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
            case 3220:
                if (str.equals("dx")) {
                    c = 4;
                    break;
                }
                break;
            case 3221:
                if (str.equals("dy")) {
                    c = 5;
                    break;
                }
                break;
            case 104364:
                if (str.equals("in1")) {
                    c = 6;
                    break;
                }
                break;
            case 113126854:
                if (str.equals("width")) {
                    c = 7;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                ((RNSVGFeOffsetManagerInterface) this.mViewManager).setHeight(t, new DynamicFromObject(obj));
                return;
            case 1:
                RNSVGFeOffsetManagerInterface rNSVGFeOffsetManagerInterface = (RNSVGFeOffsetManagerInterface) this.mViewManager;
                if (obj != null) {
                    str2 = (String) obj;
                }
                rNSVGFeOffsetManagerInterface.setResult(t, str2);
                return;
            case 2:
                ((RNSVGFeOffsetManagerInterface) this.mViewManager).setX(t, new DynamicFromObject(obj));
                return;
            case 3:
                ((RNSVGFeOffsetManagerInterface) this.mViewManager).setY(t, new DynamicFromObject(obj));
                return;
            case 4:
                ((RNSVGFeOffsetManagerInterface) this.mViewManager).setDx(t, new DynamicFromObject(obj));
                return;
            case 5:
                ((RNSVGFeOffsetManagerInterface) this.mViewManager).setDy(t, new DynamicFromObject(obj));
                return;
            case 6:
                RNSVGFeOffsetManagerInterface rNSVGFeOffsetManagerInterface2 = (RNSVGFeOffsetManagerInterface) this.mViewManager;
                if (obj != null) {
                    str2 = (String) obj;
                }
                rNSVGFeOffsetManagerInterface2.setIn1(t, str2);
                return;
            case 7:
                ((RNSVGFeOffsetManagerInterface) this.mViewManager).setWidth(t, new DynamicFromObject(obj));
                return;
            default:
                super.setProperty(t, str, obj);
                return;
        }
    }
}
