package com.facebook.react;

import com.facebook.react.bridge.ModuleHolder;
import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.uimanager.ViewManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Provider;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

public abstract class BaseReactPackage implements ReactPackage {
    public abstract NativeModule getModule(String str, ReactApplicationContext reactApplicationContext);

    public abstract ReactModuleInfoProvider getReactModuleInfoProvider();

    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        throw new UnsupportedOperationException("createNativeModules method is not supported. Use getModule() method instead.");
    }

    public final Iterable<ModuleHolder> getNativeModuleIterator$ReactAndroid_release(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        return new BaseReactPackage$getNativeModuleIterator$$inlined$Iterable$1(getReactModuleInfoProvider().getReactModuleInfos().entrySet().iterator(), this, reactApplicationContext);
    }

    /* access modifiers changed from: protected */
    public List<ModuleSpec> getViewManagers(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        return CollectionsKt.emptyList();
    }

    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        List<ModuleSpec> viewManagers = getViewManagers(reactApplicationContext);
        Collection collection = viewManagers;
        if (collection == null || collection.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (ModuleSpec provider : viewManagers) {
            Object obj = provider.getProvider().get();
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.facebook.react.uimanager.ViewManager<*, *>");
            arrayList.add((ViewManager) obj);
        }
        return arrayList;
    }

    private final class ModuleHolderProvider implements Provider {
        private final String name;
        private final ReactApplicationContext reactContext;
        final /* synthetic */ BaseReactPackage this$0;

        public ModuleHolderProvider(BaseReactPackage baseReactPackage, String str, ReactApplicationContext reactApplicationContext) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
            this.this$0 = baseReactPackage;
            this.name = str;
            this.reactContext = reactApplicationContext;
        }

        public NativeModule get() {
            return this.this$0.getModule(this.name, this.reactContext);
        }
    }
}
