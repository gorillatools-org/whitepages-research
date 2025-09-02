package com.google.android.gms.internal.play_billing;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzby extends zzbf {
    private static final Logger zzb = Logger.getLogger(zzby.class.getName());
    /* access modifiers changed from: private */
    public static final boolean zzc = zzfp.zzx();
    zzbz zza;

    private zzby() {
        throw null;
    }

    /* synthetic */ zzby(zzbx zzbx) {
    }

    @Deprecated
    static int zzt(int i, zzec zzec, zzeo zzeo) {
        int zzw = zzw(i << 3);
        return zzw + zzw + ((zzay) zzec).zza(zzeo);
    }

    static int zzu(zzec zzec, zzeo zzeo) {
        int zza2 = ((zzay) zzec).zza(zzeo);
        return zzw(zza2) + zza2;
    }

    public static int zzv(String str) {
        int i;
        try {
            i = zzfu.zzc(str);
        } catch (zzft unused) {
            i = str.getBytes(zzda.zzb).length;
        }
        return zzw(i) + i;
    }

    public static int zzw(int i) {
        return (352 - (Integer.numberOfLeadingZeros(i) * 9)) >>> 6;
    }

    public static int zzx(long j) {
        return (640 - (Long.numberOfLeadingZeros(j) * 9)) >>> 6;
    }

    public static zzby zzy(byte[] bArr, int i, int i2) {
        return new zzbv(bArr, 0, i2);
    }

    /* access modifiers changed from: package-private */
    public final void zzA(String str, zzft zzft) throws IOException {
        zzb.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzft);
        byte[] bytes = str.getBytes(zzda.zzb);
        try {
            int length = bytes.length;
            zzq(length);
            zzl(bytes, 0, length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzbw(e);
        }
    }

    public abstract int zza();

    public abstract void zzb(byte b) throws IOException;

    public abstract void zzd(int i, boolean z) throws IOException;

    public abstract void zze(int i, zzbq zzbq) throws IOException;

    public abstract void zzf(int i, int i2) throws IOException;

    public abstract void zzg(int i) throws IOException;

    public abstract void zzh(int i, long j) throws IOException;

    public abstract void zzi(long j) throws IOException;

    public abstract void zzj(int i, int i2) throws IOException;

    public abstract void zzk(int i) throws IOException;

    public abstract void zzl(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zzm(int i, String str) throws IOException;

    public abstract void zzo(int i, int i2) throws IOException;

    public abstract void zzp(int i, int i2) throws IOException;

    public abstract void zzq(int i) throws IOException;

    public abstract void zzr(int i, long j) throws IOException;

    public abstract void zzs(long j) throws IOException;

    public final void zzz() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }
}
