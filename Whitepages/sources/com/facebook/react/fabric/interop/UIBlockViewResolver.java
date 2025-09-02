package com.facebook.react.fabric.interop;

import android.view.View;
import com.facebook.react.common.annotations.UnstableReactNativeAPI;

@UnstableReactNativeAPI
public interface UIBlockViewResolver {
    View resolveView(int i);
}
