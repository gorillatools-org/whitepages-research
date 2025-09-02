package com.salesforce.marketingcloud.sfmcsdk.components.identity;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class Identity$Companion$instance$1 extends Lambda implements Function0 {
    final /* synthetic */ Identity $value;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Identity$Companion$instance$1(Identity identity) {
        super(0);
        this.$value = identity;
    }

    public final String invoke() {
        return "instance = " + this.$value + ", _instance = " + Identity._instance;
    }
}
