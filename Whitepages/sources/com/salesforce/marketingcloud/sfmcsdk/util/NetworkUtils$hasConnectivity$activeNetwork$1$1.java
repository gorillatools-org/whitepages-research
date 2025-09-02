package com.salesforce.marketingcloud.sfmcsdk.util;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class NetworkUtils$hasConnectivity$activeNetwork$1$1 extends Lambda implements Function0 {
    public static final NetworkUtils$hasConnectivity$activeNetwork$1$1 INSTANCE = new NetworkUtils$hasConnectivity$activeNetwork$1$1();

    NetworkUtils$hasConnectivity$activeNetwork$1$1() {
        super(0);
    }

    public final String invoke() {
        return "Device has _no_ connectivity.";
    }
}
