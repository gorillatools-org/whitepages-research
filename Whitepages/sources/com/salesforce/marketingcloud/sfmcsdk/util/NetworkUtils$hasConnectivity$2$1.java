package com.salesforce.marketingcloud.sfmcsdk.util;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class NetworkUtils$hasConnectivity$2$1 extends Lambda implements Function0 {
    public static final NetworkUtils$hasConnectivity$2$1 INSTANCE = new NetworkUtils$hasConnectivity$2$1();

    NetworkUtils$hasConnectivity$2$1() {
        super(0);
    }

    public final String invoke() {
        return "Device has _no_ connectivity.";
    }
}
