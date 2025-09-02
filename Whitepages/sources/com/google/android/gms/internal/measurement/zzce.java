package com.google.android.gms.internal.measurement;

import android.content.SharedPreferences;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

final class zzce implements SharedPreferences.Editor {
    boolean zza = false;
    final Set zzb = new HashSet();
    final Map zzc = new HashMap();
    final /* synthetic */ zzcg zzd;

    /* synthetic */ zzce(zzcg zzcg, zzcf zzcf) {
        this.zzd = zzcg;
    }

    private final void zza(String str, Object obj) {
        if (obj != null) {
            this.zzc.put(str, obj);
        } else {
            remove(str);
        }
    }

    public final void apply() {
        commit();
    }

    public final SharedPreferences.Editor clear() {
        this.zza = true;
        return this;
    }

    public final boolean commit() {
        if (this.zza) {
            this.zzd.zza.clear();
        }
        zzcg zzcg = this.zzd;
        Set keySet = zzcg.zza.keySet();
        Set set = this.zzb;
        keySet.removeAll(set);
        Map map = this.zzc;
        for (Map.Entry entry : map.entrySet()) {
            zzcg.zza.put((String) entry.getKey(), entry.getValue());
        }
        for (SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener : zzcg.zzb) {
            UnmodifiableIterator it = Sets.union(set, map.keySet()).iterator();
            while (it.hasNext()) {
                onSharedPreferenceChangeListener.onSharedPreferenceChanged(zzcg, (String) it.next());
            }
        }
        return this.zza || !set.isEmpty() || !map.isEmpty();
    }

    public final SharedPreferences.Editor putBoolean(String str, boolean z) {
        zza(str, Boolean.valueOf(z));
        return this;
    }

    public final SharedPreferences.Editor putFloat(String str, float f) {
        zza(str, Float.valueOf(f));
        return this;
    }

    public final SharedPreferences.Editor putInt(String str, int i) {
        zza(str, Integer.valueOf(i));
        return this;
    }

    public final SharedPreferences.Editor putLong(String str, long j) {
        zza(str, Long.valueOf(j));
        return this;
    }

    public final SharedPreferences.Editor putString(String str, String str2) {
        zza(str, str2);
        return this;
    }

    public final SharedPreferences.Editor putStringSet(String str, Set set) {
        zza(str, set);
        return this;
    }

    public final SharedPreferences.Editor remove(String str) {
        this.zzb.add(str);
        return this;
    }
}
