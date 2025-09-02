package com.facebook.appevents.iap;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import com.facebook.FacebookSdk;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONException;
import org.json.JSONObject;

public final class InAppPurchaseEventManager {
    public static final InAppPurchaseEventManager INSTANCE = new InAppPurchaseEventManager();
    private static final String PACKAGE_NAME = FacebookSdk.getApplicationContext().getPackageName();
    private static final HashMap classMap = new HashMap();
    private static final HashMap methodMap = new HashMap();
    private static final SharedPreferences purchaseInappSharedPrefs = FacebookSdk.getApplicationContext().getSharedPreferences("com.facebook.internal.PURCHASE", 0);
    private static final SharedPreferences skuDetailSharedPrefs = FacebookSdk.getApplicationContext().getSharedPreferences("com.facebook.internal.SKU_DETAILS", 0);

    private InAppPurchaseEventManager() {
    }

    public static final Object asInterface(Context context, IBinder iBinder) {
        Class<InAppPurchaseEventManager> cls = InAppPurchaseEventManager.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(context, "context");
            return INSTANCE.invokeMethod(context, "com.android.vending.billing.IInAppBillingService$Stub", "asInterface", (Object) null, new Object[]{iBinder});
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final Map getSkuDetails(Context context, ArrayList arrayList, Object obj, boolean z) {
        Class<InAppPurchaseEventManager> cls = InAppPurchaseEventManager.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(arrayList, "skuList");
            Map readSkuDetailsFromCache = INSTANCE.readSkuDetailsFromCache(arrayList);
            ArrayList arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (!readSkuDetailsFromCache.containsKey(str)) {
                    arrayList2.add(str);
                }
            }
            readSkuDetailsFromCache.putAll(INSTANCE.getSkuDetailsFromGoogle(context, arrayList2, obj, z));
            return readSkuDetailsFromCache;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    private final Map getSkuDetailsFromGoogle(Context context, ArrayList arrayList, Object obj, boolean z) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            if (obj != null) {
                if (!arrayList.isEmpty()) {
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("ITEM_ID_LIST", arrayList);
                    Object invokeMethod = invokeMethod(context, "com.android.vending.billing.IInAppBillingService", "getSkuDetails", obj, new Object[]{3, PACKAGE_NAME, z ? "subs" : "inapp", bundle});
                    if (invokeMethod != null) {
                        Bundle bundle2 = (Bundle) invokeMethod;
                        if (bundle2.getInt("RESPONSE_CODE") == 0) {
                            ArrayList<String> stringArrayList = bundle2.getStringArrayList("DETAILS_LIST");
                            if (stringArrayList != null && arrayList.size() == stringArrayList.size()) {
                                int size = arrayList.size();
                                for (int i = 0; i < size; i++) {
                                    Object obj2 = arrayList.get(i);
                                    Intrinsics.checkNotNullExpressionValue(obj2, "skuList[i]");
                                    String str = stringArrayList.get(i);
                                    Intrinsics.checkNotNullExpressionValue(str, "skuDetailsList[i]");
                                    linkedHashMap.put(obj2, str);
                                }
                            }
                            writeSkuDetailsToCache(linkedHashMap);
                        }
                    }
                }
            }
            return linkedHashMap;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    private final Map readSkuDetailsFromCache(ArrayList arrayList) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                String string = skuDetailSharedPrefs.getString(str, (String) null);
                if (string != null) {
                    List split$default = StringsKt.split$default((CharSequence) string, new String[]{";"}, false, 2, 2, (Object) null);
                    if (currentTimeMillis - Long.parseLong((String) split$default.get(0)) < 43200) {
                        Intrinsics.checkNotNullExpressionValue(str, "sku");
                        linkedHashMap.put(str, split$default.get(1));
                    }
                }
            }
            return linkedHashMap;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    private final void writeSkuDetailsToCache(Map map) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                long currentTimeMillis = System.currentTimeMillis() / 1000;
                SharedPreferences.Editor edit = skuDetailSharedPrefs.edit();
                for (Map.Entry entry : map.entrySet()) {
                    edit.putString((String) entry.getKey(), currentTimeMillis + ';' + ((String) entry.getValue()));
                }
                edit.apply();
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    private final boolean isBillingSupported(Context context, Object obj, String str) {
        if (CrashShieldHandler.isObjectCrashing(this) || obj == null) {
            return false;
        }
        try {
            Object invokeMethod = invokeMethod(context, "com.android.vending.billing.IInAppBillingService", "isBillingSupported", obj, new Object[]{3, PACKAGE_NAME, str});
            if (invokeMethod == null || ((Integer) invokeMethod).intValue() != 0) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return false;
        }
    }

