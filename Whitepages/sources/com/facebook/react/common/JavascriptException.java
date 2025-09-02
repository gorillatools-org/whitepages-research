package com.facebook.react.common;

import com.facebook.proguard.annotations.DoNotStrip;
import kotlin.jvm.internal.Intrinsics;

@DoNotStrip
public class JavascriptException extends RuntimeException {
    private String extraDataAsJson;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JavascriptException(String str) {
        super(str);
        Intrinsics.checkNotNullParameter(str, "jsStackTrace");
    }

    public final String getExtraDataAsJson() {
        return this.extraDataAsJson;
    }

    public final void setExtraDataAsJson(String str) {
        this.extraDataAsJson = str;
    }
}
