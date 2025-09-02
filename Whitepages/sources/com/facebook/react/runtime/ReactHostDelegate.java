package com.facebook.react.runtime;

import com.facebook.react.ReactPackage;
import com.facebook.react.ReactPackageTurboModuleManagerDelegate;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.common.annotations.UnstableReactNativeAPI;
import java.util.List;

@UnstableReactNativeAPI
public interface ReactHostDelegate {
    BindingsInstaller getBindingsInstaller();

    JSBundleLoader getJsBundleLoader();

    String getJsMainModulePath();

    JSRuntimeFactory getJsRuntimeFactory();

    List<ReactPackage> getReactPackages();

    ReactPackageTurboModuleManagerDelegate.Builder getTurboModuleManagerDelegateBuilder();

    void handleInstanceException(Exception exc);
}
