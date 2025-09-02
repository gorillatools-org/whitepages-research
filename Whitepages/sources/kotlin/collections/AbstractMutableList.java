package kotlin.collections;

import java.util.AbstractList;
import java.util.List;
import kotlin.jvm.internal.markers.KMutableList;

public abstract class AbstractMutableList extends AbstractList implements List, KMutableList {
    public abstract int getSize();

    public abstract Object removeAt(int i);

    public final /* bridge */ Object remove(int i) {
        return removeAt(i);
    }

    public final /* bridge */ int size() {
        return getSize();
    }

    protected AbstractMutableList() {
    }
}
