package com.salesforce.marketingcloud.sfmcsdk.components.events;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class EventManager$Companion$getValidatedName$1$1 extends Lambda implements Function0 {
    final /* synthetic */ String $input;
    final /* synthetic */ String $prefix;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    EventManager$Companion$getValidatedName$1$1(String str, String str2) {
        super(0);
        this.$prefix = str;
        this.$input = str2;
    }

    public final String invoke() {
        return this.$prefix + " '" + this.$input + "' is null, blank, starts with a \"$\", or contains a line break and will be dropped.";
    }
}
