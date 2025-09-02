package com.facebook.appevents.iap;

import com.facebook.appevents.iap.InAppPurchaseUtils;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.lang.reflect.Method;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class InAppPurchaseSkuDetailsWrapper {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static InAppPurchaseSkuDetailsWrapper instance;
    private final Method buildMethod;
    private final Class builderClazz;
    private final Method newBuilderMethod;
    private final Method setSkusListMethod;
    private final Method setTypeMethod;
    private final Class skuDetailsParamsClazz;

    public InAppPurchaseSkuDetailsWrapper(Class cls, Class cls2, Method method, Method method2, Method method3, Method method4) {
        Intrinsics.checkNotNullParameter(cls, "skuDetailsParamsClazz");
        Intrinsics.checkNotNullParameter(cls2, "builderClazz");
        Intrinsics.checkNotNullParameter(method, "newBuilderMethod");
        Intrinsics.checkNotNullParameter(method2, "setTypeMethod");
        Intrinsics.checkNotNullParameter(method3, "setSkusListMethod");
        Intrinsics.checkNotNullParameter(method4, "buildMethod");
        this.skuDetailsParamsClazz = cls;
        this.builderClazz = cls2;
        this.newBuilderMethod = method;
        this.setTypeMethod = method2;
        this.setSkusListMethod = method3;
        this.buildMethod = method4;
    }

    public static final /* synthetic */ InAppPurchaseSkuDetailsWrapper access$getInstance$cp() {
        Class<InAppPurchaseSkuDetailsWrapper> cls = InAppPurchaseSkuDetailsWrapper.class;
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

    public static final /* synthetic */ void access$setInstance$cp(InAppPurchaseSkuDetailsWrapper inAppPurchaseSkuDetailsWrapper) {
        Class<InAppPurchaseSkuDetailsWrapper> cls = InAppPurchaseSkuDetailsWrapper.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                instance = inAppPurchaseSkuDetailsWrapper;
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public final Class getSkuDetailsParamsClazz() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            return this.skuDetailsParamsClazz;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    public final Object getSkuDetailsParams(InAppPurchaseUtils.IAPProductType iAPProductType, List list) {
        Object invokeMethod;
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(iAPProductType, "productType");
            Object invokeMethod2 = InAppPurchaseUtils.invokeMethod(this.skuDetailsParamsClazz, this.newBuilderMethod, (Object) null, new Object[0]);
            if (invokeMethod2 == null || (invokeMethod = InAppPurchaseUtils.invokeMethod(this.builderClazz, this.setTypeMethod, invokeMethod2, iAPProductType.getType())) == null) {
                return null;
            }
            Object invokeMethod3 = InAppPurchaseUtils.invokeMethod(this.builderClazz, this.setSkusListMethod, invokeMethod, list);
            if (invokeMethod3 == null) {
                return null;
            }
            return InAppPurchaseUtils.invokeMethod(this.builderClazz, this.buildMethod, invokeMethod3, new Object[0]);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final synchronized InAppPurchaseSkuDetailsWrapper getOrCreateInstance() {
            InAppPurchaseSkuDetailsWrapper access$getInstance$cp;
            access$getInstance$cp = InAppPurchaseSkuDetailsWrapper.access$getInstance$cp();
            if (access$getInstance$cp == null) {
                access$getInstance$cp = createInstance();
            }
            return access$getInstance$cp;
        }

        private final InAppPurchaseSkuDetailsWrapper createInstance() {
            Class cls = InAppPurchaseUtils.getClass("com.android.billingclient.api.SkuDetailsParams");
            Class cls2 = InAppPurchaseUtils.getClass("com.android.billingclient.api.SkuDetailsParams$Builder");
            if (cls == null || cls2 == null) {
                return null;
            }
            Method method = InAppPurchaseUtils.getMethod(cls, "newBuilder", new Class[0]);
            Method method2 = InAppPurchaseUtils.getMethod(cls2, "setType", String.class);
            Method method3 = InAppPurchaseUtils.getMethod(cls2, "setSkusList", List.class);
            Method method4 = InAppPurchaseUtils.getMethod(cls2, "build", new Class[0]);
            if (method == null || method2 == null || method3 == null || method4 == null) {
                return null;
            }
            InAppPurchaseSkuDetailsWrapper.access$setInstance$cp(new InAppPurchaseSkuDetailsWrapper(cls, cls2, method, method2, method3, method4));
            return InAppPurchaseSkuDetailsWrapper.access$getInstance$cp();
        }
    }
}
