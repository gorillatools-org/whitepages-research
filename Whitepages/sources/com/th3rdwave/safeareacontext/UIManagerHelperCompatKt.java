package com.th3rdwave.safeareacontext;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import com.facebook.react.bridge.ReactContext;
import kotlin.jvm.internal.Intrinsics;

public abstract class UIManagerHelperCompatKt {
    public static final int getSurfaceId(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return -1;
    }

    public static final ReactContext getReactContext(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        Context context = view.getContext();
        if (!(context instanceof ReactContext) && (context instanceof ContextWrapper)) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
        return (ReactContext) context;
    }
}
