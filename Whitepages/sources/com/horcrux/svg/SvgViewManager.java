package com.horcrux.svg;

import android.graphics.Rect;
import android.util.SparseArray;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerDelegate;
import com.facebook.react.viewmanagers.RNSVGSvgViewAndroidManagerInterface;
import com.facebook.react.views.view.ReactViewGroup;
import com.facebook.react.views.view.ReactViewManager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

class SvgViewManager extends ReactViewManager implements RNSVGSvgViewAndroidManagerInterface<SvgView> {
    public static final String REACT_CLASS = "RNSVGSvgViewAndroid";
    private static final SparseArray<Runnable> mTagToRunnable = new SparseArray<>();
    private static final SparseArray<SvgView> mTagToSvgView = new SparseArray<>();
    private final ViewManagerDelegate<SvgView> mDelegate = new RNSVGSvgViewAndroidManagerDelegate(this);

    public boolean needsCustomLayoutForChildren() {
        return true;
    }

    /* access modifiers changed from: protected */
    public ViewManagerDelegate getDelegate() {
        return this.mDelegate;
    }

    static void setSvgView(int i, SvgView svgView) {
        mTagToSvgView.put(i, svgView);
        SparseArray<Runnable> sparseArray = mTagToRunnable;
        Runnable runnable = sparseArray.get(i);
        if (runnable != null) {
            runnable.run();
            sparseArray.delete(i);
        }
    }

    static void runWhenViewIsAvailable(int i, Runnable runnable) {
        mTagToRunnable.put(i, runnable);
    }

    static SvgView getSvgViewByTag(int i) {
        return mTagToSvgView.get(i);
    }

    public String getName() {
        return REACT_CLASS;
    }

    public ReactViewGroup createViewInstance(ThemedReactContext themedReactContext) {
        return new SvgView(themedReactContext);
    }

    public void updateExtraData(ReactViewGroup reactViewGroup, Object obj) {
        super.updateExtraData(reactViewGroup, obj);
        reactViewGroup.invalidate();
    }

    public void onDropViewInstance(ReactViewGroup reactViewGroup) {
        super.onDropViewInstance(reactViewGroup);
        mTagToSvgView.remove(reactViewGroup.getId());
    }

    @ReactProp(customType = "Color", name = "color")
    public void setColor(SvgView svgView, Integer num) {
        svgView.setCurrentColor(num);
    }

    @ReactProp(name = "minX")
    public void setMinX(SvgView svgView, float f) {
        svgView.setMinX(f);
    }

    @ReactProp(name = "minY")
    public void setMinY(SvgView svgView, float f) {
        svgView.setMinY(f);
    }

    @ReactProp(name = "vbWidth")
    public void setVbWidth(SvgView svgView, float f) {
        svgView.setVbWidth(f);
    }

    @ReactProp(name = "vbHeight")
    public void setVbHeight(SvgView svgView, float f) {
        svgView.setVbHeight(f);
    }

    @ReactProp(name = "bbWidth")
    public void setBbWidth(SvgView svgView, Dynamic dynamic) {
        svgView.setBbWidth(dynamic);
    }

    @ReactProp(name = "bbHeight")
    public void setBbHeight(SvgView svgView, Dynamic dynamic) {
        svgView.setBbHeight(dynamic);
    }

    @ReactProp(name = "align")
    public void setAlign(SvgView svgView, String str) {
        svgView.setAlign(str);
    }

    @ReactProp(name = "meetOrSlice")
    public void setMeetOrSlice(SvgView svgView, int i) {
        svgView.setMeetOrSlice(i);
    }

