package com.salesforce.marketingcloud.sfmcsdk.components.http;

import java.nio.charset.Charset;
import kotlin.jvm.internal.Intrinsics;

public final class RequestKt {
    private static final Charset UTF_8;

    static {
        Charset forName = Charset.forName("UTF-8");
        Intrinsics.checkNotNullExpressionValue(forName, "<clinit>");
        UTF_8 = forName;
    }

    public static final Charset getUTF_8() {
        return UTF_8;
    }
}
