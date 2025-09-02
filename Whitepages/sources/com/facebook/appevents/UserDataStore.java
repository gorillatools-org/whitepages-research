package com.facebook.appevents;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Patterns;
import com.facebook.FacebookSdk;
import com.facebook.appevents.aam.MetadataRule;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

public final class UserDataStore {
    public static final UserDataStore INSTANCE = new UserDataStore();
    private static final String TAG = UserDataStore.class.getSimpleName();
    private static final ConcurrentHashMap externalHashedUserData = new ConcurrentHashMap();
    private static final AtomicBoolean initialized = new AtomicBoolean(false);
    private static final ConcurrentHashMap internalHashedUserData = new ConcurrentHashMap();
    private static SharedPreferences sharedPreferences;

    private UserDataStore() {
    }

    public static final void initStore() {
        Class<UserDataStore> cls = UserDataStore.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                if (!initialized.get()) {
                    INSTANCE.initAndWait();
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    private final void writeDataIntoCache(String str, String str2) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                FacebookSdk.getExecutor().execute(new UserDataStore$$ExternalSyntheticLambda0(str, str2));
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void writeDataIntoCache$lambda$0(String str, String str2) {
        Class<UserDataStore> cls = UserDataStore.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(str, "$key");
                Intrinsics.checkNotNullParameter(str2, "$value");
                if (!initialized.get()) {
                    INSTANCE.initAndWait();
                }
                SharedPreferences sharedPreferences2 = sharedPreferences;
                if (sharedPreferences2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
                    sharedPreferences2 = null;
                }
                sharedPreferences2.edit().putString(str, str2).apply();
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    private final String normalizeData(String str, String str2) {
        String str3;
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            int length = str2.length() - 1;
            int i = 0;
            boolean z = false;
            while (true) {
                if (i > length) {
                    break;
                }
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
            String lowerCase = str2.subSequence(i, length + 1).toString().toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
            if (Intrinsics.areEqual((Object) "em", (Object) str)) {
                if (Patterns.EMAIL_ADDRESS.matcher(lowerCase).matches()) {
                    return lowerCase;
                }
                Log.e(TAG, "Setting email failure: this is not a valid email address");
                return "";
            } else if (Intrinsics.areEqual((Object) "ph", (Object) str)) {
                return new Regex("[^0-9]").replace(lowerCase, "");
            } else {
                if (!Intrinsics.areEqual((Object) "ge", (Object) str)) {
                    return lowerCase;
                }
                if (lowerCase.length() > 0) {
                    str3 = lowerCase.substring(0, 1);
                    Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                } else {
                    str3 = "";
                }
                if (!Intrinsics.areEqual((Object) "f", (Object) str3)) {
                    if (!Intrinsics.areEqual((Object) "m", (Object) str3)) {
                        Log.e(TAG, "Setting gender failure: the supported value for gender is f or m");
                        return "";
                    }
                }
                return str3;
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    public static final String getAllHashedUserData() {
        Class<UserDataStore> cls = UserDataStore.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            if (!initialized.get()) {
                INSTANCE.initAndWait();
            }
            HashMap hashMap = new HashMap();
            hashMap.putAll(externalHashedUserData);
            hashMap.putAll(INSTANCE.getEnabledInternalUserData());
            return Utility.mapToJsonStr(hashMap);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    private final Map getEnabledInternalUserData() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            HashMap hashMap = new HashMap();
            Set enabledRuleNames = MetadataRule.Companion.getEnabledRuleNames();
            for (String str : internalHashedUserData.keySet()) {
                if (enabledRuleNames.contains(str)) {
                    hashMap.put(str, internalHashedUserData.get(str));
                }
            }
            return hashMap;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    private final synchronized void initAndWait() {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                AtomicBoolean atomicBoolean = initialized;
                if (!atomicBoolean.get()) {
                    SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext());
                    Intrinsics.checkNotNullExpressionValue(defaultSharedPreferences, "getDefaultSharedPreferen….getApplicationContext())");
                    sharedPreferences = defaultSharedPreferences;
                    SharedPreferences sharedPreferences2 = null;
                    if (defaultSharedPreferences == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
                        defaultSharedPreferences = null;
                    }
                    String string = defaultSharedPreferences.getString("com.facebook.appevents.UserDataStore.userData", "");
                    if (string == null) {
                        string = "";
                    }
                    SharedPreferences sharedPreferences3 = sharedPreferences;
                    if (sharedPreferences3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
                    } else {
                        sharedPreferences2 = sharedPreferences3;
                    }
                    String string2 = sharedPreferences2.getString("com.facebook.appevents.UserDataStore.internalUserData", "");
                    if (string2 == null) {
                        string2 = "";
                    }
                    externalHashedUserData.putAll(Utility.jsonStrToMap(string));
                    internalHashedUserData.putAll(Utility.jsonStrToMap(string2));
                    atomicBoolean.set(true);
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00a8, code lost:
        if (r7 != null) goto L_0x00ac;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void setInternalUd(java.util.Map r12) {
        /*
            java.lang.Class<com.facebook.appevents.UserDataStore> r0 = com.facebook.appevents.UserDataStore.class
            boolean r1 = com.facebook.internal.instrument.crashshield.CrashShieldHandler.isObjectCrashing(r0)
            if (r1 == 0) goto L_0x0009
            return
        L_0x0009:
            java.lang.String r1 = "ud"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r1)     // Catch:{ all -> 0x001c }
            java.util.concurrent.atomic.AtomicBoolean r1 = initialized     // Catch:{ all -> 0x001c }
            boolean r1 = r1.get()     // Catch:{ all -> 0x001c }
            if (r1 != 0) goto L_0x001f
            com.facebook.appevents.UserDataStore r1 = INSTANCE     // Catch:{ all -> 0x001c }
            r1.initAndWait()     // Catch:{ all -> 0x001c }
            goto L_0x001f
        L_0x001c:
            r12 = move-exception
            goto L_0x0109
        L_0x001f:
            java.util.Set r12 = r12.entrySet()     // Catch:{ all -> 0x001c }
            java.util.Iterator r12 = r12.iterator()     // Catch:{ all -> 0x001c }
        L_0x0027:
            boolean r1 = r12.hasNext()     // Catch:{ all -> 0x001c }
            if (r1 == 0) goto L_0x00fb
            java.lang.Object r1 = r12.next()     // Catch:{ all -> 0x001c }
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch:{ all -> 0x001c }
            java.lang.Object r2 = r1.getKey()     // Catch:{ all -> 0x001c }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x001c }
            java.lang.Object r1 = r1.getValue()     // Catch:{ all -> 0x001c }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x001c }
            com.facebook.appevents.UserDataStore r3 = INSTANCE     // Catch:{ all -> 0x001c }
            int r4 = r1.length()     // Catch:{ all -> 0x001c }
            r5 = 1
            int r4 = r4 - r5
            r6 = 0
            r7 = r6
            r8 = r7
        L_0x004a:
            if (r7 > r4) goto L_0x006f
            if (r8 != 0) goto L_0x0050
            r9 = r7
            goto L_0x0051
        L_0x0050:
            r9 = r4
        L_0x0051:
            char r9 = r1.charAt(r9)     // Catch:{ all -> 0x001c }
            r10 = 32
            int r9 = kotlin.jvm.internal.Intrinsics.compare((int) r9, (int) r10)     // Catch:{ all -> 0x001c }
            if (r9 > 0) goto L_0x005f
            r9 = r5
            goto L_0x0060
        L_0x005f:
            r9 = r6
        L_0x0060:
            if (r8 != 0) goto L_0x0069
            if (r9 != 0) goto L_0x0066
            r8 = r5
            goto L_0x004a
        L_0x0066:
            int r7 = r7 + 1
            goto L_0x004a
        L_0x0069:
            if (r9 != 0) goto L_0x006c
            goto L_0x006f
        L_0x006c:
            int r4 = r4 + -1
            goto L_0x004a
        L_0x006f:
            int r4 = r4 + 1
            java.lang.CharSequence r1 = r1.subSequence(r7, r4)     // Catch:{ all -> 0x001c }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x001c }
            java.lang.String r1 = r3.normalizeData(r2, r1)     // Catch:{ all -> 0x001c }
            java.lang.String r1 = com.facebook.internal.Utility.sha256hash(r1)     // Catch:{ all -> 0x001c }
            java.util.concurrent.ConcurrentHashMap r3 = internalHashedUserData     // Catch:{ all -> 0x001c }
            boolean r4 = r3.containsKey(r2)     // Catch:{ all -> 0x001c }
            if (r4 == 0) goto L_0x00f6
            java.lang.Object r3 = r3.get(r2)     // Catch:{ all -> 0x001c }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x001c }
            java.lang.String r4 = ","
            if (r3 == 0) goto L_0x00aa
            kotlin.text.Regex r7 = new kotlin.text.Regex     // Catch:{ all -> 0x001c }
            r7.<init>((java.lang.String) r4)     // Catch:{ all -> 0x001c }
            java.util.List r7 = r7.split(r3, r6)     // Catch:{ all -> 0x001c }
            if (r7 == 0) goto L_0x00aa
            java.util.Collection r7 = (java.util.Collection) r7     // Catch:{ all -> 0x001c }
            java.lang.String[] r8 = new java.lang.String[r6]     // Catch:{ all -> 0x001c }
            java.lang.Object[] r7 = r7.toArray(r8)     // Catch:{ all -> 0x001c }
            java.lang.String[] r7 = (java.lang.String[]) r7     // Catch:{ all -> 0x001c }
            if (r7 != 0) goto L_0x00ac
        L_0x00aa:
            java.lang.String[] r7 = new java.lang.String[r6]     // Catch:{ all -> 0x001c }
        L_0x00ac:
            int r8 = r7.length     // Catch:{ all -> 0x001c }
            java.lang.Object[] r8 = java.util.Arrays.copyOf(r7, r8)     // Catch:{ all -> 0x001c }
            java.util.Set r8 = kotlin.collections.SetsKt.mutableSetOf(r8)     // Catch:{ all -> 0x001c }
            boolean r9 = r8.contains(r1)     // Catch:{ all -> 0x001c }
            if (r9 == 0) goto L_0x00bc
            return
        L_0x00bc:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x001c }
            r9.<init>()     // Catch:{ all -> 0x001c }
            int r10 = r7.length     // Catch:{ all -> 0x001c }
            if (r10 != 0) goto L_0x00c8
            r9.append(r1)     // Catch:{ all -> 0x001c }
            goto L_0x00eb
        L_0x00c8:
            int r10 = r7.length     // Catch:{ all -> 0x001c }
            r11 = 5
            if (r10 >= r11) goto L_0x00d6
            r9.append(r3)     // Catch:{ all -> 0x001c }
            r9.append(r4)     // Catch:{ all -> 0x001c }
            r9.append(r1)     // Catch:{ all -> 0x001c }
            goto L_0x00eb
        L_0x00d6:
            if (r5 >= r11) goto L_0x00e3
            r3 = r7[r5]     // Catch:{ all -> 0x001c }
            r9.append(r3)     // Catch:{ all -> 0x001c }
            r9.append(r4)     // Catch:{ all -> 0x001c }
            int r5 = r5 + 1
            goto L_0x00d6
        L_0x00e3:
            r9.append(r1)     // Catch:{ all -> 0x001c }
            r1 = r7[r6]     // Catch:{ all -> 0x001c }
            r8.remove(r1)     // Catch:{ all -> 0x001c }
        L_0x00eb:
            java.util.concurrent.ConcurrentHashMap r1 = internalHashedUserData     // Catch:{ all -> 0x001c }
            java.lang.String r3 = r9.toString()     // Catch:{ all -> 0x001c }
            r1.put(r2, r3)     // Catch:{ all -> 0x001c }
            goto L_0x0027
        L_0x00f6:
            r3.put(r2, r1)     // Catch:{ all -> 0x001c }
            goto L_0x0027
        L_0x00fb:
            com.facebook.appevents.UserDataStore r12 = INSTANCE     // Catch:{ all -> 0x001c }
            java.lang.String r1 = "com.facebook.appevents.UserDataStore.internalUserData"
            java.util.concurrent.ConcurrentHashMap r2 = internalHashedUserData     // Catch:{ all -> 0x001c }
            java.lang.String r2 = com.facebook.internal.Utility.mapToJsonStr(r2)     // Catch:{ all -> 0x001c }
            r12.writeDataIntoCache(r1, r2)     // Catch:{ all -> 0x001c }
            return
        L_0x0109:
            com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r12, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.UserDataStore.setInternalUd(java.util.Map):void");
    }
}
