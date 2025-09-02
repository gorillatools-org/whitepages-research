package androidx.room;

import androidx.sqlite.db.SupportSQLiteDatabase;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

final class RoomDatabase$endTransaction$1 extends Lambda implements Function1 {
    final /* synthetic */ RoomDatabase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RoomDatabase$endTransaction$1(RoomDatabase roomDatabase) {
        super(1);
        this.this$0 = roomDatabase;
    }

    public final Object invoke(SupportSQLiteDatabase supportSQLiteDatabase) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "it");
        this.this$0.internalEndTransaction();
        return null;
    }
}
