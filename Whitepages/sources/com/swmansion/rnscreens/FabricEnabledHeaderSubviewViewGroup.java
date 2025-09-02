package com.swmansion.rnscreens;

import android.content.Context;
import android.view.ViewGroup;
import com.facebook.react.uimanager.StateWrapper;

public abstract class FabricEnabledHeaderSubviewViewGroup extends ViewGroup {
    public final void setStateWrapper(StateWrapper stateWrapper) {
    }

    public FabricEnabledHeaderSubviewViewGroup(Context context) {
        super(context);
    }
}
