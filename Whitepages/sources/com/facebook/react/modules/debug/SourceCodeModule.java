package com.facebook.react.modules.debug;

import com.facebook.fbreact.specs.NativeSourceCodeSpec;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.annotations.ReactModule;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "SourceCode")
public final class SourceCodeModule extends NativeSourceCodeSpec {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SourceCodeModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
    }

    /* access modifiers changed from: protected */
    public Map<String, Object> getTypedExportedConstants() {
        return MapsKt.mapOf(TuplesKt.to("scriptURL", Assertions.assertNotNull(getReactApplicationContext().getSourceURL(), "No source URL loaded, have you initialised the instance?")));
    }
}
