package com.salesforce.marketingcloud.sfmcsdk.components.behaviors;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class BehaviorManagerImpl$registerForBehaviors$2$1$1$1 extends Lambda implements Function0 {
    final /* synthetic */ BehaviorType $behaviorType;
    final /* synthetic */ BehaviorListener $listener;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BehaviorManagerImpl$registerForBehaviors$2$1$1$1(BehaviorType behaviorType, BehaviorListener behaviorListener) {
        super(0);
        this.$behaviorType = behaviorType;
        this.$listener = behaviorListener;
    }

    public final String invoke() {
        return "Delivering sticky behavior " + this.$behaviorType + " to " + this.$listener;
    }
}
