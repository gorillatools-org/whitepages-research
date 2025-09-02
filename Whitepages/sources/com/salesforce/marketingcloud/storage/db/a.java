package com.salesforce.marketingcloud.storage.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.salesforce.marketingcloud.analytics.b;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.messages.Region;
import com.salesforce.marketingcloud.util.c;
import com.salesforce.marketingcloud.util.l;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

@SuppressLint({"UnknownNullness", "Range"})
public final class a extends b implements com.salesforce.marketingcloud.storage.a {
    public static final String e = "analytic_item";
    static final int f = 1000;
    private static final String[] g = {"id", C0034a.c, C0034a.i, C0034a.d, "value", C0034a.f, C0034a.e, C0034a.h, C0034a.g, "predictive_intelligence_identifier"};
    private static final String h = "CREATE TABLE analytic_item (id INTEGER PRIMARY KEY AUTOINCREMENT, event_date VARCHAR, analytic_product_type INTEGER, analytic_type INTEGER, value INTEGER, ready_to_send SMALLINT, object_ids VARCHAR, enc_json_pi_payload VARCHAR, enc_json_et_payload VARCHAR, predictive_intelligence_identifier VARCHAR DEFAULT NULL);";
    private static final String i = g.a("AnalyticItemDbStorage");

    /* renamed from: com.salesforce.marketingcloud.storage.db.a$a  reason: collision with other inner class name */
    public static class C0034a {
        public static final String a = "id";
        public static final String b = "value";
        public static final String c = "event_date";
        public static final String d = "analytic_type";
        public static final String e = "object_ids";
        public static final String f = "ready_to_send";
        public static final String g = "enc_json_et_payload";
        public static final String h = "enc_json_pi_payload";
        public static final String i = "analytic_product_type";
        public static final String j = "predictive_intelligence_identifier";
    }

