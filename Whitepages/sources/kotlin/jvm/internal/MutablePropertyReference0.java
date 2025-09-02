package kotlin.jvm.internal;

import kotlin.reflect.KCallable;
import kotlin.reflect.KMutableProperty0;
import kotlin.reflect.KProperty0;

public abstract class MutablePropertyReference0 extends MutablePropertyReference implements KMutableProperty0 {
    public MutablePropertyReference0(Object obj, Class cls, String str, String str2, int i) {
        super(obj, cls, str, str2, i);
    }

    /* access modifiers changed from: protected */
    public KCallable computeReflected() {
        return Reflection.mutableProperty0(this);
    }

    public Object invoke() {
        return get();
    }

    public KProperty0.Getter getGetter() {
        ((KMutableProperty0) getReflected()).getGetter();
        return null;
    }

    public KMutableProperty0.Setter getSetter() {
        ((KMutableProperty0) getReflected()).getSetter();
        return null;
    }

    public Object getDelegate() {
        return ((KMutableProperty0) getReflected()).getDelegate();
    }
}
