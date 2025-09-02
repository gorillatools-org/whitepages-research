package com.salesforce.marketingcloud.sfmcsdk;

import com.salesforce.marketingcloud.sfmcsdk.modules.cdp.CdpModuleConfig;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class SFMCSdkModuleConfig$Builder$cdpModuleConfig$1 extends Lambda implements Function0 {
    final /* synthetic */ CdpModuleConfig $value;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SFMCSdkModuleConfig$Builder$cdpModuleConfig$1(CdpModuleConfig cdpModuleConfig) {
        super(0);
        this.$value = cdpModuleConfig;
    }

    public final String invoke() {
        return "isModuleCompatible returned false. Config '" + this.$value + "' will not be applied.";
    }
}