    public a(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase);
    }

    public void a(b bVar, c cVar) throws Exception {
        int i2 = bVar.j() == 0 ? 0 : 1;
        int h2 = h(i2);
        if (h2 + 1 > 1000) {
            a(h2, 1000, i2);
        }
        bVar.a((int) a(c(bVar, cVar)));
    }

    /* access modifiers changed from: package-private */
    public List<b> b(Cursor cursor, c cVar) {
        List<b> emptyList = Collections.emptyList();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                ArrayList arrayList = new ArrayList();
                do {
                    b a = a(cursor, cVar);
                    if (a != null) {
                        arrayList.add(a);
                    } else {
                        int i2 = cursor.getInt(cursor.getColumnIndex("id"));
                        if (i2 >= 0) {
                            a(a("%s = ?", "id"), new String[]{String.valueOf(i2)});
                        }
                    }
                } while (cursor.moveToNext());
                emptyList = arrayList;
            }
            cursor.close();
        }
        return emptyList;
    }

    public List<b> c(c cVar) {
        return b(a(g, a("%s=? AND %s=?", C0034a.i, C0034a.f), new String[]{String.valueOf(0), "1"}, (String) null, (String) null, a("%s ASC", "id")), cVar);
    }

    public int d() {
        return h(0);
    }

    public int e() {
        return h(1);
    }

    public int g(int i2) {
        return a(a("%s = ?", C0034a.i), new String[]{String.valueOf(i2)});
    }

    public List<b> h(c cVar) {
        return b(a(g, a("(%1$s=? OR %1$s=?) AND %2$s=?", C0034a.d, C0034a.f), new String[]{String.valueOf(13), String.valueOf(11), String.valueOf(0)}), cVar);
    }

    public List<b> i(c cVar) {
        return b(1, cVar);
    }

    public List<b> o(c cVar) {
        return b(a(g, a("%s=? AND %s=?", C0034a.i, C0034a.f), new String[]{String.valueOf(1), String.valueOf(1)}, (String) null, (String) null, a("%s ASC", C0034a.c)), cVar);
    }

    private static b a(Cursor cursor, c cVar) {
        String str;
        String str2;
        b bVar;
        try {
            int i2 = cursor.getInt(cursor.getColumnIndex(C0034a.d));
            int i3 = cursor.getInt(cursor.getColumnIndex(C0034a.i)) == 0 ? 0 : 1;
            Date f2 = l.f(cursor.getString(cursor.getColumnIndex(C0034a.c)));
            boolean z = cursor.getInt(cursor.getColumnIndex(C0034a.f)) == 1;
            List emptyList = Collections.emptyList();
            JSONArray jSONArray = new JSONArray(cursor.getString(cursor.getColumnIndex(C0034a.e)));
            if (jSONArray.length() > 0) {
                emptyList = new ArrayList();
                for (int i4 = 0; i4 < jSONArray.length(); i4++) {
                    emptyList.add(jSONArray.getString(i4));
                }
            }
            List list = emptyList;
            if (cVar != null) {
                String b = cVar.b(cursor.getString(cursor.getColumnIndex(C0034a.g)));
                str2 = !TextUtils.isEmpty(b) ? new JSONObject(b).optString("requestId") : null;
                str = b;
            } else {
                str2 = null;
                str = null;
            }
            if (!TextUtils.isEmpty(str2)) {
                bVar = b.a(f2, i3, i2, list, str2, z);
            } else if (list.size() > 0) {
                bVar = b.a(f2, i3, i2, (List<String>) list, z);
            } else {
                bVar = b.a(f2, i3, i2);
                bVar.a(z);
            }
            bVar.a(cursor.getInt(cursor.getColumnIndex("id")));
            bVar.b(cursor.getInt(cursor.getColumnIndex("value")));
            bVar.b(str);
            if (i3 == 1 && cVar != null) {
                bVar.d(cVar.b(cursor.getString(cursor.getColumnIndex("predictive_intelligence_identifier"))));
                String string = cursor.getString(cursor.getColumnIndex(C0034a.h));
                if (!TextUtils.isEmpty(string)) {
                    bVar.c(cVar.b(string));
                }
            }
            return bVar;
        } catch (Exception e2) {
            g.b(i, e2, "Failed to create our analytic item from storage.", new Object[0]);
            return null;
        }
    }

    static boolean d(SQLiteDatabase sQLiteDatabase) {
        boolean c = c(sQLiteDatabase);
        if (c) {
            return c;
        }
        try {
            a(sQLiteDatabase);
            b(sQLiteDatabase);
            return c(sQLiteDatabase);
        } catch (Exception e2) {
            g.b(i, e2, "Unable to recover %s", e);
            return c;
        }
    }

    private int h(int i2) {
        return (int) DatabaseUtils.queryNumEntries(this.c, e, a("%s=%s", C0034a.i, Integer.valueOf(i2)));
    }

    public int b(int i2) {
        return a(a("%s = ? AND %s IN (%s)", C0034a.i, C0034a.d, TextUtils.join(",", b.t)), new String[]{String.valueOf(i2)});
    }

    public boolean c(int i2) {
        return DatabaseUtils.queryNumEntries(this.c, o(), a("(%1$s=? OR %1$s=?) AND %2$s=? AND %3$s=? AND %4$s=?", C0034a.d, C0034a.i, "value", C0034a.f), new String[]{String.valueOf(4), String.valueOf(5), String.valueOf(i2), String.valueOf(0), String.valueOf(0)}) > 0;
    }

    public List<b> g(c cVar) {
        return b(0, cVar);
    }

    /* access modifiers changed from: package-private */
    public String o() {
        return e;
    }

    private List<b> b(int i2, c cVar) {
        return b(a(g, a("(%1$s=? OR %1$s=?) AND %2$s=? AND %3$s=? AND %4$s=?", C0034a.d, C0034a.i, "value", C0034a.f), new String[]{String.valueOf(4), String.valueOf(5), String.valueOf(i2), String.valueOf(0), String.valueOf(0)}, (String) null, (String) null, a("%s ASC", "id")), cVar);
    }

    private static boolean c(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.compileStatement(c.a("SELECT %s FROM %s", TextUtils.join(",", g), e));
            return true;
        } catch (Exception e2) {
            g.e(i, e2, "%s is invalid", e);
            return false;
        }
    }

    public int a(String[] strArr) {
        return i(a("%s IN (%s)", "id", TextUtils.join(",", strArr)));
    }

    private static ContentValues c(b bVar, c cVar) throws Exception {
        String str;
        ContentValues contentValues = new ContentValues();
        contentValues.put(C0034a.c, l.a(bVar.b()));
        contentValues.put(C0034a.i, Integer.valueOf(bVar.j()));
        contentValues.put(C0034a.d, Integer.valueOf(bVar.a()));
        contentValues.put("value", Integer.valueOf(bVar.g()));
        contentValues.put(C0034a.f, Integer.valueOf(bVar.h() ? 1 : 0));
        contentValues.put(C0034a.e, new JSONArray(bVar.i()).toString());
        if (bVar.c() != null) {
            contentValues.put(C0034a.g, cVar.a(bVar.c()));
        }
        if (bVar.j() == 1) {
            contentValues.put("predictive_intelligence_identifier", cVar.a(bVar.f()));
            str = cVar.a(bVar.e());
        } else {
            contentValues.put("predictive_intelligence_identifier", (String) null);
            str = "";
        }
        contentValues.put(C0034a.h, str);
        return contentValues;
    }

    public int a(int i2) {
        return a(a("%s = ? AND %s NOT IN (%s)", C0034a.i, C0034a.d, TextUtils.join(",", b.t)), new String[]{String.valueOf(i2)});
    }

    public List<b> b(Region region, c cVar) {
        return b(a(g, a("(%1$s=? OR %1$s=?) AND %2$s LIKE ? AND %3$s=?", C0034a.d, C0034a.e, C0034a.f), new String[]{String.valueOf(13), String.valueOf(11), a("%%%s%%", region.id()), String.valueOf(0)}), cVar);
    }

    static void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS analytic_item");
    }

    static void b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(h);
    }

    public static String a(String str, Object... objArr) {
        return String.format(Locale.ENGLISH, str, objArr);
    }

    public int b(b bVar, c cVar) throws Exception {
        return a(c(bVar, cVar), a("%s = ?", "id"), new String[]{String.valueOf(bVar.d())});
    }

    public int a() {
        try {
            return a("analytic_product_type =? AND event_date <= ?", new String[]{String.valueOf(1), l.a(new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(14)))});
        } catch (Exception e2) {
            g.b(i, e2, "Unable to purge old analytic data.", new Object[0]);
            return 0;
        }
    }

    private void a(int i2, int i3, int i4) throws Exception {
        i(a("%s IN ( SELECT %s FROM %s WHERE %s=%d ORDER BY %s ASC LIMIT %d )", "id", "id", e, C0034a.i, Integer.valueOf(i4), "id", Integer.valueOf((i2 + 1) - i3)));
    }
}
