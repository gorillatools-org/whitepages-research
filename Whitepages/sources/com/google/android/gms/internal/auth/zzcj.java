package com.google.android.gms.internal.auth;

import android.os.Binder;

public final /* synthetic */ class zzcj {
    public static Object zza(zzck zzck) {
        long clearCallingIdentity;
        try {
            return zzck.zza();
        } catch (SecurityException unused) {
            clearCallingIdentity = Binder.clearCallingIdentity();
            Object zza = zzck.zza();
            Binder.restoreCallingIdentity(clearCallingIdentity);
            return zza;
        } catch (Throwable th) {
            Binder.restoreCallingIdentity(clearCallingIdentity);
            throw th;
        }
    }
}
