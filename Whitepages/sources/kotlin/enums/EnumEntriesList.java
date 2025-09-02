package kotlin.enums;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.Serializable;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

final class EnumEntriesList extends AbstractList implements EnumEntries, Serializable {
    private final Enum[] entries;

    public final /* bridge */ boolean contains(Object obj) {
        if (!(obj instanceof Enum)) {
            return false;
        }
        return contains((Enum) obj);
    }

    public final /* bridge */ int indexOf(Object obj) {
        if (!(obj instanceof Enum)) {
            return -1;
        }
        return indexOf((Enum) obj);
    }

    public final /* bridge */ int lastIndexOf(Object obj) {
        if (!(obj instanceof Enum)) {
            return -1;
        }
        return lastIndexOf((Enum) obj);
    }

    public EnumEntriesList(Enum[] enumArr) {
        Intrinsics.checkNotNullParameter(enumArr, RemoteConfigConstants.ResponseFieldKey.ENTRIES);
        this.entries = enumArr;
    }

    public int getSize() {
        return this.entries.length;
    }

    public Enum get(int i) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, this.entries.length);
        return this.entries[i];
    }

    public boolean contains(Enum enumR) {
        Intrinsics.checkNotNullParameter(enumR, "element");
        return ((Enum) ArraysKt.getOrNull((Object[]) this.entries, enumR.ordinal())) == enumR;
    }

    public int indexOf(Enum enumR) {
        Intrinsics.checkNotNullParameter(enumR, "element");
        int ordinal = enumR.ordinal();
        if (((Enum) ArraysKt.getOrNull((Object[]) this.entries, ordinal)) == enumR) {
            return ordinal;
        }
        return -1;
    }

    public int lastIndexOf(Enum enumR) {
        Intrinsics.checkNotNullParameter(enumR, "element");
        return indexOf((Object) enumR);
    }

    private final Object writeReplace() {
        return new EnumEntriesSerializationProxy(this.entries);
    }
}
