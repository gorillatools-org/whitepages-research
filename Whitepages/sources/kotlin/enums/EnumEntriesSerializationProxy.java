package kotlin.enums;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class EnumEntriesSerializationProxy implements Serializable {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final long serialVersionUID = 0;
    private final Class c;

    public EnumEntriesSerializationProxy(Enum[] enumArr) {
        Intrinsics.checkNotNullParameter(enumArr, RemoteConfigConstants.ResponseFieldKey.ENTRIES);
        Class<?> componentType = enumArr.getClass().getComponentType();
        Intrinsics.checkNotNull(componentType);
        this.c = componentType;
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    private final Object readResolve() {
        Object[] enumConstants = this.c.getEnumConstants();
        Intrinsics.checkNotNullExpressionValue(enumConstants, "getEnumConstants(...)");
        return EnumEntriesKt.enumEntries((Enum[]) enumConstants);
    }
}
