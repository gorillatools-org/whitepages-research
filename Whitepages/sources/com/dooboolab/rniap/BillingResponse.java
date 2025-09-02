package com.dooboolab.rniap;

import com.facebook.react.devsupport.StackTraceHelper;
import kotlin.jvm.internal.Intrinsics;

public final class BillingResponse {
    private final String code;
    private final String message;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BillingResponse)) {
            return false;
        }
        BillingResponse billingResponse = (BillingResponse) obj;
        return Intrinsics.areEqual((Object) this.code, (Object) billingResponse.code) && Intrinsics.areEqual((Object) this.message, (Object) billingResponse.message);
    }

    public int hashCode() {
        return (this.code.hashCode() * 31) + this.message.hashCode();
    }

    public String toString() {
        return "BillingResponse(code=" + this.code + ", message=" + this.message + ')';
    }

    public BillingResponse(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "code");
        Intrinsics.checkNotNullParameter(str2, StackTraceHelper.MESSAGE_KEY);
        this.code = str;
        this.message = str2;
    }

    public final String getCode() {
        return this.code;
    }

    public final String getMessage() {
        return this.message;
    }
}
