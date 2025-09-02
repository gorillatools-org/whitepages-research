package com.facebook.appevents;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import com.facebook.AccessToken;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class AppEventsLogger {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = AppEventsLogger.class.getCanonicalName();
    private final AppEventsLoggerImpl loggerImpl;

    public enum FlushBehavior {
        AUTO,
        EXPLICIT_ONLY
    }

    public /* synthetic */ AppEventsLogger(Context context, String str, AccessToken accessToken, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, str, accessToken);
    }

    private AppEventsLogger(Context context, String str, AccessToken accessToken) {
        this.loggerImpl = new AppEventsLoggerImpl(context, str, accessToken);
    }

    public final void logEvent(String str, Bundle bundle) {
        this.loggerImpl.logEvent(str, bundle);
    }

    public final void flush() {
        this.loggerImpl.flush();
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void activateApp(Application application, String str) {
            Intrinsics.checkNotNullParameter(application, "application");
            AppEventsLoggerImpl.Companion.activateApp(application, str);
        }

        public final void initializeLib(Context context, String str) {
            Intrinsics.checkNotNullParameter(context, "context");
            AppEventsLoggerImpl.Companion.initializeLib(context, str);
        }

        public final AppEventsLogger newLogger(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return new AppEventsLogger(context, (String) null, (AccessToken) null, (DefaultConstructorMarker) null);
        }

        public final FlushBehavior getFlushBehavior() {
            return AppEventsLoggerImpl.Companion.getFlushBehavior();
        }

        public final void onContextStop() {
            AppEventsLoggerImpl.Companion.onContextStop();
        }

        public final String getUserID() {
            return AnalyticsUserIDStore.getUserID();
        }

        public final String getAnonymousAppDeviceGUID(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return AppEventsLoggerImpl.Companion.getAnonymousAppDeviceGUID(context);
        }
    }
}
