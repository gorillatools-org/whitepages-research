package com.salesforce.marketingcloud.internal;

import com.facebook.react.devsupport.StackTraceHelper;
import com.salesforce.marketingcloud.messages.inbox.InboxMessage;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class b {
    public static final a a = new a((DefaultConstructorMarker) null);

    public static final class a {
        private a() {
        }

        public final String a(InboxMessage inboxMessage) {
            Intrinsics.checkNotNullParameter(inboxMessage, StackTraceHelper.MESSAGE_KEY);
            return inboxMessage.m764messageHash();
        }

        public final String b(InboxMessage inboxMessage) {
            Intrinsics.checkNotNullParameter(inboxMessage, StackTraceHelper.MESSAGE_KEY);
            return inboxMessage.m766requestId();
        }

        public final void c(InboxMessage inboxMessage, boolean z) {
            Intrinsics.checkNotNullParameter(inboxMessage, StackTraceHelper.MESSAGE_KEY);
            inboxMessage.m765read(z);
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void a(InboxMessage inboxMessage, boolean z) {
            Intrinsics.checkNotNullParameter(inboxMessage, StackTraceHelper.MESSAGE_KEY);
            inboxMessage.m747deleted(z);
        }

        public final void b(InboxMessage inboxMessage, boolean z) {
            Intrinsics.checkNotNullParameter(inboxMessage, StackTraceHelper.MESSAGE_KEY);
            inboxMessage.m762dirty(z);
        }

        public final int c(InboxMessage inboxMessage) {
            Intrinsics.checkNotNullParameter(inboxMessage, StackTraceHelper.MESSAGE_KEY);
            return inboxMessage.m767viewCount();
        }
    }

    public static final String a(InboxMessage inboxMessage) {
        return a.a(inboxMessage);
    }

    public static final String b(InboxMessage inboxMessage) {
        return a.b(inboxMessage);
    }

    public static final void c(InboxMessage inboxMessage, boolean z) {
        a.c(inboxMessage, z);
    }

    public static final void a(InboxMessage inboxMessage, boolean z) {
        a.a(inboxMessage, z);
    }

    public static final void b(InboxMessage inboxMessage, boolean z) {
        a.b(inboxMessage, z);
    }

    public static final int c(InboxMessage inboxMessage) {
        return a.c(inboxMessage);
    }
}
