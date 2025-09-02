package kotlin.reflect;

import kotlin.jvm.functions.Function1;
import kotlin.reflect.KProperty;

public interface KProperty1 extends KProperty, Function1 {

    public interface Getter extends KProperty.Getter, Function1 {
    }

    Object get(Object obj);

    Object getDelegate(Object obj);

    Getter getGetter();
}
