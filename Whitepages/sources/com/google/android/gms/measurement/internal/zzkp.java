package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;

public final /* synthetic */ class zzkp implements SharedPreferences.OnSharedPreferenceChangeListener {
    public final /* synthetic */ zzlw zza;

    public /* synthetic */ zzkp(zzlw zzlw) {
        this.zza = zzlw;
    }

    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        zzlw.zzA(this.zza, sharedPreferences, str);
    }
}
