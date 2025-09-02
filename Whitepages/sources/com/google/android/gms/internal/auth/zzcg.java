package com.google.android.gms.internal.auth;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import androidx.collection.ArrayMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzcg implements zzcl {
    public static final String[] zza = {"key", "value"};
    private static final Map zzb = new ArrayMap();
    private final ContentResolver zzc;
    private final Uri zzd;
    private final Runnable zze;
    private final ContentObserver zzf;
    private final Object zzg = new Object();
    private volatile Map zzh;
    private final List zzi = new ArrayList();

    private zzcg(ContentResolver contentResolver, Uri uri, Runnable runnable) {
        zzcf zzcf = new zzcf(this, (Handler) null);
        this.zzf = zzcf;
        contentResolver.getClass();
        uri.getClass();
        this.zzc = contentResolver;
        this.zzd = uri;
        this.zze = runnable;
        contentResolver.registerContentObserver(uri, false, zzcf);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:2|3|(5:5|6|7|8|10)|12|13) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0019 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.auth.zzcg zza(android.content.ContentResolver r4, android.net.Uri r5, java.lang.Runnable r6) {
        /*
            java.lang.Class<com.google.android.gms.internal.auth.zzcg> r0 = com.google.android.gms.internal.auth.zzcg.class
            monitor-enter(r0)
            java.util.Map r1 = zzb     // Catch:{ all -> 0x0016 }
            java.lang.Object r2 = r1.get(r5)     // Catch:{ all -> 0x0016 }
            com.google.android.gms.internal.auth.zzcg r2 = (com.google.android.gms.internal.auth.zzcg) r2     // Catch:{ all -> 0x0016 }
            if (r2 != 0) goto L_0x0019
            com.google.android.gms.internal.auth.zzcg r3 = new com.google.android.gms.internal.auth.zzcg     // Catch:{ SecurityException -> 0x0019 }
            r3.<init>(r4, r5, r6)     // Catch:{ SecurityException -> 0x0019 }
            r1.put(r5, r3)     // Catch:{ SecurityException -> 0x0018 }
            goto L_0x0018
        L_0x0016:
            r4 = move-exception
            goto L_0x001b
        L_0x0018:
            r2 = r3
        L_0x0019:
            monitor-exit(r0)     // Catch:{ all -> 0x0016 }
            return r2
        L_0x001b:
            monitor-exit(r0)     // Catch:{ all -> 0x0016 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.zzcg.zza(android.content.ContentResolver, android.net.Uri, java.lang.Runnable):com.google.android.gms.internal.auth.zzcg");
    }

    static synchronized void zzd() {
        synchronized (zzcg.class) {
            try {
                for (zzcg zzcg : zzb.values()) {
                    zzcg.zzc.unregisterContentObserver(zzcg.zzf);
                }
                zzb.clear();
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public final /* bridge */ /* synthetic */ Object zzb(String str) {
        Map map;
        Map map2 = this.zzh;
        if (map2 == null) {
            synchronized (this.zzg) {
                map2 = this.zzh;
                if (map2 == null) {
                    StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                    try {
                        map = (Map) zzcj.zza(new zzce(this));
                        StrictMode.setThreadPolicy(allowThreadDiskReads);
                    } catch (SQLiteException | IllegalStateException | SecurityException unused) {
                        try {
                            Log.e("ConfigurationContentLdr", "PhenotypeFlag unable to load ContentProvider, using default values");
                            StrictMode.setThreadPolicy(allowThreadDiskReads);
                            map = null;
                        } catch (Throwable th) {
                            StrictMode.setThreadPolicy(allowThreadDiskReads);
                            throw th;
                        }
                    }
                    this.zzh = map;
                    map2 = map;
                }
            }
        }
        if (map2 == null) {
            map2 = Collections.emptyMap();
        }
        return (String) map2.get(str);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Map zzc() {
        Map map;
        Cursor query = this.zzc.query(this.zzd, zza, (String) null, (String[]) null, (String) null);
        if (query == null) {
            return Collections.emptyMap();
        }
        try {
            int count = query.getCount();
            if (count == 0) {
                return Collections.emptyMap();
            }
            if (count <= 256) {
                map = new ArrayMap(count);
            } else {
                map = new HashMap(count, 1.0f);
            }
            while (query.moveToNext()) {
                map.put(query.getString(0), query.getString(1));
            }
            query.close();
            return map;
        } finally {
            query.close();
        }
    }

    public final void zze() {
        synchronized (this.zzg) {
            this.zzh = null;
            zzdc.zzc();
        }
        synchronized (this) {
            try {
                for (zzch zza2 : this.zzi) {
                    zza2.zza();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
