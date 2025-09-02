package com.google.android.gms.measurement.internal;

import com.salesforce.marketingcloud.storage.db.k;
import java.util.HashMap;
import java.util.concurrent.Callable;

public final /* synthetic */ class zzib implements Callable {
    public final /* synthetic */ zzif zza;
    public final /* synthetic */ String zzb;

    public /* synthetic */ zzib(zzif zzif, String str) {
        this.zza = zzif;
        this.zzb = str;
    }

    public final Object call() {
        zzif zzif = this.zza;
        zzaw zzj = zzif.zzg.zzj();
        String str = this.zzb;
        zzh zzl = zzj.zzl(str);
        HashMap hashMap = new HashMap();
        hashMap.put(k.a.b, "android");
        hashMap.put("package_name", str);
        zzif.zzu.zzf().zzj();
        hashMap.put("gmp_version", 119002L);
        if (zzl != null) {
            String zzF = zzl.zzF();
            if (zzF != null) {
                hashMap.put(k.a.q, zzF);
            }
            hashMap.put("app_version_int", Long.valueOf(zzl.zze()));
            hashMap.put("dynamite_version", Long.valueOf(zzl.zzo()));
        }
        return hashMap;
    }
}
