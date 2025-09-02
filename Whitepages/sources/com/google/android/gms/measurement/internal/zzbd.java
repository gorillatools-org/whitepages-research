package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

final class zzbd {
    final String zza;
    final String zzb;
    final long zzc;
    final long zzd;
    final long zze;
    final long zzf;
    final long zzg;
    final Long zzh;
    final Long zzi;
    final Long zzj;
    final Boolean zzk;

    zzbd(String str, String str2, long j, long j2, long j3, long j4, long j5, Long l, Long l2, Long l3, Boolean bool) {
        long j6 = j;
        long j7 = j2;
        long j8 = j3;
        long j9 = j5;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        boolean z = false;
        Preconditions.checkArgument(j6 >= 0);
        Preconditions.checkArgument(j7 >= 0);
        Preconditions.checkArgument(j8 >= 0);
        Preconditions.checkArgument(j9 >= 0 ? true : z);
        this.zza = str;
        this.zzb = str2;
        this.zzc = j6;
        this.zzd = j7;
        this.zze = j8;
        this.zzf = j4;
        this.zzg = j9;
        this.zzh = l;
        this.zzi = l2;
        this.zzj = l3;
        this.zzk = bool;
    }

    /* access modifiers changed from: package-private */
    public final zzbd zza(Long l, Long l2, Boolean bool) {
        return new zzbd(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, l, l2, bool);
    }

    /* access modifiers changed from: package-private */
    public final zzbd zzb(long j, long j2) {
        Long valueOf = Long.valueOf(j2);
        Long l = this.zzi;
        Long l2 = this.zzj;
        Boolean bool = this.zzk;
        return new zzbd(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, j, valueOf, l, l2, bool);
    }

    /* access modifiers changed from: package-private */
    public final zzbd zzc(long j) {
        return new zzbd(this.zza, this.zzb, this.zzc, this.zzd, this.zze, j, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk);
    }
}
