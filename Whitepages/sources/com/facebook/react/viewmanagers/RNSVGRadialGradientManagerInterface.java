package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;

public interface RNSVGRadialGradientManagerInterface<T extends View> {
    void setClipPath(T t, String str);

    void setClipRule(T t, int i);

    void setCx(T t, Dynamic dynamic);

    void setCy(T t, Dynamic dynamic);

    void setDisplay(T t, String str);

    void setFx(T t, Dynamic dynamic);

    void setFy(T t, Dynamic dynamic);

    void setGradient(T t, ReadableArray readableArray);

    void setGradientTransform(T t, ReadableArray readableArray);

    void setGradientUnits(T t, int i);

    void setMarkerEnd(T t, String str);

    void setMarkerMid(T t, String str);

    void setMarkerStart(T t, String str);

    void setMask(T t, String str);

    void setMatrix(T t, ReadableArray readableArray);

    void setName(T t, String str);

    void setOpacity(T t, float f);

    void setPointerEvents(T t, String str);

    void setResponsible(T t, boolean z);

    void setRx(T t, Dynamic dynamic);

    void setRy(T t, Dynamic dynamic);
}
