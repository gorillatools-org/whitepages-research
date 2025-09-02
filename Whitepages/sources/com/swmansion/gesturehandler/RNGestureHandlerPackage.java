package com.swmansion.gesturehandler;

import com.facebook.react.BaseReactPackage;
import com.facebook.react.ViewManagerOnDemandReactPackage;
import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.swmansion.gesturehandler.react.RNGestureHandlerButtonViewManager;
import com.swmansion.gesturehandler.react.RNGestureHandlerModule;
import com.swmansion.gesturehandler.react.RNGestureHandlerRootViewManager;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

public final class RNGestureHandlerPackage extends BaseReactPackage implements ViewManagerOnDemandReactPackage {
    private final Lazy viewManagers$delegate = LazyKt.lazy(new RNGestureHandlerPackage$$ExternalSyntheticLambda0());

    private final Map getViewManagers() {
        return (Map) this.viewManagers$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public static final Map viewManagers_delegate$lambda$2() {
        return MapsKt.mapOf(TuplesKt.to(RNGestureHandlerRootViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new RNGestureHandlerPackage$$ExternalSyntheticLambda2())), TuplesKt.to(RNGestureHandlerButtonViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new RNGestureHandlerPackage$$ExternalSyntheticLambda3())));
    }

    /* access modifiers changed from: private */
    public static final NativeModule viewManagers_delegate$lambda$2$lambda$0() {
        return new RNGestureHandlerRootViewManager();
    }

    /* access modifiers changed from: private */
    public static final NativeModule viewManagers_delegate$lambda$2$lambda$1() {
        return new RNGestureHandlerButtonViewManager();
    }

    public List createViewManagers(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        return CollectionsKt.listOf(new RNGestureHandlerRootViewManager(), new RNGestureHandlerButtonViewManager());
    }

    public List getViewManagerNames(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        return CollectionsKt.toList(getViewManagers().keySet());
    }

    /* access modifiers changed from: protected */
    public List getViewManagers(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        return CollectionsKt.toMutableList(getViewManagers().values());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0017, code lost:
        r2 = r2.getProvider();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.react.uimanager.ViewManager createViewManager(com.facebook.react.bridge.ReactApplicationContext r2, java.lang.String r3) {
        /*
            r1 = this;
            java.lang.String r0 = "reactContext"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r2 = "viewManagerName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r2)
            java.util.Map r2 = r1.getViewManagers()
            java.lang.Object r2 = r2.get(r3)
            com.facebook.react.bridge.ModuleSpec r2 = (com.facebook.react.bridge.ModuleSpec) r2
            r3 = 0
            if (r2 == 0) goto L_0x0024
            javax.inject.Provider r2 = r2.getProvider()
            if (r2 == 0) goto L_0x0024
            java.lang.Object r2 = r2.get()
            com.facebook.react.bridge.NativeModule r2 = (com.facebook.react.bridge.NativeModule) r2
            goto L_0x0025
        L_0x0024:
            r2 = r3
        L_0x0025:
            boolean r0 = r2 instanceof com.facebook.react.uimanager.ViewManager
            if (r0 == 0) goto L_0x002c
            r3 = r2
            com.facebook.react.uimanager.ViewManager r3 = (com.facebook.react.uimanager.ViewManager) r3
        L_0x002c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.RNGestureHandlerPackage.createViewManager(com.facebook.react.bridge.ReactApplicationContext, java.lang.String):com.facebook.react.uimanager.ViewManager");
    }

    public NativeModule getModule(String str, ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        if (Intrinsics.areEqual((Object) str, (Object) "RNGestureHandlerModule")) {
            return new RNGestureHandlerModule(reactApplicationContext);
        }
        return null;
    }

    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        try {
            Object newInstance = Class.forName("com.swmansion.gesturehandler.RNGestureHandlerPackage$$ReactModuleInfoProvider").getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
            Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type com.facebook.react.module.model.ReactModuleInfoProvider");
            return (ReactModuleInfoProvider) newInstance;
        } catch (ClassNotFoundException unused) {
            return new RNGestureHandlerPackage$$ExternalSyntheticLambda1();
        } catch (InstantiationException e) {
            throw new RuntimeException("No ReactModuleInfoProvider for RNGestureHandlerPackage$$ReactModuleInfoProvider", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("No ReactModuleInfoProvider for RNGestureHandlerPackage$$ReactModuleInfoProvider", e2);
        }
    }

    /* access modifiers changed from: private */
    public static final Map getReactModuleInfoProvider$lambda$3() {
        Class<RNGestureHandlerModule> cls = RNGestureHandlerModule.class;
        Annotation annotation = cls.getAnnotation(ReactModule.class);
        Intrinsics.checkNotNull(annotation);
        ReactModule reactModule = (ReactModule) annotation;
        String name = reactModule.name();
        String name2 = cls.getName();
        Intrinsics.checkNotNullExpressionValue(name2, "getName(...)");
        return MapsKt.mutableMapOf(TuplesKt.to("RNGestureHandlerModule", new ReactModuleInfo(name, name2, reactModule.canOverrideExistingModule(), reactModule.needsEagerInit(), reactModule.isCxxModule(), true)));
    }
}
