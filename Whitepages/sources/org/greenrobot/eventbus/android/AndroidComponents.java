package org.greenrobot.eventbus.android;

import org.greenrobot.eventbus.Logger;
import org.greenrobot.eventbus.MainThreadSupport;

public abstract class AndroidComponents {
    private static final AndroidComponents implementation = (AndroidDependenciesDetector.isAndroidSDKAvailable() ? AndroidDependenciesDetector.instantiateAndroidComponents() : null);
    public final MainThreadSupport defaultMainThreadSupport;
    public final Logger logger;

    public static boolean areAvailable() {
        return implementation != null;
    }

    public static AndroidComponents get() {
        return implementation;
    }

    public AndroidComponents(Logger logger2, MainThreadSupport mainThreadSupport) {
        this.logger = logger2;
        this.defaultMainThreadSupport = mainThreadSupport;
    }
}
