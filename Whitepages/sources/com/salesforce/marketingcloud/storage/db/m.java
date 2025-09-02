package com.salesforce.marketingcloud.storage.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.salesforce.marketingcloud.events.h;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.storage.n;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"UnknownNullness", "Range"})
public class m extends b implements n {
    public static final String g = "triggers";
    private static final String h = g.a("TriggerDbStorage");
    private final SQLiteStatement e;
    private final SQLiteStatement f;

    public m(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase);
        this.e = sQLiteDatabase.compileStatement("UPDATE triggers SET app_open_count = app_open_count + 1 WHERE (start_date IS NULL OR start_date < ?)");
        this.f = sQLiteDatabase.compileStatement("SELECT app_open_count FROM triggers WHERE id = ?");
    }

    private static boolean c(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.compileStatement("SELECT id,_key,start_date,_trigger,app_open_count FROM triggers");
            return true;
        } catch (Exception unused) {
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
            g.b(h, e2, "Unable to recover %s", g);
            return c;
        }
    }

    public void a(h hVar) throws Exception {
        ContentValues c = c(hVar);
        if (a(c, "id = ?", new String[]{hVar.h()}) == 0) {
            a(c);
        }
    }

    public int b(h hVar) {
        if (hVar != null) {
            try {
                this.f.bindString(1, hVar.h());
                return (int) this.f.simpleQueryForLong();
            } catch (Exception unused) {
            }
        }
        return 0;
    }

    public List<h> g(String str) {
        ArrayList arrayList;
        Cursor a = a(new String[]{"_trigger"}, "lower(_key) = lower(?) AND (start_date IS NULL OR start_date < ?)", new String[]{str, String.valueOf(System.currentTimeMillis())});
        try {
            if (a.moveToFirst()) {
                arrayList = new ArrayList(a.getCount());
                do {
                    arrayList.add(a(a));
                } while (a.moveToNext());
            } else {
                arrayList = null;
            }
            return arrayList != null ? arrayList : Collections.emptyList();
        } finally {
            a.close();
        }
    }

    public void k() {
        this.e.bindString(1, String.valueOf(System.currentTimeMillis()));
        this.e.execute();
    }

    public JSONArray m() {
        JSONArray jSONArray = new JSONArray();
        Cursor a = a(new String[]{"_trigger", "app_open_count"}, (String) null);
        if (a != null) {
            try {
                if (a.moveToFirst()) {
                    int columnIndex = a.getColumnIndex("_trigger");
                    int columnIndex2 = a.getColumnIndex("app_open_count");
                    do {
                        JSONObject jSONObject = new JSONObject(a.getString(columnIndex));
                        jSONObject.put("appOpenCount", a.getInt(columnIndex2));
                        jSONArray.put(jSONObject);
                    } while (a.moveToNext());
                }
            } catch (JSONException e2) {
                g.b(h, e2, "Unable to read trigger information from cursor.", new Object[0]);
            } catch (Throwable th) {
                a.close();
                throw th;
            }
            a.close();
        }
        return jSONArray;
    }

    /* access modifiers changed from: package-private */
    public String o() {
        return g;
    }

    private static h a(Cursor cursor) {
        try {
            return new h(new JSONObject(cursor.getString(cursor.getColumnIndex("_trigger"))));
        } catch (Exception e2) {
            g.b(h, e2, "Unable to read trigger from DB", new Object[0]);
            return null;
        }
    }

    private static ContentValues c(h hVar) throws Exception {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", hVar.h());
        contentValues.put("_key", hVar.i());
        contentValues.put("start_date", hVar.l() != null ? Long.valueOf(hVar.l().getTime()) : null);
        contentValues.put("_trigger", hVar.m().toString());
        return contentValues;
    }

    public h b(String str) {
        Cursor a = a(new String[]{"_trigger"}, "id = ?", new String[]{str});
        h hVar = null;
        if (a != null) {
            if (a.moveToFirst()) {
                hVar = a(a);
            }
            a.close();
        }
        return hVar;
    }

    static void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS triggers");
    }

    static void b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE triggers(id TEXT PRIMARY KEY, _key TEXT, start_date INTEGER DEFAULT NULL, _trigger TEXT, app_open_count INTEGER DEFAULT 0);");
    }

    public int b(Collection<String> collection) {
        if (collection.size() == 0) {
            return i((String) null);
        }
        try {
            return a(o(), collection);
        } catch (Exception unused) {
            g.e(h, "Unable to clean up %s table.", o());
            return 0;
        }
    }
}
