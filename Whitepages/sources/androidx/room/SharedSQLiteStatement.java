package androidx.room;

import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.internal.Intrinsics;

public abstract class SharedSQLiteStatement {
    private final RoomDatabase database;
    private final AtomicBoolean lock = new AtomicBoolean(false);
    private final Lazy stmt$delegate = LazyKt.lazy(new SharedSQLiteStatement$stmt$2(this));

    /* access modifiers changed from: protected */
    public abstract String createQuery();

    public SharedSQLiteStatement(RoomDatabase roomDatabase) {
        Intrinsics.checkNotNullParameter(roomDatabase, "database");
        this.database = roomDatabase;
    }

    private final SupportSQLiteStatement getStmt() {
        return (SupportSQLiteStatement) this.stmt$delegate.getValue();
    }

    /* access modifiers changed from: protected */
    public void assertNotMainThread() {
        this.database.assertNotMainThread();
    }

    /* access modifiers changed from: private */
    public final SupportSQLiteStatement createNewStatement() {
        return this.database.compileStatement(createQuery());
    }

    private final SupportSQLiteStatement getStmt(boolean z) {
        if (z) {
            return getStmt();
        }
        return createNewStatement();
    }

    public SupportSQLiteStatement acquire() {
        assertNotMainThread();
        return getStmt(this.lock.compareAndSet(false, true));
    }

    public void release(SupportSQLiteStatement supportSQLiteStatement) {
        Intrinsics.checkNotNullParameter(supportSQLiteStatement, "statement");
        if (supportSQLiteStatement == getStmt()) {
            this.lock.set(false);
        }
    }
}
