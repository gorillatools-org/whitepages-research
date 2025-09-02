package kotlin.collections;

import java.util.AbstractCollection;
import java.util.Collection;
import kotlin.jvm.internal.markers.KMutableCollection;

public abstract class AbstractMutableCollection extends AbstractCollection implements Collection, KMutableCollection {
    public abstract int getSize();

    public final /* bridge */ int size() {
        return getSize();
    }

    protected AbstractMutableCollection() {
    }
}
