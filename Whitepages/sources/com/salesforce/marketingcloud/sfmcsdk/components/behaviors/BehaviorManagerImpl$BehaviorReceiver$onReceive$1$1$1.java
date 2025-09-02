package com.salesforce.marketingcloud.sfmcsdk.components.behaviors;

import android.content.Intent;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class BehaviorManagerImpl$BehaviorReceiver$onReceive$1$1$1 extends Lambda implements Function0 {
    final /* synthetic */ BehaviorType $behavior;
    final /* synthetic */ Intent $it;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BehaviorManagerImpl$BehaviorReceiver$onReceive$1$1$1(BehaviorType behaviorType, Intent intent) {
        super(0);
        this.$behavior = behaviorType;
        this.$it = intent;
    }

    public final String invoke() {
        return this.$behavior + " received with " + this.$it.getExtras() + " extras.";
    }
}
