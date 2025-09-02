package com.facebook.appevents.iap;

import android.content.Context;
import com.facebook.appevents.iap.InAppPurchaseBillingClientWrapperV2V4;
import com.facebook.appevents.iap.InAppPurchaseBillingClientWrapperV5V7;
import com.facebook.appevents.iap.InAppPurchaseUtils;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;

public final class InAppPurchaseAutoLogger {
    public static final InAppPurchaseAutoLogger INSTANCE = new InAppPurchaseAutoLogger();
    private static final AtomicBoolean failedToCreateWrapper = new AtomicBoolean(false);

    private InAppPurchaseAutoLogger() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0079, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final synchronized void startIapLogging(android.content.Context r5, com.facebook.appevents.iap.InAppPurchaseUtils.BillingClientVersion r6) {
        /*
            java.lang.Class<com.facebook.appevents.iap.InAppPurchaseAutoLogger> r0 = com.facebook.appevents.iap.InAppPurchaseAutoLogger.class
            monitor-enter(r0)
            java.lang.Class<com.facebook.appevents.iap.InAppPurchaseAutoLogger> r1 = com.facebook.appevents.iap.InAppPurchaseAutoLogger.class
            boolean r1 = com.facebook.internal.instrument.crashshield.CrashShieldHandler.isObjectCrashing(r1)     // Catch:{ all -> 0x0081 }
            if (r1 == 0) goto L_0x000d
            monitor-exit(r0)
            return
        L_0x000d:
            java.lang.String r1 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r1)     // Catch:{ all -> 0x0033 }
            java.lang.String r1 = "billingClientVersion"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r1)     // Catch:{ all -> 0x0033 }
            java.util.concurrent.atomic.AtomicBoolean r1 = failedToCreateWrapper     // Catch:{ all -> 0x0033 }
            boolean r2 = r1.get()     // Catch:{ all -> 0x0033 }
            if (r2 == 0) goto L_0x0021
            monitor-exit(r0)
            return
        L_0x0021:
            kotlin.jvm.internal.Ref$ObjectRef r2 = new kotlin.jvm.internal.Ref$ObjectRef     // Catch:{ all -> 0x0033 }
            r2.<init>()     // Catch:{ all -> 0x0033 }
            com.facebook.appevents.iap.InAppPurchaseUtils$BillingClientVersion r3 = com.facebook.appevents.iap.InAppPurchaseUtils.BillingClientVersion.V2_V4     // Catch:{ all -> 0x0033 }
            if (r6 != r3) goto L_0x0035
            com.facebook.appevents.iap.InAppPurchaseBillingClientWrapperV2V4$Companion r4 = com.facebook.appevents.iap.InAppPurchaseBillingClientWrapperV2V4.Companion     // Catch:{ all -> 0x0033 }
            com.facebook.appevents.iap.InAppPurchaseBillingClientWrapperV2V4 r4 = r4.getOrCreateInstance(r5)     // Catch:{ all -> 0x0033 }
            r2.element = r4     // Catch:{ all -> 0x0033 }
            goto L_0x0041
        L_0x0033:
            r5 = move-exception
            goto L_0x007a
        L_0x0035:
            com.facebook.appevents.iap.InAppPurchaseUtils$BillingClientVersion r4 = com.facebook.appevents.iap.InAppPurchaseUtils.BillingClientVersion.V5_V7     // Catch:{ all -> 0x0033 }
            if (r6 != r4) goto L_0x0041
            com.facebook.appevents.iap.InAppPurchaseBillingClientWrapperV5V7$Companion r4 = com.facebook.appevents.iap.InAppPurchaseBillingClientWrapperV5V7.Companion     // Catch:{ all -> 0x0033 }
            com.facebook.appevents.iap.InAppPurchaseBillingClientWrapperV5V7 r4 = r4.getOrCreateInstance(r5)     // Catch:{ all -> 0x0033 }
            r2.element = r4     // Catch:{ all -> 0x0033 }
        L_0x0041:
            java.lang.Object r4 = r2.element     // Catch:{ all -> 0x0033 }
            if (r4 != 0) goto L_0x004b
            r5 = 1
            r1.set(r5)     // Catch:{ all -> 0x0033 }
            monitor-exit(r0)
            return
        L_0x004b:
            com.facebook.internal.FeatureManager$Feature r1 = com.facebook.internal.FeatureManager.Feature.AndroidIAPSubscriptionAutoLogging     // Catch:{ all -> 0x0033 }
            boolean r1 = com.facebook.internal.FeatureManager.isEnabled(r1)     // Catch:{ all -> 0x0033 }
            if (r1 == 0) goto L_0x006a
            boolean r1 = com.facebook.appevents.integrity.ProtectedModeManager.isEnabled()     // Catch:{ all -> 0x0033 }
            if (r1 == 0) goto L_0x005b
            if (r6 != r3) goto L_0x006a
        L_0x005b:
            java.lang.Object r1 = r2.element     // Catch:{ all -> 0x0033 }
            com.facebook.appevents.iap.InAppPurchaseBillingClientWrapper r1 = (com.facebook.appevents.iap.InAppPurchaseBillingClientWrapper) r1     // Catch:{ all -> 0x0033 }
            com.facebook.appevents.iap.InAppPurchaseUtils$IAPProductType r3 = com.facebook.appevents.iap.InAppPurchaseUtils.IAPProductType.INAPP     // Catch:{ all -> 0x0033 }
            com.facebook.appevents.iap.InAppPurchaseAutoLogger$$ExternalSyntheticLambda0 r4 = new com.facebook.appevents.iap.InAppPurchaseAutoLogger$$ExternalSyntheticLambda0     // Catch:{ all -> 0x0033 }
            r4.<init>(r2, r6, r5)     // Catch:{ all -> 0x0033 }
            r1.queryPurchaseHistory(r3, r4)     // Catch:{ all -> 0x0033 }
            goto L_0x0078
        L_0x006a:
            java.lang.Object r1 = r2.element     // Catch:{ all -> 0x0033 }
            com.facebook.appevents.iap.InAppPurchaseBillingClientWrapper r1 = (com.facebook.appevents.iap.InAppPurchaseBillingClientWrapper) r1     // Catch:{ all -> 0x0033 }
            com.facebook.appevents.iap.InAppPurchaseUtils$IAPProductType r2 = com.facebook.appevents.iap.InAppPurchaseUtils.IAPProductType.INAPP     // Catch:{ all -> 0x0033 }
            com.facebook.appevents.iap.InAppPurchaseAutoLogger$$ExternalSyntheticLambda1 r3 = new com.facebook.appevents.iap.InAppPurchaseAutoLogger$$ExternalSyntheticLambda1     // Catch:{ all -> 0x0033 }
            r3.<init>(r6, r5)     // Catch:{ all -> 0x0033 }
            r1.queryPurchaseHistory(r2, r3)     // Catch:{ all -> 0x0033 }
        L_0x0078:
            monitor-exit(r0)
            return
        L_0x007a:
            java.lang.Class<com.facebook.appevents.iap.InAppPurchaseAutoLogger> r6 = com.facebook.appevents.iap.InAppPurchaseAutoLogger.class
            com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r5, r6)     // Catch:{ all -> 0x0081 }
            monitor-exit(r0)
            return
        L_0x0081:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0081 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.iap.InAppPurchaseAutoLogger.startIapLogging(android.content.Context, com.facebook.appevents.iap.InAppPurchaseUtils$BillingClientVersion):void");
    }

    /* access modifiers changed from: private */
    public static final void startIapLogging$lambda$1(Ref$ObjectRef ref$ObjectRef, InAppPurchaseUtils.BillingClientVersion billingClientVersion, Context context) {
        Class<InAppPurchaseAutoLogger> cls = InAppPurchaseAutoLogger.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(ref$ObjectRef, "$billingClientWrapper");
                Intrinsics.checkNotNullParameter(billingClientVersion, "$billingClientVersion");
                Intrinsics.checkNotNullParameter(context, "$context");
                ((InAppPurchaseBillingClientWrapper) ref$ObjectRef.element).queryPurchaseHistory(InAppPurchaseUtils.IAPProductType.SUBS, new InAppPurchaseAutoLogger$$ExternalSyntheticLambda2(billingClientVersion, context));
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void startIapLogging$lambda$1$lambda$0(InAppPurchaseUtils.BillingClientVersion billingClientVersion, Context context) {
        Class<InAppPurchaseAutoLogger> cls = InAppPurchaseAutoLogger.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(billingClientVersion, "$billingClientVersion");
                Intrinsics.checkNotNullParameter(context, "$context");
                InAppPurchaseAutoLogger inAppPurchaseAutoLogger = INSTANCE;
                String packageName = context.getPackageName();
                Intrinsics.checkNotNullExpressionValue(packageName, "context.packageName");
                inAppPurchaseAutoLogger.logPurchase(billingClientVersion, packageName);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void startIapLogging$lambda$2(InAppPurchaseUtils.BillingClientVersion billingClientVersion, Context context) {
        Class<InAppPurchaseAutoLogger> cls = InAppPurchaseAutoLogger.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(billingClientVersion, "$billingClientVersion");
                Intrinsics.checkNotNullParameter(context, "$context");
                InAppPurchaseAutoLogger inAppPurchaseAutoLogger = INSTANCE;
                String packageName = context.getPackageName();
                Intrinsics.checkNotNullExpressionValue(packageName, "context.packageName");
                inAppPurchaseAutoLogger.logPurchase(billingClientVersion, packageName);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    private final void logPurchase(InAppPurchaseUtils.BillingClientVersion billingClientVersion, String str) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                boolean isFirstAppLaunchWithNewIAP = InAppPurchaseLoggerManager.getIsFirstAppLaunchWithNewIAP();
                if (isFirstAppLaunchWithNewIAP) {
                    InAppPurchaseLoggerManager.migrateOldCacheHistory();
                }
                if (billingClientVersion == InAppPurchaseUtils.BillingClientVersion.V2_V4) {
                    InAppPurchaseBillingClientWrapperV2V4.Companion companion = InAppPurchaseBillingClientWrapperV2V4.Companion;
                    InAppPurchaseLoggerManager.filterPurchaseLogging(companion.getIapPurchaseDetailsMap(), companion.getSkuDetailsMap(), false, str, billingClientVersion, isFirstAppLaunchWithNewIAP);
                    InAppPurchaseLoggerManager.filterPurchaseLogging(companion.getSubsPurchaseDetailsMap(), companion.getSkuDetailsMap(), true, str, billingClientVersion, isFirstAppLaunchWithNewIAP);
                    companion.getIapPurchaseDetailsMap().clear();
                    companion.getSubsPurchaseDetailsMap().clear();
                } else {
                    InAppPurchaseBillingClientWrapperV5V7.Companion companion2 = InAppPurchaseBillingClientWrapperV5V7.Companion;
                    InAppPurchaseLoggerManager.filterPurchaseLogging(companion2.getIapPurchaseDetailsMap(), companion2.getProductDetailsMap(), false, str, billingClientVersion, isFirstAppLaunchWithNewIAP);
                    InAppPurchaseLoggerManager.filterPurchaseLogging(companion2.getSubsPurchaseDetailsMap(), companion2.getProductDetailsMap(), true, str, billingClientVersion, isFirstAppLaunchWithNewIAP);
                    companion2.getIapPurchaseDetailsMap().clear();
                    companion2.getSubsPurchaseDetailsMap().clear();
                }
                if (isFirstAppLaunchWithNewIAP) {
                    InAppPurchaseLoggerManager.setAppHasBeenLaunchedWithNewIAP();
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }
}
