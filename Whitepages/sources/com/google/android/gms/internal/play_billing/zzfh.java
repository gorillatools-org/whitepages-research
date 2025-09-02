package com.google.android.gms.internal.play_billing;

import java.io.IOException;

final class zzfh extends zzff {
    zzfh() {
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ int zza(Object obj) {
        return ((zzfg) obj).zza();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ int zzb(Object obj) {
        return ((zzfg) obj).zzb();
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ Object zzc(Object obj) {
        zzcs zzcs = (zzcs) obj;
        zzfg zzfg = zzcs.zzc;
        if (zzfg != zzfg.zzc()) {
            return zzfg;
        }
        zzfg zzf = zzfg.zzf();
        zzcs.zzc = zzf;
        return zzf;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzd(Object obj) {
        return ((zzcs) obj).zzc;
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ Object zze(Object obj, Object obj2) {
        if (zzfg.zzc().equals(obj2)) {
            return obj;
        }
        if (zzfg.zzc().equals(obj)) {
            return zzfg.zze((zzfg) obj, (zzfg) obj2);
        }
        ((zzfg) obj).zzd((zzfg) obj2);
        return obj;
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ void zzf(Object obj, int i, long j) {
        ((zzfg) obj).zzj(i << 3, Long.valueOf(j));
    }

    /* access modifiers changed from: package-private */
    public final void zzg(Object obj) {
        ((zzcs) obj).zzc.zzh();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzh(Object obj, Object obj2) {
        ((zzcs) obj).zzc = (zzfg) obj2;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzi(Object obj, zzfx zzfx) throws IOException {
        ((zzfg) obj).zzk(zzfx);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzj(Object obj, zzfx zzfx) throws IOException {
        ((zzfg) obj).zzl(zzfx);
    }
}
