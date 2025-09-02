package com.swmansion.rnscreens;

import com.swmansion.rnscreens.ScreenFragment;

public interface ScreenEventDispatcher {
    void dispatchLifecycleEventInChildContainers(ScreenFragment.ScreenLifecycleEvent screenLifecycleEvent);

    void updateLastEventDispatched(ScreenFragment.ScreenLifecycleEvent screenLifecycleEvent);
}
