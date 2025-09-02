package androidx.datastore.core;

import kotlin.jvm.internal.DefaultConstructorMarker;

public final class Data extends State {
    private final int hashCode;
    private final Object value;

    public Data(Object obj, int i, int i2) {
        super(i2, (DefaultConstructorMarker) null);
        this.value = obj;
        this.hashCode = i;
    }

    public final Object getValue() {
        return this.value;
    }

    public final void checkHashCode() {
        Object obj = this.value;
        if ((obj != null ? obj.hashCode() : 0) != this.hashCode) {
            throw new IllegalStateException("Data in DataStore was mutated but DataStore is only compatible with Immutable types.");
        }
    }
}
