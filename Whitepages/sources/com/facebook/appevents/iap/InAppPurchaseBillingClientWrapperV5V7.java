package com.facebook.appevents.iap;

import android.content.Context;
import android.util.Log;
import com.facebook.appevents.iap.InAppPurchaseUtils;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import org.json.JSONObject;

public final class InAppPurchaseBillingClientWrapperV5V7 implements InAppPurchaseBillingClientWrapper {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = InAppPurchaseBillingClientWrapperV5V7.class.getCanonicalName();
    private static final Map iapPurchaseDetailsMap = new ConcurrentHashMap();
    private static InAppPurchaseBillingClientWrapperV5V7 instance;
    private static final AtomicBoolean isServiceConnected = new AtomicBoolean(false);
    private static final Map productDetailsMap = new ConcurrentHashMap();
    private static final Map subsPurchaseDetailsMap = new ConcurrentHashMap();
    private final Object billingClient;
    private final Class billingClientClazz;
    private final Method billingClientStartConnectionMethod;
    private final Class billingClientStateListenerClazz;
    private final Class billingResultClazz;
    private final Method billingResultGetResponseCodeMethod;
    private final Class productDetailsClazz;
    private final Class productDetailsResponseListenerClazz;
    private final Method productDetailsToStringMethod;
    private final Class purchaseClazz;
    private final Method purchaseGetOriginalJsonMethod;
    private final Class purchaseHistoryRecordClazz;
    private final Method purchaseHistoryRecordGetOriginalJsonMethod;
    private final Class purchaseHistoryResponseListenerClazz;
    private final Class purchasesResponseListenerClazz;
    private final Method queryProductDetailsAsyncMethod;
    private final Method queryProductDetailsParamsBuilderBuildMethod;
    private final Class queryProductDetailsParamsBuilderClazz;
    private final Method queryProductDetailsParamsBuilderSetProductListMethod;
    private final Class queryProductDetailsParamsClazz;
    private final Method queryProductDetailsParamsNewBuilderMethod;
    private final Method queryProductDetailsParamsProductBuilderBuildMethod;
    private final Class queryProductDetailsParamsProductBuilderClazz;
    private final Method queryProductDetailsParamsProductBuilderSetProductIdMethod;
    private final Method queryProductDetailsParamsProductBuilderSetProductTypeMethod;
    private final Class queryProductDetailsParamsProductClazz;
    private final Method queryProductDetailsParamsProductNewBuilderMethod;
    private final Method queryPurchaseHistoryAsyncMethod;
    private final Method queryPurchaseHistoryParamsBuilderBuildMethod;
    private final Class queryPurchaseHistoryParamsBuilderClazz;
    private final Method queryPurchaseHistoryParamsBuilderSetProductTypeMethod;
    private final Class queryPurchaseHistoryParamsClazz;
    private final Method queryPurchaseHistoryParamsNewBuilderMethod;
    private final Method queryPurchasesAsyncMethod;
    private final Method queryPurchasesParamsBuilderBuildMethod;
    private final Class queryPurchasesParamsBuilderClazz;
    private final Method queryPurchasesParamsBuilderSetProductTypeMethod;
    private final Class queryPurchasesParamsClazz;
    private final Method queryPurchasesParamsNewBuilderMethod;

