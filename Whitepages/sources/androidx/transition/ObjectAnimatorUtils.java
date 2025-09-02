package androidx.transition;

import android.animation.ObjectAnimator;
import android.animation.TypeConverter;
import android.graphics.Path;
import android.util.Property;

abstract class ObjectAnimatorUtils {
    static ObjectAnimator ofPointF(Object obj, Property property, Path path) {
        return ObjectAnimator.ofObject(obj, property, (TypeConverter) null, path);
    }
}
