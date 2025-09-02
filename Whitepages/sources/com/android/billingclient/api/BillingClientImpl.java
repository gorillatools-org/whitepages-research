package com.android.billingclient.api;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import com.android.billingclient.api.QueryProductDetailsParams;
import com.android.billingclient.ktx.BuildConfig;
import com.google.android.gms.internal.play_billing.zzaa;
import com.google.android.gms.internal.play_billing.zzai;
import com.google.android.gms.internal.play_billing.zzb;
import com.google.android.gms.internal.play_billing.zzga;
import com.google.android.gms.internal.play_billing.zzge;
import com.google.android.gms.internal.play_billing.zzgt;
import com.google.android.gms.internal.play_billing.zzgu;
import com.google.android.gms.internal.play_billing.zzs;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.json.JSONException;

class BillingClientImpl extends BillingClient {
    private boolean zzA;
    private ExecutorService zzB;
    /* access modifiers changed from: private */
    public volatile int zza;
    private final String zzb;
    private final Handler zzc;
    /* access modifiers changed from: private */
    public volatile zzo zzd;
    /* access modifiers changed from: private */
    public Context zze;
    /* access modifiers changed from: private */
    public zzcc zzf;
    /* access modifiers changed from: private */
    public volatile zzs zzg;
    private volatile zzbc zzh;
    /* access modifiers changed from: private */
    public boolean zzi;
    /* access modifiers changed from: private */
    public boolean zzj;
    /* access modifiers changed from: private */
    public int zzk;
    /* access modifiers changed from: private */
    public boolean zzl;
    /* access modifiers changed from: private */
    public boolean zzm;
    /* access modifiers changed from: private */
    public boolean zzn;
    /* access modifiers changed from: private */
    public boolean zzo;
    /* access modifiers changed from: private */
    public boolean zzp;
    /* access modifiers changed from: private */
    public boolean zzq;
    /* access modifiers changed from: private */
    public boolean zzr;
    /* access modifiers changed from: private */
    public boolean zzs;
    /* access modifiers changed from: private */
    public boolean zzt;
    /* access modifiers changed from: private */
    public boolean zzu;
    /* access modifiers changed from: private */
    public boolean zzv;
    /* access modifiers changed from: private */
    public boolean zzw;
    /* access modifiers changed from: private */
    public boolean zzx;
    /* access modifiers changed from: private */
    public boolean zzy;
    private PendingPurchasesParams zzz;

    private void initialize(Context context, PurchasesUpdatedListener purchasesUpdatedListener, PendingPurchasesParams pendingPurchasesParams, zzc zzc2, String str, zzcc zzcc) {
        this.zze = context.getApplicationContext();
        zzgt zzy2 = zzgu.zzy();
        zzy2.zzn(str);
        zzy2.zzm(this.zze.getPackageName());
        if (zzcc != null) {
            this.zzf = zzcc;
        } else {
            this.zzf = new zzch(this.zze, (zzgu) zzy2.zzf());
        }
        if (purchasesUpdatedListener == null) {
            zzb.zzk("BillingClient", "Billing client should have a valid listener but the provided is null.");
        }
        this.zzd = new zzo(this.zze, purchasesUpdatedListener, (zzck) null, zzc2, (UserChoiceBillingListener) null, this.zzf);
        this.zzz = pendingPurchasesParams;
        this.zzA = zzc2 != null;
        this.zze.getPackageName();
    }

    static /* bridge */ /* synthetic */ zzcz zzag(BillingClientImpl billingClientImpl, String str, int i) {
        Bundle bundle;
        zzcz zzcz;
        BillingClientImpl billingClientImpl2 = billingClientImpl;
        zzb.zzj("BillingClient", "Querying owned items, item type: ".concat(String.valueOf(str)));
        ArrayList arrayList = new ArrayList();
        Bundle zzc2 = zzb.zzc(billingClientImpl2.zzn, billingClientImpl2.zzv, billingClientImpl2.zzz.isEnabledForOneTimeProducts(), billingClientImpl2.zzz.isEnabledForPrepaidPlans(), billingClientImpl2.zzb);
        List list = null;
        String str2 = null;
        while (true) {
            try {
                if (billingClientImpl2.zzn) {
                    bundle = billingClientImpl2.zzg.zzj(true != billingClientImpl2.zzv ? 9 : 19, billingClientImpl2.zze.getPackageName(), str, str2, zzc2);
                    String str3 = str;
                } else {
                    bundle = billingClientImpl2.zzg.zzi(3, billingClientImpl2.zze.getPackageName(), str, str2);
                }
                zzda zza2 = zzdb.zza(bundle, "BillingClient", "getPurchase()");
                BillingResult zza3 = zza2.zza();
                if (zza3 != zzce.zzl) {
                    billingClientImpl2.zzap(zzcb.zza(zza2.zzb(), 9, zza3));
                    return new zzcz(zza3, list);
                }
                ArrayList<String> stringArrayList = bundle.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
                ArrayList<String> stringArrayList2 = bundle.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
                ArrayList<String> stringArrayList3 = bundle.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
                int i2 = 0;
                boolean z = false;
                while (i2 < stringArrayList2.size()) {
                    String str4 = stringArrayList2.get(i2);
                    String str5 = stringArrayList3.get(i2);
                    zzb.zzj("BillingClient", "Sku is owned: ".concat(String.valueOf(stringArrayList.get(i2))));
                    try {
                        Purchase purchase = new Purchase(str4, str5);
                        if (TextUtils.isEmpty(purchase.getPurchaseToken())) {
                            zzb.zzk("BillingClient", "BUG: empty/null token!");
                            z = true;
                        }
                        arrayList.add(purchase);
                        i2++;
                    } catch (JSONException e) {
                        zzb.zzl("BillingClient", "Got an exception trying to decode the purchase!", e);
                        BillingResult billingResult = zzce.zzj;
                        billingClientImpl2.zzap(zzcb.zza(51, 9, billingResult));
                        zzcz = new zzcz(billingResult, (List) null);
                    }
                }
                if (z) {
                    billingClientImpl2.zzap(zzcb.zza(26, 9, zzce.zzj));
                }
                str2 = bundle.getString("INAPP_CONTINUATION_TOKEN");
                zzb.zzj("BillingClient", "Continuation token: ".concat(String.valueOf(str2)));
                if (TextUtils.isEmpty(str2)) {
                    zzcz = new zzcz(zzce.zzl, arrayList);
                    break;
                }
                list = null;
            } catch (Exception e2) {
                BillingResult billingResult2 = zzce.zzm;
                billingClientImpl2.zzap(zzcb.zza(52, 9, billingResult2));
                zzb.zzl("BillingClient", "Got exception trying to get purchasesm try to reconnect", e2);
                return new zzcz(billingResult2, (List) null);
            }
        }
        return zzcz;
    }

    /* access modifiers changed from: private */
    public final Handler zzaj() {
        return Looper.myLooper() == null ? this.zzc : new Handler(Looper.myLooper());
    }

    private final BillingResult zzak(BillingResult billingResult) {
        if (Thread.interrupted()) {
            return billingResult;
        }
        this.zzc.post(new zzq(this, billingResult));
        return billingResult;
    }

    /* access modifiers changed from: private */
    public final BillingResult zzal() {
        if (this.zza == 0 || this.zza == 3) {
            return zzce.zzm;
        }
        return zzce.zzj;
    }

    private final String zzam(QueryProductDetailsParams queryProductDetailsParams) {
        if (!TextUtils.isEmpty((CharSequence) null)) {
            return null;
        }
        return this.zze.getPackageName();
    }

    private static String zzan() {
        try {
            return (String) BuildConfig.class.getField("VERSION_NAME").get((Object) null);
        } catch (Exception unused) {
            return com.android.billingclient.BuildConfig.VERSION_NAME;
        }
    }

