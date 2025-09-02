package com.reactnativecommunity.webview;

import com.facebook.react.TurboReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RNCWebViewPackage extends TurboReactPackage {
    public List createViewManagers(ReactApplicationContext reactApplicationContext) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new RNCWebViewManager());
        return arrayList;
    }

    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        return new RNCWebViewPackage$$ExternalSyntheticLambda0();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Map lambda$getReactModuleInfoProvider$0() {
        HashMap hashMap = new HashMap();
        hashMap.put("RNCWebViewModule", new ReactModuleInfo("RNCWebViewModule", "RNCWebViewModule", false, false, true, false, false));
        return hashMap;
    }

    public NativeModule getModule(String str, ReactApplicationContext reactApplicationContext) {
        if (str.equals("RNCWebViewModule")) {
            return new RNCWebViewModule(reactApplicationContext);
        }
        return null;
    }
}
