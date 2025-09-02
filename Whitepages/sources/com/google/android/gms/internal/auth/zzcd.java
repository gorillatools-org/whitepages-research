package com.google.android.gms.internal.auth;

import android.content.Context;

final class zzcd extends zzda {
    private final Context zza;
    private final zzdj zzb;

    zzcd(Context context, zzdj zzdj) {
        this.zza = context;
        this.zzb = zzdj;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzda) {
            zzda zzda = (zzda) obj;
            return this.zza.equals(zzda.zza()) && this.zzb.equals(zzda.zzb());
        }
    }

    public final int hashCode() {
        return ((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode();
    }

    public final String toString() {
        String obj = this.zza.toString();
        String obj2 = this.zzb.toString();
        return "FlagsContext{context=" + obj + ", hermeticFileOverrides=" + obj2 + "}";
    }

    /* access modifiers changed from: package-private */
    public final Context zza() {
        return this.zza;
    }

    /* access modifiers changed from: package-private */
    public final zzdj zzb() {
        return this.zzb;
    }
}
