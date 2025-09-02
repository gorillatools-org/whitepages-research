package com.salesforce.marketingcloud.sfmcsdk.modules;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class Module$initModule$1$execute$2$2$1$1 extends Lambda implements Function0 {
    final /* synthetic */ ModuleReadyHandler $handler;
    final /* synthetic */ ModuleInterface $it;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Module$initModule$1$execute$2$2$1$1(ModuleInterface moduleInterface, ModuleReadyHandler moduleReadyHandler) {
        super(0);
        this.$it = moduleInterface;
        this.$handler = moduleReadyHandler;
    }

    public final String invoke() {
        return "Failure during module " + this.$it + " delivery for " + this.$handler + '.';
    }
}
