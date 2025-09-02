package androidx.constraintlayout.core.motion.utils;

import com.facebook.hermes.intl.Constants;
import com.facebook.react.animated.InterpolationAnimatedNode;

public class Easing {
    public static String[] NAMED_EASING = {Constants.COLLATION_STANDARD, "accelerate", "decelerate", "linear"};
    static Easing sDefault = new Easing();
    String str = InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY;

    public String toString() {
        return this.str;
    }
}
