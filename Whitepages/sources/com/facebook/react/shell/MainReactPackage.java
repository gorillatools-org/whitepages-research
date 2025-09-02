package com.facebook.react.shell;

import android.annotation.SuppressLint;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.fbreact.specs.NativeAnimatedModuleSpec;
import com.facebook.fbreact.specs.NativeBlobModuleSpec;
import com.facebook.fbreact.specs.NativeDialogManagerAndroidSpec;
import com.facebook.fbreact.specs.NativeFileReaderModuleSpec;
import com.facebook.fbreact.specs.NativeNetworkingAndroidSpec;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.react.BaseReactPackage;
import com.facebook.react.ViewManagerOnDemandReactPackage;
import com.facebook.react.animated.NativeAnimatedModule;
import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.common.ClassFinder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.modules.accessibilityinfo.AccessibilityInfoModule;
import com.facebook.react.modules.appearance.AppearanceModule;
import com.facebook.react.modules.appstate.AppStateModule;
import com.facebook.react.modules.blob.BlobModule;
import com.facebook.react.modules.blob.FileReaderModule;
import com.facebook.react.modules.camera.ImageStoreManager;
import com.facebook.react.modules.clipboard.ClipboardModule;
import com.facebook.react.modules.devloading.DevLoadingModule;
import com.facebook.react.modules.devtoolsruntimesettings.ReactDevToolsRuntimeSettingsModule;
import com.facebook.react.modules.dialog.DialogModule;
import com.facebook.react.modules.fresco.FrescoModule;
import com.facebook.react.modules.i18nmanager.I18nManagerModule;
import com.facebook.react.modules.image.ImageLoaderModule;
import com.facebook.react.modules.intent.IntentModule;
import com.facebook.react.modules.network.NetworkingModule;
import com.facebook.react.modules.permissions.PermissionsModule;
import com.facebook.react.modules.reactdevtoolssettings.ReactDevToolsSettingsManagerModule;
import com.facebook.react.modules.share.ShareModule;
import com.facebook.react.modules.sound.SoundManagerModule;
import com.facebook.react.modules.statusbar.StatusBarModule;
import com.facebook.react.modules.toast.ToastModule;
import com.facebook.react.modules.vibration.VibrationModule;
import com.facebook.react.modules.websocket.WebSocketModule;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.views.drawer.ReactDrawerLayoutManager;
import com.facebook.react.views.image.GlobalImageLoadListener;
import com.facebook.react.views.image.ReactCallerContextFactory;
import com.facebook.react.views.image.ReactImageManager;
import com.facebook.react.views.modal.ReactModalHostManager;
import com.facebook.react.views.progressbar.ReactProgressBarViewManager;
import com.facebook.react.views.safeareaview.ReactSafeAreaViewManager;
import com.facebook.react.views.scroll.ReactHorizontalScrollContainerViewManager;
import com.facebook.react.views.scroll.ReactHorizontalScrollViewManager;
import com.facebook.react.views.scroll.ReactScrollViewManager;
import com.facebook.react.views.swiperefresh.SwipeRefreshLayoutManager;
import com.facebook.react.views.switchview.ReactSwitchManager;
import com.facebook.react.views.text.ReactRawTextManager;
import com.facebook.react.views.text.ReactTextViewManager;
import com.facebook.react.views.text.ReactVirtualTextViewManager;
import com.facebook.react.views.text.frescosupport.FrescoBasedReactTextInlineImageViewManager;
import com.facebook.react.views.textinput.ReactTextInputManager;
import com.facebook.react.views.unimplementedview.ReactUnimplementedViewManager;
import com.facebook.react.views.view.ReactViewManager;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

public final class MainReactPackage extends BaseReactPackage implements ViewManagerOnDemandReactPackage {
    private final MainPackageConfig config;
    @SuppressLint({"VisibleForTests"})
    private final Map<String, ModuleSpec> viewManagersMap;

