package com.salesforce.marketingcloud.notifications;

import android.app.PendingIntent;
import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.salesforce.marketingcloud.notifications.NotificationManager;

public class c extends b {
    public c(int i, NotificationManager.NotificationChannelIdProvider notificationChannelIdProvider) {
        super(i, (NotificationManager.NotificationLaunchIntentProvider) null, (NotificationManager.NotificationBuilder) null, notificationChannelIdProvider);
    }

    public NotificationCompat.Builder setupNotificationBuilder(Context context, NotificationMessage notificationMessage) {
        NotificationCompat.Builder a = b.a(context, notificationMessage, a(context, notificationMessage), this.d);
        PendingIntent c = c(context, notificationMessage);
        if (c != null) {
            a.setContentIntent(NotificationManager.redirectIntentForAnalytics(context, c, notificationMessage, true));
        }
        return a;
    }
}
