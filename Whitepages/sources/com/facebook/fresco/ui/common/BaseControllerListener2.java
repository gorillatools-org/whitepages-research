package com.facebook.fresco.ui.common;

import com.facebook.fresco.ui.common.ControllerListener2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public class BaseControllerListener2 implements ControllerListener2 {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final ControllerListener2 NO_OP_LISTENER = new BaseControllerListener2();

    public void onFailure(String str, Throwable th, ControllerListener2.Extras extras) {
        Intrinsics.checkNotNullParameter(str, "id");
    }

    public void onFinalImageSet(String str, Object obj, ControllerListener2.Extras extras) {
        Intrinsics.checkNotNullParameter(str, "id");
    }

    public void onIntermediateImageFailed(String str) {
        Intrinsics.checkNotNullParameter(str, "id");
    }

    public void onIntermediateImageSet(String str, Object obj) {
        Intrinsics.checkNotNullParameter(str, "id");
    }

    public void onRelease(String str, ControllerListener2.Extras extras) {
        Intrinsics.checkNotNullParameter(str, "id");
    }

    public void onSubmit(String str, Object obj, ControllerListener2.Extras extras) {
        Intrinsics.checkNotNullParameter(str, "id");
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
