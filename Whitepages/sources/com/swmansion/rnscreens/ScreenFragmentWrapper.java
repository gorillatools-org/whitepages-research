package com.swmansion.rnscreens;

import android.app.Activity;
import com.facebook.react.bridge.ReactContext;
import java.util.List;

public interface ScreenFragmentWrapper extends FragmentHolder, ScreenEventDispatcher {
    void addChildScreenContainer(ScreenContainer screenContainer);

    List getChildScreenContainers();

    Screen getScreen();

    void onContainerUpdate();

    void removeChildScreenContainer(ScreenContainer screenContainer);

    Activity tryGetActivity();

    ReactContext tryGetContext();
}
