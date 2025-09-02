package com.salesforce.marketingcloud.sfmcsdk.components.utils;

import java.util.concurrent.ExecutorService;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class SdkExecutors {
    private final ExecutorService diskIO;
    private final ExecutorService networkIO;

    public SdkExecutors() {
        this((ExecutorService) null, (ExecutorService) null, 3, (DefaultConstructorMarker) null);
    }

    public SdkExecutors(ExecutorService executorService, ExecutorService executorService2) {
        Intrinsics.checkNotNullParameter(executorService, "diskIO");
        Intrinsics.checkNotNullParameter(executorService2, "networkIO");
        this.diskIO = executorService;
        this.networkIO = executorService2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ SdkExecutors(java.util.concurrent.ExecutorService r1, java.util.concurrent.ExecutorService r2, int r3, kotlin.jvm.internal.DefaultConstructorMarker r4) {
        /*
            r0 = this;
            r4 = r3 & 1
            if (r4 == 0) goto L_0x000d
            java.util.concurrent.ExecutorService r1 = java.util.concurrent.Executors.newSingleThreadExecutor()
            java.lang.String r4 = "newSingleThreadExecutor()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r4)
        L_0x000d:
            r4 = 2
            r3 = r3 & r4
            if (r3 == 0) goto L_0x001a
            java.util.concurrent.ExecutorService r2 = java.util.concurrent.Executors.newFixedThreadPool(r4)
            java.lang.String r3 = "newFixedThreadPool(2)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
        L_0x001a:
            r0.<init>(r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.sfmcsdk.components.utils.SdkExecutors.<init>(java.util.concurrent.ExecutorService, java.util.concurrent.ExecutorService, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final ExecutorService getDiskIO() {
        return this.diskIO;
    }

    public final ExecutorService getNetworkIO() {
        return this.networkIO;
    }
}
