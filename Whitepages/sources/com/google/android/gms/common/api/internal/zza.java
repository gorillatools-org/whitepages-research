package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public final class zza extends Fragment implements LifecycleFragment {
    private static final WeakHashMap zza = new WeakHashMap();
    private final zzc zzb = new zzc();

    public static zza zza(Activity activity) {
        zza zza2;
        WeakHashMap weakHashMap = zza;
        WeakReference weakReference = (WeakReference) weakHashMap.get(activity);
        if (weakReference != null && (zza2 = (zza) weakReference.get()) != null) {
            return zza2;
        }
        try {
            zza zza3 = (zza) activity.getFragmentManager().findFragmentByTag("LifecycleFragmentImpl");
            if (zza3 == null || zza3.isRemoving()) {
                zza3 = new zza();
                activity.getFragmentManager().beginTransaction().add(zza3, "LifecycleFragmentImpl").commitAllowingStateLoss();
            }
            weakHashMap.put(activity, new WeakReference(zza3));
            return zza3;
        } catch (ClassCastException e) {
            throw new IllegalStateException("Fragment with tag LifecycleFragmentImpl is not a LifecycleFragmentImpl", e);
        }
    }

    public final void addCallback(String str, LifecycleCallback lifecycleCallback) {
        this.zzb.zzd(str, lifecycleCallback);
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        this.zzb.zze(str, fileDescriptor, printWriter, strArr);
    }

    public final <T extends LifecycleCallback> T getCallbackOrNull(String str, Class<T> cls) {
        return this.zzb.zzc(str, cls);
    }

    public final Activity getLifecycleActivity() {
        return getActivity();
    }

    public final boolean isCreated() {
        return this.zzb.zzm();
    }

    public final boolean isStarted() {
        return this.zzb.zzn();
    }

    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.zzb.zzf(i, i2, intent);
    }

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzb.zzg(bundle);
    }

    public final void onDestroy() {
        super.onDestroy();
        this.zzb.zzh();
    }

    public final void onResume() {
        super.onResume();
        this.zzb.zzi();
    }

    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.zzb.zzj(bundle);
    }

    public final void onStart() {
        super.onStart();
        this.zzb.zzk();
    }

    public final void onStop() {
        super.onStop();
        this.zzb.zzl();
    }
}
