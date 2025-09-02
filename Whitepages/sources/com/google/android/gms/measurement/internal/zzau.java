package com.google.android.gms.measurement.internal;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzhl;
import com.google.android.gms.internal.measurement.zzhm;
import com.google.firebase.messaging.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class zzau {
    final /* synthetic */ zzaw zza;
    private final String zzb;
    private long zzc;

    public zzau(zzaw zzaw, String str) {
        this.zza = zzaw;
        Preconditions.checkNotEmpty(str);
        this.zzb = str;
        this.zzc = -1;
    }

    public final List zza() {
        List arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            cursor = this.zza.zzj().query("raw_events", new String[]{"rowid", "name", "timestamp", "metadata_fingerprint", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "realtime"}, "app_id = ? and rowid > ?", new String[]{this.zzb, String.valueOf(this.zzc)}, (String) null, (String) null, "rowid", "1000");
            if (cursor.moveToFirst()) {
                do {
                    boolean z = false;
                    long j = cursor.getLong(0);
                    long j2 = cursor.getLong(3);
                    if (cursor.getLong(5) == 1) {
                        z = true;
                    }
                    byte[] blob = cursor.getBlob(4);
                    if (j > this.zzc) {
                        this.zzc = j;
                    }
                    try {
                        zzhl zzhl = (zzhl) zzqa.zzp(zzhm.zze(), blob);
                        String string = cursor.getString(1);
                        if (string == null) {
                            string = "";
                        }
                        zzhl.zzi(string);
                        zzhl.zzm(cursor.getLong(2));
                        arrayList.add(new zzat(j, j2, z, (zzhm) zzhl.zzba()));
                    } catch (IOException e) {
                        this.zza.zzu.zzaW().zze().zzc("Data loss. Failed to merge raw event. appId", zzhe.zzn(this.zzb), e);
                    }
                } while (cursor.moveToNext());
            } else {
                arrayList = Collections.emptyList();
            }
        } catch (SQLiteException e2) {
            this.zza.zzu.zzaW().zze().zzc("Data loss. Error querying raw events batch. appId", zzhe.zzn(this.zzb), e2);
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        if (cursor != null) {
            cursor.close();
        }
        return arrayList;
    }

    public zzau(zzaw zzaw, String str, long j) {
        this.zza = zzaw;
        Preconditions.checkNotEmpty(str);
        this.zzb = str;
        this.zzc = zzaw.zzaz("select rowid from raw_events where app_id = ? and timestamp < ? order by rowid desc limit 1", new String[]{str, String.valueOf(j)}, -1);
    }
}
