package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSScreenStackManagerInterface;

public class RNSScreenStackManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSScreenStackManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSScreenStackManagerDelegate(U u) {
        super(u);
    }

    public void setProperty(T t, String str, Object obj) {
        super.setProperty(t, str, obj);
    }
}
