package com.salesforce.marketingcloud.sfmcsdk.modules.push;

import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.analytics.AnalyticsManager;
import com.salesforce.marketingcloud.events.EventManager;
import com.salesforce.marketingcloud.messages.RegionMessageManager;
import com.salesforce.marketingcloud.messages.iam.InAppMessageManager;
import com.salesforce.marketingcloud.messages.inbox.InboxMessageManager;
import com.salesforce.marketingcloud.messages.push.PushMessageManager;
import com.salesforce.marketingcloud.notifications.NotificationManager;
import com.salesforce.marketingcloud.registration.RegistrationManager;
import com.salesforce.marketingcloud.sfmcsdk.modules.ModuleInterface;

public abstract class PushModuleInterface implements ModuleInterface {
    public abstract AnalyticsManager getAnalyticsManager();

    public abstract EventManager getEventManager();

    public abstract InAppMessageManager getInAppMessageManager();

    public abstract InboxMessageManager getInboxMessageManager();

    public abstract InitializationStatus getInitializationStatus();

    public abstract NotificationManager getNotificationManager();

    public abstract PushMessageManager getPushMessageManager();

    public abstract RegionMessageManager getRegionMessageManager();

    public abstract RegistrationManager getRegistrationManager();
}
