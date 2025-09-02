package com.salesforce.marketingcloud.sfmcsdk.components.utils;

import java.util.concurrent.ExecutorService;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

public final class SdkExecutorsKt {
    public static final void namedRunnable(ExecutorService executorService, String str, Function0 function0) {
        Intrinsics.checkNotNullParameter(executorService, "<this>");
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(function0, "block");
        executorService.execute(new SdkExecutorsKt$namedRunnable$1(str, function0, new Object[0]));
    }
}
