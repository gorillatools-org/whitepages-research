package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.List;

final class zzll implements zzor {
    private final zzlk zza;

    private zzll(zzlk zzlk) {
        byte[] bArr = zzmk.zzb;
        this.zza = zzlk;
        zzlk.zza = this;
    }

    public static zzll zza(zzlk zzlk) {
        zzll zzll = zzlk.zza;
        return zzll != null ? zzll : new zzll(zzlk);
    }

    public final void zzB(int i, int i2) throws IOException {
        this.zza.zzs(i, (i2 >> 31) ^ (i2 + i2));
    }

    public final void zzD(int i, long j) throws IOException {
        this.zza.zzu(i, (j >> 63) ^ (j + j));
    }

    @Deprecated
    public final void zzF(int i) throws IOException {
        this.zza.zzr(i, 3);
    }

    public final void zzG(int i, String str) throws IOException {
        this.zza.zzp(i, str);
    }

    public final void zzI(int i, int i2) throws IOException {
        this.zza.zzs(i, i2);
    }

    public final void zzK(int i, long j) throws IOException {
        this.zza.zzu(i, j);
    }

    public final void zzb(int i, boolean z) throws IOException {
        this.zza.zzd(i, z);
    }

    public final void zzd(int i, zzld zzld) throws IOException {
        this.zza.zze(i, zzld);
    }

