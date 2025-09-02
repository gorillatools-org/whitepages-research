package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.DynamicFromObject;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSVGFeBlendManagerInterface;

public class RNSVGFeBlendManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSVGFeBlendManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGFeBlendManagerDelegate(U u) {
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
            case 104364:
                if (str.equals("in1")) {
                    c = 4;
                    break;
                }
                break;
            case 104365:
                if (str.equals("in2")) {
                    c = 5;
                    break;
                }
                break;
            case 3357091:
                if (str.equals("mode")) {
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
                ((RNSVGFeBlendManagerInterface) this.mViewManager).setHeight(t, new DynamicFromObject(obj));
                return;
            case 1:
                RNSVGFeBlendManagerInterface rNSVGFeBlendManagerInterface = (RNSVGFeBlendManagerInterface) this.mViewManager;
                if (obj != null) {
                    str2 = (String) obj;
                }
                rNSVGFeBlendManagerInterface.setResult(t, str2);
                return;
            case 2:
                ((RNSVGFeBlendManagerInterface) this.mViewManager).setX(t, new DynamicFromObject(obj));
                return;
            case 3:
                ((RNSVGFeBlendManagerInterface) this.mViewManager).setY(t, new DynamicFromObject(obj));
                return;
            case 4:
                RNSVGFeBlendManagerInterface rNSVGFeBlendManagerInterface2 = (RNSVGFeBlendManagerInterface) this.mViewManager;
                if (obj != null) {
                    str2 = (String) obj;
                }
                rNSVGFeBlendManagerInterface2.setIn1(t, str2);
                return;
            case 5:
                RNSVGFeBlendManagerInterface rNSVGFeBlendManagerInterface3 = (RNSVGFeBlendManagerInterface) this.mViewManager;
                if (obj != null) {
                    str2 = (String) obj;
                }
                rNSVGFeBlendManagerInterface3.setIn2(t, str2);
                return;
            case 6:
                ((RNSVGFeBlendManagerInterface) this.mViewManager).setMode(t, (String) obj);
                return;
            case 7:
                ((RNSVGFeBlendManagerInterface) this.mViewManager).setWidth(t, new DynamicFromObject(obj));
                return;
            default:
                super.setProperty(t, str, obj);
                return;
        }
    }
}
