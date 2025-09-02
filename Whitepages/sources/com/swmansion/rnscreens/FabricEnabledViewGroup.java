package com.swmansion.rnscreens;

import android.view.ViewGroup;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.StateWrapper;

public abstract class FabricEnabledViewGroup extends ViewGroup {
    public final void setStateWrapper(StateWrapper stateWrapper) {
    }

    public FabricEnabledViewGroup(ReactContext reactContext) {
        super(reactContext);
    }
}
