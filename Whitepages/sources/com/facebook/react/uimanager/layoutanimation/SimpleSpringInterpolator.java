package com.facebook.react.uimanager.layoutanimation;

import android.view.animation.Interpolator;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import io.branch.rnbranch.RNBranchModule;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class SimpleSpringInterpolator implements Interpolator {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final float FACTOR = 0.5f;
    public static final String PARAM_SPRING_DAMPING = "springDamping";
    private final float _springDamping;

    public SimpleSpringInterpolator() {
        this(0.0f, 1, (DefaultConstructorMarker) null);
    }

    public static final float getSpringDamping(ReadableMap readableMap) {
        return Companion.getSpringDamping(readableMap);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SimpleSpringInterpolator(float f, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? FACTOR : f);
    }

    public SimpleSpringInterpolator(float f) {
        this._springDamping = f;
    }

    public float getInterpolation(float f) {
        double pow = Math.pow(2.0d, (double) (((float) -10) * f));
        float f2 = this._springDamping;
        return (float) (((double) 1) + (pow * Math.sin(((((double) (f - (f2 / ((float) 4)))) * 3.141592653589793d) * ((double) 2)) / ((double) f2))));
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final float getSpringDamping(ReadableMap readableMap) {
            Intrinsics.checkNotNullParameter(readableMap, RNBranchModule.NATIVE_INIT_SESSION_FINISHED_EVENT_PARAMS);
            return readableMap.getType(SimpleSpringInterpolator.PARAM_SPRING_DAMPING) == ReadableType.Number ? (float) readableMap.getDouble(SimpleSpringInterpolator.PARAM_SPRING_DAMPING) : SimpleSpringInterpolator.FACTOR;
        }
    }
}