    public final void zze(int i, List list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zza.zze(i, (zzld) list.get(i2));
        }
    }

    public final void zzf(int i, double d) throws IOException {
        this.zza.zzh(i, Double.doubleToRawLongBits(d));
    }

    @Deprecated
    public final void zzh(int i) throws IOException {
        this.zza.zzr(i, 4);
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

    public final void zzq(int i, Object obj, zzns zzns) throws IOException {
        zzlk zzlk = this.zza;
        zzlk.zzr(i, 3);
        zzns.zzi((zznh) obj, zzlk.zza);
        zzlk.zzr(i, 4);
    }

    public final void zzr(int i, int i2) throws IOException {
        this.zza.zzj(i, i2);
    }

    public final void zzt(int i, long j) throws IOException {
        this.zza.zzu(i, j);
    }

    public final void zzv(int i, Object obj, zzns zzns) throws IOException {
        this.zza.zzm(i, (zznh) obj, zzns);
    }

    public final void zzw(int i, Object obj) throws IOException {
        if (obj instanceof zzld) {
            this.zza.zzo(i, (zzld) obj);
        } else {
            this.zza.zzn(i, (zznh) obj);
        }
    }

    public final void zzx(int i, int i2) throws IOException {
        this.zza.zzf(i, i2);
    }

    public final void zzz(int i, long j) throws IOException {
        this.zza.zzh(i, j);
    }

    public final void zzH(int i, List list) throws IOException {
        int i2 = 0;
        if (list instanceof zzmt) {
            zzmt zzmt = (zzmt) list;
            while (i2 < list.size()) {
                Object zzc = zzmt.zzc();
                if (zzc instanceof String) {
                    this.zza.zzp(i, (String) zzc);
                } else {
                    this.zza.zze(i, (zzld) zzc);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzp(i, (String) list.get(i2));
            i2++;
        }
    }

    public final void zzJ(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzme) {
            zzme zzme = (zzme) list;
            if (z) {
                zzlk zzlk = this.zza;
                zzlk.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzme.size(); i4++) {
                    i3 += zzlk.zzz(zzme.zze(i4));
                }
                zzlk.zzt(i3);
                while (i2 < zzme.size()) {
                    zzlk.zzt(zzme.zze(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzme.size()) {
                this.zza.zzs(i, zzme.zze(i2));
                i2++;
            }
        } else if (z) {
            zzlk zzlk2 = this.zza;
            zzlk2.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzlk.zzz(((Integer) list.get(i6)).intValue());
            }
            zzlk2.zzt(i5);
            while (i2 < list.size()) {
                zzlk2.zzt(((Integer) list.get(i2)).intValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzs(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
    }

    public final void zzL(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzmw) {
            zzmw zzmw = (zzmw) list;
            if (z) {
                zzlk zzlk = this.zza;
                zzlk.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzmw.size(); i4++) {
                    i3 += zzlk.zzA(zzmw.zza(i4));
                }
                zzlk.zzt(i3);
                while (i2 < zzmw.size()) {
                    zzlk.zzv(zzmw.zza(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzmw.size()) {
                this.zza.zzu(i, zzmw.zza(i2));
                i2++;
            }
        } else if (z) {
            zzlk zzlk2 = this.zza;
            zzlk2.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzlk.zzA(((Long) list.get(i6)).longValue());
            }
            zzlk2.zzt(i5);
            while (i2 < list.size()) {
                zzlk2.zzv(((Long) list.get(i2)).longValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzu(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
        }
    }

    public final void zzl(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzme) {
            zzme zzme = (zzme) list;
            if (z) {
                zzlk zzlk = this.zza;
                zzlk.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzme.size(); i4++) {
                    zzme.zze(i4);
                    i3 += 4;
                }
                zzlk.zzt(i3);
                while (i2 < zzme.size()) {
                    zzlk.zzg(zzme.zze(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzme.size()) {
                this.zza.zzf(i, zzme.zze(i2));
                i2++;
            }
        } else if (z) {
            zzlk zzlk2 = this.zza;
            zzlk2.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Integer) list.get(i6)).intValue();
                i5 += 4;
            }
            zzlk2.zzt(i5);
            while (i2 < list.size()) {
                zzlk2.zzg(((Integer) list.get(i2)).intValue());
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
        if (list instanceof zzmw) {
            zzmw zzmw = (zzmw) list;
            if (z) {
                zzlk zzlk = this.zza;
                zzlk.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzmw.size(); i4++) {
                    zzmw.zza(i4);
                    i3 += 8;
                }
                zzlk.zzt(i3);
                while (i2 < zzmw.size()) {
                    zzlk.zzi(zzmw.zza(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzmw.size()) {
                this.zza.zzh(i, zzmw.zza(i2));
                i2++;
            }
        } else if (z) {
            zzlk zzlk2 = this.zza;
            zzlk2.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Long) list.get(i6)).longValue();
                i5 += 8;
            }
            zzlk2.zzt(i5);
            while (i2 < list.size()) {
                zzlk2.zzi(((Long) list.get(i2)).longValue());
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
        if (list instanceof zzku) {
            zzku zzku = (zzku) list;
            if (z) {
                zzlk zzlk = this.zza;
                zzlk.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzku.size(); i4++) {
                    zzku.zzf(i4);
                    i3++;
                }
                zzlk.zzt(i3);
                while (i2 < zzku.size()) {
                    zzlk.zzb(zzku.zzf(i2) ? (byte) 1 : 0);
                    i2++;
                }
                return;
            }
            while (i2 < zzku.size()) {
                this.zza.zzd(i, zzku.zzf(i2));
                i2++;
            }
        } else if (z) {
            zzlk zzlk2 = this.zza;
            zzlk2.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Boolean) list.get(i6)).booleanValue();
                i5++;
            }
            zzlk2.zzt(i5);
            while (i2 < list.size()) {
                zzlk2.zzb(((Boolean) list.get(i2)).booleanValue() ? (byte) 1 : 0);
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
        if (list instanceof zzme) {
            zzme zzme = (zzme) list;
            if (z) {
                zzlk zzlk = this.zza;
                zzlk.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzme.size(); i4++) {
                    i3 += zzlk.zzA((long) zzme.zze(i4));
                }
                zzlk.zzt(i3);
                while (i2 < zzme.size()) {
                    zzlk.zzk(zzme.zze(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzme.size()) {
                this.zza.zzj(i, zzme.zze(i2));
                i2++;
            }
        } else if (z) {
            zzlk zzlk2 = this.zza;
            zzlk2.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzlk.zzA((long) ((Integer) list.get(i6)).intValue());
            }
            zzlk2.zzt(i5);
            while (i2 < list.size()) {
                zzlk2.zzk(((Integer) list.get(i2)).intValue());
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
        if (list instanceof zzmw) {
            zzmw zzmw = (zzmw) list;
            if (z) {
                zzlk zzlk = this.zza;
                zzlk.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzmw.size(); i4++) {
                    zzmw.zza(i4);
                    i3 += 8;
                }
                zzlk.zzt(i3);
                while (i2 < zzmw.size()) {
                    zzlk.zzi(zzmw.zza(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzmw.size()) {
                this.zza.zzh(i, zzmw.zza(i2));
                i2++;
            }
        } else if (z) {
            zzlk zzlk2 = this.zza;
            zzlk2.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Long) list.get(i6)).longValue();
                i5 += 8;
            }
            zzlk2.zzt(i5);
            while (i2 < list.size()) {
                zzlk2.zzi(((Long) list.get(i2)).longValue());
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
        if (list instanceof zzlm) {
            zzlm zzlm = (zzlm) list;
            if (z) {
                zzlk zzlk = this.zza;
                zzlk.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzlm.size(); i4++) {
                    zzlm.zze(i4);
                    i3 += 8;
                }
                zzlk.zzt(i3);
                while (i2 < zzlm.size()) {
                    zzlk.zzi(Double.doubleToRawLongBits(zzlm.zze(i2)));
                    i2++;
                }
                return;
            }
            while (i2 < zzlm.size()) {
                this.zza.zzh(i, Double.doubleToRawLongBits(zzlm.zze(i2)));
                i2++;
            }
        } else if (z) {
            zzlk zzlk2 = this.zza;
            zzlk2.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Double) list.get(i6)).doubleValue();
                i5 += 8;
            }
            zzlk2.zzt(i5);
            while (i2 < list.size()) {
                zzlk2.zzi(Double.doubleToRawLongBits(((Double) list.get(i2)).doubleValue()));
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
        if (list instanceof zzlw) {
            zzlw zzlw = (zzlw) list;
            if (z) {
                zzlk zzlk = this.zza;
                zzlk.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzlw.size(); i4++) {
                    zzlw.zze(i4);
                    i3 += 4;
                }
                zzlk.zzt(i3);
                while (i2 < zzlw.size()) {
                    zzlk.zzg(Float.floatToRawIntBits(zzlw.zze(i2)));
                    i2++;
                }
                return;
            }
            while (i2 < zzlw.size()) {
                this.zza.zzf(i, Float.floatToRawIntBits(zzlw.zze(i2)));
                i2++;
            }
        } else if (z) {
            zzlk zzlk2 = this.zza;
            zzlk2.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Float) list.get(i6)).floatValue();
                i5 += 4;
            }
            zzlk2.zzt(i5);
            while (i2 < list.size()) {
                zzlk2.zzg(Float.floatToRawIntBits(((Float) list.get(i2)).floatValue()));
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
        if (list instanceof zzme) {
            zzme zzme = (zzme) list;
            if (z) {
                zzlk zzlk = this.zza;
                zzlk.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzme.size(); i4++) {
                    zzme.zze(i4);
                    i3 += 4;
                }
                zzlk.zzt(i3);
                while (i2 < zzme.size()) {
                    zzlk.zzg(zzme.zze(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzme.size()) {
                this.zza.zzf(i, zzme.zze(i2));
                i2++;
            }
        } else if (z) {
            zzlk zzlk2 = this.zza;
            zzlk2.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Integer) list.get(i6)).intValue();
                i5 += 4;
            }
            zzlk2.zzt(i5);
            while (i2 < list.size()) {
                zzlk2.zzg(((Integer) list.get(i2)).intValue());
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
        if (list instanceof zzme) {
            zzme zzme = (zzme) list;
            if (z) {
                zzlk zzlk = this.zza;
                zzlk.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzme.size(); i4++) {
                    int zze = zzme.zze(i4);
                    i3 += zzlk.zzz((zze >> 31) ^ (zze + zze));
                }
                zzlk.zzt(i3);
                while (i2 < zzme.size()) {
                    int zze2 = zzme.zze(i2);
                    zzlk.zzt((zze2 >> 31) ^ (zze2 + zze2));
                    i2++;
                }
                return;
            }
            while (i2 < zzme.size()) {
                zzlk zzlk2 = this.zza;
                int zze3 = zzme.zze(i2);
                zzlk2.zzs(i, (zze3 >> 31) ^ (zze3 + zze3));
                i2++;
            }
        } else if (z) {
            zzlk zzlk3 = this.zza;
            zzlk3.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                int intValue = ((Integer) list.get(i6)).intValue();
                i5 += zzlk.zzz((intValue >> 31) ^ (intValue + intValue));
            }
            zzlk3.zzt(i5);
            while (i2 < list.size()) {
                int intValue2 = ((Integer) list.get(i2)).intValue();
                zzlk3.zzt((intValue2 >> 31) ^ (intValue2 + intValue2));
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                zzlk zzlk4 = this.zza;
                int intValue3 = ((Integer) list.get(i2)).intValue();
                zzlk4.zzs(i, (intValue3 >> 31) ^ (intValue3 + intValue3));
                i2++;
            }
        }
    }

    public final void zzE(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzmw) {
            zzmw zzmw = (zzmw) list;
            if (z) {
                zzlk zzlk = this.zza;
                zzlk.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzmw.size(); i4++) {
                    long zza2 = zzmw.zza(i4);
                    i3 += zzlk.zzA((zza2 >> 63) ^ (zza2 + zza2));
                }
                zzlk.zzt(i3);
                while (i2 < zzmw.size()) {
                    long zza3 = zzmw.zza(i2);
                    zzlk.zzv((zza3 >> 63) ^ (zza3 + zza3));
                    i2++;
                }
                return;
            }
            while (i2 < zzmw.size()) {
                zzlk zzlk2 = this.zza;
                long zza4 = zzmw.zza(i2);
                zzlk2.zzu(i, (zza4 >> 63) ^ (zza4 + zza4));
                i2++;
            }
        } else if (z) {
            zzlk zzlk3 = this.zza;
            zzlk3.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                long longValue = ((Long) list.get(i6)).longValue();
                i5 += zzlk.zzA((longValue >> 63) ^ (longValue + longValue));
            }
            zzlk3.zzt(i5);
            while (i2 < list.size()) {
                long longValue2 = ((Long) list.get(i2)).longValue();
                zzlk3.zzv((longValue2 >> 63) ^ (longValue2 + longValue2));
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                zzlk zzlk4 = this.zza;
                long longValue3 = ((Long) list.get(i2)).longValue();
                zzlk4.zzu(i, (longValue3 >> 63) ^ (longValue3 + longValue3));
                i2++;
            }
        }
    }

    public final void zzj(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzme) {
            zzme zzme = (zzme) list;
            if (z) {
                zzlk zzlk = this.zza;
                zzlk.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzme.size(); i4++) {
                    i3 += zzlk.zzA((long) zzme.zze(i4));
                }
                zzlk.zzt(i3);
                while (i2 < zzme.size()) {
                    zzlk.zzk(zzme.zze(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzme.size()) {
                this.zza.zzj(i, zzme.zze(i2));
                i2++;
            }
        } else if (z) {
            zzlk zzlk2 = this.zza;
            zzlk2.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzlk.zzA((long) ((Integer) list.get(i6)).intValue());
            }
            zzlk2.zzt(i5);
            while (i2 < list.size()) {
                zzlk2.zzk(((Integer) list.get(i2)).intValue());
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
        if (list instanceof zzmw) {
            zzmw zzmw = (zzmw) list;
            if (z) {
                zzlk zzlk = this.zza;
                zzlk.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzmw.size(); i4++) {
                    i3 += zzlk.zzA(zzmw.zza(i4));
                }
                zzlk.zzt(i3);
                while (i2 < zzmw.size()) {
                    zzlk.zzv(zzmw.zza(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzmw.size()) {
                this.zza.zzu(i, zzmw.zza(i2));
                i2++;
            }
        } else if (z) {
            zzlk zzlk2 = this.zza;
            zzlk2.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzlk.zzA(((Long) list.get(i6)).longValue());
            }
            zzlk2.zzt(i5);
            while (i2 < list.size()) {
                zzlk2.zzv(((Long) list.get(i2)).longValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzu(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
        }
    }
}
