package com.google.android.gms.internal.measurement;

final class zzbw extends zzcc {
    private final String zzc;
    private final int zzd;
    private final int zze;

    /* synthetic */ zzbw(String str, boolean z, int i, zzbs zzbs, zzbt zzbt, int i2, zzbv zzbv) {
        this.zzc = str;
        this.zzd = i;
        this.zze = i2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzcc) {
            zzcc zzcc = (zzcc) obj;
            if (this.zzc.equals(zzcc.zzc())) {
                zzcc.zzd();
                int i = this.zzd;
                int zze2 = zzcc.zze();
                if (i == 0) {
                    throw null;
                } else if (i == zze2) {
                    zzcc.zza();
                    zzcc.zzb();
                    int i2 = this.zze;
                    int zzf = zzcc.zzf();
                    if (i2 == 0) {
                        throw null;
                    } else if (zzf == 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = this.zzc.hashCode() ^ 1000003;
        int i = this.zzd;
        if (i != 0) {
            int i2 = (((hashCode * 1000003) ^ 1237) * 1000003) ^ i;
            if (this.zze != 0) {
                return (i2 * 583896283) ^ 1;
            }
            throw null;
        }
        throw null;
    }

    public final String toString() {
        int i = this.zzd;
        String str = "null";
        String str2 = i != 1 ? i != 2 ? i != 3 ? i != 4 ? str : "NO_CHECKS" : "SKIP_SECURITY_CHECK" : "SKIP_COMPLIANCE_CHECK" : "ALL_CHECKS";
        if (this.zze == 1) {
            str = "READ_AND_WRITE";
        }
        String str3 = this.zzc;
        return "FileComplianceOptions{fileOwner=" + str3 + ", hasDifferentDmaOwner=false, fileChecks=" + str2 + ", dataForwardingNotAllowedResolver=null, multipleProductIdGroupsResolver=null, filePurpose=" + str + "}";
    }

    public final zzbs zza() {
        return null;
    }

    public final zzbt zzb() {
        return null;
    }

    public final String zzc() {
        return this.zzc;
    }

    public final boolean zzd() {
        return false;
    }

    public final int zze() {
        return this.zzd;
    }

    public final int zzf() {
        return this.zze;
    }
}
