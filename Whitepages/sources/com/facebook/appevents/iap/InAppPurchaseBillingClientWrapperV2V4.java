package com.facebook.appevents.iap;

import android.content.Context;
import android.util.Log;
import com.facebook.appevents.iap.InAppPurchaseUtils;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONObject;

public final class InAppPurchaseBillingClientWrapperV2V4 implements InAppPurchaseBillingClientWrapper {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = InAppPurchaseBillingClientWrapperV2V4.class.getCanonicalName();
    private static final Map iapPurchaseDetailsMap = new ConcurrentHashMap();
    private static InAppPurchaseBillingClientWrapperV2V4 instance;
    private static final AtomicBoolean isServiceConnected = new AtomicBoolean(false);
    private static final Map skuDetailsMap = new ConcurrentHashMap();
    private static final Map subsPurchaseDetailsMap = new ConcurrentHashMap();
    private final Object billingClient;
    private final Class billingClientClazz;
    private final Method getOriginalJsonMethod;
    private final Method getOriginalJsonPurchaseHistoryMethod;
    private final Method getOriginalJsonSkuMethod;
    private final Method getPurchaseListMethod;
    private final InAppPurchaseSkuDetailsWrapper inAppPurchaseSkuDetailsWrapper;
    private final Class purchaseClazz;
    private final Class purchaseHistoryRecordClazz;
    private final Class purchaseHistoryResponseListenerClazz;
    private final Class purchaseResultClazz;
    private final Method queryPurchaseHistoryAsyncMethod;
    private final Method queryPurchasesMethod;
    private final Method querySkuDetailsAsyncMethod;
    private final Class skuDetailsClazz;
    private final Class skuDetailsResponseListenerClazz;

