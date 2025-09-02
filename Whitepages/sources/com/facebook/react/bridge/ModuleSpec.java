package com.facebook.react.bridge;

import com.facebook.common.logging.FLog;
import com.facebook.react.module.annotations.ReactModule;
import javax.inject.Provider;

public class ModuleSpec {
    private static final String TAG = "ModuleSpec";
    private final String mName;
    private final Provider mProvider;

    public static ModuleSpec viewManagerSpec(Provider provider) {
        return new ModuleSpec(provider);
    }

    public static ModuleSpec nativeModuleSpec(Class<? extends NativeModule> cls, Provider provider) {
        ReactModule reactModule = (ReactModule) cls.getAnnotation(ReactModule.class);
        if (reactModule != null) {
            return new ModuleSpec(provider, reactModule.name());
        }
        FLog.w(TAG, "Could not find @ReactModule annotation on " + cls.getName() + ". So creating the module eagerly to get the name. Consider adding an annotation to make this Lazy");
        return new ModuleSpec(provider, ((NativeModule) provider.get()).getName());
    }

    public static ModuleSpec nativeModuleSpec(String str, Provider provider) {
        return new ModuleSpec(provider, str);
    }

    private ModuleSpec(Provider provider) {
        this.mProvider = provider;
        this.mName = null;
    }

    private ModuleSpec(Provider provider, String str) {
        this.mProvider = provider;
        this.mName = str;
    }

    public String getName() {
        return this.mName;
    }

    public Provider getProvider() {
        return this.mProvider;
    }
}
