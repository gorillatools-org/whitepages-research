package com.salesforce.marketingcloud.sfmcsdk.components.behaviors;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class BehaviorManagerImpl$Companion$BehaviorRunnable$run$1$1$1$2 extends Lambda implements Function0 {
    final /* synthetic */ Exception $e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BehaviorManagerImpl$Companion$BehaviorRunnable$run$1$1$1$2(Exception exc) {
        super(0);
        this.$e = exc;
    }

    public final String invoke() {
        return "Exception " + this.$e.getLocalizedMessage() + " occurred.";
    }
}
