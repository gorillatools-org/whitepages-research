package com.facebook.react;

import com.facebook.fbreact.specs.NativeDevMenuSpec;
import com.facebook.fbreact.specs.NativeDevSettingsSpec;
import com.facebook.fbreact.specs.NativeDeviceInfoSpec;
import com.facebook.fbreact.specs.NativeExceptionsManagerSpec;
import com.facebook.fbreact.specs.NativeHeadlessJsTaskSupportSpec;
import com.facebook.fbreact.specs.NativePlatformConstantsAndroidSpec;
import com.facebook.fbreact.specs.NativeSourceCodeSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.common.ClassFinder;
import com.facebook.react.devsupport.LogBoxModule;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.core.ExceptionsManagerModule;
import com.facebook.react.modules.core.HeadlessJsTaskSupportModule;
import com.facebook.react.modules.core.TimingModule;
import com.facebook.react.modules.debug.DevMenuModule;
import com.facebook.react.modules.debug.DevSettingsModule;
import com.facebook.react.modules.debug.SourceCodeModule;
import com.facebook.react.modules.deviceinfo.DeviceInfoModule;
import com.facebook.react.modules.systeminfo.AndroidInfoModule;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ViewManagerResolver;
import com.facebook.systrace.Systrace;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

class CoreModulesPackage extends BaseReactPackage implements ReactPackageLogger {
    private final DefaultHardwareBackBtnHandler mHardwareBackBtnHandler;
    private final boolean mLazyViewManagersEnabled;
    private final int mMinTimeLeftInFrameForNonBatchedOperationMs;
    /* access modifiers changed from: private */
    public final ReactInstanceManager mReactInstanceManager;

    /* access modifiers changed from: private */
    public static /* synthetic */ Map lambda$fallbackForMissingClass$0(Map map) {
        return map;
    }

    public CoreModulesPackage(ReactInstanceManager reactInstanceManager, DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler, boolean z, int i) {
        this.mReactInstanceManager = reactInstanceManager;
        this.mHardwareBackBtnHandler = defaultHardwareBackBtnHandler;
        this.mLazyViewManagersEnabled = z;
        this.mMinTimeLeftInFrameForNonBatchedOperationMs = i;
    }

    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        if (!ClassFinder.canLoadClassesFromAnnotationProcessors()) {
            return fallbackForMissingClass();
        }
        try {
            return (ReactModuleInfoProvider) ClassFinder.findClass("com.facebook.react.CoreModulesPackage$$ReactModuleInfoProvider").newInstance();
        } catch (ClassNotFoundException unused) {
            return fallbackForMissingClass();
        } catch (InstantiationException e) {
            throw new RuntimeException("No ReactModuleInfoProvider for CoreModulesPackage$$ReactModuleInfoProvider", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("No ReactModuleInfoProvider for CoreModulesPackage$$ReactModuleInfoProvider", e2);
        }
    }

    private ReactModuleInfoProvider fallbackForMissingClass() {
        Class[] clsArr = {AndroidInfoModule.class, DeviceEventManagerModule.class, DeviceInfoModule.class, DevMenuModule.class, DevSettingsModule.class, ExceptionsManagerModule.class, LogBoxModule.class, HeadlessJsTaskSupportModule.class, SourceCodeModule.class, TimingModule.class, UIManagerModule.class};
        HashMap hashMap = new HashMap();
        for (int i = 0; i < 11; i++) {
            Class cls = clsArr[i];
            ReactModule reactModule = (ReactModule) cls.getAnnotation(ReactModule.class);
            hashMap.put(reactModule.name(), new ReactModuleInfo(reactModule.name(), cls.getName(), reactModule.canOverrideExistingModule(), reactModule.needsEagerInit(), reactModule.isCxxModule(), ReactModuleInfo.classIsTurboModule(cls)));
        }
        return new CoreModulesPackage$$ExternalSyntheticLambda0(hashMap);
    }

