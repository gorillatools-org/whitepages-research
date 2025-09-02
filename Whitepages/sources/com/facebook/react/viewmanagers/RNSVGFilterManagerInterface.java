package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.Dynamic;

public interface RNSVGFilterManagerInterface<T extends View> {
    void setFilterUnits(T t, String str);

    void setHeight(T t, Dynamic dynamic);

    void setName(T t, String str);

    void setPrimitiveUnits(T t, String str);

    void setWidth(T t, Dynamic dynamic);

    void setX(T t, Dynamic dynamic);

    void setY(T t, Dynamic dynamic);
}
