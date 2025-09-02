package com.salesforce.marketingcloud.sfmcsdk.components.behaviors;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class BehaviorManagerImpl$unregisterForAllBehaviors$1$1 extends Lambda implements Function0 {
    final /* synthetic */ BehaviorListener $listener;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BehaviorManagerImpl$unregisterForAllBehaviors$1$1(BehaviorListener behaviorListener) {
        super(0);
        this.$listener = behaviorListener;
    }

    public final String invoke() {
        return "Unregistering " + this.$listener + " for all behaviors.";
    }
}
