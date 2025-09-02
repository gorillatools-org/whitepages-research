package kotlin.properties;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;

public abstract class ObservableProperty implements ReadWriteProperty {
    private Object value;

    /* access modifiers changed from: protected */
    public abstract void afterChange(KProperty kProperty, Object obj, Object obj2);

    /* access modifiers changed from: protected */
    public boolean beforeChange(KProperty kProperty, Object obj, Object obj2) {
        Intrinsics.checkNotNullParameter(kProperty, "property");
        return true;
    }

    public ObservableProperty(Object obj) {
        this.value = obj;
    }

    public Object getValue(Object obj, KProperty kProperty) {
        Intrinsics.checkNotNullParameter(kProperty, "property");
        return this.value;
    }

    public void setValue(Object obj, KProperty kProperty, Object obj2) {
        Intrinsics.checkNotNullParameter(kProperty, "property");
        Object obj3 = this.value;
        if (beforeChange(kProperty, obj3, obj2)) {
            this.value = obj2;
            afterChange(kProperty, obj3, obj2);
        }
    }

    public String toString() {
        return "ObservableProperty(value=" + this.value + ')';
    }
}
