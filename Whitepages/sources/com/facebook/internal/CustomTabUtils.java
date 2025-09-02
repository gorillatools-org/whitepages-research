package com.facebook.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import com.facebook.FacebookSdk;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.util.HashSet;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

public final class CustomTabUtils {
    private static final String[] CHROME_PACKAGES = {"com.android.chrome", "com.chrome.beta", "com.chrome.dev"};
    public static final CustomTabUtils INSTANCE = new CustomTabUtils();

    private CustomTabUtils() {
    }

    public static final String getDefaultRedirectURI() {
        Class<CustomTabUtils> cls = CustomTabUtils.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return "fbconnect://cct." + FacebookSdk.getApplicationContext().getPackageName();
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final String getValidRedirectURI(String str) {
        Class<CustomTabUtils> cls = CustomTabUtils.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(str, "developerDefinedRedirectURI");
            if (Validate.hasCustomTabRedirectActivity(FacebookSdk.getApplicationContext(), str)) {
                return str;
            }
            if (Validate.hasCustomTabRedirectActivity(FacebookSdk.getApplicationContext(), getDefaultRedirectURI())) {
                return getDefaultRedirectURI();
            }
            return "";
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final String getChromePackage() {
        Class<CustomTabUtils> cls = CustomTabUtils.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Context applicationContext = FacebookSdk.getApplicationContext();
            List<ResolveInfo> queryIntentServices = applicationContext.getPackageManager().queryIntentServices(new Intent("android.support.customtabs.action.CustomTabsService"), 0);
            Intrinsics.checkNotNullExpressionValue(queryIntentServices, "context.packageManager.q…ervices(serviceIntent, 0)");
            HashSet hashSet = ArraysKt.toHashSet(CHROME_PACKAGES);
            for (ResolveInfo resolveInfo : queryIntentServices) {
                ServiceInfo serviceInfo = resolveInfo.serviceInfo;
                if (serviceInfo != null && hashSet.contains(serviceInfo.packageName)) {
                    return serviceInfo.packageName;
                }
            }
            return null;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }
}
