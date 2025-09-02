package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.DynamicFromObject;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSVGFeFloodManagerInterface;

public class RNSVGFeFloodManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSVGFeFloodManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGFeFloodManagerDelegate(U u) {
        super(u);
    }

    public void setProperty(T t, String str, Object obj) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1960777211:
                if (str.equals("floodColor")) {
                    c = 0;
                    break;
                }
                break;
            case -1221029593:
                if (str.equals("height")) {
                    c = 1;
                    break;
                }
                break;
            case -1033006547:
                if (str.equals("floodOpacity")) {
                    c = 2;
                    break;
                }
                break;
            case -934426595:
                if (str.equals("result")) {
                    c = 3;
                    break;
                }
                break;
            case 120:
                if (str.equals("x")) {
                    c = 4;
                    break;
                }
                break;
            case 121:
                if (str.equals("y")) {
                    c = 5;
                    break;
                }
                break;
            case 113126854:
                if (str.equals("width")) {
                    c = 6;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                ((RNSVGFeFloodManagerInterface) this.mViewManager).setFloodColor(t, new DynamicFromObject(obj));
                return;
            case 1:
                ((RNSVGFeFloodManagerInterface) this.mViewManager).setHeight(t, new DynamicFromObject(obj));
                return;
            case 2:
                ((RNSVGFeFloodManagerInterface) this.mViewManager).setFloodOpacity(t, obj == null ? 1.0f : ((Double) obj).floatValue());
                return;
            case 3:
                ((RNSVGFeFloodManagerInterface) this.mViewManager).setResult(t, obj == null ? null : (String) obj);
                return;
            case 4:
                ((RNSVGFeFloodManagerInterface) this.mViewManager).setX(t, new DynamicFromObject(obj));
                return;
            case 5:
                ((RNSVGFeFloodManagerInterface) this.mViewManager).setY(t, new DynamicFromObject(obj));
                return;
            case 6:
                ((RNSVGFeFloodManagerInterface) this.mViewManager).setWidth(t, new DynamicFromObject(obj));
                return;
            default:
                super.setProperty(t, str, obj);
                return;
        }
    }
}
