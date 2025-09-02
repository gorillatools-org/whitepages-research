package com.google.android.gms.internal.measurement;

import android.content.SharedPreferences;

public final /* synthetic */ class zzkj implements SharedPreferences.OnSharedPreferenceChangeListener {
    public final /* synthetic */ zzkk zza;

    public /* synthetic */ zzkj(zzkk zzkk) {
        this.zza = zzkk;
    }

    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        zzkk.zzc(this.zza, sharedPreferences, str);
    }
}
