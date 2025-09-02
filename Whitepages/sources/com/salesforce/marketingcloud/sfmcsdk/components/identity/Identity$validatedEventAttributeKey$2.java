package com.salesforce.marketingcloud.sfmcsdk.components.identity;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class Identity$validatedEventAttributeKey$2 extends Lambda implements Function0 {
    final /* synthetic */ String $key;
    final /* synthetic */ String $reservedAttributeKeys;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Identity$validatedEventAttributeKey$2(String str, String str2) {
        super(0);
        this.$key = str;
        this.$reservedAttributeKeys = str2;
    }

    public final String invoke() {
        return "Key '" + this.$key + "' is reserved. The key value pair was dropped. Other reserved keys: " + this.$reservedAttributeKeys;
    }
}
