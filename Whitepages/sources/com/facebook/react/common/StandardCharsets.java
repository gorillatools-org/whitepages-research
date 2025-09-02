package com.facebook.react.common;

import java.nio.charset.Charset;
import kotlin.jvm.internal.Intrinsics;

public final class StandardCharsets {
    public static final StandardCharsets INSTANCE = new StandardCharsets();
    public static final Charset UTF_16;
    public static final Charset UTF_16BE;
    public static final Charset UTF_16LE;
    public static final Charset UTF_8;

    private StandardCharsets() {
    }

    static {
        Charset forName = Charset.forName("UTF-8");
        Intrinsics.checkNotNullExpressionValue(forName, "forName(...)");
        UTF_8 = forName;
        Charset forName2 = Charset.forName("UTF-16");
        Intrinsics.checkNotNullExpressionValue(forName2, "forName(...)");
        UTF_16 = forName2;
        Charset forName3 = Charset.forName("UTF-16BE");
        Intrinsics.checkNotNullExpressionValue(forName3, "forName(...)");
        UTF_16BE = forName3;
        Charset forName4 = Charset.forName("UTF-16LE");
        Intrinsics.checkNotNullExpressionValue(forName4, "forName(...)");
        UTF_16LE = forName4;
    }
}
