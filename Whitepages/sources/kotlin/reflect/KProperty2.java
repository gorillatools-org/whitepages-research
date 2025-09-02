package kotlin.reflect;

import kotlin.jvm.functions.Function2;
import kotlin.reflect.KProperty;

public interface KProperty2 extends KProperty, Function2 {

    public interface Getter extends KProperty.Getter, Function2 {
    }

    Object get(Object obj, Object obj2);

    Getter getGetter();
}
