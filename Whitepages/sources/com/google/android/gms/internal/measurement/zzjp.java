package com.google.android.gms.internal.measurement;

import android.os.Binder;

public final /* synthetic */ class zzjp {
    public static Object zza(zzjq zzjq) {
        long clearCallingIdentity;
        try {
            return zzjq.zza();
        } catch (SecurityException unused) {
            clearCallingIdentity = Binder.clearCallingIdentity();
            Object zza = zzjq.zza();
            Binder.restoreCallingIdentity(clearCallingIdentity);
            return zza;
        } catch (Throwable th) {
            Binder.restoreCallingIdentity(clearCallingIdentity);
            throw th;
        }
    }
}
