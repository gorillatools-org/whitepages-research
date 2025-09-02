package com.salesforce.marketingcloud.storage.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.util.Collection;

@SuppressLint({"UnknownNullness"})
public abstract class b {
    protected static final String d = "%s = ?";
    protected final SQLiteDatabase c;

    public b(SQLiteDatabase sQLiteDatabase) {
        this.c = sQLiteDatabase;
    }

    /* access modifiers changed from: protected */
    public final int a(String str, String[] strArr) {
        return this.c.delete(o(), str, strArr);
    }

    /* access modifiers changed from: package-private */
    public void b(String str, Collection<String> collection) {
        this.c.execSQL(c.a("DROP TABLE IF EXISTS tmp_%s;", str));
        this.c.execSQL(c.a("CREATE TEMPORARY TABLE tmp_%s(id VARCHAR);", str));
        ContentValues contentValues = new ContentValues();
        for (String put : collection) {
            contentValues.put("id", put);
            this.c.insert(c.a("tmp_%s", str), (String) null, contentValues);
        }
    }

    /* access modifiers changed from: package-private */
    public final int c(Collection<String> collection) throws SQLException {
        return a(o(), collection, false);
    }

    /* access modifiers changed from: package-private */
    public void h(String str) {
        this.c.execSQL(c.a("DROP TABLE IF EXISTS tmp_%s;", str));
    }

    /* access modifiers changed from: protected */
    public final int i(String str) {
        return a(str, (String[]) null);
    }

    /* access modifiers changed from: package-private */
    public abstract String o();

    private int a(String str, Collection<String> collection, boolean z) {
        try {
            this.c.beginTransaction();
            b(str, collection);
            int delete = this.c.delete(str, c.a("id IN(SELECT %1$s.id FROM %1$s LEFT JOIN tmp_%1$s ON %1$s.id = tmp_%1$s.id WHERE tmp_%1$s.id %2$s)", str, z ? "IS NULL" : "IS NOT NULL"), (String[]) null);
            h(str);
            this.c.setTransactionSuccessful();
            this.c.endTransaction();
            return delete;
        } catch (SQLException e) {
            this.c.endTransaction();
            throw e;
        }
    }

    /* access modifiers changed from: package-private */
    public final int a(String str, Collection<String> collection) throws SQLException {
        return a(str, collection, true);
    }

    /* access modifiers changed from: protected */
    public final long a(ContentValues contentValues) {
        return this.c.insert(o(), (String) null, contentValues);
    }

    /* access modifiers changed from: protected */
    public final Cursor a(String str, String[] strArr, String str2, String[] strArr2) {
        return this.c.query(str, strArr, str2, strArr2, (String) null, (String) null, (String) null);
    }

    /* access modifiers changed from: protected */
    public final Cursor a(String[] strArr, String str) {
        return a(strArr, str, (String[]) null, (String) null, (String) null, (String) null, (String) null);
    }

    /* access modifiers changed from: protected */
    public final Cursor a(String[] strArr, String str, String[] strArr2) {
        return a(strArr, str, strArr2, (String) null, (String) null, (String) null, (String) null);
    }

    /* access modifiers changed from: protected */
    public final Cursor a(String[] strArr, String str, String[] strArr2, String str2, String str3, String str4) {
        return this.c.query(false, o(), strArr, str, strArr2, str2, str3, str4, (String) null);
    }

    /* access modifiers changed from: protected */
    public final Cursor a(String[] strArr, String str, String[] strArr2, String str2, String str3, String str4, String str5) {
        return this.c.query(false, o(), strArr, str, strArr2, str2, str3, str4, str5);
    }

    /* access modifiers changed from: protected */
    public final int a(ContentValues contentValues, String str, String[] strArr) {
        return this.c.update(o(), contentValues, str, strArr);
    }

    /* access modifiers changed from: package-private */
    public final void a(ContentValues contentValues, Collection<String> collection) {
        try {
            this.c.beginTransactionNonExclusive();
            b(o(), collection);
            a(contentValues, c.a("id IN(SELECT %1$s.id FROM %1$s LEFT JOIN tmp_%1$s ON %1$s.id = tmp_%1$s.id WHERE tmp_%1$s.id IS NOT NULL)", o()), (String[]) null);
            h(o());
            this.c.setTransactionSuccessful();
            this.c.endTransaction();
        } catch (SQLException e) {
            this.c.endTransaction();
            throw e;
        }
    }
}
