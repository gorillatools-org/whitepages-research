package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Pair;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.internal.measurement.zzcy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicReference;

public final class zzny extends zzg {
    /* access modifiers changed from: private */
    public final zznx zza;
    /* access modifiers changed from: private */
    public zzgl zzb;
    private volatile Boolean zzc;
    private final zzaz zzd;
    /* access modifiers changed from: private */
    public ScheduledExecutorService zze;
    private final zzou zzf;
    private final List zzg = new ArrayList();
    private final zzaz zzh;

    protected zzny(zzio zzio) {
        super(zzio);
        this.zzf = new zzou(zzio.zzaU());
        this.zza = new zznx(this);
        this.zzd = new zzne(this, zzio);
        this.zzh = new zzni(this, zzio);
    }

    private final zzr zzae(boolean z) {
        Pair zza2;
        zzio zzio = this.zzu;
        zzio.zzaV();
        zzgs zzh2 = this.zzu.zzh();
        String str = null;
        if (z) {
            zzio zzio2 = zzio.zzaW().zzu;
            if (!(zzio2.zzm().zzb == null || (zza2 = zzio2.zzm().zzb.zza()) == null || zza2 == zzht.zza)) {
                str = String.valueOf(zza2.second) + ":" + ((String) zza2.first);
            }
        }
        return zzh2.zzk(str);
    }

    /* access modifiers changed from: private */
    public final void zzaf() {
        zzg();
        zzhc zzj = this.zzu.zzaW().zzj();
        List<Runnable> list = this.zzg;
        zzj.zzb("Processing queued up service tasks", Integer.valueOf(list.size()));
        for (Runnable run : list) {
            try {
                run.run();
            } catch (RuntimeException e) {
                this.zzu.zzaW().zze().zzb("Task exception while flushing queue", e);
            }
        }
        this.zzg.clear();
        this.zzh.zzb();
    }

    /* access modifiers changed from: private */
    public final void zzag() {
        zzg();
        this.zzf.zzb();
        this.zzu.zzf();
        this.zzd.zzd(((Long) zzgi.zzX.zza((Object) null)).longValue());
    }

    private final void zzah(Runnable runnable) throws IllegalStateException {
        zzg();
        if (zzaa()) {
            runnable.run();
            return;
        }
        List list = this.zzg;
        zzio zzio = this.zzu;
        zzio.zzf();
        if (((long) list.size()) >= 1000) {
            zzio.zzaW().zze().zza("Discarding data. Max runnable queue size reached");
            return;
        }
        list.add(runnable);
        this.zzh.zzd(60000);
        zzB();
    }

    private final boolean zzai() {
        this.zzu.zzaV();
        return true;
    }

    public static /* synthetic */ void zzp(zzny zzny) {
        zzgl zzgl = zzny.zzb;
        if (zzgl == null) {
            zzny.zzu.zzaW().zze().zza("Failed to send storage consent settings to service");
            return;
        }
        try {
            zzr zzae = zzny.zzae(false);
            Preconditions.checkNotNull(zzae);
            zzgl.zzA(zzae);
            zzny.zzag();
        } catch (RemoteException e) {
            zzny.zzu.zzaW().zze().zzb("Failed to send storage consent settings to the service", e);
        }
    }

    public static /* synthetic */ void zzq(zzny zzny, AtomicReference atomicReference, zzr zzr, zzpc zzpc) {
        synchronized (atomicReference) {
            try {
                zzgl zzgl = zzny.zzb;
                if (zzgl == null) {
                    zzny.zzu.zzaW().zze().zza("[sgtm] Failed to get upload batches; not connected to service");
                    return;
                }
                Preconditions.checkNotNull(zzr);
                zzgl.zzo(zzr, zzpc, new zzmx(zzny, atomicReference));
                zzny.zzag();
            } catch (RemoteException e) {
                zzny.zzu.zzaW().zze().zzb("[sgtm] Failed to get upload batches; remote exception", e);
                atomicReference.notifyAll();
            }
        }
    }

