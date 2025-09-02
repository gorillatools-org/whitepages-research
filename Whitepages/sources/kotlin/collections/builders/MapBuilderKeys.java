package kotlin.collections.builders;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.collections.AbstractMutableSet;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableCollection;

public final class MapBuilderKeys extends AbstractMutableSet implements Set, KMutableCollection {
    private final MapBuilder backing;

    public MapBuilderKeys(MapBuilder mapBuilder) {
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
        return this.backing.containsKey(obj);
    }

    public void clear() {
        this.backing.clear();
    }

    public boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection collection) {
        Intrinsics.checkNotNullParameter(collection, "elements");
        throw new UnsupportedOperationException();
    }

    public boolean remove(Object obj) {
        return this.backing.removeKey$kotlin_stdlib(obj);
    }

    public Iterator iterator() {
        return this.backing.keysIterator$kotlin_stdlib();
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
