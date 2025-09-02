package com.reactnativecommunity.clipboard;

import com.facebook.react.TurboReactPackage;
import com.facebook.react.ViewManagerOnDemandReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import com.facebook.react.uimanager.ViewManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClipboardPackage extends TurboReactPackage implements ViewManagerOnDemandReactPackage {
    public ViewManager createViewManager(ReactApplicationContext reactApplicationContext, String str) {
        return null;
    }

    public List getViewManagerNames(ReactApplicationContext reactApplicationContext) {
        return null;
    }

    /* access modifiers changed from: protected */
    public List getViewManagers(ReactApplicationContext reactApplicationContext) {
        return null;
    }

    public NativeModule getModule(String str, ReactApplicationContext reactApplicationContext) {
        str.hashCode();
        if (!str.equals(ClipboardModule.NAME)) {
            return null;
        }
        return new ClipboardModule(reactApplicationContext);
    }

    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        try {
            return (ReactModuleInfoProvider) Class.forName("com.reactnativecommunity.clipboard.ClipboardPackage$$ReactModuleInfoProvider").newInstance();
        } catch (ClassNotFoundException unused) {
            return new ReactModuleInfoProvider() {
                public Map getReactModuleInfos() {
                    HashMap hashMap = new HashMap();
                    Class cls = new Class[]{ClipboardModule.class}[0];
                    ReactModule reactModule = (ReactModule) cls.getAnnotation(ReactModule.class);
                    hashMap.put(reactModule.name(), new ReactModuleInfo(reactModule.name(), cls.getName(), reactModule.canOverrideExistingModule(), reactModule.needsEagerInit(), reactModule.hasConstants(), reactModule.isCxxModule(), TurboModule.class.isAssignableFrom(cls)));
                    return hashMap;
                }
            };
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException("No ReactModuleInfoProvider for com.reactnativecommunity.clipboard.ClipboardPackage$$ReactModuleInfoProvider", e);
        }
    }

    public List createViewManagers(ReactApplicationContext reactApplicationContext) {
        return Collections.emptyList();
    }

    public List createNativeModules(ReactApplicationContext reactApplicationContext) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ClipboardModule(reactApplicationContext));
        return arrayList;
    }
}
