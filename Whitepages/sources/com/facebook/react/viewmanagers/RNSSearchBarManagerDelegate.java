package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSSearchBarManagerInterface;
import com.facebook.react.views.textinput.ReactTextInputShadowNode;

public class RNSSearchBarManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSSearchBarManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSSearchBarManagerDelegate(U u) {
        super(u);
    }

    public void setProperty(T t, String str, Object obj) {
        boolean z = true;
        str.hashCode();
        String str2 = null;
        boolean z2 = false;
        char c = 65535;
        switch (str.hashCode()) {
            case -1619312835:
                if (str.equals("hideNavigationBar")) {
                    c = 0;
                    break;
                }
                break;
            case -1465798051:
                if (str.equals("headerIconColor")) {
                    c = 1;
                    break;
                }
                break;
            case -1339545093:
                if (str.equals("autoCapitalize")) {
                    c = 2;
                    break;
                }
                break;
            case -1063571914:
                if (str.equals("textColor")) {
                    c = 3;
                    break;
                }
                break;
            case -336520619:
                if (str.equals("barTintColor")) {
                    c = 4;
                    break;
                }
                break;
            case -256845969:
                if (str.equals("hintTextColor")) {
                    c = 5;
                    break;
                }
                break;
            case -186579527:
                if (str.equals("hideWhenScrolling")) {
                    c = 6;
                    break;
                }
                break;
            case -146361959:
                if (str.equals("cancelButtonText")) {
                    c = 7;
                    break;
                }
                break;
            case -109380883:
                if (str.equals("disableBackButtonOverride")) {
                    c = 8;
                    break;
                }
                break;
            case -39414888:
                if (str.equals("shouldShowHintSearchIcon")) {
                    c = 9;
                    break;
                }
                break;
            case 598246771:
                if (str.equals(ReactTextInputShadowNode.PROP_PLACEHOLDER)) {
                    c = 10;
                    break;
                }
                break;
            case 1327599912:
                if (str.equals("tintColor")) {
                    c = 11;
                    break;
                }
                break;
            case 1584806451:
                if (str.equals("obscureBackground")) {
                    c = 12;
                    break;
                }
                break;
            case 1706976804:
                if (str.equals("inputType")) {
                    c = 13;
                    break;
                }
                break;
            case 1792938725:
                if (str.equals("placement")) {
                    c = 14;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                RNSSearchBarManagerInterface rNSSearchBarManagerInterface = (RNSSearchBarManagerInterface) this.mViewManager;
                if (obj != null) {
                    z2 = ((Boolean) obj).booleanValue();
                }
                rNSSearchBarManagerInterface.setHideNavigationBar(t, z2);
                return;
            case 1:
                ((RNSSearchBarManagerInterface) this.mViewManager).setHeaderIconColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case 2:
                ((RNSSearchBarManagerInterface) this.mViewManager).setAutoCapitalize(t, (String) obj);
                return;
            case 3:
                ((RNSSearchBarManagerInterface) this.mViewManager).setTextColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case 4:
                ((RNSSearchBarManagerInterface) this.mViewManager).setBarTintColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case 5:
                ((RNSSearchBarManagerInterface) this.mViewManager).setHintTextColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case 6:
                RNSSearchBarManagerInterface rNSSearchBarManagerInterface2 = (RNSSearchBarManagerInterface) this.mViewManager;
                if (obj != null) {
                    z2 = ((Boolean) obj).booleanValue();
                }
                rNSSearchBarManagerInterface2.setHideWhenScrolling(t, z2);
                return;
            case 7:
                RNSSearchBarManagerInterface rNSSearchBarManagerInterface3 = (RNSSearchBarManagerInterface) this.mViewManager;
                if (obj != null) {
                    str2 = (String) obj;
                }
                rNSSearchBarManagerInterface3.setCancelButtonText(t, str2);
                return;
            case 8:
                RNSSearchBarManagerInterface rNSSearchBarManagerInterface4 = (RNSSearchBarManagerInterface) this.mViewManager;
                if (obj != null) {
                    z2 = ((Boolean) obj).booleanValue();
                }
                rNSSearchBarManagerInterface4.setDisableBackButtonOverride(t, z2);
                return;
            case 9:
                RNSSearchBarManagerInterface rNSSearchBarManagerInterface5 = (RNSSearchBarManagerInterface) this.mViewManager;
                if (obj != null) {
                    z = ((Boolean) obj).booleanValue();
                }
                rNSSearchBarManagerInterface5.setShouldShowHintSearchIcon(t, z);
                return;
            case 10:
                RNSSearchBarManagerInterface rNSSearchBarManagerInterface6 = (RNSSearchBarManagerInterface) this.mViewManager;
                if (obj != null) {
                    str2 = (String) obj;
                }
                rNSSearchBarManagerInterface6.setPlaceholder(t, str2);
                return;
            case 11:
                ((RNSSearchBarManagerInterface) this.mViewManager).setTintColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case 12:
                RNSSearchBarManagerInterface rNSSearchBarManagerInterface7 = (RNSSearchBarManagerInterface) this.mViewManager;
                if (obj != null) {
                    z2 = ((Boolean) obj).booleanValue();
                }
                rNSSearchBarManagerInterface7.setObscureBackground(t, z2);
                return;
            case 13:
                RNSSearchBarManagerInterface rNSSearchBarManagerInterface8 = (RNSSearchBarManagerInterface) this.mViewManager;
                if (obj != null) {
                    str2 = (String) obj;
                }
                rNSSearchBarManagerInterface8.setInputType(t, str2);
                return;
            case 14:
                ((RNSSearchBarManagerInterface) this.mViewManager).setPlacement(t, (String) obj);
                return;
            default:
                super.setProperty(t, str, obj);
                return;
        }
    }

    public void receiveCommand(T t, String str, ReadableArray readableArray) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1833485118:
                if (str.equals("cancelSearch")) {
                    c = 0;
                    break;
                }
                break;
            case -1270906598:
                if (str.equals("clearText")) {
                    c = 1;
                    break;
                }
                break;
            case -664358976:
                if (str.equals("toggleCancelButton")) {
                    c = 2;
                    break;
                }
                break;
            case 3027047:
                if (str.equals("blur")) {
                    c = 3;
                    break;
                }
                break;
            case 97604824:
                if (str.equals("focus")) {
                    c = 4;
                    break;
                }
                break;
            case 1984984239:
                if (str.equals("setText")) {
                    c = 5;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                ((RNSSearchBarManagerInterface) this.mViewManager).cancelSearch(t);
                return;
            case 1:
                ((RNSSearchBarManagerInterface) this.mViewManager).clearText(t);
                return;
            case 2:
                ((RNSSearchBarManagerInterface) this.mViewManager).toggleCancelButton(t, readableArray.getBoolean(0));
                return;
            case 3:
                ((RNSSearchBarManagerInterface) this.mViewManager).blur(t);
                return;
            case 4:
                ((RNSSearchBarManagerInterface) this.mViewManager).focus(t);
                return;
            case 5:
                ((RNSSearchBarManagerInterface) this.mViewManager).setText(t, readableArray.getString(0));
                return;
            default:
                return;
        }
    }
}