    public static /* synthetic */ void zzr(zzny zzny, AtomicReference atomicReference, zzr zzr, Bundle bundle) {
        synchronized (atomicReference) {
            try {
                zzgl zzgl = zzny.zzb;
                if (zzgl == null) {
                    zzny.zzu.zzaW().zze().zza("Failed to request trigger URIs; not connected to service");
                    return;
                }
                Preconditions.checkNotNull(zzr);
                zzgl.zzr(zzr, bundle, new zzmw(zzny, atomicReference));
                zzny.zzag();
            } catch (RemoteException e) {
                zzny.zzu.zzaW().zze().zzb("Failed to request trigger URIs; remote exception", e);
                atomicReference.notifyAll();
            }
        }
    }

    public static /* synthetic */ void zzs(zzny zzny, zzr zzr, zzag zzag) {
        zzgl zzgl = zzny.zzb;
        if (zzgl == null) {
            zzny.zzu.zzaW().zze().zza("[sgtm] Discarding data. Failed to update batch upload status.");
            return;
        }
        try {
            zzgl.zzC(zzr, zzag);
            zzny.zzag();
        } catch (RemoteException e) {
            zzny.zzu.zzaW().zze().zzc("[sgtm] Failed to update batch upload status, rowId, exception", Long.valueOf(zzag.zza), e);
        }
    }

    public static /* synthetic */ void zzt(zzny zzny) {
        zzgl zzgl = zzny.zzb;
        if (zzgl == null) {
            zzny.zzu.zzaW().zze().zza("Failed to send Dma consent settings to service");
            return;
        }
        try {
            zzr zzae = zzny.zzae(false);
            Preconditions.checkNotNull(zzae);
            zzgl.zzy(zzae);
            zzny.zzag();
        } catch (RemoteException e) {
            zzny.zzu.zzaW().zze().zzb("Failed to send Dma consent settings to the service", e);
        }
    }

    static /* bridge */ /* synthetic */ void zzx(zzny zzny, ComponentName componentName) {
        zzny.zzg();
        if (zzny.zzb != null) {
            zzny.zzb = null;
            zzny.zzu.zzaW().zzj().zzb("Disconnected from device MeasurementService", componentName);
            zzny.zzg();
            zzny.zzB();
        }
    }

    /* access modifiers changed from: protected */
    public final void zzA() {
        zzg();
        zza();
        zzr zzae = zzae(true);
        zzai();
        this.zzu.zzf().zzx((String) null, zzgi.zzbl);
        this.zzu.zzi().zzk();
        zzah(new zznc(this, zzae, true));
    }

    /* access modifiers changed from: package-private */
    public final void zzB() {
        zzg();
        zza();
        if (!zzaa()) {
            if (!zzad()) {
                zzio zzio = this.zzu;
                if (!zzio.zzf().zzC()) {
                    zzio.zzaV();
                    List<ResolveInfo> queryIntentServices = zzio.zzaT().getPackageManager().queryIntentServices(new Intent().setClassName(zzio.zzaT(), "com.google.android.gms.measurement.AppMeasurementService"), 65536);
                    if (queryIntentServices == null || queryIntentServices.isEmpty()) {
                        zzio.zzaW().zze().zza("Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest");
                        return;
                    }
                    Intent intent = new Intent("com.google.android.gms.measurement.START");
                    Context zzaT = zzio.zzaT();
                    zzio.zzaV();
                    intent.setComponent(new ComponentName(zzaT, "com.google.android.gms.measurement.AppMeasurementService"));
                    this.zza.zzb(intent);
                    return;
                }
                return;
            }
            this.zza.zzc();
        }
    }

    public final void zzC() {
        zzg();
        zza();
        zznx zznx = this.zza;
        zznx.zzd();
        try {
            ConnectionTracker.getInstance().unbindService(this.zzu.zzaT(), zznx);
        } catch (IllegalArgumentException | IllegalStateException unused) {
        }
        this.zzb = null;
    }

    public final void zzD(zzcy zzcy) {
        zzg();
        zza();
        zzah(new zznb(this, zzae(false), zzcy));
    }

    public final void zzE(AtomicReference atomicReference) {
        zzg();
        zza();
        zzah(new zzna(this, atomicReference, zzae(false)));
    }

