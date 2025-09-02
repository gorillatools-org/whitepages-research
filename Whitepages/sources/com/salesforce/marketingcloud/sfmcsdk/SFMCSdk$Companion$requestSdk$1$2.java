package com.salesforce.marketingcloud.sfmcsdk;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class SFMCSdk$Companion$requestSdk$1$2 extends Lambda implements Function0 {
    final /* synthetic */ WhenReadyHandler $handler;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SFMCSdk$Companion$requestSdk$1$2(WhenReadyHandler whenReadyHandler) {
        super(0);
        this.$handler = whenReadyHandler;
    }

    public final String invoke() {
        return "Failure during requestSdk() delivery for " + this.$handler + '.';
    }
}
