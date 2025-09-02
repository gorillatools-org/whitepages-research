package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.common.base.Preconditions;

public final class zzci {
    private static final ThreadLocal zza = new zzch();

    public static SharedPreferences zza(Context context, String str, int i, zzcc zzcc) {
        zzbx.zza();
        zzcg zzcg = str.equals("") ? new zzcg() : null;
        if (zzcg != null) {
            return zzcg;
        }
        ThreadLocal threadLocal = zza;
        Preconditions.checkArgument(((Boolean) threadLocal.get()).booleanValue());
        threadLocal.set(Boolean.FALSE);
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
            threadLocal.set(Boolean.TRUE);
            return sharedPreferences;
        } catch (Throwable th) {
            zza.set(Boolean.TRUE);
            throw th;
        }
    }
}
