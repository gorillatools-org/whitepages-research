package com.facebook;

import kotlin.jvm.internal.DefaultConstructorMarker;

public final class FacebookSdkNotInitializedException extends FacebookException {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final long serialVersionUID = 1;

    public FacebookSdkNotInitializedException(String str) {
        super(str);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
