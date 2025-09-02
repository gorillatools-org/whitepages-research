package com.facebook.react.modules.core;

import java.util.Comparator;
import kotlin.jvm.functions.Function2;

public final /* synthetic */ class JavaTimerManager$$ExternalSyntheticLambda1 implements Comparator {
    public final /* synthetic */ Function2 f$0;

    public /* synthetic */ JavaTimerManager$$ExternalSyntheticLambda1(Function2 function2) {
        this.f$0 = function2;
    }

    public final int compare(Object obj, Object obj2) {
        return JavaTimerManager.timers$lambda$1(this.f$0, obj, obj2);
    }
}
