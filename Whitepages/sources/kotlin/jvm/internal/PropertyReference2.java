package kotlin.jvm.internal;

import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty2;

public abstract class PropertyReference2 extends PropertyReference implements KProperty2 {
    public PropertyReference2(Class cls, String str, String str2, int i) {
        super(CallableReference.NO_RECEIVER, cls, str, str2, i);
    }

    /* access modifiers changed from: protected */
    public KCallable computeReflected() {
        return Reflection.property2(this);
    }

    public Object invoke(Object obj, Object obj2) {
        return get(obj, obj2);
    }

    public KProperty2.Getter getGetter() {
        ((KProperty2) getReflected()).getGetter();
        return null;
    }
}
