package androidx.core.util;

public abstract class TypedValueCompat {
    public static int getUnitFromComplexDimension(int i) {
        return i & 15;
    }
}
