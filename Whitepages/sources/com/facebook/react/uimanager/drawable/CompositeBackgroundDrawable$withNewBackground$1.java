package com.facebook.react.uimanager.drawable;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.reflect.KMutableProperty0;

/* synthetic */ class CompositeBackgroundDrawable$withNewBackground$1 extends FunctionReferenceImpl implements Function1 {
    CompositeBackgroundDrawable$withNewBackground$1(Object obj) {
        super(1, obj, KMutableProperty0.class, "set", "set(Ljava/lang/Object;)V", 0);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((BackgroundDrawable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(BackgroundDrawable backgroundDrawable) {
        ((KMutableProperty0) this.receiver).set(backgroundDrawable);
    }
}
