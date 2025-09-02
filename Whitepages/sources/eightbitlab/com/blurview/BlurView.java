package eightbitlab.com.blurview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class BlurView extends FrameLayout {
    private static final String TAG = "BlurView";
    BlurController blurController = new NoOpController();
    private int overlayColor;

    public BlurView(Context context) {
        super(context);
        init((AttributeSet) null, 0);
    }

    private void init(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.BlurView, i, 0);
        this.overlayColor = obtainStyledAttributes.getColor(R$styleable.BlurView_blurOverlayColor, 0);
        obtainStyledAttributes.recycle();
    }

    public void draw(Canvas canvas) {
        if (this.blurController.draw(canvas)) {
            super.draw(canvas);
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.blurController.updateBlurViewSize();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.blurController.setBlurAutoUpdate(false);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isHardwareAccelerated()) {
            Log.e(TAG, "BlurView can't be used in not hardware-accelerated window!");
        } else {
            this.blurController.setBlurAutoUpdate(true);
        }
    }

    public BlurViewFacade setupWith(ViewGroup viewGroup, BlurAlgorithm blurAlgorithm) {
        this.blurController.destroy();
        PreDrawBlurController preDrawBlurController = new PreDrawBlurController(this, viewGroup, this.overlayColor, blurAlgorithm);
        this.blurController = preDrawBlurController;
        return preDrawBlurController;
    }

    public BlurViewFacade setupWith(ViewGroup viewGroup) {
        return setupWith(viewGroup, getBlurAlgorithm());
    }

    public BlurViewFacade setBlurRadius(float f) {
        return this.blurController.setBlurRadius(f);
    }

    public BlurViewFacade setOverlayColor(int i) {
        this.overlayColor = i;
        return this.blurController.setOverlayColor(i);
    }

    public BlurViewFacade setBlurAutoUpdate(boolean z) {
        return this.blurController.setBlurAutoUpdate(z);
    }

    public BlurViewFacade setBlurEnabled(boolean z) {
        return this.blurController.setBlurEnabled(z);
    }

    private BlurAlgorithm getBlurAlgorithm() {
        if (Build.VERSION.SDK_INT >= 31) {
            return new RenderEffectBlur();
        }
        return new RenderScriptBlur(getContext());
    }
}
