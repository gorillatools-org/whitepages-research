package com.salesforce.marketingcloud.sfmcsdk.modules;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class Module$initModule$2 extends Lambda implements Function0 {
    final /* synthetic */ Module this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Module$initModule$2(Module module) {
        super(0);
        this.this$0 = module;
    }

    public final String invoke() {
        return "An exception occurred while initializing module " + this.this$0;
    }
}
