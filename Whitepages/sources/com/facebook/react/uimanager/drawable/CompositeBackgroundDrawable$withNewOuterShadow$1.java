package com.facebook.react.uimanager.drawable;

import android.graphics.drawable.LayerDrawable;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.reflect.KMutableProperty0;

/* synthetic */ class CompositeBackgroundDrawable$withNewOuterShadow$1 extends FunctionReferenceImpl implements Function1 {
    CompositeBackgroundDrawable$withNewOuterShadow$1(Object obj) {
        super(1, obj, KMutableProperty0.class, "set", "set(Ljava/lang/Object;)V", 0);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((LayerDrawable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(LayerDrawable layerDrawable) {
        ((KMutableProperty0) this.receiver).set(layerDrawable);
    }
}
