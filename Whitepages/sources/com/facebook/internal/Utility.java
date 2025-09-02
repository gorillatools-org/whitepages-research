package com.facebook.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcel;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.autofill.AutofillManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.appevents.UserDataStore;
import com.facebook.appevents.internal.AppLinkManager;
import com.facebook.internal.FeatureManager;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.salesforce.marketingcloud.b;
import com.salesforce.marketingcloud.messages.iam.j;
import io.branch.rnbranch.RNBranchModule;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Pattern;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public final class Utility {
    public static final Utility INSTANCE = new Utility();
    private static long availableExternalStorageGB = -1;
    private static String carrierName = "NoCarrier";
    private static String deviceTimeZoneName = "";
    private static String deviceTimezoneAbbreviation = "";
    private static Locale locale;
    private static int numCPUCores;
    private static long timestampOfLastCheck = -1;
    private static long totalExternalStorageGB = -1;
    private static String versionName = "";

    public interface GraphMeRequestWithCacheCallback {
        void onFailure(FacebookException facebookException);

        void onSuccess(JSONObject jSONObject);
    }

    private Utility() {
    }

    public final long getAvailableExternalStorageGB() {
        return availableExternalStorageGB;
    }

    public final String getDeviceTimeZoneName() {
        return deviceTimeZoneName;
    }

    public final String getCarrierName() {
        return carrierName;
    }

    public final String getVersionName() {
        return versionName;
    }

    public final Locale getLocale() {
        return locale;
    }

    public static final boolean isNullOrEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static final boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static final String coerceValueIfNullOrEmpty(String str, String str2) {
        return isNullOrEmpty(str) ? str2 : str;
    }

    public static final String md5hash(String str) {
        Intrinsics.checkNotNullParameter(str, "key");
        return INSTANCE.hashWithAlgorithm("MD5", str);
    }

    public static final String sha1hash(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "bytes");
        return INSTANCE.hashWithAlgorithm("SHA-1", bArr);
    }

    public static final String sha256hash(String str) {
        if (str == null) {
            return null;
        }
        return INSTANCE.hashWithAlgorithm("SHA-256", str);
    }

    private final String hashWithAlgorithm(String str, String str2) {
        byte[] bytes = str2.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        return hashWithAlgorithm(str, bytes);
    }

    private final String hashWithAlgorithm(String str, byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            Intrinsics.checkNotNullExpressionValue(instance, "hash");
            return hashBytes(instance, bArr);
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    private final String hashBytes(MessageDigest messageDigest, byte[] bArr) {
        messageDigest.update(bArr);
        byte[] digest = messageDigest.digest();
        StringBuilder sb = new StringBuilder();
        Intrinsics.checkNotNullExpressionValue(digest, "digest");
        for (byte b : digest) {
            sb.append(Integer.toHexString((b >> 4) & 15));
            sb.append(Integer.toHexString(b & 15));
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "builder.toString()");
        return sb2;
    }

    public static final Uri buildUri(String str, String str2, Bundle bundle) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https");
        builder.authority(str);
        builder.path(str2);
        if (bundle != null) {
            for (String next : bundle.keySet()) {
                Object obj = bundle.get(next);
                if (obj instanceof String) {
                    builder.appendQueryParameter(next, (String) obj);
                }
            }
        }
        Uri build = builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        return build;
    }

    public static final Bundle parseUrlQueryString(String str) {
        Bundle bundle = new Bundle();
        if (!isNullOrEmpty(str)) {
            if (str != null) {
                for (String split$default : (String[]) StringsKt.split$default((CharSequence) str, new String[]{"&"}, false, 0, 6, (Object) null).toArray(new String[0])) {
                    String[] strArr = (String[]) StringsKt.split$default((CharSequence) split$default, new String[]{"="}, false, 0, 6, (Object) null).toArray(new String[0]);
                    try {
                        if (strArr.length == 2) {
                            bundle.putString(URLDecoder.decode(strArr[0], "UTF-8"), URLDecoder.decode(strArr[1], "UTF-8"));
                        } else if (strArr.length == 1) {
                            bundle.putString(URLDecoder.decode(strArr[0], "UTF-8"), "");
                        }
                    } catch (UnsupportedEncodingException e) {
                        logd("FacebookSDK", (Exception) e);
                    }
                }
            } else {
                throw new IllegalStateException("Required value was null.");
            }
        }
        return bundle;
    }

    public static final void putNonEmptyString(Bundle bundle, String str, String str2) {
        Intrinsics.checkNotNullParameter(bundle, "b");
        if (!isNullOrEmpty(str2)) {
            bundle.putString(str, str2);
        }
    }

    public static final boolean putJSONValueInBundle(Bundle bundle, String str, Object obj) {
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        if (obj == null) {
            bundle.remove(str);
            return true;
        } else if (obj instanceof Boolean) {
            bundle.putBoolean(str, ((Boolean) obj).booleanValue());
            return true;
        } else if (obj instanceof boolean[]) {
            bundle.putBooleanArray(str, (boolean[]) obj);
            return true;
        } else if (obj instanceof Double) {
            bundle.putDouble(str, ((Number) obj).doubleValue());
            return true;
        } else if (obj instanceof double[]) {
            bundle.putDoubleArray(str, (double[]) obj);
            return true;
        } else if (obj instanceof Integer) {
            bundle.putInt(str, ((Number) obj).intValue());
            return true;
        } else if (obj instanceof int[]) {
            bundle.putIntArray(str, (int[]) obj);
            return true;
        } else if (obj instanceof Long) {
            bundle.putLong(str, ((Number) obj).longValue());
            return true;
        } else if (obj instanceof long[]) {
            bundle.putLongArray(str, (long[]) obj);
            return true;
        } else if (obj instanceof String) {
            bundle.putString(str, (String) obj);
            return true;
        } else if (obj instanceof JSONArray) {
            bundle.putString(str, ((JSONArray) obj).toString());
            return true;
        } else if (!(obj instanceof JSONObject)) {
            return false;
        } else {
            bundle.putString(str, ((JSONObject) obj).toString());
            return true;
        }
    }

    public static final void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static final void disconnectQuietly(URLConnection uRLConnection) {
        if (uRLConnection != null && (uRLConnection instanceof HttpURLConnection)) {
            ((HttpURLConnection) uRLConnection).disconnect();
        }
    }

    public static final String getMetadataApplicationId(Context context) {
        Validate.notNull(context, "context");
        return FacebookSdk.getApplicationId();
    }

    public static final Map convertJSONObjectToHashMap(JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(jSONObject, "jsonObject");
        HashMap hashMap = new HashMap();
        JSONArray names = jSONObject.names();
        if (names == null) {
            return hashMap;
        }
        int length = names.length();
        for (int i = 0; i < length; i++) {
            try {
                String string = names.getString(i);
                Intrinsics.checkNotNullExpressionValue(string, "keys.getString(i)");
                Object obj = jSONObject.get(string);
                if (obj instanceof JSONObject) {
                    obj = convertJSONObjectToHashMap((JSONObject) obj);
                }
                Intrinsics.checkNotNullExpressionValue(obj, "value");
                hashMap.put(string, obj);
            } catch (JSONException unused) {
            }
        }
        return hashMap;
    }

    public static final Map convertJSONObjectToStringMap(JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(jSONObject, "jsonObject");
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            String optString = jSONObject.optString(next);
            if (optString != null) {
                Intrinsics.checkNotNullExpressionValue(next, "key");
                hashMap.put(next, optString);
            }
        }
        return hashMap;
    }

    public static final HashSet convertJSONArrayToHashSet(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        HashSet hashSet = new HashSet();
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            String string = jSONArray.getString(i);
            Intrinsics.checkNotNullExpressionValue(string, "jsonArray.getString(i)");
            hashSet.add(string);
        }
        return hashSet;
    }

    public static final List convertJSONArrayToList(JSONArray jSONArray) {
        Intrinsics.checkNotNullParameter(jSONArray, "jsonArray");
        try {
            ArrayList arrayList = new ArrayList();
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                String string = jSONArray.getString(i);
                Intrinsics.checkNotNullExpressionValue(string, "jsonArray.getString(i)");
                arrayList.add(string);
            }
            return arrayList;
        } catch (JSONException unused) {
            return new ArrayList();
        }
    }

    public static final Object getStringPropertyAsJSON(JSONObject jSONObject, String str, String str2) {
        Intrinsics.checkNotNullParameter(jSONObject, "jsonObject");
        Object opt = jSONObject.opt(str);
        if (opt != null && (opt instanceof String)) {
            opt = new JSONTokener((String) opt).nextValue();
        }
        if (opt == null || (opt instanceof JSONObject) || (opt instanceof JSONArray)) {
            return opt;
        }
        if (str2 != null) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.putOpt(str2, opt);
            return jSONObject2;
        }
        throw new FacebookException("Got an unexpected non-JSON object.");
    }

    public static final String readStreamToString(InputStream inputStream) {
        InputStreamReader inputStreamReader;
        BufferedInputStream bufferedInputStream;
        Throwable th;
        try {
            bufferedInputStream = new BufferedInputStream(inputStream);
            try {
                inputStreamReader = new InputStreamReader(bufferedInputStream);
                try {
                    StringBuilder sb = new StringBuilder();
                    char[] cArr = new char[b.u];
                    while (true) {
                        int read = inputStreamReader.read(cArr);
                        if (read != -1) {
                            sb.append(cArr, 0, read);
                        } else {
                            String sb2 = sb.toString();
                            Intrinsics.checkNotNullExpressionValue(sb2, "{\n      bufferedInputStr…gBuilder.toString()\n    }");
                            closeQuietly(bufferedInputStream);
                            closeQuietly(inputStreamReader);
                            return sb2;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    closeQuietly(bufferedInputStream);
                    closeQuietly(inputStreamReader);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                inputStreamReader = null;
                closeQuietly(bufferedInputStream);
                closeQuietly(inputStreamReader);
                throw th;
            }
        } catch (Throwable th4) {
            bufferedInputStream = null;
            th = th4;
            inputStreamReader = null;
            closeQuietly(bufferedInputStream);
            closeQuietly(inputStreamReader);
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0031  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final int copyAndCloseInputStream(java.io.InputStream r6, java.io.OutputStream r7) {
        /*
            java.lang.String r0 = "outputStream"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            r0 = 0
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch:{ all -> 0x0029 }
            r1.<init>(r6)     // Catch:{ all -> 0x0029 }
            r0 = 8192(0x2000, float:1.14794E-41)
            byte[] r0 = new byte[r0]     // Catch:{ all -> 0x001d }
            r2 = 0
            r3 = r2
        L_0x0011:
            int r4 = r1.read(r0)     // Catch:{ all -> 0x001d }
            r5 = -1
            if (r4 == r5) goto L_0x0020
            r7.write(r0, r2, r4)     // Catch:{ all -> 0x001d }
            int r3 = r3 + r4
            goto L_0x0011
        L_0x001d:
            r7 = move-exception
            r0 = r1
            goto L_0x002a
        L_0x0020:
            r1.close()
            if (r6 == 0) goto L_0x0028
            r6.close()
        L_0x0028:
            return r3
        L_0x0029:
            r7 = move-exception
        L_0x002a:
            if (r0 == 0) goto L_0x002f
            r0.close()
        L_0x002f:
            if (r6 == 0) goto L_0x0034
            r6.close()
        L_0x0034:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.Utility.copyAndCloseInputStream(java.io.InputStream, java.io.OutputStream):int");
    }

    private final void clearCookiesForDomain(Context context, String str) {
        CookieSyncManager.createInstance(context).sync();
        CookieManager instance = CookieManager.getInstance();
        String cookie = instance.getCookie(str);
        if (cookie != null) {
            for (String split$default : (String[]) StringsKt.split$default((CharSequence) cookie, new String[]{";"}, false, 0, 6, (Object) null).toArray(new String[0])) {
                String[] strArr = (String[]) StringsKt.split$default((CharSequence) split$default, new String[]{"="}, false, 0, 6, (Object) null).toArray(new String[0]);
                if (strArr.length > 0) {
                    StringBuilder sb = new StringBuilder();
                    String str2 = strArr[0];
                    int length = str2.length() - 1;
                    int i = 0;
                    boolean z = false;
                    while (i <= length) {
                        boolean z2 = Intrinsics.compare((int) str2.charAt(!z ? i : length), 32) <= 0;
                        if (!z) {
                            if (!z2) {
                                z = true;
                            } else {
                                i++;
                            }
                        } else if (!z2) {
                            break;
                        } else {
                            length--;
                        }
                    }
                    sb.append(str2.subSequence(i, length + 1).toString());
                    sb.append("=;expires=Sat, 1 Jan 2000 00:00:01 UTC;");
                    instance.setCookie(str, sb.toString());
                }
            }
            instance.removeExpiredCookie();
        }
    }

    public static final void clearFacebookCookies(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Utility utility = INSTANCE;
            utility.clearCookiesForDomain(context, "facebook.com");
            utility.clearCookiesForDomain(context, ".facebook.com");
            utility.clearCookiesForDomain(context, "https://facebook.com");
            utility.clearCookiesForDomain(context, "https://.facebook.com");
        } catch (Exception unused) {
        }
    }

    public static final void logd(String str, Exception exc) {
        if (FacebookSdk.isDebugEnabled() && str != null && exc != null) {
            Log.d(str, exc.getClass().getSimpleName() + ": " + exc.getMessage());
        }
    }

    public static final void logd(String str, String str2) {
        if (FacebookSdk.isDebugEnabled() && str != null && str2 != null) {
            Log.d(str, str2);
        }
    }

    public static final void logd(String str, String str2, Throwable th) {
        if (FacebookSdk.isDebugEnabled() && !isNullOrEmpty(str)) {
            Log.d(str, str2, th);
        }
    }

    public static final boolean areObjectsEqual(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return Intrinsics.areEqual(obj, obj2);
    }

    public static final List jsonArrayToStringList(JSONArray jSONArray) {
        Intrinsics.checkNotNullParameter(jSONArray, "jsonArray");
        ArrayList arrayList = new ArrayList();
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            arrayList.add(jSONArray.getString(i));
        }
        return arrayList;
    }

    public static final Set jsonArrayToSet(JSONArray jSONArray) {
        Intrinsics.checkNotNullParameter(jSONArray, "jsonArray");
        HashSet hashSet = new HashSet();
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            String string = jSONArray.getString(i);
            Intrinsics.checkNotNullExpressionValue(string, "jsonArray.getString(i)");
            hashSet.add(string);
        }
        return hashSet;
    }

    public static final String mapToJsonStr(Map map) {
        Intrinsics.checkNotNullParameter(map, "map");
        String str = "";
        if (!map.isEmpty()) {
            try {
                JSONObject jSONObject = new JSONObject();
                for (Map.Entry entry : map.entrySet()) {
                    jSONObject.put((String) entry.getKey(), (String) entry.getValue());
                }
                str = jSONObject.toString();
            } catch (JSONException unused) {
            }
            Intrinsics.checkNotNullExpressionValue(str, "{\n      try {\n        va…\n        \"\"\n      }\n    }");
        }
        return str;
    }

    public static final Map jsonStrToMap(String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        if (str.length() == 0) {
            return new HashMap();
        }
        try {
            HashMap hashMap = new HashMap();
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                Intrinsics.checkNotNullExpressionValue(next, "key");
                String string = jSONObject.getString(next);
                Intrinsics.checkNotNullExpressionValue(string, "jsonObject.getString(key)");
                hashMap.put(next, string);
            }
            return hashMap;
        } catch (JSONException unused) {
            return new HashMap();
        }
    }

    public static final void setAppEventAttributionParameters(JSONObject jSONObject, AttributionIdentifiers attributionIdentifiers, String str, boolean z, Context context) {
        Intrinsics.checkNotNullParameter(jSONObject, RNBranchModule.NATIVE_INIT_SESSION_FINISHED_EVENT_PARAMS);
        Intrinsics.checkNotNullParameter(context, "context");
        FeatureManager.Feature feature = FeatureManager.Feature.ServiceUpdateCompliance;
        if (!FeatureManager.isEnabled(feature)) {
            jSONObject.put("anon_id", str);
        }
        jSONObject.put("application_tracking_enabled", !z);
        jSONObject.put("advertiser_id_collection_enabled", FacebookSdk.getAdvertiserIDCollectionEnabled());
        if (attributionIdentifiers != null) {
            if (FeatureManager.isEnabled(feature)) {
                INSTANCE.appendAnonIdUnderCompliance(jSONObject, attributionIdentifiers, str, context);
            }
            if (attributionIdentifiers.getAttributionId() != null) {
                if (FeatureManager.isEnabled(feature)) {
                    INSTANCE.appendAttributionIdUnderCompliance(jSONObject, attributionIdentifiers, context);
                } else {
                    jSONObject.put("attribution", attributionIdentifiers.getAttributionId());
                }
            }
            if (attributionIdentifiers.getAndroidAdvertiserId() != null) {
                jSONObject.put("advertiser_id", attributionIdentifiers.getAndroidAdvertiserId());
                jSONObject.put("advertiser_tracking_enabled", !attributionIdentifiers.isTrackingLimited());
            }
            if (!attributionIdentifiers.isTrackingLimited()) {
                String allHashedUserData = UserDataStore.getAllHashedUserData();
                if (allHashedUserData.length() != 0) {
                    jSONObject.put("ud", allHashedUserData);
                }
            }
            if (attributionIdentifiers.getAndroidInstallerPackage() != null) {
                jSONObject.put("installer_package", attributionIdentifiers.getAndroidInstallerPackage());
            }
        }
        AppLinkManager instance = AppLinkManager.Companion.getInstance();
        String info = instance != null ? instance.getInfo("campaign_ids") : null;
        if (info != null) {
            jSONObject.put("campaign_ids", info);
        }
    }

    public static final String getAppVersion() {
        Context applicationContext = FacebookSdk.getApplicationContext();
        if (applicationContext == null) {
            return null;
        }
        try {
            PackageInfo packageInfo = applicationContext.getPackageManager().getPackageInfo(applicationContext.getPackageName(), 0);
            if (packageInfo == null) {
                return null;
            }
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public static final void setAppEventExtendedDeviceInfoParameters(JSONObject jSONObject, Context context) {
        Locale locale2;
        int i;
        Intrinsics.checkNotNullParameter(jSONObject, RNBranchModule.NATIVE_INIT_SESSION_FINISHED_EVENT_PARAMS);
        Intrinsics.checkNotNullParameter(context, "appContext");
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("a2");
        INSTANCE.refreshPeriodicExtendedDeviceInfo(context);
        String packageName = context.getPackageName();
        int i2 = 0;
        int i3 = -1;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            if (packageInfo != null) {
                i3 = packageInfo.versionCode;
                versionName = packageInfo.versionName;
                jSONArray.put(packageName);
                jSONArray.put(i3);
                jSONArray.put(versionName);
                jSONArray.put(Build.VERSION.RELEASE);
                jSONArray.put(Build.MODEL);
                try {
                    locale2 = context.getResources().getConfiguration().locale;
                } catch (Exception unused) {
                    locale2 = Locale.getDefault();
                }
                locale = locale2;
                StringBuilder sb = new StringBuilder();
                Locale locale3 = locale;
                Display display = null;
                String language = locale3 != null ? locale3.getLanguage() : null;
                String str = "";
                if (language == null) {
                    language = str;
                }
                sb.append(language);
                sb.append('_');
                Locale locale4 = locale;
                String country = locale4 != null ? locale4.getCountry() : null;
                if (country != null) {
                    str = country;
                }
                sb.append(str);
                jSONArray.put(sb.toString());
                jSONArray.put(deviceTimezoneAbbreviation);
                jSONArray.put(carrierName);
                double d = 0.0d;
                try {
                    Object systemService = context.getSystemService("display");
                    DisplayManager displayManager = systemService instanceof DisplayManager ? (DisplayManager) systemService : null;
                    if (displayManager != null) {
                        display = displayManager.getDisplay(0);
                    }
                    if (display != null) {
                        DisplayMetrics displayMetrics = new DisplayMetrics();
                        display.getMetrics(displayMetrics);
                        int i4 = displayMetrics.widthPixels;
                        try {
                            i2 = displayMetrics.heightPixels;
                            d = (double) displayMetrics.density;
                        } catch (Exception unused2) {
                        }
                        i = i2;
                        i2 = i4;
                        jSONArray.put(i2);
                        jSONArray.put(i);
                        jSONArray.put(new DecimalFormat("#.##").format(d));
                        jSONArray.put(INSTANCE.refreshBestGuessNumberOfCPUCores());
                        jSONArray.put(totalExternalStorageGB);
                        jSONArray.put(availableExternalStorageGB);
                        jSONArray.put(deviceTimeZoneName);
                        jSONObject.put("extinfo", jSONArray.toString());
                    }
                } catch (Exception unused3) {
                }
                i = 0;
                jSONArray.put(i2);
                jSONArray.put(i);
                jSONArray.put(new DecimalFormat("#.##").format(d));
                jSONArray.put(INSTANCE.refreshBestGuessNumberOfCPUCores());
                jSONArray.put(totalExternalStorageGB);
                jSONArray.put(availableExternalStorageGB);
                jSONArray.put(deviceTimeZoneName);
                jSONObject.put("extinfo", jSONArray.toString());
            }
        } catch (PackageManager.NameNotFoundException unused4) {
        }
    }

    public static final Method getMethodQuietly(Class cls, String str, Class... clsArr) {
        Intrinsics.checkNotNullParameter(cls, "clazz");
        Intrinsics.checkNotNullParameter(str, "methodName");
        Intrinsics.checkNotNullParameter(clsArr, "parameterTypes");
        try {
            return cls.getMethod(str, (Class[]) Arrays.copyOf(clsArr, clsArr.length));
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public static final Method getMethodQuietly(String str, String str2, Class... clsArr) {
        Intrinsics.checkNotNullParameter(str, "className");
        Intrinsics.checkNotNullParameter(str2, "methodName");
        Intrinsics.checkNotNullParameter(clsArr, "parameterTypes");
        try {
            Class<?> cls = Class.forName(str);
            Intrinsics.checkNotNullExpressionValue(cls, "clazz");
            return getMethodQuietly((Class) cls, str2, (Class[]) Arrays.copyOf(clsArr, clsArr.length));
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static final Object invokeMethodQuietly(Object obj, Method method, Object... objArr) {
        Intrinsics.checkNotNullParameter(method, FirebaseAnalytics.Param.METHOD);
        Intrinsics.checkNotNullParameter(objArr, "args");
        try {
            return method.invoke(obj, Arrays.copyOf(objArr, objArr.length));
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }

    public static final String getActivityName(Context context) {
        if (context == null) {
            return "null";
        }
        if (context == context.getApplicationContext()) {
            return j.h;
        }
        String simpleName = context.getClass().getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "{\n      context.javaClass.simpleName\n    }");
        return simpleName;
    }

    public static final boolean isWebUri(Uri uri) {
        if (uri == null || (!StringsKt.equals("http", uri.getScheme(), true) && !StringsKt.equals("https", uri.getScheme(), true) && !StringsKt.equals("fbstaging", uri.getScheme(), true))) {
            return false;
        }
        return true;
    }

    public static final boolean isContentUri(Uri uri) {
        return uri != null && StringsKt.equals(FirebaseAnalytics.Param.CONTENT, uri.getScheme(), true);
    }

    public static final boolean isFileUri(Uri uri) {
        return uri != null && StringsKt.equals("file", uri.getScheme(), true);
    }

    public static final long getContentSize(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "contentUri");
        Cursor cursor = null;
        try {
            Cursor query = FacebookSdk.getApplicationContext().getContentResolver().query(uri, (String[]) null, (String) null, (String[]) null, (String) null);
            if (query == null) {
                return 0;
            }
            int columnIndex = query.getColumnIndex("_size");
            query.moveToFirst();
            long j = query.getLong(columnIndex);
            query.close();
            return j;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static final Date getBundleLongAsDate(Bundle bundle, String str, Date date) {
        long j;
        Intrinsics.checkNotNullParameter(date, "dateBase");
        if (bundle == null) {
            return null;
        }
        Object obj = bundle.get(str);
        if (obj instanceof Long) {
            j = ((Number) obj).longValue();
        } else {
            if (obj instanceof String) {
                try {
                    j = Long.parseLong((String) obj);
                } catch (NumberFormatException unused) {
                }
            }
            return null;
        }
        if (j == 0) {
            return new Date(Long.MAX_VALUE);
        }
        return new Date(date.getTime() + (j * 1000));
    }

    public static final void writeStringMapToParcel(Parcel parcel, Map map) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        if (map == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(map.size());
        for (Map.Entry entry : map.entrySet()) {
            parcel.writeString((String) entry.getKey());
            parcel.writeString((String) entry.getValue());
        }
    }

    public static final Map readStringMapFromParcel(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        int readInt = parcel.readInt();
        if (readInt < 0) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (int i = 0; i < readInt; i++) {
            hashMap.put(parcel.readString(), parcel.readString());
        }
        return hashMap;
    }

    public static final void writeNonnullStringMapToParcel(Parcel parcel, Map map) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        if (map == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(map.size());
        for (Map.Entry entry : map.entrySet()) {
            parcel.writeString((String) entry.getKey());
            parcel.writeString((String) entry.getValue());
        }
    }

    public static final Map readNonnullStringMapFromParcel(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        int readInt = parcel.readInt();
        if (readInt < 0) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (int i = 0; i < readInt; i++) {
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            if (!(readString == null || readString2 == null)) {
                hashMap.put(readString, readString2);
            }
        }
        return hashMap;
    }

    public static final boolean isCurrentAccessToken(AccessToken accessToken) {
        return accessToken != null && Intrinsics.areEqual((Object) accessToken, (Object) AccessToken.Companion.getCurrentAccessToken());
    }

    public static final String getGraphDomainFromTokenDomain(String str) {
        String facebookDomain = FacebookSdk.getFacebookDomain();
        if (str == null) {
            return facebookDomain;
        }
        if (Intrinsics.areEqual((Object) str, (Object) "gaming")) {
            return StringsKt.replace$default(facebookDomain, "facebook.com", "fb.gg", false, 4, (Object) null);
        }
        return Intrinsics.areEqual((Object) str, (Object) "instagram") ? StringsKt.replace$default(facebookDomain, "facebook.com", "instagram.com", false, 4, (Object) null) : facebookDomain;
    }

    public static final void getGraphMeRequestWithCacheAsync(String str, GraphMeRequestWithCacheCallback graphMeRequestWithCacheCallback) {
        Intrinsics.checkNotNullParameter(str, "accessToken");
        Intrinsics.checkNotNullParameter(graphMeRequestWithCacheCallback, "callback");
        JSONObject profileInformation = ProfileInformationCache.getProfileInformation(str);
        if (profileInformation != null) {
            graphMeRequestWithCacheCallback.onSuccess(profileInformation);
            return;
        }
        Utility$$ExternalSyntheticLambda0 utility$$ExternalSyntheticLambda0 = new Utility$$ExternalSyntheticLambda0(graphMeRequestWithCacheCallback, str);
        GraphRequest graphMeRequestWithCache = INSTANCE.getGraphMeRequestWithCache(str);
        graphMeRequestWithCache.setCallback(utility$$ExternalSyntheticLambda0);
        graphMeRequestWithCache.executeAsync();
    }

    /* access modifiers changed from: private */
    public static final void getGraphMeRequestWithCacheAsync$lambda$3(GraphMeRequestWithCacheCallback graphMeRequestWithCacheCallback, String str, GraphResponse graphResponse) {
        Intrinsics.checkNotNullParameter(graphMeRequestWithCacheCallback, "$callback");
        Intrinsics.checkNotNullParameter(str, "$accessToken");
        Intrinsics.checkNotNullParameter(graphResponse, "response");
        if (graphResponse.getError() != null) {
            graphMeRequestWithCacheCallback.onFailure(graphResponse.getError().getException());
            return;
        }
        JSONObject jsonObject = graphResponse.getJsonObject();
        if (jsonObject != null) {
            ProfileInformationCache.putProfileInformation(str, jsonObject);
            graphMeRequestWithCacheCallback.onSuccess(graphResponse.getJsonObject());
            return;
        }
        throw new IllegalStateException("Required value was null.");
    }

    public static final JSONObject awaitGetGraphMeRequestWithCache(String str) {
        Intrinsics.checkNotNullParameter(str, "accessToken");
        JSONObject profileInformation = ProfileInformationCache.getProfileInformation(str);
        if (profileInformation != null) {
            return profileInformation;
        }
        GraphResponse executeAndWait = INSTANCE.getGraphMeRequestWithCache(str).executeAndWait();
        if (executeAndWait.getError() != null) {
            return null;
        }
        return executeAndWait.getJsonObject();
    }

    private final GraphRequest getGraphMeRequestWithCache(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("fields", getProfileFieldsForGraphDomain(getCurrentTokenDomainWithDefault()));
        bundle.putString("access_token", str);
        GraphRequest newMeRequest = GraphRequest.Companion.newMeRequest((AccessToken) null, (GraphRequest.GraphJSONObjectCallback) null);
        newMeRequest.setParameters(bundle);
        newMeRequest.setHttpMethod(HttpMethod.GET);
        return newMeRequest;
    }

    private final String getProfileFieldsForGraphDomain(String str) {
        if (Intrinsics.areEqual((Object) str, (Object) "instagram")) {
            return "id,name,profile_picture";
        }
        return "id,name,first_name,middle_name,last_name";
    }

    private final String getCurrentTokenDomainWithDefault() {
        AccessToken currentAccessToken = AccessToken.Companion.getCurrentAccessToken();
        if (currentAccessToken == null || currentAccessToken.getGraphDomain() == null) {
            return "facebook";
        }
        return currentAccessToken.getGraphDomain();
    }

    private final int refreshBestGuessNumberOfCPUCores() {
        int i = numCPUCores;
        if (i > 0) {
            return i;
        }
        try {
            File[] listFiles = new File("/sys/devices/system/cpu/").listFiles(new Utility$$ExternalSyntheticLambda1());
            if (listFiles != null) {
                numCPUCores = listFiles.length;
            }
        } catch (Exception unused) {
        }
        if (numCPUCores <= 0) {
            numCPUCores = Math.max(Runtime.getRuntime().availableProcessors(), 1);
        }
        return numCPUCores;
    }

    /* access modifiers changed from: private */
    public static final boolean refreshBestGuessNumberOfCPUCores$lambda$4(File file, String str) {
        return Pattern.matches("cpu[0-9]+", str);
    }

    private final void refreshPeriodicExtendedDeviceInfo(Context context) {
        if (timestampOfLastCheck == -1 || System.currentTimeMillis() - timestampOfLastCheck >= 1800000) {
            timestampOfLastCheck = System.currentTimeMillis();
            refreshTimezone();
            refreshCarrierName(context);
            refreshTotalExternalStorage();
            refreshAvailableExternalStorage();
        }
    }

    private final void refreshTimezone() {
        try {
            TimeZone timeZone = TimeZone.getDefault();
            String displayName = timeZone.getDisplayName(timeZone.inDaylightTime(new Date()), 0);
            Intrinsics.checkNotNullExpressionValue(displayName, "tz.getDisplayName(tz.inD…(Date()), TimeZone.SHORT)");
            deviceTimezoneAbbreviation = displayName;
            String id = timeZone.getID();
            Intrinsics.checkNotNullExpressionValue(id, "tz.id");
            deviceTimeZoneName = id;
        } catch (AssertionError | Exception unused) {
        }
    }

    private final void refreshCarrierName(Context context) {
        if (Intrinsics.areEqual((Object) carrierName, (Object) "NoCarrier")) {
            try {
                Object systemService = context.getSystemService("phone");
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.telephony.TelephonyManager");
                String networkOperatorName = ((TelephonyManager) systemService).getNetworkOperatorName();
                Intrinsics.checkNotNullExpressionValue(networkOperatorName, "telephonyManager.networkOperatorName");
                carrierName = networkOperatorName;
            } catch (Exception unused) {
            }
        }
    }

    private final boolean externalStorageExists() {
        return Intrinsics.areEqual((Object) "mounted", (Object) Environment.getExternalStorageState());
    }

    private final void refreshAvailableExternalStorage() {
        try {
            if (externalStorageExists()) {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                availableExternalStorageGB = ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
            }
            availableExternalStorageGB = convertBytesToGB((double) availableExternalStorageGB);
        } catch (Exception unused) {
        }
    }

    private final void refreshTotalExternalStorage() {
        try {
            if (externalStorageExists()) {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                totalExternalStorageGB = ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
            }
            totalExternalStorageGB = convertBytesToGB((double) totalExternalStorageGB);
        } catch (Exception unused) {
        }
    }

    private final long convertBytesToGB(double d) {
        return Math.round(d / 1.073741824E9d);
    }

    private final void appendAnonIdUnderCompliance(JSONObject jSONObject, AttributionIdentifiers attributionIdentifiers, String str, Context context) {
        if (Build.VERSION.SDK_INT < 31 || !isGooglePlayServicesAvailable(context)) {
            jSONObject.put("anon_id", str);
        } else if (!attributionIdentifiers.isTrackingLimited()) {
            jSONObject.put("anon_id", str);
        }
    }

    private final void appendAttributionIdUnderCompliance(JSONObject jSONObject, AttributionIdentifiers attributionIdentifiers, Context context) {
        if (Build.VERSION.SDK_INT < 31 || !isGooglePlayServicesAvailable(context)) {
            jSONObject.put("attribution", attributionIdentifiers.getAttributionId());
        } else if (!attributionIdentifiers.isTrackingLimited()) {
            jSONObject.put("attribution", attributionIdentifiers.getAttributionId());
        }
    }

    private final boolean isGooglePlayServicesAvailable(Context context) {
        Method methodQuietly = getMethodQuietly("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", Context.class);
        if (methodQuietly == null) {
            return false;
        }
        Object invokeMethodQuietly = invokeMethodQuietly((Object) null, methodQuietly, context);
        if (!(invokeMethodQuietly instanceof Integer) || !Intrinsics.areEqual(invokeMethodQuietly, (Object) 0)) {
            return false;
        }
        return true;
    }

    public static final String generateRandomString(int i) {
        String bigInteger = new BigInteger(i * 5, new Random()).toString(32);
        Intrinsics.checkNotNullExpressionValue(bigInteger, "BigInteger(length * 5, r).toString(32)");
        return bigInteger;
    }

    public static final boolean mustFixWindowParamsForAutofill(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return isAutofillAvailable(context);
    }

    public static final boolean isAutofillAvailable(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        AutofillManager autofillManager = (AutofillManager) context.getSystemService(AutofillManager.class);
        return autofillManager != null && autofillManager.isAutofillSupported() && autofillManager.isEnabled();
    }

    public static final boolean isChromeOS(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (Build.VERSION.SDK_INT >= 27) {
            return context.getPackageManager().hasSystemFeature("android.hardware.type.pc");
        }
        String str = Build.DEVICE;
        if (str != null) {
            Intrinsics.checkNotNullExpressionValue(str, "DEVICE");
            if (new Regex(".+_cheets|cheets_.+").matches(str)) {
                return true;
            }
        }
        return false;
    }

    public static final Locale getResourceLocale() {
        try {
            return FacebookSdk.getApplicationContext().getResources().getConfiguration().locale;
        } catch (Exception unused) {
            return null;
        }
    }

    public static final Locale getCurrentLocale() {
        Locale resourceLocale = getResourceLocale();
        if (resourceLocale != null) {
            return resourceLocale;
        }
        Locale locale2 = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale2, "getDefault()");
        return locale2;
    }

    public static final void runOnNonUiThread(Runnable runnable) {
        try {
            FacebookSdk.getExecutor().execute(runnable);
        } catch (Exception unused) {
        }
    }

    public static final String getAppName(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            String applicationName = FacebookSdk.getApplicationName();
            if (applicationName != null) {
                return applicationName;
            }
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            int i = applicationInfo.labelRes;
            if (i == 0) {
                return applicationInfo.nonLocalizedLabel.toString();
            }
            String string = context.getString(i);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(stringId)");
            return string;
        } catch (Exception unused) {
            return "";
        }
    }

    public static final boolean isAutoAppLinkSetup() {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("fb%s://applinks", Arrays.copyOf(new Object[]{FacebookSdk.getApplicationId()}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            intent.setData(Uri.parse(format));
            Context applicationContext = FacebookSdk.getApplicationContext();
            PackageManager packageManager = applicationContext.getPackageManager();
            String packageName = applicationContext.getPackageName();
            List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
            Intrinsics.checkNotNullExpressionValue(queryIntentActivities, "packageManager.queryInte…nager.MATCH_DEFAULT_ONLY)");
            for (ResolveInfo resolveInfo : queryIntentActivities) {
                if (Intrinsics.areEqual((Object) packageName, (Object) resolveInfo.activityInfo.packageName)) {
                    return true;
                }
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static final JSONObject getDataProcessingOptions() {
        Class<Utility> cls = Utility.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            String string = FacebookSdk.getApplicationContext().getSharedPreferences("com.facebook.sdk.DataProcessingOptions", 0).getString("data_processing_options", (String) null);
            if (string != null) {
                try {
                    return new JSONObject(string);
                } catch (JSONException unused) {
                }
            }
            return null;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final boolean isDataProcessingRestricted() {
        Class<Utility> cls = Utility.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return false;
        }
        try {
            JSONObject dataProcessingOptions = getDataProcessingOptions();
            if (dataProcessingOptions == null) {
                return false;
            }
            try {
                JSONArray jSONArray = dataProcessingOptions.getJSONArray("data_processing_options");
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    String string = jSONArray.getString(i);
                    Intrinsics.checkNotNullExpressionValue(string, "options.getString(i)");
                    String lowerCase = string.toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                    if (Intrinsics.areEqual((Object) lowerCase, (Object) "ldu")) {
                        return true;
                    }
                }
            } catch (Exception unused) {
            }
            return false;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return false;
        }
    }
}
