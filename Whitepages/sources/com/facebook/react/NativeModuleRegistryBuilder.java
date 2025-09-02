package com.facebook.react;

import com.facebook.react.bridge.ModuleHolder;
import com.facebook.react.bridge.NativeModuleRegistry;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.HashMap;
import kotlin.jvm.internal.Intrinsics;

public final class NativeModuleRegistryBuilder {
    private final HashMap<String, ModuleHolder> modules;
    private final ReactApplicationContext reactApplicationContext;

    public NativeModuleRegistryBuilder(ReactApplicationContext reactApplicationContext2) {
        Intrinsics.checkNotNullParameter(reactApplicationContext2, "reactApplicationContext");
        this.reactApplicationContext = reactApplicationContext2;
        this.modules = new HashMap<>();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public NativeModuleRegistryBuilder(ReactApplicationContext reactApplicationContext2, ReactInstanceManager reactInstanceManager) {
        this(reactApplicationContext2);
        Intrinsics.checkNotNullParameter(reactApplicationContext2, "reactApplicationContext");
        Intrinsics.checkNotNullParameter(reactInstanceManager, "reactInstanceManager");
    }

    public final void processPackage(ReactPackage reactPackage) {
        Iterable<ModuleHolder> iterable;
        Intrinsics.checkNotNullParameter(reactPackage, "reactPackage");
        if (reactPackage instanceof LazyReactPackage) {
            iterable = ((LazyReactPackage) reactPackage).getNativeModuleIterator(this.reactApplicationContext);
        } else if (reactPackage instanceof BaseReactPackage) {
            iterable = ((BaseReactPackage) reactPackage).getNativeModuleIterator$ReactAndroid_release(this.reactApplicationContext);
        } else {
            iterable = ReactPackageHelper.INSTANCE.getNativeModuleIterator(reactPackage, this.reactApplicationContext);
        }
        for (ModuleHolder next : iterable) {
            String name = next.getName();
            ModuleHolder moduleHolder = this.modules.get(name);
            if (moduleHolder == null || next.getCanOverrideExistingModule()) {
                this.modules.put(name, next);
            } else {
                String className = moduleHolder.getClassName();
                throw new IllegalStateException(("\nNative module " + name + " tried to override " + className + ".\n\nCheck the getPackages() method in MainApplication.java, it might be that module is being created twice. \nIf this was your intention, set canOverrideExistingModule=true. This error may also be present if the \npackage is present only once in getPackages() but is also automatically added later during build time \nby autolinking. Try removing the existing entry and rebuild.\n").toString());
            }
        }
    }

    public final NativeModuleRegistry build() {
        return new NativeModuleRegistry(this.reactApplicationContext, this.modules);
    }
}
