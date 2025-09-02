package com.salesforce.marketingcloud.internal;

import com.facebook.react.devsupport.StackTraceHelper;
import com.salesforce.marketingcloud.messages.iam.InAppMessage;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

public final class a {
    public static final C0018a a = new C0018a((DefaultConstructorMarker) null);

    /* renamed from: com.salesforce.marketingcloud.internal.a$a  reason: collision with other inner class name */
    public static final class C0018a {
        private C0018a() {
        }

        public final String a(InAppMessage inAppMessage) {
            Intrinsics.checkNotNullParameter(inAppMessage, StackTraceHelper.MESSAGE_KEY);
            return inAppMessage.m699activityInstanceId();
        }

        public final JSONObject b(InAppMessage inAppMessage) {
            Intrinsics.checkNotNullParameter(inAppMessage, StackTraceHelper.MESSAGE_KEY);
            return inAppMessage.m720toJson();
        }

        public /* synthetic */ C0018a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final InAppMessage.CloseButton a() {
            return InAppMessage.CloseButton.Companion.a();
        }
    }

    public static final String a(InAppMessage inAppMessage) {
        return a.a(inAppMessage);
    }

    public static final JSONObject b(InAppMessage inAppMessage) {
        return a.b(inAppMessage);
    }

    public static final InAppMessage.CloseButton a() {
        return a.a();
    }
}
