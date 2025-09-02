package com.google.android.gms.internal.fido;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import com.google.android.gms.fido.fido2.Fido2PendingIntent;

@Deprecated
public final class zzi implements Fido2PendingIntent {
    private final PendingIntent zza;

    public zzi(PendingIntent pendingIntent) {
        this.zza = pendingIntent;
    }

    public final boolean hasPendingIntent() {
        return this.zza != null;
    }

    public final void launchPendingIntent(Activity activity, int i) throws IntentSender.SendIntentException {
        PendingIntent pendingIntent = this.zza;
        if (pendingIntent != null) {
            activity.startIntentSenderForResult(pendingIntent.getIntentSender(), i, (Intent) null, 0, 0, 0);
            return;
        }
        throw new IllegalStateException("No PendingIntent available");
    }
}
