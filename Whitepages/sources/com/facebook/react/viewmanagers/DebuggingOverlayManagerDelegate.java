package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.DebuggingOverlayManagerInterface;

public class DebuggingOverlayManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & DebuggingOverlayManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public DebuggingOverlayManagerDelegate(U u) {
        super(u);
    }

    public void setProperty(T t, String str, Object obj) {
        super.setProperty(t, str, obj);
    }

    public void receiveCommand(T t, String str, ReadableArray readableArray) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1942063165:
                if (str.equals("clearElementsHighlights")) {
                    c = 0;
                    break;
                }
                break;
            case 1326903961:
                if (str.equals("highlightTraceUpdates")) {
                    c = 1;
                    break;
                }
                break;
            case 1385348555:
                if (str.equals("highlightElements")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                ((DebuggingOverlayManagerInterface) this.mViewManager).clearElementsHighlights(t);
                return;
            case 1:
                ((DebuggingOverlayManagerInterface) this.mViewManager).highlightTraceUpdates(t, readableArray.getArray(0));
                return;
            case 2:
                ((DebuggingOverlayManagerInterface) this.mViewManager).highlightElements(t, readableArray.getArray(0));
                return;
            default:
                return;
        }
    }
}
