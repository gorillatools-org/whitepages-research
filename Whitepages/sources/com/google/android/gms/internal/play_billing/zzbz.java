package com.google.android.gms.internal.play_billing;

import java.io.IOException;
import java.util.List;

final class zzbz implements zzfx {
    private final zzby zza;

    private zzbz(zzby zzby) {
        byte[] bArr = zzda.zzd;
        this.zza = zzby;
        zzby.zza = this;
    }

    public static zzbz zza(zzby zzby) {
        zzbz zzbz = zzby.zza;
        return zzbz != null ? zzbz : new zzbz(zzby);
    }

    public final void zzB(int i, int i2) throws IOException {
        this.zza.zzp(i, (i2 >> 31) ^ (i2 + i2));
    }

    public final void zzD(int i, long j) throws IOException {
        this.zza.zzr(i, (j >> 63) ^ (j + j));
    }

    @Deprecated
    public final void zzF(int i) throws IOException {
        this.zza.zzo(i, 3);
    }

    public final void zzG(int i, String str) throws IOException {
        this.zza.zzm(i, str);
    }

    public final void zzI(int i, int i2) throws IOException {
        this.zza.zzp(i, i2);
    }

    public final void zzK(int i, long j) throws IOException {
        this.zza.zzr(i, j);
    }

    public final void zzb(int i, boolean z) throws IOException {
        this.zza.zzd(i, z);
    }

    public final void zzd(int i, zzbq zzbq) throws IOException {
        this.zza.zze(i, zzbq);
    }

