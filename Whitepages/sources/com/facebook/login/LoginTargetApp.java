package com.facebook.login;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public enum LoginTargetApp {
    FACEBOOK("facebook"),
    INSTAGRAM("instagram");
    
    public static final Companion Companion = null;
    private final String targetApp;

    private LoginTargetApp(String str) {
        this.targetApp = str;
    }

    static {
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    public String toString() {
        return this.targetApp;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final LoginTargetApp fromString(String str) {
            for (LoginTargetApp loginTargetApp : LoginTargetApp.values()) {
                if (Intrinsics.areEqual((Object) loginTargetApp.toString(), (Object) str)) {
                    return loginTargetApp;
                }
            }
            return LoginTargetApp.FACEBOOK;
        }
    }
}
