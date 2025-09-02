package com.facebook.react.interfaces.fabric;

import com.facebook.react.bridge.NativeMap;

public interface SurfaceHandler {
    String getModuleName();

    int getSurfaceId();

    boolean isRunning();

    void setLayoutConstraints(int i, int i2, int i3, int i4, boolean z, boolean z2, float f);

    void setMountable(boolean z);

    void setProps(NativeMap nativeMap);
}
