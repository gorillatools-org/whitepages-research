package com.facebook.appevents.iap;

import android.os.Bundle;
import com.facebook.FacebookSdk;
import com.facebook.appevents.OperationalData;
import com.facebook.appevents.OperationalDataEnum;
import com.facebook.appevents.iap.InAppPurchaseUtils;
import com.facebook.appevents.internal.AutomaticAnalyticsLogger;
import com.facebook.internal.FeatureManager;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

public final class InAppPurchaseManager {
    public static final InAppPurchaseManager INSTANCE = new InAppPurchaseManager();
    private static final AtomicBoolean enabled = new AtomicBoolean(false);
    private static String specificBillingLibraryVersion;
    private static final ConcurrentHashMap timesOfImplicitPurchases = new ConcurrentHashMap();
    private static final ConcurrentHashMap timesOfManualPurchases = new ConcurrentHashMap();

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|5|6|7|8|9|11) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        static {
            /*
                com.facebook.appevents.iap.InAppPurchaseUtils$BillingClientVersion[] r0 = com.facebook.appevents.iap.InAppPurchaseUtils.BillingClientVersion.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.appevents.iap.InAppPurchaseUtils$BillingClientVersion r1 = com.facebook.appevents.iap.InAppPurchaseUtils.BillingClientVersion.NONE     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.facebook.appevents.iap.InAppPurchaseUtils$BillingClientVersion r1 = com.facebook.appevents.iap.InAppPurchaseUtils.BillingClientVersion.V1     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.facebook.appevents.iap.InAppPurchaseUtils$BillingClientVersion r1 = com.facebook.appevents.iap.InAppPurchaseUtils.BillingClientVersion.V2_V4     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.facebook.appevents.iap.InAppPurchaseUtils$BillingClientVersion r1 = com.facebook.appevents.iap.InAppPurchaseUtils.BillingClientVersion.V5_V7     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.iap.InAppPurchaseManager.WhenMappings.<clinit>():void");
        }
    }

    private InAppPurchaseManager() {
    }

    public static final void enableAutoLogging() {
        Class<InAppPurchaseManager> cls = InAppPurchaseManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                if (!AutomaticAnalyticsLogger.isImplicitPurchaseLoggingEnabled()) {
                    InAppPurchaseLoggerManager.updateLatestPossiblePurchaseTime();
                    return;
                }
                enabled.set(true);
                startTracking();
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final void startTracking() {
        Class<InAppPurchaseManager> cls = InAppPurchaseManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                if (enabled.get()) {
                    InAppPurchaseUtils.BillingClientVersion billingClientVersion = INSTANCE.getBillingClientVersion();
                    int i = WhenMappings.$EnumSwitchMapping$0[billingClientVersion.ordinal()];
                    if (i == 2) {
                        InAppPurchaseActivityLifecycleTracker.startIapLogging(InAppPurchaseUtils.BillingClientVersion.V1);
                    } else if (i != 3) {
                        if (i == 4) {
                            if (FeatureManager.isEnabled(FeatureManager.Feature.IapLoggingLib5To7)) {
                                InAppPurchaseAutoLogger.startIapLogging(FacebookSdk.getApplicationContext(), billingClientVersion);
                            }
                        }
                    } else if (FeatureManager.isEnabled(FeatureManager.Feature.IapLoggingLib2)) {
                        InAppPurchaseAutoLogger.startIapLogging(FacebookSdk.getApplicationContext(), billingClientVersion);
                    } else {
                        InAppPurchaseActivityLifecycleTracker.startIapLogging(InAppPurchaseUtils.BillingClientVersion.V2_V4);
                    }
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    private static final void setSpecificBillingLibraryVersion(String str) {
        Class<InAppPurchaseManager> cls = InAppPurchaseManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                specificBillingLibraryVersion = str;
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final String getSpecificBillingLibraryVersion() {
        Class<InAppPurchaseManager> cls = InAppPurchaseManager.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return specificBillingLibraryVersion;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x007f, code lost:
        return com.facebook.appevents.iap.InAppPurchaseUtils.BillingClientVersion.V5_V7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0080, code lost:
        com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r0, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0083, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x007d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.facebook.appevents.iap.InAppPurchaseUtils.BillingClientVersion getBillingClientVersion() {
        /*
            r9 = this;
            boolean r0 = com.facebook.internal.instrument.crashshield.CrashShieldHandler.isObjectCrashing(r9)
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            android.content.Context r0 = com.facebook.FacebookSdk.getApplicationContext()     // Catch:{ Exception -> 0x007d }
            android.content.pm.PackageManager r2 = r0.getPackageManager()     // Catch:{ Exception -> 0x007d }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ Exception -> 0x007d }
            r3 = 128(0x80, float:1.794E-43)
            android.content.pm.ApplicationInfo r0 = r2.getApplicationInfo(r0, r3)     // Catch:{ Exception -> 0x007d }
            java.lang.String r2 = "context.packageManager.g…TA_DATA\n                )"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)     // Catch:{ Exception -> 0x007d }
            android.os.Bundle r0 = r0.metaData     // Catch:{ Exception -> 0x007d }
            java.lang.String r2 = "com.google.android.play.billingclient.version"
            java.lang.String r0 = r0.getString(r2)     // Catch:{ Exception -> 0x007d }
            if (r0 != 0) goto L_0x002e
            com.facebook.appevents.iap.InAppPurchaseUtils$BillingClientVersion r0 = com.facebook.appevents.iap.InAppPurchaseUtils.BillingClientVersion.NONE     // Catch:{ Exception -> 0x007d }
            return r0
        L_0x002c:
            r0 = move-exception
            goto L_0x0080
        L_0x002e:
            java.lang.String r2 = "."
            java.lang.String[] r4 = new java.lang.String[]{r2}     // Catch:{ Exception -> 0x007d }
            r7 = 2
            r8 = 0
            r5 = 0
            r6 = 3
            r3 = r0
            java.util.List r2 = kotlin.text.StringsKt.split$default((java.lang.CharSequence) r3, (java.lang.String[]) r4, (boolean) r5, (int) r6, (int) r7, (java.lang.Object) r8)     // Catch:{ Exception -> 0x007d }
            int r3 = r0.length()     // Catch:{ Exception -> 0x007d }
            if (r3 != 0) goto L_0x0046
            com.facebook.appevents.iap.InAppPurchaseUtils$BillingClientVersion r0 = com.facebook.appevents.iap.InAppPurchaseUtils.BillingClientVersion.V5_V7     // Catch:{ Exception -> 0x007d }
            return r0
        L_0x0046:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x007d }
            r3.<init>()     // Catch:{ Exception -> 0x007d }
            java.lang.String r4 = "GPBL."
            r3.append(r4)     // Catch:{ Exception -> 0x007d }
            r3.append(r0)     // Catch:{ Exception -> 0x007d }
            java.lang.String r0 = r3.toString()     // Catch:{ Exception -> 0x007d }
            setSpecificBillingLibraryVersion(r0)     // Catch:{ Exception -> 0x007d }
            r0 = 0
            java.lang.Object r0 = r2.get(r0)     // Catch:{ Exception -> 0x007d }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Exception -> 0x007d }
            java.lang.Integer r0 = kotlin.text.StringsKt.toIntOrNull(r0)     // Catch:{ Exception -> 0x007d }
            if (r0 == 0) goto L_0x007a
            int r0 = r0.intValue()     // Catch:{ Exception -> 0x007d }
            r2 = 1
            if (r0 != r2) goto L_0x0071
            com.facebook.appevents.iap.InAppPurchaseUtils$BillingClientVersion r0 = com.facebook.appevents.iap.InAppPurchaseUtils.BillingClientVersion.V1     // Catch:{ Exception -> 0x007d }
            goto L_0x0079
        L_0x0071:
            r2 = 5
            if (r0 >= r2) goto L_0x0077
            com.facebook.appevents.iap.InAppPurchaseUtils$BillingClientVersion r0 = com.facebook.appevents.iap.InAppPurchaseUtils.BillingClientVersion.V2_V4     // Catch:{ Exception -> 0x007d }
            goto L_0x0079
        L_0x0077:
            com.facebook.appevents.iap.InAppPurchaseUtils$BillingClientVersion r0 = com.facebook.appevents.iap.InAppPurchaseUtils.BillingClientVersion.V5_V7     // Catch:{ Exception -> 0x007d }
        L_0x0079:
            return r0
        L_0x007a:
            com.facebook.appevents.iap.InAppPurchaseUtils$BillingClientVersion r0 = com.facebook.appevents.iap.InAppPurchaseUtils.BillingClientVersion.V5_V7     // Catch:{ Exception -> 0x007d }
            return r0
        L_0x007d:
            com.facebook.appevents.iap.InAppPurchaseUtils$BillingClientVersion r0 = com.facebook.appevents.iap.InAppPurchaseUtils.BillingClientVersion.V5_V7     // Catch:{ all -> 0x002c }
            return r0
        L_0x0080:
            com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r0, r9)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.iap.InAppPurchaseManager.getBillingClientVersion():com.facebook.appevents.iap.InAppPurchaseUtils$BillingClientVersion");
    }

    public static final synchronized Bundle performDedupe(List list, long j, boolean z, List list2) {
        List list3;
        List<Pair> list4;
        boolean z2;
        Long l;
        String str;
        String str2;
        InAppPurchase inAppPurchase;
        List list5 = list;
        List list6 = list2;
        synchronized (InAppPurchaseManager.class) {
            Long l2 = null;
            if (CrashShieldHandler.isObjectCrashing(InAppPurchaseManager.class)) {
                return null;
            }
            try {
                Intrinsics.checkNotNullParameter(list5, "purchases");
                Intrinsics.checkNotNullParameter(list6, "purchaseParameters");
                if (list6.isEmpty()) {
                    return null;
                }
                if (list.size() != list2.size()) {
                    return null;
                }
                ArrayList arrayList = new ArrayList();
                int size = list.size();
                Bundle bundle = null;
                int i = 0;
                while (i < size) {
                    InAppPurchase inAppPurchase2 = (InAppPurchase) list5.get(i);
                    Pair pair = (Pair) list6.get(i);
                    Bundle bundle2 = (Bundle) pair.component1();
                    OperationalData operationalData = (OperationalData) pair.component2();
                    InAppPurchase inAppPurchase3 = new InAppPurchase(inAppPurchase2.getEventName(), new BigDecimal(String.valueOf(inAppPurchase2.getAmount())).setScale(2, RoundingMode.HALF_UP).doubleValue(), inAppPurchase2.getCurrency());
                    if (z) {
                        list4 = (List) timesOfManualPurchases.get(inAppPurchase3);
                    } else {
                        list4 = (List) timesOfImplicitPurchases.get(inAppPurchase3);
                    }
                    Collection collection = list4;
                    if (collection == null || collection.isEmpty()) {
                        inAppPurchase = inAppPurchase3;
                        str2 = null;
                        str = null;
                        l = null;
                        z2 = false;
                    } else {
                        str2 = l2;
                        str = str2;
                        l = str;
                        z2 = false;
                        for (Pair pair2 : list4) {
                            long longValue = ((Number) pair2.getFirst()).longValue();
                            Pair pair3 = (Pair) pair2.getSecond();
                            Bundle bundle3 = (Bundle) pair3.component1();
                            OperationalData operationalData2 = (OperationalData) pair3.component2();
                            if (Math.abs(j - longValue) <= InAppPurchaseDedupeConfig.INSTANCE.getDedupeWindow()) {
                                if (l == null || longValue < l.longValue()) {
                                    InAppPurchaseManager inAppPurchaseManager = INSTANCE;
                                    InAppPurchase inAppPurchase4 = inAppPurchase3;
                                    String dedupeParameter$default = getDedupeParameter$default(inAppPurchaseManager, bundle2, operationalData, bundle3, operationalData2, !z, false, 32, (Object) null);
                                    String dedupeParameter = inAppPurchaseManager.getDedupeParameter(bundle2, operationalData, bundle3, operationalData2, !z, true);
                                    if (dedupeParameter != null) {
                                        str2 = dedupeParameter;
                                    }
                                    if (dedupeParameter$default != null) {
                                        l = Long.valueOf(longValue);
                                        arrayList.add(new Pair(inAppPurchase4, Long.valueOf(longValue)));
                                        inAppPurchase3 = inAppPurchase4;
                                        str = dedupeParameter$default;
                                        z2 = true;
                                    } else {
                                        inAppPurchase3 = inAppPurchase4;
                                        str = dedupeParameter$default;
                                    }
                                }
                            }
                        }
                        inAppPurchase = inAppPurchase3;
                    }
                    if (str2 != null) {
                        if (bundle == null) {
                            bundle = new Bundle();
                        }
                        bundle.putString("fb_iap_test_dedup_result", "1");
                        bundle.putString("fb_iap_test_dedup_key_used", str2);
                    }
                    if (z2) {
                        if (bundle == null) {
                            bundle = new Bundle();
                        }
                        bundle.putString("fb_iap_non_deduped_event_time", String.valueOf(l != null ? l.longValue() / ((long) 1000) : 0));
                        bundle.putString("fb_iap_actual_dedup_result", "1");
                        bundle.putString("fb_iap_actual_dedup_key_used", str);
                    }
                    if (z && !z2) {
                        ConcurrentHashMap concurrentHashMap = timesOfImplicitPurchases;
                        if (concurrentHashMap.get(inAppPurchase) == null) {
                            concurrentHashMap.put(inAppPurchase, new ArrayList());
                        }
                        List list7 = (List) concurrentHashMap.get(inAppPurchase);
                        if (list7 != null) {
                            list7.add(new Pair(Long.valueOf(j), new Pair(bundle2, operationalData)));
                        }
                    } else if (!z && !z2) {
                        ConcurrentHashMap concurrentHashMap2 = timesOfManualPurchases;
                        if (concurrentHashMap2.get(inAppPurchase) == null) {
                            concurrentHashMap2.put(inAppPurchase, new ArrayList());
                        }
                        List list8 = (List) concurrentHashMap2.get(inAppPurchase);
                        if (list8 != null) {
                            list8.add(new Pair(Long.valueOf(j), new Pair(bundle2, operationalData)));
                        }
                    }
                    i++;
                    list5 = list;
                    list6 = list2;
                    l2 = null;
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    Pair pair4 = (Pair) it.next();
                    if (z) {
                        list3 = (List) timesOfManualPurchases.get(pair4.getFirst());
                    } else {
                        list3 = (List) timesOfImplicitPurchases.get(pair4.getFirst());
                    }
                    if (list3 != null) {
                        Iterator it2 = list3.iterator();
                        int i2 = 0;
                        while (true) {
                            if (!it2.hasNext()) {
                                break;
                            }
                            int i3 = i2 + 1;
                            if (((Number) ((Pair) it2.next()).getFirst()).longValue() == ((Number) pair4.getSecond()).longValue()) {
                                list3.remove(i2);
                                break;
                            }
                            i2 = i3;
                        }
                        if (z) {
                            if (list3.isEmpty()) {
                                timesOfManualPurchases.remove(pair4.getFirst());
                            } else {
                                timesOfManualPurchases.put(pair4.getFirst(), list3);
                            }
                        } else if (list3.isEmpty()) {
                            timesOfImplicitPurchases.remove(pair4.getFirst());
                        } else {
                            timesOfImplicitPurchases.put(pair4.getFirst(), list3);
                        }
                    }
                }
                return bundle;
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, InAppPurchaseManager.class);
                return null;
            }
        }
    }

    public static /* synthetic */ String getDedupeParameter$default(InAppPurchaseManager inAppPurchaseManager, Bundle bundle, OperationalData operationalData, Bundle bundle2, OperationalData operationalData2, boolean z, boolean z2, int i, Object obj) {
        Class<InAppPurchaseManager> cls = InAppPurchaseManager.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return inAppPurchaseManager.getDedupeParameter(bundle, operationalData, bundle2, operationalData2, z, (i & 32) != 0 ? false : z2);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public final String getDedupeParameter(Bundle bundle, OperationalData operationalData, Bundle bundle2, OperationalData operationalData2, boolean z, boolean z2) {
        List<Pair> list;
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        if (z2) {
            try {
                list = InAppPurchaseDedupeConfig.INSTANCE.getTestDedupeParameters(z);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
                return null;
            }
        } else {
            list = InAppPurchaseDedupeConfig.INSTANCE.getDedupeParameters(z);
        }
        if (list == null) {
            return null;
        }
        for (Pair pair : list) {
            Object parameter = OperationalData.Companion.getParameter(OperationalDataEnum.IAPParameters, (String) pair.getFirst(), bundle, operationalData);
            String str = parameter instanceof String ? (String) parameter : null;
            if (str != null) {
                if (str.length() != 0) {
                    for (String str2 : (List) pair.getSecond()) {
                        Object parameter2 = OperationalData.Companion.getParameter(OperationalDataEnum.IAPParameters, str2, bundle2, operationalData2);
                        String str3 = parameter2 instanceof String ? (String) parameter2 : null;
                        if (str3 != null) {
                            if (str3.length() != 0) {
                                if (Intrinsics.areEqual((Object) str3, (Object) str)) {
                                    return z ? (String) pair.getFirst() : str2;
                                }
                            }
                        }
                    }
                    continue;
                }
            }
        }
        return null;
    }
}
