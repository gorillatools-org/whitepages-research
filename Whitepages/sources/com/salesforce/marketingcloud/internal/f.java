package com.salesforce.marketingcloud.internal;

import com.facebook.react.devsupport.StackTraceHelper;
import com.salesforce.marketingcloud.messages.Message;
import java.util.Date;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class f {
    public static final a a = new a((DefaultConstructorMarker) null);

    public static final class a {
        private a() {
        }

        public final Date a(Message message) {
            Intrinsics.checkNotNullParameter(message, StackTraceHelper.MESSAGE_KEY);
            return message.m669lastShownDate();
        }

        public final Date b(Message message) {
            Intrinsics.checkNotNullParameter(message, StackTraceHelper.MESSAGE_KEY);
            return message.m671nextAllowedShow();
        }

        public final int c(Message message) {
            Intrinsics.checkNotNullParameter(message, StackTraceHelper.MESSAGE_KEY);
            return message.m673notificationId();
        }

        public final int d(Message message) {
            Intrinsics.checkNotNullParameter(message, StackTraceHelper.MESSAGE_KEY);
            return message.m675periodShowCount();
        }

        public final int e(Message message) {
            Intrinsics.checkNotNullParameter(message, StackTraceHelper.MESSAGE_KEY);
            return message.m677showCount();
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void a(Message message, Date date) {
            Intrinsics.checkNotNullParameter(message, StackTraceHelper.MESSAGE_KEY);
            message.m670lastShownDate(date);
        }

        public final void b(Message message, Date date) {
            Intrinsics.checkNotNullParameter(message, StackTraceHelper.MESSAGE_KEY);
            message.m672nextAllowedShow(date);
        }

        public final void c(Message message, int i) {
            Intrinsics.checkNotNullParameter(message, StackTraceHelper.MESSAGE_KEY);
            message.m678showCount(i);
        }

        public final void a(Message message, int i) {
            Intrinsics.checkNotNullParameter(message, StackTraceHelper.MESSAGE_KEY);
            message.m674notificationId(i);
        }

        public final void b(Message message, int i) {
            Intrinsics.checkNotNullParameter(message, StackTraceHelper.MESSAGE_KEY);
            message.m676periodShowCount(i);
        }
    }

    public static final Date a(Message message) {
        return a.a(message);
    }

    public static final Date b(Message message) {
        return a.b(message);
    }

    public static final int c(Message message) {
        return a.c(message);
    }

    public static final int d(Message message) {
        return a.d(message);
    }

    public static final int e(Message message) {
        return a.e(message);
    }

    public static final void a(Message message, Date date) {
        a.a(message, date);
    }

    public static final void b(Message message, Date date) {
        a.b(message, date);
    }

    public static final void c(Message message, int i) {
        a.c(message, i);
    }

    public static final void a(Message message, int i) {
        a.a(message, i);
    }

    public static final void b(Message message, int i) {
        a.b(message, i);
    }
}
