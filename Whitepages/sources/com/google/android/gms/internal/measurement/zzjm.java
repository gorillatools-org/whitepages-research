package com.google.android.gms.internal.measurement;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Handler;
import android.os.RemoteException;
import android.os.StrictMode;
import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzjm implements zzjr {
    public static final String[] zza = {"key", "value"};
    private static final Map zzb = new ArrayMap();
    private final ContentResolver zzc;
    private final Uri zzd;
    private final Runnable zze;
    private final ContentObserver zzf;
    private final Object zzg = new Object();
    private volatile Map zzh;
    private final List zzi = new ArrayList();

    private zzjm(ContentResolver contentResolver, Uri uri, Runnable runnable) {
        Preconditions.checkNotNull(contentResolver);
        Preconditions.checkNotNull(uri);
        this.zzc = contentResolver;
        this.zzd = uri;
        this.zze = runnable;
        this.zzf = new zzjl(this, (Handler) null);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:2|3|(5:5|6|7|8|10)|12|13) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x001f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.measurement.zzjm zza(android.content.ContentResolver r4, android.net.Uri r5, java.lang.Runnable r6) {
        /*
            java.lang.Class<com.google.android.gms.internal.measurement.zzjm> r0 = com.google.android.gms.internal.measurement.zzjm.class
            monitor-enter(r0)
            java.util.Map r1 = zzb     // Catch:{ all -> 0x001c }
            java.lang.Object r2 = r1.get(r5)     // Catch:{ all -> 0x001c }
            com.google.android.gms.internal.measurement.zzjm r2 = (com.google.android.gms.internal.measurement.zzjm) r2     // Catch:{ all -> 0x001c }
            if (r2 != 0) goto L_0x001f
            com.google.android.gms.internal.measurement.zzjm r3 = new com.google.android.gms.internal.measurement.zzjm     // Catch:{ SecurityException -> 0x001f }
            r3.<init>(r4, r5, r6)     // Catch:{ SecurityException -> 0x001f }
            android.database.ContentObserver r6 = r3.zzf     // Catch:{ SecurityException -> 0x001e }
            r2 = 0
            r4.registerContentObserver(r5, r2, r6)     // Catch:{ SecurityException -> 0x001e }
            r1.put(r5, r3)     // Catch:{ SecurityException -> 0x001e }
            goto L_0x001e
        L_0x001c:
            r4 = move-exception
            goto L_0x0021
        L_0x001e:
            r2 = r3
        L_0x001f:
            monitor-exit(r0)     // Catch:{ all -> 0x001c }
            return r2
        L_0x0021:
            monitor-exit(r0)     // Catch:{ all -> 0x001c }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjm.zza(android.content.ContentResolver, android.net.Uri, java.lang.Runnable):com.google.android.gms.internal.measurement.zzjm");
    }

    public static /* synthetic */ Map zzc(zzjm zzjm) {
        Map emptyMap;
        Cursor query;
        Map map;
        Map emptyMap2;
        ContentResolver contentResolver = zzjm.zzc;
        Uri uri = zzjm.zzd;
        ContentProviderClient acquireUnstableContentProviderClient = contentResolver.acquireUnstableContentProviderClient(uri);
        if (acquireUnstableContentProviderClient == null) {
            Log.w("ConfigurationContentLdr", "Unable to acquire ContentProviderClient, using default values");
            return Collections.emptyMap();
        }
        try {
            query = acquireUnstableContentProviderClient.query(uri, zza, (String) null, (String[]) null, (String) null);
            if (query == null) {
                Log.w("ConfigurationContentLdr", "ContentProvider query returned null cursor, using default values");
                emptyMap = Collections.emptyMap();
                acquireUnstableContentProviderClient.release();
                return emptyMap;
            }
            int count = query.getCount();
            if (count == 0) {
                emptyMap2 = Collections.emptyMap();
            } else {
                if (count <= 256) {
                    map = new ArrayMap(count);
                } else {
                    map = new HashMap(count, 1.0f);
                }
                while (query.moveToNext()) {
                    map.put(query.getString(0), query.getString(1));
                }
                if (!query.isAfterLast()) {
                    Log.w("ConfigurationContentLdr", "Cursor read incomplete (ContentProvider dead?), using default values");
                    emptyMap2 = Collections.emptyMap();
                } else {
                    query.close();
                    acquireUnstableContentProviderClient.release();
                    return map;
                }
            }
            query.close();
            acquireUnstableContentProviderClient.release();
            return emptyMap2;
        } catch (RemoteException e) {
            try {
                Log.w("ConfigurationContentLdr", "ContentProvider query failed, using default values", e);
                emptyMap = Collections.emptyMap();
            } catch (Throwable th) {
                acquireUnstableContentProviderClient.release();
                throw th;
            }
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    static synchronized void zze() {
        synchronized (zzjm.class) {
            try {
                Map map = zzb;
                for (zzjm zzjm : map.values()) {
                    zzjm.zzc.unregisterContentObserver(zzjm.zzf);
                }
                map.clear();
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    public final /* bridge */ /* synthetic */ Object zzb(String str) {
        return (String) zzd().get(str);
    }

    public final Map zzd() {
        Map emptyMap;
        Map map = this.zzh;
        if (map == null) {
            synchronized (this.zzg) {
                map = this.zzh;
                if (map == null) {
                    StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                    try {
                        emptyMap = (Map) zzjp.zza(new zzjk(this));
                    } catch (SQLiteException | IllegalStateException | SecurityException e) {
                        try {
                            Log.w("ConfigurationContentLdr", "Unable to query ContentProvider, using default values", e);
                            emptyMap = Collections.emptyMap();
                        } catch (Throwable th) {
                            StrictMode.setThreadPolicy(allowThreadDiskReads);
                            throw th;
                        }
                    }
                    StrictMode.setThreadPolicy(allowThreadDiskReads);
                    this.zzh = emptyMap;
                    map = emptyMap;
                }
            }
        }
        if (map != null) {
            return map;
        }
        return Collections.emptyMap();
    }

    public final void zzf() {
        synchronized (this.zzg) {
            this.zzh = null;
            this.zze.run();
        }
        synchronized (this) {
            try {
                for (zzjn zza2 : this.zzi) {
                    zza2.zza();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
