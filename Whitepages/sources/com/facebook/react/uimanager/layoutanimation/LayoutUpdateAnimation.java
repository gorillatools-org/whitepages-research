package com.facebook.react.uimanager.layoutanimation;

import android.view.View;
import android.view.animation.Animation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class LayoutUpdateAnimation extends AbstractLayoutAnimation {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final boolean USE_TRANSLATE_ANIMATION = false;

    /* renamed from: isValid$ReactAndroid_release */
    public boolean isValid() {
        return this.mDurationMs > 0;
    }

    /* renamed from: createAnimationImpl$ReactAndroid_release */
    public Animation createAnimationImpl(View view, int i, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(view, "view");
        boolean z = true;
        boolean z2 = (((int) view.getX()) == i && ((int) view.getY()) == i2) ? false : true;
        if (view.getWidth() == i3 && view.getHeight() == i4) {
            z = false;
        }
        if (z2 || z) {
            return new PositionAndSizeAnimation(view, i, i2, i3, i4);
        }
        return null;
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
