package com.salesforce.marketingcloud.storage.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.salesforce.marketingcloud.internal.a;
import com.salesforce.marketingcloud.messages.iam.InAppMessage;
import com.salesforce.marketingcloud.storage.g;
import com.salesforce.marketingcloud.util.c;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

@SuppressLint({"UnknownNullness", "Range"})
public class f extends b implements g {
    public static final String e = "in_app_messages";
    public static final String f = "iam_state";
    public static final String g = "iam_view";
    public static final String h = "iam_state_init";
    private static final String i = com.salesforce.marketingcloud.g.a("InAppMessageDbStorage");
    private static final String j = "id IN (%s) AND (display_count < display_limit) AND (start_date IS NULL OR start_date < ?) AND (end_date IS NULL OR end_date > ?) ORDER BY   priority ASC,  modified_date DESC LIMIT 1";

    public f(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase);
    }

    private static void b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TRIGGER iam_state_init AFTER INSERT ON in_app_messages BEGIN INSERT INTO iam_state (id) VALUES (NEW.id); END;");
    }

    private static void c(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE VIEW iam_view AS SELECT in_app_messages.id,in_app_messages.priority,in_app_messages.start_date,in_app_messages.end_date,in_app_messages.modified_date,in_app_messages.display_limit,in_app_messages.message_json,iam_state.display_count FROM in_app_messages INNER JOIN iam_state ON iam_state.id = in_app_messages.id;");
    }

    private static void d(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE in_app_messages(id TEXT PRIMARY KEY, priority INTEGER DEFAULT 999, start_date INTEGER DEFAULT NULL, end_date INTEGER DEFAULT NULL, modified_date INTEGER DEFAULT NULL, display_limit INTEGER DEFAULT 1, media_url TEXT DEFAULT NULL, message_json TEXT);");
    }

    static void e(SQLiteDatabase sQLiteDatabase) {
        h(sQLiteDatabase);
        f(sQLiteDatabase);
        g(sQLiteDatabase);
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS iam_state_init");
    }

    private static void f(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS iam_state");
    }

    private static void g(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP VIEW IF EXISTS iam_view");
    }

    private static void h(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS in_app_messages");
    }

    static void i(SQLiteDatabase sQLiteDatabase) {
        d(sQLiteDatabase);
        a(sQLiteDatabase);
        b(sQLiteDatabase);
        c(sQLiteDatabase);
    }

    private static boolean j(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.compileStatement("SELECT id,display_count FROM iam_state");
            return false;
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.e(i, e2, "%s is invalid", f);
            return true;
        }
    }

    private static boolean k(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.compileStatement("SELECT id,priority,start_date,end_date,modified_date,display_limit,media_url,message_json FROM in_app_messages");
            return false;
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.e(i, e2, "%s is invalid", e);
            return true;
        }
    }

    private static boolean l(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.compileStatement("SELECT id,priority,start_date,end_date,modified_date,display_limit,message_json,display_count FROM iam_view");
            return false;
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.e(i, e2, "%s is invalid", g);
            return true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00a4, code lost:
        if (r0 != false) goto L_0x00a6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean m(android.database.sqlite.SQLiteDatabase r8) {
        /*
            boolean r0 = k(r8)
            java.lang.String r1 = "Unable to recover %s"
            r2 = 0
            if (r0 == 0) goto L_0x0032
            r8.beginTransaction()
            h(r8)     // Catch:{ Exception -> 0x0018 }
            d((android.database.sqlite.SQLiteDatabase) r8)     // Catch:{ Exception -> 0x0018 }
            r8.setTransactionSuccessful()     // Catch:{ Exception -> 0x0018 }
            goto L_0x0024
        L_0x0016:
            r0 = move-exception
            goto L_0x002e
        L_0x0018:
            r0 = move-exception
            java.lang.String r3 = i     // Catch:{ all -> 0x0016 }
            java.lang.String r4 = "in_app_messages"
            java.lang.Object[] r4 = new java.lang.Object[]{r4}     // Catch:{ all -> 0x0016 }
            com.salesforce.marketingcloud.g.b(r3, r0, r1, r4)     // Catch:{ all -> 0x0016 }
        L_0x0024:
            r8.endTransaction()
            boolean r0 = k(r8)
            if (r0 == 0) goto L_0x0032
            return r2
        L_0x002e:
            r8.endTransaction()
            throw r0
        L_0x0032:
            boolean r0 = j(r8)
            r3 = 1
            if (r0 == 0) goto L_0x0064
            r8.beginTransaction()
            f(r8)     // Catch:{ Exception -> 0x0048 }
            a((android.database.sqlite.SQLiteDatabase) r8)     // Catch:{ Exception -> 0x0048 }
            r8.setTransactionSuccessful()     // Catch:{ Exception -> 0x0048 }
            goto L_0x0054
        L_0x0046:
            r0 = move-exception
            goto L_0x0060
        L_0x0048:
            r0 = move-exception
            java.lang.String r4 = i     // Catch:{ all -> 0x0046 }
            java.lang.String r5 = "iam_state"
            java.lang.Object[] r5 = new java.lang.Object[]{r5}     // Catch:{ all -> 0x0046 }
            com.salesforce.marketingcloud.g.b(r4, r0, r1, r5)     // Catch:{ all -> 0x0046 }
        L_0x0054:
            r8.endTransaction()
            boolean r0 = j(r8)
            if (r0 == 0) goto L_0x005e
            return r2
        L_0x005e:
            r0 = r3
            goto L_0x0065
        L_0x0060:
            r8.endTransaction()
            throw r0
        L_0x0064:
            r0 = r2
        L_0x0065:
            boolean r4 = l(r8)
            if (r4 == 0) goto L_0x0085
            g(r8)     // Catch:{ Exception -> 0x0072 }
            c(r8)     // Catch:{ Exception -> 0x0072 }
            goto L_0x007e
        L_0x0072:
            r4 = move-exception
            java.lang.String r5 = i
            java.lang.String r6 = "iam_view"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            com.salesforce.marketingcloud.g.b(r5, r4, r1, r6)
        L_0x007e:
            boolean r4 = l(r8)
            if (r4 == 0) goto L_0x0085
            return r2
        L_0x0085:
            java.lang.String r4 = "trigger"
            java.lang.String r5 = "iam_state_init"
            boolean r6 = com.salesforce.marketingcloud.storage.db.c.a(r8, r4, r5)
            if (r6 == 0) goto L_0x00a4
            b(r8)     // Catch:{ Exception -> 0x0093 }
            goto L_0x009d
        L_0x0093:
            r0 = move-exception
            java.lang.String r6 = i
            java.lang.Object[] r7 = new java.lang.Object[]{r5}
            com.salesforce.marketingcloud.g.b(r6, r0, r1, r7)
        L_0x009d:
            boolean r0 = com.salesforce.marketingcloud.storage.db.c.a(r8, r4, r5)
            if (r0 == 0) goto L_0x00a6
            return r2
        L_0x00a4:
            if (r0 == 0) goto L_0x00b6
        L_0x00a6:
            java.lang.String r0 = "INSERT OR IGNORE INTO iam_state(id) SELECT id FROM in_app_messages;"
            r8.execSQL(r0)     // Catch:{ Exception -> 0x00ac }
            goto L_0x00b6
        L_0x00ac:
            r8 = move-exception
            java.lang.String r0 = i
            java.lang.Object[] r1 = new java.lang.Object[r2]
            java.lang.String r2 = "Unable to correct relationship between iam data and iam state."
            com.salesforce.marketingcloud.g.b(r0, r8, r2, r1)
        L_0x00b6:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.storage.db.f.m(android.database.sqlite.SQLiteDatabase):boolean");
    }

    public int a(InAppMessage inAppMessage, c cVar) throws Exception {
        ContentValues b = b(inAppMessage, cVar);
        if (this.c.update(e, b, "id = ?", new String[]{inAppMessage.id()}) != 0) {
            return 2;
        }
        this.c.insert(e, (String) null, b);
        return 1;
    }

    /* access modifiers changed from: package-private */
    public String o() {
        return null;
    }

    private static void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE iam_state(id TEXT PRIMARY KEY, display_count integer default 0, FOREIGN KEY (id) REFERENCES in_app_messages(id) ON DELETE CASCADE);");
    }

    private static InAppMessage b(Cursor cursor, c cVar) {
        try {
            return new InAppMessage(new JSONObject(cVar.b(cursor.getString(cursor.getColumnIndex("message_json")))));
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(i, e2, "Unable to retrieve InAppMessage from db cursor", new Object[0]);
            return null;
        }
    }

    public JSONArray d(c cVar) {
        JSONArray jSONArray = new JSONArray();
        Cursor a = a(g, new String[]{"message_json", "display_count"}, (String) null, (String[]) null);
        if (a != null) {
            try {
                if (a.moveToFirst()) {
                    int columnIndex = a.getColumnIndex("message_json");
                    int columnIndex2 = a.getColumnIndex("display_count");
                    do {
                        JSONObject jSONObject = new JSONObject(cVar.b(a.getString(columnIndex)));
                        jSONObject.put("displayCount", a.getInt(columnIndex2));
                        jSONArray.put(jSONObject);
                    } while (a.moveToNext());
                }
            } catch (Exception e2) {
                com.salesforce.marketingcloud.g.b(i, e2, "Unable to read message information from cursor.", new Object[0]);
            } catch (Throwable th) {
                a.close();
                throw th;
            }
            a.close();
        }
        return jSONArray;
    }

    public List<String> e(c cVar) {
        ArrayList arrayList = null;
        Cursor a = a(e, new String[]{"media_url"}, "media_url IS NOT NULL", (String[]) null);
        try {
            if (a.moveToFirst()) {
                arrayList = new ArrayList(a.getCount());
                do {
                    String a2 = a(a, cVar);
                    if (a2 != null) {
                        arrayList.add(a2);
                    }
                } while (a.moveToNext());
            }
            a.close();
            return arrayList != null ? arrayList : Collections.emptyList();
        } catch (Throwable th) {
            a.close();
            throw th;
        }
    }

    private static String a(Cursor cursor, c cVar) {
        try {
            return cVar.b(cursor.getString(cursor.getColumnIndex("media_url")));
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(i, e2, "Unable to retrieve media_url from db cursor", new Object[0]);
            return null;
        }
    }

    private static ContentValues b(InAppMessage inAppMessage, c cVar) throws Exception {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", inAppMessage.id());
        contentValues.put("priority", Integer.valueOf(inAppMessage.priority()));
        Long l = null;
        contentValues.put("start_date", inAppMessage.startDateUtc() != null ? Long.valueOf(inAppMessage.startDateUtc().getTime()) : null);
        if (inAppMessage.endDateUtc() != null) {
            l = Long.valueOf(inAppMessage.endDateUtc().getTime());
        }
        contentValues.put("end_date", l);
        contentValues.put("modified_date", Long.valueOf(inAppMessage.modifiedDateUtc().getTime()));
        contentValues.put("display_limit", Integer.valueOf(inAppMessage.displayLimit()));
        InAppMessage.Media media = inAppMessage.media();
        if (media != null && !TextUtils.isEmpty(media.url())) {
            contentValues.put("media_url", cVar.a(media.url()));
        }
        contentValues.put("message_json", cVar.a(a.b(inAppMessage).toString()));
        return contentValues;
    }

    public InAppMessage a(String str, c cVar) {
        Cursor a = a(e, new String[]{"message_json"}, "id = ?", new String[]{str});
        InAppMessage inAppMessage = null;
        if (a != null) {
            if (a.moveToFirst()) {
                inAppMessage = b(a, cVar);
            }
            a.close();
        }
        return inAppMessage;
    }

    public void b(String str, int i2) {
        if (str != null && i2 >= 0) {
            this.c.execSQL("UPDATE iam_state SET display_count = MAX(display_count, ?) WHERE id = ?", new Object[]{Integer.valueOf(i2), str});
        }
    }

    public void a(InAppMessage inAppMessage) {
        if (inAppMessage != null) {
            this.c.execSQL("UPDATE iam_state SET display_count = display_count + 1 WHERE id = ?", new String[]{inAppMessage.id()});
        }
    }

    public int a(Collection<String> collection) {
        if (collection.size() == 0) {
            return this.c.delete(e, (String) null, (String[]) null);
        }
        try {
            return a(e, collection);
        } catch (SQLException unused) {
            com.salesforce.marketingcloud.g.e(i, "Unable to clean up %s table.", e);
            return 0;
        }
    }

    public InAppMessage a(Collection<String> collection, c cVar) {
        InAppMessage inAppMessage = null;
        if (collection.size() > 0) {
            this.c.beginTransaction();
            b(g, collection);
            String a = c.a(j, c.a("SELECT %1$s.id FROM %1$s LEFT JOIN tmp_%1$s ON %1$s.id = tmp_%1$s.id WHERE tmp_%1$s.id IS NOT NULL", g));
            String valueOf = String.valueOf(System.currentTimeMillis());
            Cursor a2 = a(g, new String[]{"message_json"}, a, new String[]{valueOf, valueOf});
            if (a2 != null) {
                if (a2.moveToFirst()) {
                    inAppMessage = b(a2, cVar);
                }
                a2.close();
            }
            h(g);
            this.c.setTransactionSuccessful();
            this.c.endTransaction();
        }
        return inAppMessage;
    }
}
