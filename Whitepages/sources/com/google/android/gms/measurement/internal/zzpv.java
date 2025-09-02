package com.google.android.gms.measurement.internal;

import android.app.BroadcastOptions;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzbx;
import com.google.android.gms.internal.measurement.zzcc;
import com.google.android.gms.internal.measurement.zzdh;
import com.google.android.gms.internal.measurement.zzgo;
import com.google.android.gms.internal.measurement.zzhb;
import com.google.android.gms.internal.measurement.zzhc;
import com.google.android.gms.internal.measurement.zzhl;
import com.google.android.gms.internal.measurement.zzhm;
import com.google.android.gms.internal.measurement.zzhp;
import com.google.android.gms.internal.measurement.zzhq;
import com.google.android.gms.internal.measurement.zzhv;
import com.google.android.gms.internal.measurement.zzhw;
import com.google.android.gms.internal.measurement.zzih;
import com.google.android.gms.internal.measurement.zzin;
import com.google.android.gms.internal.measurement.zzio;
import com.google.android.gms.internal.measurement.zzmm;
import com.google.android.gms.internal.measurement.zzqr;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.messaging.Constants;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

public final class zzpv implements zzjs {
    private static volatile zzpv zzb;
    private List zzA;
    private long zzB;
    private final Map zzC;
    private final Map zzD;
    private final Map zzE;
    private final Map zzF = new HashMap();
    private zzmh zzG;
    private String zzH;
    private zzaz zzI;
    /* access modifiers changed from: private */
    public long zzJ;
    private final zzqe zzK = new zzpq(this);
    long zza;
    private final zzif zzc;
    private final zzhk zzd;
    private zzaw zze;
    private zzhm zzf;
    private zzoy zzg;
    private zzae zzh;
    private final zzqa zzi;
    private zzmc zzj;
    private zzoa zzk;
    private final zzpi zzl;
    private zzhw zzm;
    /* access modifiers changed from: private */
    public final zzio zzn;
    private final AtomicBoolean zzo = new AtomicBoolean(false);
    private boolean zzp;
    private List zzq;
    /* access modifiers changed from: private */
    public final Deque zzr = new LinkedList();
    private int zzs;
    private int zzt;
    private boolean zzu;
    private boolean zzv;
    private boolean zzw;
    private FileLock zzx;
    private FileChannel zzy;
    private List zzz;

    zzpv(zzpw zzpw, zzio zzio) {
        Preconditions.checkNotNull(zzpw);
        this.zzn = zzio.zzp(zzpw.zza, (zzdh) null, (Long) null);
        this.zzB = -1;
        this.zzl = new zzpi(this);
        zzqa zzqa = new zzqa(this);
        zzqa.zzaw();
        this.zzi = zzqa;
        zzhk zzhk = new zzhk(this);
        zzhk.zzaw();
        this.zzd = zzhk;
        zzif zzif = new zzif(this);
        zzif.zzaw();
        this.zzc = zzif;
        this.zzC = new HashMap();
        this.zzD = new HashMap();
        this.zzE = new HashMap();
        zzaX().zzq(new zzpk(this, zzpw));
    }

    static /* bridge */ /* synthetic */ void zzH(zzpv zzpv, zzpw zzpw) {
        zzpv.zzaX().zzg();
        zzpv.zzm = new zzhw(zzpv);
        zzaw zzaw = new zzaw(zzpv);
        zzaw.zzaw();
        zzpv.zze = zzaw;
        zzpv.zzi().zzu((zzal) Preconditions.checkNotNull(zzpv.zzc));
        zzoa zzoa = new zzoa(zzpv);
        zzoa.zzaw();
        zzpv.zzk = zzoa;
        zzae zzae = new zzae(zzpv);
        zzae.zzaw();
        zzpv.zzh = zzae;
        zzmc zzmc = new zzmc(zzpv);
        zzmc.zzaw();
        zzpv.zzj = zzmc;
        zzoy zzoy = new zzoy(zzpv);
        zzoy.zzaw();
        zzpv.zzg = zzoy;
        zzpv.zzf = new zzhm(zzpv);
        if (zzpv.zzs != zzpv.zzt) {
            zzpv.zzaW().zze().zzc("Not all upload components initialized", Integer.valueOf(zzpv.zzs), Integer.valueOf(zzpv.zzt));
        }
        zzpv.zzo.set(true);
        zzpv.zzaW().zzj().zza("UploadController is now fully initialized");
    }

    static final void zzaA(zzhl zzhl, int i, String str) {
        List zzp2 = zzhl.zzp();
        int i2 = 0;
        while (i2 < zzp2.size()) {
            if (!"_err".equals(((zzhq) zzp2.get(i2)).zzg())) {
                i2++;
            } else {
                return;
            }
        }
        zzhp zze2 = zzhq.zze();
        zze2.zzj("_err");
        zze2.zzi((long) i);
        zzhp zze3 = zzhq.zze();
        zze3.zzj("_ev");
        zze3.zzk(str);
        zzhl.zzf((zzhq) zze2.zzba());
        zzhl.zzf((zzhq) zze3.zzba());
    }

    static final void zzaB(zzhl zzhl, String str) {
        List zzp2 = zzhl.zzp();
        for (int i = 0; i < zzp2.size(); i++) {
            if (str.equals(((zzhq) zzp2.get(i)).zzg())) {
                zzhl.zzh(i);
                return;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002c, code lost:
        r1 = com.google.android.gms.measurement.internal.zzjw.zzd;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzaC(java.lang.String r7, com.google.android.gms.measurement.internal.zzao r8) {
        /*
            r6 = this;
            com.google.android.gms.measurement.internal.zzif r0 = r6.zzc
            com.google.android.gms.internal.measurement.zzgi r1 = r0.zzi(r7)
            r2 = 1
            if (r1 != 0) goto L_0x0011
            com.google.android.gms.measurement.internal.zzjw r7 = com.google.android.gms.measurement.internal.zzjw.AD_PERSONALIZATION
            com.google.android.gms.measurement.internal.zzan r0 = com.google.android.gms.measurement.internal.zzan.FAILSAFE
            r8.zzd(r7, r0)
            return r2
        L_0x0011:
            com.google.android.gms.measurement.internal.zzaw r1 = r6.zzj()
            com.google.android.gms.measurement.internal.zzh r1 = r1.zzl(r7)
            r3 = 0
            if (r1 == 0) goto L_0x0041
            java.lang.String r1 = r1.zzK()
            com.google.android.gms.measurement.internal.zze r1 = com.google.android.gms.measurement.internal.zze.zza(r1)
            com.google.android.gms.measurement.internal.zzju r1 = r1.zzb()
            com.google.android.gms.measurement.internal.zzju r4 = com.google.android.gms.measurement.internal.zzju.POLICY
            if (r1 != r4) goto L_0x0041
            com.google.android.gms.measurement.internal.zzjw r1 = com.google.android.gms.measurement.internal.zzjw.AD_PERSONALIZATION
            com.google.android.gms.measurement.internal.zzju r4 = r0.zzf(r7, r1)
            com.google.android.gms.measurement.internal.zzju r5 = com.google.android.gms.measurement.internal.zzju.UNINITIALIZED
            if (r4 == r5) goto L_0x0041
            com.google.android.gms.measurement.internal.zzan r7 = com.google.android.gms.measurement.internal.zzan.REMOTE_ENFORCED_DEFAULT
            r8.zzd(r1, r7)
            com.google.android.gms.measurement.internal.zzju r7 = com.google.android.gms.measurement.internal.zzju.GRANTED
            if (r4 != r7) goto L_0x0040
            return r3
        L_0x0040:
            return r2
        L_0x0041:
            com.google.android.gms.measurement.internal.zzjw r1 = com.google.android.gms.measurement.internal.zzjw.AD_PERSONALIZATION
            com.google.android.gms.measurement.internal.zzan r4 = com.google.android.gms.measurement.internal.zzan.REMOTE_DEFAULT
            r8.zzd(r1, r4)
            boolean r7 = r0.zzu(r7, r1)
            if (r7 == 0) goto L_0x004f
            return r3
        L_0x004f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzpv.zzaC(java.lang.String, com.google.android.gms.measurement.internal.zzao):int");
    }

    private final zzr zzaD(String str) {
        String str2 = str;
        zzh zzl2 = zzj().zzl(str2);
        if (zzl2 == null || TextUtils.isEmpty(zzl2.zzF())) {
            zzaW().zzd().zzb("No app data available; dropping", str2);
            return null;
        }
        Boolean zzaF = zzaF(zzl2);
        if (zzaF == null || zzaF.booleanValue()) {
            return new zzr(str, zzl2.zzH(), zzl2.zzF(), zzl2.zze(), zzl2.zzE(), zzl2.zzq(), zzl2.zzn(), (String) null, zzl2.zzaJ(), false, zzl2.zzG(), 0, 0, zzl2.zzaI(), false, zzl2.zzA(), zzl2.zzx(), zzl2.zzo(), zzl2.zzN(), (String) null, zzu(str).zzq(), "", (String) null, zzl2.zzaL(), zzl2.zzw(), zzu(str).zzb(), zzm(str).zzj(), zzl2.zza(), zzl2.zzf(), zzl2.zzM(), zzl2.zzK(), 0, zzl2.zzb());
        }
        zzaW().zze().zzb("App version does not match; dropping. appId", zzhe.zzn(str));
        return null;
    }

    private final zzaz zzaE() {
        if (this.zzI == null) {
            this.zzI = new zzpn(this, this.zzn);
        }
        return this.zzI;
    }

    private final Boolean zzaF(zzh zzh2) {
        try {
            if (zzh2.zze() != -2147483648L) {
                if (zzh2.zze() == ((long) Wrappers.packageManager(this.zzn.zzaT()).getPackageInfo(zzh2.zzC(), 0).versionCode)) {
                    return Boolean.TRUE;
                }
            } else {
                String str = Wrappers.packageManager(this.zzn.zzaT()).getPackageInfo(zzh2.zzC(), 0).versionName;
                String zzF2 = zzh2.zzF();
                if (zzF2 != null && zzF2.equals(str)) {
                    return Boolean.TRUE;
                }
            }
            return Boolean.FALSE;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    private static String zzaG(Map map, String str) {
        if (map == null) {
            return null;
        }
        for (Map.Entry entry : map.entrySet()) {
            if (str.equalsIgnoreCase((String) entry.getKey())) {
                if (!((List) entry.getValue()).isEmpty()) {
                    return (String) ((List) entry.getValue()).get(0);
                }
                return null;
            }
        }
        return null;
    }

    private final void zzaH() {
        zzaX().zzg();
        if (this.zzu || this.zzv || this.zzw) {
            zzaW().zzj().zzd("Not stopping services. fetch, network, upload", Boolean.valueOf(this.zzu), Boolean.valueOf(this.zzv), Boolean.valueOf(this.zzw));
            return;
        }
        zzaW().zzj().zza("Stopping uploading service(s)");
        List<Runnable> list = this.zzq;
        if (list != null) {
            for (Runnable run : list) {
                run.run();
            }
            ((List) Preconditions.checkNotNull(this.zzq)).clear();
        }
    }

    private final void zzaI(zzhw zzhw, long j, boolean z) {
        String str;
        zzqd zzqd;
        String str2;
        Object obj;
        if (true != z) {
            str = "_lte";
        } else {
            str = "_se";
        }
        zzqd zzy2 = zzj().zzy(zzhw.zzaF(), str);
        if (zzy2 == null || (obj = zzy2.zze) == null) {
            zzqd = new zzqd(zzhw.zzaF(), "auto", str, zzaU().currentTimeMillis(), Long.valueOf(j));
        } else {
            zzqd = new zzqd(zzhw.zzaF(), "auto", str, zzaU().currentTimeMillis(), Long.valueOf(((Long) obj).longValue() + j));
        }
        zzin zze2 = zzio.zze();
        zze2.zzf(str);
        zze2.zzg(zzaU().currentTimeMillis());
        Object obj2 = zzqd.zze;
        zze2.zze(((Long) obj2).longValue());
        zzio zzio = (zzio) zze2.zzba();
        int zza2 = zzqa.zza(zzhw, str);
        if (zza2 >= 0) {
            zzhw.zzaC(zza2, zzio);
        } else {
            zzhw.zzp(zzio);
        }
        if (j > 0) {
            zzj().zzai(zzqd);
            if (true != z) {
                str2 = "lifetime";
            } else {
                str2 = "session-scoped";
            }
            zzaW().zzj().zzc("Updated engagement user property. scope, value", str2, obj2);
        }
    }

    /* access modifiers changed from: private */
    public final void zzaJ() {
        zzaX().zzg();
        if (!this.zzr.isEmpty() && !zzaE().zze()) {
            long max = Math.max(0, ((long) ((Integer) zzgi.zzaA.zza((Object) null)).intValue()) - (zzaU().elapsedRealtime() - this.zzJ));
            zzaW().zzj().zzb("Scheduling notify next app runnable, delay in ms", Long.valueOf(max));
            zzaE().zzd(max);
        }
    }

    /* access modifiers changed from: private */
    public static void zzaK(Context context, Intent intent) {
        if (Build.VERSION.SDK_INT < 34) {
            context.sendBroadcast(intent);
        } else {
            context.sendBroadcast(intent, (String) null, BroadcastOptions.makeBasic().setShareIdentityEnabled(true).toBundle());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x018d  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x01a9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzaL() {
        /*
            r20 = this;
            r0 = r20
            com.google.android.gms.measurement.internal.zzil r1 = r20.zzaX()
            r1.zzg()
            r20.zzM()
            long r1 = r0.zza
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 <= 0) goto L_0x004d
            com.google.android.gms.common.util.Clock r1 = r20.zzaU()
            long r1 = r1.elapsedRealtime()
            long r5 = r0.zza
            long r1 = r1 - r5
            long r1 = java.lang.Math.abs(r1)
            r5 = 3600000(0x36ee80, double:1.7786363E-317)
            long r5 = r5 - r1
            int r1 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r1 <= 0) goto L_0x004b
            com.google.android.gms.measurement.internal.zzhe r1 = r20.zzaW()
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zzj()
            java.lang.Long r2 = java.lang.Long.valueOf(r5)
            java.lang.String r3 = "Upload has been suspended. Will update scheduling later in approximately ms"
            r1.zzb(r3, r2)
            com.google.android.gms.measurement.internal.zzhm r1 = r20.zzq()
            r1.zzc()
            com.google.android.gms.measurement.internal.zzoy r1 = r20.zzx()
            r1.zza()
            return
        L_0x004b:
            r0.zza = r3
        L_0x004d:
            com.google.android.gms.measurement.internal.zzio r1 = r0.zzn
            boolean r1 = r1.zzM()
            if (r1 == 0) goto L_0x024a
            boolean r1 = r20.zzaN()
            if (r1 != 0) goto L_0x005d
            goto L_0x024a
        L_0x005d:
            com.google.android.gms.common.util.Clock r1 = r20.zzaU()
            long r1 = r1.currentTimeMillis()
            r20.zzi()
            com.google.android.gms.measurement.internal.zzgg r5 = com.google.android.gms.measurement.internal.zzgi.zzN
            r6 = 0
            java.lang.Object r5 = r5.zza(r6)
            java.lang.Long r5 = (java.lang.Long) r5
            long r7 = r5.longValue()
            long r7 = java.lang.Math.max(r3, r7)
            com.google.android.gms.measurement.internal.zzaw r5 = r20.zzj()
            boolean r5 = r5.zzac()
            r10 = 1
            if (r5 != 0) goto L_0x0090
            com.google.android.gms.measurement.internal.zzaw r5 = r20.zzj()
            boolean r5 = r5.zzab()
            if (r5 == 0) goto L_0x008f
            goto L_0x0090
        L_0x008f:
            r10 = 0
        L_0x0090:
            if (r10 == 0) goto L_0x00d0
            com.google.android.gms.measurement.internal.zzam r5 = r20.zzi()
            java.lang.String r5 = r5.zzo()
            boolean r11 = android.text.TextUtils.isEmpty(r5)
            if (r11 != 0) goto L_0x00bc
            java.lang.String r11 = ".none."
            boolean r5 = r11.equals(r5)
            if (r5 != 0) goto L_0x00bc
            r20.zzi()
            com.google.android.gms.measurement.internal.zzgg r5 = com.google.android.gms.measurement.internal.zzgi.zzI
            java.lang.Object r5 = r5.zza(r6)
            java.lang.Long r5 = (java.lang.Long) r5
            long r11 = r5.longValue()
            long r11 = java.lang.Math.max(r3, r11)
            goto L_0x00e3
        L_0x00bc:
            r20.zzi()
            com.google.android.gms.measurement.internal.zzgg r5 = com.google.android.gms.measurement.internal.zzgi.zzH
            java.lang.Object r5 = r5.zza(r6)
            java.lang.Long r5 = (java.lang.Long) r5
            long r11 = r5.longValue()
            long r11 = java.lang.Math.max(r3, r11)
            goto L_0x00e3
        L_0x00d0:
            r20.zzi()
            com.google.android.gms.measurement.internal.zzgg r5 = com.google.android.gms.measurement.internal.zzgi.zzG
            java.lang.Object r5 = r5.zza(r6)
            java.lang.Long r5 = (java.lang.Long) r5
            long r11 = r5.longValue()
            long r11 = java.lang.Math.max(r3, r11)
        L_0x00e3:
            com.google.android.gms.measurement.internal.zzoa r5 = r0.zzk
            com.google.android.gms.measurement.internal.zzhp r5 = r5.zzd
            long r13 = r5.zza()
            com.google.android.gms.measurement.internal.zzoa r5 = r0.zzk
            com.google.android.gms.measurement.internal.zzhp r5 = r5.zze
            long r15 = r5.zza()
            com.google.android.gms.measurement.internal.zzaw r5 = r20.zzj()
            r17 = r10
            long r9 = r5.zzf()
            com.google.android.gms.measurement.internal.zzaw r5 = r20.zzj()
            r18 = r7
            long r6 = r5.zzh()
            long r5 = java.lang.Math.max(r9, r6)
            int r7 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r7 != 0) goto L_0x0112
        L_0x010f:
            r9 = r3
            goto L_0x0189
        L_0x0112:
            long r5 = r5 - r1
            long r5 = java.lang.Math.abs(r5)
            long r5 = r1 - r5
            long r13 = r13 - r1
            long r7 = java.lang.Math.abs(r13)
            long r7 = r1 - r7
            long r15 = r15 - r1
            long r9 = java.lang.Math.abs(r15)
            long r1 = r1 - r9
            long r9 = r5 + r18
            long r7 = java.lang.Math.max(r7, r1)
            if (r17 == 0) goto L_0x0137
            int r13 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r13 <= 0) goto L_0x0137
            long r9 = java.lang.Math.min(r5, r7)
            long r9 = r9 + r11
        L_0x0137:
            com.google.android.gms.measurement.internal.zzqa r13 = r20.zzA()
            boolean r13 = r13.zzz(r7, r11)
            if (r13 != 0) goto L_0x0143
            long r9 = r7 + r11
        L_0x0143:
            int r7 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r7 == 0) goto L_0x0189
            int r5 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r5 < 0) goto L_0x0189
            r5 = 0
        L_0x014c:
            r20.zzi()
            com.google.android.gms.measurement.internal.zzgg r6 = com.google.android.gms.measurement.internal.zzgi.zzP
            r7 = 0
            java.lang.Object r6 = r6.zza(r7)
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            r8 = 0
            int r6 = java.lang.Math.max(r8, r6)
            r11 = 20
            int r6 = java.lang.Math.min(r11, r6)
            if (r5 >= r6) goto L_0x010f
            r11 = 1
            long r11 = r11 << r5
            r20.zzi()
            com.google.android.gms.measurement.internal.zzgg r6 = com.google.android.gms.measurement.internal.zzgi.zzO
            java.lang.Object r6 = r6.zza(r7)
            java.lang.Long r6 = (java.lang.Long) r6
            long r6 = r6.longValue()
            long r6 = java.lang.Math.max(r3, r6)
            long r6 = r6 * r11
            long r9 = r9 + r6
            int r6 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r6 <= 0) goto L_0x0186
            goto L_0x0189
        L_0x0186:
            int r5 = r5 + 1
            goto L_0x014c
        L_0x0189:
            int r1 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x01a9
            com.google.android.gms.measurement.internal.zzhe r1 = r20.zzaW()
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zzj()
            java.lang.String r2 = "Next upload time is 0"
            r1.zza(r2)
            com.google.android.gms.measurement.internal.zzhm r1 = r20.zzq()
            r1.zzc()
            com.google.android.gms.measurement.internal.zzoy r1 = r20.zzx()
            r1.zza()
            return
        L_0x01a9:
            com.google.android.gms.measurement.internal.zzhk r1 = r20.zzp()
            boolean r1 = r1.zzd()
            if (r1 == 0) goto L_0x022e
            com.google.android.gms.measurement.internal.zzoa r1 = r0.zzk
            com.google.android.gms.measurement.internal.zzhp r1 = r1.zzc
            long r1 = r1.zza()
            r20.zzi()
            com.google.android.gms.measurement.internal.zzgg r5 = com.google.android.gms.measurement.internal.zzgi.zzE
            r6 = 0
            java.lang.Object r5 = r5.zza(r6)
            java.lang.Long r5 = (java.lang.Long) r5
            long r5 = r5.longValue()
            long r5 = java.lang.Math.max(r3, r5)
            com.google.android.gms.measurement.internal.zzqa r7 = r20.zzA()
            boolean r7 = r7.zzz(r1, r5)
            if (r7 != 0) goto L_0x01de
            long r1 = r1 + r5
            long r9 = java.lang.Math.max(r9, r1)
        L_0x01de:
            com.google.android.gms.measurement.internal.zzhm r1 = r20.zzq()
            r1.zzc()
            com.google.android.gms.common.util.Clock r1 = r20.zzaU()
            long r1 = r1.currentTimeMillis()
            long r9 = r9 - r1
            int r1 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r1 > 0) goto L_0x0215
            r20.zzi()
            com.google.android.gms.measurement.internal.zzgg r1 = com.google.android.gms.measurement.internal.zzgi.zzJ
            r2 = 0
            java.lang.Object r1 = r1.zza(r2)
            java.lang.Long r1 = (java.lang.Long) r1
            long r1 = r1.longValue()
            long r9 = java.lang.Math.max(r3, r1)
            com.google.android.gms.measurement.internal.zzoa r1 = r0.zzk
            com.google.android.gms.measurement.internal.zzhp r1 = r1.zzd
            com.google.android.gms.common.util.Clock r2 = r20.zzaU()
            long r2 = r2.currentTimeMillis()
            r1.zzb(r2)
        L_0x0215:
            com.google.android.gms.measurement.internal.zzhe r1 = r20.zzaW()
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zzj()
            java.lang.Long r2 = java.lang.Long.valueOf(r9)
            java.lang.String r3 = "Upload scheduled in approximately ms"
            r1.zzb(r3, r2)
            com.google.android.gms.measurement.internal.zzoy r1 = r20.zzx()
            r1.zzd(r9)
            return
        L_0x022e:
            com.google.android.gms.measurement.internal.zzhe r1 = r20.zzaW()
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zzj()
            java.lang.String r2 = "No network"
            r1.zza(r2)
            com.google.android.gms.measurement.internal.zzhm r1 = r20.zzq()
            r1.zzb()
            com.google.android.gms.measurement.internal.zzoy r1 = r20.zzx()
            r1.zza()
            return
        L_0x024a:
            com.google.android.gms.measurement.internal.zzhe r1 = r20.zzaW()
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zzj()
            java.lang.String r2 = "Nothing to upload or uploading impossible"
            r1.zza(r2)
            com.google.android.gms.measurement.internal.zzhm r1 = r20.zzq()
            r1.zzc()
            com.google.android.gms.measurement.internal.zzoy r1 = r20.zzx()
            r1.zza()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzpv.zzaL():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0136, code lost:
        if (r9.equals("ecommerce_purchase") == false) goto L_0x0171;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:458:0x0d65, code lost:
        if (r11 > (com.google.android.gms.measurement.internal.zzam.zzI() + r7)) goto L_0x0d67;
     */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x0400 A[Catch:{ NumberFormatException -> 0x09d5, all -> 0x00f5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x04c3 A[Catch:{ NumberFormatException -> 0x09d5, all -> 0x00f5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x050d A[Catch:{ NumberFormatException -> 0x09d5, all -> 0x00f5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x0568 A[Catch:{ NumberFormatException -> 0x09d5, all -> 0x00f5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:218:0x0681 A[Catch:{ NumberFormatException -> 0x09d5, all -> 0x00f5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:345:0x0a10 A[SYNTHETIC, Splitter:B:345:0x0a10] */
    /* JADX WARNING: Removed duplicated region for block: B:358:0x0a58 A[SYNTHETIC, Splitter:B:358:0x0a58] */
    /* JADX WARNING: Removed duplicated region for block: B:361:0x0a80 A[SYNTHETIC, Splitter:B:361:0x0a80] */
    /* JADX WARNING: Removed duplicated region for block: B:457:0x0d57 A[Catch:{ SQLiteException -> 0x0e0d, all -> 0x0cec }] */
    /* JADX WARNING: Removed duplicated region for block: B:466:0x0dd9 A[Catch:{ SQLiteException -> 0x0e0d, all -> 0x0cec }] */
    /* JADX WARNING: Removed duplicated region for block: B:470:0x0df7 A[Catch:{ SQLiteException -> 0x0e0d, all -> 0x0cec }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzaM(java.lang.String r46, long r47) {
        /*
            r45 = this;
            r1 = r45
            java.lang.String r4 = "_efs"
            java.lang.String r7 = "_ai"
            java.lang.String r8 = "purchase"
            java.lang.String r9 = "items"
            com.google.android.gms.measurement.internal.zzaw r10 = r45.zzj()
            r10.zzH()
            com.google.android.gms.measurement.internal.zzpr r10 = new com.google.android.gms.measurement.internal.zzpr     // Catch:{ all -> 0x00f5 }
            r15 = 0
            r10.<init>(r1, r15)     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzaw r11 = r45.zzj()     // Catch:{ all -> 0x00f5 }
            long r13 = r1.zzB     // Catch:{ all -> 0x00f5 }
            r12 = r46
            r16 = r13
            r13 = r47
            r2 = r15
            r15 = r16
            r17 = r10
            r11.zzat(r12, r13, r15, r17)     // Catch:{ all -> 0x00f5 }
            java.util.List r3 = r10.zzc     // Catch:{ all -> 0x00f5 }
            if (r3 == 0) goto L_0x0035
            boolean r3 = r3.isEmpty()     // Catch:{ all -> 0x00f5 }
            if (r3 == 0) goto L_0x0039
        L_0x0035:
            r4 = r1
            r6 = 0
            goto L_0x0e7c
        L_0x0039:
            com.google.android.gms.internal.measurement.zzhx r3 = r10.zza     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzlz r3 = r3.zzch()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhw r3 = (com.google.android.gms.internal.measurement.zzhw) r3     // Catch:{ all -> 0x00f5 }
            r3.zzu()     // Catch:{ all -> 0x00f5 }
            r12 = r2
            r15 = r12
            r11 = -1
            r13 = 0
            r14 = 0
            r16 = 0
            r17 = 0
            r18 = -1
        L_0x004f:
            java.util.List r5 = r10.zzc     // Catch:{ all -> 0x00f5 }
            int r5 = r5.size()     // Catch:{ all -> 0x00f5 }
            java.lang.String r6 = "_et"
            java.lang.String r2 = "_fr"
            r20 = r4
            java.lang.String r4 = "_e"
            r21 = r14
            r48 = r15
            if (r13 >= r5) goto L_0x06aa
            java.util.List r5 = r10.zzc     // Catch:{ all -> 0x00f5 }
            java.lang.Object r5 = r5.get(r13)     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhm r5 = (com.google.android.gms.internal.measurement.zzhm) r5     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzlz r5 = r5.zzch()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhl r5 = (com.google.android.gms.internal.measurement.zzhl) r5     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzif r14 = r45.zzr()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhx r15 = r10.zza     // Catch:{ all -> 0x00f5 }
            java.lang.String r15 = r15.zzF()     // Catch:{ all -> 0x00f5 }
            r24 = r13
            java.lang.String r13 = r5.zzo()     // Catch:{ all -> 0x00f5 }
            boolean r13 = r14.zzx(r15, r13)     // Catch:{ all -> 0x00f5 }
            java.lang.String r14 = "_err"
            if (r13 == 0) goto L_0x010a
            com.google.android.gms.measurement.internal.zzhe r2 = r45.zzaW()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zzk()     // Catch:{ all -> 0x00f5 }
            java.lang.String r4 = "Dropping blocked raw event. appId"
            com.google.android.gms.internal.measurement.zzhx r6 = r10.zza     // Catch:{ all -> 0x00f5 }
            java.lang.String r6 = r6.zzF()     // Catch:{ all -> 0x00f5 }
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzhe.zzn(r6)     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzio r13 = r1.zzn     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzgx r13 = r13.zzj()     // Catch:{ all -> 0x00f5 }
            java.lang.String r15 = r5.zzo()     // Catch:{ all -> 0x00f5 }
            java.lang.String r13 = r13.zzd(r15)     // Catch:{ all -> 0x00f5 }
            r2.zzc(r4, r6, r13)     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzif r2 = r45.zzr()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhx r4 = r10.zza     // Catch:{ all -> 0x00f5 }
            java.lang.String r4 = r4.zzF()     // Catch:{ all -> 0x00f5 }
            boolean r2 = r2.zzt(r4)     // Catch:{ all -> 0x00f5 }
            if (r2 != 0) goto L_0x00fa
            com.google.android.gms.measurement.internal.zzif r2 = r45.zzr()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhx r4 = r10.zza     // Catch:{ all -> 0x00f5 }
            java.lang.String r4 = r4.zzF()     // Catch:{ all -> 0x00f5 }
            boolean r2 = r2.zzy(r4)     // Catch:{ all -> 0x00f5 }
            if (r2 == 0) goto L_0x00cf
            goto L_0x00fa
        L_0x00cf:
            java.lang.String r2 = r5.zzo()     // Catch:{ all -> 0x00f5 }
            boolean r2 = r14.equals(r2)     // Catch:{ all -> 0x00f5 }
            if (r2 != 0) goto L_0x00fa
            com.google.android.gms.measurement.internal.zzqf r25 = r45.zzB()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzqe r2 = r1.zzK     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhx r4 = r10.zza     // Catch:{ all -> 0x00f5 }
            java.lang.String r27 = r4.zzF()     // Catch:{ all -> 0x00f5 }
            java.lang.String r29 = "_ev"
            java.lang.String r30 = r5.zzo()     // Catch:{ all -> 0x00f5 }
            r31 = 0
            r28 = 11
            r26 = r2
            r25.zzR(r26, r27, r28, r29, r30, r31)     // Catch:{ all -> 0x00f5 }
            goto L_0x00fa
        L_0x00f5:
            r0 = move-exception
            r4 = r1
        L_0x00f7:
            r1 = r0
            goto L_0x0e8b
        L_0x00fa:
            r15 = r48
            r27 = r7
            r28 = r8
            r25 = r11
            r14 = r21
            r11 = r24
            r2 = 1
            r7 = r3
            goto L_0x069c
        L_0x010a:
            com.google.android.gms.internal.measurement.zzoy.zzb()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzam r13 = r45.zzi()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzgg r15 = com.google.android.gms.measurement.internal.zzgi.zzbf     // Catch:{ all -> 0x00f5 }
            r25 = r9
            r9 = 0
            boolean r13 = r13.zzx(r9, r15)     // Catch:{ all -> 0x00f5 }
            if (r13 == 0) goto L_0x016f
            java.lang.String r9 = r5.zzo()     // Catch:{ all -> 0x00f5 }
            boolean r13 = r9.equals(r8)     // Catch:{ all -> 0x00f5 }
            java.lang.String r15 = "ecommerce_purchase"
            r26 = r11
            java.lang.String r11 = "_iap"
            if (r13 != 0) goto L_0x0138
            boolean r13 = r9.equals(r11)     // Catch:{ all -> 0x00f5 }
            if (r13 != 0) goto L_0x0138
            boolean r9 = r9.equals(r15)     // Catch:{ all -> 0x00f5 }
            if (r9 == 0) goto L_0x0171
        L_0x0138:
            com.google.android.gms.internal.measurement.zzhp r9 = com.google.android.gms.internal.measurement.zzhq.zze()     // Catch:{ all -> 0x00f5 }
            java.lang.String r13 = "_cbs"
            r9.zzj(r13)     // Catch:{ all -> 0x00f5 }
            if (r16 != 0) goto L_0x015e
            com.google.android.gms.internal.measurement.zzhx r13 = r10.zza     // Catch:{ all -> 0x00f5 }
            java.lang.String r13 = r13.zzF()     // Catch:{ all -> 0x00f5 }
            boolean r16 = r1.zzaO(r13, r8)     // Catch:{ all -> 0x00f5 }
            if (r16 == 0) goto L_0x015e
            boolean r11 = r1.zzaO(r13, r11)     // Catch:{ all -> 0x00f5 }
            if (r11 == 0) goto L_0x015e
            boolean r11 = r1.zzaO(r13, r15)     // Catch:{ all -> 0x00f5 }
            if (r11 == 0) goto L_0x015e
            java.lang.String r11 = "new_buyer"
            goto L_0x0160
        L_0x015e:
            java.lang.String r11 = "returning_buyer"
        L_0x0160:
            r9.zzk(r11)     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzmd r9 = r9.zzba()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhq r9 = (com.google.android.gms.internal.measurement.zzhq) r9     // Catch:{ all -> 0x00f5 }
            r5.zzf(r9)     // Catch:{ all -> 0x00f5 }
            r16 = 1
            goto L_0x0171
        L_0x016f:
            r26 = r11
        L_0x0171:
            java.lang.String r9 = r5.zzo()     // Catch:{ all -> 0x00f5 }
            java.lang.String r11 = com.google.android.gms.measurement.internal.zzjy.zza(r7)     // Catch:{ all -> 0x00f5 }
            boolean r9 = r9.equals(r11)     // Catch:{ all -> 0x00f5 }
            if (r9 == 0) goto L_0x01e3
            r5.zzi(r7)     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzhe r9 = r45.zzaW()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzhc r9 = r9.zzj()     // Catch:{ all -> 0x00f5 }
            java.lang.String r11 = "Renaming ad_impression to _ai"
            r9.zza(r11)     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzhe r9 = r45.zzaW()     // Catch:{ all -> 0x00f5 }
            java.lang.String r9 = r9.zzr()     // Catch:{ all -> 0x00f5 }
            r11 = 5
            boolean r9 = android.util.Log.isLoggable(r9, r11)     // Catch:{ all -> 0x00f5 }
            if (r9 == 0) goto L_0x01e3
            r9 = 0
        L_0x019f:
            int r11 = r5.zza()     // Catch:{ all -> 0x00f5 }
            if (r9 >= r11) goto L_0x01e3
            java.lang.String r11 = "ad_platform"
            com.google.android.gms.internal.measurement.zzhq r13 = r5.zzn(r9)     // Catch:{ all -> 0x00f5 }
            java.lang.String r13 = r13.zzg()     // Catch:{ all -> 0x00f5 }
            boolean r11 = r11.equals(r13)     // Catch:{ all -> 0x00f5 }
            if (r11 == 0) goto L_0x01e0
            com.google.android.gms.internal.measurement.zzhq r11 = r5.zzn(r9)     // Catch:{ all -> 0x00f5 }
            java.lang.String r11 = r11.zzh()     // Catch:{ all -> 0x00f5 }
            boolean r11 = r11.isEmpty()     // Catch:{ all -> 0x00f5 }
            if (r11 != 0) goto L_0x01e0
            java.lang.String r11 = "admob"
            com.google.android.gms.internal.measurement.zzhq r13 = r5.zzn(r9)     // Catch:{ all -> 0x00f5 }
            java.lang.String r13 = r13.zzh()     // Catch:{ all -> 0x00f5 }
            boolean r11 = r11.equalsIgnoreCase(r13)     // Catch:{ all -> 0x00f5 }
            if (r11 == 0) goto L_0x01e0
            com.google.android.gms.measurement.internal.zzhe r11 = r45.zzaW()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzhc r11 = r11.zzl()     // Catch:{ all -> 0x00f5 }
            java.lang.String r13 = "AdMob ad impression logged from app. Potentially duplicative."
            r11.zza(r13)     // Catch:{ all -> 0x00f5 }
        L_0x01e0:
            r11 = 1
            int r9 = r9 + r11
            goto L_0x019f
        L_0x01e3:
            com.google.android.gms.measurement.internal.zzif r9 = r45.zzr()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhx r11 = r10.zza     // Catch:{ all -> 0x00f5 }
            java.lang.String r11 = r11.zzF()     // Catch:{ all -> 0x00f5 }
            java.lang.String r13 = r5.zzo()     // Catch:{ all -> 0x00f5 }
            boolean r9 = r9.zzw(r11, r13)     // Catch:{ all -> 0x00f5 }
            java.lang.String r11 = "_c"
            if (r9 != 0) goto L_0x0228
            r45.zzA()     // Catch:{ all -> 0x00f5 }
            java.lang.String r13 = r5.zzo()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r13)     // Catch:{ all -> 0x00f5 }
            int r15 = r13.hashCode()     // Catch:{ all -> 0x00f5 }
            r27 = r7
            r7 = 95027(0x17333, float:1.33161E-40)
            if (r15 == r7) goto L_0x020f
            goto L_0x021d
        L_0x020f:
            java.lang.String r7 = "_ui"
            boolean r7 = r13.equals(r7)
            if (r7 == 0) goto L_0x021d
        L_0x0217:
            r28 = r8
            r7 = 0
            r13 = 0
            r15 = 0
            goto L_0x022b
        L_0x021d:
            r30 = r2
            r7 = r3
            r29 = r6
            r28 = r8
            r8 = r12
            r9 = 0
            goto L_0x03fe
        L_0x0228:
            r27 = r7
            goto L_0x0217
        L_0x022b:
            int r8 = r5.zza()     // Catch:{ all -> 0x00f5 }
            r29 = r6
            java.lang.String r6 = "_r"
            if (r7 >= r8) goto L_0x029b
            com.google.android.gms.internal.measurement.zzhq r8 = r5.zzn(r7)     // Catch:{ all -> 0x00f5 }
            java.lang.String r8 = r8.zzg()     // Catch:{ all -> 0x00f5 }
            boolean r8 = r11.equals(r8)     // Catch:{ all -> 0x00f5 }
            if (r8 == 0) goto L_0x0262
            com.google.android.gms.internal.measurement.zzhq r6 = r5.zzn(r7)     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzlz r6 = r6.zzch()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhp r6 = (com.google.android.gms.internal.measurement.zzhp) r6     // Catch:{ all -> 0x00f5 }
            r8 = r12
            r12 = 1
            r6.zzi(r12)     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzmd r6 = r6.zzba()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhq r6 = (com.google.android.gms.internal.measurement.zzhq) r6     // Catch:{ all -> 0x00f5 }
            r5.zzk(r7, r6)     // Catch:{ all -> 0x00f5 }
            r30 = r2
            r12 = r3
            r2 = 1
            r13 = 1
            goto L_0x0293
        L_0x0262:
            r8 = r12
            com.google.android.gms.internal.measurement.zzhq r12 = r5.zzn(r7)     // Catch:{ all -> 0x00f5 }
            java.lang.String r12 = r12.zzg()     // Catch:{ all -> 0x00f5 }
            boolean r6 = r6.equals(r12)     // Catch:{ all -> 0x00f5 }
            if (r6 == 0) goto L_0x028f
            com.google.android.gms.internal.measurement.zzhq r6 = r5.zzn(r7)     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzlz r6 = r6.zzch()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhp r6 = (com.google.android.gms.internal.measurement.zzhp) r6     // Catch:{ all -> 0x00f5 }
            r30 = r2
            r12 = r3
            r2 = 1
            r6.zzi(r2)     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzmd r2 = r6.zzba()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhq r2 = (com.google.android.gms.internal.measurement.zzhq) r2     // Catch:{ all -> 0x00f5 }
            r5.zzk(r7, r2)     // Catch:{ all -> 0x00f5 }
            r2 = 1
            r15 = 1
            goto L_0x0293
        L_0x028f:
            r30 = r2
            r12 = r3
            r2 = 1
        L_0x0293:
            int r7 = r7 + r2
            r3 = r12
            r6 = r29
            r2 = r30
            r12 = r8
            goto L_0x022b
        L_0x029b:
            r30 = r2
            r8 = r12
            r12 = r3
            if (r13 != 0) goto L_0x02cf
            if (r9 == 0) goto L_0x02cf
            com.google.android.gms.measurement.internal.zzhe r2 = r45.zzaW()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zzj()     // Catch:{ all -> 0x00f5 }
            java.lang.String r3 = "Marking event as conversion"
            com.google.android.gms.measurement.internal.zzio r7 = r1.zzn     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzgx r7 = r7.zzj()     // Catch:{ all -> 0x00f5 }
            java.lang.String r13 = r5.zzo()     // Catch:{ all -> 0x00f5 }
            java.lang.String r7 = r7.zzd(r13)     // Catch:{ all -> 0x00f5 }
            r2.zzb(r3, r7)     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhp r2 = com.google.android.gms.internal.measurement.zzhq.zze()     // Catch:{ all -> 0x00f5 }
            r2.zzj(r11)     // Catch:{ all -> 0x00f5 }
            r3 = r12
            r12 = 1
            r2.zzi(r12)     // Catch:{ all -> 0x00f5 }
            r5.zze(r2)     // Catch:{ all -> 0x00f5 }
            goto L_0x02d0
        L_0x02cf:
            r3 = r12
        L_0x02d0:
            if (r15 != 0) goto L_0x02fc
            com.google.android.gms.measurement.internal.zzhe r2 = r45.zzaW()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zzj()     // Catch:{ all -> 0x00f5 }
            java.lang.String r7 = "Marking event as real-time"
            com.google.android.gms.measurement.internal.zzio r12 = r1.zzn     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzgx r12 = r12.zzj()     // Catch:{ all -> 0x00f5 }
            java.lang.String r13 = r5.zzo()     // Catch:{ all -> 0x00f5 }
            java.lang.String r12 = r12.zzd(r13)     // Catch:{ all -> 0x00f5 }
            r2.zzb(r7, r12)     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhp r2 = com.google.android.gms.internal.measurement.zzhq.zze()     // Catch:{ all -> 0x00f5 }
            r2.zzj(r6)     // Catch:{ all -> 0x00f5 }
            r12 = 1
            r2.zzi(r12)     // Catch:{ all -> 0x00f5 }
            r5.zze(r2)     // Catch:{ all -> 0x00f5 }
        L_0x02fc:
            com.google.android.gms.measurement.internal.zzaw r31 = r45.zzj()     // Catch:{ all -> 0x00f5 }
            long r32 = r45.zza()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhx r2 = r10.zza     // Catch:{ all -> 0x00f5 }
            java.lang.String r34 = r2.zzF()     // Catch:{ all -> 0x00f5 }
            r40 = 0
            r41 = 0
            r35 = 0
            r36 = 0
            r37 = 0
            r38 = 0
            r39 = 1
            com.google.android.gms.measurement.internal.zzas r2 = r31.zzo(r32, r34, r35, r36, r37, r38, r39, r40, r41)     // Catch:{ all -> 0x00f5 }
            long r12 = r2.zze     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzam r2 = r45.zzi()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhx r7 = r10.zza     // Catch:{ all -> 0x00f5 }
            java.lang.String r7 = r7.zzF()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzgg r15 = com.google.android.gms.measurement.internal.zzgi.zzo     // Catch:{ all -> 0x00f5 }
            int r2 = r2.zzh(r7, r15)     // Catch:{ all -> 0x00f5 }
            r7 = r3
            long r2 = (long) r2     // Catch:{ all -> 0x00f5 }
            int r2 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r2 <= 0) goto L_0x0338
            zzaB(r5, r6)     // Catch:{ all -> 0x00f5 }
            goto L_0x033a
        L_0x0338:
            r17 = 1
        L_0x033a:
            java.lang.String r2 = r5.zzo()     // Catch:{ all -> 0x00f5 }
            boolean r2 = com.google.android.gms.measurement.internal.zzqf.zzaq(r2)     // Catch:{ all -> 0x00f5 }
            if (r2 == 0) goto L_0x03fe
            if (r9 == 0) goto L_0x03fe
            com.google.android.gms.measurement.internal.zzaw r31 = r45.zzj()     // Catch:{ all -> 0x00f5 }
            long r32 = r45.zza()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhx r2 = r10.zza     // Catch:{ all -> 0x00f5 }
            java.lang.String r34 = r2.zzF()     // Catch:{ all -> 0x00f5 }
            r40 = 0
            r41 = 0
            r35 = 0
            r36 = 0
            r37 = 1
            r38 = 0
            r39 = 0
            com.google.android.gms.measurement.internal.zzas r2 = r31.zzo(r32, r34, r35, r36, r37, r38, r39, r40, r41)     // Catch:{ all -> 0x00f5 }
            long r2 = r2.zzc     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzam r6 = r45.zzi()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhx r12 = r10.zza     // Catch:{ all -> 0x00f5 }
            java.lang.String r12 = r12.zzF()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzgg r13 = com.google.android.gms.measurement.internal.zzgi.zzn     // Catch:{ all -> 0x00f5 }
            int r6 = r6.zzh(r12, r13)     // Catch:{ all -> 0x00f5 }
            long r12 = (long) r6     // Catch:{ all -> 0x00f5 }
            int r2 = (r2 > r12 ? 1 : (r2 == r12 ? 0 : -1))
            if (r2 <= 0) goto L_0x03fe
            com.google.android.gms.measurement.internal.zzhe r2 = r45.zzaW()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zzk()     // Catch:{ all -> 0x00f5 }
            java.lang.String r3 = "Too many conversions. Not logging as conversion. appId"
            com.google.android.gms.internal.measurement.zzhx r6 = r10.zza     // Catch:{ all -> 0x00f5 }
            java.lang.String r6 = r6.zzF()     // Catch:{ all -> 0x00f5 }
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzhe.zzn(r6)     // Catch:{ all -> 0x00f5 }
            r2.zzb(r3, r6)     // Catch:{ all -> 0x00f5 }
            r2 = 0
            r3 = 0
            r6 = -1
            r15 = 0
        L_0x0398:
            int r12 = r5.zza()     // Catch:{ all -> 0x00f5 }
            if (r2 >= r12) goto L_0x03c4
            com.google.android.gms.internal.measurement.zzhq r12 = r5.zzn(r2)     // Catch:{ all -> 0x00f5 }
            java.lang.String r13 = r12.zzg()     // Catch:{ all -> 0x00f5 }
            boolean r13 = r11.equals(r13)     // Catch:{ all -> 0x00f5 }
            if (r13 == 0) goto L_0x03b6
            com.google.android.gms.internal.measurement.zzlz r6 = r12.zzch()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhp r6 = (com.google.android.gms.internal.measurement.zzhp) r6     // Catch:{ all -> 0x00f5 }
            r15 = r6
            r12 = 1
            r6 = r2
            goto L_0x03c2
        L_0x03b6:
            java.lang.String r12 = r12.zzg()     // Catch:{ all -> 0x00f5 }
            boolean r12 = r14.equals(r12)     // Catch:{ all -> 0x00f5 }
            if (r12 == 0) goto L_0x03c1
            r3 = 1
        L_0x03c1:
            r12 = 1
        L_0x03c2:
            int r2 = r2 + r12
            goto L_0x0398
        L_0x03c4:
            if (r3 == 0) goto L_0x03cd
            if (r15 == 0) goto L_0x03cc
            r5.zzh(r6)     // Catch:{ all -> 0x00f5 }
            goto L_0x03fe
        L_0x03cc:
            r15 = 0
        L_0x03cd:
            if (r15 == 0) goto L_0x03e7
            com.google.android.gms.internal.measurement.zzlz r2 = r15.zzaR()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhp r2 = (com.google.android.gms.internal.measurement.zzhp) r2     // Catch:{ all -> 0x00f5 }
            r2.zzj(r14)     // Catch:{ all -> 0x00f5 }
            r12 = 10
            r2.zzi(r12)     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzmd r2 = r2.zzba()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhq r2 = (com.google.android.gms.internal.measurement.zzhq) r2     // Catch:{ all -> 0x00f5 }
            r5.zzk(r6, r2)     // Catch:{ all -> 0x00f5 }
            goto L_0x03fe
        L_0x03e7:
            com.google.android.gms.measurement.internal.zzhe r2 = r45.zzaW()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zze()     // Catch:{ all -> 0x00f5 }
            java.lang.String r3 = "Did not find conversion parameter. appId"
            com.google.android.gms.internal.measurement.zzhx r6 = r10.zza     // Catch:{ all -> 0x00f5 }
            java.lang.String r6 = r6.zzF()     // Catch:{ all -> 0x00f5 }
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzhe.zzn(r6)     // Catch:{ all -> 0x00f5 }
            r2.zzb(r3, r6)     // Catch:{ all -> 0x00f5 }
        L_0x03fe:
            if (r9 == 0) goto L_0x04b7
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x00f5 }
            java.util.List r3 = r5.zzp()     // Catch:{ all -> 0x00f5 }
            r2.<init>(r3)     // Catch:{ all -> 0x00f5 }
            r3 = 0
            r6 = -1
            r9 = -1
        L_0x040c:
            int r12 = r2.size()     // Catch:{ all -> 0x00f5 }
            java.lang.String r13 = "currency"
            java.lang.String r14 = "value"
            if (r3 >= r12) goto L_0x043d
            java.lang.Object r12 = r2.get(r3)     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhq r12 = (com.google.android.gms.internal.measurement.zzhq) r12     // Catch:{ all -> 0x00f5 }
            java.lang.String r12 = r12.zzg()     // Catch:{ all -> 0x00f5 }
            boolean r12 = r14.equals(r12)     // Catch:{ all -> 0x00f5 }
            if (r12 == 0) goto L_0x0429
            r6 = r3
        L_0x0427:
            r12 = 1
            goto L_0x043b
        L_0x0429:
            java.lang.Object r12 = r2.get(r3)     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhq r12 = (com.google.android.gms.internal.measurement.zzhq) r12     // Catch:{ all -> 0x00f5 }
            java.lang.String r12 = r12.zzg()     // Catch:{ all -> 0x00f5 }
            boolean r12 = r13.equals(r12)     // Catch:{ all -> 0x00f5 }
            if (r12 == 0) goto L_0x0427
            r9 = r3
            goto L_0x0427
        L_0x043b:
            int r3 = r3 + r12
            goto L_0x040c
        L_0x043d:
            r3 = -1
            if (r6 != r3) goto L_0x0442
            goto L_0x04b7
        L_0x0442:
            java.lang.Object r3 = r2.get(r6)     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhq r3 = (com.google.android.gms.internal.measurement.zzhq) r3     // Catch:{ all -> 0x00f5 }
            boolean r3 = r3.zzw()     // Catch:{ all -> 0x00f5 }
            if (r3 != 0) goto L_0x0473
            java.lang.Object r3 = r2.get(r6)     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhq r3 = (com.google.android.gms.internal.measurement.zzhq) r3     // Catch:{ all -> 0x00f5 }
            boolean r3 = r3.zzu()     // Catch:{ all -> 0x00f5 }
            if (r3 != 0) goto L_0x0473
            com.google.android.gms.measurement.internal.zzhe r2 = r45.zzaW()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zzl()     // Catch:{ all -> 0x00f5 }
            java.lang.String r3 = "Value must be specified with a numeric type."
            r2.zza(r3)     // Catch:{ all -> 0x00f5 }
            r5.zzh(r6)     // Catch:{ all -> 0x00f5 }
            zzaB(r5, r11)     // Catch:{ all -> 0x00f5 }
            r2 = 18
            zzaA(r5, r2, r14)     // Catch:{ all -> 0x00f5 }
            goto L_0x04b7
        L_0x0473:
            r3 = -1
            if (r9 != r3) goto L_0x0477
            goto L_0x049f
        L_0x0477:
            java.lang.Object r2 = r2.get(r9)     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhq r2 = (com.google.android.gms.internal.measurement.zzhq) r2     // Catch:{ all -> 0x00f5 }
            java.lang.String r2 = r2.zzh()     // Catch:{ all -> 0x00f5 }
            int r3 = r2.length()     // Catch:{ all -> 0x00f5 }
            r9 = 3
            if (r3 != r9) goto L_0x049f
            r3 = 0
        L_0x0489:
            int r9 = r2.length()     // Catch:{ all -> 0x00f5 }
            if (r3 >= r9) goto L_0x04b7
            int r9 = r2.codePointAt(r3)     // Catch:{ all -> 0x00f5 }
            boolean r12 = java.lang.Character.isLetter(r9)     // Catch:{ all -> 0x00f5 }
            if (r12 == 0) goto L_0x049f
            int r9 = java.lang.Character.charCount(r9)     // Catch:{ all -> 0x00f5 }
            int r3 = r3 + r9
            goto L_0x0489
        L_0x049f:
            com.google.android.gms.measurement.internal.zzhe r2 = r45.zzaW()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zzl()     // Catch:{ all -> 0x00f5 }
            java.lang.String r3 = "Value parameter discarded. You must also supply a 3-letter ISO_4217 currency code in the currency parameter."
            r2.zza(r3)     // Catch:{ all -> 0x00f5 }
            r5.zzh(r6)     // Catch:{ all -> 0x00f5 }
            zzaB(r5, r11)     // Catch:{ all -> 0x00f5 }
            r2 = 19
            zzaA(r5, r2, r13)     // Catch:{ all -> 0x00f5 }
        L_0x04b7:
            java.lang.String r2 = r5.zzo()     // Catch:{ all -> 0x00f5 }
            boolean r2 = r4.equals(r2)     // Catch:{ all -> 0x00f5 }
            r3 = 1000(0x3e8, double:4.94E-321)
            if (r2 == 0) goto L_0x050d
            r45.zzA()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzmd r2 = r5.zzba()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhm r2 = (com.google.android.gms.internal.measurement.zzhm) r2     // Catch:{ all -> 0x00f5 }
            r6 = r30
            com.google.android.gms.internal.measurement.zzhq r2 = com.google.android.gms.measurement.internal.zzqa.zzG(r2, r6)     // Catch:{ all -> 0x00f5 }
            if (r2 != 0) goto L_0x0508
            if (r8 == 0) goto L_0x0500
            long r11 = r8.zzc()     // Catch:{ all -> 0x00f5 }
            long r13 = r5.zzc()     // Catch:{ all -> 0x00f5 }
            long r11 = r11 - r13
            long r11 = java.lang.Math.abs(r11)     // Catch:{ all -> 0x00f5 }
            int r2 = (r11 > r3 ? 1 : (r11 == r3 ? 0 : -1))
            if (r2 > 0) goto L_0x0500
            com.google.android.gms.internal.measurement.zzlz r2 = r8.zzaR()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhl r2 = (com.google.android.gms.internal.measurement.zzhl) r2     // Catch:{ all -> 0x00f5 }
            boolean r3 = r1.zzaP(r5, r2)     // Catch:{ all -> 0x00f5 }
            if (r3 == 0) goto L_0x0500
            r6 = r18
            r7.zzad(r6, r2)     // Catch:{ all -> 0x00f5 }
            r18 = r6
            r11 = r26
        L_0x04fc:
            r8 = 0
            r15 = 0
            goto L_0x0562
        L_0x0500:
            r6 = r18
            r15 = r5
            r18 = r6
            r11 = r21
            goto L_0x0562
        L_0x0508:
            r6 = r18
        L_0x050a:
            r3 = r26
            goto L_0x055d
        L_0x050d:
            r6 = r18
            java.lang.String r2 = "_vs"
            java.lang.String r9 = r5.zzo()     // Catch:{ all -> 0x00f5 }
            boolean r2 = r2.equals(r9)     // Catch:{ all -> 0x00f5 }
            if (r2 == 0) goto L_0x050a
            r45.zzA()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzmd r2 = r5.zzba()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhm r2 = (com.google.android.gms.internal.measurement.zzhm) r2     // Catch:{ all -> 0x00f5 }
            r9 = r29
            com.google.android.gms.internal.measurement.zzhq r2 = com.google.android.gms.measurement.internal.zzqa.zzG(r2, r9)     // Catch:{ all -> 0x00f5 }
            if (r2 != 0) goto L_0x050a
            if (r48 == 0) goto L_0x0554
            long r8 = r48.zzc()     // Catch:{ all -> 0x00f5 }
            long r11 = r5.zzc()     // Catch:{ all -> 0x00f5 }
            long r8 = r8 - r11
            long r8 = java.lang.Math.abs(r8)     // Catch:{ all -> 0x00f5 }
            int r2 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r2 > 0) goto L_0x0554
            com.google.android.gms.internal.measurement.zzlz r2 = r48.zzaR()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhl r2 = (com.google.android.gms.internal.measurement.zzhl) r2     // Catch:{ all -> 0x00f5 }
            boolean r3 = r1.zzaP(r2, r5)     // Catch:{ all -> 0x00f5 }
            if (r3 == 0) goto L_0x0554
            r3 = r26
            r7.zzad(r3, r2)     // Catch:{ all -> 0x00f5 }
            r11 = r3
            r18 = r6
            goto L_0x04fc
        L_0x0554:
            r3 = r26
            r15 = r48
            r11 = r3
            r8 = r5
            r18 = r21
            goto L_0x0562
        L_0x055d:
            r15 = r48
            r11 = r3
            r18 = r6
        L_0x0562:
            int r2 = r5.zza()     // Catch:{ all -> 0x00f5 }
            if (r2 == 0) goto L_0x0681
            r45.zzA()     // Catch:{ all -> 0x00f5 }
            java.util.List r2 = r5.zzp()     // Catch:{ all -> 0x00f5 }
            android.os.Bundle r2 = com.google.android.gms.measurement.internal.zzqa.zzF(r2)     // Catch:{ all -> 0x00f5 }
            r3 = 0
        L_0x0574:
            int r4 = r5.zza()     // Catch:{ all -> 0x00f5 }
            if (r3 >= r4) goto L_0x062d
            com.google.android.gms.internal.measurement.zzhq r4 = r5.zzn(r3)     // Catch:{ all -> 0x00f5 }
            java.lang.String r6 = r4.zzg()     // Catch:{ all -> 0x00f5 }
            r9 = r25
            boolean r6 = r6.equals(r9)     // Catch:{ all -> 0x00f5 }
            if (r6 == 0) goto L_0x0602
            java.util.List r6 = r4.zzi()     // Catch:{ all -> 0x00f5 }
            boolean r6 = r6.isEmpty()     // Catch:{ all -> 0x00f5 }
            if (r6 != 0) goto L_0x0602
            com.google.android.gms.internal.measurement.zzhx r6 = r10.zza     // Catch:{ all -> 0x00f5 }
            java.lang.String r6 = r6.zzF()     // Catch:{ all -> 0x00f5 }
            java.util.List r4 = r4.zzi()     // Catch:{ all -> 0x00f5 }
            int r12 = r4.size()     // Catch:{ all -> 0x00f5 }
            android.os.Bundle[] r12 = new android.os.Bundle[r12]     // Catch:{ all -> 0x00f5 }
            r13 = 0
        L_0x05a5:
            int r14 = r4.size()     // Catch:{ all -> 0x00f5 }
            if (r13 >= r14) goto L_0x05f9
            java.lang.Object r14 = r4.get(r13)     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhq r14 = (com.google.android.gms.internal.measurement.zzhq) r14     // Catch:{ all -> 0x00f5 }
            r45.zzA()     // Catch:{ all -> 0x00f5 }
            java.util.List r22 = r14.zzi()     // Catch:{ all -> 0x00f5 }
            r48 = r4
            android.os.Bundle r4 = com.google.android.gms.measurement.internal.zzqa.zzF(r22)     // Catch:{ all -> 0x00f5 }
            java.util.List r14 = r14.zzi()     // Catch:{ all -> 0x00f5 }
            java.util.Iterator r14 = r14.iterator()     // Catch:{ all -> 0x00f5 }
        L_0x05c6:
            boolean r22 = r14.hasNext()     // Catch:{ all -> 0x00f5 }
            if (r22 == 0) goto L_0x05ea
            java.lang.Object r22 = r14.next()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhq r22 = (com.google.android.gms.internal.measurement.zzhq) r22     // Catch:{ all -> 0x00f5 }
            r23 = r8
            java.lang.String r8 = r5.zzo()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzlz r22 = r22.zzch()     // Catch:{ all -> 0x00f5 }
            r25 = r11
            r11 = r22
            com.google.android.gms.internal.measurement.zzhp r11 = (com.google.android.gms.internal.measurement.zzhp) r11     // Catch:{ all -> 0x00f5 }
            r1.zzaw(r8, r11, r4, r6)     // Catch:{ all -> 0x00f5 }
            r8 = r23
            r11 = r25
            goto L_0x05c6
        L_0x05ea:
            r23 = r8
            r25 = r11
            r12[r13] = r4     // Catch:{ all -> 0x00f5 }
            r4 = 1
            int r13 = r13 + r4
            r4 = r48
            r8 = r23
            r11 = r25
            goto L_0x05a5
        L_0x05f9:
            r23 = r8
            r25 = r11
            r2.putParcelableArray(r9, r12)     // Catch:{ all -> 0x00f5 }
        L_0x0600:
            r4 = 1
            goto L_0x0624
        L_0x0602:
            r23 = r8
            r25 = r11
            java.lang.String r6 = r4.zzg()     // Catch:{ all -> 0x00f5 }
            boolean r6 = r6.equals(r9)     // Catch:{ all -> 0x00f5 }
            if (r6 != 0) goto L_0x0600
            java.lang.String r6 = r5.zzo()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzlz r4 = r4.zzch()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhp r4 = (com.google.android.gms.internal.measurement.zzhp) r4     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhx r8 = r10.zza     // Catch:{ all -> 0x00f5 }
            java.lang.String r8 = r8.zzF()     // Catch:{ all -> 0x00f5 }
            r1.zzaw(r6, r4, r2, r8)     // Catch:{ all -> 0x00f5 }
            goto L_0x0600
        L_0x0624:
            int r3 = r3 + r4
            r8 = r23
            r11 = r25
            r25 = r9
            goto L_0x0574
        L_0x062d:
            r23 = r8
            r9 = r25
            r25 = r11
            r5.zzg()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzqa r3 = r45.zzA()     // Catch:{ all -> 0x00f5 }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x00f5 }
            r4.<init>()     // Catch:{ all -> 0x00f5 }
            java.util.Set r6 = r2.keySet()     // Catch:{ all -> 0x00f5 }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ all -> 0x00f5 }
        L_0x0647:
            boolean r8 = r6.hasNext()     // Catch:{ all -> 0x00f5 }
            if (r8 == 0) goto L_0x066d
            java.lang.Object r8 = r6.next()     // Catch:{ all -> 0x00f5 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhp r11 = com.google.android.gms.internal.measurement.zzhq.zze()     // Catch:{ all -> 0x00f5 }
            r11.zzj(r8)     // Catch:{ all -> 0x00f5 }
            java.lang.Object r8 = r2.get(r8)     // Catch:{ all -> 0x00f5 }
            if (r8 == 0) goto L_0x0647
            r3.zzw(r11, r8)     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzmd r8 = r11.zzba()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhq r8 = (com.google.android.gms.internal.measurement.zzhq) r8     // Catch:{ all -> 0x00f5 }
            r4.add(r8)     // Catch:{ all -> 0x00f5 }
            goto L_0x0647
        L_0x066d:
            java.util.Iterator r2 = r4.iterator()     // Catch:{ all -> 0x00f5 }
        L_0x0671:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x00f5 }
            if (r3 == 0) goto L_0x0687
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhq r3 = (com.google.android.gms.internal.measurement.zzhq) r3     // Catch:{ all -> 0x00f5 }
            r5.zzf(r3)     // Catch:{ all -> 0x00f5 }
            goto L_0x0671
        L_0x0681:
            r23 = r8
            r9 = r25
            r25 = r11
        L_0x0687:
            java.util.List r2 = r10.zzc     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzmd r3 = r5.zzba()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhm r3 = (com.google.android.gms.internal.measurement.zzhm) r3     // Catch:{ all -> 0x00f5 }
            r11 = r24
            r2.set(r11, r3)     // Catch:{ all -> 0x00f5 }
            r7.zzn(r5)     // Catch:{ all -> 0x00f5 }
            r2 = 1
            int r14 = r21 + 1
            r12 = r23
        L_0x069c:
            int r13 = r11 + 1
            r3 = r7
            r4 = r20
            r11 = r25
            r7 = r27
            r8 = r28
            r2 = 0
            goto L_0x004f
        L_0x06aa:
            r7 = r3
            r9 = r6
            r6 = r2
            r2 = 0
            r11 = r2
            r14 = r21
            r5 = 0
        L_0x06b3:
            if (r5 >= r14) goto L_0x0700
            com.google.android.gms.internal.measurement.zzhm r8 = r7.zzh(r5)     // Catch:{ all -> 0x00f5 }
            java.lang.String r13 = r8.zzh()     // Catch:{ all -> 0x00f5 }
            boolean r13 = r4.equals(r13)     // Catch:{ all -> 0x00f5 }
            if (r13 == 0) goto L_0x06d4
            r45.zzA()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhq r13 = com.google.android.gms.measurement.internal.zzqa.zzG(r8, r6)     // Catch:{ all -> 0x00f5 }
            if (r13 == 0) goto L_0x06d4
            r7.zzD(r5)     // Catch:{ all -> 0x00f5 }
            r13 = -1
            int r14 = r14 + r13
            int r5 = r5 + r13
        L_0x06d2:
            r8 = 1
            goto L_0x06fe
        L_0x06d4:
            r13 = -1
            r45.zzA()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhq r8 = com.google.android.gms.measurement.internal.zzqa.zzG(r8, r9)     // Catch:{ all -> 0x00f5 }
            if (r8 == 0) goto L_0x06d2
            boolean r15 = r8.zzw()     // Catch:{ all -> 0x00f5 }
            if (r15 == 0) goto L_0x06ed
            long r15 = r8.zzd()     // Catch:{ all -> 0x00f5 }
            java.lang.Long r15 = java.lang.Long.valueOf(r15)     // Catch:{ all -> 0x00f5 }
            goto L_0x06ee
        L_0x06ed:
            r15 = 0
        L_0x06ee:
            if (r15 == 0) goto L_0x06d2
            long r18 = r15.longValue()     // Catch:{ all -> 0x00f5 }
            int r8 = (r18 > r2 ? 1 : (r18 == r2 ? 0 : -1))
            if (r8 <= 0) goto L_0x06d2
            long r15 = r15.longValue()     // Catch:{ all -> 0x00f5 }
            long r11 = r11 + r15
            goto L_0x06d2
        L_0x06fe:
            int r5 = r5 + r8
            goto L_0x06b3
        L_0x0700:
            r4 = 0
            r1.zzaI(r7, r11, r4)     // Catch:{ all -> 0x00f5 }
            java.util.List r4 = r7.zzaM()     // Catch:{ all -> 0x00f5 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x00f5 }
        L_0x070c:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x00f5 }
            java.lang.String r6 = "_se"
            if (r5 == 0) goto L_0x0731
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhm r5 = (com.google.android.gms.internal.measurement.zzhm) r5     // Catch:{ all -> 0x00f5 }
            java.lang.String r8 = "_s"
            java.lang.String r5 = r5.zzh()     // Catch:{ all -> 0x00f5 }
            boolean r5 = r8.equals(r5)     // Catch:{ all -> 0x00f5 }
            if (r5 == 0) goto L_0x070c
            com.google.android.gms.measurement.internal.zzaw r4 = r45.zzj()     // Catch:{ all -> 0x00f5 }
            java.lang.String r5 = r7.zzaF()     // Catch:{ all -> 0x00f5 }
            r4.zzP(r5, r6)     // Catch:{ all -> 0x00f5 }
        L_0x0731:
            java.lang.String r4 = "_sid"
            int r4 = com.google.android.gms.measurement.internal.zzqa.zza(r7, r4)     // Catch:{ all -> 0x00f5 }
            if (r4 < 0) goto L_0x073e
            r4 = 1
            r1.zzaI(r7, r11, r4)     // Catch:{ all -> 0x00f5 }
            goto L_0x075e
        L_0x073e:
            int r4 = com.google.android.gms.measurement.internal.zzqa.zza(r7, r6)     // Catch:{ all -> 0x00f5 }
            if (r4 < 0) goto L_0x075e
            r7.zzE(r4)     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzhe r4 = r45.zzaW()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzhc r4 = r4.zze()     // Catch:{ all -> 0x00f5 }
            java.lang.String r5 = "Session engagement user property is in the bundle without session ID. appId"
            com.google.android.gms.internal.measurement.zzhx r6 = r10.zza     // Catch:{ all -> 0x00f5 }
            java.lang.String r6 = r6.zzF()     // Catch:{ all -> 0x00f5 }
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzhe.zzn(r6)     // Catch:{ all -> 0x00f5 }
            r4.zzb(r5, r6)     // Catch:{ all -> 0x00f5 }
        L_0x075e:
            com.google.android.gms.internal.measurement.zzhx r4 = r10.zza     // Catch:{ all -> 0x00f5 }
            java.lang.String r4 = r4.zzF()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzil r5 = r45.zzaX()     // Catch:{ all -> 0x00f5 }
            r5.zzg()     // Catch:{ all -> 0x00f5 }
            r45.zzM()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzaw r5 = r45.zzj()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzh r5 = r5.zzl(r4)     // Catch:{ all -> 0x00f5 }
            if (r5 != 0) goto L_0x078a
            com.google.android.gms.measurement.internal.zzhe r5 = r45.zzaW()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzhc r5 = r5.zze()     // Catch:{ all -> 0x00f5 }
            java.lang.String r6 = "Cannot fix consent fields without appInfo. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzhe.zzn(r4)     // Catch:{ all -> 0x00f5 }
            r5.zzb(r6, r4)     // Catch:{ all -> 0x00f5 }
            goto L_0x078d
        L_0x078a:
            r1.zzQ(r5, r7)     // Catch:{ all -> 0x00f5 }
        L_0x078d:
            com.google.android.gms.internal.measurement.zzhx r4 = r10.zza     // Catch:{ all -> 0x00f5 }
            java.lang.String r4 = r4.zzF()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzil r5 = r45.zzaX()     // Catch:{ all -> 0x00f5 }
            r5.zzg()     // Catch:{ all -> 0x00f5 }
            r45.zzM()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzaw r5 = r45.zzj()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzh r5 = r5.zzl(r4)     // Catch:{ all -> 0x00f5 }
            if (r5 != 0) goto L_0x07b9
            com.google.android.gms.measurement.internal.zzhe r5 = r45.zzaW()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzhc r5 = r5.zzk()     // Catch:{ all -> 0x00f5 }
            java.lang.String r6 = "Cannot populate ad_campaign_info without appInfo. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzhe.zzn(r4)     // Catch:{ all -> 0x00f5 }
            r5.zzb(r6, r4)     // Catch:{ all -> 0x00f5 }
            goto L_0x07bc
        L_0x07b9:
            r1.zzaa(r5, r7)     // Catch:{ all -> 0x00f5 }
        L_0x07bc:
            r4 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r7.zzax(r4)     // Catch:{ all -> 0x00f5 }
            r4 = -9223372036854775808
            r7.zzab(r4)     // Catch:{ all -> 0x00f5 }
            r4 = 0
        L_0x07ca:
            int r5 = r7.zzc()     // Catch:{ all -> 0x00f5 }
            if (r4 >= r5) goto L_0x07fd
            com.google.android.gms.internal.measurement.zzhm r5 = r7.zzh(r4)     // Catch:{ all -> 0x00f5 }
            long r8 = r5.zzd()     // Catch:{ all -> 0x00f5 }
            long r11 = r7.zzf()     // Catch:{ all -> 0x00f5 }
            int r6 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
            if (r6 >= 0) goto L_0x07e7
            long r8 = r5.zzd()     // Catch:{ all -> 0x00f5 }
            r7.zzax(r8)     // Catch:{ all -> 0x00f5 }
        L_0x07e7:
            long r8 = r5.zzd()     // Catch:{ all -> 0x00f5 }
            long r11 = r7.zze()     // Catch:{ all -> 0x00f5 }
            int r6 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
            if (r6 <= 0) goto L_0x07fa
            long r5 = r5.zzd()     // Catch:{ all -> 0x00f5 }
            r7.zzab(r5)     // Catch:{ all -> 0x00f5 }
        L_0x07fa:
            r5 = 1
            int r4 = r4 + r5
            goto L_0x07ca
        L_0x07fd:
            r7.zzB()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzjx r4 = com.google.android.gms.measurement.internal.zzjx.zza     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhx r4 = r10.zza     // Catch:{ all -> 0x00f5 }
            java.lang.String r4 = r4.zzF()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzjx r4 = r1.zzu(r4)     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhx r5 = r10.zza     // Catch:{ all -> 0x00f5 }
            java.lang.String r5 = r5.zzK()     // Catch:{ all -> 0x00f5 }
            r6 = 100
            com.google.android.gms.measurement.internal.zzjx r5 = com.google.android.gms.measurement.internal.zzjx.zzk(r5, r6)     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzjx r4 = r4.zzl(r5)     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzaw r5 = r45.zzj()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhx r6 = r10.zza     // Catch:{ all -> 0x00f5 }
            java.lang.String r6 = r6.zzF()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzjx r5 = r5.zzt(r6)     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzaw r6 = r45.zzj()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhx r8 = r10.zza     // Catch:{ all -> 0x00f5 }
            java.lang.String r8 = r8.zzF()     // Catch:{ all -> 0x00f5 }
            r6.zzW(r8, r4)     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzjw r6 = com.google.android.gms.measurement.internal.zzjw.ANALYTICS_STORAGE     // Catch:{ all -> 0x00f5 }
            boolean r8 = r4.zzr(r6)     // Catch:{ all -> 0x00f5 }
            if (r8 != 0) goto L_0x0853
            boolean r8 = r5.zzr(r6)     // Catch:{ all -> 0x00f5 }
            if (r8 == 0) goto L_0x0853
            com.google.android.gms.measurement.internal.zzaw r5 = r45.zzj()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhx r8 = r10.zza     // Catch:{ all -> 0x00f5 }
            java.lang.String r8 = r8.zzF()     // Catch:{ all -> 0x00f5 }
            r5.zzI(r8)     // Catch:{ all -> 0x00f5 }
            goto L_0x086c
        L_0x0853:
            boolean r8 = r4.zzr(r6)     // Catch:{ all -> 0x00f5 }
            if (r8 == 0) goto L_0x086c
            boolean r5 = r5.zzr(r6)     // Catch:{ all -> 0x00f5 }
            if (r5 != 0) goto L_0x086c
            com.google.android.gms.measurement.internal.zzaw r5 = r45.zzj()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhx r8 = r10.zza     // Catch:{ all -> 0x00f5 }
            java.lang.String r8 = r8.zzF()     // Catch:{ all -> 0x00f5 }
            r5.zzQ(r8)     // Catch:{ all -> 0x00f5 }
        L_0x086c:
            com.google.android.gms.measurement.internal.zzjw r5 = com.google.android.gms.measurement.internal.zzjw.AD_STORAGE     // Catch:{ all -> 0x00f5 }
            boolean r8 = r4.zzr(r5)     // Catch:{ all -> 0x00f5 }
            if (r8 != 0) goto L_0x087d
            r7.zzz()     // Catch:{ all -> 0x00f5 }
            r7.zzw()     // Catch:{ all -> 0x00f5 }
            r7.zzt()     // Catch:{ all -> 0x00f5 }
        L_0x087d:
            boolean r8 = r4.zzr(r6)     // Catch:{ all -> 0x00f5 }
            if (r8 != 0) goto L_0x0889
            r7.zzq()     // Catch:{ all -> 0x00f5 }
            r7.zzA()     // Catch:{ all -> 0x00f5 }
        L_0x0889:
            com.google.android.gms.internal.measurement.zzqr.zzb()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzam r8 = r45.zzi()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhx r9 = r10.zza     // Catch:{ all -> 0x00f5 }
            java.lang.String r9 = r9.zzF()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzgg r11 = com.google.android.gms.measurement.internal.zzgi.zzaV     // Catch:{ all -> 0x00f5 }
            boolean r8 = r8.zzx(r9, r11)     // Catch:{ all -> 0x00f5 }
            if (r8 == 0) goto L_0x08c9
            com.google.android.gms.measurement.internal.zzqf r8 = r45.zzB()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhx r9 = r10.zza     // Catch:{ all -> 0x00f5 }
            java.lang.String r9 = r9.zzF()     // Catch:{ all -> 0x00f5 }
            boolean r8 = r8.zzab(r9)     // Catch:{ all -> 0x00f5 }
            if (r8 == 0) goto L_0x08c9
            com.google.android.gms.internal.measurement.zzhx r8 = r10.zza     // Catch:{ all -> 0x00f5 }
            java.lang.String r8 = r8.zzF()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzjx r8 = r1.zzu(r8)     // Catch:{ all -> 0x00f5 }
            boolean r5 = r8.zzr(r5)     // Catch:{ all -> 0x00f5 }
            if (r5 == 0) goto L_0x08c9
            com.google.android.gms.internal.measurement.zzhx r5 = r10.zza     // Catch:{ all -> 0x00f5 }
            boolean r5 = r5.zzbt()     // Catch:{ all -> 0x00f5 }
            if (r5 == 0) goto L_0x08c9
            r1.zzR(r7, r10)     // Catch:{ all -> 0x00f5 }
        L_0x08c9:
            r7.zzr()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzae r24 = r45.zzh()     // Catch:{ all -> 0x00f5 }
            java.lang.String r25 = r7.zzaF()     // Catch:{ all -> 0x00f5 }
            java.util.List r26 = r7.zzaM()     // Catch:{ all -> 0x00f5 }
            java.util.List r27 = r7.zzaN()     // Catch:{ all -> 0x00f5 }
            long r8 = r7.zzf()     // Catch:{ all -> 0x00f5 }
            java.lang.Long r28 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x00f5 }
            long r8 = r7.zze()     // Catch:{ all -> 0x00f5 }
            java.lang.Long r29 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x00f5 }
            boolean r4 = r4.zzr(r6)     // Catch:{ all -> 0x00f5 }
            r5 = 1
            r30 = r4 ^ 1
            java.util.List r4 = r24.zza(r25, r26, r27, r28, r29, r30)     // Catch:{ all -> 0x00f5 }
            r7.zzi(r4)     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzam r4 = r45.zzi()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhx r5 = r10.zza     // Catch:{ all -> 0x00f5 }
            java.lang.String r5 = r5.zzF()     // Catch:{ all -> 0x00f5 }
            boolean r4 = r4.zzB(r5)     // Catch:{ all -> 0x00f5 }
            if (r4 == 0) goto L_0x0c2d
            java.util.HashMap r4 = new java.util.HashMap     // Catch:{ all -> 0x0adf }
            r4.<init>()     // Catch:{ all -> 0x0adf }
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x0adf }
            r5.<init>()     // Catch:{ all -> 0x0adf }
            com.google.android.gms.measurement.internal.zzqf r6 = r45.zzB()     // Catch:{ all -> 0x0adf }
            java.security.SecureRandom r6 = r6.zzJ()     // Catch:{ all -> 0x0adf }
            r8 = 0
        L_0x091d:
            int r9 = r7.zzc()     // Catch:{ all -> 0x0adf }
            if (r8 >= r9) goto L_0x0bf9
            com.google.android.gms.internal.measurement.zzhm r9 = r7.zzh(r8)     // Catch:{ all -> 0x0adf }
            com.google.android.gms.internal.measurement.zzlz r9 = r9.zzch()     // Catch:{ all -> 0x0adf }
            com.google.android.gms.internal.measurement.zzhl r9 = (com.google.android.gms.internal.measurement.zzhl) r9     // Catch:{ all -> 0x0adf }
            java.lang.String r11 = r9.zzo()     // Catch:{ all -> 0x0adf }
            java.lang.String r12 = "_ep"
            boolean r11 = r11.equals(r12)     // Catch:{ all -> 0x0adf }
            java.lang.String r12 = "_sr"
            if (r11 == 0) goto L_0x09b8
            r45.zzA()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzmd r11 = r9.zzba()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhm r11 = (com.google.android.gms.internal.measurement.zzhm) r11     // Catch:{ all -> 0x00f5 }
            java.lang.String r13 = "_en"
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzqa.zzH(r11, r13)     // Catch:{ all -> 0x00f5 }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x00f5 }
            java.lang.Object r13 = r4.get(r11)     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzbd r13 = (com.google.android.gms.measurement.internal.zzbd) r13     // Catch:{ all -> 0x00f5 }
            if (r13 != 0) goto L_0x096d
            com.google.android.gms.measurement.internal.zzaw r13 = r45.zzj()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhx r14 = r10.zza     // Catch:{ all -> 0x00f5 }
            java.lang.String r14 = r14.zzF()     // Catch:{ all -> 0x00f5 }
            java.lang.Object r15 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r11)     // Catch:{ all -> 0x00f5 }
            java.lang.String r15 = (java.lang.String) r15     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzbd r13 = r13.zzs(r14, r15)     // Catch:{ all -> 0x00f5 }
            if (r13 == 0) goto L_0x096d
            r4.put(r11, r13)     // Catch:{ all -> 0x00f5 }
        L_0x096d:
            if (r13 == 0) goto L_0x09ac
            java.lang.Long r11 = r13.zzi     // Catch:{ all -> 0x00f5 }
            if (r11 != 0) goto L_0x09ac
            java.lang.Long r11 = r13.zzj     // Catch:{ all -> 0x00f5 }
            if (r11 == 0) goto L_0x0987
            long r14 = r11.longValue()     // Catch:{ all -> 0x00f5 }
            r18 = 1
            int r14 = (r14 > r18 ? 1 : (r14 == r18 ? 0 : -1))
            if (r14 <= 0) goto L_0x0987
            r45.zzA()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzqa.zzD(r9, r12, r11)     // Catch:{ all -> 0x00f5 }
        L_0x0987:
            java.lang.Boolean r11 = r13.zzk     // Catch:{ all -> 0x00f5 }
            if (r11 == 0) goto L_0x09a0
            boolean r11 = r11.booleanValue()     // Catch:{ all -> 0x00f5 }
            if (r11 == 0) goto L_0x09a0
            r45.zzA()     // Catch:{ all -> 0x00f5 }
            r11 = 1
            java.lang.Long r13 = java.lang.Long.valueOf(r11)     // Catch:{ all -> 0x00f5 }
            r11 = r20
            com.google.android.gms.measurement.internal.zzqa.zzD(r9, r11, r13)     // Catch:{ all -> 0x00f5 }
            goto L_0x09a2
        L_0x09a0:
            r11 = r20
        L_0x09a2:
            com.google.android.gms.internal.measurement.zzmd r12 = r9.zzba()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhm r12 = (com.google.android.gms.internal.measurement.zzhm) r12     // Catch:{ all -> 0x00f5 }
            r5.add(r12)     // Catch:{ all -> 0x00f5 }
            goto L_0x09ae
        L_0x09ac:
            r11 = r20
        L_0x09ae:
            r7.zzad(r8, r9)     // Catch:{ all -> 0x00f5 }
            r48 = r6
            r3 = r7
            r12 = r11
        L_0x09b5:
            r1 = 1
            goto L_0x0bed
        L_0x09b8:
            r11 = r20
            com.google.android.gms.measurement.internal.zzif r13 = r45.zzr()     // Catch:{ all -> 0x0adf }
            com.google.android.gms.internal.measurement.zzhx r14 = r10.zza     // Catch:{ all -> 0x0adf }
            java.lang.String r14 = r14.zzF()     // Catch:{ all -> 0x0adf }
            java.lang.String r15 = "measurement.account.time_zone_offset_minutes"
            java.lang.String r15 = r13.zza(r14, r15)     // Catch:{ all -> 0x0adf }
            boolean r16 = android.text.TextUtils.isEmpty(r15)     // Catch:{ all -> 0x0adf }
            if (r16 != 0) goto L_0x09ea
            long r13 = java.lang.Long.parseLong(r15)     // Catch:{ NumberFormatException -> 0x09d5 }
            goto L_0x09ec
        L_0x09d5:
            r0 = move-exception
            r15 = r0
            com.google.android.gms.measurement.internal.zzio r13 = r13.zzu     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzhe r13 = r13.zzaW()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzhc r13 = r13.zzk()     // Catch:{ all -> 0x00f5 }
            java.lang.String r2 = "Unable to parse timezone offset. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzhe.zzn(r14)     // Catch:{ all -> 0x00f5 }
            r13.zzc(r2, r3, r15)     // Catch:{ all -> 0x00f5 }
        L_0x09ea:
            r13 = 0
        L_0x09ec:
            com.google.android.gms.measurement.internal.zzqf r2 = r45.zzB()     // Catch:{ all -> 0x0adf }
            r20 = r11
            r48 = r12
            long r11 = r9.zzc()     // Catch:{ all -> 0x0adf }
            long r2 = r2.zzt(r11, r13)     // Catch:{ all -> 0x0adf }
            com.google.android.gms.internal.measurement.zzmd r11 = r9.zzba()     // Catch:{ all -> 0x0adf }
            com.google.android.gms.internal.measurement.zzhm r11 = (com.google.android.gms.internal.measurement.zzhm) r11     // Catch:{ all -> 0x0adf }
            r15 = 1
            java.lang.Long r12 = java.lang.Long.valueOf(r15)     // Catch:{ all -> 0x0adf }
            java.lang.String r15 = "_dbg"
            boolean r16 = android.text.TextUtils.isEmpty(r15)     // Catch:{ all -> 0x0adf }
            if (r16 != 0) goto L_0x0a44
            java.util.List r11 = r11.zzi()     // Catch:{ all -> 0x00f5 }
            java.util.Iterator r11 = r11.iterator()     // Catch:{ all -> 0x00f5 }
        L_0x0a18:
            boolean r16 = r11.hasNext()     // Catch:{ all -> 0x00f5 }
            if (r16 == 0) goto L_0x0a44
            java.lang.Object r16 = r11.next()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhq r16 = (com.google.android.gms.internal.measurement.zzhq) r16     // Catch:{ all -> 0x00f5 }
            r21 = r11
            java.lang.String r11 = r16.zzg()     // Catch:{ all -> 0x00f5 }
            boolean r11 = r15.equals(r11)     // Catch:{ all -> 0x00f5 }
            if (r11 == 0) goto L_0x0a41
            long r15 = r16.zzd()     // Catch:{ all -> 0x00f5 }
            java.lang.Long r11 = java.lang.Long.valueOf(r15)     // Catch:{ all -> 0x00f5 }
            boolean r11 = r12.equals(r11)     // Catch:{ all -> 0x00f5 }
            if (r11 != 0) goto L_0x0a3f
            goto L_0x0a44
        L_0x0a3f:
            r11 = 1
            goto L_0x0a56
        L_0x0a41:
            r11 = r21
            goto L_0x0a18
        L_0x0a44:
            com.google.android.gms.measurement.internal.zzif r11 = r45.zzr()     // Catch:{ all -> 0x0adf }
            com.google.android.gms.internal.measurement.zzhx r12 = r10.zza     // Catch:{ all -> 0x0adf }
            java.lang.String r12 = r12.zzF()     // Catch:{ all -> 0x0adf }
            java.lang.String r15 = r9.zzo()     // Catch:{ all -> 0x0adf }
            int r11 = r11.zzc(r12, r15)     // Catch:{ all -> 0x0adf }
        L_0x0a56:
            if (r11 > 0) goto L_0x0a80
            com.google.android.gms.measurement.internal.zzhe r2 = r45.zzaW()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zzk()     // Catch:{ all -> 0x00f5 }
            java.lang.String r3 = "Sample rate must be positive. event, rate"
            java.lang.String r12 = r9.zzo()     // Catch:{ all -> 0x00f5 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ all -> 0x00f5 }
            r2.zzc(r3, r12, r11)     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzmd r2 = r9.zzba()     // Catch:{ all -> 0x00f5 }
            com.google.android.gms.internal.measurement.zzhm r2 = (com.google.android.gms.internal.measurement.zzhm) r2     // Catch:{ all -> 0x00f5 }
            r5.add(r2)     // Catch:{ all -> 0x00f5 }
            r7.zzad(r8, r9)     // Catch:{ all -> 0x00f5 }
        L_0x0a79:
            r48 = r6
            r3 = r7
            r12 = r20
            goto L_0x09b5
        L_0x0a80:
            java.lang.String r12 = r9.zzo()     // Catch:{ all -> 0x0adf }
            java.lang.Object r12 = r4.get(r12)     // Catch:{ all -> 0x0adf }
            com.google.android.gms.measurement.internal.zzbd r12 = (com.google.android.gms.measurement.internal.zzbd) r12     // Catch:{ all -> 0x0adf }
            if (r12 != 0) goto L_0x0ae4
            com.google.android.gms.measurement.internal.zzaw r12 = r45.zzj()     // Catch:{ all -> 0x0adf }
            com.google.android.gms.internal.measurement.zzhx r15 = r10.zza     // Catch:{ all -> 0x0adf }
            java.lang.String r15 = r15.zzF()     // Catch:{ all -> 0x0adf }
            java.lang.String r1 = r9.zzo()     // Catch:{ all -> 0x0adf }
            com.google.android.gms.measurement.internal.zzbd r12 = r12.zzs(r15, r1)     // Catch:{ all -> 0x0adf }
            if (r12 != 0) goto L_0x0ae4
            com.google.android.gms.measurement.internal.zzhe r1 = r45.zzaW()     // Catch:{ all -> 0x0adf }
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zzk()     // Catch:{ all -> 0x0adf }
            java.lang.String r12 = "Event being bundled has no eventAggregate. appId, eventName"
            com.google.android.gms.internal.measurement.zzhx r15 = r10.zza     // Catch:{ all -> 0x0adf }
            java.lang.String r15 = r15.zzF()     // Catch:{ all -> 0x0adf }
            r24 = r13
            java.lang.String r13 = r9.zzo()     // Catch:{ all -> 0x0adf }
            r1.zzc(r12, r15, r13)     // Catch:{ all -> 0x0adf }
            com.google.android.gms.measurement.internal.zzbd r12 = new com.google.android.gms.measurement.internal.zzbd     // Catch:{ all -> 0x0adf }
            com.google.android.gms.internal.measurement.zzhx r1 = r10.zza     // Catch:{ all -> 0x0adf }
            java.lang.String r27 = r1.zzF()     // Catch:{ all -> 0x0adf }
            java.lang.String r28 = r9.zzo()     // Catch:{ all -> 0x0adf }
            long r35 = r9.zzc()     // Catch:{ all -> 0x0adf }
            r41 = 0
            r42 = 0
            r29 = 1
            r31 = 1
            r33 = 1
            r37 = 0
            r39 = 0
            r40 = 0
            r26 = r12
            r26.<init>(r27, r28, r29, r31, r33, r35, r37, r39, r40, r41, r42)     // Catch:{ all -> 0x0adf }
            goto L_0x0ae6
        L_0x0adf:
            r0 = move-exception
            r4 = r45
            goto L_0x00f7
        L_0x0ae4:
            r24 = r13
        L_0x0ae6:
            r45.zzA()     // Catch:{ all -> 0x0adf }
            com.google.android.gms.internal.measurement.zzmd r1 = r9.zzba()     // Catch:{ all -> 0x0adf }
            com.google.android.gms.internal.measurement.zzhm r1 = (com.google.android.gms.internal.measurement.zzhm) r1     // Catch:{ all -> 0x0adf }
            java.lang.String r13 = "_eid"
            java.lang.Object r1 = com.google.android.gms.measurement.internal.zzqa.zzH(r1, r13)     // Catch:{ all -> 0x0adf }
            java.lang.Long r1 = (java.lang.Long) r1     // Catch:{ all -> 0x0adf }
            if (r1 == 0) goto L_0x0afc
            r13 = 1
        L_0x0afa:
            r14 = 1
            goto L_0x0afe
        L_0x0afc:
            r13 = 0
            goto L_0x0afa
        L_0x0afe:
            if (r11 != r14) goto L_0x0b28
            com.google.android.gms.internal.measurement.zzmd r1 = r9.zzba()     // Catch:{ all -> 0x0adf }
            com.google.android.gms.internal.measurement.zzhm r1 = (com.google.android.gms.internal.measurement.zzhm) r1     // Catch:{ all -> 0x0adf }
            r5.add(r1)     // Catch:{ all -> 0x0adf }
            if (r13 == 0) goto L_0x0b23
            java.lang.Long r1 = r12.zzi     // Catch:{ all -> 0x0adf }
            if (r1 != 0) goto L_0x0b17
            java.lang.Long r1 = r12.zzj     // Catch:{ all -> 0x0adf }
            if (r1 != 0) goto L_0x0b17
            java.lang.Boolean r1 = r12.zzk     // Catch:{ all -> 0x0adf }
            if (r1 == 0) goto L_0x0b23
        L_0x0b17:
            r1 = 0
            com.google.android.gms.measurement.internal.zzbd r2 = r12.zza(r1, r1, r1)     // Catch:{ all -> 0x0adf }
            java.lang.String r1 = r9.zzo()     // Catch:{ all -> 0x0adf }
            r4.put(r1, r2)     // Catch:{ all -> 0x0adf }
        L_0x0b23:
            r7.zzad(r8, r9)     // Catch:{ all -> 0x0adf }
            goto L_0x0a79
        L_0x0b28:
            int r14 = r6.nextInt(r11)     // Catch:{ all -> 0x0adf }
            if (r14 != 0) goto L_0x0b61
            r45.zzA()     // Catch:{ all -> 0x0adf }
            long r14 = (long) r11     // Catch:{ all -> 0x0adf }
            java.lang.Long r1 = java.lang.Long.valueOf(r14)     // Catch:{ all -> 0x0adf }
            r14 = r48
            com.google.android.gms.measurement.internal.zzqa.zzD(r9, r14, r1)     // Catch:{ all -> 0x0adf }
            com.google.android.gms.internal.measurement.zzmd r11 = r9.zzba()     // Catch:{ all -> 0x0adf }
            com.google.android.gms.internal.measurement.zzhm r11 = (com.google.android.gms.internal.measurement.zzhm) r11     // Catch:{ all -> 0x0adf }
            r5.add(r11)     // Catch:{ all -> 0x0adf }
            if (r13 == 0) goto L_0x0b4b
            r11 = 0
            com.google.android.gms.measurement.internal.zzbd r12 = r12.zza(r11, r1, r11)     // Catch:{ all -> 0x0adf }
        L_0x0b4b:
            java.lang.String r1 = r9.zzo()     // Catch:{ all -> 0x0adf }
            long r13 = r9.zzc()     // Catch:{ all -> 0x0adf }
            com.google.android.gms.measurement.internal.zzbd r2 = r12.zzb(r13, r2)     // Catch:{ all -> 0x0adf }
            r4.put(r1, r2)     // Catch:{ all -> 0x0adf }
            r48 = r6
            r3 = r7
            r12 = r20
            goto L_0x0be8
        L_0x0b61:
            r14 = r48
            java.lang.Long r15 = r12.zzh     // Catch:{ all -> 0x0adf }
            if (r15 == 0) goto L_0x0b78
            long r15 = r15.longValue()     // Catch:{ all -> 0x0adf }
            r48 = r6
            r26 = r12
            r21 = r13
            r43 = r15
            r16 = r7
            r6 = r43
            goto L_0x0b8e
        L_0x0b78:
            com.google.android.gms.measurement.internal.zzqf r15 = r45.zzB()     // Catch:{ all -> 0x0adf }
            r48 = r6
            r16 = r7
            long r6 = r9.zzb()     // Catch:{ all -> 0x0adf }
            r26 = r12
            r21 = r13
            r12 = r24
            long r6 = r15.zzt(r6, r12)     // Catch:{ all -> 0x0adf }
        L_0x0b8e:
            int r6 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r6 == 0) goto L_0x0bd5
            r45.zzA()     // Catch:{ all -> 0x0adf }
            r6 = 1
            java.lang.Long r1 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0adf }
            r12 = r20
            com.google.android.gms.measurement.internal.zzqa.zzD(r9, r12, r1)     // Catch:{ all -> 0x0adf }
            r45.zzA()     // Catch:{ all -> 0x0adf }
            long r6 = (long) r11     // Catch:{ all -> 0x0adf }
            java.lang.Long r1 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0adf }
            com.google.android.gms.measurement.internal.zzqa.zzD(r9, r14, r1)     // Catch:{ all -> 0x0adf }
            com.google.android.gms.internal.measurement.zzmd r6 = r9.zzba()     // Catch:{ all -> 0x0adf }
            com.google.android.gms.internal.measurement.zzhm r6 = (com.google.android.gms.internal.measurement.zzhm) r6     // Catch:{ all -> 0x0adf }
            r5.add(r6)     // Catch:{ all -> 0x0adf }
            if (r21 == 0) goto L_0x0bc0
            java.lang.Boolean r6 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x0adf }
            r11 = r26
            r7 = 0
            com.google.android.gms.measurement.internal.zzbd r1 = r11.zza(r7, r1, r6)     // Catch:{ all -> 0x0adf }
            goto L_0x0bc3
        L_0x0bc0:
            r11 = r26
            r1 = r11
        L_0x0bc3:
            java.lang.String r6 = r9.zzo()     // Catch:{ all -> 0x0adf }
            long r13 = r9.zzc()     // Catch:{ all -> 0x0adf }
            com.google.android.gms.measurement.internal.zzbd r1 = r1.zzb(r13, r2)     // Catch:{ all -> 0x0adf }
            r4.put(r6, r1)     // Catch:{ all -> 0x0adf }
        L_0x0bd2:
            r3 = r16
            goto L_0x0be8
        L_0x0bd5:
            r12 = r20
            r11 = r26
            if (r21 == 0) goto L_0x0bd2
            java.lang.String r2 = r9.zzo()     // Catch:{ all -> 0x0adf }
            r3 = 0
            com.google.android.gms.measurement.internal.zzbd r1 = r11.zza(r1, r3, r3)     // Catch:{ all -> 0x0adf }
            r4.put(r2, r1)     // Catch:{ all -> 0x0adf }
            goto L_0x0bd2
        L_0x0be8:
            r3.zzad(r8, r9)     // Catch:{ all -> 0x0adf }
            goto L_0x09b5
        L_0x0bed:
            int r8 = r8 + r1
            r1 = r45
            r6 = r48
            r7 = r3
            r20 = r12
            r2 = 0
            goto L_0x091d
        L_0x0bf9:
            r3 = r7
            r1 = 1
            int r2 = r5.size()     // Catch:{ all -> 0x0adf }
            int r6 = r3.zzc()     // Catch:{ all -> 0x0adf }
            if (r2 >= r6) goto L_0x0c0b
            r3.zzu()     // Catch:{ all -> 0x0adf }
            r3.zzj(r5)     // Catch:{ all -> 0x0adf }
        L_0x0c0b:
            java.util.Set r2 = r4.entrySet()     // Catch:{ all -> 0x0adf }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x0adf }
        L_0x0c13:
            boolean r4 = r2.hasNext()     // Catch:{ all -> 0x0adf }
            if (r4 == 0) goto L_0x0c2f
            java.lang.Object r4 = r2.next()     // Catch:{ all -> 0x0adf }
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4     // Catch:{ all -> 0x0adf }
            com.google.android.gms.measurement.internal.zzaw r5 = r45.zzj()     // Catch:{ all -> 0x0adf }
            java.lang.Object r4 = r4.getValue()     // Catch:{ all -> 0x0adf }
            com.google.android.gms.measurement.internal.zzbd r4 = (com.google.android.gms.measurement.internal.zzbd) r4     // Catch:{ all -> 0x0adf }
            r5.zzV(r4)     // Catch:{ all -> 0x0adf }
            goto L_0x0c13
        L_0x0c2d:
            r3 = r7
            r1 = 1
        L_0x0c2f:
            com.google.android.gms.internal.measurement.zzhx r2 = r10.zza     // Catch:{ all -> 0x0adf }
            java.lang.String r2 = r2.zzF()     // Catch:{ all -> 0x0adf }
            com.google.android.gms.measurement.internal.zzaw r4 = r45.zzj()     // Catch:{ all -> 0x0adf }
            com.google.android.gms.measurement.internal.zzh r4 = r4.zzl(r2)     // Catch:{ all -> 0x0adf }
            if (r4 != 0) goto L_0x0c57
            com.google.android.gms.measurement.internal.zzhe r4 = r45.zzaW()     // Catch:{ all -> 0x0adf }
            com.google.android.gms.measurement.internal.zzhc r4 = r4.zze()     // Catch:{ all -> 0x0adf }
            java.lang.String r5 = "Bundling raw events w/o app info. appId"
            com.google.android.gms.internal.measurement.zzhx r6 = r10.zza     // Catch:{ all -> 0x0adf }
            java.lang.String r6 = r6.zzF()     // Catch:{ all -> 0x0adf }
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzhe.zzn(r6)     // Catch:{ all -> 0x0adf }
            r4.zzb(r5, r6)     // Catch:{ all -> 0x0adf }
            goto L_0x0cc0
        L_0x0c57:
            int r5 = r3.zzc()     // Catch:{ all -> 0x0adf }
            if (r5 <= 0) goto L_0x0cc0
            long r5 = r4.zzs()     // Catch:{ all -> 0x0adf }
            r7 = 0
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 == 0) goto L_0x0c6b
            r3.zzap(r5)     // Catch:{ all -> 0x0adf }
            goto L_0x0c6e
        L_0x0c6b:
            r3.zzx()     // Catch:{ all -> 0x0adf }
        L_0x0c6e:
            long r7 = r4.zzu()     // Catch:{ all -> 0x0adf }
            r11 = 0
            int r9 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r9 != 0) goto L_0x0c79
            goto L_0x0c7a
        L_0x0c79:
            r5 = r7
        L_0x0c7a:
            int r7 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
            if (r7 == 0) goto L_0x0c82
            r3.zzaq(r5)     // Catch:{ all -> 0x0adf }
            goto L_0x0c85
        L_0x0c82:
            r3.zzy()     // Catch:{ all -> 0x0adf }
        L_0x0c85:
            int r5 = r3.zzc()     // Catch:{ all -> 0x0adf }
            long r5 = (long) r5     // Catch:{ all -> 0x0adf }
            r4.zzQ(r5)     // Catch:{ all -> 0x0adf }
            long r5 = r4.zzr()     // Catch:{ all -> 0x0adf }
            int r5 = (int) r5     // Catch:{ all -> 0x0adf }
            r3.zzV(r5)     // Catch:{ all -> 0x0adf }
            long r5 = r4.zzt()     // Catch:{ all -> 0x0adf }
            int r5 = (int) r5     // Catch:{ all -> 0x0adf }
            r3.zzP(r5)     // Catch:{ all -> 0x0adf }
            long r5 = r3.zzf()     // Catch:{ all -> 0x0adf }
            r4.zzau(r5)     // Catch:{ all -> 0x0adf }
            long r5 = r3.zze()     // Catch:{ all -> 0x0adf }
            r4.zzas(r5)     // Catch:{ all -> 0x0adf }
            java.lang.String r5 = r4.zzB()     // Catch:{ all -> 0x0adf }
            if (r5 == 0) goto L_0x0cb5
            r3.zzaj(r5)     // Catch:{ all -> 0x0adf }
            goto L_0x0cb8
        L_0x0cb5:
            r3.zzv()     // Catch:{ all -> 0x0adf }
        L_0x0cb8:
            com.google.android.gms.measurement.internal.zzaw r5 = r45.zzj()     // Catch:{ all -> 0x0adf }
            r6 = 0
            r5.zzT(r4, r6, r6)     // Catch:{ all -> 0x0adf }
        L_0x0cc0:
            int r4 = r3.zzc()     // Catch:{ all -> 0x0adf }
            if (r4 <= 0) goto L_0x0e41
            r4 = r45
            com.google.android.gms.measurement.internal.zzio r5 = r4.zzn     // Catch:{ all -> 0x0cec }
            r5.zzaV()     // Catch:{ all -> 0x0cec }
            com.google.android.gms.measurement.internal.zzif r5 = r45.zzr()     // Catch:{ all -> 0x0cec }
            com.google.android.gms.internal.measurement.zzhx r6 = r10.zza     // Catch:{ all -> 0x0cec }
            java.lang.String r6 = r6.zzF()     // Catch:{ all -> 0x0cec }
            com.google.android.gms.internal.measurement.zzgo r5 = r5.zzj(r6)     // Catch:{ all -> 0x0cec }
            if (r5 == 0) goto L_0x0cef
            boolean r6 = r5.zzw()     // Catch:{ all -> 0x0cec }
            if (r6 != 0) goto L_0x0ce4
            goto L_0x0cef
        L_0x0ce4:
            long r5 = r5.zzc()     // Catch:{ all -> 0x0cec }
            r3.zzR(r5)     // Catch:{ all -> 0x0cec }
            goto L_0x0d18
        L_0x0cec:
            r0 = move-exception
            goto L_0x00f7
        L_0x0cef:
            com.google.android.gms.internal.measurement.zzhx r5 = r10.zza     // Catch:{ all -> 0x0cec }
            java.lang.String r5 = r5.zzP()     // Catch:{ all -> 0x0cec }
            boolean r5 = r5.isEmpty()     // Catch:{ all -> 0x0cec }
            if (r5 == 0) goto L_0x0d01
            r5 = -1
            r3.zzR(r5)     // Catch:{ all -> 0x0cec }
            goto L_0x0d18
        L_0x0d01:
            com.google.android.gms.measurement.internal.zzhe r5 = r45.zzaW()     // Catch:{ all -> 0x0cec }
            com.google.android.gms.measurement.internal.zzhc r5 = r5.zzk()     // Catch:{ all -> 0x0cec }
            java.lang.String r6 = "Did not find measurement config or missing version info. appId"
            com.google.android.gms.internal.measurement.zzhx r7 = r10.zza     // Catch:{ all -> 0x0cec }
            java.lang.String r7 = r7.zzF()     // Catch:{ all -> 0x0cec }
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzhe.zzn(r7)     // Catch:{ all -> 0x0cec }
            r5.zzb(r6, r7)     // Catch:{ all -> 0x0cec }
        L_0x0d18:
            com.google.android.gms.measurement.internal.zzaw r5 = r45.zzj()     // Catch:{ all -> 0x0cec }
            com.google.android.gms.internal.measurement.zzmd r3 = r3.zzba()     // Catch:{ all -> 0x0cec }
            com.google.android.gms.internal.measurement.zzhx r3 = (com.google.android.gms.internal.measurement.zzhx) r3     // Catch:{ all -> 0x0cec }
            r5.zzg()     // Catch:{ all -> 0x0cec }
            r5.zzav()     // Catch:{ all -> 0x0cec }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r3)     // Catch:{ all -> 0x0cec }
            java.lang.String r6 = r3.zzF()     // Catch:{ all -> 0x0cec }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r6)     // Catch:{ all -> 0x0cec }
            boolean r6 = r3.zzbK()     // Catch:{ all -> 0x0cec }
            com.google.android.gms.common.internal.Preconditions.checkState(r6)     // Catch:{ all -> 0x0cec }
            r5.zzO()     // Catch:{ all -> 0x0cec }
            com.google.android.gms.measurement.internal.zzio r6 = r5.zzu     // Catch:{ all -> 0x0cec }
            com.google.android.gms.common.util.Clock r7 = r6.zzaU()     // Catch:{ all -> 0x0cec }
            long r7 = r7.currentTimeMillis()     // Catch:{ all -> 0x0cec }
            long r11 = r3.zzo()     // Catch:{ all -> 0x0cec }
            r6.zzf()     // Catch:{ all -> 0x0cec }
            long r13 = com.google.android.gms.measurement.internal.zzam.zzI()     // Catch:{ all -> 0x0cec }
            long r13 = r7 - r13
            int r9 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r9 < 0) goto L_0x0d67
            long r11 = r3.zzo()     // Catch:{ all -> 0x0cec }
            r6.zzf()     // Catch:{ all -> 0x0cec }
            long r13 = com.google.android.gms.measurement.internal.zzam.zzI()     // Catch:{ all -> 0x0cec }
            long r13 = r13 + r7
            int r9 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r9 <= 0) goto L_0x0d88
        L_0x0d67:
            com.google.android.gms.measurement.internal.zzhe r6 = r6.zzaW()     // Catch:{ all -> 0x0cec }
            com.google.android.gms.measurement.internal.zzhc r6 = r6.zzk()     // Catch:{ all -> 0x0cec }
            java.lang.String r9 = r3.zzF()     // Catch:{ all -> 0x0cec }
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzhe.zzn(r9)     // Catch:{ all -> 0x0cec }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x0cec }
            long r11 = r3.zzo()     // Catch:{ all -> 0x0cec }
            java.lang.Long r8 = java.lang.Long.valueOf(r11)     // Catch:{ all -> 0x0cec }
            java.lang.String r11 = "Storing bundle outside of the max uploading time span. appId, now, timestamp"
            r6.zzd(r11, r9, r7, r8)     // Catch:{ all -> 0x0cec }
        L_0x0d88:
            byte[] r6 = r3.zzcd()     // Catch:{ all -> 0x0cec }
            com.google.android.gms.measurement.internal.zzpv r7 = r5.zzg     // Catch:{ IOException -> 0x0e27 }
            com.google.android.gms.measurement.internal.zzqa r7 = r7.zzA()     // Catch:{ IOException -> 0x0e27 }
            byte[] r6 = r7.zzB(r6)     // Catch:{ IOException -> 0x0e27 }
            com.google.android.gms.measurement.internal.zzio r7 = r5.zzu     // Catch:{ all -> 0x0cec }
            com.google.android.gms.measurement.internal.zzhe r8 = r7.zzaW()     // Catch:{ all -> 0x0cec }
            com.google.android.gms.measurement.internal.zzhc r8 = r8.zzj()     // Catch:{ all -> 0x0cec }
            int r9 = r6.length     // Catch:{ all -> 0x0cec }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x0cec }
            java.lang.String r11 = "Saving bundle, size"
            r8.zzb(r11, r9)     // Catch:{ all -> 0x0cec }
            android.content.ContentValues r8 = new android.content.ContentValues     // Catch:{ all -> 0x0cec }
            r8.<init>()     // Catch:{ all -> 0x0cec }
            java.lang.String r9 = r3.zzF()     // Catch:{ all -> 0x0cec }
            java.lang.String r11 = "app_id"
            r8.put(r11, r9)     // Catch:{ all -> 0x0cec }
            long r11 = r3.zzo()     // Catch:{ all -> 0x0cec }
            java.lang.Long r9 = java.lang.Long.valueOf(r11)     // Catch:{ all -> 0x0cec }
            java.lang.String r11 = "bundle_end_timestamp"
            r8.put(r11, r9)     // Catch:{ all -> 0x0cec }
            java.lang.String r9 = "data"
            r8.put(r9, r6)     // Catch:{ all -> 0x0cec }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r17)     // Catch:{ all -> 0x0cec }
            java.lang.String r9 = "has_realtime"
            r8.put(r9, r6)     // Catch:{ all -> 0x0cec }
            boolean r6 = r3.zzbR()     // Catch:{ all -> 0x0cec }
            if (r6 == 0) goto L_0x0de6
            int r6 = r3.zzg()     // Catch:{ all -> 0x0cec }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0cec }
            java.lang.String r9 = "retry_count"
            r8.put(r9, r6)     // Catch:{ all -> 0x0cec }
        L_0x0de6:
            android.database.sqlite.SQLiteDatabase r6 = r5.zzj()     // Catch:{ SQLiteException -> 0x0e0d }
            java.lang.String r9 = "queue"
            r11 = 0
            long r8 = r6.insert(r9, r11, r8)     // Catch:{ SQLiteException -> 0x0e0d }
            r11 = -1
            int r6 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
            if (r6 != 0) goto L_0x0e43
            com.google.android.gms.measurement.internal.zzhe r6 = r7.zzaW()     // Catch:{ SQLiteException -> 0x0e0d }
            com.google.android.gms.measurement.internal.zzhc r6 = r6.zze()     // Catch:{ SQLiteException -> 0x0e0d }
            java.lang.String r7 = "Failed to insert bundle (got -1). appId"
            java.lang.String r8 = r3.zzF()     // Catch:{ SQLiteException -> 0x0e0d }
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzhe.zzn(r8)     // Catch:{ SQLiteException -> 0x0e0d }
            r6.zzb(r7, r8)     // Catch:{ SQLiteException -> 0x0e0d }
            goto L_0x0e43
        L_0x0e0d:
            r0 = move-exception
            r6 = r0
            com.google.android.gms.measurement.internal.zzio r5 = r5.zzu     // Catch:{ all -> 0x0cec }
            com.google.android.gms.measurement.internal.zzhe r5 = r5.zzaW()     // Catch:{ all -> 0x0cec }
            com.google.android.gms.measurement.internal.zzhc r5 = r5.zze()     // Catch:{ all -> 0x0cec }
            java.lang.String r3 = r3.zzF()     // Catch:{ all -> 0x0cec }
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzhe.zzn(r3)     // Catch:{ all -> 0x0cec }
            java.lang.String r7 = "Error storing bundle. appId"
            r5.zzc(r7, r3, r6)     // Catch:{ all -> 0x0cec }
            goto L_0x0e43
        L_0x0e27:
            r0 = move-exception
            r6 = r0
            com.google.android.gms.measurement.internal.zzio r5 = r5.zzu     // Catch:{ all -> 0x0cec }
            com.google.android.gms.measurement.internal.zzhe r5 = r5.zzaW()     // Catch:{ all -> 0x0cec }
            com.google.android.gms.measurement.internal.zzhc r5 = r5.zze()     // Catch:{ all -> 0x0cec }
            java.lang.String r3 = r3.zzF()     // Catch:{ all -> 0x0cec }
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzhe.zzn(r3)     // Catch:{ all -> 0x0cec }
            java.lang.String r7 = "Data loss. Failed to serialize bundle. appId"
            r5.zzc(r7, r3, r6)     // Catch:{ all -> 0x0cec }
            goto L_0x0e43
        L_0x0e41:
            r4 = r45
        L_0x0e43:
            com.google.android.gms.measurement.internal.zzaw r3 = r45.zzj()     // Catch:{ all -> 0x0cec }
            java.util.List r5 = r10.zzb     // Catch:{ all -> 0x0cec }
            r3.zzJ(r5)     // Catch:{ all -> 0x0cec }
            com.google.android.gms.measurement.internal.zzaw r3 = r45.zzj()     // Catch:{ all -> 0x0cec }
            android.database.sqlite.SQLiteDatabase r5 = r3.zzj()     // Catch:{ all -> 0x0cec }
            java.lang.String r6 = "delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)"
            java.lang.String[] r7 = new java.lang.String[]{r2, r2}     // Catch:{ SQLiteException -> 0x0e5e }
            r5.execSQL(r6, r7)     // Catch:{ SQLiteException -> 0x0e5e }
            goto L_0x0e73
        L_0x0e5e:
            r0 = move-exception
            r5 = r0
            com.google.android.gms.measurement.internal.zzio r3 = r3.zzu     // Catch:{ all -> 0x0cec }
            com.google.android.gms.measurement.internal.zzhe r3 = r3.zzaW()     // Catch:{ all -> 0x0cec }
            com.google.android.gms.measurement.internal.zzhc r3 = r3.zze()     // Catch:{ all -> 0x0cec }
            java.lang.String r6 = "Failed to remove unused event metadata. appId"
            java.lang.Object r2 = com.google.android.gms.measurement.internal.zzhe.zzn(r2)     // Catch:{ all -> 0x0cec }
            r3.zzc(r6, r2, r5)     // Catch:{ all -> 0x0cec }
        L_0x0e73:
            com.google.android.gms.measurement.internal.zzaw r2 = r45.zzj()     // Catch:{ all -> 0x0cec }
            r2.zzS()     // Catch:{ all -> 0x0cec }
            r6 = r1
            goto L_0x0e83
        L_0x0e7c:
            com.google.android.gms.measurement.internal.zzaw r1 = r45.zzj()     // Catch:{ all -> 0x0cec }
            r1.zzS()     // Catch:{ all -> 0x0cec }
        L_0x0e83:
            com.google.android.gms.measurement.internal.zzaw r1 = r45.zzj()
            r1.zzL()
            return r6
        L_0x0e8b:
            com.google.android.gms.measurement.internal.zzaw r2 = r45.zzj()
            r2.zzL()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzpv.zzaM(java.lang.String, long):boolean");
    }

    private final boolean zzaN() {
        zzaX().zzg();
        zzM();
        return zzj().zzaa() || !TextUtils.isEmpty(zzj().zzA());
    }

    private final boolean zzaO(String str, String str2) {
        zzbd zzs2 = zzj().zzs(str, str2);
        return zzs2 == null || zzs2.zzc < 1;
    }

    private final boolean zzaP(zzhl zzhl, zzhl zzhl2) {
        String str;
        Preconditions.checkArgument("_e".equals(zzhl.zzo()));
        zzA();
        zzhq zzG2 = zzqa.zzG((zzhm) zzhl.zzba(), "_sc");
        String str2 = null;
        if (zzG2 == null) {
            str = null;
        } else {
            str = zzG2.zzh();
        }
        zzA();
        zzhq zzG3 = zzqa.zzG((zzhm) zzhl2.zzba(), "_pc");
        if (zzG3 != null) {
            str2 = zzG3.zzh();
        }
        if (str2 == null || !str2.equals(str)) {
            return false;
        }
        Preconditions.checkArgument("_e".equals(zzhl.zzo()));
        zzA();
        zzhq zzG4 = zzqa.zzG((zzhm) zzhl.zzba(), "_et");
        if (zzG4 == null || !zzG4.zzw() || zzG4.zzd() <= 0) {
            return true;
        }
        long zzd2 = zzG4.zzd();
        zzA();
        zzhq zzG5 = zzqa.zzG((zzhm) zzhl2.zzba(), "_et");
        if (zzG5 != null && zzG5.zzd() > 0) {
            zzd2 += zzG5.zzd();
        }
        zzA();
        zzqa.zzD(zzhl2, "_et", Long.valueOf(zzd2));
        zzA();
        zzqa.zzD(zzhl, "_fr", 1L);
        return true;
    }

    private static final boolean zzaQ(zzr zzr2) {
        return !TextUtils.isEmpty(zzr2.zzb) || !TextUtils.isEmpty(zzr2.zzp);
    }

    private static final zzpg zzaR(zzpg zzpg) {
        if (zzpg == null) {
            throw new IllegalStateException("Upload Component not created");
        } else if (zzpg.zzax()) {
            return zzpg;
        } else {
            throw new IllegalStateException("Component not initialized: ".concat(String.valueOf(zzpg.getClass())));
        }
    }

    private static final Boolean zzaS(zzr zzr2) {
        Boolean bool = zzr2.zzq;
        String str = zzr2.zzE;
        if (!TextUtils.isEmpty(str)) {
            zzju zzb2 = zze.zza(str).zzb();
            zzju zzju = zzju.UNINITIALIZED;
            int ordinal = zzb2.ordinal();
            if (ordinal == 0 || ordinal == 1) {
                return null;
            }
            if (ordinal == 2) {
                return Boolean.TRUE;
            }
            if (ordinal == 3) {
                return Boolean.FALSE;
            }
        }
        return bool;
    }

    public static zzpv zzz(Context context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzb == null) {
            synchronized (zzpv.class) {
                try {
                    if (zzb == null) {
                        zzb = new zzpv((zzpw) Preconditions.checkNotNull(new zzpw(context)), (zzio) null);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return zzb;
    }

    public final zzqa zzA() {
        zzqa zzqa = this.zzi;
        zzaR(zzqa);
        return zzqa;
    }

    public final zzqf zzB() {
        return ((zzio) Preconditions.checkNotNull(this.zzn)).zzw();
    }

    /* access modifiers changed from: package-private */
    public final String zzC(zzjx zzjx) {
        if (!zzjx.zzr(zzjw.ANALYTICS_STORAGE)) {
            return null;
        }
        byte[] bArr = new byte[16];
        zzB().zzJ().nextBytes(bArr);
        return String.format(Locale.US, "%032x", new Object[]{new BigInteger(1, bArr)});
    }

    /* access modifiers changed from: package-private */
    public final String zzD(zzr zzr2) {
        try {
            return (String) zzaX().zzf(new zzpo(this, zzr2)).get(30000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            zzaW().zze().zzc("Failed to get app instance id. appId", zzhe.zzn(zzr2.zza), e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final List zzF(zzr zzr2, Bundle bundle) {
        zzaX().zzg();
        zzqr.zzb();
        zzam zzi2 = zzi();
        String str = zzr2.zza;
        if (!zzi2.zzx(str, zzgi.zzaV) || str == null) {
            return new ArrayList();
        }
        if (bundle != null) {
            int[] intArray = bundle.getIntArray("uriSources");
            long[] longArray = bundle.getLongArray("uriTimestamps");
            if (intArray != null) {
                if (longArray == null || longArray.length != intArray.length) {
                    zzaW().zze().zza("Uri sources and timestamps do not match");
                } else {
                    for (int i = 0; i < intArray.length; i++) {
                        zzaw zzj2 = zzj();
                        int i2 = intArray[i];
                        long j = longArray[i];
                        Preconditions.checkNotEmpty(str);
                        zzj2.zzg();
                        zzj2.zzav();
                        try {
                            int delete = zzj2.zzj().delete("trigger_uris", "app_id=? and source=? and timestamp_millis<=?", new String[]{str, String.valueOf(i2), String.valueOf(j)});
                            zzj2.zzu.zzaW().zzj().zzd("Pruned " + delete + " trigger URIs. appId, source, timestamp", str, Integer.valueOf(i2), Long.valueOf(j));
                        } catch (SQLiteException e) {
                            zzj2.zzu.zzaW().zze().zzc("Error pruning trigger URIs. appId", zzhe.zzn(str), e);
                        }
                    }
                }
            }
        }
        zzaw zzj3 = zzj();
        String str2 = zzr2.zza;
        Preconditions.checkNotEmpty(str2);
        zzj3.zzg();
        zzj3.zzav();
        List arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            cursor = zzj3.zzj().query("trigger_uris", new String[]{"trigger_uri", "timestamp_millis", "source"}, "app_id=?", new String[]{str2}, (String) null, (String) null, "rowid", (String) null);
            if (cursor.moveToFirst()) {
                do {
                    String string = cursor.getString(0);
                    if (string == null) {
                        string = "";
                    }
                    arrayList.add(new zzov(string, cursor.getLong(1), cursor.getInt(2)));
                } while (cursor.moveToNext());
            }
        } catch (SQLiteException e2) {
            zzj3.zzu.zzaW().zze().zzc("Error querying trigger uris. appId", zzhe.zzn(str2), e2);
            arrayList = Collections.emptyList();
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        if (cursor != null) {
            cursor.close();
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public final void zzK(Runnable runnable) {
        zzaX().zzg();
        if (this.zzq == null) {
            this.zzq = new ArrayList();
        }
        this.zzq.add(runnable);
    }

    /* access modifiers changed from: package-private */
    public final void zzL() {
        zzaX().zzg();
        zzM();
        if (!this.zzp) {
            this.zzp = true;
            if (zzaz()) {
                FileChannel fileChannel = this.zzy;
                zzaX().zzg();
                int i = 0;
                if (fileChannel == null || !fileChannel.isOpen()) {
                    zzaW().zze().zza("Bad channel to read from");
                } else {
                    ByteBuffer allocate = ByteBuffer.allocate(4);
                    try {
                        fileChannel.position(0);
                        int read = fileChannel.read(allocate);
                        if (read == 4) {
                            allocate.flip();
                            i = allocate.getInt();
                        } else if (read != -1) {
                            zzaW().zzk().zzb("Unexpected data length. Bytes read", Integer.valueOf(read));
                        }
                    } catch (IOException e) {
                        zzaW().zze().zzb("Failed to read from channel", e);
                    }
                }
                int zzi2 = this.zzn.zzh().zzi();
                zzaX().zzg();
                if (i > zzi2) {
                    zzaW().zze().zzc("Panic: can't downgrade version. Previous, current version", Integer.valueOf(i), Integer.valueOf(zzi2));
                } else if (i < zzi2) {
                    FileChannel fileChannel2 = this.zzy;
                    zzaX().zzg();
                    if (fileChannel2 == null || !fileChannel2.isOpen()) {
                        zzaW().zze().zza("Bad channel to read from");
                    } else {
                        ByteBuffer allocate2 = ByteBuffer.allocate(4);
                        allocate2.putInt(zzi2);
                        allocate2.flip();
                        try {
                            fileChannel2.truncate(0);
                            fileChannel2.write(allocate2);
                            fileChannel2.force(true);
                            if (fileChannel2.size() != 4) {
                                zzaW().zze().zzb("Error writing to channel. Bytes written", Long.valueOf(fileChannel2.size()));
                            }
                            zzaW().zzj().zzc("Storage version upgraded. Previous, current version", Integer.valueOf(i), Integer.valueOf(zzi2));
                            return;
                        } catch (IOException e2) {
                            zzaW().zze().zzb("Failed to write to channel", e2);
                        }
                    }
                    zzaW().zze().zzc("Storage version upgrade failed. Previous, current version", Integer.valueOf(i), Integer.valueOf(zzi2));
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzM() {
        if (!this.zzo.get()) {
            throw new IllegalStateException("UploadController is not initialized");
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzN(String str, zzhw zzhw) {
        int zza2;
        int indexOf;
        Set zzo2 = zzr().zzo(str);
        if (zzo2 != null) {
            zzhw.zzl(zzo2);
        }
        if (zzr().zzB(str)) {
            zzhw.zzs();
        }
        if (zzr().zzE(str)) {
            String zzaK = zzhw.zzaK();
            if (!TextUtils.isEmpty(zzaK) && (indexOf = zzaK.indexOf(".")) != -1) {
                zzhw.zzam(zzaK.substring(0, indexOf));
            }
        }
        if (zzr().zzF(str) && (zza2 = zzqa.zza(zzhw, "_id")) != -1) {
            zzhw.zzE(zza2);
        }
        if (zzr().zzD(str)) {
            zzhw.zzt();
        }
        if (zzr().zzA(str)) {
            zzhw.zzq();
            if (zzu(str).zzr(zzjw.ANALYTICS_STORAGE)) {
                Map map = this.zzE;
                zzps zzps = (zzps) map.get(str);
                if (zzps == null || zzps.zzb + zzi().zzk(str, zzgi.zzaj) < zzaU().elapsedRealtime()) {
                    zzps = new zzps(this);
                    map.put(str, zzps);
                }
                zzhw.zzac(zzps.zza);
            }
        }
        if (zzr().zzC(str)) {
            zzhw.zzA();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzO(zzh zzh2) {
        zzaX().zzg();
        if (!TextUtils.isEmpty(zzh2.zzH()) || !TextUtils.isEmpty(zzh2.zzA())) {
            String str = (String) Preconditions.checkNotNull(zzh2.zzC());
            zzaW().zzj().zzb("Fetching remote configuration", str);
            zzgo zzj2 = zzr().zzj(str);
            String zzl2 = zzr().zzl(str);
            ArrayMap arrayMap = null;
            if (zzj2 != null) {
                if (!TextUtils.isEmpty(zzl2)) {
                    arrayMap = new ArrayMap();
                    arrayMap.put("If-Modified-Since", zzl2);
                }
                String zzk2 = zzr().zzk(str);
                if (!TextUtils.isEmpty(zzk2)) {
                    if (arrayMap == null) {
                        arrayMap = new ArrayMap();
                    }
                    arrayMap.put("If-None-Match", zzk2);
                }
            }
            this.zzu = true;
            zzp().zza(zzh2, arrayMap, new zzpj(this));
            return;
        }
        zzW((String) Preconditions.checkNotNull(zzh2.zzC()), 204, (Throwable) null, (byte[]) null, (Map) null);
    }

    /* access modifiers changed from: package-private */
    public final void zzP(zzr zzr2, long j) {
        zzr zzr3 = zzr2;
        zzh zzl2 = zzj().zzl((String) Preconditions.checkNotNull(zzr3.zza));
        if (zzl2 != null && zzB().zzaw(zzr3.zzb, zzl2.zzH(), zzr3.zzp, zzl2.zzA())) {
            zzaW().zzk().zzb("New GMP App Id passed in. Removing cached database data. appId", zzhe.zzn(zzl2.zzC()));
            zzaw zzj2 = zzj();
            String zzC2 = zzl2.zzC();
            zzj2.zzav();
            zzj2.zzg();
            Preconditions.checkNotEmpty(zzC2);
            try {
                SQLiteDatabase zzj3 = zzj2.zzj();
                String[] strArr = {zzC2};
                int delete = zzj3.delete("events", "app_id=?", strArr) + zzj3.delete("user_attributes", "app_id=?", strArr) + zzj3.delete("conditional_properties", "app_id=?", strArr) + zzj3.delete("apps", "app_id=?", strArr) + zzj3.delete("raw_events", "app_id=?", strArr) + zzj3.delete("raw_events_metadata", "app_id=?", strArr) + zzj3.delete("event_filters", "app_id=?", strArr) + zzj3.delete("property_filters", "app_id=?", strArr) + zzj3.delete("audience_filter_values", "app_id=?", strArr) + zzj3.delete("consent_settings", "app_id=?", strArr) + zzj3.delete("default_event_params", "app_id=?", strArr) + zzj3.delete("trigger_uris", "app_id=?", strArr);
                if (delete > 0) {
                    zzj2.zzu.zzaW().zzj().zzc("Deleted application data. app, records", zzC2, Integer.valueOf(delete));
                }
            } catch (SQLiteException e) {
                zzj2.zzu.zzaW().zze().zzc("Error deleting application data. appId, error", zzhe.zzn(zzC2), e);
            }
            zzl2 = null;
        }
        if (zzl2 != null) {
            boolean z = true;
            boolean z2 = (zzl2.zze() == -2147483648L || zzl2.zze() == zzr3.zzj) ? false : true;
            String zzF2 = zzl2.zzF();
            if (zzl2.zze() != -2147483648L || zzF2 == null || zzF2.equals(zzr3.zzc)) {
                z = false;
            }
            if (z2 || z) {
                Bundle bundle = new Bundle();
                bundle.putString("_pv", zzF2);
                zzbh zzbh = new zzbh("_au", new zzbf(bundle), "auto", j);
                if (zzi().zzx((String) null, zzgi.zzbm)) {
                    zzU(zzbh, zzr3);
                } else {
                    zzS(zzbh, zzr3);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzQ(zzh zzh2, zzhw zzhw) {
        zzio zzio;
        zzaX().zzg();
        zzM();
        zzao zzb2 = zzao.zzb(zzhw.zzaH());
        String zzC2 = zzh2.zzC();
        zzaX().zzg();
        zzM();
        zzjx zzu2 = zzu(zzC2);
        zzju zzju = zzju.UNINITIALIZED;
        int ordinal = zzu2.zze().ordinal();
        if (ordinal == 1) {
            zzb2.zzd(zzjw.AD_STORAGE, zzan.REMOTE_ENFORCED_DEFAULT);
        } else if (ordinal == 2 || ordinal == 3) {
            zzb2.zzc(zzjw.AD_STORAGE, zzu2.zzb());
        } else {
            zzb2.zzd(zzjw.AD_STORAGE, zzan.FAILSAFE);
        }
        int ordinal2 = zzu2.zzf().ordinal();
        if (ordinal2 == 1) {
            zzb2.zzd(zzjw.ANALYTICS_STORAGE, zzan.REMOTE_ENFORCED_DEFAULT);
        } else if (ordinal2 == 2 || ordinal2 == 3) {
            zzb2.zzc(zzjw.ANALYTICS_STORAGE, zzu2.zzb());
        } else {
            zzb2.zzd(zzjw.ANALYTICS_STORAGE, zzan.FAILSAFE);
        }
        String zzC3 = zzh2.zzC();
        zzaX().zzg();
        zzM();
        zzba zzl2 = zzl(zzC3, zzm(zzC3), zzu(zzC3), zzb2);
        zzhw.zzak(((Boolean) Preconditions.checkNotNull(zzl2.zzh())).booleanValue());
        if (!TextUtils.isEmpty(zzl2.zzi())) {
            zzhw.zzU(zzl2.zzi());
        }
        zzaX().zzg();
        zzM();
        Iterator it = zzhw.zzaN().iterator();
        while (true) {
            if (!it.hasNext()) {
                zzio = null;
                break;
            }
            zzio = (zzio) it.next();
            if ("_npa".equals(zzio.zzg())) {
                break;
            }
        }
        if (zzio != null) {
            zzjw zzjw = zzjw.AD_PERSONALIZATION;
            if (zzb2.zza(zzjw) == zzan.UNSET) {
                zzqd zzy2 = zzj().zzy(zzh2.zzC(), "_npa");
                if (zzy2 != null) {
                    String str = zzy2.zzb;
                    if ("tcf".equals(str)) {
                        zzb2.zzd(zzjw, zzan.TCF);
                    } else if ("app".equals(str)) {
                        zzb2.zzd(zzjw, zzan.API);
                    } else {
                        zzb2.zzd(zzjw, zzan.MANIFEST);
                    }
                } else {
                    Boolean zzx2 = zzh2.zzx();
                    if (zzx2 == null || ((zzx2.booleanValue() && zzio.zzc() != 1) || (!zzx2.booleanValue() && zzio.zzc() != 0))) {
                        zzb2.zzd(zzjw, zzan.API);
                    } else {
                        zzb2.zzd(zzjw, zzan.MANIFEST);
                    }
                }
            }
        } else {
            int zzaC = zzaC(zzh2.zzC(), zzb2);
            zzin zze2 = zzio.zze();
            zze2.zzf("_npa");
            zze2.zzg(zzaU().currentTimeMillis());
            zze2.zze((long) zzaC);
            zzhw.zzp((zzio) zze2.zzba());
            zzaW().zzj().zzc("Setting user property", "non_personalized_ads(_npa)", Integer.valueOf(zzaC));
        }
        zzhw.zzS(zzb2.toString());
        boolean zzv2 = this.zzc.zzv(zzh2.zzC());
        List zzaM = zzhw.zzaM();
        int i = 0;
        for (int i2 = 0; i2 < zzaM.size(); i2++) {
            if ("_tcf".equals(((zzhm) zzaM.get(i2)).zzh())) {
                zzhl zzhl = (zzhl) ((zzhm) zzaM.get(i2)).zzch();
                List zzp2 = zzhl.zzp();
                int i3 = 0;
                while (true) {
                    if (i3 >= zzp2.size()) {
                        break;
                    } else if ("_tcfd".equals(((zzhq) zzp2.get(i3)).zzg())) {
                        String zzh3 = ((zzhq) zzp2.get(i3)).zzh();
                        if (zzv2 && zzh3.length() > 4) {
                            char[] charArray = zzh3.toCharArray();
                            int i4 = 1;
                            while (true) {
                                if (i4 >= 64) {
                                    break;
                                } else if (charArray[4] == "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_".charAt(i4)) {
                                    i = i4;
                                    break;
                                } else {
                                    i4++;
                                }
                            }
                            charArray[4] = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_".charAt(1 | i);
                            zzh3 = String.valueOf(charArray);
                        }
                        zzhp zze3 = zzhq.zze();
                        zze3.zzj("_tcfd");
                        zze3.zzk(zzh3);
                        zzhl.zzj(i3, zze3);
                    } else {
                        i3++;
                    }
                }
                zzhw.zzad(i2, zzhl);
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzR(zzhw zzhw, zzpr zzpr) {
        zzhw zzhw2 = zzhw;
        zzpr zzpr2 = zzpr;
        for (int i = 0; i < zzhw.zzc(); i++) {
            zzhl zzhl = (zzhl) zzhw2.zzh(i).zzch();
            Iterator it = zzhl.zzp().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if ("_c".equals(((zzhq) it.next()).zzg())) {
                    if (zzpr2.zza.zza() >= zzi().zzh(zzpr2.zza.zzF(), zzgi.zzak)) {
                        int zzh2 = zzi().zzh(zzpr2.zza.zzF(), zzgi.zzax);
                        String str = null;
                        if (zzh2 <= 0) {
                            if (zzi().zzx(zzpr2.zza.zzF(), zzgi.zzaX)) {
                                str = zzB().zzF();
                                zzhp zze2 = zzhq.zze();
                                zze2.zzj("_tu");
                                zze2.zzk(str);
                                zzhl.zzf((zzhq) zze2.zzba());
                            }
                            zzhp zze3 = zzhq.zze();
                            zze3.zzj("_tr");
                            zze3.zzi(1);
                            zzhl.zzf((zzhq) zze3.zzba());
                            zzov zzl2 = zzA().zzl(zzpr2.zza.zzF(), zzhw2, zzhl, str);
                            if (zzl2 != null) {
                                zzaW().zzj().zzc("Generated trigger URI. appId, uri", zzpr2.zza.zzF(), zzl2.zza);
                                zzj().zzad(zzpr2.zza.zzF(), zzl2);
                                Deque deque = this.zzr;
                                if (!deque.contains(zzpr2.zza.zzF())) {
                                    deque.add(zzpr2.zza.zzF());
                                }
                            }
                        } else if (zzj().zzo(zza(), zzpr2.zza.zzF(), false, false, false, false, false, false, true).zzg > ((long) zzh2)) {
                            zzhp zze4 = zzhq.zze();
                            zze4.zzj("_tnr");
                            zze4.zzi(1);
                            zzhl.zzf((zzhq) zze4.zzba());
                        } else {
                            if (zzi().zzx(zzpr2.zza.zzF(), zzgi.zzaX)) {
                                str = zzB().zzF();
                                zzhp zze5 = zzhq.zze();
                                zze5.zzj("_tu");
                                zze5.zzk(str);
                                zzhl.zzf((zzhq) zze5.zzba());
                            }
                            zzhp zze6 = zzhq.zze();
                            zze6.zzj("_tr");
                            zze6.zzi(1);
                            zzhl.zzf((zzhq) zze6.zzba());
                            zzov zzl3 = zzA().zzl(zzpr2.zza.zzF(), zzhw2, zzhl, str);
                            if (zzl3 != null) {
                                zzaW().zzj().zzc("Generated trigger URI. appId, uri", zzpr2.zza.zzF(), zzl3.zza);
                                zzj().zzad(zzpr2.zza.zzF(), zzl3);
                                Deque deque2 = this.zzr;
                                if (!deque2.contains(zzpr2.zza.zzF())) {
                                    deque2.add(zzpr2.zza.zzF());
                                }
                            }
                        }
                    }
                    zzhw2.zzae(i, (zzhm) zzhl.zzba());
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x002e, code lost:
        r6 = r1.zzH;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzS(com.google.android.gms.measurement.internal.zzbh r21, com.google.android.gms.measurement.internal.zzr r22) {
        /*
            r20 = this;
            r1 = r20
            r0 = r22
            java.lang.String r2 = "_s"
            java.lang.String r3 = "_sid"
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r22)
            java.lang.String r4 = r0.zza
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r4)
            com.google.android.gms.measurement.internal.zzil r5 = r20.zzaX()
            r5.zzg()
            r20.zzM()
            r5 = r21
            long r12 = r5.zzd
            com.google.android.gms.measurement.internal.zzhf r5 = com.google.android.gms.measurement.internal.zzhf.zzb(r21)
            com.google.android.gms.measurement.internal.zzil r6 = r20.zzaX()
            r6.zzg()
            com.google.android.gms.measurement.internal.zzmh r6 = r1.zzG
            r7 = 0
            if (r6 == 0) goto L_0x0038
            java.lang.String r6 = r1.zzH
            if (r6 == 0) goto L_0x0038
            boolean r6 = r6.equals(r4)
            if (r6 != 0) goto L_0x003a
        L_0x0038:
            r6 = r7
            goto L_0x003c
        L_0x003a:
            com.google.android.gms.measurement.internal.zzmh r6 = r1.zzG
        L_0x003c:
            android.os.Bundle r8 = r5.zzd
            r9 = 0
            com.google.android.gms.measurement.internal.zzqf.zzN(r6, r8, r9)
            com.google.android.gms.measurement.internal.zzbh r5 = r5.zza()
            r20.zzA()
            boolean r6 = com.google.android.gms.measurement.internal.zzqa.zzE(r5, r0)
            if (r6 != 0) goto L_0x0050
            return
        L_0x0050:
            boolean r6 = r0.zzh
            if (r6 != 0) goto L_0x0058
            r1.zzg(r0)
            return
        L_0x0058:
            java.util.List r6 = r0.zzs
            if (r6 == 0) goto L_0x0099
            java.lang.String r15 = r5.zza
            boolean r6 = r6.contains(r15)
            if (r6 == 0) goto L_0x0087
            com.google.android.gms.measurement.internal.zzbf r6 = r5.zzb
            android.os.Bundle r6 = r6.zzc()
            java.lang.String r8 = "ga_safelisted"
            r9 = 1
            r6.putLong(r8, r9)
            com.google.android.gms.measurement.internal.zzbh r8 = new com.google.android.gms.measurement.internal.zzbh
            com.google.android.gms.measurement.internal.zzbf r9 = new com.google.android.gms.measurement.internal.zzbf
            r9.<init>(r6)
            java.lang.String r6 = r5.zzc
            long r10 = r5.zzd
            r14 = r8
            r16 = r9
            r17 = r6
            r18 = r10
            r14.<init>(r15, r16, r17, r18)
            goto L_0x009a
        L_0x0087:
            com.google.android.gms.measurement.internal.zzhe r0 = r20.zzaW()
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzd()
            java.lang.String r2 = r5.zza
            java.lang.String r3 = r5.zzc
            java.lang.String r5 = "Dropping non-safelisted event. appId, event name, origin"
            r0.zzd(r5, r4, r2, r3)
            return
        L_0x0099:
            r14 = r5
        L_0x009a:
            com.google.android.gms.measurement.internal.zzaw r5 = r20.zzj()
            r5.zzH()
            com.google.android.gms.internal.measurement.zzra.zzb()     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzam r5 = r20.zzi()     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzgg r6 = com.google.android.gms.measurement.internal.zzgi.zzbi     // Catch:{ all -> 0x0106 }
            boolean r5 = r5.zzx(r7, r6)     // Catch:{ all -> 0x0106 }
            r8 = 0
            if (r5 == 0) goto L_0x0114
            java.lang.String r5 = r14.zza     // Catch:{ all -> 0x0106 }
            boolean r5 = r2.equals(r5)     // Catch:{ all -> 0x0106 }
            if (r5 == 0) goto L_0x0114
            com.google.android.gms.measurement.internal.zzaw r5 = r20.zzj()     // Catch:{ all -> 0x0106 }
            boolean r2 = r5.zzZ(r4, r2)     // Catch:{ all -> 0x0106 }
            if (r2 != 0) goto L_0x0114
            com.google.android.gms.measurement.internal.zzbf r2 = r14.zzb     // Catch:{ all -> 0x0106 }
            java.lang.Long r2 = r2.zze(r3)     // Catch:{ all -> 0x0106 }
            long r5 = r2.longValue()     // Catch:{ all -> 0x0106 }
            int r2 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r2 == 0) goto L_0x0114
            com.google.android.gms.measurement.internal.zzaw r2 = r20.zzj()     // Catch:{ all -> 0x0106 }
            java.lang.String r5 = "_f"
            boolean r2 = r2.zzZ(r4, r5)     // Catch:{ all -> 0x0106 }
            if (r2 != 0) goto L_0x0109
            com.google.android.gms.measurement.internal.zzaw r2 = r20.zzj()     // Catch:{ all -> 0x0106 }
            java.lang.String r5 = "_v"
            boolean r2 = r2.zzZ(r4, r5)     // Catch:{ all -> 0x0106 }
            if (r2 == 0) goto L_0x00eb
            goto L_0x0109
        L_0x00eb:
            com.google.android.gms.measurement.internal.zzaw r2 = r20.zzj()     // Catch:{ all -> 0x0106 }
            com.google.android.gms.common.util.Clock r5 = r20.zzaU()     // Catch:{ all -> 0x0106 }
            long r5 = r5.currentTimeMillis()     // Catch:{ all -> 0x0106 }
            r10 = -15000(0xffffffffffffc568, double:NaN)
            long r5 = r5 + r10
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0106 }
            android.os.Bundle r6 = r1.zzf(r4, r14)     // Catch:{ all -> 0x0106 }
            r2.zzG(r4, r5, r3, r6)     // Catch:{ all -> 0x0106 }
            goto L_0x0114
        L_0x0106:
            r0 = move-exception
            goto L_0x0356
        L_0x0109:
            com.google.android.gms.measurement.internal.zzaw r2 = r20.zzj()     // Catch:{ all -> 0x0106 }
            android.os.Bundle r5 = r1.zzf(r4, r14)     // Catch:{ all -> 0x0106 }
            r2.zzG(r4, r7, r3, r5)     // Catch:{ all -> 0x0106 }
        L_0x0114:
            com.google.android.gms.measurement.internal.zzaw r2 = r20.zzj()     // Catch:{ all -> 0x0106 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r4)     // Catch:{ all -> 0x0106 }
            r2.zzg()     // Catch:{ all -> 0x0106 }
            r2.zzav()     // Catch:{ all -> 0x0106 }
            int r3 = (r12 > r8 ? 1 : (r12 == r8 ? 0 : -1))
            if (r3 >= 0) goto L_0x0141
            com.google.android.gms.measurement.internal.zzio r2 = r2.zzu     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzhe r2 = r2.zzaW()     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zzk()     // Catch:{ all -> 0x0106 }
            java.lang.String r5 = "Invalid time querying timed out conditional properties"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzhe.zzn(r4)     // Catch:{ all -> 0x0106 }
            java.lang.Long r7 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x0106 }
            r2.zzc(r5, r6, r7)     // Catch:{ all -> 0x0106 }
            java.util.List r2 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0106 }
            goto L_0x014f
        L_0x0141:
            java.lang.String r5 = "active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout"
            java.lang.String r6 = java.lang.String.valueOf(r12)     // Catch:{ all -> 0x0106 }
            java.lang.String[] r6 = new java.lang.String[]{r4, r6}     // Catch:{ all -> 0x0106 }
            java.util.List r2 = r2.zzC(r5, r6)     // Catch:{ all -> 0x0106 }
        L_0x014f:
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x0106 }
        L_0x0153:
            boolean r5 = r2.hasNext()     // Catch:{ all -> 0x0106 }
            if (r5 == 0) goto L_0x019c
            java.lang.Object r5 = r2.next()     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzai r5 = (com.google.android.gms.measurement.internal.zzai) r5     // Catch:{ all -> 0x0106 }
            if (r5 == 0) goto L_0x0153
            com.google.android.gms.measurement.internal.zzhe r6 = r20.zzaW()     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzhc r6 = r6.zzj()     // Catch:{ all -> 0x0106 }
            java.lang.String r7 = "User property timed out"
            java.lang.String r8 = r5.zza     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzio r9 = r1.zzn     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzgx r9 = r9.zzj()     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzqb r10 = r5.zzc     // Catch:{ all -> 0x0106 }
            java.lang.String r10 = r10.zzb     // Catch:{ all -> 0x0106 }
            java.lang.String r9 = r9.zzf(r10)     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzqb r10 = r5.zzc     // Catch:{ all -> 0x0106 }
            java.lang.Object r10 = r10.zza()     // Catch:{ all -> 0x0106 }
            r6.zzd(r7, r8, r9, r10)     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzbh r6 = r5.zzg     // Catch:{ all -> 0x0106 }
            if (r6 == 0) goto L_0x0190
            com.google.android.gms.measurement.internal.zzbh r7 = new com.google.android.gms.measurement.internal.zzbh     // Catch:{ all -> 0x0106 }
            r7.<init>(r6, r12)     // Catch:{ all -> 0x0106 }
            r1.zzax(r7, r0)     // Catch:{ all -> 0x0106 }
        L_0x0190:
            com.google.android.gms.measurement.internal.zzaw r6 = r20.zzj()     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzqb r5 = r5.zzc     // Catch:{ all -> 0x0106 }
            java.lang.String r5 = r5.zzb     // Catch:{ all -> 0x0106 }
            r6.zza(r4, r5)     // Catch:{ all -> 0x0106 }
            goto L_0x0153
        L_0x019c:
            com.google.android.gms.measurement.internal.zzaw r2 = r20.zzj()     // Catch:{ all -> 0x0106 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r4)     // Catch:{ all -> 0x0106 }
            r2.zzg()     // Catch:{ all -> 0x0106 }
            r2.zzav()     // Catch:{ all -> 0x0106 }
            if (r3 >= 0) goto L_0x01c7
            com.google.android.gms.measurement.internal.zzio r2 = r2.zzu     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzhe r2 = r2.zzaW()     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zzk()     // Catch:{ all -> 0x0106 }
            java.lang.String r5 = "Invalid time querying expired conditional properties"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzhe.zzn(r4)     // Catch:{ all -> 0x0106 }
            java.lang.Long r7 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x0106 }
            r2.zzc(r5, r6, r7)     // Catch:{ all -> 0x0106 }
            java.util.List r2 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0106 }
            goto L_0x01d5
        L_0x01c7:
            java.lang.String r5 = "active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live"
            java.lang.String r6 = java.lang.String.valueOf(r12)     // Catch:{ all -> 0x0106 }
            java.lang.String[] r6 = new java.lang.String[]{r4, r6}     // Catch:{ all -> 0x0106 }
            java.util.List r2 = r2.zzC(r5, r6)     // Catch:{ all -> 0x0106 }
        L_0x01d5:
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x0106 }
            int r6 = r2.size()     // Catch:{ all -> 0x0106 }
            r5.<init>(r6)     // Catch:{ all -> 0x0106 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x0106 }
        L_0x01e2:
            boolean r6 = r2.hasNext()     // Catch:{ all -> 0x0106 }
            if (r6 == 0) goto L_0x0231
            java.lang.Object r6 = r2.next()     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzai r6 = (com.google.android.gms.measurement.internal.zzai) r6     // Catch:{ all -> 0x0106 }
            if (r6 == 0) goto L_0x01e2
            com.google.android.gms.measurement.internal.zzhe r7 = r20.zzaW()     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzhc r7 = r7.zzj()     // Catch:{ all -> 0x0106 }
            java.lang.String r8 = "User property expired"
            java.lang.String r9 = r6.zza     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzio r10 = r1.zzn     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzgx r10 = r10.zzj()     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzqb r11 = r6.zzc     // Catch:{ all -> 0x0106 }
            java.lang.String r11 = r11.zzb     // Catch:{ all -> 0x0106 }
            java.lang.String r10 = r10.zzf(r11)     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzqb r11 = r6.zzc     // Catch:{ all -> 0x0106 }
            java.lang.Object r11 = r11.zza()     // Catch:{ all -> 0x0106 }
            r7.zzd(r8, r9, r10, r11)     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzaw r7 = r20.zzj()     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzqb r8 = r6.zzc     // Catch:{ all -> 0x0106 }
            java.lang.String r8 = r8.zzb     // Catch:{ all -> 0x0106 }
            r7.zzP(r4, r8)     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzbh r7 = r6.zzk     // Catch:{ all -> 0x0106 }
            if (r7 == 0) goto L_0x0225
            r5.add(r7)     // Catch:{ all -> 0x0106 }
        L_0x0225:
            com.google.android.gms.measurement.internal.zzaw r7 = r20.zzj()     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzqb r6 = r6.zzc     // Catch:{ all -> 0x0106 }
            java.lang.String r6 = r6.zzb     // Catch:{ all -> 0x0106 }
            r7.zza(r4, r6)     // Catch:{ all -> 0x0106 }
            goto L_0x01e2
        L_0x0231:
            java.util.Iterator r2 = r5.iterator()     // Catch:{ all -> 0x0106 }
        L_0x0235:
            boolean r5 = r2.hasNext()     // Catch:{ all -> 0x0106 }
            if (r5 == 0) goto L_0x024a
            java.lang.Object r5 = r2.next()     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzbh r5 = (com.google.android.gms.measurement.internal.zzbh) r5     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzbh r6 = new com.google.android.gms.measurement.internal.zzbh     // Catch:{ all -> 0x0106 }
            r6.<init>(r5, r12)     // Catch:{ all -> 0x0106 }
            r1.zzax(r6, r0)     // Catch:{ all -> 0x0106 }
            goto L_0x0235
        L_0x024a:
            com.google.android.gms.measurement.internal.zzaw r2 = r20.zzj()     // Catch:{ all -> 0x0106 }
            java.lang.String r5 = r14.zza     // Catch:{ all -> 0x0106 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r4)     // Catch:{ all -> 0x0106 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r5)     // Catch:{ all -> 0x0106 }
            r2.zzg()     // Catch:{ all -> 0x0106 }
            r2.zzav()     // Catch:{ all -> 0x0106 }
            if (r3 >= 0) goto L_0x0282
            com.google.android.gms.measurement.internal.zzio r2 = r2.zzu     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzhe r3 = r2.zzaW()     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzhc r3 = r3.zzk()     // Catch:{ all -> 0x0106 }
            java.lang.String r6 = "Invalid time querying triggered conditional properties"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzhe.zzn(r4)     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzgx r2 = r2.zzj()     // Catch:{ all -> 0x0106 }
            java.lang.String r2 = r2.zzd(r5)     // Catch:{ all -> 0x0106 }
            java.lang.Long r5 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x0106 }
            r3.zzd(r6, r4, r2, r5)     // Catch:{ all -> 0x0106 }
            java.util.List r2 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0106 }
            goto L_0x0290
        L_0x0282:
            java.lang.String r3 = "active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout"
            java.lang.String r6 = java.lang.String.valueOf(r12)     // Catch:{ all -> 0x0106 }
            java.lang.String[] r4 = new java.lang.String[]{r4, r5, r6}     // Catch:{ all -> 0x0106 }
            java.util.List r2 = r2.zzC(r3, r4)     // Catch:{ all -> 0x0106 }
        L_0x0290:
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x0106 }
            int r4 = r2.size()     // Catch:{ all -> 0x0106 }
            r3.<init>(r4)     // Catch:{ all -> 0x0106 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x0106 }
        L_0x029d:
            boolean r4 = r2.hasNext()     // Catch:{ all -> 0x0106 }
            if (r4 == 0) goto L_0x032b
            java.lang.Object r4 = r2.next()     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzai r4 = (com.google.android.gms.measurement.internal.zzai) r4     // Catch:{ all -> 0x0106 }
            if (r4 == 0) goto L_0x029d
            com.google.android.gms.measurement.internal.zzqb r5 = r4.zzc     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzqd r15 = new com.google.android.gms.measurement.internal.zzqd     // Catch:{ all -> 0x0106 }
            java.lang.String r6 = r4.zza     // Catch:{ all -> 0x0106 }
            java.lang.Object r6 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r6)     // Catch:{ all -> 0x0106 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ all -> 0x0106 }
            java.lang.String r7 = r4.zzb     // Catch:{ all -> 0x0106 }
            java.lang.String r8 = r5.zzb     // Catch:{ all -> 0x0106 }
            java.lang.Object r5 = r5.zza()     // Catch:{ all -> 0x0106 }
            java.lang.Object r11 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r5)     // Catch:{ all -> 0x0106 }
            r5 = r15
            r9 = r12
            r5.<init>(r6, r7, r8, r9, r11)     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzaw r5 = r20.zzj()     // Catch:{ all -> 0x0106 }
            boolean r5 = r5.zzai(r15)     // Catch:{ all -> 0x0106 }
            if (r5 == 0) goto L_0x02f0
            com.google.android.gms.measurement.internal.zzhe r5 = r20.zzaW()     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzhc r5 = r5.zzj()     // Catch:{ all -> 0x0106 }
            java.lang.String r6 = "User property triggered"
            java.lang.String r7 = r4.zza     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzio r8 = r1.zzn     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzgx r8 = r8.zzj()     // Catch:{ all -> 0x0106 }
            java.lang.String r9 = r15.zzc     // Catch:{ all -> 0x0106 }
            java.lang.String r8 = r8.zzf(r9)     // Catch:{ all -> 0x0106 }
            java.lang.Object r9 = r15.zze     // Catch:{ all -> 0x0106 }
            r5.zzd(r6, r7, r8, r9)     // Catch:{ all -> 0x0106 }
            goto L_0x0311
        L_0x02f0:
            com.google.android.gms.measurement.internal.zzhe r5 = r20.zzaW()     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzhc r5 = r5.zze()     // Catch:{ all -> 0x0106 }
            java.lang.String r6 = "Too many active user properties, ignoring"
            java.lang.String r7 = r4.zza     // Catch:{ all -> 0x0106 }
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzhe.zzn(r7)     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzio r8 = r1.zzn     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzgx r8 = r8.zzj()     // Catch:{ all -> 0x0106 }
            java.lang.String r9 = r15.zzc     // Catch:{ all -> 0x0106 }
            java.lang.String r8 = r8.zzf(r9)     // Catch:{ all -> 0x0106 }
            java.lang.Object r9 = r15.zze     // Catch:{ all -> 0x0106 }
            r5.zzd(r6, r7, r8, r9)     // Catch:{ all -> 0x0106 }
        L_0x0311:
            com.google.android.gms.measurement.internal.zzbh r5 = r4.zzi     // Catch:{ all -> 0x0106 }
            if (r5 == 0) goto L_0x0318
            r3.add(r5)     // Catch:{ all -> 0x0106 }
        L_0x0318:
            com.google.android.gms.measurement.internal.zzqb r5 = new com.google.android.gms.measurement.internal.zzqb     // Catch:{ all -> 0x0106 }
            r5.<init>(r15)     // Catch:{ all -> 0x0106 }
            r4.zzc = r5     // Catch:{ all -> 0x0106 }
            r5 = 1
            r4.zze = r5     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzaw r5 = r20.zzj()     // Catch:{ all -> 0x0106 }
            r5.zzah(r4)     // Catch:{ all -> 0x0106 }
            goto L_0x029d
        L_0x032b:
            r1.zzax(r14, r0)     // Catch:{ all -> 0x0106 }
            java.util.Iterator r2 = r3.iterator()     // Catch:{ all -> 0x0106 }
        L_0x0332:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x0106 }
            if (r3 == 0) goto L_0x0347
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzbh r3 = (com.google.android.gms.measurement.internal.zzbh) r3     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzbh r4 = new com.google.android.gms.measurement.internal.zzbh     // Catch:{ all -> 0x0106 }
            r4.<init>(r3, r12)     // Catch:{ all -> 0x0106 }
            r1.zzax(r4, r0)     // Catch:{ all -> 0x0106 }
            goto L_0x0332
        L_0x0347:
            com.google.android.gms.measurement.internal.zzaw r0 = r20.zzj()     // Catch:{ all -> 0x0106 }
            r0.zzS()     // Catch:{ all -> 0x0106 }
            com.google.android.gms.measurement.internal.zzaw r0 = r20.zzj()
            r0.zzL()
            return
        L_0x0356:
            com.google.android.gms.measurement.internal.zzaw r2 = r20.zzj()
            r2.zzL()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzpv.zzS(com.google.android.gms.measurement.internal.zzbh, com.google.android.gms.measurement.internal.zzr):void");
    }

    /* access modifiers changed from: package-private */
    public final void zzT(zzbh zzbh, String str) {
        zzbh zzbh2 = zzbh;
        String str2 = str;
        zzh zzl2 = zzj().zzl(str2);
        if (zzl2 == null || TextUtils.isEmpty(zzl2.zzF())) {
            zzaW().zzd().zzb("No app data available; dropping event", str2);
            return;
        }
        Boolean zzaF = zzaF(zzl2);
        if (zzaF == null) {
            if (!"_ui".equals(zzbh2.zza)) {
                zzaW().zzk().zzb("Could not find package. appId", zzhe.zzn(str));
            }
        } else if (!zzaF.booleanValue()) {
            zzaW().zze().zzb("App version does not match; dropping event. appId", zzhe.zzn(str));
            return;
        }
        zzr zzr2 = r2;
        zzr zzr3 = new zzr(str, zzl2.zzH(), zzl2.zzF(), zzl2.zze(), zzl2.zzE(), zzl2.zzq(), zzl2.zzn(), (String) null, zzl2.zzaJ(), false, zzl2.zzG(), 0, 0, zzl2.zzaI(), false, zzl2.zzA(), zzl2.zzx(), zzl2.zzo(), zzl2.zzN(), (String) null, zzu(str2).zzq(), "", (String) null, zzl2.zzaL(), zzl2.zzw(), zzu(str2).zzb(), zzm(str2).zzj(), zzl2.zza(), zzl2.zzf(), zzl2.zzM(), zzl2.zzK(), 0, zzl2.zzb());
        zzU(zzbh2, zzr2);
    }

    /* access modifiers changed from: package-private */
    public final void zzU(zzbh zzbh, zzr zzr2) {
        String str = zzr2.zza;
        Preconditions.checkNotEmpty(str);
        zzhf zzb2 = zzhf.zzb(zzbh);
        zzB().zzO(zzb2.zzd, zzj().zzk(str));
        zzB().zzQ(zzb2, zzi().zzf(str));
        zzbh zza2 = zzb2.zza();
        if (Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN.equals(zza2.zza)) {
            zzbf zzbf = zza2.zzb;
            if ("referrer API v2".equals(zzbf.zzg("_cis"))) {
                String zzg2 = zzbf.zzg("gclid");
                if (!TextUtils.isEmpty(zzg2)) {
                    zzas(new zzqb("_lgclid", zza2.zzd, zzg2, "auto"), zzr2);
                }
            }
        }
        zzS(zza2, zzr2);
    }

    /* access modifiers changed from: package-private */
    public final void zzV() {
        this.zzt++;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x004c A[Catch:{ all -> 0x005f, all -> 0x0013 }] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0062 A[Catch:{ all -> 0x005f, all -> 0x0013 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00fd A[Catch:{ all -> 0x005f, all -> 0x0013 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x010b A[Catch:{ all -> 0x005f, all -> 0x0013 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x012c A[Catch:{ all -> 0x005f, all -> 0x0013 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0130 A[Catch:{ all -> 0x005f, all -> 0x0013 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzW(java.lang.String r7, int r8, java.lang.Throwable r9, byte[] r10, java.util.Map r11) {
        /*
            r6 = this;
            com.google.android.gms.measurement.internal.zzil r0 = r6.zzaX()
            r0.zzg()
            r6.zzM()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r7)
            r0 = 0
            if (r10 != 0) goto L_0x0016
            byte[] r10 = new byte[r0]     // Catch:{ all -> 0x0013 }
            goto L_0x0016
        L_0x0013:
            r7 = move-exception
            goto L_0x017b
        L_0x0016:
            com.google.android.gms.measurement.internal.zzhe r1 = r6.zzaW()     // Catch:{ all -> 0x0013 }
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zzj()     // Catch:{ all -> 0x0013 }
            java.lang.String r2 = "onConfigFetched. Response size"
            int r3 = r10.length     // Catch:{ all -> 0x0013 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0013 }
            r1.zzb(r2, r3)     // Catch:{ all -> 0x0013 }
            com.google.android.gms.measurement.internal.zzaw r1 = r6.zzj()     // Catch:{ all -> 0x0013 }
            r1.zzH()     // Catch:{ all -> 0x0013 }
            com.google.android.gms.measurement.internal.zzaw r1 = r6.zzj()     // Catch:{ all -> 0x005f }
            com.google.android.gms.measurement.internal.zzh r1 = r1.zzl(r7)     // Catch:{ all -> 0x005f }
            r2 = 200(0xc8, float:2.8E-43)
            r4 = 304(0x130, float:4.26E-43)
            if (r8 == r2) goto L_0x0047
            r2 = 204(0xcc, float:2.86E-43)
            if (r8 == r2) goto L_0x0047
            if (r8 != r4) goto L_0x0045
            r8 = r4
            goto L_0x0047
        L_0x0045:
            r2 = r0
            goto L_0x004a
        L_0x0047:
            if (r9 != 0) goto L_0x0045
            r2 = 1
        L_0x004a:
            if (r1 != 0) goto L_0x0062
            com.google.android.gms.measurement.internal.zzhe r8 = r6.zzaW()     // Catch:{ all -> 0x005f }
            com.google.android.gms.measurement.internal.zzhc r8 = r8.zzk()     // Catch:{ all -> 0x005f }
            java.lang.String r9 = "App does not exist in onConfigFetched. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzhe.zzn(r7)     // Catch:{ all -> 0x005f }
            r8.zzb(r9, r7)     // Catch:{ all -> 0x005f }
            goto L_0x015f
        L_0x005f:
            r7 = move-exception
            goto L_0x0173
        L_0x0062:
            r5 = 404(0x194, float:5.66E-43)
            if (r2 != 0) goto L_0x00be
            if (r8 != r5) goto L_0x0069
            goto L_0x00be
        L_0x0069:
            com.google.android.gms.common.util.Clock r10 = r6.zzaU()     // Catch:{ all -> 0x005f }
            long r10 = r10.currentTimeMillis()     // Catch:{ all -> 0x005f }
            r1.zzam(r10)     // Catch:{ all -> 0x005f }
            com.google.android.gms.measurement.internal.zzaw r10 = r6.zzj()     // Catch:{ all -> 0x005f }
            r10.zzT(r1, r0, r0)     // Catch:{ all -> 0x005f }
            com.google.android.gms.measurement.internal.zzhe r10 = r6.zzaW()     // Catch:{ all -> 0x005f }
            com.google.android.gms.measurement.internal.zzhc r10 = r10.zzj()     // Catch:{ all -> 0x005f }
            java.lang.String r11 = "Fetching config failed. code, error"
            java.lang.Integer r1 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x005f }
            r10.zzc(r11, r1, r9)     // Catch:{ all -> 0x005f }
            com.google.android.gms.measurement.internal.zzif r9 = r6.zzr()     // Catch:{ all -> 0x005f }
            r9.zzq(r7)     // Catch:{ all -> 0x005f }
            com.google.android.gms.measurement.internal.zzoa r7 = r6.zzk     // Catch:{ all -> 0x005f }
            com.google.android.gms.measurement.internal.zzhp r7 = r7.zze     // Catch:{ all -> 0x005f }
            com.google.android.gms.common.util.Clock r9 = r6.zzaU()     // Catch:{ all -> 0x005f }
            long r9 = r9.currentTimeMillis()     // Catch:{ all -> 0x005f }
            r7.zzb(r9)     // Catch:{ all -> 0x005f }
            r7 = 503(0x1f7, float:7.05E-43)
            if (r8 == r7) goto L_0x00aa
            r7 = 429(0x1ad, float:6.01E-43)
            if (r8 != r7) goto L_0x00b9
        L_0x00aa:
            com.google.android.gms.measurement.internal.zzoa r7 = r6.zzk     // Catch:{ all -> 0x005f }
            com.google.android.gms.measurement.internal.zzhp r7 = r7.zzc     // Catch:{ all -> 0x005f }
            com.google.android.gms.common.util.Clock r8 = r6.zzaU()     // Catch:{ all -> 0x005f }
            long r8 = r8.currentTimeMillis()     // Catch:{ all -> 0x005f }
            r7.zzb(r8)     // Catch:{ all -> 0x005f }
        L_0x00b9:
            r6.zzaL()     // Catch:{ all -> 0x005f }
            goto L_0x015f
        L_0x00be:
            java.lang.String r9 = "Last-Modified"
            java.lang.String r9 = zzaG(r11, r9)     // Catch:{ all -> 0x005f }
            java.lang.String r2 = "ETag"
            java.lang.String r11 = zzaG(r11, r2)     // Catch:{ all -> 0x005f }
            r2 = 0
            if (r8 == r5) goto L_0x00d8
            if (r8 != r4) goto L_0x00d0
            goto L_0x00d8
        L_0x00d0:
            com.google.android.gms.measurement.internal.zzif r4 = r6.zzr()     // Catch:{ all -> 0x005f }
            r4.zzz(r7, r10, r9, r11)     // Catch:{ all -> 0x005f }
            goto L_0x00e9
        L_0x00d8:
            com.google.android.gms.measurement.internal.zzif r9 = r6.zzr()     // Catch:{ all -> 0x005f }
            com.google.android.gms.internal.measurement.zzgo r9 = r9.zzj(r7)     // Catch:{ all -> 0x005f }
            if (r9 != 0) goto L_0x00e9
            com.google.android.gms.measurement.internal.zzif r9 = r6.zzr()     // Catch:{ all -> 0x005f }
            r9.zzz(r7, r2, r2, r2)     // Catch:{ all -> 0x005f }
        L_0x00e9:
            com.google.android.gms.common.util.Clock r9 = r6.zzaU()     // Catch:{ all -> 0x005f }
            long r9 = r9.currentTimeMillis()     // Catch:{ all -> 0x005f }
            r1.zzab(r9)     // Catch:{ all -> 0x005f }
            com.google.android.gms.measurement.internal.zzaw r9 = r6.zzj()     // Catch:{ all -> 0x005f }
            r9.zzT(r1, r0, r0)     // Catch:{ all -> 0x005f }
            if (r8 != r5) goto L_0x010b
            com.google.android.gms.measurement.internal.zzhe r8 = r6.zzaW()     // Catch:{ all -> 0x005f }
            com.google.android.gms.measurement.internal.zzhc r8 = r8.zzl()     // Catch:{ all -> 0x005f }
            java.lang.String r9 = "Config not found. Using empty config. appId"
            r8.zzb(r9, r7)     // Catch:{ all -> 0x005f }
            goto L_0x011c
        L_0x010b:
            com.google.android.gms.measurement.internal.zzhe r7 = r6.zzaW()     // Catch:{ all -> 0x005f }
            com.google.android.gms.measurement.internal.zzhc r7 = r7.zzj()     // Catch:{ all -> 0x005f }
            java.lang.String r9 = "Successfully fetched config. Got network response. code, size"
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x005f }
            r7.zzc(r9, r8, r3)     // Catch:{ all -> 0x005f }
        L_0x011c:
            com.google.android.gms.measurement.internal.zzhk r7 = r6.zzp()     // Catch:{ all -> 0x005f }
            boolean r7 = r7.zzd()     // Catch:{ all -> 0x005f }
            if (r7 == 0) goto L_0x0130
            boolean r7 = r6.zzaN()     // Catch:{ all -> 0x005f }
            if (r7 == 0) goto L_0x0130
            r6.zzat()     // Catch:{ all -> 0x005f }
            goto L_0x015f
        L_0x0130:
            com.google.android.gms.measurement.internal.zzam r7 = r6.zzi()     // Catch:{ all -> 0x005f }
            com.google.android.gms.measurement.internal.zzgg r8 = com.google.android.gms.measurement.internal.zzgi.zzaM     // Catch:{ all -> 0x005f }
            boolean r7 = r7.zzx(r2, r8)     // Catch:{ all -> 0x005f }
            if (r7 == 0) goto L_0x015c
            com.google.android.gms.measurement.internal.zzhk r7 = r6.zzp()     // Catch:{ all -> 0x005f }
            boolean r7 = r7.zzd()     // Catch:{ all -> 0x005f }
            if (r7 == 0) goto L_0x015c
            com.google.android.gms.measurement.internal.zzaw r7 = r6.zzj()     // Catch:{ all -> 0x005f }
            java.lang.String r8 = r1.zzC()     // Catch:{ all -> 0x005f }
            boolean r7 = r7.zzY(r8)     // Catch:{ all -> 0x005f }
            if (r7 == 0) goto L_0x015c
            java.lang.String r7 = r1.zzC()     // Catch:{ all -> 0x005f }
            r6.zzav(r7)     // Catch:{ all -> 0x005f }
            goto L_0x015f
        L_0x015c:
            r6.zzaL()     // Catch:{ all -> 0x005f }
        L_0x015f:
            com.google.android.gms.measurement.internal.zzaw r7 = r6.zzj()     // Catch:{ all -> 0x005f }
            r7.zzS()     // Catch:{ all -> 0x005f }
            com.google.android.gms.measurement.internal.zzaw r7 = r6.zzj()     // Catch:{ all -> 0x0013 }
            r7.zzL()     // Catch:{ all -> 0x0013 }
            r6.zzu = r0
            r6.zzaH()
            return
        L_0x0173:
            com.google.android.gms.measurement.internal.zzaw r8 = r6.zzj()     // Catch:{ all -> 0x0013 }
            r8.zzL()     // Catch:{ all -> 0x0013 }
            throw r7     // Catch:{ all -> 0x0013 }
        L_0x017b:
            r6.zzu = r0
            r6.zzaH()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzpv.zzW(java.lang.String, int, java.lang.Throwable, byte[], java.util.Map):void");
    }

    /* access modifiers changed from: package-private */
    public final void zzX(boolean z) {
        zzaL();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0034, code lost:
        if (r2 != null) goto L_0x0036;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzY(boolean r21, int r22, java.lang.Throwable r23, byte[] r24, java.lang.String r25, java.util.List r26) {
        /*
            r20 = this;
            r1 = r20
            r0 = r22
            r2 = r23
            r9 = r25
            com.google.android.gms.measurement.internal.zzil r3 = r20.zzaX()
            r3.zzg()
            r20.zzM()
            r10 = 0
            if (r24 != 0) goto L_0x001b
            byte[] r3 = new byte[r10]     // Catch:{ all -> 0x0018 }
            goto L_0x001d
        L_0x0018:
            r0 = move-exception
            goto L_0x02d6
        L_0x001b:
            r3 = r24
        L_0x001d:
            java.util.List r4 = r1.zzz     // Catch:{ all -> 0x0018 }
            java.lang.Object r4 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)     // Catch:{ all -> 0x0018 }
            r11 = r4
            java.util.List r11 = (java.util.List) r11     // Catch:{ all -> 0x0018 }
            r12 = 0
            r1.zzz = r12     // Catch:{ all -> 0x0018 }
            if (r21 == 0) goto L_0x008e
            r4 = 200(0xc8, float:2.8E-43)
            if (r0 == r4) goto L_0x0034
            r4 = 204(0xcc, float:2.86E-43)
            if (r0 != r4) goto L_0x0036
            r0 = r4
        L_0x0034:
            if (r2 == 0) goto L_0x008e
        L_0x0036:
            java.lang.String r4 = new java.lang.String     // Catch:{ all -> 0x0018 }
            java.nio.charset.Charset r5 = java.nio.charset.StandardCharsets.UTF_8     // Catch:{ all -> 0x0018 }
            r4.<init>(r3, r5)     // Catch:{ all -> 0x0018 }
            int r3 = r4.length()     // Catch:{ all -> 0x0018 }
            r5 = 32
            int r3 = java.lang.Math.min(r5, r3)     // Catch:{ all -> 0x0018 }
            java.lang.String r3 = r4.substring(r10, r3)     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzhe r4 = r20.zzaW()     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzhc r4 = r4.zzl()     // Catch:{ all -> 0x0018 }
            java.lang.String r5 = "Network upload failed. Will retry later. code, error"
            java.lang.Integer r6 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x0018 }
            r4.zzd(r5, r6, r2, r3)     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzoa r2 = r1.zzk     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzhp r2 = r2.zze     // Catch:{ all -> 0x0018 }
            com.google.android.gms.common.util.Clock r3 = r20.zzaU()     // Catch:{ all -> 0x0018 }
            long r3 = r3.currentTimeMillis()     // Catch:{ all -> 0x0018 }
            r2.zzb(r3)     // Catch:{ all -> 0x0018 }
            r2 = 503(0x1f7, float:7.05E-43)
            if (r0 == r2) goto L_0x0073
            r2 = 429(0x1ad, float:6.01E-43)
            if (r0 != r2) goto L_0x0082
        L_0x0073:
            com.google.android.gms.measurement.internal.zzoa r0 = r1.zzk     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzhp r0 = r0.zzc     // Catch:{ all -> 0x0018 }
            com.google.android.gms.common.util.Clock r2 = r20.zzaU()     // Catch:{ all -> 0x0018 }
            long r2 = r2.currentTimeMillis()     // Catch:{ all -> 0x0018 }
            r0.zzb(r2)     // Catch:{ all -> 0x0018 }
        L_0x0082:
            com.google.android.gms.measurement.internal.zzaw r0 = r20.zzj()     // Catch:{ all -> 0x0018 }
            r0.zzM(r11)     // Catch:{ all -> 0x0018 }
            r20.zzaL()     // Catch:{ all -> 0x0018 }
            goto L_0x02d0
        L_0x008e:
            com.google.android.gms.measurement.internal.zzhe r2 = r20.zzaW()     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zzj()     // Catch:{ all -> 0x0018 }
            java.lang.String r4 = "Network upload successful with code, uploadAttempted"
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x0018 }
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r21)     // Catch:{ all -> 0x0018 }
            r2.zzc(r4, r0, r5)     // Catch:{ all -> 0x0018 }
            if (r21 == 0) goto L_0x00b8
            com.google.android.gms.measurement.internal.zzoa r2 = r1.zzk     // Catch:{ SQLiteException -> 0x00b5 }
            com.google.android.gms.measurement.internal.zzhp r2 = r2.zzd     // Catch:{ SQLiteException -> 0x00b5 }
            com.google.android.gms.common.util.Clock r4 = r20.zzaU()     // Catch:{ SQLiteException -> 0x00b5 }
            long r4 = r4.currentTimeMillis()     // Catch:{ SQLiteException -> 0x00b5 }
            r2.zzb(r4)     // Catch:{ SQLiteException -> 0x00b5 }
            goto L_0x00b8
        L_0x00b5:
            r0 = move-exception
            goto L_0x02a6
        L_0x00b8:
            com.google.android.gms.measurement.internal.zzoa r2 = r1.zzk     // Catch:{ SQLiteException -> 0x00b5 }
            com.google.android.gms.measurement.internal.zzhp r2 = r2.zze     // Catch:{ SQLiteException -> 0x00b5 }
            r13 = 0
            r2.zzb(r13)     // Catch:{ SQLiteException -> 0x00b5 }
            r20.zzaL()     // Catch:{ SQLiteException -> 0x00b5 }
            if (r21 == 0) goto L_0x00d9
            com.google.android.gms.measurement.internal.zzhe r2 = r20.zzaW()     // Catch:{ SQLiteException -> 0x00b5 }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zzj()     // Catch:{ SQLiteException -> 0x00b5 }
            java.lang.String r4 = "Successful upload. Got network response. code, size"
            int r3 = r3.length     // Catch:{ SQLiteException -> 0x00b5 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ SQLiteException -> 0x00b5 }
            r2.zzc(r4, r0, r3)     // Catch:{ SQLiteException -> 0x00b5 }
            goto L_0x00e6
        L_0x00d9:
            com.google.android.gms.measurement.internal.zzhe r0 = r20.zzaW()     // Catch:{ SQLiteException -> 0x00b5 }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzj()     // Catch:{ SQLiteException -> 0x00b5 }
            java.lang.String r2 = "Purged empty bundles"
            r0.zza(r2)     // Catch:{ SQLiteException -> 0x00b5 }
        L_0x00e6:
            com.google.android.gms.measurement.internal.zzaw r0 = r20.zzj()     // Catch:{ SQLiteException -> 0x00b5 }
            r0.zzH()     // Catch:{ SQLiteException -> 0x00b5 }
            com.google.android.gms.measurement.internal.zzam r0 = r20.zzi()     // Catch:{ all -> 0x0174 }
            com.google.android.gms.measurement.internal.zzgg r2 = com.google.android.gms.measurement.internal.zzgi.zzaM     // Catch:{ all -> 0x0174 }
            boolean r0 = r0.zzx(r12, r2)     // Catch:{ all -> 0x0174 }
            r7 = -1
            if (r0 == 0) goto L_0x01ed
            com.google.android.gms.measurement.internal.zzam r0 = r20.zzi()     // Catch:{ all -> 0x0174 }
            com.google.android.gms.measurement.internal.zzgg r2 = com.google.android.gms.measurement.internal.zzgi.zzaP     // Catch:{ all -> 0x0174 }
            boolean r0 = r0.zzx(r12, r2)     // Catch:{ all -> 0x0174 }
            if (r0 == 0) goto L_0x01bb
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ all -> 0x0174 }
            r0.<init>()     // Catch:{ all -> 0x0174 }
            java.util.Iterator r15 = r26.iterator()     // Catch:{ all -> 0x0174 }
        L_0x0110:
            boolean r2 = r15.hasNext()     // Catch:{ all -> 0x0174 }
            if (r2 == 0) goto L_0x0177
            java.lang.Object r2 = r15.next()     // Catch:{ all -> 0x0174 }
            android.util.Pair r2 = (android.util.Pair) r2     // Catch:{ all -> 0x0174 }
            java.lang.Object r3 = r2.first     // Catch:{ all -> 0x0174 }
            r16 = r3
            com.google.android.gms.internal.measurement.zzhv r16 = (com.google.android.gms.internal.measurement.zzhv) r16     // Catch:{ all -> 0x0174 }
            java.lang.Object r2 = r2.second     // Catch:{ all -> 0x0174 }
            r17 = r2
            com.google.android.gms.measurement.internal.zzph r17 = (com.google.android.gms.measurement.internal.zzph) r17     // Catch:{ all -> 0x0174 }
            com.google.android.gms.measurement.internal.zzmf r2 = r17.zza()     // Catch:{ all -> 0x0174 }
            com.google.android.gms.measurement.internal.zzmf r3 = com.google.android.gms.measurement.internal.zzmf.SGTM_CLIENT     // Catch:{ all -> 0x0174 }
            if (r2 == r3) goto L_0x0171
            com.google.android.gms.measurement.internal.zzaw r2 = r20.zzj()     // Catch:{ all -> 0x0174 }
            java.lang.String r5 = r17.zzc()     // Catch:{ all -> 0x0174 }
            java.util.Map r6 = r17.zzd()     // Catch:{ all -> 0x0174 }
            com.google.android.gms.measurement.internal.zzmf r18 = r17.zza()     // Catch:{ all -> 0x0174 }
            r19 = 0
            r3 = r25
            r4 = r16
            r13 = r7
            r7 = r18
            r8 = r19
            long r2 = r2.zzd(r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x0174 }
            com.google.android.gms.measurement.internal.zzmf r4 = r17.zza()     // Catch:{ all -> 0x0174 }
            com.google.android.gms.measurement.internal.zzmf r5 = com.google.android.gms.measurement.internal.zzmf.GOOGLE_SIGNAL_PENDING     // Catch:{ all -> 0x0174 }
            if (r4 != r5) goto L_0x0170
            int r4 = (r2 > r13 ? 1 : (r2 == r13 ? 0 : -1))
            if (r4 == 0) goto L_0x0170
            java.lang.String r4 = r16.zzf()     // Catch:{ all -> 0x0174 }
            boolean r4 = r4.isEmpty()     // Catch:{ all -> 0x0174 }
            if (r4 != 0) goto L_0x0170
            java.lang.String r4 = r16.zzf()     // Catch:{ all -> 0x0174 }
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x0174 }
            r0.put(r4, r2)     // Catch:{ all -> 0x0174 }
        L_0x0170:
            r7 = r13
        L_0x0171:
            r13 = 0
            goto L_0x0110
        L_0x0174:
            r0 = move-exception
            goto L_0x029e
        L_0x0177:
            r13 = r7
            java.util.Iterator r15 = r26.iterator()     // Catch:{ all -> 0x0174 }
        L_0x017c:
            boolean r2 = r15.hasNext()     // Catch:{ all -> 0x0174 }
            if (r2 == 0) goto L_0x01ee
            java.lang.Object r2 = r15.next()     // Catch:{ all -> 0x0174 }
            android.util.Pair r2 = (android.util.Pair) r2     // Catch:{ all -> 0x0174 }
            java.lang.Object r3 = r2.first     // Catch:{ all -> 0x0174 }
            r4 = r3
            com.google.android.gms.internal.measurement.zzhv r4 = (com.google.android.gms.internal.measurement.zzhv) r4     // Catch:{ all -> 0x0174 }
            java.lang.Object r2 = r2.second     // Catch:{ all -> 0x0174 }
            com.google.android.gms.measurement.internal.zzph r2 = (com.google.android.gms.measurement.internal.zzph) r2     // Catch:{ all -> 0x0174 }
            com.google.android.gms.measurement.internal.zzmf r3 = r2.zza()     // Catch:{ all -> 0x0174 }
            com.google.android.gms.measurement.internal.zzmf r5 = com.google.android.gms.measurement.internal.zzmf.SGTM_CLIENT     // Catch:{ all -> 0x0174 }
            if (r3 != r5) goto L_0x017c
            java.lang.String r3 = r4.zzf()     // Catch:{ all -> 0x0174 }
            java.lang.Object r3 = r0.get(r3)     // Catch:{ all -> 0x0174 }
            r8 = r3
            java.lang.Long r8 = (java.lang.Long) r8     // Catch:{ all -> 0x0174 }
            com.google.android.gms.measurement.internal.zzaw r3 = r20.zzj()     // Catch:{ all -> 0x0174 }
            java.lang.String r5 = r2.zzc()     // Catch:{ all -> 0x0174 }
            java.util.Map r6 = r2.zzd()     // Catch:{ all -> 0x0174 }
            com.google.android.gms.measurement.internal.zzmf r7 = r2.zza()     // Catch:{ all -> 0x0174 }
            r2 = r3
            r3 = r25
            r2.zzd(r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x0174 }
            goto L_0x017c
        L_0x01bb:
            r13 = r7
            java.util.Iterator r0 = r26.iterator()     // Catch:{ all -> 0x0174 }
        L_0x01c0:
            boolean r2 = r0.hasNext()     // Catch:{ all -> 0x0174 }
            if (r2 == 0) goto L_0x01ee
            java.lang.Object r2 = r0.next()     // Catch:{ all -> 0x0174 }
            android.util.Pair r2 = (android.util.Pair) r2     // Catch:{ all -> 0x0174 }
            java.lang.Object r3 = r2.first     // Catch:{ all -> 0x0174 }
            r4 = r3
            com.google.android.gms.internal.measurement.zzhv r4 = (com.google.android.gms.internal.measurement.zzhv) r4     // Catch:{ all -> 0x0174 }
            java.lang.Object r2 = r2.second     // Catch:{ all -> 0x0174 }
            com.google.android.gms.measurement.internal.zzph r2 = (com.google.android.gms.measurement.internal.zzph) r2     // Catch:{ all -> 0x0174 }
            com.google.android.gms.measurement.internal.zzaw r3 = r20.zzj()     // Catch:{ all -> 0x0174 }
            java.lang.String r5 = r2.zzc()     // Catch:{ all -> 0x0174 }
            java.util.Map r6 = r2.zzd()     // Catch:{ all -> 0x0174 }
            com.google.android.gms.measurement.internal.zzmf r7 = r2.zza()     // Catch:{ all -> 0x0174 }
            r8 = 0
            r2 = r3
            r3 = r25
            r2.zzd(r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x0174 }
            goto L_0x01c0
        L_0x01ed:
            r13 = r7
        L_0x01ee:
            java.util.Iterator r2 = r11.iterator()     // Catch:{ all -> 0x0174 }
        L_0x01f2:
            boolean r0 = r2.hasNext()     // Catch:{ all -> 0x0174 }
            if (r0 == 0) goto L_0x024b
            java.lang.Object r0 = r2.next()     // Catch:{ all -> 0x0174 }
            r3 = r0
            java.lang.Long r3 = (java.lang.Long) r3     // Catch:{ all -> 0x0174 }
            com.google.android.gms.measurement.internal.zzaw r4 = r20.zzj()     // Catch:{ SQLiteException -> 0x023e }
            long r5 = r3.longValue()     // Catch:{ SQLiteException -> 0x023e }
            r4.zzg()     // Catch:{ SQLiteException -> 0x023e }
            r4.zzav()     // Catch:{ SQLiteException -> 0x023e }
            android.database.sqlite.SQLiteDatabase r0 = r4.zzj()     // Catch:{ SQLiteException -> 0x023e }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ SQLiteException -> 0x023e }
            java.lang.String[] r5 = new java.lang.String[]{r5}     // Catch:{ SQLiteException -> 0x023e }
            java.lang.String r6 = "queue"
            java.lang.String r7 = "rowid=?"
            int r0 = r0.delete(r6, r7, r5)     // Catch:{ SQLiteException -> 0x022d }
            r5 = 1
            if (r0 != r5) goto L_0x0225
            goto L_0x01f2
        L_0x0225:
            android.database.sqlite.SQLiteException r0 = new android.database.sqlite.SQLiteException     // Catch:{ SQLiteException -> 0x022d }
            java.lang.String r5 = "Deleted fewer rows from queue than expected"
            r0.<init>(r5)     // Catch:{ SQLiteException -> 0x022d }
            throw r0     // Catch:{ SQLiteException -> 0x022d }
        L_0x022d:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzio r4 = r4.zzu     // Catch:{ SQLiteException -> 0x023e }
            com.google.android.gms.measurement.internal.zzhe r4 = r4.zzaW()     // Catch:{ SQLiteException -> 0x023e }
            com.google.android.gms.measurement.internal.zzhc r4 = r4.zze()     // Catch:{ SQLiteException -> 0x023e }
            java.lang.String r5 = "Failed to delete a bundle in a queue table"
            r4.zzb(r5, r0)     // Catch:{ SQLiteException -> 0x023e }
            throw r0     // Catch:{ SQLiteException -> 0x023e }
        L_0x023e:
            r0 = move-exception
            java.util.List r4 = r1.zzA     // Catch:{ all -> 0x0174 }
            if (r4 == 0) goto L_0x024a
            boolean r3 = r4.contains(r3)     // Catch:{ all -> 0x0174 }
            if (r3 == 0) goto L_0x024a
            goto L_0x01f2
        L_0x024a:
            throw r0     // Catch:{ all -> 0x0174 }
        L_0x024b:
            com.google.android.gms.measurement.internal.zzaw r0 = r20.zzj()     // Catch:{ all -> 0x0174 }
            r0.zzS()     // Catch:{ all -> 0x0174 }
            com.google.android.gms.measurement.internal.zzaw r0 = r20.zzj()     // Catch:{ SQLiteException -> 0x00b5 }
            r0.zzL()     // Catch:{ SQLiteException -> 0x00b5 }
            r1.zzA = r12     // Catch:{ SQLiteException -> 0x00b5 }
            com.google.android.gms.measurement.internal.zzam r0 = r20.zzi()     // Catch:{ SQLiteException -> 0x00b5 }
            com.google.android.gms.measurement.internal.zzgg r2 = com.google.android.gms.measurement.internal.zzgi.zzaM     // Catch:{ SQLiteException -> 0x00b5 }
            boolean r0 = r0.zzx(r12, r2)     // Catch:{ SQLiteException -> 0x00b5 }
            if (r0 == 0) goto L_0x0281
            com.google.android.gms.measurement.internal.zzhk r0 = r20.zzp()     // Catch:{ SQLiteException -> 0x00b5 }
            boolean r0 = r0.zzd()     // Catch:{ SQLiteException -> 0x00b5 }
            if (r0 == 0) goto L_0x0281
            com.google.android.gms.measurement.internal.zzaw r0 = r20.zzj()     // Catch:{ SQLiteException -> 0x00b5 }
            boolean r0 = r0.zzY(r9)     // Catch:{ SQLiteException -> 0x00b5 }
            if (r0 == 0) goto L_0x0281
            r1.zzav(r9)     // Catch:{ SQLiteException -> 0x00b5 }
        L_0x027e:
            r2 = 0
            goto L_0x029b
        L_0x0281:
            com.google.android.gms.measurement.internal.zzhk r0 = r20.zzp()     // Catch:{ SQLiteException -> 0x00b5 }
            boolean r0 = r0.zzd()     // Catch:{ SQLiteException -> 0x00b5 }
            if (r0 == 0) goto L_0x0295
            boolean r0 = r20.zzaN()     // Catch:{ SQLiteException -> 0x00b5 }
            if (r0 == 0) goto L_0x0295
            r20.zzat()     // Catch:{ SQLiteException -> 0x00b5 }
            goto L_0x027e
        L_0x0295:
            r1.zzB = r13     // Catch:{ SQLiteException -> 0x00b5 }
            r20.zzaL()     // Catch:{ SQLiteException -> 0x00b5 }
            goto L_0x027e
        L_0x029b:
            r1.zza = r2     // Catch:{ SQLiteException -> 0x00b5 }
            goto L_0x02d0
        L_0x029e:
            com.google.android.gms.measurement.internal.zzaw r2 = r20.zzj()     // Catch:{ SQLiteException -> 0x00b5 }
            r2.zzL()     // Catch:{ SQLiteException -> 0x00b5 }
            throw r0     // Catch:{ SQLiteException -> 0x00b5 }
        L_0x02a6:
            com.google.android.gms.measurement.internal.zzhe r2 = r20.zzaW()     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zze()     // Catch:{ all -> 0x0018 }
            java.lang.String r3 = "Database error while trying to delete uploaded bundles"
            r2.zzb(r3, r0)     // Catch:{ all -> 0x0018 }
            com.google.android.gms.common.util.Clock r0 = r20.zzaU()     // Catch:{ all -> 0x0018 }
            long r2 = r0.elapsedRealtime()     // Catch:{ all -> 0x0018 }
            r1.zza = r2     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzhe r0 = r20.zzaW()     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzj()     // Catch:{ all -> 0x0018 }
            java.lang.String r2 = "Disable upload, time"
            long r3 = r1.zza     // Catch:{ all -> 0x0018 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x0018 }
            r0.zzb(r2, r3)     // Catch:{ all -> 0x0018 }
        L_0x02d0:
            r1.zzv = r10
            r20.zzaH()
            return
        L_0x02d6:
            r1.zzv = r10
            r20.zzaH()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzpv.zzY(boolean, int, java.lang.Throwable, byte[], java.lang.String, java.util.List):void");
    }

    /* JADX WARNING: type inference failed for: r7v4, types: [java.lang.String] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x008c A[Catch:{ all -> 0x0010 }] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzZ(java.lang.String r4, int r5, java.lang.Throwable r6, byte[] r7, com.google.android.gms.measurement.internal.zzpz r8) {
        /*
            r3 = this;
            com.google.android.gms.measurement.internal.zzil r0 = r3.zzaX()
            r0.zzg()
            r3.zzM()
            r0 = 0
            if (r7 != 0) goto L_0x0013
            byte[] r7 = new byte[r0]     // Catch:{ all -> 0x0010 }
            goto L_0x0013
        L_0x0010:
            r4 = move-exception
            goto L_0x00a8
        L_0x0013:
            r1 = 200(0xc8, float:2.8E-43)
            if (r5 == r1) goto L_0x001c
            r1 = 204(0xcc, float:2.86E-43)
            if (r5 != r1) goto L_0x0067
            r5 = r1
        L_0x001c:
            if (r6 != 0) goto L_0x0067
            com.google.android.gms.measurement.internal.zzaw r6 = r3.zzj()     // Catch:{ all -> 0x0010 }
            long r7 = r8.zzc()     // Catch:{ all -> 0x0010 }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x0010 }
            r6.zzK(r7)     // Catch:{ all -> 0x0010 }
            com.google.android.gms.measurement.internal.zzhe r6 = r3.zzaW()     // Catch:{ all -> 0x0010 }
            com.google.android.gms.measurement.internal.zzhc r6 = r6.zzj()     // Catch:{ all -> 0x0010 }
            java.lang.String r7 = "Successfully uploaded batch from upload queue. appId, status"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0010 }
            r6.zzc(r7, r4, r5)     // Catch:{ all -> 0x0010 }
            com.google.android.gms.measurement.internal.zzam r5 = r3.zzi()     // Catch:{ all -> 0x0010 }
            com.google.android.gms.measurement.internal.zzgg r6 = com.google.android.gms.measurement.internal.zzgi.zzaM     // Catch:{ all -> 0x0010 }
            r7 = 0
            boolean r5 = r5.zzx(r7, r6)     // Catch:{ all -> 0x0010 }
            if (r5 == 0) goto L_0x0063
            com.google.android.gms.measurement.internal.zzhk r5 = r3.zzp()     // Catch:{ all -> 0x0010 }
            boolean r5 = r5.zzd()     // Catch:{ all -> 0x0010 }
            if (r5 == 0) goto L_0x0063
            com.google.android.gms.measurement.internal.zzaw r5 = r3.zzj()     // Catch:{ all -> 0x0010 }
            boolean r5 = r5.zzY(r4)     // Catch:{ all -> 0x0010 }
            if (r5 == 0) goto L_0x0063
            r3.zzav(r4)     // Catch:{ all -> 0x0010 }
            goto L_0x00a2
        L_0x0063:
            r3.zzaL()     // Catch:{ all -> 0x0010 }
            goto L_0x00a2
        L_0x0067:
            java.lang.String r1 = new java.lang.String     // Catch:{ all -> 0x0010 }
            java.nio.charset.Charset r2 = java.nio.charset.StandardCharsets.UTF_8     // Catch:{ all -> 0x0010 }
            r1.<init>(r7, r2)     // Catch:{ all -> 0x0010 }
            int r7 = r1.length()     // Catch:{ all -> 0x0010 }
            r2 = 32
            int r7 = java.lang.Math.min(r2, r7)     // Catch:{ all -> 0x0010 }
            java.lang.String r7 = r1.substring(r0, r7)     // Catch:{ all -> 0x0010 }
            com.google.android.gms.measurement.internal.zzhe r1 = r3.zzaW()     // Catch:{ all -> 0x0010 }
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zzl()     // Catch:{ all -> 0x0010 }
            java.lang.String r2 = "Network upload failed. Will retry later. appId, status, error"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0010 }
            if (r6 != 0) goto L_0x008d
            r6 = r7
        L_0x008d:
            r1.zzd(r2, r4, r5, r6)     // Catch:{ all -> 0x0010 }
            com.google.android.gms.measurement.internal.zzaw r4 = r3.zzj()     // Catch:{ all -> 0x0010 }
            long r5 = r8.zzc()     // Catch:{ all -> 0x0010 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0010 }
            r4.zzN(r5)     // Catch:{ all -> 0x0010 }
            r3.zzaL()     // Catch:{ all -> 0x0010 }
        L_0x00a2:
            r3.zzv = r0
            r3.zzaH()
            return
        L_0x00a8:
            r3.zzv = r0
            r3.zzaH()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzpv.zzZ(java.lang.String, int, java.lang.Throwable, byte[], com.google.android.gms.measurement.internal.zzpz):void");
    }

    /* access modifiers changed from: package-private */
    public final long zza() {
        long currentTimeMillis = zzaU().currentTimeMillis();
        zzoa zzoa = this.zzk;
        zzoa.zzav();
        zzoa.zzg();
        zzhp zzhp = zzoa.zzf;
        long zza2 = zzhp.zza();
        if (zza2 == 0) {
            zza2 = ((long) zzoa.zzu.zzw().zzJ().nextInt(86400000)) + 1;
            zzhp.zzb(zza2);
        }
        return ((((currentTimeMillis + zza2) / 1000) / 60) / 60) / 24;
    }

    public final Context zzaT() {
        return this.zzn.zzaT();
    }

    public final Clock zzaU() {
        return ((zzio) Preconditions.checkNotNull(this.zzn)).zzaU();
    }

    public final zzaf zzaV() {
        return this.zzn.zzaV();
    }

    public final zzhe zzaW() {
        return ((zzio) Preconditions.checkNotNull(this.zzn)).zzaW();
    }

    public final zzil zzaX() {
        return ((zzio) Preconditions.checkNotNull(this.zzn)).zzaX();
    }

    /* access modifiers changed from: package-private */
    public final void zzaa(zzh zzh2, zzhw zzhw) {
        zzaX().zzg();
        zzM();
        zzhb zzc2 = zzhc.zzc();
        byte[] zzaN = zzh2.zzaN();
        if (zzaN != null) {
            try {
                zzc2 = (zzhb) zzqa.zzp(zzc2, zzaN);
            } catch (zzmm unused) {
                zzaW().zzk().zzb("Failed to parse locally stored ad campaign info. appId", zzhe.zzn(zzh2.zzC()));
            }
        }
        for (zzhm zzhm : zzhw.zzaM()) {
            if (zzhm.zzh().equals(Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN)) {
                String str = (String) zzqa.zzI(zzhm, "gclid", "");
                String str2 = (String) zzqa.zzI(zzhm, "gbraid", "");
                String str3 = (String) zzqa.zzI(zzhm, "gad_source", "");
                if (!str.isEmpty() || !str2.isEmpty()) {
                    long longValue = ((Long) zzqa.zzI(zzhm, "click_timestamp", 0L)).longValue();
                    if (longValue <= 0) {
                        longValue = zzhm.zzd();
                    }
                    if ("referrer API v2".equals(zzqa.zzH(zzhm, "_cis"))) {
                        if (longValue > zzc2.zzb()) {
                            if (str.isEmpty()) {
                                zzc2.zzh();
                            } else {
                                zzc2.zzp(str);
                            }
                            if (str2.isEmpty()) {
                                zzc2.zzg();
                            } else {
                                zzc2.zzo(str2);
                            }
                            if (str3.isEmpty()) {
                                zzc2.zzf();
                            } else {
                                zzc2.zzn(str3);
                            }
                            zzc2.zzm(longValue);
                        }
                    } else if (longValue > zzc2.zza()) {
                        if (str.isEmpty()) {
                            zzc2.zze();
                        } else {
                            zzc2.zzk(str);
                        }
                        if (str2.isEmpty()) {
                            zzc2.zzd();
                        } else {
                            zzc2.zzj(str2);
                        }
                        if (str3.isEmpty()) {
                            zzc2.zzc();
                        } else {
                            zzc2.zzi(str3);
                        }
                        zzc2.zzl(longValue);
                    }
                }
            }
        }
        if (!((zzhc) zzc2.zzba()).equals(zzhc.zze())) {
            zzhw.zzF((zzhc) zzc2.zzba());
        }
        zzh2.zzR(((zzhc) zzc2.zzba()).zzcd());
        if (zzh2.zzaK()) {
            zzj().zzT(zzh2, false, false);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzab(zzr zzr2) {
        zzaX().zzg();
        zzM();
        Preconditions.checkNotNull(zzr2);
        String str = zzr2.zza;
        Preconditions.checkNotEmpty(str);
        int i = 0;
        if (zzi().zzx((String) null, zzgi.zzay)) {
            long currentTimeMillis = zzaU().currentTimeMillis();
            int zzh2 = zzi().zzh((String) null, zzgi.zzah);
            zzi();
            long zzF2 = currentTimeMillis - zzam.zzF();
            while (i < zzh2 && zzaM((String) null, zzF2)) {
                i++;
            }
        } else {
            zzi();
            long zzH2 = zzam.zzH();
            while (((long) i) < zzH2 && zzaM(str, 0)) {
                i++;
            }
        }
        if (zzi().zzx((String) null, zzgi.zzaz)) {
            zzaX().zzg();
            zzaJ();
        }
        if (zzi().zzx((String) null, zzgi.zzaQ) && this.zzl.zzd(str, zzih.zzb(zzr2.zzG))) {
            zzaW().zzj().zzb("[sgtm] Going background, trigger client side upload. appId", str);
            zzau(str, zzaU().currentTimeMillis());
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x035b A[Catch:{ RuntimeException -> 0x020f, all -> 0x00ba }] */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x03e2 A[Catch:{ RuntimeException -> 0x020f, all -> 0x00ba }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00fe A[Catch:{ RuntimeException -> 0x020f, all -> 0x00ba }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0104 A[Catch:{ RuntimeException -> 0x020f, all -> 0x00ba }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x010c A[Catch:{ RuntimeException -> 0x020f, all -> 0x00ba }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0118 A[Catch:{ RuntimeException -> 0x020f, all -> 0x00ba }] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0125 A[Catch:{ RuntimeException -> 0x020f, all -> 0x00ba }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzac(com.google.android.gms.measurement.internal.zzr r26) {
        /*
            r25 = this;
            r1 = r25
            r2 = r26
            java.lang.String r3 = "_sysu"
            java.lang.String r4 = "_sys"
            java.lang.String r5 = "_pfo"
            java.lang.String r0 = "com.android.vending"
            java.lang.String r6 = "_npa"
            java.lang.String r7 = "_uwa"
            com.google.android.gms.measurement.internal.zzil r8 = r25.zzaX()
            r8.zzg()
            r25.zzM()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r26)
            java.lang.String r8 = r2.zza
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r8)
            boolean r9 = zzaQ(r26)
            if (r9 != 0) goto L_0x0029
            return
        L_0x0029:
            com.google.android.gms.measurement.internal.zzaw r9 = r25.zzj()
            com.google.android.gms.measurement.internal.zzh r9 = r9.zzl(r8)
            r10 = 0
            r11 = 0
            if (r9 == 0) goto L_0x0059
            java.lang.String r13 = r9.zzH()
            boolean r13 = android.text.TextUtils.isEmpty(r13)
            if (r13 == 0) goto L_0x0059
            java.lang.String r13 = r2.zzb
            boolean r13 = android.text.TextUtils.isEmpty(r13)
            if (r13 != 0) goto L_0x0059
            r9.zzab(r11)
            com.google.android.gms.measurement.internal.zzaw r13 = r25.zzj()
            r13.zzT(r9, r10, r10)
            com.google.android.gms.measurement.internal.zzif r9 = r25.zzr()
            r9.zzr(r8)
        L_0x0059:
            boolean r9 = r2.zzh
            if (r9 != 0) goto L_0x0061
            r25.zzg(r26)
            return
        L_0x0061:
            long r13 = r2.zzl
            int r9 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r9 != 0) goto L_0x006f
            com.google.android.gms.common.util.Clock r9 = r25.zzaU()
            long r13 = r9.currentTimeMillis()
        L_0x006f:
            com.google.android.gms.measurement.internal.zzio r9 = r1.zzn
            com.google.android.gms.measurement.internal.zzbb r9 = r9.zzg()
            r9.zzg()
            int r9 = r2.zzm
            r15 = 1
            if (r9 == 0) goto L_0x0095
            if (r9 == r15) goto L_0x0095
            com.google.android.gms.measurement.internal.zzhe r16 = r25.zzaW()
            com.google.android.gms.measurement.internal.zzhc r11 = r16.zzk()
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzhe.zzn(r8)
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            java.lang.String r10 = "Incorrect app type, assuming installed app. appId, appType"
            r11.zzc(r10, r12, r9)
            r9 = 0
        L_0x0095:
            com.google.android.gms.measurement.internal.zzaw r10 = r25.zzj()
            r10.zzH()
            com.google.android.gms.measurement.internal.zzaw r10 = r25.zzj()     // Catch:{ all -> 0x00ba }
            com.google.android.gms.measurement.internal.zzqd r10 = r10.zzy(r8, r6)     // Catch:{ all -> 0x00ba }
            java.lang.Boolean r11 = zzaS(r26)     // Catch:{ all -> 0x00ba }
            r12 = r3
            r21 = r4
            if (r10 == 0) goto L_0x00bd
            java.lang.String r3 = "auto"
            java.lang.String r4 = r10.zzb     // Catch:{ all -> 0x00ba }
            boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x00ba }
            if (r3 == 0) goto L_0x00b8
            goto L_0x00bd
        L_0x00b8:
            r4 = r15
            goto L_0x00f1
        L_0x00ba:
            r0 = move-exception
            goto L_0x0412
        L_0x00bd:
            if (r11 == 0) goto L_0x00eb
            com.google.android.gms.measurement.internal.zzqb r3 = new com.google.android.gms.measurement.internal.zzqb     // Catch:{ all -> 0x00ba }
            java.lang.String r16 = "_npa"
            boolean r4 = r11.booleanValue()     // Catch:{ all -> 0x00ba }
            if (r15 == r4) goto L_0x00cc
            r17 = 0
            goto L_0x00ce
        L_0x00cc:
            r17 = 1
        L_0x00ce:
            java.lang.Long r19 = java.lang.Long.valueOf(r17)     // Catch:{ all -> 0x00ba }
            java.lang.String r20 = "auto"
            r4 = r15
            r15 = r3
            r17 = r13
            r15.<init>(r16, r17, r19, r20)     // Catch:{ all -> 0x00ba }
            if (r10 == 0) goto L_0x00e7
            java.lang.Object r6 = r10.zze     // Catch:{ all -> 0x00ba }
            java.lang.Long r10 = r3.zzd     // Catch:{ all -> 0x00ba }
            boolean r6 = r6.equals(r10)     // Catch:{ all -> 0x00ba }
            if (r6 != 0) goto L_0x00f1
        L_0x00e7:
            r1.zzas(r3, r2)     // Catch:{ all -> 0x00ba }
            goto L_0x00f1
        L_0x00eb:
            r4 = r15
            if (r10 == 0) goto L_0x00f1
            r1.zzag(r6, r2)     // Catch:{ all -> 0x00ba }
        L_0x00f1:
            com.google.android.gms.measurement.internal.zzam r3 = r25.zzi()     // Catch:{ all -> 0x00ba }
            com.google.android.gms.measurement.internal.zzgg r6 = com.google.android.gms.measurement.internal.zzgi.zzbl     // Catch:{ all -> 0x00ba }
            r10 = 0
            boolean r3 = r3.zzx(r10, r6)     // Catch:{ all -> 0x00ba }
            if (r3 == 0) goto L_0x0104
            long r10 = r2.zzF     // Catch:{ all -> 0x00ba }
            r1.zzP(r2, r10)     // Catch:{ all -> 0x00ba }
            goto L_0x0107
        L_0x0104:
            r1.zzP(r2, r13)     // Catch:{ all -> 0x00ba }
        L_0x0107:
            r25.zzg(r26)     // Catch:{ all -> 0x00ba }
            if (r9 != 0) goto L_0x0118
            com.google.android.gms.measurement.internal.zzaw r6 = r25.zzj()     // Catch:{ all -> 0x00ba }
            java.lang.String r9 = "_f"
            com.google.android.gms.measurement.internal.zzbd r6 = r6.zzs(r8, r9)     // Catch:{ all -> 0x00ba }
            r15 = 0
            goto L_0x0123
        L_0x0118:
            com.google.android.gms.measurement.internal.zzaw r6 = r25.zzj()     // Catch:{ all -> 0x00ba }
            java.lang.String r9 = "_v"
            com.google.android.gms.measurement.internal.zzbd r6 = r6.zzs(r8, r9)     // Catch:{ all -> 0x00ba }
            r15 = r4
        L_0x0123:
            if (r6 != 0) goto L_0x03e2
            r9 = 3600000(0x36ee80, double:1.7786363E-317)
            long r16 = r13 / r9
            r18 = 1
            long r16 = r16 + r18
            long r16 = r16 * r9
            java.lang.String r6 = "_dac"
            java.lang.String r9 = "_et"
            java.lang.String r10 = "_r"
            java.lang.String r11 = "_c"
            if (r15 != 0) goto L_0x0393
            com.google.android.gms.measurement.internal.zzqb r15 = new com.google.android.gms.measurement.internal.zzqb     // Catch:{ all -> 0x00ba }
            java.lang.String r18 = "_fot"
            java.lang.Long r19 = java.lang.Long.valueOf(r16)     // Catch:{ all -> 0x00ba }
            java.lang.String r20 = "auto"
            r22 = r15
            r15 = r22
            r16 = r18
            r17 = r13
            r15.<init>(r16, r17, r19, r20)     // Catch:{ all -> 0x00ba }
            r1.zzas(r15, r2)     // Catch:{ all -> 0x00ba }
            com.google.android.gms.measurement.internal.zzil r15 = r25.zzaX()     // Catch:{ all -> 0x00ba }
            r15.zzg()     // Catch:{ all -> 0x00ba }
            com.google.android.gms.measurement.internal.zzhw r15 = r1.zzm     // Catch:{ all -> 0x00ba }
            java.lang.Object r15 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r15)     // Catch:{ all -> 0x00ba }
            com.google.android.gms.measurement.internal.zzhw r15 = (com.google.android.gms.measurement.internal.zzhw) r15     // Catch:{ all -> 0x00ba }
            if (r8 == 0) goto L_0x0169
            boolean r16 = r8.isEmpty()     // Catch:{ all -> 0x00ba }
            if (r16 == 0) goto L_0x016d
        L_0x0169:
            r23 = r13
            goto L_0x0247
        L_0x016d:
            com.google.android.gms.measurement.internal.zzio r3 = r15.zza     // Catch:{ all -> 0x00ba }
            com.google.android.gms.measurement.internal.zzil r16 = r3.zzaX()     // Catch:{ all -> 0x00ba }
            r16.zzg()     // Catch:{ all -> 0x00ba }
            boolean r16 = r15.zza()     // Catch:{ all -> 0x00ba }
            if (r16 != 0) goto L_0x018d
            com.google.android.gms.measurement.internal.zzhe r0 = r3.zzaW()     // Catch:{ all -> 0x00ba }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzi()     // Catch:{ all -> 0x00ba }
            java.lang.String r3 = "Install Referrer Reporter is not available"
            r0.zza(r3)     // Catch:{ all -> 0x00ba }
            r23 = r13
            goto L_0x0256
        L_0x018d:
            com.google.android.gms.measurement.internal.zzhv r4 = new com.google.android.gms.measurement.internal.zzhv     // Catch:{ all -> 0x00ba }
            r4.<init>(r15, r8)     // Catch:{ all -> 0x00ba }
            com.google.android.gms.measurement.internal.zzil r8 = r3.zzaX()     // Catch:{ all -> 0x00ba }
            r8.zzg()     // Catch:{ all -> 0x00ba }
            android.content.Intent r8 = new android.content.Intent     // Catch:{ all -> 0x00ba }
            r23 = r13
            java.lang.String r13 = "com.google.android.finsky.BIND_GET_INSTALL_REFERRER_SERVICE"
            r8.<init>(r13)     // Catch:{ all -> 0x00ba }
            android.content.ComponentName r13 = new android.content.ComponentName     // Catch:{ all -> 0x00ba }
            java.lang.String r14 = "com.google.android.finsky.externalreferrer.GetInstallReferrerService"
            r13.<init>(r0, r14)     // Catch:{ all -> 0x00ba }
            r8.setComponent(r13)     // Catch:{ all -> 0x00ba }
            android.content.Context r13 = r3.zzaT()     // Catch:{ all -> 0x00ba }
            android.content.pm.PackageManager r13 = r13.getPackageManager()     // Catch:{ all -> 0x00ba }
            if (r13 != 0) goto L_0x01c5
            com.google.android.gms.measurement.internal.zzhe r0 = r3.zzaW()     // Catch:{ all -> 0x00ba }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzm()     // Catch:{ all -> 0x00ba }
            java.lang.String r3 = "Failed to obtain Package Manager to verify binding conditions for Install Referrer"
            r0.zza(r3)     // Catch:{ all -> 0x00ba }
            goto L_0x0256
        L_0x01c5:
            r14 = 0
            java.util.List r13 = r13.queryIntentServices(r8, r14)     // Catch:{ all -> 0x00ba }
            if (r13 == 0) goto L_0x0239
            boolean r16 = r13.isEmpty()     // Catch:{ all -> 0x00ba }
            if (r16 != 0) goto L_0x0239
            java.lang.Object r13 = r13.get(r14)     // Catch:{ all -> 0x00ba }
            android.content.pm.ResolveInfo r13 = (android.content.pm.ResolveInfo) r13     // Catch:{ all -> 0x00ba }
            android.content.pm.ServiceInfo r13 = r13.serviceInfo     // Catch:{ all -> 0x00ba }
            if (r13 == 0) goto L_0x0256
            java.lang.String r14 = r13.packageName     // Catch:{ all -> 0x00ba }
            java.lang.String r13 = r13.name     // Catch:{ all -> 0x00ba }
            if (r13 == 0) goto L_0x022b
            boolean r0 = r0.equals(r14)     // Catch:{ all -> 0x00ba }
            if (r0 == 0) goto L_0x022b
            boolean r0 = r15.zza()     // Catch:{ all -> 0x00ba }
            if (r0 == 0) goto L_0x022b
            android.content.Intent r0 = new android.content.Intent     // Catch:{ all -> 0x00ba }
            r0.<init>(r8)     // Catch:{ all -> 0x00ba }
            com.google.android.gms.common.stats.ConnectionTracker r8 = com.google.android.gms.common.stats.ConnectionTracker.getInstance()     // Catch:{ RuntimeException -> 0x020f }
            android.content.Context r13 = r3.zzaT()     // Catch:{ RuntimeException -> 0x020f }
            r14 = 1
            boolean r0 = r8.bindService(r13, r0, r4, r14)     // Catch:{ RuntimeException -> 0x020f }
            com.google.android.gms.measurement.internal.zzhe r3 = r3.zzaW()     // Catch:{ RuntimeException -> 0x020f }
            com.google.android.gms.measurement.internal.zzhc r3 = r3.zzj()     // Catch:{ RuntimeException -> 0x020f }
            java.lang.String r4 = "Install Referrer Service is"
            if (r0 == 0) goto L_0x0211
            java.lang.String r0 = "available"
            goto L_0x0213
        L_0x020f:
            r0 = move-exception
            goto L_0x0217
        L_0x0211:
            java.lang.String r0 = "not available"
        L_0x0213:
            r3.zzb(r4, r0)     // Catch:{ RuntimeException -> 0x020f }
            goto L_0x0256
        L_0x0217:
            com.google.android.gms.measurement.internal.zzio r3 = r15.zza     // Catch:{ all -> 0x00ba }
            com.google.android.gms.measurement.internal.zzhe r3 = r3.zzaW()     // Catch:{ all -> 0x00ba }
            com.google.android.gms.measurement.internal.zzhc r3 = r3.zze()     // Catch:{ all -> 0x00ba }
            java.lang.String r4 = "Exception occurred while binding to Install Referrer Service"
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x00ba }
            r3.zzb(r4, r0)     // Catch:{ all -> 0x00ba }
            goto L_0x0256
        L_0x022b:
            com.google.android.gms.measurement.internal.zzhe r0 = r3.zzaW()     // Catch:{ all -> 0x00ba }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzk()     // Catch:{ all -> 0x00ba }
            java.lang.String r3 = "Play Store version 8.3.73 or higher required for Install Referrer"
            r0.zza(r3)     // Catch:{ all -> 0x00ba }
            goto L_0x0256
        L_0x0239:
            com.google.android.gms.measurement.internal.zzhe r0 = r3.zzaW()     // Catch:{ all -> 0x00ba }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzi()     // Catch:{ all -> 0x00ba }
            java.lang.String r3 = "Play Service for fetching Install Referrer is unavailable on device"
            r0.zza(r3)     // Catch:{ all -> 0x00ba }
            goto L_0x0256
        L_0x0247:
            com.google.android.gms.measurement.internal.zzio r0 = r15.zza     // Catch:{ all -> 0x00ba }
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()     // Catch:{ all -> 0x00ba }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzm()     // Catch:{ all -> 0x00ba }
            java.lang.String r3 = "Install Referrer Reporter was called with invalid app package name"
            r0.zza(r3)     // Catch:{ all -> 0x00ba }
        L_0x0256:
            com.google.android.gms.measurement.internal.zzil r0 = r25.zzaX()     // Catch:{ all -> 0x00ba }
            r0.zzg()     // Catch:{ all -> 0x00ba }
            r25.zzM()     // Catch:{ all -> 0x00ba }
            android.os.Bundle r3 = new android.os.Bundle     // Catch:{ all -> 0x00ba }
            r3.<init>()     // Catch:{ all -> 0x00ba }
            r13 = 1
            r3.putLong(r11, r13)     // Catch:{ all -> 0x00ba }
            r3.putLong(r10, r13)     // Catch:{ all -> 0x00ba }
            r10 = 0
            r3.putLong(r7, r10)     // Catch:{ all -> 0x00ba }
            r3.putLong(r5, r10)     // Catch:{ all -> 0x00ba }
            r4 = r21
            r3.putLong(r4, r10)     // Catch:{ all -> 0x00ba }
            r3.putLong(r12, r10)     // Catch:{ all -> 0x00ba }
            r3.putLong(r9, r13)     // Catch:{ all -> 0x00ba }
            boolean r0 = r2.zzo     // Catch:{ all -> 0x00ba }
            if (r0 == 0) goto L_0x0287
            r3.putLong(r6, r13)     // Catch:{ all -> 0x00ba }
        L_0x0287:
            java.lang.String r0 = r2.zza     // Catch:{ all -> 0x00ba }
            java.lang.Object r0 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r0)     // Catch:{ all -> 0x00ba }
            r6 = r0
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ all -> 0x00ba }
            com.google.android.gms.measurement.internal.zzaw r0 = r25.zzj()     // Catch:{ all -> 0x00ba }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r6)     // Catch:{ all -> 0x00ba }
            r0.zzg()     // Catch:{ all -> 0x00ba }
            r0.zzav()     // Catch:{ all -> 0x00ba }
            java.lang.String r8 = "first_open_count"
            long r10 = r0.zze(r6, r8)     // Catch:{ all -> 0x00ba }
            com.google.android.gms.measurement.internal.zzio r0 = r1.zzn     // Catch:{ all -> 0x00ba }
            android.content.Context r8 = r0.zzaT()     // Catch:{ all -> 0x00ba }
            android.content.pm.PackageManager r8 = r8.getPackageManager()     // Catch:{ all -> 0x00ba }
            if (r8 != 0) goto L_0x02c4
            com.google.android.gms.measurement.internal.zzhe r0 = r25.zzaW()     // Catch:{ all -> 0x00ba }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zze()     // Catch:{ all -> 0x00ba }
            java.lang.String r4 = "PackageManager is null, first open report might be inaccurate. appId"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzhe.zzn(r6)     // Catch:{ all -> 0x00ba }
            r0.zzb(r4, r6)     // Catch:{ all -> 0x00ba }
        L_0x02c0:
            r6 = 0
            goto L_0x0374
        L_0x02c4:
            android.content.Context r0 = r0.zzaT()     // Catch:{ NameNotFoundException -> 0x02d2 }
            com.google.android.gms.common.wrappers.PackageManagerWrapper r0 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r0)     // Catch:{ NameNotFoundException -> 0x02d2 }
            r8 = 0
            android.content.pm.PackageInfo r0 = r0.getPackageInfo(r6, r8)     // Catch:{ NameNotFoundException -> 0x02d2 }
            goto L_0x02e5
        L_0x02d2:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzhe r8 = r25.zzaW()     // Catch:{ all -> 0x00ba }
            com.google.android.gms.measurement.internal.zzhc r8 = r8.zze()     // Catch:{ all -> 0x00ba }
            java.lang.String r9 = "Package info is null, first open report might be inaccurate. appId"
            java.lang.Object r13 = com.google.android.gms.measurement.internal.zzhe.zzn(r6)     // Catch:{ all -> 0x00ba }
            r8.zzc(r9, r13, r0)     // Catch:{ all -> 0x00ba }
            r0 = 0
        L_0x02e5:
            if (r0 == 0) goto L_0x0335
            long r8 = r0.firstInstallTime     // Catch:{ all -> 0x00ba }
            r13 = 0
            int r15 = (r8 > r13 ? 1 : (r8 == r13 ? 0 : -1))
            if (r15 == 0) goto L_0x0335
            long r13 = r0.lastUpdateTime     // Catch:{ all -> 0x00ba }
            int r0 = (r8 > r13 ? 1 : (r8 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x0317
            com.google.android.gms.measurement.internal.zzam r0 = r25.zzi()     // Catch:{ all -> 0x00ba }
            com.google.android.gms.measurement.internal.zzgg r8 = com.google.android.gms.measurement.internal.zzgi.zzaH     // Catch:{ all -> 0x00ba }
            r9 = 0
            boolean r0 = r0.zzx(r9, r8)     // Catch:{ all -> 0x00ba }
            if (r0 == 0) goto L_0x0311
            r13 = 0
            int r0 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1))
            if (r0 != 0) goto L_0x030f
            r13 = 1
            r3.putLong(r7, r13)     // Catch:{ all -> 0x00ba }
            r10 = 0
        L_0x030f:
            r15 = 0
            goto L_0x0319
        L_0x0311:
            r13 = 1
            r3.putLong(r7, r13)     // Catch:{ all -> 0x00ba }
            goto L_0x030f
        L_0x0317:
            r9 = 0
            r15 = 1
        L_0x0319:
            com.google.android.gms.measurement.internal.zzqb r0 = new com.google.android.gms.measurement.internal.zzqb     // Catch:{ all -> 0x00ba }
            java.lang.String r16 = "_fi"
            r7 = 1
            if (r7 == r15) goto L_0x0323
            r7 = 0
            goto L_0x0325
        L_0x0323:
            r7 = 1
        L_0x0325:
            java.lang.Long r19 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x00ba }
            java.lang.String r20 = "auto"
            r15 = r0
            r17 = r23
            r15.<init>(r16, r17, r19, r20)     // Catch:{ all -> 0x00ba }
            r1.zzas(r0, r2)     // Catch:{ all -> 0x00ba }
            goto L_0x0336
        L_0x0335:
            r9 = 0
        L_0x0336:
            com.google.android.gms.measurement.internal.zzio r0 = r1.zzn     // Catch:{ NameNotFoundException -> 0x0346 }
            android.content.Context r0 = r0.zzaT()     // Catch:{ NameNotFoundException -> 0x0346 }
            com.google.android.gms.common.wrappers.PackageManagerWrapper r0 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r0)     // Catch:{ NameNotFoundException -> 0x0346 }
            r7 = 0
            android.content.pm.ApplicationInfo r0 = r0.getApplicationInfo(r6, r7)     // Catch:{ NameNotFoundException -> 0x0346 }
            goto L_0x0359
        L_0x0346:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzhe r7 = r25.zzaW()     // Catch:{ all -> 0x00ba }
            com.google.android.gms.measurement.internal.zzhc r7 = r7.zze()     // Catch:{ all -> 0x00ba }
            java.lang.String r8 = "Application info is null, first open report might be inaccurate. appId"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzhe.zzn(r6)     // Catch:{ all -> 0x00ba }
            r7.zzc(r8, r6, r0)     // Catch:{ all -> 0x00ba }
            r0 = r9
        L_0x0359:
            if (r0 == 0) goto L_0x02c0
            int r6 = r0.flags     // Catch:{ all -> 0x00ba }
            r7 = 1
            r6 = r6 & r7
            if (r6 == 0) goto L_0x0367
            r6 = 1
            r3.putLong(r4, r6)     // Catch:{ all -> 0x00ba }
            goto L_0x0369
        L_0x0367:
            r6 = 1
        L_0x0369:
            int r0 = r0.flags     // Catch:{ all -> 0x00ba }
            r0 = r0 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L_0x02c0
            r3.putLong(r12, r6)     // Catch:{ all -> 0x00ba }
            goto L_0x02c0
        L_0x0374:
            int r0 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            if (r0 < 0) goto L_0x037b
            r3.putLong(r5, r10)     // Catch:{ all -> 0x00ba }
        L_0x037b:
            com.google.android.gms.measurement.internal.zzbh r0 = new com.google.android.gms.measurement.internal.zzbh     // Catch:{ all -> 0x00ba }
            java.lang.String r16 = "_f"
            com.google.android.gms.measurement.internal.zzbf r4 = new com.google.android.gms.measurement.internal.zzbf     // Catch:{ all -> 0x00ba }
            r4.<init>(r3)     // Catch:{ all -> 0x00ba }
            java.lang.String r18 = "auto"
            r15 = r0
            r17 = r4
            r19 = r23
            r15.<init>(r16, r17, r18, r19)     // Catch:{ all -> 0x00ba }
            r1.zzU(r0, r2)     // Catch:{ all -> 0x00ba }
            goto L_0x0403
        L_0x0393:
            r23 = r13
            com.google.android.gms.measurement.internal.zzqb r0 = new com.google.android.gms.measurement.internal.zzqb     // Catch:{ all -> 0x00ba }
            java.lang.String r3 = "_fvt"
            java.lang.Long r19 = java.lang.Long.valueOf(r16)     // Catch:{ all -> 0x00ba }
            java.lang.String r20 = "auto"
            r15 = r0
            r16 = r3
            r17 = r23
            r15.<init>(r16, r17, r19, r20)     // Catch:{ all -> 0x00ba }
            r1.zzas(r0, r2)     // Catch:{ all -> 0x00ba }
            com.google.android.gms.measurement.internal.zzil r0 = r25.zzaX()     // Catch:{ all -> 0x00ba }
            r0.zzg()     // Catch:{ all -> 0x00ba }
            r25.zzM()     // Catch:{ all -> 0x00ba }
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x00ba }
            r0.<init>()     // Catch:{ all -> 0x00ba }
            r3 = 1
            r0.putLong(r11, r3)     // Catch:{ all -> 0x00ba }
            r0.putLong(r10, r3)     // Catch:{ all -> 0x00ba }
            r0.putLong(r9, r3)     // Catch:{ all -> 0x00ba }
            boolean r5 = r2.zzo     // Catch:{ all -> 0x00ba }
            if (r5 == 0) goto L_0x03cb
            r0.putLong(r6, r3)     // Catch:{ all -> 0x00ba }
        L_0x03cb:
            com.google.android.gms.measurement.internal.zzbh r3 = new com.google.android.gms.measurement.internal.zzbh     // Catch:{ all -> 0x00ba }
            java.lang.String r16 = "_v"
            com.google.android.gms.measurement.internal.zzbf r4 = new com.google.android.gms.measurement.internal.zzbf     // Catch:{ all -> 0x00ba }
            r4.<init>(r0)     // Catch:{ all -> 0x00ba }
            java.lang.String r18 = "auto"
            r15 = r3
            r17 = r4
            r19 = r23
            r15.<init>(r16, r17, r18, r19)     // Catch:{ all -> 0x00ba }
            r1.zzU(r3, r2)     // Catch:{ all -> 0x00ba }
            goto L_0x0403
        L_0x03e2:
            r23 = r13
            boolean r0 = r2.zzi     // Catch:{ all -> 0x00ba }
            if (r0 == 0) goto L_0x0403
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x00ba }
            r0.<init>()     // Catch:{ all -> 0x00ba }
            com.google.android.gms.measurement.internal.zzbh r3 = new com.google.android.gms.measurement.internal.zzbh     // Catch:{ all -> 0x00ba }
            java.lang.String r16 = "_cd"
            com.google.android.gms.measurement.internal.zzbf r4 = new com.google.android.gms.measurement.internal.zzbf     // Catch:{ all -> 0x00ba }
            r4.<init>(r0)     // Catch:{ all -> 0x00ba }
            java.lang.String r18 = "auto"
            r15 = r3
            r17 = r4
            r19 = r23
            r15.<init>(r16, r17, r18, r19)     // Catch:{ all -> 0x00ba }
            r1.zzU(r3, r2)     // Catch:{ all -> 0x00ba }
        L_0x0403:
            com.google.android.gms.measurement.internal.zzaw r0 = r25.zzj()     // Catch:{ all -> 0x00ba }
            r0.zzS()     // Catch:{ all -> 0x00ba }
            com.google.android.gms.measurement.internal.zzaw r0 = r25.zzj()
            r0.zzL()
            return
        L_0x0412:
            com.google.android.gms.measurement.internal.zzaw r2 = r25.zzj()
            r2.zzL()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzpv.zzac(com.google.android.gms.measurement.internal.zzr):void");
    }

    /* access modifiers changed from: package-private */
    public final void zzad() {
        this.zzs++;
    }

    /* access modifiers changed from: package-private */
    public final void zzae(zzai zzai) {
        zzr zzaD = zzaD((String) Preconditions.checkNotNull(zzai.zza));
        if (zzaD != null) {
            zzaf(zzai, zzaD);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzaf(zzai zzai, zzr zzr2) {
        Preconditions.checkNotNull(zzai);
        Preconditions.checkNotEmpty(zzai.zza);
        Preconditions.checkNotNull(zzai.zzc);
        Preconditions.checkNotEmpty(zzai.zzc.zzb);
        zzaX().zzg();
        zzM();
        if (zzaQ(zzr2)) {
            if (!zzr2.zzh) {
                zzg(zzr2);
                return;
            }
            zzj().zzH();
            try {
                zzg(zzr2);
                String str = (String) Preconditions.checkNotNull(zzai.zza);
                zzai zzm2 = zzj().zzm(str, zzai.zzc.zzb);
                if (zzm2 != null) {
                    zzaW().zzd().zzc("Removing conditional user property", zzai.zza, this.zzn.zzj().zzf(zzai.zzc.zzb));
                    zzj().zza(str, zzai.zzc.zzb);
                    if (zzm2.zze) {
                        zzj().zzP(str, zzai.zzc.zzb);
                    }
                    zzbh zzbh = zzai.zzk;
                    if (zzbh != null) {
                        zzbf zzbf = zzbh.zzb;
                        zzax((zzbh) Preconditions.checkNotNull(zzB().zzC(str, ((zzbh) Preconditions.checkNotNull(zzbh)).zza, zzbf != null ? zzbf.zzc() : null, zzm2.zzb, zzbh.zzd, true, true)), zzr2);
                    }
                } else {
                    zzaW().zzk().zzc("Conditional user property doesn't exist", zzhe.zzn(zzai.zza), this.zzn.zzj().zzf(zzai.zzc.zzb));
                }
                zzj().zzS();
                zzj().zzL();
            } catch (Throwable th) {
                zzj().zzL();
                throw th;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzag(String str, zzr zzr2) {
        zzaX().zzg();
        zzM();
        if (zzaQ(zzr2)) {
            if (!zzr2.zzh) {
                zzg(zzr2);
                return;
            }
            Boolean zzaS = zzaS(zzr2);
            if (!"_npa".equals(str) || zzaS == null) {
                zzhc zzd2 = zzaW().zzd();
                zzio zzio = this.zzn;
                zzd2.zzb("Removing user property", zzio.zzj().zzf(str));
                zzj().zzH();
                try {
                    zzg(zzr2);
                    if ("_id".equals(str)) {
                        zzj().zzP((String) Preconditions.checkNotNull(zzr2.zza), "_lair");
                    }
                    zzj().zzP((String) Preconditions.checkNotNull(zzr2.zza), str);
                    zzj().zzS();
                    zzaW().zzd().zzb("User property removed", zzio.zzj().zzf(str));
                    zzj().zzL();
                } catch (Throwable th) {
                    zzj().zzL();
                    throw th;
                }
            } else {
                zzaW().zzd().zza("Falling back to manifest metadata value for ad personalization");
                zzas(new zzqb("_npa", zzaU().currentTimeMillis(), Long.valueOf(true != zzaS.booleanValue() ? 0 : 1), "auto"), zzr2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzah(zzr zzr2) {
        if (this.zzz != null) {
            ArrayList arrayList = new ArrayList();
            this.zzA = arrayList;
            arrayList.addAll(this.zzz);
        }
        zzaw zzj2 = zzj();
        String str = (String) Preconditions.checkNotNull(zzr2.zza);
        Preconditions.checkNotEmpty(str);
        zzj2.zzg();
        zzj2.zzav();
        try {
            SQLiteDatabase zzj3 = zzj2.zzj();
            String[] strArr = {str};
            int delete = zzj3.delete("apps", "app_id=?", strArr) + zzj3.delete("events", "app_id=?", strArr) + zzj3.delete("events_snapshot", "app_id=?", strArr) + zzj3.delete("user_attributes", "app_id=?", strArr) + zzj3.delete("conditional_properties", "app_id=?", strArr) + zzj3.delete("raw_events", "app_id=?", strArr) + zzj3.delete("raw_events_metadata", "app_id=?", strArr) + zzj3.delete("queue", "app_id=?", strArr) + zzj3.delete("audience_filter_values", "app_id=?", strArr) + zzj3.delete("main_event_params", "app_id=?", strArr) + zzj3.delete("default_event_params", "app_id=?", strArr) + zzj3.delete("trigger_uris", "app_id=?", strArr) + zzj3.delete("upload_queue", "app_id=?", strArr);
            if (delete > 0) {
                zzj2.zzu.zzaW().zzj().zzc("Reset analytics data. app, records", str, Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzj2.zzu.zzaW().zze().zzc("Error resetting analytics data. appId, error", zzhe.zzn(str), e);
        }
        if (zzr2.zzh) {
            zzac(zzr2);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzai(zzr zzr2) {
        zzaX().zzg();
        zzM();
        String str = zzr2.zza;
        Preconditions.checkNotEmpty(str);
        zzba zze2 = zzba.zze(zzr2.zzA);
        zzaW().zzj().zzc("Setting DMA consent for package", str, zze2);
        zzaX().zzg();
        zzM();
        zzju zzf2 = zzba.zzc(zzd(str), 100).zzf();
        this.zzD.put(str, zze2);
        zzj().zzU(str, zze2);
        zzju zzf3 = zzba.zzc(zzd(str), 100).zzf();
        zzaX().zzg();
        zzM();
        zzju zzju = zzju.DENIED;
        boolean z = true;
        boolean z2 = zzf2 == zzju && zzf3 == zzju.GRANTED;
        if (!(zzf2 == zzju.GRANTED && zzf3 == zzju)) {
            z = false;
        }
        if (z2 || z) {
            zzaW().zzj().zzb("Generated _dcu event for", str);
            Bundle bundle = new Bundle();
            if (zzj().zzo(zza(), str, false, false, false, false, false, false, false).zzf < ((long) zzi().zzh(str, zzgi.zzal))) {
                bundle.putLong(NotificationMessage.NOTIF_KEY_REQUEST_ID, 1);
                zzaW().zzj().zzc("_dcu realtime event count", str, Long.valueOf(zzj().zzo(zza(), str, false, false, false, false, false, true, false).zzf));
            }
            this.zzK.zza(str, "_dcu", bundle);
        }
    }

    public final void zzaj(String str, zzmh zzmh) {
        zzaX().zzg();
        String str2 = this.zzH;
        if (str2 == null || str2.equals(str) || zzmh != null) {
            this.zzH = str;
            this.zzG = zzmh;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzak(zzr zzr2) {
        zzaX().zzg();
        zzM();
        String str = zzr2.zza;
        Preconditions.checkNotEmpty(str);
        zzjx zzk2 = zzjx.zzk(zzr2.zzu, zzr2.zzz);
        zzu(str);
        zzaW().zzj().zzc("Setting storage consent for package", str, zzk2);
        zzaq(str, zzk2);
    }

    /* access modifiers changed from: package-private */
    public final void zzal(List list) {
        Preconditions.checkArgument(!list.isEmpty());
        if (this.zzz != null) {
            zzaW().zze().zza("Set uploading progress before finishing the previous upload");
        } else {
            this.zzz = new ArrayList(list);
        }
    }

    /* access modifiers changed from: protected */
    public final void zzam() {
        zzaX().zzg();
        zzj().zzO();
        zzaw zzj2 = zzj();
        zzj2.zzg();
        zzj2.zzav();
        if (zzj2.zzae()) {
            zzgg zzgg = zzgi.zzau;
            if (((Long) zzgg.zza((Object) null)).longValue() != 0) {
                SQLiteDatabase zzj3 = zzj2.zzj();
                zzio zzio = zzj2.zzu;
                int delete = zzj3.delete("trigger_uris", "abs(timestamp_millis - ?) > cast(? as integer)", new String[]{String.valueOf(zzio.zzaU().currentTimeMillis()), String.valueOf(zzgg.zza((Object) null))});
                if (delete > 0) {
                    zzio.zzaW().zzj().zzb("Deleted stale trigger uris. rowsDeleted", Integer.valueOf(delete));
                }
            }
        }
        if (this.zzk.zzd.zza() == 0) {
            this.zzk.zzd.zzb(zzaU().currentTimeMillis());
        }
        zzaL();
    }

    /* access modifiers changed from: package-private */
    public final void zzan(zzai zzai) {
        zzr zzaD = zzaD((String) Preconditions.checkNotNull(zzai.zza));
        if (zzaD != null) {
            zzao(zzai, zzaD);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzao(zzai zzai, zzr zzr2) {
        Preconditions.checkNotNull(zzai);
        Preconditions.checkNotEmpty(zzai.zza);
        Preconditions.checkNotNull(zzai.zzb);
        Preconditions.checkNotNull(zzai.zzc);
        Preconditions.checkNotEmpty(zzai.zzc.zzb);
        zzaX().zzg();
        zzM();
        if (zzaQ(zzr2)) {
            if (!zzr2.zzh) {
                zzg(zzr2);
                return;
            }
            zzai zzai2 = new zzai(zzai);
            boolean z = false;
            zzai2.zze = false;
            zzj().zzH();
            try {
                zzai zzm2 = zzj().zzm((String) Preconditions.checkNotNull(zzai2.zza), zzai2.zzc.zzb);
                if (zzm2 != null && !zzm2.zzb.equals(zzai2.zzb)) {
                    zzaW().zzk().zzd("Updating a conditional user property with different origin. name, origin, origin (from DB)", this.zzn.zzj().zzf(zzai2.zzc.zzb), zzai2.zzb, zzm2.zzb);
                }
                if (zzm2 != null && zzm2.zze) {
                    zzai2.zzb = zzm2.zzb;
                    zzai2.zzd = zzm2.zzd;
                    zzai2.zzh = zzm2.zzh;
                    zzai2.zzf = zzm2.zzf;
                    zzai2.zzi = zzm2.zzi;
                    zzai2.zze = true;
                    zzqb zzqb = zzai2.zzc;
                    zzai2.zzc = new zzqb(zzqb.zzb, zzm2.zzc.zzc, zzqb.zza(), zzm2.zzc.zzf);
                } else if (TextUtils.isEmpty(zzai2.zzf)) {
                    zzqb zzqb2 = zzai2.zzc;
                    zzai2.zzc = new zzqb(zzqb2.zzb, zzai2.zzd, zzqb2.zza(), zzai2.zzc.zzf);
                    zzai2.zze = true;
                    z = true;
                }
                if (zzai2.zze) {
                    zzqb zzqb3 = zzai2.zzc;
                    zzqd zzqd = new zzqd((String) Preconditions.checkNotNull(zzai2.zza), zzai2.zzb, zzqb3.zzb, zzqb3.zzc, Preconditions.checkNotNull(zzqb3.zza()));
                    if (zzj().zzai(zzqd)) {
                        zzaW().zzd().zzd("User property updated immediately", zzai2.zza, this.zzn.zzj().zzf(zzqd.zzc), zzqd.zze);
                    } else {
                        zzaW().zze().zzd("(2)Too many active user properties, ignoring", zzhe.zzn(zzai2.zza), this.zzn.zzj().zzf(zzqd.zzc), zzqd.zze);
                    }
                    if (z && zzai2.zzi != null) {
                        zzax(new zzbh(zzai2.zzi, zzai2.zzd), zzr2);
                    }
                }
                if (zzj().zzah(zzai2)) {
                    zzaW().zzd().zzd("Conditional property added", zzai2.zza, this.zzn.zzj().zzf(zzai2.zzc.zzb), zzai2.zzc.zza());
                } else {
                    zzaW().zze().zzd("Too many conditional properties, ignoring", zzhe.zzn(zzai2.zza), this.zzn.zzj().zzf(zzai2.zzc.zzb), zzai2.zzc.zza());
                }
                zzj().zzS();
                zzj().zzL();
            } catch (Throwable th) {
                zzj().zzL();
                throw th;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzap(String str, zzag zzag) {
        zzam zzi2 = zzi();
        zzgg zzgg = zzgi.zzaP;
        if (zzi2.zzx((String) null, zzgg)) {
            zzaX().zzg();
            zzM();
            zzaw zzj2 = zzj();
            long j = zzag.zza;
            zzpz zzx2 = zzj2.zzx(j);
            if (zzx2 == null) {
                zzaW().zzk().zzc("[sgtm] Queued batch doesn't exist. appId, rowId", str, Long.valueOf(j));
                return;
            }
            String zzh2 = zzx2.zzh();
            if (zzag.zzb == zzme.SUCCESS.zza()) {
                Map map = this.zzF;
                if (map.containsKey(zzh2)) {
                    map.remove(zzh2);
                }
                zzaw zzj3 = zzj();
                Long valueOf = Long.valueOf(j);
                zzj3.zzK(valueOf);
                zzaW().zzj().zzc("[sgtm] queued batch deleted after successful client upload. appId, rowId", str, valueOf);
                long j2 = zzag.zzc;
                if (j2 > 0) {
                    zzaw zzj4 = zzj();
                    zzio zzio = zzj4.zzu;
                    if (zzio.zzf().zzx((String) null, zzgg)) {
                        zzj4.zzg();
                        zzj4.zzav();
                        Long valueOf2 = Long.valueOf(j2);
                        Preconditions.checkNotNull(valueOf2);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("upload_type", Integer.valueOf(zzmf.GOOGLE_SIGNAL.zza()));
                        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, Long.valueOf(zzio.zzaU().currentTimeMillis()));
                        try {
                            if (((long) zzj4.zzj().update("upload_queue", contentValues, "rowid=? AND app_id=? AND upload_type=?", new String[]{String.valueOf(j2), str, String.valueOf(zzmf.GOOGLE_SIGNAL_PENDING.zza())})) != 1) {
                                zzio.zzaW().zzk().zzc("Google Signal pending batch not updated. appId, rowId", str, valueOf2);
                            }
                        } catch (SQLiteException e) {
                            zzj4.zzu.zzaW().zze().zzd("Failed to update google Signal pending batch. appid, rowId", str, Long.valueOf(j2), e);
                            throw e;
                        }
                    }
                    zzaW().zzj().zzc("[sgtm] queued Google Signal batch updated. appId, signalRowId", str, Long.valueOf(zzag.zzc));
                    zzav(str);
                    return;
                }
                return;
            }
            if (zzag.zzb == zzme.BACKOFF.zza()) {
                Map map2 = this.zzF;
                zzpt zzpt = (zzpt) map2.get(zzh2);
                if (zzpt == null) {
                    zzpt = new zzpt(this);
                    map2.put(zzh2, zzpt);
                } else {
                    zzpt.zzb();
                }
                zzaW().zzj().zzd("[sgtm] Putting sGTM server in backoff mode. appId, destination, nextRetryInSeconds", str, zzh2, Long.valueOf((zzpt.zzc - zzaU().currentTimeMillis()) / 1000));
            }
            zzaw zzj5 = zzj();
            Long valueOf3 = Long.valueOf(zzag.zza);
            zzj5.zzN(valueOf3);
            zzaW().zzj().zzc("[sgtm] increased batch retry count after failed client upload. appId, rowId", str, valueOf3);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzaq(String str, zzjx zzjx) {
        zzaX().zzg();
        zzM();
        this.zzC.put(str, zzjx);
        zzj().zzX(str, zzjx);
    }

    /* access modifiers changed from: package-private */
    public final void zzar(String str, boolean z, Long l, Long l2) {
        zzh zzl2 = zzj().zzl(str);
        if (zzl2 != null) {
            zzl2.zzaF(z);
            zzl2.zzaG(l);
            zzl2.zzaH(l2);
            if (zzl2.zzaK()) {
                zzj().zzT(zzl2, false, false);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzas(zzqb zzqb, zzr zzr2) {
        String str;
        zzqd zzy2;
        long j;
        int i;
        zzqb zzqb2 = zzqb;
        zzr zzr3 = zzr2;
        zzaX().zzg();
        zzM();
        if (zzaQ(zzr2)) {
            if (!zzr3.zzh) {
                zzg(zzr3);
                return;
            }
            zzqf zzB2 = zzB();
            String str2 = zzqb2.zzb;
            int zzj2 = zzB2.zzj(str2);
            if (zzj2 != 0) {
                zzqf zzB3 = zzB();
                zzi();
                zzB().zzR(this.zzK, zzr3.zza, zzj2, "_ev", zzB3.zzG(str2, 24, true), str2 != null ? str2.length() : 0);
                return;
            }
            int zzd2 = zzB().zzd(str2, zzqb.zza());
            if (zzd2 != 0) {
                zzqf zzB4 = zzB();
                zzi();
                String zzG2 = zzB4.zzG(str2, 24, true);
                Object zza2 = zzqb.zza();
                if (zza2 == null || (!(zza2 instanceof String) && !(zza2 instanceof CharSequence))) {
                    i = 0;
                } else {
                    i = zza2.toString().length();
                }
                zzB().zzR(this.zzK, zzr3.zza, zzd2, "_ev", zzG2, i);
                return;
            }
            Object zzE2 = zzB().zzE(str2, zzqb.zza());
            if (zzE2 != null) {
                if (NotificationMessage.NOTIF_KEY_SID.equals(str2)) {
                    long j2 = zzqb2.zzc;
                    String str3 = zzqb2.zzf;
                    String str4 = (String) Preconditions.checkNotNull(zzr3.zza);
                    zzqd zzy3 = zzj().zzy(str4, "_sno");
                    if (zzy3 != null) {
                        Object obj = zzy3.zze;
                        if (obj instanceof Long) {
                            j = ((Long) obj).longValue();
                            str = NotificationMessage.NOTIF_KEY_SID;
                            zzas(new zzqb("_sno", j2, Long.valueOf(j + 1), str3), zzr3);
                        }
                    }
                    if (zzy3 != null) {
                        zzaW().zzk().zzb("Retrieved last session number from database does not contain a valid (long) value", zzy3.zze);
                    }
                    zzbd zzs2 = zzj().zzs(str4, "_s");
                    if (zzs2 != null) {
                        zzhc zzj3 = zzaW().zzj();
                        str = NotificationMessage.NOTIF_KEY_SID;
                        long j3 = zzs2.zzc;
                        zzj3.zzb("Backfill the session number. Last used session number", Long.valueOf(j3));
                        j = j3;
                    } else {
                        str = NotificationMessage.NOTIF_KEY_SID;
                        j = 0;
                    }
                    zzas(new zzqb("_sno", j2, Long.valueOf(j + 1), str3), zzr3);
                } else {
                    str = NotificationMessage.NOTIF_KEY_SID;
                }
                String str5 = zzr3.zza;
                zzqd zzqd = new zzqd((String) Preconditions.checkNotNull(str5), (String) Preconditions.checkNotNull(zzqb2.zzf), str2, zzqb2.zzc, zzE2);
                zzhc zzj4 = zzaW().zzj();
                zzio zzio = this.zzn;
                String str6 = zzqd.zzc;
                zzj4.zzc("Setting user property", zzio.zzj().zzf(str6), zzE2);
                zzj().zzH();
                try {
                    if ("_id".equals(str6) && (zzy2 = zzj().zzy(str5, "_id")) != null && !zzqd.zze.equals(zzy2.zze)) {
                        zzj().zzP(str5, "_lair");
                    }
                    zzg(zzr3);
                    boolean zzai = zzj().zzai(zzqd);
                    if (str.equals(str2)) {
                        long zzd3 = zzA().zzd(zzr3.zzw);
                        zzh zzl2 = zzj().zzl(str5);
                        if (zzl2 != null) {
                            zzl2.zzaB(zzd3);
                            if (zzl2.zzaK()) {
                                zzj().zzT(zzl2, false, false);
                            }
                        }
                    }
                    zzj().zzS();
                    if (!zzai) {
                        zzaW().zze().zzc("Too many unique user properties are set. Ignoring user property", zzio.zzj().zzf(str6), zzqd.zze);
                        zzB().zzR(this.zzK, str5, 9, (String) null, (String) null, 0);
                    }
                    zzj().zzL();
                } catch (Throwable th) {
                    zzj().zzL();
                    throw th;
                }
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r7v0 */
    /* JADX WARNING: type inference failed for: r7v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r7v3 */
    /* JADX WARNING: type inference failed for: r7v4 */
    /* JADX WARNING: type inference failed for: r7v6, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r7v9 */
    /* JADX WARNING: type inference failed for: r7v10 */
    /* JADX WARNING: type inference failed for: r7v13 */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0124, code lost:
        if (r7 == 0) goto L_0x0127;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0194, code lost:
        if (r1 != null) goto L_0x0170;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x01b2 A[SYNTHETIC, Splitter:B:80:0x01b2] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:57:0x0170=Splitter:B:57:0x0170, B:44:0x0127=Splitter:B:44:0x0127, B:71:0x0197=Splitter:B:71:0x0197, B:35:0x0109=Splitter:B:35:0x0109} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzat() {
        /*
            r11 = this;
            com.google.android.gms.measurement.internal.zzil r0 = r11.zzaX()
            r0.zzg()
            r11.zzM()
            r0 = 1
            r11.zzw = r0
            r0 = 0
            com.google.android.gms.measurement.internal.zzio r1 = r11.zzn     // Catch:{ all -> 0x002c }
            r1.zzaV()     // Catch:{ all -> 0x002c }
            com.google.android.gms.measurement.internal.zzny r1 = r1.zzu()     // Catch:{ all -> 0x002c }
            java.lang.Boolean r1 = r1.zzl()     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x002f
            com.google.android.gms.measurement.internal.zzhe r1 = r11.zzaW()     // Catch:{ all -> 0x002c }
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zzk()     // Catch:{ all -> 0x002c }
            java.lang.String r2 = "Upload data called on the client side before use of service was decided"
            r1.zza(r2)     // Catch:{ all -> 0x002c }
            goto L_0x01aa
        L_0x002c:
            r1 = move-exception
            goto L_0x01b6
        L_0x002f:
            boolean r1 = r1.booleanValue()     // Catch:{ all -> 0x002c }
            if (r1 == 0) goto L_0x0044
            com.google.android.gms.measurement.internal.zzhe r1 = r11.zzaW()     // Catch:{ all -> 0x002c }
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zze()     // Catch:{ all -> 0x002c }
            java.lang.String r2 = "Upload called in the client side when service should be used"
            r1.zza(r2)     // Catch:{ all -> 0x002c }
            goto L_0x01aa
        L_0x0044:
            long r1 = r11.zza     // Catch:{ all -> 0x002c }
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 <= 0) goto L_0x0051
            r11.zzaL()     // Catch:{ all -> 0x002c }
            goto L_0x01aa
        L_0x0051:
            com.google.android.gms.measurement.internal.zzil r1 = r11.zzaX()     // Catch:{ all -> 0x002c }
            r1.zzg()     // Catch:{ all -> 0x002c }
            java.util.List r1 = r11.zzz     // Catch:{ all -> 0x002c }
            if (r1 == 0) goto L_0x006b
            com.google.android.gms.measurement.internal.zzhe r1 = r11.zzaW()     // Catch:{ all -> 0x002c }
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zzj()     // Catch:{ all -> 0x002c }
            java.lang.String r2 = "Uploading requested multiple times"
            r1.zza(r2)     // Catch:{ all -> 0x002c }
            goto L_0x01aa
        L_0x006b:
            com.google.android.gms.measurement.internal.zzhk r1 = r11.zzp()     // Catch:{ all -> 0x002c }
            boolean r1 = r1.zzd()     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0087
            com.google.android.gms.measurement.internal.zzhe r1 = r11.zzaW()     // Catch:{ all -> 0x002c }
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zzj()     // Catch:{ all -> 0x002c }
            java.lang.String r2 = "Network not connected, ignoring upload request"
            r1.zza(r2)     // Catch:{ all -> 0x002c }
            r11.zzaL()     // Catch:{ all -> 0x002c }
            goto L_0x01aa
        L_0x0087:
            com.google.android.gms.common.util.Clock r1 = r11.zzaU()     // Catch:{ all -> 0x002c }
            long r1 = r1.currentTimeMillis()     // Catch:{ all -> 0x002c }
            com.google.android.gms.measurement.internal.zzam r5 = r11.zzi()     // Catch:{ all -> 0x002c }
            com.google.android.gms.measurement.internal.zzgg r6 = com.google.android.gms.measurement.internal.zzgi.zzah     // Catch:{ all -> 0x002c }
            r7 = 0
            int r5 = r5.zzh(r7, r6)     // Catch:{ all -> 0x002c }
            r11.zzi()     // Catch:{ all -> 0x002c }
            long r8 = com.google.android.gms.measurement.internal.zzam.zzF()     // Catch:{ all -> 0x002c }
            long r8 = r1 - r8
            r6 = r0
        L_0x00a4:
            if (r6 >= r5) goto L_0x00af
            boolean r10 = r11.zzaM(r7, r8)     // Catch:{ all -> 0x002c }
            if (r10 == 0) goto L_0x00af
            int r6 = r6 + 1
            goto L_0x00a4
        L_0x00af:
            com.google.android.gms.internal.measurement.zzqr.zzb()     // Catch:{ all -> 0x002c }
            com.google.android.gms.measurement.internal.zzil r5 = r11.zzaX()     // Catch:{ all -> 0x002c }
            r5.zzg()     // Catch:{ all -> 0x002c }
            r11.zzaJ()     // Catch:{ all -> 0x002c }
            com.google.android.gms.measurement.internal.zzoa r5 = r11.zzk     // Catch:{ all -> 0x002c }
            com.google.android.gms.measurement.internal.zzhp r5 = r5.zzd     // Catch:{ all -> 0x002c }
            long r5 = r5.zza()     // Catch:{ all -> 0x002c }
            int r3 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r3 == 0) goto L_0x00df
            com.google.android.gms.measurement.internal.zzhe r3 = r11.zzaW()     // Catch:{ all -> 0x002c }
            com.google.android.gms.measurement.internal.zzhc r3 = r3.zzd()     // Catch:{ all -> 0x002c }
            java.lang.String r4 = "Uploading events. Elapsed time since last upload attempt (ms)"
            long r5 = r1 - r5
            long r5 = java.lang.Math.abs(r5)     // Catch:{ all -> 0x002c }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x002c }
            r3.zzb(r4, r5)     // Catch:{ all -> 0x002c }
        L_0x00df:
            com.google.android.gms.measurement.internal.zzaw r3 = r11.zzj()     // Catch:{ all -> 0x002c }
            java.lang.String r3 = r3.zzA()     // Catch:{ all -> 0x002c }
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x002c }
            r5 = -1
            if (r4 != 0) goto L_0x0135
            long r8 = r11.zzB     // Catch:{ all -> 0x002c }
            int r4 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
            if (r4 != 0) goto L_0x0130
            com.google.android.gms.measurement.internal.zzaw r4 = r11.zzj()     // Catch:{ all -> 0x002c }
            android.database.sqlite.SQLiteDatabase r8 = r4.zzj()     // Catch:{ SQLiteException -> 0x0114 }
            java.lang.String r9 = "select rowid from raw_events order by rowid desc limit 1;"
            android.database.Cursor r7 = r8.rawQuery(r9, r7)     // Catch:{ SQLiteException -> 0x0114 }
            boolean r8 = r7.moveToFirst()     // Catch:{ SQLiteException -> 0x0114 }
            if (r8 != 0) goto L_0x010d
        L_0x0109:
            r7.close()     // Catch:{ all -> 0x002c }
            goto L_0x0127
        L_0x010d:
            long r5 = r7.getLong(r0)     // Catch:{ SQLiteException -> 0x0114 }
            goto L_0x0109
        L_0x0112:
            r1 = move-exception
            goto L_0x012a
        L_0x0114:
            r8 = move-exception
            com.google.android.gms.measurement.internal.zzio r4 = r4.zzu     // Catch:{ all -> 0x0112 }
            com.google.android.gms.measurement.internal.zzhe r4 = r4.zzaW()     // Catch:{ all -> 0x0112 }
            com.google.android.gms.measurement.internal.zzhc r4 = r4.zze()     // Catch:{ all -> 0x0112 }
            java.lang.String r9 = "Error querying raw events"
            r4.zzb(r9, r8)     // Catch:{ all -> 0x0112 }
            if (r7 == 0) goto L_0x0127
            goto L_0x0109
        L_0x0127:
            r11.zzB = r5     // Catch:{ all -> 0x002c }
            goto L_0x0130
        L_0x012a:
            if (r7 == 0) goto L_0x012f
            r7.close()     // Catch:{ all -> 0x002c }
        L_0x012f:
            throw r1     // Catch:{ all -> 0x002c }
        L_0x0130:
            r11.zzau(r3, r1)     // Catch:{ all -> 0x002c }
            goto L_0x01aa
        L_0x0135:
            r11.zzB = r5     // Catch:{ all -> 0x002c }
            com.google.android.gms.measurement.internal.zzaw r3 = r11.zzj()     // Catch:{ all -> 0x002c }
            r11.zzi()     // Catch:{ all -> 0x002c }
            long r4 = com.google.android.gms.measurement.internal.zzam.zzF()     // Catch:{ all -> 0x002c }
            long r1 = r1 - r4
            r3.zzg()     // Catch:{ all -> 0x002c }
            r3.zzav()     // Catch:{ all -> 0x002c }
            android.database.sqlite.SQLiteDatabase r4 = r3.zzj()     // Catch:{ SQLiteException -> 0x0182, all -> 0x017f }
            java.lang.String r5 = "select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;"
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ SQLiteException -> 0x0182, all -> 0x017f }
            java.lang.String[] r1 = new java.lang.String[]{r1}     // Catch:{ SQLiteException -> 0x0182, all -> 0x017f }
            android.database.Cursor r1 = r4.rawQuery(r5, r1)     // Catch:{ SQLiteException -> 0x0182, all -> 0x017f }
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0176 }
            if (r2 != 0) goto L_0x0178
            com.google.android.gms.measurement.internal.zzio r2 = r3.zzu     // Catch:{ SQLiteException -> 0x0176 }
            com.google.android.gms.measurement.internal.zzhe r2 = r2.zzaW()     // Catch:{ SQLiteException -> 0x0176 }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zzj()     // Catch:{ SQLiteException -> 0x0176 }
            java.lang.String r4 = "No expired configs for apps with pending events"
            r2.zza(r4)     // Catch:{ SQLiteException -> 0x0176 }
        L_0x0170:
            r1.close()     // Catch:{ all -> 0x002c }
            goto L_0x0197
        L_0x0174:
            r2 = move-exception
            goto L_0x017d
        L_0x0176:
            r2 = move-exception
            goto L_0x0185
        L_0x0178:
            java.lang.String r7 = r1.getString(r0)     // Catch:{ SQLiteException -> 0x0176 }
            goto L_0x0170
        L_0x017d:
            r7 = r1
            goto L_0x01b0
        L_0x017f:
            r1 = move-exception
            r2 = r1
            goto L_0x01b0
        L_0x0182:
            r1 = move-exception
            r2 = r1
            r1 = r7
        L_0x0185:
            com.google.android.gms.measurement.internal.zzio r3 = r3.zzu     // Catch:{ all -> 0x0174 }
            com.google.android.gms.measurement.internal.zzhe r3 = r3.zzaW()     // Catch:{ all -> 0x0174 }
            com.google.android.gms.measurement.internal.zzhc r3 = r3.zze()     // Catch:{ all -> 0x0174 }
            java.lang.String r4 = "Error selecting expired configs"
            r3.zzb(r4, r2)     // Catch:{ all -> 0x0174 }
            if (r1 == 0) goto L_0x0197
            goto L_0x0170
        L_0x0197:
            boolean r1 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x01aa
            com.google.android.gms.measurement.internal.zzaw r1 = r11.zzj()     // Catch:{ all -> 0x002c }
            com.google.android.gms.measurement.internal.zzh r1 = r1.zzl(r7)     // Catch:{ all -> 0x002c }
            if (r1 == 0) goto L_0x01aa
            r11.zzO(r1)     // Catch:{ all -> 0x002c }
        L_0x01aa:
            r11.zzw = r0
            r11.zzaH()
            return
        L_0x01b0:
            if (r7 == 0) goto L_0x01b5
            r7.close()     // Catch:{ all -> 0x002c }
        L_0x01b5:
            throw r2     // Catch:{ all -> 0x002c }
        L_0x01b6:
            r11.zzw = r0
            r11.zzaH()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzpv.zzat():void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x01f6, code lost:
        if (r11 != null) goto L_0x006b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x03d0  */
    /* JADX WARNING: Removed duplicated region for block: B:249:0x0690  */
    /* JADX WARNING: Removed duplicated region for block: B:255:0x06de  */
    /* JADX WARNING: Removed duplicated region for block: B:278:0x040a A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:289:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:291:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0200  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzau(java.lang.String r29, long r30) {
        /*
            r28 = this;
            r8 = r28
            r9 = r29
            r1 = r30
            com.google.android.gms.measurement.internal.zzam r0 = r28.zzi()
            com.google.android.gms.measurement.internal.zzgg r3 = com.google.android.gms.measurement.internal.zzgi.zzg
            int r0 = r0.zzh(r9, r3)
            com.google.android.gms.measurement.internal.zzam r3 = r28.zzi()
            com.google.android.gms.measurement.internal.zzgg r4 = com.google.android.gms.measurement.internal.zzgi.zzh
            int r3 = r3.zzh(r9, r4)
            r4 = 0
            int r3 = java.lang.Math.max(r4, r3)
            com.google.android.gms.measurement.internal.zzaw r5 = r28.zzj()
            r5.zzg()
            r5.zzav()
            r6 = 1
            if (r0 <= 0) goto L_0x002e
            r7 = r6
            goto L_0x002f
        L_0x002e:
            r7 = r4
        L_0x002f:
            com.google.android.gms.common.internal.Preconditions.checkArgument(r7)
            if (r3 <= 0) goto L_0x0036
            r7 = r6
            goto L_0x0037
        L_0x0036:
            r7 = r4
        L_0x0037:
            com.google.android.gms.common.internal.Preconditions.checkArgument(r7)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r29)
            android.database.sqlite.SQLiteDatabase r11 = r5.zzj()     // Catch:{ SQLiteException -> 0x01d9, all -> 0x01d7 }
            java.lang.String r12 = "queue"
            java.lang.String r13 = "rowid"
            java.lang.String r14 = "data"
            java.lang.String r15 = "retry_count"
            java.lang.String[] r13 = new java.lang.String[]{r13, r14, r15}     // Catch:{ SQLiteException -> 0x01d9, all -> 0x01d7 }
            java.lang.String r14 = "app_id=?"
            java.lang.String[] r15 = new java.lang.String[]{r29}     // Catch:{ SQLiteException -> 0x01d9, all -> 0x01d7 }
            java.lang.String r18 = "rowid"
            java.lang.String r19 = java.lang.String.valueOf(r0)     // Catch:{ SQLiteException -> 0x01d9, all -> 0x01d7 }
            r16 = 0
            r17 = 0
            android.database.Cursor r11 = r11.query(r12, r13, r14, r15, r16, r17, r18, r19)     // Catch:{ SQLiteException -> 0x01d9, all -> 0x01d7 }
            boolean r0 = r11.moveToFirst()     // Catch:{ SQLiteException -> 0x0073 }
            if (r0 != 0) goto L_0x0076
            java.util.List r0 = java.util.Collections.emptyList()     // Catch:{ SQLiteException -> 0x0073 }
        L_0x006b:
            r11.close()
            goto L_0x01fa
        L_0x0070:
            r0 = move-exception
            goto L_0x01d4
        L_0x0073:
            r0 = move-exception
            goto L_0x01df
        L_0x0076:
            java.util.ArrayList r12 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0073 }
            r12.<init>()     // Catch:{ SQLiteException -> 0x0073 }
            r13 = r4
        L_0x007c:
            long r14 = r11.getLong(r4)     // Catch:{ SQLiteException -> 0x0073 }
            byte[] r0 = r11.getBlob(r6)     // Catch:{ IOException -> 0x01ac }
            com.google.android.gms.measurement.internal.zzpv r10 = r5.zzg     // Catch:{ IOException -> 0x01ac }
            com.google.android.gms.measurement.internal.zzqa r10 = r10.zzA()     // Catch:{ IOException -> 0x01ac }
            java.io.ByteArrayInputStream r6 = new java.io.ByteArrayInputStream     // Catch:{ IOException -> 0x0194 }
            r6.<init>(r0)     // Catch:{ IOException -> 0x0194 }
            java.util.zip.GZIPInputStream r0 = new java.util.zip.GZIPInputStream     // Catch:{ IOException -> 0x0194 }
            r0.<init>(r6)     // Catch:{ IOException -> 0x0194 }
            java.io.ByteArrayOutputStream r7 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x0194 }
            r7.<init>()     // Catch:{ IOException -> 0x0194 }
            r4 = 1024(0x400, float:1.435E-42)
            byte[] r4 = new byte[r4]     // Catch:{ IOException -> 0x0194 }
        L_0x009d:
            int r1 = r0.read(r4)     // Catch:{ IOException -> 0x0194 }
            if (r1 > 0) goto L_0x0196
            r0.close()     // Catch:{ IOException -> 0x0194 }
            r6.close()     // Catch:{ IOException -> 0x0194 }
            byte[] r0 = r7.toByteArray()     // Catch:{ IOException -> 0x0194 }
            boolean r1 = r12.isEmpty()     // Catch:{ SQLiteException -> 0x0073 }
            if (r1 != 0) goto L_0x00b9
            int r1 = r0.length     // Catch:{ SQLiteException -> 0x0073 }
            int r1 = r1 + r13
            if (r1 <= r3) goto L_0x00b9
            goto L_0x01cf
        L_0x00b9:
            com.google.android.gms.internal.measurement.zzhw r1 = com.google.android.gms.internal.measurement.zzhx.zzz()     // Catch:{ IOException -> 0x017f }
            com.google.android.gms.internal.measurement.zzng r1 = com.google.android.gms.measurement.internal.zzqa.zzp(r1, r0)     // Catch:{ IOException -> 0x017f }
            com.google.android.gms.internal.measurement.zzhw r1 = (com.google.android.gms.internal.measurement.zzhw) r1     // Catch:{ IOException -> 0x017f }
            boolean r2 = r12.isEmpty()     // Catch:{ SQLiteException -> 0x0073 }
            if (r2 != 0) goto L_0x015d
            r2 = 0
            java.lang.Object r4 = r12.get(r2)     // Catch:{ SQLiteException -> 0x0073 }
            android.util.Pair r4 = (android.util.Pair) r4     // Catch:{ SQLiteException -> 0x0073 }
            java.lang.Object r2 = r4.first     // Catch:{ SQLiteException -> 0x0073 }
            com.google.android.gms.internal.measurement.zzhx r2 = (com.google.android.gms.internal.measurement.zzhx) r2     // Catch:{ SQLiteException -> 0x0073 }
            com.google.android.gms.internal.measurement.zzmd r4 = r1.zzba()     // Catch:{ SQLiteException -> 0x0073 }
            com.google.android.gms.internal.measurement.zzhx r4 = (com.google.android.gms.internal.measurement.zzhx) r4     // Catch:{ SQLiteException -> 0x0073 }
            java.lang.String r6 = r2.zzK()     // Catch:{ SQLiteException -> 0x0073 }
            java.lang.String r7 = r4.zzK()     // Catch:{ SQLiteException -> 0x0073 }
            boolean r6 = r6.equals(r7)     // Catch:{ SQLiteException -> 0x0073 }
            if (r6 == 0) goto L_0x01cf
            java.lang.String r6 = r2.zzJ()     // Catch:{ SQLiteException -> 0x0073 }
            java.lang.String r7 = r4.zzJ()     // Catch:{ SQLiteException -> 0x0073 }
            boolean r6 = r6.equals(r7)     // Catch:{ SQLiteException -> 0x0073 }
            if (r6 == 0) goto L_0x01cf
            boolean r6 = r2.zzbu()     // Catch:{ SQLiteException -> 0x0073 }
            boolean r7 = r4.zzbu()     // Catch:{ SQLiteException -> 0x0073 }
            if (r6 != r7) goto L_0x01cf
            java.lang.String r6 = r2.zzL()     // Catch:{ SQLiteException -> 0x0073 }
            java.lang.String r7 = r4.zzL()     // Catch:{ SQLiteException -> 0x0073 }
            boolean r6 = r6.equals(r7)     // Catch:{ SQLiteException -> 0x0073 }
            if (r6 == 0) goto L_0x01cf
            java.util.List r2 = r2.zzY()     // Catch:{ SQLiteException -> 0x0073 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ SQLiteException -> 0x0073 }
        L_0x0116:
            boolean r6 = r2.hasNext()     // Catch:{ SQLiteException -> 0x0073 }
            r20 = -1
            java.lang.String r7 = "_npa"
            if (r6 == 0) goto L_0x0135
            java.lang.Object r6 = r2.next()     // Catch:{ SQLiteException -> 0x0073 }
            com.google.android.gms.internal.measurement.zzio r6 = (com.google.android.gms.internal.measurement.zzio) r6     // Catch:{ SQLiteException -> 0x0073 }
            java.lang.String r10 = r6.zzg()     // Catch:{ SQLiteException -> 0x0073 }
            boolean r10 = r7.equals(r10)     // Catch:{ SQLiteException -> 0x0073 }
            if (r10 == 0) goto L_0x0116
            long r22 = r6.zzc()     // Catch:{ SQLiteException -> 0x0073 }
            goto L_0x0137
        L_0x0135:
            r22 = r20
        L_0x0137:
            java.util.List r2 = r4.zzY()     // Catch:{ SQLiteException -> 0x0073 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ SQLiteException -> 0x0073 }
        L_0x013f:
            boolean r4 = r2.hasNext()     // Catch:{ SQLiteException -> 0x0073 }
            if (r4 == 0) goto L_0x0159
            java.lang.Object r4 = r2.next()     // Catch:{ SQLiteException -> 0x0073 }
            com.google.android.gms.internal.measurement.zzio r4 = (com.google.android.gms.internal.measurement.zzio) r4     // Catch:{ SQLiteException -> 0x0073 }
            java.lang.String r6 = r4.zzg()     // Catch:{ SQLiteException -> 0x0073 }
            boolean r6 = r7.equals(r6)     // Catch:{ SQLiteException -> 0x0073 }
            if (r6 == 0) goto L_0x013f
            long r20 = r4.zzc()     // Catch:{ SQLiteException -> 0x0073 }
        L_0x0159:
            int r2 = (r22 > r20 ? 1 : (r22 == r20 ? 0 : -1))
            if (r2 != 0) goto L_0x01cf
        L_0x015d:
            r2 = 2
            boolean r4 = r11.isNull(r2)     // Catch:{ SQLiteException -> 0x0073 }
            if (r4 != 0) goto L_0x016b
            int r4 = r11.getInt(r2)     // Catch:{ SQLiteException -> 0x0073 }
            r1.zzat(r4)     // Catch:{ SQLiteException -> 0x0073 }
        L_0x016b:
            int r0 = r0.length     // Catch:{ SQLiteException -> 0x0073 }
            int r13 = r13 + r0
            com.google.android.gms.internal.measurement.zzmd r0 = r1.zzba()     // Catch:{ SQLiteException -> 0x0073 }
            com.google.android.gms.internal.measurement.zzhx r0 = (com.google.android.gms.internal.measurement.zzhx) r0     // Catch:{ SQLiteException -> 0x0073 }
            java.lang.Long r1 = java.lang.Long.valueOf(r14)     // Catch:{ SQLiteException -> 0x0073 }
            android.util.Pair r0 = android.util.Pair.create(r0, r1)     // Catch:{ SQLiteException -> 0x0073 }
            r12.add(r0)     // Catch:{ SQLiteException -> 0x0073 }
            goto L_0x01c0
        L_0x017f:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzio r1 = r5.zzu     // Catch:{ SQLiteException -> 0x0073 }
            com.google.android.gms.measurement.internal.zzhe r1 = r1.zzaW()     // Catch:{ SQLiteException -> 0x0073 }
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zze()     // Catch:{ SQLiteException -> 0x0073 }
            java.lang.String r2 = "Failed to merge queued bundle. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzhe.zzn(r29)     // Catch:{ SQLiteException -> 0x0073 }
            r1.zzc(r2, r4, r0)     // Catch:{ SQLiteException -> 0x0073 }
            goto L_0x01c0
        L_0x0194:
            r0 = move-exception
            goto L_0x019c
        L_0x0196:
            r2 = 0
            r7.write(r4, r2, r1)     // Catch:{ IOException -> 0x0194 }
            goto L_0x009d
        L_0x019c:
            com.google.android.gms.measurement.internal.zzio r1 = r10.zzu     // Catch:{ IOException -> 0x01ac }
            com.google.android.gms.measurement.internal.zzhe r1 = r1.zzaW()     // Catch:{ IOException -> 0x01ac }
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zze()     // Catch:{ IOException -> 0x01ac }
            java.lang.String r2 = "Failed to ungzip content"
            r1.zzb(r2, r0)     // Catch:{ IOException -> 0x01ac }
            throw r0     // Catch:{ IOException -> 0x01ac }
        L_0x01ac:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzio r1 = r5.zzu     // Catch:{ SQLiteException -> 0x0073 }
            com.google.android.gms.measurement.internal.zzhe r1 = r1.zzaW()     // Catch:{ SQLiteException -> 0x0073 }
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zze()     // Catch:{ SQLiteException -> 0x0073 }
            java.lang.String r2 = "Failed to unzip queued bundle. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzhe.zzn(r29)     // Catch:{ SQLiteException -> 0x0073 }
            r1.zzc(r2, r4, r0)     // Catch:{ SQLiteException -> 0x0073 }
        L_0x01c0:
            boolean r0 = r11.moveToNext()     // Catch:{ SQLiteException -> 0x0073 }
            if (r0 == 0) goto L_0x01cf
            if (r13 <= r3) goto L_0x01c9
            goto L_0x01cf
        L_0x01c9:
            r1 = r30
            r4 = 0
            r6 = 1
            goto L_0x007c
        L_0x01cf:
            r11.close()
            r0 = r12
            goto L_0x01fa
        L_0x01d4:
            r10 = r11
            goto L_0x06dc
        L_0x01d7:
            r0 = move-exception
            goto L_0x01db
        L_0x01d9:
            r0 = move-exception
            goto L_0x01de
        L_0x01db:
            r10 = 0
            goto L_0x06dc
        L_0x01de:
            r11 = 0
        L_0x01df:
            com.google.android.gms.measurement.internal.zzio r1 = r5.zzu     // Catch:{ all -> 0x0070 }
            com.google.android.gms.measurement.internal.zzhe r1 = r1.zzaW()     // Catch:{ all -> 0x0070 }
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zze()     // Catch:{ all -> 0x0070 }
            java.lang.String r2 = "Error querying bundles. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzhe.zzn(r29)     // Catch:{ all -> 0x0070 }
            r1.zzc(r2, r3, r0)     // Catch:{ all -> 0x0070 }
            java.util.List r0 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0070 }
            if (r11 == 0) goto L_0x01fa
            goto L_0x006b
        L_0x01fa:
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x06db
            com.google.android.gms.measurement.internal.zzjx r1 = r28.zzu(r29)
            com.google.android.gms.measurement.internal.zzjw r2 = com.google.android.gms.measurement.internal.zzjw.AD_STORAGE
            boolean r1 = r1.zzr(r2)
            if (r1 == 0) goto L_0x0261
            java.util.Iterator r1 = r0.iterator()
        L_0x0210:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x022f
            java.lang.Object r2 = r1.next()
            android.util.Pair r2 = (android.util.Pair) r2
            java.lang.Object r2 = r2.first
            com.google.android.gms.internal.measurement.zzhx r2 = (com.google.android.gms.internal.measurement.zzhx) r2
            java.lang.String r3 = r2.zzT()
            boolean r3 = r3.isEmpty()
            if (r3 != 0) goto L_0x0210
            java.lang.String r1 = r2.zzT()
            goto L_0x0230
        L_0x022f:
            r1 = 0
        L_0x0230:
            if (r1 == 0) goto L_0x0261
            r2 = 0
        L_0x0233:
            int r3 = r0.size()
            if (r2 >= r3) goto L_0x0261
            java.lang.Object r3 = r0.get(r2)
            android.util.Pair r3 = (android.util.Pair) r3
            java.lang.Object r3 = r3.first
            com.google.android.gms.internal.measurement.zzhx r3 = (com.google.android.gms.internal.measurement.zzhx) r3
            java.lang.String r4 = r3.zzT()
            boolean r4 = r4.isEmpty()
            if (r4 == 0) goto L_0x024e
            goto L_0x025e
        L_0x024e:
            java.lang.String r3 = r3.zzT()
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L_0x025e
            r3 = 0
            java.util.List r0 = r0.subList(r3, r2)
            goto L_0x0261
        L_0x025e:
            int r2 = r2 + 1
            goto L_0x0233
        L_0x0261:
            com.google.android.gms.internal.measurement.zzht r1 = com.google.android.gms.internal.measurement.zzhv.zzb()
            int r2 = r0.size()
            java.util.ArrayList r3 = new java.util.ArrayList
            int r4 = r0.size()
            r3.<init>(r4)
            com.google.android.gms.measurement.internal.zzam r4 = r28.zzi()
            boolean r4 = r4.zzy(r9)
            if (r4 == 0) goto L_0x028a
            com.google.android.gms.measurement.internal.zzjx r4 = r28.zzu(r29)
            com.google.android.gms.measurement.internal.zzjw r5 = com.google.android.gms.measurement.internal.zzjw.AD_STORAGE
            boolean r4 = r4.zzr(r5)
            if (r4 == 0) goto L_0x028a
            r4 = 1
            goto L_0x028b
        L_0x028a:
            r4 = 0
        L_0x028b:
            com.google.android.gms.measurement.internal.zzjx r5 = r28.zzu(r29)
            com.google.android.gms.measurement.internal.zzjw r6 = com.google.android.gms.measurement.internal.zzjw.AD_STORAGE
            boolean r5 = r5.zzr(r6)
            com.google.android.gms.measurement.internal.zzjx r6 = r28.zzu(r29)
            com.google.android.gms.measurement.internal.zzjw r7 = com.google.android.gms.measurement.internal.zzjw.ANALYTICS_STORAGE
            boolean r6 = r6.zzr(r7)
            com.google.android.gms.internal.measurement.zzrd.zzb()
            com.google.android.gms.measurement.internal.zzam r7 = r28.zzi()
            com.google.android.gms.measurement.internal.zzgg r10 = com.google.android.gms.measurement.internal.zzgi.zzaL
            boolean r7 = r7.zzx(r9, r10)
            com.google.android.gms.measurement.internal.zzpi r10 = r8.zzl
            com.google.android.gms.measurement.internal.zzph r11 = r10.zza(r9)
            r12 = 0
        L_0x02b3:
            if (r12 >= r2) goto L_0x041a
            java.lang.Object r13 = r0.get(r12)
            android.util.Pair r13 = (android.util.Pair) r13
            java.lang.Object r13 = r13.first
            com.google.android.gms.internal.measurement.zzhx r13 = (com.google.android.gms.internal.measurement.zzhx) r13
            com.google.android.gms.internal.measurement.zzlz r13 = r13.zzch()
            com.google.android.gms.internal.measurement.zzhw r13 = (com.google.android.gms.internal.measurement.zzhw) r13
            java.lang.Object r14 = r0.get(r12)
            android.util.Pair r14 = (android.util.Pair) r14
            java.lang.Object r14 = r14.second
            java.lang.Long r14 = (java.lang.Long) r14
            r3.add(r14)
            com.google.android.gms.measurement.internal.zzam r14 = r28.zzi()
            r14.zzj()
            r14 = 119002(0x1d0da, double:5.8795E-319)
            r13.zzaB(r14)
            r14 = r30
            r13.zzaA(r14)
            r20 = r0
            com.google.android.gms.measurement.internal.zzio r0 = r8.zzn
            r0.zzaV()
            r0 = r2
            r2 = 0
            r13.zzau(r2)
            if (r4 != 0) goto L_0x02f5
            r13.zzt()
        L_0x02f5:
            if (r5 != 0) goto L_0x02fd
            r13.zzz()
            r13.zzw()
        L_0x02fd:
            if (r6 != 0) goto L_0x0302
            r13.zzq()
        L_0x0302:
            r8.zzN(r9, r13)
            if (r7 != 0) goto L_0x030a
            r13.zzA()
        L_0x030a:
            if (r6 != 0) goto L_0x030f
            r13.zzr()
        L_0x030f:
            java.lang.String r2 = r13.zzaL()
            boolean r21 = android.text.TextUtils.isEmpty(r2)
            if (r21 != 0) goto L_0x032e
            r21 = r0
            java.lang.String r0 = "00000000-0000-0000-0000-000000000000"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0324
            goto L_0x0330
        L_0x0324:
            r22 = r4
            r23 = r5
            r27 = r6
            r26 = r7
            goto L_0x03c9
        L_0x032e:
            r21 = r0
        L_0x0330:
            java.util.ArrayList r0 = new java.util.ArrayList
            java.util.List r2 = r13.zzaM()
            r0.<init>(r2)
            java.util.Iterator r2 = r0.iterator()
            r22 = r4
            r23 = r5
            r4 = 0
            r5 = 0
            r24 = 0
            r25 = 0
        L_0x0347:
            boolean r26 = r2.hasNext()
            if (r26 == 0) goto L_0x03b3
            java.lang.Object r26 = r2.next()
            r27 = r6
            r6 = r26
            com.google.android.gms.internal.measurement.zzhm r6 = (com.google.android.gms.internal.measurement.zzhm) r6
            r26 = r7
            java.lang.String r7 = r6.zzh()
            java.lang.String r14 = "_fx"
            boolean r7 = r14.equals(r7)
            if (r7 == 0) goto L_0x0373
            r2.remove()
            r14 = r30
            r7 = r26
            r6 = r27
            r24 = 1
        L_0x0370:
            r25 = 1
            goto L_0x0347
        L_0x0373:
            java.lang.String r7 = r6.zzh()
            java.lang.String r14 = "_f"
            boolean r7 = r14.equals(r7)
            if (r7 == 0) goto L_0x03ac
            r28.zzA()
            java.lang.String r7 = "_pfo"
            com.google.android.gms.internal.measurement.zzhq r7 = com.google.android.gms.measurement.internal.zzqa.zzG(r6, r7)
            if (r7 == 0) goto L_0x0392
            long r14 = r7.zzd()
            java.lang.Long r4 = java.lang.Long.valueOf(r14)
        L_0x0392:
            r28.zzA()
            java.lang.String r7 = "_uwa"
            com.google.android.gms.internal.measurement.zzhq r6 = com.google.android.gms.measurement.internal.zzqa.zzG(r6, r7)
            if (r6 == 0) goto L_0x03a5
            long r5 = r6.zzd()
            java.lang.Long r5 = java.lang.Long.valueOf(r5)
        L_0x03a5:
            r14 = r30
            r7 = r26
            r6 = r27
            goto L_0x0370
        L_0x03ac:
            r14 = r30
            r7 = r26
            r6 = r27
            goto L_0x0347
        L_0x03b3:
            r27 = r6
            r26 = r7
            if (r24 == 0) goto L_0x03bf
            r13.zzu()
            r13.zzj(r0)
        L_0x03bf:
            if (r25 == 0) goto L_0x03c9
            java.lang.String r0 = r13.zzaF()
            r2 = 1
            r8.zzar(r0, r2, r4, r5)
        L_0x03c9:
            int r0 = r13.zzc()
            if (r0 != 0) goto L_0x03d0
            goto L_0x040a
        L_0x03d0:
            com.google.android.gms.measurement.internal.zzam r0 = r28.zzi()
            com.google.android.gms.measurement.internal.zzgg r2 = com.google.android.gms.measurement.internal.zzgi.zzaB
            boolean r0 = r0.zzx(r9, r2)
            if (r0 == 0) goto L_0x03f1
            com.google.android.gms.internal.measurement.zzmd r0 = r13.zzba()
            com.google.android.gms.internal.measurement.zzhx r0 = (com.google.android.gms.internal.measurement.zzhx) r0
            byte[] r0 = r0.zzcd()
            com.google.android.gms.measurement.internal.zzqa r2 = r28.zzA()
            long r4 = r2.zzf(r0)
            r13.zzQ(r4)
        L_0x03f1:
            com.google.android.gms.measurement.internal.zzam r0 = r28.zzi()
            com.google.android.gms.measurement.internal.zzgg r2 = com.google.android.gms.measurement.internal.zzgi.zzaP
            r4 = 0
            boolean r0 = r0.zzx(r4, r2)
            if (r0 == 0) goto L_0x0407
            com.google.android.gms.internal.measurement.zzim r0 = r11.zzb()
            if (r0 == 0) goto L_0x0407
            r13.zzaw(r0)
        L_0x0407:
            r1.zzc(r13)
        L_0x040a:
            int r12 = r12 + 1
            r0 = r20
            r2 = r21
            r4 = r22
            r5 = r23
            r7 = r26
            r6 = r27
            goto L_0x02b3
        L_0x041a:
            int r0 = r1.zza()
            if (r0 != 0) goto L_0x0434
            r8.zzal(r3)
            r5 = 0
            java.util.List r7 = java.util.Collections.emptyList()
            r2 = 0
            r3 = 204(0xcc, float:2.86E-43)
            r4 = 0
            r1 = r28
            r6 = r29
            r1.zzY(r2, r3, r4, r5, r6, r7)
            return
        L_0x0434:
            com.google.android.gms.internal.measurement.zzmd r0 = r1.zzba()
            com.google.android.gms.internal.measurement.zzhv r0 = (com.google.android.gms.internal.measurement.zzhv) r0
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            com.google.android.gms.measurement.internal.zzam r2 = r28.zzi()
            com.google.android.gms.measurement.internal.zzgg r4 = com.google.android.gms.measurement.internal.zzgi.zzaP
            r5 = 0
            boolean r2 = r2.zzx(r5, r4)
            if (r2 == 0) goto L_0x0456
            com.google.android.gms.measurement.internal.zzmf r2 = r11.zza()
            com.google.android.gms.measurement.internal.zzmf r4 = com.google.android.gms.measurement.internal.zzmf.SGTM_CLIENT
            if (r2 != r4) goto L_0x0456
            r2 = 1
            goto L_0x0457
        L_0x0456:
            r2 = 0
        L_0x0457:
            com.google.android.gms.measurement.internal.zzmf r4 = r11.zza()
            com.google.android.gms.measurement.internal.zzmf r5 = com.google.android.gms.measurement.internal.zzmf.SGTM
            if (r4 == r5) goto L_0x0467
            if (r2 == 0) goto L_0x0463
            r2 = 1
            goto L_0x0467
        L_0x0463:
            r5 = r30
            goto L_0x0679
        L_0x0467:
            com.google.android.gms.internal.measurement.zzmd r0 = r1.zzba()
            com.google.android.gms.internal.measurement.zzhv r0 = (com.google.android.gms.internal.measurement.zzhv) r0
            java.util.List r0 = r0.zzh()
            java.util.Iterator r0 = r0.iterator()
        L_0x0475:
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L_0x0490
            java.lang.Object r4 = r0.next()
            com.google.android.gms.internal.measurement.zzhx r4 = (com.google.android.gms.internal.measurement.zzhx) r4
            boolean r4 = r4.zzbI()
            if (r4 == 0) goto L_0x0475
            java.util.UUID r0 = java.util.UUID.randomUUID()
            java.lang.String r0 = r0.toString()
            goto L_0x0491
        L_0x0490:
            r0 = 0
        L_0x0491:
            com.google.android.gms.internal.measurement.zzmd r4 = r1.zzba()
            com.google.android.gms.internal.measurement.zzhv r4 = (com.google.android.gms.internal.measurement.zzhv) r4
            com.google.android.gms.measurement.internal.zzil r5 = r28.zzaX()
            r5.zzg()
            r28.zzM()
            com.google.android.gms.internal.measurement.zzht r5 = com.google.android.gms.internal.measurement.zzhv.zzc(r4)
            boolean r6 = android.text.TextUtils.isEmpty(r0)
            if (r6 != 0) goto L_0x04ae
            r5.zzf(r0)
        L_0x04ae:
            com.google.android.gms.measurement.internal.zzif r6 = r28.zzr()
            java.lang.String r6 = r6.zzm(r9)
            boolean r12 = android.text.TextUtils.isEmpty(r6)
            if (r12 != 0) goto L_0x04bf
            r5.zzg(r6)
        L_0x04bf:
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.List r4 = r4.zzh()
            java.util.Iterator r4 = r4.iterator()
        L_0x04cc:
            boolean r12 = r4.hasNext()
            if (r12 == 0) goto L_0x04e9
            java.lang.Object r12 = r4.next()
            com.google.android.gms.internal.measurement.zzhx r12 = (com.google.android.gms.internal.measurement.zzhx) r12
            com.google.android.gms.internal.measurement.zzhw r12 = com.google.android.gms.internal.measurement.zzhx.zzA(r12)
            r12.zzt()
            com.google.android.gms.internal.measurement.zzmd r12 = r12.zzba()
            com.google.android.gms.internal.measurement.zzhx r12 = (com.google.android.gms.internal.measurement.zzhx) r12
            r6.add(r12)
            goto L_0x04cc
        L_0x04e9:
            r5.zzd()
            r5.zzb(r6)
            com.google.android.gms.measurement.internal.zzam r4 = r28.zzi()
            com.google.android.gms.measurement.internal.zzgg r6 = com.google.android.gms.measurement.internal.zzgi.zzaN
            r12 = 0
            boolean r4 = r4.zzx(r12, r6)
            if (r4 == 0) goto L_0x0517
            com.google.android.gms.measurement.internal.zzhe r4 = r28.zzaW()
            com.google.android.gms.measurement.internal.zzhc r4 = r4.zzj()
            boolean r12 = android.text.TextUtils.isEmpty(r0)
            if (r12 == 0) goto L_0x050d
            java.lang.String r12 = "null"
            goto L_0x0511
        L_0x050d:
            java.lang.String r12 = r5.zzi()
        L_0x0511:
            java.lang.String r13 = "[sgtm] Processed MeasurementBatch for sGTM with sgtmJoinId: "
            r4.zzb(r13, r12)
            goto L_0x0524
        L_0x0517:
            com.google.android.gms.measurement.internal.zzhe r4 = r28.zzaW()
            com.google.android.gms.measurement.internal.zzhc r4 = r4.zzj()
            java.lang.String r12 = "[sgtm] Processed MeasurementBatch for sGTM."
            r4.zza(r12)
        L_0x0524:
            com.google.android.gms.internal.measurement.zzmd r4 = r5.zzba()
            com.google.android.gms.internal.measurement.zzhv r4 = (com.google.android.gms.internal.measurement.zzhv) r4
            boolean r5 = android.text.TextUtils.isEmpty(r0)
            if (r5 != 0) goto L_0x0605
            com.google.android.gms.measurement.internal.zzam r5 = r28.zzi()
            r12 = 0
            boolean r5 = r5.zzx(r12, r6)
            if (r5 == 0) goto L_0x0605
            com.google.android.gms.internal.measurement.zzmd r1 = r1.zzba()
            com.google.android.gms.internal.measurement.zzhv r1 = (com.google.android.gms.internal.measurement.zzhv) r1
            com.google.android.gms.measurement.internal.zzil r5 = r28.zzaX()
            r5.zzg()
            r28.zzM()
            com.google.android.gms.internal.measurement.zzht r5 = com.google.android.gms.internal.measurement.zzhv.zzb()
            com.google.android.gms.measurement.internal.zzhe r6 = r28.zzaW()
            com.google.android.gms.measurement.internal.zzhc r6 = r6.zzj()
            java.lang.String r12 = "[sgtm] Processing Google Signal, sgtmJoinId:"
            r6.zzb(r12, r0)
            r5.zzf(r0)
            java.util.List r0 = r1.zzh()
            java.util.Iterator r0 = r0.iterator()
        L_0x0567:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0589
            java.lang.Object r1 = r0.next()
            com.google.android.gms.internal.measurement.zzhx r1 = (com.google.android.gms.internal.measurement.zzhx) r1
            com.google.android.gms.internal.measurement.zzhw r6 = com.google.android.gms.internal.measurement.zzhx.zzz()
            java.lang.String r12 = r1.zzN()
            r6.zzY(r12)
            int r1 = r1.zzd()
            r6.zzV(r1)
            r5.zzc(r6)
            goto L_0x0567
        L_0x0589:
            com.google.android.gms.internal.measurement.zzmd r0 = r5.zzba()
            com.google.android.gms.internal.measurement.zzhv r0 = (com.google.android.gms.internal.measurement.zzhv) r0
            com.google.android.gms.measurement.internal.zzpv r1 = r10.zzg
            com.google.android.gms.measurement.internal.zzif r1 = r1.zzr()
            java.lang.String r1 = r1.zzm(r9)
            boolean r5 = android.text.TextUtils.isEmpty(r1)
            if (r5 != 0) goto L_0x05e5
            com.google.android.gms.measurement.internal.zzgg r5 = com.google.android.gms.measurement.internal.zzgi.zzr
            r6 = 0
            java.lang.Object r5 = r5.zza(r6)
            java.lang.String r5 = (java.lang.String) r5
            android.net.Uri r5 = android.net.Uri.parse(r5)
            android.net.Uri$Builder r6 = r5.buildUpon()
            java.lang.String r5 = r5.getAuthority()
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r1)
            java.lang.String r1 = "."
            r10.append(r1)
            r10.append(r5)
            java.lang.String r1 = r10.toString()
            r6.authority(r1)
            com.google.android.gms.measurement.internal.zzph r1 = new com.google.android.gms.measurement.internal.zzph
            android.net.Uri r5 = r6.build()
            java.lang.String r5 = r5.toString()
            if (r2 == 0) goto L_0x05da
            com.google.android.gms.measurement.internal.zzmf r6 = com.google.android.gms.measurement.internal.zzmf.GOOGLE_SIGNAL_PENDING
            goto L_0x05dc
        L_0x05da:
            com.google.android.gms.measurement.internal.zzmf r6 = com.google.android.gms.measurement.internal.zzmf.GOOGLE_SIGNAL
        L_0x05dc:
            java.util.Map r10 = java.util.Collections.emptyMap()
            r12 = 0
            r1.<init>(r5, r10, r6, r12)
            goto L_0x05fe
        L_0x05e5:
            r12 = 0
            com.google.android.gms.measurement.internal.zzph r1 = new com.google.android.gms.measurement.internal.zzph
            com.google.android.gms.measurement.internal.zzgg r5 = com.google.android.gms.measurement.internal.zzgi.zzr
            java.lang.Object r5 = r5.zza(r12)
            java.lang.String r5 = (java.lang.String) r5
            if (r2 == 0) goto L_0x05f5
            com.google.android.gms.measurement.internal.zzmf r6 = com.google.android.gms.measurement.internal.zzmf.GOOGLE_SIGNAL_PENDING
            goto L_0x05f7
        L_0x05f5:
            com.google.android.gms.measurement.internal.zzmf r6 = com.google.android.gms.measurement.internal.zzmf.GOOGLE_SIGNAL
        L_0x05f7:
            java.util.Map r10 = java.util.Collections.emptyMap()
            r1.<init>(r5, r10, r6, r12)
        L_0x05fe:
            android.util.Pair r0 = android.util.Pair.create(r0, r1)
            r7.add(r0)
        L_0x0605:
            if (r2 == 0) goto L_0x0676
            com.google.android.gms.internal.measurement.zzlz r0 = r4.zzch()
            com.google.android.gms.internal.measurement.zzht r0 = (com.google.android.gms.internal.measurement.zzht) r0
            r1 = 0
        L_0x060e:
            int r2 = r4.zza()
            if (r1 >= r2) goto L_0x062c
            com.google.android.gms.internal.measurement.zzhx r2 = r4.zze(r1)
            com.google.android.gms.internal.measurement.zzlz r2 = r2.zzch()
            com.google.android.gms.internal.measurement.zzhw r2 = (com.google.android.gms.internal.measurement.zzhw) r2
            r2.zzC()
            r5 = r30
            r2.zzO(r5)
            r0.zze(r1, r2)
            int r1 = r1 + 1
            goto L_0x060e
        L_0x062c:
            com.google.android.gms.internal.measurement.zzmd r0 = r0.zzba()
            com.google.android.gms.internal.measurement.zzhv r0 = (com.google.android.gms.internal.measurement.zzhv) r0
            android.util.Pair r0 = android.util.Pair.create(r0, r11)
            r7.add(r0)
            r8.zzal(r3)
            r4 = 0
            r5 = 0
            r2 = 0
            r3 = 204(0xcc, float:2.86E-43)
            r1 = r28
            r6 = r29
            r1.zzY(r2, r3, r4, r5, r6, r7)
            java.lang.String r0 = r11.zzc()
            boolean r0 = r8.zzay(r9, r0)
            if (r0 == 0) goto L_0x06db
            com.google.android.gms.measurement.internal.zzhe r0 = r28.zzaW()
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzj()
            java.lang.String r1 = "[sgtm] Sending sgtm batches available notification to app"
            r0.zzb(r1, r9)
            android.content.Intent r0 = new android.content.Intent
            r0.<init>()
            java.lang.String r1 = "com.google.android.gms.measurement.BATCHES_AVAILABLE"
            r0.setAction(r1)
            r0.setPackage(r9)
            com.google.android.gms.measurement.internal.zzio r1 = r8.zzn
            android.content.Context r1 = r1.zzaT()
            zzaK(r1, r0)
            return
        L_0x0676:
            r5 = r30
            r0 = r4
        L_0x0679:
            com.google.android.gms.measurement.internal.zzam r1 = r28.zzi()
            com.google.android.gms.measurement.internal.zzgg r2 = com.google.android.gms.measurement.internal.zzgi.zzaO
            r4 = 0
            boolean r1 = r1.zzx(r4, r2)
            if (r1 == 0) goto L_0x0690
            com.google.android.gms.measurement.internal.zzhk r1 = r28.zzp()
            boolean r1 = r1.zzd()
            if (r1 == 0) goto L_0x06db
        L_0x0690:
            com.google.android.gms.measurement.internal.zzhe r1 = r28.zzaW()
            java.lang.String r1 = r1.zzr()
            r2 = 2
            boolean r1 = android.util.Log.isLoggable(r1, r2)
            if (r1 == 0) goto L_0x06a8
            com.google.android.gms.measurement.internal.zzqa r1 = r28.zzA()
            java.lang.String r10 = r1.zzq(r0)
            goto L_0x06a9
        L_0x06a8:
            r10 = r4
        L_0x06a9:
            r28.zzA()
            byte[] r1 = r0.zzcd()
            r8.zzal(r3)
            com.google.android.gms.measurement.internal.zzoa r2 = r8.zzk
            com.google.android.gms.measurement.internal.zzhp r2 = r2.zze
            r2.zzb(r5)
            com.google.android.gms.measurement.internal.zzhe r2 = r28.zzaW()
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zzj()
            int r1 = r1.length
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r3 = "Uploading data. app, uncompressed size, data"
            r2.zzd(r3, r9, r1, r10)
            r1 = 1
            r8.zzv = r1
            com.google.android.gms.measurement.internal.zzhk r1 = r28.zzp()
            com.google.android.gms.measurement.internal.zzpl r2 = new com.google.android.gms.measurement.internal.zzpl
            r2.<init>(r8, r9, r7)
            r1.zzc(r9, r11, r0, r2)
        L_0x06db:
            return
        L_0x06dc:
            if (r10 == 0) goto L_0x06e1
            r10.close()
        L_0x06e1:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzpv.zzau(java.lang.String, long):void");
    }

    /* access modifiers changed from: package-private */
    public final void zzav(String str) {
        zzhv zzg2;
        zzaX().zzg();
        zzM();
        this.zzw = true;
        try {
            zzio zzio = this.zzn;
            zzio.zzaV();
            Boolean zzl2 = zzio.zzu().zzl();
            if (zzl2 == null) {
                zzaW().zzk().zza("Upload data called on the client side before use of service was decided");
            } else if (zzl2.booleanValue()) {
                zzaW().zze().zza("Upload called in the client side when service should be used");
            } else if (this.zza > 0) {
                zzaL();
            } else if (!zzp().zzd()) {
                zzaW().zzj().zza("Network not connected, ignoring upload request");
                zzaL();
            } else if (!zzj().zzY(str)) {
                zzaW().zzj().zzb("[sgtm] Upload queue has no batches for appId", str);
            } else {
                zzpz zzw2 = zzj().zzw(str);
                if (!(zzw2 == null || (zzg2 = zzw2.zzg()) == null)) {
                    zzaW().zzj().zzd("[sgtm] Uploading data from upload queue. appId, type, url", str, zzw2.zzd(), zzw2.zzh());
                    byte[] zzcd = zzg2.zzcd();
                    if (Log.isLoggable(zzaW().zzr(), 2)) {
                        zzaW().zzj().zzd("[sgtm] Uploading data from upload queue. appId, uncompressed size, data", str, Integer.valueOf(zzcd.length), zzA().zzq(zzg2));
                    }
                    this.zzv = true;
                    zzp().zzc(str, zzw2.zzf(), zzg2, new zzpm(this, str, zzw2));
                }
            }
        } finally {
            this.zzw = false;
            zzaH();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzaw(String str, zzhp zzhp, Bundle bundle, String str2) {
        int zzd2;
        List listOf = CollectionUtils.listOf((T[]) new String[]{"_o", "_sn", "_sc", "_si"});
        if (zzqf.zzap(zzhp.zzl()) || zzqf.zzap(str)) {
            zzd2 = zzi().zzd(str2, true);
        } else {
            zzd2 = zzi().zzc(str2, true);
        }
        long j = (long) zzd2;
        long codePointCount = (long) zzhp.zzm().codePointCount(0, zzhp.zzm().length());
        zzqf zzB2 = zzB();
        String zzl2 = zzhp.zzl();
        zzi();
        String zzG2 = zzB2.zzG(zzl2, 40, true);
        if (codePointCount > j && !listOf.contains(zzhp.zzl())) {
            if ("_ev".equals(zzhp.zzl())) {
                bundle.putString("_ev", zzB().zzG(zzhp.zzm(), zzi().zzd(str2, true), true));
                return;
            }
            zzaW().zzl().zzc("Param value is too long; discarded. Name, value length", zzG2, Long.valueOf(codePointCount));
            if (bundle.getLong("_err") == 0) {
                bundle.putLong("_err", 4);
                if (bundle.getString("_ev") == null) {
                    bundle.putString("_ev", zzG2);
                    bundle.putLong("_el", codePointCount);
                }
            }
            bundle.remove(zzhp.zzl());
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: IfRegionVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Don't wrap MOVE or CONST insns: 0x04b4: MOVE  (r4v21 java.lang.String) = (r21v0 java.lang.String)
        	at jadx.core.dex.instructions.args.InsnArg.wrapArg(InsnArg.java:164)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.assignInline(CodeShrinkVisitor.java:133)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.checkInline(CodeShrinkVisitor.java:118)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkBlock(CodeShrinkVisitor.java:65)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkMethod(CodeShrinkVisitor.java:43)
        	at jadx.core.dex.visitors.regions.TernaryMod.makeTernaryInsn(TernaryMod.java:122)
        	at jadx.core.dex.visitors.regions.TernaryMod.visitRegion(TernaryMod.java:34)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:73)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:27)
        	at jadx.core.dex.visitors.regions.IfRegionVisitor.visit(IfRegionVisitor.java:31)
        */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:148:0x04cc */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x0376 A[Catch:{ SQLiteException -> 0x0295, all -> 0x0178 }] */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x039d A[Catch:{ SQLiteException -> 0x0295, all -> 0x0178 }] */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x0523 A[Catch:{ SQLiteException -> 0x0295, all -> 0x0178 }] */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x0564 A[Catch:{ SQLiteException -> 0x0295, all -> 0x0178 }] */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x05d3 A[Catch:{ SQLiteException -> 0x0295, all -> 0x0178 }] */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x0617 A[Catch:{ SQLiteException -> 0x0295, all -> 0x0178 }] */
    /* JADX WARNING: Removed duplicated region for block: B:181:0x0622 A[Catch:{ SQLiteException -> 0x0295, all -> 0x0178 }] */
    /* JADX WARNING: Removed duplicated region for block: B:184:0x062d A[Catch:{ SQLiteException -> 0x0295, all -> 0x0178 }] */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x0638 A[Catch:{ SQLiteException -> 0x0295, all -> 0x0178 }] */
    /* JADX WARNING: Removed duplicated region for block: B:190:0x0644 A[Catch:{ SQLiteException -> 0x0295, all -> 0x0178 }] */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x0655 A[Catch:{ SQLiteException -> 0x0295, all -> 0x0178 }] */
    /* JADX WARNING: Removed duplicated region for block: B:196:0x067f A[Catch:{ SQLiteException -> 0x0295, all -> 0x0178 }] */
    /* JADX WARNING: Removed duplicated region for block: B:203:0x06a3 A[Catch:{ SQLiteException -> 0x0295, all -> 0x0178 }] */
    /* JADX WARNING: Removed duplicated region for block: B:244:0x073c A[Catch:{ SQLiteException -> 0x0295, all -> 0x0178 }] */
    /* JADX WARNING: Removed duplicated region for block: B:247:0x0746 A[Catch:{ SQLiteException -> 0x0295, all -> 0x0178 }] */
    /* JADX WARNING: Removed duplicated region for block: B:250:0x076f A[Catch:{ SQLiteException -> 0x0295, all -> 0x0178 }] */
    /* JADX WARNING: Removed duplicated region for block: B:251:0x0774 A[Catch:{ SQLiteException -> 0x0295, all -> 0x0178 }] */
    /* JADX WARNING: Removed duplicated region for block: B:259:0x07a3 A[Catch:{ SQLiteException -> 0x0295, all -> 0x0178 }] */
    /* JADX WARNING: Removed duplicated region for block: B:273:0x0800 A[Catch:{ SQLiteException -> 0x0295, all -> 0x0178 }] */
    /* JADX WARNING: Removed duplicated region for block: B:275:0x0804 A[Catch:{ SQLiteException -> 0x0295, all -> 0x0178 }] */
    /* JADX WARNING: Removed duplicated region for block: B:278:0x0814 A[Catch:{ SQLiteException -> 0x0295, all -> 0x0178 }] */
    /* JADX WARNING: Removed duplicated region for block: B:285:0x084b A[Catch:{ SQLiteException -> 0x0295, all -> 0x0178 }] */
    /* JADX WARNING: Removed duplicated region for block: B:307:0x0905 A[Catch:{ SQLiteException -> 0x0295, all -> 0x0178 }] */
    /* JADX WARNING: Removed duplicated region for block: B:312:0x091c A[Catch:{ SQLiteException -> 0x0295, all -> 0x0178 }] */
    /* JADX WARNING: Removed duplicated region for block: B:316:0x097b A[Catch:{ SQLiteException -> 0x0295, all -> 0x0178 }] */
    /* JADX WARNING: Removed duplicated region for block: B:324:0x09a5 A[Catch:{ SQLiteException -> 0x0295, all -> 0x0178 }] */
    /* JADX WARNING: Removed duplicated region for block: B:328:0x09c1 A[Catch:{ SQLiteException -> 0x0295, all -> 0x0178 }] */
    /* JADX WARNING: Removed duplicated region for block: B:344:0x0a7f A[Catch:{ SQLiteException -> 0x0295, all -> 0x0178 }] */
    /* JADX WARNING: Removed duplicated region for block: B:357:0x0b26 A[Catch:{ SQLiteException -> 0x0b3a }] */
    /* JADX WARNING: Removed duplicated region for block: B:360:0x0b3d  */
    /* JADX WARNING: Removed duplicated region for block: B:386:0x07fa A[EDGE_INSN: B:386:0x07fa->B:271:0x07fa ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:397:0x0a8c A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0188  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x01a3 A[Catch:{ SQLiteException -> 0x0295, all -> 0x0178 }] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0202 A[Catch:{ SQLiteException -> 0x0295, all -> 0x0178 }] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0214 A[Catch:{ SQLiteException -> 0x0295, all -> 0x0178 }] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x02cd A[Catch:{ SQLiteException -> 0x0295, all -> 0x0178 }] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0311 A[Catch:{ SQLiteException -> 0x0295, all -> 0x0178 }] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0314 A[Catch:{ SQLiteException -> 0x0295, all -> 0x0178 }] */
    final void zzax(com.google.android.gms.measurement.internal.zzbh r50, com.google.android.gms.measurement.internal.zzr r51) {
        /*
            r49 = this;
            r1 = r49
            r2 = r51
            java.lang.String r3 = "metadata_fingerprint"
            java.lang.String r4 = "app_id"
            java.lang.String r5 = "_fx"
            java.lang.String r6 = "raw_events"
            java.lang.String r7 = "_sno"
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r51)
            java.lang.String r15 = r2.zza
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r15)
            long r25 = java.lang.System.nanoTime()
            com.google.android.gms.measurement.internal.zzil r8 = r49.zzaX()
            r8.zzg()
            r49.zzM()
            r49.zzA()
            boolean r8 = com.google.android.gms.measurement.internal.zzqa.zzE(r50, r51)
            if (r8 != 0) goto L_0x002f
            goto L_0x00d4
        L_0x002f:
            boolean r8 = r2.zzh
            if (r8 != 0) goto L_0x0037
            r1.zzg(r2)
            return
        L_0x0037:
            com.google.android.gms.measurement.internal.zzif r8 = r49.zzr()
            r9 = r50
            java.lang.String r13 = r9.zza
            boolean r8 = r8.zzx(r15, r13)
            java.lang.String r14 = "_err"
            r12 = 0
            if (r8 == 0) goto L_0x00d5
            com.google.android.gms.measurement.internal.zzhe r2 = r49.zzaW()
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zzk()
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzhe.zzn(r15)
            com.google.android.gms.measurement.internal.zzio r4 = r1.zzn
            com.google.android.gms.measurement.internal.zzgx r4 = r4.zzj()
            java.lang.String r4 = r4.zzd(r13)
            java.lang.String r5 = "Dropping blocked event. appId"
            r2.zzc(r5, r3, r4)
            com.google.android.gms.measurement.internal.zzif r2 = r49.zzr()
            boolean r2 = r2.zzt(r15)
            if (r2 != 0) goto L_0x008e
            com.google.android.gms.measurement.internal.zzif r2 = r49.zzr()
            boolean r2 = r2.zzy(r15)
            if (r2 == 0) goto L_0x0078
            goto L_0x008e
        L_0x0078:
            boolean r2 = r14.equals(r13)
            if (r2 != 0) goto L_0x00d4
            com.google.android.gms.measurement.internal.zzqf r8 = r49.zzB()
            com.google.android.gms.measurement.internal.zzqe r9 = r1.zzK
            java.lang.String r12 = "_ev"
            r14 = 0
            r11 = 11
            r10 = r15
            r8.zzR(r9, r10, r11, r12, r13, r14)
            return
        L_0x008e:
            com.google.android.gms.measurement.internal.zzaw r2 = r49.zzj()
            com.google.android.gms.measurement.internal.zzh r2 = r2.zzl(r15)
            if (r2 == 0) goto L_0x00d4
            long r3 = r2.zzp()
            long r5 = r2.zzg()
            long r3 = java.lang.Math.max(r3, r5)
            com.google.android.gms.common.util.Clock r5 = r49.zzaU()
            long r5 = r5.currentTimeMillis()
            long r5 = r5 - r3
            long r3 = java.lang.Math.abs(r5)
            r49.zzi()
            com.google.android.gms.measurement.internal.zzgg r5 = com.google.android.gms.measurement.internal.zzgi.zzM
            java.lang.Object r5 = r5.zza(r12)
            java.lang.Long r5 = (java.lang.Long) r5
            long r5 = r5.longValue()
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 <= 0) goto L_0x00d4
            com.google.android.gms.measurement.internal.zzhe r3 = r49.zzaW()
            com.google.android.gms.measurement.internal.zzhc r3 = r3.zzd()
            java.lang.String r4 = "Fetching config for blocked app"
            r3.zza(r4)
            r1.zzO(r2)
        L_0x00d4:
            return
        L_0x00d5:
            com.google.android.gms.measurement.internal.zzhf r8 = com.google.android.gms.measurement.internal.zzhf.zzb(r50)
            com.google.android.gms.measurement.internal.zzqf r9 = r49.zzB()
            com.google.android.gms.measurement.internal.zzam r10 = r49.zzi()
            int r10 = r10.zzf(r15)
            r9.zzQ(r8, r10)
            com.google.android.gms.measurement.internal.zzam r9 = r49.zzi()
            com.google.android.gms.measurement.internal.zzgg r10 = com.google.android.gms.measurement.internal.zzgi.zzaf
            r11 = 10
            r13 = 35
            int r9 = r9.zzi(r15, r10, r11, r13)
            android.os.Bundle r10 = r8.zzd
            java.util.TreeSet r11 = new java.util.TreeSet
            java.util.Set r13 = r10.keySet()
            r11.<init>(r13)
            java.util.Iterator r11 = r11.iterator()
        L_0x0105:
            boolean r13 = r11.hasNext()
            if (r13 == 0) goto L_0x0126
            java.lang.Object r13 = r11.next()
            java.lang.String r13 = (java.lang.String) r13
            java.lang.String r12 = "items"
            boolean r12 = r12.equals(r13)
            if (r12 == 0) goto L_0x0124
            com.google.android.gms.measurement.internal.zzqf r12 = r49.zzB()
            android.os.Parcelable[] r13 = r10.getParcelableArray(r13)
            r12.zzP(r13, r9)
        L_0x0124:
            r12 = 0
            goto L_0x0105
        L_0x0126:
            com.google.android.gms.measurement.internal.zzbh r12 = r8.zza()
            com.google.android.gms.measurement.internal.zzhe r8 = r49.zzaW()
            java.lang.String r8 = r8.zzr()
            r9 = 2
            boolean r8 = android.util.Log.isLoggable(r8, r9)
            if (r8 == 0) goto L_0x0150
            com.google.android.gms.measurement.internal.zzhe r8 = r49.zzaW()
            com.google.android.gms.measurement.internal.zzhc r8 = r8.zzj()
            com.google.android.gms.measurement.internal.zzio r9 = r1.zzn
            com.google.android.gms.measurement.internal.zzgx r9 = r9.zzj()
            java.lang.String r9 = r9.zzc(r12)
            java.lang.String r10 = "Logging event"
            r8.zzb(r10, r9)
        L_0x0150:
            com.google.android.gms.measurement.internal.zzaw r8 = r49.zzj()
            r8.zzH()
            r1.zzg(r2)     // Catch:{ all -> 0x0178 }
            java.lang.String r8 = "ecommerce_purchase"
            java.lang.String r9 = r12.zza     // Catch:{ all -> 0x0178 }
            boolean r8 = r8.equals(r9)     // Catch:{ all -> 0x0178 }
            java.lang.String r10 = "refund"
            if (r8 != 0) goto L_0x0174
            java.lang.String r8 = "purchase"
            boolean r8 = r8.equals(r9)     // Catch:{ all -> 0x0178 }
            if (r8 != 0) goto L_0x0174
            boolean r8 = r10.equals(r9)     // Catch:{ all -> 0x0178 }
            if (r8 == 0) goto L_0x0176
        L_0x0174:
            r8 = 1
            goto L_0x017c
        L_0x0176:
            r8 = 0
            goto L_0x017c
        L_0x0178:
            r0 = move-exception
            r2 = r0
            goto L_0x0bbb
        L_0x017c:
            java.lang.String r11 = "_iap"
            boolean r11 = r11.equals(r9)     // Catch:{ all -> 0x0178 }
            r27 = r3
            java.lang.String r3 = "value"
            if (r11 != 0) goto L_0x0199
            if (r8 == 0) goto L_0x018c
            r8 = 1
            goto L_0x0199
        L_0x018c:
            r21 = r3
            r28 = r4
            r29 = r5
        L_0x0192:
            r30 = r6
            r6 = r12
            r3 = r14
            r5 = 0
            goto L_0x0300
        L_0x0199:
            com.google.android.gms.measurement.internal.zzbf r11 = r12.zzb     // Catch:{ all -> 0x0178 }
            java.lang.String r13 = "currency"
            java.lang.String r13 = r11.zzg(r13)     // Catch:{ all -> 0x0178 }
            if (r8 == 0) goto L_0x0202
            java.lang.Double r8 = r11.zzd(r3)     // Catch:{ all -> 0x0178 }
            double r18 = r8.doubleValue()     // Catch:{ all -> 0x0178 }
            r20 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            double r18 = r18 * r20
            r22 = 0
            int r8 = (r18 > r22 ? 1 : (r18 == r22 ? 0 : -1))
            if (r8 != 0) goto L_0x01c8
            java.lang.Long r8 = r11.zze(r3)     // Catch:{ all -> 0x0178 }
            r28 = r4
            r29 = r5
            long r4 = r8.longValue()     // Catch:{ all -> 0x0178 }
            double r4 = (double) r4     // Catch:{ all -> 0x0178 }
            double r18 = r4 * r20
            goto L_0x01cc
        L_0x01c8:
            r28 = r4
            r29 = r5
        L_0x01cc:
            r4 = 4890909195324358656(0x43e0000000000000, double:9.223372036854776E18)
            int r4 = (r18 > r4 ? 1 : (r18 == r4 ? 0 : -1))
            if (r4 > 0) goto L_0x01e4
            r4 = -4332462841530417152(0xc3e0000000000000, double:-9.223372036854776E18)
            int r4 = (r18 > r4 ? 1 : (r18 == r4 ? 0 : -1))
            if (r4 < 0) goto L_0x01e4
            long r4 = java.lang.Math.round(r18)     // Catch:{ all -> 0x0178 }
            boolean r8 = r10.equals(r9)     // Catch:{ all -> 0x0178 }
            if (r8 == 0) goto L_0x020e
            long r4 = -r4
            goto L_0x020e
        L_0x01e4:
            com.google.android.gms.measurement.internal.zzhe r2 = r49.zzaW()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zzk()     // Catch:{ all -> 0x0178 }
            java.lang.String r3 = "Data lost. Currency value is too big. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzhe.zzn(r15)     // Catch:{ all -> 0x0178 }
            java.lang.Double r5 = java.lang.Double.valueOf(r18)     // Catch:{ all -> 0x0178 }
            r2.zzc(r3, r4, r5)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzaw r2 = r49.zzj()     // Catch:{ all -> 0x0178 }
            r2.zzS()     // Catch:{ all -> 0x0178 }
            goto L_0x05af
        L_0x0202:
            r28 = r4
            r29 = r5
            java.lang.Long r4 = r11.zze(r3)     // Catch:{ all -> 0x0178 }
            long r4 = r4.longValue()     // Catch:{ all -> 0x0178 }
        L_0x020e:
            boolean r8 = android.text.TextUtils.isEmpty(r13)     // Catch:{ all -> 0x0178 }
            if (r8 != 0) goto L_0x02fc
            java.util.Locale r8 = java.util.Locale.US     // Catch:{ all -> 0x0178 }
            java.lang.String r8 = r13.toUpperCase(r8)     // Catch:{ all -> 0x0178 }
            java.lang.String r9 = "[A-Z]{3}"
            boolean r9 = r8.matches(r9)     // Catch:{ all -> 0x0178 }
            if (r9 == 0) goto L_0x02fc
            java.lang.String r9 = "_ltv_"
            java.lang.String r11 = r9.concat(r8)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzaw r8 = r49.zzj()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzqd r8 = r8.zzy(r15, r11)     // Catch:{ all -> 0x0178 }
            if (r8 == 0) goto L_0x0238
            java.lang.Object r8 = r8.zze     // Catch:{ all -> 0x0178 }
            boolean r9 = r8 instanceof java.lang.Long     // Catch:{ all -> 0x0178 }
            if (r9 != 0) goto L_0x0240
        L_0x0238:
            r21 = r3
            r30 = r6
            r6 = r12
            r3 = r14
            r14 = 0
            goto L_0x026a
        L_0x0240:
            java.lang.Long r8 = (java.lang.Long) r8     // Catch:{ all -> 0x0178 }
            long r8 = r8.longValue()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzqd r18 = new com.google.android.gms.measurement.internal.zzqd     // Catch:{ all -> 0x0178 }
            java.lang.String r10 = r12.zzc     // Catch:{ all -> 0x0178 }
            com.google.android.gms.common.util.Clock r13 = r49.zzaU()     // Catch:{ all -> 0x0178 }
            long r19 = r13.currentTimeMillis()     // Catch:{ all -> 0x0178 }
            long r8 = r8 + r4
            java.lang.Long r4 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x0178 }
            r8 = r18
            r9 = r15
            r5 = 0
            r30 = r6
            r6 = r12
            r12 = r19
            r21 = r3
            r3 = r14
            r14 = r4
            r8.<init>(r9, r10, r11, r12, r14)     // Catch:{ all -> 0x0178 }
        L_0x0267:
            r4 = r18
            goto L_0x02c3
        L_0x026a:
            com.google.android.gms.measurement.internal.zzaw r8 = r49.zzj()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzam r9 = r49.zzi()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzgg r10 = com.google.android.gms.measurement.internal.zzgi.zzS     // Catch:{ all -> 0x0178 }
            int r9 = r9.zzh(r15, r10)     // Catch:{ all -> 0x0178 }
            int r9 = r9 + -1
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r15)     // Catch:{ all -> 0x0178 }
            r8.zzg()     // Catch:{ all -> 0x0178 }
            r8.zzav()     // Catch:{ all -> 0x0178 }
            android.database.sqlite.SQLiteDatabase r10 = r8.zzj()     // Catch:{ SQLiteException -> 0x0295 }
            java.lang.String r12 = "delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '!_ltv!_%' escape '!'order by set_timestamp desc limit ?,10);"
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch:{ SQLiteException -> 0x0295 }
            java.lang.String[] r9 = new java.lang.String[]{r15, r15, r9}     // Catch:{ SQLiteException -> 0x0295 }
            r10.execSQL(r12, r9)     // Catch:{ SQLiteException -> 0x0295 }
            goto L_0x02aa
        L_0x0295:
            r0 = move-exception
            r9 = r0
            com.google.android.gms.measurement.internal.zzio r8 = r8.zzu     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzhe r8 = r8.zzaW()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzhc r8 = r8.zze()     // Catch:{ all -> 0x0178 }
            java.lang.String r10 = "Error pruning currencies. appId"
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzhe.zzn(r15)     // Catch:{ all -> 0x0178 }
            r8.zzc(r10, r12, r9)     // Catch:{ all -> 0x0178 }
        L_0x02aa:
            com.google.android.gms.measurement.internal.zzqd r18 = new com.google.android.gms.measurement.internal.zzqd     // Catch:{ all -> 0x0178 }
            java.lang.String r10 = r6.zzc     // Catch:{ all -> 0x0178 }
            com.google.android.gms.common.util.Clock r8 = r49.zzaU()     // Catch:{ all -> 0x0178 }
            long r12 = r8.currentTimeMillis()     // Catch:{ all -> 0x0178 }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x0178 }
            r8 = r18
            r9 = r15
            r5 = r14
            r14 = r4
            r8.<init>(r9, r10, r11, r12, r14)     // Catch:{ all -> 0x0178 }
            goto L_0x0267
        L_0x02c3:
            com.google.android.gms.measurement.internal.zzaw r8 = r49.zzj()     // Catch:{ all -> 0x0178 }
            boolean r8 = r8.zzai(r4)     // Catch:{ all -> 0x0178 }
            if (r8 != 0) goto L_0x0300
            com.google.android.gms.measurement.internal.zzhe r8 = r49.zzaW()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzhc r8 = r8.zze()     // Catch:{ all -> 0x0178 }
            java.lang.String r9 = "Too many unique user properties are set. Ignoring user property. appId"
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzhe.zzn(r15)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzio r11 = r1.zzn     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzgx r11 = r11.zzj()     // Catch:{ all -> 0x0178 }
            java.lang.String r12 = r4.zzc     // Catch:{ all -> 0x0178 }
            java.lang.String r11 = r11.zzf(r12)     // Catch:{ all -> 0x0178 }
            java.lang.Object r4 = r4.zze     // Catch:{ all -> 0x0178 }
            r8.zzd(r9, r10, r11, r4)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzqf r8 = r49.zzB()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzqe r9 = r1.zzK     // Catch:{ all -> 0x0178 }
            r13 = 0
            r14 = 0
            r11 = 9
            r12 = 0
            r10 = r15
            r8.zzR(r9, r10, r11, r12, r13, r14)     // Catch:{ all -> 0x0178 }
            goto L_0x0300
        L_0x02fc:
            r21 = r3
            goto L_0x0192
        L_0x0300:
            java.lang.String r4 = r6.zza     // Catch:{ all -> 0x0178 }
            boolean r22 = com.google.android.gms.measurement.internal.zzqf.zzaq(r4)     // Catch:{ all -> 0x0178 }
            boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x0178 }
            r49.zzB()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzbf r14 = r6.zzb     // Catch:{ all -> 0x0178 }
            if (r14 != 0) goto L_0x0314
            r9 = 0
            goto L_0x0333
        L_0x0314:
            com.google.android.gms.measurement.internal.zzbe r8 = new com.google.android.gms.measurement.internal.zzbe     // Catch:{ all -> 0x0178 }
            r8.<init>(r14)     // Catch:{ all -> 0x0178 }
            r9 = 0
        L_0x031b:
            boolean r11 = r8.hasNext()     // Catch:{ all -> 0x0178 }
            if (r11 == 0) goto L_0x0333
            java.lang.String r11 = r8.next()     // Catch:{ all -> 0x0178 }
            java.lang.Object r11 = r14.zzf(r11)     // Catch:{ all -> 0x0178 }
            boolean r12 = r11 instanceof android.os.Parcelable[]     // Catch:{ all -> 0x0178 }
            if (r12 == 0) goto L_0x031b
            android.os.Parcelable[] r11 = (android.os.Parcelable[]) r11     // Catch:{ all -> 0x0178 }
            int r11 = r11.length     // Catch:{ all -> 0x0178 }
            long r11 = (long) r11     // Catch:{ all -> 0x0178 }
            long r9 = r9 + r11
            goto L_0x031b
        L_0x0333:
            r12 = 1
            long r18 = r9 + r12
            com.google.android.gms.measurement.internal.zzaw r8 = r49.zzj()     // Catch:{ all -> 0x0178 }
            long r9 = r49.zza()     // Catch:{ all -> 0x0178 }
            r20 = 0
            r23 = 0
            r24 = 1
            r32 = 0
            r33 = 0
            r11 = r15
            r34 = r6
            r5 = 0
            r12 = r18
            r35 = r14
            r14 = r24
            r36 = r15
            r15 = r22
            r16 = r32
            r17 = r3
            r18 = r33
            r19 = r20
            r20 = r23
            com.google.android.gms.measurement.internal.zzas r8 = r8.zzp(r9, r11, r12, r14, r15, r16, r17, r18, r19, r20)     // Catch:{ all -> 0x0178 }
            long r9 = r8.zzb     // Catch:{ all -> 0x0178 }
            r49.zzi()     // Catch:{ all -> 0x0178 }
            long r11 = com.google.android.gms.measurement.internal.zzam.zzH()     // Catch:{ all -> 0x0178 }
            long r9 = r9 - r11
            int r11 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            r12 = 1000(0x3e8, double:4.94E-321)
            if (r11 <= 0) goto L_0x039d
            long r9 = r9 % r12
            r14 = 1
            int r2 = (r9 > r14 ? 1 : (r9 == r14 ? 0 : -1))
            if (r2 != 0) goto L_0x0394
            com.google.android.gms.measurement.internal.zzhe r2 = r49.zzaW()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zze()     // Catch:{ all -> 0x0178 }
            java.lang.String r3 = "Data loss. Too many events logged. appId, count"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzhe.zzn(r36)     // Catch:{ all -> 0x0178 }
            long r5 = r8.zzb     // Catch:{ all -> 0x0178 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0178 }
            r2.zzc(r3, r4, r5)     // Catch:{ all -> 0x0178 }
        L_0x0394:
            com.google.android.gms.measurement.internal.zzaw r2 = r49.zzj()     // Catch:{ all -> 0x0178 }
            r2.zzS()     // Catch:{ all -> 0x0178 }
            goto L_0x05af
        L_0x039d:
            r14 = 1
            if (r22 == 0) goto L_0x03f4
            long r9 = r8.zza     // Catch:{ all -> 0x0178 }
            r49.zzi()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzgg r11 = com.google.android.gms.measurement.internal.zzgi.zzm     // Catch:{ all -> 0x0178 }
            r14 = 0
            java.lang.Object r11 = r11.zza(r14)     // Catch:{ all -> 0x0178 }
            java.lang.Integer r11 = (java.lang.Integer) r11     // Catch:{ all -> 0x0178 }
            int r11 = r11.intValue()     // Catch:{ all -> 0x0178 }
            long r14 = (long) r11     // Catch:{ all -> 0x0178 }
            long r9 = r9 - r14
            int r11 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r11 <= 0) goto L_0x03f4
            long r9 = r9 % r12
            r2 = 1
            int r2 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
            if (r2 != 0) goto L_0x03d7
            com.google.android.gms.measurement.internal.zzhe r2 = r49.zzaW()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zze()     // Catch:{ all -> 0x0178 }
            java.lang.String r3 = "Data loss. Too many public events logged. appId, count"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzhe.zzn(r36)     // Catch:{ all -> 0x0178 }
            long r5 = r8.zza     // Catch:{ all -> 0x0178 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0178 }
            r2.zzc(r3, r4, r5)     // Catch:{ all -> 0x0178 }
        L_0x03d7:
            com.google.android.gms.measurement.internal.zzqf r8 = r49.zzB()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzqe r9 = r1.zzK     // Catch:{ all -> 0x0178 }
            java.lang.String r12 = "_ev"
            r10 = r34
            java.lang.String r13 = r10.zza     // Catch:{ all -> 0x0178 }
            r14 = 0
            r11 = 16
            r10 = r36
            r8.zzR(r9, r10, r11, r12, r13, r14)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzaw r2 = r49.zzj()     // Catch:{ all -> 0x0178 }
            r2.zzS()     // Catch:{ all -> 0x0178 }
            goto L_0x05af
        L_0x03f4:
            r10 = r34
            r9 = 1000000(0xf4240, float:1.401298E-39)
            if (r3 == 0) goto L_0x043e
            long r11 = r8.zzd     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzam r3 = r49.zzi()     // Catch:{ all -> 0x0178 }
            java.lang.String r13 = r2.zza     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzgg r14 = com.google.android.gms.measurement.internal.zzgi.zzl     // Catch:{ all -> 0x0178 }
            int r3 = r3.zzh(r13, r14)     // Catch:{ all -> 0x0178 }
            int r3 = java.lang.Math.min(r9, r3)     // Catch:{ all -> 0x0178 }
            r13 = 0
            int r3 = java.lang.Math.max(r13, r3)     // Catch:{ all -> 0x0178 }
            long r13 = (long) r3     // Catch:{ all -> 0x0178 }
            long r11 = r11 - r13
            int r3 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1))
            if (r3 <= 0) goto L_0x043e
            r13 = 1
            int r2 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r2 != 0) goto L_0x0435
            com.google.android.gms.measurement.internal.zzhe r2 = r49.zzaW()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zze()     // Catch:{ all -> 0x0178 }
            java.lang.String r3 = "Too many error events logged. appId, count"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzhe.zzn(r36)     // Catch:{ all -> 0x0178 }
            long r5 = r8.zzd     // Catch:{ all -> 0x0178 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0178 }
            r2.zzc(r3, r4, r5)     // Catch:{ all -> 0x0178 }
        L_0x0435:
            com.google.android.gms.measurement.internal.zzaw r2 = r49.zzj()     // Catch:{ all -> 0x0178 }
            r2.zzS()     // Catch:{ all -> 0x0178 }
            goto L_0x05af
        L_0x043e:
            android.os.Bundle r3 = r35.zzc()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzqf r8 = r49.zzB()     // Catch:{ all -> 0x0178 }
            java.lang.String r11 = "_o"
            java.lang.String r12 = r10.zzc     // Catch:{ all -> 0x0178 }
            r8.zzS(r3, r11, r12)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzqf r8 = r49.zzB()     // Catch:{ all -> 0x0178 }
            java.lang.String r11 = r2.zzD     // Catch:{ all -> 0x0178 }
            r15 = r36
            boolean r8 = r8.zzak(r15, r11)     // Catch:{ all -> 0x0178 }
            java.lang.String r13 = "_r"
            if (r8 == 0) goto L_0x0474
            com.google.android.gms.measurement.internal.zzqf r8 = r49.zzB()     // Catch:{ all -> 0x0178 }
            java.lang.String r11 = "_dbg"
            r16 = 1
            java.lang.Long r14 = java.lang.Long.valueOf(r16)     // Catch:{ all -> 0x0178 }
            r8.zzS(r3, r11, r14)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzqf r8 = r49.zzB()     // Catch:{ all -> 0x0178 }
            r8.zzS(r3, r13, r14)     // Catch:{ all -> 0x0178 }
            goto L_0x0476
        L_0x0474:
            r16 = 1
        L_0x0476:
            java.lang.String r8 = "_s"
            boolean r8 = r8.equals(r4)     // Catch:{ all -> 0x0178 }
            if (r8 == 0) goto L_0x0497
            com.google.android.gms.measurement.internal.zzaw r8 = r49.zzj()     // Catch:{ all -> 0x0178 }
            java.lang.String r11 = r2.zza     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzqd r8 = r8.zzy(r11, r7)     // Catch:{ all -> 0x0178 }
            if (r8 == 0) goto L_0x0497
            java.lang.Object r8 = r8.zze     // Catch:{ all -> 0x0178 }
            boolean r11 = r8 instanceof java.lang.Long     // Catch:{ all -> 0x0178 }
            if (r11 == 0) goto L_0x0497
            com.google.android.gms.measurement.internal.zzqf r11 = r49.zzB()     // Catch:{ all -> 0x0178 }
            r11.zzS(r3, r7, r8)     // Catch:{ all -> 0x0178 }
        L_0x0497:
            com.google.android.gms.measurement.internal.zzam r7 = r49.zzi()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzgg r8 = com.google.android.gms.measurement.internal.zzgi.zzbg     // Catch:{ all -> 0x0178 }
            r11 = 0
            boolean r7 = r7.zzx(r11, r8)     // Catch:{ all -> 0x0178 }
            if (r7 == 0) goto L_0x04cc
            java.lang.String r7 = "am"
            boolean r7 = java.util.Objects.equals(r12, r7)     // Catch:{ all -> 0x0178 }
            if (r7 == 0) goto L_0x04cc
            java.lang.String r7 = "_ai"
            boolean r4 = java.util.Objects.equals(r4, r7)     // Catch:{ all -> 0x0178 }
            if (r4 == 0) goto L_0x04cc
            r4 = r21
            java.lang.Object r7 = r3.get(r4)     // Catch:{ all -> 0x0178 }
            if (r7 == 0) goto L_0x04cc
            boolean r8 = r7 instanceof java.lang.String     // Catch:{ all -> 0x0178 }
            if (r8 == 0) goto L_0x04cc
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ NumberFormatException -> 0x04cc }
            double r7 = java.lang.Double.parseDouble(r7)     // Catch:{ NumberFormatException -> 0x04cc }
            r3.remove(r4)     // Catch:{ NumberFormatException -> 0x04cc }
            r3.putDouble(r4, r7)     // Catch:{ NumberFormatException -> 0x04cc }
        L_0x04cc:
            com.google.android.gms.measurement.internal.zzaw r4 = r49.zzj()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r15)     // Catch:{ all -> 0x0178 }
            r4.zzg()     // Catch:{ all -> 0x0178 }
            r4.zzav()     // Catch:{ all -> 0x0178 }
            android.database.sqlite.SQLiteDatabase r7 = r4.zzj()     // Catch:{ SQLiteException -> 0x0507 }
            com.google.android.gms.measurement.internal.zzio r8 = r4.zzu     // Catch:{ SQLiteException -> 0x0507 }
            com.google.android.gms.measurement.internal.zzam r8 = r8.zzf()     // Catch:{ SQLiteException -> 0x0507 }
            com.google.android.gms.measurement.internal.zzgg r11 = com.google.android.gms.measurement.internal.zzgi.zzp     // Catch:{ SQLiteException -> 0x0507 }
            int r8 = r8.zzh(r15, r11)     // Catch:{ SQLiteException -> 0x0507 }
            int r8 = java.lang.Math.min(r9, r8)     // Catch:{ SQLiteException -> 0x0507 }
            r9 = 0
            int r8 = java.lang.Math.max(r9, r8)     // Catch:{ SQLiteException -> 0x0507 }
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ SQLiteException -> 0x0507 }
            java.lang.String r9 = "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)"
            java.lang.String[] r8 = new java.lang.String[]{r15, r8}     // Catch:{ SQLiteException -> 0x0507 }
            r14 = r30
            int r4 = r7.delete(r14, r9, r8)     // Catch:{ SQLiteException -> 0x0504 }
            long r7 = (long) r4
            goto L_0x051f
        L_0x0504:
            r0 = move-exception
        L_0x0505:
            r7 = r0
            goto L_0x050b
        L_0x0507:
            r0 = move-exception
            r14 = r30
            goto L_0x0505
        L_0x050b:
            com.google.android.gms.measurement.internal.zzio r4 = r4.zzu     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzhe r4 = r4.zzaW()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzhc r4 = r4.zze()     // Catch:{ all -> 0x0178 }
            java.lang.String r8 = "Error deleting over the limit events. appId"
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzhe.zzn(r15)     // Catch:{ all -> 0x0178 }
            r4.zzc(r8, r9, r7)     // Catch:{ all -> 0x0178 }
            r7 = r5
        L_0x051f:
            int r4 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r4 <= 0) goto L_0x0538
            com.google.android.gms.measurement.internal.zzhe r4 = r49.zzaW()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzhc r4 = r4.zzk()     // Catch:{ all -> 0x0178 }
            java.lang.String r9 = "Data lost. Too many events stored on disk, deleted. appId"
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzhe.zzn(r15)     // Catch:{ all -> 0x0178 }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x0178 }
            r4.zzc(r9, r11, r7)     // Catch:{ all -> 0x0178 }
        L_0x0538:
            com.google.android.gms.measurement.internal.zzbc r4 = new com.google.android.gms.measurement.internal.zzbc     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzio r7 = r1.zzn     // Catch:{ all -> 0x0178 }
            java.lang.String r11 = r10.zzc     // Catch:{ all -> 0x0178 }
            java.lang.String r12 = r10.zza     // Catch:{ all -> 0x0178 }
            long r9 = r10.zzd     // Catch:{ all -> 0x0178 }
            r18 = 0
            r8 = r4
            r20 = r9
            r9 = r7
            r10 = r11
            r11 = r15
            r40 = r13
            r37 = r14
            r13 = r20
            r5 = r15
            r15 = r18
            r17 = r3
            r8.<init>((com.google.android.gms.measurement.internal.zzio) r9, (java.lang.String) r10, (java.lang.String) r11, (java.lang.String) r12, (long) r13, (long) r15, (android.os.Bundle) r17)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzaw r3 = r49.zzj()     // Catch:{ all -> 0x0178 }
            java.lang.String r10 = r4.zzb     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzbd r3 = r3.zzs(r5, r10)     // Catch:{ all -> 0x0178 }
            if (r3 != 0) goto L_0x05d3
            com.google.android.gms.measurement.internal.zzaw r3 = r49.zzj()     // Catch:{ all -> 0x0178 }
            long r8 = r3.zzi(r5)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzam r3 = r49.zzi()     // Catch:{ all -> 0x0178 }
            int r3 = r3.zzb(r5)     // Catch:{ all -> 0x0178 }
            long r11 = (long) r3     // Catch:{ all -> 0x0178 }
            int r3 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
            if (r3 < 0) goto L_0x05b7
            if (r22 == 0) goto L_0x05b7
            com.google.android.gms.measurement.internal.zzhe r2 = r49.zzaW()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zze()     // Catch:{ all -> 0x0178 }
            java.lang.String r3 = "Too many event names used, ignoring event. appId, name, supported count"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzhe.zzn(r5)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzgx r6 = r7.zzj()     // Catch:{ all -> 0x0178 }
            java.lang.String r6 = r6.zzd(r10)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzam r7 = r49.zzi()     // Catch:{ all -> 0x0178 }
            int r7 = r7.zzb(r5)     // Catch:{ all -> 0x0178 }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x0178 }
            r2.zzd(r3, r4, r6, r7)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzqf r8 = r49.zzB()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzqe r9 = r1.zzK     // Catch:{ all -> 0x0178 }
            r13 = 0
            r14 = 0
            r11 = 8
            r12 = 0
            r10 = r5
            r8.zzR(r9, r10, r11, r12, r13, r14)     // Catch:{ all -> 0x0178 }
        L_0x05af:
            com.google.android.gms.measurement.internal.zzaw r2 = r49.zzj()
            r2.zzL()
            return
        L_0x05b7:
            com.google.android.gms.measurement.internal.zzbd r3 = new com.google.android.gms.measurement.internal.zzbd     // Catch:{ all -> 0x0178 }
            long r6 = r4.zzd     // Catch:{ all -> 0x0178 }
            r23 = 0
            r24 = 0
            r11 = 0
            r13 = 0
            r15 = 0
            r19 = 0
            r21 = 0
            r22 = 0
            r8 = r3
            r9 = r5
            r17 = r6
            r8.<init>(r9, r10, r11, r13, r15, r17, r19, r21, r22, r23, r24)     // Catch:{ all -> 0x0178 }
            goto L_0x05df
        L_0x05d3:
            long r5 = r3.zzf     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzbc r4 = r4.zza(r7, r5)     // Catch:{ all -> 0x0178 }
            long r5 = r4.zzd     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzbd r3 = r3.zzc(r5)     // Catch:{ all -> 0x0178 }
        L_0x05df:
            com.google.android.gms.measurement.internal.zzaw r5 = r49.zzj()     // Catch:{ all -> 0x0178 }
            r5.zzV(r3)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzil r3 = r49.zzaX()     // Catch:{ all -> 0x0178 }
            r3.zzg()     // Catch:{ all -> 0x0178 }
            r49.zzM()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r51)     // Catch:{ all -> 0x0178 }
            java.lang.String r3 = r4.zza     // Catch:{ all -> 0x0178 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r3)     // Catch:{ all -> 0x0178 }
            java.lang.String r5 = r2.zza     // Catch:{ all -> 0x0178 }
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.common.internal.Preconditions.checkArgument(r3)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.internal.measurement.zzhw r3 = com.google.android.gms.internal.measurement.zzhx.zzz()     // Catch:{ all -> 0x0178 }
            r6 = 1
            r3.zzar(r6)     // Catch:{ all -> 0x0178 }
            java.lang.String r7 = "android"
            r3.zzan(r7)     // Catch:{ all -> 0x0178 }
            boolean r7 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x0178 }
            if (r7 != 0) goto L_0x061a
            r3.zzI(r5)     // Catch:{ all -> 0x0178 }
        L_0x061a:
            java.lang.String r7 = r2.zzd     // Catch:{ all -> 0x0178 }
            boolean r8 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x0178 }
            if (r8 != 0) goto L_0x0625
            r3.zzK(r7)     // Catch:{ all -> 0x0178 }
        L_0x0625:
            java.lang.String r7 = r2.zzc     // Catch:{ all -> 0x0178 }
            boolean r8 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x0178 }
            if (r8 != 0) goto L_0x0630
            r3.zzL(r7)     // Catch:{ all -> 0x0178 }
        L_0x0630:
            java.lang.String r7 = r2.zzw     // Catch:{ all -> 0x0178 }
            boolean r8 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x0178 }
            if (r8 != 0) goto L_0x063b
            r3.zzav(r7)     // Catch:{ all -> 0x0178 }
        L_0x063b:
            long r7 = r2.zzj     // Catch:{ all -> 0x0178 }
            r9 = -2147483648(0xffffffff80000000, double:NaN)
            int r9 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r9 == 0) goto L_0x0648
            int r7 = (int) r7     // Catch:{ all -> 0x0178 }
            r3.zzM(r7)     // Catch:{ all -> 0x0178 }
        L_0x0648:
            long r7 = r2.zze     // Catch:{ all -> 0x0178 }
            r3.zzai(r7)     // Catch:{ all -> 0x0178 }
            java.lang.String r7 = r2.zzb     // Catch:{ all -> 0x0178 }
            boolean r8 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x0178 }
            if (r8 != 0) goto L_0x0658
            r3.zzah(r7)     // Catch:{ all -> 0x0178 }
        L_0x0658:
            java.lang.Object r7 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r5)     // Catch:{ all -> 0x0178 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzjx r7 = r1.zzu(r7)     // Catch:{ all -> 0x0178 }
            java.lang.String r8 = r2.zzu     // Catch:{ all -> 0x0178 }
            r9 = 100
            com.google.android.gms.measurement.internal.zzjx r8 = com.google.android.gms.measurement.internal.zzjx.zzk(r8, r9)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzjx r7 = r7.zzl(r8)     // Catch:{ all -> 0x0178 }
            java.lang.String r8 = r7.zzp()     // Catch:{ all -> 0x0178 }
            r3.zzT(r8)     // Catch:{ all -> 0x0178 }
            java.lang.String r8 = r3.zzaJ()     // Catch:{ all -> 0x0178 }
            boolean r8 = r8.isEmpty()     // Catch:{ all -> 0x0178 }
            if (r8 == 0) goto L_0x068a
            java.lang.String r8 = r2.zzp     // Catch:{ all -> 0x0178 }
            boolean r10 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x0178 }
            if (r10 != 0) goto L_0x068a
            r3.zzH(r8)     // Catch:{ all -> 0x0178 }
        L_0x068a:
            com.google.android.gms.internal.measurement.zzqr.zzb()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzam r8 = r49.zzi()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzgg r10 = com.google.android.gms.measurement.internal.zzgi.zzaV     // Catch:{ all -> 0x0178 }
            boolean r8 = r8.zzx(r5, r10)     // Catch:{ all -> 0x0178 }
            if (r8 == 0) goto L_0x073c
            com.google.android.gms.measurement.internal.zzqf r8 = r49.zzB()     // Catch:{ all -> 0x0178 }
            boolean r5 = r8.zzab(r5)     // Catch:{ all -> 0x0178 }
            if (r5 == 0) goto L_0x073c
            int r5 = r2.zzB     // Catch:{ all -> 0x0178 }
            r3.zzG(r5)     // Catch:{ all -> 0x0178 }
            long r10 = r2.zzC     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzjw r5 = com.google.android.gms.measurement.internal.zzjw.AD_STORAGE     // Catch:{ all -> 0x0178 }
            boolean r5 = r7.zzr(r5)     // Catch:{ all -> 0x0178 }
            r7 = 32
            if (r5 != 0) goto L_0x06be
            r12 = 0
            int r5 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r5 == 0) goto L_0x06be
            r12 = -2
            long r10 = r10 & r12
            long r10 = r10 | r7
        L_0x06be:
            r12 = 1
            int r5 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r5 != 0) goto L_0x06c6
            r5 = r6
            goto L_0x06c7
        L_0x06c6:
            r5 = 0
        L_0x06c7:
            r3.zzaa(r5)     // Catch:{ all -> 0x0178 }
            r14 = 0
            int r5 = (r10 > r14 ? 1 : (r10 == r14 ? 0 : -1))
            if (r5 != 0) goto L_0x06d2
            goto L_0x073e
        L_0x06d2:
            com.google.android.gms.internal.measurement.zzhf r5 = com.google.android.gms.internal.measurement.zzhg.zza()     // Catch:{ all -> 0x0178 }
            long r16 = r10 & r12
            int r16 = (r16 > r14 ? 1 : (r16 == r14 ? 0 : -1))
            if (r16 == 0) goto L_0x06dd
            goto L_0x06de
        L_0x06dd:
            r6 = 0
        L_0x06de:
            r5.zzc(r6)     // Catch:{ all -> 0x0178 }
            r16 = 2
            long r16 = r10 & r16
            int r6 = (r16 > r14 ? 1 : (r16 == r14 ? 0 : -1))
            if (r6 == 0) goto L_0x06eb
            r6 = 1
            goto L_0x06ec
        L_0x06eb:
            r6 = 0
        L_0x06ec:
            r5.zze(r6)     // Catch:{ all -> 0x0178 }
            r16 = 4
            long r16 = r10 & r16
            int r6 = (r16 > r14 ? 1 : (r16 == r14 ? 0 : -1))
            if (r6 == 0) goto L_0x06f9
            r6 = 1
            goto L_0x06fa
        L_0x06f9:
            r6 = 0
        L_0x06fa:
            r5.zzf(r6)     // Catch:{ all -> 0x0178 }
            r16 = 8
            long r16 = r10 & r16
            int r6 = (r16 > r14 ? 1 : (r16 == r14 ? 0 : -1))
            if (r6 == 0) goto L_0x0707
            r6 = 1
            goto L_0x0708
        L_0x0707:
            r6 = 0
        L_0x0708:
            r5.zzg(r6)     // Catch:{ all -> 0x0178 }
            r16 = 16
            long r16 = r10 & r16
            int r6 = (r16 > r14 ? 1 : (r16 == r14 ? 0 : -1))
            if (r6 == 0) goto L_0x0715
            r6 = 1
            goto L_0x0716
        L_0x0715:
            r6 = 0
        L_0x0716:
            r5.zzb(r6)     // Catch:{ all -> 0x0178 }
            long r6 = r10 & r7
            int r6 = (r6 > r14 ? 1 : (r6 == r14 ? 0 : -1))
            if (r6 == 0) goto L_0x0721
            r6 = 1
            goto L_0x0722
        L_0x0721:
            r6 = 0
        L_0x0722:
            r5.zza(r6)     // Catch:{ all -> 0x0178 }
            r6 = 64
            long r6 = r6 & r10
            int r6 = (r6 > r14 ? 1 : (r6 == r14 ? 0 : -1))
            if (r6 == 0) goto L_0x072e
            r6 = 1
            goto L_0x072f
        L_0x072e:
            r6 = 0
        L_0x072f:
            r5.zzd(r6)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.internal.measurement.zzmd r5 = r5.zzba()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.internal.measurement.zzhg r5 = (com.google.android.gms.internal.measurement.zzhg) r5     // Catch:{ all -> 0x0178 }
            r3.zzN(r5)     // Catch:{ all -> 0x0178 }
            goto L_0x073e
        L_0x073c:
            r12 = 1
        L_0x073e:
            long r5 = r2.zzf     // Catch:{ all -> 0x0178 }
            r7 = 0
            int r10 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r10 == 0) goto L_0x0749
            r3.zzW(r5)     // Catch:{ all -> 0x0178 }
        L_0x0749:
            long r5 = r2.zzr     // Catch:{ all -> 0x0178 }
            r3.zzZ(r5)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzqa r5 = r49.zzA()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzpv r6 = r5.zzg     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzio r6 = r6.zzn     // Catch:{ all -> 0x0178 }
            android.content.Context r6 = r6.zzaT()     // Catch:{ all -> 0x0178 }
            android.content.ContentResolver r6 = r6.getContentResolver()     // Catch:{ all -> 0x0178 }
            java.lang.String r7 = "com.google.android.gms.measurement"
            android.net.Uri r7 = com.google.android.gms.internal.measurement.zzjx.zza(r7)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzbj r8 = new com.google.android.gms.measurement.internal.zzbj     // Catch:{ all -> 0x0178 }
            r8.<init>()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.internal.measurement.zzjm r6 = com.google.android.gms.internal.measurement.zzjm.zza(r6, r7, r8)     // Catch:{ all -> 0x0178 }
            if (r6 != 0) goto L_0x0774
            java.util.Map r6 = java.util.Collections.emptyMap()     // Catch:{ all -> 0x0178 }
            goto L_0x0778
        L_0x0774:
            java.util.Map r6 = r6.zzd()     // Catch:{ all -> 0x0178 }
        L_0x0778:
            if (r6 == 0) goto L_0x0780
            boolean r7 = r6.isEmpty()     // Catch:{ all -> 0x0178 }
            if (r7 == 0) goto L_0x0783
        L_0x0780:
            r7 = 0
            goto L_0x0802
        L_0x0783:
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ all -> 0x0178 }
            r7.<init>()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzgg r8 = com.google.android.gms.measurement.internal.zzgi.zzae     // Catch:{ all -> 0x0178 }
            r10 = 0
            java.lang.Object r8 = r8.zza(r10)     // Catch:{ all -> 0x0178 }
            java.lang.Integer r8 = (java.lang.Integer) r8     // Catch:{ all -> 0x0178 }
            int r8 = r8.intValue()     // Catch:{ all -> 0x0178 }
            java.util.Set r6 = r6.entrySet()     // Catch:{ all -> 0x0178 }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ all -> 0x0178 }
        L_0x079d:
            boolean r10 = r6.hasNext()     // Catch:{ all -> 0x0178 }
            if (r10 == 0) goto L_0x07fa
            java.lang.Object r10 = r6.next()     // Catch:{ all -> 0x0178 }
            java.util.Map$Entry r10 = (java.util.Map.Entry) r10     // Catch:{ all -> 0x0178 }
            java.lang.Object r11 = r10.getKey()     // Catch:{ all -> 0x0178 }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x0178 }
            java.lang.String r14 = "measurement.id."
            boolean r11 = r11.startsWith(r14)     // Catch:{ all -> 0x0178 }
            if (r11 == 0) goto L_0x079d
            java.lang.Object r10 = r10.getValue()     // Catch:{ NumberFormatException -> 0x07e8 }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ NumberFormatException -> 0x07e8 }
            int r10 = java.lang.Integer.parseInt(r10)     // Catch:{ NumberFormatException -> 0x07e8 }
            if (r10 == 0) goto L_0x079d
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ NumberFormatException -> 0x07e8 }
            r7.add(r10)     // Catch:{ NumberFormatException -> 0x07e8 }
            int r10 = r7.size()     // Catch:{ NumberFormatException -> 0x07e8 }
            if (r10 < r8) goto L_0x079d
            com.google.android.gms.measurement.internal.zzio r10 = r5.zzu     // Catch:{ NumberFormatException -> 0x07e8 }
            com.google.android.gms.measurement.internal.zzhe r10 = r10.zzaW()     // Catch:{ NumberFormatException -> 0x07e8 }
            com.google.android.gms.measurement.internal.zzhc r10 = r10.zzk()     // Catch:{ NumberFormatException -> 0x07e8 }
            java.lang.String r11 = "Too many experiment IDs. Number of IDs"
            int r14 = r7.size()     // Catch:{ NumberFormatException -> 0x07e8 }
            java.lang.Integer r14 = java.lang.Integer.valueOf(r14)     // Catch:{ NumberFormatException -> 0x07e8 }
            r10.zzb(r11, r14)     // Catch:{ NumberFormatException -> 0x07e8 }
            goto L_0x07fa
        L_0x07e8:
            r0 = move-exception
            r10 = r0
            com.google.android.gms.measurement.internal.zzio r11 = r5.zzu     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzhe r11 = r11.zzaW()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzhc r11 = r11.zzk()     // Catch:{ all -> 0x0178 }
            java.lang.String r14 = "Experiment ID NumberFormatException"
            r11.zzb(r14, r10)     // Catch:{ all -> 0x0178 }
            goto L_0x079d
        L_0x07fa:
            boolean r5 = r7.isEmpty()     // Catch:{ all -> 0x0178 }
            if (r5 == 0) goto L_0x0802
            goto L_0x0780
        L_0x0802:
            if (r7 == 0) goto L_0x0807
            r3.zzk(r7)     // Catch:{ all -> 0x0178 }
        L_0x0807:
            com.google.android.gms.measurement.internal.zzam r5 = r49.zzi()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzgg r6 = com.google.android.gms.measurement.internal.zzgi.zzbk     // Catch:{ all -> 0x0178 }
            r7 = 0
            boolean r5 = r5.zzx(r7, r6)     // Catch:{ all -> 0x0178 }
            if (r5 == 0) goto L_0x0819
            java.lang.String r5 = ""
            r3.zzaf(r5)     // Catch:{ all -> 0x0178 }
        L_0x0819:
            java.lang.String r5 = r2.zza     // Catch:{ all -> 0x0178 }
            java.lang.Object r6 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r5)     // Catch:{ all -> 0x0178 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzjx r6 = r1.zzu(r6)     // Catch:{ all -> 0x0178 }
            java.lang.String r7 = r2.zzu     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzjx r7 = com.google.android.gms.measurement.internal.zzjx.zzk(r7, r9)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzjx r6 = r6.zzl(r7)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzjw r7 = com.google.android.gms.measurement.internal.zzjw.AD_STORAGE     // Catch:{ all -> 0x0178 }
            boolean r8 = r6.zzr(r7)     // Catch:{ all -> 0x0178 }
            if (r8 == 0) goto L_0x08c5
            boolean r8 = r2.zzn     // Catch:{ all -> 0x0178 }
            if (r8 == 0) goto L_0x08c5
            com.google.android.gms.measurement.internal.zzoa r8 = r1.zzk     // Catch:{ all -> 0x0178 }
            android.util.Pair r8 = r8.zzd(r5, r6)     // Catch:{ all -> 0x0178 }
            java.lang.Object r9 = r8.first     // Catch:{ all -> 0x0178 }
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9     // Catch:{ all -> 0x0178 }
            boolean r9 = android.text.TextUtils.isEmpty(r9)     // Catch:{ all -> 0x0178 }
            if (r9 != 0) goto L_0x08c5
            java.lang.Object r9 = r8.first     // Catch:{ all -> 0x0178 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ all -> 0x0178 }
            r3.zzas(r9)     // Catch:{ all -> 0x0178 }
            java.lang.Object r9 = r8.second     // Catch:{ all -> 0x0178 }
            if (r9 == 0) goto L_0x085f
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch:{ all -> 0x0178 }
            boolean r9 = r9.booleanValue()     // Catch:{ all -> 0x0178 }
            r3.zzal(r9)     // Catch:{ all -> 0x0178 }
        L_0x085f:
            java.lang.String r9 = r4.zzb     // Catch:{ all -> 0x0178 }
            r10 = r29
            boolean r9 = r9.equals(r10)     // Catch:{ all -> 0x0178 }
            if (r9 != 0) goto L_0x08c5
            java.lang.Object r8 = r8.first     // Catch:{ all -> 0x0178 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ all -> 0x0178 }
            java.lang.String r9 = "00000000-0000-0000-0000-000000000000"
            boolean r8 = r8.equals(r9)     // Catch:{ all -> 0x0178 }
            if (r8 != 0) goto L_0x08c5
            com.google.android.gms.measurement.internal.zzaw r8 = r49.zzj()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzh r8 = r8.zzl(r5)     // Catch:{ all -> 0x0178 }
            if (r8 == 0) goto L_0x08c5
            boolean r9 = r8.zzaM()     // Catch:{ all -> 0x0178 }
            if (r9 == 0) goto L_0x08c5
            r9 = 0
            r11 = 0
            r1.zzar(r5, r11, r9, r9)     // Catch:{ all -> 0x0178 }
            android.os.Bundle r9 = new android.os.Bundle     // Catch:{ all -> 0x0178 }
            r9.<init>()     // Catch:{ all -> 0x0178 }
            java.lang.Long r11 = r8.zzy()     // Catch:{ all -> 0x0178 }
            if (r11 == 0) goto L_0x08a7
            java.lang.String r14 = "_pfo"
            long r12 = r11.longValue()     // Catch:{ all -> 0x0178 }
            r11 = r6
            r15 = r7
            r6 = 0
            long r12 = java.lang.Math.max(r6, r12)     // Catch:{ all -> 0x0178 }
            r9.putLong(r14, r12)     // Catch:{ all -> 0x0178 }
            goto L_0x08a9
        L_0x08a7:
            r11 = r6
            r15 = r7
        L_0x08a9:
            java.lang.Long r6 = r8.zzz()     // Catch:{ all -> 0x0178 }
            if (r6 == 0) goto L_0x08b8
            java.lang.String r7 = "_uwa"
            long r12 = r6.longValue()     // Catch:{ all -> 0x0178 }
            r9.putLong(r7, r12)     // Catch:{ all -> 0x0178 }
        L_0x08b8:
            r8 = r40
            r6 = 1
            r9.putLong(r8, r6)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzqe r6 = r1.zzK     // Catch:{ all -> 0x0178 }
            r6.zza(r5, r10, r9)     // Catch:{ all -> 0x0178 }
            goto L_0x08c9
        L_0x08c5:
            r11 = r6
            r15 = r7
            r8 = r40
        L_0x08c9:
            com.google.android.gms.measurement.internal.zzio r6 = r1.zzn     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzbb r7 = r6.zzg()     // Catch:{ all -> 0x0178 }
            r7.zzv()     // Catch:{ all -> 0x0178 }
            java.lang.String r7 = android.os.Build.MODEL     // Catch:{ all -> 0x0178 }
            r3.zzX(r7)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzbb r7 = r6.zzg()     // Catch:{ all -> 0x0178 }
            r7.zzv()     // Catch:{ all -> 0x0178 }
            java.lang.String r7 = android.os.Build.VERSION.RELEASE     // Catch:{ all -> 0x0178 }
            r3.zzam(r7)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzbb r7 = r6.zzg()     // Catch:{ all -> 0x0178 }
            long r9 = r7.zza()     // Catch:{ all -> 0x0178 }
            int r7 = (int) r9     // Catch:{ all -> 0x0178 }
            r3.zzaz(r7)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzbb r7 = r6.zzg()     // Catch:{ all -> 0x0178 }
            java.lang.String r7 = r7.zzb()     // Catch:{ all -> 0x0178 }
            r3.zzaD(r7)     // Catch:{ all -> 0x0178 }
            long r9 = r2.zzy     // Catch:{ all -> 0x0178 }
            r3.zzay(r9)     // Catch:{ all -> 0x0178 }
            boolean r7 = r6.zzJ()     // Catch:{ all -> 0x0178 }
            if (r7 == 0) goto L_0x0912
            r3.zzaF()     // Catch:{ all -> 0x0178 }
            r7 = 0
            boolean r9 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x0178 }
            if (r9 != 0) goto L_0x0912
            r3.zzY(r7)     // Catch:{ all -> 0x0178 }
        L_0x0912:
            com.google.android.gms.measurement.internal.zzaw r7 = r49.zzj()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzh r7 = r7.zzl(r5)     // Catch:{ all -> 0x0178 }
            if (r7 != 0) goto L_0x097b
            com.google.android.gms.measurement.internal.zzh r7 = new com.google.android.gms.measurement.internal.zzh     // Catch:{ all -> 0x0178 }
            r7.<init>(r6, r5)     // Catch:{ all -> 0x0178 }
            java.lang.String r6 = r1.zzC(r11)     // Catch:{ all -> 0x0178 }
            r7.zzV(r6)     // Catch:{ all -> 0x0178 }
            java.lang.String r6 = r2.zzk     // Catch:{ all -> 0x0178 }
            r7.zzan(r6)     // Catch:{ all -> 0x0178 }
            java.lang.String r6 = r2.zzb     // Catch:{ all -> 0x0178 }
            r7.zzao(r6)     // Catch:{ all -> 0x0178 }
            r6 = r15
            boolean r6 = r11.zzr(r6)     // Catch:{ all -> 0x0178 }
            if (r6 == 0) goto L_0x0944
            com.google.android.gms.measurement.internal.zzoa r6 = r1.zzk     // Catch:{ all -> 0x0178 }
            boolean r9 = r2.zzn     // Catch:{ all -> 0x0178 }
            java.lang.String r6 = r6.zzf(r5, r9)     // Catch:{ all -> 0x0178 }
            r7.zzax(r6)     // Catch:{ all -> 0x0178 }
        L_0x0944:
            r9 = 0
            r7.zzat(r9)     // Catch:{ all -> 0x0178 }
            r7.zzau(r9)     // Catch:{ all -> 0x0178 }
            r7.zzas(r9)     // Catch:{ all -> 0x0178 }
            java.lang.String r6 = r2.zzc     // Catch:{ all -> 0x0178 }
            r7.zzX(r6)     // Catch:{ all -> 0x0178 }
            long r9 = r2.zzj     // Catch:{ all -> 0x0178 }
            r7.zzY(r9)     // Catch:{ all -> 0x0178 }
            java.lang.String r6 = r2.zzd     // Catch:{ all -> 0x0178 }
            r7.zzW(r6)     // Catch:{ all -> 0x0178 }
            long r9 = r2.zze     // Catch:{ all -> 0x0178 }
            r7.zzap(r9)     // Catch:{ all -> 0x0178 }
            long r9 = r2.zzf     // Catch:{ all -> 0x0178 }
            r7.zzaj(r9)     // Catch:{ all -> 0x0178 }
            boolean r6 = r2.zzh     // Catch:{ all -> 0x0178 }
            r7.zzav(r6)     // Catch:{ all -> 0x0178 }
            long r9 = r2.zzr     // Catch:{ all -> 0x0178 }
            r7.zzal(r9)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzaw r6 = r49.zzj()     // Catch:{ all -> 0x0178 }
            r9 = 0
            r6.zzT(r7, r9, r9)     // Catch:{ all -> 0x0178 }
            goto L_0x097c
        L_0x097b:
            r9 = 0
        L_0x097c:
            com.google.android.gms.measurement.internal.zzjw r6 = com.google.android.gms.measurement.internal.zzjw.ANALYTICS_STORAGE     // Catch:{ all -> 0x0178 }
            boolean r6 = r11.zzr(r6)     // Catch:{ all -> 0x0178 }
            if (r6 == 0) goto L_0x099b
            java.lang.String r6 = r7.zzD()     // Catch:{ all -> 0x0178 }
            boolean r6 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x0178 }
            if (r6 != 0) goto L_0x099b
            java.lang.String r6 = r7.zzD()     // Catch:{ all -> 0x0178 }
            java.lang.Object r6 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r6)     // Catch:{ all -> 0x0178 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ all -> 0x0178 }
            r3.zzJ(r6)     // Catch:{ all -> 0x0178 }
        L_0x099b:
            java.lang.String r6 = r7.zzG()     // Catch:{ all -> 0x0178 }
            boolean r6 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x0178 }
            if (r6 != 0) goto L_0x09b2
            java.lang.String r6 = r7.zzG()     // Catch:{ all -> 0x0178 }
            java.lang.Object r6 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r6)     // Catch:{ all -> 0x0178 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ all -> 0x0178 }
            r3.zzag(r6)     // Catch:{ all -> 0x0178 }
        L_0x09b2:
            com.google.android.gms.measurement.internal.zzaw r6 = r49.zzj()     // Catch:{ all -> 0x0178 }
            java.util.List r5 = r6.zzE(r5)     // Catch:{ all -> 0x0178 }
            r11 = r9
        L_0x09bb:
            int r6 = r5.size()     // Catch:{ all -> 0x0178 }
            if (r11 >= r6) goto L_0x0a1f
            com.google.android.gms.internal.measurement.zzin r6 = com.google.android.gms.internal.measurement.zzio.zze()     // Catch:{ all -> 0x0178 }
            java.lang.Object r10 = r5.get(r11)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzqd r10 = (com.google.android.gms.measurement.internal.zzqd) r10     // Catch:{ all -> 0x0178 }
            java.lang.String r10 = r10.zzc     // Catch:{ all -> 0x0178 }
            r6.zzf(r10)     // Catch:{ all -> 0x0178 }
            java.lang.Object r10 = r5.get(r11)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzqd r10 = (com.google.android.gms.measurement.internal.zzqd) r10     // Catch:{ all -> 0x0178 }
            long r12 = r10.zzd     // Catch:{ all -> 0x0178 }
            r6.zzg(r12)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzqa r10 = r49.zzA()     // Catch:{ all -> 0x0178 }
            java.lang.Object r12 = r5.get(r11)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzqd r12 = (com.google.android.gms.measurement.internal.zzqd) r12     // Catch:{ all -> 0x0178 }
            java.lang.Object r12 = r12.zze     // Catch:{ all -> 0x0178 }
            r10.zzx(r6, r12)     // Catch:{ all -> 0x0178 }
            r3.zzo(r6)     // Catch:{ all -> 0x0178 }
            java.lang.String r6 = "_sid"
            java.lang.Object r10 = r5.get(r11)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzqd r10 = (com.google.android.gms.measurement.internal.zzqd) r10     // Catch:{ all -> 0x0178 }
            java.lang.String r10 = r10.zzc     // Catch:{ all -> 0x0178 }
            boolean r6 = r6.equals(r10)     // Catch:{ all -> 0x0178 }
            if (r6 == 0) goto L_0x0a1c
            long r12 = r7.zzv()     // Catch:{ all -> 0x0178 }
            r14 = 0
            int r6 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r6 == 0) goto L_0x0a1c
            com.google.android.gms.measurement.internal.zzqa r6 = r49.zzA()     // Catch:{ all -> 0x0178 }
            java.lang.String r10 = r2.zzw     // Catch:{ all -> 0x0178 }
            long r12 = r6.zzd(r10)     // Catch:{ all -> 0x0178 }
            long r14 = r7.zzv()     // Catch:{ all -> 0x0178 }
            int r6 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r6 == 0) goto L_0x0a1c
            r3.zzA()     // Catch:{ all -> 0x0178 }
        L_0x0a1c:
            int r11 = r11 + 1
            goto L_0x09bb
        L_0x0a1f:
            com.google.android.gms.measurement.internal.zzaw r2 = r49.zzj()     // Catch:{ IOException -> 0x0b58 }
            com.google.android.gms.internal.measurement.zzmd r5 = r3.zzba()     // Catch:{ IOException -> 0x0b58 }
            com.google.android.gms.internal.measurement.zzhx r5 = (com.google.android.gms.internal.measurement.zzhx) r5     // Catch:{ IOException -> 0x0b58 }
            r2.zzg()     // Catch:{ IOException -> 0x0b58 }
            r2.zzav()     // Catch:{ IOException -> 0x0b58 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5)     // Catch:{ IOException -> 0x0b58 }
            java.lang.String r6 = r5.zzF()     // Catch:{ IOException -> 0x0b58 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r6)     // Catch:{ IOException -> 0x0b58 }
            byte[] r6 = r5.zzcd()     // Catch:{ IOException -> 0x0b58 }
            com.google.android.gms.measurement.internal.zzpv r7 = r2.zzg     // Catch:{ IOException -> 0x0b58 }
            com.google.android.gms.measurement.internal.zzqa r7 = r7.zzA()     // Catch:{ IOException -> 0x0b58 }
            long r10 = r7.zzf(r6)     // Catch:{ IOException -> 0x0b58 }
            android.content.ContentValues r7 = new android.content.ContentValues     // Catch:{ IOException -> 0x0b58 }
            r7.<init>()     // Catch:{ IOException -> 0x0b58 }
            java.lang.String r12 = r5.zzF()     // Catch:{ IOException -> 0x0b58 }
            r13 = r28
            r7.put(r13, r12)     // Catch:{ IOException -> 0x0b58 }
            java.lang.Long r12 = java.lang.Long.valueOf(r10)     // Catch:{ IOException -> 0x0b58 }
            r14 = r27
            r7.put(r14, r12)     // Catch:{ IOException -> 0x0b58 }
            java.lang.String r12 = "metadata"
            r7.put(r12, r6)     // Catch:{ IOException -> 0x0b58 }
            android.database.sqlite.SQLiteDatabase r6 = r2.zzj()     // Catch:{ SQLiteException -> 0x0b5b }
            java.lang.String r12 = "raw_events_metadata"
            r15 = 4
            r9 = 0
            r6.insertWithOnConflict(r12, r9, r7, r15)     // Catch:{ SQLiteException -> 0x0b5b }
            com.google.android.gms.measurement.internal.zzaw r2 = r49.zzj()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzbf r3 = r4.zzf     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzbe r5 = new com.google.android.gms.measurement.internal.zzbe     // Catch:{ all -> 0x0178 }
            r5.<init>(r3)     // Catch:{ all -> 0x0178 }
        L_0x0a79:
            boolean r3 = r5.hasNext()     // Catch:{ all -> 0x0178 }
            if (r3 == 0) goto L_0x0a8c
            java.lang.String r3 = r5.next()     // Catch:{ all -> 0x0178 }
            boolean r3 = r8.equals(r3)     // Catch:{ all -> 0x0178 }
            if (r3 == 0) goto L_0x0a79
        L_0x0a89:
            r31 = 1
            goto L_0x0aca
        L_0x0a8c:
            com.google.android.gms.measurement.internal.zzif r3 = r49.zzr()     // Catch:{ all -> 0x0178 }
            java.lang.String r5 = r4.zza     // Catch:{ all -> 0x0178 }
            java.lang.String r6 = r4.zzb     // Catch:{ all -> 0x0178 }
            boolean r3 = r3.zzw(r5, r6)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzaw r38 = r49.zzj()     // Catch:{ all -> 0x0178 }
            long r39 = r49.zza()     // Catch:{ all -> 0x0178 }
            r47 = 0
            r48 = 0
            r42 = 0
            r43 = 0
            r44 = 0
            r45 = 0
            r46 = 0
            r41 = r5
            com.google.android.gms.measurement.internal.zzas r6 = r38.zzo(r39, r41, r42, r43, r44, r45, r46, r47, r48)     // Catch:{ all -> 0x0178 }
            if (r3 == 0) goto L_0x0ac8
            long r6 = r6.zze     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzam r3 = r49.zzi()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzgg r8 = com.google.android.gms.measurement.internal.zzgi.zzo     // Catch:{ all -> 0x0178 }
            int r3 = r3.zzh(r5, r8)     // Catch:{ all -> 0x0178 }
            long r8 = (long) r3     // Catch:{ all -> 0x0178 }
            int r3 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r3 >= 0) goto L_0x0ac8
            goto L_0x0a89
        L_0x0ac8:
            r31 = 0
        L_0x0aca:
            r2.zzg()     // Catch:{ all -> 0x0178 }
            r2.zzav()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)     // Catch:{ all -> 0x0178 }
            java.lang.String r3 = r4.zza     // Catch:{ all -> 0x0178 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r3)     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzpv r5 = r2.zzg     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzqa r5 = r5.zzA()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.internal.measurement.zzhm r5 = r5.zzm(r4)     // Catch:{ all -> 0x0178 }
            byte[] r5 = r5.zzcd()     // Catch:{ all -> 0x0178 }
            android.content.ContentValues r6 = new android.content.ContentValues     // Catch:{ all -> 0x0178 }
            r6.<init>()     // Catch:{ all -> 0x0178 }
            r6.put(r13, r3)     // Catch:{ all -> 0x0178 }
            java.lang.String r7 = "name"
            java.lang.String r8 = r4.zzb     // Catch:{ all -> 0x0178 }
            r6.put(r7, r8)     // Catch:{ all -> 0x0178 }
            java.lang.String r7 = "timestamp"
            long r8 = r4.zzd     // Catch:{ all -> 0x0178 }
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x0178 }
            r6.put(r7, r8)     // Catch:{ all -> 0x0178 }
            java.lang.Long r7 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x0178 }
            r6.put(r14, r7)     // Catch:{ all -> 0x0178 }
            java.lang.String r7 = "data"
            r6.put(r7, r5)     // Catch:{ all -> 0x0178 }
            java.lang.String r5 = "realtime"
            java.lang.Integer r7 = java.lang.Integer.valueOf(r31)     // Catch:{ all -> 0x0178 }
            r6.put(r5, r7)     // Catch:{ all -> 0x0178 }
            android.database.sqlite.SQLiteDatabase r5 = r2.zzj()     // Catch:{ SQLiteException -> 0x0b3a }
            r7 = r37
            r8 = 0
            long r5 = r5.insert(r7, r8, r6)     // Catch:{ SQLiteException -> 0x0b3a }
            r7 = -1
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 != 0) goto L_0x0b3d
            com.google.android.gms.measurement.internal.zzio r5 = r2.zzu     // Catch:{ SQLiteException -> 0x0b3a }
            com.google.android.gms.measurement.internal.zzhe r5 = r5.zzaW()     // Catch:{ SQLiteException -> 0x0b3a }
            com.google.android.gms.measurement.internal.zzhc r5 = r5.zze()     // Catch:{ SQLiteException -> 0x0b3a }
            java.lang.String r6 = "Failed to insert raw event (got -1). appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzhe.zzn(r3)     // Catch:{ SQLiteException -> 0x0b3a }
            r5.zzb(r6, r3)     // Catch:{ SQLiteException -> 0x0b3a }
            goto L_0x0b8a
        L_0x0b3a:
            r0 = move-exception
            r3 = r0
            goto L_0x0b42
        L_0x0b3d:
            r5 = 0
            r1.zza = r5     // Catch:{ all -> 0x0178 }
            goto L_0x0b8a
        L_0x0b42:
            com.google.android.gms.measurement.internal.zzio r2 = r2.zzu     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzhe r2 = r2.zzaW()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zze()     // Catch:{ all -> 0x0178 }
            java.lang.String r5 = "Error storing raw event. appId"
            java.lang.String r4 = r4.zza     // Catch:{ all -> 0x0178 }
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzhe.zzn(r4)     // Catch:{ all -> 0x0178 }
            r2.zzc(r5, r4, r3)     // Catch:{ all -> 0x0178 }
            goto L_0x0b8a
        L_0x0b58:
            r0 = move-exception
            r2 = r0
            goto L_0x0b75
        L_0x0b5b:
            r0 = move-exception
            r4 = r0
            com.google.android.gms.measurement.internal.zzio r2 = r2.zzu     // Catch:{ IOException -> 0x0b58 }
            com.google.android.gms.measurement.internal.zzhe r2 = r2.zzaW()     // Catch:{ IOException -> 0x0b58 }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zze()     // Catch:{ IOException -> 0x0b58 }
            java.lang.String r6 = "Error storing raw event metadata. appId"
            java.lang.String r5 = r5.zzF()     // Catch:{ IOException -> 0x0b58 }
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzhe.zzn(r5)     // Catch:{ IOException -> 0x0b58 }
            r2.zzc(r6, r5, r4)     // Catch:{ IOException -> 0x0b58 }
            throw r4     // Catch:{ IOException -> 0x0b58 }
        L_0x0b75:
            com.google.android.gms.measurement.internal.zzhe r4 = r49.zzaW()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzhc r4 = r4.zze()     // Catch:{ all -> 0x0178 }
            java.lang.String r5 = "Data loss. Failed to insert raw event metadata. appId"
            java.lang.String r3 = r3.zzaF()     // Catch:{ all -> 0x0178 }
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzhe.zzn(r3)     // Catch:{ all -> 0x0178 }
            r4.zzc(r5, r3, r2)     // Catch:{ all -> 0x0178 }
        L_0x0b8a:
            com.google.android.gms.measurement.internal.zzaw r2 = r49.zzj()     // Catch:{ all -> 0x0178 }
            r2.zzS()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzaw r2 = r49.zzj()
            r2.zzL()
            r49.zzaL()
            com.google.android.gms.measurement.internal.zzhe r2 = r49.zzaW()
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zzj()
            long r3 = java.lang.System.nanoTime()
            long r3 = r3 - r25
            r5 = 500000(0x7a120, double:2.47033E-318)
            long r3 = r3 + r5
            r5 = 1000000(0xf4240, double:4.940656E-318)
            long r3 = r3 / r5
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            java.lang.String r4 = "Background event processing time, ms"
            r2.zzb(r4, r3)
            return
        L_0x0bbb:
            com.google.android.gms.measurement.internal.zzaw r3 = r49.zzj()
            r3.zzL()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzpv.zzax(com.google.android.gms.measurement.internal.zzbh, com.google.android.gms.measurement.internal.zzr):void");
    }

    /* access modifiers changed from: package-private */
    public final boolean zzay(String str, String str2) {
        zzh zzl2 = zzj().zzl(str);
        if (zzl2 == null || !zzB().zzak(str, zzl2.zzM())) {
            zzpt zzpt = (zzpt) this.zzF.get(str2);
            if (zzpt == null) {
                return true;
            }
            return zzpt.zzc();
        }
        this.zzF.remove(str2);
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzaz() {
        zzaX().zzg();
        FileLock fileLock = this.zzx;
        if (fileLock == null || !fileLock.isValid()) {
            this.zze.zzu.zzf();
            File filesDir = this.zzn.zzaT().getFilesDir();
            zzbx.zza();
            int i = zzcc.zzb;
            try {
                FileChannel channel = new RandomAccessFile(new File(new File(filesDir, "google_app_measurement.db").getPath()), "rw").getChannel();
                this.zzy = channel;
                FileLock tryLock = channel.tryLock();
                this.zzx = tryLock;
                if (tryLock != null) {
                    zzaW().zzj().zza("Storage concurrent access okay");
                    return true;
                }
                zzaW().zze().zza("Storage concurrent data access panic");
                return false;
            } catch (FileNotFoundException e) {
                zzaW().zze().zzb("Failed to acquire storage lock", e);
                return false;
            } catch (IOException e2) {
                zzaW().zze().zzb("Failed to access storage lock file", e2);
                return false;
            } catch (OverlappingFileLockException e3) {
                zzaW().zzk().zzb("Storage lock already acquired", e3);
                return false;
            }
        } else {
            zzaW().zzj().zza("Storage concurrent access okay");
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public final Bundle zzd(String str) {
        int i;
        String str2;
        zzaX().zzg();
        zzM();
        if (zzr().zzi(str) == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        zzjx zzu2 = zzu(str);
        bundle.putAll(zzu2.zzc());
        bundle.putAll(zzl(str, zzm(str), zzu2, new zzao()).zzb());
        zzqd zzy2 = zzj().zzy(str, "_npa");
        if (zzy2 != null) {
            i = zzy2.zze.equals(1L);
        } else {
            i = zzaC(str, new zzao());
        }
        if (1 != i) {
            str2 = "granted";
        } else {
            str2 = "denied";
        }
        bundle.putString("ad_personalization", str2);
        return bundle;
    }

    /* access modifiers changed from: package-private */
    public final Bundle zzf(String str, zzbh zzbh) {
        Bundle bundle = new Bundle();
        bundle.putLong(NotificationMessage.NOTIF_KEY_SID, zzbh.zzb.zze(NotificationMessage.NOTIF_KEY_SID).longValue());
        zzqd zzy2 = zzj().zzy(str, "_sno");
        if (zzy2 != null) {
            Object obj = zzy2.zze;
            if (obj instanceof Long) {
                bundle.putLong("_sno", ((Long) obj).longValue());
            }
        }
        return bundle;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01f0, code lost:
        if (r11 != false) goto L_0x01f4;
     */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x013c  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0147  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0152  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x015e  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0173  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0199  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x019f  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x01ca  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x01e5  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x01f0  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x01f3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzh zzg(com.google.android.gms.measurement.internal.zzr r14) {
        /*
            r13 = this;
            com.google.android.gms.measurement.internal.zzil r0 = r13.zzaX()
            r0.zzg()
            r13.zzM()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r14)
            java.lang.String r2 = r14.zza
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r2)
            java.lang.String r0 = r14.zzv
            boolean r1 = r0.isEmpty()
            r8 = 0
            if (r1 != 0) goto L_0x0025
            java.util.Map r1 = r13.zzE
            com.google.android.gms.measurement.internal.zzps r3 = new com.google.android.gms.measurement.internal.zzps
            r3.<init>(r13, r0)
            r1.put(r2, r3)
        L_0x0025:
            com.google.android.gms.measurement.internal.zzaw r0 = r13.zzj()
            com.google.android.gms.measurement.internal.zzh r0 = r0.zzl(r2)
            com.google.android.gms.measurement.internal.zzjx r1 = r13.zzu(r2)
            java.lang.String r3 = r14.zzu
            r4 = 100
            com.google.android.gms.measurement.internal.zzjx r3 = com.google.android.gms.measurement.internal.zzjx.zzk(r3, r4)
            com.google.android.gms.measurement.internal.zzjx r1 = r1.zzl(r3)
            com.google.android.gms.measurement.internal.zzjw r3 = com.google.android.gms.measurement.internal.zzjw.AD_STORAGE
            boolean r4 = r1.zzr(r3)
            if (r4 == 0) goto L_0x004e
            com.google.android.gms.measurement.internal.zzoa r4 = r13.zzk
            boolean r5 = r14.zzn
            java.lang.String r4 = r4.zzf(r2, r5)
            goto L_0x0050
        L_0x004e:
            java.lang.String r4 = ""
        L_0x0050:
            r9 = 1
            r10 = 0
            if (r0 != 0) goto L_0x0077
            com.google.android.gms.measurement.internal.zzio r0 = r13.zzn
            com.google.android.gms.measurement.internal.zzh r5 = new com.google.android.gms.measurement.internal.zzh
            r5.<init>(r0, r2)
            com.google.android.gms.measurement.internal.zzjw r0 = com.google.android.gms.measurement.internal.zzjw.ANALYTICS_STORAGE
            boolean r0 = r1.zzr(r0)
            if (r0 == 0) goto L_0x006a
            java.lang.String r0 = r13.zzC(r1)
            r5.zzV(r0)
        L_0x006a:
            boolean r0 = r1.zzr(r3)
            if (r0 == 0) goto L_0x0073
            r5.zzax(r4)
        L_0x0073:
            r0 = r5
        L_0x0074:
            r11 = r10
            goto L_0x012a
        L_0x0077:
            boolean r3 = r1.zzr(r3)
            if (r3 == 0) goto L_0x010f
            if (r4 == 0) goto L_0x010f
            java.lang.String r3 = r0.zzJ()
            boolean r3 = r4.equals(r3)
            if (r3 != 0) goto L_0x010f
            java.lang.String r3 = r0.zzJ()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            r0.zzax(r4)
            boolean r4 = r14.zzn
            if (r4 == 0) goto L_0x00f4
            com.google.android.gms.measurement.internal.zzoa r4 = r13.zzk
            android.util.Pair r4 = r4.zzd(r2, r1)
            java.lang.Object r4 = r4.first
            java.lang.String r5 = "00000000-0000-0000-0000-000000000000"
            boolean r4 = r5.equals(r4)
            if (r4 != 0) goto L_0x00f4
            if (r3 != 0) goto L_0x00f4
            com.google.android.gms.measurement.internal.zzjw r3 = com.google.android.gms.measurement.internal.zzjw.ANALYTICS_STORAGE
            boolean r3 = r1.zzr(r3)
            if (r3 == 0) goto L_0x00bb
            java.lang.String r1 = r13.zzC(r1)
            r0.zzV(r1)
            r11 = r10
            goto L_0x00bc
        L_0x00bb:
            r11 = r9
        L_0x00bc:
            com.google.android.gms.measurement.internal.zzaw r1 = r13.zzj()
            java.lang.String r3 = "_id"
            com.google.android.gms.measurement.internal.zzqd r1 = r1.zzy(r2, r3)
            if (r1 == 0) goto L_0x012a
            com.google.android.gms.measurement.internal.zzaw r1 = r13.zzj()
            java.lang.String r3 = "_lair"
            com.google.android.gms.measurement.internal.zzqd r1 = r1.zzy(r2, r3)
            if (r1 != 0) goto L_0x012a
            com.google.android.gms.common.util.Clock r1 = r13.zzaU()
            long r5 = r1.currentTimeMillis()
            com.google.android.gms.measurement.internal.zzqd r12 = new com.google.android.gms.measurement.internal.zzqd
            r3 = 1
            java.lang.Long r7 = java.lang.Long.valueOf(r3)
            java.lang.String r3 = "auto"
            java.lang.String r4 = "_lair"
            r1 = r12
            r1.<init>(r2, r3, r4, r5, r7)
            com.google.android.gms.measurement.internal.zzaw r1 = r13.zzj()
            r1.zzai(r12)
            goto L_0x012a
        L_0x00f4:
            java.lang.String r2 = r0.zzD()
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 == 0) goto L_0x0074
            com.google.android.gms.measurement.internal.zzjw r2 = com.google.android.gms.measurement.internal.zzjw.ANALYTICS_STORAGE
            boolean r2 = r1.zzr(r2)
            if (r2 == 0) goto L_0x0074
            java.lang.String r1 = r13.zzC(r1)
            r0.zzV(r1)
            goto L_0x0074
        L_0x010f:
            java.lang.String r2 = r0.zzD()
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 == 0) goto L_0x0074
            com.google.android.gms.measurement.internal.zzjw r2 = com.google.android.gms.measurement.internal.zzjw.ANALYTICS_STORAGE
            boolean r2 = r1.zzr(r2)
            if (r2 == 0) goto L_0x0074
            java.lang.String r1 = r13.zzC(r1)
            r0.zzV(r1)
            goto L_0x0074
        L_0x012a:
            java.lang.String r1 = r14.zzb
            r0.zzao(r1)
            java.lang.String r1 = r14.zzp
            r0.zzS(r1)
            java.lang.String r1 = r14.zzk
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L_0x013f
            r0.zzan(r1)
        L_0x013f:
            long r1 = r14.zze
            r3 = 0
            int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r3 == 0) goto L_0x014a
            r0.zzap(r1)
        L_0x014a:
            java.lang.String r1 = r14.zzc
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L_0x0155
            r0.zzX(r1)
        L_0x0155:
            long r1 = r14.zzj
            r0.zzY(r1)
            java.lang.String r1 = r14.zzd
            if (r1 == 0) goto L_0x0161
            r0.zzW(r1)
        L_0x0161:
            long r1 = r14.zzf
            r0.zzaj(r1)
            boolean r1 = r14.zzh
            r0.zzav(r1)
            java.lang.String r1 = r14.zzg
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L_0x0176
            r0.zzaq(r1)
        L_0x0176:
            boolean r1 = r14.zzn
            r0.zzU(r1)
            java.lang.Boolean r1 = r14.zzq
            r0.zzaw(r1)
            long r1 = r14.zzr
            r0.zzal(r1)
            java.lang.String r1 = r14.zzw
            r0.zzaA(r1)
            com.google.android.gms.internal.measurement.zzpn.zzb()
            com.google.android.gms.measurement.internal.zzam r1 = r13.zzi()
            com.google.android.gms.measurement.internal.zzgg r2 = com.google.android.gms.measurement.internal.zzgi.zzaK
            boolean r1 = r1.zzx(r8, r2)
            if (r1 == 0) goto L_0x019f
            java.util.List r1 = r14.zzs
            r0.zzay(r1)
            goto L_0x01b1
        L_0x019f:
            com.google.android.gms.internal.measurement.zzpn.zzb()
            com.google.android.gms.measurement.internal.zzam r1 = r13.zzi()
            com.google.android.gms.measurement.internal.zzgg r2 = com.google.android.gms.measurement.internal.zzgi.zzaJ
            boolean r1 = r1.zzx(r8, r2)
            if (r1 == 0) goto L_0x01b1
            r0.zzay(r8)
        L_0x01b1:
            boolean r1 = r14.zzx
            r0.zzaD(r1)
            java.lang.String r1 = r14.zzD
            r0.zzaC(r1)
            com.google.android.gms.internal.measurement.zzqr.zzb()
            com.google.android.gms.measurement.internal.zzam r1 = r13.zzi()
            com.google.android.gms.measurement.internal.zzgg r2 = com.google.android.gms.measurement.internal.zzgi.zzaV
            boolean r1 = r1.zzx(r8, r2)
            if (r1 == 0) goto L_0x01cf
            int r1 = r14.zzB
            r0.zzT(r1)
        L_0x01cf:
            long r1 = r14.zzy
            r0.zzaE(r1)
            java.lang.String r1 = r14.zzE
            r0.zzaz(r1)
            com.google.android.gms.measurement.internal.zzam r1 = r13.zzi()
            com.google.android.gms.measurement.internal.zzgg r2 = com.google.android.gms.measurement.internal.zzgi.zzaP
            boolean r1 = r1.zzx(r8, r2)
            if (r1 == 0) goto L_0x01ea
            int r14 = r14.zzG
            r0.zzaa(r14)
        L_0x01ea:
            boolean r14 = r0.zzaK()
            if (r14 != 0) goto L_0x01f3
            if (r11 == 0) goto L_0x01fb
            goto L_0x01f4
        L_0x01f3:
            r9 = r11
        L_0x01f4:
            com.google.android.gms.measurement.internal.zzaw r14 = r13.zzj()
            r14.zzT(r0, r9, r10)
        L_0x01fb:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzpv.zzg(com.google.android.gms.measurement.internal.zzr):com.google.android.gms.measurement.internal.zzh");
    }

    public final zzae zzh() {
        zzae zzae = this.zzh;
        zzaR(zzae);
        return zzae;
    }

    public final zzam zzi() {
        return ((zzio) Preconditions.checkNotNull(this.zzn)).zzf();
    }

    public final zzaw zzj() {
        zzaw zzaw = this.zze;
        zzaR(zzaw);
        return zzaw;
    }

    /* access modifiers changed from: package-private */
    public final zzba zzl(String str, zzba zzba, zzjx zzjx, zzao zzao) {
        zzju zzju;
        zzjw zzjw;
        zzju zzf2;
        int i = 90;
        if (zzr().zzi(str) == null) {
            if (zzba.zzf() == zzju.DENIED) {
                i = zzba.zza();
                zzao.zzc(zzjw.AD_USER_DATA, i);
            } else {
                zzao.zzd(zzjw.AD_USER_DATA, zzan.FAILSAFE);
            }
            return new zzba(Boolean.FALSE, i, Boolean.TRUE, "-");
        }
        zzju zzf3 = zzba.zzf();
        zzju zzju2 = zzju.GRANTED;
        if (zzf3 == zzju2 || zzf3 == (zzju = zzju.DENIED)) {
            i = zzba.zza();
            zzao.zzc(zzjw.AD_USER_DATA, i);
        } else if (zzf3 != zzju.POLICY || (zzf2 = this.zzc.zzf(str, zzjw)) == zzju.UNINITIALIZED) {
            zzif zzif = this.zzc;
            zzjw zzjw2 = zzjw.AD_USER_DATA;
            zzjw zzh2 = zzif.zzh(str, zzjw2);
            zzju zze2 = zzjx.zze();
            boolean z = zze2 == zzju2 || zze2 == zzju;
            if (zzh2 != zzjw.AD_STORAGE || !z) {
                zzao.zzd(zzjw2, zzan.REMOTE_DEFAULT);
                zzf3 = true != zzif.zzu(str, zzjw2) ? zzju : zzju2;
            } else {
                zzao.zzd(zzjw2, zzan.REMOTE_DELEGATION);
                zzf3 = zze2;
            }
        } else {
            zzao.zzd((zzjw = zzjw.AD_USER_DATA), zzan.REMOTE_ENFORCED_DEFAULT);
            zzf3 = zzf2;
        }
        boolean zzv2 = this.zzc.zzv(str);
        SortedSet zzp2 = zzr().zzp(str);
        if (zzf3 == zzju.DENIED || zzp2.isEmpty()) {
            return new zzba(Boolean.FALSE, i, Boolean.valueOf(zzv2), "-");
        }
        Boolean bool = Boolean.TRUE;
        Boolean valueOf = Boolean.valueOf(zzv2);
        String str2 = "";
        if (zzv2) {
            str2 = TextUtils.join(str2, zzp2);
        }
        return new zzba(bool, i, valueOf, str2);
    }

    /* access modifiers changed from: package-private */
    public final zzba zzm(String str) {
        zzaX().zzg();
        zzM();
        Map map = this.zzD;
        zzba zzba = (zzba) map.get(str);
        if (zzba != null) {
            return zzba;
        }
        zzba zzq2 = zzj().zzq(str);
        map.put(str, zzq2);
        return zzq2;
    }

    public final zzgx zzo() {
        return this.zzn.zzj();
    }

    public final zzhk zzp() {
        zzhk zzhk = this.zzd;
        zzaR(zzhk);
        return zzhk;
    }

    public final zzhm zzq() {
        zzhm zzhm = this.zzf;
        if (zzhm != null) {
            return zzhm;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    public final zzif zzr() {
        zzif zzif = this.zzc;
        zzaR(zzif);
        return zzif;
    }

    /* access modifiers changed from: package-private */
    public final zzio zzt() {
        return this.zzn;
    }

    /* access modifiers changed from: package-private */
    public final zzjx zzu(String str) {
        zzjx zzjx = zzjx.zza;
        zzaX().zzg();
        zzM();
        zzjx zzjx2 = (zzjx) this.zzC.get(str);
        if (zzjx2 == null) {
            zzjx2 = zzj().zzu(str);
            if (zzjx2 == null) {
                zzjx2 = zzjx.zza;
            }
            zzaq(str, zzjx2);
        }
        return zzjx2;
    }

    public final zzmc zzv() {
        zzmc zzmc = this.zzj;
        zzaR(zzmc);
        return zzmc;
    }

    public final zzoa zzw() {
        return this.zzk;
    }

    public final zzoy zzx() {
        zzoy zzoy = this.zzg;
        zzaR(zzoy);
        return zzoy;
    }

    public final zzpi zzy() {
        return this.zzl;
    }
}
