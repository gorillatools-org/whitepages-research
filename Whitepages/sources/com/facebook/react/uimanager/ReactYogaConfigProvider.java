package com.facebook.react.uimanager;

import com.facebook.yoga.YogaConfig;
import com.facebook.yoga.YogaConfigFactory;
import com.facebook.yoga.YogaErrata;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.internal.Intrinsics;

public final class ReactYogaConfigProvider {
    public static final ReactYogaConfigProvider INSTANCE = new ReactYogaConfigProvider();
    private static final Lazy yogaConfig$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new ReactYogaConfigProvider$$ExternalSyntheticLambda0());

    private ReactYogaConfigProvider() {
    }

    private final YogaConfig getYogaConfig() {
        Object value = yogaConfig$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (YogaConfig) value;
    }

    /* access modifiers changed from: private */
    public static final YogaConfig yogaConfig_delegate$lambda$0() {
        YogaConfig create = YogaConfigFactory.create();
        create.setPointScaleFactor(0.0f);
        create.setErrata(YogaErrata.ALL);
        return create;
    }

    public static final YogaConfig get() {
        return INSTANCE.getYogaConfig();
    }
}
