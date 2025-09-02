package kotlin.collections.builders;

import java.util.Collection;
import java.util.Iterator;
import kotlin.collections.AbstractMutableCollection;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableCollection;

public final class MapBuilderValues extends AbstractMutableCollection implements Collection, KMutableCollection {
    private final MapBuilder backing;

    public MapBuilderValues(MapBuilder mapBuilder) {
        Intrinsics.checkNotNullParameter(mapBuilder, "backing");
        this.backing = mapBuilder;
    }

    public int getSize() {
        return this.backing.size();
    }

    public boolean isEmpty() {
        return this.backing.isEmpty();
    }

    public boolean contains(Object obj) {
        return this.backing.containsValue(obj);
    }

    public boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection collection) {
        Intrinsics.checkNotNullParameter(collection, "elements");
        throw new UnsupportedOperationException();
    }

    public void clear() {
        this.backing.clear();
    }

    public Iterator iterator() {
        return this.backing.valuesIterator$kotlin_stdlib();
    }

    public boolean remove(Object obj) {
        return this.backing.removeValue$kotlin_stdlib(obj);
    }

    public boolean removeAll(Collection collection) {
        Intrinsics.checkNotNullParameter(collection, "elements");
        this.backing.checkIsMutable$kotlin_stdlib();
        return super.removeAll(collection);
    }

    public boolean retainAll(Collection collection) {
        Intrinsics.checkNotNullParameter(collection, "elements");
        this.backing.checkIsMutable$kotlin_stdlib();
        return super.retainAll(collection);
    }
}
