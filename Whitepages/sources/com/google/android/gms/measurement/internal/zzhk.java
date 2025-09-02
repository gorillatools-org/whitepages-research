package com.google.android.gms.measurement.internal;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzhv;
import com.salesforce.marketingcloud.storage.db.k;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

public final class zzhk extends zzpg {
    public zzhk(zzpv zzpv) {
        super(zzpv);
    }

    public final void zza(zzh zzh, Map map, zzhg zzhg) {
        zzg();
        zzav();
        Preconditions.checkNotNull(zzh);
        Preconditions.checkNotNull(zzhg);
        zzpi zzy = this.zzg.zzy();
        Uri.Builder builder = new Uri.Builder();
        String zzH = zzh.zzH();
        if (TextUtils.isEmpty(zzH)) {
            zzH = zzh.zzA();
        }
        Uri.Builder appendQueryParameter = builder.scheme((String) zzgi.zze.zza((Object) null)).encodedAuthority((String) zzgi.zzf.zza((Object) null)).path("config/app/".concat(String.valueOf(zzH))).appendQueryParameter(k.a.b, "android");
        zzy.zzu.zzf().zzj();
        appendQueryParameter.appendQueryParameter("gmp_version", String.valueOf(119002)).appendQueryParameter("runtime_version", "0");
        String uri = builder.build().toString();
        try {
            this.zzu.zzaX().zzp(new zzhi(this, zzh.zzC(), new URI(uri).toURL(), (byte[]) null, map, zzhg));
        } catch (IllegalArgumentException | MalformedURLException | URISyntaxException unused) {
            this.zzu.zzaW().zze().zzc("Failed to parse config URL. Not fetching. appId", zzhe.zzn(zzh.zzC()), uri);
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zzb() {
        return false;
    }

    public final void zzc(String str, zzph zzph, zzhv zzhv, zzhg zzhg) {
        zzg();
        zzav();
        try {
            URL url = new URI(zzph.zzc()).toURL();
            this.zzg.zzA();
            String str2 = str;
            this.zzu.zzaX().zzp(new zzhi(this, str2, url, zzhv.zzcd(), zzph.zzd(), zzhg));
        } catch (IllegalArgumentException | MalformedURLException | URISyntaxException unused) {
            this.zzu.zzaW().zze().zzc("Failed to parse URL. Not uploading MeasurementBatch. appId", zzhe.zzn(str), zzph.zzc());
        }
    }

    public final boolean zzd() {
        zzav();
        ConnectivityManager connectivityManager = (ConnectivityManager) this.zzu.zzaT().getSystemService("connectivity");
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            try {
                networkInfo = connectivityManager.getActiveNetworkInfo();
            } catch (SecurityException unused) {
            }
        }
        return networkInfo != null && networkInfo.isConnected();
    }
}
