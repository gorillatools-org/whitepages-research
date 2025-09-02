package com.facebook.react.defaults;

import android.app.Application;
import android.content.Context;
import com.facebook.react.JSEngineResolutionAlgorithm;
import com.facebook.react.ReactHost;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactPackageTurboModuleManagerDelegate;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UIManagerProvider;
import com.facebook.react.common.annotations.UnstableReactNativeAPI;
import com.facebook.react.defaults.DefaultTurboModuleManagerDelegate;
import com.facebook.react.fabric.ComponentFactory;
import com.facebook.react.fabric.FabricUIManagerProviderImpl;
import com.facebook.react.uimanager.ViewManagerRegistry;
import com.facebook.react.uimanager.ViewManagerResolver;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

public abstract class DefaultReactNativeHost extends ReactNativeHost {
    /* access modifiers changed from: protected */
    public Boolean isHermesEnabled() {
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean isNewArchEnabled() {
        return false;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected DefaultReactNativeHost(Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
    }

    /* access modifiers changed from: protected */
    public ReactPackageTurboModuleManagerDelegate.Builder getReactPackageTurboModuleManagerDelegateBuilder() {
        if (isNewArchEnabled()) {
            return new DefaultTurboModuleManagerDelegate.Builder();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public UIManagerProvider getUIManagerProvider() {
        if (isNewArchEnabled()) {
            return new DefaultReactNativeHost$$ExternalSyntheticLambda0(this);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static final UIManager getUIManagerProvider$lambda$0(DefaultReactNativeHost defaultReactNativeHost, ReactApplicationContext reactApplicationContext) {
        ViewManagerRegistry viewManagerRegistry;
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactApplicationContext");
        ComponentFactory componentFactory = new ComponentFactory();
        DefaultComponentsRegistry.register(componentFactory);
        if (defaultReactNativeHost.getLazyViewManagersEnabled()) {
            viewManagerRegistry = new ViewManagerRegistry((ViewManagerResolver) new DefaultReactNativeHost$getUIManagerProvider$1$viewManagerRegistry$1(defaultReactNativeHost));
        } else {
            viewManagerRegistry = new ViewManagerRegistry(defaultReactNativeHost.getReactInstanceManager().getOrCreateViewManagers(reactApplicationContext));
        }
        return new FabricUIManagerProviderImpl(componentFactory, viewManagerRegistry).createUIManager(reactApplicationContext);
    }

    /* access modifiers changed from: protected */
    public JSEngineResolutionAlgorithm getJSEngineResolutionAlgorithm() {
        Boolean isHermesEnabled = isHermesEnabled();
        if (Intrinsics.areEqual((Object) isHermesEnabled, (Object) Boolean.TRUE)) {
            return JSEngineResolutionAlgorithm.HERMES;
        }
        if (Intrinsics.areEqual((Object) isHermesEnabled, (Object) Boolean.FALSE)) {
            return JSEngineResolutionAlgorithm.JSC;
        }
        if (isHermesEnabled == null) {
            return null;
        }
        throw new NoWhenBranchMatchedException();
    }

    @UnstableReactNativeAPI
    public final ReactHost toReactHost$ReactAndroid_release(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        List<ReactPackage> packages = getPackages();
        Intrinsics.checkNotNullExpressionValue(packages, "getPackages(...)");
        String jSMainModuleName = getJSMainModuleName();
        Intrinsics.checkNotNullExpressionValue(jSMainModuleName, "getJSMainModuleName(...)");
        String bundleAssetName = getBundleAssetName();
        if (bundleAssetName == null) {
            bundleAssetName = FirebaseAnalytics.Param.INDEX;
        }
        String str = bundleAssetName;
        String jSBundleFile = getJSBundleFile();
        Boolean isHermesEnabled = isHermesEnabled();
        return DefaultReactHost.getDefaultReactHost$default(context, packages, jSMainModuleName, str, jSBundleFile, isHermesEnabled != null ? isHermesEnabled.booleanValue() : true, getUseDeveloperSupport(), (List) null, 128, (Object) null);
    }
}
