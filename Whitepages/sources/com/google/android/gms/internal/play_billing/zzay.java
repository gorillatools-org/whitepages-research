package com.google.android.gms.internal.play_billing;

import com.google.android.gms.internal.play_billing.zzax;
import com.google.android.gms.internal.play_billing.zzay;
import java.io.IOException;

public abstract class zzay<MessageType extends zzay<MessageType, BuilderType>, BuilderType extends zzax<MessageType, BuilderType>> implements zzec {
    protected int zza = 0;

    /* access modifiers changed from: package-private */
    public int zza(zzeo zzeo) {
        throw null;
    }

    public final zzbq zzb() {
        try {
            int zzf = zzf();
            zzbq zzbq = zzbq.zzb;
            byte[] bArr = new byte[zzf];
            zzby zzy = zzby.zzy(bArr, 0, zzf);
            zze(zzy);
            zzy.zzz();
            return new zzbn(bArr);
        } catch (IOException e) {
            String name = getClass().getName();
            throw new RuntimeException("Serializing " + name + " to a ByteString threw an IOException (should never happen).", e);
        }
    }

    public final byte[] zzc() {
        try {
            int zzf = zzf();
            byte[] bArr = new byte[zzf];
            zzby zzy = zzby.zzy(bArr, 0, zzf);
            zze(zzy);
            zzy.zzz();
            return bArr;
        } catch (IOException e) {
            String name = getClass().getName();
            throw new RuntimeException("Serializing " + name + " to a byte array threw an IOException (should never happen).", e);
        }
    }
}
