package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.DynamicFromObject;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSVGFeGaussianBlurManagerInterface;

public class RNSVGFeGaussianBlurManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSVGFeGaussianBlurManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGFeGaussianBlurManagerDelegate(U u) {
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
            case 104364:
                if (str.equals("in1")) {
                    c = 4;
                    break;
                }
                break;
            case 113126854:
                if (str.equals("width")) {
                    c = 5;
                    break;
                }
                break;
            case 1530721536:
                if (str.equals("edgeMode")) {
                    c = 6;
                    break;
                }
                break;
            case 1837475450:
                if (str.equals("stdDeviationX")) {
                    c = 7;
                    break;
                }
                break;
            case 1837475451:
                if (str.equals("stdDeviationY")) {
                    c = 8;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                ((RNSVGFeGaussianBlurManagerInterface) this.mViewManager).setHeight(t, new DynamicFromObject(obj));
                return;
            case 1:
                RNSVGFeGaussianBlurManagerInterface rNSVGFeGaussianBlurManagerInterface = (RNSVGFeGaussianBlurManagerInterface) this.mViewManager;
                if (obj != null) {
                    str2 = (String) obj;
                }
                rNSVGFeGaussianBlurManagerInterface.setResult(t, str2);
                return;
            case 2:
                ((RNSVGFeGaussianBlurManagerInterface) this.mViewManager).setX(t, new DynamicFromObject(obj));
                return;
            case 3:
                ((RNSVGFeGaussianBlurManagerInterface) this.mViewManager).setY(t, new DynamicFromObject(obj));
                return;
            case 4:
                RNSVGFeGaussianBlurManagerInterface rNSVGFeGaussianBlurManagerInterface2 = (RNSVGFeGaussianBlurManagerInterface) this.mViewManager;
                if (obj != null) {
                    str2 = (String) obj;
                }
                rNSVGFeGaussianBlurManagerInterface2.setIn1(t, str2);
                return;
            case 5:
                ((RNSVGFeGaussianBlurManagerInterface) this.mViewManager).setWidth(t, new DynamicFromObject(obj));
                return;
            case 6:
                ((RNSVGFeGaussianBlurManagerInterface) this.mViewManager).setEdgeMode(t, (String) obj);
                return;
            case 7:
                RNSVGFeGaussianBlurManagerInterface rNSVGFeGaussianBlurManagerInterface3 = (RNSVGFeGaussianBlurManagerInterface) this.mViewManager;
                if (obj != null) {
                    f = ((Double) obj).floatValue();
                }
                rNSVGFeGaussianBlurManagerInterface3.setStdDeviationX(t, f);
                return;
            case 8:
                RNSVGFeGaussianBlurManagerInterface rNSVGFeGaussianBlurManagerInterface4 = (RNSVGFeGaussianBlurManagerInterface) this.mViewManager;
                if (obj != null) {
                    f = ((Double) obj).floatValue();
                }
                rNSVGFeGaussianBlurManagerInterface4.setStdDeviationY(t, f);
                return;
            default:
                super.setProperty(t, str, obj);
                return;
        }
    }
}
