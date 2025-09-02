package com.salesforce.marketingcloud.sfmcsdk;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

final class SFMCSdk$Companion$notifyInitializationStatusListener$1 extends Lambda implements Function0 {
    final /* synthetic */ Function1 $listener;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SFMCSdk$Companion$notifyInitializationStatusListener$1(Function1 function1) {
        super(0);
        this.$listener = function1;
    }

    public final String invoke() {
        return "Failed to delivery initialization state to listener " + this.$listener + '.';
    }
}
