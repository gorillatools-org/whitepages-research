package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableMap;

public interface AnimatedNodeWithUpdateableConfig {
    void onUpdateConfig(ReadableMap readableMap);
}
