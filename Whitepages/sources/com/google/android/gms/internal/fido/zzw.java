package com.google.android.gms.internal.fido;

import android.os.IBinder;
import android.os.IInterface;

public final class zzw extends zza implements IInterface {
    zzw(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fido.u2f.internal.regular.IU2fAppService");
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.google.android.gms.internal.fido.zzv, android.os.IBinder] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzc(com.google.android.gms.internal.fido.zzv r3, com.google.android.gms.fido.u2f.api.common.RegisterRequestParams r4) throws android.os.RemoteException {
        /*
            r2 = this;
            android.os.Parcel r0 = r2.zza()
            int r1 = com.google.android.gms.internal.fido.zzc.zza
            r0.writeStrongBinder(r3)
            com.google.android.gms.internal.fido.zzc.zzd(r0, r4)
            r3 = 1
            r2.zzb(r3, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.fido.zzw.zzc(com.google.android.gms.internal.fido.zzv, com.google.android.gms.fido.u2f.api.common.RegisterRequestParams):void");
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.google.android.gms.internal.fido.zzv, android.os.IBinder] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzd(com.google.android.gms.internal.fido.zzv r3, com.google.android.gms.fido.u2f.api.common.SignRequestParams r4) throws android.os.RemoteException {
        /*
            r2 = this;
            android.os.Parcel r0 = r2.zza()
            int r1 = com.google.android.gms.internal.fido.zzc.zza
            r0.writeStrongBinder(r3)
            com.google.android.gms.internal.fido.zzc.zzd(r0, r4)
            r3 = 2
            r2.zzb(r3, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.fido.zzw.zzd(com.google.android.gms.internal.fido.zzv, com.google.android.gms.fido.u2f.api.common.SignRequestParams):void");
    }
}
