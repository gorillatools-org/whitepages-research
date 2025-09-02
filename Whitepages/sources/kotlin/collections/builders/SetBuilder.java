package kotlin.collections.builders;

import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.collections.AbstractMutableSet;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableCollection;

public final class SetBuilder extends AbstractMutableSet implements Set, Serializable, KMutableCollection {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final SetBuilder Empty = new SetBuilder(MapBuilder.Companion.getEmpty$kotlin_stdlib());
    private final MapBuilder backing;

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public SetBuilder(MapBuilder mapBuilder) {
        Intrinsics.checkNotNullParameter(mapBuilder, "backing");
        this.backing = mapBuilder;
    }

    public SetBuilder() {
        this(new MapBuilder());
    }

    public SetBuilder(int i) {
        this(new MapBuilder(i));
    }

    public final Set build() {
        this.backing.build();
        return size() > 0 ? this : Empty;
    }

    private final Object writeReplace() {
        if (this.backing.isReadOnly$kotlin_stdlib()) {
            return new SerializedCollection(this, 1);
        }
        throw new NotSerializableException("The set cannot be serialized while it is being built.");
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
        return this.backing.addKey$kotlin_stdlib(obj) >= 0;
    }

    public boolean remove(Object obj) {
        return this.backing.removeKey$kotlin_stdlib(obj);
    }

    public Iterator iterator() {
        return this.backing.keysIterator$kotlin_stdlib();
    }

    public boolean addAll(Collection collection) {
        Intrinsics.checkNotNullParameter(collection, "elements");
        this.backing.checkIsMutable$kotlin_stdlib();
        return super.addAll(collection);
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
