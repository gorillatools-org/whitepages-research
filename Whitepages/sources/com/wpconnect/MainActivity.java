package com.wpconnect;

import android.content.Intent;
import android.os.Bundle;
import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint;
import com.facebook.react.defaults.DefaultReactActivityDelegate;
import io.branch.rnbranch.RNBranchModule;
import kotlin.jvm.internal.Intrinsics;
import org.devio.rn.splashscreen.SplashScreen;

public final class MainActivity extends ReactActivity {
    /* access modifiers changed from: protected */
    public String getMainComponentName() {
        return "WPConnect";
    }

    /* access modifiers changed from: protected */
    public ReactActivityDelegate createReactActivityDelegate() {
        return new DefaultReactActivityDelegate(this, getMainComponentName(), DefaultNewArchitectureEntryPoint.getFabricEnabled());
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        MainApplication.Companion.getCallbackManager().onActivityResult(i, i2, intent);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        SplashScreen.show(this, true);
        super.onCreate((Bundle) null);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        Intent intent = getIntent();
        RNBranchModule.initSession(intent != null ? intent.getData() : null, this);
    }

    public void onNewIntent(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.onNewIntent(intent);
        RNBranchModule.onNewIntent(intent);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        CallerIdModule callerIdModule;
        Intrinsics.checkNotNullParameter(strArr, "permissions");
        Intrinsics.checkNotNullParameter(iArr, "grantResults");
        super.onRequestPermissionsResult(i, strArr, iArr);
        ReactContext currentReactContext = getReactInstanceManager().getCurrentReactContext();
        if (currentReactContext != null && (callerIdModule = (CallerIdModule) currentReactContext.getNativeModule(CallerIdModule.class)) != null) {
            callerIdModule.onRequestPermissionsResult(i, strArr, iArr);
        }
    }
}
