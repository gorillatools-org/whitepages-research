package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zzay extends zzaw {
    public zzay() {
        this.zza.add(zzbl.EQUALS);
        this.zza.add(zzbl.GREATER_THAN);
        this.zza.add(zzbl.GREATER_THAN_EQUALS);
        this.zza.add(zzbl.IDENTITY_EQUALS);
        this.zza.add(zzbl.IDENTITY_NOT_EQUALS);
        this.zza.add(zzbl.LESS_THAN);
        this.zza.add(zzbl.LESS_THAN_EQUALS);
        this.zza.add(zzbl.NOT_EQUALS);
    }

    private static boolean zzc(zzap zzap, zzap zzap2) {
        if (zzap.getClass().equals(zzap2.getClass())) {
            if ((zzap instanceof zzau) || (zzap instanceof zzan)) {
                return true;
            }
            if (zzap instanceof zzah) {
                return !Double.isNaN(zzap.zzh().doubleValue()) && !Double.isNaN(zzap2.zzh().doubleValue()) && zzap.zzh().doubleValue() == zzap2.zzh().doubleValue();
            }
            if (zzap instanceof zzat) {
                return zzap.zzi().equals(zzap2.zzi());
            }
            if (zzap instanceof zzaf) {
                return zzap.zzg().equals(zzap2.zzg());
            }
            return zzap == zzap2;
        } else if (((zzap instanceof zzau) || (zzap instanceof zzan)) && ((zzap2 instanceof zzau) || (zzap2 instanceof zzan))) {
            return true;
        } else {
            boolean z = zzap instanceof zzah;
            if (z && (zzap2 instanceof zzat)) {
                return zzc(zzap, new zzah(zzap2.zzh()));
            }
            boolean z2 = zzap instanceof zzat;
            if (z2 && (zzap2 instanceof zzah)) {
                return zzc(new zzah(zzap.zzh()), zzap2);
            }
            if (zzap instanceof zzaf) {
                return zzc(new zzah(zzap.zzh()), zzap2);
            }
            if (zzap2 instanceof zzaf) {
                return zzc(zzap, new zzah(zzap2.zzh()));
            }
            if ((z2 || z) && (zzap2 instanceof zzal)) {
                return zzc(zzap, new zzat(zzap2.zzi()));
            }
            if (!(zzap instanceof zzal) || (!(zzap2 instanceof zzat) && !(zzap2 instanceof zzah))) {
                return false;
            }
            return zzc(new zzat(zzap.zzi()), zzap2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0054, code lost:
        r0 = (r3 > 0.0d ? 1 : (r3 == 0.0d ? 0 : -1));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean zzd(com.google.android.gms.internal.measurement.zzap r8, com.google.android.gms.internal.measurement.zzap r9) {
        /*
            boolean r0 = r8 instanceof com.google.android.gms.internal.measurement.zzal
            if (r0 == 0) goto L_0x000e
            com.google.android.gms.internal.measurement.zzat r0 = new com.google.android.gms.internal.measurement.zzat
            java.lang.String r8 = r8.zzi()
            r0.<init>(r8)
            r8 = r0
        L_0x000e:
            boolean r0 = r9 instanceof com.google.android.gms.internal.measurement.zzal
            if (r0 == 0) goto L_0x001c
            com.google.android.gms.internal.measurement.zzat r0 = new com.google.android.gms.internal.measurement.zzat
            java.lang.String r9 = r9.zzi()
            r0.<init>(r9)
            r9 = r0
        L_0x001c:
            boolean r0 = r8 instanceof com.google.android.gms.internal.measurement.zzat
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0037
            boolean r0 = r9 instanceof com.google.android.gms.internal.measurement.zzat
            if (r0 != 0) goto L_0x0027
            goto L_0x0037
        L_0x0027:
            java.lang.String r8 = r8.zzi()
            java.lang.String r9 = r9.zzi()
            int r8 = r8.compareTo(r9)
            if (r8 >= 0) goto L_0x0036
            return r1
        L_0x0036:
            return r2
        L_0x0037:
            java.lang.Double r8 = r8.zzh()
            double r3 = r8.doubleValue()
            java.lang.Double r8 = r9.zzh()
            double r8 = r8.doubleValue()
            boolean r0 = java.lang.Double.isNaN(r3)
            if (r0 != 0) goto L_0x006d
            boolean r0 = java.lang.Double.isNaN(r8)
            if (r0 == 0) goto L_0x0054
            goto L_0x006d
        L_0x0054:
            r5 = 0
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 != 0) goto L_0x005e
            int r7 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x0065
        L_0x005e:
            if (r0 != 0) goto L_0x0066
            int r0 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
            if (r0 == 0) goto L_0x0065
            goto L_0x0066
        L_0x0065:
            return r2
        L_0x0066:
            int r8 = java.lang.Double.compare(r3, r8)
            if (r8 >= 0) goto L_0x006d
            return r1
        L_0x006d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzay.zzd(com.google.android.gms.internal.measurement.zzap, com.google.android.gms.internal.measurement.zzap):boolean");
    }

    private static boolean zze(zzap zzap, zzap zzap2) {
        if (zzap instanceof zzal) {
            zzap = new zzat(zzap.zzi());
        }
        if (zzap2 instanceof zzal) {
            zzap2 = new zzat(zzap2.zzi());
        }
        if (((!(zzap instanceof zzat) || !(zzap2 instanceof zzat)) && (Double.isNaN(zzap.zzh().doubleValue()) || Double.isNaN(zzap2.zzh().doubleValue()))) || zzd(zzap2, zzap)) {
            return false;
        }
        return true;
    }

    public final zzap zza(String str, zzg zzg, List list) {
        boolean z;
        boolean zzc;
        zzh.zzh(zzh.zze(str).name(), 2, list);
        zzap zzb = zzg.zzb((zzap) list.get(0));
        zzap zzb2 = zzg.zzb((zzap) list.get(1));
        int ordinal = zzh.zze(str).ordinal();
        if (ordinal != 23) {
            if (ordinal == 48) {
                zzc = zzc(zzb, zzb2);
            } else if (ordinal == 42) {
                z = zzd(zzb, zzb2);
            } else if (ordinal != 43) {
                switch (ordinal) {
                    case 37:
                        z = zzd(zzb2, zzb);
                        break;
                    case 38:
                        z = zze(zzb2, zzb);
                        break;
                    case 39:
                        z = zzh.zzl(zzb, zzb2);
                        break;
                    case 40:
                        zzc = zzh.zzl(zzb, zzb2);
                        break;
                    default:
                        return super.zzb(str);
                }
            } else {
                z = zze(zzb, zzb2);
            }
            z = !zzc;
        } else {
            z = zzc(zzb, zzb2);
        }
        return z ? zzap.zzk : zzap.zzl;
    }
}
