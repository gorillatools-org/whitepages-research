package com.rnlineargradient;

import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;

public abstract class LinearGradientManagerSpec<T extends View> extends SimpleViewManager<T> {
    public abstract void setAngle(T t, float f);

    public abstract void setAngleCenter(T t, ReadableMap readableMap);

    public abstract void setBorderRadii(T t, ReadableArray readableArray);

    public abstract void setColors(T t, ReadableArray readableArray);

    public abstract void setEndPoint(T t, ReadableMap readableMap);

    public abstract void setLocations(T t, ReadableArray readableArray);

    public abstract void setStartPoint(T t, ReadableMap readableMap);

    public abstract void setUseAngle(T t, boolean z);
}
