package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzex extends zzeu {
    final /* synthetic */ Bundle zza;
    final /* synthetic */ Activity zzb;
    final /* synthetic */ zzfe zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzex(zzfe zzfe, Bundle bundle, Activity activity) {
        super(zzfe.zza, true);
        this.zza = bundle;
        this.zzb = activity;
        this.zzc = zzfe;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        Bundle bundle;
        Bundle bundle2 = this.zza;
        if (bundle2 != null) {
            bundle = new Bundle();
            if (bundle2.containsKey("com.google.app_measurement.screen_service")) {
                Object obj = bundle2.get("com.google.app_measurement.screen_service");
                if (obj instanceof Bundle) {
                    bundle.putBundle("com.google.app_measurement.screen_service", (Bundle) obj);
                }
            }
        } else {
            bundle = null;
        }
        Activity activity = this.zzb;
        ((zzcv) Preconditions.checkNotNull(this.zzc.zza.zzj)).onActivityCreatedByScionActivityInfo(zzdj.zza(activity), bundle, this.zzi);
    }
}
