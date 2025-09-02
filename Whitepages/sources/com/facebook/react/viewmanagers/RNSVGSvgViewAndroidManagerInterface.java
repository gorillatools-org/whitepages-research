package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableMap;

public interface RNSVGSvgViewAndroidManagerInterface<T extends View> {
    void setAccessible(T t, boolean z);

    void setAlign(T t, String str);

    void setBackfaceVisibility(T t, String str);

    void setBbHeight(T t, Dynamic dynamic);

    void setBbWidth(T t, Dynamic dynamic);

    void setBorderBlockColor(T t, Integer num);

    void setBorderBlockEndColor(T t, Integer num);

    void setBorderBlockStartColor(T t, Integer num);

    void setBorderBottomColor(T t, Integer num);

    void setBorderBottomEndRadius(T t, Dynamic dynamic);

    void setBorderBottomLeftRadius(T t, Dynamic dynamic);

    void setBorderBottomRightRadius(T t, Dynamic dynamic);

    void setBorderBottomStartRadius(T t, Dynamic dynamic);

    void setBorderColor(T t, Integer num);

    void setBorderEndColor(T t, Integer num);

    void setBorderEndEndRadius(T t, Dynamic dynamic);

    void setBorderEndStartRadius(T t, Dynamic dynamic);

    void setBorderLeftColor(T t, Integer num);

    void setBorderRadius(T t, Dynamic dynamic);

    void setBorderRightColor(T t, Integer num);

    void setBorderStartColor(T t, Integer num);

    void setBorderStartEndRadius(T t, Dynamic dynamic);

    void setBorderStartStartRadius(T t, Dynamic dynamic);

    void setBorderStyle(T t, String str);

    void setBorderTopColor(T t, Integer num);

    void setBorderTopEndRadius(T t, Dynamic dynamic);

    void setBorderTopLeftRadius(T t, Dynamic dynamic);

    void setBorderTopRightRadius(T t, Dynamic dynamic);

    void setBorderTopStartRadius(T t, Dynamic dynamic);

    void setColor(T t, Integer num);

    void setFocusable(T t, boolean z);

    void setHasTVPreferredFocus(T t, boolean z);

    void setHitSlop(T t, Dynamic dynamic);

    void setMeetOrSlice(T t, int i);

    void setMinX(T t, float f);

    void setMinY(T t, float f);

    void setNativeBackgroundAndroid(T t, ReadableMap readableMap);

    void setNativeForegroundAndroid(T t, ReadableMap readableMap);

    void setNeedsOffscreenAlphaCompositing(T t, boolean z);

    void setNextFocusDown(T t, int i);

    void setNextFocusForward(T t, int i);

    void setNextFocusLeft(T t, int i);

    void setNextFocusRight(T t, int i);

    void setNextFocusUp(T t, int i);

    void setPointerEvents(T t, String str);

    void setRemoveClippedSubviews(T t, boolean z);

    void setVbHeight(T t, float f);

    void setVbWidth(T t, float f);
}
