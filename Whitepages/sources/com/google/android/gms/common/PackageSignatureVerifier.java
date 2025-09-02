package com.google.android.gms.common;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;

@ShowFirstParty
@KeepForSdk
public class PackageSignatureVerifier {
    static volatile zzab zza;
    private static zzac zzb;

    private static zzac zza(Context context) {
        zzac zzac;
        synchronized (PackageSignatureVerifier.class) {
            try {
                if (zzb == null) {
                    zzb = new zzac(context);
                }
                zzac = zzb;
            } catch (Throwable th) {
                throw th;
            }
        }
        return zzac;
    }

    @ShowFirstParty
    @KeepForSdk
    public PackageVerificationResult queryPackageSignatureVerified(Context context, String str) {
        String str2;
        boolean honorsDebugCertificates = GooglePlayServicesUtilLight.honorsDebugCertificates(context);
        zza(context);
        if (zzn.zzf()) {
            if (true != honorsDebugCertificates) {
                str2 = "-0";
            } else {
                str2 = "-1";
            }
            String concat = String.valueOf(str).concat(str2);
            if (zza != null && zza.zza.equals(concat)) {
                return zza.zzb;
            }
            zza(context);
            zzw zzc = zzn.zzc(str, honorsDebugCertificates, false, false);
            if (zzc.zza) {
                zza = new zzab(concat, PackageVerificationResult.zzd(str, zzc.zzd));
                return zza.zzb;
            }
            Preconditions.checkNotNull(zzc.zzb);
            return PackageVerificationResult.zza(str, zzc.zzb, zzc.zzc);
        }
        throw new zzad();
    }

    @ShowFirstParty
    @KeepForSdk
    public PackageVerificationResult queryPackageSignatureVerifiedWithRetry(Context context, String str) {
        try {
            PackageVerificationResult queryPackageSignatureVerified = queryPackageSignatureVerified(context, str);
            queryPackageSignatureVerified.zzb();
            return queryPackageSignatureVerified;
        } catch (SecurityException e) {
            PackageVerificationResult queryPackageSignatureVerified2 = queryPackageSignatureVerified(context, str);
            if (!queryPackageSignatureVerified2.zzc()) {
                return queryPackageSignatureVerified2;
            }
            Log.e("PkgSignatureVerifier", "Got flaky result during package signature verification", e);
            return queryPackageSignatureVerified2;
        }
    }
}
