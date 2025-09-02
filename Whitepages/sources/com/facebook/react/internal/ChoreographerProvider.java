package com.facebook.react.internal;

import android.view.Choreographer;

public interface ChoreographerProvider {

    public interface Choreographer {
        void postFrameCallback(Choreographer.FrameCallback frameCallback);

        void removeFrameCallback(Choreographer.FrameCallback frameCallback);
    }

    Choreographer getChoreographer();
}
