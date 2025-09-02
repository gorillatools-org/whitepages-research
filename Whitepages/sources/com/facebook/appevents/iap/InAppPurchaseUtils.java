package com.facebook.appevents.iap;

import android.content.Context;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

public final class InAppPurchaseUtils {
    public static final InAppPurchaseUtils INSTANCE = new InAppPurchaseUtils();

    private InAppPurchaseUtils() {
    }

    public static final Class getClass(String str) {
        Class<InAppPurchaseUtils> cls = InAppPurchaseUtils.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(str, "className");
            try {
                return Class.forName(str);
            } catch (ClassNotFoundException unused) {
                return null;
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final Method getMethod(Class cls, String str, Class... clsArr) {
        Class<InAppPurchaseUtils> cls2 = InAppPurchaseUtils.class;
        if (CrashShieldHandler.isObjectCrashing(cls2)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(cls, "clazz");
            Intrinsics.checkNotNullParameter(str, "methodName");
            Intrinsics.checkNotNullParameter(clsArr, "args");
            try {
                return cls.getMethod(str, (Class[]) Arrays.copyOf(clsArr, clsArr.length));
            } catch (NoSuchMethodException unused) {
                return null;
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls2);
            return null;
        }
    }

    public static final Method getDeclaredMethod$facebook_core_release(Class cls, String str, Class... clsArr) {
        Class<InAppPurchaseUtils> cls2 = InAppPurchaseUtils.class;
        if (CrashShieldHandler.isObjectCrashing(cls2)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(cls, "clazz");
            Intrinsics.checkNotNullParameter(str, "methodName");
            Intrinsics.checkNotNullParameter(clsArr, "args");
            try {
                return cls.getDeclaredMethod(str, (Class[]) Arrays.copyOf(clsArr, clsArr.length));
            } catch (NoSuchMethodException unused) {
                return null;
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls2);
            return null;
        }
    }

    public static final Object invokeMethod(Class cls, Method method, Object obj, Object... objArr) {
        Class<InAppPurchaseUtils> cls2 = InAppPurchaseUtils.class;
        if (CrashShieldHandler.isObjectCrashing(cls2)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(cls, "clazz");
            Intrinsics.checkNotNullParameter(method, FirebaseAnalytics.Param.METHOD);
            Intrinsics.checkNotNullParameter(objArr, "args");
            if (obj != null) {
                obj = cls.cast(obj);
            }
            try {
                return method.invoke(obj, Arrays.copyOf(objArr, objArr.length));
            } catch (IllegalAccessException | InvocationTargetException unused) {
                return null;
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls2);
            return null;
        }
    }

    public static final Class getClassFromContext$facebook_core_release(Context context, String str) {
        Class<InAppPurchaseUtils> cls = InAppPurchaseUtils.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(str, "className");
            try {
                return context.getClassLoader().loadClass(str);
            } catch (ClassNotFoundException unused) {
                return null;
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public enum BillingClientVersion {
        NONE("none"),
        V1("Android-GPBL-V1"),
        V2_V4("Android-GPBL-V2-V4"),
        V5_V7("Android-GPBL-V5-V7");
        
        private final String type;

        private BillingClientVersion(String str) {
            this.type = str;
        }

        public final String getType() {
            return this.type;
        }
    }

    public enum IAPProductType {
        INAPP("inapp"),
        SUBS("subs");
        
        private final String type;

        private IAPProductType(String str) {
            this.type = str;
        }

        public final String getType() {
            return this.type;
        }
    }
}
