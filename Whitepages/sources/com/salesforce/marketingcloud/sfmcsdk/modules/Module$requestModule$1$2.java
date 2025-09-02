package com.salesforce.marketingcloud.sfmcsdk.modules;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class Module$requestModule$1$2 extends Lambda implements Function0 {
    final /* synthetic */ ModuleReadyHandler $handler;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Module$requestModule$1$2(ModuleReadyHandler moduleReadyHandler) {
        super(0);
        this.$handler = moduleReadyHandler;
    }

    public final String invoke() {
        return "Failure during requestPush() delivery for " + this.$handler + '.';
    }
}
