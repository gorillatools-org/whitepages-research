package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.viewmanagers.ActivityIndicatorViewManagerInterface;
import com.facebook.react.views.progressbar.ReactProgressBarViewManager;

public class ActivityIndicatorViewManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & ActivityIndicatorViewManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public ActivityIndicatorViewManagerDelegate(U u) {
        super(u);
    }

    public void setProperty(T t, String str, Object obj) {
        str.hashCode();
        boolean z = true;
        char c = 65535;
        switch (str.hashCode()) {
            case 3530753:
                if (str.equals("size")) {
                    c = 0;
                    break;
                }
                break;
            case 94842723:
                if (str.equals(ViewProps.COLOR)) {
                    c = 1;
                    break;
                }
                break;
            case 865748226:
                if (str.equals("hidesWhenStopped")) {
                    c = 2;
                    break;
                }
                break;
            case 1118509918:
                if (str.equals(ReactProgressBarViewManager.PROP_ANIMATING)) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                ((ActivityIndicatorViewManagerInterface) this.mViewManager).setSize(t, (String) obj);
                return;
            case 1:
                ((ActivityIndicatorViewManagerInterface) this.mViewManager).setColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case 2:
                ActivityIndicatorViewManagerInterface activityIndicatorViewManagerInterface = (ActivityIndicatorViewManagerInterface) this.mViewManager;
                if (obj != null) {
                    z = ((Boolean) obj).booleanValue();
                }
                activityIndicatorViewManagerInterface.setHidesWhenStopped(t, z);
                return;
            case 3:
                ActivityIndicatorViewManagerInterface activityIndicatorViewManagerInterface2 = (ActivityIndicatorViewManagerInterface) this.mViewManager;
                if (obj != null) {
                    z = ((Boolean) obj).booleanValue();
                }
                activityIndicatorViewManagerInterface2.setAnimating(t, z);
                return;
            default:
                super.setProperty(t, str, obj);
                return;
        }
    }
}
