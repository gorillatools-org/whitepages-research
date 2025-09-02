package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.HttpUrl;

public final class zzgx {
    protected static final AtomicReference zza = new AtomicReference();
    protected static final AtomicReference zzb = new AtomicReference();
    protected static final AtomicReference zzc = new AtomicReference();
    private final zzgw zzd;

    public zzgx(zzgw zzgw) {
        this.zzd = zzgw;
    }

    private static final String zzg(String str, String[] strArr, String[] strArr2, AtomicReference atomicReference) {
        String str2;
        Preconditions.checkNotNull(strArr);
        Preconditions.checkNotNull(strArr2);
        Preconditions.checkNotNull(atomicReference);
        Preconditions.checkArgument(strArr.length == strArr2.length);
        for (int i = 0; i < strArr.length; i++) {
            if (Objects.equals(str, strArr[i])) {
                synchronized (atomicReference) {
                    try {
                        String[] strArr3 = (String[]) atomicReference.get();
                        if (strArr3 == null) {
                            strArr3 = new String[strArr2.length];
                            atomicReference.set(strArr3);
                        }
                        str2 = strArr3[i];
                        if (str2 == null) {
                            str2 = strArr2[i] + "(" + strArr[i] + ")";
                            strArr3[i] = str2;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return str2;
            }
        }
        return str;
    }

    /* access modifiers changed from: protected */
    public final String zza(Object[] objArr) {
        String str;
        if (objArr == null) {
            return HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Bundle bundle : objArr) {
            if (bundle instanceof Bundle) {
                str = zzb(bundle);
            } else {
                str = String.valueOf(bundle);
            }
            if (str != null) {
                if (sb.length() != 1) {
                    sb.append(", ");
                }
                sb.append(str);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public final String zzb(Bundle bundle) {
        String str;
        if (bundle == null) {
            return null;
        }
        if (!this.zzd.zza()) {
            return bundle.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Bundle[{");
        for (String next : bundle.keySet()) {
            if (sb.length() != 8) {
                sb.append(", ");
            }
            sb.append(zze(next));
            sb.append("=");
            Object obj = bundle.get(next);
            if (obj instanceof Bundle) {
                str = zza(new Object[]{obj});
            } else if (obj instanceof Object[]) {
                str = zza((Object[]) obj);
            } else if (obj instanceof ArrayList) {
                str = zza(((ArrayList) obj).toArray());
            } else {
                str = String.valueOf(obj);
            }
            sb.append(str);
        }
        sb.append("}]");
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public final String zzc(zzbh zzbh) {
        String str;
        zzgw zzgw = this.zzd;
        if (!zzgw.zza()) {
            return zzbh.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("origin=");
        sb.append(zzbh.zzc);
        sb.append(",name=");
        sb.append(zzd(zzbh.zza));
        sb.append(",params=");
        zzbf zzbf = zzbh.zzb;
        if (zzbf == null) {
            str = null;
        } else if (!zzgw.zza()) {
            str = zzbf.toString();
        } else {
            str = zzb(zzbf.zzc());
        }
        sb.append(str);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public final String zzd(String str) {
        if (str == null) {
            return null;
        }
        if (!this.zzd.zza()) {
            return str;
        }
        return zzg(str, zzjy.zzc, zzjy.zza, zza);
    }

    /* access modifiers changed from: protected */
    public final String zze(String str) {
        if (str == null) {
            return null;
        }
        if (!this.zzd.zza()) {
            return str;
        }
        return zzg(str, zzjz.zzb, zzjz.zza, zzb);
    }

    /* access modifiers changed from: protected */
    public final String zzf(String str) {
        if (str == null) {
            return null;
        }
        if (!this.zzd.zza()) {
            return str;
        }
        if (!str.startsWith("_exp_")) {
            return zzg(str, zzka.zzb, zzka.zza, zzc);
        }
        return "experiment_id(" + str + ")";
    }
}
