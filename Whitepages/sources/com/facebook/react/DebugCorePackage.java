package com.facebook.react;

import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.views.debuggingoverlay.DebuggingOverlayManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DebugCorePackage extends BaseReactPackage implements ViewManagerOnDemandReactPackage {
    private Map<String, ModuleSpec> mViewManagers;

    public NativeModule getModule(String str, ReactApplicationContext reactApplicationContext) {
        return null;
    }

    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        return new ReactModuleInfoProvider() {
            public Map<String, ReactModuleInfo> getReactModuleInfos() {
                return Collections.emptyMap();
            }
        };
    }

    private Map<String, ModuleSpec> getViewManagersMap() {
        if (this.mViewManagers == null) {
            HashMap hashMap = new HashMap();
            hashMap.put(DebuggingOverlayManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new DebugCorePackage$$ExternalSyntheticLambda0()));
            this.mViewManagers = hashMap;
        }
        return this.mViewManagers;
    }

    public List<ModuleSpec> getViewManagers(ReactApplicationContext reactApplicationContext) {
        return new ArrayList(getViewManagersMap().values());
    }

    public Collection<String> getViewManagerNames(ReactApplicationContext reactApplicationContext) {
        return getViewManagersMap().keySet();
    }

    public ViewManager createViewManager(ReactApplicationContext reactApplicationContext, String str) {
        ModuleSpec moduleSpec = getViewManagersMap().get(str);
        if (moduleSpec != null) {
            return (ViewManager) moduleSpec.getProvider().get();
        }
        return null;
    }
}