    public NativeModule getModule(String str, ReactApplicationContext reactApplicationContext) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2013505529:
                if (str.equals("LogBox")) {
                    c = 0;
                    break;
                }
                break;
            case -1789797270:
                if (str.equals("Timing")) {
                    c = 1;
                    break;
                }
                break;
            case -1633589448:
                if (str.equals(NativeDevSettingsSpec.NAME)) {
                    c = 2;
                    break;
                }
                break;
            case -1520650172:
                if (str.equals(NativeDeviceInfoSpec.NAME)) {
                    c = 3;
                    break;
                }
                break;
            case -1071344908:
                if (str.equals(NativeDevMenuSpec.NAME)) {
                    c = 4;
                    break;
                }
                break;
            case -1037217463:
                if (str.equals("DeviceEventManager")) {
                    c = 5;
                    break;
                }
                break;
            case -790603268:
                if (str.equals(NativePlatformConstantsAndroidSpec.NAME)) {
                    c = 6;
                    break;
                }
                break;
            case 512434409:
                if (str.equals(NativeExceptionsManagerSpec.NAME)) {
                    c = 7;
                    break;
                }
                break;
            case 881516744:
                if (str.equals(NativeSourceCodeSpec.NAME)) {
                    c = 8;
                    break;
                }
                break;
            case 1256514152:
                if (str.equals(NativeHeadlessJsTaskSupportSpec.NAME)) {
                    c = 9;
                    break;
                }
                break;
            case 1861242489:
                if (str.equals(UIManagerModule.NAME)) {
                    c = 10;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return new LogBoxModule(reactApplicationContext, this.mReactInstanceManager.getDevSupportManager());
            case 1:
                return new TimingModule(reactApplicationContext, this.mReactInstanceManager.getDevSupportManager());
            case 2:
                return new DevSettingsModule(reactApplicationContext, this.mReactInstanceManager.getDevSupportManager());
            case 3:
                return new DeviceInfoModule(reactApplicationContext);
            case 4:
                return new DevMenuModule(reactApplicationContext, this.mReactInstanceManager.getDevSupportManager());
            case 5:
                return new DeviceEventManagerModule(reactApplicationContext, this.mHardwareBackBtnHandler);
            case 6:
                return new AndroidInfoModule(reactApplicationContext);
            case 7:
                return new ExceptionsManagerModule(this.mReactInstanceManager.getDevSupportManager());
            case 8:
                return new SourceCodeModule(reactApplicationContext);
            case 9:
                return new HeadlessJsTaskSupportModule(reactApplicationContext);
            case 10:
                return createUIManager(reactApplicationContext);
            default:
                throw new IllegalArgumentException("In CoreModulesPackage, could not find Native module for " + str);
        }
    }

    private UIManagerModule createUIManager(ReactApplicationContext reactApplicationContext) {
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_START);
        Systrace.beginSection(0, "createUIManagerModule");
        try {
            if (this.mLazyViewManagersEnabled) {
                return new UIManagerModule(reactApplicationContext, (ViewManagerResolver) new ViewManagerResolver() {
                    public ViewManager getViewManager(String str) {
                        return CoreModulesPackage.this.mReactInstanceManager.createViewManager(str);
                    }

                    public Collection<String> getViewManagerNames() {
                        return CoreModulesPackage.this.mReactInstanceManager.getViewManagerNames();
                    }
                }, this.mMinTimeLeftInFrameForNonBatchedOperationMs);
            }
            UIManagerModule uIManagerModule = new UIManagerModule(reactApplicationContext, this.mReactInstanceManager.getOrCreateViewManagers(reactApplicationContext), this.mMinTimeLeftInFrameForNonBatchedOperationMs);
            Systrace.endSection(0);
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_END);
            return uIManagerModule;
        } finally {
            Systrace.endSection(0);
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_END);
        }
    }

    public void startProcessPackage() {
        ReactMarker.logMarker(ReactMarkerConstants.PROCESS_CORE_REACT_PACKAGE_START);
    }

    public void endProcessPackage() {
        ReactMarker.logMarker(ReactMarkerConstants.PROCESS_CORE_REACT_PACKAGE_END);
    }
}
