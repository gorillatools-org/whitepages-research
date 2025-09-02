package com.salesforce.marketingcloud.proximity;

import com.salesforce.marketingcloud.MCKeep;
import com.salesforce.marketingcloud.notifications.NotificationManager;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@MCKeep
public final class ProximityNotificationCustomizationOptions {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static ProximityNotificationCustomizationOptions instance;
    private final NotificationManager.NotificationChannelIdProvider channelIdProvider;
    private final int smallIconResId;

    @MCKeep
    public static final class Companion {
        private Companion() {
        }

        public final ProximityNotificationCustomizationOptions create(int i) {
            if (ProximityNotificationCustomizationOptions.instance == null) {
                ProximityNotificationCustomizationOptions.instance = new ProximityNotificationCustomizationOptions(i, (NotificationManager.NotificationChannelIdProvider) null, (DefaultConstructorMarker) null);
            }
            ProximityNotificationCustomizationOptions access$getInstance$cp = ProximityNotificationCustomizationOptions.instance;
            Intrinsics.checkNotNull(access$getInstance$cp);
            return access$getInstance$cp;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ProximityNotificationCustomizationOptions create(int i, NotificationManager.NotificationChannelIdProvider notificationChannelIdProvider) {
            Intrinsics.checkNotNullParameter(notificationChannelIdProvider, "channelIdProvider");
            if (ProximityNotificationCustomizationOptions.instance == null) {
                ProximityNotificationCustomizationOptions.instance = new ProximityNotificationCustomizationOptions(i, notificationChannelIdProvider, (DefaultConstructorMarker) null);
            }
            ProximityNotificationCustomizationOptions access$getInstance$cp = ProximityNotificationCustomizationOptions.instance;
            Intrinsics.checkNotNull(access$getInstance$cp);
            return access$getInstance$cp;
        }
    }

    private ProximityNotificationCustomizationOptions(int i, NotificationManager.NotificationChannelIdProvider notificationChannelIdProvider) {
        this.smallIconResId = i;
        this.channelIdProvider = notificationChannelIdProvider;
    }

    public static final ProximityNotificationCustomizationOptions create(int i) {
        return Companion.create(i);
    }

    public final NotificationManager.NotificationChannelIdProvider getChannelIdProvider() {
        return this.channelIdProvider;
    }

    public final int getSmallIconResId() {
        return this.smallIconResId;
    }

    public /* synthetic */ ProximityNotificationCustomizationOptions(int i, NotificationManager.NotificationChannelIdProvider notificationChannelIdProvider, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, notificationChannelIdProvider);
    }

    public static final ProximityNotificationCustomizationOptions create(int i, NotificationManager.NotificationChannelIdProvider notificationChannelIdProvider) {
        return Companion.create(i, notificationChannelIdProvider);
    }
}
