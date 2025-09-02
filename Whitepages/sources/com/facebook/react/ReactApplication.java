package com.facebook.react;

public interface ReactApplication {
    ReactHost getReactHost() {
        return null;
    }

    ReactNativeHost getReactNativeHost();
}
