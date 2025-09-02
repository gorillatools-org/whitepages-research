package com.facebook.appevents.internal;

public final class Constants {
    public static final Constants INSTANCE = new Constants();

    public static final int getDefaultAppEventsSessionTimeoutInSeconds() {
        return 60;
    }

    private Constants() {
    }
}
