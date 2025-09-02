package com.salesforce.marketingcloud.sfmcsdk;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class SFMCSdkReceiver$onReceive$1 extends Lambda implements Function0 {
    final /* synthetic */ String $action;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SFMCSdkReceiver$onReceive$1(String str) {
        super(0);
        this.$action = str;
    }

    public final String invoke() {
        return "onReceive with action: " + this.$action;
    }
}
