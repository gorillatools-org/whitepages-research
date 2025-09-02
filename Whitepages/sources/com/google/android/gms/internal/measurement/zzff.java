package com.google.android.gms.internal.measurement;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.measurement.dynamite.ModuleDescriptor;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.measurement.internal.zzig;
import com.google.android.gms.measurement.internal.zzkb;
import com.google.android.gms.measurement.internal.zzmg;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class zzff {
    private static volatile zzff zzc;
    protected final Clock zza;
    protected final ExecutorService zzb;
    /* access modifiers changed from: private */
    public final String zzd;
    private final AppMeasurementSdk zze;
    private final List zzf;
    private int zzg;
    /* access modifiers changed from: private */
    public boolean zzh;
    private final String zzi;
    /* access modifiers changed from: private */
    public volatile zzcv zzj;

    protected zzff(Context context, String str, String str2, String str3, Bundle bundle) {
        if (str == null || !zzX(str2, str3)) {
            this.zzd = "FA";
        } else {
            this.zzd = str;
        }
        this.zza = DefaultClock.getInstance();
        zzcq.zza();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new zzeg(this));
        boolean z = true;
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        this.zzb = Executors.unconfigurableExecutorService(threadPoolExecutor);
        this.zze = new AppMeasurementSdk(this);
        this.zzf = new ArrayList();
        try {
            if (zzmg.zzc(context, "google_app_id", zzig.zza(context)) != null && !zzT()) {
                this.zzi = null;
                this.zzh = true;
                Log.w(this.zzd, "Disabling data collection. Found google_app_id in strings.xml but Google Analytics for Firebase is missing. Remove this value or add Google Analytics for Firebase to resume data collection.");
                return;
            }
        } catch (IllegalStateException unused) {
        }
        if (!zzX(str2, str3)) {
            this.zzi = "fa";
            if (str2 == null || str3 == null) {
                if ((str2 == null) ^ (str3 != null ? false : z)) {
                    Log.w(this.zzd, "Specified origin or custom app id is null. Both parameters will be ignored.");
                }
            } else {
                Log.v(this.zzd, "Deferring to Google Analytics for Firebase for event data collection. https://firebase.google.com/docs/analytics");
            }
        } else {
            this.zzi = str2;
        }
        zzW(new zzdu(this, str2, str3, context, bundle));
        Application application = (Application) context.getApplicationContext();
        if (application == null) {
            Log.w(this.zzd, "Unable to register lifecycle notifications. Application null.");
        } else {
            application.registerActivityLifecycleCallbacks(new zzfe(this));
        }
    }

    /* access modifiers changed from: private */
    public final void zzU(Exception exc, boolean z, boolean z2) {
        this.zzh |= z;
        if (z) {
            Log.w(this.zzd, "Data collection startup failed. No data will be collected.", exc);
            return;
        }
        if (z2) {
            zzB(5, "Error with data collection. Data lost.", exc, (Object) null, (Object) null);
        }
        Log.w(this.zzd, "Error with data collection. Data lost.", exc);
    }

    private final void zzV(String str, String str2, Bundle bundle, boolean z, boolean z2, Long l) {
        zzW(new zzet(this, l, str, str2, bundle, z, z2));
    }

    /* access modifiers changed from: private */
    public final void zzW(zzeu zzeu) {
        this.zzb.execute(zzeu);
    }

    /* access modifiers changed from: private */
    public final boolean zzX(String str, String str2) {
        return (str2 == null || str == null || zzT()) ? false : true;
    }

    public static zzff zzg(Context context, String str, String str2, String str3, Bundle bundle) {
        Preconditions.checkNotNull(context);
        if (zzc == null) {
            synchronized (zzff.class) {
                try {
                    if (zzc == null) {
                        zzc = new zzff(context, str, str2, str3, bundle);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return zzc;
    }

    public final void zzA(String str, String str2, Bundle bundle, long j) {
        zzV(str, str2, bundle, true, false, Long.valueOf(j));
    }

    public final void zzB(int i, String str, Object obj, Object obj2, Object obj3) {
        zzW(new zzeh(this, false, 5, str, obj, (Object) null, (Object) null));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0039, code lost:
        if (r3.zzj == null) goto L_0x0048;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r3.zzj.registerOnMeasurementEventListener(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0040, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0041, code lost:
        android.util.Log.w(r3.zzd, "Failed to register event listener on calling thread. Trying again on the dynamite thread.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0048, code lost:
        zzW(new com.google.android.gms.internal.measurement.zzer(r3, r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0050, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzC(com.google.android.gms.measurement.internal.zzkc r4) {
        /*
            r3 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)
            java.util.List r0 = r3.zzf
            monitor-enter(r0)
            r1 = 0
        L_0x0007:
            int r2 = r0.size()     // Catch:{ all -> 0x0024 }
            if (r1 >= r2) goto L_0x0029
            java.lang.Object r2 = r0.get(r1)     // Catch:{ all -> 0x0024 }
            android.util.Pair r2 = (android.util.Pair) r2     // Catch:{ all -> 0x0024 }
            java.lang.Object r2 = r2.first     // Catch:{ all -> 0x0024 }
            boolean r2 = r4.equals(r2)     // Catch:{ all -> 0x0024 }
            if (r2 == 0) goto L_0x0026
            java.lang.String r4 = r3.zzd     // Catch:{ all -> 0x0024 }
            java.lang.String r1 = "OnEventListener already registered."
            android.util.Log.w(r4, r1)     // Catch:{ all -> 0x0024 }
            monitor-exit(r0)     // Catch:{ all -> 0x0024 }
            return
        L_0x0024:
            r4 = move-exception
            goto L_0x0051
        L_0x0026:
            int r1 = r1 + 1
            goto L_0x0007
        L_0x0029:
            com.google.android.gms.internal.measurement.zzew r1 = new com.google.android.gms.internal.measurement.zzew     // Catch:{ all -> 0x0024 }
            r1.<init>(r4)     // Catch:{ all -> 0x0024 }
            android.util.Pair r2 = new android.util.Pair     // Catch:{ all -> 0x0024 }
            r2.<init>(r4, r1)     // Catch:{ all -> 0x0024 }
            r0.add(r2)     // Catch:{ all -> 0x0024 }
            monitor-exit(r0)     // Catch:{ all -> 0x0024 }
            com.google.android.gms.internal.measurement.zzcv r4 = r3.zzj
            if (r4 == 0) goto L_0x0048
            com.google.android.gms.internal.measurement.zzcv r4 = r3.zzj     // Catch:{ BadParcelableException | NetworkOnMainThreadException | RemoteException | IllegalArgumentException | IllegalStateException | NullPointerException | SecurityException | UnsupportedOperationException -> 0x0041 }
            r4.registerOnMeasurementEventListener(r1)     // Catch:{ BadParcelableException | NetworkOnMainThreadException | RemoteException | IllegalArgumentException | IllegalStateException | NullPointerException | SecurityException | UnsupportedOperationException -> 0x0041 }
            return
        L_0x0041:
            java.lang.String r4 = r3.zzd
            java.lang.String r0 = "Failed to register event listener on calling thread. Trying again on the dynamite thread."
            android.util.Log.w(r4, r0)
        L_0x0048:
            com.google.android.gms.internal.measurement.zzer r4 = new com.google.android.gms.internal.measurement.zzer
            r4.<init>(r3, r1)
            r3.zzW(r4)
            return
        L_0x0051:
            monitor-exit(r0)     // Catch:{ all -> 0x0024 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzff.zzC(com.google.android.gms.measurement.internal.zzkc):void");
    }

    public final void zzD() {
        zzW(new zzdt(this));
    }

    public final void zzE(Runnable runnable) {
        zzW(new zzdz(this, runnable));
    }

    public final void zzF(Bundle bundle) {
        zzW(new zzdm(this, bundle));
    }

    public final void zzG(Bundle bundle) {
        zzW(new zzds(this, bundle));
    }

    public final void zzH(zzdj zzdj, String str, String str2) {
        zzW(new zzdq(this, zzdj, str, str2));
    }

    public final void zzI(boolean z) {
        zzW(new zzen(this, z));
    }

    public final void zzJ(Bundle bundle) {
        zzW(new zzeo(this, bundle));
    }

    public final void zzK(zzkb zzkb) {
        zzev zzev = new zzev(zzkb);
        if (this.zzj != null) {
            try {
                this.zzj.setEventInterceptor(zzev);
                return;
            } catch (BadParcelableException | NetworkOnMainThreadException | RemoteException | IllegalArgumentException | IllegalStateException | NullPointerException | SecurityException | UnsupportedOperationException unused) {
                Log.w(this.zzd, "Failed to set event interceptor on calling thread. Trying again on the dynamite thread.");
            }
        }
        zzW(new zzep(this, zzev));
    }

    public final void zzL(Boolean bool) {
        zzW(new zzdr(this, bool));
    }

    public final void zzM(long j) {
        zzW(new zzdv(this, j));
    }

    public final void zzN(Intent intent) {
        zzW(new zzeq(this, intent));
    }

    public final void zzO(String str) {
        zzW(new zzdp(this, str));
    }

    public final void zzP(String str, String str2, Object obj, boolean z) {
        zzW(new zzdl(this, str, str2, obj, z));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003d, code lost:
        if (r3.zzj == null) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r3.zzj.unregisterOnMeasurementEventListener(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0044, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0045, code lost:
        android.util.Log.w(r3.zzd, "Failed to unregister event listener on calling thread. Trying again on the dynamite thread.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004c, code lost:
        zzW(new com.google.android.gms.internal.measurement.zzes(r3, r4));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0054, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzQ(com.google.android.gms.measurement.internal.zzkc r4) {
        /*
            r3 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)
            java.util.List r0 = r3.zzf
            monitor-enter(r0)
            r1 = 0
        L_0x0007:
            int r2 = r0.size()     // Catch:{ all -> 0x0022 }
            if (r1 >= r2) goto L_0x0027
            java.lang.Object r2 = r0.get(r1)     // Catch:{ all -> 0x0022 }
            android.util.Pair r2 = (android.util.Pair) r2     // Catch:{ all -> 0x0022 }
            java.lang.Object r2 = r2.first     // Catch:{ all -> 0x0022 }
            boolean r2 = r4.equals(r2)     // Catch:{ all -> 0x0022 }
            if (r2 == 0) goto L_0x0024
            java.lang.Object r4 = r0.get(r1)     // Catch:{ all -> 0x0022 }
            android.util.Pair r4 = (android.util.Pair) r4     // Catch:{ all -> 0x0022 }
            goto L_0x0028
        L_0x0022:
            r4 = move-exception
            goto L_0x0055
        L_0x0024:
            int r1 = r1 + 1
            goto L_0x0007
        L_0x0027:
            r4 = 0
        L_0x0028:
            if (r4 != 0) goto L_0x0033
            java.lang.String r4 = r3.zzd     // Catch:{ all -> 0x0022 }
            java.lang.String r1 = "OnEventListener had not been registered."
            android.util.Log.w(r4, r1)     // Catch:{ all -> 0x0022 }
            monitor-exit(r0)     // Catch:{ all -> 0x0022 }
            return
        L_0x0033:
            r0.remove(r4)     // Catch:{ all -> 0x0022 }
            java.lang.Object r4 = r4.second     // Catch:{ all -> 0x0022 }
            com.google.android.gms.internal.measurement.zzew r4 = (com.google.android.gms.internal.measurement.zzew) r4     // Catch:{ all -> 0x0022 }
            monitor-exit(r0)     // Catch:{ all -> 0x0022 }
            com.google.android.gms.internal.measurement.zzcv r0 = r3.zzj
            if (r0 == 0) goto L_0x004c
            com.google.android.gms.internal.measurement.zzcv r0 = r3.zzj     // Catch:{ BadParcelableException | NetworkOnMainThreadException | RemoteException | IllegalArgumentException | IllegalStateException | NullPointerException | SecurityException | UnsupportedOperationException -> 0x0045 }
            r0.unregisterOnMeasurementEventListener(r4)     // Catch:{ BadParcelableException | NetworkOnMainThreadException | RemoteException | IllegalArgumentException | IllegalStateException | NullPointerException | SecurityException | UnsupportedOperationException -> 0x0045 }
            return
        L_0x0045:
            java.lang.String r0 = r3.zzd
            java.lang.String r1 = "Failed to unregister event listener on calling thread. Trying again on the dynamite thread."
            android.util.Log.w(r0, r1)
        L_0x004c:
            com.google.android.gms.internal.measurement.zzes r0 = new com.google.android.gms.internal.measurement.zzes
            r0.<init>(r3, r4)
            r3.zzW(r0)
            return
        L_0x0055:
            monitor-exit(r0)     // Catch:{ all -> 0x0022 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzff.zzQ(com.google.android.gms.measurement.internal.zzkc):void");
    }

    /* access modifiers changed from: protected */
    public final boolean zzT() {
        try {
            Class.forName("com.google.firebase.analytics.FirebaseAnalytics", false, zzff.class.getClassLoader());
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public final int zza(String str) {
        zzcs zzcs = new zzcs();
        zzW(new zzej(this, str, zzcs));
        Integer num = (Integer) zzcs.zzf(zzcs.zzb(10000), Integer.class);
        if (num == null) {
            return 25;
        }
        return num.intValue();
    }

    public final long zzb() {
        zzcs zzcs = new zzcs();
        zzW(new zzec(this, zzcs));
        Long zzc2 = zzcs.zzc(500);
        if (zzc2 != null) {
            return zzc2.longValue();
        }
        long nextLong = new Random(System.nanoTime() ^ this.zza.currentTimeMillis()).nextLong();
        int i = this.zzg + 1;
        this.zzg = i;
        return nextLong + ((long) i);
    }

    public final Bundle zzc(Bundle bundle, boolean z) {
        zzcs zzcs = new zzcs();
        zzW(new zzei(this, bundle, zzcs));
        if (z) {
            return zzcs.zzb(5000);
        }
        return null;
    }

    public final AppMeasurementSdk zzd() {
        return this.zze;
    }

    /* access modifiers changed from: protected */
    public final zzcv zzf(Context context, boolean z) {
        try {
            return zzcu.asInterface(DynamiteModule.load(context, DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION, ModuleDescriptor.MODULE_ID).instantiate("com.google.android.gms.measurement.internal.AppMeasurementDynamiteService"));
        } catch (DynamiteModule.LoadingException e) {
            zzU(e, true, false);
            return null;
        }
    }

    public final Long zzh() {
        zzcs zzcs = new zzcs();
        zzW(new zzel(this, zzcs));
        return zzcs.zzc(120000);
    }

    public final Object zzi(int i) {
        zzcs zzcs = new zzcs();
        zzW(new zzem(this, zzcs, i));
        return zzcs.zzf(zzcs.zzb(15000), Object.class);
    }

    public final String zzk() {
        return this.zzi;
    }

    public final String zzl() {
        zzcs zzcs = new zzcs();
        zzW(new zzek(this, zzcs));
        return zzcs.zzd(120000);
    }

    public final String zzm() {
        zzcs zzcs = new zzcs();
        zzW(new zzeb(this, zzcs));
        return zzcs.zzd(50);
    }

    public final String zzn() {
        zzcs zzcs = new zzcs();
        zzW(new zzee(this, zzcs));
        return zzcs.zzd(500);
    }

    public final String zzo() {
        zzcs zzcs = new zzcs();
        zzW(new zzed(this, zzcs));
        return zzcs.zzd(500);
    }

    public final String zzp() {
        zzcs zzcs = new zzcs();
        zzW(new zzea(this, zzcs));
        return zzcs.zzd(500);
    }

    public final List zzq(String str, String str2) {
        zzcs zzcs = new zzcs();
        zzW(new zzdo(this, str, str2, zzcs));
        List list = (List) zzcs.zzf(zzcs.zzb(5000), List.class);
        return list == null ? Collections.emptyList() : list;
    }

    public final Map zzr(String str, String str2, boolean z) {
        zzcs zzcs = new zzcs();
        zzW(new zzef(this, str, str2, z, zzcs));
        Bundle zzb2 = zzcs.zzb(5000);
        if (zzb2 == null || zzb2.size() == 0) {
            return Collections.emptyMap();
        }
        HashMap hashMap = new HashMap(zzb2.size());
        for (String next : zzb2.keySet()) {
            Object obj = zzb2.get(next);
            if ((obj instanceof Double) || (obj instanceof Long) || (obj instanceof String)) {
                hashMap.put(next, obj);
            }
        }
        return hashMap;
    }

    public final void zzv(String str) {
        zzW(new zzdw(this, str));
    }

    public final void zzw(String str, String str2, Bundle bundle) {
        zzW(new zzdn(this, str, str2, bundle));
    }

    public final void zzx(String str) {
        zzW(new zzdx(this, str));
    }

    public final void zzy(String str, Bundle bundle) {
        zzV((String) null, str, bundle, false, true, (Long) null);
    }

    public final void zzz(String str, String str2, Bundle bundle) {
        zzV(str, str2, bundle, true, true, (Long) null);
    }
}
