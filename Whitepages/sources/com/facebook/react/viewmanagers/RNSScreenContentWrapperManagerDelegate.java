package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSScreenContentWrapperManagerInterface;

public class RNSScreenContentWrapperManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSScreenContentWrapperManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSScreenContentWrapperManagerDelegate(U u) {
        super(u);
    }

    public void setProperty(T t, String str, Object obj) {
        super.setProperty(t, str, obj);
    }
}
