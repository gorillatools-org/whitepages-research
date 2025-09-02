package androidx.room.util;

import android.database.AbstractWindowedCursor;
import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteQuery;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public abstract class DBUtil {
    public static final Cursor query(RoomDatabase roomDatabase, SupportSQLiteQuery supportSQLiteQuery, boolean z, CancellationSignal cancellationSignal) {
        Intrinsics.checkNotNullParameter(roomDatabase, "db");
        Intrinsics.checkNotNullParameter(supportSQLiteQuery, "sqLiteQuery");
        Cursor query = roomDatabase.query(supportSQLiteQuery, cancellationSignal);
        if (!z || !(query instanceof AbstractWindowedCursor)) {
            return query;
        }
        AbstractWindowedCursor abstractWindowedCursor = (AbstractWindowedCursor) query;
        int count = abstractWindowedCursor.getCount();
        return (abstractWindowedCursor.hasWindow() ? abstractWindowedCursor.getWindow().getNumRows() : count) < count ? CursorUtil.copyAndClose(query) : query;
    }

    public static final void dropFtsSyncTriggers(SupportSQLiteDatabase supportSQLiteDatabase) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "db");
        List createListBuilder = CollectionsKt.createListBuilder();
        Cursor query = supportSQLiteDatabase.query("SELECT name FROM sqlite_master WHERE type = 'trigger'");
        while (query.moveToNext()) {
            try {
                createListBuilder.add(query.getString(0));
            } catch (Throwable th) {
                CloseableKt.closeFinally(query, th);
                throw th;
            }
        }
        Unit unit = Unit.INSTANCE;
        CloseableKt.closeFinally(query, (Throwable) null);
        for (String str : CollectionsKt.build(createListBuilder)) {
            Intrinsics.checkNotNullExpressionValue(str, "triggerName");
            if (StringsKt.startsWith$default(str, "room_fts_content_sync_", false, 2, (Object) null)) {
                supportSQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS " + str);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003e, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003f, code lost:
        kotlin.io.CloseableKt.closeFinally(r8, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0042, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final int readVersion(java.io.File r8) {
        /*
            java.lang.String r0 = "databaseFile"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r8)
            java.nio.channels.FileChannel r8 = r0.getChannel()
            r0 = 4
            java.nio.ByteBuffer r7 = java.nio.ByteBuffer.allocate(r0)     // Catch:{ all -> 0x0033 }
            r4 = 4
            r6 = 1
            r2 = 60
            r1 = r8
            r1.tryLock(r2, r4, r6)     // Catch:{ all -> 0x0033 }
            r1 = 60
            r8.position(r1)     // Catch:{ all -> 0x0033 }
            int r1 = r8.read(r7)     // Catch:{ all -> 0x0033 }
            if (r1 != r0) goto L_0x0035
            r7.rewind()     // Catch:{ all -> 0x0033 }
            int r0 = r7.getInt()     // Catch:{ all -> 0x0033 }
            r1 = 0
            kotlin.io.CloseableKt.closeFinally(r8, r1)
            return r0
        L_0x0033:
            r0 = move-exception
            goto L_0x003d
        L_0x0035:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x0033 }
            java.lang.String r1 = "Bad database header, unable to read 4 bytes at offset 60"
            r0.<init>(r1)     // Catch:{ all -> 0x0033 }
            throw r0     // Catch:{ all -> 0x0033 }
        L_0x003d:
            throw r0     // Catch:{ all -> 0x003e }
        L_0x003e:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r8, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.util.DBUtil.readVersion(java.io.File):int");
    }
}
