package com.salesforce.marketingcloud.internal;

import com.facebook.react.devsupport.StackTraceHelper;
import com.google.firebase.messaging.Constants;
import com.salesforce.marketingcloud.messages.Message;
import com.salesforce.marketingcloud.messages.Region;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class h {
    public static final a a = new a((DefaultConstructorMarker) null);

    public static final class a {
        private a() {
        }

        public final NotificationMessage a(Message message, Region region) {
            Intrinsics.checkNotNullParameter(message, StackTraceHelper.MESSAGE_KEY);
            Intrinsics.checkNotNullParameter(region, "region");
            return NotificationMessage.Companion.a(message, region);
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final NotificationMessage a(Map<String, String> map) {
            Intrinsics.checkNotNullParameter(map, Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
            return NotificationMessage.Companion.a(map);
        }

        public final void a(NotificationMessage notificationMessage, int i) {
            Intrinsics.checkNotNullParameter(notificationMessage, StackTraceHelper.MESSAGE_KEY);
            notificationMessage.setNotificationId$sdk_release(i);
        }
    }

    public static final NotificationMessage a(Message message, Region region) {
        return a.a(message, region);
    }

    public static final NotificationMessage a(Map<String, String> map) {
        return a.a(map);
    }

    public static final void a(NotificationMessage notificationMessage, int i) {
        a.a(notificationMessage, i);
    }
}
