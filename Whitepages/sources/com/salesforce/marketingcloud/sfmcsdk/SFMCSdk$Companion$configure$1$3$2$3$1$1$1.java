package com.salesforce.marketingcloud.sfmcsdk;

import com.salesforce.marketingcloud.sfmcsdk.modules.Config;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class SFMCSdk$Companion$configure$1$3$2$3$1$1$1 extends Lambda implements Function0 {
    final /* synthetic */ Config $config;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SFMCSdk$Companion$configure$1$3$2$3$1$1$1(Config config) {
        super(0);
        this.$config = config;
    }

    public final String invoke() {
        return "Module (" + this.$config.getModuleIdentifier() + ") has completed initialization.";
    }
}