    public MainReactPackage() {
        this((MainPackageConfig) null, 1, (DefaultConstructorMarker) null);
    }

    /* access modifiers changed from: private */
    public static final Map fallbackForMissingClass$lambda$19(Map map) {
        return map;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ MainReactPackage(MainPackageConfig mainPackageConfig, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : mainPackageConfig);
    }

    public MainReactPackage(MainPackageConfig mainPackageConfig) {
        this.config = mainPackageConfig;
        this.viewManagersMap = MapsKt.mapOf(TuplesKt.to(ReactDrawerLayoutManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new MainReactPackage$$ExternalSyntheticLambda1())), TuplesKt.to(ReactHorizontalScrollViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new MainReactPackage$$ExternalSyntheticLambda9())), TuplesKt.to(ReactHorizontalScrollContainerViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new MainReactPackage$$ExternalSyntheticLambda10())), TuplesKt.to(ReactProgressBarViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new MainReactPackage$$ExternalSyntheticLambda11())), TuplesKt.to(ReactSafeAreaViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new MainReactPackage$$ExternalSyntheticLambda12())), TuplesKt.to(ReactScrollViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new MainReactPackage$$ExternalSyntheticLambda13())), TuplesKt.to(ReactSwitchManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new MainReactPackage$$ExternalSyntheticLambda14())), TuplesKt.to(SwipeRefreshLayoutManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new MainReactPackage$$ExternalSyntheticLambda15())), TuplesKt.to(FrescoBasedReactTextInlineImageViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new MainReactPackage$$ExternalSyntheticLambda16())), TuplesKt.to(ReactImageManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new MainReactPackage$$ExternalSyntheticLambda17())), TuplesKt.to(ReactModalHostManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new MainReactPackage$$ExternalSyntheticLambda2())), TuplesKt.to(ReactRawTextManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new MainReactPackage$$ExternalSyntheticLambda3())), TuplesKt.to(ReactTextInputManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new MainReactPackage$$ExternalSyntheticLambda4())), TuplesKt.to(ReactTextViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new MainReactPackage$$ExternalSyntheticLambda5())), TuplesKt.to("RCTView", ModuleSpec.viewManagerSpec(new MainReactPackage$$ExternalSyntheticLambda6())), TuplesKt.to(ReactVirtualTextViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new MainReactPackage$$ExternalSyntheticLambda7())), TuplesKt.to(ReactUnimplementedViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new MainReactPackage$$ExternalSyntheticLambda8())));
    }

    public NativeModule getModule(String str, ReactApplicationContext reactApplicationContext) {
        NativeModule appearanceModule;
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        ImagePipelineConfig imagePipelineConfig = null;
        switch (str.hashCode()) {
            case -2115067288:
                if (!str.equals("ToastAndroid")) {
                    return null;
                }
                return new ToastModule(reactApplicationContext);
            case -1962922905:
                if (!str.equals("ImageStoreManager")) {
                    return null;
                }
                return new ImageStoreManager(reactApplicationContext);
            case -1850625090:
                if (!str.equals("SoundManager")) {
                    return null;
                }
                return new SoundManagerModule(reactApplicationContext);
            case -1654566518:
                if (!str.equals(NativeDialogManagerAndroidSpec.NAME)) {
                    return null;
                }
                return new DialogModule(reactApplicationContext);
            case -1344126773:
                if (!str.equals(NativeFileReaderModuleSpec.NAME)) {
                    return null;
                }
                return new FileReaderModule(reactApplicationContext);
            case -1067020766:
                if (!str.equals("ReactDevToolsRuntimeSettingsModule")) {
                    return null;
                }
                return new ReactDevToolsRuntimeSettingsModule(reactApplicationContext);
            case -1062061717:
                if (!str.equals("PermissionsAndroid")) {
                    return null;
                }
                return new PermissionsModule(reactApplicationContext);
            case -657277650:
                if (!str.equals("ImageLoader")) {
                    return null;
                }
                return new ImageLoaderModule(reactApplicationContext);
            case -585704955:
                if (!str.equals("ReactDevToolsSettingsManager")) {
                    return null;
                }
                return new ReactDevToolsSettingsManagerModule(reactApplicationContext);
            case -570370161:
                if (!str.equals("I18nManager")) {
                    return null;
                }
                return new I18nManagerModule(reactApplicationContext);
            case -504784764:
                if (str.equals("Appearance")) {
                    appearanceModule = new AppearanceModule(reactApplicationContext, (AppearanceModule.OverrideColorScheme) null, 2, (DefaultConstructorMarker) null);
                    break;
                } else {
                    return null;
                }
            case -457866500:
                if (!str.equals("AccessibilityInfo")) {
                    return null;
                }
                return new AccessibilityInfoModule(reactApplicationContext);
            case -382654004:
                if (!str.equals("StatusBarManager")) {
                    return null;
                }
                return new StatusBarModule(reactApplicationContext);
            case -254310125:
                if (!str.equals("WebSocketModule")) {
                    return null;
                }
                return new WebSocketModule(reactApplicationContext);
            case -99249460:
                if (!str.equals("DevLoadingView")) {
                    return null;
                }
                return new DevLoadingModule(reactApplicationContext);
            case 163245714:
                if (str.equals(FrescoModule.NAME)) {
                    MainPackageConfig mainPackageConfig = this.config;
                    if (mainPackageConfig != null) {
                        imagePipelineConfig = mainPackageConfig.getFrescoConfig();
                    }
                    appearanceModule = new FrescoModule(reactApplicationContext, true, imagePipelineConfig);
                    break;
                } else {
                    return null;
                }
            case 403570038:
                if (!str.equals("Clipboard")) {
                    return null;
                }
                return new ClipboardModule(reactApplicationContext);
            case 563961875:
                if (!str.equals("IntentAndroid")) {
                    return null;
                }
                return new IntentModule(reactApplicationContext);
            case 1221389072:
                if (!str.equals("AppState")) {
                    return null;
                }
                return new AppStateModule(reactApplicationContext);
            case 1515242260:
                if (!str.equals(NativeNetworkingAndroidSpec.NAME)) {
                    return null;
                }
                return new NetworkingModule(reactApplicationContext);
            case 1547941001:
                if (!str.equals(NativeBlobModuleSpec.NAME)) {
                    return null;
                }
                return new BlobModule(reactApplicationContext);
            case 1555425035:
                if (!str.equals("ShareModule")) {
                    return null;
                }
                return new ShareModule(reactApplicationContext);
            case 1721274886:
                if (!str.equals(NativeAnimatedModuleSpec.NAME)) {
                    return null;
                }
                return new NativeAnimatedModule(reactApplicationContext);
            case 1922110066:
                if (!str.equals("Vibration")) {
                    return null;
                }
                return new VibrationModule(reactApplicationContext);
            default:
                return null;
        }
        return appearanceModule;
    }

    public List<ViewManager<?, ?>> createViewManagers(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        return CollectionsKt.listOf(new ReactDrawerLayoutManager(), new ReactHorizontalScrollViewManager(), new ReactHorizontalScrollContainerViewManager(), new ReactProgressBarViewManager(), new ReactScrollViewManager(), new ReactSwitchManager(), new ReactSafeAreaViewManager(), new SwipeRefreshLayoutManager(), new FrescoBasedReactTextInlineImageViewManager((AbstractDraweeControllerBuilder) null, (Object) null, 3, (DefaultConstructorMarker) null), new ReactImageManager((AbstractDraweeControllerBuilder) null, (GlobalImageLoadListener) null, (ReactCallerContextFactory) null, 7, (DefaultConstructorMarker) null), new ReactModalHostManager(), new ReactRawTextManager(), new ReactTextInputManager(), new ReactTextViewManager(), new ReactViewManager(), new ReactVirtualTextViewManager(), new ReactUnimplementedViewManager());
    }

    public final Map<String, ModuleSpec> getViewManagersMap() {
        return this.viewManagersMap;
    }

    /* access modifiers changed from: private */
    public static final NativeModule viewManagersMap$lambda$0() {
        return new ReactDrawerLayoutManager();
    }

    /* access modifiers changed from: private */
    public static final NativeModule viewManagersMap$lambda$1() {
        return new ReactHorizontalScrollViewManager();
    }

    /* access modifiers changed from: private */
    public static final NativeModule viewManagersMap$lambda$2() {
        return new ReactHorizontalScrollContainerViewManager();
    }

    /* access modifiers changed from: private */
    public static final NativeModule viewManagersMap$lambda$3() {
        return new ReactProgressBarViewManager();
    }

    /* access modifiers changed from: private */
    public static final NativeModule viewManagersMap$lambda$4() {
        return new ReactSafeAreaViewManager();
    }

    /* access modifiers changed from: private */
    public static final NativeModule viewManagersMap$lambda$5() {
        return new ReactScrollViewManager();
    }

    /* access modifiers changed from: private */
    public static final NativeModule viewManagersMap$lambda$6() {
        return new ReactSwitchManager();
    }

    /* access modifiers changed from: private */
    public static final NativeModule viewManagersMap$lambda$7() {
        return new SwipeRefreshLayoutManager();
    }

    /* access modifiers changed from: private */
    public static final NativeModule viewManagersMap$lambda$8() {
        return new FrescoBasedReactTextInlineImageViewManager((AbstractDraweeControllerBuilder) null, (Object) null, 3, (DefaultConstructorMarker) null);
    }

    /* access modifiers changed from: private */
    public static final NativeModule viewManagersMap$lambda$9() {
        return new ReactImageManager((AbstractDraweeControllerBuilder) null, (GlobalImageLoadListener) null, (ReactCallerContextFactory) null, 7, (DefaultConstructorMarker) null);
    }

    /* access modifiers changed from: private */
    public static final NativeModule viewManagersMap$lambda$10() {
        return new ReactModalHostManager();
    }

    /* access modifiers changed from: private */
    public static final NativeModule viewManagersMap$lambda$11() {
        return new ReactRawTextManager();
    }

    /* access modifiers changed from: private */
    public static final NativeModule viewManagersMap$lambda$12() {
        return new ReactTextInputManager();
    }

    /* access modifiers changed from: private */
    public static final NativeModule viewManagersMap$lambda$13() {
        return new ReactTextViewManager();
    }

    /* access modifiers changed from: private */
    public static final NativeModule viewManagersMap$lambda$14() {
        return new ReactViewManager();
    }

    /* access modifiers changed from: private */
    public static final NativeModule viewManagersMap$lambda$15() {
        return new ReactVirtualTextViewManager();
    }

    /* access modifiers changed from: private */
    public static final NativeModule viewManagersMap$lambda$16() {
        return new ReactUnimplementedViewManager();
    }

    public List<ModuleSpec> getViewManagers(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        return CollectionsKt.toList(this.viewManagersMap.values());
    }

    public Collection<String> getViewManagerNames(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        return this.viewManagersMap.keySet();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0015, code lost:
        r2 = r2.getProvider();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.react.uimanager.ViewManager<?, ?> createViewManager(com.facebook.react.bridge.ReactApplicationContext r2, java.lang.String r3) {
        /*
            r1 = this;
            java.lang.String r0 = "reactContext"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r2 = "viewManagerName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r2)
            java.util.Map<java.lang.String, com.facebook.react.bridge.ModuleSpec> r2 = r1.viewManagersMap
            java.lang.Object r2 = r2.get(r3)
            com.facebook.react.bridge.ModuleSpec r2 = (com.facebook.react.bridge.ModuleSpec) r2
            r3 = 0
            if (r2 == 0) goto L_0x0022
            javax.inject.Provider r2 = r2.getProvider()
            if (r2 == 0) goto L_0x0022
            java.lang.Object r2 = r2.get()
            com.facebook.react.bridge.NativeModule r2 = (com.facebook.react.bridge.NativeModule) r2
            goto L_0x0023
        L_0x0022:
            r2 = r3
        L_0x0023:
            boolean r0 = r2 instanceof com.facebook.react.uimanager.ViewManager
            if (r0 == 0) goto L_0x002a
            r3 = r2
            com.facebook.react.uimanager.ViewManager r3 = (com.facebook.react.uimanager.ViewManager) r3
        L_0x002a:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.shell.MainReactPackage.createViewManager(com.facebook.react.bridge.ReactApplicationContext, java.lang.String):com.facebook.react.uimanager.ViewManager");
    }

    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        if (!ClassFinder.canLoadClassesFromAnnotationProcessors()) {
            return fallbackForMissingClass();
        }
        try {
            Class<?> findClass = ClassFinder.findClass("com.facebook.react.shell.MainReactPackage$$ReactModuleInfoProvider");
            ReactModuleInfoProvider reactModuleInfoProvider = null;
            Object newInstance = findClass != null ? findClass.newInstance() : null;
            if (newInstance instanceof ReactModuleInfoProvider) {
                reactModuleInfoProvider = (ReactModuleInfoProvider) newInstance;
            }
            return reactModuleInfoProvider == null ? fallbackForMissingClass() : reactModuleInfoProvider;
        } catch (ClassNotFoundException unused) {
            return fallbackForMissingClass();
        } catch (InstantiationException e) {
            throw new RuntimeException("No ReactModuleInfoProvider for MainReactPackage$$ReactModuleInfoProvider", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("No ReactModuleInfoProvider for MainReactPackage$$ReactModuleInfoProvider", e2);
        }
    }

    private final ReactModuleInfoProvider fallbackForMissingClass() {
        Class<ReactModule> cls;
        Class[] clsArr = {AccessibilityInfoModule.class, AppearanceModule.class, AppStateModule.class, BlobModule.class, DevLoadingModule.class, FileReaderModule.class, ClipboardModule.class, DialogModule.class, FrescoModule.class, I18nManagerModule.class, ImageLoaderModule.class, ImageStoreManager.class, IntentModule.class, NativeAnimatedModule.class, NetworkingModule.class, PermissionsModule.class, ReactDevToolsSettingsManagerModule.class, ReactDevToolsRuntimeSettingsModule.class, ShareModule.class, StatusBarModule.class, SoundManagerModule.class, ToastModule.class, VibrationModule.class, WebSocketModule.class};
        ArrayList<Class> arrayList = new ArrayList<>();
        int i = 0;
        while (true) {
            cls = ReactModule.class;
            if (i >= 24) {
                break;
            }
            Class cls2 = clsArr[i];
            if (cls2.isAnnotationPresent(cls)) {
                arrayList.add(cls2);
            }
            i++;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(arrayList, 10)), 16));
        for (Class cls3 : arrayList) {
            Annotation annotation = cls3.getAnnotation(cls);
            if (annotation != null) {
                ReactModule reactModule = (ReactModule) annotation;
                String name = reactModule.name();
                String name2 = reactModule.name();
                String name3 = cls3.getName();
                Intrinsics.checkNotNullExpressionValue(name3, "getName(...)");
                Pair pair = TuplesKt.to(name, new ReactModuleInfo(name2, name3, reactModule.canOverrideExistingModule(), reactModule.needsEagerInit(), reactModule.isCxxModule(), ReactModuleInfo.Companion.classIsTurboModule(cls3)));
                linkedHashMap.put(pair.getFirst(), pair.getSecond());
            } else {
                throw new IllegalStateException("Required value was null.");
            }
        }
        return new MainReactPackage$$ExternalSyntheticLambda0(linkedHashMap);
    }
}
