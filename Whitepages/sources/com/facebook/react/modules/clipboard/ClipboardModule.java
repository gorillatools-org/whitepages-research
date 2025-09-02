package com.facebook.react.modules.clipboard;

import android.content.ClipData;
import android.content.ClipboardManager;
import com.facebook.fbreact.specs.NativeClipboardSpec;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.annotations.ReactModule;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "Clipboard")
public final class ClipboardModule extends NativeClipboardSpec {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String NAME = "Clipboard";

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ClipboardModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        Intrinsics.checkNotNullParameter(reactApplicationContext, "context");
    }

    private final ClipboardManager getClipboardService() {
        Object systemService = getReactApplicationContext().getSystemService("clipboard");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.content.ClipboardManager");
        return (ClipboardManager) systemService;
    }

    public void getString(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        try {
            ClipData primaryClip = getClipboardService().getPrimaryClip();
            if (primaryClip == null || primaryClip.getItemCount() < 1) {
                promise.resolve("");
            } else {
                promise.resolve(String.valueOf(primaryClip.getItemAt(0).getText()));
            }
        } catch (Exception e) {
            promise.reject((Throwable) e);
        }
    }

    public void setString(String str) {
        ClipData newPlainText = ClipData.newPlainText((CharSequence) null, str);
        Intrinsics.checkNotNullExpressionValue(newPlainText, "newPlainText(...)");
        getClipboardService().setPrimaryClip(newPlainText);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
