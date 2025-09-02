package com.wpconnect;

import com.facebook.react.bridge.ReactContext;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class CallerIdOverlayService$$ExternalSyntheticLambda2 implements Function1 {
    public final /* synthetic */ String f$0;

    public /* synthetic */ CallerIdOverlayService$$ExternalSyntheticLambda2(String str) {
        this.f$0 = str;
    }

    public final Object invoke(Object obj) {
        return CallerIdOverlayService.onStartCommand$lambda$4(this.f$0, (ReactContext) obj);
    }
}
