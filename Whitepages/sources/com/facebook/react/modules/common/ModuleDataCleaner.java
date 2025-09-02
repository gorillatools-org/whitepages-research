package com.facebook.react.modules.common;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.ReactConstants;
import java.util.Collection;
import kotlin.jvm.internal.Intrinsics;

public final class ModuleDataCleaner {
    public static final ModuleDataCleaner INSTANCE = new ModuleDataCleaner();

    public interface Cleanable {
        void clearSensitiveData();
    }

    private ModuleDataCleaner() {
    }

    public static final void cleanDataFromModules(ReactContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Collection<NativeModule> nativeModules = reactContext.getNativeModules();
        Intrinsics.checkNotNullExpressionValue(nativeModules, "getNativeModules(...)");
        for (NativeModule nativeModule : nativeModules) {
            if (nativeModule instanceof Cleanable) {
                String name = nativeModule.getName();
                FLog.d(ReactConstants.TAG, "Cleaning data from " + name);
                ((Cleanable) nativeModule).clearSensitiveData();
            }
        }
    }
}
