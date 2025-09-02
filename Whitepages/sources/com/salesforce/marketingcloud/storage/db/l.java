package com.salesforce.marketingcloud.storage.db;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.storage.db.upgrades.b;
import com.salesforce.marketingcloud.storage.db.upgrades.d;
import com.salesforce.marketingcloud.storage.db.upgrades.e;
import com.salesforce.marketingcloud.storage.db.upgrades.f;
import com.salesforce.marketingcloud.storage.db.upgrades.h;
import com.salesforce.marketingcloud.storage.db.upgrades.i;
import com.salesforce.marketingcloud.storage.db.upgrades.j;
import com.salesforce.marketingcloud.storage.exceptions.a;
import com.salesforce.marketingcloud.util.c;
import java.util.Locale;

@SuppressLint({"UnknownNullness"})
public final class l extends SQLiteOpenHelper {
    public static final int d = 11;
    private static final String e = "mcsdk_%s.db";
    private static final String f = g.a("StorageSqliteOpenHelper");
    private final Context a;
    private final c b;
    private boolean c;

    public l(Context context, c cVar, String str) {
        this(context, cVar, a(str), 11);
    }

    public static String a(String str) {
        return String.format(Locale.ENGLISH, e, new Object[]{str});
    }

    public void b() throws Exception {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        a(writableDatabase);
        writableDatabase.execSQL("VACUUM");
        onCreate(writableDatabase);
    }

    public void c() throws a, IllegalStateException {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        if (!k.c(writableDatabase)) {
            g.e(f, "Database table %s was not initialized properly and will be dropped and recreated.  Some data may be lost.", k.e);
            try {
                b();
                if (!k.c(writableDatabase)) {
                    throw new IllegalStateException(String.format(Locale.ENGLISH, "%s could not be initialized.", new Object[]{k.e}));
                }
                throw new a();
            } catch (Exception e2) {
                throw new IllegalStateException(e2.getMessage(), e2);
            }
        } else if (!g.d(writableDatabase)) {
            throw new IllegalStateException(String.format(Locale.ENGLISH, "%s could not be initialized.", new Object[]{g.e}));
        } else if (!a.d(writableDatabase)) {
            throw new IllegalStateException(String.format(Locale.ENGLISH, "%s could not be initialized.", new Object[]{a.e}));
        } else if (!j.g(writableDatabase)) {
            throw new IllegalStateException(String.format(Locale.ENGLISH, "%s could not be initialized.", new Object[]{j.e}));
        } else if (!i.d(writableDatabase)) {
            throw new IllegalStateException(String.format(Locale.ENGLISH, "%s could not be initialized.", new Object[]{i.e}));
        } else if (!h.d(writableDatabase)) {
            throw new IllegalStateException(String.format(Locale.ENGLISH, "%s could not be initialized.", new Object[]{h.e}));
        } else if (!m.d(writableDatabase)) {
            throw new IllegalStateException(String.format(Locale.ENGLISH, "%s could not be initialized.", new Object[]{m.g}));
        } else if (!f.m(writableDatabase)) {
            throw new IllegalStateException(String.format(Locale.ENGLISH, "%s could not be initialized.", new Object[]{f.e}));
        } else if (!e.d(writableDatabase)) {
            throw new IllegalStateException(String.format(Locale.ENGLISH, "%s could not be initialized.", new Object[]{"device_stats"}));
        }
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.beginTransaction();
        try {
            k.b(sQLiteDatabase);
            g.b(sQLiteDatabase);
            a.b(sQLiteDatabase);
            j.d(sQLiteDatabase);
            i.b(sQLiteDatabase);
            h.b(sQLiteDatabase);
            f.i(sQLiteDatabase);
            m.b(sQLiteDatabase);
            e.b(sQLiteDatabase);
            sQLiteDatabase.setTransactionSuccessful();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        g.e(f, "SQLite database being downgraded from %d to %d", Integer.valueOf(i2), Integer.valueOf(i));
        this.c = true;
        a(sQLiteDatabase);
        onCreate(sQLiteDatabase);
    }

    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        super.onOpen(sQLiteDatabase);
        if (!sQLiteDatabase.isReadOnly()) {
            sQLiteDatabase.execSQL("PRAGMA foreign_keys=ON");
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 2) {
            b.b(sQLiteDatabase, this.a, this.b);
        }
        if (i < 3) {
            com.salesforce.marketingcloud.storage.db.upgrades.c.f(sQLiteDatabase);
        }
        if (i < 4) {
            d.c(sQLiteDatabase);
        }
        if (i < 5) {
            e.b(sQLiteDatabase);
        }
        if (i < 6) {
            f.b(sQLiteDatabase);
        }
        if (i < 7) {
            com.salesforce.marketingcloud.storage.db.upgrades.g.b(sQLiteDatabase);
        }
        if (i < 8) {
            h.a(sQLiteDatabase);
        }
        if (i < 9) {
            i.b(sQLiteDatabase);
        }
        if (i < 10) {
            j.b(sQLiteDatabase, this.b);
        }
        if (i < 11) {
            com.salesforce.marketingcloud.storage.db.upgrades.a.b(sQLiteDatabase, this.b);
        }
    }

    l(Context context, c cVar, String str, int i) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, i);
        this.a = context;
        this.b = cVar;
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.beginTransaction();
        try {
            k.a(sQLiteDatabase);
            g.a(sQLiteDatabase);
            a.a(sQLiteDatabase);
            j.c(sQLiteDatabase);
            i.a(sQLiteDatabase);
            h.a(sQLiteDatabase);
            f.e(sQLiteDatabase);
            m.a(sQLiteDatabase);
            e.a(sQLiteDatabase);
            sQLiteDatabase.setTransactionSuccessful();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    public boolean a() {
        return this.c;
    }
}
