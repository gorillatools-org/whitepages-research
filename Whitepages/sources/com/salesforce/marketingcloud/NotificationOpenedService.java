package com.salesforce.marketingcloud;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.salesforce.marketingcloud.notifications.NotificationManager;
import com.salesforce.marketingcloud.notifications.a;
import com.salesforce.marketingcloud.util.l;
import java.util.concurrent.TimeUnit;

public class NotificationOpenedService extends IntentService {
    private static final String a = g.a("NotificationOpenedService");

    public NotificationOpenedService() {
        super(a);
    }

    private static void a(Context context, Bundle bundle) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(a.f37n).putExtras(bundle));
    }

    public static Intent b(Context context, Bundle bundle) {
        return new Intent(context, NotificationOpenedService.class).setAction(NotificationManager.ACTION_NOTIFICATION_CLICKED).putExtras(bundle);
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        if (intent != null && intent.getAction() != null) {
            PowerManager.WakeLock wakeLock = null;
            try {
                String str = a;
                wakeLock = ((PowerManager) getSystemService("power")).newWakeLock(1, str);
                wakeLock.setReferenceCounted(false);
                wakeLock.acquire(TimeUnit.SECONDS.toMillis(30));
                if (!l.a(500, 50) || MarketingCloudSdk.getInstance() == null) {
                    g.e(str, "MarketingCloudSdk#init must be called in your application's onCreate", new Object[0]);
                } else if (NotificationManager.ACTION_NOTIFICATION_CLICKED.equals(intent.getAction())) {
                    a(getApplicationContext(), intent.getExtras());
                }
                if (!wakeLock.isHeld()) {
                    return;
                }
            } catch (Exception e) {
                g.b(a, e, "Encountered exception while handling action: %s", intent.getAction());
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
    }
}
