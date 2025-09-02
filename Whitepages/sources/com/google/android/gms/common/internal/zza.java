package com.google.android.gms.common.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

abstract class zza extends zzc {
    public final int zza;
    public final Bundle zzb;
    final /* synthetic */ BaseGmsClient zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected zza(BaseGmsClient baseGmsClient, int i, Bundle bundle) {
        super(baseGmsClient, Boolean.TRUE);
        this.zzc = baseGmsClient;
        this.zza = i;
        this.zzb = bundle;
    }

    /* JADX WARNING: type inference failed for: r3v6, types: [android.os.Parcelable] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ void zza(java.lang.Object r3) {
        /*
            r2 = this;
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            int r3 = r2.zza
            r0 = 1
            r1 = 0
            if (r3 != 0) goto L_0x001e
            boolean r3 = r2.zzd()
            if (r3 != 0) goto L_0x001d
            com.google.android.gms.common.internal.BaseGmsClient r3 = r2.zzc
            r3.zzp(r0, (android.os.IInterface) null)
            com.google.android.gms.common.ConnectionResult r3 = new com.google.android.gms.common.ConnectionResult
            r0 = 8
            r3.<init>(r0, r1)
            r2.zzb(r3)
        L_0x001d:
            return
        L_0x001e:
            com.google.android.gms.common.internal.BaseGmsClient r3 = r2.zzc
            r3.zzp(r0, (android.os.IInterface) null)
            android.os.Bundle r3 = r2.zzb
            if (r3 == 0) goto L_0x0030
            java.lang.String r0 = "pendingIntent"
            android.os.Parcelable r3 = r3.getParcelable(r0)
            r1 = r3
            android.app.PendingIntent r1 = (android.app.PendingIntent) r1
        L_0x0030:
            int r3 = r2.zza
            com.google.android.gms.common.ConnectionResult r0 = new com.google.android.gms.common.ConnectionResult
            r0.<init>(r3, r1)
            r2.zzb(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.zza.zza(java.lang.Object):void");
    }

    /* access modifiers changed from: protected */
    public abstract void zzb(ConnectionResult connectionResult);

    /* access modifiers changed from: protected */
    public final void zzc() {
    }

    /* access modifiers changed from: protected */
    public abstract boolean zzd();
}
