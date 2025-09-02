package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzlz;
import com.google.android.gms.internal.measurement.zzmd;
import java.io.IOException;

public class zzlz<MessageType extends zzmd<MessageType, BuilderType>, BuilderType extends zzlz<MessageType, BuilderType>> extends zzkn<MessageType, BuilderType> {
    protected zzmd zza;
    private final zzmd zzb;

    protected zzlz(MessageType messagetype) {
        this.zzb = messagetype;
        if (!messagetype.zzcw()) {
            this.zza = messagetype.zzcj();
            return;
        }
        throw new IllegalArgumentException("Default instance must be immutable.");
    }

    private static void zza(Object obj, Object obj2) {
        zznp.zza().zzb(obj.getClass()).zzg(obj, obj2);
    }

    public final /* bridge */ /* synthetic */ zzkn zzaS(byte[] bArr, int i, int i2) throws zzmm {
        int i3 = zzlp.zzb;
        int i4 = zznp.zza;
        zzaZ(bArr, 0, i2, zzlp.zza);
        return this;
    }

    public final /* bridge */ /* synthetic */ zzkn zzaT(byte[] bArr, int i, int i2, zzlp zzlp) throws zzmm {
        zzaZ(bArr, 0, i2, zzlp);
        return this;
    }

    /* renamed from: zzaX */
    public final zzlz zzaR() {
        zzlz zzlz = (zzlz) this.zzb.zzl(5, (Object) null, (Object) null);
        zzlz.zza = zzbc();
        return zzlz;
    }

    public final zzlz zzaY(zzmd zzmd) {
        if (!this.zzb.equals(zzmd)) {
            if (!this.zza.zzcw()) {
                zzbf();
            }
            zza(this.zza, zzmd);
        }
        return this;
    }

    public final zzlz zzaZ(byte[] bArr, int i, int i2, zzlp zzlp) throws zzmm {
        if (!this.zza.zzcw()) {
            zzbf();
        }
        try {
            zznp.zza().zzb(this.zza.getClass()).zzh(this.zza, bArr, 0, i2, new zzks(zzlp));
            return this;
        } catch (zzmm e) {
            throw e;
        } catch (IndexOutOfBoundsException unused) {
            throw new zzmm("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        } catch (IOException e2) {
            throw new RuntimeException("Reading from byte array should not throw IOException.", e2);
        }
    }

    public final MessageType zzba() {
        MessageType zzbb = zzbc();
        if (zzbb.zzcD()) {
            return zzbb;
        }
        throw new zzod(zzbb);
    }

    /* renamed from: zzbb */
    public MessageType zzbc() {
        if (!this.zza.zzcw()) {
            return this.zza;
        }
        this.zza.zzcr();
        return this.zza;
    }

    /* access modifiers changed from: protected */
    public final void zzbe() {
        if (!this.zza.zzcw()) {
            zzbf();
        }
    }

    /* access modifiers changed from: protected */
    public void zzbf() {
        zzmd zzcj = this.zzb.zzcj();
        zza(zzcj, this.zza);
        this.zza = zzcj;
    }

    public final /* bridge */ /* synthetic */ zznh zzcC() {
        throw null;
    }

    public final boolean zzcD() {
        return zzmd.zzd(this.zza, false);
    }
}
