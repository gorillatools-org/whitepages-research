package androidx.lifecycle;

import android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class Lifecycling {
    public static final Lifecycling INSTANCE = new Lifecycling();
    private static final Map callbackCache = new HashMap();
    private static final Map classToAdapters = new HashMap();

    private Lifecycling() {
    }

    public static final LifecycleEventObserver lifecycleEventObserver(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "object");
        boolean z = obj instanceof LifecycleEventObserver;
        boolean z2 = obj instanceof DefaultLifecycleObserver;
        if (z && z2) {
            return new DefaultLifecycleObserverAdapter((DefaultLifecycleObserver) obj, (LifecycleEventObserver) obj);
        }
        if (z2) {
            return new DefaultLifecycleObserverAdapter((DefaultLifecycleObserver) obj, (LifecycleEventObserver) null);
        }
        if (z) {
            return (LifecycleEventObserver) obj;
        }
        Class<?> cls = obj.getClass();
        Lifecycling lifecycling = INSTANCE;
        if (lifecycling.getObserverConstructorType(cls) != 2) {
            return new ReflectiveGenericLifecycleObserver(obj);
        }
        Object obj2 = classToAdapters.get(cls);
        Intrinsics.checkNotNull(obj2);
        List list = (List) obj2;
        if (list.size() == 1) {
            lifecycling.createGeneratedAdapter((Constructor) list.get(0), obj);
            return new SingleGeneratedAdapterObserver((GeneratedAdapter) null);
        }
        int size = list.size();
        GeneratedAdapter[] generatedAdapterArr = new GeneratedAdapter[size];
        for (int i = 0; i < size; i++) {
            INSTANCE.createGeneratedAdapter((Constructor) list.get(i), obj);
            generatedAdapterArr[i] = null;
        }
        return new CompositeGeneratedAdaptersObserver(generatedAdapterArr);
    }

    private final GeneratedAdapter createGeneratedAdapter(Constructor constructor, Object obj) {
        try {
            Object newInstance = constructor.newInstance(new Object[]{obj});
            Intrinsics.checkNotNullExpressionValue(newInstance, "{\n            constructo…tance(`object`)\n        }");
            MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(newInstance);
            return null;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }

    private final Constructor generatedConstructor(Class cls) {
        try {
            Package packageR = cls.getPackage();
            String canonicalName = cls.getCanonicalName();
            String name = packageR != null ? packageR.getName() : "";
            Intrinsics.checkNotNullExpressionValue(name, "fullPackage");
            if (name.length() != 0) {
                Intrinsics.checkNotNullExpressionValue(canonicalName, "name");
                canonicalName = canonicalName.substring(name.length() + 1);
                Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String).substring(startIndex)");
            }
            Intrinsics.checkNotNullExpressionValue(canonicalName, "if (fullPackage.isEmpty(…g(fullPackage.length + 1)");
            String adapterName = getAdapterName(canonicalName);
            if (name.length() != 0) {
                adapterName = name + '.' + adapterName;
            }
            Class<?> cls2 = Class.forName(adapterName);
            Intrinsics.checkNotNull(cls2, "null cannot be cast to non-null type java.lang.Class<out androidx.lifecycle.GeneratedAdapter>");
            Constructor<?> declaredConstructor = cls2.getDeclaredConstructor(new Class[]{cls});
            if (declaredConstructor.isAccessible()) {
                return declaredConstructor;
            }
            declaredConstructor.setAccessible(true);
            return declaredConstructor;
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private final int getObserverConstructorType(Class cls) {
        Map map = callbackCache;
        Integer num = (Integer) map.get(cls);
        if (num != null) {
            return num.intValue();
        }
        int resolveObserverCallbackType = resolveObserverCallbackType(cls);
        map.put(cls, Integer.valueOf(resolveObserverCallbackType));
        return resolveObserverCallbackType;
    }

    private final int resolveObserverCallbackType(Class cls) {
        ArrayList arrayList;
        if (cls.getCanonicalName() == null) {
            return 1;
        }
        Constructor generatedConstructor = generatedConstructor(cls);
        if (generatedConstructor != null) {
            classToAdapters.put(cls, CollectionsKt.listOf(generatedConstructor));
            return 2;
        } else if (ClassesInfoCache.sInstance.hasLifecycleMethods(cls)) {
            return 1;
        } else {
            Class superclass = cls.getSuperclass();
            if (isLifecycleParent(superclass)) {
                Intrinsics.checkNotNullExpressionValue(superclass, "superclass");
                if (getObserverConstructorType(superclass) == 1) {
                    return 1;
                }
                Object obj = classToAdapters.get(superclass);
                Intrinsics.checkNotNull(obj);
                arrayList = new ArrayList((Collection) obj);
            } else {
                arrayList = null;
            }
            Class[] interfaces = cls.getInterfaces();
            Intrinsics.checkNotNullExpressionValue(interfaces, "klass.interfaces");
            for (Class cls2 : interfaces) {
                if (isLifecycleParent(cls2)) {
                    Intrinsics.checkNotNullExpressionValue(cls2, "intrface");
                    if (getObserverConstructorType(cls2) == 1) {
                        return 1;
                    }
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    Object obj2 = classToAdapters.get(cls2);
                    Intrinsics.checkNotNull(obj2);
                    arrayList.addAll((Collection) obj2);
                }
            }
            if (arrayList == null) {
                return 1;
            }
            classToAdapters.put(cls, arrayList);
            return 2;
        }
    }

    private final boolean isLifecycleParent(Class cls) {
        return cls != null && LifecycleObserver.class.isAssignableFrom(cls);
    }

    public static final String getAdapterName(String str) {
        Intrinsics.checkNotNullParameter(str, "className");
        return StringsKt.replace$default(str, ".", "_", false, 4, (Object) null) + "_LifecycleAdapter";
    }
}