    public /* synthetic */ InAppPurchaseBillingClientWrapperV2V4(Object obj, Class cls, Class cls2, Class cls3, Class cls4, Class cls5, Class cls6, Class cls7, Method method, Method method2, Method method3, Method method4, Method method5, Method method6, Method method7, InAppPurchaseSkuDetailsWrapper inAppPurchaseSkuDetailsWrapper2, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, cls, cls2, cls3, cls4, cls5, cls6, cls7, method, method2, method3, method4, method5, method6, method7, inAppPurchaseSkuDetailsWrapper2);
    }

    private InAppPurchaseBillingClientWrapperV2V4(Object obj, Class cls, Class cls2, Class cls3, Class cls4, Class cls5, Class cls6, Class cls7, Method method, Method method2, Method method3, Method method4, Method method5, Method method6, Method method7, InAppPurchaseSkuDetailsWrapper inAppPurchaseSkuDetailsWrapper2) {
        this.billingClient = obj;
        this.billingClientClazz = cls;
        this.purchaseResultClazz = cls2;
        this.purchaseClazz = cls3;
        this.skuDetailsClazz = cls4;
        this.purchaseHistoryRecordClazz = cls5;
        this.skuDetailsResponseListenerClazz = cls6;
        this.purchaseHistoryResponseListenerClazz = cls7;
        this.queryPurchasesMethod = method;
        this.getPurchaseListMethod = method2;
        this.getOriginalJsonMethod = method3;
        this.getOriginalJsonSkuMethod = method4;
        this.getOriginalJsonPurchaseHistoryMethod = method5;
        this.querySkuDetailsAsyncMethod = method6;
        this.queryPurchaseHistoryAsyncMethod = method7;
        this.inAppPurchaseSkuDetailsWrapper = inAppPurchaseSkuDetailsWrapper2;
    }

    public static final /* synthetic */ Method access$getGetOriginalJsonPurchaseHistoryMethod$p(InAppPurchaseBillingClientWrapperV2V4 inAppPurchaseBillingClientWrapperV2V4) {
        Class<InAppPurchaseBillingClientWrapperV2V4> cls = InAppPurchaseBillingClientWrapperV2V4.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return inAppPurchaseBillingClientWrapperV2V4.getOriginalJsonPurchaseHistoryMethod;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final /* synthetic */ Method access$getGetOriginalJsonSkuMethod$p(InAppPurchaseBillingClientWrapperV2V4 inAppPurchaseBillingClientWrapperV2V4) {
        Class<InAppPurchaseBillingClientWrapperV2V4> cls = InAppPurchaseBillingClientWrapperV2V4.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return inAppPurchaseBillingClientWrapperV2V4.getOriginalJsonSkuMethod;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final /* synthetic */ Map access$getIapPurchaseDetailsMap$cp() {
        Class<InAppPurchaseBillingClientWrapperV2V4> cls = InAppPurchaseBillingClientWrapperV2V4.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return iapPurchaseDetailsMap;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final /* synthetic */ InAppPurchaseBillingClientWrapperV2V4 access$getInstance$cp() {
        Class<InAppPurchaseBillingClientWrapperV2V4> cls = InAppPurchaseBillingClientWrapperV2V4.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return instance;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final /* synthetic */ Class access$getPurchaseHistoryRecordClazz$p(InAppPurchaseBillingClientWrapperV2V4 inAppPurchaseBillingClientWrapperV2V4) {
        Class<InAppPurchaseBillingClientWrapperV2V4> cls = InAppPurchaseBillingClientWrapperV2V4.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return inAppPurchaseBillingClientWrapperV2V4.purchaseHistoryRecordClazz;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final /* synthetic */ Class access$getSkuDetailsClazz$p(InAppPurchaseBillingClientWrapperV2V4 inAppPurchaseBillingClientWrapperV2V4) {
        Class<InAppPurchaseBillingClientWrapperV2V4> cls = InAppPurchaseBillingClientWrapperV2V4.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return inAppPurchaseBillingClientWrapperV2V4.skuDetailsClazz;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final /* synthetic */ Map access$getSkuDetailsMap$cp() {
        Class<InAppPurchaseBillingClientWrapperV2V4> cls = InAppPurchaseBillingClientWrapperV2V4.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return skuDetailsMap;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final /* synthetic */ Map access$getSubsPurchaseDetailsMap$cp() {
        Class<InAppPurchaseBillingClientWrapperV2V4> cls = InAppPurchaseBillingClientWrapperV2V4.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return subsPurchaseDetailsMap;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final /* synthetic */ String access$getTAG$cp() {
        Class<InAppPurchaseBillingClientWrapperV2V4> cls = InAppPurchaseBillingClientWrapperV2V4.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return TAG;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final /* synthetic */ AtomicBoolean access$isServiceConnected$cp() {
        Class<InAppPurchaseBillingClientWrapperV2V4> cls = InAppPurchaseBillingClientWrapperV2V4.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return isServiceConnected;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final /* synthetic */ void access$querySkuDetailsAsync(InAppPurchaseBillingClientWrapperV2V4 inAppPurchaseBillingClientWrapperV2V4, InAppPurchaseUtils.IAPProductType iAPProductType, List list, Runnable runnable) {
        Class<InAppPurchaseBillingClientWrapperV2V4> cls = InAppPurchaseBillingClientWrapperV2V4.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                inAppPurchaseBillingClientWrapperV2V4.querySkuDetailsAsync(iAPProductType, list, runnable);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final /* synthetic */ void access$setInstance$cp(InAppPurchaseBillingClientWrapperV2V4 inAppPurchaseBillingClientWrapperV2V4) {
        Class<InAppPurchaseBillingClientWrapperV2V4> cls = InAppPurchaseBillingClientWrapperV2V4.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                instance = inAppPurchaseBillingClientWrapperV2V4;
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public Object getBillingClient() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            return this.billingClient;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    private final void querySkuDetailsAsync(InAppPurchaseUtils.IAPProductType iAPProductType, List list, Runnable runnable) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                executeServiceRequest(new InAppPurchaseBillingClientWrapperV2V4$$ExternalSyntheticLambda1(this, runnable, iAPProductType, list));
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void querySkuDetailsAsync$lambda$1(InAppPurchaseBillingClientWrapperV2V4 inAppPurchaseBillingClientWrapperV2V4, Runnable runnable, InAppPurchaseUtils.IAPProductType iAPProductType, List list) {
        Class<InAppPurchaseBillingClientWrapperV2V4> cls = InAppPurchaseBillingClientWrapperV2V4.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(inAppPurchaseBillingClientWrapperV2V4, "this$0");
                Intrinsics.checkNotNullParameter(runnable, "$completionHandler");
                Intrinsics.checkNotNullParameter(iAPProductType, "$skuType");
                Intrinsics.checkNotNullParameter(list, "$skuIDs");
                Object newProxyInstance = Proxy.newProxyInstance(inAppPurchaseBillingClientWrapperV2V4.skuDetailsResponseListenerClazz.getClassLoader(), new Class[]{inAppPurchaseBillingClientWrapperV2V4.skuDetailsResponseListenerClazz}, new SkuDetailsResponseListenerWrapper(inAppPurchaseBillingClientWrapperV2V4, runnable));
                InAppPurchaseUtils.invokeMethod(inAppPurchaseBillingClientWrapperV2V4.billingClientClazz, inAppPurchaseBillingClientWrapperV2V4.querySkuDetailsAsyncMethod, inAppPurchaseBillingClientWrapperV2V4.getBillingClient(), inAppPurchaseBillingClientWrapperV2V4.inAppPurchaseSkuDetailsWrapper.getSkuDetailsParams(iAPProductType, list), newProxyInstance);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public void queryPurchaseHistory(InAppPurchaseUtils.IAPProductType iAPProductType, Runnable runnable) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                Intrinsics.checkNotNullParameter(iAPProductType, "productType");
                Intrinsics.checkNotNullParameter(runnable, "completionHandler");
                executeServiceRequest(new InAppPurchaseBillingClientWrapperV2V4$$ExternalSyntheticLambda0(this, iAPProductType, runnable));
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void queryPurchaseHistory$lambda$2(InAppPurchaseBillingClientWrapperV2V4 inAppPurchaseBillingClientWrapperV2V4, InAppPurchaseUtils.IAPProductType iAPProductType, Runnable runnable) {
        Class<InAppPurchaseBillingClientWrapperV2V4> cls = InAppPurchaseBillingClientWrapperV2V4.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(inAppPurchaseBillingClientWrapperV2V4, "this$0");
                Intrinsics.checkNotNullParameter(iAPProductType, "$productType");
                Intrinsics.checkNotNullParameter(runnable, "$completionHandler");
                InAppPurchaseUtils.invokeMethod(inAppPurchaseBillingClientWrapperV2V4.billingClientClazz, inAppPurchaseBillingClientWrapperV2V4.queryPurchaseHistoryAsyncMethod, inAppPurchaseBillingClientWrapperV2V4.getBillingClient(), iAPProductType.getType(), Proxy.newProxyInstance(inAppPurchaseBillingClientWrapperV2V4.purchaseHistoryResponseListenerClazz.getClassLoader(), new Class[]{inAppPurchaseBillingClientWrapperV2V4.purchaseHistoryResponseListenerClazz}, new PurchaseHistoryResponseListenerWrapper(inAppPurchaseBillingClientWrapperV2V4, iAPProductType, runnable)));
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    private final void executeServiceRequest(Runnable runnable) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                if (isServiceConnected.get()) {
                    runnable.run();
                } else {
                    startConnection(runnable);
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    private final void startConnection(Runnable runnable) {
        Method method;
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                Class cls = InAppPurchaseUtils.getClass("com.android.billingclient.api.BillingClientStateListener");
                if (cls != null && (method = InAppPurchaseUtils.getMethod(this.billingClientClazz, "startConnection", cls)) != null) {
                    InAppPurchaseUtils.invokeMethod(this.billingClientClazz, method, getBillingClient(), Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new BillingClientStateListenerWrapper(runnable)));
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public static final class BillingClientStateListenerWrapper implements InvocationHandler {
        private final Runnable runnable;

        public BillingClientStateListenerWrapper(Runnable runnable2) {
            this.runnable = runnable2;
        }

        public Object invoke(Object obj, Method method, Object[] objArr) {
            Method method2;
            if (CrashShieldHandler.isObjectCrashing(this)) {
                return null;
            }
            try {
                Intrinsics.checkNotNullParameter(obj, "proxy");
                Intrinsics.checkNotNullParameter(method, "m");
                if (Intrinsics.areEqual((Object) method.getName(), (Object) "onBillingSetupFinished")) {
                    Object orNull = objArr != null ? ArraysKt.getOrNull(objArr, 0) : null;
                    Class cls = InAppPurchaseUtils.getClass("com.android.billingclient.api.BillingResult");
                    if (!(cls == null || (method2 = InAppPurchaseUtils.getMethod(cls, "getResponseCode", new Class[0])) == null || !Intrinsics.areEqual(InAppPurchaseUtils.invokeMethod(cls, method2, orNull, new Object[0]), (Object) 0))) {
                        InAppPurchaseBillingClientWrapperV2V4.Companion.isServiceConnected().set(true);
                        Runnable runnable2 = this.runnable;
                        if (runnable2 != null) {
                            runnable2.run();
                        }
                    }
                } else {
                    String name = method.getName();
                    Intrinsics.checkNotNullExpressionValue(name, "m.name");
                    if (StringsKt.endsWith$default(name, "onBillingServiceDisconnected", false, 2, (Object) null)) {
                        InAppPurchaseBillingClientWrapperV2V4.Companion.isServiceConnected().set(false);
                    }
                }
                return null;
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
                return null;
            }
        }
    }

    public static final class PurchasesUpdatedListenerWrapper implements InvocationHandler {
        public Object invoke(Object obj, Method method, Object[] objArr) {
            if (CrashShieldHandler.isObjectCrashing(this)) {
                return null;
            }
            try {
                Intrinsics.checkNotNullParameter(obj, "proxy");
                Intrinsics.checkNotNullParameter(method, "m");
                return null;
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
                return null;
            }
        }
    }

    public final class PurchaseHistoryResponseListenerWrapper implements InvocationHandler {
        private Runnable completionHandler;
        private InAppPurchaseUtils.IAPProductType skuType;
        final /* synthetic */ InAppPurchaseBillingClientWrapperV2V4 this$0;

        public PurchaseHistoryResponseListenerWrapper(InAppPurchaseBillingClientWrapperV2V4 inAppPurchaseBillingClientWrapperV2V4, InAppPurchaseUtils.IAPProductType iAPProductType, Runnable runnable) {
            Intrinsics.checkNotNullParameter(iAPProductType, "skuType");
            Intrinsics.checkNotNullParameter(runnable, "completionHandler");
            this.this$0 = inAppPurchaseBillingClientWrapperV2V4;
            this.skuType = iAPProductType;
            this.completionHandler = runnable;
        }

        public void invoke(Object obj, Method method, Object[] objArr) {
            if (!CrashShieldHandler.isObjectCrashing(this)) {
                try {
                    Intrinsics.checkNotNullParameter(obj, "proxy");
                    Intrinsics.checkNotNullParameter(method, FirebaseAnalytics.Param.METHOD);
                    if (Intrinsics.areEqual((Object) method.getName(), (Object) "onPurchaseHistoryResponse")) {
                        Object orNull = objArr != null ? ArraysKt.getOrNull(objArr, 1) : null;
                        if (orNull == null) {
                            return;
                        }
                        if (orNull instanceof List) {
                            ArrayList arrayList = new ArrayList();
                            for (Object invokeMethod : (List) orNull) {
                                try {
                                    Object invokeMethod2 = InAppPurchaseUtils.invokeMethod(InAppPurchaseBillingClientWrapperV2V4.access$getPurchaseHistoryRecordClazz$p(this.this$0), InAppPurchaseBillingClientWrapperV2V4.access$getGetOriginalJsonPurchaseHistoryMethod$p(this.this$0), invokeMethod, new Object[0]);
                                    String str = invokeMethod2 instanceof String ? (String) invokeMethod2 : null;
                                    if (str != null) {
                                        JSONObject jSONObject = new JSONObject(str);
                                        if (jSONObject.has("productId")) {
                                            String string = jSONObject.getString("productId");
                                            Intrinsics.checkNotNullExpressionValue(string, "skuID");
                                            arrayList.add(string);
                                            if (this.skuType == InAppPurchaseUtils.IAPProductType.INAPP) {
                                                InAppPurchaseBillingClientWrapperV2V4.Companion.getIapPurchaseDetailsMap().put(string, jSONObject);
                                            } else {
                                                InAppPurchaseBillingClientWrapperV2V4.Companion.getSubsPurchaseDetailsMap().put(string, jSONObject);
                                            }
                                        }
                                    }
                                } catch (Exception unused) {
                                }
                            }
                            if (!arrayList.isEmpty()) {
                                InAppPurchaseBillingClientWrapperV2V4.access$querySkuDetailsAsync(this.this$0, this.skuType, arrayList, this.completionHandler);
                            } else {
                                this.completionHandler.run();
                            }
                        }
                    }
                } catch (Throwable th) {
                    CrashShieldHandler.handleThrowable(th, this);
                }
            }
        }
    }

    public final class SkuDetailsResponseListenerWrapper implements InvocationHandler {
        private Runnable completionHandler;
        final /* synthetic */ InAppPurchaseBillingClientWrapperV2V4 this$0;

        public SkuDetailsResponseListenerWrapper(InAppPurchaseBillingClientWrapperV2V4 inAppPurchaseBillingClientWrapperV2V4, Runnable runnable) {
            Intrinsics.checkNotNullParameter(runnable, "completionHandler");
            this.this$0 = inAppPurchaseBillingClientWrapperV2V4;
            this.completionHandler = runnable;
        }

        public void invoke(Object obj, Method method, Object[] objArr) {
            if (!CrashShieldHandler.isObjectCrashing(this)) {
                try {
                    Intrinsics.checkNotNullParameter(obj, "proxy");
                    Intrinsics.checkNotNullParameter(method, "m");
                    if (Intrinsics.areEqual((Object) method.getName(), (Object) "onSkuDetailsResponse")) {
                        Object orNull = objArr != null ? ArraysKt.getOrNull(objArr, 1) : null;
                        if (orNull == null) {
                            return;
                        }
                        if (orNull instanceof List) {
                            for (Object invokeMethod : (List) orNull) {
                                try {
                                    Object invokeMethod2 = InAppPurchaseUtils.invokeMethod(InAppPurchaseBillingClientWrapperV2V4.access$getSkuDetailsClazz$p(this.this$0), InAppPurchaseBillingClientWrapperV2V4.access$getGetOriginalJsonSkuMethod$p(this.this$0), invokeMethod, new Object[0]);
                                    String str = invokeMethod2 instanceof String ? (String) invokeMethod2 : null;
                                    if (str != null) {
                                        JSONObject jSONObject = new JSONObject(str);
                                        if (jSONObject.has("productId")) {
                                            String string = jSONObject.getString("productId");
                                            Map skuDetailsMap = InAppPurchaseBillingClientWrapperV2V4.Companion.getSkuDetailsMap();
                                            Intrinsics.checkNotNullExpressionValue(string, "skuID");
                                            skuDetailsMap.put(string, jSONObject);
                                        }
                                    }
                                } catch (Exception unused) {
                                }
                            }
                            this.completionHandler.run();
                        }
                    }
                } catch (Throwable th) {
                    CrashShieldHandler.handleThrowable(th, this);
                }
            }
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AtomicBoolean isServiceConnected() {
            return InAppPurchaseBillingClientWrapperV2V4.access$isServiceConnected$cp();
        }

        public final Map getIapPurchaseDetailsMap() {
            return InAppPurchaseBillingClientWrapperV2V4.access$getIapPurchaseDetailsMap$cp();
        }

        public final Map getSubsPurchaseDetailsMap() {
            return InAppPurchaseBillingClientWrapperV2V4.access$getSubsPurchaseDetailsMap$cp();
        }

        public final Map getSkuDetailsMap() {
            return InAppPurchaseBillingClientWrapperV2V4.access$getSkuDetailsMap$cp();
        }

        public final synchronized InAppPurchaseBillingClientWrapperV2V4 getOrCreateInstance(Context context) {
            InAppPurchaseBillingClientWrapperV2V4 access$getInstance$cp;
            Intrinsics.checkNotNullParameter(context, "context");
            access$getInstance$cp = InAppPurchaseBillingClientWrapperV2V4.access$getInstance$cp();
            if (access$getInstance$cp == null) {
                access$getInstance$cp = createInstance(context);
            }
            return access$getInstance$cp;
        }

        private final InAppPurchaseBillingClientWrapperV2V4 createInstance(Context context) {
            InAppPurchaseSkuDetailsWrapper orCreateInstance = InAppPurchaseSkuDetailsWrapper.Companion.getOrCreateInstance();
            if (orCreateInstance == null) {
                return null;
            }
            Class cls = InAppPurchaseUtils.getClass("com.android.billingclient.api.BillingClient");
            Class cls2 = InAppPurchaseUtils.getClass("com.android.billingclient.api.Purchase");
            Class cls3 = InAppPurchaseUtils.getClass("com.android.billingclient.api.Purchase$PurchasesResult");
            Class cls4 = InAppPurchaseUtils.getClass("com.android.billingclient.api.SkuDetails");
            Class cls5 = InAppPurchaseUtils.getClass("com.android.billingclient.api.PurchaseHistoryRecord");
            Class cls6 = InAppPurchaseUtils.getClass("com.android.billingclient.api.SkuDetailsResponseListener");
            Class cls7 = InAppPurchaseUtils.getClass("com.android.billingclient.api.PurchaseHistoryResponseListener");
            if (cls == null || cls3 == null || cls2 == null || cls4 == null || cls6 == null || cls5 == null || cls7 == null) {
                Log.w(InAppPurchaseBillingClientWrapperV2V4.access$getTAG$cp(), "Failed to create Google Play billing library wrapper for in-app purchase auto-logging");
                return null;
            }
            Class<String> cls8 = String.class;
            Method method = InAppPurchaseUtils.getMethod(cls, "queryPurchases", cls8);
            Method method2 = InAppPurchaseUtils.getMethod(cls3, "getPurchasesList", new Class[0]);
            Method method3 = InAppPurchaseUtils.getMethod(cls2, "getOriginalJson", new Class[0]);
            Method method4 = InAppPurchaseUtils.getMethod(cls4, "getOriginalJson", new Class[0]);
            Method method5 = InAppPurchaseUtils.getMethod(cls5, "getOriginalJson", new Class[0]);
            Method method6 = InAppPurchaseUtils.getMethod(cls, "querySkuDetailsAsync", orCreateInstance.getSkuDetailsParamsClazz(), cls6);
            Method method7 = InAppPurchaseUtils.getMethod(cls, "queryPurchaseHistoryAsync", cls8, cls7);
            if (method == null || method2 == null || method3 == null || method4 == null || method5 == null || method6 == null || method7 == null) {
                Log.w(InAppPurchaseBillingClientWrapperV2V4.access$getTAG$cp(), "Failed to create Google Play billing library wrapper for in-app purchase auto-logging");
                return null;
            }
            Object createBillingClient = createBillingClient(context, cls);
            if (createBillingClient == null) {
                Log.w(InAppPurchaseBillingClientWrapperV2V4.access$getTAG$cp(), "Failed to build a Google Play billing library wrapper for in-app purchase auto-logging");
                return null;
            }
            InAppPurchaseBillingClientWrapperV2V4.access$setInstance$cp(new InAppPurchaseBillingClientWrapperV2V4(createBillingClient, cls, cls3, cls2, cls4, cls5, cls6, cls7, method, method2, method3, method4, method5, method6, method7, orCreateInstance, (DefaultConstructorMarker) null));
            return InAppPurchaseBillingClientWrapperV2V4.access$getInstance$cp();
        }

        private final Object createBillingClient(Context context, Class cls) {
            Object invokeMethod;
            Object invokeMethod2;
            Object invokeMethod3;
            Class cls2 = InAppPurchaseUtils.getClass("com.android.billingclient.api.BillingClient$Builder");
            Class cls3 = InAppPurchaseUtils.getClass("com.android.billingclient.api.PurchasesUpdatedListener");
            if (cls2 == null || cls3 == null) {
                return null;
            }
            Method method = InAppPurchaseUtils.getMethod(cls, "newBuilder", Context.class);
            Method method2 = InAppPurchaseUtils.getMethod(cls2, "enablePendingPurchases", new Class[0]);
            Method method3 = InAppPurchaseUtils.getMethod(cls2, "setListener", cls3);
            Method method4 = InAppPurchaseUtils.getMethod(cls2, "build", new Class[0]);
            if (method == null || method2 == null || method3 == null || method4 == null || (invokeMethod = InAppPurchaseUtils.invokeMethod(cls, method, (Object) null, context)) == null || (invokeMethod2 = InAppPurchaseUtils.invokeMethod(cls2, method3, invokeMethod, Proxy.newProxyInstance(cls3.getClassLoader(), new Class[]{cls3}, new PurchasesUpdatedListenerWrapper()))) == null || (invokeMethod3 = InAppPurchaseUtils.invokeMethod(cls2, method2, invokeMethod2, new Object[0])) == null) {
                return null;
            }
            return InAppPurchaseUtils.invokeMethod(cls2, method4, invokeMethod3, new Object[0]);
        }
    }
}
