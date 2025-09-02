package com.salesforce.marketingcloud.sfmcsdk.components.behaviors;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class LifecycleListener$onStart$1 extends Lambda implements Function0 {
    public static final LifecycleListener$onStart$1 INSTANCE = new LifecycleListener$onStart$1();

    LifecycleListener$onStart$1() {
        super(0);
    }

    public final String invoke() {
        return "Application came into the foreground.";
    }
}
