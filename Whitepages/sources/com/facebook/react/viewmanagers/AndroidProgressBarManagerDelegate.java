package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.viewmanagers.AndroidProgressBarManagerInterface;
import com.facebook.react.views.progressbar.ReactProgressBarViewManager;

public class AndroidProgressBarManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & AndroidProgressBarManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public AndroidProgressBarManagerDelegate(U u) {
        super(u);
    }

    public void setProperty(T t, String str, Object obj) {
        boolean z = true;
        boolean z2 = false;
        str.hashCode();
        String str2 = null;
        char c = 65535;
        switch (str.hashCode()) {
            case -1001078227:
                if (str.equals(ReactProgressBarViewManager.PROP_PROGRESS)) {
                    c = 0;
                    break;
                }
                break;
            case -877170387:
                if (str.equals(ViewProps.TEST_ID)) {
                    c = 1;
                    break;
                }
                break;
            case -676876213:
                if (str.equals(ReactProgressBarViewManager.PROP_ATTR)) {
                    c = 2;
                    break;
                }
                break;
            case 94842723:
                if (str.equals(ViewProps.COLOR)) {
                    c = 3;
                    break;
                }
                break;
            case 633138363:
                if (str.equals(ReactProgressBarViewManager.PROP_INDETERMINATE)) {
                    c = 4;
                    break;
                }
                break;
            case 1118509918:
                if (str.equals(ReactProgressBarViewManager.PROP_ANIMATING)) {
                    c = 5;
                    break;
                }
                break;
            case 1804741442:
                if (str.equals(ReactProgressBarViewManager.PROP_STYLE)) {
                    c = 6;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                ((AndroidProgressBarManagerInterface) this.mViewManager).setProgress(t, obj == null ? 0.0d : ((Double) obj).doubleValue());
                return;
            case 1:
                ((AndroidProgressBarManagerInterface) this.mViewManager).setTestID(t, obj == null ? "" : (String) obj);
                return;
            case 2:
                AndroidProgressBarManagerInterface androidProgressBarManagerInterface = (AndroidProgressBarManagerInterface) this.mViewManager;
                if (obj != null) {
                    str2 = (String) obj;
                }
                androidProgressBarManagerInterface.setTypeAttr(t, str2);
                return;
            case 3:
                ((AndroidProgressBarManagerInterface) this.mViewManager).setColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case 4:
                AndroidProgressBarManagerInterface androidProgressBarManagerInterface2 = (AndroidProgressBarManagerInterface) this.mViewManager;
                if (obj != null) {
                    z2 = ((Boolean) obj).booleanValue();
                }
                androidProgressBarManagerInterface2.setIndeterminate(t, z2);
                return;
            case 5:
                AndroidProgressBarManagerInterface androidProgressBarManagerInterface3 = (AndroidProgressBarManagerInterface) this.mViewManager;
                if (obj != null) {
                    z = ((Boolean) obj).booleanValue();
                }
                androidProgressBarManagerInterface3.setAnimating(t, z);
                return;
            case 6:
                AndroidProgressBarManagerInterface androidProgressBarManagerInterface4 = (AndroidProgressBarManagerInterface) this.mViewManager;
                if (obj != null) {
                    str2 = (String) obj;
                }
                androidProgressBarManagerInterface4.setStyleAttr(t, str2);
                return;
            default:
                super.setProperty(t, str, obj);
                return;
        }
    }
}
