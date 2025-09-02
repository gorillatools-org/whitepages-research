package com.salesforce.marketingcloud.sfmcsdk.modules;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class Module$initModule$1$execute$1 extends Lambda implements Function0 {
    final /* synthetic */ Config $config;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Module$initModule$1$execute$1(Config config) {
        super(0);
        this.$config = config;
    }

    public final String invoke() {
        return "~~ " + this.$config.getModuleIdentifier().name() + " Module Initialization Started ~~";
    }
}
