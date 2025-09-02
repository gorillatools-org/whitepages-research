package com.wpconnect;

import android.app.PendingIntent;
import android.content.Context;
import com.salesforce.marketingcloud.notifications.NotificationManager;
import com.salesforce.marketingcloud.notifications.NotificationMessage;

public final /* synthetic */ class MainApplication$$ExternalSyntheticLambda1 implements NotificationManager.NotificationLaunchIntentProvider {
    public final /* synthetic */ MainApplication f$0;

    public /* synthetic */ MainApplication$$ExternalSyntheticLambda1(MainApplication mainApplication) {
        this.f$0 = mainApplication;
    }

    public final PendingIntent getNotificationPendingIntent(Context context, NotificationMessage notificationMessage) {
        return MainApplication.onCreate$lambda$2(this.f$0, context, notificationMessage);
    }
}
