package kotlin.reflect;

import kotlin.jvm.functions.Function0;
import kotlin.reflect.KProperty;

public interface KProperty0 extends KProperty, Function0 {

    public interface Getter extends KProperty.Getter, Function0 {
    }

    Object get();

    Object getDelegate();

    Getter getGetter();
}
