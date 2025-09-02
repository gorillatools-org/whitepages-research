package kotlin.collections.builders;

import java.util.Map;
import kotlin.collections.AbstractMutableSet;
import kotlin.jvm.internal.Intrinsics;

public abstract class AbstractMapBuilderEntrySet extends AbstractMutableSet {
    public abstract boolean containsEntry(Map.Entry entry);

    public abstract /* bridge */ boolean remove(Map.Entry entry);

    public final /* bridge */ boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        return contains((Map.Entry) obj);
    }

    public final /* bridge */ boolean remove(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        return remove((Map.Entry) obj);
    }

    public final boolean contains(Map.Entry entry) {
        Intrinsics.checkNotNullParameter(entry, "element");
        return containsEntry(entry);
    }
}
