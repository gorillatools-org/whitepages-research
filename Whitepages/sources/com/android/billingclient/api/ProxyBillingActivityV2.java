package com.android.billingclient.api;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts$StartIntentSenderForResult;
import com.google.android.apps.common.proguard.UsedByReflection;
import com.google.android.gms.internal.play_billing.zzb;

@UsedByReflection("PlatformActivityProxy")
public class ProxyBillingActivityV2 extends ComponentActivity {
    private ActivityResultLauncher zza;
    private ActivityResultLauncher zzb;
    private ResultReceiver zzc;
    private ResultReceiver zzd;

    /* access modifiers changed from: protected */
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zza = registerForActivityResult(new ActivityResultContracts$StartIntentSenderForResult(), new zzcw(this));
        this.zzb = registerForActivityResult(new ActivityResultContracts$StartIntentSenderForResult(), new zzcx(this));
        if (bundle == null) {
            zzb.zzj("ProxyBillingActivityV2", "Launching Play Store billing dialog");
            if (getIntent().hasExtra("ALTERNATIVE_BILLING_ONLY_DIALOG_INTENT")) {
                this.zzc = (ResultReceiver) getIntent().getParcelableExtra("alternative_billing_only_dialog_result_receiver");
                this.zza.launch(new IntentSenderRequest.Builder((PendingIntent) getIntent().getParcelableExtra("ALTERNATIVE_BILLING_ONLY_DIALOG_INTENT")).build());
            } else if (getIntent().hasExtra("external_payment_dialog_pending_intent")) {
                this.zzd = (ResultReceiver) getIntent().getParcelableExtra("external_payment_dialog_result_receiver");
                this.zzb.launch(new IntentSenderRequest.Builder((PendingIntent) getIntent().getParcelableExtra("external_payment_dialog_pending_intent")).build());
            }
        } else if (bundle.containsKey("alternative_billing_only_dialog_result_receiver")) {
            this.zzc = (ResultReceiver) bundle.getParcelable("alternative_billing_only_dialog_result_receiver");
        } else if (bundle.containsKey("external_payment_dialog_result_receiver")) {
            this.zzd = (ResultReceiver) bundle.getParcelable("external_payment_dialog_result_receiver");
        }
    }

    /* access modifiers changed from: protected */
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        ResultReceiver resultReceiver = this.zzc;
        if (resultReceiver != null) {
            bundle.putParcelable("alternative_billing_only_dialog_result_receiver", resultReceiver);
        }
        ResultReceiver resultReceiver2 = this.zzd;
        if (resultReceiver2 != null) {
            bundle.putParcelable("external_payment_dialog_result_receiver", resultReceiver2);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(ActivityResult activityResult) {
        Bundle bundle;
        Intent data = activityResult.getData();
        int responseCode = zzb.zze(data, "ProxyBillingActivityV2").getResponseCode();
        ResultReceiver resultReceiver = this.zzc;
        if (resultReceiver != null) {
            if (data == null) {
                bundle = null;
            } else {
                bundle = data.getExtras();
            }
            resultReceiver.send(responseCode, bundle);
        }
        if (!(activityResult.getResultCode() == -1 && responseCode == 0)) {
            int resultCode = activityResult.getResultCode();
            zzb.zzk("ProxyBillingActivityV2", "Alternative billing only dialog finished with resultCode " + resultCode + " and billing's responseCode: " + responseCode);
        }
        finish();
    }

    /* access modifiers changed from: package-private */
    public final void zzb(ActivityResult activityResult) {
        Bundle bundle;
        Intent data = activityResult.getData();
        int responseCode = zzb.zze(data, "ProxyBillingActivityV2").getResponseCode();
        ResultReceiver resultReceiver = this.zzd;
        if (resultReceiver != null) {
            if (data == null) {
                bundle = null;
            } else {
                bundle = data.getExtras();
            }
            resultReceiver.send(responseCode, bundle);
        }
        if (!(activityResult.getResultCode() == -1 && responseCode == 0)) {
            zzb.zzk("ProxyBillingActivityV2", String.format("External offer dialog finished with resultCode: %s and billing's responseCode: %s", new Object[]{Integer.valueOf(activityResult.getResultCode()), Integer.valueOf(responseCode)}));
        }
        finish();
    }
}
