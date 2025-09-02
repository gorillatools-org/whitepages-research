package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import androidx.collection.ArrayMap;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class zzkk implements zzjr {
    private static final Map zza = new ArrayMap();
    private final SharedPreferences zzb;
    private final Runnable zzc;
    private SharedPreferences.OnSharedPreferenceChangeListener zzd;
    private final Object zze = new Object();
    private volatile Map zzf;
    private final List zzg = new ArrayList();

    private zzkk(SharedPreferences sharedPreferences, Runnable runnable) {
        this.zzb = sharedPreferences;
        this.zzc = runnable;
    }

    static zzkk zza(Context context, String str, Runnable runnable) {
        zzkk zzkk;
        SharedPreferences zza2;
        if (zzji.zzc() && !str.startsWith("direct_boot:") && !zzji.zzb(context)) {
            return null;
        }
        synchronized (zzkk.class) {
            Map map = zza;
            zzkk = (zzkk) map.get(str);
            if (zzkk == null) {
                StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                try {
                    if (str.startsWith("direct_boot:")) {
                        if (zzji.zzc()) {
                            context = context.createDeviceProtectedStorageContext();
                        }
                        zza2 = zzci.zza(context, str.substring(12), 0, zzcd.zza);
                    } else {
                        zza2 = zzci.zza(context, str, 0, zzcd.zza);
                    }
                    StrictMode.setThreadPolicy(allowThreadDiskReads);
                    zzkk = new zzkk(zza2, runnable);
                    zzkj zzkj = new zzkj(zzkk);
                    zzkk.zzd = zzkj;
                    zzkk.zzb.registerOnSharedPreferenceChangeListener(zzkj);
                    map.put(str, zzkk);
                } catch (Throwable th) {
                    StrictMode.setThreadPolicy(allowThreadDiskReads);
                    throw th;
                }
            }
        }
        return zzkk;
    }

    public static /* synthetic */ void zzc(zzkk zzkk, SharedPreferences sharedPreferences, String str) {
        synchronized (zzkk.zze) {
            zzkk.zzf = null;
            zzki.zzc();
        }
        synchronized (zzkk) {
            try {
                for (zzjn zza2 : zzkk.zzg) {
                    zza2.zza();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    static synchronized void zzd() {
        synchronized (zzkk.class) {
            try {
                Map map = zza;
                for (zzkk zzkk : map.values()) {
                    zzkk.zzb.unregisterOnSharedPreferenceChangeListener((SharedPreferences.OnSharedPreferenceChangeListener) Preconditions.checkNotNull(zzkk.zzd));
                }
                map.clear();
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    public final Object zzb(String str) {
        StrictMode.ThreadPolicy allowThreadDiskReads;
        Map<String, ?> map = this.zzf;
        if (map == null) {
            synchronized (this.zze) {
                try {
                    map = this.zzf;
                    if (map == null) {
                        allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                        Map<String, ?> all = this.zzb.getAll();
                        this.zzf = all;
                        StrictMode.setThreadPolicy(allowThreadDiskReads);
                        map = all;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        if (map != null) {
            return map.get(str);
        }
        return null;
    }
}
