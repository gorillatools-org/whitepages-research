package com.facebook.react.modules.i18nmanager;

import com.facebook.fbreact.specs.NativeI18nManagerSpec;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import java.util.Locale;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "I18nManager")
public final class I18nManagerModule extends NativeI18nManagerSpec {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String NAME = "I18nManager";

    public I18nManagerModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public Map<String, Object> getTypedExportedConstants() {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Locale locale = reactApplicationContext.getResources().getConfiguration().getLocales().get(0);
        I18nUtil.Companion companion = I18nUtil.Companion;
        I18nUtil instance = companion.getInstance();
        Intrinsics.checkNotNull(reactApplicationContext);
        return MapsKt.mapOf(TuplesKt.to("isRTL", Boolean.valueOf(instance.isRTL(reactApplicationContext))), TuplesKt.to("doLeftAndRightSwapInRTL", Boolean.valueOf(companion.getInstance().doLeftAndRightSwapInRTL(reactApplicationContext))), TuplesKt.to("localeIdentifier", locale.toString()));
    }

    public void allowRTL(boolean z) {
        I18nUtil instance = I18nUtil.Companion.getInstance();
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        instance.allowRTL(reactApplicationContext, z);
    }

    public void forceRTL(boolean z) {
        I18nUtil instance = I18nUtil.Companion.getInstance();
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        instance.forceRTL(reactApplicationContext, z);
    }

    public void swapLeftAndRightInRTL(boolean z) {
        I18nUtil instance = I18nUtil.Companion.getInstance();
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        instance.swapLeftAndRightInRTL(reactApplicationContext, z);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
