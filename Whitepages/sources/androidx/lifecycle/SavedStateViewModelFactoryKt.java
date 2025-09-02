package androidx.lifecycle;

import android.app.Application;
import io.branch.rnbranch.RNBranchModule;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

public abstract class SavedStateViewModelFactoryKt {
    /* access modifiers changed from: private */
    public static final List ANDROID_VIEWMODEL_SIGNATURE;
    /* access modifiers changed from: private */
    public static final List VIEWMODEL_SIGNATURE;

    public static final ViewModel newInstance(Class cls, Constructor constructor, Object... objArr) {
        Intrinsics.checkNotNullParameter(cls, "modelClass");
        Intrinsics.checkNotNullParameter(constructor, "constructor");
        Intrinsics.checkNotNullParameter(objArr, RNBranchModule.NATIVE_INIT_SESSION_FINISHED_EVENT_PARAMS);
        try {
            return (ViewModel) constructor.newInstance(Arrays.copyOf(objArr, objArr.length));
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to access " + cls, e);
        } catch (InstantiationException e2) {
            throw new RuntimeException("A " + cls + " cannot be instantiated.", e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException("An exception happened in constructor of " + cls, e3.getCause());
        }
    }

    static {
        Class<SavedStateHandle> cls = SavedStateHandle.class;
        ANDROID_VIEWMODEL_SIGNATURE = CollectionsKt.listOf(Application.class, cls);
        VIEWMODEL_SIGNATURE = CollectionsKt.listOf(cls);
    }

    public static final Constructor findMatchingConstructor(Class cls, List list) {
        Intrinsics.checkNotNullParameter(cls, "modelClass");
        Intrinsics.checkNotNullParameter(list, "signature");
        Constructor[] constructors = cls.getConstructors();
        Intrinsics.checkNotNullExpressionValue(constructors, "modelClass.constructors");
        int length = constructors.length;
        int i = 0;
        while (i < length) {
            Constructor constructor = constructors[i];
            Class[] parameterTypes = constructor.getParameterTypes();
            Intrinsics.checkNotNullExpressionValue(parameterTypes, "constructor.parameterTypes");
            List list2 = ArraysKt.toList(parameterTypes);
            if (Intrinsics.areEqual((Object) list, (Object) list2)) {
                Intrinsics.checkNotNull(constructor, "null cannot be cast to non-null type java.lang.reflect.Constructor<T of androidx.lifecycle.SavedStateViewModelFactoryKt.findMatchingConstructor>");
                return constructor;
            } else if (list.size() != list2.size() || !list2.containsAll(list)) {
                i++;
            } else {
                throw new UnsupportedOperationException("Class " + cls.getSimpleName() + " must have parameters in the proper order: " + list);
            }
        }
        return null;
    }
}
