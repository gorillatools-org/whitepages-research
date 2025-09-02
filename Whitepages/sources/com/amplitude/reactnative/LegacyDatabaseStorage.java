package com.amplitude.reactnative;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.File;
import java.util.List;
import kotlin.NotImplementedError;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class LegacyDatabaseStorage extends SQLiteOpenHelper {
    private int currentDbVersion = 4;
    private File file;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LegacyDatabaseStorage(Context context, String str) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 4);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, "databaseName");
        File databasePath = context.getDatabasePath(str);
        Intrinsics.checkNotNullExpressionValue(databasePath, "getDatabasePath(...)");
        this.file = databasePath;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        Intrinsics.checkNotNullParameter(sQLiteDatabase, "db");
        throw new NotImplementedError((String) null, 1, (DefaultConstructorMarker) null);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        this.currentDbVersion = i;
    }

    private final Cursor queryDb(SQLiteDatabase sQLiteDatabase, String str, String[] strArr, String str2, String[] strArr2, String str3) {
        return sQLiteDatabase.query(str, strArr, str2, strArr2, (String) null, (String) null, str3, (String) null);
    }

    private final void handleIfCursorRowTooLargeException(IllegalStateException illegalStateException) {
        String message = illegalStateException.getMessage();
        if (message == null || message.length() == 0 || !StringsKt.contains$default((CharSequence) message, (CharSequence) "Couldn't read", false, 2, (Object) null) || !StringsKt.contains$default((CharSequence) message, (CharSequence) "CursorWindow", false, 2, (Object) null)) {
            throw illegalStateException;
        }
        closeDb();
    }

    private final void convertIfCursorWindowException(RuntimeException runtimeException) {
        String message = runtimeException.getMessage();
        if (message == null || message.length() == 0 || (!StringsKt.startsWith$default(message, "Cursor window allocation of", false, 2, (Object) null) && !StringsKt.startsWith$default(message, "Could not allocate CursorWindow", false, 2, (Object) null))) {
            throw runtimeException;
        }
        throw new CursorWindowAllocationException(message);
    }

    private final void closeDb() {
        try {
            close();
        } catch (Exception e) {
            LogcatLogger logger = LogcatLogger.Companion.getLogger();
            logger.error("close failed: " + e.getMessage());
        }
    }

    public final synchronized List readEvents() {
        return readEventsFromTable("events");
    }

    public final synchronized List readIdentifies() {
        return readEventsFromTable("identifys");
    }

    public final synchronized List readInterceptedIdentifies() {
        if (this.currentDbVersion < 4) {
            return CollectionsKt.emptyList();
        }
        return readEventsFromTable("identify_interceptor");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00c6, code lost:
        if (r3 == null) goto L_0x0069;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.List readEventsFromTable(java.lang.String r12) {
        /*
            r11 = this;
            java.lang.String r0 = " failed: "
            java.lang.String r1 = "read events from "
            java.io.File r2 = r11.file
            boolean r2 = r2.exists()
            if (r2 != 0) goto L_0x0012
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            return r12
        L_0x0012:
            java.util.LinkedList r2 = new java.util.LinkedList
            r2.<init>()
            r3 = 0
            android.database.sqlite.SQLiteDatabase r5 = r11.getReadableDatabase()     // Catch:{ SQLiteException -> 0x0064, StackOverflowError -> 0x0062, IllegalStateException -> 0x0060, RuntimeException -> 0x005e }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)     // Catch:{ SQLiteException -> 0x0064, StackOverflowError -> 0x0062, IllegalStateException -> 0x0060, RuntimeException -> 0x005e }
            java.lang.String r4 = "id"
            java.lang.String r6 = "event"
            java.lang.String[] r7 = new java.lang.String[]{r4, r6}     // Catch:{ SQLiteException -> 0x0064, StackOverflowError -> 0x0062, IllegalStateException -> 0x0060, RuntimeException -> 0x005e }
            java.lang.String r10 = "id ASC"
            r8 = 0
            r9 = 0
            r4 = r11
            r6 = r12
            android.database.Cursor r3 = r4.queryDb(r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x0064, StackOverflowError -> 0x0062, IllegalStateException -> 0x0060, RuntimeException -> 0x005e }
        L_0x0031:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)     // Catch:{ SQLiteException -> 0x0064, StackOverflowError -> 0x0062, IllegalStateException -> 0x0060, RuntimeException -> 0x005e }
            boolean r4 = r3.moveToNext()     // Catch:{ SQLiteException -> 0x0064, StackOverflowError -> 0x0062, IllegalStateException -> 0x0060, RuntimeException -> 0x005e }
            if (r4 == 0) goto L_0x0066
            r4 = 0
            long r4 = r3.getLong(r4)     // Catch:{ SQLiteException -> 0x0064, StackOverflowError -> 0x0062, IllegalStateException -> 0x0060, RuntimeException -> 0x005e }
            r6 = 1
            java.lang.String r6 = r3.getString(r6)     // Catch:{ SQLiteException -> 0x0064, StackOverflowError -> 0x0062, IllegalStateException -> 0x0060, RuntimeException -> 0x005e }
            if (r6 == 0) goto L_0x0031
            int r7 = r6.length()     // Catch:{ SQLiteException -> 0x0064, StackOverflowError -> 0x0062, IllegalStateException -> 0x0060, RuntimeException -> 0x005e }
            if (r7 != 0) goto L_0x004d
            goto L_0x0031
        L_0x004d:
            org.json.JSONObject r7 = new org.json.JSONObject     // Catch:{ SQLiteException -> 0x0064, StackOverflowError -> 0x0062, IllegalStateException -> 0x0060, RuntimeException -> 0x005e }
            r7.<init>(r6)     // Catch:{ SQLiteException -> 0x0064, StackOverflowError -> 0x0062, IllegalStateException -> 0x0060, RuntimeException -> 0x005e }
            java.lang.String r6 = "event_id"
            r7.put(r6, r4)     // Catch:{ SQLiteException -> 0x0064, StackOverflowError -> 0x0062, IllegalStateException -> 0x0060, RuntimeException -> 0x005e }
            r2.add(r7)     // Catch:{ SQLiteException -> 0x0064, StackOverflowError -> 0x0062, IllegalStateException -> 0x0060, RuntimeException -> 0x005e }
            goto L_0x0031
        L_0x005b:
            r12 = move-exception
            goto L_0x00ca
        L_0x005e:
            r12 = move-exception
            goto L_0x006d
        L_0x0060:
            r12 = move-exception
            goto L_0x0073
        L_0x0062:
            r4 = move-exception
            goto L_0x0079
        L_0x0064:
            r4 = move-exception
            goto L_0x00a1
        L_0x0066:
            r3.close()
        L_0x0069:
            r11.close()
            goto L_0x00c9
        L_0x006d:
            r11.convertIfCursorWindowException(r12)     // Catch:{ all -> 0x005b }
            if (r3 == 0) goto L_0x0069
            goto L_0x0066
        L_0x0073:
            r11.handleIfCursorRowTooLargeException(r12)     // Catch:{ all -> 0x005b }
            if (r3 == 0) goto L_0x0069
            goto L_0x0066
        L_0x0079:
            com.amplitude.reactnative.LogcatLogger$Companion r5 = com.amplitude.reactnative.LogcatLogger.Companion     // Catch:{ all -> 0x005b }
            com.amplitude.reactnative.LogcatLogger r5 = r5.getLogger()     // Catch:{ all -> 0x005b }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x005b }
            r6.<init>()     // Catch:{ all -> 0x005b }
            r6.append(r1)     // Catch:{ all -> 0x005b }
            r6.append(r12)     // Catch:{ all -> 0x005b }
            r6.append(r0)     // Catch:{ all -> 0x005b }
            java.lang.String r12 = r4.getMessage()     // Catch:{ all -> 0x005b }
            r6.append(r12)     // Catch:{ all -> 0x005b }
            java.lang.String r12 = r6.toString()     // Catch:{ all -> 0x005b }
            r5.error(r12)     // Catch:{ all -> 0x005b }
            r11.closeDb()     // Catch:{ all -> 0x005b }
            if (r3 == 0) goto L_0x0069
            goto L_0x0066
        L_0x00a1:
            com.amplitude.reactnative.LogcatLogger$Companion r5 = com.amplitude.reactnative.LogcatLogger.Companion     // Catch:{ all -> 0x005b }
            com.amplitude.reactnative.LogcatLogger r5 = r5.getLogger()     // Catch:{ all -> 0x005b }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x005b }
            r6.<init>()     // Catch:{ all -> 0x005b }
            r6.append(r1)     // Catch:{ all -> 0x005b }
            r6.append(r12)     // Catch:{ all -> 0x005b }
            r6.append(r0)     // Catch:{ all -> 0x005b }
            java.lang.String r12 = r4.getMessage()     // Catch:{ all -> 0x005b }
            r6.append(r12)     // Catch:{ all -> 0x005b }
            java.lang.String r12 = r6.toString()     // Catch:{ all -> 0x005b }
            r5.error(r12)     // Catch:{ all -> 0x005b }
            r11.closeDb()     // Catch:{ all -> 0x005b }
            if (r3 == 0) goto L_0x0069
            goto L_0x0066
        L_0x00c9:
            return r2
        L_0x00ca:
            if (r3 == 0) goto L_0x00cf
            r3.close()
        L_0x00cf:
            r11.close()
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.reactnative.LegacyDatabaseStorage.readEventsFromTable(java.lang.String):java.util.List");
    }

    public final synchronized void removeEvent(int i) {
        removeEventFromTable("events", i);
    }

    public final synchronized void removeIdentify(int i) {
        removeEventFromTable("identifys", i);
    }

    public final synchronized void removeInterceptedIdentify(int i) {
        if (this.currentDbVersion >= 4) {
            removeEventFromTable("identify_interceptor", i);
        }
    }

    private final void removeEventFromTable(String str, int i) {
        try {
            getWritableDatabase().delete(str, "id = ?", new String[]{String.valueOf(i)});
        } catch (SQLiteException e) {
            LogcatLogger logger = LogcatLogger.Companion.getLogger();
            logger.error("remove events from " + str + " failed: " + e.getMessage());
            closeDb();
        } catch (StackOverflowError e2) {
            LogcatLogger logger2 = LogcatLogger.Companion.getLogger();
            logger2.error("remove events from " + str + " failed: " + e2.getMessage());
            closeDb();
        } catch (Throwable th) {
            close();
            throw th;
        }
        close();
    }

    public final synchronized String getValue(String str) {
        Intrinsics.checkNotNullParameter(str, "key");
        return (String) getValueFromTable("store", str);
    }

    public final synchronized Long getLongValue(String str) {
        Intrinsics.checkNotNullParameter(str, "key");
        return (Long) getValueFromTable("long_store", str);
    }

    /* JADX WARNING: type inference failed for: r12v14, types: [java.lang.Long] */
    /* JADX WARNING: type inference failed for: r12v16, types: [java.lang.String] */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00c7, code lost:
        if (r13 != null) goto L_0x0057;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0073 A[Catch:{ all -> 0x0042 }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0079 A[Catch:{ all -> 0x0042 }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00a1 A[Catch:{ all -> 0x0042 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00cd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.Object getValueFromTable(java.lang.String r12, java.lang.String r13) {
        /*
            r11 = this;
            java.lang.String r0 = " failed: "
            java.lang.String r1 = "getValue from "
            java.io.File r2 = r11.file
            boolean r2 = r2.exists()
            r3 = 0
            if (r2 != 0) goto L_0x000e
            return r3
        L_0x000e:
            android.database.sqlite.SQLiteDatabase r5 = r11.getReadableDatabase()     // Catch:{ SQLiteException -> 0x006b, StackOverflowError -> 0x0068, IllegalStateException -> 0x0065, RuntimeException -> 0x0062, all -> 0x005f }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)     // Catch:{ SQLiteException -> 0x006b, StackOverflowError -> 0x0068, IllegalStateException -> 0x0065, RuntimeException -> 0x0062, all -> 0x005f }
            java.lang.String r2 = "key"
            java.lang.String r4 = "value"
            java.lang.String[] r7 = new java.lang.String[]{r2, r4}     // Catch:{ SQLiteException -> 0x006b, StackOverflowError -> 0x0068, IllegalStateException -> 0x0065, RuntimeException -> 0x0062, all -> 0x005f }
            java.lang.String r8 = "key = ?"
            java.lang.String[] r9 = new java.lang.String[]{r13}     // Catch:{ SQLiteException -> 0x006b, StackOverflowError -> 0x0068, IllegalStateException -> 0x0065, RuntimeException -> 0x0062, all -> 0x005f }
            r10 = 0
            r4 = r11
            r6 = r12
            android.database.Cursor r13 = r4.queryDb(r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x006b, StackOverflowError -> 0x0068, IllegalStateException -> 0x0065, RuntimeException -> 0x0062, all -> 0x005f }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r13)     // Catch:{ SQLiteException -> 0x004c, StackOverflowError -> 0x004a, IllegalStateException -> 0x0048, RuntimeException -> 0x0046 }
            boolean r2 = r13.moveToFirst()     // Catch:{ SQLiteException -> 0x004c, StackOverflowError -> 0x004a, IllegalStateException -> 0x0048, RuntimeException -> 0x0046 }
            if (r2 == 0) goto L_0x0057
            java.lang.String r2 = "store"
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r2)     // Catch:{ SQLiteException -> 0x004c, StackOverflowError -> 0x004a, IllegalStateException -> 0x0048, RuntimeException -> 0x0046 }
            r4 = 1
            if (r2 == 0) goto L_0x004e
            java.lang.String r12 = r13.getString(r4)     // Catch:{ SQLiteException -> 0x004c, StackOverflowError -> 0x004a, IllegalStateException -> 0x0048, RuntimeException -> 0x0046 }
        L_0x0040:
            r3 = r12
            goto L_0x0057
        L_0x0042:
            r12 = move-exception
            r3 = r13
            goto L_0x00cb
        L_0x0046:
            r12 = move-exception
            goto L_0x006e
        L_0x0048:
            r12 = move-exception
            goto L_0x0074
        L_0x004a:
            r2 = move-exception
            goto L_0x007a
        L_0x004c:
            r2 = move-exception
            goto L_0x00a2
        L_0x004e:
            long r4 = r13.getLong(r4)     // Catch:{ SQLiteException -> 0x004c, StackOverflowError -> 0x004a, IllegalStateException -> 0x0048, RuntimeException -> 0x0046 }
            java.lang.Long r12 = java.lang.Long.valueOf(r4)     // Catch:{ SQLiteException -> 0x004c, StackOverflowError -> 0x004a, IllegalStateException -> 0x0048, RuntimeException -> 0x0046 }
            goto L_0x0040
        L_0x0057:
            r13.close()
        L_0x005a:
            r11.close()
            goto L_0x00ca
        L_0x005f:
            r12 = move-exception
            goto L_0x00cb
        L_0x0062:
            r12 = move-exception
            r13 = r3
            goto L_0x006e
        L_0x0065:
            r12 = move-exception
            r13 = r3
            goto L_0x0074
        L_0x0068:
            r2 = move-exception
            r13 = r3
            goto L_0x007a
        L_0x006b:
            r2 = move-exception
            r13 = r3
            goto L_0x00a2
        L_0x006e:
            r11.convertIfCursorWindowException(r12)     // Catch:{ all -> 0x0042 }
            if (r13 == 0) goto L_0x005a
            goto L_0x0057
        L_0x0074:
            r11.handleIfCursorRowTooLargeException(r12)     // Catch:{ all -> 0x0042 }
            if (r13 == 0) goto L_0x005a
            goto L_0x0057
        L_0x007a:
            com.amplitude.reactnative.LogcatLogger$Companion r4 = com.amplitude.reactnative.LogcatLogger.Companion     // Catch:{ all -> 0x0042 }
            com.amplitude.reactnative.LogcatLogger r4 = r4.getLogger()     // Catch:{ all -> 0x0042 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0042 }
            r5.<init>()     // Catch:{ all -> 0x0042 }
            r5.append(r1)     // Catch:{ all -> 0x0042 }
            r5.append(r12)     // Catch:{ all -> 0x0042 }
            r5.append(r0)     // Catch:{ all -> 0x0042 }
            java.lang.String r12 = r2.getMessage()     // Catch:{ all -> 0x0042 }
            r5.append(r12)     // Catch:{ all -> 0x0042 }
            java.lang.String r12 = r5.toString()     // Catch:{ all -> 0x0042 }
            r4.error(r12)     // Catch:{ all -> 0x0042 }
            r11.closeDb()     // Catch:{ all -> 0x0042 }
            if (r13 == 0) goto L_0x005a
            goto L_0x0057
        L_0x00a2:
            com.amplitude.reactnative.LogcatLogger$Companion r4 = com.amplitude.reactnative.LogcatLogger.Companion     // Catch:{ all -> 0x0042 }
            com.amplitude.reactnative.LogcatLogger r4 = r4.getLogger()     // Catch:{ all -> 0x0042 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0042 }
            r5.<init>()     // Catch:{ all -> 0x0042 }
            r5.append(r1)     // Catch:{ all -> 0x0042 }
            r5.append(r12)     // Catch:{ all -> 0x0042 }
            r5.append(r0)     // Catch:{ all -> 0x0042 }
            java.lang.String r12 = r2.getMessage()     // Catch:{ all -> 0x0042 }
            r5.append(r12)     // Catch:{ all -> 0x0042 }
            java.lang.String r12 = r5.toString()     // Catch:{ all -> 0x0042 }
            r4.error(r12)     // Catch:{ all -> 0x0042 }
            r11.closeDb()     // Catch:{ all -> 0x0042 }
            if (r13 == 0) goto L_0x005a
            goto L_0x0057
        L_0x00ca:
            return r3
        L_0x00cb:
            if (r3 == 0) goto L_0x00d0
            r3.close()
        L_0x00d0:
            r11.close()
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.reactnative.LegacyDatabaseStorage.getValueFromTable(java.lang.String, java.lang.String):java.lang.Object");
    }
}
