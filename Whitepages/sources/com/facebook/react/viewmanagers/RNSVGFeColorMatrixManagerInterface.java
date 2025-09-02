package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;

public interface RNSVGFeColorMatrixManagerInterface<T extends View> {
    void setHeight(T t, Dynamic dynamic);

    void setIn1(T t, String str);

    void setResult(T t, String str);

    void setType(T t, String str);

    void setValues(T t, ReadableArray readableArray);

    void setWidth(T t, Dynamic dynamic);

    void setX(T t, Dynamic dynamic);

    void setY(T t, Dynamic dynamic);
}
