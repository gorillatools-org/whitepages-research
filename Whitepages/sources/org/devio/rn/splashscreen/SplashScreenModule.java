package org.devio.rn.splashscreen;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class SplashScreenModule extends ReactContextBaseJavaModule {
    public SplashScreenModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public String getName() {
        return "SplashScreen";
    }

    @ReactMethod
    public void show() {
        SplashScreen.show(getCurrentActivity());
    }

    @ReactMethod
    public void hide() {
        SplashScreen.hide(getCurrentActivity());
    }
}
