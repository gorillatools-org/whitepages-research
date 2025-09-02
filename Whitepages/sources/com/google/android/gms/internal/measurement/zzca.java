package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class zzca extends SQLiteOpenHelper {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzca(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        super(context, true == str.equals("") ? null : str, (SQLiteDatabase.CursorFactory) null, 1);
        int i2 = zzcc.zzb;
        zzbx.zza();
    }
}
