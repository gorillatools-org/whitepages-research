package com.salesforce.marketingcloud.sfmcsdk.components.http;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class NetworkManager$recordRetryAfter$1 extends Lambda implements Function0 {
    public static final NetworkManager$recordRetryAfter$1 INSTANCE = new NetworkManager$recordRetryAfter$1();

    NetworkManager$recordRetryAfter$1() {
        super(0);
    }

    public final String invoke() {
        return "Unable to parse Retry-After value.";
    }
}
