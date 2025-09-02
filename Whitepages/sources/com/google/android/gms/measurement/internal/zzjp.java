package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import android.os.Binder;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.GoogleSignatureVerifier;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.internal.measurement.zzaa;
import com.google.android.gms.internal.measurement.zzc;
import com.google.android.gms.internal.measurement.zzd;
import com.google.firebase.messaging.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class zzjp extends zzgk {
    /* access modifiers changed from: private */
    public final zzpv zza;
    private Boolean zzb;
    private String zzc = null;

    public zzjp(zzpv zzpv, String str) {
        Preconditions.checkNotNull(zzpv);
        this.zza = zzpv;
    }

    public static /* synthetic */ void zzE(zzjp zzjp, zzr zzr) {
        zzpv zzpv = zzjp.zza;
        zzpv.zzL();
        zzpv.zzak(zzr);
    }

    public static /* synthetic */ void zzF(zzjp zzjp, zzr zzr, zzag zzag) {
        zzpv zzpv = zzjp.zza;
        zzpv.zzL();
        zzpv.zzap((String) Preconditions.checkNotNull(zzr.zza), zzag);
    }

    public static /* synthetic */ void zzG(zzjp zzjp, zzr zzr) {
        zzpv zzpv = zzjp.zza;
        zzpv.zzL();
        zzpv.zzai(zzr);
    }

    public static /* synthetic */ void zzH(zzjp zzjp, zzr zzr, Bundle bundle, zzgo zzgo, String str) {
        zzpv zzpv = zzjp.zza;
        zzpv.zzL();
        try {
            zzgo.zze(zzpv.zzF(zzr, bundle));
        } catch (RemoteException e) {
            zzjp.zza.zzaW().zze().zzc("Failed to return trigger URIs for app", str, e);
        }
    }

    public static /* synthetic */ void zzI(zzjp zzjp, Bundle bundle, String str, zzr zzr) {
        zzjp zzjp2 = zzjp;
        Bundle bundle2 = bundle;
        String str2 = str;
        zzpv zzpv = zzjp2.zza;
        boolean zzx = zzpv.zzi().zzx((String) null, zzgi.zzbc);
        boolean zzx2 = zzpv.zzi().zzx((String) null, zzgi.zzbe);
        if (!bundle.isEmpty() || !zzx) {
            zzaw zzj = zzpv.zzj();
            zzj.zzg();
            zzj.zzav();
            zzaw zzaw = zzj;
            byte[] zzcd = zzaw.zzg.zzA().zzm(new zzbc(zzj.zzu, "", str, "dep", 0, 0, bundle)).zzcd();
            zzio zzio = zzaw.zzu;
            zzio.zzaW().zzj().zzc("Saving default event parameters, appId, data size", str2, Integer.valueOf(zzcd.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str2);
            contentValues.put("parameters", zzcd);
            try {
                if (zzaw.zzj().insertWithOnConflict("default_event_params", (String) null, contentValues, 5) == -1) {
                    zzio.zzaW().zze().zzb("Failed to insert default event parameters (got -1). appId", zzhe.zzn(str));
                }
            } catch (SQLiteException e) {
                zzaw.zzu.zzaW().zze().zzc("Error storing default event parameters. appId", zzhe.zzn(str), e);
            }
            zzpv zzpv2 = zzjp2.zza;
            zzaw zzj2 = zzpv2.zzj();
            long j = zzr.zzF;
            if (!zzj2.zzag(str2, j)) {
                return;
            }
            if (zzx2) {
                zzpv2.zzj().zzG(str2, Long.valueOf(j), (String) null, bundle2);
            } else {
                zzpv2.zzj().zzG(str2, (Long) null, (String) null, bundle2);
            }
        } else {
            zzaw zzj3 = zzjp2.zza.zzj();
            zzj3.zzg();
            zzj3.zzav();
            try {
                zzj3.zzj().execSQL("delete from default_event_params where app_id=?", new String[]{str});
            } catch (SQLiteException e2) {
                zzj3.zzu.zzaW().zze().zzb("Error clearing default event params", e2);
            }
        }
    }

    private final void zzM(zzr zzr, boolean z) {
        Preconditions.checkNotNull(zzr);
        String str = zzr.zza;
        Preconditions.checkNotEmpty(str);
        zzN(str, false);
        this.zza.zzB().zzac(zzr.zzb, zzr.zzp);
    }

    private final void zzN(String str, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            if (z) {
                try {
                    if (this.zzb == null) {
                        boolean z2 = true;
                        if (!"com.google.android.gms".equals(this.zzc)) {
                            zzpv zzpv = this.zza;
                            if (!UidVerifier.isGooglePlayServicesUid(zzpv.zzaT(), Binder.getCallingUid())) {
                                if (!GoogleSignatureVerifier.getInstance(zzpv.zzaT()).isUidGoogleSigned(Binder.getCallingUid())) {
                                    z2 = false;
                                }
                            }
                        }
                        this.zzb = Boolean.valueOf(z2);
                    }
                    if (this.zzb.booleanValue()) {
                        return;
                    }
                } catch (SecurityException e) {
                    this.zza.zzaW().zze().zzb("Measurement Service called with invalid calling package. appId", zzhe.zzn(str));
                    throw e;
                }
            }
            if (this.zzc == null && GooglePlayServicesUtilLight.uidHasPackageName(this.zza.zzaT(), Binder.getCallingUid(), str)) {
                this.zzc = str;
            }
            if (!str.equals(this.zzc)) {
                throw new SecurityException(String.format("Unknown calling package name '%s'.", new Object[]{str}));
            }
            return;
        }
        this.zza.zzaW().zze().zza("Measurement Service called without app package");
        throw new SecurityException("Measurement Service called without app package");
    }

    private final void zzO(zzbh zzbh, zzr zzr) {
        zzpv zzpv = this.zza;
        zzpv.zzL();
        zzpv.zzS(zzbh, zzr);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x00bc, code lost:
        if (r0.zzaU().currentTimeMillis() >= (r2.zzb() + java.lang.Math.min(((java.lang.Long) com.google.android.gms.measurement.internal.zzgi.zzw.zza((java.lang.Object) null)).longValue() * (1 << (r4 - 1)), ((java.lang.Long) com.google.android.gms.measurement.internal.zzgi.zzx.zza((java.lang.Object) null)).longValue()))) goto L_0x00be;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ void zzd(com.google.android.gms.measurement.internal.zzjp r10, java.lang.String r11, com.google.android.gms.measurement.internal.zzpc r12, com.google.android.gms.measurement.internal.zzgr r13) {
        /*
            com.google.android.gms.measurement.internal.zzpv r0 = r10.zza
            r0.zzL()
            com.google.android.gms.measurement.internal.zzam r1 = r0.zzi()
            com.google.android.gms.measurement.internal.zzgg r2 = com.google.android.gms.measurement.internal.zzgi.zzaP
            r3 = 0
            boolean r1 = r1.zzx(r3, r2)
            if (r1 != 0) goto L_0x001d
            com.google.android.gms.measurement.internal.zzpe r12 = new com.google.android.gms.measurement.internal.zzpe
            java.util.List r0 = java.util.Collections.emptyList()
            r12.<init>(r0)
            goto L_0x0153
        L_0x001d:
            com.google.android.gms.measurement.internal.zzil r1 = r0.zzaX()
            r1.zzg()
            r0.zzM()
            com.google.android.gms.measurement.internal.zzaw r1 = r0.zzj()
            com.google.android.gms.measurement.internal.zzgg r2 = com.google.android.gms.measurement.internal.zzgi.zzA
            java.lang.Object r2 = r2.zza(r3)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            java.util.List r12 = r1.zzD(r11, r12, r2)
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Iterator r12 = r12.iterator()
        L_0x0044:
            boolean r2 = r12.hasNext()
            if (r2 == 0) goto L_0x014e
            java.lang.Object r2 = r12.next()
            com.google.android.gms.measurement.internal.zzpz r2 = (com.google.android.gms.measurement.internal.zzpz) r2
            java.lang.String r4 = r2.zzh()
            boolean r4 = r0.zzay(r11, r4)
            if (r4 != 0) goto L_0x0074
            com.google.android.gms.measurement.internal.zzhe r4 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r4 = r4.zzj()
            long r5 = r2.zzc()
            java.lang.Long r5 = java.lang.Long.valueOf(r5)
            java.lang.String r2 = r2.zzh()
            java.lang.String r6 = "[sgtm] batch skipped due to destination in backoff. appId, rowId, url"
            r4.zzd(r6, r11, r5, r2)
            goto L_0x0044
        L_0x0074:
            int r4 = r2.zza()
            if (r4 > 0) goto L_0x007b
            goto L_0x00be
        L_0x007b:
            com.google.android.gms.measurement.internal.zzgg r5 = com.google.android.gms.measurement.internal.zzgi.zzy
            java.lang.Object r5 = r5.zza(r3)
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            if (r4 <= r5) goto L_0x008b
            goto L_0x012f
        L_0x008b:
            com.google.android.gms.measurement.internal.zzgg r5 = com.google.android.gms.measurement.internal.zzgi.zzw
            java.lang.Object r5 = r5.zza(r3)
            java.lang.Long r5 = (java.lang.Long) r5
            long r5 = r5.longValue()
            int r4 = r4 + -1
            r7 = 1
            long r7 = r7 << r4
            long r5 = r5 * r7
            com.google.android.gms.measurement.internal.zzgg r4 = com.google.android.gms.measurement.internal.zzgi.zzx
            java.lang.Object r4 = r4.zza(r3)
            java.lang.Long r4 = (java.lang.Long) r4
            long r7 = r4.longValue()
            long r4 = java.lang.Math.min(r5, r7)
            com.google.android.gms.common.util.Clock r6 = r0.zzaU()
            long r6 = r6.currentTimeMillis()
            long r8 = r2.zzb()
            long r8 = r8 + r4
            int r4 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r4 < 0) goto L_0x012f
        L_0x00be:
            com.google.android.gms.measurement.internal.zzpa r2 = r2.zze()
            com.google.android.gms.internal.measurement.zzht r4 = com.google.android.gms.internal.measurement.zzhv.zzb()     // Catch:{ zzmm -> 0x0120 }
            byte[] r5 = r2.zzb     // Catch:{ zzmm -> 0x0120 }
            com.google.android.gms.internal.measurement.zzng r4 = com.google.android.gms.measurement.internal.zzqa.zzp(r4, r5)     // Catch:{ zzmm -> 0x0120 }
            com.google.android.gms.internal.measurement.zzht r4 = (com.google.android.gms.internal.measurement.zzht) r4     // Catch:{ zzmm -> 0x0120 }
            r5 = 0
        L_0x00cf:
            int r6 = r4.zza()     // Catch:{ zzmm -> 0x0120 }
            if (r5 >= r6) goto L_0x00f0
            com.google.android.gms.internal.measurement.zzhx r6 = r4.zzh(r5)     // Catch:{ zzmm -> 0x0120 }
            com.google.android.gms.internal.measurement.zzlz r6 = r6.zzch()     // Catch:{ zzmm -> 0x0120 }
            com.google.android.gms.internal.measurement.zzhw r6 = (com.google.android.gms.internal.measurement.zzhw) r6     // Catch:{ zzmm -> 0x0120 }
            com.google.android.gms.common.util.Clock r7 = r0.zzaU()     // Catch:{ zzmm -> 0x0120 }
            long r7 = r7.currentTimeMillis()     // Catch:{ zzmm -> 0x0120 }
            r6.zzaA(r7)     // Catch:{ zzmm -> 0x0120 }
            r4.zze(r5, r6)     // Catch:{ zzmm -> 0x0120 }
            int r5 = r5 + 1
            goto L_0x00cf
        L_0x00f0:
            com.google.android.gms.internal.measurement.zzmd r5 = r4.zzba()     // Catch:{ zzmm -> 0x0120 }
            com.google.android.gms.internal.measurement.zzhv r5 = (com.google.android.gms.internal.measurement.zzhv) r5     // Catch:{ zzmm -> 0x0120 }
            byte[] r5 = r5.zzcd()     // Catch:{ zzmm -> 0x0120 }
            r2.zzb = r5     // Catch:{ zzmm -> 0x0120 }
            com.google.android.gms.measurement.internal.zzhe r5 = r0.zzaW()     // Catch:{ zzmm -> 0x0120 }
            java.lang.String r5 = r5.zzr()     // Catch:{ zzmm -> 0x0120 }
            r6 = 2
            boolean r5 = android.util.Log.isLoggable(r5, r6)     // Catch:{ zzmm -> 0x0120 }
            if (r5 == 0) goto L_0x011b
            com.google.android.gms.measurement.internal.zzqa r5 = r0.zzA()     // Catch:{ zzmm -> 0x0120 }
            com.google.android.gms.internal.measurement.zzmd r4 = r4.zzba()     // Catch:{ zzmm -> 0x0120 }
            com.google.android.gms.internal.measurement.zzhv r4 = (com.google.android.gms.internal.measurement.zzhv) r4     // Catch:{ zzmm -> 0x0120 }
            java.lang.String r4 = r5.zzq(r4)     // Catch:{ zzmm -> 0x0120 }
            r2.zzg = r4     // Catch:{ zzmm -> 0x0120 }
        L_0x011b:
            r1.add(r2)
            goto L_0x0044
        L_0x0120:
            com.google.android.gms.measurement.internal.zzhe r2 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zzk()
            java.lang.String r4 = "Failed to parse queued batch. appId"
            r2.zzb(r4, r11)
            goto L_0x0044
        L_0x012f:
            com.google.android.gms.measurement.internal.zzhe r4 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r4 = r4.zzj()
            long r5 = r2.zzc()
            java.lang.Long r5 = java.lang.Long.valueOf(r5)
            long r6 = r2.zzb()
            java.lang.Long r2 = java.lang.Long.valueOf(r6)
            java.lang.String r6 = "[sgtm] batch skipped waiting for next retry. appId, rowId, lastUploadMillis"
            r4.zzd(r6, r11, r5, r2)
            goto L_0x0044
        L_0x014e:
            com.google.android.gms.measurement.internal.zzpe r12 = new com.google.android.gms.measurement.internal.zzpe
            r12.<init>(r1)
        L_0x0153:
            r13.zze(r12)     // Catch:{ RemoteException -> 0x0170 }
            com.google.android.gms.measurement.internal.zzpv r13 = r10.zza     // Catch:{ RemoteException -> 0x0170 }
            com.google.android.gms.measurement.internal.zzhe r13 = r13.zzaW()     // Catch:{ RemoteException -> 0x0170 }
            com.google.android.gms.measurement.internal.zzhc r13 = r13.zzj()     // Catch:{ RemoteException -> 0x0170 }
            java.lang.String r0 = "[sgtm] Sending queued upload batches to client. appId, count"
            java.util.List r12 = r12.zza     // Catch:{ RemoteException -> 0x0170 }
            int r12 = r12.size()     // Catch:{ RemoteException -> 0x0170 }
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch:{ RemoteException -> 0x0170 }
            r13.zzc(r0, r11, r12)     // Catch:{ RemoteException -> 0x0170 }
            return
        L_0x0170:
            r12 = move-exception
            com.google.android.gms.measurement.internal.zzpv r10 = r10.zza
            com.google.android.gms.measurement.internal.zzhe r10 = r10.zzaW()
            com.google.android.gms.measurement.internal.zzhc r10 = r10.zze()
            java.lang.String r13 = "[sgtm] Failed to return upload batches for app"
            r10.zzc(r13, r11, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzjp.zzd(com.google.android.gms.measurement.internal.zzjp, java.lang.String, com.google.android.gms.measurement.internal.zzpc, com.google.android.gms.measurement.internal.zzgr):void");
    }

    public final void zzA(zzr zzr) {
        Preconditions.checkNotEmpty(zzr.zza);
        Preconditions.checkNotNull(zzr.zzu);
        zzK(new zzir(this, zzr));
    }

    public final void zzB(zzqb zzqb, zzr zzr) {
        Preconditions.checkNotNull(zzqb);
        zzM(zzr, false);
        zzL(new zzjm(this, zzqb, zzr));
    }

    public final void zzC(zzr zzr, zzag zzag) {
        if (this.zza.zzi().zzx((String) null, zzgi.zzaP)) {
            zzM(zzr, false);
            zzL(new zzip(this, zzr, zzag));
        }
    }

    public final byte[] zzD(zzbh zzbh, String str) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzbh);
        zzN(str, true);
        zzpv zzpv = this.zza;
        zzhc zzd = zzpv.zzaW().zzd();
        zzgx zzo = zzpv.zzo();
        String str2 = zzbh.zza;
        zzd.zzb("Log and bundle. event", zzo.zzd(str2));
        long nanoTime = zzpv.zzaU().nanoTime() / 1000000;
        try {
            byte[] bArr = (byte[]) zzpv.zzaX().zzh(new zzjl(this, zzbh, str)).get();
            if (bArr == null) {
                zzpv.zzaW().zze().zzb("Log and bundle returned null. appId", zzhe.zzn(str));
                bArr = new byte[0];
            }
            zzpv.zzaW().zzd().zzd("Log and bundle processed. event, size, time_ms", zzpv.zzo().zzd(str2), Integer.valueOf(bArr.length), Long.valueOf((zzpv.zzaU().nanoTime() / 1000000) - nanoTime));
            return bArr;
        } catch (InterruptedException e) {
            e = e;
            zzpv zzpv2 = this.zza;
            zzpv2.zzaW().zze().zzd("Failed to log and bundle. appId, event, error", zzhe.zzn(str), zzpv2.zzo().zzd(zzbh.zza), e);
            return null;
        } catch (ExecutionException e2) {
            e = e2;
            zzpv zzpv22 = this.zza;
            zzpv22.zzaW().zze().zzd("Failed to log and bundle. appId, event, error", zzhe.zzn(str), zzpv22.zzo().zzd(zzbh.zza), e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzJ(zzbh zzbh, zzr zzr) {
        zzc zzc2 = null;
        if (!((Boolean) zzgi.zzbn.zza((Object) null)).booleanValue()) {
            zzpv zzpv = this.zza;
            zzif zzr2 = zzpv.zzr();
            String str = zzr.zza;
            if (!zzr2.zzs(str)) {
                zzO(zzbh, zzr);
                return;
            }
            zzpv.zzaW().zzj().zzb("EES config found for", str);
        }
        zzpv zzpv2 = this.zza;
        zzif zzr3 = zzpv2.zzr();
        String str2 = zzr.zza;
        if (!TextUtils.isEmpty(str2)) {
            zzc2 = (zzc) zzr3.zzd.get(str2);
        }
        if (zzc2 != null) {
            try {
                Map zzv = zzpv2.zzA().zzv(zzbh.zzb.zzc(), true);
                String str3 = zzbh.zza;
                String zza2 = zzjy.zza(str3);
                if (zza2 != null) {
                    str3 = zza2;
                }
                if (zzc2.zze(new zzaa(str3, zzbh.zzd, zzv))) {
                    if (zzc2.zzg()) {
                        zzpv zzpv3 = this.zza;
                        zzpv3.zzaW().zzj().zzb("EES edited event", zzbh.zza);
                        zzO(zzpv3.zzA().zzj(zzc2.zza().zzb()), zzr);
                    } else {
                        zzO(zzbh, zzr);
                    }
                    if (zzc2.zzf()) {
                        for (zzaa zzaa : zzc2.zza().zzc()) {
                            zzpv zzpv4 = this.zza;
                            zzpv4.zzaW().zzj().zzb("EES logging created event", zzaa.zze());
                            zzO(zzpv4.zzA().zzj(zzaa), zzr);
                        }
                        return;
                    }
                    return;
                }
            } catch (zzd unused) {
                this.zza.zzaW().zze().zzc("EES error. appId, eventName", zzr.zzb, zzbh.zza);
            }
            this.zza.zzaW().zzj().zzb("EES was not applied to event", zzbh.zza);
            zzO(zzbh, zzr);
            return;
        }
        this.zza.zzaW().zzj().zzb("EES not loaded for", zzr.zza);
        zzO(zzbh, zzr);
    }

    /* access modifiers changed from: package-private */
    public final void zzK(Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        zzpv zzpv = this.zza;
        if (zzpv.zzaX().zzu()) {
            runnable.run();
        } else {
            zzpv.zzaX().zzr(runnable);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzL(Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        zzpv zzpv = this.zza;
        if (zzpv.zzaX().zzu()) {
            runnable.run();
        } else {
            zzpv.zzaX().zzq(runnable);
        }
    }

    /* access modifiers changed from: package-private */
    public final zzbh zzb(zzbh zzbh, zzr zzr) {
        zzbf zzbf;
        if (!(!Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN.equals(zzbh.zza) || (zzbf = zzbh.zzb) == null || zzbf.zza() == 0)) {
            String zzg = zzbf.zzg("_cis");
            if ("referrer broadcast".equals(zzg) || "referrer API".equals(zzg)) {
                this.zza.zzaW().zzi().zzb("Event has been filtered ", zzbh.toString());
                return new zzbh("_cmpx", zzbf, zzbh.zzc, zzbh.zzd);
            }
        }
        return zzbh;
    }

    public final zzap zze(zzr zzr) {
        zzM(zzr, false);
        Preconditions.checkNotEmpty(zzr.zza);
        try {
            return (zzap) this.zza.zzaX().zzh(new zzji(this, zzr)).get(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            this.zza.zzaW().zze().zzc("Failed to get consent. appId", zzhe.zzn(zzr.zza), e);
            return new zzap((Bundle) null);
        }
    }

    public final String zzf(zzr zzr) {
        zzM(zzr, false);
        return this.zza.zzD(zzr);
    }

    public final List zzg(zzr zzr, Bundle bundle) {
        zzM(zzr, false);
        Preconditions.checkNotNull(zzr.zza);
        zzpv zzpv = this.zza;
        if (zzpv.zzi().zzx((String) null, zzgi.zzbh)) {
            try {
                return (List) zzpv.zzaX().zzh(new zzjn(this, zzr, bundle)).get(10000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                this.zza.zzaW().zze().zzc("Failed to get trigger URIs. appId", zzhe.zzn(zzr.zza), e);
                return Collections.emptyList();
            }
        } else {
            try {
                return (List) this.zza.zzaX().zzf(new zzjo(this, zzr, bundle)).get();
            } catch (InterruptedException | ExecutionException e2) {
                this.zza.zzaW().zze().zzc("Failed to get trigger URIs. appId", zzhe.zzn(zzr.zza), e2);
                return Collections.emptyList();
            }
        }
    }

    public final List zzh(zzr zzr, boolean z) {
        zzM(zzr, false);
        String str = zzr.zza;
        Preconditions.checkNotNull(str);
        try {
            List<zzqd> list = (List) this.zza.zzaX().zzf(new zziv(this, str)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzqd zzqd : list) {
                if (!z) {
                    if (!zzqf.zzap(zzqd.zzc)) {
                    }
                }
                arrayList.add(new zzqb(zzqd));
            }
            return arrayList;
        } catch (InterruptedException e) {
            e = e;
            this.zza.zzaW().zze().zzc("Failed to get user properties. appId", zzhe.zzn(zzr.zza), e);
            return null;
        } catch (ExecutionException e2) {
            e = e2;
            this.zza.zzaW().zze().zzc("Failed to get user properties. appId", zzhe.zzn(zzr.zza), e);
            return null;
        }
    }

    public final List zzi(String str, String str2, zzr zzr) {
        zzM(zzr, false);
        String str3 = zzr.zza;
        Preconditions.checkNotNull(str3);
        try {
            return (List) this.zza.zzaX().zzf(new zzjd(this, str3, str, str2)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzaW().zze().zzb("Failed to get conditional user properties", e);
            return Collections.emptyList();
        }
    }

    public final List zzj(String str, String str2, String str3) {
        zzN(str, true);
        try {
            return (List) this.zza.zzaX().zzf(new zzje(this, str, str2, str3)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzaW().zze().zzb("Failed to get conditional user properties as", e);
            return Collections.emptyList();
        }
    }

    public final List zzk(String str, String str2, boolean z, zzr zzr) {
        zzM(zzr, false);
        String str3 = zzr.zza;
        Preconditions.checkNotNull(str3);
        try {
            List<zzqd> list = (List) this.zza.zzaX().zzf(new zzjb(this, str3, str, str2)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzqd zzqd : list) {
                if (!z) {
                    if (!zzqf.zzap(zzqd.zzc)) {
                    }
                }
                arrayList.add(new zzqb(zzqd));
            }
            return arrayList;
        } catch (InterruptedException e) {
            e = e;
            this.zza.zzaW().zze().zzc("Failed to query user properties. appId", zzhe.zzn(zzr.zza), e);
            return Collections.emptyList();
        } catch (ExecutionException e2) {
            e = e2;
            this.zza.zzaW().zze().zzc("Failed to query user properties. appId", zzhe.zzn(zzr.zza), e);
            return Collections.emptyList();
        }
    }

    public final List zzl(String str, String str2, String str3, boolean z) {
        zzN(str, true);
        try {
            List<zzqd> list = (List) this.zza.zzaX().zzf(new zzjc(this, str, str2, str3)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzqd zzqd : list) {
                if (!z) {
                    if (!zzqf.zzap(zzqd.zzc)) {
                    }
                }
                arrayList.add(new zzqb(zzqd));
            }
            return arrayList;
        } catch (InterruptedException e) {
            e = e;
            this.zza.zzaW().zze().zzc("Failed to get user properties as. appId", zzhe.zzn(str), e);
            return Collections.emptyList();
        } catch (ExecutionException e2) {
            e = e2;
            this.zza.zzaW().zze().zzc("Failed to get user properties as. appId", zzhe.zzn(str), e);
            return Collections.emptyList();
        }
    }

    public final void zzm(zzr zzr) {
        zzM(zzr, false);
        zzL(new zzix(this, zzr));
    }

    public final void zzn(zzr zzr) {
        zzM(zzr, false);
        zzL(new zziw(this, zzr));
    }

    public final void zzo(zzr zzr, zzpc zzpc, zzgr zzgr) {
        zzpv zzpv = this.zza;
        if (!zzpv.zzi().zzx((String) null, zzgi.zzaP)) {
            try {
                zzgr.zze(new zzpe(Collections.emptyList()));
                zzpv.zzaW().zzj().zza("[sgtm] Client upload is not enabled on the service side.");
            } catch (RemoteException e) {
                this.zza.zzaW().zzk().zzb("[sgtm] UploadBatchesCallback failed.", e);
            }
        } else {
            zzM(zzr, false);
            this.zza.zzaX().zzq(new zzis(this, (String) Preconditions.checkNotNull(zzr.zza), zzpc, zzgr));
        }
    }

    public final void zzp(zzbh zzbh, zzr zzr) {
        Preconditions.checkNotNull(zzbh);
        zzM(zzr, false);
        zzL(new zzjj(this, zzbh, zzr));
    }

    public final void zzq(zzbh zzbh, String str, String str2) {
        Preconditions.checkNotNull(zzbh);
        Preconditions.checkNotEmpty(str);
        zzN(str, true);
        zzL(new zzjk(this, zzbh, str));
    }

    public final void zzr(zzr zzr, Bundle bundle, zzgo zzgo) {
        zzM(zzr, false);
        this.zza.zzaX().zzq(new zziq(this, zzr, bundle, zzgo, (String) Preconditions.checkNotNull(zzr.zza)));
    }

    public final void zzs(zzr zzr) {
        String str = zzr.zza;
        Preconditions.checkNotEmpty(str);
        zzN(str, false);
        zzL(new zzjg(this, zzr));
    }

    public final void zzt(zzai zzai, zzr zzr) {
        Preconditions.checkNotNull(zzai);
        Preconditions.checkNotNull(zzai.zzc);
        zzM(zzr, false);
        zzai zzai2 = new zzai(zzai);
        zzai2.zza = zzr.zza;
        zzL(new zziz(this, zzai2, zzr));
    }

    public final void zzu(zzai zzai) {
        Preconditions.checkNotNull(zzai);
        Preconditions.checkNotNull(zzai.zzc);
        Preconditions.checkNotEmpty(zzai.zza);
        zzN(zzai.zza, true);
        zzL(new zzja(this, new zzai(zzai)));
    }

    public final void zzv(zzr zzr) {
        Preconditions.checkNotEmpty(zzr.zza);
        Preconditions.checkNotNull(zzr.zzu);
        zzK(new zzjh(this, zzr));
    }

    public final void zzw(long j, String str, String str2, String str3) {
        zzL(new zziy(this, str2, str3, str, j));
    }

    public final void zzx(Bundle bundle, zzr zzr) {
        zzM(zzr, false);
        String str = zzr.zza;
        Preconditions.checkNotNull(str);
        zzL(new zziu(this, bundle, str, zzr));
    }

    public final void zzy(zzr zzr) {
        Preconditions.checkNotEmpty(zzr.zza);
        Preconditions.checkNotNull(zzr.zzu);
        zzK(new zzit(this, zzr));
    }

    public final void zzz(zzr zzr) {
        zzM(zzr, false);
        zzL(new zzjf(this, zzr));
    }
}
