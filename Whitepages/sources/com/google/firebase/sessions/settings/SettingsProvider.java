package com.google.firebase.sessions.settings;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.time.Duration;

public interface SettingsProvider {
    Double getSamplingRate();

    Boolean getSessionEnabled();

    /* renamed from: getSessionRestartTimeout-FghU774  reason: not valid java name */
    Duration m599getSessionRestartTimeoutFghU774();

    boolean isSettingsStale();

    Object updateSettings(Continuation continuation);

    public static final class DefaultImpls {
        public static boolean isSettingsStale(SettingsProvider settingsProvider) {
            return false;
        }

        public static Object updateSettings(SettingsProvider settingsProvider, Continuation continuation) {
            return Unit.INSTANCE;
        }
    }
}
