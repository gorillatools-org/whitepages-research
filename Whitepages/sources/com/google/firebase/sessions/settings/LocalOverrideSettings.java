package com.google.firebase.sessions.settings;

import android.content.Context;
import android.os.Bundle;
import com.google.firebase.sessions.settings.SettingsProvider;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;

public final class LocalOverrideSettings implements SettingsProvider {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @Deprecated
    public static final String SAMPLING_RATE = "firebase_sessions_sampling_rate";
    @Deprecated
    public static final String SESSIONS_ENABLED = "firebase_sessions_enabled";
    @Deprecated
    public static final String SESSION_RESTART_TIMEOUT = "firebase_sessions_sessions_restart_timeout";
    private final Bundle metadata;

    private static /* synthetic */ void getMetadata$annotations() {
    }

    public LocalOverrideSettings(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
        this.metadata = bundle == null ? Bundle.EMPTY : bundle;
    }

    public boolean isSettingsStale() {
        return SettingsProvider.DefaultImpls.isSettingsStale(this);
    }

    public Object updateSettings(Continuation continuation) {
        return SettingsProvider.DefaultImpls.updateSettings(this, continuation);
    }

    public Boolean getSessionEnabled() {
        if (this.metadata.containsKey(SESSIONS_ENABLED)) {
            return Boolean.valueOf(this.metadata.getBoolean(SESSIONS_ENABLED));
        }
        return null;
    }

    /* renamed from: getSessionRestartTimeout-FghU774  reason: not valid java name */
    public Duration m595getSessionRestartTimeoutFghU774() {
        if (this.metadata.containsKey(SESSION_RESTART_TIMEOUT)) {
            return Duration.m873boximpl(DurationKt.toDuration(this.metadata.getInt(SESSION_RESTART_TIMEOUT), DurationUnit.SECONDS));
        }
        return null;
    }

    public Double getSamplingRate() {
        if (this.metadata.containsKey(SAMPLING_RATE)) {
            return Double.valueOf(this.metadata.getDouble(SAMPLING_RATE));
        }
        return null;
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
