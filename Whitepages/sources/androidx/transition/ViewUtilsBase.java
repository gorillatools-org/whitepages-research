package androidx.transition;

import android.graphics.Matrix;
import android.util.Log;
import android.view.View;
import java.lang.reflect.Field;

abstract class ViewUtilsBase {
    private static Field sViewFlagsField;
    private static boolean sViewFlagsFieldFetched;

    public abstract void clearNonTransitionAlpha(View view);

    public abstract float getTransitionAlpha(View view);

    public abstract void saveNonTransitionAlpha(View view);

    public abstract void setLeftTopRightBottom(View view, int i, int i2, int i3, int i4);

    public abstract void setTransitionAlpha(View view, float f);

    public abstract void transformMatrixToGlobal(View view, Matrix matrix);

    public abstract void transformMatrixToLocal(View view, Matrix matrix);

    ViewUtilsBase() {
    }

    public void setTransitionVisibility(View view, int i) {
        if (!sViewFlagsFieldFetched) {
            try {
                Field declaredField = View.class.getDeclaredField("mViewFlags");
                sViewFlagsField = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
                Log.i("ViewUtilsBase", "fetchViewFlagsField: ");
            }
            sViewFlagsFieldFetched = true;
        }
        Field field = sViewFlagsField;
        if (field != null) {
            try {
                sViewFlagsField.setInt(view, i | (field.getInt(view) & -13));
            } catch (IllegalAccessException unused2) {
            }
        }
    }
}
