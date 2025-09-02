package com.salesforce.marketingcloud.notifications;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.RemoteMessage;
import com.salesforce.marketingcloud.MCKeep;
import com.salesforce.marketingcloud.NotificationOpenedService;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.internal.h;
import com.salesforce.marketingcloud.internal.i;
import com.salesforce.marketingcloud.util.l;

public abstract class NotificationManager {
    @MCKeep
    public static final String ACTION_NOTIFICATION_CLICKED = "com.salesforce.marketingcloud.NOTIFICATION_CLICKED";
    @MCKeep
    public static final String DEFAULT_CHANNEL_ID = "com.salesforce.marketingcloud.DEFAULT_CHANNEL";
    @MCKeep
    public static final String DEFAULT_FOREGROUND_CHANNEL_ID = "com.salesforce.marketingcloud.DEFAULT_FOREGROUND_CHANNEL";
    static final String d = g.a("NotificationManager");
    private static final String e = "com.salesforce.marketingcloud.notifications.EXTRA_MESSAGE";

    @MCKeep
    public interface NotificationBuilder {
        NotificationCompat.Builder setupNotificationBuilder(Context context, NotificationMessage notificationMessage);
    }

    @MCKeep
    public interface NotificationChannelIdProvider {
        String getNotificationChannelId(Context context, NotificationMessage notificationMessage);
    }

    @MCKeep
    public interface NotificationLaunchIntentProvider {
        PendingIntent getNotificationPendingIntent(Context context, NotificationMessage notificationMessage);
    }

    @MCKeep
    public interface NotificationMessageDisplayedListener {
        void onNotificationMessageDisplayed(NotificationMessage notificationMessage);
    }

    @MCKeep
    public interface ShouldShowNotificationListener {
        boolean shouldShowNotification(NotificationMessage notificationMessage);
    }

    static Intent a(Intent intent, NotificationMessage notificationMessage) {
        return intent.putExtra(e, i.a(notificationMessage));
    }

    @MCKeep
    public static void cancelNotificationMessage(Context context, NotificationMessage notificationMessage) {
        if (notificationMessage.notificationId() >= 0) {
            ((android.app.NotificationManager) context.getSystemService("notification")).cancel("com.marketingcloud.salesforce.notifications.TAG", notificationMessage.notificationId());
        }
    }

    @MCKeep
    public static String createDefaultNotificationChannel(Context context) {
        return b.b(context, false);
    }

    @MCKeep
    public static String createForegroundNotificationChannel(Context context) {
        return b.a(context, false);
    }

    @MCKeep
    public static NotificationMessage extractMessage(Intent intent) {
        try {
            return (NotificationMessage) i.a(intent.getByteArrayExtra(e), NotificationMessage.CREATOR);
        } catch (Exception e2) {
            g.b(d, e2, "Unable to retrieve NotificationMessage from Intent (%s).", intent);
            return null;
        }
    }

    @MCKeep
    public static NotificationCompat.Builder getDefaultNotificationBuilder(Context context, NotificationMessage notificationMessage, String str, int i) {
        return b.a(context, notificationMessage, str, i);
    }

    @MCKeep
    public static PendingIntent redirectIntentForAnalytics(Context context, PendingIntent pendingIntent, RemoteMessage remoteMessage, boolean z) {
        try {
            return redirectIntentForAnalytics(context, pendingIntent, h.a(remoteMessage.getData()), z);
        } catch (Exception e2) {
            g.b(d, e2, "Failed to create {NotificationMessage} from {RemoteMessage}, not processing {PendingIntent} for analytics.", new Object[0]);
            return null;
        }
    }

    @MCKeep
    public abstract boolean areNotificationsEnabled();

    @MCKeep
    public abstract void disableNotifications();

    @MCKeep
    public abstract void enableNotifications();

    @MCKeep
    public abstract void registerNotificationMessageDisplayedListener(NotificationMessageDisplayedListener notificationMessageDisplayedListener);

    @MCKeep
    public abstract void setShouldShowNotificationListener(ShouldShowNotificationListener shouldShowNotificationListener);

    @MCKeep
    public abstract void unregisterNotificationMessageDisplayedListener(NotificationMessageDisplayedListener notificationMessageDisplayedListener);

    @MCKeep
    public static String createDefaultNotificationChannel(Context context, boolean z) {
        return b.b(context, z);
    }

    @MCKeep
    public static PendingIntent redirectIntentForAnalytics(Context context, PendingIntent pendingIntent, NotificationMessage notificationMessage, boolean z) {
        Bundle bundle = new Bundle(3);
        bundle.putByteArray(e, i.a(notificationMessage));
        bundle.putParcelable("com.salesforce.marketingcloud.notifications.EXTRA_OPEN_INTENT", pendingIntent);
        bundle.putBoolean("com.salesforce.marketingcloud.notifications.EXTRA_AUTO_CANCEL", z);
        Uri fromParts = Uri.fromParts("mcsdk", "pushOpen", String.valueOf(System.currentTimeMillis()));
        int a = l.a(1073741824);
        return Build.VERSION.SDK_INT >= 31 ? PendingIntent.getActivity(context, 0, NotificationOpenActivity.a(context, bundle).setData(fromParts), a) : PendingIntent.getService(context, 0, NotificationOpenedService.b(context, bundle).setData(fromParts), a);
    }
}
