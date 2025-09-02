package eightbitlab.com.blurview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import eightbitlab.com.blurview.SizeScaler;

public final class PreDrawBlurController implements BlurController {
    private final BlurAlgorithm blurAlgorithm;
    private boolean blurEnabled = true;
    private float blurRadius = 16.0f;
    final View blurView;
    private final int[] blurViewLocation = new int[2];
    private final ViewTreeObserver.OnPreDrawListener drawListener = new ViewTreeObserver.OnPreDrawListener() {
        public boolean onPreDraw() {
            PreDrawBlurController.this.updateBlur();
            return true;
        }
    };
    private Drawable frameClearDrawable;
    private boolean initialized;
    private Bitmap internalBitmap;
    private BlurViewCanvas internalCanvas;
    private int overlayColor;
    private final int[] rootLocation = new int[2];
    private final ViewGroup rootView;

    public PreDrawBlurController(View view, ViewGroup viewGroup, int i, BlurAlgorithm blurAlgorithm2) {
        this.rootView = viewGroup;
        this.blurView = view;
        this.overlayColor = i;
        this.blurAlgorithm = blurAlgorithm2;
        if (blurAlgorithm2 instanceof RenderEffectBlur) {
            ((RenderEffectBlur) blurAlgorithm2).setContext(view.getContext());
        }
        init(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    /* access modifiers changed from: package-private */
    public void init(int i, int i2) {
        setBlurAutoUpdate(true);
        SizeScaler sizeScaler = new SizeScaler(this.blurAlgorithm.scaleFactor());
        if (sizeScaler.isZeroSized(i, i2)) {
            this.blurView.setWillNotDraw(true);
            return;
        }
        this.blurView.setWillNotDraw(false);
        SizeScaler.Size scale = sizeScaler.scale(i, i2);
        this.internalBitmap = Bitmap.createBitmap(scale.width, scale.height, this.blurAlgorithm.getSupportedBitmapConfig());
        this.internalCanvas = new BlurViewCanvas(this.internalBitmap);
        this.initialized = true;
        updateBlur();
    }

    /* access modifiers changed from: package-private */
    public void updateBlur() {
        if (this.blurEnabled && this.initialized) {
            Drawable drawable = this.frameClearDrawable;
            if (drawable == null) {
                this.internalBitmap.eraseColor(0);
            } else {
                drawable.draw(this.internalCanvas);
            }
            this.internalCanvas.save();
            setupInternalCanvasMatrix();
            this.rootView.draw(this.internalCanvas);
            this.internalCanvas.restore();
            blurAndSave();
        }
    }

    private void setupInternalCanvasMatrix() {
        this.rootView.getLocationOnScreen(this.rootLocation);
        this.blurView.getLocationOnScreen(this.blurViewLocation);
        int[] iArr = this.blurViewLocation;
        int i = iArr[0];
        int[] iArr2 = this.rootLocation;
        int i2 = i - iArr2[0];
        int i3 = iArr[1] - iArr2[1];
        float height = ((float) this.blurView.getHeight()) / ((float) this.internalBitmap.getHeight());
        float width = ((float) this.blurView.getWidth()) / ((float) this.internalBitmap.getWidth());
        this.internalCanvas.translate(((float) (-i2)) / width, ((float) (-i3)) / height);
        this.internalCanvas.scale(1.0f / width, 1.0f / height);
    }

    public boolean draw(Canvas canvas) {
        if (this.blurEnabled && this.initialized) {
            if (canvas instanceof BlurViewCanvas) {
                return false;
            }
            float height = ((float) this.blurView.getHeight()) / ((float) this.internalBitmap.getHeight());
            canvas.save();
            canvas.scale(((float) this.blurView.getWidth()) / ((float) this.internalBitmap.getWidth()), height);
            this.blurAlgorithm.render(canvas, this.internalBitmap);
            canvas.restore();
            int i = this.overlayColor;
            if (i != 0) {
                canvas.drawColor(i);
            }
        }
        return true;
    }

    private void blurAndSave() {
        this.internalBitmap = this.blurAlgorithm.blur(this.internalBitmap, this.blurRadius);
        if (!this.blurAlgorithm.canModifyBitmap()) {
            this.internalCanvas.setBitmap(this.internalBitmap);
        }
    }

    public void updateBlurViewSize() {
        init(this.blurView.getMeasuredWidth(), this.blurView.getMeasuredHeight());
    }

    public void destroy() {
        setBlurAutoUpdate(false);
        this.blurAlgorithm.destroy();
        this.initialized = false;
    }

    public BlurViewFacade setBlurRadius(float f) {
        this.blurRadius = f;
        return this;
    }

    public BlurViewFacade setFrameClearDrawable(Drawable drawable) {
        this.frameClearDrawable = drawable;
        return this;
    }

    public BlurViewFacade setBlurEnabled(boolean z) {
        this.blurEnabled = z;
        setBlurAutoUpdate(z);
        this.blurView.invalidate();
        return this;
    }

    public BlurViewFacade setBlurAutoUpdate(boolean z) {
        this.rootView.getViewTreeObserver().removeOnPreDrawListener(this.drawListener);
        if (z) {
            this.rootView.getViewTreeObserver().addOnPreDrawListener(this.drawListener);
        }
        return this;
    }

    public BlurViewFacade setOverlayColor(int i) {
        if (this.overlayColor != i) {
            this.overlayColor = i;
            this.blurView.invalidate();
        }
        return this;
    }
}
