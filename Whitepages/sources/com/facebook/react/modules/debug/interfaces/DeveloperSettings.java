package com.facebook.react.modules.debug.interfaces;

import com.facebook.react.packagerconnection.PackagerConnectionSettings;

public interface DeveloperSettings {
    static /* synthetic */ void isStartSamplingProfilerOnInit$annotations() {
    }

    void addMenuItem(String str);

    PackagerConnectionSettings getPackagerConnectionSettings();

    boolean isAnimationFpsDebugEnabled();

    boolean isDeviceDebugEnabled();

    boolean isElementInspectorEnabled();

    boolean isFpsDebugEnabled();

    boolean isHotModuleReplacementEnabled();

    boolean isJSDevModeEnabled();

    boolean isJSMinifyEnabled();

    boolean isRemoteJSDebugEnabled();

    boolean isStartSamplingProfilerOnInit();

    void setAnimationFpsDebugEnabled(boolean z);

    void setDeviceDebugEnabled(boolean z);

    void setElementInspectorEnabled(boolean z);

    void setFpsDebugEnabled(boolean z);

    void setHotModuleReplacementEnabled(boolean z);

    void setJSDevModeEnabled(boolean z);

    void setJSMinifyEnabled(boolean z);

    void setRemoteJSDebugEnabled(boolean z);

    void setStartSamplingProfilerOnInit(boolean z);
}
