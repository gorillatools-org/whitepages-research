package com.salesforce.marketingcloud.storage.db.upgrades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.storage.db.a;
import com.salesforce.marketingcloud.util.c;
import java.util.ArrayList;
import java.util.Collection;

public final class b {
    static final String a = "geofence_request";
    static final String b = "beacon_request";
    private static final String c = g.a("Version1ToVersion2");

    private static class a {
        final String a;
        final String b;

        a(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            String str = this.a;
            if (str == null && aVar.a == null) {
                return true;
            }
            return str.equalsIgnoreCase(aVar.a);
        }

        public int hashCode() {
            return this.a.toLowerCase().hashCode();
        }
    }

    private b() {
    }

    private static void a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL("ALTER TABLE analytic_item RENAME TO " + "old_analytic_item");
            sQLiteDatabase.execSQL("CREATE TABLE analytic_item (id INTEGER PRIMARY KEY AUTOINCREMENT, event_date VARCHAR, analytic_product_type INTEGER, analytic_types VARCHAR, value INTEGER, ready_to_send SMALLINT, object_ids VARCHAR, json_payload VARCHAR)");
            Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT * FROM " + "old_analytic_item", (String[]) null);
            if (rawQuery != null) {
                if (rawQuery.moveToFirst()) {
                    do {
                        ContentValues contentValues = new ContentValues();
                        try {
                            contentValues.put("id", Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex("id"))));
                            contentValues.put(a.C0034a.c, rawQuery.getString(rawQuery.getColumnIndex(a.C0034a.c)));
                            contentValues.put(a.C0034a.i, Integer.valueOf(TextUtils.isEmpty(rawQuery.getString(rawQuery.getColumnIndex("pi_app_key"))) ^ true ? 1 : 0));
                            contentValues.put("analytic_types", rawQuery.getString(rawQuery.getColumnIndex("analytic_types")));
                            contentValues.put("value", Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex("value"))));
                            contentValues.put(a.C0034a.f, Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex(a.C0034a.f))));
                            contentValues.put(a.C0034a.e, rawQuery.getString(rawQuery.getColumnIndex(a.C0034a.e)));
                            contentValues.put("json_payload", rawQuery.getString(rawQuery.getColumnIndex("json_payload")));
                            sQLiteDatabase.insert(com.salesforce.marketingcloud.storage.db.a.e, (String) null, contentValues);
                        } catch (Exception e) {
                            g.b(c, e, "Failed to update item in Analytics local storage during upgrade.", new Object[0]);
                        }
                    } while (rawQuery.moveToNext());
                }
                rawQuery.close();
            }
            sQLiteDatabase.execSQL("DROP TABLE " + "old_analytic_item");
            sQLiteDatabase.setTransactionSuccessful();
        } catch (SQLException e2) {
            g.b(c, e2, "Failed to upgrade Analytics local storage.  Starting fresh.  Some analytics items may have been lost.", new Object[0]);
            try {
                sQLiteDatabase.execSQL("CREATE TABLE analytic_item (id INTEGER PRIMARY KEY AUTOINCREMENT, event_date VARCHAR, analytic_product_type INTEGER, analytic_type INTEGER, value INTEGER, ready_to_send SMALLINT, object_ids VARCHAR, json_payload VARCHAR, request_id VARCHAR);");
                sQLiteDatabase.setTransactionSuccessful();
            } catch (SQLException e3) {
                g.b(c, e3, "Failed to create local storage for Analytics.", new Object[0]);
            }
        } catch (Throwable th) {
            sQLiteDatabase.endTransaction();
            throw th;
        }
        sQLiteDatabase.endTransaction();
    }

    private static void b(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("DELETE FROM messages WHERE message_type NOT IN ( 5, 3, 4 )");
        } catch (Exception e) {
            g.b(c, e, "Unable to clean unused messages from db.", new Object[0]);
        }
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL("ALTER TABLE messages RENAME TO " + "old_messages");
            sQLiteDatabase.execSQL("CREATE TABLE messages (id VARCHAR PRIMARY KEY, alert VARCHAR, sound VARCHAR, open_direct VARCHAR, start_date VARCHAR, end_date VARCHAR, message_type INTEGER, content_type INTEGER, url VARCHAR, custom VARCHAR, keys VARCHAR, period_show_count INTEGER, last_shown_date VARCHAR, next_allowed_show VARCHAR, show_count INTEGER, message_limit INTEGER, rolling_period SMALLINT, period_type INTEGER, number_of_periods INTEGER, messages_per_period INTEGER, proximity INTEGER, has_entered SMALLINT, notify_id INTEGER );");
            sQLiteDatabase.execSQL("INSERT INTO messages SELECT id, alert, sound, open_direct, start_date, end_date, message_type, content_type, url, custom, keys, period_show_count, last_shown_date, next_allowed_show, show_count, message_limit, rolling_period, period_type, number_of_periods, messages_per_period, proximity, has_entered, notify_id FROM " + "old_messages");
            sQLiteDatabase.execSQL("DROP TABLE " + "old_messages");
            sQLiteDatabase.setTransactionSuccessful();
        } catch (Exception e2) {
            sQLiteDatabase.endTransaction();
            try {
                sQLiteDatabase.beginTransaction();
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS messages");
                sQLiteDatabase.execSQL("CREATE TABLE messages (id VARCHAR PRIMARY KEY, alert VARCHAR, sound VARCHAR, open_direct VARCHAR, start_date VARCHAR, end_date VARCHAR, message_type INTEGER, content_type INTEGER, url VARCHAR, custom VARCHAR, keys VARCHAR, period_show_count INTEGER, last_shown_date VARCHAR, next_allowed_show VARCHAR, show_count INTEGER, message_limit INTEGER, rolling_period SMALLINT, period_type INTEGER, number_of_periods INTEGER, messages_per_period INTEGER, proximity INTEGER, has_entered SMALLINT, notify_id INTEGER );");
                sQLiteDatabase.setTransactionSuccessful();
            } catch (Exception e3) {
                g.b(c, e3, "Unable a create message table.", new Object[0]);
            }
            g.b(c, e2, "Unable to update message table", new Object[0]);
        } catch (Throwable th) {
            sQLiteDatabase.endTransaction();
            throw th;
        }
        sQLiteDatabase.endTransaction();
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x0018 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void c(android.database.sqlite.SQLiteDatabase r3) {
        /*
            java.lang.String r0 = "CREATE TABLE regions (id VARCHAR PRIMARY KEY, latitude VARCHAR, longitude VARCHAR, radius INTEGER, beacon_guid VARCHAR, beacon_major INTEGER, beacon_minor INTEGER, description VARCHAR, name VARCHAR, location_type INTEGER );"
            java.lang.String r1 = "DROP TABLE IF EXISTS regions"
            r3.beginTransaction()     // Catch:{ SQLException -> 0x0018 }
            r3.execSQL(r1)     // Catch:{ SQLException -> 0x0018 }
            r3.execSQL(r0)     // Catch:{ SQLException -> 0x0018 }
            java.lang.String r2 = "DELETE FROM region_message"
            r3.execSQL(r2)     // Catch:{ SQLException -> 0x0018 }
            r3.setTransactionSuccessful()     // Catch:{ SQLException -> 0x0018 }
            goto L_0x002b
        L_0x0016:
            r0 = move-exception
            goto L_0x002f
        L_0x0018:
            r3.execSQL(r1)     // Catch:{ all -> 0x0016 }
            java.lang.String r1 = "DROP TABLE IF EXISTS region_message"
            r3.execSQL(r1)     // Catch:{ all -> 0x0016 }
            r3.execSQL(r0)     // Catch:{ all -> 0x0016 }
            java.lang.String r0 = "CREATE TABLE region_message (id INTEGER PRIMARY KEY AUTOINCREMENT, region_id VARCHAR, message_id VARCHAR );"
            r3.execSQL(r0)     // Catch:{ all -> 0x0016 }
            r3.setTransactionSuccessful()     // Catch:{ all -> 0x0016 }
        L_0x002b:
            r3.endTransaction()
            return
        L_0x002f:
            r3.endTransaction()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.storage.db.upgrades.b.c(android.database.sqlite.SQLiteDatabase):void");
    }

    private static void d(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL("CREATE TABLE location_table (id INTEGER PRIMARY KEY CHECK (id = 0), latitude VARCHAR, longitude VARCHAR );");
            sQLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            g.b(c, e, "Unable to create location table", new Object[0]);
        } catch (Throwable th) {
            sQLiteDatabase.endTransaction();
            throw th;
        }
        sQLiteDatabase.endTransaction();
        try {
            sQLiteDatabase.execSQL("DROP TABLE beacon_request");
            sQLiteDatabase.execSQL("DROP TABLE geofence_request");
        } catch (Exception e2) {
            g.b(c, e2, "Unable to drop unused request tables", new Object[0]);
        }
    }

    private static void e(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL("CREATE TABLE cloud_page_messages (id VARCHAR PRIMARY KEY, start_date VARCHAR, end_date VARCHAR, message_type INTEGER, content_type INTEGER, url VARCHAR, subject VARCHAR, read SMALLINT, message_deleted SMALLINT)");
            sQLiteDatabase.execSQL("INSERT INTO cloud_page_messages SELECT id,start_date,end_date,message_type,content_type,url,subject,read,message_deleted FROM messages WHERE message_type=1 AND content_type=2");
            sQLiteDatabase.execSQL("DELETE FROM messages WHERE message_type=1 AND content_type=2");
            sQLiteDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
            g.b(c, e, "Failed to move Messages to CloudPage Messages table.", new Object[0]);
            try {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS cloud_page_messages");
                sQLiteDatabase.execSQL("CREATE TABLE cloud_page_messages (id VARCHAR PRIMARY KEY, start_date VARCHAR, end_date VARCHAR, message_type INTEGER, content_type INTEGER, url VARCHAR, subject VARCHAR, read SMALLINT, message_deleted SMALLINT)");
                sQLiteDatabase.setTransactionSuccessful();
            } catch (Exception e2) {
                g.b(c, e2, "Could not create cloud_page_messages table.", new Object[0]);
            }
        } catch (Throwable th) {
            sQLiteDatabase.endTransaction();
            throw th;
        }
        sQLiteDatabase.endTransaction();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v1, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v10, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v13, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v24, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v25, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v26, resolved type: android.database.Cursor} */
    /* JADX WARNING: type inference failed for: r10v11, types: [java.lang.Throwable, java.lang.Exception] */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00a5, code lost:
        if (r2 != false) goto L_0x00a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00a7, code lost:
        r10 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r2 = a(r11.b(r10.getString(r10.getColumnIndex(com.salesforce.marketingcloud.storage.db.k.a.h))));
        r3 = new androidx.collection.ArraySet();
        r10 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00c0, code lost:
        if (r2.isEmpty() != false) goto L_0x0109;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00c2, code lost:
        r4 = r2.size() - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00c8, code lost:
        if (r4 < 0) goto L_0x00da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00ca, code lost:
        r3.add(r2.get(r4));
        r4 = r4 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00da, code lost:
        r2 = new android.content.ContentValues();
        r2.put(com.salesforce.marketingcloud.storage.db.k.a.h, r11.a(a((java.util.Collection<com.salesforce.marketingcloud.storage.db.upgrades.b.a>) r3)));
        r9.update(com.salesforce.marketingcloud.storage.db.k.e, r2, "id=?", new java.lang.String[]{r10.getString(r10.getColumnIndex("id"))});
        r10 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x010d, code lost:
        if (r2 == false) goto L_0x010f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x010f, code lost:
        r10.close();
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(android.database.sqlite.SQLiteDatabase r9, android.content.Context r10, com.salesforce.marketingcloud.util.c r11) {
        /*
            java.lang.String r0 = "attributes"
            java.lang.String r1 = "registration"
            java.lang.String r2 = "CREATE TABLE registration (id INTEGER PRIMARY KEY AUTOINCREMENT, platform VARCHAR, subscriber_key VARCHAR, et_app_id VARCHAR, badge INTEGER, timezone INTEGER, dst SMALLINT, tags VARCHAR, attributes VARCHAR, platform_version VARCHAR, push_enabled SMALLINT, location_enabled SMALLINT, hwid VARCHAR, gcm_sender_id VARCHAR, system_token VARCHAR, device_id VARCHAR, app_version VARCHAR, sdk_version VARCHAR, locale VARCHAR )"
            java.lang.String r3 = "id,platform,subscriber_key,et_app_id,badge,timezone,dst,tags,attributes,platform_version,push_enabled,location_enabled,hwid,gcm_sender_id,locale"
            r4 = 0
            r9.beginTransaction()     // Catch:{ SQLException -> 0x0067 }
            java.lang.String r5 = "ALTER TABLE registration RENAME TO old_registration"
            r9.execSQL(r5)     // Catch:{ SQLException -> 0x0067 }
            r9.execSQL(r2)     // Catch:{ SQLException -> 0x0067 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ SQLException -> 0x0067 }
            r5.<init>()     // Catch:{ SQLException -> 0x0067 }
            java.lang.String r6 = "INSERT INTO registration ("
            r5.append(r6)     // Catch:{ SQLException -> 0x0067 }
            r5.append(r3)     // Catch:{ SQLException -> 0x0067 }
            java.lang.String r6 = ") SELECT "
            r5.append(r6)     // Catch:{ SQLException -> 0x0067 }
            r5.append(r3)     // Catch:{ SQLException -> 0x0067 }
            java.lang.String r3 = " FROM old_registration"
            r5.append(r3)     // Catch:{ SQLException -> 0x0067 }
            java.lang.String r3 = r5.toString()     // Catch:{ SQLException -> 0x0067 }
            r9.execSQL(r3)     // Catch:{ SQLException -> 0x0067 }
            long r5 = android.database.DatabaseUtils.queryNumEntries(r9, r1)     // Catch:{ SQLException -> 0x0067 }
            r7 = 0
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 <= 0) goto L_0x0069
            android.content.ContentValues r3 = new android.content.ContentValues     // Catch:{ SQLException -> 0x0067 }
            r5 = 3
            r3.<init>(r5)     // Catch:{ SQLException -> 0x0067 }
            java.lang.String r5 = "device_id"
            java.lang.String r6 = com.salesforce.marketingcloud.util.e.a((android.content.Context) r10, (java.lang.String) r4)     // Catch:{ SQLException -> 0x0067 }
            r3.put(r5, r6)     // Catch:{ SQLException -> 0x0067 }
            java.lang.String r5 = "app_version"
            java.lang.String r10 = com.salesforce.marketingcloud.util.h.a(r10)     // Catch:{ SQLException -> 0x0067 }
            r3.put(r5, r10)     // Catch:{ SQLException -> 0x0067 }
            java.lang.String r10 = "sdk_version"
            java.lang.String r5 = com.salesforce.marketingcloud.MarketingCloudSdk.getSdkVersionName()     // Catch:{ SQLException -> 0x0067 }
            r3.put(r10, r5)     // Catch:{ SQLException -> 0x0067 }
            r9.update(r1, r3, r4, r4)     // Catch:{ SQLException -> 0x0067 }
            goto L_0x0069
        L_0x0064:
            r10 = move-exception
            goto L_0x011d
        L_0x0067:
            r10 = move-exception
            goto L_0x0075
        L_0x0069:
            java.lang.String r10 = "DROP TABLE old_registration"
            r9.execSQL(r10)     // Catch:{ SQLException -> 0x0067 }
            r9.setTransactionSuccessful()     // Catch:{ SQLException -> 0x0067 }
        L_0x0071:
            r9.endTransaction()
            goto L_0x0096
        L_0x0075:
            java.lang.String r3 = c     // Catch:{ all -> 0x0064 }
            java.lang.String r5 = "Unable to update registration table"
            r6 = 0
            java.lang.Object[] r7 = new java.lang.Object[r6]     // Catch:{ all -> 0x0064 }
            com.salesforce.marketingcloud.g.b(r3, r10, r5, r7)     // Catch:{ all -> 0x0064 }
            java.lang.String r10 = "DROP TABLE IF EXISTS registration"
            r9.execSQL(r10)     // Catch:{ Exception -> 0x008b }
            r9.execSQL(r2)     // Catch:{ Exception -> 0x008b }
            r9.setTransactionSuccessful()     // Catch:{ Exception -> 0x008b }
            goto L_0x0071
        L_0x008b:
            r10 = move-exception
            java.lang.String r2 = c     // Catch:{ all -> 0x0064 }
            java.lang.String r3 = "Unable to create registration table"
            java.lang.Object[] r5 = new java.lang.Object[r6]     // Catch:{ all -> 0x0064 }
            com.salesforce.marketingcloud.g.b(r2, r10, r3, r5)     // Catch:{ all -> 0x0064 }
            goto L_0x0071
        L_0x0096:
            r9.beginTransaction()     // Catch:{ all -> 0x00d6 }
            java.lang.String r10 = "SELECT id, attributes FROM registration"
            android.database.Cursor r10 = r9.rawQuery(r10, r4)     // Catch:{ all -> 0x00d6 }
            if (r10 == 0) goto L_0x0112
            boolean r2 = r10.moveToFirst()     // Catch:{ all -> 0x00d6 }
            if (r2 == 0) goto L_0x010f
        L_0x00a7:
            int r2 = r10.getColumnIndex(r0)     // Catch:{ Exception -> 0x00d8 }
            java.lang.String r2 = r10.getString(r2)     // Catch:{ Exception -> 0x00d8 }
            java.lang.String r2 = r11.b(r2)     // Catch:{ Exception -> 0x00d8 }
            java.util.ArrayList r2 = a((java.lang.String) r2)     // Catch:{ Exception -> 0x00d8 }
            androidx.collection.ArraySet r3 = new androidx.collection.ArraySet     // Catch:{ Exception -> 0x00d8 }
            r3.<init>()     // Catch:{ Exception -> 0x00d8 }
            boolean r4 = r2.isEmpty()     // Catch:{ Exception -> 0x00d8 }
            if (r4 != 0) goto L_0x0109
            int r4 = r2.size()     // Catch:{ Exception -> 0x00d8 }
            int r4 = r4 + -1
        L_0x00c8:
            if (r4 < 0) goto L_0x00da
            java.lang.Object r5 = r2.get(r4)     // Catch:{ Exception -> 0x00d8 }
            com.salesforce.marketingcloud.storage.db.upgrades.b$a r5 = (com.salesforce.marketingcloud.storage.db.upgrades.b.a) r5     // Catch:{ Exception -> 0x00d8 }
            r3.add(r5)     // Catch:{ Exception -> 0x00d8 }
            int r4 = r4 + -1
            goto L_0x00c8
        L_0x00d6:
            r10 = move-exception
            goto L_0x0119
        L_0x00d8:
            r2 = move-exception
            goto L_0x00fe
        L_0x00da:
            android.content.ContentValues r2 = new android.content.ContentValues     // Catch:{ Exception -> 0x00d8 }
            r2.<init>()     // Catch:{ Exception -> 0x00d8 }
            java.lang.String r3 = a((java.util.Collection<com.salesforce.marketingcloud.storage.db.upgrades.b.a>) r3)     // Catch:{ Exception -> 0x00d8 }
            java.lang.String r3 = r11.a(r3)     // Catch:{ Exception -> 0x00d8 }
            r2.put(r0, r3)     // Catch:{ Exception -> 0x00d8 }
            java.lang.String r3 = "id=?"
            java.lang.String r4 = "id"
            int r4 = r10.getColumnIndex(r4)     // Catch:{ Exception -> 0x00d8 }
            java.lang.String r4 = r10.getString(r4)     // Catch:{ Exception -> 0x00d8 }
            java.lang.String[] r4 = new java.lang.String[]{r4}     // Catch:{ Exception -> 0x00d8 }
            r9.update(r1, r2, r3, r4)     // Catch:{ Exception -> 0x00d8 }
            goto L_0x0109
        L_0x00fe:
            java.lang.String r3 = c     // Catch:{ all -> 0x00d6 }
            java.lang.String r4 = "Unable to remove duplicate attributes from row"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}     // Catch:{ all -> 0x00d6 }
            com.salesforce.marketingcloud.g.b((java.lang.String) r3, (java.lang.String) r4, (java.lang.Object[]) r2)     // Catch:{ all -> 0x00d6 }
        L_0x0109:
            boolean r2 = r10.moveToNext()     // Catch:{ all -> 0x00d6 }
            if (r2 != 0) goto L_0x00a7
        L_0x010f:
            r10.close()     // Catch:{ all -> 0x00d6 }
        L_0x0112:
            r9.setTransactionSuccessful()     // Catch:{ all -> 0x00d6 }
            r9.endTransaction()
            return
        L_0x0119:
            r9.endTransaction()
            throw r10
        L_0x011d:
            r9.endTransaction()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.storage.db.upgrades.b.a(android.database.sqlite.SQLiteDatabase, android.content.Context, com.salesforce.marketingcloud.util.c):void");
    }

    public static void b(SQLiteDatabase sQLiteDatabase, Context context, c cVar) {
        e(sQLiteDatabase);
        b(sQLiteDatabase);
        d(sQLiteDatabase);
        c(sQLiteDatabase);
        a(sQLiteDatabase, context, cVar);
        a(sQLiteDatabase);
    }

    private static ArrayList<a> a(String str) {
        ArrayList<a> arrayList = new ArrayList<>();
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split("\\^\\|\\^");
            int i = 0;
            while (i < split.length) {
                while (true) {
                    String str2 = split[i];
                    if (str2 != null && !str2.isEmpty()) {
                        break;
                    }
                    i++;
                }
                int i2 = i + 1;
                if (i2 >= split.length) {
                    arrayList.add(new a(split[i], ""));
                } else {
                    arrayList.add(new a(split[i], split[i2]));
                }
                i += 2;
            }
        }
        return arrayList;
    }

    private static synchronized String a(Collection<a> collection) {
        synchronized (b.class) {
            if (collection == null) {
                return null;
            }
            try {
                ArrayList<a> arrayList = new ArrayList<>(collection.size());
                for (a add : collection) {
                    arrayList.add(add);
                }
                StringBuilder sb = new StringBuilder();
                for (a aVar : arrayList) {
                    if (aVar != null) {
                        sb.append(aVar.a);
                        sb.append("^|^");
                        sb.append(aVar.b);
                        sb.append("^|^");
                    } else {
                        g.e(c, "A null attribute was encountered.", new Object[0]);
                    }
                }
                String sb2 = sb.toString();
                return sb2;
            } finally {
                while (true) {
                }
            }
        }
    }
}
