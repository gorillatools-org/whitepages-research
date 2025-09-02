package com.android.billingclient.api;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import com.google.android.gms.internal.play_billing.zzai;
import com.google.android.gms.internal.play_billing.zzb;
import com.google.android.gms.internal.play_billing.zzcd;
import com.google.android.gms.internal.play_billing.zzga;
import java.util.List;
import java.util.Objects;

final class zzn extends BroadcastReceiver {
    final /* synthetic */ zzo zza;
    private boolean zzb;
    private final boolean zzc;

    zzn(zzo zzo, boolean z) {
        this.zza = zzo;
        this.zzc = z;
    }

    private final void zzd(Bundle bundle, BillingResult billingResult, int i) {
        if (bundle.getByteArray("FAILURE_LOGGING_PAYLOAD") != null) {
            try {
                this.zza.zze.zza(zzga.zzA(bundle.getByteArray("FAILURE_LOGGING_PAYLOAD"), zzcd.zza()));
            } catch (Throwable unused) {
                zzb.zzk("BillingBroadcastManager", "Failed parsing Api failure.");
            }
        } else {
            this.zza.zze.zza(zzcb.zza(23, i, billingResult));
        }
    }

    public final void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        int i = 1;
        if (extras == null) {
            zzb.zzk("BillingBroadcastManager", "Bundle is null.");
            zzcc zzb2 = this.zza.zze;
            BillingResult billingResult = zzce.zzj;
            zzb2.zza(zzcb.zza(11, 1, billingResult));
            zzo zzo = this.zza;
            if (zzo.zzb != null) {
                zzo.zzb.onPurchasesUpdated(billingResult, (List) null);
                return;
            }
            return;
        }
        BillingResult zze = zzb.zze(intent, "BillingBroadcastManager");
        String action = intent.getAction();
        if (true == Objects.equals(extras.getString("INTENT_SOURCE"), "LAUNCH_BILLING_FLOW")) {
            i = 2;
        }
        if (action.equals("com.android.vending.billing.PURCHASES_UPDATED") || action.equals("com.android.vending.billing.LOCAL_BROADCAST_PURCHASES_UPDATED")) {
            List zzi = zzb.zzi(extras);
            if (zze.getResponseCode() == 0) {
                this.zza.zze.zzc(zzcb.zzc(i));
            } else {
                zzd(extras, zze, i);
            }
            this.zza.zzb.onPurchasesUpdated(zze, zzi);
        } else if (!action.equals("com.android.vending.billing.ALTERNATIVE_BILLING")) {
        } else {
            if (zze.getResponseCode() != 0) {
                zzd(extras, zze, i);
                this.zza.zzb.onPurchasesUpdated(zze, zzai.zzk());
                return;
            }
            zzo zzo2 = this.zza;
            zzc unused = zzo2.getClass();
            UserChoiceBillingListener unused2 = zzo2.getClass();
            zzb.zzk("BillingBroadcastManager", "AlternativeBillingListener and UserChoiceBillingListener is null.");
            zzcc zzb3 = this.zza.zze;
            BillingResult billingResult2 = zzce.zzj;
            zzb3.zza(zzcb.zza(77, i, billingResult2));
            this.zza.zzb.onPurchasesUpdated(billingResult2, zzai.zzk());
        }
    }

    public final synchronized void zza(Context context, IntentFilter intentFilter) {
        try {
            if (!this.zzb) {
                if (Build.VERSION.SDK_INT >= 33) {
                    context.registerReceiver(this, intentFilter, true != this.zzc ? 4 : 2);
                } else {
                    context.registerReceiver(this, intentFilter);
                }
                this.zzb = true;
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public final synchronized void zzb(Context context, IntentFilter intentFilter, String str) {
        try {
            if (!this.zzb) {
                if (Build.VERSION.SDK_INT >= 33) {
                    context.registerReceiver(this, intentFilter, "com.google.android.finsky.permission.PLAY_BILLING_LIBRARY_BROADCAST", (Handler) null, true != this.zzc ? 4 : 2);
                } else {
                    context.registerReceiver(this, intentFilter, "com.google.android.finsky.permission.PLAY_BILLING_LIBRARY_BROADCAST", (Handler) null);
                }
                this.zzb = true;
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public final synchronized void zzc(Context context) {
        if (this.zzb) {
            context.unregisterReceiver(this);
            this.zzb = false;
            return;
        }
        zzb.zzk("BillingBroadcastManager", "Receiver is not registered.");
    }
}
