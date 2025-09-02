package com.facebook.react.uimanager;

import android.view.View;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.DynamicFromObject;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.BaseViewManager;
import kotlin.jvm.internal.Intrinsics;

public abstract class BaseViewManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode>> implements ViewManagerDelegate<T> {
    protected final U mViewManager;

    public void receiveCommand(T t, String str, ReadableArray readableArray) {
        Intrinsics.checkNotNullParameter(t, "view");
    }

    public BaseViewManagerDelegate(U u) {
        Intrinsics.checkNotNullParameter(u, "mViewManager");
        this.mViewManager = u;
    }

    public void setProperty(T t, String str, Object obj) {
        Intrinsics.checkNotNullParameter(t, "view");
        if (str != null) {
            float f = 1.0f;
            float f2 = 0.0f;
            float f3 = Float.NaN;
            boolean z = false;
            switch (str.hashCode()) {
                case -2018402664:
                    if (str.equals(ViewProps.MIX_BLEND_MODE)) {
                        this.mViewManager.setMixBlendMode(t, (String) obj);
                        return;
                    }
                    return;
                case -1898517556:
                    if (str.equals(ViewProps.ON_POINTER_ENTER_CAPTURE)) {
                        U u = this.mViewManager;
                        Boolean bool = (Boolean) obj;
                        if (bool != null) {
                            z = bool.booleanValue();
                        }
                        u.setPointerEnterCapture(t, z);
                        return;
                    }
                    return;
                case -1721943862:
                    if (str.equals(ViewProps.TRANSLATE_X)) {
                        U u2 = this.mViewManager;
                        Double d = (Double) obj;
                        if (d != null) {
                            f2 = (float) d.doubleValue();
                        }
                        u2.setTranslateX(t, f2);
                        return;
                    }
                    return;
                case -1721943861:
                    if (str.equals(ViewProps.TRANSLATE_Y)) {
                        U u3 = this.mViewManager;
                        Double d2 = (Double) obj;
                        if (d2 != null) {
                            f2 = (float) d2.doubleValue();
                        }
                        u3.setTranslateY(t, f2);
                        return;
                    }
                    return;
                case -1589741021:
                    if (str.equals(ViewProps.SHADOW_COLOR)) {
                        U u4 = this.mViewManager;
                        Integer color = obj == null ? 0 : ColorPropConverter.getColor(obj, t.getContext());
                        Intrinsics.checkNotNull(color);
                        u4.setShadowColor(t, color.intValue());
                        return;
                    }
                    return;
                case -1489432511:
                    if (str.equals(ViewProps.OUTLINE_COLOR)) {
                        this.mViewManager.setOutlineColor(t, (Integer) obj);
                        return;
                    }
                    return;
                case -1474494833:
                    if (str.equals(ViewProps.OUTLINE_STYLE)) {
                        this.mViewManager.setOutlineStyle(t, (String) obj);
                        return;
                    }
                    return;
                case -1471148380:
                    if (str.equals(ViewProps.OUTLINE_WIDTH)) {
                        U u5 = this.mViewManager;
                        Double d3 = (Double) obj;
                        if (d3 != null) {
                            f3 = (float) d3.doubleValue();
                        }
                        u5.setOutlineWidth(t, f3);
                        return;
                    }
                    return;
                case -1351902487:
                    if (str.equals(ViewProps.ON_CLICK)) {
                        U u6 = this.mViewManager;
                        Boolean bool2 = (Boolean) obj;
                        if (bool2 != null) {
                            z = bool2.booleanValue();
                        }
                        u6.setClick(t, z);
                        return;
                    }
                    return;
                case -1274492040:
                    if (str.equals(ViewProps.FILTER)) {
                        this.mViewManager.setFilter(t, (ReadableArray) obj);
                        return;
                    }
                    return;
                case -1267206133:
                    if (str.equals(ViewProps.OPACITY)) {
                        U u7 = this.mViewManager;
                        Double d4 = (Double) obj;
                        if (d4 != null) {
                            f = (float) d4.doubleValue();
                        }
                        u7.setOpacity(t, f);
                        return;
                    }
                    return;
                case -1247970794:
                    if (str.equals(ViewProps.ON_POINTER_OUT_CAPTURE)) {
                        U u8 = this.mViewManager;
                        Boolean bool3 = (Boolean) obj;
                        if (bool3 != null) {
                            z = bool3.booleanValue();
                        }
                        u8.setPointerOutCapture(t, z);
                        return;
                    }
                    return;
                case -1228066334:
                    if (str.equals(ViewProps.BORDER_TOP_LEFT_RADIUS)) {
                        U u9 = this.mViewManager;
                        Double d5 = (Double) obj;
                        if (d5 != null) {
                            f3 = (float) d5.doubleValue();
                        }
                        u9.setBorderTopLeftRadius(t, f3);
                        return;
                    }
                    return;
                case -1219666915:
                    if (str.equals(ViewProps.ON_CLICK_CAPTURE)) {
                        U u10 = this.mViewManager;
                        Boolean bool4 = (Boolean) obj;
                        if (bool4 != null) {
                            z = bool4.booleanValue();
                        }
                        u10.setClickCapture(t, z);
                        return;
                    }
                    return;
                case -1036769289:
                    if (str.equals(ViewProps.ON_POINTER_MOVE_CAPTURE)) {
                        U u11 = this.mViewManager;
                        Boolean bool5 = (Boolean) obj;
                        if (bool5 != null) {
                            z = bool5.booleanValue();
                        }
                        u11.setPointerMoveCapture(t, z);
                        return;
                    }
                    return;
                case -908189618:
                    if (str.equals(ViewProps.SCALE_X)) {
                        U u12 = this.mViewManager;
                        Double d6 = (Double) obj;
                        if (d6 != null) {
                            f = (float) d6.doubleValue();
                        }
                        u12.setScaleX(t, f);
                        return;
                    }
                    return;
                case -908189617:
                    if (str.equals(ViewProps.SCALE_Y)) {
                        U u13 = this.mViewManager;
                        Double d7 = (Double) obj;
                        if (d7 != null) {
                            f = (float) d7.doubleValue();
                        }
                        u13.setScaleY(t, f);
                        return;
                    }
                    return;
                case -877170387:
                    if (str.equals(ViewProps.TEST_ID)) {
                        this.mViewManager.setTestId(t, (String) obj);
                        return;
                    }
                    return;
                case -781597262:
                    if (str.equals(ViewProps.TRANSFORM_ORIGIN)) {
                        this.mViewManager.setTransformOrigin(t, (ReadableArray) obj);
                        return;
                    }
                    return;
                case -731417480:
                    if (str.equals(ViewProps.Z_INDEX)) {
                        U u14 = this.mViewManager;
                        Double d8 = (Double) obj;
                        if (d8 != null) {
                            f2 = (float) d8.doubleValue();
                        }
                        u14.setZIndex(t, f2);
                        return;
                    }
                    return;
                case -112141555:
                    if (str.equals(ViewProps.ON_POINTER_LEAVE_CAPTURE)) {
                        U u15 = this.mViewManager;
                        Boolean bool6 = (Boolean) obj;
                        if (bool6 != null) {
                            z = bool6.booleanValue();
                        }
                        u15.setPointerLeaveCapture(t, z);
                        return;
                    }
                    return;
                case -101663499:
                    if (str.equals(ViewProps.ACCESSIBILITY_HINT)) {
                        this.mViewManager.setAccessibilityHint(t, (String) obj);
                        return;
                    }
                    return;
                case -101359900:
                    if (str.equals(ViewProps.ACCESSIBILITY_ROLE)) {
                        this.mViewManager.setAccessibilityRole(t, (String) obj);
                        return;
                    }
                    return;
                case -80891667:
                    if (str.equals(ViewProps.RENDER_TO_HARDWARE_TEXTURE)) {
                        U u16 = this.mViewManager;
                        Boolean bool7 = (Boolean) obj;
                        if (bool7 != null) {
                            z = bool7.booleanValue();
                        }
                        u16.setRenderToHardwareTexture(t, z);
                        return;
                    }
                    return;
                case -40300674:
                    if (str.equals(ViewProps.ROTATION)) {
                        U u17 = this.mViewManager;
                        Double d9 = (Double) obj;
                        if (d9 != null) {
                            f2 = (float) d9.doubleValue();
                        }
                        u17.setRotation(t, f2);
                        return;
                    }
                    return;
                case -4379043:
                    if (str.equals(ViewProps.ELEVATION)) {
                        U u18 = this.mViewManager;
                        Double d10 = (Double) obj;
                        if (d10 != null) {
                            f2 = (float) d10.doubleValue();
                        }
                        u18.setElevation(t, f2);
                        return;
                    }
                    return;
                case 3506294:
                    if (str.equals(ViewProps.ROLE)) {
                        this.mViewManager.setRole(t, (String) obj);
                        return;
                    }
                    return;
                case 17941018:
                    if (str.equals(ViewProps.ON_POINTER_ENTER)) {
                        U u19 = this.mViewManager;
                        Boolean bool8 = (Boolean) obj;
                        if (bool8 != null) {
                            z = bool8.booleanValue();
                        }
                        u19.setPointerEnter(t, z);
                        return;
                    }
                    return;
                case 24119801:
                    if (str.equals(ViewProps.ON_POINTER_LEAVE)) {
                        U u20 = this.mViewManager;
                        Boolean bool9 = (Boolean) obj;
                        if (bool9 != null) {
                            z = bool9.booleanValue();
                        }
                        u20.setPointerLeave(t, z);
                        return;
                    }
                    return;
                case 36255470:
                    if (str.equals(ViewProps.ACCESSIBILITY_LIVE_REGION)) {
                        this.mViewManager.setAccessibilityLiveRegion(t, (String) obj);
                        return;
                    }
                    return;
                case 132353428:
                    if (str.equals(ViewProps.ON_POINTER_OVER_CAPTURE)) {
                        U u21 = this.mViewManager;
                        Boolean bool10 = (Boolean) obj;
                        if (bool10 != null) {
                            z = bool10.booleanValue();
                        }
                        u21.setPointerOverCapture(t, z);
                        return;
                    }
                    return;
                case 317346576:
                    if (str.equals(ViewProps.ON_POINTER_OUT)) {
                        U u22 = this.mViewManager;
                        Boolean bool11 = (Boolean) obj;
                        if (bool11 != null) {
                            z = bool11.booleanValue();
                        }
                        u22.setPointerOut(t, z);
                        return;
                    }
                    return;
                case 333432965:
                    if (str.equals(ViewProps.BORDER_TOP_RIGHT_RADIUS)) {
                        U u23 = this.mViewManager;
                        Double d11 = (Double) obj;
                        if (d11 != null) {
                            f3 = (float) d11.doubleValue();
                        }
                        u23.setBorderTopRightRadius(t, f3);
                        return;
                    }
                    return;
                case 581268560:
                    if (str.equals(ViewProps.BORDER_BOTTOM_LEFT_RADIUS)) {
                        U u24 = this.mViewManager;
                        Double d12 = (Double) obj;
                        if (d12 != null) {
                            f3 = (float) d12.doubleValue();
                        }
                        u24.setBorderBottomLeftRadius(t, f3);
                        return;
                    }
                    return;
                case 588239831:
                    if (str.equals(ViewProps.BORDER_BOTTOM_RIGHT_RADIUS)) {
                        U u25 = this.mViewManager;
                        Double d13 = (Double) obj;
                        if (d13 != null) {
                            f3 = (float) d13.doubleValue();
                        }
                        u25.setBorderBottomRightRadius(t, f3);
                        return;
                    }
                    return;
                case 743055051:
                    if (str.equals(ViewProps.BOX_SHADOW)) {
                        this.mViewManager.setBoxShadow(t, (ReadableArray) obj);
                        return;
                    }
                    return;
                case 746986311:
                    if (str.equals(ViewProps.IMPORTANT_FOR_ACCESSIBILITY)) {
                        this.mViewManager.setImportantForAccessibility(t, (String) obj);
                        return;
                    }
                    return;
                case 1052666732:
                    if (str.equals(ViewProps.TRANSFORM)) {
                        this.mViewManager.setTransform(t, (ReadableArray) obj);
                        return;
                    }
                    return;
                case 1146842694:
                    if (str.equals(ViewProps.ACCESSIBILITY_LABEL)) {
                        this.mViewManager.setAccessibilityLabel(t, (String) obj);
                        return;
                    }
                    return;
                case 1153872867:
                    if (str.equals(ViewProps.ACCESSIBILITY_STATE)) {
                        this.mViewManager.setViewState(t, (ReadableMap) obj);
                        return;
                    }
                    return;
                case 1156088003:
                    if (str.equals(ViewProps.ACCESSIBILITY_VALUE)) {
                        this.mViewManager.setAccessibilityValue(t, (ReadableMap) obj);
                        return;
                    }
                    return;
                case 1247744079:
                    if (str.equals(ViewProps.ON_POINTER_MOVE)) {
                        U u26 = this.mViewManager;
                        Boolean bool12 = (Boolean) obj;
                        if (bool12 != null) {
                            z = bool12.booleanValue();
                        }
                        u26.setPointerMove(t, z);
                        return;
                    }
                    return;
                case 1247809874:
                    if (str.equals(ViewProps.ON_POINTER_OVER)) {
                        U u27 = this.mViewManager;
                        Boolean bool13 = (Boolean) obj;
                        if (bool13 != null) {
                            z = bool13.booleanValue();
                        }
                        u27.setPointerOver(t, z);
                        return;
                    }
                    return;
                case 1287124693:
                    if (str.equals(ViewProps.BACKGROUND_COLOR)) {
                        U u28 = this.mViewManager;
                        Integer color2 = obj == null ? 0 : ColorPropConverter.getColor(obj, t.getContext());
                        Intrinsics.checkNotNull(color2);
                        u28.setBackgroundColor(t, color2.intValue());
                        return;
                    }
                    return;
                case 1349188574:
                    if (str.equals(ViewProps.BORDER_RADIUS)) {
                        U u29 = this.mViewManager;
                        Double d14 = (Double) obj;
                        if (d14 != null) {
                            f3 = (float) d14.doubleValue();
                        }
                        u29.setBorderRadius(t, f3);
                        return;
                    }
                    return;
                case 1407295349:
                    if (str.equals(ViewProps.OUTLINE_OFFSET)) {
                        U u30 = this.mViewManager;
                        Double d15 = (Double) obj;
                        if (d15 != null) {
                            f3 = (float) d15.doubleValue();
                        }
                        u30.setOutlineOffset(t, f3);
                        return;
                    }
                    return;
                case 1505602511:
                    if (str.equals(ViewProps.ACCESSIBILITY_ACTIONS)) {
                        this.mViewManager.setAccessibilityActions(t, (ReadableArray) obj);
                        return;
                    }
                    return;
                case 1761903244:
                    if (str.equals(ViewProps.ACCESSIBILITY_COLLECTION)) {
                        this.mViewManager.setAccessibilityCollection(t, (ReadableMap) obj);
                        return;
                    }
                    return;
                case 1865277756:
                    if (str.equals(ViewProps.ACCESSIBILITY_LABELLED_BY)) {
                        this.mViewManager.setAccessibilityLabelledBy(t, new DynamicFromObject(obj));
                        return;
                    }
                    return;
                case 1993034687:
                    if (str.equals(ViewProps.ACCESSIBILITY_COLLECTION_ITEM)) {
                        this.mViewManager.setAccessibilityCollectionItem(t, (ReadableMap) obj);
                        return;
                    }
                    return;
                case 2045685618:
                    if (str.equals(ViewProps.NATIVE_ID)) {
                        this.mViewManager.setNativeId(t, (String) obj);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }
}
