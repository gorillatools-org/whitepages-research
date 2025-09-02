package com.salesforce.marketingcloud.sfmcsdk.components.http;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class NetworkManager$isBlockedByRetryAfter$1$1 extends Lambda implements Function0 {
    final /* synthetic */ String $requestName;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NetworkManager$isBlockedByRetryAfter$1$1(String str) {
        super(0);
        this.$requestName = str;
    }

    public final String invoke() {
        return "Route " + this.$requestName + " _blocked_ by Retry-After.";
    }
}
