package com.swmansion.rnscreens;

import com.facebook.react.bridge.ReadableArray;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class ScreenViewManager$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ ReadableArray f$0;

    public /* synthetic */ ScreenViewManager$$ExternalSyntheticLambda0(ReadableArray readableArray) {
        this.f$0 = readableArray;
    }

    public final Object invoke(Object obj) {
        return Double.valueOf(ScreenViewManager.setSheetAllowedDetents$lambda$0(this.f$0, ((Integer) obj).intValue()));
    }
}