    @ReactProp(name = "pointerEvents")
    public void setPointerEvents(SvgView svgView, String str) {
        try {
            Class<? super Object> superclass = svgView.getClass().getSuperclass();
            if (superclass != null) {
                Method declaredMethod = superclass.getDeclaredMethod("setPointerEvents", new Class[]{PointerEvents.class});
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(svgView, new Object[]{PointerEvents.valueOf(str.toUpperCase(Locale.US).replace("-", "_"))});
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void setHasTVPreferredFocus(SvgView svgView, boolean z) {
        super.setTVPreferredFocus(svgView, z);
    }

    public void setBorderBottomColor(SvgView svgView, Integer num) {
        super.setBorderColor(svgView, 4, num);
    }

    public void setNextFocusDown(SvgView svgView, int i) {
        super.nextFocusDown(svgView, i);
    }

    public void setBorderRightColor(SvgView svgView, Integer num) {
        super.setBorderColor(svgView, 2, num);
    }

    public void setNextFocusRight(SvgView svgView, int i) {
        super.nextFocusRight(svgView, i);
    }

    public void setBorderLeftColor(SvgView svgView, Integer num) {
        super.setBorderColor(svgView, 1, num);
    }

    public void setBorderColor(SvgView svgView, Integer num) {
        super.setBorderColor(svgView, 0, num);
    }

    public void setRemoveClippedSubviews(SvgView svgView, boolean z) {
        super.setRemoveClippedSubviews(svgView, z);
    }

    public void setNextFocusForward(SvgView svgView, int i) {
        super.nextFocusForward(svgView, i);
    }

    public void setNextFocusUp(SvgView svgView, int i) {
        super.nextFocusUp(svgView, i);
    }

    public void setAccessible(SvgView svgView, boolean z) {
        super.setAccessible(svgView, z);
    }

    public void setBorderStartColor(SvgView svgView, Integer num) {
        super.setBorderColor(svgView, 5, num);
    }

    public void setBorderEndColor(SvgView svgView, Integer num) {
        super.setBorderColor(svgView, 6, num);
    }

    public void setFocusable(SvgView svgView, boolean z) {
        super.setFocusable(svgView, z);
    }

    public void setNativeBackgroundAndroid(SvgView svgView, ReadableMap readableMap) {
        super.setNativeBackground(svgView, readableMap);
    }

    public void setNativeForegroundAndroid(SvgView svgView, ReadableMap readableMap) {
        super.setNativeForeground(svgView, readableMap);
    }

    public void setBackfaceVisibility(SvgView svgView, String str) {
        super.setBackfaceVisibility(svgView, str);
    }

    public void setBorderStyle(SvgView svgView, String str) {
        super.setBorderStyle(svgView, str);
    }

    public void setNeedsOffscreenAlphaCompositing(SvgView svgView, boolean z) {
        super.setNeedsOffscreenAlphaCompositing(svgView, z);
    }

    /* renamed from: com.horcrux.svg.SvgViewManager$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.facebook.react.bridge.ReadableType[] r0 = com.facebook.react.bridge.ReadableType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$facebook$react$bridge$ReadableType = r0
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Map     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Number     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Null     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.SvgViewManager.AnonymousClass1.<clinit>():void");
        }
    }

    public void setHitSlop(SvgView svgView, Dynamic dynamic) {
        int i = AnonymousClass1.$SwitchMap$com$facebook$react$bridge$ReadableType[dynamic.getType().ordinal()];
        if (i == 1) {
            ReadableMap asMap = dynamic.asMap();
            int i2 = 0;
            int pixelFromDIP = asMap.hasKey(ViewProps.LEFT) ? (int) PixelUtil.toPixelFromDIP(asMap.getDouble(ViewProps.LEFT)) : 0;
            int pixelFromDIP2 = asMap.hasKey(ViewProps.TOP) ? (int) PixelUtil.toPixelFromDIP(asMap.getDouble(ViewProps.TOP)) : 0;
            int pixelFromDIP3 = asMap.hasKey(ViewProps.RIGHT) ? (int) PixelUtil.toPixelFromDIP(asMap.getDouble(ViewProps.RIGHT)) : 0;
            if (asMap.hasKey(ViewProps.BOTTOM)) {
                i2 = (int) PixelUtil.toPixelFromDIP(asMap.getDouble(ViewProps.BOTTOM));
            }
            svgView.setHitSlopRect(new Rect(pixelFromDIP, pixelFromDIP2, pixelFromDIP3, i2));
        } else if (i != 2) {
            if (i != 3) {
                FLog.w(ReactConstants.TAG, "Invalid type for 'hitSlop' value " + dynamic.getType());
            }
            svgView.setHitSlopRect((Rect) null);
        } else {
            int pixelFromDIP4 = (int) PixelUtil.toPixelFromDIP(dynamic.asDouble());
            svgView.setHitSlopRect(new Rect(pixelFromDIP4, pixelFromDIP4, pixelFromDIP4, pixelFromDIP4));
        }
    }

    public void setBorderTopColor(SvgView svgView, Integer num) {
        super.setBorderColor(svgView, 3, num);
    }

    public void setNextFocusLeft(SvgView svgView, int i) {
        super.nextFocusLeft(svgView, i);
    }

    public void setBorderBlockColor(SvgView svgView, Integer num) {
        super.setBorderColor(svgView, 9, num);
    }

    public void setBorderBlockEndColor(SvgView svgView, Integer num) {
        super.setBorderColor(svgView, 10, num);
    }

    public void setBorderBlockStartColor(SvgView svgView, Integer num) {
        super.setBorderColor(svgView, 11, num);
    }

    public void setBorderRadius(SvgView svgView, Dynamic dynamic) {
        super.setBorderRadius((ReactViewGroup) svgView, 0, dynamic);
    }

    public void setBorderTopLeftRadius(SvgView svgView, Dynamic dynamic) {
        super.setBorderRadius((ReactViewGroup) svgView, 1, dynamic);
    }

    public void setBorderTopRightRadius(SvgView svgView, Dynamic dynamic) {
        super.setBorderRadius((ReactViewGroup) svgView, 2, dynamic);
    }

    public void setBorderBottomRightRadius(SvgView svgView, Dynamic dynamic) {
        super.setBorderRadius((ReactViewGroup) svgView, 3, dynamic);
    }

    public void setBorderBottomLeftRadius(SvgView svgView, Dynamic dynamic) {
        super.setBorderRadius((ReactViewGroup) svgView, 4, dynamic);
    }

    public void setBorderTopStartRadius(SvgView svgView, Dynamic dynamic) {
        super.setBorderRadius((ReactViewGroup) svgView, 5, dynamic);
    }

    public void setBorderTopEndRadius(SvgView svgView, Dynamic dynamic) {
        super.setBorderRadius((ReactViewGroup) svgView, 6, dynamic);
    }

    public void setBorderBottomStartRadius(SvgView svgView, Dynamic dynamic) {
        super.setBorderRadius((ReactViewGroup) svgView, 7, dynamic);
    }

    public void setBorderBottomEndRadius(SvgView svgView, Dynamic dynamic) {
        super.setBorderRadius((ReactViewGroup) svgView, 8, dynamic);
    }

    public void setBorderEndEndRadius(SvgView svgView, Dynamic dynamic) {
        super.setBorderRadius((ReactViewGroup) svgView, 9, dynamic);
    }

    public void setBorderEndStartRadius(SvgView svgView, Dynamic dynamic) {
        super.setBorderRadius((ReactViewGroup) svgView, 10, dynamic);
    }

    public void setBorderStartEndRadius(SvgView svgView, Dynamic dynamic) {
        super.setBorderRadius((ReactViewGroup) svgView, 11, dynamic);
    }

    public void setBorderStartStartRadius(SvgView svgView, Dynamic dynamic) {
        super.setBorderRadius((ReactViewGroup) svgView, 12, dynamic);
    }
}
