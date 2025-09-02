package app.notifee.core;

import android.content.Intent;

public final /* synthetic */ class Notifee$$ExternalSyntheticLambda13 implements Runnable {
    public final /* synthetic */ Intent f$0;

    public /* synthetic */ Notifee$$ExternalSyntheticLambda13(Intent intent) {
        this.f$0 = intent;
    }

    public final void run() {
        Notifee.getContext().startActivity(this.f$0);
    }
}
