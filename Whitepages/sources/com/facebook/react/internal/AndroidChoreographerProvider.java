package com.facebook.react.internal;

import android.view.Choreographer;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.internal.ChoreographerProvider;
import kotlin.jvm.internal.Intrinsics;

public final class AndroidChoreographerProvider implements ChoreographerProvider {
    public static final AndroidChoreographerProvider INSTANCE = new AndroidChoreographerProvider();

    private AndroidChoreographerProvider() {
    }

    private static final class AndroidChoreographer implements ChoreographerProvider.Choreographer {
        private final Choreographer instance;

        public AndroidChoreographer() {
            Choreographer instance2 = Choreographer.getInstance();
            Intrinsics.checkNotNullExpressionValue(instance2, "getInstance(...)");
            this.instance = instance2;
        }

        public void postFrameCallback(Choreographer.FrameCallback frameCallback) {
            Intrinsics.checkNotNullParameter(frameCallback, "callback");
            this.instance.postFrameCallback(frameCallback);
        }

        public void removeFrameCallback(Choreographer.FrameCallback frameCallback) {
            Intrinsics.checkNotNullParameter(frameCallback, "callback");
            this.instance.removeFrameCallback(frameCallback);
        }
    }

    public static final AndroidChoreographerProvider getInstance() {
        return INSTANCE;
    }

    public ChoreographerProvider.Choreographer getChoreographer() {
        UiThreadUtil.assertOnUiThread();
        return new AndroidChoreographer();
    }
}
