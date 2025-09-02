package com.wpconnect;

import android.content.Context;
import com.salesforce.marketingcloud.notifications.NotificationManager;
import com.salesforce.marketingcloud.notifications.NotificationMessage;

public final /* synthetic */ class MainApplication$$ExternalSyntheticLambda2 implements NotificationManager.NotificationChannelIdProvider {
    public final String getNotificationChannelId(Context context, NotificationMessage notificationMessage) {
        return MainApplication.onCreate$lambda$3(context, notificationMessage);
    }
}
