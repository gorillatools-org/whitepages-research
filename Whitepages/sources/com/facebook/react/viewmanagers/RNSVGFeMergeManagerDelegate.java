package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.DynamicFromObject;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSVGFeMergeManagerInterface;

public class RNSVGFeMergeManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSVGFeMergeManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSVGFeMergeManagerDelegate(U u) {
        super(u);
    }

    public void setProperty(T t, String str, Object obj) {
        str.hashCode();
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
            case 104993457:
                if (str.equals("nodes")) {
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
        }
        switch (c) {
            case 0:
                ((RNSVGFeMergeManagerInterface) this.mViewManager).setHeight(t, new DynamicFromObject(obj));
                return;
            case 1:
                ((RNSVGFeMergeManagerInterface) this.mViewManager).setResult(t, obj == null ? null : (String) obj);
                return;
            case 2:
                ((RNSVGFeMergeManagerInterface) this.mViewManager).setX(t, new DynamicFromObject(obj));
                return;
            case 3:
                ((RNSVGFeMergeManagerInterface) this.mViewManager).setY(t, new DynamicFromObject(obj));
                return;
            case 4:
                ((RNSVGFeMergeManagerInterface) this.mViewManager).setNodes(t, (ReadableArray) obj);
                return;
            case 5:
                ((RNSVGFeMergeManagerInterface) this.mViewManager).setWidth(t, new DynamicFromObject(obj));
                return;
            default:
                super.setProperty(t, str, obj);
                return;
        }
    }
}
