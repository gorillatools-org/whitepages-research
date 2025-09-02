package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.proguard.annotations.DoNotStripAny;
import com.facebook.react.fabric.mounting.MountingManager;

@DoNotStripAny
public interface MountItem {
    void execute(MountingManager mountingManager);

    int getSurfaceId();
}
