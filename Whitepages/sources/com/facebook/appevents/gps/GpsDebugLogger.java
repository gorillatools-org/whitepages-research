package com.facebook.appevents.gps;

import android.content.Context;
import android.os.Bundle;
import com.facebook.appevents.InternalAppEventsLogger;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.text.StringsKt;

public final class GpsDebugLogger {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final boolean shouldLog = (Random.Default.nextDouble() <= 1.0E-4d);
    private final InternalAppEventsLogger internalAppEventsLogger;

    public GpsDebugLogger(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.internalAppEventsLogger = new InternalAppEventsLogger(context);
    }

    public final void log(String str, Bundle bundle) {
        if (shouldLog && isGPSDebugEvent(str)) {
            this.internalAppEventsLogger.logEventImplicitly(str, bundle);
        }
    }

    private final boolean isGPSDebugEvent(String str) {
        if (str != null) {
            return StringsKt.contains$default((CharSequence) str, (CharSequence) "gps", false, 2, (Object) null);
        }
        return false;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
