package com.facebook.appevents;

import android.content.Context;
import android.os.Bundle;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class InternalAppEventsLogger {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final AppEventsLoggerImpl loggerImpl;

    public InternalAppEventsLogger(AppEventsLoggerImpl appEventsLoggerImpl) {
        Intrinsics.checkNotNullParameter(appEventsLoggerImpl, "loggerImpl");
        this.loggerImpl = appEventsLoggerImpl;
    }

    public InternalAppEventsLogger(Context context) {
        this(new AppEventsLoggerImpl(context, (String) null, (AccessToken) null));
    }

    public InternalAppEventsLogger(Context context, String str) {
        this(new AppEventsLoggerImpl(context, str, (AccessToken) null));
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public InternalAppEventsLogger(String str, String str2, AccessToken accessToken) {
        this(new AppEventsLoggerImpl(str, str2, accessToken));
        Intrinsics.checkNotNullParameter(str, "activityName");
    }

    public final void logEvent(String str, Bundle bundle) {
        if (FacebookSdk.getAutoLogAppEventsEnabled()) {
            this.loggerImpl.logEvent(str, bundle);
        }
    }

    public final void logEvent(String str, double d, Bundle bundle) {
        if (FacebookSdk.getAutoLogAppEventsEnabled()) {
            this.loggerImpl.logEvent(str, d, bundle);
        }
    }

    public final void logPurchaseImplicitly(BigDecimal bigDecimal, Currency currency, Bundle bundle, OperationalData operationalData) {
        if (FacebookSdk.getAutoLogAppEventsEnabled()) {
            this.loggerImpl.logPurchaseImplicitly(bigDecimal, currency, bundle, operationalData);
        }
    }

    public final void logEventFromSE(String str, String str2) {
        this.loggerImpl.logEventFromSE(str, str2);
    }

    public final void logEventImplicitly(String str, BigDecimal bigDecimal, Currency currency, Bundle bundle, OperationalData operationalData) {
        if (FacebookSdk.getAutoLogAppEventsEnabled()) {
            this.loggerImpl.logEventImplicitly(str, bigDecimal, currency, bundle, operationalData);
        }
    }

    public final void logEventImplicitly(String str) {
        if (FacebookSdk.getAutoLogAppEventsEnabled()) {
            this.loggerImpl.logEventImplicitly(str, (Double) null, (Bundle) null);
        }
    }

    public final void logEventImplicitly(String str, Double d, Bundle bundle) {
        if (FacebookSdk.getAutoLogAppEventsEnabled()) {
            this.loggerImpl.logEventImplicitly(str, d, bundle);
        }
    }

    public final void logEventImplicitly(String str, Bundle bundle) {
        if (FacebookSdk.getAutoLogAppEventsEnabled()) {
            this.loggerImpl.logEventImplicitly(str, (Double) null, bundle);
        }
    }

    public final void logChangedSettingsEvent(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "parameters");
        if (((bundle.getInt("previous") & 2) != 0) || FacebookSdk.getAutoLogAppEventsEnabled()) {
            this.loggerImpl.logEventImplicitly("fb_sdk_settings_changed", (Double) null, bundle);
        }
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

        public final AppEventsLogger.FlushBehavior getFlushBehavior() {
            return AppEventsLoggerImpl.Companion.getFlushBehavior();
        }

        public final Executor getAnalyticsExecutor() {
            return AppEventsLoggerImpl.Companion.getAnalyticsExecutor();
        }

        public final String getPushNotificationsRegistrationId() {
            return AppEventsLoggerImpl.Companion.getPushNotificationsRegistrationId();
        }

        public final void setInternalUserData(Map map) {
            Intrinsics.checkNotNullParameter(map, "ud");
            UserDataStore.setInternalUd(map);
        }

        public final InternalAppEventsLogger createInstance(String str, String str2, AccessToken accessToken) {
            Intrinsics.checkNotNullParameter(str, "activityName");
            return new InternalAppEventsLogger(str, str2, accessToken);
        }
    }
}
