package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.Dynamic;

public interface RNSVGFeFloodManagerInterface<T extends View> {
    void setFloodColor(T t, Dynamic dynamic);

    void setFloodOpacity(T t, float f);

    void setHeight(T t, Dynamic dynamic);

    void setResult(T t, String str);

    void setWidth(T t, Dynamic dynamic);

    void setX(T t, Dynamic dynamic);

    void setY(T t, Dynamic dynamic);
}
