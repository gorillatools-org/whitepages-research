package com.salesforce.marketingcloud.sfmcsdk;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class WhenReadyHandler$execute$1 extends Lambda implements Function0 {
    final /* synthetic */ SFMCSdkReadyListener $listener;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    WhenReadyHandler$execute$1(SFMCSdkReadyListener sFMCSdkReadyListener) {
        super(0);
        this.$listener = sFMCSdkReadyListener;
    }

    public final String invoke() {
        return "Error in " + this.$listener.getClass().getName();
    }
}