    public /* synthetic */ InAppPurchaseBillingClientWrapperV5V7(Object obj, Class cls, Class cls2, Class cls3, Class cls4, Class cls5, Class cls6, Class cls7, Class cls8, Class cls9, Class cls10, Class cls11, Class cls12, Class cls13, Class cls14, Class cls15, Class cls16, Class cls17, Method method, Method method2, Method method3, Method method4, Method method5, Method method6, Method method7, Method method8, Method method9, Method method10, Method method11, Method method12, Method method13, Method method14, Method method15, Method method16, Method method17, Method method18, Method method19, Method method20, Method method21, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, cls, cls2, cls3, cls4, cls5, cls6, cls7, cls8, cls9, cls10, cls11, cls12, cls13, cls14, cls15, cls16, cls17, method, method2, method3, method4, method5, method6, method7, method8, method9, method10, method11, method12, method13, method14, method15, method16, method17, method18, method19, method20, method21);
    }

    private InAppPurchaseBillingClientWrapperV5V7(Object obj, Class cls, Class cls2, Class cls3, Class cls4, Class cls5, Class cls6, Class cls7, Class cls8, Class cls9, Class cls10, Class cls11, Class cls12, Class cls13, Class cls14, Class cls15, Class cls16, Class cls17, Method method, Method method2, Method method3, Method method4, Method method5, Method method6, Method method7, Method method8, Method method9, Method method10, Method method11, Method method12, Method method13, Method method14, Method method15, Method method16, Method method17, Method method18, Method method19, Method method20, Method method21) {
        this.billingClient = obj;
        this.billingClientClazz = cls;
        this.purchaseClazz = cls2;
        this.productDetailsClazz = cls3;
        this.purchaseHistoryRecordClazz = cls4;
        this.queryProductDetailsParamsProductClazz = cls5;
        this.billingResultClazz = cls6;
        this.queryProductDetailsParamsClazz = cls7;
        this.queryPurchaseHistoryParamsClazz = cls8;
        this.queryPurchasesParamsClazz = cls9;
        this.queryProductDetailsParamsBuilderClazz = cls10;
        this.queryPurchaseHistoryParamsBuilderClazz = cls11;
        this.queryPurchasesParamsBuilderClazz = cls12;
        this.queryProductDetailsParamsProductBuilderClazz = cls13;
        this.billingClientStateListenerClazz = cls14;
        this.productDetailsResponseListenerClazz = cls15;
        this.purchasesResponseListenerClazz = cls16;
        this.purchaseHistoryResponseListenerClazz = cls17;
        this.queryPurchasesAsyncMethod = method;
        this.queryPurchasesParamsNewBuilderMethod = method2;
        this.queryPurchasesParamsBuilderBuildMethod = method3;
        this.queryPurchasesParamsBuilderSetProductTypeMethod = method4;
        this.purchaseGetOriginalJsonMethod = method5;
        this.queryPurchaseHistoryAsyncMethod = method6;
        this.queryPurchaseHistoryParamsNewBuilderMethod = method7;
        this.queryPurchaseHistoryParamsBuilderBuildMethod = method8;
        this.queryPurchaseHistoryParamsBuilderSetProductTypeMethod = method9;
        this.purchaseHistoryRecordGetOriginalJsonMethod = method10;
        this.queryProductDetailsAsyncMethod = method11;
        this.queryProductDetailsParamsNewBuilderMethod = method12;
        this.queryProductDetailsParamsBuilderBuildMethod = method13;
        this.queryProductDetailsParamsBuilderSetProductListMethod = method14;
        this.queryProductDetailsParamsProductNewBuilderMethod = method15;
        this.queryProductDetailsParamsProductBuilderBuildMethod = method16;
        this.queryProductDetailsParamsProductBuilderSetProductIdMethod = method17;
        this.queryProductDetailsParamsProductBuilderSetProductTypeMethod = method18;
        this.productDetailsToStringMethod = method19;
        this.billingClientStartConnectionMethod = method20;
        this.billingResultGetResponseCodeMethod = method21;
    }

    public static final /* synthetic */ Map access$getIapPurchaseDetailsMap$cp() {
        Class<InAppPurchaseBillingClientWrapperV5V7> cls = InAppPurchaseBillingClientWrapperV5V7.class;
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

    public static final /* synthetic */ InAppPurchaseBillingClientWrapperV5V7 access$getInstance$cp() {
        Class<InAppPurchaseBillingClientWrapperV5V7> cls = InAppPurchaseBillingClientWrapperV5V7.class;
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

    public static final /* synthetic */ Map access$getProductDetailsMap$cp() {
        Class<InAppPurchaseBillingClientWrapperV5V7> cls = InAppPurchaseBillingClientWrapperV5V7.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return productDetailsMap;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final /* synthetic */ Map access$getSubsPurchaseDetailsMap$cp() {
        Class<InAppPurchaseBillingClientWrapperV5V7> cls = InAppPurchaseBillingClientWrapperV5V7.class;
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
        Class<InAppPurchaseBillingClientWrapperV5V7> cls = InAppPurchaseBillingClientWrapperV5V7.class;
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

    public static final /* synthetic */ void access$onBillingServiceDisconnected(InAppPurchaseBillingClientWrapperV5V7 inAppPurchaseBillingClientWrapperV5V7, Object[] objArr, Object[] objArr2) {
        Class<InAppPurchaseBillingClientWrapperV5V7> cls = InAppPurchaseBillingClientWrapperV5V7.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                inAppPurchaseBillingClientWrapperV5V7.onBillingServiceDisconnected(objArr, objArr2);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final /* synthetic */ void access$onBillingSetupFinished(InAppPurchaseBillingClientWrapperV5V7 inAppPurchaseBillingClientWrapperV5V7, Object[] objArr, Object[] objArr2) {
        Class<InAppPurchaseBillingClientWrapperV5V7> cls = InAppPurchaseBillingClientWrapperV5V7.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                inAppPurchaseBillingClientWrapperV5V7.onBillingSetupFinished(objArr, objArr2);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final /* synthetic */ void access$onProductDetailsResponse(InAppPurchaseBillingClientWrapperV5V7 inAppPurchaseBillingClientWrapperV5V7, Object[] objArr, Object[] objArr2) {
        Class<InAppPurchaseBillingClientWrapperV5V7> cls = InAppPurchaseBillingClientWrapperV5V7.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                inAppPurchaseBillingClientWrapperV5V7.onProductDetailsResponse(objArr, objArr2);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final /* synthetic */ void access$onPurchaseHistoryResponse(InAppPurchaseBillingClientWrapperV5V7 inAppPurchaseBillingClientWrapperV5V7, Object[] objArr, Object[] objArr2) {
        Class<InAppPurchaseBillingClientWrapperV5V7> cls = InAppPurchaseBillingClientWrapperV5V7.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                inAppPurchaseBillingClientWrapperV5V7.onPurchaseHistoryResponse(objArr, objArr2);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final /* synthetic */ void access$onQueryPurchasesResponse(InAppPurchaseBillingClientWrapperV5V7 inAppPurchaseBillingClientWrapperV5V7, Object[] objArr, Object[] objArr2) {
        Class<InAppPurchaseBillingClientWrapperV5V7> cls = InAppPurchaseBillingClientWrapperV5V7.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                inAppPurchaseBillingClientWrapperV5V7.onQueryPurchasesResponse(objArr, objArr2);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final /* synthetic */ void access$setInstance$cp(InAppPurchaseBillingClientWrapperV5V7 inAppPurchaseBillingClientWrapperV5V7) {
        Class<InAppPurchaseBillingClientWrapperV5V7> cls = InAppPurchaseBillingClientWrapperV5V7.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                instance = inAppPurchaseBillingClientWrapperV5V7;
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

    public final class ListenerWrapper implements InvocationHandler {
        private Object[] wrapperArgs;

        public ListenerWrapper(Object[] objArr) {
            this.wrapperArgs = objArr;
        }

        public Object invoke(Object obj, Method method, Object[] objArr) {
            Intrinsics.checkNotNullParameter(obj, "proxy");
            Intrinsics.checkNotNullParameter(method, "m");
            String name = method.getName();
            if (name == null) {
                return null;
            }
            switch (name.hashCode()) {
                case -1642587947:
                    if (!name.equals("onPurchaseHistoryResponse")) {
                        return null;
                    }
                    InAppPurchaseBillingClientWrapperV5V7.access$onPurchaseHistoryResponse(InAppPurchaseBillingClientWrapperV5V7.this, this.wrapperArgs, objArr);
                    return null;
                case -1599362358:
                    if (!name.equals("onQueryPurchasesResponse")) {
                        return null;
                    }
                    InAppPurchaseBillingClientWrapperV5V7.access$onQueryPurchasesResponse(InAppPurchaseBillingClientWrapperV5V7.this, this.wrapperArgs, objArr);
                    return null;
                case -79406125:
                    if (!name.equals("onBillingSetupFinished")) {
                        return null;
                    }
                    InAppPurchaseBillingClientWrapperV5V7.access$onBillingSetupFinished(InAppPurchaseBillingClientWrapperV5V7.this, this.wrapperArgs, objArr);
                    return null;
                case 1227540564:
                    if (!name.equals("onBillingServiceDisconnected")) {
                        return null;
                    }
                    InAppPurchaseBillingClientWrapperV5V7.access$onBillingServiceDisconnected(InAppPurchaseBillingClientWrapperV5V7.this, this.wrapperArgs, objArr);
                    return null;
                case 1940131955:
                    if (!name.equals("onProductDetailsResponse")) {
                        return null;
                    }
                    InAppPurchaseBillingClientWrapperV5V7.access$onProductDetailsResponse(InAppPurchaseBillingClientWrapperV5V7.this, this.wrapperArgs, objArr);
                    return null;
                default:
                    return null;
            }
        }
    }

    private final Object getQueryPurchaseHistoryParams(InAppPurchaseUtils.IAPProductType iAPProductType) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            return InAppPurchaseUtils.invokeMethod(this.queryPurchaseHistoryParamsBuilderClazz, this.queryPurchaseHistoryParamsBuilderBuildMethod, InAppPurchaseUtils.invokeMethod(this.queryPurchaseHistoryParamsBuilderClazz, this.queryPurchaseHistoryParamsBuilderSetProductTypeMethod, InAppPurchaseUtils.invokeMethod(this.queryPurchaseHistoryParamsClazz, this.queryPurchaseHistoryParamsNewBuilderMethod, (Object) null, new Object[0]), iAPProductType.getType()), new Object[0]);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    private final Object getQueryProductDetailsParams(InAppPurchaseUtils.IAPProductType iAPProductType, List list) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            if (list.isEmpty()) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Object invokeMethod = InAppPurchaseUtils.invokeMethod(this.queryProductDetailsParamsProductBuilderClazz, this.queryProductDetailsParamsProductBuilderBuildMethod, InAppPurchaseUtils.invokeMethod(this.queryProductDetailsParamsProductBuilderClazz, this.queryProductDetailsParamsProductBuilderSetProductTypeMethod, InAppPurchaseUtils.invokeMethod(this.queryProductDetailsParamsProductBuilderClazz, this.queryProductDetailsParamsProductBuilderSetProductIdMethod, InAppPurchaseUtils.invokeMethod(this.queryProductDetailsParamsProductClazz, this.queryProductDetailsParamsProductNewBuilderMethod, (Object) null, new Object[0]), (String) it.next()), iAPProductType.getType()), new Object[0]);
                if (invokeMethod != null) {
                    arrayList.add(invokeMethod);
                }
            }
            return InAppPurchaseUtils.invokeMethod(this.queryProductDetailsParamsBuilderClazz, this.queryProductDetailsParamsBuilderBuildMethod, InAppPurchaseUtils.invokeMethod(this.queryProductDetailsParamsBuilderClazz, this.queryProductDetailsParamsBuilderSetProductListMethod, InAppPurchaseUtils.invokeMethod(this.queryProductDetailsParamsClazz, this.queryProductDetailsParamsNewBuilderMethod, (Object) null, new Object[0]), arrayList), new Object[0]);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    public void queryPurchaseHistory(InAppPurchaseUtils.IAPProductType iAPProductType, Runnable runnable) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                Intrinsics.checkNotNullParameter(iAPProductType, "productType");
                Intrinsics.checkNotNullParameter(runnable, "completionHandler");
                executeServiceRequest(new InAppPurchaseBillingClientWrapperV5V7$$ExternalSyntheticLambda0(this, iAPProductType, runnable));
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void queryPurchaseHistory$lambda$1(InAppPurchaseBillingClientWrapperV5V7 inAppPurchaseBillingClientWrapperV5V7, InAppPurchaseUtils.IAPProductType iAPProductType, Runnable runnable) {
        Class<InAppPurchaseBillingClientWrapperV5V7> cls = InAppPurchaseBillingClientWrapperV5V7.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(inAppPurchaseBillingClientWrapperV5V7, "this$0");
                Intrinsics.checkNotNullParameter(iAPProductType, "$productType");
                Intrinsics.checkNotNullParameter(runnable, "$completionHandler");
                InAppPurchaseUtils.invokeMethod(inAppPurchaseBillingClientWrapperV5V7.billingClientClazz, inAppPurchaseBillingClientWrapperV5V7.queryPurchaseHistoryAsyncMethod, inAppPurchaseBillingClientWrapperV5V7.getBillingClient(), inAppPurchaseBillingClientWrapperV5V7.getQueryPurchaseHistoryParams(iAPProductType), Proxy.newProxyInstance(inAppPurchaseBillingClientWrapperV5V7.purchaseHistoryResponseListenerClazz.getClassLoader(), new Class[]{inAppPurchaseBillingClientWrapperV5V7.purchaseHistoryResponseListenerClazz}, new ListenerWrapper(new Object[]{iAPProductType, runnable})));
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    private final void queryProductDetailsAsync(InAppPurchaseUtils.IAPProductType iAPProductType, List list, Runnable runnable) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                executeServiceRequest(new InAppPurchaseBillingClientWrapperV5V7$$ExternalSyntheticLambda1(this, runnable, iAPProductType, list));
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void queryProductDetailsAsync$lambda$2(InAppPurchaseBillingClientWrapperV5V7 inAppPurchaseBillingClientWrapperV5V7, Runnable runnable, InAppPurchaseUtils.IAPProductType iAPProductType, List list) {
        Class<InAppPurchaseBillingClientWrapperV5V7> cls = InAppPurchaseBillingClientWrapperV5V7.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(inAppPurchaseBillingClientWrapperV5V7, "this$0");
                Intrinsics.checkNotNullParameter(runnable, "$completionHandler");
                Intrinsics.checkNotNullParameter(iAPProductType, "$productType");
                Intrinsics.checkNotNullParameter(list, "$productIds");
                Object newProxyInstance = Proxy.newProxyInstance(inAppPurchaseBillingClientWrapperV5V7.productDetailsResponseListenerClazz.getClassLoader(), new Class[]{inAppPurchaseBillingClientWrapperV5V7.productDetailsResponseListenerClazz}, new ListenerWrapper(new Object[]{runnable}));
                Object queryProductDetailsParams = inAppPurchaseBillingClientWrapperV5V7.getQueryProductDetailsParams(iAPProductType, list);
                if (queryProductDetailsParams != null) {
                    InAppPurchaseUtils.invokeMethod(inAppPurchaseBillingClientWrapperV5V7.billingClientClazz, inAppPurchaseBillingClientWrapperV5V7.queryProductDetailsAsyncMethod, inAppPurchaseBillingClientWrapperV5V7.getBillingClient(), queryProductDetailsParams, newProxyInstance);
                }
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
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                InAppPurchaseUtils.invokeMethod(this.billingClientClazz, this.billingClientStartConnectionMethod, getBillingClient(), Proxy.newProxyInstance(this.billingClientStateListenerClazz.getClassLoader(), new Class[]{this.billingClientStateListenerClazz}, new ListenerWrapper(new Object[]{runnable})));
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public final String getOriginalJson(String str) {
        List groupValues;
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(str, "productDetailsString");
            MatchResult find$default = Regex.find$default(new Regex("jsonString='(.*?)'"), str, 0, 2, (Object) null);
            if (find$default == null || (groupValues = find$default.getGroupValues()) == null) {
                return null;
            }
            return (String) CollectionsKt.getOrNull(groupValues, 1);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    private final void onQueryPurchasesResponse(Object[] objArr, Object[] objArr2) {
        Object obj;
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            if (objArr != null) {
                try {
                    obj = ArraysKt.getOrNull(objArr, 0);
                } catch (Throwable th) {
                    CrashShieldHandler.handleThrowable(th, this);
                    return;
                }
            } else {
                obj = null;
            }
            if (obj == null) {
                return;
            }
            if (obj instanceof InAppPurchaseUtils.IAPProductType) {
                Object orNull = ArraysKt.getOrNull(objArr, 1);
                if (orNull instanceof Runnable) {
                    Object orNull2 = objArr2 != null ? ArraysKt.getOrNull(objArr2, 1) : null;
                    if (orNull2 == null) {
                        return;
                    }
                    if (orNull2 instanceof List) {
                        ArrayList arrayList = new ArrayList();
                        for (Object invokeMethod : (List) orNull2) {
                            Object invokeMethod2 = InAppPurchaseUtils.invokeMethod(this.purchaseClazz, this.purchaseGetOriginalJsonMethod, invokeMethod, new Object[0]);
                            String str = invokeMethod2 instanceof String ? (String) invokeMethod2 : null;
                            if (str != null) {
                                JSONObject jSONObject = new JSONObject(str);
                                if (jSONObject.has("productId")) {
                                    String string = jSONObject.getString("productId");
                                    if (!productDetailsMap.containsKey(string)) {
                                        Intrinsics.checkNotNullExpressionValue(string, "productId");
                                        arrayList.add(string);
                                    }
                                    if (obj == InAppPurchaseUtils.IAPProductType.INAPP) {
                                        Map map = iapPurchaseDetailsMap;
                                        Intrinsics.checkNotNullExpressionValue(string, "productId");
                                        map.put(string, jSONObject);
                                    } else {
                                        Map map2 = subsPurchaseDetailsMap;
                                        Intrinsics.checkNotNullExpressionValue(string, "productId");
                                        map2.put(string, jSONObject);
                                    }
                                }
                            }
                        }
                        if (!arrayList.isEmpty()) {
                            queryProductDetailsAsync((InAppPurchaseUtils.IAPProductType) obj, arrayList, (Runnable) orNull);
                        } else {
                            ((Runnable) orNull).run();
                        }
                    }
                }
            }
        }
    }

    private final void onPurchaseHistoryResponse(Object[] objArr, Object[] objArr2) {
        Object obj;
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            if (objArr != null) {
                try {
                    obj = ArraysKt.getOrNull(objArr, 0);
                } catch (Throwable th) {
                    CrashShieldHandler.handleThrowable(th, this);
                    return;
                }
            } else {
                obj = null;
            }
            if (obj == null) {
                return;
            }
            if (obj instanceof InAppPurchaseUtils.IAPProductType) {
                Object orNull = ArraysKt.getOrNull(objArr, 1);
                if (orNull instanceof Runnable) {
                    Object orNull2 = objArr2 != null ? ArraysKt.getOrNull(objArr2, 1) : null;
                    if (orNull2 == null) {
                        return;
                    }
                    if (orNull2 instanceof List) {
                        ArrayList arrayList = new ArrayList();
                        for (Object invokeMethod : (List) orNull2) {
                            try {
                                Object invokeMethod2 = InAppPurchaseUtils.invokeMethod(this.purchaseHistoryRecordClazz, this.purchaseHistoryRecordGetOriginalJsonMethod, invokeMethod, new Object[0]);
                                String str = invokeMethod2 instanceof String ? (String) invokeMethod2 : null;
                                if (str != null) {
                                    JSONObject jSONObject = new JSONObject(str);
                                    if (jSONObject.has("productId")) {
                                        String string = jSONObject.getString("productId");
                                        if (!productDetailsMap.containsKey(string)) {
                                            Intrinsics.checkNotNullExpressionValue(string, "productId");
                                            arrayList.add(string);
                                        }
                                        if (obj == InAppPurchaseUtils.IAPProductType.INAPP) {
                                            Map map = iapPurchaseDetailsMap;
                                            Intrinsics.checkNotNullExpressionValue(string, "productId");
                                            map.put(string, jSONObject);
                                        } else {
                                            Map map2 = subsPurchaseDetailsMap;
                                            Intrinsics.checkNotNullExpressionValue(string, "productId");
                                            map2.put(string, jSONObject);
                                        }
                                    }
                                }
                            } catch (Exception unused) {
                            }
                        }
                        if (!arrayList.isEmpty()) {
                            queryProductDetailsAsync((InAppPurchaseUtils.IAPProductType) obj, arrayList, (Runnable) orNull);
                        } else {
                            ((Runnable) orNull).run();
                        }
                    }
                }
            }
        }
    }

    private final void onProductDetailsResponse(Object[] objArr, Object[] objArr2) {
        Object obj;
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            if (objArr != null) {
                try {
                    obj = ArraysKt.getOrNull(objArr, 0);
                } catch (Throwable th) {
                    CrashShieldHandler.handleThrowable(th, this);
                    return;
                }
            } else {
                obj = null;
            }
            Object orNull = objArr2 != null ? ArraysKt.getOrNull(objArr2, 1) : null;
            if (orNull == null) {
                return;
            }
            if (orNull instanceof List) {
                for (Object invokeMethod : (List) orNull) {
                    try {
                        Object invokeMethod2 = InAppPurchaseUtils.invokeMethod(this.productDetailsClazz, this.productDetailsToStringMethod, invokeMethod, new Object[0]);
                        String str = invokeMethod2 instanceof String ? (String) invokeMethod2 : null;
                        if (str != null) {
                            String originalJson = getOriginalJson(str);
                            if (originalJson != null) {
                                JSONObject jSONObject = new JSONObject(originalJson);
                                if (jSONObject.has("productId")) {
                                    String string = jSONObject.getString("productId");
                                    Map map = productDetailsMap;
                                    Intrinsics.checkNotNullExpressionValue(string, "productId");
                                    map.put(string, jSONObject);
                                }
                            }
                        }
                    } catch (Exception unused) {
                    }
                }
                if (obj == null) {
                    return;
                }
                if (obj instanceof Runnable) {
                    ((Runnable) obj).run();
                }
            }
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: CodeShrinkVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Don't wrap MOVE or CONST insns: 0x0036: MOVE  (r5v3 java.lang.Runnable) = (r5v2 java.lang.Runnable)
        	at jadx.core.dex.instructions.args.InsnArg.wrapArg(InsnArg.java:164)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.assignInline(CodeShrinkVisitor.java:133)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.checkInline(CodeShrinkVisitor.java:118)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkBlock(CodeShrinkVisitor.java:65)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkMethod(CodeShrinkVisitor.java:43)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.visit(CodeShrinkVisitor.java:35)
        */
    private final void onBillingSetupFinished(java.lang.Object[] r5, java.lang.Object[] r6) {
        /*
            r4 = this;
            boolean r0 = com.facebook.internal.instrument.crashshield.CrashShieldHandler.isObjectCrashing(r4)
            if (r0 == 0) goto L_0x0007
            return
        L_0x0007:
            if (r6 == 0) goto L_0x0044
            int r0 = r6.length     // Catch:{ all -> 0x003e }
            if (r0 != 0) goto L_0x000d
            goto L_0x0044
        L_0x000d:
            r0 = 0
            r6 = r6[r0]     // Catch:{ all -> 0x003e }
            java.lang.Class r1 = r4.billingResultClazz     // Catch:{ all -> 0x003e }
            java.lang.reflect.Method r2 = r4.billingResultGetResponseCodeMethod     // Catch:{ all -> 0x003e }
            java.lang.Object[] r3 = new java.lang.Object[r0]     // Catch:{ all -> 0x003e }
            java.lang.Object r6 = com.facebook.appevents.iap.InAppPurchaseUtils.invokeMethod(r1, r2, r6, r3)     // Catch:{ all -> 0x003e }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x003e }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r1)     // Catch:{ all -> 0x003e }
            if (r6 == 0) goto L_0x0040
            java.util.concurrent.atomic.AtomicBoolean r6 = isServiceConnected     // Catch:{ all -> 0x003e }
            r1 = 1
            r6.set(r1)     // Catch:{ all -> 0x003e }
            if (r5 == 0) goto L_0x0040
            int r6 = r5.length     // Catch:{ all -> 0x003e }
            if (r6 != 0) goto L_0x0030
            goto L_0x0040
        L_0x0030:
            r5 = r5[r0]     // Catch:{ all -> 0x003e }
            boolean r6 = r5 instanceof java.lang.Runnable     // Catch:{ all -> 0x003e }
            if (r6 == 0) goto L_0x0040
            java.lang.Runnable r5 = (java.lang.Runnable) r5     // Catch:{ all -> 0x003e }
            if (r5 == 0) goto L_0x0040
            r5.run()     // Catch:{ all -> 0x003e }
            goto L_0x0040
        L_0x003e:
            r5 = move-exception
            goto L_0x0041
        L_0x0040:
            return
        L_0x0041:
            com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r5, r4)
        L_0x0044:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.iap.InAppPurchaseBillingClientWrapperV5V7.onBillingSetupFinished(java.lang.Object[], java.lang.Object[]):void");
    }

    private final void onBillingServiceDisconnected(Object[] objArr, Object[] objArr2) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                isServiceConnected.set(false);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public static final class Companion implements InvocationHandler {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public Object invoke(Object obj, Method method, Object[] objArr) {
            Intrinsics.checkNotNullParameter(obj, "proxy");
            Intrinsics.checkNotNullParameter(method, "m");
            return null;
        }

        private Companion() {
        }

        public final Map getIapPurchaseDetailsMap() {
            return InAppPurchaseBillingClientWrapperV5V7.access$getIapPurchaseDetailsMap$cp();
        }

        public final Map getSubsPurchaseDetailsMap() {
            return InAppPurchaseBillingClientWrapperV5V7.access$getSubsPurchaseDetailsMap$cp();
        }

        public final Map getProductDetailsMap() {
            return InAppPurchaseBillingClientWrapperV5V7.access$getProductDetailsMap$cp();
        }

        public final synchronized InAppPurchaseBillingClientWrapperV5V7 getOrCreateInstance(Context context) {
            InAppPurchaseBillingClientWrapperV5V7 access$getInstance$cp;
            Intrinsics.checkNotNullParameter(context, "context");
            access$getInstance$cp = InAppPurchaseBillingClientWrapperV5V7.access$getInstance$cp();
            if (access$getInstance$cp == null) {
                access$getInstance$cp = createInstance(context);
            }
            return access$getInstance$cp;
        }

        private final InAppPurchaseBillingClientWrapperV5V7 createInstance(Context context) {
            Class cls = InAppPurchaseUtils.getClass("com.android.billingclient.api.BillingClient");
            Class cls2 = InAppPurchaseUtils.getClass("com.android.billingclient.api.Purchase");
            Class cls3 = InAppPurchaseUtils.getClass("com.android.billingclient.api.ProductDetails");
            Class cls4 = InAppPurchaseUtils.getClass("com.android.billingclient.api.PurchaseHistoryRecord");
            Class cls5 = InAppPurchaseUtils.getClass("com.android.billingclient.api.QueryProductDetailsParams$Product");
            Class cls6 = InAppPurchaseUtils.getClass("com.android.billingclient.api.BillingResult");
            Class cls7 = InAppPurchaseUtils.getClass("com.android.billingclient.api.QueryProductDetailsParams");
            Class cls8 = InAppPurchaseUtils.getClass("com.android.billingclient.api.QueryPurchaseHistoryParams");
            Class cls9 = InAppPurchaseUtils.getClass("com.android.billingclient.api.QueryPurchasesParams");
            Class cls10 = InAppPurchaseUtils.getClass("com.android.billingclient.api.QueryProductDetailsParams$Builder");
            Class cls11 = InAppPurchaseUtils.getClass("com.android.billingclient.api.QueryPurchaseHistoryParams$Builder");
            Class cls12 = InAppPurchaseUtils.getClass("com.android.billingclient.api.QueryPurchasesParams$Builder");
            Class cls13 = InAppPurchaseUtils.getClass("com.android.billingclient.api.QueryProductDetailsParams$Product$Builder");
            Class cls14 = InAppPurchaseUtils.getClass("com.android.billingclient.api.BillingClient$Builder");
            Class cls15 = InAppPurchaseUtils.getClass("com.android.billingclient.api.PurchasesUpdatedListener");
            Class cls16 = InAppPurchaseUtils.getClass("com.android.billingclient.api.BillingClientStateListener");
            Class cls17 = InAppPurchaseUtils.getClass("com.android.billingclient.api.ProductDetailsResponseListener");
            Class cls18 = InAppPurchaseUtils.getClass("com.android.billingclient.api.PurchasesResponseListener");
            Class cls19 = InAppPurchaseUtils.getClass("com.android.billingclient.api.PurchaseHistoryResponseListener");
            if (cls == null || cls2 == null || cls3 == null || cls4 == null || cls5 == null || cls6 == null || cls7 == null || cls8 == null || cls9 == null || cls10 == null || cls11 == null || cls12 == null || cls13 == null || cls14 == null || cls15 == null || cls16 == null || cls17 == null || cls18 == null || cls19 == null) {
                Log.w(InAppPurchaseBillingClientWrapperV5V7.access$getTAG$cp(), "Failed to create Google Play billing library wrapper for in-app purchase auto-logging");
                return null;
            }
            String str = "Failed to create Google Play billing library wrapper for in-app purchase auto-logging";
            Class cls20 = cls14;
            Class cls21 = cls18;
            Class cls22 = cls15;
            Method method = InAppPurchaseUtils.getMethod(cls, "queryPurchasesAsync", cls9, cls21);
            Class cls23 = cls21;
            Method method2 = InAppPurchaseUtils.getMethod(cls9, "newBuilder", new Class[0]);
            Method method3 = InAppPurchaseUtils.getMethod(cls12, "build", new Class[0]);
            Class<String> cls24 = String.class;
            Class cls25 = cls9;
            Class cls26 = cls6;
            Method method4 = InAppPurchaseUtils.getMethod(cls12, "setProductType", cls24);
            Class cls27 = cls12;
            Method method5 = InAppPurchaseUtils.getMethod(cls2, "getOriginalJson", new Class[0]);
            Class cls28 = cls2;
            Class cls29 = cls19;
            Method method6 = InAppPurchaseUtils.getMethod(cls, "queryPurchaseHistoryAsync", cls8, cls29);
            Method method7 = InAppPurchaseUtils.getMethod(cls8, "newBuilder", new Class[0]);
            Method method8 = InAppPurchaseUtils.getMethod(cls11, "build", new Class[0]);
            Method method9 = InAppPurchaseUtils.getMethod(cls11, "setProductType", cls24);
            Method method10 = InAppPurchaseUtils.getMethod(cls4, "getOriginalJson", new Class[0]);
            Class cls30 = cls17;
            Class cls31 = cls29;
            Method method11 = InAppPurchaseUtils.getMethod(cls, "queryProductDetailsAsync", cls7, cls30);
            Method method12 = InAppPurchaseUtils.getMethod(cls7, "newBuilder", new Class[0]);
            Method method13 = InAppPurchaseUtils.getMethod(cls10, "build", new Class[0]);
            Method method14 = InAppPurchaseUtils.getMethod(cls10, "setProductList", List.class);
            Method method15 = InAppPurchaseUtils.getMethod(cls5, "newBuilder", new Class[0]);
            Method method16 = InAppPurchaseUtils.getMethod(cls13, "build", new Class[0]);
            Method method17 = InAppPurchaseUtils.getMethod(cls13, "setProductId", cls24);
            Method method18 = InAppPurchaseUtils.getMethod(cls13, "setProductType", cls24);
            Class cls32 = cls3;
            Method method19 = InAppPurchaseUtils.getMethod(cls32, "toString", new Class[0]);
            Method method20 = InAppPurchaseUtils.getMethod(cls, "startConnection", cls16);
            Class cls33 = cls26;
            Method method21 = InAppPurchaseUtils.getMethod(cls33, "getResponseCode", new Class[0]);
            if (method == null || method2 == null || method3 == null || method4 == null || method5 == null || method6 == null || method7 == null || method8 == null || method9 == null || method10 == null || method11 == null || method12 == null || method13 == null || method14 == null || method15 == null || method16 == null || method17 == null || method18 == null || method19 == null || method20 == null || method21 == null) {
                Log.w(InAppPurchaseBillingClientWrapperV5V7.access$getTAG$cp(), str);
                return null;
            }
            Object createBillingClient = createBillingClient(context, cls, cls20, cls22);
            if (createBillingClient == null) {
                Log.w(InAppPurchaseBillingClientWrapperV5V7.access$getTAG$cp(), "Failed to build a Google Play billing library wrapper for in-app purchase auto-logging");
                return null;
            }
            Class cls34 = cls28;
            Class cls35 = cls32;
            Class cls36 = cls33;
            Class cls37 = cls25;
            Class cls38 = cls27;
            InAppPurchaseBillingClientWrapperV5V7.access$setInstance$cp(new InAppPurchaseBillingClientWrapperV5V7(createBillingClient, cls, cls34, cls35, cls4, cls5, cls36, cls7, cls8, cls37, cls10, cls11, cls38, cls13, cls16, cls30, cls23, cls31, method, method2, method3, method4, method5, method6, method7, method8, method9, method10, method11, method12, method13, method14, method15, method16, method17, method18, method19, method20, method21, (DefaultConstructorMarker) null));
            return InAppPurchaseBillingClientWrapperV5V7.access$getInstance$cp();
        }

        private final Object createBillingClient(Context context, Class cls, Class cls2, Class cls3) {
            Object invokeMethod;
            Method method = InAppPurchaseUtils.getMethod(cls, "newBuilder", Context.class);
            Method method2 = InAppPurchaseUtils.getMethod(cls2, "setListener", cls3);
            Method method3 = InAppPurchaseUtils.getMethod(cls2, "enablePendingPurchases", new Class[0]);
            Method method4 = InAppPurchaseUtils.getMethod(cls2, "build", new Class[0]);
            if (method4 == null || method2 == null || method == null || method3 == null || (invokeMethod = InAppPurchaseUtils.invokeMethod(cls2, method2, InAppPurchaseUtils.invokeMethod(cls, method, (Object) null, context), Proxy.newProxyInstance(cls3.getClassLoader(), new Class[]{cls3}, this))) == null) {
                return null;
            }
            return InAppPurchaseUtils.invokeMethod(cls2, method4, InAppPurchaseUtils.invokeMethod(cls2, method3, invokeMethod, new Object[0]), new Object[0]);
        }
    }
}
