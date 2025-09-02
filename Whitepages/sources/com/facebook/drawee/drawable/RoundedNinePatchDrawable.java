package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.drawable.NinePatchDrawable;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import kotlin.jvm.internal.Intrinsics;

public final class RoundedNinePatchDrawable extends RoundedDrawable {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RoundedNinePatchDrawable(NinePatchDrawable ninePatchDrawable) {
        super(ninePatchDrawable);
        Intrinsics.checkNotNullParameter(ninePatchDrawable, "ninePatchDrawable");
    }

    public void draw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("RoundedNinePatchDrawable#draw");
        }
        if (!shouldRound()) {
            super.draw(canvas);
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
                return;
            }
            return;
        }
        updateTransform();
        updatePath();
        canvas.clipPath(this.mPath);
        super.draw(canvas);
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
    }
}
