package com.google.android.gms.internal.auth;

import com.google.android.gms.internal.auth.zzet;
import com.google.android.gms.internal.auth.zzev;

public class zzet<MessageType extends zzev<MessageType, BuilderType>, BuilderType extends zzet<MessageType, BuilderType>> extends zzdp<MessageType, BuilderType> {
    protected zzev zza;
    private final zzev zzb;

    protected zzet(MessageType messagetype) {
        this.zzb = messagetype;
        if (!messagetype.zzm()) {
            this.zza = messagetype.zzc();
            return;
        }
        throw new IllegalArgumentException("Default instance must be immutable.");
    }

    /* renamed from: zzb */
    public final zzet zza() {
        zzet zzet = (zzet) this.zzb.zzn(5, (Object) null, (Object) null);
        zzet.zza = zzd();
        return zzet;
    }

    /* renamed from: zzc */
    public MessageType zzd() {
        if (!this.zza.zzm()) {
            return this.zza;
        }
        this.zza.zzi();
        return this.zza;
    }

    public final /* bridge */ /* synthetic */ zzfx zze() {
        throw null;
    }
}
