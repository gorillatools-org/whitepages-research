package com.facebook.react.uimanager;

import com.facebook.proguard.annotations.DoNotStripAny;
import com.facebook.react.bridge.NativeMap;
import com.facebook.react.bridge.RuntimeExecutor;
import com.facebook.soloader.SoLoader;

@DoNotStripAny
public final class UIConstantsProviderBinding {
    public static final UIConstantsProviderBinding INSTANCE = new UIConstantsProviderBinding();

    @DoNotStripAny
    public interface ConstantsForViewManagerProvider {
        NativeMap getConstantsForViewManager(String str);
    }

    @DoNotStripAny
    public interface ConstantsProvider {
        NativeMap getConstants();
    }

    @DoNotStripAny
    public interface DefaultEventTypesProvider {
        NativeMap getDefaultEventTypes();
    }

    public static final native void install(RuntimeExecutor runtimeExecutor, DefaultEventTypesProvider defaultEventTypesProvider, ConstantsForViewManagerProvider constantsForViewManagerProvider, ConstantsProvider constantsProvider);

    private UIConstantsProviderBinding() {
    }

    static {
        SoLoader.loadLibrary("uimanagerjni");
    }
}
