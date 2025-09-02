package com.reactnativecommunity.blurview;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlurViewPackage implements ReactPackage {
    public List createNativeModules(ReactApplicationContext reactApplicationContext) {
        return new ArrayList();
    }

    public List createViewManagers(ReactApplicationContext reactApplicationContext) {
        return Collections.singletonList(new BlurViewManager(reactApplicationContext));
    }
}
