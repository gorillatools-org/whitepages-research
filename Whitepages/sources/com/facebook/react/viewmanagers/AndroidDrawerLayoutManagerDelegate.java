package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.AndroidDrawerLayoutManagerInterface;
import com.facebook.react.views.drawer.ReactDrawerLayoutManager;

public class AndroidDrawerLayoutManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & AndroidDrawerLayoutManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public AndroidDrawerLayoutManagerDelegate(U u) {
        super(u);
    }

    public void setProperty(T t, String str, Object obj) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2082382380:
                if (str.equals("statusBarBackgroundColor")) {
                    c = 0;
                    break;
                }
                break;
            case -1233873500:
                if (str.equals("drawerBackgroundColor")) {
                    c = 1;
                    break;
                }
                break;
            case -764307226:
                if (str.equals("keyboardDismissMode")) {
                    c = 2;
                    break;
                }
                break;
            case 268251989:
                if (str.equals("drawerWidth")) {
                    c = 3;
                    break;
                }
                break;
            case 695891258:
                if (str.equals("drawerPosition")) {
                    c = 4;
                    break;
                }
                break;
            case 1857208703:
                if (str.equals("drawerLockMode")) {
                    c = 5;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                ((AndroidDrawerLayoutManagerInterface) this.mViewManager).setStatusBarBackgroundColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case 1:
                ((AndroidDrawerLayoutManagerInterface) this.mViewManager).setDrawerBackgroundColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case 2:
                ((AndroidDrawerLayoutManagerInterface) this.mViewManager).setKeyboardDismissMode(t, (String) obj);
                return;
            case 3:
                ((AndroidDrawerLayoutManagerInterface) this.mViewManager).setDrawerWidth(t, obj == null ? null : Float.valueOf(((Double) obj).floatValue()));
                return;
            case 4:
                ((AndroidDrawerLayoutManagerInterface) this.mViewManager).setDrawerPosition(t, (String) obj);
                return;
            case 5:
                ((AndroidDrawerLayoutManagerInterface) this.mViewManager).setDrawerLockMode(t, (String) obj);
                return;
            default:
                super.setProperty(t, str, obj);
                return;
        }
    }

    public void receiveCommand(T t, String str, ReadableArray readableArray) {
        str.hashCode();
        if (str.equals(ReactDrawerLayoutManager.COMMAND_CLOSE_DRAWER)) {
            ((AndroidDrawerLayoutManagerInterface) this.mViewManager).closeDrawer(t);
        } else if (str.equals(ReactDrawerLayoutManager.COMMAND_OPEN_DRAWER)) {
            ((AndroidDrawerLayoutManagerInterface) this.mViewManager).openDrawer(t);
        }
    }
}
