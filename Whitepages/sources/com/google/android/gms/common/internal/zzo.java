package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.net.Uri;
import com.google.firebase.analytics.FirebaseAnalytics;

public final class zzo {
    private static final Uri zza = new Uri.Builder().scheme(FirebaseAnalytics.Param.CONTENT).authority("com.google.android.gms.chimera").build();
    private final String zzb;
    private final String zzc;
    private final ComponentName zzd;
    private final int zze;
    private final boolean zzf;

    public zzo(ComponentName componentName, int i) {
        this.zzb = null;
        this.zzc = null;
        Preconditions.checkNotNull(componentName);
        this.zzd = componentName;
        this.zze = 4225;
        this.zzf = false;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzo)) {
            return false;
        }
        zzo zzo = (zzo) obj;
        return Objects.equal(this.zzb, zzo.zzb) && Objects.equal(this.zzc, zzo.zzc) && Objects.equal(this.zzd, zzo.zzd) && this.zzf == zzo.zzf;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzb, this.zzc, this.zzd, 4225, Boolean.valueOf(this.zzf));
    }

    public final String toString() {
        String str = this.zzb;
        if (str != null) {
            return str;
        }
        Preconditions.checkNotNull(this.zzd);
        return this.zzd.flattenToString();
    }

    public final ComponentName zza() {
        return this.zzd;
    }

    /* JADX WARNING: type inference failed for: r6v9, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0090 A[PHI: r2 
      PHI: (r2v3 android.content.Intent) = (r2v1 android.content.Intent), (r2v5 android.content.Intent), (r2v5 android.content.Intent) binds: [B:26:0x0050, B:28:0x005b, B:30:0x0065] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.content.Intent zzb(android.content.Context r6) throws com.google.android.gms.common.internal.zzaj {
        /*
            r5 = this;
            java.lang.String r0 = "ConnectionStatusConfig"
            java.lang.String r1 = r5.zzb
            if (r1 == 0) goto L_0x00b1
            boolean r1 = r5.zzf
            r2 = 0
            if (r1 == 0) goto L_0x00a1
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            java.lang.String r3 = r5.zzb
            java.lang.String r4 = "serviceActionBundleKey"
            r1.putString(r4, r3)
            android.content.ContentResolver r6 = r6.getContentResolver()     // Catch:{ RemoteException -> 0x0038, IllegalArgumentException -> 0x0036 }
            android.net.Uri r3 = zza     // Catch:{ RemoteException -> 0x0038, IllegalArgumentException -> 0x0036 }
            android.content.ContentProviderClient r6 = r6.acquireUnstableContentProviderClient(r3)     // Catch:{ RemoteException -> 0x0038, IllegalArgumentException -> 0x0036 }
            if (r6 == 0) goto L_0x003a
            java.lang.String r3 = "serviceIntentCall"
            android.os.Bundle r1 = r6.call(r3, r2, r1)     // Catch:{ all -> 0x0031 }
            r6.release()     // Catch:{ RemoteException -> 0x002f, IllegalArgumentException -> 0x002d }
            goto L_0x0050
        L_0x002d:
            r6 = move-exception
            goto L_0x0043
        L_0x002f:
            r6 = move-exception
            goto L_0x0043
        L_0x0031:
            r1 = move-exception
            r6.release()     // Catch:{ RemoteException -> 0x0038, IllegalArgumentException -> 0x0036 }
            throw r1     // Catch:{ RemoteException -> 0x0038, IllegalArgumentException -> 0x0036 }
        L_0x0036:
            r6 = move-exception
            goto L_0x0042
        L_0x0038:
            r6 = move-exception
            goto L_0x0042
        L_0x003a:
            android.os.RemoteException r6 = new android.os.RemoteException     // Catch:{ RemoteException -> 0x0038, IllegalArgumentException -> 0x0036 }
            java.lang.String r1 = "Failed to acquire ContentProviderClient"
            r6.<init>(r1)     // Catch:{ RemoteException -> 0x0038, IllegalArgumentException -> 0x0036 }
            throw r6     // Catch:{ RemoteException -> 0x0038, IllegalArgumentException -> 0x0036 }
        L_0x0042:
            r1 = r2
        L_0x0043:
            java.lang.String r3 = "Dynamic intent resolution failed: "
            java.lang.String r6 = r6.toString()
            java.lang.String r6 = r3.concat(r6)
            android.util.Log.w(r0, r6)
        L_0x0050:
            if (r1 == 0) goto L_0x0090
            java.lang.String r6 = "serviceResponseIntentKey"
            android.os.Parcelable r6 = r1.getParcelable(r6)
            r2 = r6
            android.content.Intent r2 = (android.content.Intent) r2
            if (r2 != 0) goto L_0x0090
            java.lang.String r6 = "serviceMissingResolutionIntentKey"
            android.os.Parcelable r6 = r1.getParcelable(r6)
            android.app.PendingIntent r6 = (android.app.PendingIntent) r6
            if (r6 != 0) goto L_0x0068
            goto L_0x0090
        L_0x0068:
            java.lang.String r1 = r5.zzb
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Dynamic lookup for intent failed for action "
            r2.append(r3)
            r2.append(r1)
            java.lang.String r1 = " but has possible resolution"
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            android.util.Log.w(r0, r1)
            com.google.android.gms.common.internal.zzaj r0 = new com.google.android.gms.common.internal.zzaj
            com.google.android.gms.common.ConnectionResult r1 = new com.google.android.gms.common.ConnectionResult
            r2 = 25
            r1.<init>(r2, r6)
            r0.<init>(r1)
            throw r0
        L_0x0090:
            if (r2 != 0) goto L_0x00a1
            java.lang.String r6 = r5.zzb
            java.lang.String r6 = java.lang.String.valueOf(r6)
            java.lang.String r1 = "Dynamic lookup for intent failed for action: "
            java.lang.String r6 = r1.concat(r6)
            android.util.Log.w(r0, r6)
        L_0x00a1:
            if (r2 != 0) goto L_0x00bc
            java.lang.String r6 = r5.zzb
            android.content.Intent r0 = new android.content.Intent
            r0.<init>(r6)
            java.lang.String r6 = r5.zzc
            android.content.Intent r6 = r0.setPackage(r6)
            return r6
        L_0x00b1:
            android.content.Intent r6 = new android.content.Intent
            r6.<init>()
            android.content.ComponentName r0 = r5.zzd
            android.content.Intent r2 = r6.setComponent(r0)
        L_0x00bc:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.zzo.zzb(android.content.Context):android.content.Intent");
    }

    public final String zzc() {
        return this.zzc;
    }

    public zzo(String str, int i, boolean z) {
        this(str, "com.google.android.gms", 4225, false);
    }

    public zzo(String str, String str2, int i, boolean z) {
        Preconditions.checkNotEmpty(str);
        this.zzb = str;
        Preconditions.checkNotEmpty(str2);
        this.zzc = str2;
        this.zzd = null;
        this.zze = 4225;
        this.zzf = z;
    }
}
