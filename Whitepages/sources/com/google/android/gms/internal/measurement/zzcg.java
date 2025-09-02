package com.google.android.gms.internal.measurement;

import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class zzcg implements SharedPreferences {
    /* access modifiers changed from: private */
    public final Map zza = new HashMap();
    /* access modifiers changed from: private */
    public final Set zzb = new HashSet();

    private final Object zzc(String str, Object obj) {
        Object obj2 = this.zza.get(str);
        return obj2 != null ? obj2 : obj;
    }

    public final boolean contains(String str) {
        return this.zza.containsKey(str);
    }

    public final SharedPreferences.Editor edit() {
        return new zzce(this, (zzcf) null);
    }

    public final Map getAll() {
        return this.zza;
    }

    public final boolean getBoolean(String str, boolean z) {
        return ((Boolean) zzc(str, Boolean.valueOf(z))).booleanValue();
    }

    public final float getFloat(String str, float f) {
        return ((Float) zzc(str, Float.valueOf(f))).floatValue();
    }

    public final int getInt(String str, int i) {
        return ((Integer) zzc(str, Integer.valueOf(i))).intValue();
    }

    public final long getLong(String str, long j) {
        return ((Long) zzc(str, Long.valueOf(j))).longValue();
    }

    public final String getString(String str, String str2) {
        return (String) zzc(str, str2);
    }

    public final Set getStringSet(String str, Set set) {
        return (Set) zzc(str, set);
    }

    public final void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.zzb.add(onSharedPreferenceChangeListener);
    }

    public final void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.zzb.remove(onSharedPreferenceChangeListener);
    }
}
