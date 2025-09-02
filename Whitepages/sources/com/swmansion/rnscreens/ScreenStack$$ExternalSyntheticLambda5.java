package com.swmansion.rnscreens;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref$ObjectRef;

public final /* synthetic */ class ScreenStack$$ExternalSyntheticLambda5 implements Function1 {
    public final /* synthetic */ Ref$ObjectRef f$0;
    public final /* synthetic */ ScreenStack f$1;

    public /* synthetic */ ScreenStack$$ExternalSyntheticLambda5(Ref$ObjectRef ref$ObjectRef, ScreenStack screenStack) {
        this.f$0 = ref$ObjectRef;
        this.f$1 = screenStack;
    }

    public final Object invoke(Object obj) {
        return Boolean.valueOf(ScreenStack.onUpdate$lambda$15$lambda$9(this.f$0, this.f$1, (ScreenFragmentWrapper) obj));
    }
}
