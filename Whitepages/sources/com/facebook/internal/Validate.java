package com.facebook.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.FacebookSdkNotInitializedException;
import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class Validate {
    public static final Validate INSTANCE = new Validate();
    private static final String TAG = Validate.class.getName();

    private Validate() {
    }

    public static final void notNull(Object obj, String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        if (obj == null) {
            throw new NullPointerException("Argument '" + str + "' cannot be null");
        }
    }

    public static final void notEmpty(Collection collection, String str) {
        Intrinsics.checkNotNullParameter(collection, "container");
        Intrinsics.checkNotNullParameter(str, "name");
        if (collection.isEmpty()) {
            throw new IllegalArgumentException(("Container '" + str + "' cannot be empty").toString());
        }
    }

    public static final void containsNoNulls(Collection collection, String str) {
        Intrinsics.checkNotNullParameter(collection, "container");
        Intrinsics.checkNotNullParameter(str, "name");
        for (Object obj : collection) {
            if (obj == null) {
                throw new NullPointerException("Container '" + str + "' cannot contain null values");
            }
        }
    }

    public static final void notEmptyAndContainsNoNulls(Collection collection, String str) {
        Intrinsics.checkNotNullParameter(collection, "container");
        Intrinsics.checkNotNullParameter(str, "name");
        containsNoNulls(collection, str);
        notEmpty(collection, str);
    }

    public static final String notNullOrEmpty(String str, String str2) {
        Intrinsics.checkNotNullParameter(str2, "name");
        if (str != null && str.length() > 0) {
            return str;
        }
        throw new IllegalArgumentException(("Argument '" + str2 + "' cannot be null or empty").toString());
    }

    public static final void notEmpty(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "arg");
        Intrinsics.checkNotNullParameter(str2, "name");
        if (str.length() <= 0) {
            throw new IllegalArgumentException(("Argument '" + str2 + "' cannot be empty").toString());
        }
    }

    public static final void sdkInitialized() {
        if (!FacebookSdk.isInitialized()) {
            throw new FacebookSdkNotInitializedException("The SDK has not been initialized, make sure to call FacebookSdk.sdkInitialize() first.");
        }
    }

    public static final String hasAppID() {
        String applicationId = FacebookSdk.getApplicationId();
        if (applicationId != null) {
            return applicationId;
        }
        throw new IllegalStateException("No App ID found, please set the App ID.");
    }

    public static final String hasClientToken() {
        String clientToken = FacebookSdk.getClientToken();
        if (clientToken != null) {
            return clientToken;
        }
        throw new IllegalStateException("No Client Token found, please set the Client Token. Please follow https://developers.facebook.com/docs/android/getting-started/#client-access-token to get the token and fill it in AndroidManifest.xml");
    }

    public static final void hasInternetPermissions(Context context, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (context.checkCallingOrSelfPermission("android.permission.INTERNET") != -1) {
            return;
        }
        if (!z) {
            Log.w(TAG, "No internet permissions granted for the app, please add <uses-permission android:name=\"android.permission.INTERNET\" /> to your AndroidManifest.xml.");
            return;
        }
        throw new IllegalStateException("No internet permissions granted for the app, please add <uses-permission android:name=\"android.permission.INTERNET\" /> to your AndroidManifest.xml.");
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void hasFacebookActivity(android.content.Context r3, boolean r4) {
        /*
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            android.content.pm.PackageManager r0 = r3.getPackageManager()
            if (r0 == 0) goto L_0x0018
            android.content.ComponentName r1 = new android.content.ComponentName
            java.lang.String r2 = "com.facebook.FacebookActivity"
            r1.<init>(r3, r2)
            r3 = 1
            android.content.pm.ActivityInfo r3 = r0.getActivityInfo(r1, r3)     // Catch:{ NameNotFoundException -> 0x0018 }
            goto L_0x0019
        L_0x0018:
            r3 = 0
        L_0x0019:
            if (r3 != 0) goto L_0x002b
            java.lang.String r3 = "FacebookActivity is not declared in the AndroidManifest.xml. If you are using the facebook-common module or dependent modules please add com.facebook.FacebookActivity to your AndroidManifest.xml file. See https://developers.facebook.com/docs/android/getting-started for more info."
            if (r4 != 0) goto L_0x0025
            java.lang.String r4 = TAG
            android.util.Log.w(r4, r3)
            goto L_0x002b
        L_0x0025:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            r4.<init>(r3)
            throw r4
        L_0x002b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.Validate.hasFacebookActivity(android.content.Context, boolean):void");
    }

    public static final boolean hasCustomTabRedirectActivity(Context context, String str) {
        List<ResolveInfo> list;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, "redirectURI");
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.addCategory("android.intent.category.BROWSABLE");
            intent.setData(Uri.parse(str));
            list = packageManager.queryIntentActivities(intent, 64);
        } else {
            list = null;
        }
        if (list == null) {
            return false;
        }
        boolean z = false;
        for (ResolveInfo resolveInfo : list) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            if (!Intrinsics.areEqual((Object) activityInfo.name, (Object) "com.facebook.CustomTabActivity") || !Intrinsics.areEqual((Object) activityInfo.packageName, (Object) context.getPackageName())) {
                return false;
            }
            z = true;
        }
        return z;
    }
}
