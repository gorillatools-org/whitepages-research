package com.rnlineargradient;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.Collections;
import java.util.List;

public class LinearGradientPackage implements ReactPackage {
    public List createNativeModules(ReactApplicationContext reactApplicationContext) {
        return Collections.emptyList();
    }

    public List createViewManagers(ReactApplicationContext reactApplicationContext) {
        return Collections.singletonList(new LinearGradientManager());
    }
}
