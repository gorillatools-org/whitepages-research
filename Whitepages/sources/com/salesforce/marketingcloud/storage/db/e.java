package com.salesforce.marketingcloud.storage.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.salesforce.marketingcloud.analytics.stats.b;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.storage.d;
import com.salesforce.marketingcloud.storage.db.a;
import com.salesforce.marketingcloud.util.c;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@SuppressLint({"Range"})
public class e extends b implements d {
    static final String e = "device_stats";
    private static final String f = g.a("DeviceStatsDbStorage");

    public e(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase);
    }

    static void b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE device_stats(id INTEGER PRIMARY KEY, type INTEGER, date INTEGER, event_data TEXT, in_transit INTEGER DEFAULT 0, ready_to_send INTEGER DEFAULT 0);");
    }

    public void a(b bVar, c cVar) throws Exception {
        ContentValues b = b(bVar, cVar);
        if (bVar.b() == null || a(b, "id = ?", new String[]{bVar.b().toString()}) == 0) {
            a(b);
        }
    }

    public void c(String[] strArr) {
        if (strArr.length > 0) {
            try {
                g.c(f, "Deleted %d items of %d items", Integer.valueOf(strArr.length), Integer.valueOf(c(Arrays.asList(strArr))));
            } catch (Exception unused) {
                g.e(f, "Unable to clean up %s table.", o());
            }
        }
    }

    public void d(String[] strArr) {
        if (strArr.length > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("in_transit", 0);
            try {
                a(contentValues, (Collection<String>) Arrays.asList(strArr));
            } catch (Exception unused) {
                g.e(f, "Unable to update %s table.", o());
            }
        }
    }

    public int f() {
        return i((String) null);
    }

    public List<b> j(c cVar) {
        List<b> emptyList = Collections.emptyList();
        Cursor rawQuery = this.c.rawQuery("SELECT * FROM device_stats WHERE ready_to_send=0", (String[]) null);
        if (rawQuery != null) {
            if (rawQuery.moveToFirst()) {
                ArrayList arrayList = new ArrayList(rawQuery.getCount());
                do {
                    arrayList.add(a(rawQuery, cVar));
                } while (rawQuery.moveToNext());
                emptyList = arrayList;
            }
            rawQuery.close();
        }
        return emptyList;
    }

    public List<b> k(c cVar) {
        return a(cVar, "SELECT * FROM device_stats WHERE ready_to_send=1 AND in_transit=0 AND type IN(100, 101, 102, 103, 104, 106, 107)");
    }

    /* access modifiers changed from: package-private */
    public String o() {
        return e;
    }

    public List<b> p(c cVar) {
        return a(cVar, "SELECT * FROM device_stats WHERE ready_to_send=1 AND in_transit=0 AND type IN(105)");
    }

    private static b a(Cursor cursor, c cVar) {
        try {
            int i = cursor.getInt(cursor.getColumnIndex("id"));
            int i2 = cursor.getInt(cursor.getColumnIndex("type"));
            Date date = new Date(cursor.getLong(cursor.getColumnIndex("date")));
            com.salesforce.marketingcloud.analytics.stats.d a = com.salesforce.marketingcloud.analytics.stats.d.a(cVar.b(cursor.getString(cursor.getColumnIndex("event_data"))));
            boolean z = true;
            if (cursor.getInt(cursor.getColumnIndex(a.C0034a.f)) != 1) {
                z = false;
            }
            return b.a(i, i2, date, a, z);
        } catch (Exception e2) {
            g.b(f, e2, "Unable to read analytic item from cursor.", new Object[0]);
            return null;
        }
    }

    private static ContentValues b(b bVar, c cVar) throws Exception {
        ContentValues contentValues = new ContentValues();
        if (bVar.b() != null) {
            contentValues.put("id", bVar.b());
        }
        contentValues.put("type", Integer.valueOf(bVar.d()));
        contentValues.put("date", Long.valueOf(bVar.a().getTime()));
        contentValues.put("event_data", cVar.a(bVar.c().a()));
        contentValues.put(a.C0034a.f, Integer.valueOf(bVar.e() ? 1 : 0));
        return contentValues;
    }

    private static boolean c(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.compileStatement("SELECT id,type,date,event_data,in_transit,ready_to_send FROM device_stats");
            return true;
        } catch (Exception e2) {
            g.e(f, e2, "%s is invalid", e);
            return false;
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
            g.b(f, e2, "Unable to recover %s", e);
            return c;
        }
    }

    static void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS device_stats");
    }

    private List<b> a(c cVar, String str) {
        List<b> emptyList = Collections.emptyList();
        this.c.beginTransaction();
        Cursor rawQuery = this.c.rawQuery(str, (String[]) null);
        if (rawQuery != null) {
            if (rawQuery.moveToFirst()) {
                int count = rawQuery.getCount();
                int columnIndex = rawQuery.getColumnIndex("id");
                String[] strArr = new String[count];
                ArrayList arrayList = new ArrayList(count);
                int i = 0;
                while (true) {
                    arrayList.add(a(rawQuery, cVar));
                    strArr[i] = rawQuery.getString(columnIndex);
                    if (!rawQuery.moveToNext()) {
                        break;
                    }
                    int i2 = i + 1;
                    if (i >= count) {
                        break;
                    }
                    i = i2;
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put("in_transit", 1);
                try {
                    a(contentValues, (Collection<String>) Arrays.asList(strArr));
                    this.c.setTransactionSuccessful();
                } catch (Exception unused) {
                    g.e(f, "Unable to update %s table.", o());
                }
                emptyList = arrayList;
            }
            rawQuery.close();
            this.c.endTransaction();
        }
        return emptyList;
    }

    public int a() {
        try {
            return a("(type = ? OR type = ?) AND in_transit = 0 AND date <= ?", new String[]{String.valueOf(b.l), String.valueOf(b.m), String.valueOf(System.currentTimeMillis() - 1209600000)});
        } catch (Exception e2) {
            g.b(f, e2, "Unable to purge old debug/telemetry data.", new Object[0]);
            return 0;
        }
    }
}
