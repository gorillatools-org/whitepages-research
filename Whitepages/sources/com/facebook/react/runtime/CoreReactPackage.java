package com.facebook.react.runtime;

import com.facebook.fbreact.specs.NativeDevMenuSpec;
import com.facebook.fbreact.specs.NativeDevSettingsSpec;
import com.facebook.fbreact.specs.NativeDeviceInfoSpec;
import com.facebook.fbreact.specs.NativeExceptionsManagerSpec;
import com.facebook.fbreact.specs.NativePlatformConstantsAndroidSpec;
import com.facebook.fbreact.specs.NativeSourceCodeSpec;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.BaseReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.common.ClassFinder;
import com.facebook.react.devsupport.LogBoxModule;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.core.ExceptionsManagerModule;
import com.facebook.react.modules.debug.DevMenuModule;
import com.facebook.react.modules.debug.DevSettingsModule;
import com.facebook.react.modules.debug.SourceCodeModule;
import com.facebook.react.modules.deviceinfo.DeviceInfoModule;
import com.facebook.react.modules.systeminfo.AndroidInfoModule;
import java.util.HashMap;
import java.util.Map;

class CoreReactPackage extends BaseReactPackage {
    private final DevSupportManager mDevSupportManager;
    private final DefaultHardwareBackBtnHandler mHardwareBackBtnHandler;

    /* access modifiers changed from: private */
    public static /* synthetic */ Map lambda$fallbackForMissingClass$0(Map map) {
        return map;
    }

    public CoreReactPackage(DevSupportManager devSupportManager, DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler) {
        this.mDevSupportManager = devSupportManager;
        this.mHardwareBackBtnHandler = defaultHardwareBackBtnHandler;
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
            case -1633589448:
                if (str.equals(NativeDevSettingsSpec.NAME)) {
                    c = 1;
                    break;
                }
                break;
            case -1520650172:
                if (str.equals(NativeDeviceInfoSpec.NAME)) {
                    c = 2;
                    break;
                }
                break;
            case -1071344908:
                if (str.equals(NativeDevMenuSpec.NAME)) {
                    c = 3;
                    break;
                }
                break;
            case -1037217463:
                if (str.equals("DeviceEventManager")) {
                    c = 4;
                    break;
                }
                break;
            case -790603268:
                if (str.equals(NativePlatformConstantsAndroidSpec.NAME)) {
                    c = 5;
                    break;
                }
                break;
            case 512434409:
                if (str.equals(NativeExceptionsManagerSpec.NAME)) {
                    c = 6;
                    break;
                }
                break;
            case 881516744:
                if (str.equals(NativeSourceCodeSpec.NAME)) {
                    c = 7;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return new LogBoxModule(reactApplicationContext, this.mDevSupportManager);
            case 1:
                return new DevSettingsModule(reactApplicationContext, this.mDevSupportManager);
            case 2:
                return new DeviceInfoModule(reactApplicationContext);
            case 3:
                return new DevMenuModule(reactApplicationContext, this.mDevSupportManager);
            case 4:
                return new DeviceEventManagerModule(reactApplicationContext, this.mHardwareBackBtnHandler);
            case 5:
                return new AndroidInfoModule(reactApplicationContext);
            case 6:
                return new ExceptionsManagerModule(this.mDevSupportManager);
            case 7:
                return new SourceCodeModule(reactApplicationContext);
            default:
                return null;
        }
    }

    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        Class<CoreReactPackage> cls = CoreReactPackage.class;
        if (!ClassFinder.canLoadClassesFromAnnotationProcessors()) {
            return fallbackForMissingClass();
        }
        try {
            return (ReactModuleInfoProvider) ((Class) Assertions.assertNotNull(ClassFinder.findClass(cls.getName() + "$$ReactModuleInfoProvider"))).newInstance();
        } catch (ClassNotFoundException unused) {
            return fallbackForMissingClass();
        } catch (InstantiationException e) {
            throw new RuntimeException("No ReactModuleInfoProvider for " + cls.getName() + "$$ReactModuleInfoProvider", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("No ReactModuleInfoProvider for " + cls.getName() + "$$ReactModuleInfoProvider", e2);
        }
    }

    private ReactModuleInfoProvider fallbackForMissingClass() {
        Class[] clsArr = {AndroidInfoModule.class, DeviceInfoModule.class, SourceCodeModule.class, DevMenuModule.class, DevSettingsModule.class, DeviceEventManagerModule.class, LogBoxModule.class, ExceptionsManagerModule.class};
        HashMap hashMap = new HashMap();
        for (int i = 0; i < 8; i++) {
            Class cls = clsArr[i];
            ReactModule reactModule = (ReactModule) cls.getAnnotation(ReactModule.class);
            if (reactModule != null) {
                hashMap.put(reactModule.name(), new ReactModuleInfo(reactModule.name(), cls.getName(), reactModule.canOverrideExistingModule(), reactModule.needsEagerInit(), reactModule.isCxxModule(), ReactModuleInfo.classIsTurboModule(cls)));
            }
        }
        return new CoreReactPackage$$ExternalSyntheticLambda0(hashMap);
    }
}
