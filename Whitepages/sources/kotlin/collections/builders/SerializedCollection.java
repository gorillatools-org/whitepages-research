package kotlin.collections.builders;

import java.io.Externalizable;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class SerializedCollection implements Externalizable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final long serialVersionUID = 0;
    private Collection collection;
    private final int tag;

    public SerializedCollection(Collection collection2, int i) {
        Intrinsics.checkNotNullParameter(collection2, "collection");
        this.collection = collection2;
        this.tag = i;
    }

    public void writeExternal(ObjectOutput objectOutput) {
        Intrinsics.checkNotNullParameter(objectOutput, "output");
        objectOutput.writeByte(this.tag);
        objectOutput.writeInt(this.collection.size());
        for (Object writeObject : this.collection) {
            objectOutput.writeObject(writeObject);
        }
    }

    public void readExternal(ObjectInput objectInput) {
        Collection collection2;
        Intrinsics.checkNotNullParameter(objectInput, "input");
        byte readByte = objectInput.readByte();
        byte b = readByte & 1;
        if ((readByte & -2) == 0) {
            int readInt = objectInput.readInt();
            if (readInt >= 0) {
                int i = 0;
                if (b == 0) {
                    List createListBuilder = CollectionsKt.createListBuilder(readInt);
                    while (i < readInt) {
                        createListBuilder.add(objectInput.readObject());
                        i++;
                    }
                    collection2 = CollectionsKt.build(createListBuilder);
                } else if (b == 1) {
                    Set createSetBuilder = SetsKt.createSetBuilder(readInt);
                    while (i < readInt) {
                        createSetBuilder.add(objectInput.readObject());
                        i++;
                    }
                    collection2 = SetsKt.build(createSetBuilder);
                } else {
                    throw new InvalidObjectException("Unsupported collection type tag: " + b + '.');
                }
                this.collection = collection2;
                return;
            }
            throw new InvalidObjectException("Illegal size value: " + readInt + '.');
        }
        throw new InvalidObjectException("Unsupported flags value: " + readByte + '.');
    }

    private final Object readResolve() {
        return this.collection;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
