package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzfp;
import com.google.android.gms.internal.measurement.zzfv;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

abstract class zzab {
    final String zzb;
    final int zzc;
    Boolean zzd;
    Boolean zze;
    Long zzf;
    Long zzg;

    zzab(String str, int i) {
        this.zzb = str;
        this.zzc = i;
    }

    private static Boolean zzd(String str, int i, boolean z, String str2, List list, String str3, zzhe zzhe) {
        if (i == 7) {
            if (list == null || list.isEmpty()) {
                return null;
            }
        } else if (str2 == null) {
            return null;
        }
        if (!z && i != 2) {
            str = str.toUpperCase(Locale.ENGLISH);
        }
        switch (i - 1) {
            case 1:
                if (str3 == null) {
                    return null;
                }
                try {
                    return Boolean.valueOf(Pattern.compile(str3, true != z ? 66 : 0).matcher(str).matches());
                } catch (PatternSyntaxException unused) {
                    if (zzhe != null) {
                        zzhe.zzk().zzb("Invalid regular expression in REGEXP audience filter. expression", str3);
                    }
                    return null;
                }
            case 2:
                return Boolean.valueOf(str.startsWith(str2));
            case 3:
                return Boolean.valueOf(str.endsWith(str2));
            case 4:
                return Boolean.valueOf(str.contains(str2));
            case 5:
                return Boolean.valueOf(str.equals(str2));
            case 6:
                if (list == null) {
                    return null;
                }
                return Boolean.valueOf(list.contains(str));
            default:
                return null;
        }
    }

    static Boolean zze(BigDecimal bigDecimal, zzfp zzfp, double d) {
        BigDecimal bigDecimal2;
        BigDecimal bigDecimal3;
        BigDecimal bigDecimal4;
        Preconditions.checkNotNull(zzfp);
        if (zzfp.zzg()) {
            boolean z = true;
            if (zzfp.zzm() != 1) {
                if (zzfp.zzm() == 5) {
                    if (!zzfp.zzk() || !zzfp.zzj()) {
                        return null;
                    }
                } else if (!zzfp.zzh()) {
                    return null;
                }
                int zzm = zzfp.zzm();
                if (zzfp.zzm() == 5) {
                    if (zzqa.zzA(zzfp.zze()) && zzqa.zzA(zzfp.zzd())) {
                        try {
                            BigDecimal bigDecimal5 = new BigDecimal(zzfp.zze());
                            bigDecimal3 = new BigDecimal(zzfp.zzd());
                            bigDecimal2 = bigDecimal5;
                            bigDecimal4 = null;
                        } catch (NumberFormatException unused) {
                        }
                    }
                    return null;
                } else if (!zzqa.zzA(zzfp.zzc())) {
                    return null;
                } else {
                    try {
                        bigDecimal4 = new BigDecimal(zzfp.zzc());
                        bigDecimal2 = null;
                        bigDecimal3 = null;
                    } catch (NumberFormatException unused2) {
                    }
                }
                if (zzm == 5) {
                    if (bigDecimal2 == null) {
                        return null;
                    }
                } else if (bigDecimal4 == null) {
                    return null;
                }
                int i = zzm - 1;
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4 || bigDecimal2 == null) {
                                return null;
                            }
                            if (bigDecimal.compareTo(bigDecimal2) < 0 || bigDecimal.compareTo(bigDecimal3) > 0) {
                                z = false;
                            }
                            return Boolean.valueOf(z);
                        } else if (bigDecimal4 == null) {
                            return null;
                        } else {
                            if (d != 0.0d) {
                                if (bigDecimal.compareTo(bigDecimal4.subtract(new BigDecimal(d).multiply(new BigDecimal(2)))) <= 0 || bigDecimal.compareTo(bigDecimal4.add(new BigDecimal(d).multiply(new BigDecimal(2)))) >= 0) {
                                    z = false;
                                }
                                return Boolean.valueOf(z);
                            }
                            if (bigDecimal.compareTo(bigDecimal4) != 0) {
                                z = false;
                            }
                            return Boolean.valueOf(z);
                        }
                    } else if (bigDecimal4 == null) {
                        return null;
                    } else {
                        if (bigDecimal.compareTo(bigDecimal4) <= 0) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    }
                } else if (bigDecimal4 == null) {
                    return null;
                } else {
                    if (bigDecimal.compareTo(bigDecimal4) >= 0) {
                        z = false;
                    }
                    return Boolean.valueOf(z);
                }
            }
        }
        return null;
    }

    static Boolean zzf(String str, zzfv zzfv, zzhe zzhe) {
        String zzd2;
        List list;
        Preconditions.checkNotNull(zzfv);
        if (str == null || !zzfv.zzi() || zzfv.zzj() == 1 || (zzfv.zzj() != 7 ? !zzfv.zzh() : zzfv.zza() == 0)) {
            return null;
        }
        int zzj = zzfv.zzj();
        boolean zzf2 = zzfv.zzf();
        if (zzf2 || zzj == 2 || zzj == 7) {
            zzd2 = zzfv.zzd();
        } else {
            zzd2 = zzfv.zzd().toUpperCase(Locale.ENGLISH);
        }
        String str2 = zzd2;
        if (zzfv.zza() == 0) {
            list = null;
        } else {
            List<String> zze2 = zzfv.zze();
            if (!zzf2) {
                ArrayList arrayList = new ArrayList(zze2.size());
                for (String upperCase : zze2) {
                    arrayList.add(upperCase.toUpperCase(Locale.ENGLISH));
                }
                zze2 = Collections.unmodifiableList(arrayList);
            }
            list = zze2;
        }
        return zzd(str, zzj, zzf2, str2, list, zzj == 2 ? str2 : null, zzhe);
    }

    static Boolean zzg(double d, zzfp zzfp) {
        try {
            return zze(new BigDecimal(d), zzfp, Math.ulp(d));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    static Boolean zzh(long j, zzfp zzfp) {
        try {
            return zze(new BigDecimal(j), zzfp, 0.0d);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    static Boolean zzi(String str, zzfp zzfp) {
        if (!zzqa.zzA(str)) {
            return null;
        }
        try {
            return zze(new BigDecimal(str), zzfp, 0.0d);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    static Boolean zzj(Boolean bool, boolean z) {
        if (bool == null) {
            return null;
        }
        return Boolean.valueOf(bool.booleanValue() != z);
    }

    /* access modifiers changed from: package-private */
    public abstract int zza();

    /* access modifiers changed from: package-private */
    public abstract boolean zzb();

    /* access modifiers changed from: package-private */
    public abstract boolean zzc();
}
