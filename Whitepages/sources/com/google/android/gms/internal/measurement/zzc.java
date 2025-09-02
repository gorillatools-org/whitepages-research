package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

public final class zzc {
    final zzf zza;
    zzg zzb;
    final zzab zzc = new zzab();
    private final zzz zzd = new zzz();

    public zzc() {
        zzf zzf = new zzf();
        this.zza = zzf;
        this.zzb = zzf.zzb.zza();
        zzf.zzd.zza("internal.registerCallback", new zza(this));
        zzf.zzd.zza("internal.eventLogger", new zzb(this));
    }

    public static /* synthetic */ zzai zzb(zzc zzc2) {
        return new zzv(zzc2.zzd);
    }

    public final zzab zza() {
        return this.zzc;
    }

    public final void zzc(zziv zziv) throws zzd {
        zzai zzai;
        try {
            zzf zzf = this.zza;
            this.zzb = zzf.zzb.zza();
            if (!(zzf.zza(this.zzb, (zziz[]) zziv.zzc().toArray(new zziz[0])) instanceof zzag)) {
                for (zzit zzit : zziv.zza().zzd()) {
                    List zzc2 = zzit.zzc();
                    String zzb2 = zzit.zzb();
                    Iterator it = zzc2.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            zzap zza2 = zzf.zza(this.zzb, (zziz) it.next());
                            if (zza2 instanceof zzam) {
                                zzg zzg = this.zzb;
                                if (!zzg.zzh(zzb2)) {
                                    zzai = null;
                                } else {
                                    zzap zzd2 = zzg.zzd(zzb2);
                                    if (zzd2 instanceof zzai) {
                                        zzai = (zzai) zzd2;
                                    } else {
                                        throw new IllegalStateException("Invalid function name: ".concat(String.valueOf(zzb2)));
                                    }
                                }
                                if (zzai != null) {
                                    zzai.zza(this.zzb, Collections.singletonList(zza2));
                                } else {
                                    throw new IllegalStateException("Rule function is undefined: ".concat(String.valueOf(zzb2)));
                                }
                            } else {
                                throw new IllegalArgumentException("Invalid rule definition");
                            }
                        }
                    }
                }
                return;
            }
            throw new IllegalStateException("Program loading failed");
        } catch (Throwable th) {
            throw new zzd(th);
        }
    }

    public final void zzd(String str, Callable callable) {
        this.zza.zzd.zza(str, callable);
    }

    public final boolean zze(zzaa zzaa) throws zzd {
        try {
            zzab zzab = this.zzc;
            zzab.zzd(zzaa);
            this.zza.zzc.zzg("runtime.counter", new zzah(Double.valueOf(0.0d)));
            this.zzd.zzb(this.zzb.zza(), zzab);
            return zzg() || zzf();
        } catch (Throwable th) {
            throw new zzd(th);
        }
    }

    public final boolean zzf() {
        return !this.zzc.zzc().isEmpty();
    }

    public final boolean zzg() {
        zzab zzab = this.zzc;
        return !zzab.zzb().equals(zzab.zza());
    }
}
