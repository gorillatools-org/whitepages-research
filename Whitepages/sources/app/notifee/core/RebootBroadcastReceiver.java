package app.notifee.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import n.o.t.i.f.e.e.e;

public class RebootBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Log.i("RebootReceiver", "Received reboot event");
        if (e.a == null) {
            e.a(context.getApplicationContext());
        }
        new b().b();
    }
}
