package androidx.room;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;

public final class InvalidationLiveDataContainer {
    private final RoomDatabase database;
    private final Set liveDataSet;

    public InvalidationLiveDataContainer(RoomDatabase roomDatabase) {
        Intrinsics.checkNotNullParameter(roomDatabase, "database");
        this.database = roomDatabase;
        Set newSetFromMap = Collections.newSetFromMap(new IdentityHashMap());
        Intrinsics.checkNotNullExpressionValue(newSetFromMap, "newSetFromMap(IdentityHashMap())");
        this.liveDataSet = newSetFromMap;
    }
}
