package androidx.sqlite.db;

import android.database.Cursor;
import android.os.Bundle;
import kotlin.jvm.internal.Intrinsics;

public final class SupportSQLiteCompat$Api23Impl {
    public static final SupportSQLiteCompat$Api23Impl INSTANCE = new SupportSQLiteCompat$Api23Impl();

    private SupportSQLiteCompat$Api23Impl() {
    }

    public static final void setExtras(Cursor cursor, Bundle bundle) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        Intrinsics.checkNotNullParameter(bundle, "extras");
        cursor.setExtras(bundle);
    }
}
