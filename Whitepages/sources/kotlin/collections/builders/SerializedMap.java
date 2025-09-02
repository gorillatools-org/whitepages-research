package kotlin.collections.builders;

import java.io.Externalizable;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Map;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

final class SerializedMap implements Externalizable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final long serialVersionUID = 0;
    private Map map;

    public SerializedMap(Map map2) {
        Intrinsics.checkNotNullParameter(map2, "map");
        this.map = map2;
    }

    public void writeExternal(ObjectOutput objectOutput) {
        Intrinsics.checkNotNullParameter(objectOutput, "output");
        objectOutput.writeByte(0);
        objectOutput.writeInt(this.map.size());
        for (Map.Entry entry : this.map.entrySet()) {
            objectOutput.writeObject(entry.getKey());
            objectOutput.writeObject(entry.getValue());
        }
    }

    public void readExternal(ObjectInput objectInput) {
        Intrinsics.checkNotNullParameter(objectInput, "input");
        byte readByte = objectInput.readByte();
        if (readByte == 0) {
            int readInt = objectInput.readInt();
            if (readInt >= 0) {
                Map createMapBuilder = MapsKt.createMapBuilder(readInt);
                for (int i = 0; i < readInt; i++) {
                    createMapBuilder.put(objectInput.readObject(), objectInput.readObject());
                }
                this.map = MapsKt.build(createMapBuilder);
                return;
            }
            throw new InvalidObjectException("Illegal size value: " + readInt + '.');
        }
        throw new InvalidObjectException("Unsupported flags value: " + readByte);
    }

    private final Object readResolve() {
        return this.map;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