    public final void zze(int i, List list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zza.zze(i, (zzbq) list.get(i2));
        }
    }

    public final void zzf(int i, double d) throws IOException {
        this.zza.zzh(i, Double.doubleToRawLongBits(d));
    }

    @Deprecated
    public final void zzh(int i) throws IOException {
        this.zza.zzo(i, 4);
    }

    public final void zzi(int i, int i2) throws IOException {
        this.zza.zzj(i, i2);
    }

    public final void zzk(int i, int i2) throws IOException {
        this.zza.zzf(i, i2);
    }

    public final void zzm(int i, long j) throws IOException {
        this.zza.zzh(i, j);
    }

    public final void zzo(int i, float f) throws IOException {
        this.zza.zzf(i, Float.floatToRawIntBits(f));
    }

    public final void zzq(int i, Object obj, zzeo zzeo) throws IOException {
        zzby zzby = this.zza;
        zzby.zzo(i, 3);
        zzeo.zzi((zzec) obj, zzby.zza);
        zzby.zzo(i, 4);
    }

    public final void zzr(int i, int i2) throws IOException {
        this.zza.zzj(i, i2);
    }

    public final void zzt(int i, long j) throws IOException {
        this.zza.zzr(i, j);
    }

    public final void zzv(int i, Object obj, zzeo zzeo) throws IOException {
        zzec zzec = (zzec) obj;
        zzbv zzbv = (zzbv) this.zza;
        zzbv.zzq((i << 3) | 2);
        zzbv.zzq(((zzay) zzec).zza(zzeo));
        zzeo.zzi(zzec, zzbv.zza);
    }

    public final void zzw(int i, Object obj) throws IOException {
        if (obj instanceof zzbq) {
            zzbv zzbv = (zzbv) this.zza;
            zzbv.zzq(11);
            zzbv.zzp(2, i);
            zzbv.zze(3, (zzbq) obj);
            zzbv.zzq(12);
            return;
        }
        zzby zzby = this.zza;
        zzec zzec = (zzec) obj;
        zzbv zzbv2 = (zzbv) zzby;
        zzbv2.zzq(11);
        zzbv2.zzp(2, i);
        zzbv2.zzq(26);
        zzbv2.zzq(zzec.zzf());
        zzec.zze(zzby);
        zzbv2.zzq(12);
    }

    public final void zzx(int i, int i2) throws IOException {
        this.zza.zzf(i, i2);
    }

    public final void zzz(int i, long j) throws IOException {
        this.zza.zzh(i, j);
    }

    public final void zzH(int i, List list) throws IOException {
        int i2 = 0;
        if (list instanceof zzdk) {
            zzdk zzdk = (zzdk) list;
            while (i2 < list.size()) {
                Object zzf = zzdk.zzf(i2);
                if (zzf instanceof String) {
                    this.zza.zzm(i, (String) zzf);
                } else {
                    this.zza.zze(i, (zzbq) zzf);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzm(i, (String) list.get(i2));
            i2++;
        }
    }

    public final void zzJ(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzct) {
            zzct zzct = (zzct) list;
            if (z) {
                this.zza.zzo(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzct.size(); i4++) {
                    i3 += zzby.zzw(zzct.zze(i4));
                }
                this.zza.zzq(i3);
                while (i2 < zzct.size()) {
                    this.zza.zzq(zzct.zze(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzct.size()) {
                this.zza.zzp(i, zzct.zze(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzo(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzby.zzw(((Integer) list.get(i6)).intValue());
            }
            this.zza.zzq(i5);
            while (i2 < list.size()) {
                this.zza.zzq(((Integer) list.get(i2)).intValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzp(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
    }

    public final void zzL(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzdr) {
            zzdr zzdr = (zzdr) list;
            if (z) {
                this.zza.zzo(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzdr.size(); i4++) {
                    i3 += zzby.zzx(zzdr.zze(i4));
                }
                this.zza.zzq(i3);
                while (i2 < zzdr.size()) {
                    this.zza.zzs(zzdr.zze(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzdr.size()) {
                this.zza.zzr(i, zzdr.zze(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzo(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzby.zzx(((Long) list.get(i6)).longValue());
            }
            this.zza.zzq(i5);
            while (i2 < list.size()) {
                this.zza.zzs(((Long) list.get(i2)).longValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzr(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
        }
    }

    public final void zzl(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzct) {
            zzct zzct = (zzct) list;
            if (z) {
                this.zza.zzo(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzct.size(); i4++) {
                    zzct.zze(i4);
                    i3 += 4;
                }
                this.zza.zzq(i3);
                while (i2 < zzct.size()) {
                    this.zza.zzg(zzct.zze(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzct.size()) {
                this.zza.zzf(i, zzct.zze(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzo(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Integer) list.get(i6)).intValue();
                i5 += 4;
            }
            this.zza.zzq(i5);
            while (i2 < list.size()) {
                this.zza.zzg(((Integer) list.get(i2)).intValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzf(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
    }

    public final void zzn(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzdr) {
            zzdr zzdr = (zzdr) list;
            if (z) {
                this.zza.zzo(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzdr.size(); i4++) {
                    zzdr.zze(i4);
                    i3 += 8;
                }
                this.zza.zzq(i3);
                while (i2 < zzdr.size()) {
                    this.zza.zzi(zzdr.zze(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzdr.size()) {
                this.zza.zzh(i, zzdr.zze(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzo(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Long) list.get(i6)).longValue();
                i5 += 8;
            }
            this.zza.zzq(i5);
            while (i2 < list.size()) {
                this.zza.zzi(((Long) list.get(i2)).longValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzh(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
        }
    }

    public final void zzc(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzbe) {
            zzbe zzbe = (zzbe) list;
            if (z) {
                this.zza.zzo(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzbe.size(); i4++) {
                    zzbe.zzf(i4);
                    i3++;
                }
                this.zza.zzq(i3);
                while (i2 < zzbe.size()) {
                    this.zza.zzb(zzbe.zzf(i2) ? (byte) 1 : 0);
                    i2++;
                }
                return;
            }
            while (i2 < zzbe.size()) {
                this.zza.zzd(i, zzbe.zzf(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzo(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Boolean) list.get(i6)).booleanValue();
                i5++;
            }
            this.zza.zzq(i5);
            while (i2 < list.size()) {
                this.zza.zzb(((Boolean) list.get(i2)).booleanValue() ? (byte) 1 : 0);
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzd(i, ((Boolean) list.get(i2)).booleanValue());
                i2++;
            }
        }
    }

    public final void zzs(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzct) {
            zzct zzct = (zzct) list;
            if (z) {
                this.zza.zzo(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzct.size(); i4++) {
                    i3 += zzby.zzx((long) zzct.zze(i4));
                }
                this.zza.zzq(i3);
                while (i2 < zzct.size()) {
                    this.zza.zzk(zzct.zze(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzct.size()) {
                this.zza.zzj(i, zzct.zze(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzo(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzby.zzx((long) ((Integer) list.get(i6)).intValue());
            }
            this.zza.zzq(i5);
            while (i2 < list.size()) {
                this.zza.zzk(((Integer) list.get(i2)).intValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzj(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
    }

    public final void zzA(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzdr) {
            zzdr zzdr = (zzdr) list;
            if (z) {
                this.zza.zzo(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzdr.size(); i4++) {
                    zzdr.zze(i4);
                    i3 += 8;
                }
                this.zza.zzq(i3);
                while (i2 < zzdr.size()) {
                    this.zza.zzi(zzdr.zze(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzdr.size()) {
                this.zza.zzh(i, zzdr.zze(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzo(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Long) list.get(i6)).longValue();
                i5 += 8;
            }
            this.zza.zzq(i5);
            while (i2 < list.size()) {
                this.zza.zzi(((Long) list.get(i2)).longValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzh(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
        }
    }

    public final void zzg(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzca) {
            zzca zzca = (zzca) list;
            if (z) {
                this.zza.zzo(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzca.size(); i4++) {
                    zzca.zze(i4);
                    i3 += 8;
                }
                this.zza.zzq(i3);
                while (i2 < zzca.size()) {
                    this.zza.zzi(Double.doubleToRawLongBits(zzca.zze(i2)));
                    i2++;
                }
                return;
            }
            while (i2 < zzca.size()) {
                this.zza.zzh(i, Double.doubleToRawLongBits(zzca.zze(i2)));
                i2++;
            }
        } else if (z) {
            this.zza.zzo(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Double) list.get(i6)).doubleValue();
                i5 += 8;
            }
            this.zza.zzq(i5);
            while (i2 < list.size()) {
                this.zza.zzi(Double.doubleToRawLongBits(((Double) list.get(i2)).doubleValue()));
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzh(i, Double.doubleToRawLongBits(((Double) list.get(i2)).doubleValue()));
                i2++;
            }
        }
    }

    public final void zzp(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzck) {
            zzck zzck = (zzck) list;
            if (z) {
                this.zza.zzo(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzck.size(); i4++) {
                    zzck.zze(i4);
                    i3 += 4;
                }
                this.zza.zzq(i3);
                while (i2 < zzck.size()) {
                    this.zza.zzg(Float.floatToRawIntBits(zzck.zze(i2)));
                    i2++;
                }
                return;
            }
            while (i2 < zzck.size()) {
                this.zza.zzf(i, Float.floatToRawIntBits(zzck.zze(i2)));
                i2++;
            }
        } else if (z) {
            this.zza.zzo(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Float) list.get(i6)).floatValue();
                i5 += 4;
            }
            this.zza.zzq(i5);
            while (i2 < list.size()) {
                this.zza.zzg(Float.floatToRawIntBits(((Float) list.get(i2)).floatValue()));
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzf(i, Float.floatToRawIntBits(((Float) list.get(i2)).floatValue()));
                i2++;
            }
        }
    }

    public final void zzy(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzct) {
            zzct zzct = (zzct) list;
            if (z) {
                this.zza.zzo(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzct.size(); i4++) {
                    zzct.zze(i4);
                    i3 += 4;
                }
                this.zza.zzq(i3);
                while (i2 < zzct.size()) {
                    this.zza.zzg(zzct.zze(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzct.size()) {
                this.zza.zzf(i, zzct.zze(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzo(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Integer) list.get(i6)).intValue();
                i5 += 4;
            }
            this.zza.zzq(i5);
            while (i2 < list.size()) {
                this.zza.zzg(((Integer) list.get(i2)).intValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzf(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
    }

    public final void zzC(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzct) {
            zzct zzct = (zzct) list;
            if (z) {
                this.zza.zzo(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzct.size(); i4++) {
                    int zze = zzct.zze(i4);
                    i3 += zzby.zzw((zze >> 31) ^ (zze + zze));
                }
                this.zza.zzq(i3);
                while (i2 < zzct.size()) {
                    zzby zzby = this.zza;
                    int zze2 = zzct.zze(i2);
                    zzby.zzq((zze2 >> 31) ^ (zze2 + zze2));
                    i2++;
                }
                return;
            }
            while (i2 < zzct.size()) {
                zzby zzby2 = this.zza;
                int zze3 = zzct.zze(i2);
                zzby2.zzp(i, (zze3 >> 31) ^ (zze3 + zze3));
                i2++;
            }
        } else if (z) {
            this.zza.zzo(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                int intValue = ((Integer) list.get(i6)).intValue();
                i5 += zzby.zzw((intValue >> 31) ^ (intValue + intValue));
            }
            this.zza.zzq(i5);
            while (i2 < list.size()) {
                zzby zzby3 = this.zza;
                int intValue2 = ((Integer) list.get(i2)).intValue();
                zzby3.zzq((intValue2 >> 31) ^ (intValue2 + intValue2));
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                zzby zzby4 = this.zza;
                int intValue3 = ((Integer) list.get(i2)).intValue();
                zzby4.zzp(i, (intValue3 >> 31) ^ (intValue3 + intValue3));
                i2++;
            }
        }
    }

    public final void zzE(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzdr) {
            zzdr zzdr = (zzdr) list;
            if (z) {
                this.zza.zzo(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzdr.size(); i4++) {
                    long zze = zzdr.zze(i4);
                    i3 += zzby.zzx((zze >> 63) ^ (zze + zze));
                }
                this.zza.zzq(i3);
                while (i2 < zzdr.size()) {
                    zzby zzby = this.zza;
                    long zze2 = zzdr.zze(i2);
                    zzby.zzs((zze2 >> 63) ^ (zze2 + zze2));
                    i2++;
                }
                return;
            }
            while (i2 < zzdr.size()) {
                zzby zzby2 = this.zza;
                long zze3 = zzdr.zze(i2);
                zzby2.zzr(i, (zze3 >> 63) ^ (zze3 + zze3));
                i2++;
            }
        } else if (z) {
            this.zza.zzo(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                long longValue = ((Long) list.get(i6)).longValue();
                i5 += zzby.zzx((longValue >> 63) ^ (longValue + longValue));
            }
            this.zza.zzq(i5);
            while (i2 < list.size()) {
                zzby zzby3 = this.zza;
                long longValue2 = ((Long) list.get(i2)).longValue();
                zzby3.zzs((longValue2 >> 63) ^ (longValue2 + longValue2));
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                zzby zzby4 = this.zza;
                long longValue3 = ((Long) list.get(i2)).longValue();
                zzby4.zzr(i, (longValue3 >> 63) ^ (longValue3 + longValue3));
                i2++;
            }
        }
    }

    public final void zzj(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzct) {
            zzct zzct = (zzct) list;
            if (z) {
                this.zza.zzo(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzct.size(); i4++) {
                    i3 += zzby.zzx((long) zzct.zze(i4));
                }
                this.zza.zzq(i3);
                while (i2 < zzct.size()) {
                    this.zza.zzk(zzct.zze(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzct.size()) {
                this.zza.zzj(i, zzct.zze(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzo(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzby.zzx((long) ((Integer) list.get(i6)).intValue());
            }
            this.zza.zzq(i5);
            while (i2 < list.size()) {
                this.zza.zzk(((Integer) list.get(i2)).intValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzj(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
    }

    public final void zzu(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzdr) {
            zzdr zzdr = (zzdr) list;
            if (z) {
                this.zza.zzo(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzdr.size(); i4++) {
                    i3 += zzby.zzx(zzdr.zze(i4));
                }
                this.zza.zzq(i3);
                while (i2 < zzdr.size()) {
                    this.zza.zzs(zzdr.zze(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzdr.size()) {
                this.zza.zzr(i, zzdr.zze(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzo(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzby.zzx(((Long) list.get(i6)).longValue());
            }
            this.zza.zzq(i5);
            while (i2 < list.size()) {
                this.zza.zzs(((Long) list.get(i2)).longValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzr(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
        }
    }
}
