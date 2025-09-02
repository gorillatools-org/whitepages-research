package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.Dynamic;

public interface RNSVGFeGaussianBlurManagerInterface<T extends View> {
    void setEdgeMode(T t, String str);

    void setHeight(T t, Dynamic dynamic);

    void setIn1(T t, String str);

    void setResult(T t, String str);

    void setStdDeviationX(T t, float f);

    void setStdDeviationY(T t, float f);

    void setWidth(T t, Dynamic dynamic);

    void setX(T t, Dynamic dynamic);

    void setY(T t, Dynamic dynamic);
}
