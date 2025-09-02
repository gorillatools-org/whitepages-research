package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.Dynamic;

public interface RNSVGFeCompositeManagerInterface<T extends View> {
    void setHeight(T t, Dynamic dynamic);

    void setIn1(T t, String str);

    void setIn2(T t, String str);

    void setK1(T t, float f);

    void setK2(T t, float f);

    void setK3(T t, float f);

    void setK4(T t, float f);

    void setOperator1(T t, String str);

    void setResult(T t, String str);

    void setWidth(T t, Dynamic dynamic);

    void setX(T t, Dynamic dynamic);

    void setY(T t, Dynamic dynamic);
}
