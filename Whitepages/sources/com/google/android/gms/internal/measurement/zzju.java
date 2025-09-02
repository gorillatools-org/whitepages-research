package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.util.Log;
import androidx.core.content.PermissionChecker;
import com.google.common.base.Preconditions;

final class zzju implements zzjr {
    private static zzju zza;
    private final Context zzb;
    private final ContentObserver zzc;
    private boolean zzd;

    private zzju() {
        this.zzd = false;
        this.zzb = null;
        this.zzc = null;
    }

    private zzju(Context context) {
        this.zzd = false;
        this.zzb = context;
        this.zzc = new zzjt(this, (Handler) null);
    }

    static zzju zza(Context context) {
        zzju zzju;
        synchronized (zzju.class) {
            try {
                if (zza == null) {
                    zza = PermissionChecker.checkSelfPermission(context, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0 ? new zzju(context) : new zzju();
                }
                zzju zzju2 = zza;
                if (!(zzju2 == null || zzju2.zzc == null || zzju2.zzd)) {
                    context.getContentResolver().registerContentObserver(zzjc.zza, true, zza.zzc);
                    ((zzju) Preconditions.checkNotNull(zza)).zzd = true;
                }
            } catch (SecurityException e) {
                Log.e("GservicesLoader", "Unable to register Gservices content observer", e);
            } catch (Throwable th) {
                throw th;
            }
            zzju = (zzju) Preconditions.checkNotNull(zza);
        }
        return zzju;
    }

    static synchronized void zze() {
        Context context;
        synchronized (zzju.class) {
            try {
                zzju zzju = zza;
                if (!(zzju == null || (context = zzju.zzb) == null || zzju.zzc == null || !zzju.zzd)) {
                    context.getContentResolver().unregisterContentObserver(zza.zzc);
                }
                zza = null;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    /* renamed from: zzd */
    public final String zzb(String str) {
        Context context = this.zzb;
        if (context != null && !zzji.zza(context)) {
            try {
                return (String) zzjp.zza(new zzjs(this, str));
            } catch (IllegalStateException | NullPointerException | SecurityException e) {
                Log.e("GservicesLoader", "Unable to read GServices for: ".concat(str), e);
            }
        }
        return null;
    }
}
