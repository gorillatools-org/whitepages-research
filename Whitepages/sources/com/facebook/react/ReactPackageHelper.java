package com.facebook.react;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ModuleHolder;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.common.ReactConstants;
import kotlin.jvm.internal.Intrinsics;

public final class ReactPackageHelper {
    public static final ReactPackageHelper INSTANCE = new ReactPackageHelper();

    private ReactPackageHelper() {
    }

    public final Iterable<ModuleHolder> getNativeModuleIterator(ReactPackage reactPackage, ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactPackage, "reactPackage");
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactApplicationContext");
        String simpleName = reactPackage.getClass().getSimpleName();
        FLog.d(ReactConstants.TAG, simpleName + " is not a LazyReactPackage, falling back to old version.");
        return new ReactPackageHelper$getNativeModuleIterator$$inlined$Iterable$1(reactPackage.createNativeModules(reactApplicationContext));
    }
}
