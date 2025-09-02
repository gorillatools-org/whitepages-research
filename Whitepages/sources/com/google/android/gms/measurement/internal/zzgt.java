package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import com.google.android.gms.internal.measurement.zzca;
import com.salesforce.marketingcloud.storage.db.i;

final class zzgt extends zzca {
    final /* synthetic */ zzgv zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzgt(zzgv zzgv, Context context, String str) {
        super(context, "google_app_measurement_local.db", (SQLiteDatabase.CursorFactory) null, 1);
        this.zza = zzgv;
    }

    public final SQLiteDatabase getWritableDatabase() throws SQLiteException {
        try {
            return super.getWritableDatabase();
        } catch (SQLiteDatabaseLockedException e) {
            throw e;
        } catch (SQLiteException unused) {
            zzio zzio = this.zza.zzu;
            zzio.zzaW().zze().zza("Opening the local database failed, dropping and recreating it");
            zzio.zzf();
            if (!zzio.zzaT().getDatabasePath("google_app_measurement_local.db").delete()) {
                zzio.zzaW().zze().zzb("Failed to delete corrupted local db file", "google_app_measurement_local.db");
            }
            try {
                return super.getWritableDatabase();
            } catch (SQLiteException e2) {
                this.zza.zzu.zzaW().zze().zzb("Failed to open local database. Events will bypass local storage", e2);
                return null;
            }
        }
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        zzax.zzb(this.zza.zzu.zzaW(), sQLiteDatabase);
    }

    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public final void onOpen(SQLiteDatabase sQLiteDatabase) {
        zzax.zza(this.zza.zzu.zzaW(), sQLiteDatabase, i.e, "create table if not exists messages ( type INTEGER NOT NULL, entry BLOB NOT NULL)", "type,entry", zzgv.zza);
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
