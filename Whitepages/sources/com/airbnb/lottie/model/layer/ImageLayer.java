package com.airbnb.lottie.model.layer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieImageAsset;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.DropShadowKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;

public class ImageLayer extends BaseLayer {
    private BaseKeyframeAnimation colorFilterAnimation;
    private DropShadowKeyframeAnimation dropShadowAnimation;
    private final Rect dst = new Rect();
    private BaseKeyframeAnimation imageAnimation;
    private final LottieImageAsset lottieImageAsset;
    private final Paint paint = new LPaint(3);
    private final Rect src = new Rect();

    ImageLayer(LottieDrawable lottieDrawable, Layer layer) {
        super(lottieDrawable, layer);
        this.lottieImageAsset = lottieDrawable.getLottieImageAssetForId(layer.getRefId());
        if (getDropShadowEffect() != null) {
            this.dropShadowAnimation = new DropShadowKeyframeAnimation(this, this, getDropShadowEffect());
        }
    }

    public void drawLayer(Canvas canvas, Matrix matrix, int i) {
        Bitmap bitmap = getBitmap();
        if (bitmap != null && !bitmap.isRecycled() && this.lottieImageAsset != null) {
            float dpScale = Utils.dpScale();
            this.paint.setAlpha(i);
            BaseKeyframeAnimation baseKeyframeAnimation = this.colorFilterAnimation;
            if (baseKeyframeAnimation != null) {
                this.paint.setColorFilter((ColorFilter) baseKeyframeAnimation.getValue());
            }
            canvas.save();
            canvas.concat(matrix);
            this.src.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
            if (this.lottieDrawable.getMaintainOriginalImageBounds()) {
                this.dst.set(0, 0, (int) (((float) this.lottieImageAsset.getWidth()) * dpScale), (int) (((float) this.lottieImageAsset.getHeight()) * dpScale));
            } else {
                this.dst.set(0, 0, (int) (((float) bitmap.getWidth()) * dpScale), (int) (((float) bitmap.getHeight()) * dpScale));
            }
            DropShadowKeyframeAnimation dropShadowKeyframeAnimation = this.dropShadowAnimation;
            if (dropShadowKeyframeAnimation != null) {
                dropShadowKeyframeAnimation.applyTo(this.paint, matrix, i);
            }
            canvas.drawBitmap(bitmap, this.src, this.dst, this.paint);
            canvas.restore();
        }
    }

    public void getBounds(RectF rectF, Matrix matrix, boolean z) {
        super.getBounds(rectF, matrix, z);
        if (this.lottieImageAsset != null) {
            float dpScale = Utils.dpScale();
            rectF.set(0.0f, 0.0f, ((float) this.lottieImageAsset.getWidth()) * dpScale, ((float) this.lottieImageAsset.getHeight()) * dpScale);
            this.boundsMatrix.mapRect(rectF);
        }
    }

    private Bitmap getBitmap() {
        Bitmap bitmap;
        BaseKeyframeAnimation baseKeyframeAnimation = this.imageAnimation;
        if (baseKeyframeAnimation != null && (bitmap = (Bitmap) baseKeyframeAnimation.getValue()) != null) {
            return bitmap;
        }
        Bitmap bitmapForId = this.lottieDrawable.getBitmapForId(this.layerModel.getRefId());
        if (bitmapForId != null) {
            return bitmapForId;
        }
        LottieImageAsset lottieImageAsset2 = this.lottieImageAsset;
        if (lottieImageAsset2 != null) {
            return lottieImageAsset2.getBitmap();
        }
        return null;
    }

    public void addValueCallback(Object obj, LottieValueCallback lottieValueCallback) {
        super.addValueCallback(obj, lottieValueCallback);
        if (obj == LottieProperty.COLOR_FILTER) {
            if (lottieValueCallback == null) {
                this.colorFilterAnimation = null;
            } else {
                this.colorFilterAnimation = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            }
        } else if (obj != LottieProperty.IMAGE) {
        } else {
            if (lottieValueCallback == null) {
                this.imageAnimation = null;
            } else {
                this.imageAnimation = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            }
        }
    }
}
