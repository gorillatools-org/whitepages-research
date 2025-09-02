package com.wpconnect;

import com.facebook.react.PackageList;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.defaults.DefaultReactNativeHost;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.microsoft.codepush.react.CodePush;
import com.reactnativecommunity.webview.RNCWebViewPackage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

public final class MainApplication$reactNativeHost$1 extends DefaultReactNativeHost {
    final /* synthetic */ MainApplication this$0;

    public boolean getUseDeveloperSupport() {
        return false;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MainApplication$reactNativeHost$1(MainApplication mainApplication) {
        super(mainApplication);
        this.this$0 = mainApplication;
    }

    /* access modifiers changed from: protected */
    public List<ReactPackage> getPackages() {
        ArrayList<ReactPackage> packages = new PackageList((ReactNativeHost) this).getPackages();
        Intrinsics.checkNotNullExpressionValue(packages, "getPackages(...)");
        List<ReactPackage> mutableList = CollectionsKt.toMutableList((Collection) packages);
        mutableList.add(new CallerIdPackage());
        mutableList.add(new CodePush(this.this$0.getResources().getString(R.string.CodePushDeploymentKey), this.this$0.getApplicationContext(), false));
        mutableList.add(new RNCWebViewPackage());
        return mutableList;
    }

    /* access modifiers changed from: protected */
    public String getJSBundleFile() {
        String jSBundleFile = CodePush.getJSBundleFile();
        Intrinsics.checkNotNullExpressionValue(jSBundleFile, "getJSBundleFile(...)");
        return jSBundleFile;
    }

    /* access modifiers changed from: protected */
    public String getJSMainModuleName() {
        return FirebaseAnalytics.Param.INDEX;
    }
}
