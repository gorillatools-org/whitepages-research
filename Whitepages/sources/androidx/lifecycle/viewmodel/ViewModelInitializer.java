package androidx.lifecycle.viewmodel;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public final class ViewModelInitializer {
    private final Class clazz;
    private final Function1 initializer;

    public ViewModelInitializer(Class cls, Function1 function1) {
        Intrinsics.checkNotNullParameter(cls, "clazz");
        Intrinsics.checkNotNullParameter(function1, "initializer");
        this.clazz = cls;
        this.initializer = function1;
    }

    public final Class getClazz$lifecycle_viewmodel_release() {
        return this.clazz;
    }

    public final Function1 getInitializer$lifecycle_viewmodel_release() {
        return this.initializer;
    }
}
