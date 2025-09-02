package kotlin.jvm.internal;

public class MutablePropertyReference1Impl extends MutablePropertyReference1 {
    public MutablePropertyReference1Impl(Class cls, String str, String str2, int i) {
        super(CallableReference.NO_RECEIVER, cls, str, str2, i);
    }

    public Object get(Object obj) {
        getGetter();
        throw null;
    }
}
