package com.google.android.gms.measurement.internal;

import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public final class zzbb extends zzjr {
    private long zza;
    private String zzb;

    zzbb(zzio zzio) {
        super(zzio);
    }

    public final long zza() {
        zzv();
        return this.zza;
    }

    public final String zzb() {
        zzv();
        return this.zzb;
    }

    /* access modifiers changed from: protected */
    public final boolean zzc() {
        Calendar instance = Calendar.getInstance();
        this.zza = TimeUnit.MINUTES.convert((long) (instance.get(15) + instance.get(16)), TimeUnit.MILLISECONDS);
        Locale locale = Locale.getDefault();
        String language = locale.getLanguage();
        Locale locale2 = Locale.ENGLISH;
        String lowerCase = language.toLowerCase(locale2);
        String lowerCase2 = locale.getCountry().toLowerCase(locale2);
        this.zzb = lowerCase + "-" + lowerCase2;
        return false;
    }
}
