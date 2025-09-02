package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.measurement.dynamite.ModuleDescriptor;
import com.google.android.gms.measurement.internal.zzig;

final class zzdu extends zzeu {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ Context zzc;
    final /* synthetic */ Bundle zzd;
    final /* synthetic */ zzff zze;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdu(zzff zzff, String str, String str2, Context context, Bundle bundle) {
        super(zzff, true);
        this.zza = str;
        this.zzb = str2;
        this.zzc = context;
        this.zzd = bundle;
        this.zze = zzff;
    }

    public final void zza() {
        String str;
        String str2;
        String str3;
        try {
            zzff zzff = this.zze;
            String str4 = this.zza;
            String str5 = this.zzb;
            if (zzff.zzX(str4, str5)) {
                str2 = str4;
                str = str5;
                str3 = zzff.zzd;
            } else {
                str3 = null;
                str2 = null;
                str = null;
            }
            Context context = this.zzc;
            Preconditions.checkNotNull(context);
            zzff.zzj = zzff.zzf(context, true);
            if (zzff.zzj == null) {
                Log.w(zzff.zzd, "Failed to connect to measurement client.");
                return;
            }
            int localVersion = DynamiteModule.getLocalVersion(context, ModuleDescriptor.MODULE_ID);
            int remoteVersion = DynamiteModule.getRemoteVersion(context, ModuleDescriptor.MODULE_ID);
            ((zzcv) Preconditions.checkNotNull(zzff.zzj)).initialize(ObjectWrapper.wrap(context), new zzdh(119002, (long) Math.max(localVersion, remoteVersion), remoteVersion < localVersion, str3, str2, str, this.zzd, zzig.zza(context)), this.zzh);
        } catch (Exception e) {
            this.zze.zzU(e, true, false);
        }
    }
}
