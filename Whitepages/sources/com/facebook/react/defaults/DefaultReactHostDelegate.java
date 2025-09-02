package com.facebook.react.defaults;

import com.facebook.jni.annotations.DoNotStrip;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactPackageTurboModuleManagerDelegate;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.common.annotations.UnstableReactNativeAPI;
import com.facebook.react.runtime.BindingsInstaller;
import com.facebook.react.runtime.JSRuntimeFactory;
import com.facebook.react.runtime.ReactHostDelegate;
import com.facebook.react.runtime.hermes.HermesInstance;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@DoNotStrip
@UnstableReactNativeAPI
public final class DefaultReactHostDelegate implements ReactHostDelegate {
    private final BindingsInstaller bindingsInstaller;
    private final Function1 exceptionHandler;
    private final JSBundleLoader jsBundleLoader;
    private final String jsMainModulePath;
    private final JSRuntimeFactory jsRuntimeFactory;
    private final List<ReactPackage> reactPackages;
    private final ReactPackageTurboModuleManagerDelegate.Builder turboModuleManagerDelegateBuilder;

    public DefaultReactHostDelegate(String str, JSBundleLoader jSBundleLoader, List<? extends ReactPackage> list, JSRuntimeFactory jSRuntimeFactory, BindingsInstaller bindingsInstaller2, Function1 function1, ReactPackageTurboModuleManagerDelegate.Builder builder) {
        Intrinsics.checkNotNullParameter(str, "jsMainModulePath");
        Intrinsics.checkNotNullParameter(jSBundleLoader, "jsBundleLoader");
        Intrinsics.checkNotNullParameter(list, "reactPackages");
        Intrinsics.checkNotNullParameter(jSRuntimeFactory, "jsRuntimeFactory");
        Intrinsics.checkNotNullParameter(function1, "exceptionHandler");
        Intrinsics.checkNotNullParameter(builder, "turboModuleManagerDelegateBuilder");
        this.jsMainModulePath = str;
        this.jsBundleLoader = jSBundleLoader;
        this.reactPackages = list;
        this.jsRuntimeFactory = jSRuntimeFactory;
        this.bindingsInstaller = bindingsInstaller2;
        this.exceptionHandler = function1;
        this.turboModuleManagerDelegateBuilder = builder;
    }

    public String getJsMainModulePath() {
        return this.jsMainModulePath;
    }

    public JSBundleLoader getJsBundleLoader() {
        return this.jsBundleLoader;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DefaultReactHostDelegate(String str, JSBundleLoader jSBundleLoader, List list, JSRuntimeFactory jSRuntimeFactory, BindingsInstaller bindingsInstaller2, Function1 function1, ReactPackageTurboModuleManagerDelegate.Builder builder, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, jSBundleLoader, (i & 4) != 0 ? CollectionsKt.emptyList() : list, (i & 8) != 0 ? new HermesInstance() : jSRuntimeFactory, (i & 16) != 0 ? null : bindingsInstaller2, (i & 32) != 0 ? new DefaultReactHostDelegate$$ExternalSyntheticLambda0() : function1, builder);
    }

    public List<ReactPackage> getReactPackages() {
        return this.reactPackages;
    }

    public JSRuntimeFactory getJsRuntimeFactory() {
        return this.jsRuntimeFactory;
    }

    public BindingsInstaller getBindingsInstaller() {
        return this.bindingsInstaller;
    }

    /* access modifiers changed from: private */
    public static final Unit _init_$lambda$0(Exception exc) {
        Intrinsics.checkNotNullParameter(exc, "it");
        throw exc;
    }

    public ReactPackageTurboModuleManagerDelegate.Builder getTurboModuleManagerDelegateBuilder() {
        return this.turboModuleManagerDelegateBuilder;
    }

    public void handleInstanceException(Exception exc) {
        Intrinsics.checkNotNullParameter(exc, "error");
        this.exceptionHandler.invoke(exc);
    }
}
