package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.ViewManagerWithGeneratedInterface;

public interface UnimplementedNativeViewManagerInterface<T extends View> extends ViewManagerWithGeneratedInterface {
    void setName(T t, String str);
}
