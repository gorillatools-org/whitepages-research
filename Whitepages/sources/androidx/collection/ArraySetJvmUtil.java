package androidx.collection;

import java.lang.reflect.Array;

abstract class ArraySetJvmUtil {
    static Object[] resizeForToArray(Object[] objArr, int i) {
        if (objArr.length < i) {
            return (Object[]) Array.newInstance(objArr.getClass().getComponentType(), i);
        }
        if (objArr.length > i) {
            objArr[i] = null;
        }
        return objArr;
    }
}
