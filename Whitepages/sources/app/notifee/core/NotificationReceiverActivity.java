package app.notifee.core;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import n.o.t.i.f.e.e.k;

public class NotificationReceiverActivity extends Activity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        k.a(this, getIntent());
        finish();
    }

    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        k.a(this, intent);
        finish();
    }
}
