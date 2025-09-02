package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.Dynamic;

public interface RNSVGFeBlendManagerInterface<T extends View> {
    void setHeight(T t, Dynamic dynamic);

    void setIn1(T t, String str);

    void setIn2(T t, String str);

    void setMode(T t, String str);

    void setResult(T t, String str);

    void setWidth(T t, Dynamic dynamic);

    void setX(T t, Dynamic dynamic);

    void setY(T t, Dynamic dynamic);
}
