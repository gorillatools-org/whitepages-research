package com.salesforce.marketingcloud.storage.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.google.firebase.messaging.Constants;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.location.LatLon;
import com.salesforce.marketingcloud.messages.Message;
import com.salesforce.marketingcloud.messages.Region;
import com.salesforce.marketingcloud.storage.db.h;
import com.salesforce.marketingcloud.storage.l;
import com.salesforce.marketingcloud.util.c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressLint({"UnknownNullness", "Range"})
public final class j extends b implements l {
    public static final String e = "regions";
    public static final String f = "region_messages";
    static final String g = g.a("RegionDbStorage");

    public j(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase);
    }

    private static void b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE regions(id TEXT PRIMARY KEY, latitude TEXT, longitude TEXT, radius INTEGER, beacon_guid TEXT, beacon_major INTEGER, beacon_minor INTEGER, description TEXT, name TEXT, location_type INTEGER, is_inside SMALLINT);");
    }

    static void c(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS regions");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS region_messages");
    }

    private static boolean e(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.compileStatement("SELECT region_id,message_id FROM region_messages");
            return true;
        } catch (Exception e2) {
            g.e(g, e2, "%s is invalid", f);
            return false;
        }
    }

    static boolean g(SQLiteDatabase sQLiteDatabase) {
        boolean f2 = f(sQLiteDatabase);
        if (!f2) {
            try {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS regions");
                b(sQLiteDatabase);
                f2 = f(sQLiteDatabase);
            } catch (Exception e2) {
                g.b(g, e2, "Unable to recover %s", e);
            }
        }
        boolean e3 = e(sQLiteDatabase);
        if (f2 && !e3) {
            try {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS region_messages");
                a(sQLiteDatabase);
                e3 = e(sQLiteDatabase);
            } catch (Exception e4) {
                g.b(g, e4, "Unable to recover %s", f);
            }
        }
        return f2 && e3;
    }

    public void a(Region region, c cVar) throws Exception {
        this.c.insertWithOnConflict(e, (String) null, c(region, cVar), 5);
        if (!region.messages().isEmpty()) {
            for (Message a : region.messages()) {
                this.c.insertWithOnConflict(f, (String) null, a(region, a), 5);
            }
        }
    }

    public List<String> d(int i) {
        List<String> emptyList = Collections.emptyList();
        Cursor rawQuery = this.c.rawQuery("SELECT id FROM regions WHERE location_type=?", new String[]{String.valueOf(i)});
        if (rawQuery != null) {
            if (rawQuery.moveToFirst()) {
                ArrayList arrayList = new ArrayList();
                int columnIndex = rawQuery.getColumnIndex("id");
                do {
                    arrayList.add(rawQuery.getString(columnIndex));
                } while (rawQuery.moveToNext());
                emptyList = arrayList;
            }
            rawQuery.close();
        }
        return emptyList;
    }

    public int f(int i) {
        return this.c.delete(e, "location_type=?", new String[]{String.valueOf(i)});
    }

    public void l() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("is_inside", 0);
        this.c.update(e, contentValues, (String) null, (String[]) null);
    }

    public Region m(c cVar) {
        Cursor rawQuery = this.c.rawQuery("SELECT * FROM regions WHERE id=?", new String[]{Region.MAGIC_REGION_ID});
        Region region = null;
        if (rawQuery != null) {
            if (rawQuery.moveToFirst()) {
                try {
                    region = com.salesforce.marketingcloud.internal.j.a(new LatLon(Double.valueOf(cVar.b(rawQuery.getString(rawQuery.getColumnIndex(h.a.b)))).doubleValue(), Double.valueOf(cVar.b(rawQuery.getString(rawQuery.getColumnIndex(h.a.c)))).doubleValue()), rawQuery.getInt(rawQuery.getColumnIndex("radius")));
                } catch (Exception e2) {
                    g.b(g, e2, "Unable to read magic region from DB.", new Object[0]);
                }
            }
            rawQuery.close();
        }
        return region;
    }

    /* access modifiers changed from: package-private */
    public String o() {
        return e;
    }

    private static void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE region_messages (region_id TEXT,  message_id TEXT, FOREIGN KEY (region_id) REFERENCES regions(id) ON DELETE CASCADE, PRIMARY KEY (region_id, message_id));");
    }

    static void d(SQLiteDatabase sQLiteDatabase) {
        b(sQLiteDatabase);
        a(sQLiteDatabase);
    }

    private static boolean f(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.compileStatement("SELECT id,latitude,longitude,radius,beacon_guid,beacon_major,beacon_minor,description,name,location_type,is_inside FROM regions");
            return true;
        } catch (Exception e2) {
            g.e(g, e2, "%s is invalid", e);
            return false;
        }
    }

    public List<String> c(String str, int i) {
        ArrayList arrayList;
        Cursor rawQuery = this.c.rawQuery("SELECT message_id FROM region_messages INNER JOIN messages on region_messages.message_id = messages.id WHERE region_id = ? AND messages.message_type = ?", new String[]{str, String.valueOf(i)});
        if (rawQuery.moveToFirst()) {
            arrayList = new ArrayList(rawQuery.getCount());
            int columnIndex = rawQuery.getColumnIndex(Constants.MessagePayloadKeys.MSGID_SERVER);
            do {
                arrayList.add(rawQuery.getString(columnIndex));
            } while (rawQuery.moveToNext());
        } else {
            arrayList = null;
        }
        rawQuery.close();
        return arrayList != null ? arrayList : Collections.emptyList();
    }

    private static ContentValues c(Region region, c cVar) throws Exception {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", region.id());
        contentValues.put(h.a.b, cVar.a(String.valueOf(region.center().latitude())));
        contentValues.put(h.a.c, cVar.a(String.valueOf(region.center().longitude())));
        contentValues.put("radius", Integer.valueOf(region.radius()));
        contentValues.put("beacon_guid", cVar.a(region.proximityUuid()));
        contentValues.put("beacon_major", Integer.valueOf(region.major()));
        contentValues.put("beacon_minor", Integer.valueOf(region.minor()));
        contentValues.put("description", cVar.a(region.description()));
        contentValues.put("name", cVar.a(region.name()));
        contentValues.put("location_type", Integer.valueOf(region.regionType()));
        contentValues.put("is_inside", Integer.valueOf(com.salesforce.marketingcloud.internal.j.a(region) ? 1 : 0));
        return contentValues;
    }

    public Region a(String str, c cVar) {
        Cursor rawQuery = this.c.rawQuery("SELECT * FROM regions WHERE id=?", new String[]{str});
        Region region = null;
        if (rawQuery != null) {
            if (rawQuery.moveToFirst()) {
                region = d.c(rawQuery, cVar);
            }
            rawQuery.close();
        }
        return region;
    }

    public List<Region> a(int i, c cVar) {
        List<Region> emptyList = Collections.emptyList();
        Cursor rawQuery = this.c.rawQuery("SELECT * FROM regions WHERE location_type=?", new String[]{String.valueOf(i)});
        if (rawQuery != null) {
            if (rawQuery.moveToFirst()) {
                ArrayList arrayList = new ArrayList(rawQuery.getCount());
                do {
                    Region c = d.c(rawQuery, cVar);
                    if (c != null) {
                        arrayList.add(c);
                    }
                } while (rawQuery.moveToNext());
                emptyList = arrayList;
            }
            rawQuery.close();
        }
        return emptyList;
    }

    private static ContentValues a(Region region, Message message) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("region_id", region.id());
        contentValues.put(Constants.MessagePayloadKeys.MSGID_SERVER, message.id());
        return contentValues;
    }

    public void a(String str, boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("is_inside", Integer.valueOf(z ? 1 : 0));
        this.c.update(e, contentValues, "id=?", new String[]{str});
    }
}
