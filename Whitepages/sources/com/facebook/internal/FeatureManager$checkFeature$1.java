package com.facebook.internal;

import com.facebook.internal.FeatureManager;
import com.facebook.internal.FetchedAppGateKeepersManager;

public final class FeatureManager$checkFeature$1 implements FetchedAppGateKeepersManager.Callback {
    final /* synthetic */ FeatureManager.Callback $callback;
    final /* synthetic */ FeatureManager.Feature $feature;

    FeatureManager$checkFeature$1(FeatureManager.Callback callback, FeatureManager.Feature feature) {
        this.$callback = callback;
        this.$feature = feature;
    }

    public void onCompleted() {
        this.$callback.onCompleted(FeatureManager.isEnabled(this.$feature));
    }
}
