package androidx.room;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;

public final class InvalidationTracker$refreshRunnable$1 implements Runnable {
    final /* synthetic */ InvalidationTracker this$0;

    InvalidationTracker$refreshRunnable$1(InvalidationTracker invalidationTracker) {
        this.this$0 = invalidationTracker;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0080, code lost:
        if (r0 != null) goto L_0x0082;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0082, code lost:
        r0.decrementCountAndScheduleClose();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00a6, code lost:
        if (r0 == null) goto L_0x00c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00bd, code lost:
        if (r0 == null) goto L_0x00c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00c7, code lost:
        if (r2.isEmpty() != false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00c9, code lost:
        r0 = r4.this$0.getObserverMap$room_runtime_release();
        r1 = r4.this$0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00d1, code lost:
        monitor-enter(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        r1 = r1.getObserverMap$room_runtime_release().iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00de, code lost:
        if (r1.hasNext() == false) goto L_0x00f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00e0, code lost:
        ((androidx.room.InvalidationTracker.ObserverWrapper) ((java.util.Map.Entry) r1.next()).getValue()).notifyByTableInvalidStatus$room_runtime_release(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00f0, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00f2, code lost:
        r1 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00f4, code lost:
        monitor-exit(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00f7, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r4 = this;
            androidx.room.InvalidationTracker r0 = r4.this$0
            androidx.room.RoomDatabase r0 = r0.getDatabase$room_runtime_release()
            java.util.concurrent.locks.Lock r0 = r0.getCloseLock$room_runtime_release()
            r0.lock()
            androidx.room.InvalidationTracker r1 = r4.this$0     // Catch:{ IllegalStateException -> 0x008b, SQLiteException -> 0x0089 }
            boolean r1 = r1.ensureInitialization$room_runtime_release()     // Catch:{ IllegalStateException -> 0x008b, SQLiteException -> 0x0089 }
            if (r1 != 0) goto L_0x0024
            r0.unlock()
            androidx.room.InvalidationTracker r0 = r4.this$0
            androidx.room.AutoCloser r0 = r0.autoCloser
            if (r0 == 0) goto L_0x0023
            r0.decrementCountAndScheduleClose()
        L_0x0023:
            return
        L_0x0024:
            androidx.room.InvalidationTracker r1 = r4.this$0     // Catch:{ IllegalStateException -> 0x008b, SQLiteException -> 0x0089 }
            java.util.concurrent.atomic.AtomicBoolean r1 = r1.getPendingRefresh()     // Catch:{ IllegalStateException -> 0x008b, SQLiteException -> 0x0089 }
            r2 = 1
            r3 = 0
            boolean r1 = r1.compareAndSet(r2, r3)     // Catch:{ IllegalStateException -> 0x008b, SQLiteException -> 0x0089 }
            if (r1 != 0) goto L_0x0041
            r0.unlock()
            androidx.room.InvalidationTracker r0 = r4.this$0
            androidx.room.AutoCloser r0 = r0.autoCloser
            if (r0 == 0) goto L_0x0040
            r0.decrementCountAndScheduleClose()
        L_0x0040:
            return
        L_0x0041:
            androidx.room.InvalidationTracker r1 = r4.this$0     // Catch:{ IllegalStateException -> 0x008b, SQLiteException -> 0x0089 }
            androidx.room.RoomDatabase r1 = r1.getDatabase$room_runtime_release()     // Catch:{ IllegalStateException -> 0x008b, SQLiteException -> 0x0089 }
            boolean r1 = r1.inTransaction()     // Catch:{ IllegalStateException -> 0x008b, SQLiteException -> 0x0089 }
            if (r1 == 0) goto L_0x005c
            r0.unlock()
            androidx.room.InvalidationTracker r0 = r4.this$0
            androidx.room.AutoCloser r0 = r0.autoCloser
            if (r0 == 0) goto L_0x005b
            r0.decrementCountAndScheduleClose()
        L_0x005b:
            return
        L_0x005c:
            androidx.room.InvalidationTracker r1 = r4.this$0     // Catch:{ IllegalStateException -> 0x008b, SQLiteException -> 0x0089 }
            androidx.room.RoomDatabase r1 = r1.getDatabase$room_runtime_release()     // Catch:{ IllegalStateException -> 0x008b, SQLiteException -> 0x0089 }
            androidx.sqlite.db.SupportSQLiteOpenHelper r1 = r1.getOpenHelper()     // Catch:{ IllegalStateException -> 0x008b, SQLiteException -> 0x0089 }
            androidx.sqlite.db.SupportSQLiteDatabase r1 = r1.getWritableDatabase()     // Catch:{ IllegalStateException -> 0x008b, SQLiteException -> 0x0089 }
            r1.beginTransactionNonExclusive()     // Catch:{ IllegalStateException -> 0x008b, SQLiteException -> 0x0089 }
            java.util.Set r2 = r4.checkUpdatedTable()     // Catch:{ all -> 0x008d }
            r1.setTransactionSuccessful()     // Catch:{ all -> 0x008d }
            r1.endTransaction()     // Catch:{ IllegalStateException -> 0x008b, SQLiteException -> 0x0089 }
            r0.unlock()
            androidx.room.InvalidationTracker r0 = r4.this$0
            androidx.room.AutoCloser r0 = r0.autoCloser
            if (r0 == 0) goto L_0x00c0
        L_0x0082:
            r0.decrementCountAndScheduleClose()
            goto L_0x00c0
        L_0x0086:
            r1 = move-exception
            goto L_0x00f9
        L_0x0089:
            r1 = move-exception
            goto L_0x0092
        L_0x008b:
            r1 = move-exception
            goto L_0x00a9
        L_0x008d:
            r2 = move-exception
            r1.endTransaction()     // Catch:{ IllegalStateException -> 0x008b, SQLiteException -> 0x0089 }
            throw r2     // Catch:{ IllegalStateException -> 0x008b, SQLiteException -> 0x0089 }
        L_0x0092:
            java.lang.String r2 = "ROOM"
            java.lang.String r3 = "Cannot run invalidation tracker. Is the db closed?"
            android.util.Log.e(r2, r3, r1)     // Catch:{ all -> 0x0086 }
            java.util.Set r2 = kotlin.collections.SetsKt.emptySet()     // Catch:{ all -> 0x0086 }
            r0.unlock()
            androidx.room.InvalidationTracker r0 = r4.this$0
            androidx.room.AutoCloser r0 = r0.autoCloser
            if (r0 == 0) goto L_0x00c0
            goto L_0x0082
        L_0x00a9:
            java.lang.String r2 = "ROOM"
            java.lang.String r3 = "Cannot run invalidation tracker. Is the db closed?"
            android.util.Log.e(r2, r3, r1)     // Catch:{ all -> 0x0086 }
            java.util.Set r2 = kotlin.collections.SetsKt.emptySet()     // Catch:{ all -> 0x0086 }
            r0.unlock()
            androidx.room.InvalidationTracker r0 = r4.this$0
            androidx.room.AutoCloser r0 = r0.autoCloser
            if (r0 == 0) goto L_0x00c0
            goto L_0x0082
        L_0x00c0:
            r0 = r2
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x00f8
            androidx.room.InvalidationTracker r0 = r4.this$0
            androidx.arch.core.internal.SafeIterableMap r0 = r0.getObserverMap$room_runtime_release()
            androidx.room.InvalidationTracker r1 = r4.this$0
            monitor-enter(r0)
            androidx.arch.core.internal.SafeIterableMap r1 = r1.getObserverMap$room_runtime_release()     // Catch:{ all -> 0x00f0 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x00f0 }
        L_0x00da:
            boolean r3 = r1.hasNext()     // Catch:{ all -> 0x00f0 }
            if (r3 == 0) goto L_0x00f2
            java.lang.Object r3 = r1.next()     // Catch:{ all -> 0x00f0 }
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3     // Catch:{ all -> 0x00f0 }
            java.lang.Object r3 = r3.getValue()     // Catch:{ all -> 0x00f0 }
            androidx.room.InvalidationTracker$ObserverWrapper r3 = (androidx.room.InvalidationTracker.ObserverWrapper) r3     // Catch:{ all -> 0x00f0 }
            r3.notifyByTableInvalidStatus$room_runtime_release(r2)     // Catch:{ all -> 0x00f0 }
            goto L_0x00da
        L_0x00f0:
            r1 = move-exception
            goto L_0x00f6
        L_0x00f2:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00f0 }
            monitor-exit(r0)
            goto L_0x00f8
        L_0x00f6:
            monitor-exit(r0)
            throw r1
        L_0x00f8:
            return
        L_0x00f9:
            r0.unlock()
            androidx.room.InvalidationTracker r0 = r4.this$0
            androidx.room.AutoCloser r0 = r0.autoCloser
            if (r0 == 0) goto L_0x0107
            r0.decrementCountAndScheduleClose()
        L_0x0107:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.InvalidationTracker$refreshRunnable$1.run():void");
    }

    private final Set checkUpdatedTable() {
        InvalidationTracker invalidationTracker = this.this$0;
        Set createSetBuilder = SetsKt.createSetBuilder();
        Cursor query$default = RoomDatabase.query$default(invalidationTracker.getDatabase$room_runtime_release(), new SimpleSQLiteQuery("SELECT * FROM room_table_modification_log WHERE invalidated = 1;"), (CancellationSignal) null, 2, (Object) null);
        while (query$default.moveToNext()) {
            try {
                createSetBuilder.add(Integer.valueOf(query$default.getInt(0)));
            } catch (Throwable th) {
                CloseableKt.closeFinally(query$default, th);
                throw th;
            }
        }
        Unit unit = Unit.INSTANCE;
        CloseableKt.closeFinally(query$default, (Throwable) null);
        Set build = SetsKt.build(createSetBuilder);
        if (!build.isEmpty()) {
            if (this.this$0.getCleanupStatement$room_runtime_release() != null) {
                SupportSQLiteStatement cleanupStatement$room_runtime_release = this.this$0.getCleanupStatement$room_runtime_release();
                if (cleanupStatement$room_runtime_release != null) {
                    cleanupStatement$room_runtime_release.executeUpdateDelete();
                } else {
                    throw new IllegalArgumentException("Required value was null.");
                }
            } else {
                throw new IllegalStateException("Required value was null.");
            }
        }
        return build;
    }
}
