package com.salesforce.marketingcloud.sfmcsdk;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class SFMCSdk$Companion$configure$1$3$3$1 extends Lambda implements Function0 {
    final /* synthetic */ boolean $this_run;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SFMCSdk$Companion$configure$1$3$3$1(boolean z) {
        super(0);
        this.$this_run = z;
    }

    public final String invoke() {
        StringBuilder sb = new StringBuilder();
        sb.append("Module init latch time exceeded: ");
        sb.append(!this.$this_run);
        return sb.toString();
    }
}
