package com.salesforce.marketingcloud.sfmcsdk.components.behaviors;

import java.util.EnumSet;

public interface BehaviorManager {
    void registerForBehaviors(EnumSet<BehaviorType> enumSet, BehaviorListener behaviorListener);

    void unregisterForAllBehaviors(BehaviorListener behaviorListener);
}
