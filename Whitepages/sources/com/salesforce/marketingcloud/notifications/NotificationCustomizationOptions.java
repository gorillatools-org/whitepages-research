package com.salesforce.marketingcloud.notifications;

import android.os.Build;
import com.salesforce.marketingcloud.MCKeep;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.notifications.NotificationManager;
import com.salesforce.marketingcloud.util.l;

@MCKeep
public class NotificationCustomizationOptions {
    static final String TAG = g.a("NotificationCustomizationOptions");
    final NotificationManager.NotificationChannelIdProvider channelIdProvider;
    final NotificationManager.NotificationLaunchIntentProvider launchIntentProvider;
    final NotificationManager.NotificationBuilder notificationBuilder;
    final int smallIconResId;

    private NotificationCustomizationOptions(int i, NotificationManager.NotificationLaunchIntentProvider notificationLaunchIntentProvider, NotificationManager.NotificationChannelIdProvider notificationChannelIdProvider, NotificationManager.NotificationBuilder notificationBuilder2) {
        this.smallIconResId = i;
        this.launchIntentProvider = notificationLaunchIntentProvider;
        this.channelIdProvider = notificationChannelIdProvider;
        this.notificationBuilder = notificationBuilder2;
    }

    private static String classNameOrNull(Object obj) {
        return obj != null ? obj.getClass().getName() : "null";
    }

    public static NotificationCustomizationOptions create(int i) {
        return new NotificationCustomizationOptions(i, (NotificationManager.NotificationLaunchIntentProvider) null, (NotificationManager.NotificationChannelIdProvider) null, (NotificationManager.NotificationBuilder) null);
    }

    public NotificationManager.NotificationBuilder getNotificationBuilder() {
        return this.notificationBuilder;
    }

    public String toString() {
        NotificationManager.NotificationBuilder notificationBuilder2 = this.notificationBuilder;
        return notificationBuilder2 != null ? String.format(l.a, "{notificationBuilder=%s}", new Object[]{classNameOrNull(notificationBuilder2)}) : String.format(l.a, "{smallIconResId=%d, launchIntentProvider=%s, channelIdProvider=%s}", new Object[]{Integer.valueOf(this.smallIconResId), classNameOrNull(this.launchIntentProvider), classNameOrNull(this.channelIdProvider)});
    }

    public static NotificationCustomizationOptions create(int i, NotificationManager.NotificationLaunchIntentProvider notificationLaunchIntentProvider, NotificationManager.NotificationChannelIdProvider notificationChannelIdProvider) {
        if (notificationLaunchIntentProvider != null && Build.VERSION.SDK_INT >= 31) {
            g.c(TAG, "Make sure FLAG_IMMUTABLE or FLAG_MUTABLE for Pending Intent is set because of Targeting S+ (version 31 and above) ", new Object[0]);
        }
        return new NotificationCustomizationOptions(i, notificationLaunchIntentProvider, notificationChannelIdProvider, (NotificationManager.NotificationBuilder) null);
    }

    public static NotificationCustomizationOptions create(NotificationManager.NotificationBuilder notificationBuilder2) {
        if (notificationBuilder2 != null) {
            return new NotificationCustomizationOptions(0, (NotificationManager.NotificationLaunchIntentProvider) null, (NotificationManager.NotificationChannelIdProvider) null, notificationBuilder2);
        }
        throw new IllegalArgumentException("The provided NotificationManager.NotificationBuilder cannot be null.");
    }
}
