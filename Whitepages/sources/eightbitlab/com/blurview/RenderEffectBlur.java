package eightbitlab.com.blurview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RenderEffect;
import android.graphics.RenderNode;
import android.graphics.Shader;

public class RenderEffectBlur implements BlurAlgorithm {
    private Context context;
    public BlurAlgorithm fallbackAlgorithm;
    private int height;
    private float lastBlurRadius = 1.0f;
    private final RenderNode node = RenderEffectBlur$$ExternalSyntheticApiModelOutline6.m("BlurViewNode");
    private int width;

    public boolean canModifyBitmap() {
        return true;
    }

    public float scaleFactor() {
        return 6.0f;
    }

    public Bitmap blur(Bitmap bitmap, float f) {
        this.lastBlurRadius = f;
        if (!(bitmap.getHeight() == this.height && bitmap.getWidth() == this.width)) {
            this.height = bitmap.getHeight();
            int width2 = bitmap.getWidth();
            this.width = width2;
            boolean unused = this.node.setPosition(0, 0, width2, this.height);
        }
        this.node.beginRecording().drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        this.node.endRecording();
        boolean unused2 = this.node.setRenderEffect(RenderEffect.createBlurEffect(f, f, Shader.TileMode.MIRROR));
        return bitmap;
    }

    public void destroy() {
        this.node.discardDisplayList();
        BlurAlgorithm blurAlgorithm = this.fallbackAlgorithm;
        if (blurAlgorithm != null) {
            blurAlgorithm.destroy();
        }
    }

    public Bitmap.Config getSupportedBitmapConfig() {
        return Bitmap.Config.ARGB_8888;
    }

    public void render(Canvas canvas, Bitmap bitmap) {
        if (canvas.isHardwareAccelerated()) {
            canvas.drawRenderNode(this.node);
            return;
        }
        if (this.fallbackAlgorithm == null) {
            this.fallbackAlgorithm = new RenderScriptBlur(this.context);
        }
        this.fallbackAlgorithm.blur(bitmap, this.lastBlurRadius);
        this.fallbackAlgorithm.render(canvas, bitmap);
    }

    /* access modifiers changed from: package-private */
    public void setContext(Context context2) {
        this.context = context2;
    }
}
