package kotlin.reflect;

import kotlin.jvm.functions.Function1;

public interface KMutableProperty0 extends KProperty0, KProperty {

    public interface Setter extends KMutableProperty$Setter, Function1 {
    }

    Setter getSetter();

    void set(Object obj);
}
