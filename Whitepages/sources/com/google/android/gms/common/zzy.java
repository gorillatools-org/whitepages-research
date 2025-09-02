package com.google.android.gms.common;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.common.zzak;
import java.util.List;

final class zzy {
    private String zza = null;
    private long zzb = -1;
    private zzak zzc = zzak.zzl();
    private zzak zzd = zzak.zzl();

    zzy() {
    }

    /* access modifiers changed from: package-private */
    public final zzy zza(long j) {
        this.zzb = j;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final zzy zzb(List list) {
        Preconditions.checkNotNull(list);
        this.zzd = zzak.zzk(list);
        return this;
    }

    /* access modifiers changed from: package-private */
    public final zzy zzc(List list) {
        Preconditions.checkNotNull(list);
        this.zzc = zzak.zzk(list);
        return this;
    }

    /* access modifiers changed from: package-private */
    public final zzy zzd(String str) {
        this.zza = str;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final zzaa zze() {
        if (this.zza == null) {
            throw new IllegalStateException("packageName must be defined");
        } else if (this.zzb < 0) {
            throw new IllegalStateException("minimumStampedVersionNumber must be greater than or equal to 0");
        } else if (!this.zzc.isEmpty() || !this.zzd.isEmpty()) {
            return new zzaa(this.zza, this.zzb, this.zzc, this.zzd, (zzz) null);
        } else {
            throw new IllegalStateException("Either orderedTestCerts or orderedProdCerts must have at least one cert");
        }
    }
}
