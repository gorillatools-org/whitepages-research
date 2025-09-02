package com.salesforce.marketingcloud.storage.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.Locale;

class c {
    private c() {
    }

    static String a(String str, Object... objArr) {
        return String.format(Locale.ENGLISH, str, objArr);
    }

    static boolean a(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        Cursor rawQuery = sQLiteDatabase.rawQuery(String.format(Locale.ENGLISH, "SELECT EXISTS(SELECT 1 FROM sqlite_master WHERE type='%s' and name='%s')", new Object[]{str, str2}), (String[]) null);
        boolean z = false;
        if (rawQuery.moveToFirst() && rawQuery.getInt(0) == 0) {
            z = true;
        }
        rawQuery.close();
        return z;
    }
}
