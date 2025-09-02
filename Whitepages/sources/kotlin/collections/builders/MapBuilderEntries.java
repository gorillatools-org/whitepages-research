package kotlin.collections.builders;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

public final class MapBuilderEntries extends AbstractMapBuilderEntrySet {
    private final MapBuilder backing;

    public MapBuilderEntries(MapBuilder mapBuilder) {
        Intrinsics.checkNotNullParameter(mapBuilder, "backing");
        this.backing = mapBuilder;
    }

    public int getSize() {
        return this.backing.size();
    }

    public boolean isEmpty() {
        return this.backing.isEmpty();
    }

    public boolean containsEntry(Map.Entry entry) {
        Intrinsics.checkNotNullParameter(entry, "element");
        return this.backing.containsEntry$kotlin_stdlib(entry);
    }

    public void clear() {
        this.backing.clear();
    }

    public boolean add(Map.Entry entry) {
        Intrinsics.checkNotNullParameter(entry, "element");
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection collection) {
        Intrinsics.checkNotNullParameter(collection, "elements");
        throw new UnsupportedOperationException();
    }

    public boolean remove(Map.Entry entry) {
        Intrinsics.checkNotNullParameter(entry, "element");
        return this.backing.removeEntry$kotlin_stdlib(entry);
    }

    public Iterator iterator() {
        return this.backing.entriesIterator$kotlin_stdlib();
    }

    public boolean containsAll(Collection collection) {
        Intrinsics.checkNotNullParameter(collection, "elements");
        return this.backing.containsAllEntries$kotlin_stdlib(collection);
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
