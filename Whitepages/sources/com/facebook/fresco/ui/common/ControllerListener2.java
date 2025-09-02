package com.facebook.fresco.ui.common;

import android.net.Uri;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;

public interface ControllerListener2 {
    void onFailure(String str, Throwable th, Extras extras);

    void onFinalImageSet(String str, Object obj, Extras extras);

    void onIntermediateImageFailed(String str);

    void onIntermediateImageSet(String str, Object obj);

    void onRelease(String str, Extras extras);

    void onSubmit(String str, Object obj, Extras extras);

    public static final class Extras {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        public Object callerContext;
        public Map componentExtras;
        public Map datasourceExtras;
        public Float focusX;
        public Float focusY;
        public Map imageExtras;
        public Map imageSourceExtras;
        public boolean logWithHighSamplingRate;
        public Uri mainUri;
        public Object scaleType;
        public Map shortcutExtras;
        public int viewportHeight = -1;
        public int viewportWidth = -1;

        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }
    }
}
