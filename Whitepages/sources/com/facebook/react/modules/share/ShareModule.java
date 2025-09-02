package com.facebook.react.modules.share;

import android.app.Activity;
import android.content.Intent;
import com.facebook.fbreact.specs.NativeShareModuleSpec;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.devsupport.StackTraceHelper;
import com.facebook.react.module.annotations.ReactModule;
import com.salesforce.marketingcloud.UrlHandler;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "ShareModule")
public final class ShareModule extends NativeShareModuleSpec {
    private static final String ACTION_SHARED = "sharedAction";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String ERROR_INVALID_CONTENT = "E_INVALID_CONTENT";
    private static final String ERROR_UNABLE_TO_OPEN_DIALOG = "E_UNABLE_TO_OPEN_DIALOG";
    public static final String NAME = "ShareModule";

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ShareModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
    }

    public void share(ReadableMap readableMap, String str, Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        if (readableMap == null) {
            promise.reject(ERROR_INVALID_CONTENT, "Content cannot be null");
            return;
        }
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setTypeAndNormalize("text/plain");
            if (readableMap.hasKey("title")) {
                intent.putExtra("android.intent.extra.SUBJECT", readableMap.getString("title"));
            }
            if (readableMap.hasKey(StackTraceHelper.MESSAGE_KEY)) {
                intent.putExtra("android.intent.extra.TEXT", readableMap.getString(StackTraceHelper.MESSAGE_KEY));
            }
            Intent createChooser = Intent.createChooser(intent, str);
            createChooser.addCategory("android.intent.category.DEFAULT");
            Activity currentActivity = getCurrentActivity();
            if (currentActivity != null) {
                currentActivity.startActivity(createChooser);
            } else {
                getReactApplicationContext().startActivity(createChooser);
            }
            WritableMap createMap = Arguments.createMap();
            createMap.putString(UrlHandler.ACTION, ACTION_SHARED);
            promise.resolve(createMap);
        } catch (Exception unused) {
            promise.reject(ERROR_UNABLE_TO_OPEN_DIALOG, "Failed to open share dialog");
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
