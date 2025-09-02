package kotlin.enums;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import kotlin.jvm.internal.Intrinsics;

public abstract class EnumEntriesKt {
    public static final EnumEntries enumEntries(Enum[] enumArr) {
        Intrinsics.checkNotNullParameter(enumArr, RemoteConfigConstants.ResponseFieldKey.ENTRIES);
        return new EnumEntriesList(enumArr);
    }
}
