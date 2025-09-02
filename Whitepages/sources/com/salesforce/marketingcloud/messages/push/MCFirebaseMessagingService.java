package com.salesforce.marketingcloud.messages.push;

import android.annotation.SuppressLint;
import android.content.Context;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.salesforce.marketingcloud.MCService;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import com.salesforce.marketingcloud.util.l;

@SuppressLint({"UnknownNullness"})
public class MCFirebaseMessagingService extends FirebaseMessagingService {
    private static final String a = g.a("MCFirebaseMessagingService");

    static void a(RemoteMessage remoteMessage) {
        if (remoteMessage == null) {
            g.e(a, "RemoteMessage was null.", new Object[0]);
            return;
        }
        String str = (remoteMessage.getData() == null || !remoteMessage.getData().containsKey(NotificationMessage.NOTIF_KEY_ID)) ? "Unknown Message" : remoteMessage.getData().get(NotificationMessage.NOTIF_KEY_ID);
        String str2 = a;
        g.d(str2, "onMessageReceived() for MessageID: '%s'", str);
        MarketingCloudSdk a2 = a();
        if (a2 == null) {
            g.e(str2, "Marketing Cloud SDK init failed.  Push message ignored.", new Object[0]);
        } else {
            a2.getPushMessageManager().handleMessage(remoteMessage);
        }
    }

    public void onMessageReceived(RemoteMessage remoteMessage) {
        g.d(a, "onMessageReceived()", new Object[0]);
        a(remoteMessage);
    }

    public void onNewToken(String str) {
        a((Context) this);
    }

    static void a(Context context) {
        MarketingCloudSdk a2 = a();
        if (a2 == null) {
            g.e(a, "Marketing Cloud SDK init failed.  Unable to update push token.", new Object[0]);
            return;
        }
        String senderId = a2.getMarketingCloudConfig().senderId();
        if (senderId != null) {
            MCService.b(context, senderId);
        } else {
            g.a(a, "Received new token intent but senderId was not set.", new Object[0]);
        }
    }

    private static MarketingCloudSdk a() {
        if (l.a(1000, 50) && MarketingCloudSdk.getInstance() != null) {
            return MarketingCloudSdk.getInstance();
        }
        g.e(a, "MarketingCloudSdk#init must be called in your application's onCreate", new Object[0]);
        return null;
    }
}
