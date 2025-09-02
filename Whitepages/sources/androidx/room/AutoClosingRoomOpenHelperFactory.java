package androidx.room;

import androidx.sqlite.db.SupportSQLiteOpenHelper;
import kotlin.jvm.internal.Intrinsics;

public final class AutoClosingRoomOpenHelperFactory implements SupportSQLiteOpenHelper.Factory {
    private final AutoCloser autoCloser;
    private final SupportSQLiteOpenHelper.Factory delegate;

    public AutoClosingRoomOpenHelperFactory(SupportSQLiteOpenHelper.Factory factory, AutoCloser autoCloser2) {
        Intrinsics.checkNotNullParameter(factory, "delegate");
        Intrinsics.checkNotNullParameter(autoCloser2, "autoCloser");
        this.delegate = factory;
        this.autoCloser = autoCloser2;
    }

    public AutoClosingRoomOpenHelper create(SupportSQLiteOpenHelper.Configuration configuration) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        return new AutoClosingRoomOpenHelper(this.delegate.create(configuration), this.autoCloser);
    }
}
