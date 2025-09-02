package com.google.android.gms.internal.play_billing;

import com.google.android.gms.internal.play_billing.zzcn;
import com.google.android.gms.internal.play_billing.zzcs;

public class zzcn<MessageType extends zzcs<MessageType, BuilderType>, BuilderType extends zzcn<MessageType, BuilderType>> extends zzax<MessageType, BuilderType> {
    protected zzcs zza;
    private final zzcs zzb;

    protected zzcn(MessageType messagetype) {
        this.zzb = messagetype;
        if (!messagetype.zzw()) {
            this.zza = messagetype.zzl();
            return;
        }
        throw new IllegalArgumentException("Default instance must be immutable.");
    }

    private static void zzl(Object obj, Object obj2) {
        zzel.zza().zzb(obj.getClass()).zzg(obj, obj2);
    }

    /* renamed from: zzb */
    public final zzcn zza() {
        zzcn zzcn = (zzcn) this.zzb.zzx(5, (Object) null, (Object) null);
        zzcn.zza = zzg();
        return zzcn;
    }

    public final zzcn zzc(zzcs zzcs) {
        if (!this.zzb.equals(zzcs)) {
            if (!this.zza.zzw()) {
                zzj();
            }
            zzl(this.zza, zzcs);
        }
        return this;
    }

    /* renamed from: zzd */
    public final MessageType zzf() {
        MessageType zze = zzg();
        if (zze.zzk()) {
            return zze;
        }
        throw new zzfe(zze);
    }

    /* renamed from: zze */
    public MessageType zzg() {
        if (!this.zza.zzw()) {
            return this.zza;
        }
        this.zza.zzr();
        return this.zza;
    }

    public final /* bridge */ /* synthetic */ zzec zzh() {
        throw null;
    }

    /* access modifiers changed from: protected */
    public final void zzi() {
        if (!this.zza.zzw()) {
            zzj();
        }
    }

    /* access modifiers changed from: protected */
    public void zzj() {
        zzcs zzl = this.zzb.zzl();
        zzl(zzl, this.zza);
        this.zza = zzl;
    }

    public final boolean zzk() {
        return zzcs.zzv(this.zza, false);
    }
}