    public static final ArrayList getPurchasesInapp(Context context, Object obj) {
        Class<InAppPurchaseEventManager> cls = InAppPurchaseEventManager.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(context, "context");
            InAppPurchaseEventManager inAppPurchaseEventManager = INSTANCE;
            return inAppPurchaseEventManager.filterPurchases(inAppPurchaseEventManager.getPurchases(context, obj, "inapp"));
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final ArrayList getPurchasesSubs(Context context, Object obj) {
        Class<InAppPurchaseEventManager> cls = InAppPurchaseEventManager.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(context, "context");
            InAppPurchaseEventManager inAppPurchaseEventManager = INSTANCE;
            return inAppPurchaseEventManager.filterPurchases(inAppPurchaseEventManager.getPurchases(context, obj, "subs"));
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x005a A[EDGE_INSN: B:26:0x005a->B:22:0x005a ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.ArrayList getPurchases(android.content.Context r13, java.lang.Object r14, java.lang.String r15) {
        /*
            r12 = this;
            boolean r0 = com.facebook.internal.instrument.crashshield.CrashShieldHandler.isObjectCrashing(r12)
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x0051 }
            r0.<init>()     // Catch:{ all -> 0x0051 }
            if (r14 != 0) goto L_0x0010
            return r0
        L_0x0010:
            boolean r2 = r12.isBillingSupported(r13, r14, r15)     // Catch:{ all -> 0x0051 }
            if (r2 == 0) goto L_0x005a
            r2 = 0
            r3 = r1
        L_0x0018:
            r4 = 3
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0051 }
            java.lang.String r5 = PACKAGE_NAME     // Catch:{ all -> 0x0051 }
            java.lang.Object[] r11 = new java.lang.Object[]{r4, r5, r15, r3}     // Catch:{ all -> 0x0051 }
            java.lang.String r8 = "com.android.vending.billing.IInAppBillingService"
            java.lang.String r9 = "getPurchases"
            r6 = r12
            r7 = r13
            r10 = r14
            java.lang.Object r3 = r6.invokeMethod(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x0051 }
            if (r3 == 0) goto L_0x0053
            android.os.Bundle r3 = (android.os.Bundle) r3     // Catch:{ all -> 0x0051 }
            java.lang.String r4 = "RESPONSE_CODE"
            int r4 = r3.getInt(r4)     // Catch:{ all -> 0x0051 }
            if (r4 != 0) goto L_0x0053
            java.lang.String r4 = "INAPP_PURCHASE_DATA_LIST"
            java.util.ArrayList r4 = r3.getStringArrayList(r4)     // Catch:{ all -> 0x0051 }
            if (r4 == 0) goto L_0x005a
            int r5 = r4.size()     // Catch:{ all -> 0x0051 }
            int r2 = r2 + r5
            r0.addAll(r4)     // Catch:{ all -> 0x0051 }
            java.lang.String r4 = "INAPP_CONTINUATION_TOKEN"
            java.lang.String r3 = r3.getString(r4)     // Catch:{ all -> 0x0051 }
            goto L_0x0054
        L_0x0051:
            r13 = move-exception
            goto L_0x005b
        L_0x0053:
            r3 = r1
        L_0x0054:
            r4 = 30
            if (r2 >= r4) goto L_0x005a
            if (r3 != 0) goto L_0x0018
        L_0x005a:
            return r0
        L_0x005b:
            com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r13, r12)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.iap.InAppPurchaseEventManager.getPurchases(android.content.Context, java.lang.Object, java.lang.String):java.util.ArrayList");
    }

    public final boolean hasFreeTrialPeirod(String str) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return false;
        }
        try {
            Intrinsics.checkNotNullParameter(str, "skuDetail");
            try {
                String optString = new JSONObject(str).optString("freeTrialPeriod");
                if (optString == null || optString.length() <= 0) {
                    return false;
                }
                return true;
            } catch (JSONException unused) {
                return false;
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0017, code lost:
        r3 = INSTANCE;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.util.ArrayList getPurchaseHistoryInapp(android.content.Context r6, java.lang.Object r7) {
        /*
            java.lang.Class<com.facebook.appevents.iap.InAppPurchaseEventManager> r0 = com.facebook.appevents.iap.InAppPurchaseEventManager.class
            boolean r1 = com.facebook.internal.instrument.crashshield.CrashShieldHandler.isObjectCrashing(r0)
            r2 = 0
            if (r1 == 0) goto L_0x000a
            return r2
        L_0x000a:
            java.lang.String r1 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r1)     // Catch:{ all -> 0x0036 }
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0036 }
            r1.<init>()     // Catch:{ all -> 0x0036 }
            if (r7 != 0) goto L_0x0017
            return r1
        L_0x0017:
            com.facebook.appevents.iap.InAppPurchaseEventManager r3 = INSTANCE     // Catch:{ all -> 0x0036 }
            java.lang.String r4 = "com.android.vending.billing.IInAppBillingService"
            java.lang.Class r4 = r3.getClass(r6, r4)     // Catch:{ all -> 0x0036 }
            if (r4 != 0) goto L_0x0022
            return r1
        L_0x0022:
            java.lang.String r5 = "getPurchaseHistory"
            java.lang.reflect.Method r4 = r3.getMethod(r4, r5)     // Catch:{ all -> 0x0036 }
            if (r4 != 0) goto L_0x002b
            return r1
        L_0x002b:
            java.lang.String r1 = "inapp"
            java.util.ArrayList r6 = r3.getPurchaseHistory(r6, r7, r1)     // Catch:{ all -> 0x0036 }
            java.util.ArrayList r6 = r3.filterPurchases(r6)     // Catch:{ all -> 0x0036 }
            return r6
        L_0x0036:
            r6 = move-exception
            com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r6, r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.iap.InAppPurchaseEventManager.getPurchaseHistoryInapp(android.content.Context, java.lang.Object):java.util.ArrayList");
    }

    private final ArrayList getPurchaseHistory(Context context, Object obj, String str) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            ArrayList arrayList = new ArrayList();
            if (isBillingSupported(context, obj, str)) {
                int i = 0;
                String str2 = null;
                boolean z = false;
                do {
                    Object invokeMethod = invokeMethod(context, "com.android.vending.billing.IInAppBillingService", "getPurchaseHistory", obj, new Object[]{6, PACKAGE_NAME, str, str2, new Bundle()});
                    if (invokeMethod != null) {
                        long currentTimeMillis = System.currentTimeMillis() / 1000;
                        Bundle bundle = (Bundle) invokeMethod;
                        if (bundle.getInt("RESPONSE_CODE") == 0) {
                            ArrayList<String> stringArrayList = bundle.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
                            if (stringArrayList != null) {
                                Iterator<String> it = stringArrayList.iterator();
                                while (true) {
                                    if (!it.hasNext()) {
                                        break;
                                    }
                                    String next = it.next();
                                    try {
                                        if (currentTimeMillis - (new JSONObject(next).getLong("purchaseTime") / 1000) > 1200) {
                                            z = true;
                                            break;
                                        }
                                        arrayList.add(next);
                                        i++;
                                    } catch (JSONException unused) {
                                    }
                                }
                                str2 = bundle.getString("INAPP_CONTINUATION_TOKEN");
                                if (i >= 30 || str2 == null) {
                                    break;
                                }
                            }
                        }
                    }
                    str2 = null;
                } while (!z);
            }
            return arrayList;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    private final ArrayList filterPurchases(ArrayList arrayList) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            ArrayList arrayList2 = new ArrayList();
            SharedPreferences.Editor edit = purchaseInappSharedPrefs.edit();
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    String string = jSONObject.getString("productId");
                    long j = jSONObject.getLong("purchaseTime");
                    String string2 = jSONObject.getString("purchaseToken");
                    if (currentTimeMillis - (j / 1000) <= 86400) {
                        if (!Intrinsics.areEqual((Object) purchaseInappSharedPrefs.getString(string, ""), (Object) string2)) {
                            edit.putString(string, string2);
                            arrayList2.add(str);
                        }
                    }
                } catch (JSONException unused) {
                }
            }
            edit.apply();
            return arrayList2;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.reflect.Method getMethod(java.lang.Class r7, java.lang.String r8) {
        /*
            r6 = this;
            boolean r0 = com.facebook.internal.instrument.crashshield.CrashShieldHandler.isObjectCrashing(r6)
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            java.util.HashMap r0 = methodMap     // Catch:{ all -> 0x0034 }
            java.lang.Object r2 = r0.get(r8)     // Catch:{ all -> 0x0034 }
            java.lang.reflect.Method r2 = (java.lang.reflect.Method) r2     // Catch:{ all -> 0x0034 }
            if (r2 == 0) goto L_0x0013
            return r2
        L_0x0013:
            int r2 = r8.hashCode()     // Catch:{ all -> 0x0034 }
            java.lang.Class<android.os.Bundle> r3 = android.os.Bundle.class
            java.lang.String r4 = "TYPE"
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            switch(r2) {
                case -1801122596: goto L_0x006d;
                case -1450694211: goto L_0x005a;
                case -1123215065: goto L_0x004a;
                case -594356707: goto L_0x0037;
                case -573310373: goto L_0x0021;
                default: goto L_0x0020;
            }
        L_0x0020:
            goto L_0x0075
        L_0x0021:
            java.lang.String r2 = "getSkuDetails"
            boolean r2 = r8.equals(r2)     // Catch:{ all -> 0x0034 }
            if (r2 != 0) goto L_0x002a
            goto L_0x0075
        L_0x002a:
            java.lang.Class r2 = java.lang.Integer.TYPE     // Catch:{ all -> 0x0034 }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)     // Catch:{ all -> 0x0034 }
            java.lang.Class[] r2 = new java.lang.Class[]{r2, r5, r5, r3}     // Catch:{ all -> 0x0034 }
            goto L_0x0080
        L_0x0034:
            r7 = move-exception
            goto L_0x009c
        L_0x0037:
            java.lang.String r2 = "getPurchaseHistory"
            boolean r2 = r8.equals(r2)     // Catch:{ all -> 0x0034 }
            if (r2 != 0) goto L_0x0040
            goto L_0x0075
        L_0x0040:
            java.lang.Class r2 = java.lang.Integer.TYPE     // Catch:{ all -> 0x0034 }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)     // Catch:{ all -> 0x0034 }
            java.lang.Class[] r2 = new java.lang.Class[]{r2, r5, r5, r5, r3}     // Catch:{ all -> 0x0034 }
            goto L_0x0080
        L_0x004a:
            java.lang.String r2 = "asInterface"
            boolean r2 = r8.equals(r2)     // Catch:{ all -> 0x0034 }
            if (r2 != 0) goto L_0x0053
            goto L_0x0075
        L_0x0053:
            java.lang.Class<android.os.IBinder> r2 = android.os.IBinder.class
            java.lang.Class[] r2 = new java.lang.Class[]{r2}     // Catch:{ all -> 0x0034 }
            goto L_0x0080
        L_0x005a:
            java.lang.String r2 = "isBillingSupported"
            boolean r2 = r8.equals(r2)     // Catch:{ all -> 0x0034 }
            if (r2 != 0) goto L_0x0063
            goto L_0x0075
        L_0x0063:
            java.lang.Class r2 = java.lang.Integer.TYPE     // Catch:{ all -> 0x0034 }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)     // Catch:{ all -> 0x0034 }
            java.lang.Class[] r2 = new java.lang.Class[]{r2, r5, r5}     // Catch:{ all -> 0x0034 }
            goto L_0x0080
        L_0x006d:
            java.lang.String r2 = "getPurchases"
            boolean r2 = r8.equals(r2)     // Catch:{ all -> 0x0034 }
            if (r2 != 0) goto L_0x0077
        L_0x0075:
            r2 = r1
            goto L_0x0080
        L_0x0077:
            java.lang.Class r2 = java.lang.Integer.TYPE     // Catch:{ all -> 0x0034 }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)     // Catch:{ all -> 0x0034 }
            java.lang.Class[] r2 = new java.lang.Class[]{r2, r5, r5, r5}     // Catch:{ all -> 0x0034 }
        L_0x0080:
            if (r2 != 0) goto L_0x008b
            java.lang.Class[] r2 = new java.lang.Class[]{r1}     // Catch:{ all -> 0x0034 }
            java.lang.reflect.Method r7 = com.facebook.appevents.iap.InAppPurchaseUtils.getDeclaredMethod$facebook_core_release(r7, r8, r2)     // Catch:{ all -> 0x0034 }
            goto L_0x0096
        L_0x008b:
            int r3 = r2.length     // Catch:{ all -> 0x0034 }
            java.lang.Object[] r2 = java.util.Arrays.copyOf(r2, r3)     // Catch:{ all -> 0x0034 }
            java.lang.Class[] r2 = (java.lang.Class[]) r2     // Catch:{ all -> 0x0034 }
            java.lang.reflect.Method r7 = com.facebook.appevents.iap.InAppPurchaseUtils.getDeclaredMethod$facebook_core_release(r7, r8, r2)     // Catch:{ all -> 0x0034 }
        L_0x0096:
            if (r7 == 0) goto L_0x009b
            r0.put(r8, r7)     // Catch:{ all -> 0x0034 }
        L_0x009b:
            return r7
        L_0x009c:
            com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r7, r6)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.iap.InAppPurchaseEventManager.getMethod(java.lang.Class, java.lang.String):java.lang.reflect.Method");
    }

    private final Class getClass(Context context, String str) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            HashMap hashMap = classMap;
            Class cls = (Class) hashMap.get(str);
            if (cls != null) {
                return cls;
            }
            Class classFromContext$facebook_core_release = InAppPurchaseUtils.getClassFromContext$facebook_core_release(context, str);
            if (classFromContext$facebook_core_release != null) {
                hashMap.put(str, classFromContext$facebook_core_release);
            }
            return classFromContext$facebook_core_release;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    private final Object invokeMethod(Context context, String str, String str2, Object obj, Object[] objArr) {
        Method method;
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            Class cls = getClass(context, str);
            if (cls == null || (method = getMethod(cls, str2)) == null) {
                return null;
            }
            return InAppPurchaseUtils.invokeMethod(cls, method, obj, Arrays.copyOf(objArr, objArr.length));
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    public static final void clearSkuDetailsCache() {
        Class<InAppPurchaseEventManager> cls = InAppPurchaseEventManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                long currentTimeMillis = System.currentTimeMillis() / 1000;
                SharedPreferences sharedPreferences = skuDetailSharedPrefs;
                long j = sharedPreferences.getLong("LAST_CLEARED_TIME", 0);
                if (j == 0) {
                    sharedPreferences.edit().putLong("LAST_CLEARED_TIME", currentTimeMillis).apply();
                } else if (currentTimeMillis - j > 604800) {
                    sharedPreferences.edit().clear().putLong("LAST_CLEARED_TIME", currentTimeMillis).apply();
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }
}
