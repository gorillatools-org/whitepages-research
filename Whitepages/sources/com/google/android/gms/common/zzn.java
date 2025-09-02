package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.os.StrictMode;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zzaf;
import com.google.android.gms.common.internal.zzag;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Hex;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import java.security.MessageDigest;

final class zzn {
    static final zzl zza = new zzf(zzj.zze("0\u0005È0\u0003° \u0003\u0002\u0001\u0002\u0002\u0014\u0010e\bsù/Qí"));
    static final zzl zzb = new zzg(zzj.zze("0\u0006\u00040\u0003ì \u0003\u0002\u0001\u0002\u0002\u0014\u0003£²­×árÊkì"));
    static final zzl zzc = new zzh(zzj.zze("0\u0004C0\u0003+ \u0003\u0002\u0001\u0002\u0002\t\u0000ÂàFdJ00"));
    static final zzl zzd = new zzi(zzj.zze("0\u0004¨0\u0003 \u0003\u0002\u0001\u0002\u0002\t\u0000Õ¸l}ÓNõ0"));
    private static volatile zzag zze;
    private static final Object zzf = new Object();
    private static Context zzg;

    static zzw zza(String str, zzj zzj, boolean z, boolean z2) {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            return zzh(str, zzj, z, z2);
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    static zzw zzb(String str, boolean z, boolean z2, boolean z3) {
        return zzi(str, z, false, false, true);
    }

    static zzw zzc(String str, boolean z, boolean z2, boolean z3) {
        return zzi(str, z, false, false, false);
    }

    static /* synthetic */ String zzd(boolean z, String str, zzj zzj) throws Exception {
        String str2;
        if (z || !zzh(str, zzj, true, false).zza) {
            str2 = "not allowed";
        } else {
            str2 = "debug cert rejected";
        }
        MessageDigest zza2 = AndroidUtilsLight.zza("SHA-256");
        Preconditions.checkNotNull(zza2);
        return String.format("%s: pkg=%s, sha256=%s, atk=%s, ver=%s", new Object[]{str2, str, Hex.bytesToStringLowercase(zza2.digest(zzj.zzf())), Boolean.valueOf(z), "12451000.false"});
    }

    static synchronized void zze(Context context) {
        synchronized (zzn.class) {
            if (zzg != null) {
                Log.w("GoogleCertificates", "GoogleCertificates has been initialized already");
            } else if (context != null) {
                zzg = context.getApplicationContext();
            }
        }
    }

    static boolean zzf() {
        boolean z;
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            zzj();
            z = zze.zzg();
        } catch (RemoteException | DynamiteModule.LoadingException e) {
            Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);
            z = false;
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            throw th;
        }
        StrictMode.setThreadPolicy(allowThreadDiskReads);
        return z;
    }

    static boolean zzg() {
        boolean z;
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            zzj();
            z = zze.zzi();
        } catch (RemoteException | DynamiteModule.LoadingException e) {
            Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);
            z = false;
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            throw th;
        }
        StrictMode.setThreadPolicy(allowThreadDiskReads);
        return z;
    }

    private static zzw zzh(String str, zzj zzj, boolean z, boolean z2) {
        try {
            zzj();
            Preconditions.checkNotNull(zzg);
            try {
                return zze.zzh(new zzs(str, zzj, z, z2), ObjectWrapper.wrap(zzg.getPackageManager())) ? zzw.zzb() : new zzu(new zze(z, str, zzj), (zzv) null);
            } catch (RemoteException e) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);
                return zzw.zzd("module call", e);
            }
        } catch (DynamiteModule.LoadingException e2) {
            Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e2);
            return zzw.zzd("module init: ".concat(String.valueOf(e2.getMessage())), e2);
        }
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [com.google.android.gms.dynamic.IObjectWrapper, android.os.IBinder] */
    private static zzw zzi(String str, boolean z, boolean z2, boolean z3, boolean z4) {
        zzw zzw;
        zzq zzq;
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            Preconditions.checkNotNull(zzg);
            try {
                zzj();
                zzo zzo = new zzo(str, z, false, ObjectWrapper.wrap(zzg), false, true);
                if (z4) {
                    zzq = zze.zze(zzo);
                } else {
                    zzq = zze.zzf(zzo);
                }
                if (zzq.zzb()) {
                    zzw = zzw.zzf(zzq.zzc());
                } else {
                    String zza2 = zzq.zza();
                    PackageManager.NameNotFoundException nameNotFoundException = zzq.zzd() == 4 ? new PackageManager.NameNotFoundException() : null;
                    if (zza2 == null) {
                        zza2 = "error checking package certificate";
                    }
                    zzw = zzw.zzg(zzq.zzc(), zzq.zzd(), zza2, nameNotFoundException);
                }
            } catch (DynamiteModule.LoadingException e) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);
                zzw = zzw.zzd("module init: ".concat(String.valueOf(e.getMessage())), e);
            }
        } catch (RemoteException e2) {
            Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e2);
            zzw = zzw.zzd("module call", e2);
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            throw th;
        }
        StrictMode.setThreadPolicy(allowThreadDiskReads);
        return zzw;
    }

    private static void zzj() throws DynamiteModule.LoadingException {
        if (zze == null) {
            Preconditions.checkNotNull(zzg);
            synchronized (zzf) {
                try {
                    if (zze == null) {
                        zze = zzaf.zzb(DynamiteModule.load(zzg, DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING, "com.google.android.gms.googlecertificates").instantiate("com.google.android.gms.common.GoogleCertificatesImpl"));
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }
}
