package com.salesforce.marketingcloud.sfmcsdk;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class SFMCSdk$Companion$configure$1$3$moduleInitLatch$1$1 extends Lambda implements Function0 {
    final /* synthetic */ SFMCSdkModuleConfig $config;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SFMCSdk$Companion$configure$1$3$moduleInitLatch$1$1(SFMCSdkModuleConfig sFMCSdkModuleConfig) {
        super(0);
        this.$config = sFMCSdkModuleConfig;
    }

    public final String invoke() {
        return "Initializing " + this.$config.getConfigs$sfmcsdk_release().size() + " modules.";
    }
}
