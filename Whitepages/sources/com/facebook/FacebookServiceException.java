package com.facebook;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class FacebookServiceException extends FacebookException {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final long serialVersionUID = 1;
    private final FacebookRequestError requestError;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FacebookServiceException(FacebookRequestError facebookRequestError, String str) {
        super(str);
        Intrinsics.checkNotNullParameter(facebookRequestError, "requestError");
        this.requestError = facebookRequestError;
    }

    public final FacebookRequestError getRequestError() {
        return this.requestError;
    }

    public String toString() {
        String str = "{FacebookServiceException: " + "httpResponseCode: " + this.requestError.getRequestStatusCode() + ", facebookErrorCode: " + this.requestError.getErrorCode() + ", facebookErrorType: " + this.requestError.getErrorType() + ", message: " + this.requestError.getErrorMessage() + "}";
        Intrinsics.checkNotNullExpressionValue(str, "StringBuilder()\n        …(\"}\")\n        .toString()");
        return str;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
