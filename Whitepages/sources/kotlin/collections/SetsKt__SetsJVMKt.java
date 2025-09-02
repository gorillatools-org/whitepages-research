package kotlin.collections;

import java.util.Collections;
import java.util.Set;
import kotlin.collections.builders.SetBuilder;
import kotlin.jvm.internal.Intrinsics;

abstract class SetsKt__SetsJVMKt {
    public static Set setOf(Object obj) {
        Set singleton = Collections.singleton(obj);
        Intrinsics.checkNotNullExpressionValue(singleton, "singleton(...)");
        return singleton;
    }

    public static Set createSetBuilder() {
        return new SetBuilder();
    }

    public static Set createSetBuilder(int i) {
        return new SetBuilder(i);
    }

    public static Set build(Set set) {
        Intrinsics.checkNotNullParameter(set, "builder");
        return ((SetBuilder) set).build();
    }
}
