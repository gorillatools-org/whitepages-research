package com.google.android.gms.internal.play_billing;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzcd {
    static final zzcd zza = new zzcd(true);
    private static volatile boolean zzb = false;
    private static volatile zzcd zzc;
    private final Map zzd;

    zzcd() {
        this.zzd = new HashMap();
    }

    public final zzcq zzb(zzec zzec, int i) {
        return (zzcq) this.zzd.get(new zzcc(zzec, i));
    }

    zzcd(boolean z) {
        this.zzd = Collections.emptyMap();
    }

    public static zzcd zza() {
        zzcd zzcd = zzc;
        if (zzcd != null) {
            return zzcd;
        }
        synchronized (zzcd.class) {
            try {
                zzcd zzcd2 = zzc;
                if (zzcd2 != null) {
                    return zzcd2;
                }
                zzcd zzb2 = zzcl.zzb(zzcd.class);
                zzc = zzb2;
                return zzb2;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
