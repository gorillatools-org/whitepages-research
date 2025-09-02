package com.facebook.appevents;

import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;

public final class AppEventsManager {
    public static final AppEventsManager INSTANCE = new AppEventsManager();

    private AppEventsManager() {
    }

    public static final void start() {
        Class<AppEventsManager> cls = AppEventsManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                FetchedAppSettingsManager.getAppSettingsAsync(new AppEventsManager$start$1());
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }
}