    /* access modifiers changed from: protected */
    public final void zzF(zzcy zzcy, String str, String str2) {
        zzg();
        zza();
        zzah(new zzno(this, str, str2, zzae(false), zzcy));
    }

    /* access modifiers changed from: protected */
    public final void zzG(AtomicReference atomicReference, String str, String str2, String str3) {
        zzg();
        zza();
        zzah(new zznn(this, atomicReference, (String) null, str2, str3, zzae(false)));
    }

    /* access modifiers changed from: protected */
    public final void zzH(AtomicReference atomicReference, Bundle bundle) {
        zzg();
        zza();
        zzah(new zzms(this, atomicReference, zzae(false), bundle));
    }

    /* access modifiers changed from: protected */
    public final void zzI(AtomicReference atomicReference, zzpc zzpc) {
        zzg();
        zza();
        zzah(new zzmt(this, atomicReference, zzae(false), zzpc));
    }

    /* access modifiers changed from: protected */
    public final void zzJ(AtomicReference atomicReference, boolean z) {
        zzg();
        zza();
        zzah(new zzmv(this, atomicReference, zzae(false), z));
    }

    /* access modifiers changed from: protected */
    public final void zzK(zzcy zzcy, String str, String str2, boolean z) {
        zzg();
        zza();
        zzah(new zzmu(this, str, str2, zzae(false), z, zzcy));
    }

    /* access modifiers changed from: protected */
    public final void zzL(AtomicReference atomicReference, String str, String str2, String str3, boolean z) {
        zzg();
        zza();
        zzah(new zznp(this, atomicReference, (String) null, str2, str3, zzae(false), z));
    }

    /* access modifiers changed from: protected */
    public final void zzM(zzbh zzbh, String str) {
        Preconditions.checkNotNull(zzbh);
        zzg();
        zza();
        zzai();
        zzah(new zznl(this, true, zzae(true), this.zzu.zzi().zzp(zzbh), zzbh, str));
    }

    public final void zzN(zzcy zzcy, zzbh zzbh, String str) {
        zzg();
        zza();
        zzio zzio = this.zzu;
        if (zzio.zzw().zzp(GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE) != 0) {
            zzio.zzaW().zzk().zza("Not bundling data. Service unavailable or out of date");
            zzio.zzw().zzW(zzcy, new byte[0]);
            return;
        }
        zzah(new zznh(this, zzbh, str, zzcy));
    }

