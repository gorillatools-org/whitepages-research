package com.salesforce.marketingcloud.sfmcsdk.util;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class ApplicationUtils$getApplicationVersion$1 extends Lambda implements Function0 {
    public static final ApplicationUtils$getApplicationVersion$1 INSTANCE = new ApplicationUtils$getApplicationVersion$1();

    ApplicationUtils$getApplicationVersion$1() {
        super(0);
    }

    public final String invoke() {
        return "Failed to get VERSION_NAME from the application's BuildConfig.";
    }
}
