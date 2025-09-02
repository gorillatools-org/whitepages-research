package com.google.android.gms.measurement.internal;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;

public final class zzhe extends zzjr {
    /* access modifiers changed from: private */
    public char zza = 0;
    /* access modifiers changed from: private */
    public long zzb = -1;
    private String zzc;
    private final zzhc zzd = new zzhc(this, 6, false, false);
    private final zzhc zze = new zzhc(this, 6, true, false);
    private final zzhc zzf = new zzhc(this, 6, false, true);
    private final zzhc zzg = new zzhc(this, 5, false, false);
    private final zzhc zzh = new zzhc(this, 5, true, false);
    private final zzhc zzi = new zzhc(this, 5, false, true);
    private final zzhc zzj = new zzhc(this, 4, false, false);
    private final zzhc zzk = new zzhc(this, 3, false, false);
    private final zzhc zzl = new zzhc(this, 2, false, false);

    zzhe(zzio zzio) {
        super(zzio);
    }

    protected static Object zzn(String str) {
        if (str == null) {
            return null;
        }
        return new zzhd(str);
    }

    static String zzo(boolean z, String str, Object obj, Object obj2, Object obj3) {
        String zzp = zzp(z, obj);
        String zzp2 = zzp(z, obj2);
        String zzp3 = zzp(z, obj3);
        StringBuilder sb = new StringBuilder();
        String str2 = "";
        if (str == null) {
            str = str2;
        }
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            str2 = ": ";
        }
        String str3 = ", ";
        if (!TextUtils.isEmpty(zzp)) {
            sb.append(str2);
            sb.append(zzp);
            str2 = str3;
        }
        if (!TextUtils.isEmpty(zzp2)) {
            sb.append(str2);
            sb.append(zzp2);
        } else {
            str3 = str2;
        }
        if (!TextUtils.isEmpty(zzp3)) {
            sb.append(str3);
            sb.append(zzp3);
        }
        return sb.toString();
    }

    static String zzp(boolean z, Object obj) {
        String className;
        String str = "";
        if (obj == null) {
            return str;
        }
        if (obj instanceof Integer) {
            obj = Long.valueOf((long) ((Integer) obj).intValue());
        }
        int i = 0;
        if (obj instanceof Long) {
            if (!z) {
                return obj.toString();
            }
            Long l = (Long) obj;
            if (Math.abs(l.longValue()) < 100) {
                return obj.toString();
            }
            char charAt = obj.toString().charAt(0);
            String valueOf = String.valueOf(Math.abs(l.longValue()));
            long round = Math.round(Math.pow(10.0d, (double) (valueOf.length() - 1)));
            long round2 = Math.round(Math.pow(10.0d, (double) valueOf.length()) - 4.0d);
            StringBuilder sb = new StringBuilder();
            if (charAt == '-') {
                str = "-";
            }
            sb.append(str);
            sb.append(round);
            sb.append("...");
            sb.append(str);
            sb.append(round2);
            return sb.toString();
        } else if (obj instanceof Boolean) {
            return obj.toString();
        } else {
            if (obj instanceof Throwable) {
                Throwable th = (Throwable) obj;
                StringBuilder sb2 = new StringBuilder(z ? th.getClass().getName() : th.toString());
                String zzq = zzq(zzio.class.getCanonicalName());
                StackTraceElement[] stackTrace = th.getStackTrace();
                int length = stackTrace.length;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    StackTraceElement stackTraceElement = stackTrace[i];
                    if (!stackTraceElement.isNativeMethod() && (className = stackTraceElement.getClassName()) != null && zzq(className).equals(zzq)) {
                        sb2.append(": ");
                        sb2.append(stackTraceElement);
                        break;
                    }
                    i++;
                }
                return sb2.toString();
            } else if (obj instanceof zzhd) {
                return ((zzhd) obj).zza;
            } else {
                if (z) {
                    return "-";
                }
                return obj.toString();
            }
        }
    }

    static String zzq(String str) {
        int lastIndexOf;
        if (!TextUtils.isEmpty(str) && (lastIndexOf = str.lastIndexOf(46)) != -1) {
            return str.substring(0, lastIndexOf);
        }
        return "";
    }

    /* access modifiers changed from: protected */
    public final boolean zzc() {
        return false;
    }

    public final zzhc zzd() {
        return this.zzk;
    }

    public final zzhc zze() {
        return this.zzd;
    }

    public final zzhc zzf() {
        return this.zzf;
    }

    public final zzhc zzh() {
        return this.zze;
    }

    public final zzhc zzi() {
        return this.zzj;
    }

    public final zzhc zzj() {
        return this.zzl;
    }

    public final zzhc zzk() {
        return this.zzg;
    }

    public final zzhc zzl() {
        return this.zzi;
    }

    public final zzhc zzm() {
        return this.zzh;
    }

    /* access modifiers changed from: protected */
    public final String zzr() {
        String str;
        synchronized (this) {
            try {
                if (this.zzc == null) {
                    zzio zzio = this.zzu;
                    if (zzio.zzz() != null) {
                        this.zzc = zzio.zzz();
                    } else {
                        this.zzc = this.zzu.zzf().zzq();
                    }
                }
                Preconditions.checkNotNull(this.zzc);
                str = this.zzc;
            } catch (Throwable th) {
                throw th;
            }
        }
        return str;
    }

    /* access modifiers changed from: protected */
    public final void zzu(int i, boolean z, boolean z2, String str, Object obj, Object obj2, Object obj3) {
        if (!z && Log.isLoggable(zzr(), i)) {
            Log.println(i, zzr(), zzo(false, str, obj, obj2, obj3));
        }
        if (!z2 && i >= 5) {
            Preconditions.checkNotNull(str);
            zzil zzo = this.zzu.zzo();
            if (zzo == null) {
                Log.println(6, zzr(), "Scheduler not set. Not logging error/warn");
            } else if (!zzo.zzy()) {
                Log.println(6, zzr(), "Scheduler not initialized. Not logging error/warn");
            } else {
                if (i >= 9) {
                    i = 8;
                }
                zzo.zzq(new zzhb(this, i, str, obj, obj2, obj3));
            }
        }
    }
}
