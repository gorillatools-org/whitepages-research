package com.google.android.gms.common.api.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import androidx.collection.ArrayMap;
import com.google.android.gms.internal.common.zzh;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Map;

final class zzc {
    private final Map zza = Collections.synchronizedMap(new ArrayMap());
    /* access modifiers changed from: private */
    public int zzb = 0;
    /* access modifiers changed from: private */
    public Bundle zzc;

    zzc() {
    }

    /* access modifiers changed from: package-private */
    public final LifecycleCallback zzc(String str, Class cls) {
        return (LifecycleCallback) cls.cast(this.zza.get(str));
    }

    /* access modifiers changed from: package-private */
    public final void zzd(String str, LifecycleCallback lifecycleCallback) {
        if (!this.zza.containsKey(str)) {
            this.zza.put(str, lifecycleCallback);
            if (this.zzb > 0) {
                new zzh(Looper.getMainLooper()).post(new zzb(this, lifecycleCallback, str));
                return;
            }
            return;
        }
        throw new IllegalArgumentException("LifecycleCallback with tag " + str + " already added to this fragment.");
    }

    /* access modifiers changed from: package-private */
    public final void zze(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        for (LifecycleCallback dump : this.zza.values()) {
            dump.dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzf(int i, int i2, Intent intent) {
        for (LifecycleCallback onActivityResult : this.zza.values()) {
            onActivityResult.onActivityResult(i, i2, intent);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzg(Bundle bundle) {
        this.zzb = 1;
        this.zzc = bundle;
        for (Map.Entry entry : this.zza.entrySet()) {
            ((LifecycleCallback) entry.getValue()).onCreate(bundle != null ? bundle.getBundle((String) entry.getKey()) : null);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzh() {
        this.zzb = 5;
        for (LifecycleCallback onDestroy : this.zza.values()) {
            onDestroy.onDestroy();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzi() {
        this.zzb = 3;
        for (LifecycleCallback onResume : this.zza.values()) {
            onResume.onResume();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzj(Bundle bundle) {
        if (bundle != null) {
            for (Map.Entry entry : this.zza.entrySet()) {
                Bundle bundle2 = new Bundle();
                ((LifecycleCallback) entry.getValue()).onSaveInstanceState(bundle2);
                bundle.putBundle((String) entry.getKey(), bundle2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzk() {
        this.zzb = 2;
        for (LifecycleCallback onStart : this.zza.values()) {
            onStart.onStart();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzl() {
        this.zzb = 4;
        for (LifecycleCallback onStop : this.zza.values()) {
            onStop.onStop();
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zzm() {
        return this.zzb > 0;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzn() {
        return this.zzb >= 2;
    }
}
