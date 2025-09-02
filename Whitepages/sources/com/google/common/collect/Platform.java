package com.google.common.collect;

import java.lang.reflect.Array;
import java.util.Arrays;

abstract class Platform {
    static Object[] newArray(Object[] objArr, int i) {
        return (Object[]) Array.newInstance(objArr.getClass().getComponentType(), i);
    }

    static Object[] copy(Object[] objArr, int i, int i2, Object[] objArr2) {
        return Arrays.copyOfRange(objArr, i, i2, objArr2.getClass());
    }
}
