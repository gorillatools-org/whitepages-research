package kotlin.properties;

import kotlin.reflect.KProperty;

public interface ReadWriteProperty extends ReadOnlyProperty {
    Object getValue(Object obj, KProperty kProperty);

    void setValue(Object obj, KProperty kProperty, Object obj2);
}
