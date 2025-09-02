package com.android.billingclient.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.ResultReceiver;
import com.google.android.apps.common.proguard.UsedByReflection;
import com.google.android.gms.internal.play_billing.zzb;

@UsedByReflection("PlatformActivityProxy")
public class ProxyBillingActivity extends Activity {
    private ResultReceiver inAppMessageResultReceiver;
    private boolean isFlowFromFirstPartyClient;
    private ResultReceiver priceChangeResultReceiver;
    private boolean sendCancelledBroadcastIfFinished;

    private Intent makeAlternativeBillingIntent(String str) {
        Intent intent = new Intent("com.android.vending.billing.ALTERNATIVE_BILLING");
        intent.setPackage(getApplicationContext().getPackageName());
        intent.putExtra("ALTERNATIVE_BILLING_USER_CHOICE_DATA", str);
        return intent;
    }

    private Intent makePurchasesUpdatedIntent() {
        Intent intent = new Intent("com.android.vending.billing.LOCAL_BROADCAST_PURCHASES_UPDATED");
        intent.setPackage(getApplicationContext().getPackageName());
        return intent;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0080  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onActivityResult(int r8, int r9, android.content.Intent r10) {
        /*
            r7 = this;
            super.onActivityResult(r8, r9, r10)
            r0 = 100
            r1 = 0
            r2 = 0
            r3 = 110(0x6e, float:1.54E-43)
            java.lang.String r4 = "ProxyBillingActivity"
            if (r8 == r0) goto L_0x0043
            if (r8 != r3) goto L_0x0010
            goto L_0x0043
        L_0x0010:
            r9 = 101(0x65, float:1.42E-43)
            if (r8 != r9) goto L_0x0028
            int r8 = com.google.android.gms.internal.play_billing.zzb.zza(r10, r4)
            android.os.ResultReceiver r9 = r7.inAppMessageResultReceiver
            if (r9 == 0) goto L_0x00f6
            if (r10 != 0) goto L_0x001f
            goto L_0x0023
        L_0x001f:
            android.os.Bundle r2 = r10.getExtras()
        L_0x0023:
            r9.send(r8, r2)
            goto L_0x00f6
        L_0x0028:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "Got onActivityResult with wrong requestCode: "
            r9.append(r10)
            r9.append(r8)
            java.lang.String r8 = "; skipping..."
            r9.append(r8)
            java.lang.String r8 = r9.toString()
            com.google.android.gms.internal.play_billing.zzb.zzk(r4, r8)
            goto L_0x00f6
        L_0x0043:
            com.android.billingclient.api.BillingResult r0 = com.google.android.gms.internal.play_billing.zzb.zze(r10, r4)
            int r0 = r0.getResponseCode()
            r5 = -1
            if (r9 != r5) goto L_0x0054
            if (r0 == 0) goto L_0x0052
            r9 = r5
            goto L_0x0054
        L_0x0052:
            r0 = r1
            goto L_0x0070
        L_0x0054:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Activity finished with resultCode "
            r5.append(r6)
            r5.append(r9)
            java.lang.String r9 = " and billing's responseCode: "
            r5.append(r9)
            r5.append(r0)
            java.lang.String r9 = r5.toString()
            com.google.android.gms.internal.play_billing.zzb.zzk(r4, r9)
        L_0x0070:
            android.os.ResultReceiver r9 = r7.priceChangeResultReceiver
            if (r9 == 0) goto L_0x0080
            if (r10 != 0) goto L_0x0077
            goto L_0x007b
        L_0x0077:
            android.os.Bundle r2 = r10.getExtras()
        L_0x007b:
            r9.send(r0, r2)
            goto L_0x00f6
        L_0x0080:
            if (r10 == 0) goto L_0x00e7
            android.os.Bundle r9 = r10.getExtras()
            java.lang.String r0 = "LAUNCH_BILLING_FLOW"
            java.lang.String r2 = "INTENT_SOURCE"
            if (r9 == 0) goto L_0x00af
            android.os.Bundle r9 = r10.getExtras()
            java.lang.String r4 = "ALTERNATIVE_BILLING_USER_CHOICE_DATA"
            java.lang.String r9 = r9.getString(r4)
            if (r9 == 0) goto L_0x00a0
            android.content.Intent r9 = r7.makeAlternativeBillingIntent(r9)
            r9.putExtra(r2, r0)
            goto L_0x00eb
        L_0x00a0:
            android.content.Intent r9 = r7.makePurchasesUpdatedIntent()
            android.os.Bundle r10 = r10.getExtras()
            r9.putExtras(r10)
            r9.putExtra(r2, r0)
            goto L_0x00eb
        L_0x00af:
            android.content.Intent r9 = r7.makePurchasesUpdatedIntent()
            java.lang.String r10 = "Got null bundle!"
            com.google.android.gms.internal.play_billing.zzb.zzk(r4, r10)
            java.lang.String r10 = "RESPONSE_CODE"
            r4 = 6
            r9.putExtra(r10, r4)
            java.lang.String r10 = "DEBUG_MESSAGE"
            java.lang.String r5 = "An internal error occurred."
            r9.putExtra(r10, r5)
            com.android.billingclient.api.BillingResult$Builder r10 = com.android.billingclient.api.BillingResult.newBuilder()
            r10.setResponseCode(r4)
            r10.setDebugMessage(r5)
            com.android.billingclient.api.BillingResult r10 = r10.build()
            r4 = 22
            r5 = 2
            com.google.android.gms.internal.play_billing.zzga r10 = com.android.billingclient.api.zzcb.zza(r4, r5, r10)
            byte[] r10 = r10.zzc()
            java.lang.String r4 = "FAILURE_LOGGING_PAYLOAD"
            r9.putExtra(r4, r10)
            r9.putExtra(r2, r0)
            goto L_0x00eb
        L_0x00e7:
            android.content.Intent r9 = r7.makePurchasesUpdatedIntent()
        L_0x00eb:
            if (r8 != r3) goto L_0x00f3
            java.lang.String r8 = "IS_FIRST_PARTY_PURCHASE"
            r10 = 1
            r9.putExtra(r8, r10)
        L_0x00f3:
            r7.sendBroadcast(r9)
        L_0x00f6:
            r7.sendCancelledBroadcastIfFinished = r1
            r7.finish()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.billingclient.api.ProxyBillingActivity.onActivityResult(int, int, android.content.Intent):void");
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        int i;
        PendingIntent pendingIntent;
        int i2;
        super.onCreate(bundle);
        if (bundle == null) {
            zzb.zzj("ProxyBillingActivity", "Launching Play Store billing flow");
            if (getIntent().hasExtra("BUY_INTENT")) {
                pendingIntent = (PendingIntent) getIntent().getParcelableExtra("BUY_INTENT");
                if (getIntent().hasExtra("IS_FLOW_FROM_FIRST_PARTY_CLIENT") && getIntent().getBooleanExtra("IS_FLOW_FROM_FIRST_PARTY_CLIENT", false)) {
                    this.isFlowFromFirstPartyClient = true;
                    i2 = 110;
                }
                i = 100;
                this.sendCancelledBroadcastIfFinished = true;
                startIntentSenderForResult(pendingIntent.getIntentSender(), i, new Intent(), 0, 0, 0);
            } else if (getIntent().hasExtra("SUBS_MANAGEMENT_INTENT")) {
                pendingIntent = (PendingIntent) getIntent().getParcelableExtra("SUBS_MANAGEMENT_INTENT");
                this.priceChangeResultReceiver = (ResultReceiver) getIntent().getParcelableExtra("result_receiver");
                i = 100;
                this.sendCancelledBroadcastIfFinished = true;
                startIntentSenderForResult(pendingIntent.getIntentSender(), i, new Intent(), 0, 0, 0);
            } else if (getIntent().hasExtra("IN_APP_MESSAGE_INTENT")) {
                pendingIntent = (PendingIntent) getIntent().getParcelableExtra("IN_APP_MESSAGE_INTENT");
                this.inAppMessageResultReceiver = (ResultReceiver) getIntent().getParcelableExtra("in_app_message_result_receiver");
                i2 = 101;
            } else {
                i = 100;
                pendingIntent = null;
                this.sendCancelledBroadcastIfFinished = true;
                startIntentSenderForResult(pendingIntent.getIntentSender(), i, new Intent(), 0, 0, 0);
            }
            i = i2;
            try {
                this.sendCancelledBroadcastIfFinished = true;
                startIntentSenderForResult(pendingIntent.getIntentSender(), i, new Intent(), 0, 0, 0);
            } catch (IntentSender.SendIntentException e) {
                zzb.zzl("ProxyBillingActivity", "Got exception while trying to start a purchase flow.", e);
                ResultReceiver resultReceiver = this.priceChangeResultReceiver;
                if (resultReceiver != null) {
                    resultReceiver.send(6, (Bundle) null);
                } else {
                    ResultReceiver resultReceiver2 = this.inAppMessageResultReceiver;
                    if (resultReceiver2 != null) {
                        resultReceiver2.send(0, (Bundle) null);
                    } else {
                        Intent makePurchasesUpdatedIntent = makePurchasesUpdatedIntent();
                        if (this.isFlowFromFirstPartyClient) {
                            makePurchasesUpdatedIntent.putExtra("IS_FIRST_PARTY_PURCHASE", true);
                        }
                        makePurchasesUpdatedIntent.putExtra("RESPONSE_CODE", 6);
                        makePurchasesUpdatedIntent.putExtra("DEBUG_MESSAGE", "An internal error occurred.");
                        sendBroadcast(makePurchasesUpdatedIntent);
                    }
                }
                this.sendCancelledBroadcastIfFinished = false;
                finish();
            }
        } else {
            zzb.zzj("ProxyBillingActivity", "Launching Play Store billing flow from savedInstanceState");
            this.sendCancelledBroadcastIfFinished = bundle.getBoolean("send_cancelled_broadcast_if_finished", false);
            if (bundle.containsKey("result_receiver")) {
                this.priceChangeResultReceiver = (ResultReceiver) bundle.getParcelable("result_receiver");
            } else if (bundle.containsKey("in_app_message_result_receiver")) {
                this.inAppMessageResultReceiver = (ResultReceiver) bundle.getParcelable("in_app_message_result_receiver");
            }
            this.isFlowFromFirstPartyClient = bundle.getBoolean("IS_FLOW_FROM_FIRST_PARTY_CLIENT", false);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (isFinishing() && this.sendCancelledBroadcastIfFinished) {
            Intent makePurchasesUpdatedIntent = makePurchasesUpdatedIntent();
            makePurchasesUpdatedIntent.putExtra("RESPONSE_CODE", 1);
            makePurchasesUpdatedIntent.putExtra("DEBUG_MESSAGE", "Billing dialog closed.");
            sendBroadcast(makePurchasesUpdatedIntent);
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        ResultReceiver resultReceiver = this.priceChangeResultReceiver;
        if (resultReceiver != null) {
            bundle.putParcelable("result_receiver", resultReceiver);
        }
        ResultReceiver resultReceiver2 = this.inAppMessageResultReceiver;
        if (resultReceiver2 != null) {
            bundle.putParcelable("in_app_message_result_receiver", resultReceiver2);
        }
        bundle.putBoolean("send_cancelled_broadcast_if_finished", this.sendCancelledBroadcastIfFinished);
        bundle.putBoolean("IS_FLOW_FROM_FIRST_PARTY_CLIENT", this.isFlowFromFirstPartyClient);
    }
}
