package kotlin.collections;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

abstract class ArraysKt___ArraysJvmKt extends ArraysKt__ArraysKt {
    public static List asList(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        List asList = ArraysUtilJVM.asList(objArr);
        Intrinsics.checkNotNullExpressionValue(asList, "asList(...)");
        return asList;
    }

    public static /* synthetic */ Object[] copyInto$default(Object[] objArr, Object[] objArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = objArr.length;
        }
        return ArraysKt.copyInto(objArr, objArr2, i, i2, i3);
    }

    public static Object[] copyInto(Object[] objArr, Object[] objArr2, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        Intrinsics.checkNotNullParameter(objArr2, FirebaseAnalytics.Param.DESTINATION);
        System.arraycopy(objArr, i2, objArr2, i, i3 - i2);
        return objArr2;
    }

    public static /* synthetic */ byte[] copyInto$default(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = bArr.length;
        }
        return ArraysKt.copyInto(bArr, bArr2, i, i2, i3);
    }

    public static byte[] copyInto(byte[] bArr, byte[] bArr2, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(bArr2, FirebaseAnalytics.Param.DESTINATION);
        System.arraycopy(bArr, i2, bArr2, i, i3 - i2);
        return bArr2;
    }

    public static /* synthetic */ int[] copyInto$default(int[] iArr, int[] iArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = iArr.length;
        }
        return ArraysKt.copyInto(iArr, iArr2, i, i2, i3);
    }

    public static int[] copyInto(int[] iArr, int[] iArr2, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(iArr2, FirebaseAnalytics.Param.DESTINATION);
        System.arraycopy(iArr, i2, iArr2, i, i3 - i2);
        return iArr2;
    }

    public static long[] copyInto(long[] jArr, long[] jArr2, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        Intrinsics.checkNotNullParameter(jArr2, FirebaseAnalytics.Param.DESTINATION);
        System.arraycopy(jArr, i2, jArr2, i, i3 - i2);
        return jArr2;
    }

    public static Object[] copyOfRange(Object[] objArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        ArraysKt__ArraysJVMKt.copyOfRangeToIndexCheck(i2, objArr.length);
        Object[] copyOfRange = Arrays.copyOfRange(objArr, i, i2);
        Intrinsics.checkNotNullExpressionValue(copyOfRange, "copyOfRange(...)");
        return copyOfRange;
    }

    public static byte[] copyOfRange(byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        ArraysKt__ArraysJVMKt.copyOfRangeToIndexCheck(i2, bArr.length);
        byte[] copyOfRange = Arrays.copyOfRange(bArr, i, i2);
        Intrinsics.checkNotNullExpressionValue(copyOfRange, "copyOfRange(...)");
        return copyOfRange;
    }

    public static /* synthetic */ void fill$default(Object[] objArr, Object obj, int i, int i2, int i3, Object obj2) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = objArr.length;
        }
        ArraysKt.fill(objArr, obj, i, i2);
    }

    public static void fill(Object[] objArr, Object obj, int i, int i2) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        Arrays.fill(objArr, i, i2, obj);
    }

    public static /* synthetic */ void fill$default(int[] iArr, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = iArr.length;
        }
        fill(iArr, i, i2, i3);
    }

    public static final void fill(int[] iArr, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Arrays.fill(iArr, i2, i3, i);
    }

    public static /* synthetic */ void fill$default(float[] fArr, float f, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = fArr.length;
        }
        fill(fArr, f, i, i2);
    }

    public static final void fill(float[] fArr, float f, int i, int i2) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        Arrays.fill(fArr, i, i2, f);
    }

    public static void sort(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        if (objArr.length > 1) {
            Arrays.sort(objArr);
        }
    }

    public static final void sortWith(Object[] objArr, Comparator comparator) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (objArr.length > 1) {
            Arrays.sort(objArr, comparator);
        }
    }
}
