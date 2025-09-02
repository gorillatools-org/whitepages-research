package com.th3rdwave.safeareacontext;

import android.content.Context;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.EventDispatcher;
import kotlin.jvm.internal.Intrinsics;

public abstract class SafeAreaProviderManagerKt {
    /* access modifiers changed from: private */
    public static final void handleOnInsetsChange(SafeAreaProvider safeAreaProvider, EdgeInsets edgeInsets, Rect rect) {
        Context context = safeAreaProvider.getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
        ReactContext reactContext = (ReactContext) context;
        int id = safeAreaProvider.getId();
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(reactContext, id);
        if (eventDispatcherForReactTag != null) {
            eventDispatcherForReactTag.dispatchEvent(new InsetsChangeEvent(UIManagerHelperCompatKt.getSurfaceId(reactContext), id, edgeInsets, rect));
        }
    }
}
