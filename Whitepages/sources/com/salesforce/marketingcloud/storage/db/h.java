package com.salesforce.marketingcloud.storage.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.location.LatLon;
import com.salesforce.marketingcloud.storage.i;
import com.salesforce.marketingcloud.util.c;
import java.util.Locale;

@SuppressLint({"UnknownNullness", "Range"})
public final class h extends b implements i {
    public static final String e = "location_table";
    private static final String f = "CREATE TABLE location_table (id INTEGER PRIMARY KEY CHECK (id = 0), latitude VARCHAR, longitude VARCHAR );";
    private static final String[] g = {"id", a.b, a.c};
    private static final String h = g.a("LocationDbStorage");

    public static class a {
        public static final String a = "id";
        public static final String b = "latitude";
        public static final String c = "longitude";
    }

    public h(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase);
    }

    static void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS location_table");
    }

    static void b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(f);
    }

    private static boolean c(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.compileStatement(c.a("SELECT %s FROM %s", TextUtils.join(",", g), e));
            return true;
        } catch (Exception e2) {
            g.e(h, e2, "%s is invalid", e);
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
            g.b(h, e2, "Unable to recover %s", e);
            return c;
        }
    }

    public LatLon f(c cVar) {
        Cursor a2 = a(g, String.format(Locale.ENGLISH, "%s = ?", new Object[]{"id"}), new String[]{"0"});
        LatLon latLon = null;
        if (a2 != null) {
            if (a2.moveToFirst()) {
                try {
                    latLon = new LatLon(Double.valueOf(cVar.b(a2.getString(a2.getColumnIndex(a.b)))).doubleValue(), Double.valueOf(cVar.b(a2.getString(a2.getColumnIndex(a.c)))).doubleValue());
                } catch (Exception e2) {
                    g.b(h, e2, "Unable to read location from database.", new Object[0]);
                }
            }
            a2.close();
        }
        return latLon;
    }

    public int g() {
        return i((String) null);
    }

    /* access modifiers changed from: package-private */
    public String o() {
        return e;
    }

    public void a(LatLon latLon, c cVar) throws Exception {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", 0);
        contentValues.put(a.b, cVar.a(Double.toString(latLon.latitude())));
        contentValues.put(a.c, cVar.a(Double.toString(latLon.longitude())));
        if (a(contentValues, String.format(Locale.ENGLISH, "%s = ?", new Object[]{"id"}), new String[]{"0"}) == 0) {
            a(contentValues);
        }
    }
}
