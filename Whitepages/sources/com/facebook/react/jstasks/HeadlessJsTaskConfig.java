package com.facebook.react.jstasks;

import com.facebook.react.bridge.WritableMap;
import com.google.firebase.messaging.Constants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class HeadlessJsTaskConfig {
    private final WritableMap data;
    private final boolean isAllowedInForeground;
    private final HeadlessJsTaskRetryPolicy retryPolicy;
    private final String taskKey;
    private final long timeout;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public HeadlessJsTaskConfig(String str, WritableMap writableMap) {
        this(str, writableMap, 0, false, (HeadlessJsTaskRetryPolicy) null, 28, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "taskKey");
        Intrinsics.checkNotNullParameter(writableMap, Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public HeadlessJsTaskConfig(String str, WritableMap writableMap, long j) {
        this(str, writableMap, j, false, (HeadlessJsTaskRetryPolicy) null, 24, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "taskKey");
        Intrinsics.checkNotNullParameter(writableMap, Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public HeadlessJsTaskConfig(String str, WritableMap writableMap, long j, boolean z) {
        this(str, writableMap, j, z, (HeadlessJsTaskRetryPolicy) null, 16, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "taskKey");
        Intrinsics.checkNotNullParameter(writableMap, Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
    }

    public HeadlessJsTaskConfig(String str, WritableMap writableMap, long j, boolean z, HeadlessJsTaskRetryPolicy headlessJsTaskRetryPolicy) {
        Intrinsics.checkNotNullParameter(str, "taskKey");
        Intrinsics.checkNotNullParameter(writableMap, Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
        this.taskKey = str;
        this.data = writableMap;
        this.timeout = j;
        this.isAllowedInForeground = z;
        this.retryPolicy = headlessJsTaskRetryPolicy;
    }

    public final String getTaskKey() {
        return this.taskKey;
    }

    public final WritableMap getData() {
        return this.data;
    }

    public final long getTimeout() {
        return this.timeout;
    }

    public final boolean isAllowedInForeground() {
        return this.isAllowedInForeground;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ HeadlessJsTaskConfig(String str, WritableMap writableMap, long j, boolean z, HeadlessJsTaskRetryPolicy headlessJsTaskRetryPolicy, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, writableMap, (i & 4) != 0 ? 0 : j, (i & 8) != 0 ? false : z, (i & 16) != 0 ? NoRetryPolicy.INSTANCE : headlessJsTaskRetryPolicy);
    }

    public final HeadlessJsTaskRetryPolicy getRetryPolicy() {
        return this.retryPolicy;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public HeadlessJsTaskConfig(com.facebook.react.jstasks.HeadlessJsTaskConfig r9) {
        /*
            r8 = this;
            java.lang.String r0 = "source"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            java.lang.String r2 = r9.taskKey
            com.facebook.react.bridge.WritableMap r0 = r9.data
            com.facebook.react.bridge.WritableMap r3 = r0.copy()
            long r4 = r9.timeout
            boolean r6 = r9.isAllowedInForeground
            com.facebook.react.jstasks.HeadlessJsTaskRetryPolicy r9 = r9.retryPolicy
            if (r9 == 0) goto L_0x001b
            com.facebook.react.jstasks.HeadlessJsTaskRetryPolicy r9 = r9.copy()
        L_0x0019:
            r7 = r9
            goto L_0x001d
        L_0x001b:
            r9 = 0
            goto L_0x0019
        L_0x001d:
            r1 = r8
            r1.<init>(r2, r3, r4, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.jstasks.HeadlessJsTaskConfig.<init>(com.facebook.react.jstasks.HeadlessJsTaskConfig):void");
    }
}
