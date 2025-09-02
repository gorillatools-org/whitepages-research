package androidx.room;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import androidx.sqlite.db.SupportSQLiteCompat$Api19Impl;
import androidx.sqlite.db.SupportSQLiteCompat$Api23Impl;
import androidx.sqlite.db.SupportSQLiteCompat$Api29Impl;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.google.android.gms.actions.SearchIntents;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public final class AutoClosingRoomOpenHelper implements SupportSQLiteOpenHelper, DelegatingOpenHelper {
    public final AutoCloser autoCloser;
    private final AutoClosingSupportSQLiteDatabase autoClosingDb;
    private final SupportSQLiteOpenHelper delegate;

    public String getDatabaseName() {
        return this.delegate.getDatabaseName();
    }

    public void setWriteAheadLoggingEnabled(boolean z) {
        this.delegate.setWriteAheadLoggingEnabled(z);
    }

    public AutoClosingRoomOpenHelper(SupportSQLiteOpenHelper supportSQLiteOpenHelper, AutoCloser autoCloser2) {
        Intrinsics.checkNotNullParameter(supportSQLiteOpenHelper, "delegate");
        Intrinsics.checkNotNullParameter(autoCloser2, "autoCloser");
        this.delegate = supportSQLiteOpenHelper;
        this.autoCloser = autoCloser2;
        autoCloser2.init(getDelegate());
        this.autoClosingDb = new AutoClosingSupportSQLiteDatabase(autoCloser2);
    }

    public SupportSQLiteOpenHelper getDelegate() {
        return this.delegate;
    }

    public SupportSQLiteDatabase getWritableDatabase() {
        this.autoClosingDb.pokeOpen();
        return this.autoClosingDb;
    }

    public void close() {
        this.autoClosingDb.close();
    }

    public static final class AutoClosingSupportSQLiteDatabase implements SupportSQLiteDatabase {
        private final AutoCloser autoCloser;

        public AutoClosingSupportSQLiteDatabase(AutoCloser autoCloser2) {
            Intrinsics.checkNotNullParameter(autoCloser2, "autoCloser");
            this.autoCloser = autoCloser2;
        }

        public final void pokeOpen() {
            this.autoCloser.executeRefCountingFunction(AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$pokeOpen$1.INSTANCE);
        }

        public SupportSQLiteStatement compileStatement(String str) {
            Intrinsics.checkNotNullParameter(str, "sql");
            return new AutoClosingSupportSqliteStatement(str, this.autoCloser);
        }

        public void beginTransaction() {
            try {
                this.autoCloser.incrementCountAndEnsureDbIsOpen().beginTransaction();
            } catch (Throwable th) {
                this.autoCloser.decrementCountAndScheduleClose();
                throw th;
            }
        }

        public void beginTransactionNonExclusive() {
            try {
                this.autoCloser.incrementCountAndEnsureDbIsOpen().beginTransactionNonExclusive();
            } catch (Throwable th) {
                this.autoCloser.decrementCountAndScheduleClose();
                throw th;
            }
        }

        public void endTransaction() {
            if (this.autoCloser.getDelegateDatabase$room_runtime_release() != null) {
                try {
                    SupportSQLiteDatabase delegateDatabase$room_runtime_release = this.autoCloser.getDelegateDatabase$room_runtime_release();
                    Intrinsics.checkNotNull(delegateDatabase$room_runtime_release);
                    delegateDatabase$room_runtime_release.endTransaction();
                } finally {
                    this.autoCloser.decrementCountAndScheduleClose();
                }
            } else {
                throw new IllegalStateException("End transaction called but delegateDb is null");
            }
        }

        public void setTransactionSuccessful() {
            Unit unit;
            SupportSQLiteDatabase delegateDatabase$room_runtime_release = this.autoCloser.getDelegateDatabase$room_runtime_release();
            if (delegateDatabase$room_runtime_release != null) {
                delegateDatabase$room_runtime_release.setTransactionSuccessful();
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            if (unit == null) {
                throw new IllegalStateException("setTransactionSuccessful called but delegateDb is null");
            }
        }

        public boolean inTransaction() {
            if (this.autoCloser.getDelegateDatabase$room_runtime_release() == null) {
                return false;
            }
            return ((Boolean) this.autoCloser.executeRefCountingFunction(AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$inTransaction$1.INSTANCE)).booleanValue();
        }

        public Cursor query(String str) {
            Intrinsics.checkNotNullParameter(str, SearchIntents.EXTRA_QUERY);
            try {
                return new KeepAliveCursor(this.autoCloser.incrementCountAndEnsureDbIsOpen().query(str), this.autoCloser);
            } catch (Throwable th) {
                this.autoCloser.decrementCountAndScheduleClose();
                throw th;
            }
        }

        public Cursor query(SupportSQLiteQuery supportSQLiteQuery) {
            Intrinsics.checkNotNullParameter(supportSQLiteQuery, SearchIntents.EXTRA_QUERY);
            try {
                return new KeepAliveCursor(this.autoCloser.incrementCountAndEnsureDbIsOpen().query(supportSQLiteQuery), this.autoCloser);
            } catch (Throwable th) {
                this.autoCloser.decrementCountAndScheduleClose();
                throw th;
            }
        }

        public Cursor query(SupportSQLiteQuery supportSQLiteQuery, CancellationSignal cancellationSignal) {
            Intrinsics.checkNotNullParameter(supportSQLiteQuery, SearchIntents.EXTRA_QUERY);
            try {
                return new KeepAliveCursor(this.autoCloser.incrementCountAndEnsureDbIsOpen().query(supportSQLiteQuery, cancellationSignal), this.autoCloser);
            } catch (Throwable th) {
                this.autoCloser.decrementCountAndScheduleClose();
                throw th;
            }
        }

        public int update(String str, int i, ContentValues contentValues, String str2, Object[] objArr) {
            Intrinsics.checkNotNullParameter(str, "table");
            Intrinsics.checkNotNullParameter(contentValues, "values");
            return ((Number) this.autoCloser.executeRefCountingFunction(new AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$update$1(str, i, contentValues, str2, objArr))).intValue();
        }

        public void execSQL(String str) {
            Intrinsics.checkNotNullParameter(str, "sql");
            this.autoCloser.executeRefCountingFunction(new AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$execSQL$1(str));
        }

        public void execSQL(String str, Object[] objArr) {
            Intrinsics.checkNotNullParameter(str, "sql");
            Intrinsics.checkNotNullParameter(objArr, "bindArgs");
            this.autoCloser.executeRefCountingFunction(new AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$execSQL$2(str, objArr));
        }

        public boolean isOpen() {
            SupportSQLiteDatabase delegateDatabase$room_runtime_release = this.autoCloser.getDelegateDatabase$room_runtime_release();
            if (delegateDatabase$room_runtime_release == null) {
                return false;
            }
            return delegateDatabase$room_runtime_release.isOpen();
        }

        public String getPath() {
            return (String) this.autoCloser.executeRefCountingFunction(AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$path$1.INSTANCE);
        }

        public boolean isWriteAheadLoggingEnabled() {
            return ((Boolean) this.autoCloser.executeRefCountingFunction(AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$isWriteAheadLoggingEnabled$1.INSTANCE)).booleanValue();
        }

        public List getAttachedDbs() {
            return (List) this.autoCloser.executeRefCountingFunction(AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$attachedDbs$1.INSTANCE);
        }

        public void close() {
            this.autoCloser.closeDatabaseIfOpen();
        }
    }

    private static final class KeepAliveCursor implements Cursor {
        private final AutoCloser autoCloser;
        private final Cursor delegate;

        public void copyStringToBuffer(int i, CharArrayBuffer charArrayBuffer) {
            this.delegate.copyStringToBuffer(i, charArrayBuffer);
        }

        public void deactivate() {
            this.delegate.deactivate();
        }

        public byte[] getBlob(int i) {
            return this.delegate.getBlob(i);
        }

        public int getColumnCount() {
            return this.delegate.getColumnCount();
        }

        public int getColumnIndex(String str) {
            return this.delegate.getColumnIndex(str);
        }

        public int getColumnIndexOrThrow(String str) {
            return this.delegate.getColumnIndexOrThrow(str);
        }

        public String getColumnName(int i) {
            return this.delegate.getColumnName(i);
        }

        public String[] getColumnNames() {
            return this.delegate.getColumnNames();
        }

        public int getCount() {
            return this.delegate.getCount();
        }

        public double getDouble(int i) {
            return this.delegate.getDouble(i);
        }

        public Bundle getExtras() {
            return this.delegate.getExtras();
        }

        public float getFloat(int i) {
            return this.delegate.getFloat(i);
        }

        public int getInt(int i) {
            return this.delegate.getInt(i);
        }

        public long getLong(int i) {
            return this.delegate.getLong(i);
        }

        public int getPosition() {
            return this.delegate.getPosition();
        }

        public short getShort(int i) {
            return this.delegate.getShort(i);
        }

        public String getString(int i) {
            return this.delegate.getString(i);
        }

        public int getType(int i) {
            return this.delegate.getType(i);
        }

        public boolean getWantsAllOnMoveCalls() {
            return this.delegate.getWantsAllOnMoveCalls();
        }

        public boolean isAfterLast() {
            return this.delegate.isAfterLast();
        }

        public boolean isBeforeFirst() {
            return this.delegate.isBeforeFirst();
        }

        public boolean isClosed() {
            return this.delegate.isClosed();
        }

        public boolean isFirst() {
            return this.delegate.isFirst();
        }

        public boolean isLast() {
            return this.delegate.isLast();
        }

        public boolean isNull(int i) {
            return this.delegate.isNull(i);
        }

        public boolean move(int i) {
            return this.delegate.move(i);
        }

        public boolean moveToFirst() {
            return this.delegate.moveToFirst();
        }

        public boolean moveToLast() {
            return this.delegate.moveToLast();
        }

        public boolean moveToNext() {
            return this.delegate.moveToNext();
        }

        public boolean moveToPosition(int i) {
            return this.delegate.moveToPosition(i);
        }

        public boolean moveToPrevious() {
            return this.delegate.moveToPrevious();
        }

        public void registerContentObserver(ContentObserver contentObserver) {
            this.delegate.registerContentObserver(contentObserver);
        }

        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            this.delegate.registerDataSetObserver(dataSetObserver);
        }

        public boolean requery() {
            return this.delegate.requery();
        }

        public Bundle respond(Bundle bundle) {
            return this.delegate.respond(bundle);
        }

        public void setNotificationUri(ContentResolver contentResolver, Uri uri) {
            this.delegate.setNotificationUri(contentResolver, uri);
        }

        public void unregisterContentObserver(ContentObserver contentObserver) {
            this.delegate.unregisterContentObserver(contentObserver);
        }

        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            this.delegate.unregisterDataSetObserver(dataSetObserver);
        }

        public KeepAliveCursor(Cursor cursor, AutoCloser autoCloser2) {
            Intrinsics.checkNotNullParameter(cursor, "delegate");
            Intrinsics.checkNotNullParameter(autoCloser2, "autoCloser");
            this.delegate = cursor;
            this.autoCloser = autoCloser2;
        }

        public void close() {
            this.delegate.close();
            this.autoCloser.decrementCountAndScheduleClose();
        }

        public void setNotificationUris(ContentResolver contentResolver, List list) {
            Intrinsics.checkNotNullParameter(contentResolver, "cr");
            Intrinsics.checkNotNullParameter(list, "uris");
            SupportSQLiteCompat$Api29Impl.setNotificationUris(this.delegate, contentResolver, list);
        }

        public Uri getNotificationUri() {
            return SupportSQLiteCompat$Api19Impl.getNotificationUri(this.delegate);
        }

        public List getNotificationUris() {
            return SupportSQLiteCompat$Api29Impl.getNotificationUris(this.delegate);
        }

        public void setExtras(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "extras");
            SupportSQLiteCompat$Api23Impl.setExtras(this.delegate, bundle);
        }
    }

    private static final class AutoClosingSupportSqliteStatement implements SupportSQLiteStatement {
        private final AutoCloser autoCloser;
        private final ArrayList binds = new ArrayList();
        /* access modifiers changed from: private */
        public final String sql;

        public void close() {
        }

        public AutoClosingSupportSqliteStatement(String str, AutoCloser autoCloser2) {
            Intrinsics.checkNotNullParameter(str, "sql");
            Intrinsics.checkNotNullParameter(autoCloser2, "autoCloser");
            this.sql = str;
            this.autoCloser = autoCloser2;
        }

        private final Object executeSqliteStatementWithRefCount(Function1 function1) {
            return this.autoCloser.executeRefCountingFunction(new AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeSqliteStatementWithRefCount$1(this, function1));
        }

        /* access modifiers changed from: private */
        public final void doBinds(SupportSQLiteStatement supportSQLiteStatement) {
            Iterator it = this.binds.iterator();
            int i = 0;
            while (it.hasNext()) {
                it.next();
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                Object obj = this.binds.get(i);
                if (obj == null) {
                    supportSQLiteStatement.bindNull(i2);
                } else if (obj instanceof Long) {
                    supportSQLiteStatement.bindLong(i2, ((Number) obj).longValue());
                } else if (obj instanceof Double) {
                    supportSQLiteStatement.bindDouble(i2, ((Number) obj).doubleValue());
                } else if (obj instanceof String) {
                    supportSQLiteStatement.bindString(i2, (String) obj);
                } else if (obj instanceof byte[]) {
                    supportSQLiteStatement.bindBlob(i2, (byte[]) obj);
                }
                i = i2;
            }
        }

        private final void saveBinds(int i, Object obj) {
            int size;
            int i2 = i - 1;
            if (i2 >= this.binds.size() && (size = this.binds.size()) <= i2) {
                while (true) {
                    this.binds.add((Object) null);
                    if (size == i2) {
                        break;
                    }
                    size++;
                }
            }
            this.binds.set(i2, obj);
        }

        public int executeUpdateDelete() {
            return ((Number) executeSqliteStatementWithRefCount(AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeUpdateDelete$1.INSTANCE)).intValue();
        }

        public long executeInsert() {
            return ((Number) executeSqliteStatementWithRefCount(AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeInsert$1.INSTANCE)).longValue();
        }

        public void bindNull(int i) {
            saveBinds(i, (Object) null);
        }

        public void bindLong(int i, long j) {
            saveBinds(i, Long.valueOf(j));
        }

        public void bindDouble(int i, double d) {
            saveBinds(i, Double.valueOf(d));
        }

        public void bindString(int i, String str) {
            Intrinsics.checkNotNullParameter(str, "value");
            saveBinds(i, str);
        }

        public void bindBlob(int i, byte[] bArr) {
            Intrinsics.checkNotNullParameter(bArr, "value");
            saveBinds(i, bArr);
        }
    }
}
