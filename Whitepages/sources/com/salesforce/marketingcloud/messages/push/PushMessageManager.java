package com.salesforce.marketingcloud.messages.push;

import com.google.firebase.messaging.RemoteMessage;
import com.salesforce.marketingcloud.MCKeep;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import java.util.Map;
import org.json.JSONObject;

public abstract class PushMessageManager {
    static final String d = g.a("PushMessageManager");
    public static final String e = "com.salesforce.marketingcloud.messages.push.TOKEN_REFRESHED";
    public static final String f = "com.salesforce.marketingcloud.push.TOKEN_REFRESH_SUCCESSFUL";
    public static final String g = "com.salesforce.marketingcloud.push.TOKEN_SENDER_ID";
    public static final String h = "com.salesforce.marketingcloud.notifications.PUSH_ENABLED";
    public static final String i = "com.salesforce.marketingcloud.push.TOKEN";

    @MCKeep
    public interface PushTokenRefreshListener {
        void onTokenRefreshed(String str);
    }

    @MCKeep
    public interface SilentPushListener {
        void silentPushReceived(Map<String, String> map);
    }

    @MCKeep
    public static boolean isMarketingCloudPush(RemoteMessage remoteMessage) {
        return remoteMessage != null && isMarketingCloudPush(remoteMessage.getData());
    }

    @MCKeep
    public abstract void disablePush();

    @MCKeep
    public abstract void enablePush();

    @MCKeep
    public abstract JSONObject getPushDebugInfo();

    @MCKeep
    public abstract String getPushToken();

    @MCKeep
    public abstract boolean handleMessage(RemoteMessage remoteMessage);

    @MCKeep
    public abstract boolean handleMessage(Map<String, String> map);

    @MCKeep
    public abstract boolean isPushEnabled();

    @MCKeep
    public abstract void registerSilentPushListener(SilentPushListener silentPushListener);

    @MCKeep
    public abstract void registerTokenRefreshListener(PushTokenRefreshListener pushTokenRefreshListener);

    @MCKeep
    public abstract void setPushToken(String str);

    @MCKeep
    public abstract void unregisterSilentPushListener(SilentPushListener silentPushListener);

    @MCKeep
    public abstract void unregisterTokenRefreshListener(PushTokenRefreshListener pushTokenRefreshListener);

    @MCKeep
    public static boolean isMarketingCloudPush(Map<String, String> map) {
        return map != null && "SFMC".equalsIgnoreCase(map.get(NotificationMessage.NOTIF_KEY_SID));
    }
}
