package androidx.constraintlayout.widget;

import android.util.SparseIntArray;
import java.util.HashMap;

public class SharedValues {
    private SparseIntArray mValues = new SparseIntArray();
    private HashMap mValuesListeners = new HashMap();
}
