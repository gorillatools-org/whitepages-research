package com.facebook.react.modules.devtoolsruntimesettings;

import com.facebook.fbreact.specs.NativeReactDevToolsRuntimeSettingsModuleSpec;
import com.facebook.jni.annotations.DoNotStripAny;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@DoNotStripAny
@ReactModule(name = "ReactDevToolsRuntimeSettingsModule")
public final class ReactDevToolsRuntimeSettingsModule extends NativeReactDevToolsRuntimeSettingsModuleSpec {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String NAME = "ReactDevToolsRuntimeSettingsModule";
    private static final Settings settings = new Settings();

    public ReactDevToolsRuntimeSettingsModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public void setReloadAndProfileConfig(ReadableMap readableMap) {
        Intrinsics.checkNotNullParameter(readableMap, "map");
        if (readableMap.hasKey("shouldReloadAndProfile")) {
            settings.setShouldReloadAndProfile(readableMap.getBoolean("shouldReloadAndProfile"));
        }
        if (readableMap.hasKey("recordChangeDescriptions")) {
            settings.setRecordChangeDescriptions(readableMap.getBoolean("recordChangeDescriptions"));
        }
    }

    public WritableMap getReloadAndProfileConfig() {
        WritableMap createMap = Arguments.createMap();
        Settings settings2 = settings;
        createMap.putBoolean("shouldReloadAndProfile", settings2.getShouldReloadAndProfile());
        createMap.putBoolean("recordChangeDescriptions", settings2.getRecordChangeDescriptions());
        Intrinsics.checkNotNull(createMap);
        return createMap;
    }
}
