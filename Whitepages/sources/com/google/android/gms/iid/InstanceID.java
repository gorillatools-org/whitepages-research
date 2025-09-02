package com.google.android.gms.iid;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.io.IOException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Deprecated
public class InstanceID {
    public static final String ERROR_MAIN_THREAD = "MAIN_THREAD";
    public static final String ERROR_MISSING_INSTANCEID_SERVICE = "MISSING_INSTANCEID_SERVICE";
    public static final String ERROR_SERVICE_NOT_AVAILABLE = "SERVICE_NOT_AVAILABLE";
    public static final String ERROR_TIMEOUT = "TIMEOUT";
    private static final zzaj<Boolean> zzbu = zzai.zzy().zzd("gcm_check_for_different_iid_in_token", true);
    private static Map<String, InstanceID> zzbv = new ArrayMap();
    private static final long zzbw = TimeUnit.DAYS.toMillis(7);
    private static zzak zzbx;
    private static zzaf zzby;
    private static String zzbz;
    private String zzca = "";
    private Context zzl;

    @ShowFirstParty
    private InstanceID(Context context, String str) {
        this.zzl = context.getApplicationContext();
        this.zzca = str;
    }

    static int zzg(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            String valueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(valueOf.length() + 38);
            sb.append("Never happens: can't find own package ");
            sb.append(valueOf);
            Log.w("InstanceID", sb.toString());
            return 0;
        }
    }

    static String zzh(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            String valueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(valueOf.length() + 38);
            sb.append("Never happens: can't find own package ");
            sb.append(valueOf);
            Log.w("InstanceID", sb.toString());
            return null;
        }
    }

    @Deprecated
    public static InstanceID getInstance(Context context) {
        return getInstance(context, (Bundle) null);
    }

    @KeepForSdk
    public static synchronized InstanceID getInstance(Context context, Bundle bundle) {
        InstanceID instanceID;
        synchronized (InstanceID.class) {
            String string = bundle == null ? "" : bundle.getString("subtype");
            if (string == null) {
                string = "";
            }
            Context applicationContext = context.getApplicationContext();
            if (zzbx == null) {
                String packageName = applicationContext.getPackageName();
                StringBuilder sb = new StringBuilder(String.valueOf(packageName).length() + 73);
                sb.append("Instance ID SDK is deprecated, ");
                sb.append(packageName);
                sb.append(" should update to use Firebase Instance ID");
                Log.w("InstanceID", sb.toString());
                zzbx = new zzak(applicationContext);
                zzby = new zzaf(applicationContext);
            }
            zzbz = Integer.toString(zzg(applicationContext));
            instanceID = zzbv.get(string);
            if (instanceID == null) {
                instanceID = new InstanceID(applicationContext, string);
                zzbv.put(string, instanceID);
            }
        }
        return instanceID;
    }

    private final KeyPair getKeyPair() {
        return zzbx.zzj(this.zzca).getKeyPair();
    }

    @KeepForSdk
    public String getSubtype() {
        return this.zzca;
    }

    @Deprecated
    public String getId() {
        return zzd(getKeyPair());
    }

    static String zzd(KeyPair keyPair) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA1").digest(keyPair.getPublic().getEncoded());
            digest[0] = (byte) ((digest[0] & 15) + 112);
            return Base64.encodeToString(digest, 0, 8, 11);
        } catch (NoSuchAlgorithmException unused) {
            Log.w("InstanceID", "Unexpected error, device missing required algorithms");
            return null;
        }
    }

    @Deprecated
    public long getCreationTime() {
        return zzbx.zzj(this.zzca).getCreationTime();
    }

    @Deprecated
    public void deleteInstanceID() throws IOException {
        zzd("*", "*", (Bundle) null);
        zzo();
    }

    /* access modifiers changed from: package-private */
    public final void zzo() {
        zzbx.zzk(this.zzca);
    }

    @Deprecated
    public void deleteToken(String str, String str2) throws IOException {
        zzd(str, str2, (Bundle) null);
    }

    @ShowFirstParty
    public final void zzd(String str, String str2, Bundle bundle) throws IOException {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            zzbx.zzh(this.zzca, str, str2);
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putString("sender", str);
            if (str2 != null) {
                bundle.putString("scope", str2);
            }
            bundle.putString("subscription", str);
            bundle.putString("delete", "1");
            bundle.putString("X-delete", "1");
            bundle.putString("subtype", "".equals(this.zzca) ? str : this.zzca);
            if (!"".equals(this.zzca)) {
                str = this.zzca;
            }
            bundle.putString("X-subtype", str);
            zzaf.zzi(zzby.zzd(bundle, getKeyPair()));
            return;
        }
        throw new IOException(ERROR_MAIN_THREAD);
    }

    public static zzak zzp() {
        return zzbx;
    }

    @Deprecated
    public String getToken(String str, String str2) throws IOException {
        return getToken(str, str2, (Bundle) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getToken(java.lang.String r7, java.lang.String r8, android.os.Bundle r9) throws java.io.IOException {
        /*
            r6 = this;
            android.os.Looper r0 = android.os.Looper.getMainLooper()
            android.os.Looper r1 = android.os.Looper.myLooper()
            if (r0 == r1) goto L_0x0097
            com.google.android.gms.iid.zzak r0 = zzbx
            java.lang.String r1 = "appVersion"
            java.lang.String r0 = r0.get(r1)
            if (r0 == 0) goto L_0x0041
            java.lang.String r1 = zzbz
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x001d
            goto L_0x0041
        L_0x001d:
            com.google.android.gms.iid.zzak r0 = zzbx
            java.lang.String r1 = r6.zzca
            long r0 = r0.zzg(r1, r7, r8)
            r2 = 0
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 >= 0) goto L_0x002c
            goto L_0x0041
        L_0x002c:
            long r2 = java.lang.System.currentTimeMillis()
            long r2 = r2 - r0
            long r0 = zzbw
            int r0 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r0 < 0) goto L_0x0038
            goto L_0x0041
        L_0x0038:
            com.google.android.gms.iid.zzak r0 = zzbx
            java.lang.String r1 = r6.zzca
            java.lang.String r0 = r0.zzf(r1, r7, r8)
            goto L_0x0042
        L_0x0041:
            r0 = 0
        L_0x0042:
            if (r0 != 0) goto L_0x0096
            if (r9 != 0) goto L_0x004b
            android.os.Bundle r9 = new android.os.Bundle
            r9.<init>()
        L_0x004b:
            java.lang.String r9 = r6.zze(r7, r8, r9)
            com.google.android.gms.iid.zzaj<java.lang.Boolean> r0 = zzbu
            java.lang.Object r0 = r0.get()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x0087
            java.lang.String r0 = ":"
            boolean r1 = r9.contains(r0)
            if (r1 == 0) goto L_0x0087
            java.lang.String r1 = r6.getId()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r0 = r1.concat(r0)
            boolean r0 = r9.startsWith(r0)
            if (r0 == 0) goto L_0x0078
            goto L_0x0087
        L_0x0078:
            android.content.Context r7 = r6.zzl
            com.google.android.gms.iid.zzak r8 = zzbx
            com.google.android.gms.iid.InstanceIDListenerService.zzd(r7, r8)
            java.io.IOException r7 = new java.io.IOException
            java.lang.String r8 = "SERVICE_NOT_AVAILABLE"
            r7.<init>(r8)
            throw r7
        L_0x0087:
            if (r9 == 0) goto L_0x0095
            com.google.android.gms.iid.zzak r0 = zzbx
            java.lang.String r1 = r6.zzca
            java.lang.String r5 = zzbz
            r2 = r7
            r3 = r8
            r4 = r9
            r0.zzd(r1, r2, r3, r4, r5)
        L_0x0095:
            r0 = r9
        L_0x0096:
            return r0
        L_0x0097:
            java.io.IOException r7 = new java.io.IOException
            java.lang.String r8 = "MAIN_THREAD"
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.iid.InstanceID.getToken(java.lang.String, java.lang.String, android.os.Bundle):java.lang.String");
    }

    public final String zze(String str, String str2, Bundle bundle) throws IOException {
        if (str2 != null) {
            bundle.putString("scope", str2);
        }
        bundle.putString("sender", str);
        String str3 = "".equals(this.zzca) ? str : this.zzca;
        if (!bundle.containsKey("legacy.register")) {
            bundle.putString("subscription", str);
            bundle.putString("subtype", str3);
            bundle.putString("X-subscription", str);
            bundle.putString("X-subtype", str3);
        }
        String zzi = zzaf.zzi(zzby.zzd(bundle, getKeyPair()));
        if (!"RST".equals(zzi) && !zzi.startsWith("RST|")) {
            return zzi;
        }
        InstanceIDListenerService.zzd(this.zzl, zzbx);
        throw new IOException(ERROR_SERVICE_NOT_AVAILABLE);
    }
}
