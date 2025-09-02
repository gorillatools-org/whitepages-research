package androidx.room;

import androidx.sqlite.db.SupportSQLiteStatement;
import kotlin.jvm.internal.Intrinsics;

public abstract class EntityInsertionAdapter extends SharedSQLiteStatement {
    /* access modifiers changed from: protected */
    public abstract void bind(SupportSQLiteStatement supportSQLiteStatement, Object obj);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EntityInsertionAdapter(RoomDatabase roomDatabase) {
        super(roomDatabase);
        Intrinsics.checkNotNullParameter(roomDatabase, "database");
    }

    public final void insert(Object obj) {
        SupportSQLiteStatement acquire = acquire();
        try {
            bind(acquire, obj);
            acquire.executeInsert();
        } finally {
            release(acquire);
        }
    }
}
