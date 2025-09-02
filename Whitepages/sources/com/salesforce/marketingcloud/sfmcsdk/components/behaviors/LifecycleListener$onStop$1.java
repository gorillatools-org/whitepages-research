package com.salesforce.marketingcloud.sfmcsdk.components.behaviors;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class LifecycleListener$onStop$1 extends Lambda implements Function0 {
    public static final LifecycleListener$onStop$1 INSTANCE = new LifecycleListener$onStop$1();

    LifecycleListener$onStop$1() {
        super(0);
    }

    public final String invoke() {
        return "Application went into the background.";
    }
}
