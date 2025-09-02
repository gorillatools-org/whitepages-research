package com.salesforce.marketingcloud.sfmcsdk;

import com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleConfig;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class SFMCSdkModuleConfig$Builder$pushModuleConfig$1 extends Lambda implements Function0 {
    final /* synthetic */ PushModuleConfig $value;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SFMCSdkModuleConfig$Builder$pushModuleConfig$1(PushModuleConfig pushModuleConfig) {
        super(0);
        this.$value = pushModuleConfig;
    }

    public final String invoke() {
        return "isModuleCompatible returned false. Config '" + this.$value + "' will not be applied.";
    }
}
