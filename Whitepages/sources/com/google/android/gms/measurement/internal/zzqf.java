package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.ext.SdkExtensions;
import android.text.TextUtils;
import androidx.privacysandbox.ads.adservices.java.measurement.MeasurementManagerFutures;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzcy;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.salesforce.marketingcloud.b;
import com.salesforce.marketingcloud.sfmcsdk.components.http.NetworkManager;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import java.util.TreeSet;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import javax.security.auth.x500.X500Principal;

public final class zzqf extends zzjr {
    public static final /* synthetic */ int zza = 0;
    private static final String[] zzb = {"firebase_", "google_", "ga_"};
    private static final String[] zzc = {"_err"};
    private SecureRandom zzd;
    private final AtomicLong zze = new AtomicLong(0);
    private int zzf;
    private MeasurementManagerFutures zzg;
    private Boolean zzh;
    private Integer zzi = null;

    zzqf(zzio zzio) {
        super(zzio);
    }

    static MessageDigest zzI() {
        int i = 0;
        while (i < 2) {
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                if (instance != null) {
                    return instance;
                }
                i++;
            } catch (NoSuchAlgorithmException unused) {
            }
        }
        return null;
    }

    public static ArrayList zzK(List list) {
        if (list == null) {
            return new ArrayList(0);
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            zzai zzai = (zzai) it.next();
            Bundle bundle = new Bundle();
            bundle.putString("app_id", zzai.zza);
            bundle.putString("origin", zzai.zzb);
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, zzai.zzd);
            bundle.putString("name", zzai.zzc.zzb);
            zzjt.zzb(bundle, Preconditions.checkNotNull(zzai.zzc.zza()));
            bundle.putBoolean("active", zzai.zze);
            String str = zzai.zzf;
            if (str != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, str);
            }
            zzbh zzbh = zzai.zzg;
            if (zzbh != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, zzbh.zza);
                zzbf zzbf = zzbh.zzb;
                if (zzbf != null) {
                    bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, zzbf.zzc());
                }
            }
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, zzai.zzh);
            zzbh zzbh2 = zzai.zzi;
            if (zzbh2 != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, zzbh2.zza);
                zzbf zzbf2 = zzbh2.zzb;
                if (zzbf2 != null) {
                    bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, zzbf2.zzc());
                }
            }
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, zzai.zzc.zzc);
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, zzai.zzj);
            zzbh zzbh3 = zzai.zzk;
            if (zzbh3 != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, zzbh3.zza);
                zzbf zzbf3 = zzbh3.zzb;
                if (zzbf3 != null) {
                    bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, zzbf3.zzc());
                }
            }
            arrayList.add(bundle);
        }
        return arrayList;
    }

    public static void zzN(zzmh zzmh, Bundle bundle, boolean z) {
        if (!(bundle == null || zzmh == null)) {
            if (!bundle.containsKey("_sc") || z) {
                String str = zzmh.zza;
                if (str != null) {
                    bundle.putString("_sn", str);
                } else {
                    bundle.remove("_sn");
                }
                String str2 = zzmh.zzb;
                if (str2 != null) {
                    bundle.putString("_sc", str2);
                } else {
                    bundle.remove("_sc");
                }
                bundle.putLong("_si", zzmh.zzc);
                return;
            }
            z = false;
        }
        if (bundle != null && zzmh == null && z) {
            bundle.remove("_sn");
            bundle.remove("_sc");
            bundle.remove("_si");
        }
    }

    private final int zzaA(String str) {
        if ("_ldl".equals(str)) {
            this.zzu.zzf();
            return b.u;
        } else if ("_id".equals(str)) {
            this.zzu.zzf();
            return 256;
        } else if ("_lgclid".equals(str)) {
            this.zzu.zzf();
            return 100;
        } else {
            this.zzu.zzf();
            return 36;
        }
    }

    private final Object zzaB(int i, Object obj, boolean z, boolean z2, String str) {
        if (obj == null) {
            return null;
        }
        if ((obj instanceof Long) || (obj instanceof Double)) {
            return obj;
        }
        if (obj instanceof Integer) {
            return Long.valueOf((long) ((Integer) obj).intValue());
        }
        if (obj instanceof Byte) {
            return Long.valueOf((long) ((Byte) obj).byteValue());
        }
        if (obj instanceof Short) {
            return Long.valueOf((long) ((Short) obj).shortValue());
        }
        if (obj instanceof Boolean) {
            return Long.valueOf(true != ((Boolean) obj).booleanValue() ? 0 : 1);
        } else if (obj instanceof Float) {
            return Double.valueOf(((Float) obj).doubleValue());
        } else {
            if ((obj instanceof String) || (obj instanceof Character) || (obj instanceof CharSequence)) {
                return zzG(obj.toString(), i, z);
            }
            if (!z2 || (!(obj instanceof Bundle[]) && !(obj instanceof Parcelable[]))) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (Parcelable parcelable : (Parcelable[]) obj) {
                if (parcelable instanceof Bundle) {
                    Bundle zzz = zzz((Bundle) parcelable, (String) null);
                    if (!zzz.isEmpty()) {
                        arrayList.add(zzz);
                    }
                }
            }
            return arrayList.toArray(new Bundle[arrayList.size()]);
        }
    }

    private static boolean zzaC(String str, String[] strArr) {
        Preconditions.checkNotNull(strArr);
        for (String equals : strArr) {
            if (Objects.equals(str, equals)) {
                return true;
            }
        }
        return false;
    }

    static boolean zzap(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith("_");
    }

    static boolean zzaq(String str) {
        Preconditions.checkNotEmpty(str);
        if (str.charAt(0) != '_' || str.equals("_ep")) {
            return true;
        }
        return false;
    }

    static boolean zzar(Context context) {
        ActivityInfo receiverInfo;
        Preconditions.checkNotNull(context);
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null || (receiverInfo = packageManager.getReceiverInfo(new ComponentName(context, "com.google.android.gms.measurement.AppMeasurementReceiver"), 0)) == null || !receiverInfo.enabled) {
                return false;
            }
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    static boolean zzas(Context context, String str) {
        ServiceInfo serviceInfo;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null || (serviceInfo = packageManager.getServiceInfo(new ComponentName(context, str), 0)) == null || !serviceInfo.enabled) {
                return false;
            }
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    static boolean zzat(Context context, boolean z) {
        Preconditions.checkNotNull(context);
        return zzas(context, "com.google.android.gms.measurement.AppMeasurementJobService");
    }

    public static boolean zzau(String str) {
        return !zzc[0].equals(str);
    }

    static final boolean zzaz(Bundle bundle, int i) {
        if (bundle == null || bundle.getLong("_err") != 0) {
            return false;
        }
        bundle.putLong("_err", (long) i);
        return true;
    }

    static long zzr(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        int length = bArr.length;
        int i = 0;
        Preconditions.checkState(length > 0);
        int i2 = length - 1;
        long j = 0;
        while (i2 >= 0 && i2 >= bArr.length - 8) {
            j += (((long) bArr[i2]) & 255) << i;
            i += 8;
            i2--;
        }
        return j;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: java.lang.String} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.os.Bundle zzA(java.lang.String r22, java.lang.String r23, android.os.Bundle r24, java.util.List r25, boolean r26) {
        /*
            r21 = this;
            r9 = r21
            r10 = r23
            r11 = r24
            r12 = r25
            java.lang.String[] r0 = com.google.android.gms.measurement.internal.zzjy.zzd
            boolean r13 = zzaC(r10, r0)
            if (r11 == 0) goto L_0x010b
            android.os.Bundle r15 = new android.os.Bundle
            r15.<init>(r11)
            com.google.android.gms.measurement.internal.zzio r8 = r9.zzu
            com.google.android.gms.measurement.internal.zzam r0 = r8.zzf()
            int r7 = r0.zze()
            java.util.TreeSet r0 = new java.util.TreeSet
            java.util.Set r1 = r24.keySet()
            r0.<init>(r1)
            java.util.Iterator r16 = r0.iterator()
            r17 = 0
            r18 = r17
            r19 = r18
        L_0x0032:
            boolean r0 = r16.hasNext()
            if (r0 == 0) goto L_0x0109
            java.lang.Object r0 = r16.next()
            r6 = r0
            java.lang.String r6 = (java.lang.String) r6
            if (r12 == 0) goto L_0x004b
            boolean r0 = r12.contains(r6)
            if (r0 != 0) goto L_0x0048
            goto L_0x004b
        L_0x0048:
            r0 = r17
            goto L_0x005a
        L_0x004b:
            if (r26 != 0) goto L_0x0052
            int r0 = r9.zzi(r6)
            goto L_0x0054
        L_0x0052:
            r0 = r17
        L_0x0054:
            if (r0 != 0) goto L_0x005a
            int r0 = r9.zzh(r6)
        L_0x005a:
            if (r0 == 0) goto L_0x006e
            r1 = 3
            if (r0 != r1) goto L_0x0061
            r1 = r6
            goto L_0x0062
        L_0x0061:
            r1 = 0
        L_0x0062:
            r9.zzM(r15, r0, r6, r1)
            r15.remove(r6)
            r12 = r7
            r20 = r8
        L_0x006b:
            r3 = 0
            goto L_0x0102
        L_0x006e:
            java.lang.Object r4 = r11.get(r6)
            r0 = r21
            r1 = r22
            r2 = r23
            r3 = r6
            r5 = r15
            r14 = r6
            r6 = r25
            r12 = r7
            r7 = r26
            r20 = r8
            r8 = r13
            int r0 = r0.zza(r1, r2, r3, r4, r5, r6, r7, r8)
            r1 = 17
            if (r0 != r1) goto L_0x0091
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            r9.zzM(r15, r1, r14, r0)
            goto L_0x00ad
        L_0x0091:
            if (r0 == 0) goto L_0x00ad
            java.lang.String r1 = "_ev"
            boolean r1 = r1.equals(r14)
            if (r1 != 0) goto L_0x00ad
            r1 = 21
            if (r0 != r1) goto L_0x00a1
            r6 = r10
            goto L_0x00a2
        L_0x00a1:
            r6 = r14
        L_0x00a2:
            java.lang.Object r1 = r11.get(r14)
            r9.zzM(r15, r0, r6, r1)
            r15.remove(r14)
            goto L_0x006b
        L_0x00ad:
            boolean r0 = zzaq(r14)
            if (r0 == 0) goto L_0x006b
            int r0 = r18 + 1
            if (r0 <= r12) goto L_0x0100
            com.google.android.gms.measurement.internal.zzam r1 = r20.zzf()
            com.google.android.gms.measurement.internal.zzgg r2 = com.google.android.gms.measurement.internal.zzgi.zzbr
            r3 = 0
            boolean r1 = r1.zzx(r3, r2)
            if (r1 == 0) goto L_0x00c6
            if (r19 != 0) goto L_0x00f7
        L_0x00c6:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Event can't contain more than "
            r1.append(r2)
            r1.append(r12)
            java.lang.String r2 = " params"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.google.android.gms.measurement.internal.zzhe r2 = r20.zzaW()
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zzf()
            com.google.android.gms.measurement.internal.zzgx r4 = r20.zzj()
            java.lang.String r4 = r4.zzd(r10)
            com.google.android.gms.measurement.internal.zzgx r5 = r20.zzj()
            java.lang.String r5 = r5.zzb(r11)
            r2.zzc(r1, r4, r5)
        L_0x00f7:
            r1 = 5
            zzaz(r15, r1)
            r15.remove(r14)
            r19 = 1
        L_0x0100:
            r18 = r0
        L_0x0102:
            r7 = r12
            r8 = r20
            r12 = r25
            goto L_0x0032
        L_0x0109:
            r14 = r15
            goto L_0x010d
        L_0x010b:
            r3 = 0
            r14 = r3
        L_0x010d:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzqf.zzA(java.lang.String, java.lang.String, android.os.Bundle, java.util.List, boolean):android.os.Bundle");
    }

    /* access modifiers changed from: package-private */
    public final MeasurementManagerFutures zzB() {
        if (this.zzg == null) {
            this.zzg = MeasurementManagerFutures.from(this.zzu.zzaT());
        }
        return this.zzg;
    }

    /* access modifiers changed from: package-private */
    public final zzbh zzC(String str, String str2, Bundle bundle, String str3, long j, boolean z, boolean z2) {
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        if (zzf(str2) == 0) {
            Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
            bundle2.putString("_o", str3);
            Bundle zzA = zzA(str, str2, bundle2, CollectionUtils.listOf("_o"), true);
            if (z) {
                zzA = zzz(zzA, str);
            }
            Preconditions.checkNotNull(zzA);
            return new zzbh(str2, new zzbf(zzA), str3, j);
        }
        zzio zzio = this.zzu;
        zzio.zzaW().zze().zzb("Invalid conditional property event name", zzio.zzj().zzf(str2));
        throw new IllegalArgumentException();
    }

    /* access modifiers changed from: package-private */
    public final Object zzD(String str, Object obj) {
        int zzc2;
        if ("_ev".equals(str)) {
            return zzaB(this.zzu.zzf().zzd((String) null, false), obj, true, true, (String) null);
        }
        if (zzap(str)) {
            zzc2 = this.zzu.zzf().zzd((String) null, false);
        } else {
            zzc2 = this.zzu.zzf().zzc((String) null, false);
        }
        return zzaB(zzc2, obj, false, true, (String) null);
    }

    /* access modifiers changed from: package-private */
    public final Object zzE(String str, Object obj) {
        if ("_ldl".equals(str)) {
            return zzaB(zzaA(str), obj, true, false, (String) null);
        }
        return zzaB(zzaA(str), obj, false, false, (String) null);
    }

    /* access modifiers changed from: package-private */
    public final String zzF() {
        byte[] bArr = new byte[16];
        zzJ().nextBytes(bArr);
        return String.format(Locale.US, "%032x", new Object[]{new BigInteger(1, bArr)});
    }

    public final String zzG(String str, int i, boolean z) {
        if (str == null) {
            return null;
        }
        if (str.codePointCount(0, str.length()) <= i) {
            return str;
        }
        if (z) {
            return String.valueOf(str.substring(0, str.offsetByCodePoints(0, i))).concat("...");
        }
        return null;
    }

    public final URL zzH(long j, String str, String str2, long j2, String str3) {
        try {
            Preconditions.checkNotEmpty(str2);
            Preconditions.checkNotEmpty(str);
            String format = String.format("https://www.googleadservices.com/pagead/conversion/app/deeplink?id_type=adid&sdk_version=%s&rdid=%s&bundleid=%s&retry=%s", new Object[]{String.format("v%s.%s", new Object[]{119002L, Integer.valueOf(zzm())}), str2, str, Long.valueOf(j2)});
            if (str.equals(this.zzu.zzf().zzp())) {
                format = format.concat("&ddl_test=1");
            }
            if (!str3.isEmpty()) {
                if (str3.charAt(0) != '&') {
                    format = format.concat("&");
                }
                format = format.concat(str3);
            }
            return new URL(format);
        } catch (MalformedURLException e) {
            e = e;
            this.zzu.zzaW().zze().zzb("Failed to create BOW URL for Deferred Deep Link. exception", e.getMessage());
            return null;
        } catch (IllegalArgumentException e2) {
            e = e2;
            this.zzu.zzaW().zze().zzb("Failed to create BOW URL for Deferred Deep Link. exception", e.getMessage());
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final SecureRandom zzJ() {
        zzg();
        if (this.zzd == null) {
            this.zzd = new SecureRandom();
        }
        return this.zzd;
    }

    /* access modifiers changed from: package-private */
    public final void zzL(Bundle bundle, long j) {
        long j2 = bundle.getLong("_et");
        if (j2 != 0) {
            this.zzu.zzaW().zzk().zzb("Params already contained engagement", Long.valueOf(j2));
        } else {
            j2 = 0;
        }
        bundle.putLong("_et", j + j2);
    }

    /* access modifiers changed from: package-private */
    public final void zzM(Bundle bundle, int i, String str, Object obj) {
        if (zzaz(bundle, i)) {
            this.zzu.zzf();
            bundle.putString("_ev", zzG(str, 40, true));
            if (obj != null) {
                Preconditions.checkNotNull(bundle);
                if ((obj instanceof String) || (obj instanceof CharSequence)) {
                    bundle.putLong("_el", (long) obj.toString().length());
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzO(Bundle bundle, Bundle bundle2) {
        if (bundle2 != null) {
            for (String next : bundle2.keySet()) {
                if (!bundle.containsKey(next)) {
                    this.zzu.zzw().zzS(bundle, next, bundle2.get(next));
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzP(Parcelable[] parcelableArr, int i) {
        Preconditions.checkNotNull(parcelableArr);
        for (Bundle bundle : parcelableArr) {
            int i2 = 0;
            boolean z = false;
            for (String str : new TreeSet(bundle.keySet())) {
                if (zzaq(str) && !zzaC(str, zzjz.zzd) && (i2 = i2 + 1) > i) {
                    zzio zzio = this.zzu;
                    if (!zzio.zzf().zzx((String) null, zzgi.zzbr) || !z) {
                        zzio.zzaW().zzf().zzc("Param can't contain more than " + i + " item-scoped custom parameters", zzio.zzj().zze(str), zzio.zzj().zzb(bundle));
                    }
                    zzaz(bundle, 28);
                    bundle.remove(str);
                    z = true;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzQ(zzhf zzhf, int i) {
        Bundle bundle = zzhf.zzd;
        int i2 = 0;
        boolean z = false;
        for (String str : new TreeSet(bundle.keySet())) {
            if (zzaq(str) && (i2 = i2 + 1) > i) {
                zzio zzio = this.zzu;
                if (!zzio.zzf().zzx((String) null, zzgi.zzbr) || !z) {
                    zzio.zzaW().zzf().zzc("Event can't contain more than " + i + " params", zzio.zzj().zzd(zzhf.zza), zzio.zzj().zzb(bundle));
                    zzaz(bundle, 5);
                }
                bundle.remove(str);
                z = true;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzR(zzqe zzqe, String str, int i, String str2, String str3, int i2) {
        Bundle bundle = new Bundle();
        zzaz(bundle, i);
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            bundle.putString(str2, str3);
        }
        if (i == 6 || i == 7 || i == 2) {
            bundle.putLong("_el", (long) i2);
        }
        zzqe.zza(str, "_err", bundle);
    }

    /* access modifiers changed from: package-private */
    public final void zzS(Bundle bundle, String str, Object obj) {
        if (bundle != null) {
            if (obj instanceof Long) {
                bundle.putLong(str, ((Long) obj).longValue());
            } else if (obj instanceof String) {
                bundle.putString(str, String.valueOf(obj));
            } else if (obj instanceof Double) {
                bundle.putDouble(str, ((Double) obj).doubleValue());
            } else if (obj instanceof Bundle[]) {
                bundle.putParcelableArray(str, (Bundle[]) obj);
            } else if (str != null) {
                String simpleName = obj != null ? obj.getClass().getSimpleName() : null;
                zzio zzio = this.zzu;
                zzio.zzaW().zzl().zzc("Not putting event parameter. Invalid value type. name, type", zzio.zzj().zze(str), simpleName);
            }
        }
    }

    public final void zzT(zzcy zzcy, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("r", z);
        try {
            zzcy.zze(bundle);
        } catch (RemoteException e) {
            this.zzu.zzaW().zzk().zzb("Error returning boolean value to wrapper", e);
        }
    }

    public final void zzU(zzcy zzcy, ArrayList arrayList) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("r", arrayList);
        try {
            zzcy.zze(bundle);
        } catch (RemoteException e) {
            this.zzu.zzaW().zzk().zzb("Error returning bundle list to wrapper", e);
        }
    }

    public final void zzV(zzcy zzcy, Bundle bundle) {
        try {
            zzcy.zze(bundle);
        } catch (RemoteException e) {
            this.zzu.zzaW().zzk().zzb("Error returning bundle value to wrapper", e);
        }
    }

    public final void zzW(zzcy zzcy, byte[] bArr) {
        Bundle bundle = new Bundle();
        bundle.putByteArray("r", bArr);
        try {
            zzcy.zze(bundle);
        } catch (RemoteException e) {
            this.zzu.zzaW().zzk().zzb("Error returning byte array to wrapper", e);
        }
    }

    public final void zzX(zzcy zzcy, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("r", i);
        try {
            zzcy.zze(bundle);
        } catch (RemoteException e) {
            this.zzu.zzaW().zzk().zzb("Error returning int value to wrapper", e);
        }
    }

    public final void zzY(zzcy zzcy, long j) {
        Bundle bundle = new Bundle();
        bundle.putLong("r", j);
        try {
            zzcy.zze(bundle);
        } catch (RemoteException e) {
            this.zzu.zzaW().zzk().zzb("Error returning long value to wrapper", e);
        }
    }

    public final void zzZ(zzcy zzcy, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("r", str);
        try {
            zzcy.zze(bundle);
        } catch (RemoteException e) {
            this.zzu.zzaW().zzk().zzb("Error returning string value to wrapper", e);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00d3 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00d4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zza(java.lang.String r14, java.lang.String r15, java.lang.String r16, java.lang.Object r17, android.os.Bundle r18, java.util.List r19, boolean r20, boolean r21) {
        /*
            r13 = this;
            r7 = r13
            r8 = r16
            r0 = r17
            r1 = r18
            r13.zzg()
            boolean r2 = r13.zzal(r0)
            java.lang.String r3 = "param"
            r4 = 0
            if (r2 == 0) goto L_0x00a5
            if (r21 == 0) goto L_0x00a7
            java.lang.String[] r2 = com.google.android.gms.measurement.internal.zzjz.zzc
            boolean r2 = zzaC(r8, r2)
            if (r2 != 0) goto L_0x0020
            r0 = 20
            return r0
        L_0x0020:
            com.google.android.gms.measurement.internal.zzio r2 = r7.zzu
            com.google.android.gms.measurement.internal.zzny r2 = r2.zzu()
            r2.zzg()
            r2.zza()
            boolean r5 = r2.zzad()
            if (r5 != 0) goto L_0x0033
            goto L_0x0045
        L_0x0033:
            com.google.android.gms.measurement.internal.zzio r2 = r2.zzu
            com.google.android.gms.measurement.internal.zzqf r2 = r2.zzw()
            int r2 = r2.zzm()
            r5 = 200900(0x310c4, float:2.81521E-40)
            if (r2 >= r5) goto L_0x0045
            r0 = 25
            return r0
        L_0x0045:
            com.google.android.gms.measurement.internal.zzio r2 = r7.zzu
            r2.zzf()
            boolean r5 = r0 instanceof android.os.Parcelable[]
            if (r5 == 0) goto L_0x0053
            r6 = r0
            android.os.Parcelable[] r6 = (android.os.Parcelable[]) r6
            int r6 = r6.length
            goto L_0x005e
        L_0x0053:
            boolean r6 = r0 instanceof java.util.ArrayList
            if (r6 == 0) goto L_0x00a5
            r6 = r0
            java.util.ArrayList r6 = (java.util.ArrayList) r6
            int r6 = r6.size()
        L_0x005e:
            r9 = 200(0xc8, float:2.8E-43)
            if (r6 <= r9) goto L_0x00a5
            com.google.android.gms.measurement.internal.zzhe r10 = r2.zzaW()
            com.google.android.gms.measurement.internal.zzhc r10 = r10.zzl()
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.String r11 = "Parameter array is too long; discarded. Value kind, name, array length"
            r10.zzd(r11, r3, r8, r6)
            r2.zzf()
            r2 = 17
            if (r5 == 0) goto L_0x008b
            r5 = r0
            android.os.Parcelable[] r5 = (android.os.Parcelable[]) r5
            int r6 = r5.length
            if (r6 <= r9) goto L_0x0089
            java.lang.Object[] r5 = java.util.Arrays.copyOf(r5, r9)
            android.os.Parcelable[] r5 = (android.os.Parcelable[]) r5
            r1.putParcelableArray(r8, r5)
        L_0x0089:
            r9 = r2
            goto L_0x00aa
        L_0x008b:
            boolean r5 = r0 instanceof java.util.ArrayList
            if (r5 == 0) goto L_0x0089
            r5 = r0
            java.util.ArrayList r5 = (java.util.ArrayList) r5
            int r6 = r5.size()
            if (r6 <= r9) goto L_0x0089
            java.util.ArrayList r6 = new java.util.ArrayList
            java.util.List r5 = r5.subList(r4, r9)
            r6.<init>(r5)
            r1.putParcelableArrayList(r8, r6)
            goto L_0x0089
        L_0x00a5:
            r9 = r4
            goto L_0x00aa
        L_0x00a7:
            r0 = 21
            return r0
        L_0x00aa:
            boolean r1 = zzap(r15)
            r2 = 0
            if (r1 != 0) goto L_0x00c3
            boolean r1 = zzap(r16)
            if (r1 == 0) goto L_0x00b8
            goto L_0x00c3
        L_0x00b8:
            com.google.android.gms.measurement.internal.zzio r1 = r7.zzu
            com.google.android.gms.measurement.internal.zzam r1 = r1.zzf()
            int r1 = r1.zzc(r2, r4)
            goto L_0x00cd
        L_0x00c3:
            com.google.android.gms.measurement.internal.zzio r1 = r7.zzu
            com.google.android.gms.measurement.internal.zzam r1 = r1.zzf()
            int r1 = r1.zzd(r2, r4)
        L_0x00cd:
            boolean r1 = r13.zzaf(r3, r8, r1, r0)
            if (r1 == 0) goto L_0x00d4
            return r9
        L_0x00d4:
            if (r21 == 0) goto L_0x0164
            boolean r1 = r0 instanceof android.os.Bundle
            if (r1 == 0) goto L_0x00eb
            r4 = r0
            android.os.Bundle r4 = (android.os.Bundle) r4
            r0 = r13
            r1 = r14
            r2 = r15
            r3 = r16
            r5 = r19
            r6 = r20
            r0.zzaa(r1, r2, r3, r4, r5, r6)
            goto L_0x0163
        L_0x00eb:
            boolean r1 = r0 instanceof android.os.Parcelable[]
            if (r1 == 0) goto L_0x0122
            r10 = r0
            android.os.Parcelable[] r10 = (android.os.Parcelable[]) r10
            int r11 = r10.length
            r12 = r4
        L_0x00f4:
            if (r12 >= r11) goto L_0x0163
            r0 = r10[r12]
            boolean r1 = r0 instanceof android.os.Bundle
            if (r1 != 0) goto L_0x0110
            com.google.android.gms.measurement.internal.zzio r1 = r7.zzu
            com.google.android.gms.measurement.internal.zzhe r1 = r1.zzaW()
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zzl()
            java.lang.Class r0 = r0.getClass()
            java.lang.String r2 = "All Parcelable[] elements must be of type Bundle. Value type, name"
            r1.zzc(r2, r0, r8)
            goto L_0x0164
        L_0x0110:
            r4 = r0
            android.os.Bundle r4 = (android.os.Bundle) r4
            r0 = r13
            r1 = r14
            r2 = r15
            r3 = r16
            r5 = r19
            r6 = r20
            r0.zzaa(r1, r2, r3, r4, r5, r6)
            int r12 = r12 + 1
            goto L_0x00f4
        L_0x0122:
            boolean r1 = r0 instanceof java.util.ArrayList
            if (r1 == 0) goto L_0x0164
            r10 = r0
            java.util.ArrayList r10 = (java.util.ArrayList) r10
            int r11 = r10.size()
            r12 = r4
        L_0x012e:
            if (r12 >= r11) goto L_0x0163
            java.lang.Object r0 = r10.get(r12)
            boolean r1 = r0 instanceof android.os.Bundle
            if (r1 != 0) goto L_0x0151
            com.google.android.gms.measurement.internal.zzio r1 = r7.zzu
            com.google.android.gms.measurement.internal.zzhe r1 = r1.zzaW()
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zzl()
            if (r0 == 0) goto L_0x0149
            java.lang.Class r0 = r0.getClass()
            goto L_0x014b
        L_0x0149:
            java.lang.String r0 = "null"
        L_0x014b:
            java.lang.String r2 = "All ArrayList elements must be of type Bundle. Value type, name"
            r1.zzc(r2, r0, r8)
            goto L_0x0164
        L_0x0151:
            r4 = r0
            android.os.Bundle r4 = (android.os.Bundle) r4
            r0 = r13
            r1 = r14
            r2 = r15
            r3 = r16
            r5 = r19
            r6 = r20
            r0.zzaa(r1, r2, r3, r4, r5, r6)
            int r12 = r12 + 1
            goto L_0x012e
        L_0x0163:
            return r9
        L_0x0164:
            r0 = 4
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzqf.zza(java.lang.String, java.lang.String, java.lang.String, java.lang.Object, android.os.Bundle, java.util.List, boolean, boolean):int");
    }

    /* access modifiers changed from: protected */
    public final void zzaZ() {
        zzg();
        SecureRandom secureRandom = new SecureRandom();
        long nextLong = secureRandom.nextLong();
        if (nextLong == 0) {
            nextLong = secureRandom.nextLong();
            if (nextLong == 0) {
                this.zzu.zzaW().zzk().zza("Utils falling back to Random for random id");
            }
        }
        this.zze.set(nextLong);
    }

    /* access modifiers changed from: package-private */
    public final void zzaa(String str, String str2, String str3, Bundle bundle, List list, boolean z) {
        int i;
        int i2;
        char c;
        int i3;
        int i4;
        String str4;
        int i5;
        String str5 = str2;
        Bundle bundle2 = bundle;
        List list2 = list;
        if (bundle2 != null) {
            zzio zzio = this.zzu;
            char c2 = 20064;
            int i6 = true != zzio.zzf().zzu.zzw().zzao(231100000, true) ? 0 : 35;
            int i7 = 0;
            boolean z2 = false;
            for (String str6 : new TreeSet(bundle.keySet())) {
                if (list2 == null || !list2.contains(str6)) {
                    i = !z ? zzi(str6) : 0;
                    if (i == 0) {
                        i = zzh(str6);
                    }
                } else {
                    i = 0;
                }
                String str7 = null;
                if (i != 0) {
                    if (i == 3) {
                        str7 = str6;
                    }
                    zzM(bundle2, i, str6, str7);
                    bundle2.remove(str6);
                    i2 = i6;
                    c = c2;
                } else {
                    if (zzal(bundle2.get(str6))) {
                        zzio.zzaW().zzl().zzd("Nested Bundle parameters are not allowed; discarded. event name, param name, child param name", str5, str3, str6);
                        i5 = 22;
                        str4 = str6;
                        i4 = i6;
                    } else {
                        String str8 = str3;
                        str4 = str6;
                        i4 = i6;
                        i5 = zza(str, str2, str6, bundle2.get(str6), bundle, list, z, false);
                    }
                    if (i5 != 0 && !"_ev".equals(str4)) {
                        zzM(bundle2, i5, str4, bundle2.get(str4));
                        bundle2.remove(str4);
                    } else if (zzaq(str4) && !zzaC(str4, zzjz.zzd)) {
                        int i8 = i7 + 1;
                        c = 20064;
                        if (!zzao(231100000, true)) {
                            zzio.zzaW().zzf().zzc("Item array not supported on client's version of Google Play Services (Android Only)", zzio.zzj().zzd(str5), zzio.zzj().zzb(bundle2));
                            zzaz(bundle2, 23);
                            bundle2.remove(str4);
                            i2 = i4;
                        } else {
                            i2 = i4;
                            if (i8 > i2) {
                                if (!zzio.zzf().zzx((String) null, zzgi.zzbr) || !z2) {
                                    zzio.zzaW().zzf().zzc("Item can't contain more than " + i2 + " item-scoped custom params", zzio.zzj().zzd(str5), zzio.zzj().zzb(bundle2));
                                }
                                zzaz(bundle2, 28);
                                bundle2.remove(str4);
                                i7 = i8;
                                c2 = 20064;
                                i3 = i2;
                                z2 = true;
                            }
                        }
                        i7 = i8;
                    }
                    i2 = i4;
                    c = 20064;
                }
                c2 = c;
                i3 = i2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zzab(String str) {
        String str2 = (String) zzgi.zzaq.zza((Object) null);
        return str2.equals("*") || Arrays.asList(str2.split(",")).contains(str);
    }

    /* access modifiers changed from: package-private */
    public final boolean zzac(String str, String str2) {
        zzio zzio = this.zzu;
        if (zzio.zzf().zzx((String) null, zzgi.zzbp)) {
            if (!TextUtils.isEmpty(str)) {
                if (zzax(str)) {
                    return true;
                }
                if (this.zzu.zzL()) {
                    zzio.zzaW().zzf().zzb("Invalid google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI. provided id", zzhe.zzn(str));
                    return false;
                }
            } else if (this.zzu.zzL()) {
                zzio.zzaW().zzf().zza("Missing google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI");
                return false;
            }
            return false;
        }
        if (!TextUtils.isEmpty(str)) {
            if (!zzax(str)) {
                if (this.zzu.zzL()) {
                    zzio.zzaW().zzf().zzb("Invalid google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI. provided id", zzhe.zzn(str));
                }
                return false;
            }
        } else if (TextUtils.isEmpty(str2)) {
            if (this.zzu.zzL()) {
                zzio.zzaW().zzf().zza("Missing google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI");
            }
            return false;
        } else if (!zzax(str2)) {
            zzio.zzaW().zzf().zzb("Invalid admob_app_id. Analytics disabled.", zzhe.zzn(str2));
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzad(String str, int i, String str2) {
        if (str2 == null) {
            this.zzu.zzaW().zzf().zzb("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.codePointCount(0, str2.length()) <= i) {
            return true;
        } else {
            this.zzu.zzaW().zzf().zzd("Name is too long. Type, maximum supported length, name", str, Integer.valueOf(i), str2);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zzae(String str, String[] strArr, String[] strArr2, String str2) {
        if (str2 == null) {
            this.zzu.zzaW().zzf().zzb("Name is required and can't be null. Type", str);
            return false;
        }
        Preconditions.checkNotNull(str2);
        String[] strArr3 = zzb;
        for (int i = 0; i < 3; i++) {
            if (str2.startsWith(strArr3[i])) {
                this.zzu.zzaW().zzf().zzc("Name starts with reserved prefix. Type, name", str, str2);
                return false;
            }
        }
        if (strArr == null || !zzaC(str2, strArr)) {
            return true;
        }
        if (strArr2 != null && zzaC(str2, strArr2)) {
            return true;
        }
        this.zzu.zzaW().zzf().zzc("Name is reserved. Type, name", str, str2);
        return false;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzaf(String str, String str2, int i, Object obj) {
        if (obj != null && !(obj instanceof Long) && !(obj instanceof Float) && !(obj instanceof Integer) && !(obj instanceof Byte) && !(obj instanceof Short) && !(obj instanceof Boolean) && !(obj instanceof Double)) {
            if (!(obj instanceof String) && !(obj instanceof Character) && !(obj instanceof CharSequence)) {
                return false;
            }
            String obj2 = obj.toString();
            if (obj2.codePointCount(0, obj2.length()) > i) {
                this.zzu.zzaW().zzl().zzd("Value is too long; discarded. Value kind, name, value length", str, str2, Integer.valueOf(obj2.length()));
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzag(String str, String str2) {
        if (str2 == null) {
            this.zzu.zzaW().zzf().zzb("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            this.zzu.zzaW().zzf().zzb("Name is required and can't be empty. Type", str);
            return false;
        } else {
            int codePointAt = str2.codePointAt(0);
            if (!Character.isLetter(codePointAt)) {
                if (codePointAt == 95) {
                    codePointAt = 95;
                } else {
                    this.zzu.zzaW().zzf().zzc("Name must start with a letter or _ (underscore). Type, name", str, str2);
                    return false;
                }
            }
            int length = str2.length();
            int charCount = Character.charCount(codePointAt);
            while (charCount < length) {
                int codePointAt2 = str2.codePointAt(charCount);
                if (codePointAt2 == 95 || Character.isLetterOrDigit(codePointAt2)) {
                    charCount += Character.charCount(codePointAt2);
                } else {
                    this.zzu.zzaW().zzf().zzc("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                    return false;
                }
            }
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zzah(String str, String str2) {
        if (str2 == null) {
            this.zzu.zzaW().zzf().zzb("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            this.zzu.zzaW().zzf().zzb("Name is required and can't be empty. Type", str);
            return false;
        } else {
            int codePointAt = str2.codePointAt(0);
            if (!Character.isLetter(codePointAt)) {
                this.zzu.zzaW().zzf().zzc("Name must start with a letter. Type, name", str, str2);
                return false;
            }
            int length = str2.length();
            int charCount = Character.charCount(codePointAt);
            while (charCount < length) {
                int codePointAt2 = str2.codePointAt(charCount);
                if (codePointAt2 == 95 || Character.isLetterOrDigit(codePointAt2)) {
                    charCount += Character.charCount(codePointAt2);
                } else {
                    this.zzu.zzaW().zzf().zzc("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                    return false;
                }
            }
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    @TargetApi(30)
    public final boolean zzai() {
        Integer num;
        Object e;
        if (this.zzh == null) {
            MeasurementManagerFutures zzB = zzB();
            boolean z = false;
            if (zzB == null) {
                return false;
            }
            try {
                num = (Integer) zzB.getMeasurementApiStatusAsync().get(10000, TimeUnit.MILLISECONDS);
                if (num != null) {
                    try {
                        if (num.intValue() == 1) {
                            z = true;
                        }
                    } catch (CancellationException e2) {
                        e = e2;
                        this.zzu.zzaW().zzk().zzb("Measurement manager api exception", e);
                        this.zzh = Boolean.FALSE;
                        this.zzu.zzaW().zzj().zzb("Measurement manager api status result", num);
                        return this.zzh.booleanValue();
                    } catch (ExecutionException e3) {
                        e = e3;
                        this.zzu.zzaW().zzk().zzb("Measurement manager api exception", e);
                        this.zzh = Boolean.FALSE;
                        this.zzu.zzaW().zzj().zzb("Measurement manager api status result", num);
                        return this.zzh.booleanValue();
                    } catch (InterruptedException e4) {
                        e = e4;
                        this.zzu.zzaW().zzk().zzb("Measurement manager api exception", e);
                        this.zzh = Boolean.FALSE;
                        this.zzu.zzaW().zzj().zzb("Measurement manager api status result", num);
                        return this.zzh.booleanValue();
                    } catch (TimeoutException e5) {
                        e = e5;
                        this.zzu.zzaW().zzk().zzb("Measurement manager api exception", e);
                        this.zzh = Boolean.FALSE;
                        this.zzu.zzaW().zzj().zzb("Measurement manager api status result", num);
                        return this.zzh.booleanValue();
                    }
                }
                this.zzh = Boolean.valueOf(z);
            } catch (InterruptedException | CancellationException | ExecutionException | TimeoutException e6) {
                e = e6;
                num = null;
                this.zzu.zzaW().zzk().zzb("Measurement manager api exception", e);
                this.zzh = Boolean.FALSE;
                this.zzu.zzaW().zzj().zzb("Measurement manager api status result", num);
                return this.zzh.booleanValue();
            }
            this.zzu.zzaW().zzj().zzb("Measurement manager api status result", num);
        }
        return this.zzh.booleanValue();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzaj(String str) {
        zzg();
        zzio zzio = this.zzu;
        if (Wrappers.packageManager(zzio.zzaT()).checkCallingOrSelfPermission(str) == 0) {
            return true;
        }
        zzio.zzaW().zzd().zzb("Permission not granted", str);
        return false;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzak(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            return true;
        }
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        zzio zzio = this.zzu;
        String zzo = zzio.zzf().zzo();
        zzio.zzaV();
        return zzo.equals(str);
    }

    /* access modifiers changed from: package-private */
    public final boolean zzal(Object obj) {
        return (obj instanceof Parcelable[]) || (obj instanceof ArrayList) || (obj instanceof Bundle);
    }

    /* access modifiers changed from: package-private */
    public final boolean zzam(Context context, String str) {
        Signature[] signatureArr;
        X500Principal x500Principal = new X500Principal("CN=Android Debug,O=Android,C=US");
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(str, 64);
            if (packageInfo == null || (signatureArr = packageInfo.signatures) == null || signatureArr.length <= 0) {
                return true;
            }
            return ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signatureArr[0].toByteArray()))).getSubjectX500Principal().equals(x500Principal);
        } catch (CertificateException e) {
            this.zzu.zzaW().zze().zzb("Error obtaining certificate", e);
            return true;
        } catch (PackageManager.NameNotFoundException e2) {
            this.zzu.zzaW().zze().zzb("Package name not found", e2);
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zzan() {
        zzg();
        return zzq() == 1;
    }

    public final boolean zzao(int i, boolean z) {
        Boolean zzl = this.zzu.zzu().zzl();
        if (zzm() >= i / 1000) {
            return true;
        }
        if (zzl != null) {
            return !zzl.booleanValue();
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzav(String str, String str2) {
        boolean isEmpty = TextUtils.isEmpty(str);
        boolean isEmpty2 = TextUtils.isEmpty(str2);
        if (!isEmpty && !isEmpty2) {
            Preconditions.checkNotNull(str);
            if (!str.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzaw(String str, String str2, String str3, String str4) {
        if (this.zzu.zzf().zzx((String) null, zzgi.zzbp)) {
            return zzav(str, str2);
        }
        boolean isEmpty = TextUtils.isEmpty(str);
        boolean isEmpty2 = TextUtils.isEmpty(str2);
        if (!isEmpty && !isEmpty2) {
            Preconditions.checkNotNull(str);
            return !str.equals(str2);
        } else if (isEmpty && isEmpty2) {
            return (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) ? !TextUtils.isEmpty(str4) : !str3.equals(str4);
        } else {
            if (isEmpty) {
                return TextUtils.isEmpty(str3) || !str3.equals(str4);
            }
            if (TextUtils.isEmpty(str4)) {
                return false;
            }
            return TextUtils.isEmpty(str3) || !str3.equals(str4);
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zzax(String str) {
        String str2;
        Preconditions.checkNotNull(str);
        if (true != this.zzu.zzf().zzx((String) null, zzgi.zzbp)) {
            str2 = "^(1:\\d+:android:[a-f0-9]+|ca-app-pub-.*)$";
        } else {
            str2 = "^1:\\d+:android:[a-f0-9]+$";
        }
        return str.matches(str2);
    }

    /* access modifiers changed from: package-private */
    public final byte[] zzay(Parcelable parcelable) {
        if (parcelable == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        try {
            parcelable.writeToParcel(obtain, 0);
            return obtain.marshall();
        } finally {
            obtain.recycle();
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zzc() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public final int zzd(String str, Object obj) {
        boolean z;
        if ("_ldl".equals(str)) {
            z = zzaf("user property referrer", str, zzaA(str), obj);
        } else {
            z = zzaf("user property", str, zzaA(str), obj);
        }
        return z ? 0 : 7;
    }

    /* access modifiers changed from: package-private */
    public final int zzf(String str) {
        if (!zzag("event", str)) {
            return 2;
        }
        if (!zzae("event", zzjy.zza, zzjy.zzb, str)) {
            return 13;
        }
        this.zzu.zzf();
        if (!zzad("event", 40, str)) {
            return 2;
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public final int zzh(String str) {
        if (!zzag("event param", str)) {
            return 3;
        }
        if (!zzae("event param", (String[]) null, (String[]) null, str)) {
            return 14;
        }
        this.zzu.zzf();
        if (!zzad("event param", 40, str)) {
            return 3;
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public final int zzi(String str) {
        if (!zzah("event param", str)) {
            return 3;
        }
        if (!zzae("event param", (String[]) null, (String[]) null, str)) {
            return 14;
        }
        this.zzu.zzf();
        if (!zzad("event param", 40, str)) {
            return 3;
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public final int zzj(String str) {
        if (!zzag("user property", str)) {
            return 6;
        }
        if (!zzae("user property", zzka.zza, (String[]) null, str)) {
            return 15;
        }
        this.zzu.zzf();
        if (!zzad("user property", 24, str)) {
            return 6;
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public final int zzl() {
        if (Build.VERSION.SDK_INT < 30 || SdkExtensions.getExtensionVersion(30) <= 3) {
            return 0;
        }
        return SdkExtensions.getExtensionVersion(1000000);
    }

    public final int zzm() {
        if (this.zzi == null) {
            this.zzi = Integer.valueOf(GoogleApiAvailabilityLight.getInstance().getApkVersion(this.zzu.zzaT()) / 1000);
        }
        return this.zzi.intValue();
    }

    public final int zzp(int i) {
        return GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(this.zzu.zzaT(), GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }

    /* access modifiers changed from: package-private */
    public final long zzq() {
        long j;
        zzg();
        if (!zzab(this.zzu.zzh().zzm())) {
            return 0;
        }
        if (Build.VERSION.SDK_INT < 30) {
            j = 4;
        } else if (SdkExtensions.getExtensionVersion(30) < 4) {
            j = 8;
        } else {
            zzio zzio = this.zzu;
            int zzl = zzl();
            zzio.zzf();
            j = zzl < ((Integer) zzgi.zzak.zza((Object) null)).intValue() ? 16 : 0;
        }
        if (!zzaj("android.permission.ACCESS_ADSERVICES_ATTRIBUTION")) {
            j |= 2;
        }
        if (j == 0 && !zzai()) {
            j = 64;
        }
        if (j == 0) {
            return 1;
        }
        return j;
    }

    public final long zzs() {
        long andIncrement;
        long j;
        AtomicLong atomicLong = this.zze;
        if (atomicLong.get() == 0) {
            synchronized (atomicLong) {
                long nextLong = new Random(System.nanoTime() ^ this.zzu.zzaU().currentTimeMillis()).nextLong();
                int i = this.zzf + 1;
                this.zzf = i;
                j = nextLong + ((long) i);
            }
            return j;
        }
        AtomicLong atomicLong2 = this.zze;
        synchronized (atomicLong2) {
            atomicLong2.compareAndSet(-1, 1);
            andIncrement = atomicLong2.getAndIncrement();
        }
        return andIncrement;
    }

    public final long zzt(long j, long j2) {
        return (j + (j2 * 60000)) / NetworkManager.MAX_SERVER_RETRY;
    }

    /* access modifiers changed from: package-private */
    public final Bundle zzu(Uri uri) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        Uri uri2 = uri;
        if (uri2 != null) {
            try {
                if (uri.isHierarchical()) {
                    str9 = uri2.getQueryParameter("utm_campaign");
                    str8 = uri2.getQueryParameter("utm_source");
                    str7 = uri2.getQueryParameter("utm_medium");
                    str6 = uri2.getQueryParameter("gclid");
                    str5 = uri2.getQueryParameter("gbraid");
                    str4 = uri2.getQueryParameter("utm_id");
                    str3 = uri2.getQueryParameter("dclid");
                    str2 = uri2.getQueryParameter("srsltid");
                    str = uri2.getQueryParameter("sfmc_id");
                } else {
                    str9 = null;
                    str8 = null;
                    str7 = null;
                    str6 = null;
                    str5 = null;
                    str4 = null;
                    str3 = null;
                    str2 = null;
                    str = null;
                }
                if (TextUtils.isEmpty(str9) && TextUtils.isEmpty(str8) && TextUtils.isEmpty(str7) && TextUtils.isEmpty(str6) && TextUtils.isEmpty(str5) && TextUtils.isEmpty(str4) && TextUtils.isEmpty(str3) && TextUtils.isEmpty(str2) && TextUtils.isEmpty(str)) {
                    return null;
                }
                Bundle bundle = new Bundle();
                if (!TextUtils.isEmpty(str9)) {
                    str10 = "sfmc_id";
                    bundle.putString("campaign", str9);
                } else {
                    str10 = "sfmc_id";
                }
                if (!TextUtils.isEmpty(str8)) {
                    bundle.putString("source", str8);
                }
                if (!TextUtils.isEmpty(str7)) {
                    bundle.putString("medium", str7);
                }
                if (!TextUtils.isEmpty(str6)) {
                    bundle.putString("gclid", str6);
                }
                if (!TextUtils.isEmpty(str5)) {
                    bundle.putString("gbraid", str5);
                }
                String queryParameter = uri2.getQueryParameter("gad_source");
                if (!TextUtils.isEmpty(queryParameter)) {
                    bundle.putString("gad_source", queryParameter);
                }
                String queryParameter2 = uri2.getQueryParameter("utm_term");
                if (!TextUtils.isEmpty(queryParameter2)) {
                    bundle.putString(FirebaseAnalytics.Param.TERM, queryParameter2);
                }
                String queryParameter3 = uri2.getQueryParameter("utm_content");
                if (!TextUtils.isEmpty(queryParameter3)) {
                    bundle.putString(FirebaseAnalytics.Param.CONTENT, queryParameter3);
                }
                String queryParameter4 = uri2.getQueryParameter(FirebaseAnalytics.Param.ACLID);
                if (!TextUtils.isEmpty(queryParameter4)) {
                    bundle.putString(FirebaseAnalytics.Param.ACLID, queryParameter4);
                }
                String queryParameter5 = uri2.getQueryParameter(FirebaseAnalytics.Param.CP1);
                if (!TextUtils.isEmpty(queryParameter5)) {
                    bundle.putString(FirebaseAnalytics.Param.CP1, queryParameter5);
                }
                String queryParameter6 = uri2.getQueryParameter("anid");
                if (!TextUtils.isEmpty(queryParameter6)) {
                    bundle.putString("anid", queryParameter6);
                }
                if (!TextUtils.isEmpty(str4)) {
                    bundle.putString(FirebaseAnalytics.Param.CAMPAIGN_ID, str4);
                }
                if (!TextUtils.isEmpty(str3)) {
                    bundle.putString("dclid", str3);
                }
                String queryParameter7 = uri2.getQueryParameter("utm_source_platform");
                if (!TextUtils.isEmpty(queryParameter7)) {
                    bundle.putString(FirebaseAnalytics.Param.SOURCE_PLATFORM, queryParameter7);
                }
                String queryParameter8 = uri2.getQueryParameter("utm_creative_format");
                if (!TextUtils.isEmpty(queryParameter8)) {
                    bundle.putString(FirebaseAnalytics.Param.CREATIVE_FORMAT, queryParameter8);
                }
                String queryParameter9 = uri2.getQueryParameter("utm_marketing_tactic");
                if (!TextUtils.isEmpty(queryParameter9)) {
                    bundle.putString(FirebaseAnalytics.Param.MARKETING_TACTIC, queryParameter9);
                }
                if (!TextUtils.isEmpty(str2)) {
                    bundle.putString("srsltid", str2);
                }
                if (!TextUtils.isEmpty(str)) {
                    bundle.putString(str10, str);
                }
                return bundle;
            } catch (UnsupportedOperationException e) {
                this.zzu.zzaW().zzk().zzb("Install referrer url isn't a hierarchical URI", e);
                return null;
            }
        } else {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final Bundle zzz(Bundle bundle, String str) {
        Bundle bundle2 = new Bundle();
        if (bundle != null) {
            for (String next : bundle.keySet()) {
                Object zzD = zzD(next, bundle.get(next));
                if (zzD == null) {
                    zzio zzio = this.zzu;
                    zzio.zzaW().zzl().zzb("Param value can't be null", zzio.zzj().zze(next));
                } else {
                    zzS(bundle2, next, zzD);
                }
            }
        }
        return bundle2;
    }
}
