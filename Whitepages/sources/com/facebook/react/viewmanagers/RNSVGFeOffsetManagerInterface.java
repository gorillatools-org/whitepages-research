package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.Dynamic;

public interface RNSVGFeOffsetManagerInterface<T extends View> {
    void setDx(T t, Dynamic dynamic);

    void setDy(T t, Dynamic dynamic);

    void setHeight(T t, Dynamic dynamic);

    void setIn1(T t, String str);

    void setResult(T t, String str);

    void setWidth(T t, Dynamic dynamic);

    void setX(T t, Dynamic dynamic);

    void setY(T t, Dynamic dynamic);
}
