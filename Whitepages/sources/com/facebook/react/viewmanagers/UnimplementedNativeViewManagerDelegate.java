package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.UnimplementedNativeViewManagerInterface;

public class UnimplementedNativeViewManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & UnimplementedNativeViewManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public UnimplementedNativeViewManagerDelegate(U u) {
        super(u);
    }

    public void setProperty(T t, String str, Object obj) {
        str.hashCode();
        if (!str.equals("name")) {
            super.setProperty(t, str, obj);
        } else {
            ((UnimplementedNativeViewManagerInterface) this.mViewManager).setName(t, obj == null ? "" : (String) obj);
        }
    }
}