    /* access modifiers changed from: private */
    public final Future zzao(Callable callable, long j, Runnable runnable, Handler handler) {
        if (this.zzB == null) {
            this.zzB = Executors.newFixedThreadPool(zzb.zza, new zzat(this));
        }
        try {
            Future submit = this.zzB.submit(callable);
            handler.postDelayed(new zzy(submit, runnable), (long) (((double) j) * 0.95d));
            return submit;
        } catch (Exception e) {
            zzb.zzl("BillingClient", "Async task throws exception!", e);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public final void zzap(zzga zzga) {
        this.zzf.zzb(zzga, this.zzk);
    }

    /* access modifiers changed from: private */
    public final void zzaq(zzge zzge) {
        this.zzf.zzd(zzge, this.zzk);
    }

    private final void zzar(String str, PurchaseHistoryResponseListener purchaseHistoryResponseListener) {
        if (!isReady()) {
            BillingResult billingResult = zzce.zzm;
            zzap(zzcb.zza(2, 11, billingResult));
            purchaseHistoryResponseListener.onPurchaseHistoryResponse(billingResult, (List) null);
            return;
        }
        if (zzao(new zzav(this, str, purchaseHistoryResponseListener), 30000, new zzam(this, purchaseHistoryResponseListener), zzaj()) == null) {
            BillingResult zzal = zzal();
            zzap(zzcb.zza(25, 11, zzal));
            purchaseHistoryResponseListener.onPurchaseHistoryResponse(zzal, (List) null);
        }
    }

    private final void zzas(String str, PurchasesResponseListener purchasesResponseListener) {
        if (!isReady()) {
            BillingResult billingResult = zzce.zzm;
            zzap(zzcb.zza(2, 9, billingResult));
            purchasesResponseListener.onQueryPurchasesResponse(billingResult, zzai.zzk());
        } else if (TextUtils.isEmpty(str)) {
            zzb.zzk("BillingClient", "Please provide a valid product type.");
            BillingResult billingResult2 = zzce.zzg;
            zzap(zzcb.zza(50, 9, billingResult2));
            purchasesResponseListener.onQueryPurchasesResponse(billingResult2, zzai.zzk());
        } else {
            if (zzao(new zzau(this, str, purchasesResponseListener), 30000, new zzae(this, purchasesResponseListener), zzaj()) == null) {
                BillingResult zzal = zzal();
                zzap(zzcb.zza(25, 9, zzal));
                purchasesResponseListener.onQueryPurchasesResponse(zzal, zzai.zzk());
            }
        }
    }

    private final boolean zzat() {
        return this.zzv && this.zzz.isEnabledForPrepaidPlans();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: com.google.android.gms.internal.play_billing.zzge} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: com.google.android.gms.internal.play_billing.zzga} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v2 */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzau(com.android.billingclient.api.BillingResult r8, int r9, int r10) {
        /*
            r7 = this;
            int r0 = r8.getResponseCode()
            r1 = 0
            java.lang.String r2 = "Unable to create logging payload"
            java.lang.String r3 = "BillingLogger"
            r4 = 5
            if (r0 == 0) goto L_0x004d
            int r0 = com.android.billingclient.api.zzcb.$r8$clinit
            com.google.android.gms.internal.play_billing.zzfz r0 = com.google.android.gms.internal.play_billing.zzga.zzy()     // Catch:{ Exception -> 0x0045 }
            com.google.android.gms.internal.play_billing.zzgg r5 = com.google.android.gms.internal.play_billing.zzgk.zzy()     // Catch:{ Exception -> 0x0045 }
            int r6 = r8.getResponseCode()     // Catch:{ Exception -> 0x0045 }
            r5.zzn(r6)     // Catch:{ Exception -> 0x0045 }
            java.lang.String r8 = r8.getDebugMessage()     // Catch:{ Exception -> 0x0045 }
            r5.zzm(r8)     // Catch:{ Exception -> 0x0045 }
            r5.zzo(r9)     // Catch:{ Exception -> 0x0045 }
            r0.zzl(r5)     // Catch:{ Exception -> 0x0045 }
            r0.zzn(r4)     // Catch:{ Exception -> 0x0045 }
            com.google.android.gms.internal.play_billing.zzgz r8 = com.google.android.gms.internal.play_billing.zzhb.zzy()     // Catch:{ Exception -> 0x0045 }
            r8.zzl(r10)     // Catch:{ Exception -> 0x0045 }
            com.google.android.gms.internal.play_billing.zzcs r8 = r8.zzf()     // Catch:{ Exception -> 0x0045 }
            com.google.android.gms.internal.play_billing.zzhb r8 = (com.google.android.gms.internal.play_billing.zzhb) r8     // Catch:{ Exception -> 0x0045 }
            r0.zzm(r8)     // Catch:{ Exception -> 0x0045 }
            com.google.android.gms.internal.play_billing.zzcs r8 = r0.zzf()     // Catch:{ Exception -> 0x0045 }
            com.google.android.gms.internal.play_billing.zzga r8 = (com.google.android.gms.internal.play_billing.zzga) r8     // Catch:{ Exception -> 0x0045 }
            r1 = r8
            goto L_0x0049
        L_0x0045:
            r8 = move-exception
            com.google.android.gms.internal.play_billing.zzb.zzl(r3, r2, r8)
        L_0x0049:
            r7.zzap(r1)
            return
        L_0x004d:
            int r8 = com.android.billingclient.api.zzcb.$r8$clinit
            com.google.android.gms.internal.play_billing.zzgd r8 = com.google.android.gms.internal.play_billing.zzge.zzy()     // Catch:{ Exception -> 0x006e }
            r8.zzm(r4)     // Catch:{ Exception -> 0x006e }
            com.google.android.gms.internal.play_billing.zzgz r9 = com.google.android.gms.internal.play_billing.zzhb.zzy()     // Catch:{ Exception -> 0x006e }
            r9.zzl(r10)     // Catch:{ Exception -> 0x006e }
            com.google.android.gms.internal.play_billing.zzcs r9 = r9.zzf()     // Catch:{ Exception -> 0x006e }
            com.google.android.gms.internal.play_billing.zzhb r9 = (com.google.android.gms.internal.play_billing.zzhb) r9     // Catch:{ Exception -> 0x006e }
            r8.zzl(r9)     // Catch:{ Exception -> 0x006e }
            com.google.android.gms.internal.play_billing.zzcs r8 = r8.zzf()     // Catch:{ Exception -> 0x006e }
            com.google.android.gms.internal.play_billing.zzge r8 = (com.google.android.gms.internal.play_billing.zzge) r8     // Catch:{ Exception -> 0x006e }
            r1 = r8
            goto L_0x0072
        L_0x006e:
            r8 = move-exception
            com.google.android.gms.internal.play_billing.zzb.zzl(r3, r2, r8)
        L_0x0072:
            r7.zzaq(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.billingclient.api.BillingClientImpl.zzau(com.android.billingclient.api.BillingResult, int, int):void");
    }

    static /* bridge */ /* synthetic */ zzbt zzg(BillingClientImpl billingClientImpl, String str) {
        zzbt zzbt;
        BillingClientImpl billingClientImpl2 = billingClientImpl;
        zzb.zzj("BillingClient", "Querying purchase history, item type: ".concat(String.valueOf(str)));
        ArrayList arrayList = new ArrayList();
        Bundle zzc2 = zzb.zzc(billingClientImpl2.zzn, billingClientImpl2.zzv, billingClientImpl2.zzz.isEnabledForOneTimeProducts(), billingClientImpl2.zzz.isEnabledForPrepaidPlans(), billingClientImpl2.zzb);
        String str2 = null;
        while (billingClientImpl2.zzl) {
            try {
                Bundle zzh2 = billingClientImpl2.zzg.zzh(6, billingClientImpl2.zze.getPackageName(), str, str2, zzc2);
                zzda zza2 = zzdb.zza(zzh2, "BillingClient", "getPurchaseHistory()");
                BillingResult zza3 = zza2.zza();
                if (zza3 != zzce.zzl) {
                    billingClientImpl2.zzap(zzcb.zza(zza2.zzb(), 11, zza3));
                    return new zzbt(zza3, (List) null);
                }
                ArrayList<String> stringArrayList = zzh2.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
                ArrayList<String> stringArrayList2 = zzh2.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
                ArrayList<String> stringArrayList3 = zzh2.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
                int i = 0;
                boolean z = false;
                while (i < stringArrayList2.size()) {
                    String str3 = stringArrayList2.get(i);
                    String str4 = stringArrayList3.get(i);
                    zzb.zzj("BillingClient", "Purchase record found for sku : ".concat(String.valueOf(stringArrayList.get(i))));
                    try {
                        PurchaseHistoryRecord purchaseHistoryRecord = new PurchaseHistoryRecord(str3, str4);
                        if (TextUtils.isEmpty(purchaseHistoryRecord.getPurchaseToken())) {
                            zzb.zzk("BillingClient", "BUG: empty/null token!");
                            z = true;
                        }
                        arrayList.add(purchaseHistoryRecord);
                        i++;
                    } catch (JSONException e) {
                        zzb.zzl("BillingClient", "Got an exception trying to decode the purchase!", e);
                        BillingResult billingResult = zzce.zzj;
                        billingClientImpl2.zzap(zzcb.zza(51, 11, billingResult));
                        zzbt = new zzbt(billingResult, (List) null);
                    }
                }
                if (z) {
                    billingClientImpl2.zzap(zzcb.zza(26, 11, zzce.zzj));
                }
                str2 = zzh2.getString("INAPP_CONTINUATION_TOKEN");
                zzb.zzj("BillingClient", "Continuation token: ".concat(String.valueOf(str2)));
                if (TextUtils.isEmpty(str2)) {
                    zzbt = new zzbt(zzce.zzl, arrayList);
                    return zzbt;
                }
            } catch (RemoteException e2) {
                zzb.zzl("BillingClient", "Got exception trying to get purchase history, try to reconnect", e2);
                BillingResult billingResult2 = zzce.zzm;
                billingClientImpl2.zzap(zzcb.zza(59, 11, billingResult2));
                zzbt = new zzbt(billingResult2, (List) null);
            }
        }
        zzb.zzk("BillingClient", "getPurchaseHistory is not supported on current device");
        return new zzbt(zzce.zzq, (List) null);
    }

    public final void acknowledgePurchase(AcknowledgePurchaseParams acknowledgePurchaseParams, AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener) {
        if (!isReady()) {
            BillingResult billingResult = zzce.zzm;
            zzap(zzcb.zza(2, 3, billingResult));
            acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(billingResult);
        } else if (TextUtils.isEmpty(acknowledgePurchaseParams.getPurchaseToken())) {
            zzb.zzk("BillingClient", "Please provide a valid purchase token.");
            BillingResult billingResult2 = zzce.zzi;
            zzap(zzcb.zza(26, 3, billingResult2));
            acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(billingResult2);
        } else if (!this.zzn) {
            BillingResult billingResult3 = zzce.zzb;
            zzap(zzcb.zza(27, 3, billingResult3));
            acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(billingResult3);
        } else {
            if (zzao(new zzu(this, acknowledgePurchaseParams, acknowledgePurchaseResponseListener), 30000, new zzv(this, acknowledgePurchaseResponseListener), zzaj()) == null) {
                BillingResult zzal = zzal();
                zzap(zzcb.zza(25, 3, zzal));
                acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(zzal);
            }
        }
    }

    public final void consumeAsync(ConsumeParams consumeParams, ConsumeResponseListener consumeResponseListener) {
        if (!isReady()) {
            BillingResult billingResult = zzce.zzm;
            zzap(zzcb.zza(2, 4, billingResult));
            consumeResponseListener.onConsumeResponse(billingResult, consumeParams.getPurchaseToken());
            return;
        }
        if (zzao(new zzah(this, consumeParams, consumeResponseListener), 30000, new zzai(this, consumeResponseListener, consumeParams), zzaj()) == null) {
            BillingResult zzal = zzal();
            zzap(zzcb.zza(25, 4, zzal));
            consumeResponseListener.onConsumeResponse(zzal, consumeParams.getPurchaseToken());
        }
    }

    public final void endConnection() {
        zzaq(zzcb.zzc(12));
        try {
            if (this.zzd != null) {
                this.zzd.zzf();
            }
            if (this.zzh != null) {
                this.zzh.zzc();
            }
            if (!(this.zzh == null || this.zzg == null)) {
                zzb.zzj("BillingClient", "Unbinding from service.");
                this.zze.unbindService(this.zzh);
                this.zzh = null;
            }
            this.zzg = null;
            ExecutorService executorService = this.zzB;
            if (executorService != null) {
                executorService.shutdownNow();
                this.zzB = null;
            }
        } catch (Exception e) {
            zzb.zzl("BillingClient", "There was an exception while ending connection!", e);
        } catch (Throwable th) {
            this.zza = 3;
            throw th;
        }
        this.zza = 3;
    }

    public void getBillingConfigAsync(GetBillingConfigParams getBillingConfigParams, BillingConfigResponseListener billingConfigResponseListener) {
        if (!isReady()) {
            zzb.zzk("BillingClient", "Service disconnected.");
            BillingResult billingResult = zzce.zzm;
            zzap(zzcb.zza(2, 13, billingResult));
            billingConfigResponseListener.onBillingConfigResponse(billingResult, (BillingConfig) null);
        } else if (!this.zzu) {
            zzb.zzk("BillingClient", "Current client doesn't support get billing config.");
            BillingResult billingResult2 = zzce.zzA;
            zzap(zzcb.zza(32, 13, billingResult2));
            billingConfigResponseListener.onBillingConfigResponse(billingResult2, (BillingConfig) null);
        } else {
            String str = this.zzb;
            Bundle bundle = new Bundle();
            bundle.putString("playBillingLibraryVersion", str);
            if (zzao(new zzw(this, bundle, billingConfigResponseListener), 30000, new zzx(this, billingConfigResponseListener), zzaj()) == null) {
                BillingResult zzal = zzal();
                zzap(zzcb.zza(25, 13, zzal));
                billingConfigResponseListener.onBillingConfigResponse(zzal, (BillingConfig) null);
            }
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.android.billingclient.api.BillingResult isFeatureSupported(java.lang.String r15) {
        /*
            r14 = this;
            boolean r0 = r14.isReady()
            r1 = 2
            r2 = 5
            if (r0 != 0) goto L_0x0020
            com.android.billingclient.api.BillingResult r15 = com.android.billingclient.api.zzce.zzm
            int r0 = r15.getResponseCode()
            if (r0 == 0) goto L_0x0018
            com.google.android.gms.internal.play_billing.zzga r0 = com.android.billingclient.api.zzcb.zza(r1, r2, r15)
            r14.zzap(r0)
            goto L_0x001f
        L_0x0018:
            com.google.android.gms.internal.play_billing.zzge r0 = com.android.billingclient.api.zzcb.zzc(r2)
            r14.zzaq(r0)
        L_0x001f:
            return r15
        L_0x0020:
            com.android.billingclient.api.BillingResult r0 = com.android.billingclient.api.zzce.zza
            int r0 = r15.hashCode()
            r3 = 1
            r4 = 4
            r5 = 3
            r6 = 6
            r7 = 7
            r8 = 8
            r9 = 11
            r10 = 12
            r11 = 13
            r12 = 9
            r13 = 10
            switch(r0) {
                case -422092961: goto L_0x00c3;
                case 96321: goto L_0x00b9;
                case 97314: goto L_0x00af;
                case 98307: goto L_0x00a5;
                case 99300: goto L_0x009b;
                case 100293: goto L_0x0091;
                case 101286: goto L_0x0087;
                case 102279: goto L_0x007d;
                case 103272: goto L_0x0073;
                case 104265: goto L_0x0068;
                case 105258: goto L_0x005d;
                case 106251: goto L_0x0052;
                case 207616302: goto L_0x0047;
                case 1987365622: goto L_0x003c;
                default: goto L_0x003a;
            }
        L_0x003a:
            goto L_0x00cd
        L_0x003c:
            java.lang.String r0 = "subscriptions"
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x00cd
            r0 = 0
            goto L_0x00ce
        L_0x0047:
            java.lang.String r0 = "priceChangeConfirmation"
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x00cd
            r0 = r1
            goto L_0x00ce
        L_0x0052:
            java.lang.String r0 = "kkk"
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x00cd
            r0 = r11
            goto L_0x00ce
        L_0x005d:
            java.lang.String r0 = "jjj"
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x00cd
            r0 = r10
            goto L_0x00ce
        L_0x0068:
            java.lang.String r0 = "iii"
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x00cd
            r0 = r9
            goto L_0x00ce
        L_0x0073:
            java.lang.String r0 = "hhh"
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x00cd
            r0 = r13
            goto L_0x00ce
        L_0x007d:
            java.lang.String r0 = "ggg"
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x00cd
            r0 = r12
            goto L_0x00ce
        L_0x0087:
            java.lang.String r0 = "fff"
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x00cd
            r0 = r8
            goto L_0x00ce
        L_0x0091:
            java.lang.String r0 = "eee"
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x00cd
            r0 = r7
            goto L_0x00ce
        L_0x009b:
            java.lang.String r0 = "ddd"
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x00cd
            r0 = r2
            goto L_0x00ce
        L_0x00a5:
            java.lang.String r0 = "ccc"
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x00cd
            r0 = r6
            goto L_0x00ce
        L_0x00af:
            java.lang.String r0 = "bbb"
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x00cd
            r0 = r5
            goto L_0x00ce
        L_0x00b9:
            java.lang.String r0 = "aaa"
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x00cd
            r0 = r4
            goto L_0x00ce
        L_0x00c3:
            java.lang.String r0 = "subscriptionsUpdate"
            boolean r0 = r15.equals(r0)
            if (r0 == 0) goto L_0x00cd
            r0 = r3
            goto L_0x00ce
        L_0x00cd:
            r0 = -1
        L_0x00ce:
            switch(r0) {
                case 0: goto L_0x01a9;
                case 1: goto L_0x019c;
                case 2: goto L_0x018d;
                case 3: goto L_0x017e;
                case 4: goto L_0x016f;
                case 5: goto L_0x0160;
                case 6: goto L_0x0151;
                case 7: goto L_0x0142;
                case 8: goto L_0x0133;
                case 9: goto L_0x0124;
                case 10: goto L_0x0115;
                case 11: goto L_0x0106;
                case 12: goto L_0x00f5;
                case 13: goto L_0x00e4;
                default: goto L_0x00d1;
            }
        L_0x00d1:
            java.lang.String r0 = "BillingClient"
            java.lang.String r1 = "Unsupported feature: "
            java.lang.String r15 = r1.concat(r15)
            com.google.android.gms.internal.play_billing.zzb.zzk(r0, r15)
            com.android.billingclient.api.BillingResult r15 = com.android.billingclient.api.zzce.zzz
            r0 = 34
            r14.zzau(r15, r0, r3)
            return r15
        L_0x00e4:
            boolean r15 = r14.zzy
            if (r15 == 0) goto L_0x00eb
            com.android.billingclient.api.BillingResult r15 = com.android.billingclient.api.zzce.zzl
            goto L_0x00ed
        L_0x00eb:
            com.android.billingclient.api.BillingResult r15 = com.android.billingclient.api.zzce.zzy
        L_0x00ed:
            r0 = 103(0x67, float:1.44E-43)
            r1 = 18
            r14.zzau(r15, r0, r1)
            return r15
        L_0x00f5:
            boolean r15 = r14.zzx
            if (r15 == 0) goto L_0x00fc
            com.android.billingclient.api.BillingResult r15 = com.android.billingclient.api.zzce.zzl
            goto L_0x00fe
        L_0x00fc:
            com.android.billingclient.api.BillingResult r15 = com.android.billingclient.api.zzce.zzE
        L_0x00fe:
            r0 = 66
            r1 = 14
            r14.zzau(r15, r0, r1)
            return r15
        L_0x0106:
            boolean r15 = r14.zzw
            if (r15 == 0) goto L_0x010d
            com.android.billingclient.api.BillingResult r15 = com.android.billingclient.api.zzce.zzl
            goto L_0x010f
        L_0x010d:
            com.android.billingclient.api.BillingResult r15 = com.android.billingclient.api.zzce.zzD
        L_0x010f:
            r0 = 60
            r14.zzau(r15, r0, r11)
            return r15
        L_0x0115:
            boolean r15 = r14.zzu
            if (r15 == 0) goto L_0x011c
            com.android.billingclient.api.BillingResult r15 = com.android.billingclient.api.zzce.zzl
            goto L_0x011e
        L_0x011c:
            com.android.billingclient.api.BillingResult r15 = com.android.billingclient.api.zzce.zzB
        L_0x011e:
            r0 = 33
            r14.zzau(r15, r0, r10)
            return r15
        L_0x0124:
            boolean r15 = r14.zzu
            if (r15 == 0) goto L_0x012b
            com.android.billingclient.api.BillingResult r15 = com.android.billingclient.api.zzce.zzl
            goto L_0x012d
        L_0x012b:
            com.android.billingclient.api.BillingResult r15 = com.android.billingclient.api.zzce.zzA
        L_0x012d:
            r0 = 32
            r14.zzau(r15, r0, r9)
            return r15
        L_0x0133:
            boolean r15 = r14.zzt
            if (r15 == 0) goto L_0x013a
            com.android.billingclient.api.BillingResult r15 = com.android.billingclient.api.zzce.zzl
            goto L_0x013c
        L_0x013a:
            com.android.billingclient.api.BillingResult r15 = com.android.billingclient.api.zzce.zzv
        L_0x013c:
            r0 = 20
            r14.zzau(r15, r0, r13)
            return r15
        L_0x0142:
            boolean r15 = r14.zzs
            if (r15 == 0) goto L_0x0149
            com.android.billingclient.api.BillingResult r15 = com.android.billingclient.api.zzce.zzl
            goto L_0x014b
        L_0x0149:
            com.android.billingclient.api.BillingResult r15 = com.android.billingclient.api.zzce.zzt
        L_0x014b:
            r0 = 61
            r14.zzau(r15, r0, r12)
            return r15
        L_0x0151:
            boolean r15 = r14.zzs
            if (r15 == 0) goto L_0x0158
            com.android.billingclient.api.BillingResult r15 = com.android.billingclient.api.zzce.zzl
            goto L_0x015a
        L_0x0158:
            com.android.billingclient.api.BillingResult r15 = com.android.billingclient.api.zzce.zzt
        L_0x015a:
            r0 = 19
            r14.zzau(r15, r0, r8)
            return r15
        L_0x0160:
            boolean r15 = r14.zzq
            if (r15 == 0) goto L_0x0167
            com.android.billingclient.api.BillingResult r15 = com.android.billingclient.api.zzce.zzl
            goto L_0x0169
        L_0x0167:
            com.android.billingclient.api.BillingResult r15 = com.android.billingclient.api.zzce.zzu
        L_0x0169:
            r0 = 21
            r14.zzau(r15, r0, r7)
            return r15
        L_0x016f:
            boolean r15 = r14.zzr
            if (r15 == 0) goto L_0x0176
            com.android.billingclient.api.BillingResult r15 = com.android.billingclient.api.zzce.zzl
            goto L_0x0178
        L_0x0176:
            com.android.billingclient.api.BillingResult r15 = com.android.billingclient.api.zzce.zzs
        L_0x0178:
            r0 = 31
            r14.zzau(r15, r0, r6)
            return r15
        L_0x017e:
            boolean r15 = r14.zzp
            if (r15 == 0) goto L_0x0185
            com.android.billingclient.api.BillingResult r15 = com.android.billingclient.api.zzce.zzl
            goto L_0x0187
        L_0x0185:
            com.android.billingclient.api.BillingResult r15 = com.android.billingclient.api.zzce.zzw
        L_0x0187:
            r0 = 30
            r14.zzau(r15, r0, r2)
            return r15
        L_0x018d:
            boolean r15 = r14.zzm
            if (r15 == 0) goto L_0x0194
            com.android.billingclient.api.BillingResult r15 = com.android.billingclient.api.zzce.zzl
            goto L_0x0196
        L_0x0194:
            com.android.billingclient.api.BillingResult r15 = com.android.billingclient.api.zzce.zzr
        L_0x0196:
            r0 = 35
            r14.zzau(r15, r0, r4)
            return r15
        L_0x019c:
            boolean r15 = r14.zzj
            if (r15 == 0) goto L_0x01a3
            com.android.billingclient.api.BillingResult r15 = com.android.billingclient.api.zzce.zzl
            goto L_0x01a5
        L_0x01a3:
            com.android.billingclient.api.BillingResult r15 = com.android.billingclient.api.zzce.zzp
        L_0x01a5:
            r14.zzau(r15, r13, r5)
            return r15
        L_0x01a9:
            boolean r15 = r14.zzi
            if (r15 == 0) goto L_0x01b0
            com.android.billingclient.api.BillingResult r15 = com.android.billingclient.api.zzce.zzl
            goto L_0x01b2
        L_0x01b0:
            com.android.billingclient.api.BillingResult r15 = com.android.billingclient.api.zzce.zzo
        L_0x01b2:
            r14.zzau(r15, r12, r1)
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.billingclient.api.BillingClientImpl.isFeatureSupported(java.lang.String):com.android.billingclient.api.BillingResult");
    }

    public final boolean isReady() {
        return (this.zza != 2 || this.zzg == null || this.zzh == null) ? false : true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:142:0x0370  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x0374  */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x03c3 A[SYNTHETIC, Splitter:B:155:0x03c3] */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x03d9 A[Catch:{ TimeoutException -> 0x03d7, CancellationException -> 0x03d5, Exception -> 0x03d3 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.android.billingclient.api.BillingResult launchBillingFlow(android.app.Activity r25, com.android.billingclient.api.BillingFlowParams r26) {
        /*
            r24 = this;
            r8 = r24
            r0 = r25
            java.lang.String r9 = "BUY_INTENT"
            java.lang.String r1 = "proxyPackageVersion"
            com.android.billingclient.api.zzo r2 = r8.zzd
            r10 = 2
            if (r2 == 0) goto L_0x0457
            com.android.billingclient.api.zzo r2 = r8.zzd
            com.android.billingclient.api.PurchasesUpdatedListener r2 = r2.zzd()
            if (r2 == 0) goto L_0x0457
            boolean r2 = r24.isReady()
            if (r2 != 0) goto L_0x0028
            com.android.billingclient.api.BillingResult r0 = com.android.billingclient.api.zzce.zzm
            com.google.android.gms.internal.play_billing.zzga r1 = com.android.billingclient.api.zzcb.zza(r10, r10, r0)
            r8.zzap(r1)
            r8.zzak(r0)
            return r0
        L_0x0028:
            java.util.ArrayList r2 = r26.zzf()
            java.util.List r3 = r26.zzg()
            r4 = 0
            java.lang.Object r5 = com.google.android.gms.internal.play_billing.zzan.zza(r2, r4)
            android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(r5)
            java.lang.Object r5 = com.google.android.gms.internal.play_billing.zzan.zza(r3, r4)
            com.android.billingclient.api.BillingFlowParams$ProductDetailsParams r5 = (com.android.billingclient.api.BillingFlowParams.ProductDetailsParams) r5
            com.android.billingclient.api.ProductDetails r6 = r5.zza()
            java.lang.String r6 = r6.getProductId()
            com.android.billingclient.api.ProductDetails r7 = r5.zza()
            java.lang.String r7 = r7.getProductType()
            java.lang.String r11 = "subs"
            boolean r11 = r7.equals(r11)
            r12 = 9
            java.lang.String r13 = "BillingClient"
            if (r11 == 0) goto L_0x0071
            boolean r11 = r8.zzi
            if (r11 == 0) goto L_0x005f
            goto L_0x0071
        L_0x005f:
            java.lang.String r0 = "Current client doesn't support subscriptions."
            com.google.android.gms.internal.play_billing.zzb.zzk(r13, r0)
            com.android.billingclient.api.BillingResult r0 = com.android.billingclient.api.zzce.zzo
            com.google.android.gms.internal.play_billing.zzga r1 = com.android.billingclient.api.zzcb.zza(r12, r10, r0)
            r8.zzap(r1)
            r8.zzak(r0)
            return r0
        L_0x0071:
            boolean r11 = r26.zzp()
            if (r11 == 0) goto L_0x0090
            boolean r11 = r8.zzl
            if (r11 == 0) goto L_0x007c
            goto L_0x0090
        L_0x007c:
            java.lang.String r0 = "Current client doesn't support extra params for buy intent."
            com.google.android.gms.internal.play_billing.zzb.zzk(r13, r0)
            com.android.billingclient.api.BillingResult r0 = com.android.billingclient.api.zzce.zzh
            r1 = 18
            com.google.android.gms.internal.play_billing.zzga r1 = com.android.billingclient.api.zzcb.zza(r1, r10, r0)
            r8.zzap(r1)
            r8.zzak(r0)
            return r0
        L_0x0090:
            int r11 = r2.size()
            r14 = 1
            if (r11 <= r14) goto L_0x00b0
            boolean r11 = r8.zzs
            if (r11 == 0) goto L_0x009c
            goto L_0x00b0
        L_0x009c:
            java.lang.String r0 = "Current client doesn't support multi-item purchases."
            com.google.android.gms.internal.play_billing.zzb.zzk(r13, r0)
            com.android.billingclient.api.BillingResult r0 = com.android.billingclient.api.zzce.zzt
            r1 = 19
            com.google.android.gms.internal.play_billing.zzga r1 = com.android.billingclient.api.zzcb.zza(r1, r10, r0)
            r8.zzap(r1)
            r8.zzak(r0)
            return r0
        L_0x00b0:
            boolean r11 = r3.isEmpty()
            if (r11 != 0) goto L_0x00cf
            boolean r11 = r8.zzt
            if (r11 == 0) goto L_0x00bb
            goto L_0x00cf
        L_0x00bb:
            java.lang.String r0 = "Current client doesn't support purchases with ProductDetails."
            com.google.android.gms.internal.play_billing.zzb.zzk(r13, r0)
            com.android.billingclient.api.BillingResult r0 = com.android.billingclient.api.zzce.zzv
            r1 = 20
            com.google.android.gms.internal.play_billing.zzga r1 = com.android.billingclient.api.zzcb.zza(r1, r10, r0)
            r8.zzap(r1)
            r8.zzak(r0)
            return r0
        L_0x00cf:
            boolean r11 = r8.zzl
            if (r11 == 0) goto L_0x03a4
            boolean r11 = r8.zzn
            boolean r15 = r8.zzv
            com.android.billingclient.api.PendingPurchasesParams r12 = r8.zzz
            boolean r12 = r12.isEnabledForOneTimeProducts()
            com.android.billingclient.api.PendingPurchasesParams r10 = r8.zzz
            boolean r10 = r10.isEnabledForPrepaidPlans()
            boolean r4 = r8.zzA
            java.lang.String r14 = r8.zzb
            r17 = r9
            android.os.Bundle r9 = new android.os.Bundle
            r9.<init>()
            java.lang.String r0 = "playBillingLibraryVersion"
            r9.putString(r0, r14)
            int r0 = r26.zza()
            if (r0 == 0) goto L_0x0102
            int r0 = r26.zza()
            java.lang.String r14 = "prorationMode"
            r9.putInt(r14, r0)
        L_0x0102:
            java.lang.String r0 = r26.zzb()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x0115
            java.lang.String r0 = r26.zzb()
            java.lang.String r14 = "accountId"
            r9.putString(r14, r0)
        L_0x0115:
            java.lang.String r0 = r26.zzc()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x0128
            java.lang.String r0 = r26.zzc()
            java.lang.String r14 = "obfuscatedProfileId"
            r9.putString(r14, r0)
        L_0x0128:
            boolean r0 = r26.zzo()
            if (r0 == 0) goto L_0x0134
            java.lang.String r0 = "isOfferPersonalizedByDeveloper"
            r14 = 1
            r9.putBoolean(r0, r14)
        L_0x0134:
            r0 = 0
            boolean r14 = android.text.TextUtils.isEmpty(r0)
            if (r14 != 0) goto L_0x014d
            java.util.ArrayList r14 = new java.util.ArrayList
            java.lang.String[] r18 = new java.lang.String[]{r0}
            java.util.List r0 = java.util.Arrays.asList(r18)
            r14.<init>(r0)
            java.lang.String r0 = "skusToReplace"
            r9.putStringArrayList(r0, r14)
        L_0x014d:
            java.lang.String r0 = r26.zzd()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x0160
            java.lang.String r0 = r26.zzd()
            java.lang.String r14 = "oldSkuPurchaseToken"
            r9.putString(r14, r0)
        L_0x0160:
            r0 = 0
            boolean r14 = android.text.TextUtils.isEmpty(r0)
            if (r14 != 0) goto L_0x016c
            java.lang.String r14 = "oldSkuPurchaseId"
            r9.putString(r14, r0)
        L_0x016c:
            java.lang.String r14 = r26.zze()
            boolean r14 = android.text.TextUtils.isEmpty(r14)
            if (r14 != 0) goto L_0x0180
            java.lang.String r14 = r26.zze()
            java.lang.String r0 = "originalExternalTransactionId"
            r9.putString(r0, r14)
            r0 = 0
        L_0x0180:
            boolean r14 = android.text.TextUtils.isEmpty(r0)
            if (r14 != 0) goto L_0x018b
            java.lang.String r14 = "paymentsPurchaseParams"
            r9.putString(r14, r0)
        L_0x018b:
            if (r11 == 0) goto L_0x0196
            if (r12 == 0) goto L_0x0196
            java.lang.String r0 = "enablePendingPurchases"
            r11 = 1
            r9.putBoolean(r0, r11)
            goto L_0x0197
        L_0x0196:
            r11 = 1
        L_0x0197:
            if (r15 == 0) goto L_0x01a0
            if (r10 == 0) goto L_0x01a0
            java.lang.String r0 = "enablePendingPurchaseForSubscriptions"
            r9.putBoolean(r0, r11)
        L_0x01a0:
            if (r4 == 0) goto L_0x01a7
            java.lang.String r0 = "enableAlternativeBilling"
            r9.putBoolean(r0, r11)
        L_0x01a7:
            boolean r0 = r2.isEmpty()
            java.lang.String r4 = "SKU_OFFER_ID_TOKEN_LIST"
            java.lang.String r10 = "additionalSkuTypes"
            java.lang.String r11 = "additionalSkus"
            java.lang.String r12 = "skuDetailsTokens"
            if (r0 != 0) goto L_0x0226
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.ArrayList r15 = new java.util.ArrayList
            r15.<init>()
            java.util.ArrayList r15 = new java.util.ArrayList
            r15.<init>()
            java.util.ArrayList r15 = new java.util.ArrayList
            r15.<init>()
            java.util.ArrayList r15 = new java.util.ArrayList
            r15.<init>()
            java.util.Iterator r15 = r2.iterator()
            boolean r18 = r15.hasNext()
            if (r18 != 0) goto L_0x021d
            boolean r15 = r0.isEmpty()
            if (r15 != 0) goto L_0x01e1
            r9.putStringArrayList(r12, r0)
        L_0x01e1:
            int r0 = r2.size()
            r12 = 1
            if (r0 <= r12) goto L_0x020a
            java.util.ArrayList r0 = new java.util.ArrayList
            int r15 = r2.size()
            int r15 = r15 + -1
            r0.<init>(r15)
            java.util.ArrayList r15 = new java.util.ArrayList
            int r16 = r2.size()
            int r14 = r16 + -1
            r15.<init>(r14)
            int r14 = r2.size()
            if (r12 < r14) goto L_0x0214
            r9.putStringArrayList(r11, r0)
            r9.putStringArrayList(r10, r15)
        L_0x020a:
            r21 = r1
            r20 = r6
            r19 = r7
            r23 = r13
            goto L_0x02df
        L_0x0214:
            java.lang.Object r0 = r2.get(r12)
            android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(r0)
            r0 = 0
            throw r0
        L_0x021d:
            r0 = 0
            java.lang.Object r1 = r15.next()
            android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(r1)
            throw r0
        L_0x0226:
            r0 = 1
            java.util.ArrayList r2 = new java.util.ArrayList
            int r14 = r3.size()
            int r14 = r14 + -1
            r2.<init>(r14)
            java.util.ArrayList r14 = new java.util.ArrayList
            int r15 = r3.size()
            int r15 = r15 + -1
            r14.<init>(r15)
            java.util.ArrayList r15 = new java.util.ArrayList
            r15.<init>()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r19 = r7
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r21 = r1
            r20 = r6
            r6 = 0
        L_0x0253:
            int r1 = r3.size()
            if (r6 >= r1) goto L_0x02ba
            java.lang.Object r1 = r3.get(r6)
            com.android.billingclient.api.BillingFlowParams$ProductDetailsParams r1 = (com.android.billingclient.api.BillingFlowParams.ProductDetailsParams) r1
            com.android.billingclient.api.ProductDetails r22 = r1.zza()
            java.lang.String r23 = r22.zzb()
            boolean r23 = r23.isEmpty()
            if (r23 != 0) goto L_0x0277
            r23 = r13
            java.lang.String r13 = r22.zzb()
            r15.add(r13)
            goto L_0x0279
        L_0x0277:
            r23 = r13
        L_0x0279:
            java.lang.String r1 = r1.zzb()
            r0.add(r1)
            java.lang.String r1 = r22.zzc()
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x0291
            java.lang.String r1 = r22.zzc()
            r7.add(r1)
        L_0x0291:
            if (r6 <= 0) goto L_0x02b5
            java.lang.Object r1 = r3.get(r6)
            com.android.billingclient.api.BillingFlowParams$ProductDetailsParams r1 = (com.android.billingclient.api.BillingFlowParams.ProductDetailsParams) r1
            com.android.billingclient.api.ProductDetails r1 = r1.zza()
            java.lang.String r1 = r1.getProductId()
            r2.add(r1)
            java.lang.Object r1 = r3.get(r6)
            com.android.billingclient.api.BillingFlowParams$ProductDetailsParams r1 = (com.android.billingclient.api.BillingFlowParams.ProductDetailsParams) r1
            com.android.billingclient.api.ProductDetails r1 = r1.zza()
            java.lang.String r1 = r1.getProductType()
            r14.add(r1)
        L_0x02b5:
            int r6 = r6 + 1
            r13 = r23
            goto L_0x0253
        L_0x02ba:
            r23 = r13
            r9.putStringArrayList(r4, r0)
            boolean r0 = r15.isEmpty()
            if (r0 != 0) goto L_0x02c8
            r9.putStringArrayList(r12, r15)
        L_0x02c8:
            boolean r0 = r7.isEmpty()
            if (r0 != 0) goto L_0x02d3
            java.lang.String r0 = "SKU_SERIALIZED_DOCID_LIST"
            r9.putStringArrayList(r0, r7)
        L_0x02d3:
            boolean r0 = r2.isEmpty()
            if (r0 != 0) goto L_0x02df
            r9.putStringArrayList(r11, r2)
            r9.putStringArrayList(r10, r14)
        L_0x02df:
            boolean r0 = r9.containsKey(r4)
            if (r0 == 0) goto L_0x02fa
            boolean r0 = r8.zzq
            if (r0 == 0) goto L_0x02ea
            goto L_0x02fa
        L_0x02ea:
            com.android.billingclient.api.BillingResult r0 = com.android.billingclient.api.zzce.zzu
            r1 = 21
            r2 = 2
            com.google.android.gms.internal.play_billing.zzga r1 = com.android.billingclient.api.zzcb.zza(r1, r2, r0)
            r8.zzap(r1)
            r8.zzak(r0)
            return r0
        L_0x02fa:
            if (r5 == 0) goto L_0x031a
            com.android.billingclient.api.ProductDetails r0 = r5.zza()
            java.lang.String r0 = r0.zza()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x031a
            com.android.billingclient.api.ProductDetails r0 = r5.zza()
            java.lang.String r0 = r0.zza()
            java.lang.String r1 = "skuPackageName"
            r9.putString(r1, r0)
            r0 = 0
            r14 = 1
            goto L_0x031c
        L_0x031a:
            r0 = 0
            r14 = 0
        L_0x031c:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L_0x0327
            java.lang.String r1 = "accountName"
            r9.putString(r1, r0)
        L_0x0327:
            android.content.Intent r0 = r25.getIntent()
            if (r0 != 0) goto L_0x0335
            java.lang.String r0 = "Activity's intent is null."
            r10 = r23
            com.google.android.gms.internal.play_billing.zzb.zzk(r10, r0)
            goto L_0x0366
        L_0x0335:
            r10 = r23
            java.lang.String r1 = "PROXY_PACKAGE"
            java.lang.String r2 = r0.getStringExtra(r1)
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L_0x0366
            java.lang.String r0 = r0.getStringExtra(r1)
            java.lang.String r1 = "proxyPackage"
            r9.putString(r1, r0)
            android.content.Context r1 = r8.zze     // Catch:{ NameNotFoundException -> 0x035f }
            android.content.pm.PackageManager r1 = r1.getPackageManager()     // Catch:{ NameNotFoundException -> 0x035f }
            r2 = 0
            android.content.pm.PackageInfo r0 = r1.getPackageInfo(r0, r2)     // Catch:{ NameNotFoundException -> 0x035f }
            java.lang.String r0 = r0.versionName     // Catch:{ NameNotFoundException -> 0x035f }
            r1 = r21
            r9.putString(r1, r0)     // Catch:{ NameNotFoundException -> 0x0361 }
            goto L_0x0366
        L_0x035f:
            r1 = r21
        L_0x0361:
            java.lang.String r0 = "package not found"
            r9.putString(r1, r0)
        L_0x0366:
            boolean r0 = r8.zzt
            if (r0 == 0) goto L_0x0374
            boolean r0 = r3.isEmpty()
            if (r0 != 0) goto L_0x0374
            r0 = 17
        L_0x0372:
            r3 = r0
            goto L_0x0386
        L_0x0374:
            boolean r0 = r8.zzr
            if (r0 == 0) goto L_0x037d
            if (r14 == 0) goto L_0x037d
            r0 = 15
            goto L_0x0372
        L_0x037d:
            boolean r0 = r8.zzn
            if (r0 == 0) goto L_0x0384
            r3 = 9
            goto L_0x0386
        L_0x0384:
            r0 = 6
            goto L_0x0372
        L_0x0386:
            com.android.billingclient.api.zzas r0 = new com.android.billingclient.api.zzas
            r1 = r0
            r2 = r24
            r4 = r20
            r5 = r19
            r6 = r26
            r7 = r9
            r1.<init>(r2, r3, r4, r5, r6, r7)
            r5 = 0
            android.os.Handler r6 = r8.zzc
            r3 = 5000(0x1388, double:2.4703E-320)
            r1 = r24
            r2 = r0
            java.util.concurrent.Future r0 = r1.zzao(r2, r3, r5, r6)
            r1 = 78
            goto L_0x03c1
        L_0x03a4:
            r20 = r6
            r19 = r7
            r17 = r9
            r10 = r13
            com.android.billingclient.api.zzr r2 = new com.android.billingclient.api.zzr
            r1 = r19
            r0 = r20
            r2.<init>(r8, r0, r1)
            r5 = 0
            android.os.Handler r6 = r8.zzc
            r3 = 5000(0x1388, double:2.4703E-320)
            r1 = r24
            java.util.concurrent.Future r0 = r1.zzao(r2, r3, r5, r6)
            r1 = 80
        L_0x03c1:
            if (r0 != 0) goto L_0x03d9
            com.android.billingclient.api.BillingResult r0 = com.android.billingclient.api.zzce.zzm     // Catch:{ TimeoutException -> 0x03d7, CancellationException -> 0x03d5, Exception -> 0x03d3 }
            r1 = 25
            r2 = 2
            com.google.android.gms.internal.play_billing.zzga r1 = com.android.billingclient.api.zzcb.zza(r1, r2, r0)     // Catch:{ TimeoutException -> 0x03d7, CancellationException -> 0x03d5, Exception -> 0x03d3 }
            r8.zzap(r1)     // Catch:{ TimeoutException -> 0x03d7, CancellationException -> 0x03d5, Exception -> 0x03d3 }
            r8.zzak(r0)     // Catch:{ TimeoutException -> 0x03d7, CancellationException -> 0x03d5, Exception -> 0x03d3 }
            return r0
        L_0x03d3:
            r0 = move-exception
            goto L_0x042f
        L_0x03d5:
            r0 = move-exception
            goto L_0x0443
        L_0x03d7:
            r0 = move-exception
            goto L_0x0443
        L_0x03d9:
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ TimeoutException -> 0x03d7, CancellationException -> 0x03d5, Exception -> 0x03d3 }
            r3 = 5000(0x1388, double:2.4703E-320)
            java.lang.Object r0 = r0.get(r3, r2)     // Catch:{ TimeoutException -> 0x03d7, CancellationException -> 0x03d5, Exception -> 0x03d3 }
            android.os.Bundle r0 = (android.os.Bundle) r0     // Catch:{ TimeoutException -> 0x03d7, CancellationException -> 0x03d5, Exception -> 0x03d3 }
            int r2 = com.google.android.gms.internal.play_billing.zzb.zzb(r0, r10)     // Catch:{ TimeoutException -> 0x03d7, CancellationException -> 0x03d5, Exception -> 0x03d3 }
            java.lang.String r3 = com.google.android.gms.internal.play_billing.zzb.zzg(r0, r10)     // Catch:{ TimeoutException -> 0x03d7, CancellationException -> 0x03d5, Exception -> 0x03d3 }
            if (r2 == 0) goto L_0x0415
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ TimeoutException -> 0x03d7, CancellationException -> 0x03d5, Exception -> 0x03d3 }
            r4.<init>()     // Catch:{ TimeoutException -> 0x03d7, CancellationException -> 0x03d5, Exception -> 0x03d3 }
            java.lang.String r5 = "Unable to buy item, Error response code: "
            r4.append(r5)     // Catch:{ TimeoutException -> 0x03d7, CancellationException -> 0x03d5, Exception -> 0x03d3 }
            r4.append(r2)     // Catch:{ TimeoutException -> 0x03d7, CancellationException -> 0x03d5, Exception -> 0x03d3 }
            java.lang.String r4 = r4.toString()     // Catch:{ TimeoutException -> 0x03d7, CancellationException -> 0x03d5, Exception -> 0x03d3 }
            com.google.android.gms.internal.play_billing.zzb.zzk(r10, r4)     // Catch:{ TimeoutException -> 0x03d7, CancellationException -> 0x03d5, Exception -> 0x03d3 }
            com.android.billingclient.api.BillingResult r2 = com.android.billingclient.api.zzce.zza(r2, r3)     // Catch:{ TimeoutException -> 0x03d7, CancellationException -> 0x03d5, Exception -> 0x03d3 }
            if (r0 == 0) goto L_0x0409
            r1 = 23
        L_0x0409:
            r3 = 2
            com.google.android.gms.internal.play_billing.zzga r0 = com.android.billingclient.api.zzcb.zza(r1, r3, r2)     // Catch:{ TimeoutException -> 0x03d7, CancellationException -> 0x03d5, Exception -> 0x03d3 }
            r8.zzap(r0)     // Catch:{ TimeoutException -> 0x03d7, CancellationException -> 0x03d5, Exception -> 0x03d3 }
            r8.zzak(r2)     // Catch:{ TimeoutException -> 0x03d7, CancellationException -> 0x03d5, Exception -> 0x03d3 }
            return r2
        L_0x0415:
            android.content.Intent r1 = new android.content.Intent     // Catch:{ TimeoutException -> 0x03d7, CancellationException -> 0x03d5, Exception -> 0x03d3 }
            java.lang.Class<com.android.billingclient.api.ProxyBillingActivity> r2 = com.android.billingclient.api.ProxyBillingActivity.class
            r3 = r25
            r1.<init>(r3, r2)     // Catch:{ TimeoutException -> 0x03d7, CancellationException -> 0x03d5, Exception -> 0x03d3 }
            r2 = r17
            android.os.Parcelable r0 = r0.getParcelable(r2)     // Catch:{ TimeoutException -> 0x03d7, CancellationException -> 0x03d5, Exception -> 0x03d3 }
            android.app.PendingIntent r0 = (android.app.PendingIntent) r0     // Catch:{ TimeoutException -> 0x03d7, CancellationException -> 0x03d5, Exception -> 0x03d3 }
            r1.putExtra(r2, r0)     // Catch:{ TimeoutException -> 0x03d7, CancellationException -> 0x03d5, Exception -> 0x03d3 }
            r3.startActivity(r1)     // Catch:{ TimeoutException -> 0x03d7, CancellationException -> 0x03d5, Exception -> 0x03d3 }
            com.android.billingclient.api.BillingResult r0 = com.android.billingclient.api.zzce.zzl
            return r0
        L_0x042f:
            java.lang.String r1 = "Exception while launching billing flow. Try to reconnect"
            com.google.android.gms.internal.play_billing.zzb.zzl(r10, r1, r0)
            com.android.billingclient.api.BillingResult r0 = com.android.billingclient.api.zzce.zzm
            r1 = 5
            r2 = 2
            com.google.android.gms.internal.play_billing.zzga r1 = com.android.billingclient.api.zzcb.zza(r1, r2, r0)
            r8.zzap(r1)
            r8.zzak(r0)
            return r0
        L_0x0443:
            java.lang.String r1 = "Time out while launching billing flow. Try to reconnect"
            com.google.android.gms.internal.play_billing.zzb.zzl(r10, r1, r0)
            com.android.billingclient.api.BillingResult r0 = com.android.billingclient.api.zzce.zzn
            r1 = 4
            r2 = 2
            com.google.android.gms.internal.play_billing.zzga r1 = com.android.billingclient.api.zzcb.zza(r1, r2, r0)
            r8.zzap(r1)
            r8.zzak(r0)
            return r0
        L_0x0457:
            r2 = r10
            com.android.billingclient.api.BillingResult r0 = com.android.billingclient.api.zzce.zzF
            r1 = 12
            com.google.android.gms.internal.play_billing.zzga r1 = com.android.billingclient.api.zzcb.zza(r1, r2, r0)
            r8.zzap(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.billingclient.api.BillingClientImpl.launchBillingFlow(android.app.Activity, com.android.billingclient.api.BillingFlowParams):com.android.billingclient.api.BillingResult");
    }

    public final void queryProductDetailsAsync(QueryProductDetailsParams queryProductDetailsParams, ProductDetailsResponseListener productDetailsResponseListener) {
        if (!isReady()) {
            BillingResult billingResult = zzce.zzm;
            zzap(zzcb.zza(2, 7, billingResult));
            productDetailsResponseListener.onProductDetailsResponse(billingResult, new ArrayList());
        } else if (!this.zzt) {
            zzb.zzk("BillingClient", "Querying product details is not supported.");
            BillingResult billingResult2 = zzce.zzv;
            zzap(zzcb.zza(20, 7, billingResult2));
            productDetailsResponseListener.onProductDetailsResponse(billingResult2, new ArrayList());
        } else {
            if (zzao(new zzan(this, queryProductDetailsParams, productDetailsResponseListener), 30000, new zzao(this, productDetailsResponseListener), zzaj()) == null) {
                BillingResult zzal = zzal();
                zzap(zzcb.zza(25, 7, zzal));
                productDetailsResponseListener.onProductDetailsResponse(zzal, new ArrayList());
            }
        }
    }

    public final void queryPurchaseHistoryAsync(QueryPurchaseHistoryParams queryPurchaseHistoryParams, PurchaseHistoryResponseListener purchaseHistoryResponseListener) {
        zzar(queryPurchaseHistoryParams.zza(), purchaseHistoryResponseListener);
    }

    public final void queryPurchasesAsync(QueryPurchasesParams queryPurchasesParams, PurchasesResponseListener purchasesResponseListener) {
        zzas(queryPurchasesParams.zza(), purchasesResponseListener);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzR(AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener) {
        BillingResult billingResult = zzce.zzn;
        zzap(zzcb.zza(24, 3, billingResult));
        acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(billingResult);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzS(BillingResult billingResult) {
        if (this.zzd.zzd() != null) {
            this.zzd.zzd().onPurchasesUpdated(billingResult, (List) null);
        } else {
            zzb.zzk("BillingClient", "No valid listener is set in BroadcastManager");
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzT(ConsumeResponseListener consumeResponseListener, ConsumeParams consumeParams) {
        BillingResult billingResult = zzce.zzn;
        zzap(zzcb.zza(24, 4, billingResult));
        consumeResponseListener.onConsumeResponse(billingResult, consumeParams.getPurchaseToken());
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzW(BillingConfigResponseListener billingConfigResponseListener) {
        BillingResult billingResult = zzce.zzn;
        zzap(zzcb.zza(24, 13, billingResult));
        billingConfigResponseListener.onBillingConfigResponse(billingResult, (BillingConfig) null);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzZ(ProductDetailsResponseListener productDetailsResponseListener) {
        BillingResult billingResult = zzce.zzn;
        zzap(zzcb.zza(24, 7, billingResult));
        productDetailsResponseListener.onProductDetailsResponse(billingResult, new ArrayList());
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzaa(PurchaseHistoryResponseListener purchaseHistoryResponseListener) {
        BillingResult billingResult = zzce.zzn;
        zzap(zzcb.zza(24, 11, billingResult));
        purchaseHistoryResponseListener.onPurchaseHistoryResponse(billingResult, (List) null);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzab(PurchasesResponseListener purchasesResponseListener) {
        BillingResult billingResult = zzce.zzn;
        zzap(zzcb.zza(24, 9, billingResult));
        purchasesResponseListener.onQueryPurchasesResponse(billingResult, zzai.zzk());
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Bundle zzc(int i, String str, String str2, BillingFlowParams billingFlowParams, Bundle bundle) {
        return this.zzg.zzg(i, this.zze.getPackageName(), str, str2, (String) null, bundle);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Bundle zzd(String str, String str2) {
        return this.zzg.zzf(3, this.zze.getPackageName(), str, str2, (String) null);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzk(AcknowledgePurchaseParams acknowledgePurchaseParams, AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener) {
        try {
            zzs zzs2 = this.zzg;
            String packageName = this.zze.getPackageName();
            String purchaseToken = acknowledgePurchaseParams.getPurchaseToken();
            String str = this.zzb;
            Bundle bundle = new Bundle();
            bundle.putString("playBillingLibraryVersion", str);
            Bundle zzd2 = zzs2.zzd(9, packageName, purchaseToken, bundle);
            acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(zzce.zza(zzb.zzb(zzd2, "BillingClient"), zzb.zzg(zzd2, "BillingClient")));
            return null;
        } catch (Exception e) {
            zzb.zzl("BillingClient", "Error acknowledge purchase!", e);
            BillingResult billingResult = zzce.zzm;
            zzap(zzcb.zza(28, 3, billingResult));
            acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(billingResult);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzl(ConsumeParams consumeParams, ConsumeResponseListener consumeResponseListener) {
        int i;
        String str;
        String purchaseToken = consumeParams.getPurchaseToken();
        try {
            zzb.zzj("BillingClient", "Consuming purchase with token: " + purchaseToken);
            if (this.zzn) {
                zzs zzs2 = this.zzg;
                String packageName = this.zze.getPackageName();
                boolean z = this.zzn;
                String str2 = this.zzb;
                Bundle bundle = new Bundle();
                if (z) {
                    bundle.putString("playBillingLibraryVersion", str2);
                }
                Bundle zze2 = zzs2.zze(9, packageName, purchaseToken, bundle);
                i = zze2.getInt("RESPONSE_CODE");
                str = zzb.zzg(zze2, "BillingClient");
            } else {
                i = this.zzg.zza(3, this.zze.getPackageName(), purchaseToken);
                str = "";
            }
            BillingResult zza2 = zzce.zza(i, str);
            if (i == 0) {
                zzb.zzj("BillingClient", "Successfully consumed purchase.");
                consumeResponseListener.onConsumeResponse(zza2, purchaseToken);
                return null;
            }
            zzb.zzk("BillingClient", "Error consuming purchase with token. Response code: " + i);
            zzap(zzcb.zza(23, 4, zza2));
            consumeResponseListener.onConsumeResponse(zza2, purchaseToken);
            return null;
        } catch (Exception e) {
            zzb.zzl("BillingClient", "Error consuming purchase!", e);
            BillingResult billingResult = zzce.zzm;
            zzap(zzcb.zza(29, 4, billingResult));
            consumeResponseListener.onConsumeResponse(billingResult, purchaseToken);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzm(Bundle bundle, BillingConfigResponseListener billingConfigResponseListener) {
        try {
            this.zzg.zzp(18, this.zze.getPackageName(), bundle, new zzbk(billingConfigResponseListener, this.zzf, this.zzk, (zzbj) null));
        } catch (DeadObjectException e) {
            zzb.zzl("BillingClient", "getBillingConfig got a dead object exception (try to reconnect).", e);
            BillingResult billingResult = zzce.zzm;
            zzap(zzcb.zza(62, 13, billingResult));
            billingConfigResponseListener.onBillingConfigResponse(billingResult, (BillingConfig) null);
        } catch (Exception e2) {
            zzb.zzl("BillingClient", "getBillingConfig got an exception.", e2);
            BillingResult billingResult2 = zzce.zzj;
            zzap(zzcb.zza(62, 13, billingResult2));
            billingConfigResponseListener.onBillingConfigResponse(billingResult2, (BillingConfig) null);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzn(QueryProductDetailsParams queryProductDetailsParams, ProductDetailsResponseListener productDetailsResponseListener) {
        String str;
        int i;
        int i2;
        int i3;
        ArrayList arrayList = new ArrayList();
        String zzb2 = queryProductDetailsParams.zzb();
        zzai zza2 = queryProductDetailsParams.zza();
        int size = zza2.size();
        int i4 = 0;
        while (true) {
            if (i4 >= size) {
                str = "";
                i = 0;
                break;
            }
            int i5 = i4 + 20;
            ArrayList arrayList2 = new ArrayList(zza2.subList(i4, i5 > size ? size : i5));
            ArrayList arrayList3 = new ArrayList();
            int size2 = arrayList2.size();
            for (int i6 = 0; i6 < size2; i6++) {
                arrayList3.add(((QueryProductDetailsParams.Product) arrayList2.get(i6)).zza());
            }
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("ITEM_ID_LIST", arrayList3);
            bundle.putString("playBillingLibraryVersion", this.zzb);
            try {
                zzs zzs2 = this.zzg;
                int i7 = true != this.zzw ? 17 : 20;
                String packageName = this.zze.getPackageName();
                boolean zzat = zzat();
                String str2 = this.zzb;
                zzam(queryProductDetailsParams);
                zzam(queryProductDetailsParams);
                zzam(queryProductDetailsParams);
                Bundle bundle2 = new Bundle();
                bundle2.putString("playBillingLibraryVersion", str2);
                bundle2.putBoolean("enablePendingPurchases", true);
                bundle2.putString("SKU_DETAILS_RESPONSE_FORMAT", "PRODUCT_DETAILS");
                if (zzat) {
                    bundle2.putBoolean("enablePendingPurchaseForSubscriptions", true);
                }
                ArrayList arrayList4 = new ArrayList();
                ArrayList arrayList5 = new ArrayList();
                int size3 = arrayList2.size();
                zzai zzai = zza2;
                int i8 = 0;
                boolean z = false;
                boolean z2 = false;
                while (i8 < size3) {
                    ArrayList arrayList6 = arrayList2;
                    arrayList4.add((Object) null);
                    z |= !TextUtils.isEmpty((CharSequence) null);
                    int i9 = size3;
                    if (((QueryProductDetailsParams.Product) arrayList2.get(i8)).zzb().equals("first_party")) {
                        zzaa.zzc((Object) null, "Serialized DocId is required for constructing ExtraParams to query ProductDetails for all first party products.");
                        arrayList5.add((Object) null);
                        z2 = true;
                    }
                    i8++;
                    size3 = i9;
                    arrayList2 = arrayList6;
                }
                if (z) {
                    bundle2.putStringArrayList("SKU_OFFER_ID_TOKEN_LIST", arrayList4);
                }
                if (!arrayList5.isEmpty()) {
                    bundle2.putStringArrayList("SKU_SERIALIZED_DOCID_LIST", arrayList5);
                }
                if (z2 && !TextUtils.isEmpty((CharSequence) null)) {
                    bundle2.putString("accountName", (String) null);
                }
                i2 = 7;
                try {
                    Bundle zzl2 = zzs2.zzl(i7, packageName, zzb2, bundle, bundle2);
                    str = "Item is unavailable for purchase.";
                    if (zzl2 == null) {
                        zzb.zzk("BillingClient", "queryProductDetailsAsync got empty product details response.");
                        zzap(zzcb.zza(44, 7, zzce.zzC));
                        break;
                    } else if (!zzl2.containsKey("DETAILS_LIST")) {
                        i = zzb.zzb(zzl2, "BillingClient");
                        str = zzb.zzg(zzl2, "BillingClient");
                        if (i != 0) {
                            zzb.zzk("BillingClient", "getSkuDetails() failed for queryProductDetailsAsync. Response code: " + i);
                            zzap(zzcb.zza(23, 7, zzce.zza(i, str)));
                        } else {
                            zzb.zzk("BillingClient", "getSkuDetails() returned a bundle with neither an error nor a product detail list for queryProductDetailsAsync.");
                            zzap(zzcb.zza(45, 7, zzce.zza(6, str)));
                            i = 6;
                        }
                    } else {
                        ArrayList<String> stringArrayList = zzl2.getStringArrayList("DETAILS_LIST");
                        if (stringArrayList == null) {
                            zzb.zzk("BillingClient", "queryProductDetailsAsync got null response list");
                            zzap(zzcb.zza(46, 7, zzce.zzC));
                            break;
                        }
                        int i10 = 0;
                        while (i10 < stringArrayList.size()) {
                            try {
                                ProductDetails productDetails = new ProductDetails(stringArrayList.get(i10));
                                zzb.zzj("BillingClient", "Got product details: ".concat(productDetails.toString()));
                                arrayList.add(productDetails);
                                i10++;
                            } catch (JSONException e) {
                                zzb.zzl("BillingClient", "Got a JSON exception trying to decode ProductDetails. \n Exception: ", e);
                                str = "Error trying to decode SkuDetails.";
                                i3 = 6;
                                zzap(zzcb.zza(47, 7, zzce.zza(6, str)));
                                i = i3;
                                productDetailsResponseListener.onProductDetailsResponse(zzce.zza(i, str), arrayList);
                                return null;
                            }
                        }
                        i4 = i5;
                        zza2 = zzai;
                    }
                } catch (Exception e2) {
                    e = e2;
                    i3 = 6;
                    zzb.zzl("BillingClient", "queryProductDetailsAsync got a remote exception (try to reconnect).", e);
                    zzap(zzcb.zza(43, i2, zzce.zzj));
                    str = "An internal error occurred.";
                    i = i3;
                    productDetailsResponseListener.onProductDetailsResponse(zzce.zza(i, str), arrayList);
                    return null;
                }
            } catch (Exception e3) {
                e = e3;
                i3 = 6;
                i2 = 7;
                zzb.zzl("BillingClient", "queryProductDetailsAsync got a remote exception (try to reconnect).", e);
                zzap(zzcb.zza(43, i2, zzce.zzj));
                str = "An internal error occurred.";
                i = i3;
                productDetailsResponseListener.onProductDetailsResponse(zzce.zza(i, str), arrayList);
                return null;
            }
        }
        i = 4;
        productDetailsResponseListener.onProductDetailsResponse(zzce.zza(i, str), arrayList);
        return null;
    }

    BillingClientImpl(String str, Context context, zzcc zzcc, ExecutorService executorService) {
        this.zza = 0;
        this.zzc = new Handler(Looper.getMainLooper());
        this.zzk = 0;
        String zzan = zzan();
        this.zzb = zzan;
        this.zze = context.getApplicationContext();
        zzgt zzy2 = zzgu.zzy();
        zzy2.zzn(zzan);
        zzy2.zzm(this.zze.getPackageName());
        this.zzf = new zzch(this.zze, (zzgu) zzy2.zzf());
        this.zze.getPackageName();
    }

    BillingClientImpl(String str, PendingPurchasesParams pendingPurchasesParams, Context context, zzck zzck, zzcc zzcc, ExecutorService executorService) {
        this.zza = 0;
        this.zzc = new Handler(Looper.getMainLooper());
        this.zzk = 0;
        this.zzb = zzan();
        this.zze = context.getApplicationContext();
        zzgt zzy2 = zzgu.zzy();
        zzy2.zzn(zzan());
        zzy2.zzm(this.zze.getPackageName());
        this.zzf = new zzch(this.zze, (zzgu) zzy2.zzf());
        zzb.zzk("BillingClient", "Billing client should have a valid listener but the provided is null.");
        this.zzd = new zzo(this.zze, (PurchasesUpdatedListener) null, (zzck) null, (zzc) null, (UserChoiceBillingListener) null, this.zzf);
        this.zzz = pendingPurchasesParams;
        this.zze.getPackageName();
    }

    BillingClientImpl(String str, PendingPurchasesParams pendingPurchasesParams, Context context, PurchasesUpdatedListener purchasesUpdatedListener, zzc zzc2, zzcc zzcc, ExecutorService executorService) {
        String zzan = zzan();
        this.zza = 0;
        this.zzc = new Handler(Looper.getMainLooper());
        this.zzk = 0;
        this.zzb = zzan;
        initialize(context, purchasesUpdatedListener, pendingPurchasesParams, (zzc) null, zzan, (zzcc) null);
    }

    public final void startConnection(BillingClientStateListener billingClientStateListener) {
        if (isReady()) {
            zzb.zzj("BillingClient", "Service connection is valid. No need to re-initialize.");
            zzaq(zzcb.zzc(6));
            billingClientStateListener.onBillingSetupFinished(zzce.zzl);
            return;
        }
        int i = 1;
        if (this.zza == 1) {
            zzb.zzk("BillingClient", "Client is already in the process of connecting to billing service.");
            BillingResult billingResult = zzce.zzd;
            zzap(zzcb.zza(37, 6, billingResult));
            billingClientStateListener.onBillingSetupFinished(billingResult);
        } else if (this.zza == 3) {
            zzb.zzk("BillingClient", "Client was already closed and can't be reused. Please create another instance.");
            BillingResult billingResult2 = zzce.zzm;
            zzap(zzcb.zza(38, 6, billingResult2));
            billingClientStateListener.onBillingSetupFinished(billingResult2);
        } else {
            this.zza = 1;
            zzb.zzj("BillingClient", "Starting in-app billing setup.");
            this.zzh = new zzbc(this, billingClientStateListener, (zzbb) null);
            Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
            intent.setPackage("com.android.vending");
            List<ResolveInfo> queryIntentServices = this.zze.getPackageManager().queryIntentServices(intent, 0);
            if (queryIntentServices == null || queryIntentServices.isEmpty()) {
                i = 41;
            } else {
                ServiceInfo serviceInfo = queryIntentServices.get(0).serviceInfo;
                if (serviceInfo != null) {
                    String str = serviceInfo.packageName;
                    String str2 = serviceInfo.name;
                    if (!"com.android.vending".equals(str) || str2 == null) {
                        zzb.zzk("BillingClient", "The device doesn't have valid Play Store.");
                        i = 40;
                    } else {
                        ComponentName componentName = new ComponentName(str, str2);
                        Intent intent2 = new Intent(intent);
                        intent2.setComponent(componentName);
                        intent2.putExtra("playBillingLibraryVersion", this.zzb);
                        if (this.zze.bindService(intent2, this.zzh, 1)) {
                            zzb.zzj("BillingClient", "Service was bonded successfully.");
                            return;
                        } else {
                            zzb.zzk("BillingClient", "Connection to Billing service is blocked.");
                            i = 39;
                        }
                    }
                }
            }
            this.zza = 0;
            zzb.zzj("BillingClient", "Billing service unavailable on device.");
            BillingResult billingResult3 = zzce.zzc;
            zzap(zzcb.zza(i, 6, billingResult3));
            billingClientStateListener.onBillingSetupFinished(billingResult3);
        }
    }
}
