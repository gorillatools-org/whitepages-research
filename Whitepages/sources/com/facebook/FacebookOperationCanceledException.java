package com.facebook;

import kotlin.jvm.internal.DefaultConstructorMarker;

public final class FacebookOperationCanceledException extends FacebookException {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final long serialVersionUID = 1;

    public FacebookOperationCanceledException() {
    }

    public FacebookOperationCanceledException(String str) {
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
