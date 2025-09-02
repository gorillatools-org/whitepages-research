package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.app.BroadcastOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.internal.measurement.zzdh;
import com.google.android.gms.internal.measurement.zzki;
import com.google.firebase.messaging.Constants;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzio implements zzjs {
    private static volatile zzio zzb;
    private boolean zzA = false;
    private Boolean zzB;
    private long zzC;
    private volatile Boolean zzD;
    private volatile boolean zzE;
    private int zzF;
    private int zzG;
    private final AtomicInteger zzH = new AtomicInteger(0);
    final long zza;
    private final Context zzc;
    private final String zzd;
    private final String zze;
    private final String zzf;
    private final boolean zzg;
    private final zzaf zzh;
    private final zzam zzi;
    private final zzht zzj;
    private final zzhe zzk;
    private final zzil zzl;
    private final zzop zzm;
    private final zzqf zzn;
    private final zzgx zzo;
    private final Clock zzp;
    private final zzmo zzq;
    private final zzlw zzr;
    private final zzd zzs;
    private final zzmb zzt;
    private final String zzu;
    private zzgv zzv;
    private zzny zzw;
    private zzbb zzx;
    private zzgs zzy;
    private zzmd zzz;

    zzio(zzke zzke) {
        long j;
        boolean z = false;
        Preconditions.checkNotNull(zzke);
        Context context = zzke.zza;
        zzaf zzaf = new zzaf(context);
        this.zzh = zzaf;
        zzgf.zza = zzaf;
        this.zzc = context;
        this.zzd = zzke.zzb;
        this.zze = zzke.zzc;
        this.zzf = zzke.zzd;
        this.zzg = zzke.zzh;
        this.zzD = zzke.zze;
        this.zzu = zzke.zzj;
        this.zzE = true;
        zzki.zzd(context);
        Clock instance = DefaultClock.getInstance();
        this.zzp = instance;
        Long l = zzke.zzi;
        if (l != null) {
            j = l.longValue();
        } else {
            j = instance.currentTimeMillis();
        }
        this.zza = j;
        this.zzi = new zzam(this);
        zzht zzht = new zzht(this);
        zzht.zzw();
        this.zzj = zzht;
        zzhe zzhe = new zzhe(this);
        zzhe.zzw();
        this.zzk = zzhe;
        zzqf zzqf = new zzqf(this);
        zzqf.zzw();
        this.zzn = zzqf;
        this.zzo = new zzgx(new zzkd(zzke, this));
        this.zzs = new zzd(this);
        zzmo zzmo = new zzmo(this);
        zzmo.zzb();
        this.zzq = zzmo;
        zzlw zzlw = new zzlw(this);
        zzlw.zzb();
        this.zzr = zzlw;
        zzop zzop = new zzop(this);
        zzop.zzb();
        this.zzm = zzop;
        zzmb zzmb = new zzmb(this);
        zzmb.zzw();
        this.zzt = zzmb;
        zzil zzil = new zzil(this);
        zzil.zzw();
        this.zzl = zzil;
        zzdh zzdh = zzke.zzg;
        z = (zzdh == null || zzdh.zzb == 0) ? true : z;
        if (context.getApplicationContext() instanceof Application) {
            zzS(zzlw);
            if (zzlw.zzu.zzc.getApplicationContext() instanceof Application) {
                Application application = (Application) zzlw.zzu.zzc.getApplicationContext();
                if (zzlw.zza == null) {
                    zzlw.zza = new zzlv(zzlw);
                }
                if (z) {
                    application.unregisterActivityLifecycleCallbacks(zzlw.zza);
                    application.registerActivityLifecycleCallbacks(zzlw.zza);
                    zzhe zzhe2 = zzlw.zzu.zzk;
                    zzT(zzhe2);
                    zzhe2.zzj().zza("Registered activity lifecycle callback");
                }
            }
        } else {
            zzT(zzhe);
            zzhe.zzk().zza("Application context is not an Application");
        }
        zzil.zzq(new zzin(this, zzke));
    }

    public static /* synthetic */ void zzB(zzio zzio, String str, int i, Throwable th, byte[] bArr, Map map) {
        int i2;
        zzio zzio2 = zzio;
        int i3 = i;
        Throwable th2 = th;
        byte[] bArr2 = bArr;
        if (i3 == 200 || i3 == 204) {
            i2 = i3;
        } else {
            i2 = 304;
            if (i3 != 304) {
                i2 = i3;
                zzhe zzhe = zzio2.zzk;
                zzT(zzhe);
                zzhe.zzk().zzc("Network Request for Deferred Deep Link failed. response, exception", Integer.valueOf(i2), th2);
            }
        }
        if (th2 == null) {
            zzht zzht = zzio2.zzj;
            zzR(zzht);
            zzht.zzo.zza(true);
            if (bArr2 == null || bArr2.length == 0) {
                zzhe zzhe2 = zzio2.zzk;
                zzT(zzhe2);
                zzhe2.zzd().zza("Deferred Deep Link response empty.");
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(new String(bArr2));
                String optString = jSONObject.optString("deeplink", "");
                if (TextUtils.isEmpty(optString)) {
                    zzhe zzhe3 = zzio2.zzk;
                    zzT(zzhe3);
                    zzhe3.zzd().zza("Deferred Deep Link is empty.");
                    return;
                }
                String optString2 = jSONObject.optString("gclid", "");
                String optString3 = jSONObject.optString("gbraid", "");
                String optString4 = jSONObject.optString("gad_source", "");
                double optDouble = jSONObject.optDouble("timestamp", 0.0d);
                Bundle bundle = new Bundle();
                zzqf zzqf = zzio2.zzn;
                zzR(zzqf);
                zzio zzio3 = zzqf.zzu;
                if (!TextUtils.isEmpty(optString)) {
                    Context context = zzio3.zzc;
                    String str2 = "timestamp";
                    double d = optDouble;
                    List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(optString)), 0);
                    if (queryIntentActivities != null && !queryIntentActivities.isEmpty()) {
                        if (!TextUtils.isEmpty(optString3)) {
                            bundle.putString("gbraid", optString3);
                        }
                        if (!TextUtils.isEmpty(optString4)) {
                            bundle.putString("gad_source", optString4);
                        }
                        bundle.putString("gclid", optString2);
                        bundle.putString("_cis", "ddp");
                        zzio2.zzr.zzR("auto", Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, bundle);
                        zzR(zzqf);
                        if (!TextUtils.isEmpty(optString)) {
                            try {
                                SharedPreferences.Editor edit = context.getSharedPreferences("google.analytics.deferred.deeplink.prefs", 0).edit();
                                edit.putString("deeplink", optString);
                                edit.putLong(str2, Double.doubleToRawLongBits(d));
                                if (edit.commit()) {
                                    Intent intent = new Intent("android.google.analytics.action.DEEPLINK_ACTION");
                                    Context context2 = zzqf.zzu.zzc;
                                    if (Build.VERSION.SDK_INT < 34) {
                                        context2.sendBroadcast(intent);
                                        return;
                                    } else {
                                        context2.sendBroadcast(intent, (String) null, BroadcastOptions.makeBasic().setShareIdentityEnabled(true).toBundle());
                                        return;
                                    }
                                } else {
                                    return;
                                }
                            } catch (RuntimeException e) {
                                zzhe zzhe4 = zzqf.zzu.zzk;
                                zzT(zzhe4);
                                zzhe4.zze().zzb("Failed to persist Deferred Deep Link. exception", e);
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                }
                zzhe zzhe5 = zzio2.zzk;
                zzT(zzhe5);
                zzhe5.zzk().zzd("Deferred Deep Link validation failed. gclid, gbraid, deep link", optString2, optString3, optString);
                return;
            } catch (JSONException e2) {
                zzhe zzhe6 = zzio2.zzk;
                zzT(zzhe6);
                zzhe6.zze().zzb("Failed to parse the Deferred Deep Link response. exception", e2);
                return;
            }
        }
        zzhe zzhe7 = zzio2.zzk;
        zzT(zzhe7);
        zzhe7.zzk().zzc("Network Request for Deferred Deep Link failed. response, exception", Integer.valueOf(i2), th2);
    }

    static /* synthetic */ void zzC(zzio zzio, zzke zzke) {
        zzil zzil = zzio.zzl;
        zzT(zzil);
        zzil.zzg();
        zzam zzam = zzio.zzi;
        zzam.zzq();
        zzbb zzbb = new zzbb(zzio);
        zzbb.zzw();
        zzio.zzx = zzbb;
        zzdh zzdh = zzke.zzg;
        zzgs zzgs = new zzgs(zzio, zzke.zzf, zzdh == null ? 0 : zzdh.zza);
        zzgs.zzb();
        zzio.zzy = zzgs;
        zzgv zzgv = new zzgv(zzio);
        zzgv.zzb();
        zzio.zzv = zzgv;
        zzny zzny = new zzny(zzio);
        zzny.zzb();
        zzio.zzw = zzny;
        zzqf zzqf = zzio.zzn;
        zzqf.zzx();
        zzio.zzj.zzx();
        zzio.zzy.zzc();
        zzmd zzmd = new zzmd(zzio);
        zzmd.zzb();
        zzio.zzz = zzmd;
        zzmd.zzc();
        zzhe zzhe = zzio.zzk;
        zzT(zzhe);
        zzhc zzi2 = zzhe.zzi();
        zzam.zzj();
        zzi2.zzb("App measurement initialized, version", 119002L);
        zzT(zzhe);
        zzhe.zzi().zza("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        String zzm2 = zzgs.zzm();
        if (TextUtils.isEmpty(zzio.zzd)) {
            zzR(zzqf);
            if (zzqf.zzak(zzm2, zzam.zzs())) {
                zzT(zzhe);
                zzhe.zzi().zza("Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.");
            } else {
                zzT(zzhe);
                zzhe.zzi().zza("To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ".concat(String.valueOf(zzm2)));
            }
        }
        zzT(zzhe);
        zzhe.zzd().zza("Debug-level message logging enabled");
        int i = zzio.zzF;
        AtomicInteger atomicInteger = zzio.zzH;
        if (i != atomicInteger.get()) {
            zzT(zzhe);
            zzhe.zze().zzc("Not all components initialized", Integer.valueOf(zzio.zzF), Integer.valueOf(atomicInteger.get()));
        }
        zzio.zzA = true;
    }

    static final void zzP() {
        throw new IllegalStateException("Unexpected call on client side");
    }

    private static final void zzQ(zzf zzf2) {
        if (zzf2 == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    private static final void zzR(zzjq zzjq) {
        if (zzjq == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    private static final void zzS(zzg zzg2) {
        if (zzg2 == null) {
            throw new IllegalStateException("Component not created");
        } else if (!zzg2.zze()) {
            throw new IllegalStateException("Component not initialized: ".concat(String.valueOf(zzg2.getClass())));
        }
    }

    private static final void zzT(zzjr zzjr) {
        if (zzjr == null) {
            throw new IllegalStateException("Component not created");
        } else if (!zzjr.zzy()) {
            throw new IllegalStateException("Component not initialized: ".concat(String.valueOf(zzjr.getClass())));
        }
    }

    public static zzio zzp(Context context, zzdh zzdh, Long l) {
        Bundle bundle;
        if (zzdh != null && (zzdh.zze == null || zzdh.zzf == null)) {
            zzdh = new zzdh(zzdh.zza, zzdh.zzb, zzdh.zzc, zzdh.zzd, (String) null, (String) null, zzdh.zzg, (String) null);
        }
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzb == null) {
            synchronized (zzio.class) {
                try {
                    if (zzb == null) {
                        zzb = new zzio(new zzke(context, zzdh, l));
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        } else if (!(zzdh == null || (bundle = zzdh.zzg) == null || !bundle.containsKey("dataCollectionDefaultEnabled"))) {
            Preconditions.checkNotNull(zzb);
            zzb.zzD = Boolean.valueOf(bundle.getBoolean("dataCollectionDefaultEnabled"));
        }
        Preconditions.checkNotNull(zzb);
        return zzb;
    }

    public final String zzA() {
        return this.zzu;
    }

    /* access modifiers changed from: package-private */
    public final void zzD() {
        this.zzH.incrementAndGet();
    }

    /* access modifiers changed from: package-private */
    public final void zzE() {
        this.zzF++;
    }

    /* access modifiers changed from: package-private */
    public final void zzF(boolean z) {
        this.zzD = Boolean.valueOf(z);
    }

    public final void zzG(boolean z) {
        zzil zzil = this.zzl;
        zzT(zzil);
        zzil.zzg();
        this.zzE = z;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0119, code lost:
        if (r6.zzt() == false) goto L_0x00ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0038, code lost:
        if (r6.zzan() == false) goto L_0x003a;
     */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x02cb  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x04a7  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x011e  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0142  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x015e  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x016a  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x021d  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0245  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzH(com.google.android.gms.internal.measurement.zzdh r14) {
        /*
            r13 = this;
            com.google.android.gms.measurement.internal.zzil r0 = r13.zzl
            zzT(r0)
            r0.zzg()
            com.google.android.gms.measurement.internal.zzgg r0 = com.google.android.gms.measurement.internal.zzgi.zzaR
            com.google.android.gms.measurement.internal.zzam r1 = r13.zzi
            r2 = 0
            boolean r3 = r1.zzx(r2, r0)
            r4 = 0
            r5 = 1
            if (r3 == 0) goto L_0x0023
            com.google.android.gms.measurement.internal.zzmd r3 = r13.zzs()
            com.google.android.gms.internal.measurement.zzih r3 = r3.zzi()
            com.google.android.gms.internal.measurement.zzih r6 = com.google.android.gms.internal.measurement.zzih.CLIENT_UPLOAD_ELIGIBLE
            if (r3 != r6) goto L_0x0023
            r3 = r5
            goto L_0x0024
        L_0x0023:
            r3 = r4
        L_0x0024:
            com.google.android.gms.internal.measurement.zzqr.zzb()
            com.google.android.gms.measurement.internal.zzgg r6 = com.google.android.gms.measurement.internal.zzgi.zzaW
            boolean r6 = r1.zzx(r2, r6)
            if (r6 == 0) goto L_0x003a
            com.google.android.gms.measurement.internal.zzqf r6 = r13.zzn
            zzR(r6)
            boolean r6 = r6.zzan()
            if (r6 != 0) goto L_0x003d
        L_0x003a:
            if (r3 == 0) goto L_0x008e
            r3 = r5
        L_0x003d:
            com.google.android.gms.measurement.internal.zzqf r6 = r13.zzn
            zzR(r6)
            r6.zzg()
            android.content.IntentFilter r7 = new android.content.IntentFilter
            r7.<init>()
            java.lang.String r8 = "com.google.android.gms.measurement.TRIGGERS_AVAILABLE"
            r7.addAction(r8)
            com.google.android.gms.measurement.internal.zzio r8 = r6.zzu
            com.google.android.gms.measurement.internal.zzam r9 = r8.zzi
            boolean r0 = r9.zzx(r2, r0)
            if (r0 == 0) goto L_0x005e
            java.lang.String r0 = "com.google.android.gms.measurement.BATCHES_AVAILABLE"
            r7.addAction(r0)
        L_0x005e:
            com.google.android.gms.measurement.internal.zzw r0 = new com.google.android.gms.measurement.internal.zzw
            com.google.android.gms.measurement.internal.zzio r6 = r6.zzu
            r0.<init>(r6)
            android.content.Context r6 = r8.zzc
            r9 = 2
            androidx.core.content.ContextCompat.registerReceiver(r6, r0, r7, r9)
            com.google.android.gms.measurement.internal.zzhe r0 = r8.zzk
            zzT(r0)
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzd()
            java.lang.String r6 = "Registered app receiver"
            r0.zza(r6)
            if (r3 == 0) goto L_0x008e
            com.google.android.gms.measurement.internal.zzmd r0 = r13.zzs()
            com.google.android.gms.measurement.internal.zzgg r3 = com.google.android.gms.measurement.internal.zzgi.zzB
            java.lang.Object r3 = r3.zza(r2)
            java.lang.Long r3 = (java.lang.Long) r3
            long r6 = r3.longValue()
            r0.zzj(r6)
        L_0x008e:
            com.google.android.gms.measurement.internal.zzht r0 = r13.zzj
            zzR(r0)
            com.google.android.gms.measurement.internal.zzjx r3 = r0.zzh()
            int r6 = r3.zzb()
            java.lang.String r7 = "google_analytics_default_allow_ad_storage"
            com.google.android.gms.measurement.internal.zzju r7 = r1.zzm(r7, r4)
            java.lang.String r8 = "google_analytics_default_allow_analytics_storage"
            com.google.android.gms.measurement.internal.zzju r8 = r1.zzm(r8, r4)
            com.google.android.gms.measurement.internal.zzju r9 = com.google.android.gms.measurement.internal.zzju.UNINITIALIZED
            r10 = -10
            r11 = 30
            if (r7 != r9) goto L_0x00b1
            if (r8 == r9) goto L_0x00bf
        L_0x00b1:
            zzR(r0)
            boolean r12 = r0.zzq(r10)
            if (r12 == 0) goto L_0x00bf
            com.google.android.gms.measurement.internal.zzjx r6 = com.google.android.gms.measurement.internal.zzjx.zzj(r7, r8, r10)
            goto L_0x011c
        L_0x00bf:
            com.google.android.gms.measurement.internal.zzgs r7 = r13.zzh()
            java.lang.String r7 = r7.zzo()
            boolean r7 = android.text.TextUtils.isEmpty(r7)
            if (r7 != 0) goto L_0x00ec
            if (r6 == 0) goto L_0x00dd
            if (r6 == r11) goto L_0x00dd
            r7 = 10
            if (r6 == r7) goto L_0x00dd
            if (r6 == r11) goto L_0x00dd
            if (r6 == r11) goto L_0x00dd
            r7 = 40
            if (r6 != r7) goto L_0x00ec
        L_0x00dd:
            com.google.android.gms.measurement.internal.zzlw r6 = r13.zzr
            zzS(r6)
            com.google.android.gms.measurement.internal.zzjx r7 = new com.google.android.gms.measurement.internal.zzjx
            r7.<init>(r2, r2, r10)
            r6.zzak(r7, r4)
        L_0x00ea:
            r6 = r2
            goto L_0x011c
        L_0x00ec:
            com.google.android.gms.measurement.internal.zzgg r6 = com.google.android.gms.measurement.internal.zzgi.zzbp
            boolean r6 = r1.zzx(r2, r6)
            if (r6 != 0) goto L_0x00ea
            com.google.android.gms.measurement.internal.zzgs r6 = r13.zzh()
            java.lang.String r6 = r6.zzo()
            boolean r6 = android.text.TextUtils.isEmpty(r6)
            if (r6 == 0) goto L_0x00ea
            if (r14 == 0) goto L_0x00ea
            android.os.Bundle r6 = r14.zzg
            if (r6 == 0) goto L_0x00ea
            zzR(r0)
            boolean r7 = r0.zzq(r11)
            if (r7 == 0) goto L_0x00ea
            com.google.android.gms.measurement.internal.zzjx r6 = com.google.android.gms.measurement.internal.zzjx.zzi(r6, r11)
            boolean r7 = r6.zzt()
            if (r7 != 0) goto L_0x011c
            goto L_0x00ea
        L_0x011c:
            if (r6 == 0) goto L_0x0127
            com.google.android.gms.measurement.internal.zzlw r3 = r13.zzr
            zzS(r3)
            r3.zzak(r6, r5)
            r3 = r6
        L_0x0127:
            com.google.android.gms.measurement.internal.zzlw r6 = r13.zzr
            zzS(r6)
            r6.zzaj(r3)
            zzR(r0)
            com.google.android.gms.measurement.internal.zzba r3 = r0.zzf()
            int r3 = r3.zza()
            java.lang.String r7 = "google_analytics_default_allow_ad_personalization_signals"
            com.google.android.gms.measurement.internal.zzju r7 = r1.zzm(r7, r5)
            if (r7 == r9) goto L_0x0150
            com.google.android.gms.measurement.internal.zzhe r8 = r13.zzk
            zzT(r8)
            com.google.android.gms.measurement.internal.zzhc r8 = r8.zzj()
            java.lang.String r12 = "Default ad personalization consent from Manifest"
            r8.zzb(r12, r7)
        L_0x0150:
            java.lang.String r7 = "google_analytics_default_allow_ad_user_data"
            com.google.android.gms.measurement.internal.zzju r7 = r1.zzm(r7, r5)
            if (r7 == r9) goto L_0x016a
            boolean r8 = com.google.android.gms.measurement.internal.zzjx.zzs(r10, r3)
            if (r8 == 0) goto L_0x016a
            zzS(r6)
            com.google.android.gms.measurement.internal.zzba r14 = com.google.android.gms.measurement.internal.zzba.zzd(r7, r10)
            r6.zzag(r14, r5)
            goto L_0x01e5
        L_0x016a:
            com.google.android.gms.measurement.internal.zzgs r7 = r13.zzh()
            java.lang.String r7 = r7.zzo()
            boolean r7 = android.text.TextUtils.isEmpty(r7)
            if (r7 != 0) goto L_0x0188
            if (r3 == 0) goto L_0x017c
            if (r3 != r11) goto L_0x0188
        L_0x017c:
            zzS(r6)
            com.google.android.gms.measurement.internal.zzba r14 = new com.google.android.gms.measurement.internal.zzba
            r14.<init>((java.lang.Boolean) r2, (int) r10, (java.lang.Boolean) r2, (java.lang.String) r2)
            r6.zzag(r14, r5)
            goto L_0x01e5
        L_0x0188:
            com.google.android.gms.measurement.internal.zzgs r7 = r13.zzh()
            java.lang.String r7 = r7.zzo()
            boolean r7 = android.text.TextUtils.isEmpty(r7)
            if (r7 == 0) goto L_0x01b2
            if (r14 == 0) goto L_0x01b2
            android.os.Bundle r7 = r14.zzg
            if (r7 == 0) goto L_0x01b2
            boolean r3 = com.google.android.gms.measurement.internal.zzjx.zzs(r11, r3)
            if (r3 == 0) goto L_0x01b2
            com.google.android.gms.measurement.internal.zzba r3 = com.google.android.gms.measurement.internal.zzba.zzc(r7, r11)
            boolean r7 = r3.zzk()
            if (r7 == 0) goto L_0x01b2
            zzS(r6)
            r6.zzag(r3, r5)
        L_0x01b2:
            com.google.android.gms.measurement.internal.zzgs r3 = r13.zzh()
            java.lang.String r3 = r3.zzo()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x01e5
            if (r14 == 0) goto L_0x01e5
            android.os.Bundle r3 = r14.zzg
            if (r3 == 0) goto L_0x01e5
            zzR(r0)
            com.google.android.gms.measurement.internal.zzhr r7 = r0.zzh
            java.lang.String r7 = r7.zza()
            if (r7 != 0) goto L_0x01e5
            java.lang.Boolean r3 = com.google.android.gms.measurement.internal.zzba.zzg(r3)
            if (r3 == 0) goto L_0x01e5
            zzS(r6)
            java.lang.String r14 = r14.zze
            java.lang.String r7 = "allow_personalized_ads"
            java.lang.String r3 = r3.toString()
            r6.zzal(r14, r7, r3, r4)
        L_0x01e5:
            java.lang.String r14 = "google_analytics_tcf_data_enabled"
            java.lang.Boolean r14 = r1.zzn(r14)
            if (r14 != 0) goto L_0x01ee
            goto L_0x01f4
        L_0x01ee:
            boolean r14 = r14.booleanValue()
            if (r14 == 0) goto L_0x020e
        L_0x01f4:
            com.google.android.gms.measurement.internal.zzhe r14 = r13.zzk
            zzT(r14)
            com.google.android.gms.measurement.internal.zzhc r14 = r14.zzd()
            java.lang.String r3 = "TCF client enabled."
            r14.zza(r3)
            zzS(r6)
            r6.zzW()
            zzS(r6)
            r6.zzN()
        L_0x020e:
            zzR(r0)
            com.google.android.gms.measurement.internal.zzhp r14 = r0.zzc
            long r7 = r14.zza()
            r9 = 0
            int r3 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r3 != 0) goto L_0x0237
            com.google.android.gms.measurement.internal.zzhe r3 = r13.zzk
            zzT(r3)
            long r7 = r13.zza
            com.google.android.gms.measurement.internal.zzhc r3 = r3.zzj()
            java.lang.Long r9 = java.lang.Long.valueOf(r7)
            java.lang.String r10 = "Persisting first open"
            r3.zzb(r10, r9)
            zzR(r0)
            r14.zzb(r7)
        L_0x0237:
            zzS(r6)
            com.google.android.gms.measurement.internal.zzx r3 = r6.zzb
            r3.zzc()
            boolean r3 = r13.zzM()
            if (r3 != 0) goto L_0x02cb
            boolean r14 = r13.zzJ()
            if (r14 == 0) goto L_0x049a
            com.google.android.gms.measurement.internal.zzqf r14 = r13.zzn
            zzR(r14)
            java.lang.String r0 = "android.permission.INTERNET"
            boolean r0 = r14.zzaj(r0)
            if (r0 != 0) goto L_0x0266
            com.google.android.gms.measurement.internal.zzhe r0 = r13.zzk
            zzT(r0)
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zze()
            java.lang.String r1 = "App is missing INTERNET permission"
            r0.zza(r1)
        L_0x0266:
            zzR(r14)
            java.lang.String r0 = "android.permission.ACCESS_NETWORK_STATE"
            boolean r14 = r14.zzaj(r0)
            if (r14 != 0) goto L_0x027f
            com.google.android.gms.measurement.internal.zzhe r14 = r13.zzk
            zzT(r14)
            com.google.android.gms.measurement.internal.zzhc r14 = r14.zze()
            java.lang.String r0 = "App is missing ACCESS_NETWORK_STATE permission"
            r14.zza(r0)
        L_0x027f:
            android.content.Context r14 = r13.zzc
            com.google.android.gms.common.wrappers.PackageManagerWrapper r0 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r14)
            boolean r0 = r0.isCallerInstantApp()
            if (r0 != 0) goto L_0x02bb
            com.google.android.gms.measurement.internal.zzam r0 = r13.zzi
            boolean r0 = r0.zzC()
            if (r0 != 0) goto L_0x02bb
            boolean r0 = com.google.android.gms.measurement.internal.zzqf.zzar(r14)
            if (r0 != 0) goto L_0x02a7
            com.google.android.gms.measurement.internal.zzhe r0 = r13.zzk
            zzT(r0)
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zze()
            java.lang.String r1 = "AppMeasurementReceiver not registered/enabled"
            r0.zza(r1)
        L_0x02a7:
            boolean r14 = com.google.android.gms.measurement.internal.zzqf.zzat(r14, r4)
            if (r14 != 0) goto L_0x02bb
            com.google.android.gms.measurement.internal.zzhe r14 = r13.zzk
            zzT(r14)
            com.google.android.gms.measurement.internal.zzhc r14 = r14.zze()
            java.lang.String r0 = "AppMeasurementService not registered/enabled"
            r14.zza(r0)
        L_0x02bb:
            com.google.android.gms.measurement.internal.zzhe r14 = r13.zzk
            zzT(r14)
            com.google.android.gms.measurement.internal.zzhc r14 = r14.zze()
            java.lang.String r0 = "Uploading is not possible. App measurement disabled"
            r14.zza(r0)
            goto L_0x049a
        L_0x02cb:
            com.google.android.gms.measurement.internal.zzgs r3 = r13.zzh()
            java.lang.String r3 = r3.zzo()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x02ef
            com.google.android.gms.measurement.internal.zzgg r3 = com.google.android.gms.measurement.internal.zzgi.zzbp
            boolean r3 = r1.zzx(r2, r3)
            if (r3 != 0) goto L_0x03c7
            com.google.android.gms.measurement.internal.zzgs r3 = r13.zzh()
            java.lang.String r3 = r3.zzl()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x03c7
        L_0x02ef:
            com.google.android.gms.measurement.internal.zzgg r3 = com.google.android.gms.measurement.internal.zzgi.zzbp
            boolean r4 = r1.zzx(r2, r3)
            if (r4 == 0) goto L_0x0310
            com.google.android.gms.measurement.internal.zzqf r4 = r13.zzn
            zzR(r4)
            com.google.android.gms.measurement.internal.zzgs r7 = r13.zzh()
            java.lang.String r7 = r7.zzo()
            zzR(r0)
            java.lang.String r8 = r0.zzj()
            boolean r4 = r4.zzav(r7, r8)
            goto L_0x0340
        L_0x0310:
            com.google.android.gms.measurement.internal.zzqf r4 = r13.zzn
            zzR(r4)
            com.google.android.gms.measurement.internal.zzgs r7 = r13.zzh()
            java.lang.String r7 = r7.zzo()
            zzR(r0)
            java.lang.String r8 = r0.zzj()
            com.google.android.gms.measurement.internal.zzgs r9 = r13.zzh()
            java.lang.String r9 = r9.zzl()
            zzR(r0)
            r0.zzg()
            android.content.SharedPreferences r10 = r0.zzb()
            java.lang.String r11 = "admob_app_id"
            java.lang.String r10 = r10.getString(r11, r2)
            boolean r4 = r4.zzaw(r7, r8, r9, r10)
        L_0x0340:
            if (r4 == 0) goto L_0x038e
            com.google.android.gms.measurement.internal.zzhe r4 = r13.zzk
            zzT(r4)
            com.google.android.gms.measurement.internal.zzhc r4 = r4.zzi()
            java.lang.String r7 = "Rechecking which service to use due to a GMP App Id change"
            r4.zza(r7)
            zzR(r0)
            r0.zzg()
            java.lang.Boolean r4 = r0.zzi()
            android.content.SharedPreferences r7 = r0.zzb()
            android.content.SharedPreferences$Editor r7 = r7.edit()
            r7.clear()
            r7.apply()
            if (r4 == 0) goto L_0x036d
            r0.zzm(r4)
        L_0x036d:
            com.google.android.gms.measurement.internal.zzgv r4 = r13.zzi()
            r4.zzj()
            com.google.android.gms.measurement.internal.zzny r4 = r13.zzw
            r4.zzC()
            com.google.android.gms.measurement.internal.zzny r4 = r13.zzw
            r4.zzB()
            zzR(r0)
            long r7 = r13.zza
            r14.zzb(r7)
            zzR(r0)
            com.google.android.gms.measurement.internal.zzhr r14 = r0.zze
            r14.zzb(r2)
        L_0x038e:
            zzR(r0)
            com.google.android.gms.measurement.internal.zzgs r14 = r13.zzh()
            java.lang.String r14 = r14.zzo()
            r0.zzg()
            android.content.SharedPreferences r4 = r0.zzb()
            android.content.SharedPreferences$Editor r4 = r4.edit()
            java.lang.String r7 = "gmp_app_id"
            r4.putString(r7, r14)
            r4.apply()
            boolean r14 = r1.zzx(r2, r3)
            if (r14 == 0) goto L_0x03b9
            zzR(r0)
            r0.zzl(r2)
            goto L_0x03c7
        L_0x03b9:
            zzR(r0)
            com.google.android.gms.measurement.internal.zzgs r14 = r13.zzh()
            java.lang.String r14 = r14.zzl()
            r0.zzl(r14)
        L_0x03c7:
            zzR(r0)
            com.google.android.gms.measurement.internal.zzjx r14 = r0.zzh()
            com.google.android.gms.measurement.internal.zzjw r1 = com.google.android.gms.measurement.internal.zzjw.ANALYTICS_STORAGE
            boolean r14 = r14.zzr(r1)
            if (r14 != 0) goto L_0x03de
            zzR(r0)
            com.google.android.gms.measurement.internal.zzhr r14 = r0.zze
            r14.zzb(r2)
        L_0x03de:
            zzS(r6)
            zzR(r0)
            com.google.android.gms.measurement.internal.zzhr r14 = r0.zze
            java.lang.String r14 = r14.zza()
            r6.zzac(r14)
            com.google.android.gms.measurement.internal.zzqf r14 = r13.zzn
            zzR(r14)
            com.google.android.gms.measurement.internal.zzio r14 = r14.zzu     // Catch:{ ClassNotFoundException -> 0x0400 }
            android.content.Context r14 = r14.zzc     // Catch:{ ClassNotFoundException -> 0x0400 }
            java.lang.ClassLoader r14 = r14.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x0400 }
            java.lang.String r0 = "com.google.firebase.remoteconfig.FirebaseRemoteConfig"
            r14.loadClass(r0)     // Catch:{ ClassNotFoundException -> 0x0400 }
            goto L_0x0425
        L_0x0400:
            com.google.android.gms.measurement.internal.zzht r14 = r13.zzj
            zzR(r14)
            com.google.android.gms.measurement.internal.zzhr r0 = r14.zzq
            java.lang.String r1 = r0.zza()
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x0425
            com.google.android.gms.measurement.internal.zzhe r1 = r13.zzk
            zzT(r1)
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zzk()
            java.lang.String r3 = "Remote config removed with active feature rollouts"
            r1.zza(r3)
            zzR(r14)
            r0.zzb(r2)
        L_0x0425:
            com.google.android.gms.measurement.internal.zzgs r14 = r13.zzh()
            java.lang.String r14 = r14.zzo()
            boolean r14 = android.text.TextUtils.isEmpty(r14)
            if (r14 == 0) goto L_0x044b
            com.google.android.gms.measurement.internal.zzam r14 = r13.zzi
            com.google.android.gms.measurement.internal.zzgg r0 = com.google.android.gms.measurement.internal.zzgi.zzbp
            boolean r14 = r14.zzx(r2, r0)
            if (r14 != 0) goto L_0x049a
            com.google.android.gms.measurement.internal.zzgs r14 = r13.zzh()
            java.lang.String r14 = r14.zzl()
            boolean r14 = android.text.TextUtils.isEmpty(r14)
            if (r14 != 0) goto L_0x049a
        L_0x044b:
            boolean r14 = r13.zzJ()
            com.google.android.gms.measurement.internal.zzht r0 = r13.zzj
            zzR(r0)
            boolean r1 = r0.zzo()
            if (r1 != 0) goto L_0x046a
            com.google.android.gms.measurement.internal.zzam r1 = r13.zzi
            boolean r1 = r1.zzA()
            if (r1 != 0) goto L_0x046a
            zzR(r0)
            r1 = r14 ^ 1
            r0.zzn(r1)
        L_0x046a:
            if (r14 == 0) goto L_0x0474
            com.google.android.gms.measurement.internal.zzlw r14 = r13.zzr
            zzS(r14)
            r14.zzH()
        L_0x0474:
            com.google.android.gms.measurement.internal.zzop r14 = r13.zzm
            zzS(r14)
            com.google.android.gms.measurement.internal.zzoo r14 = r14.zza
            r14.zza()
            com.google.android.gms.measurement.internal.zzny r14 = r13.zzu()
            java.util.concurrent.atomic.AtomicReference r1 = new java.util.concurrent.atomic.AtomicReference
            r1.<init>()
            r14.zzE(r1)
            com.google.android.gms.measurement.internal.zzny r14 = r13.zzu()
            zzR(r0)
            com.google.android.gms.measurement.internal.zzho r0 = r0.zzt
            android.os.Bundle r0 = r0.zza()
            r14.zzT(r0)
        L_0x049a:
            com.google.android.gms.internal.measurement.zzqr.zzb()
            com.google.android.gms.measurement.internal.zzam r14 = r13.zzi
            com.google.android.gms.measurement.internal.zzgg r0 = com.google.android.gms.measurement.internal.zzgi.zzaW
            boolean r14 = r14.zzx(r2, r0)
            if (r14 == 0) goto L_0x04fa
            com.google.android.gms.measurement.internal.zzqf r14 = r13.zzn
            zzR(r14)
            boolean r14 = r14.zzan()
            if (r14 == 0) goto L_0x04fa
            com.google.android.gms.measurement.internal.zzgg r14 = com.google.android.gms.measurement.internal.zzgi.zzaw
            java.lang.Object r14 = r14.zza(r2)
            java.lang.Integer r14 = (java.lang.Integer) r14
            int r14 = r14.intValue()
            long r0 = (long) r14
            java.util.Random r14 = new java.util.Random
            r14.<init>()
            r2 = 5000(0x1388, float:7.006E-42)
            int r14 = r14.nextInt(r2)
            r2 = 1000(0x3e8, double:4.94E-321)
            long r0 = r0 * r2
            long r2 = (long) r14
            com.google.android.gms.common.util.Clock r14 = r13.zzp
            long r0 = r0 + r2
            long r2 = r14.elapsedRealtime()
            long r0 = r0 - r2
            r2 = 500(0x1f4, double:2.47E-321)
            long r0 = java.lang.Math.max(r2, r0)
            int r14 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r14 <= 0) goto L_0x04f2
            com.google.android.gms.measurement.internal.zzhe r14 = r13.zzk
            zzT(r14)
            com.google.android.gms.measurement.internal.zzhc r14 = r14.zzj()
            java.lang.Long r2 = java.lang.Long.valueOf(r0)
            java.lang.String r3 = "Waiting to fetch trigger URIs until some time after boot. Delay in millis"
            r14.zzb(r3, r2)
        L_0x04f2:
            com.google.android.gms.measurement.internal.zzlw r14 = r13.zzr
            zzS(r14)
            r14.zzab(r0)
        L_0x04fa:
            com.google.android.gms.measurement.internal.zzht r14 = r13.zzj
            zzR(r14)
            com.google.android.gms.measurement.internal.zzhn r14 = r14.zzj
            r14.zza(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzio.zzH(com.google.android.gms.internal.measurement.zzdh):void");
    }

    public final boolean zzI() {
        return this.zzD != null && this.zzD.booleanValue();
    }

    public final boolean zzJ() {
        return zza() == 0;
    }

    public final boolean zzK() {
        zzil zzil = this.zzl;
        zzT(zzil);
        zzil.zzg();
        return this.zzE;
    }

    public final boolean zzL() {
        return TextUtils.isEmpty(this.zzd);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0082  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzM() {
        /*
            r5 = this;
            boolean r0 = r5.zzA
            if (r0 == 0) goto L_0x00c3
            com.google.android.gms.measurement.internal.zzil r0 = r5.zzl
            zzT(r0)
            r0.zzg()
            java.lang.Boolean r0 = r5.zzB
            if (r0 == 0) goto L_0x0031
            long r1 = r5.zzC
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 == 0) goto L_0x0031
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto L_0x00bc
            com.google.android.gms.common.util.Clock r0 = r5.zzp
            long r0 = r0.elapsedRealtime()
            long r2 = r5.zzC
            long r0 = r0 - r2
            long r0 = java.lang.Math.abs(r0)
            r2 = 1000(0x3e8, double:4.94E-321)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 <= 0) goto L_0x00bc
        L_0x0031:
            com.google.android.gms.common.util.Clock r0 = r5.zzp
            long r0 = r0.elapsedRealtime()
            r5.zzC = r0
            com.google.android.gms.measurement.internal.zzqf r0 = r5.zzn
            zzR(r0)
            java.lang.String r1 = "android.permission.INTERNET"
            boolean r1 = r0.zzaj(r1)
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x0075
            zzR(r0)
            java.lang.String r1 = "android.permission.ACCESS_NETWORK_STATE"
            boolean r1 = r0.zzaj(r1)
            if (r1 == 0) goto L_0x0075
            android.content.Context r1 = r5.zzc
            com.google.android.gms.common.wrappers.PackageManagerWrapper r4 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r1)
            boolean r4 = r4.isCallerInstantApp()
            if (r4 != 0) goto L_0x0073
            com.google.android.gms.measurement.internal.zzam r4 = r5.zzi
            boolean r4 = r4.zzC()
            if (r4 != 0) goto L_0x0073
            boolean r4 = com.google.android.gms.measurement.internal.zzqf.zzar(r1)
            if (r4 == 0) goto L_0x0075
            boolean r1 = com.google.android.gms.measurement.internal.zzqf.zzat(r1, r3)
            if (r1 == 0) goto L_0x0075
        L_0x0073:
            r1 = r2
            goto L_0x0076
        L_0x0075:
            r1 = r3
        L_0x0076:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            r5.zzB = r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x00bc
            zzR(r0)
            com.google.android.gms.measurement.internal.zzgs r1 = r5.zzh()
            java.lang.String r1 = r1.zzo()
            com.google.android.gms.measurement.internal.zzgs r4 = r5.zzh()
            java.lang.String r4 = r4.zzl()
            boolean r0 = r0.zzac(r1, r4)
            if (r0 != 0) goto L_0x00b6
            com.google.android.gms.measurement.internal.zzam r0 = r5.zzi
            r1 = 0
            com.google.android.gms.measurement.internal.zzgg r4 = com.google.android.gms.measurement.internal.zzgi.zzbp
            boolean r0 = r0.zzx(r1, r4)
            if (r0 != 0) goto L_0x00b5
            com.google.android.gms.measurement.internal.zzgs r0 = r5.zzh()
            java.lang.String r0 = r0.zzl()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x00b5
            goto L_0x00b6
        L_0x00b5:
            r2 = r3
        L_0x00b6:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r2)
            r5.zzB = r0
        L_0x00bc:
            java.lang.Boolean r0 = r5.zzB
            boolean r0 = r0.booleanValue()
            return r0
        L_0x00c3:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "AppMeasurement is not initialized"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzio.zzM():boolean");
    }

    public final boolean zzN() {
        return this.zzg;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0087, code lost:
        if (r4.zzm() >= 234200) goto L_0x0089;
     */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0197  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzO() {
        /*
            r11 = this;
            com.google.android.gms.measurement.internal.zzil r0 = r11.zzl
            zzT(r0)
            r0.zzg()
            com.google.android.gms.measurement.internal.zzmb r0 = r11.zzt
            zzT(r0)
            zzT(r0)
            com.google.android.gms.measurement.internal.zzgs r1 = r11.zzh()
            java.lang.String r1 = r1.zzm()
            com.google.android.gms.measurement.internal.zzam r2 = r11.zzi
            boolean r2 = r2.zzw()
            r10 = 0
            if (r2 == 0) goto L_0x01b5
            com.google.android.gms.measurement.internal.zzht r2 = r11.zzj
            zzR(r2)
            android.util.Pair r2 = r2.zzd(r1)
            java.lang.Object r3 = r2.second
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r3 = r3.booleanValue()
            if (r3 != 0) goto L_0x01a6
            java.lang.Object r3 = r2.first
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x0040
            goto L_0x01a6
        L_0x0040:
            zzT(r0)
            r0.zzv()
            com.google.android.gms.measurement.internal.zzio r0 = r0.zzu
            android.content.Context r0 = r0.zzc
            java.lang.String r3 = "connectivity"
            java.lang.Object r0 = r0.getSystemService(r3)
            android.net.ConnectivityManager r0 = (android.net.ConnectivityManager) r0
            r3 = 0
            if (r0 == 0) goto L_0x005a
            android.net.NetworkInfo r0 = r0.getActiveNetworkInfo()     // Catch:{ SecurityException -> 0x005a }
            goto L_0x005b
        L_0x005a:
            r0 = r3
        L_0x005b:
            if (r0 == 0) goto L_0x0197
            boolean r0 = r0.isConnected()
            if (r0 == 0) goto L_0x0197
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            com.google.android.gms.measurement.internal.zzny r4 = r11.zzu()
            r4.zzg()
            r4.zza()
            boolean r5 = r4.zzad()
            if (r5 != 0) goto L_0x0079
            goto L_0x0089
        L_0x0079:
            com.google.android.gms.measurement.internal.zzio r4 = r4.zzu
            com.google.android.gms.measurement.internal.zzqf r4 = r4.zzn
            zzR(r4)
            int r4 = r4.zzm()
            r5 = 234200(0x392d8, float:3.28184E-40)
            if (r4 < r5) goto L_0x013d
        L_0x0089:
            com.google.android.gms.measurement.internal.zzlw r4 = r11.zzr
            zzS(r4)
            com.google.android.gms.measurement.internal.zzio r5 = r4.zzu
            r4.zzg()
            com.google.android.gms.measurement.internal.zzny r4 = r5.zzu()
            com.google.android.gms.measurement.internal.zzap r4 = r4.zzh()
            if (r4 == 0) goto L_0x009f
            android.os.Bundle r3 = r4.zza
        L_0x009f:
            r4 = 1
            if (r3 != 0) goto L_0x00dd
            int r0 = r11.zzG
            int r1 = r0 + 1
            r11.zzG = r1
            r1 = 10
            if (r0 >= r1) goto L_0x00ad
            r10 = r4
        L_0x00ad:
            com.google.android.gms.measurement.internal.zzhe r2 = r11.zzk
            zzT(r2)
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zzd()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Failed to retrieve DMA consent from the service, "
            r3.append(r4)
            if (r0 >= r1) goto L_0x00c5
            java.lang.String r0 = "Retrying."
            goto L_0x00c7
        L_0x00c5:
            java.lang.String r0 = "Skipping."
        L_0x00c7:
            r3.append(r0)
            java.lang.String r0 = " retryCount"
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            int r1 = r11.zzG
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r2.zzb(r0, r1)
            return r10
        L_0x00dd:
            r5 = 100
            com.google.android.gms.measurement.internal.zzjx r6 = com.google.android.gms.measurement.internal.zzjx.zzi(r3, r5)
            java.lang.String r7 = "&gcs="
            r0.append(r7)
            java.lang.String r6 = r6.zzp()
            r0.append(r6)
            com.google.android.gms.measurement.internal.zzba r5 = com.google.android.gms.measurement.internal.zzba.zzc(r3, r5)
            java.lang.String r6 = "&dma="
            r0.append(r6)
            java.lang.Boolean r6 = r5.zzh()
            java.lang.Boolean r7 = java.lang.Boolean.FALSE
            boolean r6 = java.util.Objects.equals(r6, r7)
            r6 = r6 ^ r4
            r0.append(r6)
            java.lang.String r6 = r5.zzi()
            boolean r6 = android.text.TextUtils.isEmpty(r6)
            if (r6 != 0) goto L_0x011c
            java.lang.String r6 = "&dma_cps="
            r0.append(r6)
            java.lang.String r5 = r5.zzi()
            r0.append(r5)
        L_0x011c:
            java.lang.Boolean r3 = com.google.android.gms.measurement.internal.zzba.zzg(r3)
            java.lang.Boolean r5 = java.lang.Boolean.TRUE
            boolean r3 = java.util.Objects.equals(r3, r5)
            r3 = r3 ^ r4
            java.lang.String r4 = "&npa="
            r0.append(r4)
            r0.append(r3)
            com.google.android.gms.measurement.internal.zzhe r3 = r11.zzk
            zzT(r3)
            com.google.android.gms.measurement.internal.zzhc r3 = r3.zzj()
            java.lang.String r4 = "Consent query parameters to Bow"
            r3.zzb(r4, r0)
        L_0x013d:
            com.google.android.gms.measurement.internal.zzqf r3 = r11.zzn
            zzR(r3)
            com.google.android.gms.measurement.internal.zzgs r4 = r11.zzh()
            com.google.android.gms.measurement.internal.zzio r4 = r4.zzu
            com.google.android.gms.measurement.internal.zzam r4 = r4.zzi
            r4.zzj()
            java.lang.Object r2 = r2.first
            r6 = r2
            java.lang.String r6 = (java.lang.String) r6
            com.google.android.gms.measurement.internal.zzht r2 = r11.zzj
            zzR(r2)
            com.google.android.gms.measurement.internal.zzhp r2 = r2.zzp
            long r4 = r2.zza()
            r7 = -1
            long r7 = r7 + r4
            java.lang.String r9 = r0.toString()
            r4 = 119002(0x1d0da, double:5.8795E-319)
            r2 = r3
            r3 = r4
            r5 = r1
            java.net.URL r5 = r2.zzH(r3, r5, r6, r7, r9)
            if (r5 == 0) goto L_0x0196
            com.google.android.gms.measurement.internal.zzmb r3 = r11.zzt
            zzT(r3)
            com.google.android.gms.measurement.internal.zzim r8 = new com.google.android.gms.measurement.internal.zzim
            r8.<init>(r11)
            r3.zzv()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r8)
            com.google.android.gms.measurement.internal.zzio r0 = r3.zzu
            com.google.android.gms.measurement.internal.zzil r0 = r0.zzl
            zzT(r0)
            com.google.android.gms.measurement.internal.zzma r9 = new com.google.android.gms.measurement.internal.zzma
            r6 = 0
            r7 = 0
            r2 = r9
            r4 = r1
            r2.<init>(r3, r4, r5, r6, r7, r8)
            r0.zzp(r9)
        L_0x0196:
            return r10
        L_0x0197:
            com.google.android.gms.measurement.internal.zzhe r0 = r11.zzk
            zzT(r0)
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzk()
            java.lang.String r1 = "Network is not available for Deferred Deep Link request. Skipping"
            r0.zza(r1)
            return r10
        L_0x01a6:
            com.google.android.gms.measurement.internal.zzhe r0 = r11.zzk
            zzT(r0)
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzj()
            java.lang.String r1 = "ADID unavailable to retrieve Deferred Deep Link. Skipping"
            r0.zza(r1)
            return r10
        L_0x01b5:
            com.google.android.gms.measurement.internal.zzhe r0 = r11.zzk
            zzT(r0)
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzj()
            java.lang.String r1 = "ADID collection is disabled from Manifest. Skipping"
            r0.zza(r1)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzio.zzO():boolean");
    }

    public final int zza() {
        zzil zzil = this.zzl;
        zzT(zzil);
        zzil.zzg();
        zzam zzam = this.zzi;
        if (zzam.zzA()) {
            return 1;
        }
        zzT(zzil);
        zzil.zzg();
        if (!this.zzE) {
            return 8;
        }
        zzht zzht = this.zzj;
        zzR(zzht);
        Boolean zzi2 = zzht.zzi();
        if (zzi2 == null) {
            zzaf zzaf = zzam.zzu.zzh;
            Boolean zzn2 = zzam.zzn("firebase_analytics_collection_enabled");
            if (zzn2 != null) {
                if (zzn2.booleanValue()) {
                    return 0;
                }
                return 4;
            } else if (this.zzD == null || this.zzD.booleanValue()) {
                return 0;
            } else {
                return 7;
            }
        } else if (zzi2.booleanValue()) {
            return 0;
        } else {
            return 3;
        }
    }

    public final Context zzaT() {
        return this.zzc;
    }

    public final Clock zzaU() {
        return this.zzp;
    }

    public final zzaf zzaV() {
        return this.zzh;
    }

    public final zzhe zzaW() {
        zzhe zzhe = this.zzk;
        zzT(zzhe);
        return zzhe;
    }

    public final zzil zzaX() {
        zzil zzil = this.zzl;
        zzT(zzil);
        return zzil;
    }

    public final zzd zzd() {
        zzd zzd2 = this.zzs;
        zzQ(zzd2);
        return zzd2;
    }

    public final zzam zzf() {
        return this.zzi;
    }

    public final zzbb zzg() {
        zzT(this.zzx);
        return this.zzx;
    }

    public final zzgs zzh() {
        zzS(this.zzy);
        return this.zzy;
    }

    public final zzgv zzi() {
        zzS(this.zzv);
        return this.zzv;
    }

    public final zzgx zzj() {
        return this.zzo;
    }

    public final zzhe zzl() {
        zzhe zzhe = this.zzk;
        if (zzhe == null || !zzhe.zzy()) {
            return null;
        }
        return zzhe;
    }

    public final zzht zzm() {
        zzht zzht = this.zzj;
        zzR(zzht);
        return zzht;
    }

    /* access modifiers changed from: package-private */
    public final zzil zzo() {
        return this.zzl;
    }

    public final zzlw zzq() {
        zzlw zzlw = this.zzr;
        zzS(zzlw);
        return zzlw;
    }

    public final zzmb zzr() {
        zzmb zzmb = this.zzt;
        zzT(zzmb);
        return zzmb;
    }

    public final zzmd zzs() {
        zzQ(this.zzz);
        return this.zzz;
    }

    public final zzmo zzt() {
        zzmo zzmo = this.zzq;
        zzS(zzmo);
        return zzmo;
    }

    public final zzny zzu() {
        zzS(this.zzw);
        return this.zzw;
    }

    public final zzop zzv() {
        zzop zzop = this.zzm;
        zzS(zzop);
        return zzop;
    }

    public final zzqf zzw() {
        zzqf zzqf = this.zzn;
        zzR(zzqf);
        return zzqf;
    }

    public final String zzx() {
        if (this.zzi.zzx((String) null, zzgi.zzbp)) {
            return null;
        }
        return this.zzd;
    }

    public final String zzy() {
        if (this.zzi.zzx((String) null, zzgi.zzbp)) {
            return null;
        }
        return this.zze;
    }

    public final String zzz() {
        return this.zzf;
    }
}