    /* access modifiers changed from: protected */
    public final void zzO() {
        zzg();
        zza();
        zzr zzae = zzae(false);
        zzai();
        this.zzu.zzi().zzj();
        zzah(new zzmz(this, zzae));
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x010c  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x01af  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzP(com.google.android.gms.measurement.internal.zzgl r60, com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable r61, com.google.android.gms.measurement.internal.zzr r62) {
        /*
            r59 = this;
            r1 = r59
            r2 = r60
            r3 = r61
            r59.zzg()
            r59.zza()
            r59.zzai()
            com.google.android.gms.measurement.internal.zzio r4 = r1.zzu
            r4.zzf()
            r6 = 100
            r0 = r62
            r8 = r6
            r7 = 0
        L_0x001a:
            r9 = 1001(0x3e9, float:1.403E-42)
            if (r7 >= r9) goto L_0x022d
            if (r8 != r6) goto L_0x022d
            com.google.android.gms.measurement.internal.zzio r8 = r1.zzu
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            com.google.android.gms.measurement.internal.zzgv r8 = r8.zzi()
            java.util.List r8 = r8.zzi(r6)
            if (r8 == 0) goto L_0x0039
            r9.addAll(r8)
            int r8 = r8.size()
            goto L_0x003a
        L_0x0039:
            r8 = 0
        L_0x003a:
            if (r3 == 0) goto L_0x004a
            if (r8 >= r6) goto L_0x004a
            java.lang.String r10 = r0.zzc
            long r11 = r0.zzj
            com.google.android.gms.measurement.internal.zzgu r13 = new com.google.android.gms.measurement.internal.zzgu
            r13.<init>(r3, r10, r11)
            r9.add(r13)
        L_0x004a:
            com.google.android.gms.measurement.internal.zzam r10 = r4.zzf()
            com.google.android.gms.measurement.internal.zzgg r11 = com.google.android.gms.measurement.internal.zzgi.zzaU
            r12 = 0
            boolean r10 = r10.zzx(r12, r11)
            int r11 = r9.size()
            r13 = 0
        L_0x005a:
            if (r13 >= r11) goto L_0x0227
            java.lang.Object r14 = r9.get(r13)
            com.google.android.gms.measurement.internal.zzgu r14 = (com.google.android.gms.measurement.internal.zzgu) r14
            com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable r15 = r14.zza
            com.google.android.gms.measurement.internal.zzam r5 = r4.zzf()
            com.google.android.gms.measurement.internal.zzgg r6 = com.google.android.gms.measurement.internal.zzgi.zzbl
            boolean r5 = r5.zzx(r12, r6)
            if (r5 == 0) goto L_0x0105
            java.lang.String r5 = r14.zzb
            boolean r16 = android.text.TextUtils.isEmpty(r5)
            if (r16 != 0) goto L_0x0105
            r58 = r13
            long r12 = r14.zzc
            r20 = r12
            java.lang.String r12 = r0.zza
            r17 = r12
            java.lang.String r12 = r0.zzb
            r18 = r12
            java.lang.String r12 = r0.zzd
            r22 = r12
            long r12 = r0.zze
            r23 = r12
            long r12 = r0.zzf
            r25 = r12
            java.lang.String r12 = r0.zzg
            r27 = r12
            boolean r12 = r0.zzh
            r28 = r12
            boolean r12 = r0.zzi
            r29 = r12
            java.lang.String r12 = r0.zzk
            r30 = r12
            long r12 = r0.zzl
            r31 = r12
            int r12 = r0.zzm
            r33 = r12
            boolean r12 = r0.zzn
            r34 = r12
            boolean r12 = r0.zzo
            r35 = r12
            java.lang.String r12 = r0.zzp
            r36 = r12
            java.lang.Boolean r12 = r0.zzq
            r37 = r12
            long r12 = r0.zzr
            r38 = r12
            java.util.List r12 = r0.zzs
            r40 = r12
            java.lang.String r12 = r0.zzt
            r41 = r12
            java.lang.String r12 = r0.zzu
            r42 = r12
            java.lang.String r12 = r0.zzv
            r43 = r12
            java.lang.String r12 = r0.zzw
            r44 = r12
            boolean r12 = r0.zzx
            r45 = r12
            long r12 = r0.zzy
            r46 = r12
            int r12 = r0.zzz
            r48 = r12
            java.lang.String r12 = r0.zzA
            r49 = r12
            int r12 = r0.zzB
            r50 = r12
            long r12 = r0.zzC
            r51 = r12
            java.lang.String r12 = r0.zzD
            r53 = r12
            java.lang.String r12 = r0.zzE
            r54 = r12
            long r12 = r0.zzF
            r55 = r12
            int r0 = r0.zzG
            r57 = r0
            com.google.android.gms.measurement.internal.zzr r0 = new com.google.android.gms.measurement.internal.zzr
            r16 = r0
            r19 = r5
            r16.<init>((java.lang.String) r17, (java.lang.String) r18, (java.lang.String) r19, (long) r20, (java.lang.String) r22, (long) r23, (long) r25, (java.lang.String) r27, (boolean) r28, (boolean) r29, (java.lang.String) r30, (long) r31, (int) r33, (boolean) r34, (boolean) r35, (java.lang.String) r36, (java.lang.Boolean) r37, (long) r38, (java.util.List) r40, (java.lang.String) r41, (java.lang.String) r42, (java.lang.String) r43, (java.lang.String) r44, (boolean) r45, (long) r46, (int) r48, (java.lang.String) r49, (int) r50, (long) r51, (java.lang.String) r53, (java.lang.String) r54, (long) r55, (int) r57)
        L_0x0103:
            r5 = r0
            goto L_0x0108
        L_0x0105:
            r58 = r13
            goto L_0x0103
        L_0x0108:
            boolean r0 = r15 instanceof com.google.android.gms.measurement.internal.zzbh
            if (r0 == 0) goto L_0x01af
            r12 = 0
            if (r10 == 0) goto L_0x0133
            com.google.android.gms.measurement.internal.zzio r0 = r1.zzu     // Catch:{ RemoteException -> 0x012d }
            com.google.android.gms.common.util.Clock r6 = r0.zzaU()     // Catch:{ RemoteException -> 0x012d }
            long r16 = r6.currentTimeMillis()     // Catch:{ RemoteException -> 0x012d }
            com.google.android.gms.common.util.Clock r0 = r0.zzaU()     // Catch:{ RemoteException -> 0x0127 }
            long r18 = r0.elapsedRealtime()     // Catch:{ RemoteException -> 0x0127 }
            r24 = r16
            r26 = r18
            goto L_0x0137
        L_0x0127:
            r0 = move-exception
            r26 = r12
            r19 = r16
            goto L_0x0176
        L_0x012d:
            r0 = move-exception
            r19 = r12
            r26 = r19
            goto L_0x0176
        L_0x0133:
            r24 = r12
            r26 = r24
        L_0x0137:
            com.google.android.gms.measurement.internal.zzbh r15 = (com.google.android.gms.measurement.internal.zzbh) r15     // Catch:{ RemoteException -> 0x0173 }
            r2.zzp(r15, r5)     // Catch:{ RemoteException -> 0x0173 }
            if (r10 == 0) goto L_0x0170
            com.google.android.gms.measurement.internal.zzhe r0 = r4.zzaW()     // Catch:{ RemoteException -> 0x0173 }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzj()     // Catch:{ RemoteException -> 0x0173 }
            java.lang.String r6 = "Logging telemetry for logEvent from database"
            r0.zza(r6)     // Catch:{ RemoteException -> 0x0173 }
            com.google.android.gms.measurement.internal.zzio r0 = r1.zzu     // Catch:{ RemoteException -> 0x0173 }
            com.google.android.gms.measurement.internal.zzha r16 = com.google.android.gms.measurement.internal.zzha.zza(r0)     // Catch:{ RemoteException -> 0x0173 }
            com.google.android.gms.common.util.Clock r6 = r0.zzaU()     // Catch:{ RemoteException -> 0x0173 }
            long r21 = r6.currentTimeMillis()     // Catch:{ RemoteException -> 0x0173 }
            com.google.android.gms.common.util.Clock r0 = r0.zzaU()     // Catch:{ RemoteException -> 0x0173 }
            long r14 = r0.elapsedRealtime()     // Catch:{ RemoteException -> 0x0173 }
            long r14 = r14 - r26
            int r0 = (int) r14     // Catch:{ RemoteException -> 0x0173 }
            r17 = 36301(0x8dcd, float:5.0869E-41)
            r18 = 0
            r19 = r24
            r23 = r0
            r16.zzc(r17, r18, r19, r21, r23)     // Catch:{ RemoteException -> 0x0173 }
        L_0x0170:
            r13 = 0
            goto L_0x021e
        L_0x0173:
            r0 = move-exception
            r19 = r24
        L_0x0176:
            com.google.android.gms.measurement.internal.zzio r6 = r1.zzu
            com.google.android.gms.measurement.internal.zzhe r6 = r6.zzaW()
            com.google.android.gms.measurement.internal.zzhc r6 = r6.zze()
            java.lang.String r14 = "Failed to send event to the service"
            r6.zzb(r14, r0)
            if (r10 == 0) goto L_0x0170
            int r0 = (r19 > r12 ? 1 : (r19 == r12 ? 0 : -1))
            if (r0 == 0) goto L_0x0170
            com.google.android.gms.measurement.internal.zzio r0 = r1.zzu
            com.google.android.gms.measurement.internal.zzha r16 = com.google.android.gms.measurement.internal.zzha.zza(r0)
            com.google.android.gms.common.util.Clock r6 = r0.zzaU()
            long r21 = r6.currentTimeMillis()
            com.google.android.gms.common.util.Clock r0 = r0.zzaU()
            long r12 = r0.elapsedRealtime()
            long r12 = r12 - r26
            int r0 = (int) r12
            r17 = 36301(0x8dcd, float:5.0869E-41)
            r18 = 13
            r23 = r0
            r16.zzc(r17, r18, r19, r21, r23)
            goto L_0x0170
        L_0x01af:
            boolean r0 = r15 instanceof com.google.android.gms.measurement.internal.zzqb
            if (r0 == 0) goto L_0x01ca
            com.google.android.gms.measurement.internal.zzqb r15 = (com.google.android.gms.measurement.internal.zzqb) r15     // Catch:{ RemoteException -> 0x01b9 }
            r2.zzB(r15, r5)     // Catch:{ RemoteException -> 0x01b9 }
            goto L_0x0170
        L_0x01b9:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzio r6 = r1.zzu
            com.google.android.gms.measurement.internal.zzhe r6 = r6.zzaW()
            com.google.android.gms.measurement.internal.zzhc r6 = r6.zze()
            java.lang.String r12 = "Failed to send user property to the service"
            r6.zzb(r12, r0)
            goto L_0x0170
        L_0x01ca:
            boolean r0 = r15 instanceof com.google.android.gms.measurement.internal.zzai
            if (r0 == 0) goto L_0x01e5
            com.google.android.gms.measurement.internal.zzai r15 = (com.google.android.gms.measurement.internal.zzai) r15     // Catch:{ RemoteException -> 0x01d4 }
            r2.zzt(r15, r5)     // Catch:{ RemoteException -> 0x01d4 }
            goto L_0x0170
        L_0x01d4:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzio r6 = r1.zzu
            com.google.android.gms.measurement.internal.zzhe r6 = r6.zzaW()
            com.google.android.gms.measurement.internal.zzhc r6 = r6.zze()
            java.lang.String r12 = "Failed to send conditional user property to the service"
            r6.zzb(r12, r0)
            goto L_0x0170
        L_0x01e5:
            com.google.android.gms.measurement.internal.zzio r0 = r1.zzu
            com.google.android.gms.measurement.internal.zzam r12 = r0.zzf()
            r13 = 0
            boolean r6 = r12.zzx(r13, r6)
            if (r6 == 0) goto L_0x0211
            boolean r6 = r15 instanceof com.google.android.gms.measurement.internal.zzbf
            if (r6 == 0) goto L_0x0211
            com.google.android.gms.measurement.internal.zzbf r15 = (com.google.android.gms.measurement.internal.zzbf) r15     // Catch:{ RemoteException -> 0x0200 }
            android.os.Bundle r0 = r15.zzc()     // Catch:{ RemoteException -> 0x0200 }
            r2.zzx(r0, r5)     // Catch:{ RemoteException -> 0x0200 }
            goto L_0x021e
        L_0x0200:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzio r6 = r1.zzu
            com.google.android.gms.measurement.internal.zzhe r6 = r6.zzaW()
            com.google.android.gms.measurement.internal.zzhc r6 = r6.zze()
            java.lang.String r12 = "Failed to send default event parameters to the service"
            r6.zzb(r12, r0)
            goto L_0x021e
        L_0x0211:
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zze()
            java.lang.String r6 = "Discarding data. Unrecognized parcel type."
            r0.zza(r6)
        L_0x021e:
            int r0 = r58 + 1
            r12 = r13
            r6 = 100
            r13 = r0
            r0 = r5
            goto L_0x005a
        L_0x0227:
            int r7 = r7 + 1
            r6 = 100
            goto L_0x001a
        L_0x022d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzny.zzP(com.google.android.gms.measurement.internal.zzgl, com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable, com.google.android.gms.measurement.internal.zzr):void");
    }

    /* access modifiers changed from: protected */
    public final void zzQ(zzai zzai) {
        Preconditions.checkNotNull(zzai);
        zzg();
        zza();
        this.zzu.zzaV();
        zzah(new zznm(this, true, zzae(true), this.zzu.zzi().zzn(zzai), new zzai(zzai), zzai));
    }

    /* access modifiers changed from: protected */
    public final void zzR(boolean z) {
        zzg();
        zza();
        if (zzab()) {
            zzah(new zznk(this, zzae(false)));
        }
    }

    /* access modifiers changed from: protected */
    public final void zzS(zzmh zzmh) {
        zzg();
        zza();
        zzah(new zznf(this, zzmh));
    }

    public final void zzT(Bundle bundle) {
        zzg();
        zza();
        zzbf zzbf = new zzbf(bundle);
        zzai();
        zzah(new zzng(this, true, zzae(false), this.zzu.zzf().zzx((String) null, zzgi.zzbl) && this.zzu.zzi().zzo(zzbf), zzbf, bundle));
    }

    /* access modifiers changed from: protected */
    public final void zzU() {
        zzg();
        zza();
        zzah(new zzmq(this));
    }

    /* access modifiers changed from: protected */
    public final void zzV() {
        zzg();
        zza();
        zzah(new zznj(this, zzae(true)));
    }

    /* access modifiers changed from: protected */
    public final void zzW(zzgl zzgl) {
        zzg();
        Preconditions.checkNotNull(zzgl);
        this.zzb = zzgl;
        zzag();
        zzaf();
    }

    /* access modifiers changed from: protected */
    public final void zzX(boolean z) {
        zzg();
        zza();
        zzah(new zzmp(this));
    }

    /* access modifiers changed from: protected */
    public final void zzY(zzqb zzqb) {
        zzg();
        zza();
        zzai();
        zzah(new zzmy(this, zzae(true), this.zzu.zzi().zzq(zzqb), zzqb));
    }

    /* access modifiers changed from: protected */
    public final void zzZ(zzag zzag) {
        zzg();
        zza();
        zzr zzae = zzae(true);
        Preconditions.checkNotNull(zzae);
        zzah(new zzmr(this, zzae, zzag));
    }

    public final boolean zzaa() {
        zzg();
        zza();
        return this.zzb != null;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzab() {
        zzg();
        zza();
        if (!zzad() || this.zzu.zzw().zzm() >= ((Integer) zzgi.zzaI.zza((Object) null)).intValue()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzac() {
        zzg();
        zza();
        if (!zzad() || this.zzu.zzw().zzm() >= 241200) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0104  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0112  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzad() {
        /*
            r8 = this;
            r8.zzg()
            r8.zza()
            java.lang.Boolean r0 = r8.zzc
            if (r0 != 0) goto L_0x0130
            r8.zzg()
            r8.zza()
            com.google.android.gms.measurement.internal.zzio r0 = r8.zzu
            com.google.android.gms.measurement.internal.zzht r1 = r0.zzm()
            r1.zzg()
            android.content.SharedPreferences r2 = r1.zzb()
            java.lang.String r3 = "use_service"
            boolean r2 = r2.contains(r3)
            r4 = 0
            if (r2 != 0) goto L_0x0028
            r1 = 0
            goto L_0x0034
        L_0x0028:
            android.content.SharedPreferences r1 = r1.zzb()
            boolean r1 = r1.getBoolean(r3, r4)
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
        L_0x0034:
            r2 = 1
            if (r1 == 0) goto L_0x003f
            boolean r5 = r1.booleanValue()
            if (r5 == 0) goto L_0x003f
            goto L_0x012a
        L_0x003f:
            r0.zzaV()
            com.google.android.gms.measurement.internal.zzio r5 = r8.zzu
            com.google.android.gms.measurement.internal.zzgs r5 = r5.zzh()
            int r5 = r5.zzh()
            if (r5 != r2) goto L_0x0051
        L_0x004e:
            r4 = r2
            goto L_0x00f8
        L_0x0051:
            com.google.android.gms.measurement.internal.zzhe r5 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r5 = r5.zzj()
            java.lang.String r6 = "Checking service availability"
            r5.zza(r6)
            com.google.android.gms.measurement.internal.zzqf r5 = r0.zzw()
            r6 = 12451000(0xbdfcb8, float:1.7447567E-38)
            int r5 = r5.zzp(r6)
            if (r5 == 0) goto L_0x00e9
            if (r5 == r2) goto L_0x00db
            r6 = 2
            if (r5 == r6) goto L_0x00b9
            r1 = 3
            if (r5 == r1) goto L_0x00ab
            r1 = 9
            if (r5 == r1) goto L_0x009d
            r1 = 18
            if (r5 == r1) goto L_0x008f
            com.google.android.gms.measurement.internal.zzhe r1 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zzk()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r5)
            java.lang.String r5 = "Unexpected service status"
            r1.zzb(r5, r2)
        L_0x008c:
            r2 = r4
            goto L_0x00f8
        L_0x008f:
            com.google.android.gms.measurement.internal.zzhe r1 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zzk()
            java.lang.String r4 = "Service updating"
            r1.zza(r4)
            goto L_0x004e
        L_0x009d:
            com.google.android.gms.measurement.internal.zzhe r1 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zzk()
            java.lang.String r2 = "Service invalid"
            r1.zza(r2)
            goto L_0x008c
        L_0x00ab:
            com.google.android.gms.measurement.internal.zzhe r1 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zzk()
            java.lang.String r2 = "Service disabled"
            r1.zza(r2)
            goto L_0x008c
        L_0x00b9:
            com.google.android.gms.measurement.internal.zzhe r5 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r5 = r5.zzd()
            java.lang.String r6 = "Service container out of date"
            r5.zza(r6)
            com.google.android.gms.measurement.internal.zzqf r5 = r0.zzw()
            int r5 = r5.zzm()
            r6 = 17443(0x4423, float:2.4443E-41)
            if (r5 >= r6) goto L_0x00d3
            goto L_0x00f8
        L_0x00d3:
            if (r1 != 0) goto L_0x00d6
            goto L_0x00d7
        L_0x00d6:
            r2 = r4
        L_0x00d7:
            r7 = r4
            r4 = r2
            r2 = r7
            goto L_0x00f8
        L_0x00db:
            com.google.android.gms.measurement.internal.zzhe r1 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zzj()
            java.lang.String r5 = "Service missing"
            r1.zza(r5)
            goto L_0x00f8
        L_0x00e9:
            com.google.android.gms.measurement.internal.zzhe r1 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zzj()
            java.lang.String r4 = "Service available"
            r1.zza(r4)
            goto L_0x004e
        L_0x00f8:
            if (r4 != 0) goto L_0x0112
            com.google.android.gms.measurement.internal.zzam r1 = r0.zzf()
            boolean r1 = r1.zzC()
            if (r1 == 0) goto L_0x0112
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zze()
            java.lang.String r1 = "No way to upload. Consider using the full version of Analytics"
            r0.zza(r1)
            goto L_0x0129
        L_0x0112:
            if (r2 == 0) goto L_0x0129
            com.google.android.gms.measurement.internal.zzht r0 = r0.zzm()
            r0.zzg()
            android.content.SharedPreferences r0 = r0.zzb()
            android.content.SharedPreferences$Editor r0 = r0.edit()
            r0.putBoolean(r3, r4)
            r0.apply()
        L_0x0129:
            r2 = r4
        L_0x012a:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r2)
            r8.zzc = r0
        L_0x0130:
            java.lang.Boolean r0 = r8.zzc
            boolean r0 = r0.booleanValue()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzny.zzad():boolean");
    }

    /* access modifiers changed from: protected */
    public final boolean zzf() {
        return false;
    }

    /* access modifiers changed from: protected */
    public final zzap zzh() {
        zzg();
        zza();
        zzgl zzgl = this.zzb;
        if (zzgl == null) {
            zzB();
            this.zzu.zzaW().zzd().zza("Failed to get consents; not connected to service yet.");
            return null;
        }
        zzr zzae = zzae(false);
        Preconditions.checkNotNull(zzae);
        try {
            zzap zze2 = zzgl.zze(zzae);
            zzag();
            return zze2;
        } catch (RemoteException e) {
            this.zzu.zzaW().zze().zzb("Failed to get consents; remote exception", e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final Boolean zzl() {
        return this.zzc;
    }

    /* access modifiers changed from: protected */
    public final void zzz() {
        zzg();
        zza();
        zzah(new zznd(this, zzae(true)));
    }
}
