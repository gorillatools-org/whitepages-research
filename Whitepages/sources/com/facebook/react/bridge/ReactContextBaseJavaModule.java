package com.facebook.react.bridge;

import android.app.Activity;

public abstract class ReactContextBaseJavaModule extends BaseJavaModule {
    public ReactContextBaseJavaModule() {
        super((ReactApplicationContext) null);
    }

    public ReactContextBaseJavaModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    /* access modifiers changed from: protected */
    public final Activity getCurrentActivity() {
        return getReactApplicationContext().getCurrentActivity();
    }
}
