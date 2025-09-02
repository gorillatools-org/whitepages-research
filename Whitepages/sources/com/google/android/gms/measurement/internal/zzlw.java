package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import androidx.privacysandbox.ads.adservices.java.measurement.MeasurementManagerFutures;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzkl;
import com.google.android.gms.internal.measurement.zzkm;
import com.google.android.gms.internal.measurement.zzqr;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public final class zzlw extends zzg {
    protected zzlv zza;
    final zzx zzb;
    protected boolean zzc = true;
    private zzkb zzd;
    private final Set zze = new CopyOnWriteArraySet();
    private boolean zzf;
    private final AtomicReference zzg = new AtomicReference();
    private final Object zzh = new Object();
    /* access modifiers changed from: private */
    public boolean zzi = false;
    /* access modifiers changed from: private */
    public int zzj = 1;
    private zzaz zzk;
    private zzaz zzl;
    private PriorityQueue zzm;
    private boolean zzn;
    private zzjx zzo = zzjx.zza;
    private final AtomicLong zzp = new AtomicLong(0);
    private long zzq = -1;
    /* access modifiers changed from: private */
    public zzaz zzr;
    private SharedPreferences.OnSharedPreferenceChangeListener zzs;
    private zzaz zzt;
    private final zzqe zzv = new zzlk(this);

    protected zzlw(zzio zzio) {
        super(zzio);
        this.zzb = new zzx(zzio);
    }

    public static /* synthetic */ void zzA(zzlw zzlw, SharedPreferences sharedPreferences, String str) {
        zzio zzio = zzlw.zzu;
        if (!zzio.zzf().zzx((String) null, zzgi.zzbj)) {
            if (Objects.equals(str, "IABTCF_TCString")) {
                zzio.zzaW().zzj().zza("IABTCF_TCString change picked up in listener.");
                ((zzaz) Preconditions.checkNotNull(zzlw.zzt)).zzd(500);
            }
        } else if (Objects.equals(str, "IABTCF_TCString") || Objects.equals(str, "IABTCF_gdprApplies") || Objects.equals(str, "IABTCF_EnableAdvertiserConsentMode")) {
            zzio.zzaW().zzj().zza("IABTCF_TCString change picked up in listener.");
            ((zzaz) Preconditions.checkNotNull(zzlw.zzt)).zzd(500);
        }
    }

    static /* synthetic */ void zzD(zzlw zzlw, zzjx zzjx, long j, boolean z, boolean z2) {
        zzlw.zzg();
        zzlw.zza();
        zzio zzio = zzlw.zzu;
        zzjx zzh2 = zzio.zzm().zzh();
        if (j > zzlw.zzq || !zzjx.zzs(zzh2.zzb(), zzjx.zzb())) {
            zzht zzm2 = zzio.zzm();
            zzio zzio2 = zzm2.zzu;
            zzm2.zzg();
            int zzb2 = zzjx.zzb();
            if (zzm2.zzq(zzb2)) {
                zzio zzio3 = zzlw.zzu;
                SharedPreferences.Editor edit = zzm2.zzb().edit();
                edit.putString("consent_settings", zzjx.zzq());
                edit.putInt("consent_source", zzb2);
                edit.apply();
                zzio.zzaW().zzj().zzb("Setting storage consent(FE)", zzjx);
                zzlw.zzq = j;
                if (zzio3.zzu().zzac()) {
                    zzio3.zzu().zzX(z);
                } else {
                    zzio3.zzu().zzR(z);
                }
                if (z2) {
                    zzio3.zzu().zzE(new AtomicReference());
                    return;
                }
                return;
            }
            zzio.zzaW().zzi().zzb("Lower precedence consent source ignored, proposed source", Integer.valueOf(zzjx.zzb()));
            return;
        }
        zzio.zzaW().zzi().zzb("Dropped out-of-date consent setting, proposed settings", zzjx);
    }

    static /* bridge */ /* synthetic */ void zzF(zzlw zzlw, int i) {
        if (zzlw.zzk == null) {
            zzlw.zzk = new zzku(zzlw, zzlw.zzu);
        }
        zzlw.zzk.zzd(((long) i) * 1000);
    }

    static /* bridge */ /* synthetic */ int zzaq(zzlw zzlw, Throwable th) {
        String message = th.getMessage();
        zzlw.zzn = false;
        int i = 2;
        if (message != null) {
            if ((th instanceof IllegalStateException) || message.contains("garbage collected") || th.getClass().getSimpleName().equals("ServiceUnavailableException")) {
                i = 1;
                if (message.contains("Background")) {
                    zzlw.zzn = true;
                    return 1;
                }
            } else if (!(th instanceof SecurityException) || message.endsWith("READ_DEVICE_CONFIG")) {
                return i;
            } else {
                return 3;
            }
        }
        return i;
    }

    private final zzme zzar(zzpa zzpa) {
        try {
            URL url = new URI(zzpa.zzc).toURL();
            AtomicReference atomicReference = new AtomicReference();
            String zzn2 = this.zzu.zzh().zzn();
            zzio zzio = this.zzu;
            zzhc zzj2 = zzio.zzaW().zzj();
            Long valueOf = Long.valueOf(zzpa.zza);
            zzj2.zzd("[sgtm] Uploading data from app. row_id, url, uncompressed size", valueOf, zzpa.zzc, Integer.valueOf(zzpa.zzb.length));
            if (!TextUtils.isEmpty(zzpa.zzg)) {
                zzio.zzaW().zzj().zzc("[sgtm] Uploading data from app. row_id", valueOf, zzpa.zzg);
            }
            HashMap hashMap = new HashMap();
            Bundle bundle = zzpa.zzd;
            for (String next : bundle.keySet()) {
                String string = bundle.getString(next);
                if (!TextUtils.isEmpty(string)) {
                    hashMap.put(next, string);
                }
            }
            zzmb zzr2 = zzio.zzr();
            byte[] bArr = zzpa.zzb;
            zzkn zzkn = new zzkn(this, atomicReference, zzpa);
            zzr2.zzv();
            Preconditions.checkNotNull(url);
            Preconditions.checkNotNull(bArr);
            Preconditions.checkNotNull(zzkn);
            zzr2.zzu.zzaX().zzp(new zzma(zzr2, zzn2, url, bArr, hashMap, zzkn));
            try {
                zzio zzio2 = zzio.zzw().zzu;
                long j = 60000;
                long currentTimeMillis = zzio2.zzaU().currentTimeMillis() + 60000;
                synchronized (atomicReference) {
                    while (atomicReference.get() == null && j > 0) {
                        atomicReference.wait(j);
                        j = currentTimeMillis - zzio2.zzaU().currentTimeMillis();
                    }
                }
            } catch (InterruptedException unused) {
                this.zzu.zzaW().zzk().zza("[sgtm] Interrupted waiting for uploading batch");
            } catch (Throwable th) {
                throw th;
            }
            return atomicReference.get() == null ? zzme.UNKNOWN : (zzme) atomicReference.get();
        } catch (MalformedURLException | URISyntaxException e) {
            this.zzu.zzaW().zze().zzd("[sgtm] Bad upload url for row_id", zzpa.zzc, Long.valueOf(zzpa.zza), e);
            return zzme.FAILURE;
        }
    }

    /* access modifiers changed from: private */
    public final void zzas(Boolean bool, boolean z) {
        zzg();
        zza();
        zzio zzio = this.zzu;
        zzio.zzaW().zzd().zzb("Setting app measurement enabled (FE)", bool);
        zzio.zzm().zzm(bool);
        if (z) {
            zzht zzm2 = zzio.zzm();
            zzio zzio2 = zzm2.zzu;
            zzm2.zzg();
            SharedPreferences.Editor edit = zzm2.zzb().edit();
            if (bool != null) {
                edit.putBoolean("measurement_enabled_from_api", bool.booleanValue());
            } else {
                edit.remove("measurement_enabled_from_api");
            }
            edit.apply();
        }
        if (this.zzu.zzK() || (bool != null && !bool.booleanValue())) {
            zzat();
        }
    }

    /* access modifiers changed from: private */
    public final void zzat() {
        zzg();
        zzio zzio = this.zzu;
        String zza2 = zzio.zzm().zzh.zza();
        if (zza2 != null) {
            if ("unset".equals(zza2)) {
                zzan("app", "_npa", (Object) null, zzio.zzaU().currentTimeMillis());
            } else {
                zzan("app", "_npa", Long.valueOf(true != "true".equals(zza2) ? 0 : 1), zzio.zzaU().currentTimeMillis());
            }
        }
        if (!this.zzu.zzJ() || !this.zzc) {
            zzio.zzaW().zzd().zza("Updating Scion state (FE)");
            this.zzu.zzu().zzV();
            return;
        }
        zzio.zzaW().zzd().zza("Recording app launch after enabling measurement for the first time (FE)");
        zzH();
        this.zzu.zzv().zza.zza();
        zzio.zzaX().zzq(new zzkw(this));
    }

    public static /* synthetic */ void zzz(zzlw zzlw, Bundle bundle) {
        Bundle bundle2;
        int i;
        if (bundle.isEmpty()) {
            bundle2 = bundle;
        } else {
            zzio zzio = zzlw.zzu;
            bundle2 = new Bundle(zzio.zzm().zzt.zza());
            Iterator<String> it = bundle.keySet().iterator();
            while (true) {
                i = 0;
                if (!it.hasNext()) {
                    break;
                }
                String next = it.next();
                Object obj = bundle.get(next);
                if (obj != null && !(obj instanceof String) && !(obj instanceof Long) && !(obj instanceof Double)) {
                    if (zzio.zzw().zzal(obj)) {
                        zzio.zzw().zzR(zzlw.zzv, (String) null, 27, (String) null, (String) null, 0);
                    }
                    zzio.zzaW().zzl().zzc("Invalid default event parameter type. Name, value", next, obj);
                } else if (zzqf.zzap(next)) {
                    zzio.zzaW().zzl().zzb("Invalid default event parameter name. Name", next);
                } else if (obj == null) {
                    bundle2.remove(next);
                } else if (zzio.zzw().zzaf("param", next, zzio.zzf().zzc((String) null, false), obj)) {
                    zzio.zzw().zzS(bundle2, next, obj);
                }
            }
            zzio.zzw();
            int zze2 = zzio.zzf().zze();
            if (bundle2.size() > zze2) {
                for (String str : new TreeSet(bundle2.keySet())) {
                    i++;
                    if (i > zze2) {
                        bundle2.remove(str);
                    }
                }
                zzio.zzw().zzR(zzlw.zzv, (String) null, 26, (String) null, (String) null, 0);
                zzio.zzaW().zzl().zza("Too many default event parameters set. Discarding beyond event parameter limit");
            }
        }
        zzio zzio2 = zzlw.zzu;
        zzio2.zzm().zzt.zzb(bundle2);
        if (!bundle.isEmpty() || zzio2.zzf().zzx((String) null, zzgi.zzbd)) {
            zzlw.zzu.zzu().zzT(bundle2);
        }
    }

    public final void zzH() {
        zzg();
        zza();
        if (this.zzu.zzM()) {
            zzio zzio = this.zzu;
            zzam zzf2 = zzio.zzf();
            zzf2.zzu.zzaV();
            Boolean zzn2 = zzf2.zzn("google_analytics_deferred_deep_link_enabled");
            if (zzn2 != null && zzn2.booleanValue()) {
                zzio.zzaW().zzd().zza("Deferred Deep Link feature enabled.");
                zzio.zzaX().zzq(new zzko(this));
            }
            this.zzu.zzu().zzA();
            this.zzc = false;
            zzht zzm2 = zzio.zzm();
            zzm2.zzg();
            String string = zzm2.zzb().getString("previous_os_version", (String) null);
            zzm2.zzu.zzg().zzv();
            String str = Build.VERSION.RELEASE;
            if (!TextUtils.isEmpty(str) && !str.equals(string)) {
                SharedPreferences.Editor edit = zzm2.zzb().edit();
                edit.putString("previous_os_version", str);
                edit.apply();
            }
            if (!TextUtils.isEmpty(string)) {
                zzio.zzg().zzv();
                if (!string.equals(str)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("_po", string);
                    zzR("auto", "_ou", bundle);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzI() {
        zzg();
        zzaz zzaz = this.zzl;
        if (zzaz != null) {
            zzaz.zzb();
        }
    }

    public final void zzJ(String str, String str2, Bundle bundle) {
        zzio zzio = this.zzu;
        long currentTimeMillis = zzio.zzaU().currentTimeMillis();
        Preconditions.checkNotEmpty(str);
        Bundle bundle2 = new Bundle();
        bundle2.putString("name", str);
        bundle2.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, currentTimeMillis);
        if (str2 != null) {
            bundle2.putString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, str2);
            bundle2.putBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, bundle);
        }
        zzio.zzaX().zzq(new zzlg(this, bundle2));
    }

    public final void zzK() {
        zzio zzio = this.zzu;
        if ((zzio.zzaT().getApplicationContext() instanceof Application) && this.zza != null) {
            ((Application) zzio.zzaT().getApplicationContext()).unregisterActivityLifecycleCallbacks(this.zza);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzL() {
        zzqr.zzb();
        zzio zzio = this.zzu;
        if (!zzio.zzf().zzx((String) null, zzgi.zzaW)) {
            return;
        }
        if (!zzio.zzaX().zzu()) {
            zzio.zzaV();
            if (!zzaf.zza()) {
                zza();
                zzio.zzaW().zzj().zza("Getting trigger URIs (FE)");
                AtomicReference atomicReference = new AtomicReference();
                zzio.zzaX().zze(atomicReference, 10000, "get trigger URIs", new zzki(this, atomicReference));
                List list = (List) atomicReference.get();
                if (list == null) {
                    zzio.zzaW().zze().zza("Timed out waiting for get trigger URIs");
                } else {
                    zzio.zzaX().zzq(new zzkj(this, list));
                }
            } else {
                zzio.zzaW().zze().zza("Cannot get trigger URIs from main thread");
            }
        } else {
            zzio.zzaW().zze().zza("Cannot get trigger URIs from analytics worker thread");
        }
    }

    public final void zzM() {
        zzg();
        zzio zzio = this.zzu;
        if (!zzio.zzm().zzo.zzb()) {
            long zza2 = zzio.zzm().zzp.zza();
            zzio.zzm().zzp.zzb(1 + zza2);
            zzio.zzf();
            if (zza2 >= 5) {
                zzio.zzaW().zzk().zza("Permanently failed to retrieve Deferred Deep Link. Reached maximum retries.");
                zzio.zzm().zzo.zza(true);
                return;
            }
            if (this.zzr == null) {
                this.zzr = new zzld(this, this.zzu);
            }
            this.zzr.zzd(0);
            return;
        }
        zzio.zzaW().zzd().zza("Deferred Deep Link already retrieved. Not fetching again.");
    }

    public final void zzN() {
        zzio zzio;
        zzgg zzgg;
        zzoq zzoq;
        zzoq zzoq2;
        zzlw zzlw;
        int i;
        zzkm zzkm;
        zzg();
        zzio zzio2 = this.zzu;
        zzio2.zzaW().zzd().zza("Handle tcf update.");
        SharedPreferences zza2 = zzio2.zzm().zza();
        HashMap hashMap = new HashMap();
        zzgg zzgg2 = zzgi.zzbj;
        if (((Boolean) zzgg2.zza((Object) null)).booleanValue()) {
            int i2 = zzot.zzb;
            zzkl zzkl = zzkl.IAB_TCF_PURPOSE_STORE_AND_ACCESS_INFORMATION_ON_A_DEVICE;
            zzos zzos = zzos.CONSENT;
            Map.Entry zza3 = zzor.zza(zzkl, zzos);
            zzkl zzkl2 = zzkl.IAB_TCF_PURPOSE_SELECT_BASIC_ADS;
            zzos zzos2 = zzos.FLEXIBLE_LEGITIMATE_INTEREST;
            zzgg = zzgg2;
            zzio = zzio2;
            ImmutableMap ofEntries = ImmutableMap.ofEntries(zza3, zzor.zza(zzkl2, zzos2), zzor.zza(zzkl.IAB_TCF_PURPOSE_CREATE_A_PERSONALISED_ADS_PROFILE, zzos), zzor.zza(zzkl.IAB_TCF_PURPOSE_SELECT_PERSONALISED_ADS, zzos), zzor.zza(zzkl.IAB_TCF_PURPOSE_MEASURE_AD_PERFORMANCE, zzos2), zzor.zza(zzkl.IAB_TCF_PURPOSE_APPLY_MARKET_RESEARCH_TO_GENERATE_AUDIENCE_INSIGHTS, zzos2), zzor.zza(zzkl.IAB_TCF_PURPOSE_DEVELOP_AND_IMPROVE_PRODUCTS, zzos2));
            ImmutableSet of = ImmutableSet.of("CH");
            char[] cArr = new char[5];
            int zza4 = zzot.zza(zza2, "IABTCF_CmpSdkID");
            int zza5 = zzot.zza(zza2, "IABTCF_PolicyVersion");
            int zza6 = zzot.zza(zza2, "IABTCF_gdprApplies");
            int zza7 = zzot.zza(zza2, "IABTCF_PurposeOneTreatment");
            int zza8 = zzot.zza(zza2, "IABTCF_EnableAdvertiserConsentMode");
            String zzb2 = zzot.zzb(zza2, "IABTCF_PublisherCC");
            ImmutableMap.Builder builder = ImmutableMap.builder();
            UnmodifiableIterator it = ofEntries.keySet().iterator();
            while (it.hasNext()) {
                zzkl zzkl3 = (zzkl) it.next();
                String zzb3 = zzot.zzb(zza2, "IABTCF_PublisherRestrictions" + zzkl3.zza());
                if (TextUtils.isEmpty(zzb3) || zzb3.length() < 755) {
                    zzkm = zzkm.PURPOSE_RESTRICTION_UNDEFINED;
                } else {
                    int digit = Character.digit(zzb3.charAt(754), 10);
                    zzkm = (digit < 0 || digit > zzkm.values().length || digit == 0) ? zzkm.PURPOSE_RESTRICTION_NOT_ALLOWED : digit != 1 ? digit != 2 ? zzkm.PURPOSE_RESTRICTION_UNDEFINED : zzkm.PURPOSE_RESTRICTION_REQUIRE_LEGITIMATE_INTEREST : zzkm.PURPOSE_RESTRICTION_REQUIRE_CONSENT;
                }
                builder.put(zzkl3, zzkm);
            }
            ImmutableMap buildOrThrow = builder.buildOrThrow();
            String zzb4 = zzot.zzb(zza2, "IABTCF_PurposeConsents");
            String zzb5 = zzot.zzb(zza2, "IABTCF_VendorConsents");
            boolean z = !TextUtils.isEmpty(zzb5) && zzb5.length() >= 755 && zzb5.charAt(754) == '1';
            String zzb6 = zzot.zzb(zza2, "IABTCF_PurposeLegitimateInterests");
            String zzb7 = zzot.zzb(zza2, "IABTCF_VendorLegitimateInterests");
            boolean z2 = !TextUtils.isEmpty(zzb7) && zzb7.length() >= 755 && zzb7.charAt(754) == '1';
            cArr[0] = '2';
            zzoq = new zzoq(zzot.zzc(ofEntries, buildOrThrow, of, cArr, zza4, zza8, zza6, zza5, zza7, zzb2, zzb4, zzb6, z, z2));
        } else {
            zzio = zzio2;
            zzgg = zzgg2;
            String zzb8 = zzot.zzb(zza2, "IABTCF_VendorConsents");
            if (!"".equals(zzb8) && zzb8.length() > 754) {
                hashMap.put("GoogleConsent", String.valueOf(zzb8.charAt(754)));
            }
            int zza9 = zzot.zza(zza2, "IABTCF_gdprApplies");
            if (zza9 != -1) {
                hashMap.put("gdprApplies", String.valueOf(zza9));
            }
            int zza10 = zzot.zza(zza2, "IABTCF_EnableAdvertiserConsentMode");
            if (zza10 != -1) {
                hashMap.put("EnableAdvertiserConsentMode", String.valueOf(zza10));
            }
            int zza11 = zzot.zza(zza2, "IABTCF_PolicyVersion");
            if (zza11 != -1) {
                hashMap.put("PolicyVersion", String.valueOf(zza11));
            }
            String zzb9 = zzot.zzb(zza2, "IABTCF_PurposeConsents");
            if (!"".equals(zzb9)) {
                hashMap.put("PurposeConsents", zzb9);
            }
            int zza12 = zzot.zza(zza2, "IABTCF_CmpSdkID");
            if (zza12 != -1) {
                hashMap.put("CmpSdkID", String.valueOf(zza12));
            }
            zzoq = new zzoq(hashMap);
        }
        zzio.zzaW().zzj().zzb("Tcf preferences read", zzoq);
        if (zzio.zzf().zzx((String) null, zzgg)) {
            zzht zzm2 = zzio.zzm();
            zzm2.zzg();
            String string = zzm2.zzb().getString("stored_tcf_param", "");
            HashMap hashMap2 = new HashMap();
            if (TextUtils.isEmpty(string)) {
                zzoq2 = new zzoq(hashMap2);
            } else {
                String[] split = string.split(";");
                int length = split.length;
                int i3 = 0;
                while (i3 < length) {
                    String[] split2 = split[i3].split("=");
                    if (split2.length < 2 || !zzot.zza.contains(split2[0])) {
                        i = 1;
                    } else {
                        i = 1;
                        hashMap2.put(split2[0], split2[1]);
                    }
                    i3 += i;
                }
                zzoq2 = new zzoq(hashMap2);
            }
            if (zzio.zzm().zzr(zzoq)) {
                Bundle zza13 = zzoq.zza();
                zzio.zzaW().zzj().zzb("Consent generated from Tcf", zza13);
                if (zza13 != Bundle.EMPTY) {
                    zzlw = this;
                    zzlw.zzaf(zza13, -30, zzio.zzaU().currentTimeMillis());
                } else {
                    zzlw = this;
                }
                Bundle bundle = new Bundle();
                bundle.putString("_tcfm", zzoq.zzc(zzoq2));
                bundle.putString("_tcfd2", zzoq.zzb());
                bundle.putString("_tcfd", zzoq.zzd());
                zzlw.zzR("auto", "_tcf", bundle);
                return;
            }
            return;
        }
        if (zzio.zzm().zzr(zzoq)) {
            Bundle zza14 = zzoq.zza();
            zzio.zzaW().zzj().zzb("Consent generated from Tcf", zza14);
            if (zza14 != Bundle.EMPTY) {
                zzaf(zza14, -30, zzio.zzaU().currentTimeMillis());
            }
            Bundle bundle2 = new Bundle();
            bundle2.putString("_tcfd", zzoq.zzd());
            zzR("auto", "_tcf", bundle2);
        }
    }

    public final void zzO(String str, String str2, Bundle bundle) {
        zzP(str, str2, bundle, true, true, this.zzu.zzaU().currentTimeMillis());
    }

    public final void zzP(String str, String str2, Bundle bundle, boolean z, boolean z2, long j) {
        Bundle bundle2 = bundle == null ? new Bundle() : bundle;
        String str3 = str2;
        if (Objects.equals(str2, FirebaseAnalytics.Event.SCREEN_VIEW)) {
            this.zzu.zzt().zzy(bundle2, j);
            return;
        }
        long j2 = j;
        boolean z3 = true;
        if (z2 && this.zzd != null && !zzqf.zzap(str2)) {
            z3 = false;
        }
        zzZ(str == null ? "app" : str, str2, j, bundle2, z2, z3, z, (String) null);
    }

    public final void zzQ(String str, String str2, Bundle bundle, String str3) {
        zzio.zzP();
        zzZ("auto", str2, this.zzu.zzaU().currentTimeMillis(), bundle, false, true, true, str3);
    }

    /* access modifiers changed from: package-private */
    public final void zzR(String str, String str2, Bundle bundle) {
        zzg();
        zzS(str, str2, this.zzu.zzaU().currentTimeMillis(), bundle);
    }

    /* access modifiers changed from: package-private */
    public final void zzS(String str, String str2, long j, Bundle bundle) {
        zzg();
        zzT(str, str2, j, bundle, true, this.zzd == null || zzqf.zzap(str2), true, (String) null);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0122  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzT(java.lang.String r25, java.lang.String r26, long r27, android.os.Bundle r29, boolean r30, boolean r31, boolean r32, java.lang.String r33) {
        /*
            r24 = this;
            r7 = r24
            r8 = r25
            r9 = r26
            r10 = r27
            r12 = r29
            r13 = 1
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r25)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r29)
            r24.zzg()
            r24.zza()
            com.google.android.gms.measurement.internal.zzio r0 = r7.zzu
            boolean r1 = r0.zzJ()
            if (r1 == 0) goto L_0x04ae
            com.google.android.gms.measurement.internal.zzio r1 = r7.zzu
            com.google.android.gms.measurement.internal.zzgs r1 = r1.zzh()
            java.util.List r1 = r1.zzp()
            if (r1 == 0) goto L_0x0042
            boolean r1 = r1.contains(r9)
            if (r1 == 0) goto L_0x0032
            goto L_0x0042
        L_0x0032:
            com.google.android.gms.measurement.internal.zzio r0 = r7.zzu
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzd()
            java.lang.String r1 = "Dropping non-safelisted event. event name, origin"
            r0.zzc(r1, r9, r8)
            return
        L_0x0042:
            boolean r1 = r7.zzf
            r14 = 0
            if (r1 != 0) goto L_0x009e
            r7.zzf = r13
            boolean r0 = r0.zzN()     // Catch:{ ClassNotFoundException -> 0x008f }
            java.lang.String r1 = "com.google.android.gms.tagmanager.TagManagerService"
            if (r0 != 0) goto L_0x0060
            com.google.android.gms.measurement.internal.zzio r0 = r7.zzu     // Catch:{ ClassNotFoundException -> 0x008f }
            android.content.Context r0 = r0.zzaT()     // Catch:{ ClassNotFoundException -> 0x008f }
            java.lang.ClassLoader r0 = r0.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x008f }
            java.lang.Class r0 = java.lang.Class.forName(r1, r13, r0)     // Catch:{ ClassNotFoundException -> 0x008f }
            goto L_0x0064
        L_0x0060:
            java.lang.Class r0 = java.lang.Class.forName(r1)     // Catch:{ ClassNotFoundException -> 0x008f }
        L_0x0064:
            java.lang.String r1 = "initialize"
            java.lang.Class<android.content.Context> r2 = android.content.Context.class
            java.lang.Class[] r2 = new java.lang.Class[]{r2}     // Catch:{ Exception -> 0x007e }
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r1, r2)     // Catch:{ Exception -> 0x007e }
            com.google.android.gms.measurement.internal.zzio r1 = r7.zzu     // Catch:{ Exception -> 0x007e }
            android.content.Context r1 = r1.zzaT()     // Catch:{ Exception -> 0x007e }
            java.lang.Object[] r1 = new java.lang.Object[]{r1}     // Catch:{ Exception -> 0x007e }
            r0.invoke(r14, r1)     // Catch:{ Exception -> 0x007e }
            goto L_0x009e
        L_0x007e:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzio r1 = r7.zzu     // Catch:{ ClassNotFoundException -> 0x008f }
            com.google.android.gms.measurement.internal.zzhe r1 = r1.zzaW()     // Catch:{ ClassNotFoundException -> 0x008f }
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zzk()     // Catch:{ ClassNotFoundException -> 0x008f }
            java.lang.String r2 = "Failed to invoke Tag Manager's initialize() method"
            r1.zzb(r2, r0)     // Catch:{ ClassNotFoundException -> 0x008f }
            goto L_0x009e
        L_0x008f:
            com.google.android.gms.measurement.internal.zzio r0 = r7.zzu
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzi()
            java.lang.String r1 = "Tag Manager is not found and thus will not be used"
            r0.zza(r1)
        L_0x009e:
            java.lang.String r0 = "_cmp"
            boolean r0 = r0.equals(r9)
            if (r0 == 0) goto L_0x00c8
            java.lang.String r0 = "gclid"
            boolean r1 = r12.containsKey(r0)
            if (r1 == 0) goto L_0x00c8
            com.google.android.gms.measurement.internal.zzio r1 = r7.zzu
            r1.zzaV()
            java.lang.String r4 = r12.getString(r0)
            com.google.android.gms.common.util.Clock r0 = r1.zzaU()
            long r5 = r0.currentTimeMillis()
            java.lang.String r2 = "auto"
            java.lang.String r3 = "_lgclid"
            r1 = r24
            r1.zzan(r2, r3, r4, r5)
        L_0x00c8:
            com.google.android.gms.measurement.internal.zzio r0 = r7.zzu
            r0.zzaV()
            if (r30 == 0) goto L_0x00e6
            boolean r1 = com.google.android.gms.measurement.internal.zzqf.zzau(r26)
            if (r1 == 0) goto L_0x00e6
            com.google.android.gms.measurement.internal.zzqf r1 = r0.zzw()
            com.google.android.gms.measurement.internal.zzht r2 = r0.zzm()
            com.google.android.gms.measurement.internal.zzho r2 = r2.zzt
            android.os.Bundle r2 = r2.zza()
            r1.zzO(r12, r2)
        L_0x00e6:
            r1 = 40
            r15 = 0
            if (r32 != 0) goto L_0x0163
            r0.zzaV()
            java.lang.String r2 = "_iap"
            boolean r2 = r2.equals(r9)
            if (r2 != 0) goto L_0x0163
            com.google.android.gms.measurement.internal.zzio r2 = r7.zzu
            com.google.android.gms.measurement.internal.zzqf r3 = r2.zzw()
            java.lang.String r4 = "event"
            boolean r5 = r3.zzah(r4, r9)
            if (r5 != 0) goto L_0x0106
        L_0x0104:
            r6 = 2
            goto L_0x0120
        L_0x0106:
            java.lang.String[] r5 = com.google.android.gms.measurement.internal.zzjy.zza
            java.lang.String[] r6 = com.google.android.gms.measurement.internal.zzjy.zzb
            boolean r5 = r3.zzae(r4, r5, r6, r9)
            if (r5 != 0) goto L_0x0113
            r6 = 13
            goto L_0x0120
        L_0x0113:
            com.google.android.gms.measurement.internal.zzio r5 = r3.zzu
            r5.zzf()
            boolean r3 = r3.zzad(r4, r1, r9)
            if (r3 != 0) goto L_0x011f
            goto L_0x0104
        L_0x011f:
            r6 = r15
        L_0x0120:
            if (r6 == 0) goto L_0x0163
            com.google.android.gms.measurement.internal.zzhe r3 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r3 = r3.zzf()
            com.google.android.gms.measurement.internal.zzgx r0 = r0.zzj()
            java.lang.String r0 = r0.zzd(r9)
            java.lang.String r4 = "Invalid public event name. Event will not be logged (FE)"
            r3.zzb(r4, r0)
            com.google.android.gms.measurement.internal.zzqf r0 = r2.zzw()
            r2.zzf()
            java.lang.String r0 = r0.zzG(r9, r1, r13)
            if (r9 == 0) goto L_0x0148
            int r15 = r26.length()
        L_0x0148:
            com.google.android.gms.measurement.internal.zzqf r1 = r2.zzw()
            com.google.android.gms.measurement.internal.zzqe r2 = r7.zzv
            r3 = 0
            java.lang.String r4 = "_ev"
            r25 = r1
            r26 = r2
            r27 = r3
            r28 = r6
            r29 = r4
            r30 = r0
            r31 = r15
            r25.zzR(r26, r27, r28, r29, r30, r31)
            return
        L_0x0163:
            r0.zzaV()
            com.google.android.gms.measurement.internal.zzio r6 = r7.zzu
            com.google.android.gms.measurement.internal.zzmo r2 = r6.zzt()
            com.google.android.gms.measurement.internal.zzmh r2 = r2.zzj(r15)
            java.lang.String r3 = "_sc"
            if (r2 == 0) goto L_0x017c
            boolean r4 = r12.containsKey(r3)
            if (r4 != 0) goto L_0x017c
            r2.zzd = r13
        L_0x017c:
            if (r30 == 0) goto L_0x0182
            if (r32 != 0) goto L_0x0182
            r4 = r13
            goto L_0x0183
        L_0x0182:
            r4 = r15
        L_0x0183:
            com.google.android.gms.measurement.internal.zzqf.zzN(r2, r12, r4)
            java.lang.String r2 = "am"
            boolean r2 = r2.equals(r8)
            boolean r4 = com.google.android.gms.measurement.internal.zzqf.zzap(r26)
            if (r30 == 0) goto L_0x01cd
            com.google.android.gms.measurement.internal.zzkb r5 = r7.zzd
            if (r5 == 0) goto L_0x01cd
            if (r4 != 0) goto L_0x01cd
            if (r2 == 0) goto L_0x019d
            r16 = r13
            goto L_0x01cf
        L_0x019d:
            com.google.android.gms.measurement.internal.zzhe r1 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zzd()
            com.google.android.gms.measurement.internal.zzgx r2 = r0.zzj()
            java.lang.String r2 = r2.zzd(r9)
            com.google.android.gms.measurement.internal.zzgx r0 = r0.zzj()
            java.lang.String r0 = r0.zzb(r12)
            java.lang.String r3 = "Passing event to registered event handler (FE)"
            r1.zzc(r3, r2, r0)
            com.google.android.gms.measurement.internal.zzkb r0 = r7.zzd
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r0)
            com.google.android.gms.measurement.internal.zzkb r1 = r7.zzd
            r2 = r25
            r3 = r26
            r4 = r29
            r5 = r27
            r1.interceptEvent(r2, r3, r4, r5)
            return
        L_0x01cd:
            r16 = r2
        L_0x01cf:
            com.google.android.gms.measurement.internal.zzio r5 = r7.zzu
            boolean r2 = r5.zzM()
            if (r2 != 0) goto L_0x01da
            r15 = r7
            goto L_0x04ad
        L_0x01da:
            com.google.android.gms.measurement.internal.zzqf r2 = r0.zzw()
            int r2 = r2.zzf(r9)
            if (r2 == 0) goto L_0x0224
            com.google.android.gms.measurement.internal.zzhe r3 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r3 = r3.zzf()
            com.google.android.gms.measurement.internal.zzgx r4 = r0.zzj()
            java.lang.String r4 = r4.zzd(r9)
            java.lang.String r6 = "Invalid event name. Event will not be logged (FE)"
            r3.zzb(r6, r4)
            com.google.android.gms.measurement.internal.zzqf r3 = r0.zzw()
            r0.zzf()
            java.lang.String r0 = r3.zzG(r9, r1, r13)
            if (r9 == 0) goto L_0x020a
            int r15 = r26.length()
        L_0x020a:
            com.google.android.gms.measurement.internal.zzqf r1 = r5.zzw()
            com.google.android.gms.measurement.internal.zzqe r3 = r7.zzv
            java.lang.String r4 = "_ev"
            r25 = r1
            r26 = r3
            r27 = r33
            r28 = r2
            r29 = r4
            r30 = r0
            r31 = r15
            r25.zzR(r26, r27, r28, r29, r30, r31)
            return
        L_0x0224:
            java.lang.String r1 = "_sn"
            java.lang.String r2 = "_si"
            java.lang.String r4 = "_o"
            java.lang.String[] r1 = new java.lang.String[]{r4, r1, r3, r2}
            java.util.List r17 = com.google.android.gms.common.util.CollectionUtils.listOf((T[]) r1)
            com.google.android.gms.measurement.internal.zzqf r1 = r0.zzw()
            r2 = r33
            r3 = r26
            r18 = r4
            r4 = r29
            r12 = r5
            r5 = r17
            r17 = r6
            r6 = r32
            android.os.Bundle r5 = r1.zzA(r2, r3, r4, r5, r6)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5)
            r0.zzaV()
            com.google.android.gms.measurement.internal.zzmo r1 = r17.zzt()
            com.google.android.gms.measurement.internal.zzmh r1 = r1.zzj(r15)
            java.lang.String r6 = "_ae"
            if (r1 == 0) goto L_0x0286
            boolean r1 = r6.equals(r9)
            if (r1 == 0) goto L_0x0286
            com.google.android.gms.measurement.internal.zzop r1 = r17.zzv()
            com.google.android.gms.measurement.internal.zzon r1 = r1.zzb
            com.google.android.gms.measurement.internal.zzop r2 = r1.zzc
            com.google.android.gms.measurement.internal.zzio r2 = r2.zzu
            com.google.android.gms.common.util.Clock r2 = r2.zzaU()
            long r13 = r2.elapsedRealtime()
            long r3 = r1.zzb
            long r2 = r13 - r3
            r1.zzb = r13
            r13 = 0
            int r1 = (r2 > r13 ? 1 : (r2 == r13 ? 0 : -1))
            if (r1 <= 0) goto L_0x0286
            com.google.android.gms.measurement.internal.zzqf r1 = r0.zzw()
            r1.zzL(r5, r2)
        L_0x0286:
            java.lang.String r1 = "auto"
            boolean r1 = r1.equals(r8)
            java.lang.String r2 = "_ffr"
            if (r1 != 0) goto L_0x02d8
            java.lang.String r1 = "_ssr"
            boolean r1 = r1.equals(r9)
            if (r1 == 0) goto L_0x02d8
            com.google.android.gms.measurement.internal.zzqf r1 = r0.zzw()
            java.lang.String r2 = r5.getString(r2)
            boolean r3 = com.google.android.gms.common.util.Strings.isEmptyOrWhitespace(r2)
            if (r3 == 0) goto L_0x02a8
            r2 = 0
            goto L_0x02ae
        L_0x02a8:
            if (r2 == 0) goto L_0x02ae
            java.lang.String r2 = r2.trim()
        L_0x02ae:
            com.google.android.gms.measurement.internal.zzio r1 = r1.zzu
            com.google.android.gms.measurement.internal.zzht r3 = r1.zzm()
            com.google.android.gms.measurement.internal.zzhr r3 = r3.zzq
            java.lang.String r3 = r3.zza()
            boolean r3 = java.util.Objects.equals(r2, r3)
            if (r3 != 0) goto L_0x02ca
            com.google.android.gms.measurement.internal.zzht r1 = r1.zzm()
            com.google.android.gms.measurement.internal.zzhr r1 = r1.zzq
            r1.zzb(r2)
            goto L_0x02f7
        L_0x02ca:
            com.google.android.gms.measurement.internal.zzhe r0 = r1.zzaW()
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzd()
            java.lang.String r1 = "Not logging duplicate session_start_with_rollout event"
            r0.zza(r1)
            return
        L_0x02d8:
            boolean r1 = r6.equals(r9)
            if (r1 == 0) goto L_0x02f7
            com.google.android.gms.measurement.internal.zzqf r1 = r0.zzw()
            com.google.android.gms.measurement.internal.zzio r1 = r1.zzu
            com.google.android.gms.measurement.internal.zzht r1 = r1.zzm()
            com.google.android.gms.measurement.internal.zzhr r1 = r1.zzq
            java.lang.String r1 = r1.zza()
            boolean r3 = android.text.TextUtils.isEmpty(r1)
            if (r3 != 0) goto L_0x02f7
            r5.putString(r2, r1)
        L_0x02f7:
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>()
            r13.add(r5)
            com.google.android.gms.measurement.internal.zzam r1 = r0.zzf()
            com.google.android.gms.measurement.internal.zzgg r2 = com.google.android.gms.measurement.internal.zzgi.zzba
            r3 = 0
            boolean r1 = r1.zzx(r3, r2)
            if (r1 == 0) goto L_0x0315
            com.google.android.gms.measurement.internal.zzop r1 = r17.zzv()
            boolean r1 = r1.zzp()
            goto L_0x031f
        L_0x0315:
            com.google.android.gms.measurement.internal.zzht r1 = r0.zzm()
            com.google.android.gms.measurement.internal.zzhn r1 = r1.zzn
            boolean r1 = r1.zzb()
        L_0x031f:
            com.google.android.gms.measurement.internal.zzht r2 = r0.zzm()
            com.google.android.gms.measurement.internal.zzhp r2 = r2.zzk
            long r2 = r2.zza()
            r19 = 0
            int r2 = (r2 > r19 ? 1 : (r2 == r19 ? 0 : -1))
            if (r2 <= 0) goto L_0x038d
            com.google.android.gms.measurement.internal.zzht r2 = r0.zzm()
            boolean r2 = r2.zzp(r10)
            if (r2 == 0) goto L_0x038d
            if (r1 == 0) goto L_0x038d
            com.google.android.gms.measurement.internal.zzhe r1 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zzj()
            java.lang.String r2 = "Current session is expired, remove the session number, ID, and engagement time"
            r1.zza(r2)
            com.google.android.gms.common.util.Clock r1 = r0.zzaU()
            long r21 = r1.currentTimeMillis()
            java.lang.String r3 = "_sid"
            r4 = 0
            java.lang.String r2 = "auto"
            r1 = r24
            r7 = r19
            r14 = r5
            r23 = r6
            r5 = r21
            r1.zzan(r2, r3, r4, r5)
            com.google.android.gms.common.util.Clock r1 = r0.zzaU()
            long r5 = r1.currentTimeMillis()
            java.lang.String r3 = "_sno"
            java.lang.String r2 = "auto"
            r1 = r24
            r1.zzan(r2, r3, r4, r5)
            com.google.android.gms.common.util.Clock r1 = r0.zzaU()
            long r5 = r1.currentTimeMillis()
            java.lang.String r3 = "_se"
            java.lang.String r2 = "auto"
            r1 = r24
            r1.zzan(r2, r3, r4, r5)
            com.google.android.gms.measurement.internal.zzht r1 = r0.zzm()
            com.google.android.gms.measurement.internal.zzhp r1 = r1.zzl
            r1.zzb(r7)
            goto L_0x0392
        L_0x038d:
            r14 = r5
            r23 = r6
            r7 = r19
        L_0x0392:
            java.lang.String r1 = "extend_session"
            long r1 = r14.getLong(r1, r7)
            r3 = 1
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x03b5
            com.google.android.gms.measurement.internal.zzhe r1 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zzj()
            java.lang.String r2 = "EXTEND_SESSION param attached: initiate a new session or extend the current active session"
            r1.zza(r2)
            com.google.android.gms.measurement.internal.zzop r1 = r12.zzv()
            com.google.android.gms.measurement.internal.zzoo r1 = r1.zza
            r2 = 1
            r1.zzb(r10, r2)
        L_0x03b5:
            java.util.ArrayList r1 = new java.util.ArrayList
            java.util.Set r2 = r14.keySet()
            r1.<init>(r2)
            java.util.Collections.sort(r1)
            int r2 = r1.size()
            r3 = r15
        L_0x03c6:
            if (r3 >= r2) goto L_0x0410
            java.lang.Object r4 = r1.get(r3)
            java.lang.String r4 = (java.lang.String) r4
            if (r4 == 0) goto L_0x040d
            r0.zzw()
            java.lang.Object r5 = r14.get(r4)
            boolean r6 = r5 instanceof android.os.Bundle
            if (r6 == 0) goto L_0x03e4
            r6 = 1
            android.os.Bundle[] r7 = new android.os.Bundle[r6]
            android.os.Bundle r5 = (android.os.Bundle) r5
            r7[r15] = r5
            r5 = r7
            goto L_0x0408
        L_0x03e4:
            boolean r6 = r5 instanceof android.os.Parcelable[]
            if (r6 == 0) goto L_0x03f4
            android.os.Parcelable[] r5 = (android.os.Parcelable[]) r5
            int r6 = r5.length
            java.lang.Class<android.os.Bundle[]> r7 = android.os.Bundle[].class
            java.lang.Object[] r5 = java.util.Arrays.copyOf(r5, r6, r7)
            android.os.Bundle[] r5 = (android.os.Bundle[]) r5
            goto L_0x0408
        L_0x03f4:
            boolean r6 = r5 instanceof java.util.ArrayList
            if (r6 == 0) goto L_0x0407
            java.util.ArrayList r5 = (java.util.ArrayList) r5
            int r6 = r5.size()
            android.os.Bundle[] r6 = new android.os.Bundle[r6]
            java.lang.Object[] r5 = r5.toArray(r6)
            android.os.Bundle[] r5 = (android.os.Bundle[]) r5
            goto L_0x0408
        L_0x0407:
            r5 = 0
        L_0x0408:
            if (r5 == 0) goto L_0x040d
            r14.putParcelableArray(r4, r5)
        L_0x040d:
            r4 = 1
            int r3 = r3 + r4
            goto L_0x03c6
        L_0x0410:
            r7 = r15
        L_0x0411:
            int r1 = r13.size()
            if (r7 >= r1) goto L_0x0483
            java.lang.Object r1 = r13.get(r7)
            android.os.Bundle r1 = (android.os.Bundle) r1
            if (r7 == 0) goto L_0x0426
            java.lang.String r2 = "_ep"
            r8 = r25
        L_0x0423:
            r12 = r18
            goto L_0x042a
        L_0x0426:
            r8 = r25
            r2 = r9
            goto L_0x0423
        L_0x042a:
            r1.putString(r12, r8)
            if (r31 == 0) goto L_0x043a
            com.google.android.gms.measurement.internal.zzqf r3 = r0.zzw()
            r14 = 0
            android.os.Bundle r1 = r3.zzz(r1, r14)
        L_0x0438:
            r5 = r1
            goto L_0x043c
        L_0x043a:
            r14 = 0
            goto L_0x0438
        L_0x043c:
            com.google.android.gms.measurement.internal.zzbh r6 = new com.google.android.gms.measurement.internal.zzbh
            com.google.android.gms.measurement.internal.zzbf r3 = new com.google.android.gms.measurement.internal.zzbf
            r3.<init>(r5)
            r1 = r6
            r4 = r25
            r14 = r5
            r15 = r6
            r5 = r27
            r1.<init>(r2, r3, r4, r5)
            com.google.android.gms.measurement.internal.zzny r1 = r17.zzu()
            r5 = r33
            r1.zzM(r15, r5)
            r15 = r24
            if (r16 != 0) goto L_0x047d
            java.util.Set r1 = r15.zze
            java.util.Iterator r19 = r1.iterator()
        L_0x0460:
            boolean r1 = r19.hasNext()
            if (r1 == 0) goto L_0x047d
            java.lang.Object r1 = r19.next()
            com.google.android.gms.measurement.internal.zzkc r1 = (com.google.android.gms.measurement.internal.zzkc) r1
            android.os.Bundle r4 = new android.os.Bundle
            r4.<init>(r14)
            r2 = r25
            r3 = r26
            r5 = r27
            r1.onEvent(r2, r3, r4, r5)
            r5 = r33
            goto L_0x0460
        L_0x047d:
            r1 = 1
            int r7 = r7 + r1
            r18 = r12
            r15 = 0
            goto L_0x0411
        L_0x0483:
            r15 = r24
            r0.zzaV()
            com.google.android.gms.measurement.internal.zzmo r1 = r17.zzt()
            r2 = 0
            com.google.android.gms.measurement.internal.zzmh r1 = r1.zzj(r2)
            if (r1 == 0) goto L_0x04ad
            r1 = r23
            boolean r1 = r1.equals(r9)
            if (r1 == 0) goto L_0x04ad
            com.google.android.gms.measurement.internal.zzop r1 = r17.zzv()
            com.google.android.gms.common.util.Clock r0 = r0.zzaU()
            long r2 = r0.elapsedRealtime()
            com.google.android.gms.measurement.internal.zzon r0 = r1.zzb
            r1 = 1
            r0.zzd(r1, r1, r2)
        L_0x04ad:
            return
        L_0x04ae:
            r15 = r7
            com.google.android.gms.measurement.internal.zzio r0 = r15.zzu
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzd()
            java.lang.String r1 = "Event not sent since app measurement is disabled"
            r0.zza(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzlw.zzT(java.lang.String, java.lang.String, long, android.os.Bundle, boolean, boolean, boolean, java.lang.String):void");
    }

    /* access modifiers changed from: package-private */
    @TargetApi(30)
    public final void zzU() {
        zzov zzov;
        zzg();
        this.zzn = false;
        if (!zzy().isEmpty() && !this.zzi && (zzov = (zzov) zzy().poll()) != null) {
            zzio zzio = this.zzu;
            MeasurementManagerFutures zzB = zzio.zzw().zzB();
            if (zzB != null) {
                this.zzi = true;
                zzhc zzj2 = zzio.zzaW().zzj();
                String str = zzov.zza;
                zzj2.zzb("Registering trigger URI", str);
                ListenableFuture registerTriggerAsync = zzB.registerTriggerAsync(Uri.parse(str));
                if (registerTriggerAsync == null) {
                    this.zzi = false;
                    zzy().add(zzov);
                    return;
                }
                Futures.addCallback(registerTriggerAsync, new zzkt(this, zzov), new zzks(this));
            }
        }
    }

    public final void zzV(zzkc zzkc) {
        zza();
        Preconditions.checkNotNull(zzkc);
        if (!this.zze.add(zzkc)) {
            this.zzu.zzaW().zzk().zza("OnEventListener already registered");
        }
    }

    public final void zzW() {
        zzg();
        zzio zzio = this.zzu;
        zzio.zzaW().zzd().zza("Register tcfPrefChangeListener.");
        if (this.zzs == null) {
            this.zzt = new zzky(this, this.zzu);
            this.zzs = new zzkp(this);
        }
        zzio.zzm().zza().registerOnSharedPreferenceChangeListener(this.zzs);
    }

    public final void zzX(long j) {
        this.zzg.set((Object) null);
        this.zzu.zzaX().zzq(new zzle(this, j));
    }

    /* access modifiers changed from: package-private */
    public final void zzY(Runnable runnable) {
        zzio zzio = this.zzu;
        if (zzio.zzf().zzx((String) null, zzgi.zzaR)) {
            zza();
            if (zzio.zzaX().zzu()) {
                zzio.zzaW().zze().zza("Cannot retrieve and upload batches from analytics worker thread");
            } else if (!zzio.zzaX().zzt()) {
                zzio.zzaV();
                if (!zzaf.zza()) {
                    zzio.zzaW().zzj().zza("[sgtm] Started client-side batch upload work.");
                    boolean z = false;
                    int i = 0;
                    int i2 = 0;
                    while (!z) {
                        zzio.zzaW().zzj().zza("[sgtm] Getting upload batches from service (FE)");
                        AtomicReference atomicReference = new AtomicReference();
                        zzio.zzaX().zze(atomicReference, 10000, "[sgtm] Getting upload batches", new zzkl(this, atomicReference));
                        zzpe zzpe = (zzpe) atomicReference.get();
                        if (zzpe == null) {
                            break;
                        }
                        List list = zzpe.zza;
                        if (!list.isEmpty()) {
                            zzio.zzaW().zzj().zzb("[sgtm] Retrieved upload batches. count", Integer.valueOf(list.size()));
                            i += list.size();
                            Iterator it = list.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    z = false;
                                    break;
                                }
                                zzme zzar = zzar((zzpa) it.next());
                                if (zzar == zzme.SUCCESS) {
                                    i2++;
                                } else if (zzar == zzme.BACKOFF) {
                                    z = true;
                                    break;
                                }
                            }
                        } else {
                            break;
                        }
                    }
                    zzio.zzaW().zzj().zzc("[sgtm] Completed client-side batch upload work. total, success", Integer.valueOf(i), Integer.valueOf(i2));
                    runnable.run();
                    return;
                }
                zzio.zzaW().zze().zza("Cannot retrieve and upload batches from main thread");
            } else {
                zzio.zzaW().zze().zza("Cannot retrieve and upload batches from analytics network thread");
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void zzZ(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        int i = zzqf.zza;
        Bundle bundle2 = new Bundle(bundle);
        for (String next : bundle2.keySet()) {
            Object obj = bundle2.get(next);
            if (obj instanceof Bundle) {
                bundle2.putBundle(next, new Bundle((Bundle) obj));
            } else {
                int i2 = 0;
                if (obj instanceof Parcelable[]) {
                    Parcelable[] parcelableArr = (Parcelable[]) obj;
                    while (i2 < parcelableArr.length) {
                        Parcelable parcelable = parcelableArr[i2];
                        if (parcelable instanceof Bundle) {
                            parcelableArr[i2] = new Bundle((Bundle) parcelable);
                        }
                        i2++;
                    }
                } else if (obj instanceof List) {
                    List list = (List) obj;
                    while (i2 < list.size()) {
                        Object obj2 = list.get(i2);
                        if (obj2 instanceof Bundle) {
                            list.set(i2, new Bundle((Bundle) obj2));
                        }
                        i2++;
                    }
                }
            }
        }
        this.zzu.zzaX().zzq(new zzkz(this, str, str2, j, bundle2, z, z2, z3, str3));
    }

    /* access modifiers changed from: package-private */
    public final void zzaa(String str, String str2, long j, Object obj) {
        this.zzu.zzaX().zzq(new zzla(this, str, str2, obj, j));
    }

    /* access modifiers changed from: package-private */
    public final void zzab(long j) {
        zzg();
        if (this.zzl == null) {
            this.zzl = new zzkr(this, this.zzu);
        }
        this.zzl.zzd(j);
    }

    /* access modifiers changed from: package-private */
    public final void zzac(String str) {
        this.zzg.set(str);
    }

    public final void zzad(Bundle bundle) {
        zzae(bundle, this.zzu.zzaU().currentTimeMillis());
    }

    public final void zzae(Bundle bundle, long j) {
        Preconditions.checkNotNull(bundle);
        Bundle bundle2 = new Bundle(bundle);
        if (!TextUtils.isEmpty(bundle2.getString("app_id"))) {
            this.zzu.zzaW().zzk().zza("Package name should be null when calling setConditionalUserProperty");
        }
        bundle2.remove("app_id");
        Preconditions.checkNotNull(bundle2);
        Class<String> cls = String.class;
        zzjt.zza(bundle2, "app_id", cls, (Object) null);
        zzjt.zza(bundle2, "origin", cls, (Object) null);
        zzjt.zza(bundle2, "name", cls, (Object) null);
        zzjt.zza(bundle2, "value", Object.class, (Object) null);
        zzjt.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, cls, (Object) null);
        Class<Long> cls2 = Long.class;
        zzjt.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, cls2, 0L);
        zzjt.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, cls, (Object) null);
        Class<Bundle> cls3 = Bundle.class;
        zzjt.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, cls3, (Object) null);
        zzjt.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, cls, (Object) null);
        zzjt.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, cls3, (Object) null);
        zzjt.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, cls2, 0L);
        zzjt.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, cls, (Object) null);
        zzjt.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, cls3, (Object) null);
        Preconditions.checkNotEmpty(bundle2.getString("name"));
        Preconditions.checkNotEmpty(bundle2.getString("origin"));
        Preconditions.checkNotNull(bundle2.get("value"));
        bundle2.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, j);
        String string = bundle2.getString("name");
        Object obj = bundle2.get("value");
        zzio zzio = this.zzu;
        if (zzio.zzw().zzj(string) != 0) {
            zzio.zzaW().zze().zzb("Invalid conditional user property name", zzio.zzj().zzf(string));
        } else if (zzio.zzw().zzd(string, obj) == 0) {
            Object zzE = zzio.zzw().zzE(string, obj);
            if (zzE == null) {
                zzio.zzaW().zze().zzc("Unable to normalize conditional user property value", zzio.zzj().zzf(string), obj);
                return;
            }
            zzjt.zzb(bundle2, zzE);
            long j2 = bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT);
            if (!TextUtils.isEmpty(bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME))) {
                zzio.zzf();
                if (j2 > 15552000000L || j2 < 1) {
                    zzio.zzaW().zze().zzc("Invalid conditional user property timeout", zzio.zzj().zzf(string), Long.valueOf(j2));
                    return;
                }
            }
            long j3 = bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE);
            zzio.zzf();
            if (j3 > 15552000000L || j3 < 1) {
                zzio.zzaW().zze().zzc("Invalid conditional user property time to live", zzio.zzj().zzf(string), Long.valueOf(j3));
            } else {
                zzio.zzaX().zzq(new zzlf(this, bundle2));
            }
        } else {
            zzio.zzaW().zze().zzc("Invalid conditional user property value", zzio.zzj().zzf(string), obj);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: java.lang.Boolean} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzaf(android.os.Bundle r9, int r10, long r11) {
        /*
            r8 = this;
            r8.zza()
            com.google.android.gms.measurement.internal.zzjx r0 = com.google.android.gms.measurement.internal.zzjx.zza
            com.google.android.gms.measurement.internal.zzjv r0 = com.google.android.gms.measurement.internal.zzjv.STORAGE
            com.google.android.gms.measurement.internal.zzjw[] r0 = r0.zzd
            int r1 = r0.length
            r2 = 0
        L_0x000d:
            r3 = 0
            if (r2 >= r1) goto L_0x003c
            r4 = r0[r2]
            java.lang.String r4 = r4.zze
            boolean r5 = r9.containsKey(r4)
            if (r5 == 0) goto L_0x0039
            java.lang.String r4 = r9.getString(r4)
            if (r4 == 0) goto L_0x0039
            java.lang.String r5 = "granted"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x002b
            java.lang.Boolean r3 = java.lang.Boolean.TRUE
            goto L_0x0035
        L_0x002b:
            java.lang.String r5 = "denied"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0035
            java.lang.Boolean r3 = java.lang.Boolean.FALSE
        L_0x0035:
            if (r3 != 0) goto L_0x0039
            r3 = r4
            goto L_0x003c
        L_0x0039:
            int r2 = r2 + 1
            goto L_0x000d
        L_0x003c:
            if (r3 == 0) goto L_0x005a
            com.google.android.gms.measurement.internal.zzio r0 = r8.zzu
            com.google.android.gms.measurement.internal.zzhe r1 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zzl()
            java.lang.String r2 = "Ignoring invalid consent setting"
            r1.zzb(r2, r3)
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzl()
            java.lang.String r1 = "Valid consent values are 'granted', 'denied'"
            r0.zza(r1)
        L_0x005a:
            com.google.android.gms.measurement.internal.zzio r0 = r8.zzu
            com.google.android.gms.measurement.internal.zzil r0 = r0.zzaX()
            boolean r0 = r0.zzu()
            com.google.android.gms.measurement.internal.zzjx r1 = com.google.android.gms.measurement.internal.zzjx.zzi(r9, r10)
            boolean r2 = r1.zzt()
            if (r2 == 0) goto L_0x0071
            r8.zzak(r1, r0)
        L_0x0071:
            com.google.android.gms.measurement.internal.zzba r1 = com.google.android.gms.measurement.internal.zzba.zzc(r9, r10)
            boolean r2 = r1.zzk()
            if (r2 == 0) goto L_0x007e
            r8.zzag(r1, r0)
        L_0x007e:
            java.lang.Boolean r9 = com.google.android.gms.measurement.internal.zzba.zzg(r9)
            if (r9 == 0) goto L_0x00a9
            r1 = -30
            if (r10 != r1) goto L_0x008c
            java.lang.String r10 = "tcf"
        L_0x008a:
            r2 = r10
            goto L_0x008f
        L_0x008c:
            java.lang.String r10 = "app"
            goto L_0x008a
        L_0x008f:
            if (r0 == 0) goto L_0x009d
            java.lang.String r4 = r9.toString()
            java.lang.String r3 = "allow_personalized_ads"
            r1 = r8
            r5 = r11
            r1.zzan(r2, r3, r4, r5)
            return
        L_0x009d:
            java.lang.String r4 = r9.toString()
            java.lang.String r3 = "allow_personalized_ads"
            r5 = 0
            r1 = r8
            r6 = r11
            r1.zzam(r2, r3, r4, r5, r6)
        L_0x00a9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzlw.zzaf(android.os.Bundle, int, long):void");
    }

    public final void zzah(zzkb zzkb) {
        zzkb zzkb2;
        zzg();
        zza();
        if (!(zzkb == null || zzkb == (zzkb2 = this.zzd))) {
            Preconditions.checkState(zzkb2 == null, "EventInterceptor already set.");
        }
        this.zzd = zzkb;
    }

    public final void zzai(Boolean bool) {
        zza();
        this.zzu.zzaX().zzq(new zzlp(this, bool));
    }

    /* access modifiers changed from: package-private */
    public final void zzaj(zzjx zzjx) {
        boolean z;
        zzg();
        if ((!zzjx.zzr(zzjw.ANALYTICS_STORAGE) || !zzjx.zzr(zzjw.AD_STORAGE)) && !this.zzu.zzu().zzab()) {
            z = false;
        } else {
            z = true;
        }
        zzio zzio = this.zzu;
        if (z != zzio.zzK()) {
            zzio.zzG(z);
            zzht zzm2 = this.zzu.zzm();
            zzio zzio2 = zzm2.zzu;
            zzm2.zzg();
            Boolean valueOf = zzm2.zzb().contains("measurement_enabled_from_api") ? Boolean.valueOf(zzm2.zzb().getBoolean("measurement_enabled_from_api", true)) : null;
            if (!z || valueOf == null || valueOf.booleanValue()) {
                zzas(Boolean.valueOf(z), false);
            }
        }
    }

    public final void zzak(zzjx zzjx, boolean z) {
        boolean z2;
        boolean z3;
        zzjx zzjx2;
        boolean z4;
        zzju zzju;
        zza();
        int zzb2 = zzjx.zzb();
        if (zzb2 != -10 && zzjx.zze() == (zzju = zzju.UNINITIALIZED) && zzjx.zzf() == zzju) {
            this.zzu.zzaW().zzl().zza("Ignoring empty consent settings");
            return;
        }
        synchronized (this.zzh) {
            try {
                z2 = false;
                if (zzjx.zzs(zzb2, this.zzo.zzb())) {
                    z4 = zzjx.zzu(this.zzo);
                    zzjw zzjw = zzjw.ANALYTICS_STORAGE;
                    if (zzjx.zzr(zzjw) && !this.zzo.zzr(zzjw)) {
                        z2 = true;
                    }
                    zzjx zzm2 = zzjx.zzm(this.zzo);
                    this.zzo = zzm2;
                    zzjx2 = zzm2;
                    z3 = z2;
                    z2 = true;
                } else {
                    zzjx2 = zzjx;
                    z4 = false;
                    z3 = false;
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (!z2) {
            this.zzu.zzaW().zzi().zzb("Ignoring lower-priority consent settings, proposed settings", zzjx2);
            return;
        }
        long andIncrement = this.zzp.getAndIncrement();
        if (z4) {
            this.zzg.set((Object) null);
            zzlr zzlr = new zzlr(this, zzjx2, andIncrement, z3);
            if (z) {
                zzg();
                zzlr.run();
                return;
            }
            this.zzu.zzaX().zzr(zzlr);
            return;
        }
        zzls zzls = new zzls(this, zzjx2, andIncrement, z3);
        if (z) {
            zzg();
            zzls.run();
        } else if (zzb2 == 30 || zzb2 == -10) {
            this.zzu.zzaX().zzr(zzls);
        } else {
            this.zzu.zzaX().zzq(zzls);
        }
    }

    public final void zzal(String str, String str2, Object obj, boolean z) {
        zzam(str, str2, obj, z, this.zzu.zzaU().currentTimeMillis());
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzan(java.lang.String r10, java.lang.String r11, java.lang.Object r12, long r13) {
        /*
            r9 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r10)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r11)
            r9.zzg()
            r9.zza()
            java.lang.String r0 = "allow_personalized_ads"
            boolean r0 = r0.equals(r11)
            if (r0 == 0) goto L_0x0074
            boolean r0 = r12 instanceof java.lang.String
            java.lang.String r1 = "_npa"
            if (r0 == 0) goto L_0x0053
            r0 = r12
            java.lang.String r0 = (java.lang.String) r0
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 != 0) goto L_0x0053
            java.util.Locale r11 = java.util.Locale.ENGLISH
            java.lang.String r11 = r0.toLowerCase(r11)
            r12 = 1
            java.lang.String r0 = "false"
            boolean r11 = r0.equals(r11)
            r2 = 1
            if (r12 == r11) goto L_0x0037
            r11 = 0
            goto L_0x0038
        L_0x0037:
            r11 = r2
        L_0x0038:
            com.google.android.gms.measurement.internal.zzio r4 = r9.zzu
            java.lang.Long r12 = java.lang.Long.valueOf(r11)
            com.google.android.gms.measurement.internal.zzht r11 = r4.zzm()
            com.google.android.gms.measurement.internal.zzhr r11 = r11.zzh
            long r4 = r12.longValue()
            int r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r2 != 0) goto L_0x004e
            java.lang.String r0 = "true"
        L_0x004e:
            r11.zzb(r0)
        L_0x0051:
            r11 = r1
            goto L_0x0063
        L_0x0053:
            if (r12 != 0) goto L_0x0063
            com.google.android.gms.measurement.internal.zzio r11 = r9.zzu
            com.google.android.gms.measurement.internal.zzht r11 = r11.zzm()
            com.google.android.gms.measurement.internal.zzhr r11 = r11.zzh
            java.lang.String r0 = "unset"
            r11.zzb(r0)
            goto L_0x0051
        L_0x0063:
            com.google.android.gms.measurement.internal.zzio r0 = r9.zzu
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzj()
            java.lang.String r1 = "Setting user property(FE)"
            java.lang.String r2 = "non_personalized_ads(_npa)"
            r0.zzc(r1, r2, r12)
        L_0x0074:
            r4 = r11
            r7 = r12
            com.google.android.gms.measurement.internal.zzio r11 = r9.zzu
            boolean r12 = r11.zzJ()
            if (r12 != 0) goto L_0x008e
            com.google.android.gms.measurement.internal.zzio r10 = r9.zzu
            com.google.android.gms.measurement.internal.zzhe r10 = r10.zzaW()
            com.google.android.gms.measurement.internal.zzhc r10 = r10.zzj()
            java.lang.String r11 = "User property not set since app measurement is disabled"
            r10.zza(r11)
            return
        L_0x008e:
            boolean r11 = r11.zzM()
            if (r11 != 0) goto L_0x0095
            return
        L_0x0095:
            com.google.android.gms.measurement.internal.zzio r11 = r9.zzu
            com.google.android.gms.measurement.internal.zzqb r12 = new com.google.android.gms.measurement.internal.zzqb
            r3 = r12
            r5 = r13
            r8 = r10
            r3.<init>(r4, r5, r7, r8)
            com.google.android.gms.measurement.internal.zzny r10 = r11.zzu()
            r10.zzY(r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzlw.zzan(java.lang.String, java.lang.String, java.lang.Object, long):void");
    }

    public final void zzao(zzkc zzkc) {
        zza();
        Preconditions.checkNotNull(zzkc);
        if (!this.zze.remove(zzkc)) {
            this.zzu.zzaW().zzk().zza("OnEventListener had not been registered");
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zzap() {
        return this.zzn;
    }

    /* access modifiers changed from: protected */
    public final boolean zzf() {
        return false;
    }

    public final int zzi(String str) {
        Preconditions.checkNotEmpty(str);
        this.zzu.zzf();
        return 25;
    }

    public final Boolean zzl() {
        AtomicReference atomicReference = new AtomicReference();
        return (Boolean) this.zzu.zzaX().zze(atomicReference, 15000, "boolean test flag value", new zzlb(this, atomicReference));
    }

    public final Double zzm() {
        AtomicReference atomicReference = new AtomicReference();
        return (Double) this.zzu.zzaX().zze(atomicReference, 15000, "double test flag value", new zzlo(this, atomicReference));
    }

    public final Integer zzp() {
        AtomicReference atomicReference = new AtomicReference();
        return (Integer) this.zzu.zzaX().zze(atomicReference, 15000, "int test flag value", new zzln(this, atomicReference));
    }

    public final Long zzq() {
        AtomicReference atomicReference = new AtomicReference();
        return (Long) this.zzu.zzaX().zze(atomicReference, 15000, "long test flag value", new zzlm(this, atomicReference));
    }

    public final String zzr() {
        return (String) this.zzg.get();
    }

    public final String zzs() {
        zzmh zzi2 = this.zzu.zzt().zzi();
        if (zzi2 != null) {
            return zzi2.zzb;
        }
        return null;
    }

    public final String zzt() {
        zzmh zzi2 = this.zzu.zzt().zzi();
        if (zzi2 != null) {
            return zzi2.zza;
        }
        return null;
    }

    public final String zzu() {
        AtomicReference atomicReference = new AtomicReference();
        return (String) this.zzu.zzaX().zze(atomicReference, 15000, "String test flag value", new zzll(this, atomicReference));
    }

    public final ArrayList zzv(String str, String str2) {
        zzio zzio = this.zzu;
        if (zzio.zzaX().zzu()) {
            zzio.zzaW().zze().zza("Cannot get conditional user properties from analytics worker thread");
            return new ArrayList(0);
        }
        zzio.zzaV();
        if (zzaf.zza()) {
            zzio.zzaW().zze().zza("Cannot get conditional user properties from main thread");
            return new ArrayList(0);
        }
        AtomicReference atomicReference = new AtomicReference();
        this.zzu.zzaX().zze(atomicReference, 5000, "get conditional user properties", new zzlh(this, atomicReference, (String) null, str, str2));
        List list = (List) atomicReference.get();
        if (list != null) {
            return zzqf.zzK(list);
        }
        zzio.zzaW().zze().zzb("Timed out waiting for get conditional user properties", (Object) null);
        return new ArrayList();
    }

    public final List zzw(boolean z) {
        zza();
        zzio zzio = this.zzu;
        zzio.zzaW().zzj().zza("Getting user properties (FE)");
        if (!zzio.zzaX().zzu()) {
            zzio.zzaV();
            if (zzaf.zza()) {
                zzio.zzaW().zze().zza("Cannot get all user properties from main thread");
                return Collections.emptyList();
            }
            AtomicReference atomicReference = new AtomicReference();
            this.zzu.zzaX().zze(atomicReference, 5000, "get user properties", new zzlc(this, atomicReference, z));
            List list = (List) atomicReference.get();
            if (list != null) {
                return list;
            }
            zzio.zzaW().zze().zzb("Timed out waiting for get user properties, includeInternal", Boolean.valueOf(z));
            return Collections.emptyList();
        }
        zzio.zzaW().zze().zza("Cannot get all user properties from analytics worker thread");
        return Collections.emptyList();
    }

    public final Map zzx(String str, String str2, boolean z) {
        zzio zzio = this.zzu;
        if (zzio.zzaX().zzu()) {
            zzio.zzaW().zze().zza("Cannot get user properties from analytics worker thread");
            return Collections.emptyMap();
        }
        zzio.zzaV();
        if (zzaf.zza()) {
            zzio.zzaW().zze().zza("Cannot get user properties from main thread");
            return Collections.emptyMap();
        }
        AtomicReference atomicReference = new AtomicReference();
        this.zzu.zzaX().zze(atomicReference, 5000, "get user properties", new zzli(this, atomicReference, (String) null, str, str2, z));
        List<zzqb> list = (List) atomicReference.get();
        if (list == null) {
            zzio.zzaW().zze().zzb("Timed out waiting for handle get user properties, includeInternal", Boolean.valueOf(z));
            return Collections.emptyMap();
        }
        ArrayMap arrayMap = new ArrayMap(list.size());
        for (zzqb zzqb : list) {
            Object zza2 = zzqb.zza();
            if (zza2 != null) {
                arrayMap.put(zzqb.zzb, zza2);
            }
        }
        return arrayMap;
    }

    /* access modifiers changed from: package-private */
    @TargetApi(30)
    public final PriorityQueue zzy() {
        if (this.zzm == null) {
            this.zzm = new PriorityQueue(Comparator.comparing(new zzkf(), new zzkh()));
        }
        return this.zzm;
    }

    /* access modifiers changed from: package-private */
    public final void zzag(zzba zzba, boolean z) {
        zzlq zzlq = new zzlq(this, zzba);
        if (z) {
            zzg();
            zzlq.run();
            return;
        }
        this.zzu.zzaX().zzq(zzlq);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0067  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzam(java.lang.String r17, java.lang.String r18, java.lang.Object r19, boolean r20, long r21) {
        /*
            r16 = this;
            r6 = r16
            r2 = r18
            r0 = r19
            r1 = 0
            r3 = 24
            if (r20 == 0) goto L_0x0017
            com.google.android.gms.measurement.internal.zzio r4 = r6.zzu
            com.google.android.gms.measurement.internal.zzqf r4 = r4.zzw()
            int r4 = r4.zzj(r2)
        L_0x0015:
            r12 = r4
            goto L_0x0041
        L_0x0017:
            com.google.android.gms.measurement.internal.zzio r4 = r6.zzu
            com.google.android.gms.measurement.internal.zzqf r4 = r4.zzw()
            java.lang.String r5 = "user property"
            boolean r7 = r4.zzah(r5, r2)
            r8 = 6
            if (r7 != 0) goto L_0x0028
        L_0x0026:
            r12 = r8
            goto L_0x0041
        L_0x0028:
            java.lang.String[] r7 = com.google.android.gms.measurement.internal.zzka.zza
            r9 = 0
            boolean r7 = r4.zzae(r5, r7, r9, r2)
            if (r7 != 0) goto L_0x0034
            r4 = 15
            goto L_0x0015
        L_0x0034:
            com.google.android.gms.measurement.internal.zzio r7 = r4.zzu
            r7.zzf()
            boolean r4 = r4.zzad(r5, r3, r2)
            if (r4 != 0) goto L_0x0040
            goto L_0x0026
        L_0x0040:
            r12 = r1
        L_0x0041:
            r4 = 1
            if (r12 == 0) goto L_0x0067
            com.google.android.gms.measurement.internal.zzio r0 = r6.zzu
            com.google.android.gms.measurement.internal.zzqf r5 = r0.zzw()
            r0.zzf()
            java.lang.String r14 = r5.zzG(r2, r3, r4)
            if (r2 == 0) goto L_0x0057
            int r1 = r18.length()
        L_0x0057:
            r15 = r1
            com.google.android.gms.measurement.internal.zzio r0 = r6.zzu
            com.google.android.gms.measurement.internal.zzqe r10 = r6.zzv
            com.google.android.gms.measurement.internal.zzqf r9 = r0.zzw()
            r11 = 0
            java.lang.String r13 = "_ev"
            r9.zzR(r10, r11, r12, r13, r14, r15)
            return
        L_0x0067:
            if (r17 != 0) goto L_0x006c
            java.lang.String r5 = "app"
            goto L_0x006e
        L_0x006c:
            r5 = r17
        L_0x006e:
            if (r0 == 0) goto L_0x00c0
            com.google.android.gms.measurement.internal.zzio r7 = r6.zzu
            com.google.android.gms.measurement.internal.zzqf r8 = r7.zzw()
            int r12 = r8.zzd(r2, r0)
            if (r12 == 0) goto L_0x00aa
            com.google.android.gms.measurement.internal.zzqf r5 = r7.zzw()
            r7.zzf()
            java.lang.String r14 = r5.zzG(r2, r3, r4)
            boolean r2 = r0 instanceof java.lang.String
            if (r2 != 0) goto L_0x0092
            boolean r2 = r0 instanceof java.lang.CharSequence
            if (r2 == 0) goto L_0x0090
            goto L_0x0092
        L_0x0090:
            r15 = r1
            goto L_0x009b
        L_0x0092:
            java.lang.String r0 = r19.toString()
            int r1 = r0.length()
            goto L_0x0090
        L_0x009b:
            com.google.android.gms.measurement.internal.zzio r0 = r6.zzu
            com.google.android.gms.measurement.internal.zzqe r10 = r6.zzv
            com.google.android.gms.measurement.internal.zzqf r9 = r0.zzw()
            r11 = 0
            java.lang.String r13 = "_ev"
            r9.zzR(r10, r11, r12, r13, r14, r15)
            return
        L_0x00aa:
            com.google.android.gms.measurement.internal.zzqf r1 = r7.zzw()
            java.lang.Object r7 = r1.zzE(r2, r0)
            if (r7 == 0) goto L_0x00bf
            r0 = r16
            r1 = r5
            r2 = r18
            r3 = r21
            r5 = r7
            r0.zzaa(r1, r2, r3, r5)
        L_0x00bf:
            return
        L_0x00c0:
            r7 = 0
            r0 = r16
            r1 = r5
            r2 = r18
            r3 = r21
            r5 = r7
            r0.zzaa(r1, r2, r3, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzlw.zzam(java.lang.String, java.lang.String, java.lang.Object, boolean, long):void");
    }
}
