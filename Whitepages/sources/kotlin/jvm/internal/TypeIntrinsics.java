package kotlin.jvm.internal;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Function;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.jvm.internal.markers.KMutableCollection;
import kotlin.jvm.internal.markers.KMutableIterable;
import kotlin.jvm.internal.markers.KMutableList;
import kotlin.jvm.internal.markers.KMutableMap;

public abstract class TypeIntrinsics {
    private static Throwable sanitizeStackTrace(Throwable th) {
        return Intrinsics.sanitizeStackTrace(th, TypeIntrinsics.class.getName());
    }

    public static void throwCce(Object obj, String str) {
        String name = obj == null ? "null" : obj.getClass().getName();
        throwCce(name + " cannot be cast to " + str);
    }

    public static void throwCce(String str) {
        throw throwCce(new ClassCastException(str));
    }

    public static ClassCastException throwCce(ClassCastException classCastException) {
        throw ((ClassCastException) sanitizeStackTrace(classCastException));
    }

    public static Iterable asMutableIterable(Object obj) {
        if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableIterable)) {
            throwCce(obj, "kotlin.collections.MutableIterable");
        }
        return castToIterable(obj);
    }

    public static Iterable castToIterable(Object obj) {
        try {
            return (Iterable) obj;
        } catch (ClassCastException e) {
            throw throwCce(e);
        }
    }

    public static Collection asMutableCollection(Object obj) {
        if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableCollection)) {
            throwCce(obj, "kotlin.collections.MutableCollection");
        }
        return castToCollection(obj);
    }

    public static Collection castToCollection(Object obj) {
        try {
            return (Collection) obj;
        } catch (ClassCastException e) {
            throw throwCce(e);
        }
    }

    public static List asMutableList(Object obj) {
        if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableList)) {
            throwCce(obj, "kotlin.collections.MutableList");
        }
        return castToList(obj);
    }

    public static List castToList(Object obj) {
        try {
            return (List) obj;
        } catch (ClassCastException e) {
            throw throwCce(e);
        }
    }

    public static Map asMutableMap(Object obj) {
        if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableMap)) {
            throwCce(obj, "kotlin.collections.MutableMap");
        }
        return castToMap(obj);
    }

    public static Map castToMap(Object obj) {
        try {
            return (Map) obj;
        } catch (ClassCastException e) {
            throw throwCce(e);
        }
    }

    public static int getFunctionArity(Object obj) {
        if (obj instanceof FunctionBase) {
            return ((FunctionBase) obj).getArity();
        }
        if (obj instanceof Function0) {
            return 0;
        }
        if (obj instanceof Function1) {
            return 1;
        }
        if (obj instanceof Function2) {
            return 2;
        }
        if (obj instanceof Function3) {
            return 3;
        }
        return obj instanceof Function4 ? 4 : -1;
    }

    public static boolean isFunctionOfArity(Object obj, int i) {
        return (obj instanceof Function) && getFunctionArity(obj) == i;
    }

    public static Object beforeCheckcastToFunctionOfArity(Object obj, int i) {
        if (obj != null && !isFunctionOfArity(obj, i)) {
            throwCce(obj, "kotlin.jvm.functions.Function" + i);
        }
        return obj;
    }
}
