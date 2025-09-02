package com.facebook.react.bridge.interop;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

public final class InteropModuleRegistry {
    private final Map<Class<?>, Object> supportedModules = new LinkedHashMap();

    public final <T extends JavaScriptModule> boolean shouldReturnInteropModule(Class<T> cls) {
        Intrinsics.checkNotNullParameter(cls, "requestedModule");
        return checkReactFeatureFlagsConditions() && this.supportedModules.containsKey(cls);
    }

    public final <T extends JavaScriptModule> T getInteropModule(Class<T> cls) {
        Intrinsics.checkNotNullParameter(cls, "requestedModule");
        if (!checkReactFeatureFlagsConditions()) {
            return null;
        }
        T t = this.supportedModules.get(cls);
        if (t instanceof JavaScriptModule) {
            return (JavaScriptModule) t;
        }
        return null;
    }

    public final <T extends JavaScriptModule> void registerInteropModule(Class<T> cls, Object obj) {
        Intrinsics.checkNotNullParameter(cls, "interopModuleInterface");
        Intrinsics.checkNotNullParameter(obj, "interopModule");
        if (checkReactFeatureFlagsConditions()) {
            this.supportedModules.put(cls, obj);
        }
    }

    private final boolean checkReactFeatureFlagsConditions() {
        return ReactNativeFeatureFlags.enableFabricRenderer() && ReactNativeFeatureFlags.useFabricInterop();
    }
}
