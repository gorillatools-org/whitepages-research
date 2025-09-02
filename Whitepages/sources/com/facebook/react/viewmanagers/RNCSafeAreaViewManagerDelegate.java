package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNCSafeAreaViewManagerInterface;

public class RNCSafeAreaViewManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNCSafeAreaViewManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNCSafeAreaViewManagerDelegate(U u) {
        super(u);
    }

    public void setProperty(T t, String str, Object obj) {
        str.hashCode();
        if (str.equals("mode")) {
            ((RNCSafeAreaViewManagerInterface) this.mViewManager).setMode(t, (String) obj);
        } else if (!str.equals("edges")) {
            super.setProperty(t, str, obj);
        } else {
            ((RNCSafeAreaViewManagerInterface) this.mViewManager).setEdges(t, (ReadableMap) obj);
        }
    }
}
