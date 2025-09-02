package com.salesforce.marketingcloud.sfmcsdk.components.utils;

import kotlin.jvm.functions.Function0;

public final class SdkExecutorsKt$namedRunnable$1 extends NamedRunnable {
    final /* synthetic */ Function0 $block;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SdkExecutorsKt$namedRunnable$1(String str, Function0 function0, Object[] objArr) {
        super(str, objArr);
        this.$block = function0;
    }

    /* access modifiers changed from: protected */
    public void execute() {
        this.$block.invoke();
    }
}
