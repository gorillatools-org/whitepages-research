package androidx.room;

import kotlin.jvm.internal.Intrinsics;

public final class RoomMasterTable {
    public static final RoomMasterTable INSTANCE = new RoomMasterTable();

    private RoomMasterTable() {
    }

    public static final String createInsertQuery(String str) {
        Intrinsics.checkNotNullParameter(str, "hash");
        return "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '" + str + "')";
    }
}
