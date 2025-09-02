package com.google.android.gms.measurement.internal;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.google.firebase.messaging.Constants;

public final class zzx {
    private final zzio zza;

    public zzx(zzio zzio) {
        this.zza = zzio;
    }

    /* access modifiers changed from: package-private */
    public final void zza(String str, Bundle bundle) {
        String str2;
        zzio zzio = this.zza;
        zzio.zzaX().zzg();
        if (!zzio.zzJ()) {
            if (bundle.isEmpty()) {
                str2 = null;
            } else {
                if (true == str.isEmpty()) {
                    str = "auto";
                }
                Uri.Builder builder = new Uri.Builder();
                builder.path(str);
                for (String next : bundle.keySet()) {
                    builder.appendQueryParameter(next, bundle.getString(next));
                }
                str2 = builder.build().toString();
            }
            if (!TextUtils.isEmpty(str2)) {
                zzio.zzm().zzr.zzb(str2);
                zzio.zzm().zzs.zzb(zzio.zzaU().currentTimeMillis());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb() {
        String str;
        zzio zzio = this.zza;
        zzio.zzaX().zzg();
        if (zzd()) {
            if (zze()) {
                zzio.zzm().zzr.zzb((String) null);
                Bundle bundle = new Bundle();
                bundle.putString("source", "(not set)");
                bundle.putString("medium", "(not set)");
                bundle.putString("_cis", "intent");
                bundle.putLong("_cc", 1);
                zzio.zzq().zzR("auto", "_cmpx", bundle);
            } else {
                String zza2 = zzio.zzm().zzr.zza();
                if (TextUtils.isEmpty(zza2)) {
                    zzio.zzaW().zzh().zza("Cache still valid but referrer not found");
                } else {
                    long zza3 = zzio.zzm().zzs.zza() / 3600000;
                    Uri parse = Uri.parse(zza2);
                    Bundle bundle2 = new Bundle();
                    Pair pair = new Pair(parse.getPath(), bundle2);
                    for (String next : parse.getQueryParameterNames()) {
                        bundle2.putString(next, parse.getQueryParameter(next));
                    }
                    ((Bundle) pair.second).putLong("_cc", (zza3 - 1) * 3600000);
                    Object obj = pair.first;
                    if (obj == null) {
                        str = "app";
                    } else {
                        str = (String) obj;
                    }
                    zzio.zzq().zzR(str, Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, (Bundle) pair.second);
                }
                zzio.zzm().zzr.zzb((String) null);
            }
            zzio.zzm().zzs.zzb(0);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzc() {
        if (zzd() && zze()) {
            this.zza.zzm().zzr.zzb((String) null);
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zzd() {
        return this.zza.zzm().zzs.zza() > 0;
    }

    /* access modifiers changed from: package-private */
    public final boolean zze() {
        if (!zzd()) {
            return false;
        }
        zzio zzio = this.zza;
        if (zzio.zzaU().currentTimeMillis() - zzio.zzm().zzs.zza() > zzio.zzf().zzk((String) null, zzgi.zzai)) {
            return true;
        }
        return false;
    }
}
