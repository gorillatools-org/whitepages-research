package com.salesforce.marketingcloud.sfmcsdk.util;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class ApplicationUtils$getApplicationName$1 extends Lambda implements Function0 {
    public static final ApplicationUtils$getApplicationName$1 INSTANCE = new ApplicationUtils$getApplicationName$1();

    ApplicationUtils$getApplicationName$1() {
        super(0);
    }

    public final String invoke() {
        return "Failed to get appName from the packageManager.";
    }
}
