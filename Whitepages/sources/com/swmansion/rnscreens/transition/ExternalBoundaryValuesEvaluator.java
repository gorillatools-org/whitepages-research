package com.swmansion.rnscreens.transition;

import android.animation.FloatEvaluator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public final class ExternalBoundaryValuesEvaluator extends FloatEvaluator {
    private Number endValueCache;
    private final Function1 endValueProvider;
    private Number startValueCache;
    private final Function1 startValueProvider;

    public ExternalBoundaryValuesEvaluator(Function1 function1, Function1 function12) {
        Intrinsics.checkNotNullParameter(function1, "startValueProvider");
        Intrinsics.checkNotNullParameter(function12, "endValueProvider");
        this.startValueProvider = function1;
        this.endValueProvider = function12;
    }

    private final Number getStartValue(Number number) {
        if (this.startValueCache == null) {
            this.startValueCache = (Number) this.startValueProvider.invoke(number);
        }
        return this.startValueCache;
    }

    private final Number getEndValue(Number number) {
        if (this.endValueCache == null) {
            this.endValueCache = (Number) this.endValueProvider.invoke(number);
        }
        return this.endValueCache;
    }

    public Float evaluate(float f, Number number, Number number2) {
        Number startValue = getStartValue(number);
        Number endValue = getEndValue(number2);
        if (startValue == null || endValue == null) {
            return null;
        }
        return super.evaluate(f, startValue, endValue);
    }
}
