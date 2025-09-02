package com.salesforce.marketingcloud.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PowerManager;
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.util.l;
import java.util.concurrent.TimeUnit;

public class NotificationOpenActivity extends FragmentActivity {
    private static final String b = g.a("NotificationOpenActivity");
    private BroadcastReceiver a;

    class a extends BroadcastReceiver {
        a() {
        }

        public void onReceive(Context context, Intent intent) {
            NotificationOpenActivity.this.a();
        }
    }

    public static Intent a(Context context, Bundle bundle) {
        return new Intent(context, NotificationOpenActivity.class).setAction(NotificationManager.ACTION_NOTIFICATION_CLICKED).putExtras(bundle).setFlags(8388608);
    }

    private void b(Context context, Bundle bundle) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(a.f37n).putExtras(bundle));
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent() == null) {
            a();
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(a.o);
        intentFilter.setPriority(999);
        this.a = new a();
        LocalBroadcastManager.getInstance(this).registerReceiver(this.a, intentFilter);
        PowerManager.WakeLock wakeLock = null;
        try {
            String str = b;
            wakeLock = ((PowerManager) getSystemService("power")).newWakeLock(1, str);
            wakeLock.setReferenceCounted(false);
            wakeLock.acquire(TimeUnit.SECONDS.toMillis(30));
            if (!l.a(500, 50) || MarketingCloudSdk.getInstance() == null) {
                g.e(str, "MarketingCloudSdk#init must be called in your application's onCreate", new Object[0]);
            } else if (NotificationManager.ACTION_NOTIFICATION_CLICKED.equals(getIntent().getAction())) {
                b(getApplicationContext(), getIntent().getExtras());
            }
            if (!wakeLock.isHeld()) {
                return;
            }
        } catch (Exception e) {
            g.b(b, e, "Encountered exception while handling action: %s", getIntent().getAction());
            if (wakeLock == null || !wakeLock.isHeld()) {
                return;
            }
        } catch (Throwable th) {
            if (wakeLock != null && wakeLock.isHeld()) {
                try {
                    wakeLock.release();
                } catch (Exception unused) {
                }
            }
            throw th;
        }
        try {
            wakeLock.release();
        } catch (Exception unused2) {
        }
    }

    /* access modifiers changed from: protected */
    public void a() {
        if (this.a != null) {
            try {
                LocalBroadcastManager.getInstance(this).unregisterReceiver(this.a);
            } catch (IllegalArgumentException unused) {
                g.e(b, "com.salesforce.marketingcloud.notifications.open.RECEIVED Receiver is not registered.", new Object[0]);
            }
        }
        if (!isFinishing()) {
            finish();
        }
    }
}
