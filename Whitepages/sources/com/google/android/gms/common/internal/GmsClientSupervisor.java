package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.HandlerThread;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.concurrent.Executor;

@KeepForSdk
public abstract class GmsClientSupervisor {
    static HandlerThread zza = null;
    private static final Object zzb = new Object();
    private static int zzc = 9;
    private static zzs zzd = null;
    private static Executor zze = null;
    private static boolean zzf = false;

    @KeepForSdk
    public static int getDefaultBindFlags() {
        return 4225;
    }

    @KeepForSdk
    public static GmsClientSupervisor getInstance(Context context) {
        Looper looper;
        synchronized (zzb) {
            try {
                if (zzd == null) {
                    Context applicationContext = context.getApplicationContext();
                    if (zzf) {
                        looper = getOrStartHandlerThread().getLooper();
                    } else {
                        looper = context.getMainLooper();
                    }
                    zzd = new zzs(applicationContext, looper, zze);
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return zzd;
    }

    @KeepForSdk
    public static HandlerThread getOrStartHandlerThread() {
        synchronized (zzb) {
            try {
                HandlerThread handlerThread = zza;
                if (handlerThread != null) {
                    return handlerThread;
                }
                HandlerThread handlerThread2 = new HandlerThread("GoogleApiHandler", zzc);
                zza = handlerThread2;
                handlerThread2.start();
                HandlerThread handlerThread3 = zza;
                return handlerThread3;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @KeepForSdk
    public static void setDefaultBindExecutor(Executor executor) {
        synchronized (zzb) {
            try {
                zzs zzs = zzd;
                if (zzs != null) {
                    zzs.zzi(executor);
                }
                zze = executor;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @KeepForSdk
    public static boolean setGamHandlerThreadPriorityIfNotInitialized(int i) {
        synchronized (zzb) {
            try {
                if (zza != null) {
                    return false;
                }
                zzc = i;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @KeepForSdk
    public static void setUseHandlerThreadForCallbacks() {
        synchronized (zzb) {
            try {
                zzs zzs = zzd;
                if (zzs != null && !zzf) {
                    zzs.zzj(getOrStartHandlerThread().getLooper());
                }
                zzf = true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @KeepForSdk
    public boolean bindService(ComponentName componentName, ServiceConnection serviceConnection, String str) {
        return zza(new zzo(componentName, 4225), serviceConnection, str, (Executor) null).isSuccess();
    }

    @KeepForSdk
    public void unbindService(ComponentName componentName, ServiceConnection serviceConnection, String str) {
        zzb(new zzo(componentName, 4225), serviceConnection, str);
    }

    /* access modifiers changed from: protected */
    public abstract ConnectionResult zza(zzo zzo, ServiceConnection serviceConnection, String str, Executor executor);

    /* access modifiers changed from: protected */
    public abstract void zzb(zzo zzo, ServiceConnection serviceConnection, String str);

    public final void zzc(String str, String str2, int i, ServiceConnection serviceConnection, String str3, boolean z) {
        zzb(new zzo(str, str2, 4225, z), serviceConnection, str3);
    }

    @KeepForSdk
    public void unbindService(String str, ServiceConnection serviceConnection, String str2) {
        zzb(new zzo(str, 4225, false), serviceConnection, str2);
    }

    @KeepForSdk
    public boolean bindService(ComponentName componentName, ServiceConnection serviceConnection, String str, Executor executor) {
        return zza(new zzo(componentName, 4225), serviceConnection, str, executor).isSuccess();
    }

    @KeepForSdk
    public static HandlerThread getOrStartHandlerThread(int i) {
        synchronized (zzb) {
            try {
                HandlerThread handlerThread = zza;
                if (handlerThread != null) {
                    return handlerThread;
                }
                HandlerThread handlerThread2 = new HandlerThread("GoogleApiHandler", i);
                zza = handlerThread2;
                handlerThread2.start();
                HandlerThread handlerThread3 = zza;
                return handlerThread3;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @KeepForSdk
    public boolean bindService(String str, ServiceConnection serviceConnection, String str2) {
        return zza(new zzo(str, 4225, false), serviceConnection, str2, (Executor) null).isSuccess();
    }
}
