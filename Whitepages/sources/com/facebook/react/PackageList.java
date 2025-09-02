package com.facebook.react;

import agency.flexible.react.modules.email.EmailPackage;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import com.RNAppleAuthentication.AppleAuthenticationAndroidPackage;
import com.airbnb.android.react.lottie.LottiePackage;
import com.amplitude.reactnative.AmplitudeReactNativePackage;
import com.dooboolab.rniap.RNIapPackage;
import com.facebook.react.shell.MainPackageConfig;
import com.facebook.react.shell.MainReactPackage;
import com.horcrux.svg.SvgPackage;
import com.learnium.RNDeviceInfo.RNDeviceInfo;
import com.lugg.RNCConfig.RNCConfigPackage;
import com.oblador.storereview.StoreReviewPackage;
import com.reactnativecommunity.asyncstorage.AsyncStoragePackage;
import com.reactnativecommunity.blurview.BlurViewPackage;
import com.reactnativecommunity.clipboard.ClipboardPackage;
import com.reactnativecommunity.geolocation.GeolocationPackage;
import com.reactnativecommunity.netinfo.NetInfoPackage;
import com.rnlineargradient.LinearGradientPackage;
import com.rt2zz.reactnativecontacts.ReactNativeContacts;
import com.salesforce.marketingcloud.reactnative.RNSFMCSdk;
import com.swmansion.gesturehandler.RNGestureHandlerPackage;
import com.swmansion.rnscreens.RNScreensPackage;
import com.th3rdwave.safeareacontext.SafeAreaContextPackage;
import com.zoontek.rnpermissions.RNPermissionsPackage;
import io.branch.rnbranch.RNBranchPackage;
import io.invertase.firebase.analytics.ReactNativeFirebaseAnalyticsPackage;
import io.invertase.firebase.app.ReactNativeFirebaseAppPackage;
import io.invertase.firebase.config.ReactNativeFirebaseConfigPackage;
import io.invertase.firebase.crashlytics.ReactNativeFirebaseCrashlyticsPackage;
import io.invertase.firebase.perf.ReactNativeFirebasePerfPackage;
import io.invertase.notifee.NotifeePackage;
import java.util.ArrayList;
import java.util.Arrays;
import org.devio.rn.splashscreen.SplashScreenReactPackage;

public class PackageList {
    private Application application;
    private MainPackageConfig mConfig;
    private ReactNativeHost reactNativeHost;

    public PackageList(ReactNativeHost reactNativeHost2) {
        this(reactNativeHost2, (MainPackageConfig) null);
    }

    public PackageList(Application application2) {
        this(application2, (MainPackageConfig) null);
    }

    public PackageList(ReactNativeHost reactNativeHost2, MainPackageConfig mainPackageConfig) {
        this.reactNativeHost = reactNativeHost2;
        this.mConfig = mainPackageConfig;
    }

    public PackageList(Application application2, MainPackageConfig mainPackageConfig) {
        this.reactNativeHost = null;
        this.application = application2;
        this.mConfig = mainPackageConfig;
    }

    private ReactNativeHost getReactNativeHost() {
        return this.reactNativeHost;
    }

    private Resources getResources() {
        return getApplication().getResources();
    }

    private Application getApplication() {
        ReactNativeHost reactNativeHost2 = this.reactNativeHost;
        if (reactNativeHost2 == null) {
            return this.application;
        }
        return reactNativeHost2.getApplication();
    }

    private Context getApplicationContext() {
        return getApplication().getApplicationContext();
    }

    public ArrayList<ReactPackage> getPackages() {
        return new ArrayList<>(Arrays.asList(new ReactPackage[]{new MainReactPackage(this.mConfig), new AmplitudeReactNativePackage(), new AppleAuthenticationAndroidPackage(), new NotifeePackage(), new AsyncStoragePackage(), new ClipboardPackage(), new BlurViewPackage(), new GeolocationPackage(), new NetInfoPackage(), new ReactNativeFirebaseAnalyticsPackage(), new ReactNativeFirebaseAppPackage(), new ReactNativeFirebaseCrashlyticsPackage(), new ReactNativeFirebasePerfPackage(), new ReactNativeFirebaseConfigPackage(), new LottiePackage(), new RNBranchPackage(), new RNCConfigPackage(), new ReactNativeContacts(), new RNDeviceInfo(), new EmailPackage(), new RNGestureHandlerPackage(), new RNIapPackage(), new LinearGradientPackage(), new RNSFMCSdk(), new RNPermissionsPackage(), new SafeAreaContextPackage(), new RNScreensPackage(), new SplashScreenReactPackage(), new StoreReviewPackage(), new SvgPackage()}));
    }
}
