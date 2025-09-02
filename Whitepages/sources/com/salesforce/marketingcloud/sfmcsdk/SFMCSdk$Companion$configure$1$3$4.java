package com.salesforce.marketingcloud.sfmcsdk;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class SFMCSdk$Companion$configure$1$3$4 extends Lambda implements Function0 {
    final /* synthetic */ long $initStartedTime;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SFMCSdk$Companion$configure$1$3$4(long j) {
        super(0);
        this.$initStartedTime = j;
    }

    public final String invoke() {
        return "SFMC SDK Ready took " + (System.currentTimeMillis() - this.$initStartedTime) + "ms.";
    }
}
