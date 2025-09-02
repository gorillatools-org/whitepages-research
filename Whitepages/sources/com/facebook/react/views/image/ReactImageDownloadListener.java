package com.facebook.react.views.image;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.drawable.ForwardingDrawable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public class ReactImageDownloadListener<INFO> extends ForwardingDrawable implements ControllerListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int MAX_LEVEL = 10000;

    private static final class EmptyDrawable extends Drawable {
        public void draw(Canvas canvas) {
            Intrinsics.checkNotNullParameter(canvas, "canvas");
        }

        public int getOpacity() {
            return -1;
        }

        public void setAlpha(int i) {
        }

        public void setColorFilter(ColorFilter colorFilter) {
        }
    }

    public void onFailure(String str, Throwable th) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(th, "throwable");
    }

    public void onFinalImageSet(String str, INFO info, Animatable animatable) {
        Intrinsics.checkNotNullParameter(str, "id");
    }

    public void onIntermediateImageFailed(String str, Throwable th) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(th, "throwable");
    }

    public void onIntermediateImageSet(String str, INFO info) {
        Intrinsics.checkNotNullParameter(str, "id");
    }

    public void onProgressChange(int i, int i2) {
    }

    public void onRelease(String str) {
        Intrinsics.checkNotNullParameter(str, "id");
    }

    public void onSubmit(String str, Object obj) {
        Intrinsics.checkNotNullParameter(str, "id");
    }

    public ReactImageDownloadListener() {
        super(new EmptyDrawable());
    }

    /* access modifiers changed from: protected */
    public boolean onLevelChange(int i) {
        onProgressChange(i, MAX_LEVEL);
        return super.onLevelChange(i);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
