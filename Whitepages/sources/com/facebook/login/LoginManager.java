package com.facebook.login;

import java.util.Set;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public abstract class LoginManager {
    public static final Companion Companion;
    /* access modifiers changed from: private */
    public static final Set OTHER_PUBLISH_PERMISSIONS;
    private static final String TAG;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isPublishPermission(String str) {
            if (str == null) {
                return false;
            }
            if (StringsKt.startsWith$default(str, "publish", false, 2, (Object) null) || StringsKt.startsWith$default(str, "manage", false, 2, (Object) null) || LoginManager.OTHER_PUBLISH_PERMISSIONS.contains(str)) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: private */
        public final Set getOtherPublishPermissions() {
            return SetsKt.setOf("ads_management", "create_event", "rsvp_event");
        }
    }

    static {
        Companion companion = new Companion((DefaultConstructorMarker) null);
        Companion = companion;
        OTHER_PUBLISH_PERMISSIONS = companion.getOtherPublishPermissions();
        String cls = LoginManager.class.toString();
        Intrinsics.checkNotNullExpressionValue(cls, "LoginManager::class.java.toString()");
        TAG = cls;
    }
}
