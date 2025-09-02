package com.salesforce.marketingcloud.storage.db.upgrades;

import android.annotation.SuppressLint;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.util.c;

@SuppressLint({"Range"})
public class j {
    private static final String a = g.a("Version9ToVersion10");

    private j() {
    }

    private static void a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL("CREATE TABLE device_stats(id INTEGER PRIMARY KEY, type INTEGER, date INTEGER, event_data TEXT, in_transit INTEGER DEFAULT 0, ready_to_send INTEGER DEFAULT 0);");
            sQLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            g.b(a, e, "Unable to create device stats table.", new Object[0]);
        } catch (Throwable th) {
            sQLiteDatabase.endTransaction();
            throw th;
        }
        sQLiteDatabase.endTransaction();
    }

    private static void b(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL("CREATE TABLE triggers (id TEXT PRIMARY KEY, _key TEXT, start_date INTEGER DEFAULT NULL, _trigger TEXT, app_open_count INTEGER DEFAULT 0);");
            sQLiteDatabase.execSQL("CREATE TABLE in_app_messages(id TEXT PRIMARY KEY, priority INTEGER DEFAULT 999, start_date DATETIME, end_date DATETIME, modified_date DATETIME, display_limit INTEGER DEFAULT 1, media_url TEXT DEFAULT NULL, message_json TEXT);");
            sQLiteDatabase.execSQL("CREATE TABLE iam_state(id TEXT PRIMARY KEY, display_count integer DEFAULT 0, FOREIGN KEY (id) REFERENCES in_app_messages(id) ON DELETE CASCADE);");
            sQLiteDatabase.execSQL("CREATE TRIGGER iam_state_init AFTER INSERT ON in_app_messages BEGIN INSERT INTO iam_state (id) VALUES (NEW.id); END;");
            sQLiteDatabase.execSQL("CREATE VIEW iam_view AS SELECT in_app_messages.id,in_app_messages.priority,in_app_messages.start_date,in_app_messages.end_date,in_app_messages.modified_date,in_app_messages.display_limit,in_app_messages.message_json,iam_state.display_count FROM in_app_messages INNER JOIN iam_state ON iam_state.id = in_app_messages.id;");
            sQLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            g.b(a, e, "Unable to create in app message table", new Object[0]);
        } catch (Throwable th) {
            sQLiteDatabase.endTransaction();
            throw th;
        }
        sQLiteDatabase.endTransaction();
    }

    private static void c(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL("CREATE TABLE region_messages ( region_id TEXT, message_id TEXT, FOREIGN KEY (region_id) REFERENCES regions(id) ON DELETE CASCADE, PRIMARY KEY (region_id, message_id));");
            sQLiteDatabase.execSQL("INSERT INTO region_messages SELECT region_id,message_id FROM region_message;");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS region_message");
            sQLiteDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
            g.b(a, e, "Unable to create region_messages table and migrate data from region_message.", new Object[0]);
            try {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS region_messages");
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS region_message");
                sQLiteDatabase.execSQL("CREATE TABLE region_messages ( region_id TEXT, message_id TEXT, FOREIGN KEY (region_id) REFERENCES regions(id) ON DELETE CASCADE, PRIMARY KEY (region_id, message_id));");
                sQLiteDatabase.setTransactionSuccessful();
            } catch (SQLException e2) {
                g.b(a, e2, "Unable to create region_messages table", new Object[0]);
            }
        } catch (Throwable th) {
            sQLiteDatabase.endTransaction();
            throw th;
        }
        sQLiteDatabase.endTransaction();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x006f, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0070, code lost:
        r1 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0074, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0159, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x015a, code lost:
        r1 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x015f, code lost:
        r20 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0162, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0163, code lost:
        r19 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x020d, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x020e, code lost:
        r1 = r21;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x0229 A[EDGE_INSN: B:104:0x0229->B:68:0x0229 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x006f A[Catch:{ Exception -> 0x0074, all -> 0x006f }, ExcHandler: all (th java.lang.Throwable), Splitter:B:8:0x0040] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x01c2 A[Catch:{ Exception -> 0x0159, all -> 0x006f }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x01c9 A[Catch:{ Exception -> 0x0159, all -> 0x006f }] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x022a A[Catch:{ Exception -> 0x0233 }, LOOP:0: B:6:0x0036->B:69:0x022a, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0263 A[Catch:{ Exception -> 0x0288, all -> 0x0286 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(android.database.sqlite.SQLiteDatabase r21, com.salesforce.marketingcloud.util.c r22) {
        /*
            r1 = r21
            r2 = r22
            java.lang.String r3 = "message_hash"
            java.lang.String r4 = "sound"
            java.lang.String r5 = "alert"
            java.lang.String r6 = "title"
            java.lang.String r7 = "keys"
            java.lang.String r8 = "custom"
            java.lang.String r9 = "subject"
            java.lang.String r10 = "url"
            java.lang.String r11 = "end_date"
            java.lang.String r12 = "start_date"
            java.lang.String r13 = "id"
            r15 = 0
            r21.beginTransaction()     // Catch:{ Exception -> 0x023b }
            java.lang.String r0 = "DROP TABLE IF EXISTS inbox_messages;"
            r1.execSQL(r0)     // Catch:{ Exception -> 0x023b }
            java.lang.String r0 = "CREATE TABLE inbox_messages(id TEXT PRIMARY KEY, start_date INTEGER DEFAULT NULL, end_date INTEGER DEFAULT NULL, is_deleted INTEGER DEFAULT 0, is_read INTEGER DEFAULT 0, is_dirty INTEGER DEFAULT 0, message_hash TEXT DEFAULT NULL, message_json TEXT);"
            r1.execSQL(r0)     // Catch:{ Exception -> 0x023b }
            java.lang.String r0 = "SELECT * FROM cloud_page_messages"
            android.database.Cursor r14 = r1.rawQuery(r0, r15)     // Catch:{ Exception -> 0x023b }
            if (r14 == 0) goto L_0x023f
            boolean r0 = r14.moveToFirst()     // Catch:{ Exception -> 0x023b }
            if (r0 == 0) goto L_0x0235
        L_0x0036:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Exception -> 0x0217 }
            r0.<init>()     // Catch:{ Exception -> 0x0217 }
            android.content.ContentValues r15 = new android.content.ContentValues     // Catch:{ Exception -> 0x0217 }
            r15.<init>()     // Catch:{ Exception -> 0x0217 }
            int r1 = r14.getColumnIndex(r13)     // Catch:{ Exception -> 0x020d, all -> 0x006f }
            java.lang.String r1 = r14.getString(r1)     // Catch:{ Exception -> 0x020d, all -> 0x006f }
            r15.put(r13, r1)     // Catch:{ Exception -> 0x020d, all -> 0x006f }
            r0.put(r13, r1)     // Catch:{ Exception -> 0x020d, all -> 0x006f }
            int r1 = r14.getColumnIndex(r12)     // Catch:{ Exception -> 0x020d, all -> 0x006f }
            java.lang.String r1 = r14.getString(r1)     // Catch:{ Exception -> 0x020d, all -> 0x006f }
            if (r1 == 0) goto L_0x0081
            java.util.Date r16 = com.salesforce.marketingcloud.util.l.f(r1)     // Catch:{ Exception -> 0x007d, all -> 0x006f }
            long r16 = r16.getTime()     // Catch:{ Exception -> 0x007d, all -> 0x006f }
            r18 = r13
            java.lang.Long r13 = java.lang.Long.valueOf(r16)     // Catch:{ Exception -> 0x0074, all -> 0x006f }
            r15.put(r12, r13)     // Catch:{ Exception -> 0x0074, all -> 0x006f }
            java.lang.String r13 = "startDateUtc"
            r0.put(r13, r1)     // Catch:{ Exception -> 0x0074, all -> 0x006f }
            goto L_0x0083
        L_0x006f:
            r0 = move-exception
            r1 = r21
            goto L_0x02a8
        L_0x0074:
            r0 = move-exception
        L_0x0075:
            r1 = r21
            r19 = r8
            r20 = r9
            goto L_0x0219
        L_0x007d:
            r0 = move-exception
            r18 = r13
            goto L_0x0075
        L_0x0081:
            r18 = r13
        L_0x0083:
            int r1 = r14.getColumnIndex(r11)     // Catch:{ Exception -> 0x0074, all -> 0x006f }
            java.lang.String r1 = r14.getString(r1)     // Catch:{ Exception -> 0x0074, all -> 0x006f }
            if (r1 == 0) goto L_0x00a1
            java.util.Date r13 = com.salesforce.marketingcloud.util.l.f(r1)     // Catch:{ Exception -> 0x0074, all -> 0x006f }
            long r16 = r13.getTime()     // Catch:{ Exception -> 0x0074, all -> 0x006f }
            java.lang.Long r13 = java.lang.Long.valueOf(r16)     // Catch:{ Exception -> 0x0074, all -> 0x006f }
            r15.put(r11, r13)     // Catch:{ Exception -> 0x0074, all -> 0x006f }
            java.lang.String r13 = "endDateUtc"
            r0.put(r13, r1)     // Catch:{ Exception -> 0x0074, all -> 0x006f }
        L_0x00a1:
            java.lang.String r1 = "messageType"
            r13 = 8
            r0.put(r1, r13)     // Catch:{ Exception -> 0x0074, all -> 0x006f }
            java.lang.String r1 = "contentType"
            r13 = 2
            r0.put(r1, r13)     // Catch:{ Exception -> 0x0074, all -> 0x006f }
            int r1 = r14.getColumnIndex(r10)     // Catch:{ Exception -> 0x0074, all -> 0x006f }
            java.lang.String r1 = r14.getString(r1)     // Catch:{ Exception -> 0x0074, all -> 0x006f }
            java.lang.String r1 = r2.b(r1)     // Catch:{ Exception -> 0x0074, all -> 0x006f }
            r0.put(r10, r1)     // Catch:{ Exception -> 0x0074, all -> 0x006f }
            int r1 = r14.getColumnIndex(r9)     // Catch:{ Exception -> 0x0074, all -> 0x006f }
            java.lang.String r1 = r14.getString(r1)     // Catch:{ Exception -> 0x0074, all -> 0x006f }
            java.lang.String r1 = r2.b(r1)     // Catch:{ Exception -> 0x0074, all -> 0x006f }
            r0.put(r9, r1)     // Catch:{ Exception -> 0x0074, all -> 0x006f }
            java.lang.String r1 = "read"
            int r1 = r14.getColumnIndex(r1)     // Catch:{ Exception -> 0x0074, all -> 0x006f }
            int r1 = r14.getInt(r1)     // Catch:{ Exception -> 0x0074, all -> 0x006f }
            java.lang.String r13 = "is_read"
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ Exception -> 0x0074, all -> 0x006f }
            r15.put(r13, r1)     // Catch:{ Exception -> 0x0074, all -> 0x006f }
            java.lang.String r1 = "message_deleted"
            int r1 = r14.getColumnIndex(r1)     // Catch:{ Exception -> 0x0074, all -> 0x006f }
            int r1 = r14.getInt(r1)     // Catch:{ Exception -> 0x0074, all -> 0x006f }
            java.lang.String r13 = "is_deleted"
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ Exception -> 0x0074, all -> 0x006f }
            r15.put(r13, r1)     // Catch:{ Exception -> 0x0074, all -> 0x006f }
            int r1 = r14.getColumnIndex(r8)     // Catch:{ Exception -> 0x0074, all -> 0x006f }
            java.lang.String r1 = r14.getString(r1)     // Catch:{ Exception -> 0x0074, all -> 0x006f }
            java.lang.String r1 = r2.b(r1)     // Catch:{ Exception -> 0x0074, all -> 0x006f }
            r0.put(r8, r1)     // Catch:{ Exception -> 0x0074, all -> 0x006f }
            int r1 = r14.getColumnIndex(r7)     // Catch:{ Exception -> 0x0074, all -> 0x006f }
            java.lang.String r1 = r14.getString(r1)     // Catch:{ Exception -> 0x0074, all -> 0x006f }
            java.lang.String r1 = r2.b(r1)     // Catch:{ Exception -> 0x0074, all -> 0x006f }
            if (r1 == 0) goto L_0x016e
            java.util.Map r1 = com.salesforce.marketingcloud.util.l.c((java.lang.String) r1)     // Catch:{ Exception -> 0x0162, all -> 0x006f }
            boolean r13 = r1.isEmpty()     // Catch:{ Exception -> 0x0162, all -> 0x006f }
            if (r13 != 0) goto L_0x016e
            org.json.JSONArray r13 = new org.json.JSONArray     // Catch:{ Exception -> 0x0162, all -> 0x006f }
            r13.<init>()     // Catch:{ Exception -> 0x0162, all -> 0x006f }
            java.util.Set r1 = r1.entrySet()     // Catch:{ Exception -> 0x0162, all -> 0x006f }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ Exception -> 0x0162, all -> 0x006f }
        L_0x0126:
            boolean r16 = r1.hasNext()     // Catch:{ Exception -> 0x0162, all -> 0x006f }
            if (r16 == 0) goto L_0x0166
            java.lang.Object r16 = r1.next()     // Catch:{ Exception -> 0x0162, all -> 0x006f }
            java.util.Map$Entry r16 = (java.util.Map.Entry) r16     // Catch:{ Exception -> 0x0162, all -> 0x006f }
            r17 = r1
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Exception -> 0x0162, all -> 0x006f }
            r1.<init>()     // Catch:{ Exception -> 0x0162, all -> 0x006f }
            r19 = r8
            java.lang.String r8 = "key"
            r20 = r9
            java.lang.Object r9 = r16.getKey()     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            r1.put(r8, r9)     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            java.lang.String r8 = "value"
            java.lang.Object r9 = r16.getValue()     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            r1.put(r8, r9)     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            r13.put(r1)     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            r1 = r17
            r8 = r19
            r9 = r20
            goto L_0x0126
        L_0x0159:
            r0 = move-exception
        L_0x015a:
            r1 = r21
            goto L_0x0219
        L_0x015e:
            r0 = move-exception
        L_0x015f:
            r20 = r9
            goto L_0x015a
        L_0x0162:
            r0 = move-exception
            r19 = r8
            goto L_0x015f
        L_0x0166:
            r19 = r8
            r20 = r9
            r0.put(r7, r13)     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            goto L_0x0172
        L_0x016e:
            r19 = r8
            r20 = r9
        L_0x0172:
            int r1 = r14.getColumnIndex(r6)     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            java.lang.String r1 = r14.getString(r1)     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            java.lang.String r1 = r2.b(r1)     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            r0.put(r6, r1)     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            int r1 = r14.getColumnIndex(r5)     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            java.lang.String r1 = r14.getString(r1)     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            java.lang.String r1 = r2.b(r1)     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            r0.put(r5, r1)     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            int r1 = r14.getColumnIndex(r4)     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            java.lang.String r1 = r14.getString(r1)     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            r0.put(r4, r1)     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            java.lang.String r1 = "mediaUrl"
            int r1 = r14.getColumnIndex(r1)     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            java.lang.String r1 = r14.getString(r1)     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            java.lang.String r1 = r2.b(r1)     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            java.lang.String r8 = "mediaAlt"
            int r8 = r14.getColumnIndex(r8)     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            java.lang.String r8 = r14.getString(r8)     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            java.lang.String r8 = r2.b(r8)     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            if (r1 != 0) goto L_0x01bb
            if (r8 == 0) goto L_0x01d3
        L_0x01bb:
            org.json.JSONObject r9 = new org.json.JSONObject     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            r9.<init>()     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            if (r1 == 0) goto L_0x01c7
            java.lang.String r13 = "androidUrl"
            r9.put(r13, r1)     // Catch:{ Exception -> 0x0159, all -> 0x006f }
        L_0x01c7:
            if (r8 == 0) goto L_0x01ce
            java.lang.String r1 = "alt"
            r9.put(r1, r8)     // Catch:{ Exception -> 0x0159, all -> 0x006f }
        L_0x01ce:
            java.lang.String r1 = "media"
            r0.put(r1, r9)     // Catch:{ Exception -> 0x0159, all -> 0x006f }
        L_0x01d3:
            int r1 = r14.getColumnIndex(r3)     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            java.lang.String r1 = r14.getString(r1)     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            r15.put(r3, r1)     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            java.lang.String r8 = "hash"
            r0.put(r8, r1)     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            java.lang.String r1 = "requestId"
            java.lang.String r8 = "request_id"
            int r8 = r14.getColumnIndex(r8)     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            java.lang.String r8 = r14.getString(r8)     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            r0.put(r1, r8)     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            java.lang.String r1 = "message_json"
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            java.lang.String r0 = r2.a(r0)     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            r15.put(r1, r0)     // Catch:{ Exception -> 0x0159, all -> 0x006f }
            java.lang.String r0 = "inbox_messages"
            r8 = 0
            r1 = r21
            r1.insert(r0, r8, r15)     // Catch:{ Exception -> 0x020b }
            goto L_0x0223
        L_0x0208:
            r0 = move-exception
            goto L_0x02a8
        L_0x020b:
            r0 = move-exception
            goto L_0x0219
        L_0x020d:
            r0 = move-exception
            r1 = r21
        L_0x0210:
            r19 = r8
            r20 = r9
            r18 = r13
            goto L_0x0219
        L_0x0217:
            r0 = move-exception
            goto L_0x0210
        L_0x0219:
            java.lang.String r8 = a     // Catch:{ Exception -> 0x0233 }
            java.lang.String r9 = "Unable to update Inbox message."
            r13 = 0
            java.lang.Object[] r15 = new java.lang.Object[r13]     // Catch:{ Exception -> 0x0233 }
            com.salesforce.marketingcloud.g.b(r8, r0, r9, r15)     // Catch:{ Exception -> 0x0233 }
        L_0x0223:
            boolean r0 = r14.moveToNext()     // Catch:{ Exception -> 0x0233 }
            if (r0 != 0) goto L_0x022a
            goto L_0x0237
        L_0x022a:
            r13 = r18
            r8 = r19
            r9 = r20
            r15 = 0
            goto L_0x0036
        L_0x0233:
            r0 = move-exception
            goto L_0x0245
        L_0x0235:
            r18 = r13
        L_0x0237:
            r14.close()     // Catch:{ Exception -> 0x0233 }
            goto L_0x0241
        L_0x023b:
            r0 = move-exception
            r18 = r13
            goto L_0x0245
        L_0x023f:
            r18 = r13
        L_0x0241:
            r21.setTransactionSuccessful()     // Catch:{ Exception -> 0x0233 }
            goto L_0x024f
        L_0x0245:
            java.lang.String r2 = a     // Catch:{ all -> 0x0208 }
            java.lang.String r3 = "Unable to update any Inbox messages."
            r4 = 0
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ all -> 0x0208 }
            com.salesforce.marketingcloud.g.b(r2, r0, r3, r5)     // Catch:{ all -> 0x0208 }
        L_0x024f:
            r21.endTransaction()
            java.lang.String r0 = "DROP TABLE IF EXISTS cloud_page_messages"
            r1.execSQL(r0)
            r21.beginTransaction()
            java.lang.String r0 = "SELECT id FROM inbox_message_status"
            r2 = 0
            android.database.Cursor r0 = r1.rawQuery(r0, r2)     // Catch:{ Exception -> 0x0288 }
            if (r0 == 0) goto L_0x028d
            boolean r2 = r0.moveToFirst()     // Catch:{ Exception -> 0x0288 }
            if (r2 == 0) goto L_0x028a
        L_0x0269:
            java.lang.String r2 = "UPDATE inbox_messages SET is_dirty=1 WHERE id=?"
            r3 = r18
            int r4 = r0.getColumnIndex(r3)     // Catch:{ Exception -> 0x0288 }
            java.lang.String r4 = r0.getString(r4)     // Catch:{ Exception -> 0x0288 }
            java.lang.String[] r4 = new java.lang.String[]{r4}     // Catch:{ Exception -> 0x0288 }
            r1.execSQL(r2, r4)     // Catch:{ Exception -> 0x0288 }
            boolean r2 = r0.moveToNext()     // Catch:{ Exception -> 0x0288 }
            if (r2 != 0) goto L_0x0283
            goto L_0x028a
        L_0x0283:
            r18 = r3
            goto L_0x0269
        L_0x0286:
            r0 = move-exception
            goto L_0x02a4
        L_0x0288:
            r0 = move-exception
            goto L_0x0291
        L_0x028a:
            r0.close()     // Catch:{ Exception -> 0x0288 }
        L_0x028d:
            r21.setTransactionSuccessful()     // Catch:{ Exception -> 0x0288 }
            goto L_0x029b
        L_0x0291:
            java.lang.String r2 = a     // Catch:{ all -> 0x0286 }
            java.lang.String r3 = "Unable to update inbox status values"
            r4 = 0
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x0286 }
            com.salesforce.marketingcloud.g.b(r2, r0, r3, r4)     // Catch:{ all -> 0x0286 }
        L_0x029b:
            r21.endTransaction()
            java.lang.String r0 = "DROP TABLE IF EXISTS inbox_message_status"
            r1.execSQL(r0)
            return
        L_0x02a4:
            r21.endTransaction()
            throw r0
        L_0x02a8:
            r21.endTransaction()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.storage.db.upgrades.j.a(android.database.sqlite.SQLiteDatabase, com.salesforce.marketingcloud.util.c):void");
    }

    public static void b(SQLiteDatabase sQLiteDatabase, c cVar) {
        a(sQLiteDatabase, cVar);
        b(sQLiteDatabase);
        a(sQLiteDatabase);
        c(sQLiteDatabase);
    }
}
