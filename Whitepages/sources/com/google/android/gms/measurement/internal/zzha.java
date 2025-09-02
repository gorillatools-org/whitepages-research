package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.MethodInvocation;
import com.google.android.gms.common.internal.TelemetryData;
import com.google.android.gms.common.internal.TelemetryLogging;
import com.google.android.gms.common.internal.TelemetryLoggingClient;
import com.google.android.gms.common.internal.TelemetryLoggingOptions;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

public final class zzha {
    private static zzha zza;
    private final zzio zzb;
    private final TelemetryLoggingClient zzc;
    private final AtomicLong zzd = new AtomicLong(-1);

    private zzha(Context context, zzio zzio) {
        this.zzc = TelemetryLogging.getClient(context, TelemetryLoggingOptions.builder().setApi("measurement:api").build());
        this.zzb = zzio;
    }

    static zzha zza(zzio zzio) {
        if (zza == null) {
            zza = new zzha(zzio.zzaT(), zzio);
        }
        return zza;
    }

    public final synchronized void zzc(int i, int i2, long j, long j2, int i3) {
        synchronized (this) {
            long elapsedRealtime = this.zzb.zzaU().elapsedRealtime();
            AtomicLong atomicLong = this.zzd;
            if (atomicLong.get() != -1) {
                if (elapsedRealtime - atomicLong.get() <= 1800000) {
                    return;
                }
            }
            this.zzc.log(new TelemetryData(0, Arrays.asList(new MethodInvocation[]{new MethodInvocation(36301, i2, 0, j, j2, (String) null, (String) null, 0, i3)}))).addOnFailureListener(new zzgz(this, elapsedRealtime));
        }
    }
}
