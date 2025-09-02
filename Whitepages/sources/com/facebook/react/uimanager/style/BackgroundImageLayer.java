package com.facebook.react.uimanager.style;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Shader;
import com.facebook.react.bridge.ReadableMap;
import kotlin.jvm.internal.Intrinsics;

public final class BackgroundImageLayer {
    private final Gradient gradient;

    public BackgroundImageLayer(ReadableMap readableMap, Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Gradient gradient2 = null;
        if (readableMap != null) {
            try {
                gradient2 = new Gradient(readableMap, context);
            } catch (IllegalArgumentException unused) {
            }
        }
        this.gradient = gradient2;
    }

    public final Shader getShader(Rect rect) {
        Intrinsics.checkNotNullParameter(rect, "bounds");
        Gradient gradient2 = this.gradient;
        if (gradient2 != null) {
            return gradient2.getShader(rect);
        }
        return null;
    }
}
