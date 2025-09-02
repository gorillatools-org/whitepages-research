package kotlin.collections;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

abstract class ArraysKt__ArraysKt extends ArraysKt__ArraysJVMKt {
    public static boolean contentDeepEquals(Object[] objArr, Object[] objArr2) {
        if (objArr == objArr2) {
            return true;
        }
        if (objArr == null || objArr2 == null || objArr.length != objArr2.length) {
            return false;
        }
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            Object[] objArr3 = objArr[i];
            Object[] objArr4 = objArr2[i];
            if (objArr3 != objArr4) {
                if (objArr3 == null || objArr4 == null) {
                    return false;
                }
                if (!(objArr3 instanceof Object[]) || !(objArr4 instanceof Object[])) {
                    if (!(objArr3 instanceof byte[]) || !(objArr4 instanceof byte[])) {
                        if (!(objArr3 instanceof short[]) || !(objArr4 instanceof short[])) {
                            if (!(objArr3 instanceof int[]) || !(objArr4 instanceof int[])) {
                                if (!(objArr3 instanceof long[]) || !(objArr4 instanceof long[])) {
                                    if (!(objArr3 instanceof float[]) || !(objArr4 instanceof float[])) {
                                        if (!(objArr3 instanceof double[]) || !(objArr4 instanceof double[])) {
                                            if (!(objArr3 instanceof char[]) || !(objArr4 instanceof char[])) {
                                                if (!(objArr3 instanceof boolean[]) || !(objArr4 instanceof boolean[])) {
                                                    if (!Intrinsics.areEqual((Object) objArr3, (Object) objArr4)) {
                                                        return false;
                                                    }
                                                } else if (!Arrays.equals((boolean[]) objArr3, (boolean[]) objArr4)) {
                                                    return false;
                                                }
                                            } else if (!Arrays.equals((char[]) objArr3, (char[]) objArr4)) {
                                                return false;
                                            }
                                        } else if (!Arrays.equals((double[]) objArr3, (double[]) objArr4)) {
                                            return false;
                                        }
                                    } else if (!Arrays.equals((float[]) objArr3, (float[]) objArr4)) {
                                        return false;
                                    }
                                } else if (!Arrays.equals((long[]) objArr3, (long[]) objArr4)) {
                                    return false;
                                }
                            } else if (!Arrays.equals((int[]) objArr3, (int[]) objArr4)) {
                                return false;
                            }
                        } else if (!Arrays.equals((short[]) objArr3, (short[]) objArr4)) {
                            return false;
                        }
                    } else if (!Arrays.equals((byte[]) objArr3, (byte[]) objArr4)) {
                        return false;
                    }
                } else if (!ArraysKt.contentDeepEquals(objArr3, objArr4)) {
                    return false;
                }
            }
        }
        return true;
    }
}
