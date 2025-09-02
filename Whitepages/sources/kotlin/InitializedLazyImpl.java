package kotlin;

import java.io.Serializable;

public final class InitializedLazyImpl implements Lazy, Serializable {
    private final Object value;

    public boolean isInitialized() {
        return true;
    }

    public InitializedLazyImpl(Object obj) {
        this.value = obj;
    }

    public Object getValue() {
        return this.value;
    }

    public String toString() {
        return String.valueOf(getValue());
    }
}
