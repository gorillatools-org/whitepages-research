package kotlin.collections;

import java.util.AbstractSet;
import java.util.Set;
import kotlin.jvm.internal.markers.KMutableCollection;

public abstract class AbstractMutableSet extends AbstractSet implements Set, KMutableCollection {
    public abstract int getSize();

    public final /* bridge */ int size() {
        return getSize();
    }

    protected AbstractMutableSet() {
    }
}
