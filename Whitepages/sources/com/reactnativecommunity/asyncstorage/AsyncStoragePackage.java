package com.reactnativecommunity.asyncstorage;

import com.facebook.react.TurboReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsyncStoragePackage extends TurboReactPackage {
    /* access modifiers changed from: protected */
    public List getViewManagers(ReactApplicationContext reactApplicationContext) {
        return null;
    }

    public NativeModule getModule(String str, ReactApplicationContext reactApplicationContext) {
        str.hashCode();
        if (!str.equals(AsyncStorageModule.NAME)) {
            return null;
        }
        return new AsyncStorageModule(reactApplicationContext);
    }

    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        try {
            return (ReactModuleInfoProvider) Class.forName("com.reactnativecommunity.asyncstorage.AsyncStoragePackage$$ReactModuleInfoProvider").newInstance();
        } catch (ClassNotFoundException unused) {
            return new ReactModuleInfoProvider() {
                public Map getReactModuleInfos() {
                    HashMap hashMap = new HashMap();
                    Class cls = new Class[]{AsyncStorageModule.class}[0];
                    ReactModule reactModule = (ReactModule) cls.getAnnotation(ReactModule.class);
                    hashMap.put(reactModule.name(), new ReactModuleInfo(reactModule.name(), cls.getName(), reactModule.canOverrideExistingModule(), reactModule.needsEagerInit(), reactModule.hasConstants(), reactModule.isCxxModule(), false));
                    return hashMap;
                }
            };
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException("No ReactModuleInfoProvider for com.reactnativecommunity.asyncstorage.AsyncStoragePackage$$ReactModuleInfoProvider", e);
        }
    }

    public List createViewManagers(ReactApplicationContext reactApplicationContext) {
        return Collections.emptyList();
    }
}
