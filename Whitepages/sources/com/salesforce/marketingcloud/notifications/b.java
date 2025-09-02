package com.salesforce.marketingcloud.notifications;

import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.net.Uri;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.salesforce.marketingcloud.R;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.notifications.NotificationManager;
import com.salesforce.marketingcloud.util.l;
import java.net.HttpURLConnection;
import java.net.URL;

class b implements NotificationManager.NotificationBuilder {
    private final NotificationManager.NotificationLaunchIntentProvider a;
    private final NotificationManager.NotificationBuilder b;
    private final NotificationManager.NotificationChannelIdProvider c;
    final int d;

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.salesforce.marketingcloud.notifications.NotificationMessage$Sound[] r0 = com.salesforce.marketingcloud.notifications.NotificationMessage.Sound.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                com.salesforce.marketingcloud.notifications.NotificationMessage$Sound r1 = com.salesforce.marketingcloud.notifications.NotificationMessage.Sound.CUSTOM     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.salesforce.marketingcloud.notifications.NotificationMessage$Sound r1 = com.salesforce.marketingcloud.notifications.NotificationMessage.Sound.DEFAULT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.salesforce.marketingcloud.notifications.NotificationMessage$Sound r1 = com.salesforce.marketingcloud.notifications.NotificationMessage.Sound.NONE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.notifications.b.a.<clinit>():void");
        }
    }

    public b(int i, NotificationManager.NotificationLaunchIntentProvider notificationLaunchIntentProvider, NotificationManager.NotificationBuilder notificationBuilder, NotificationManager.NotificationChannelIdProvider notificationChannelIdProvider) {
        this.a = notificationLaunchIntentProvider;
        this.b = notificationBuilder;
        this.c = notificationChannelIdProvider;
        this.d = i;
    }

    static String a(Context context, boolean z) {
        android.app.NotificationManager notificationManager;
        if (l.c() && (notificationManager = (android.app.NotificationManager) context.getSystemService("notification")) != null && (notificationManager.getNotificationChannel(NotificationManager.DEFAULT_FOREGROUND_CHANNEL_ID) == null || z)) {
            NotificationChannel notificationChannel = new NotificationChannel(NotificationManager.DEFAULT_FOREGROUND_CHANNEL_ID, context.getString(R.string.mcsdk_foreground_notification_channel_name), 3);
            notificationChannel.enableLights(false);
            notificationChannel.enableVibration(false);
            notificationChannel.setShowBadge(false);
            notificationChannel.setSound((Uri) null, (AudioAttributes) null);
            notificationChannel.setLockscreenVisibility(0);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        return NotificationManager.DEFAULT_FOREGROUND_CHANNEL_ID;
    }

    static String b(Context context, boolean z) {
        android.app.NotificationManager notificationManager;
        if (l.c() && (notificationManager = (android.app.NotificationManager) context.getSystemService("notification")) != null && (notificationManager.getNotificationChannel(NotificationManager.DEFAULT_CHANNEL_ID) == null || z)) {
            NotificationChannel notificationChannel = new NotificationChannel(NotificationManager.DEFAULT_CHANNEL_ID, context.getString(R.string.mcsdk_default_notification_channel_name), 3);
            notificationChannel.enableLights(false);
            notificationChannel.enableVibration(false);
            notificationChannel.setShowBadge(true);
            notificationChannel.setLockscreenVisibility(0);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        return NotificationManager.DEFAULT_CHANNEL_ID;
    }

    /* access modifiers changed from: package-private */
    public PendingIntent c(Context context, NotificationMessage notificationMessage) {
        try {
            NotificationManager.NotificationLaunchIntentProvider notificationLaunchIntentProvider = this.a;
            if (notificationLaunchIntentProvider != null) {
                return notificationLaunchIntentProvider.getNotificationPendingIntent(context, notificationMessage);
            }
        } catch (IllegalArgumentException e) {
            g.b(NotificationManager.d, e, "Missing FLAG_IMMUTABLE or FLAG_MUTABLE flag in PendingIntent", new Object[0]);
        }
        int a2 = l.a(134217728);
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        if (launchIntentForPackage == null) {
            return null;
        }
        Intent a3 = NotificationManager.a(launchIntentForPackage, notificationMessage);
        a3.addFlags(134217728);
        return PendingIntent.getActivity(context, notificationMessage.notificationId(), a3, a2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x0017  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.core.app.NotificationCompat.Builder setupNotificationBuilder(android.content.Context r5, com.salesforce.marketingcloud.notifications.NotificationMessage r6) {
        /*
            r4 = this;
            com.salesforce.marketingcloud.notifications.NotificationManager$NotificationBuilder r0 = r4.b
            if (r0 == 0) goto L_0x0014
            androidx.core.app.NotificationCompat$Builder r0 = r0.setupNotificationBuilder(r5, r6)     // Catch:{ Exception -> 0x0009 }
            goto L_0x0015
        L_0x0009:
            r0 = move-exception
            java.lang.String r1 = com.salesforce.marketingcloud.notifications.NotificationManager.d
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r3 = "Custom notification builder threw an exception.  Using default notification builder."
            com.salesforce.marketingcloud.g.b(r1, r0, r3, r2)
        L_0x0014:
            r0 = 0
        L_0x0015:
            if (r0 != 0) goto L_0x002f
            java.lang.String r0 = r4.b((android.content.Context) r5, (com.salesforce.marketingcloud.notifications.NotificationMessage) r6)
            int r1 = r4.d
            androidx.core.app.NotificationCompat$Builder r0 = a(r5, r6, r0, r1)
            android.app.PendingIntent r1 = r4.c(r5, r6)
            if (r1 == 0) goto L_0x002f
            r2 = 1
            android.app.PendingIntent r5 = com.salesforce.marketingcloud.notifications.NotificationManager.redirectIntentForAnalytics((android.content.Context) r5, (android.app.PendingIntent) r1, (com.salesforce.marketingcloud.notifications.NotificationMessage) r6, (boolean) r2)
            r0.setContentIntent(r5)
        L_0x002f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.notifications.b.setupNotificationBuilder(android.content.Context, com.salesforce.marketingcloud.notifications.NotificationMessage):androidx.core.app.NotificationCompat$Builder");
    }

    private static Bitmap a(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setReadTimeout(30000);
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.connect();
            return BitmapFactory.decodeStream(httpURLConnection.getInputStream());
        } catch (Exception e) {
            g.b(NotificationManager.d, e, "Unable to download image %s", str);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0017  */
    @android.annotation.SuppressLint({"NewApi"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String b(android.content.Context r5, com.salesforce.marketingcloud.notifications.NotificationMessage r6) {
        /*
            r4 = this;
            com.salesforce.marketingcloud.notifications.NotificationManager$NotificationChannelIdProvider r0 = r4.c
            r1 = 0
            if (r0 == 0) goto L_0x0014
            java.lang.String r6 = r0.getNotificationChannelId(r5, r6)     // Catch:{ Exception -> 0x000a }
            goto L_0x0015
        L_0x000a:
            r6 = move-exception
            java.lang.String r0 = com.salesforce.marketingcloud.notifications.NotificationManager.d
            java.lang.Object[] r2 = new java.lang.Object[r1]
            java.lang.String r3 = "Exception thrown while app determined channel id for notification message."
            com.salesforce.marketingcloud.g.b(r0, r6, r3, r2)
        L_0x0014:
            r6 = 0
        L_0x0015:
            if (r6 != 0) goto L_0x001c
            b((android.content.Context) r5, (boolean) r1)
            java.lang.String r6 = "com.salesforce.marketingcloud.DEFAULT_CHANNEL"
        L_0x001c:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.notifications.b.b(android.content.Context, com.salesforce.marketingcloud.notifications.NotificationMessage):java.lang.String");
    }

    private static Uri a(Context context, String str, Uri uri) {
        int identifier = context.getResources().getIdentifier(b(str), "raw", context.getPackageName());
        if (identifier <= 0) {
            return uri;
        }
        return Uri.parse("android.resource://" + context.getPackageName() + RemoteSettings.FORWARD_SLASH_STRING + identifier);
    }

    private static String b(String str) {
        return (str == null || str.lastIndexOf(46) <= 0) ? str : str.substring(0, str.lastIndexOf(46));
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00b5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static androidx.core.app.NotificationCompat.Builder a(android.content.Context r5, com.salesforce.marketingcloud.notifications.NotificationMessage r6, java.lang.String r7, int r8) {
        /*
            androidx.core.app.NotificationCompat$Builder r0 = new androidx.core.app.NotificationCompat$Builder
            r0.<init>(r5, r7)
            android.content.pm.ApplicationInfo r7 = r5.getApplicationInfo()
            int r7 = r7.icon
            if (r7 <= 0) goto L_0x0018
            android.content.res.Resources r1 = r5.getResources()
            android.graphics.Bitmap r7 = android.graphics.BitmapFactory.decodeResource(r1, r7)
            r0.setLargeIcon(r7)
        L_0x0018:
            r0.setSmallIcon(r8)
            java.lang.String r7 = r6.title()
            r0.setContentTitle(r7)
            java.lang.String r7 = r6.mediaUrl()
            boolean r7 = android.text.TextUtils.isEmpty(r7)
            r8 = 1
            r1 = 0
            if (r7 != 0) goto L_0x005e
            java.lang.String r7 = r6.mediaUrl()     // Catch:{ Exception -> 0x004e }
            android.graphics.Bitmap r7 = a(r7)     // Catch:{ Exception -> 0x004e }
            if (r7 == 0) goto L_0x005e
            androidx.core.app.NotificationCompat$BigPictureStyle r2 = new androidx.core.app.NotificationCompat$BigPictureStyle     // Catch:{ Exception -> 0x004e }
            r2.<init>()     // Catch:{ Exception -> 0x004e }
            androidx.core.app.NotificationCompat$BigPictureStyle r7 = r2.bigPicture(r7)     // Catch:{ Exception -> 0x004e }
            java.lang.String r2 = r6.alert()     // Catch:{ Exception -> 0x004e }
            androidx.core.app.NotificationCompat$BigPictureStyle r7 = r7.setSummaryText(r2)     // Catch:{ Exception -> 0x004e }
            r0.setStyle(r7)     // Catch:{ Exception -> 0x004e }
            r7 = r8
            goto L_0x005f
        L_0x004e:
            r7 = move-exception
            java.lang.String r2 = com.salesforce.marketingcloud.notifications.NotificationManager.d
            java.lang.String r3 = r6.mediaUrl()
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            java.lang.String r4 = "Unable to load notification image %s"
            com.salesforce.marketingcloud.g.b(r2, r7, r4, r3)
        L_0x005e:
            r7 = r1
        L_0x005f:
            if (r7 != 0) goto L_0x0070
            java.lang.String r2 = r6.mediaAltText()
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L_0x0070
            java.lang.String r2 = r6.mediaAltText()
            goto L_0x0074
        L_0x0070:
            java.lang.String r2 = r6.alert()
        L_0x0074:
            r0.setContentText(r2)
            r0.setTicker(r2)
            if (r7 != 0) goto L_0x0090
            androidx.core.app.NotificationCompat$BigTextStyle r7 = new androidx.core.app.NotificationCompat$BigTextStyle
            r7.<init>()
            androidx.core.app.NotificationCompat$BigTextStyle r7 = r7.bigText(r2)
            java.lang.String r2 = r6.title()
            androidx.core.app.NotificationCompat$BigTextStyle r7 = r7.setBigContentTitle(r2)
            r0.setStyle(r7)
        L_0x0090:
            int[] r7 = com.salesforce.marketingcloud.notifications.b.a.a
            com.salesforce.marketingcloud.notifications.NotificationMessage$Sound r2 = r6.sound()
            int r2 = r2.ordinal()
            r7 = r7[r2]
            if (r7 == r8) goto L_0x00b5
            r5 = 2
            if (r7 == r5) goto L_0x00af
            r5 = 3
            if (r7 == r5) goto L_0x00a5
            goto L_0x00c0
        L_0x00a5:
            java.lang.String r5 = com.salesforce.marketingcloud.notifications.NotificationManager.d
            java.lang.Object[] r6 = new java.lang.Object[r1]
            java.lang.String r7 = "No sound was set for notification."
            com.salesforce.marketingcloud.g.a((java.lang.String) r5, (java.lang.String) r7, (java.lang.Object[]) r6)
            goto L_0x00c0
        L_0x00af:
            android.net.Uri r5 = android.provider.Settings.System.DEFAULT_NOTIFICATION_URI
        L_0x00b1:
            r0.setSound(r5)
            goto L_0x00c0
        L_0x00b5:
            java.lang.String r6 = r6.soundName()
            android.net.Uri r7 = android.provider.Settings.System.DEFAULT_NOTIFICATION_URI
            android.net.Uri r5 = a(r5, r6, r7)
            goto L_0x00b1
        L_0x00c0:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.notifications.b.a(android.content.Context, com.salesforce.marketingcloud.notifications.NotificationMessage, java.lang.String, int):androidx.core.app.NotificationCompat$Builder");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0017  */
    @android.annotation.SuppressLint({"NewApi"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String a(android.content.Context r5, com.salesforce.marketingcloud.notifications.NotificationMessage r6) {
        /*
            r4 = this;
            com.salesforce.marketingcloud.notifications.NotificationManager$NotificationChannelIdProvider r0 = r4.c
            r1 = 0
            if (r0 == 0) goto L_0x0014
            java.lang.String r6 = r0.getNotificationChannelId(r5, r6)     // Catch:{ Exception -> 0x000a }
            goto L_0x0015
        L_0x000a:
            r6 = move-exception
            java.lang.String r0 = com.salesforce.marketingcloud.notifications.NotificationManager.d
            java.lang.Object[] r2 = new java.lang.Object[r1]
            java.lang.String r3 = "Exception thrown while app determined channel id for notification message."
            com.salesforce.marketingcloud.g.b(r0, r6, r3, r2)
        L_0x0014:
            r6 = 0
        L_0x0015:
            if (r6 != 0) goto L_0x001c
            a((android.content.Context) r5, (boolean) r1)
            java.lang.String r6 = "com.salesforce.marketingcloud.DEFAULT_FOREGROUND_CHANNEL"
        L_0x001c:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.notifications.b.a(android.content.Context, com.salesforce.marketingcloud.notifications.NotificationMessage):java.lang.String");
    }
}
