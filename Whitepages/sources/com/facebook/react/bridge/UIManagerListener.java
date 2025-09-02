package com.facebook.react.bridge;

import com.facebook.react.common.annotations.UnstableReactNativeAPI;

@UnstableReactNativeAPI
public interface UIManagerListener {
    void didDispatchMountItems(UIManager uIManager);

    void didMountItems(UIManager uIManager);

    void didScheduleMountItems(UIManager uIManager);

    void willDispatchViewUpdates(UIManager uIManager);

    void willMountItems(UIManager uIManager);
}
