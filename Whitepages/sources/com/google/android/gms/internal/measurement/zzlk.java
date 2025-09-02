package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzlk extends zzkv {
    public static final /* synthetic */ int zzb = 0;
    private static final Logger zzc = Logger.getLogger(zzlk.class.getName());
    /* access modifiers changed from: private */
    public static final boolean zzd = zzol.zzx();
    zzll zza;

    private zzlk() {
        throw null;
    }

    /* synthetic */ zzlk(zzlj zzlj) {
    }

    public static int zzA(long j) {
        return (640 - (Long.numberOfLeadingZeros(j) * 9)) >>> 6;
    }

    @Deprecated
    static int zzw(int i, zznh zznh, zzns zzns) {
        int zzz = zzz(i << 3);
        return zzz + zzz + ((zzko) zznh).zzca(zzns);
    }

    static int zzx(zznh zznh, zzns zzns) {
        int zzca = ((zzko) zznh).zzca(zzns);
        return zzz(zzca) + zzca;
    }

    public static int zzy(String str) {
        int i;
        try {
            i = zzoo.zzc(str);
        } catch (zzon unused) {
            i = str.getBytes(zzmk.zza).length;
        }
        return zzz(i) + i;
    }

    public static int zzz(int i) {
        return (352 - (Integer.numberOfLeadingZeros(i) * 9)) >>> 6;
    }

    public final void zzB() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzC(String str, zzon zzon) throws IOException {
        zzc.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzon);
        byte[] bytes = str.getBytes(zzmk.zza);
        try {
            int length = bytes.length;
            zzt(length);
            zzl(bytes, 0, length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzli(e);
        }
    }

    public abstract int zza();

    public abstract void zzb(byte b) throws IOException;

    public abstract void zzd(int i, boolean z) throws IOException;

    public abstract void zze(int i, zzld zzld) throws IOException;

    public abstract void zzf(int i, int i2) throws IOException;

    public abstract void zzg(int i) throws IOException;

    public abstract void zzh(int i, long j) throws IOException;

    public abstract void zzi(long j) throws IOException;

    public abstract void zzj(int i, int i2) throws IOException;

    public abstract void zzk(int i) throws IOException;

    public abstract void zzl(byte[] bArr, int i, int i2) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void zzm(int i, zznh zznh, zzns zzns) throws IOException;

    public abstract void zzn(int i, zznh zznh) throws IOException;

    public abstract void zzo(int i, zzld zzld) throws IOException;

    public abstract void zzp(int i, String str) throws IOException;

    public abstract void zzr(int i, int i2) throws IOException;

    public abstract void zzs(int i, int i2) throws IOException;

    public abstract void zzt(int i) throws IOException;

    public abstract void zzu(int i, long j) throws IOException;

    public abstract void zzv(long j) throws IOException;
}
