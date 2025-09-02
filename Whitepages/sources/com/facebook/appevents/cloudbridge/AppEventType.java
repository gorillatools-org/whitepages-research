package com.facebook.appevents.cloudbridge;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public enum AppEventType {
    MOBILE_APP_INSTALL,
    CUSTOM,
    OTHER;
    
    public static final Companion Companion = null;

    static {
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AppEventType invoke(String str) {
            Intrinsics.checkNotNullParameter(str, "rawValue");
            if (Intrinsics.areEqual((Object) str, (Object) "MOBILE_APP_INSTALL")) {
                return AppEventType.MOBILE_APP_INSTALL;
            }
            if (Intrinsics.areEqual((Object) str, (Object) "CUSTOM_APP_EVENTS")) {
                return AppEventType.CUSTOM;
            }
            return AppEventType.OTHER;
        }
    }
}
