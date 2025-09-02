package com.facebook.react.internal.turbomodule.core.interfaces;

import com.facebook.react.bridge.NativeModule;
import java.util.Collection;
import java.util.List;

public interface TurboModuleRegistry {
    List<String> getEagerInitModuleNames();

    NativeModule getModule(String str);

    Collection<NativeModule> getModules();

    boolean hasModule(String str);

    void invalidate();
}
