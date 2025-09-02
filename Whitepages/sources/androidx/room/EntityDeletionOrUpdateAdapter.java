package androidx.room;

import androidx.sqlite.db.SupportSQLiteStatement;
import kotlin.jvm.internal.Intrinsics;

public abstract class EntityDeletionOrUpdateAdapter extends SharedSQLiteStatement {
    /* access modifiers changed from: protected */
    public abstract void bind(SupportSQLiteStatement supportSQLiteStatement, Object obj);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EntityDeletionOrUpdateAdapter(RoomDatabase roomDatabase) {
        super(roomDatabase);
        Intrinsics.checkNotNullParameter(roomDatabase, "database");
    }

    public final int handle(Object obj) {
        SupportSQLiteStatement acquire = acquire();
        try {
            bind(acquire, obj);
            return acquire.executeUpdateDelete();
        } finally {
            release(acquire);
        }
    }
}
